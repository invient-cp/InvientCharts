/*
 * Copyright 2011 Invient (www.invient.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.invient.vaadin.charts;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.Component;

import com.invient.vaadin.charts.InvientCharts.SeriesCUR.SeriesCURType;
import com.invient.vaadin.charts.InvientChartsConfig.Axis;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.PlotBand;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.PlotLine;
import com.invient.vaadin.charts.InvientChartsConfig.BaseLineConfig;
import com.invient.vaadin.charts.InvientChartsConfig.PointConfig;
import com.invient.vaadin.charts.InvientChartsConfig.SeriesConfig;
import com.invient.vaadin.charts.InvientChartsConfig.SubTitle;
import com.invient.vaadin.charts.InvientChartsConfig.Title;
import com.invient.vaadin.charts.InvientChartsConfig.XAxis;
import com.invient.vaadin.charts.InvientChartsConfig.YAxis;
import com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts;

/**
 * A Vaddin component representing charts. It is a the main class of
 * InvientCharts library.
 * 
 * A chart typically contains one or more series of same or different types.
 * This class allows us to specify series of different types say line and pie
 * and hence it makes it easy to build a combination chart.
 * 
 * After a chart {@link InvientCharts} is created, the following changes to the
 * chart will be reflected rendered on the webkit.
 * <ul>
 * <li>Set or update chart {@link Title} and/or {@link SubTitle}</li>
 * <li>Modify chart size</li>
 * <li>Add, update and remove one or more instances of {@link PlotBand} and
 * {@link PlotLine}</li>
 * <li>Set or update axis categories</li>
 * <li>Set or update axis min and max values</li>
 * <li>Add, update and remove one or more instances of {@link Series}</li>
 * <li>Show or hide one or more instances of {@link Series}</li>
 * <li>Add and remove one or more instances of {@link Point}</li>
 * <li>Register and unregister event listeners</li>
 * </ul>
 * 
 * @author Invient
 * 
 */
@SuppressWarnings("serial")
@ClientWidget(VInvientCharts.class)
public class InvientCharts extends AbstractComponent {

    private InvientChartsConfig chartConfig;
    private boolean isRetrieveSVG = false;
    private boolean isPrint = false;

    /**
     * Creates this chart object with given chart configuration
     * 
     * @param chartConfig
     */
    public InvientCharts(InvientChartsConfig chartConfig) {
        if (chartConfig == null) {
            throw new IllegalArgumentException(
                    "The chart cannot be created without chartConfig argument.");
        }
        this.chartConfig = chartConfig;
        this.chartConfig.setInvientCharts(this);
    }

    /**
     * Returns chart configuration object
     * 
     * @return Returns chart configuration object
     */
    public InvientChartsConfig getConfig() {
        return this.chartConfig;
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);
        // Update all series with reference of x and y axis.
        this.setAxisInAllSeriesIfNotSetAlready();
        // Configurations options
        target.startTag("options");
        if (chartConfig != null) {
            InvientChartsUtil.writeTitleConfig(target, chartConfig.getTitle());
            InvientChartsUtil.writeSubtitleConfig(target,
                    chartConfig.getSubtitle());
            InvientChartsUtil
                    .writeCreditConfig(target, chartConfig.getCredit());
            InvientChartsUtil
                    .writeLegendConfig(target, chartConfig.getLegend());
            InvientChartsUtil.writeTooltipConfig(target,
                    chartConfig.getTooltip());
            InvientChartsUtil.writeGeneralChartConfig(target,
                    chartConfig.getGeneralChartConfig());
            InvientChartsUtil.writeSeriesConfigPerSeriesType(target,
                    chartConfig.getSeriesConfig());
            InvientChartsUtil.writeXAxes(target, chartConfig.getXAxes(),
                    chartConfig);
            InvientChartsUtil.writeYAxes(target, chartConfig.getYAxes(),
                    chartConfig);
            InvientChartsUtil.writeChartLabelConfig(target,
                    chartConfig.getChartLabel());
        }
        target.endTag("options");

        target.startTag("chartData");
        InvientChartsUtil.writeSeries(target, chartConfig
                .getGeneralChartConfig().getType(), this.chartSeries,
                chartConfig.getXAxes(), chartConfig.getYAxes());
        target.endTag("chartData");
        // A flag to indicate whether to retrieve svg from client or not.
        target.addAttribute("isRetrieveSVG", isRetrieveSVG);
        // A flag to indicate whether to prompt print dialog on client or not
        target.addAttribute("isPrint", isPrint);

        // Events
        target.startTag("events");
        // Chart Events
        paintChartEvents(target);
        // Series/Point Events
        paintSeriesAndPointEvents(target);
        target.endTag("events");

        // If the flag reloadChartData is true then the
        // client will ignore seriesOperations and
        // remove all existing series of a chart and
        // add series info received from the server.
        target.addAttribute("reloadChartSeries", reloadChartSeries);
        target.startTag("chartDataUpdates");
        if (!reloadChartSeries) {
            InvientChartsUtil.writeChartDataUpdates(target, seriesCURMap);
        }
        target.endTag("chartDataUpdates");
        // reset flag
        reloadChartSeries = false;
        // reset series operations
        seriesCURMap.clear();
        // reset to not retrieve svg when other updates on the chart occurs.
        // The svg is retrieved only when a svg available listener is registered
        // on this chart
        isRetrieveSVG = false;
        isPrint = false;
    }

    private void paintChartEvents(PaintTarget target) throws PaintException {
        target.startTag("chartEvents");
        if (chartAddSeriesListener != null && chartAddSeriesListener.size() > 0) {
            target.addAttribute("addSeries", true);
        }
        if (chartClickListener != null && chartClickListener.size() > 0) {
            target.addAttribute("click", true);
        }
        if (chartZoomListener != null && chartZoomListener.size() > 0) {
            target.addAttribute("selection", true);
        }
        target.endTag("chartEvents");
    }

    private void paintSeriesAndPointEvents(PaintTarget target)
            throws PaintException {
        target.startTag("seriesEvents");
        // For each series type, check if listeners exist. If so then add.
        for (SeriesType seriesType : SeriesType.values()) {
            paintSeriesEvents(target, seriesType);
        }
        target.endTag("seriesEvents");
    }

    private void paintSeriesEvents(PaintTarget target, SeriesType seriesType)
            throws PaintException {
        String tagName = seriesType.getName();
        target.startTag(tagName);
        if (seriesClickListeners.containsKey(seriesType)
                && seriesClickListeners.get(seriesType).size() > 0) {
            target.addAttribute("click", true);
        }
        if (seriesHideListeners.containsKey(seriesType)
                && seriesHideListeners.get(seriesType).size() > 0) {
            target.addAttribute("hide", true);
        }
        if (seriesShowListeners.containsKey(seriesType)
                && seriesShowListeners.get(seriesType).size() > 0) {
            target.addAttribute("show", true);
        }
        if (seriesLegendItemClickListeners.containsKey(seriesType)
                && seriesLegendItemClickListeners.get(seriesType).size() > 0) {
            target.addAttribute("legendItemClick", true);
        }
        // Check for point events
        paintPointEvents(target, seriesType);
        //
        target.endTag(tagName);
    }

    private void paintPointEvents(PaintTarget target, SeriesType seriesType)
            throws PaintException {
        target.startTag("pointEvents");
        if (pointClickListeners.containsKey(seriesType)
                && pointClickListeners.get(seriesType).size() > 0) {
            target.addAttribute("click", true);
        }
        if (pointRemoveListeners.containsKey(seriesType)
                && pointRemoveListeners.get(seriesType).size() > 0) {
            target.addAttribute("remove", true);
        }
        if (pointSelectListeners.containsKey(seriesType)
                && pointSelectListeners.get(seriesType).size() > 0) {
            target.addAttribute("select", true);
        }
        if (pointUnselectListeners.containsKey(seriesType)
                && pointUnselectListeners.get(seriesType).size() > 0) {
            target.addAttribute("unselect", true);
        }
        // Event applicable only for pie chart
        if (SeriesType.PIE.equals(seriesType)
                && pieChartLegendItemClickListener.size() > 0) {
            target.addAttribute("legendItemClick", true);
        }
        target.endTag("pointEvents");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void changeVariables(Object source, Map<String, Object> variables) {
        if (variables.containsKey("event")) {
            Map<String, Object> eventData = (Map<String, Object>) variables
                    .get("eventData");
            String eventName = (String) variables.get("event");
            if (eventName.equalsIgnoreCase("addSeries")) {
                fireAddSeries();
            } else if (eventName.equalsIgnoreCase("chartClick")) {
                double xAxisPos = Double.parseDouble((String) eventData
                        .get("xAxisPos"));
                double yAxisPos = Double.parseDouble((String) eventData
                        .get("yAxisPos"));
                //
                MousePosition mousePosition = getClickPosition(eventData);
                fireChartClick(new DecimalPoint(xAxisPos, yAxisPos),
                        mousePosition);
            } else if (eventName.equalsIgnoreCase("chartZoom")) {
                double xAxisMin = Double.parseDouble((String) eventData
                        .get("xAxisMin"));
                double xAxisMax = Double.parseDouble((String) eventData
                        .get("xAxisMax"));
                double yAxisMin = Double.parseDouble((String) eventData
                        .get("yAxisMin"));
                double yAxisMax = Double.parseDouble((String) eventData
                        .get("yAxisMax"));
                fireChartZoom(new ChartArea(xAxisMin, xAxisMax, yAxisMin,
                        yAxisMax));
            } else if (eventName.equalsIgnoreCase("chartResetZoom")) {
                fireChartResetZoom();
            } else if (eventName.equalsIgnoreCase("chartSVGAvailable")) {
                fireChartSVGAvailable((String) eventData.get("svg"));
            } else if (eventName.equalsIgnoreCase("seriesClick")) {
                PointEventData pointEventData = getPointEventData(eventData);
                //
                MousePosition mousePosition = getClickPosition(eventData);
                fireSeriesClick(
                        getSeriesFromEventData(pointEventData.getSeriesName()),
                        getPointFromEventData(pointEventData), mousePosition);
            } else if (eventName.equalsIgnoreCase("seriesHide")) {
                String seriesName = (String) eventData.get("seriesName");
                fireSeriesHide(getSeriesFromEventData(seriesName));
            } else if (eventName.equalsIgnoreCase("seriesShow")) {
                String seriesName = (String) eventData.get("seriesName");
                fireSeriesShow(getSeriesFromEventData(seriesName));
            } else if (eventName.equalsIgnoreCase("seriesLegendItemClick")) {
                String seriesName = (String) eventData.get("seriesName");
                fireSeriesLegendItemClick(getSeriesFromEventData(seriesName));
            } else if (eventName.equalsIgnoreCase("pieLegendItemClick")) {
                PointEventData pointEventData = getPointEventData(eventData);
                fireLegendItemClick(getPointFromEventData(pointEventData));
            } else if (eventName.equalsIgnoreCase("pointClick")) {
                MousePosition mousePosition = getClickPosition(eventData);
                //
                PointEventData pointEventData = getPointEventData(eventData);
                firePointClick(pointEventData.getCategory(),
                        getPointFromEventData(pointEventData), mousePosition);
            } else if (eventName.equalsIgnoreCase("pointSelect")) {
                PointEventData pointEventData = getPointEventData(eventData);
                firePointSelect(pointEventData.getCategory(),
                        getPointFromEventData(pointEventData));
            } else if (eventName.equalsIgnoreCase("pointUnselect")) {
                PointEventData pointEventData = getPointEventData(eventData);
                firePointUnselect(pointEventData.getCategory(),
                        getPointFromEventData(pointEventData));
            } else if (eventName.equalsIgnoreCase("pointRemove")) {
                PointEventData pointEventData = getPointEventData(eventData);
                firePointRemove(pointEventData.getCategory(),
                        getPointFromEventData(pointEventData));
            }
        }
    }

    private Point getPointFromEventData(PointEventData eventData) {
        // First locate a series and then point
        Series series = getSeriesFromEventData(eventData.getSeriesName());
        if (series != null) {
            if (series instanceof XYSeries) {
                for (DecimalPoint point : ((XYSeries) series).getPoints()) {
                    if (point.getY() != null
                            && point.getY().compareTo(eventData.getPointY()) == 0
                            && point.getX() != null
                            && point.getX().compareTo(eventData.getPointX()) == 0) {
                        return point;
                    }
                }
            } else {
                for (DateTimePoint point : ((DateTimeSeries) series)
                        .getPoints()) {
                    if (point.getY() != null
                            && point.getY().compareTo(eventData.getPointY()) == 0
                            && point.getX() != null
                            && getDateInMilliseconds(point.getX(),
                                    ((DateTimeSeries) series).isIncludeTime()) == (long) eventData
                                    .getPointX()) {
                        return point;
                    }
                }
            }
        }
        return null;
    }

    private static Long getDateInMilliseconds(Date dt, boolean isIncludeTime) {
        if (dt == null) {
            return null;
        }
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(dt);
        if (!isIncludeTime) {
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        }
        return cal.getTimeInMillis();
    }

    private Series getSeriesFromEventData(String seriesName) {
        for (Series series : this.chartSeries) {
            if (series.getName().equals(seriesName)) {
                return series;
            }
        }
        // Should not happen
        // If it happens then why? Any comments???
        return null;
    }

    private void fireAddSeries() {
        fireEvent(new ChartAddSeriesEvent(this, this));
    }

    private void fireChartClick(Point point, MousePosition mousePosition) {
        fireEvent(new ChartClickEvent(this, this, point, mousePosition));
    }

    private void fireChartZoom(ChartArea selectedArea) {
        fireEvent(new ChartZoomEvent(this, this, selectedArea));
    }

    private void fireChartSVGAvailable(String svg) {
        fireEvent(new ChartSVGAvailableEvent(this, this, svg));
    }

    private void fireChartResetZoom() {
        fireEvent(new ChartResetZoomEvent(this, this));
    }

    private void fireSeriesClick(Series series, Point point,
            MousePosition mousePosition) {
        fireEvent(new SeriesClickEvent(this, this, series, point, mousePosition));
    }

    private void fireSeriesShow(Series series) {
        fireEvent(new SeriesShowEvent(this, this, series));
    }

    private void fireSeriesHide(Series series) {
        fireEvent(new SeriesHideEvent(this, this, series));
    }

    private void fireSeriesLegendItemClick(Series series) {
        fireEvent(new SeriesLegendItemClickEvent(this, this, series));
    }

    private void firePointClick(String category, Point point,
            MousePosition mousePosition) {
        fireEvent(new PointClickEvent(this, this, category, point,
                mousePosition));
    }

    private void firePointSelect(String category, Point point) {
        fireEvent(new PointSelectEvent(this, this, category, point));
    }

    private void firePointUnselect(String category, Point point) {
        fireEvent(new PointUnselectEvent(this, this, category, point));
    }

    private void firePointRemove(String category, Point point) {
        fireEvent(new PointRemoveEvent(this, this, category, point));
    }

    private void fireLegendItemClick(Point point) {
        fireEvent(new PieChartLegendItemClickEvent(this, this, point));
    }

    private PointEventData getPointEventData(Map<String, Object> eventData) {
        String seriesName = (String) eventData.get("seriesName");
        String category = (String) eventData.get("category");
        double pointX = Double.valueOf((String) eventData.get("pointX"));
        double pointY = Double.valueOf((String) eventData.get("pointY"));
        return new PointEventData(seriesName, category, pointX, pointY);
    }

    private MousePosition getClickPosition(Map<String, Object> eventData) {
        Integer mouseX = null;
        if (eventData.get("mouseX") instanceof Integer) {
            mouseX = (Integer) eventData.get("mouseX");
        }
        Integer mouseY = null;
        if (eventData.get("mouseY") instanceof Integer) {
            mouseY = (Integer) eventData.get("mouseY");
        }
        if (mouseX != null && mouseY != null) {
            return new MousePosition(mouseX, mouseY);
        }
        return null;
    }

    /**
     * This class contain mouse coordinates when a click event occurs on a
     * chart, a series or a point.
     * 
     * The mouse coordinates are in pixels.
     * 
     * @author Invient
     * 
     */
    public final class MousePosition implements Serializable {
        private int mouseX;
        private int mouseY;

        /**
         * Creates this object with given arguments.
         * 
         * @param mouseX
         *            x position of mouse when a click event occurred, in pixel
         * @param mouseY
         *            y position of mouse when a click event occurred, in pixel
         */
        public MousePosition(int mouseX, int mouseY) {
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }

        /**
         * 
         * @return Returns x position of mouse when a click event occurred, in
         *         pixel
         */
        public int getMouseX() {
            return mouseX;
        }

        /**
         * 
         * @return Returns y position of mouse when a click event occurred, in
         *         pixel
         */
        public int getMouseY() {
            return mouseY;
        }

        @Override
        public String toString() {
            return "MousePosition [mouseX=" + mouseX + ", mouseY=" + mouseY
                    + "]";
        }

    }

    private final class PointEventData implements Serializable {
        private String seriesName;
        private String category;
        private double pointX;
        private double pointY;

        public PointEventData(String seriesName, String category,
                double pointX, double pointY) {
            super();
            this.seriesName = seriesName;
            this.category = category;
            this.pointX = pointX;
            this.pointY = pointY;
        }

        public String getSeriesName() {
            return seriesName;
        }

        public String getCategory() {
            return category;
        }

        public double getPointX() {
            return pointX;
        }

        public double getPointY() {
            return pointY;
        }

        @Override
        public String toString() {
            return "PointEventData [seriesName=" + seriesName + ", category="
                    + category + ", pointX=" + pointX + ", pointY=" + pointY
                    + "]";
        }

    }

    /***************************** POINT CLICK EVENT *****************************/
    /**
     * Click event. This event is thrown, when any point of this chart is
     * clicked and the point marker is enabled. The point marker is enabled by
     * default.
     * 
     * @author Invient
     */
    public class PointClickEvent extends Component.Event {

        private String category;
        private Point point;
        private InvientCharts chart;
        private MousePosition mousePosition;

        /**
         * New instance of the point click event.
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param category
         *            a category to which point is associated in case of
         *            categorized axis,
         * @param point
         *            the point on which the click event occurred
         * @param mousePosition
         *            the position of a mouse when the click event occurred
         */
        public PointClickEvent(Component source, InvientCharts chart,
                String category, Point point, MousePosition mousePosition) {
            super(source);
            this.chart = chart;
            this.category = category;
            this.point = point;
            this.mousePosition = mousePosition;
        }

        /**
         * 
         * @return Returns a category to which point is associated in case of
         *         categorized axis only.
         */
        public String getCategory() {
            return category;
        }

        /**
         * 
         * @return Returns the chart object associated with the point
         */
        public InvientCharts getChart() {
            return chart;
        }

        /**
         * 
         * @return Returns the point on which the click event occurred
         */
        public Point getPoint() {
            return this.point;
        }

        /**
         * 
         * @return Returns the position of a mouse when the click event occurred
         */
        public MousePosition getMousePosition() {
            return mousePosition;
        }

    }

    /**
     * Interface for listening for a {@link PointClickEvent} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface PointClickListener extends Serializable {
        public void pointClick(PointClickEvent pointClickEvent);
    }

    private Map<SeriesType, Set<PointClickListener>> pointClickListeners = new HashMap<SeriesType, Set<PointClickListener>>();

    /**
     * Adds the point click listener. If the argument seriesTypes is not
     * specified then the listener will be added for all series type otherwise
     * it will be added for a specific series type
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(PointClickListener listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }

        for (SeriesType seriesType : seriesTypes) {
            if (pointClickListeners.containsKey(seriesType)) {
                pointClickListeners.get(seriesType).add(listener);
            } else {
                Set<PointClickListener> listeners = new HashSet<PointClickListener>();
                listeners.add(listener);
                pointClickListeners.put(seriesType, listeners);
            }
        }
        addListener(PointClickEvent.class, listener, POINT_CLICK_METHOD);
    }

    /**
     * Removes the point click listener. If the argument seriesTypes is not
     * specified then the listener will be removed only for a series type
     * SeriesType.COMMONSERIES otherwise the listener will be removed for all
     * specified series types.
     * 
     * @param listener
     *            the listener to be removed
     * @param seriesTypes
     *            one or more series types as defined by (@link SeriesType}
     */
    public void removeListener(PointClickListener listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }

        for (SeriesType seriesType : seriesTypes) {
            if (pointClickListeners.containsKey(seriesType)) {
                pointClickListeners.get(seriesType).remove(listener);
            }
        }
        removeListener(PointClickEvent.class, listener, POINT_CLICK_METHOD);
    }

    /**
     * Point remove event. This event is thrown, when any point of this chart is
     * removed from its series.
     * 
     * This event is EXPERIMENTAL ONLY.
     * 
     * @author Invient
     */
    public class PointRemoveEvent extends Component.Event {

        private String category;
        private Point point;
        private InvientCharts chart;

        /**
         * New instance of the point remove event.
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param category
         *            a category to which point is associated in case of
         *            categorized axis,
         * @param point
         *            the point removed
         */
        public PointRemoveEvent(Component source, InvientCharts chart,
                String category, Point point) {
            super(source);
            this.chart = chart;
            this.category = category;
            this.point = point;
        }

        /**
         * 
         * @return Returns a category to which point is associated in case of
         *         categorized axis only.
         */
        public String getCategory() {
            return category;
        }

        /**
         * 
         * @return Returns the chart object associated with the point
         */
        public InvientCharts getChart() {
            return chart;
        }

        /**
         * 
         * @return Returns the point which has been removed
         */
        public Point getPoint() {
            return this.point;
        }

    }

    /**
     * Interface for listening for a {@link PointRemoveEvent} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface PointRemoveListener extends Serializable {
        public void pointRemove(PointRemoveEvent pointRemoveEvent);
    }

    private Map<SeriesType, Set<PointRemoveListener>> pointRemoveListeners = new HashMap<SeriesType, Set<PointRemoveListener>>();

    /**
     * Adds the point remove listener. If the argument seriesTypes is not
     * specified then the listener will be added for all series type otherwise
     * it will be added for a specific series type
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(PointRemoveListener listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }
        for (SeriesType seriesType : seriesTypes) {
            if (pointRemoveListeners.containsKey(seriesType)) {
                pointRemoveListeners.get(seriesType).add(listener);
            } else {
                Set<PointRemoveListener> listeners = new HashSet<PointRemoveListener>();
                listeners.add(listener);
                pointRemoveListeners.put(seriesType, listeners);
            }
        }
        addListener(PointRemoveEvent.class, listener, POINT_REMOVE_METHOD);
    }

    /**
     * Removes the point remove listener. If the argument seriesTypes is not
     * specified then the listener will be removed only for a series type
     * SeriesType.COMMONSERIES otherwise the listener will be removed for all
     * specified series types.
     * 
     * @param listener
     *            the listener to be removed
     * @param seriesTypes
     *            one or more series types as defined by (@link SeriesType}
     */
    public void removeListener(PointRemoveListener listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }
        for (SeriesType seriesType : seriesTypes) {
            if (pointRemoveListeners.containsKey(seriesType)) {
                pointRemoveListeners.get(seriesType).remove(listener);
            }
        }
        pointRemoveListeners.remove(listener);
        removeListener(PointRemoveEvent.class, listener, POINT_REMOVE_METHOD);
    }

    /**
     * Poin unselect event. This event is thrown, when any point of this chart
     * is unselected and the point marker is enabled. The point marker is
     * enabled by default.
     * 
     * @author Invient
     */
    public class PointUnselectEvent extends Component.Event {

        private String category;
        private Point point;
        private InvientCharts chart;

        /**
         * New instance of the point unselect event.
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param category
         *            a category to which point is associated in case of
         *            categorized axis,
         * @param point
         *            the point unselected as a result of this event
         */
        public PointUnselectEvent(Component source, InvientCharts chart,
                String category, Point point) {
            super(source);
            this.chart = chart;
            this.category = category;
            this.point = point;
        }

        /**
         * 
         * @return Returns a category to which point is associated in case of
         *         categorized axis only.
         */
        public String getCategory() {
            return category;
        }

        /**
         * 
         * @return Returns the chart object associated with the point
         */
        public InvientCharts getChart() {
            return chart;
        }

        /**
         * 
         * @return Returns the unselected point
         */
        public Point getPoint() {
            return this.point;
        }

    }

    /**
     * Interface for listening for a {@link PointUnselectEvent} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface PointUnselectListener extends Serializable {
        public void pointUnSelect(PointUnselectEvent pointUnSelectEvent);
    }

    private Map<SeriesType, Set<PointUnselectListener>> pointUnselectListeners = new HashMap<SeriesType, Set<PointUnselectListener>>();

    /**
     * Adds the point unselect listener. If the argument seriesTypes is not
     * specified then the listener will be added for all series type otherwise
     * it will be added for a specific series type
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(PointUnselectListener listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }
        for (SeriesType seriesType : seriesTypes) {
            if (pointUnselectListeners.containsKey(seriesType)) {
                pointUnselectListeners.get(seriesType).add(listener);
            } else {
                Set<PointUnselectListener> listeners = new HashSet<PointUnselectListener>();
                listeners.add(listener);
                pointUnselectListeners.put(seriesType, listeners);
            }
        }
        addListener(PointUnselectEvent.class, listener, POINT_UNSELECT_METHOD);
    }

    /**
     * Removes the point unselect listener. If the argument seriesTypes is not
     * specified then the listener will be removed only for a series type
     * SeriesType.COMMONSERIES otherwise the listener will be removed for all
     * specified series types.
     * 
     * @param listener
     *            the listener to be removed
     * @param seriesTypes
     *            one or more series types as defined by (@link SeriesType}
     */
    public void removeListener(PointUnselectListener listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }
        for (SeriesType seriesType : seriesTypes) {
            if (pointUnselectListeners.containsKey(seriesType)) {
                pointUnselectListeners.get(seriesType).remove(listener);
            }
        }
        removeListener(PointUnselectEvent.class, listener,
                POINT_UNSELECT_METHOD);
    }

    /**
     * Point select event. This event is thrown, when any point of this chart is
     * selected and the point marker is enabled. The point marker is enabled by
     * default.
     * 
     * @author Invient
     */
    public class PointSelectEvent extends Component.Event {

        private String category;
        private Point point;
        private InvientCharts chart;

        /**
         * New instance of the point select event.
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param category
         *            a category to which point is associated in case of
         *            categorized axis,
         * @param point
         *            the point selected as a result of this event
         */
        public PointSelectEvent(Component source, InvientCharts chart,
                String category, Point point) {
            super(source);
            this.chart = chart;
            this.category = category;
            this.point = point;
        }

        /**
         * 
         * @return Returns a category to which point is associated in case of
         *         categorized axis only.
         */
        public String getCategory() {
            return category;
        }

        /**
         * 
         * @return Returns the chart object associated with the point
         */
        public InvientCharts getChart() {
            return chart;
        }

        /**
         * 
         * @return Returns the selected point
         */
        public Point getPoint() {
            return this.point;
        }

    }

    /**
     * Interface for listening for a {@link PointSelectListener} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface PointSelectListener extends Serializable {
        public void pointSelected(PointSelectEvent pointSelectEvent);
    }

    private Map<SeriesType, Set<PointSelectListener>> pointSelectListeners = new HashMap<SeriesType, Set<PointSelectListener>>();

    /**
     * Adds the point select listener. If the argument seriesTypes is not
     * specified then the listener will be added for all series type otherwise
     * it will be added for a specific series type
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(PointSelectListener listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }

        for (SeriesType seriesType : seriesTypes) {
            if (pointSelectListeners.containsKey(seriesType)) {
                pointSelectListeners.get(seriesType).add(listener);
            } else {
                Set<PointSelectListener> listeners = new HashSet<PointSelectListener>();
                listeners.add(listener);
                pointSelectListeners.put(seriesType, listeners);
            }
        }
        addListener(PointSelectEvent.class, listener, POINT_SELECT_METHOD);
    }

    /**
     * Removes the point select listener. If the argument seriesTypes is not
     * specified then the listener will be removed only for a series type
     * SeriesType.COMMONSERIES otherwise the listener will be removed for all
     * specified series types.
     * 
     * @param listener
     *            the listener to be removed
     * @param seriesTypes
     *            one or more series types as defined by (@link SeriesType}
     */
    public void removeListener(PointSelectListener listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }
        for (SeriesType seriesType : seriesTypes) {
            if (pointSelectListeners.containsKey(seriesType)) {
                pointSelectListeners.get(seriesType).remove(listener);
            }
        }
        removeListener(PointSelectEvent.class, listener, POINT_SELECT_METHOD);
    }

    private static final Method POINT_CLICK_METHOD;
    private static final Method POINT_REMOVE_METHOD;
    private static final Method POINT_SELECT_METHOD;
    private static final Method POINT_UNSELECT_METHOD;

    static {
        try {
            POINT_CLICK_METHOD = PointClickListener.class.getDeclaredMethod(
                    "pointClick", new Class[] { PointClickEvent.class });
            POINT_REMOVE_METHOD = PointRemoveListener.class.getDeclaredMethod(
                    "pointRemove", new Class[] { PointRemoveEvent.class });
            POINT_SELECT_METHOD = PointSelectListener.class.getDeclaredMethod(
                    "pointSelected", new Class[] { PointSelectEvent.class });
            POINT_UNSELECT_METHOD = PointUnselectListener.class
                    .getDeclaredMethod("pointUnSelect",
                            new Class[] { PointUnselectEvent.class });
        } catch (final java.lang.NoSuchMethodException e) {
            // This should not happen
            throw new java.lang.RuntimeException(
                    "Internal error finding methods in Button");
        }
    }

    // ***************************** Series Events ****************************/

    /**
     * Series click event. This event is thrown, when any series of this chart
     * is clicked.
     * 
     * @author Invient
     */
    public class SeriesClickEvent extends Component.Event {
        private Point point;
        private Series series;
        private InvientCharts chart;
        private MousePosition mousePosition;

        /**
         * New instance of the series click event.
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param series
         *            the series on which click event occurred
         * @param point
         *            the closest point of a series
         * @param mousePosition
         *            the position of a mouse when the click event occurred
         */
        public SeriesClickEvent(Component source, InvientCharts chart,
                Series series, Point point, MousePosition mousePosition) {
            super(source);
            this.chart = chart;
            this.series = series;
            this.point = point;
            this.mousePosition = mousePosition;
        }

        /**
         * 
         * @return Returns the chart object associated with the point
         */
        public InvientCharts getChart() {
            return this.chart;
        }

        /**
         * 
         * @return Returns the series object on which the click event occurred
         */
        public Series getSeries() {
            return this.series;
        }

        /**
         * 
         * @return Returns the point of a series closest to the position where
         *         mouse click event occurred.
         */
        public Point getNearestPoint() {
            return this.point;
        }

        /**
         * 
         * @return Returns the position of a mouse when the click event occurred
         */
        public MousePosition getMousePosition() {
            return mousePosition;
        }

    }

    /**
     * Interface for listening for a {@link SeriesClickListerner} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface SeriesClickListerner extends Serializable {
        public void seriesClick(SeriesClickEvent seriesClickEvent);
    }

    private Map<SeriesType, Set<SeriesClickListerner>> seriesClickListeners = new HashMap<SeriesType, Set<SeriesClickListerner>>();

    /**
     * Adds the series click listener. If the argument seriesTypes is not
     * specified then the listener will be added for all series type otherwise
     * it will be added for a specific series type
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(SeriesClickListerner listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }

        for (SeriesType seriesType : seriesTypes) {
            if (seriesClickListeners.containsKey(seriesType)) {
                seriesClickListeners.get(seriesType).add(listener);
            } else {
                Set<SeriesClickListerner> listeners = new HashSet<SeriesClickListerner>();
                listeners.add(listener);
                seriesClickListeners.put(seriesType, listeners);
            }
        }
        addListener(SeriesClickEvent.class, listener, SERIES_CLICK_METHOD);
    }

    /**
     * Removes the series click listener. If the argument seriesTypes is not
     * specified then the listener will be removed only for a series type
     * SeriesType.COMMONSERIES otherwise the listener will be removed for all
     * specified series types.
     * 
     * @param listener
     *            the listener to be removed
     * @param seriesTypes
     *            one or more series types as defined by (@link SeriesType}
     */
    public void removeListener(SeriesClickListerner listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }

        for (SeriesType seriesType : seriesTypes) {
            if (seriesClickListeners.containsKey(seriesType)) {
                seriesClickListeners.get(seriesType).remove(listener);
            }
        }
        removeListener(SeriesClickEvent.class, listener, SERIES_CLICK_METHOD);
    }

    /**
     * Series Hide event. This event is thrown, when any series of this chart is
     * hidden.
     * 
     * @author Invient
     */
    public class SeriesHideEvent extends Component.Event {
        private Series series;
        private InvientCharts chart;

        /**
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param series
         *            the series which got hidden
         */
        public SeriesHideEvent(Component source, InvientCharts chart,
                Series series) {
            super(source);
            this.chart = chart;
            this.series = series;
        }

        /**
         * 
         * @return Returns the chart object associated with the point
         */
        public InvientCharts getChart() {
            return this.chart;
        }

        /**
         * 
         * @return Returns the series which got hidden
         */
        public Series getSeries() {
            return this.series;
        }
    }

    /**
     * Interface for listening for a {@link SeriesHideEvent} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface SeriesHideListerner extends Serializable {
        public void seriesHide(SeriesHideEvent seriesHideEvent);
    }

    private Map<SeriesType, Set<SeriesHideListerner>> seriesHideListeners = new HashMap<SeriesType, Set<SeriesHideListerner>>();

    /**
     * Adds the series hide listener. If the argument seriesTypes is not
     * specified then the listener will be added for all series type otherwise
     * it will be added for a specific series type
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(SeriesHideListerner listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }

        for (SeriesType seriesType : seriesTypes) {
            if (seriesHideListeners.containsKey(seriesType)) {
                seriesHideListeners.get(seriesType).add(listener);
            } else {
                Set<SeriesHideListerner> listeners = new HashSet<SeriesHideListerner>();
                listeners.add(listener);
                seriesHideListeners.put(seriesType, listeners);
            }
        }
        addListener(SeriesHideEvent.class, listener, SERIES_HIDE_METHOD);
    }

    /**
     * Removes the series hide listener. If the argument seriesTypes is not
     * specified then the listener will be removed only for a series type
     * SeriesType.COMMONSERIES otherwise the listener will be removed for all
     * specified series types.
     * 
     * @param listener
     *            the listener to be removed
     * @param seriesTypes
     *            one or more series types as defined by (@link SeriesType}
     */
    public void removeListener(SeriesHideListerner listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }

        for (SeriesType seriesType : seriesTypes) {
            if (seriesHideListeners.containsKey(seriesType)) {
                seriesHideListeners.get(seriesType).remove(listener);
            }
        }
        removeListener(SeriesHideEvent.class, listener, SERIES_HIDE_METHOD);
    }

    /**
     * Series show event. This event is thrown, when any series of this chart is
     * displayed after a chart is created.
     * 
     * @author Invient
     */
    public class SeriesShowEvent extends Component.Event {
        private Series series;
        private InvientCharts chart;

        /**
         * New instance of the series show event.
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param series
         *            the series which got displayed
         */
        public SeriesShowEvent(Component source, InvientCharts chart,
                Series series) {
            super(source);
            this.chart = chart;
            this.series = series;
        }

        /**
         * 
         * @return Returns the chart object associated with the series
         */
        public InvientCharts getChart() {
            return this.chart;
        }

        /**
         * 
         * @return Returns the series which got displayed
         */
        public Series getSeries() {
            return this.series;
        }
    }

    /**
     * Interface for listening for a {@link SeriesShowEvent} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface SeriesShowListerner extends Serializable {
        public void seriesShow(SeriesShowEvent seriesShowEvent);
    }

    private Map<SeriesType, Set<SeriesShowListerner>> seriesShowListeners = new HashMap<SeriesType, Set<SeriesShowListerner>>();

    /**
     * Adds the series show listener. If the argument seriesTypes is not
     * specified then the listener will be added for all series type otherwise
     * it will be added for a specific series type
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(SeriesShowListerner listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }

        for (SeriesType seriesType : seriesTypes) {
            if (seriesShowListeners.containsKey(seriesType)) {
                seriesShowListeners.get(seriesType).add(listener);
            } else {
                Set<SeriesShowListerner> listeners = new HashSet<SeriesShowListerner>();
                listeners.add(listener);
                seriesShowListeners.put(seriesType, listeners);
            }
        }
        addListener(SeriesShowEvent.class, listener, SERIES_SHOW_METHOD);
    }

    /**
     * Removes the series show listener. If the argument seriesTypes is not
     * specified then the listener will be removed only for a series type
     * SeriesType.COMMONSERIES otherwise the listener will be removed for all
     * specified series types.
     * 
     * @param listener
     *            the listener to be removed
     * @param seriesTypes
     *            one or more series types as defined by (@link SeriesType}
     */
    public void removeListener(SeriesShowListerner listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }

        for (SeriesType seriesType : seriesTypes) {
            if (seriesShowListeners.containsKey(seriesType)) {
                seriesShowListeners.get(seriesType).remove(listener);
            }
        }
        removeListener(SeriesShowEvent.class, listener, SERIES_SHOW_METHOD);
    }

    // LEGENDITEMCLICK
    // This event occurs when a series is clicked in the legend.
    // This event is not applicable for PieChart instead use
    // LegendItemClickEvent/LegendItemClickListener
    /**
     * Series legend item click event. This event is thrown, when legend item is
     * clicked. This event is not applicable for PieChart instead use
     * {@link LegendItemClickEvent}
     * 
     * @author Invient
     */
    public class SeriesLegendItemClickEvent extends Component.Event {
        private Series series;
        private InvientCharts chart;

        /**
         * New instance of the point click event.
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param series
         *            the series associated with the legend item
         */
        public SeriesLegendItemClickEvent(Component source,
                InvientCharts chart, Series series) {
            super(source);
            this.chart = chart;
            this.series = series;
        }

        /**
         * 
         * @return Returns the chart object associated with the series
         */
        public InvientCharts getChart() {
            return this.chart;
        }

        /**
         * 
         * @return Returns the series associated with the legend item
         */
        public Series getSeries() {
            return this.series;
        }
    }

    /**
     * Interface for listening for a {@link SeriesLegendItemClickEvent}
     * triggered by {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface SeriesLegendItemClickListerner extends Serializable {
        public void seriesLegendItemClick(
                SeriesLegendItemClickEvent seriesLegendItemClickEvent);
    }

    private Map<SeriesType, Set<SeriesLegendItemClickListerner>> seriesLegendItemClickListeners = new HashMap<SeriesType, Set<SeriesLegendItemClickListerner>>();

    /**
     * Adds the series legend item click listener. If the argument seriesTypes
     * is not specified then the listener will be added for all series type
     * otherwise it will be added for a specific series type
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(SeriesLegendItemClickListerner listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }

        for (SeriesType seriesType : seriesTypes) {
            if (seriesLegendItemClickListeners.containsKey(seriesType)) {
                seriesLegendItemClickListeners.get(seriesType).add(listener);
            } else {
                Set<SeriesLegendItemClickListerner> listeners = new HashSet<SeriesLegendItemClickListerner>();
                listeners.add(listener);
                seriesLegendItemClickListeners.put(seriesType, listeners);
            }
        }
        addListener(SeriesLegendItemClickEvent.class, listener,
                SERIES_LEGENDITEM_CLICK_METHOD);
    }

    /**
     * Removes the series legend item click listener. If the argument
     * seriesTypes is not specified then the listener will be removed only for a
     * series type SeriesType.COMMONSERIES otherwise the listener will be
     * removed for all specified series types.
     * 
     * @param listener
     *            the listener to be removed
     * @param seriesTypes
     *            one or more series types as defined by (@link SeriesType}
     */
    public void removeListener(SeriesLegendItemClickListerner listener,
            SeriesType... seriesTypes) {
        if (seriesTypes.length == 0) {
            seriesTypes = new SeriesType[] { SeriesType.COMMONSERIES };
        }
        for (SeriesType seriesType : seriesTypes) {
            if (seriesLegendItemClickListeners.containsKey(seriesType)) {
                seriesLegendItemClickListeners.get(seriesType).remove(listener);
            }
        }
        removeListener(SeriesLegendItemClickEvent.class, listener,
                SERIES_LEGENDITEM_CLICK_METHOD);
    }

    private static final Method SERIES_CLICK_METHOD;
    // private static final Method SERIES_CHECKBOX_CLICK_METHOD;
    private static final Method SERIES_HIDE_METHOD;
    private static final Method SERIES_SHOW_METHOD;
    private static final Method SERIES_LEGENDITEM_CLICK_METHOD;

    static {
        try {
            SERIES_CLICK_METHOD = SeriesClickListerner.class.getDeclaredMethod(
                    "seriesClick", new Class[] { SeriesClickEvent.class });
            // SERIES_CHECKBOX_CLICK_METHOD = SeriesCheckboxClickListerner.class
            // .getDeclaredMethod("seriesCheckboxClick",
            // new Class[] { SeriesCheckboxClickEvent.class });
            SERIES_HIDE_METHOD = SeriesHideListerner.class.getDeclaredMethod(
                    "seriesHide", new Class[] { SeriesHideEvent.class });
            SERIES_SHOW_METHOD = SeriesShowListerner.class.getDeclaredMethod(
                    "seriesShow", new Class[] { SeriesShowEvent.class });
            SERIES_LEGENDITEM_CLICK_METHOD = SeriesLegendItemClickListerner.class
                    .getDeclaredMethod("seriesLegendItemClick",
                            new Class[] { SeriesLegendItemClickEvent.class });
        } catch (final java.lang.NoSuchMethodException e) {
            // This should never happen
            throw new java.lang.RuntimeException(
                    "Internal error finding methods in Button");
        }
    }

    /**************************** PieChart related events ****************************/
    // PieChart LEGENDITEMCLICK
    // This event occurs when a point of a PieChart is clicked

    /**
     * PieChart legend item click event. This event is thrown, when the legend
     * item belonging to the pie point (slice) is clicked.
     * 
     * @author Invient
     */
    public class PieChartLegendItemClickEvent extends Component.Event {

        private InvientCharts chart;
        private Point point;

        /**
         * New instance of the piechart legend item click event
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param point
         *            the pie point (slice) associated with the legend item
         */
        public PieChartLegendItemClickEvent(Component source,
                InvientCharts chart, Point point) {
            super(source);
            this.chart = chart;
            this.point = point;
        }

        /**
         * 
         * @return Returns the chart object associated with the point
         */
        public InvientCharts getChart() {
            return this.chart;
        }

        /**
         * 
         * @return Returns the pie point (slice) associated with the legend item
         */
        public Point getPoint() {
            return this.point;
        }
    }

    /**
     * Interface for listening for a {@link PieChartLegendItemClickEvent}
     * triggered by {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface PieChartLegendItemClickListener extends Serializable {
        public void legendItemClick(
                PieChartLegendItemClickEvent legendItemClickEvent);
    }

    private Set<PieChartLegendItemClickListener> pieChartLegendItemClickListener = new HashSet<PieChartLegendItemClickListener>();

    /**
     * Adds the piechart legend item click listener.
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(PieChartLegendItemClickListener listener) {
        pieChartLegendItemClickListener.add(listener);
        addListener(PieChartLegendItemClickEvent.class, listener,
                LEGENDITEM_CLICK_METHOD);
    }

    /**
     * Removes the piechart legend item click listener.
     * 
     * @param listener
     *            the listener to be removed
     */
    public void removeListener(PieChartLegendItemClickListener listener) {
        pieChartLegendItemClickListener.remove(listener);
        removeListener(PieChartLegendItemClickEvent.class, listener,
                LEGENDITEM_CLICK_METHOD);
    }

    private static final Method LEGENDITEM_CLICK_METHOD;

    static {
        try {
            LEGENDITEM_CLICK_METHOD = PieChartLegendItemClickListener.class
                    .getDeclaredMethod("legendItemClick",
                            new Class[] { PieChartLegendItemClickEvent.class });
        } catch (final java.lang.NoSuchMethodException e) {
            // This should never happen
            throw new java.lang.RuntimeException(
                    "Internal error finding methods in Button");
        }
    }

    /***************************** Chart Events *****************************/
    /**
     * Chart Click event. This event is thrown, when this chart is clicked.
     * 
     * @author Invient
     */
    public class ChartClickEvent extends Component.Event {
        private InvientCharts chart;
        private Point point;
        private MousePosition mousePosition;

        /**
         * New instance of the chart click event.
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param point
         *            the position where the click event occurred in axes units
         * @param mousePosition
         *            the coordinate of mouse where the click event occurred in
         *            pixels
         */
        public ChartClickEvent(Component source, InvientCharts chart,
                Point point, MousePosition mousePosition) {
            super(source);
            this.chart = chart;
            this.point = point;
            this.mousePosition = mousePosition;
        }

        /**
         * Returns the chart object on which the click event occurred
         * 
         * @return Returns the chart object on which the click event occurred
         * @see InvientCharts
         */
        public InvientCharts getChart() {
            return this.chart;
        }

        /**
         * Returns the point representing the position where the click event
         * occurred in axes units
         * 
         * @return Returns the point representing the position where the click
         *         event occurred in axes units
         * @see Point
         */
        public Point getPoint() {
            return this.point;
        }

        /**
         * Returns the position of a mouse when the click event occurred
         * 
         * @return Returns the position of a mouse when the click event occurred
         * @see MousePosition
         */
        public MousePosition getMousePosition() {
            return mousePosition;
        }

        @Override
        public String toString() {
            return "ChartClickEvent [point=" + point + ", mousePosition="
                    + mousePosition + "]";
        }

    }

    /**
     * Interface for listening for a {@link ChartClickEvent} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface ChartClickListener extends Serializable {
        public void chartClick(ChartClickEvent chartClickEvent);
    }

    private Set<ChartClickListener> chartClickListener = new HashSet<ChartClickListener>();

    /**
     * Adds the chart click listener.
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(ChartClickListener listener) {
        chartClickListener.add(listener);
        addListener(ChartClickEvent.class, listener, CHART_CLICK_METHOD);
    }

    /**
     * Removes the chart click listener.
     * 
     * @param listener
     *            the listener to be removed
     */
    public void removeListener(ChartClickListener listener) {
        chartClickListener.remove(listener);
        removeListener(ChartClickEvent.class, listener, CHART_CLICK_METHOD);
    }

    /**
     * Add series event. This event is thrown, when a series is added to the
     * chart.
     * 
     * @author Invient
     */
    public class ChartAddSeriesEvent extends Component.Event {
        private InvientCharts chart;

        /**
         * New instance of the chart add series event.
         * 
         * @param source
         * @param chart
         */
        public ChartAddSeriesEvent(Component source, InvientCharts chart) {
            super(source);
            this.chart = chart;
        }

        /**
         * Returns the chart object to which a series is added
         * 
         * @return Returns the chart object to which a series has been added.
         * @see InvientCharts
         */
        public InvientCharts getChart() {
            return this.chart;
        }
    }

    /**
     * Interface for listening for a {@link ChartAddSeriesEvent} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface ChartAddSeriesListener extends Serializable {
        public void chartAddSeries(ChartAddSeriesEvent chartAddSeriesEvent);
    }

    private Set<ChartAddSeriesListener> chartAddSeriesListener = new HashSet<ChartAddSeriesListener>();

    /**
     * Adds the series add listener.
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(ChartAddSeriesListener listener) {
        chartAddSeriesListener.add(listener);
        addListener(ChartAddSeriesEvent.class, listener,
                CHART_ADD_SERIES_METHOD);
    }

    /**
     * Removes the series add listener.
     * 
     * @param listener
     *            the listener to be removed
     */
    public void removeListener(ChartAddSeriesListener listener) {
        chartAddSeriesListener.remove(listener);
        removeListener(ChartAddSeriesEvent.class, listener,
                CHART_ADD_SERIES_METHOD);
    }

    /**
     * Defines information on the selected area.
     * 
     * @author Invient
     * 
     */
    public final class ChartArea implements Serializable {
        private double xAxisMin;
        private double xAxisMax;
        private double yAxisMin;
        private double yAxisMax;

        public ChartArea(double xAxisMin, double xAxisMax, double yAxisMin,
                double yAxisMax) {
            this.xAxisMin = xAxisMin;
            this.xAxisMax = xAxisMax;
            this.yAxisMin = yAxisMin;
            this.yAxisMax = yAxisMax;
        }

        public double getxAxisMin() {
            return xAxisMin;
        }

        public double getxAxisMax() {
            return xAxisMax;
        }

        public double getyAxisMin() {
            return yAxisMin;
        }

        public double getyAxisMax() {
            return yAxisMax;
        }

        @Override
        public String toString() {
            return "ChartSelectedArea [xAxisMin=" + xAxisMin + ", xAxisMax="
                    + xAxisMax + ", yAxisMin=" + yAxisMin + ", yAxisMax="
                    + yAxisMax + "]";
        }

    }

    /**
     * Chart zoom event. This event is thrown, when an area of the chart has
     * been selected.
     * 
     * @author Invient
     */
    public class ChartZoomEvent extends Component.Event {
        private InvientCharts chart;
        private ChartArea chartArea;

        /**
         * New instance of the chart zoom event.
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param chartArea
         *            the chartArea object containing dimensions of zoomed area
         *            of the chart
         */
        public ChartZoomEvent(Component source, InvientCharts chart,
                ChartArea chartArea) {
            super(source);
            this.chart = chart;
            this.chartArea = chartArea;
        }

        /**
         * Returns the chart object for which the zoom event has occurred
         * 
         * @return Returns the chart object for which the zoom event has
         *         occurred
         */
        public InvientCharts getChart() {
            return this.chart;
        }

        /**
         * Returns the chartArea object containing dimensions of zoomed area of
         * the chart
         * 
         * @return Returns the chartArea object containing dimensions of zoomed
         *         area of the chart
         */
        public ChartArea getChartArea() {
            return this.chartArea;
        }
    }

    /**
     * Interface for listening for a {@link ChartZoomEvent} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface ChartZoomListener extends Serializable {
        public void chartZoom(ChartZoomEvent chartZoomEvent);
    }

    private Set<ChartZoomListener> chartZoomListener = new HashSet<ChartZoomListener>();

    /**
     * Adds the chart zoom listener.
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(ChartZoomListener listener) {
        chartZoomListener.add(listener);
        addListener(ChartZoomEvent.class, listener, CHART_ZOOM_METHOD);
    }

    /**
     * Removes the chart zoom listener.
     * 
     * @param listener
     *            the listener to be removed
     */
    public void removeListener(ChartZoomListener listener) {
        chartZoomListener.remove(listener);
        removeListener(ChartZoomEvent.class, listener, CHART_ZOOM_METHOD);
    }

    /**
     * Chart reset zoom event. This event is thrown, when a chart is reset by
     * setting its zoom level to normal.
     * 
     * @author Invient
     */
    public class ChartResetZoomEvent extends Component.Event {
        private InvientCharts chart;

        /**
         * New instance of the chart reset zoom event
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         */
        public ChartResetZoomEvent(Component source, InvientCharts chart) {
            super(source);
            this.chart = chart;
        }

        /**
         * Returns the chart object for which zoom has been reset to normal
         * 
         * @return Returns the chart object for which zoom has been reset to
         *         normal
         */
        public InvientCharts getChart() {
            return this.chart;
        }
    }

    /**
     * Interface for listening for a {@link ChartResetZoomEvent} triggered by
     * {@link InvientCharts}
     * 
     * @author Invient
     * 
     */
    public interface ChartResetZoomListener extends Serializable {
        public void chartResetZoom(ChartResetZoomEvent chartResetZoomEvent);
    }

    private Set<ChartResetZoomListener> chartResetZoomListener = new HashSet<ChartResetZoomListener>();

    /**
     * Adds the chart reset zoom listener.
     * 
     * @param listener
     *            the Listener to be added.
     */
    public void addListener(ChartResetZoomListener listener) {
        chartResetZoomListener.add(listener);
        addListener(ChartResetZoomEvent.class, listener,
                CHART_RESET_ZOOM_METHOD);
    }

    /**
     * Removes the chart reset zoom listener.
     * 
     * @param listener
     *            the listener to be removed
     */
    public void removeListener(ChartResetZoomListener listener) {
        chartResetZoomListener.remove(listener);
        removeListener(ChartResetZoomEvent.class, listener,
                CHART_RESET_ZOOM_METHOD);
    }

    /**
     * Chart SVG event. This event is thrown, when an SVG string representing
     * the chart is received or ready.
     * 
     * Note that this event is thrown only once after a
     * {@link ChartSVGAvailableListener} is registered.
     * 
     * @author Invient
     */
    public class ChartSVGAvailableEvent extends Component.Event {
        private InvientCharts chart;
        private String svg;

        /**
         * New instance of the chart svg available event.
         * 
         * @param source
         *            the chart object itself
         * @param chart
         *            the chart object itself
         * @param svg
         *            an svg string representing the chart object
         */
        public ChartSVGAvailableEvent(Component source, InvientCharts chart,
                String svg) {
            super(source);
            this.chart = chart;
            this.svg = svg;
        }

        /**
         * Returns the chart object for which an svg string representation is
         * available
         * 
         * @return Returns the chart object for which an svg string
         *         representation is available
         */
        public InvientCharts getChart() {
            return this.chart;
        }

        /**
         * 
         * @return Returns an SVG string representing the chart
         */
        public String getSVG() {
            return this.svg;
        }

    }

    /**
     * Interface for listening for a {@link ChartSVGAvailableEvent} triggered by
     * {@link InvientCharts}.
     * 
     * The chart can have only one listener of this type registered at any time.
     * If a listener has already been registered and an attempt is made to
     * register another listener then the previously registered listener will be
     * unregistered and the new listener will be registered.
     * 
     * A listener will be called only once after it has been registered though
     * it will be called again if the same listener is registered again.
     * 
     * @author Invient
     * 
     */
    public interface ChartSVGAvailableListener extends Serializable {
        public void svgAvailable(ChartSVGAvailableEvent chartSVGAvailableEvent);
    }

    private ChartSVGAvailableListener svgAvailableListener;

    /**
     * Adds the chart svg available listener for this chart. If the chart
     * already has a listener of this type then the existing listener will be
     * removed and the argument listener will be registered.
     * 
     * @param listener
     *            the Listener to be added or registered.
     */
    public void addListener(ChartSVGAvailableListener listener) {
        if (svgAvailableListener != null && svgAvailableListener != listener) {
            // remove earlier listener
            removeListener(svgAvailableListener);
        }
        svgAvailableListener = listener;
        addListener(ChartSVGAvailableEvent.class, svgAvailableListener,
                CHART_SVG_AVAILABLE_METHOD);
        isRetrieveSVG = true;
        requestRepaint();
    }

    /**
     * Removes the chart svg available listener for this chart.
     * 
     * @param listener
     *            the listener to be removed or unregistered.
     */
    public void removeListener(ChartSVGAvailableListener listener) {
        if (svgAvailableListener == listener) {
            removeListener(ChartSVGAvailableEvent.class, listener,
                    CHART_SVG_AVAILABLE_METHOD);
            isRetrieveSVG = false;
            svgAvailableListener = null;
        }
    }

    private static final Method CHART_CLICK_METHOD;
    private static final Method CHART_ADD_SERIES_METHOD;
    private static final Method CHART_ZOOM_METHOD;
    private static final Method CHART_RESET_ZOOM_METHOD;
    private static final Method CHART_SVG_AVAILABLE_METHOD;

    static {
        try {
            CHART_CLICK_METHOD = ChartClickListener.class.getDeclaredMethod(
                    "chartClick", new Class[] { ChartClickEvent.class });
            CHART_ADD_SERIES_METHOD = ChartAddSeriesListener.class
                    .getDeclaredMethod("chartAddSeries",
                            new Class[] { ChartAddSeriesEvent.class });
            CHART_ZOOM_METHOD = ChartZoomListener.class.getDeclaredMethod(
                    "chartZoom", new Class[] { ChartZoomEvent.class });
            CHART_RESET_ZOOM_METHOD = ChartResetZoomListener.class
                    .getDeclaredMethod("chartResetZoom",
                            new Class[] { ChartResetZoomEvent.class });
            CHART_SVG_AVAILABLE_METHOD = ChartSVGAvailableListener.class
                    .getDeclaredMethod("svgAvailable",
                            new Class[] { ChartSVGAvailableEvent.class });
        } catch (final java.lang.NoSuchMethodException e) {
            // This should never happen unless there is a typo!
            throw new java.lang.RuntimeException(
                    "Internal error finding methods in Button");
        }
    }

    // *************************************************************************//
    // **************************** Chart Container
    // ****************************//
    // *************************************************************************//
    private LinkedHashSet<Series> chartSeries = new LinkedHashSet<Series>();
    private boolean reloadChartSeries = false;

    /**
     * The data of a chart is defined in terms of {@link Series}. This method
     * removes all previously set series of this chart and adds the argument
     * series. If the argument series is null then no actions are taken.
     * 
     * @param series
     *            A collection of series to set as chart's data
     */
    public void setSeries(LinkedHashSet<Series> series) {
        if (series != null) {
            reloadChartSeries = true;
            this.chartSeries.clear();
            this.seriesCURMap.clear();
            for (Series seriesData : series) {
                addSeries(seriesData);
            }
        }
    }

    /**
     * Returns a series whose name matches the argument name.
     * 
     * @param name
     *            the name of the series
     * @return Returns a series with the given name
     */
    public Series getSeries(String name) {
        for (Series series : this.chartSeries) {
            if (series.getName().equals(name)) {
                return series;
            }
        }
        return null;
    }

    /**
     * Returns all series associated with this chart.
     * @return returns all series associated with this chart.
     */
    public LinkedHashSet<Series> getAllSeries() {
        return this.chartSeries;
    }

    /**
     * Adds the argument series to this chart.
     * 
     * @param seriesData
     *            the series to be added
     */
    public void addSeries(Series seriesData) {
        if (this.chartSeries.add(seriesData)) {
            this.setAxisInSeriesIfNotSetAlready(seriesData);
            seriesData.setInvientCharts(this);
            addSeriesCUROperation(new SeriesCUR(SeriesCURType.ADD,
                    seriesData.getName()));
            requestRepaint();
        }
    }

    // Before sending data to the client, this method sets 
    // axis in all series associated with the chart
    private void setAxisInAllSeriesIfNotSetAlready() {
        for(Series series : this.chartSeries) {
            setAxisInSeriesIfNotSetAlready(series);
        }
    }
    
    private void setAxisInSeriesIfNotSetAlready(Series series) {
        if (this.getConfig() != null) {
            if (series.getXAxis() == null
                    && this.getConfig().getXAxes() != null
                    && this.getConfig().getXAxes().size() > 0) {
                series.setXAxis(this.getConfig().getXAxes().iterator().next());
            }
            if (series.getYAxis() == null
                    && this.getConfig().getYAxes() != null
                    && this.getConfig().getYAxes().size() > 0) {
                series.setYAxis(this.getConfig().getYAxes().iterator().next());
            }
        }
    }

    /**
     * Removes a series whose name matches the argument name.
     * 
     * @param name
     *            the name of the series
     */
    public void removeSeries(String name) {
        for (Iterator<Series> seriesItr = this.chartSeries.iterator(); seriesItr
                .hasNext();) {
            Series series = seriesItr.next();
            if (series.getName().equals(name)) {
                seriesItr.remove();
                series.setInvientCharts(null);
                addSeriesCUROperation(new SeriesCUR(SeriesCURType.REMOVE,
                        series.getName()));
                requestRepaint();
            }
        }
    }

    /**
     * Removes the argument seriesData from this chart.
     * 
     * @param seriesData
     *            the series object to be removed
     */
    public void removeSeries(Series seriesData) {
        if (this.chartSeries.remove(seriesData)) {
            seriesData.setInvientCharts(null);
            addSeriesCUROperation(new SeriesCUR(SeriesCURType.REMOVE,
                    seriesData.getName()));
            requestRepaint();
        }
    }

    /**
     * This class represents a point of the chart's series. A series can have
     * one or more points. A point has (X, Y) coordinates. None of the
     * coordinates are mandatory. The name of a point can be displayed in a
     * tooltip.
     * 
     * To represent no activity or missing points in the chart, create a point
     * with both X and Y as null or just Y as null.
     * 
     * It is possible to specify custom configuration for each point. e.g. If a
     * highest point can be marked in a chart with a different color using this
     * configuration.
     * 
     * A point cannot be created without a series. It must belong to a series.
     * However, the point must be added to a series by calling Series.addPoint()
     * or Series.setPoints() to permanently add point to the series.
     * 
     * @author Invient
     * 
     * @see DecimalPoint
     * @see DateTimePoint
     * @see PointConfig
     * 
     */
    public static abstract class Point implements Serializable {
        private String id;
        private String name;
        private Series series;
        private PointConfig config;
        private boolean isAutosetX;
        private boolean shift;

        /**
         * Creates a point with given arguments.
         * 
         * @param series
         *            The series to which the point must be associated.
         * @exception IllegalArgumentException
         *                If the argument series is null
         * 
         */
        public Point(Series series) {
            if (series == null) {
                throw new IllegalArgumentException(
                        "A point cannot be created without a series.");
            }
            this.series = series;
        }

        /**
         * To allow creation of a point from inside of InvientCharts component
         */
        private Point() {
            // FIXME this is not a correct way of doing it.
        }

        /**
         * Creates a point with given arguments.
         * 
         * @param series
         *            The series to which the point must be associated.
         * @param config
         *            The configuration for this point, if any
         * @exception IllegalArgumentException
         *                If the argument series is null
         */
        public Point(Series series, PointConfig config) {
            this(series);
            this.config = config;
        }

        /**
         * Creates a point with given arguments.
         * 
         * @param series
         *            The series to which the point must be associated.
         * @param name
         *            name of this point
         * @exception IllegalArgumentException
         *                If the argument series is null
         */
        public Point(Series series, String name) {
            this(series);
            this.name = name;
        }

        /**
         * Creates a point with given arguments.
         * 
         * @param series
         *            The series to which the point must be associated.
         * @param name
         *            name of this point
         * @param config
         *            The configuration for this point, if any
         * @exception IllegalArgumentException
         *                If the argument series is null
         */
        public Point(Series series, String name, PointConfig config) {
            this(series, name);
            this.config = config;
        }

        String getId() {
            return id;
        }

        /**
         * 
         * @return Returns name of this point
         */
        public String getName() {
            return name;
        }

        /**
         * Sets name of this point
         * 
         * @param name
         *            name of this point
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 
         * @return Returns {@link Series} associated with this point
         */
        public Series getSeries() {
            return series;
        }

        /**
         * 
         * @return Returns {@link PointConfig} for this point
         */
        public PointConfig getConfig() {
            return config;
        }

        /**
         * Sets {@link PointConfig} for this point
         * 
         * @param config
         *            configuration of this point
         * @see PointConfig
         */
        public void setConfig(PointConfig config) {
            this.config = config;
        }

        /**
         * 
         * @return Returns true if X value of this point is set programmatically
         */
        boolean isAutosetX() {
            return isAutosetX;
        }

        /**
         * If the argument is true it indicates that the X value of this point
         * is set programmatically and user has not specified it.
         * 
         * @return
         */
        void setAutosetX(boolean isAutosetX) {
            this.isAutosetX = isAutosetX;
        }

        /**
         * 
         * @return Returns true if a point at the start of the series should be
         *         shifted off when this point is appended otherwise false.
         */
        boolean isShift() {
            return shift;
        }

        /**
         * A value of true means one point is shifted off the start of the
         * series as one is appended to the end.
         * 
         * @param shift
         */
        void setShift(boolean shift) {
            this.shift = shift;
        }

        /**
         * 
         * @return Returns X value of this point
         */
        public abstract Object getX();

        /**
         * 
         * @return Returns Y value of this point
         */
        public abstract Object getY();

        @Override
        public String toString() {
            return "Point [id=" + id + ", name=" + name + ", series="
                    + series.getName() + ", config=" + config + "]";
        }

    }

    /**
     * This class represent a point with (X, Y) both as number. It should be
     * used to add points to {@link XYSeries}
     * 
     * @author Invient
     * 
     */
    public static final class DecimalPoint extends Point {
        private Double x;
        private Double y;

        /**
         * @param series
         *            the series to which this belongs to
         */
        public DecimalPoint(Series series) {
            super(series);
        }

        /**
         * @param series
         *            the series to which this point belongs to
         * @param y
         *            the y value of this point
         */
        public DecimalPoint(Series series, double y) {
            super(series);
            this.y = y;
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param name
         *            the name of this point
         * @param y
         *            the y value of this point
         */
        public DecimalPoint(Series series, String name, double y) {
            super(series, name);
            this.y = y;
        }

        /**
         * To allow creation of a point within the InvientChart.
         * 
         * @param x
         *            the x value of this point
         * @param y
         *            the y value of this point
         */
        private DecimalPoint(double x, double y) {
            // FIXME this is not a correct way of doing it.
            super();
            this.x = x;
            this.y = y;
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param name
         *            the name for this point
         * @param y
         *            the y value of this point
         * @param config
         */
        public DecimalPoint(Series series, String name, double y,
                PointConfig config) {
            super(series, name, config);
            this.y = y;
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param y
         *            the y value of this point
         * @param config
         *            the configuration for this point
         */
        public DecimalPoint(Series series, double y, PointConfig config) {
            super(series, config);
            this.y = y;
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param x
         *            the x value of this point
         * @param y
         *            the y value of this point
         */
        public DecimalPoint(Series series, double x, double y) {
            this(series, x, y, null);
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param x
         *            the x value of this point
         * @param y
         *            the y value of this point
         */
        public DecimalPoint(Series series, Double x, Double y) {
            this(series, x, y, null);
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param x
         *            the x value of this point
         * @param y
         *            the y value of this point
         * @param config
         *            the configuration of this point
         */
        public DecimalPoint(Series series, double x, double y,
                PointConfig config) {
            super(series, config);
            this.x = x;
            this.y = y;
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param x
         *            the x value of this point
         * @param y
         *            the y value of this point
         * @param config
         *            the configuration of this point
         */
        public DecimalPoint(Series series, Double x, Double y,
                PointConfig config) {
            super(series, config);
            this.x = x;
            this.y = y;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.invient.vaadin.chart.InvientChart.Point#getX()
         */
        @Override
        public Double getX() {
            return x;
        }

        /**
         * Sets the x value of this point
         * 
         * @param x
         */
        private void setX(Double x) {
            this.x = x;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.invient.vaadin.chart.InvientChart.Point#getY()
         */
        @Override
        public Double getY() {
            return y;
        }

        /**
         * Sets the y value of this point
         * 
         * @param y
         */
        private void setY(Double y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "DecimalPoint [x=" + x + ", y=" + y + ", id=" + getId()
                    + ", name=" + getName() + ", seriesName="
                    + (getSeries() != null ? getSeries().getName() : "") + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((y == null) ? 0 : y.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DecimalPoint other = (DecimalPoint) obj;
            // If x is null then return always false as x is calculated if not
            // specified
            if (x == null || other.x == null) {
                return false;
            }
            if (!x.equals(other.x))
                return false;
            if (y == null) {
                if (other.y != null)
                    return false;
            } else if (other.y == null) {
                return false;
            } else if (y.compareTo(other.y) != 0)
                return false;
            return true;
        }

    }

    /**
     * This class represent a point with (X, Y) both as number. It should be
     * used to add points to {@link DateTimeSeries}
     * 
     * @author Invient
     * 
     */
    public static final class DateTimePoint extends Point {
        private Date x;
        private Double y;

        /**
         * @param series
         *            the series to which this belongs to
         */
        public DateTimePoint(Series series) {
            super(series);
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param y
         *            the y value of this point
         */
        public DateTimePoint(Series series, double y) {
            this(series, "", y);
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param name
         *            the name of this point
         * @param y
         *            the y value of this point
         */
        public DateTimePoint(Series series, String name, double y) {
            super(series, name);
            this.y = y;
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param name
         *            the name of this point
         * @param y
         *            the y value of this point
         * @param config
         */
        public DateTimePoint(Series series, String name, double y,
                PointConfig config) {
            super(series, name, config);
            this.y = y;
        }

        /**
         * @param series
         *            the series to which this belongs to
         * @param x
         *            the x value of this point
         * @param y
         *            the y value of this point
         */
        public DateTimePoint(Series series, Date x, double y) {
            this(series, y);
            this.x = x;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.invient.vaadin.chart.InvientChart.Point#getX()
         */
        public Date getX() {
            return x;
        }

        /**
         * Sets the x value of this point
         * 
         * @param x
         */
        private void setX(Date x) {
            this.x = x;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.invient.vaadin.chart.InvientChart.Point#getY()
         */
        public Double getY() {
            return y;
        }

        /**
         * Sets the y value of this point
         * 
         * @param y
         */
        private void setY(Double y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "DateTimePoint [x="
                    + getDateInMilliseconds(
                            x,
                            (getSeries() != null ? ((DateTimeSeries) getSeries())
                                    .isIncludeTime() : false)) + ", y=" + y
                    + ", id=" + getId() + ", name=" + getName()
                    + ", seriesName="
                    + (getSeries() != null ? getSeries().getName() : "") + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((y == null) ? 0 : y.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DateTimePoint other = (DateTimePoint) obj;
            // If x is null then return always false as x is calculated if not
            // specified
            if (x == null || other.x == null) {
                return false;
            }

            boolean pointIncludeTime = (this.getSeries() instanceof DateTimeSeries ? ((DateTimeSeries) this
                    .getSeries()).isIncludeTime() : false);
            boolean pointOtherIncludeTime = (other.getSeries() instanceof DateTimeSeries ? ((DateTimeSeries) other
                    .getSeries()).isIncludeTime() : false);
            Long pointX = getDateInMilliseconds(x, pointIncludeTime);
            Long pointOtherX = getDateInMilliseconds(other.x,
                    pointOtherIncludeTime);

            if (pointX.compareTo(pointOtherX) != 0)
                return false;

            if (y == null) {
                if (other.y != null)
                    return false;
            } else if (other.y == null) {
                return false;
            } else if (y.compareTo(other.y) != 0)
                return false;
            return true;
        }
    }

    /**
     * This class defines a series of the chart. A series contains a collection
     * of points. Series can be one of types defined by {@link SeriesType}.
     * 
     * Each series must have unique name. If an attempt is made to add two
     * series with same then only the first added series will be in effect.
     * 
     * If the series type is not specified, it defaults to chart type and the
     * default chart type is SeriesType.LINE. A series has unique xAxis and
     * yAxis object associated with it. There is no need to set xAxis and yAxis
     * unless the chart has more than one one axis of any type and the series
     * must belong to any of the secondary axis.
     * 
     * It is also possible to specify configuration for individual series and
     * not just series type.
     * 
     * @author Invient
     * 
     */
    public static abstract class Series implements Serializable {
        private LinkedHashSet points = new LinkedHashSet();
        private String name = "";
        private SeriesType type;
        private String stack;
        private XAxis xAxis;
        private YAxis yAxis;
        private SeriesConfig config;
        private InvientCharts invientCharts;

        /**
         * Creates a series with given name
         * 
         * @param name
         *            the name of this series
         */
        public Series(String name) {
            this.name = name;
        }

        /**
         * Creates a series with given name and type
         * 
         * @param name
         *            the name of this series
         * @param seriesType
         *            the type of this series
         */
        public Series(String name, SeriesType seriesType) {
            this(name);
            this.type = seriesType;
        }

        /**
         * Creates a series with given name and configuration
         * 
         * @param name
         *            the name of this series
         * @param config
         *            the configuration for this series
         */
        public Series(String name, SeriesConfig config) {
            this(name);
            this.config = config;
        }

        /**
         * Creates a series with given name, type and configuration
         * 
         * @param name
         *            the name of this series
         * @param seriesType
         *            the type of this series
         * @param config
         *            the configuration for this series
         */
        public Series(String name, SeriesType seriesType, SeriesConfig config) {
            this(name, config);
            this.type = seriesType;
        }

        /**
         * @return Returns the configuration object associated with this series
         */
        public SeriesConfig getConfig() {
            return config;
        }

        /**
         * @return Returns name of this series
         */
        public String getName() {
            return name;
        }

        /**
         * Sets name of this series
         * 
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return
         */
        public SeriesType getType() {
            return type;
        }

        /**
         * Sets type of this series
         * 
         * @param type
         */
        public void setType(SeriesType type) {
            this.type = type;
        }

        /**
         * @return Returns stack of this series
         */
        public String getStack() {
            return stack;
        }

        /**
         * By using this stack property, it is possible to group series in a
         * stacked chart. Sets stack for this series. If two series belongs to
         * the same stack then the resultant chart will be stacked chart
         * 
         * @param stack
         */
        public void setStack(String stack) {
            this.stack = stack;
        }

        /**
         * @return Returns x-axis associated with this series.
         * @see Axis
         */
        public XAxis getXAxis() {
            return xAxis;
        }

        /**
         * Sets x-axis of this series. A series can be associated with at most
         * one x-axis.
         * 
         * @param xAxis
         */
        public void setXAxis(XAxis xAxis) {
            this.xAxis = xAxis;
        }

        /**
         * @return Returns y-axis of this series.
         */
        public YAxis getYAxis() {
            return yAxis;
        }

        /**
         * Sets y-axis of this series. A series can be associated with at most
         * one y-axis.
         * 
         * @param yAxis
         */
        public void setYAxis(YAxis yAxis) {
            this.yAxis = yAxis;
        }

        /**
         * @param points
         */
        protected void removePoint(Point... points) {
            List<Point> pointsRemovedList = new ArrayList<Point>();
            for (Point point : points) {
                if (this.points.remove(point)) {
                    pointsRemovedList.add(point);
                }
            }
            updatePointXValuesIfNotPresent();
            for (Point point : pointsRemovedList) {
                if (this.invientCharts != null) {
                    this.invientCharts.addSeriesPointRemovedOperation(point
                            .getSeries().getName(), point);
                    this.invientCharts.requestRepaint();
                }
            }
        }

        /**
         * Removes all points in this series
         */
        protected void removeAllPoints() {
            this.points.clear();
            if (this.invientCharts != null) {
                this.invientCharts.addSeriesCUROperation(new SeriesCUR(
                        SeriesCURType.UPDATE, this.getName(), true));
                this.invientCharts.requestRepaint();
            }
        }

        /**
         * Adds one or more points into this series, specified as an argument to
         * this method
         * 
         * @param points
         * @return Returns null if the argument is null otherwise returns a
         *         collection of points which are added in this series. If a
         *         point has same (x, y) value as any other point in the
         *         argument points then it will not be added.
         */
        protected LinkedHashSet addPoint(boolean shift, Point... points) {
            if (shift) {
                // Remove first point as other points gets appended at the end
                Iterator pointsItr = this.points.iterator();
                if (pointsItr.hasNext()) {
                    pointsItr.next();
                    pointsItr.remove();
                }
            }
            List<Point> pointsAddedList = new ArrayList<Point>();
            for (Point point : points) {
                if (this.points.add(point)) {
                    pointsAddedList.add(point);
                }
            }
            updatePointXValuesIfNotPresent();
            // Now record point add event as we need to know x value of a point
            for (Point point : pointsAddedList) {
                if (this.invientCharts != null) {
                    this.invientCharts.addSeriesPointAddedOperation(point
                            .getSeries().getName(), point);
                    this.invientCharts.requestRepaint();
                }
            }
            return new LinkedHashSet(pointsAddedList);
        }

        private void addPointsInternal(LinkedHashSet<? extends Point> points) {
            for (Point point : points) {
                this.points.add(point);
            }
        }

        /**
         * 
         * @return Returns all points of this series. Adding or removing any
         *         point to or from the returned collection will not impact the
         *         chart. To add a point or points, use addPoint() or
         *         removePoint() method.
         */
        protected LinkedHashSet getPoints() {
            return new LinkedHashSet(this.points);
        }

        /**
         * Sets points into this series
         * 
         * @param points
         * @return Returns null if the argument is null otherwise returns a
         *         collection of points which are set in this series. If a point
         *         has same (x, y) value as any other point in the argument
         *         points then it will not be added.
         */
        protected LinkedHashSet setPoints(LinkedHashSet<? extends Point> points) {
            if (points != null) {
                this.points.clear();
                addPointsInternal(points);
                updatePointXValuesIfNotPresent();
                if (this.invientCharts != null) {
                    this.invientCharts.addSeriesCUROperation(new SeriesCUR(
                            SeriesCURType.UPDATE, this.getName(), true));
                    this.invientCharts.requestRepaint();
                }
                return getPoints();
            }
            return null;
        }

        /**
         * Each of the subclass needs to implement this method to ensure that
         * each point has appropriate X value even if it is not specified.
         */
        protected abstract void updatePointXValuesIfNotPresent();

        /**
         * Show this series
         */
        public void show() {
            this.config = (this.config == null ? new SeriesConfig()
                    : this.config);
            this.config.setVisible(true);
            if (this.invientCharts != null) {
                this.invientCharts.addSeriesCUROperation(new SeriesCUR(
                        SeriesCURType.UPDATE, this.getName()));
                this.invientCharts.requestRepaint();
            }
        }

        /**
         * Hide this series
         */
        public void hide() {
            this.config = (this.config == null ? new SeriesConfig()
                    : this.config);
            this.config.setVisible(false);
            if (this.invientCharts != null) {
                this.invientCharts.addSeriesCUROperation(new SeriesCUR(
                        SeriesCURType.UPDATE, this.getName()));
                this.invientCharts.requestRepaint();
            }
        }

        void setInvientCharts(InvientCharts invientCharts) {
            this.invientCharts = invientCharts;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Series other = (Series) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Series [points=" + points + ", name=" + name + ", type="
                    + type + ", stack=" + stack + ", xAxis=" + xAxis
                    + ", yAxis=" + yAxis + ", config=" + config + "]";
        }
    }

    /**
     * This class defines a number series. In this series both X and Y values
     * must be number. To use date values, use {@link DateTimeSeries}
     * 
     * @author Invient
     * 
     * @see DateTimeSeries
     */
    public static class XYSeries extends Series {

        /**
         * Creates a series with given name
         * 
         * @param name
         *            the name of this series
         */
        public XYSeries(String name) {
            super(name);
        }

        /**
         * Creates a series with given name and configuration
         * 
         * @param name
         *            the name of this series
         * @param config
         *            the configuration for this series
         */
        public XYSeries(String name, SeriesConfig config) {
            super(name, config);
        }

        /**
         * Creates a series with given name and type
         * 
         * @param name
         *            the name of this series
         * @param seriesType
         *            the type of this series
         */
        public XYSeries(String name, SeriesType seriesType) {
            super(name, seriesType);
        }

        /**
         * Creates a series with given name, type and configuration
         * 
         * @param name
         *            the name of this series
         * @param seriesType
         *            the type of this series
         * @param config
         *            the configuration for this series
         */
        public XYSeries(String name, SeriesType seriesType, SeriesConfig config) {
            super(name, seriesType, config);
        }

        /**
         * Removes the specified point from the series
         * 
         * @param points
         */
        public void removePoint(DecimalPoint... points) {
            super.removePoint(points);
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.invient.vaadin.chart.InvientChart.Series#removeAllPoints()
         */
        public void removeAllPoints() {
            super.removeAllPoints();
        }

        /**
         * Appends the specified point into the series if they do not exists in
         * this series. The points which already exists will not be appended. A
         * collection of points appended to this series will be returned.
         * 
         * @param points
         * @return Returns a collection of points which are added in this
         *         series. If a point has same (x, y) value as any other point
         *         in the input argument points then it will not be added in
         *         this series.
         */
        public LinkedHashSet<DecimalPoint> addPoint(DecimalPoint... points) {
            return super.addPoint(false, points);
        }

        /**
         * Append the specified point into this series. If the argument shift is
         * true then one point is shifted off the start of this series as one is
         * appended to the end.
         * 
         * @param points
         * @param shift
         *            If true then one point is shifted off the start of this
         *            series as one is appended to the end.
         * @return Returns a collection of points which are added in this
         *         series. If a point has same (x, y) value as any other point
         *         in the input argument points then it will not be added in
         *         this series.
         */
        public LinkedHashSet<DecimalPoint> addPoint(DecimalPoint point,
                boolean shift) {
            point.setShift(shift);
            return super.addPoint(shift, point);
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.invient.vaadin.chart.InvientChart.Series#getPoints()
         */
        public LinkedHashSet<DecimalPoint> getPoints() {
            return super.getPoints();
        }

        /**
         * Sets points into this series. This method removes all of its points
         * and then add points specified in the method argument. If the argument
         * is null then no actions are taken.
         * 
         * @param points
         *            the collection of points to set into this series.
         * @return Returns a collection of points which are set in this series.
         *         If a point has same (x, y) value as any other point in the
         *         argument points then it will not be added.
         */
        public LinkedHashSet<DecimalPoint> setSeriesPoints(
                LinkedHashSet<DecimalPoint> points) {
            return super.setPoints(points);
        }

        @Override
        protected void updatePointXValuesIfNotPresent() {
            double pointStart = 0;
            double pointInterval = 1;
            if (super.getConfig() instanceof BaseLineConfig) {
                BaseLineConfig config = (BaseLineConfig) super.getConfig();
                if (config.getPointStart() != null) {
                    pointStart = config.getPointStart();
                }
                if (config.getPointInterval() != null) {
                    pointInterval = config.getPointInterval();
                }
            }
            int count = 0;
            for (DecimalPoint point : getPoints()) {
                if ((point.getX() == null || (point.getX() != null && point
                        .isAutosetX()))) {
                    point.setAutosetX(true);
                    if (count == 0) {
                        point.setX(pointStart);
                        count++;
                    } else {
                        pointStart = pointStart + pointInterval;
                        point.setX(pointStart);
                    }
                }
            }
        }

    }

    /**
     * This class defines a datetime series. In this series, the X value must be
     * date and Y values must be number. To use number values, use
     * {@link XYSeries}
     * <p>
     * By default, the time of a day is not included in the X value. In order to
     * include time, use a constructor with argument isIncludeTime and pass true
     * value for the argument.
     * 
     * @author Invient
     * 
     * @see XYSeries
     */
    public static class DateTimeSeries extends Series {
        private boolean includeTime;

        /**
         * Creates a series with given name. This series will not consider time
         * in the X property of {@link DateTimePoint}. To include time, use any
         * constructor having isIncludeTime as part of the arguments.
         * 
         * @param name
         *            the name of this series
         */
        public DateTimeSeries(String name) {
            this(name, false);
        }

        /**
         * Creates a series with given name and boolean value.
         * 
         * @param name
         *            the name of this series
         * @param isIncludeTime
         *            If true then the time in the X property of
         *            {@link DateTimePoint} will be considered when drawing the
         *            chart. Defaults to false.
         */
        public DateTimeSeries(String name, boolean isIncludeTime) {
            super(name);
            this.includeTime = isIncludeTime;
        }

        /**
         * Creates a series with given name and configuration.
         * 
         * @param name
         *            the name of this series
         * @param config
         *            the configuration for this series
         */
        public DateTimeSeries(String name, SeriesConfig config) {
            this(name, config, false);
        }

        /**
         * Creates a series with given name, configuration and boolean value.
         * 
         * @param name
         *            the name of this series
         * @param config
         *            the configuration for this series
         * @param isIncludeTime
         *            If true then the time in the X property of
         *            {@link DateTimePoint} will be considered when drawing the
         *            chart. Defaults to false.
         */
        public DateTimeSeries(String name, SeriesConfig config,
                boolean isIncludeTime) {
            super(name, config);
            this.includeTime = isIncludeTime;
        }

        /**
         * Creates a series with given name and type.
         * 
         * @param name
         *            the name of this series
         * @param seriesType
         *            the type of this series
         */
        public DateTimeSeries(String name, SeriesType seriesType) {
            this(name, seriesType, false);
        }

        /**
         * Creates a series with given name, type and boolean value.
         * 
         * @param name
         *            the name of this series
         * @param seriesType
         *            the type of this series
         * @param isIncludeTime
         *            If true then the time in the X property of
         *            {@link DateTimePoint} will be considered when drawing the
         *            chart. Defaults to false.
         */
        public DateTimeSeries(String name, SeriesType seriesType,
                boolean isIncludeTime) {
            super(name, seriesType);
            this.includeTime = isIncludeTime;
        }

        /**
         * Creates a series with given name, type and configuration.
         * 
         * @param name
         *            the name of this series
         * @param seriesType
         *            the type of this series
         * @param config
         *            the configuration for this series
         */
        public DateTimeSeries(String name, SeriesType seriesType,
                SeriesConfig config) {
            this(name, seriesType, config, false);
        }

        /**
         * Creates a series with given name, type, configuration and boolean
         * value.
         * 
         * @param name
         *            the name of this series
         * @param seriesType
         *            the type of this series
         * @param config
         *            the configuration for this series
         * @param isIncludeTime
         *            If true then the time in the X property of
         *            {@link DateTimePoint} will be considered when drawing the
         *            chart. Defaults to false.
         */
        public DateTimeSeries(String name, SeriesType seriesType,
                SeriesConfig config, boolean isIncludeTime) {
            super(name, seriesType, config);
            this.includeTime = isIncludeTime;
        }

        /**
         * Removes all points specified as method argument into this series
         * 
         * @param points
         */
        public void removePoint(DateTimePoint... points) {
            super.removePoint(points);
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.invient.vaadin.chart.InvientChart.Series#removeAllPoints()
         */
        public void removeAllPoints() {
            super.removeAllPoints();
        }

        /**
         * Appends the specified point into the series if they do not exists in
         * this series. The points which already exists will not be appended. A
         * collection of points appended to this series will be returned.
         * 
         * @param points
         * @return Returns a collection of points which are added in this
         *         series. If a point has same (x, y) value as any other point
         *         in the input argument points then it will not be added in
         *         this series.
         */
        public LinkedHashSet<DateTimePoint> addPoint(DateTimePoint... points) {
            return super.addPoint(false, points);
        }

        /**
         * Append the specified point into this series. If the argument shift is
         * true then one point is shifted off the start of this series as one is
         * appended to the end.
         * 
         * @param point
         *            A point to be added at the end of this series
         * @param shift
         *            If true then one point is shifted off the start of this
         *            series as one is appended to the end.
         * @return Returns a collection of points which are added in this
         *         series. If a point has same (x, y) value as any other point
         *         in the input argument points then it will not be added in
         *         this series.
         */
        public LinkedHashSet<DateTimePoint> addPoint(DateTimePoint point,
                boolean shift) {
            point.setShift(shift);
            return super.addPoint(shift, point);
        }

        /**
         * 
         * @return Returns true if the time in the X property of
         *         {@link DateTimePoint} will be considered when drawing the
         *         chart otherwise false.
         */
        public boolean isIncludeTime() {
            return includeTime;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.invient.vaadin.chart.InvientChart.Series#getPoints()
         */
        public LinkedHashSet<DateTimePoint> getPoints() {
            return super.getPoints();
        }

        /**
         * Sets points into this series. This method removes all of its points
         * and then add points specified in the method argument. If the argument
         * is null then no actions are taken.
         * 
         * @param points
         *            the collection of points to set into this series.
         * @return Returns a collection of points which are added in this
         *         series. If a point has same (x, y) value as any other point
         *         in the input argument points then it will not be added in
         *         this series.
         */
        public LinkedHashSet<DateTimePoint> setSeriesPoints(
                LinkedHashSet<DateTimePoint> points) {
            return super.setPoints(points);
        }

        @Override
        protected void updatePointXValuesIfNotPresent() {
            double pointStart = (double) getDefPointStart();
            double pointInterval = 3600000; // 1 hour
            if (super.getConfig() instanceof BaseLineConfig) {
                BaseLineConfig config = (BaseLineConfig) super.getConfig();
                if (config.getPointStart() != null) {
                    pointStart = config.getPointStart();
                }
                if (config.getPointInterval() != null) {
                    pointInterval = config.getPointInterval();
                }
            }
            Date prevDate = new Date((long) pointStart);
            int count = 0;
            for (DateTimePoint point : getPoints()) {
                if ((point.getX() == null || (point.getX() != null && point
                        .isAutosetX()))) {
                    point.setAutosetX(true);
                    if (count == 0) {
                        point.setX(prevDate);
                        count++;
                    } else {
                        point.setX(getUpdatedDate(prevDate,
                                (long) pointInterval));
                        prevDate = point.getX();
                    }
                }
            }
        }

        private static long getDefPointStart() {
            Calendar cal = GregorianCalendar.getInstance();
            cal.set(Calendar.YEAR, 1970);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTimeInMillis();
        }

        private static Date getUpdatedDate(Date dt, long milliseconds) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(dt.getTime() + milliseconds);
            return cal.getTime();
        }

        @Override
        public String toString() {
            return "DateTimeSeries [includeTime=" + includeTime
                    + ", getConfig()=" + getConfig() + ", getName()="
                    + getName() + ", getType()=" + getType() + ", getStack()="
                    + getStack() + ", getXAxis()=" + getXAxis()
                    + ", getYAxis()=" + getYAxis() + "]";
        }

    }

    // *******************************************************************//
    // *************** Highcharts Configuration options ******************//
    // *******************************************************************//

    public static enum SeriesType {
        COMMONSERIES("series"), LINE("line"), SPLINE("spline"), SCATTER(
                "scatter"), AREA("area"), AREASPLINE("areaspline"), BAR("bar"), COLUMN(
                "column"), PIE("pie");
        private String type;

        private SeriesType(String type) {
            this.type = type;
        }

        public String getName() {
            return this.type;
        }
    }

    static class SeriesCUR implements Serializable {
        private SeriesCURType type;
        private String name;
        private boolean reloadPoints = false;
        private LinkedHashSet<Point> pointsAdded = new LinkedHashSet<InvientCharts.Point>();
        private LinkedHashSet<Point> pointsRemoved = new LinkedHashSet<InvientCharts.Point>();

        public SeriesCURType getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public SeriesCUR(SeriesCURType type, String name) {
            super();
            this.type = type;
            this.name = name;
        }

        public SeriesCUR(SeriesCURType type, String name, boolean reloadPoints) {
            super();
            this.type = type;
            this.name = name;
            this.reloadPoints = reloadPoints;
        }

        /**
         * Indicates whether the client/terminal should update series by setting
         * all data of a series instead of adding or removing individual points
         * 
         * @return Returns true if the data of the series must be reloaded
         *         otherwise false.
         */
        boolean isReloadPoints() {
            return reloadPoints;
        }

        void setReloadPoints(boolean reloadPoints) {
            this.reloadPoints = reloadPoints;
        }

        void trackPointAdded(Point point) {
            pointsAdded.add(point);
        }

        void trackPointRemoved(Point point) {
            // If the point was added earlier and now removed
            // then there is no need to record its add/remove operation
            // as add of a point is nullified by remove of a point
            if (!removePointIfTrackedAlready(point)) {
                pointsRemoved.add(point);
            }
        }

        boolean removePointIfTrackedAlready(Point point) {
            return pointsAdded.remove(point);
        }

        // Used to clear all points added/removed when
        // series data is set/cleared using series.setPoints() or
        // series.removeAllPoints()
        void clearTrackedPoints() {
            pointsAdded.clear();
            pointsRemoved.clear();
        }

        public LinkedHashSet<Point> getPointsAdded() {
            return pointsAdded;
        }

        public LinkedHashSet<Point> getPointsRemoved() {
            return pointsRemoved;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + ((type == null) ? 0 : type.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            SeriesCUR other = (SeriesCUR) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            if (type == null) {
                if (other.type != null)
                    return false;
            } else if (!type.equals(other.type))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "SeriesCUR [type=" + type + ", name=" + name
                    + ", reloadPoints=" + reloadPoints + ", pointsAdded="
                    + pointsAdded + ", pointsRemoved=" + pointsRemoved + "]";
        }

        static enum SeriesCURType {
            ADD("Add"), UPDATE("Update"), REMOVE("Remove");
            private String name;

            private SeriesCURType(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }
        }
    }

    private LinkedHashMap<String, LinkedHashSet<SeriesCUR>> seriesCURMap = new LinkedHashMap<String, LinkedHashSet<SeriesCUR>>();

    boolean addSeriesCUROperation(SeriesCUR newSeriesCUR) {
        //
        if (seriesCURMap.keySet().contains(newSeriesCUR.getName())) {
            LinkedHashSet<SeriesCUR> seriesCURSet = seriesCURMap
                    .get(newSeriesCUR.getName());
            // If for a series, no operation is found
            if (seriesCURSet == null || seriesCURSet.size() == 0) {
                seriesCURSet = new LinkedHashSet<InvientCharts.SeriesCUR>();
                seriesCURSet.add(newSeriesCUR);
                seriesCURMap.put(newSeriesCUR.getName(), seriesCURSet);
            } else if (seriesCURSet.contains(newSeriesCUR)) {
                SeriesCUR seriesCUR = getMatchedSeriesCUR(seriesCURSet,
                        newSeriesCUR);
                // In case of series update (due to series.show/hide or
                // series.setPoints or series.removeAllPoints)
                // we need to check if all points of a series are set afresh. If
                // so then
                // set a flag to indicate that instead of adding and removing
                // points to and from series, set series data in full.
                if (seriesCUR.getType().equals(SeriesCURType.UPDATE)) {
                    seriesCUR.setReloadPoints(newSeriesCUR.isReloadPoints());
                    if (newSeriesCUR.isReloadPoints()) {
                        seriesCUR.clearTrackedPoints();
                    }
                    return true;
                }
                // Operation on a series has already been recorded
                return false;
            } else {
                Iterator<SeriesCUR> seriesCURItr = seriesCURSet.iterator();
                while (seriesCURItr.hasNext()) {
                    SeriesCUR seriesCUR = seriesCURItr.next();
                    if (seriesCUR.getName().equals(newSeriesCUR.getName())) {
                        if (SeriesCURType.REMOVE.equals(newSeriesCUR.getType())
                                && SeriesCURType.ADD
                                        .equals(seriesCUR.getType())) {
                            // Remove addition of a series as there is no reason
                            // to add
                            // a series and
                            // then remove it. E.g. If a new series is added and
                            // then
                            // removed then
                            // actually there is nothing to be done
                            seriesCURItr.remove();
                            return false;
                        }
                        if (SeriesCURType.UPDATE.equals(newSeriesCUR.getType())
                                && SeriesCURType.ADD
                                        .equals(seriesCUR.getType())) {
                            // There is no need for update as adding a series
                            // will
                            // take care of applying any update to the series
                            // attributes
                            // specifically visibility
                            return false;
                        }
                        if (SeriesCURType.REMOVE.equals(newSeriesCUR.getType())
                                && SeriesCURType.UPDATE.equals(seriesCUR
                                        .getType())) {
                            // Remove update of a series as there is no reason
                            // to update
                            // a series
                            // and then remove it. E.g. If an existing series
                            // was
                            // updated (for show/hide) and
                            // then removed then series need not be updated
                            // after all it
                            // is going to be
                            // removed. Hover, the remove operation must be
                            // captured.
                            seriesCURItr.remove();
                            break;
                        }
                    }
                }
            }
            seriesCURSet.add(newSeriesCUR);
            return true;
        } else {
            LinkedHashSet<SeriesCUR> seriesCURSet = new LinkedHashSet<InvientCharts.SeriesCUR>();
            seriesCURSet.add(newSeriesCUR);
            seriesCURMap.put(newSeriesCUR.getName(), seriesCURSet);
            return true;
        }
    }

    void addSeriesPointAddedOperation(String seriesName, Point point) {
        LinkedHashSet<SeriesCUR> seriesCURSet = seriesCURMap.get(seriesName);
        if (seriesCURSet == null || seriesCURSet.size() == 0) {
            SeriesCUR seriesCUR = new SeriesCUR(SeriesCURType.UPDATE,
                    seriesName);
            seriesCUR.trackPointAdded(point);
            seriesCURSet = new LinkedHashSet<InvientCharts.SeriesCUR>();
            seriesCURSet.add(seriesCUR);
            seriesCURMap.put(seriesName, seriesCURSet);
        } else {
            SeriesCUR lastSeriesCur = getLastSeriesCUR(seriesCURSet);
            // Track points only if series is updated.
            // Tracking point is useless in following cases
            // 1. A new series is added : In this case, a series will be added
            // with all points so no need to track
            // 2. A series is removed : In this case, a series will be removed
            // and hence any point added to the series doesn't carry any
            // meaning.
            if (lastSeriesCur.getType().equals(SeriesCURType.UPDATE)
                    && !lastSeriesCur.isReloadPoints()) {
                lastSeriesCur.trackPointAdded(point);
            }
        }
    }

    private SeriesCUR getLastSeriesCUR(LinkedHashSet<SeriesCUR> seriesCURSet) {
        if (seriesCURSet == null || seriesCURSet.size() == 0) {
            return null;
        }
        SeriesCUR lastSeriesCur = null;
        for (SeriesCUR seriesCur : seriesCURSet) {
            lastSeriesCur = seriesCur;
        }
        return lastSeriesCur;
    }

    private SeriesCUR getMatchedSeriesCUR(
            LinkedHashSet<SeriesCUR> seriesCURSet,
            SeriesCUR matchAgainstSeriesCUR) {
        for (SeriesCUR seriesCur : seriesCURSet) {
            if (matchAgainstSeriesCUR.equals(seriesCur)) {
                return seriesCur;
            }
        }
        return null;
    }

    void addSeriesPointRemovedOperation(String seriesName, Point point) {
        LinkedHashSet<SeriesCUR> seriesCURSet = seriesCURMap.get(seriesName);
        if (seriesCURSet == null || seriesCURSet.size() == 0) {
            SeriesCUR seriesCUR = new SeriesCUR(SeriesCURType.UPDATE,
                    seriesName);
            seriesCUR.trackPointRemoved(point);
            seriesCURSet = new LinkedHashSet<InvientCharts.SeriesCUR>();
            seriesCURSet.add(seriesCUR);
            seriesCURMap.put(seriesName, seriesCURSet);
        } else {
            SeriesCUR lastSeriesCur = getLastSeriesCUR(seriesCURSet);
            // Track points only if series is updated.
            // Tracking point is useless in following cases
            // 1. A new series is added : In this case, a series will be added
            // with all points so no need to track
            // 2. A series is removed : In this case, a series will be removed
            // and hence any point removed from the series
            // doesn't carry any meaning.
            if (lastSeriesCur.getType().equals(SeriesCURType.UPDATE)
                    && !lastSeriesCur.isReloadPoints()) {
                lastSeriesCur.trackPointRemoved(point);
            }
        }
    }

    /**
     * After a series is added or removed, there is no need to call this method
     * as it is handled implicitly. This method will send updates to the client.
     * This method should be called after adding/removing plotbands and
     * plotlines. This inconsistency will be fixed in next revision.
     * 
     */
    public void refresh() {
        super.requestRepaint();
    }

    /**
     * Displays a Print dialog of the Webkit to print this chart. Invoking this
     * method causes the Webkit to hide other widgets on the screen and only
     * this chart widget will be visible. Also it prints this chart widget as it
     * is displayed.
     * 
     */
    public void print() {
        isPrint = true;
        requestRepaint();
    }

}