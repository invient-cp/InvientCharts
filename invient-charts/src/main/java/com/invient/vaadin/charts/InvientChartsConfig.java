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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import com.invient.vaadin.charts.InvientCharts.SeriesType;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.AxisTitle;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.AxisType;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.Grid;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.MinorGrid;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.MinorTick;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.Tick;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.WeekDay;

/**
 * This class encapsulates a number of configuration options for the
 * InvientChars. These configuration options are {@link Title}, {@link SubTitle}
 * , {@link GeneralChartConfig}, {@link Credit}, {@link Legend}, {@link Tooltip}
 * , {@link ChartLabel}, {@link SeriesConfig}, {@link XAxis} and {@link YAxis}
 * 
 * All configuration properties which are of object type are initialized with an
 * object instance.
 * 
 * These configuration options are static and generally set once. After a chart
 * ({@link InvientCharts}) created, any changes made to the configuration options
 * will not reflect in the chart. You would have to create a new chart
 * {@link InvientCharts}
 * 
 * For some APIs, the description has been taken from
 * http://www.highcharts.com/ref/
 * 
 * @author Invient
 */
@SuppressWarnings("serial")
public final class InvientChartsConfig implements Serializable {

    private Title title = new Title();
    private SubTitle subtitle = new SubTitle();
    private GeneralChartConfig generalChartConfig = new GeneralChartConfig();
    private Credit credit = new Credit();
    private Legend legend = new Legend();
    private Tooltip tooltip = new Tooltip();
    private ChartLabel chartLabel = new ChartLabel();

    private LinkedHashMap<SeriesType, SeriesConfig> seriesTypeConfig = new LinkedHashMap<SeriesType, SeriesConfig>();
    private LinkedHashSet<XAxis> xAxes = new LinkedHashSet<XAxis>();
    private LinkedHashSet<YAxis> yAxes = new LinkedHashSet<YAxis>();

    private InvientCharts invientCharts;
    
    InvientCharts getInvientCharts() {
        return this.invientCharts;
    }
    
    void setInvientCharts(InvientCharts invientCharts) {
        this.invientCharts = invientCharts;
    }
    
    /**
     * 
     * @return The {@link ChartLabel} object representing labels at arbitrary
     *         position in the chart.
     */
    public ChartLabel getChartLabel() {
        return chartLabel;
    }

    /**
     * Sets the argument {@link ChartLabel} object only if it is non-null
     * 
     * @param chartLabel
     */
    public void setChartLabel(ChartLabel chartLabel) {
        if (chartLabel != null) {
            this.chartLabel = chartLabel;
        }
    }

    /**
     * The {@link ChartLabel} class represents a set of labels which an be
     * placed at arbitrary position in the chart.
     * 
     * @author Invient
     * 
     */
    public static class ChartLabel implements Serializable {

        private String style;
        private List<ChartLabelItem> labels = new ArrayList<ChartLabelItem>();

        /**
         * 
         * @return Returns css style.
         */
        public String getStyle() {
            return style;
        }

        /**
         * Sets css style for all labels in this class
         * 
         * @param style
         *            css style string
         */
        public void setStyle(String style) {
            this.style = style;
        }

        /**
         * @return Returns a list of {@link ChartLabelItem} objects
         */
        public List<ChartLabelItem> getLabels() {
            return labels;
        }

        /**
         * Sets a list of {@link ChartLabelItem} objects
         * 
         * @param labels
         */
        public void setLabels(List<ChartLabelItem> labels) {
            if (labels != null) {
                this.labels = labels;
            }
        }

        /**
         * Appends the specified element at the end of {@link ChartLabelItem}
         * list
         * 
         * @param label
         *            element to be appended
         */
        public void addLabel(ChartLabelItem label) {
            this.labels.add(label);
        }

        /**
         * Removes the specified element from the list of {@link ChartLabelItem}
         * 
         * @param label
         */
        public void removeLabel(ChartLabelItem label) {
            this.labels.remove(label);
        }

        /**
         * This class represents a label placed at arbitrary location in the
         * chart. The label can have html text and it can be styled using
         * css-style.
         * 
         * @author Invient
         * 
         */
        public static class ChartLabelItem implements Serializable {
            private String html;
            private String style;

            /**
             * Creates a new instance with specified html and style arguments.
             * 
             * @param html
             * @param style
             */
            public ChartLabelItem(String html, String style) {
                super();
                this.html = html;
                this.style = style;
            }

            /**
             * 
             * @return Returns html of this label
             */
            public String getHtml() {
                return html;
            }

            /**
             * Sets html for this label
             * 
             * @param html
             *            It can be plain or html string.
             */
            public void setHtml(String html) {
                this.html = html;
            }

            /**
             * 
             * @return Returns css-style of this label
             */
            public String getStyle() {
                return style;
            }

            /**
             * Sets css style for this label
             * 
             * @param style
             */
            public void setStyle(String style) {
                this.style = style;
            }
        }

    }

    /**
     * 
     * @return Returns a collection of x-axis.
     */
    public LinkedHashSet<XAxis> getXAxes() {
        return xAxes;
    }

    /**
     * Sets a collection of x-axis for the chart. The collection of x-axis is
     * set only if argument xAxes is non-null.
     * 
     * @param xAxes
     */
    public void setXAxes(LinkedHashSet<XAxis> xAxes) {
        if (xAxes != null) {
            this.xAxes = xAxes;
        }
    }

    /**
     * Adds specified x-axis to the collection of x-axis
     * 
     * @param xAxis
     * @return Returns true if the x-axis is added successfully otherwise false
     */
    public boolean addXAxes(XAxis xAxis) {
        return this.xAxes.add(xAxis);
    }

    /**
     * @return Returns a collection of y-axis.
     */
    public LinkedHashSet<YAxis> getYAxes() {
        return yAxes;
    }

    /**
     * Sets a collection of y-axis for the chart. The collection of y-axis is
     * set only if argument yAxes is non-null
     * 
     * @param yAxes
     */
    public void setYAxes(LinkedHashSet<YAxis> yAxes) {
        if (yAxes != null) {
            this.yAxes = yAxes;
        }
    }

    /**
     * Adds specified y-axis to the collection of y-axis
     * 
     * @param yAxis
     * @return Returns true if the y-axis is added successfully otherwise false
     */
    public boolean addYAxes(YAxis yAxis) {
        return this.yAxes.add(yAxis);
    }

    /**
     * @return Returns {@link Title} object
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Sets the argument title only if the argument title is non-null
     * 
     * @param title
     */
    public void setTitle(Title title) {
        if (title != null) {
            this.title = title;
        }
    }

    /**
     * 
     * @return Returns subtitle
     */
    public SubTitle getSubtitle() {
        return subtitle;
    }

    /**
     * Sets the argument subtitle only if the argument is non-null
     * 
     * @param subtitle
     */
    public void setSubtitle(SubTitle subtitle) {
        if (subtitle != null) {
            this.subtitle = subtitle;
        }
    }

    /**
     * 
     * @return Returns tooltip object associated with this class
     */
    public Tooltip getTooltip() {
        return tooltip;
    }

    /**
     * Sets {@link Tooltip} object only if the argument tooltip is non-null
     * 
     * @param tooltip
     */
    public void setTooltip(Tooltip tooltip) {
        if (tooltip != null) {
            this.tooltip = tooltip;
        }
    }

    /**
     * 
     * @return Returns legend object of the chart
     */
    public Legend getLegend() {
        return legend;
    }

    /**
     * Sets {@link Legend} object only if the argument legend is non-null
     * 
     * @param legend
     */
    public void setLegend(Legend legend) {
        if (legend != null) {
            this.legend = legend;
        }
    }

    /**
     * 
     * @return Returns credit object of the chart
     */
    public Credit getCredit() {
        return credit;
    }

    /**
     * Sets the {@link Credit} object only if the argument credit is non-null
     * 
     * @param credit
     */
    public void setCredit(Credit credit) {
        if (credit != null) {
            this.credit = credit;
        }
    }

    /**
     * 
     * @return Returns {@link GeneralChartConfig} object
     */
    public GeneralChartConfig getGeneralChartConfig() {
        return generalChartConfig;
    }

    /**
     * Sets {@link GeneralChartConfig} object only if the argument is non-null
     * 
     * @param generalChartConfig
     */
    public void setGeneralChartConfig(GeneralChartConfig generalChartConfig) {
        if (generalChartConfig != null) {
            this.generalChartConfig = generalChartConfig;
        }
    }

    LinkedHashMap<SeriesType, SeriesConfig> getSeriesConfig() {
        return seriesTypeConfig;
    }

    /**
     * Sets a set of {@link SeriesConfig} objects only if the argument is
     * non-null
     * 
     * @param seriesConfigs
     */
    public void setSeriesConfig(LinkedHashSet<SeriesConfig> seriesConfigs) {
        if (seriesTypeConfig != null) {
            this.seriesTypeConfig.clear();
            for (SeriesConfig config : seriesConfigs) {
                addSeriesConfig(config);
            }
        }
    }

    /**
     * Adds the specified argument only if it is non-null.
     * 
     * @param seriesConfig
     * @throws IllegalArgumentException
     *             if the argument is null
     */
    public void addSeriesConfig(SeriesConfig seriesConfig) {
        if (seriesConfig == null) {
            throw new IllegalArgumentException(
                    "Argument SeriesConfig cannot be null.");
        }
        this.seriesTypeConfig.put(getSeriesType(seriesConfig), seriesConfig);
    }

    /**
     * @param seriesConfig
     * @return
     */
    private static SeriesType getSeriesType(SeriesConfig seriesConfig) {
        SeriesType seriesType = SeriesType.COMMONSERIES;
        if (LineConfig.class.equals(seriesConfig.getClass())) {
            seriesType = SeriesType.LINE;
        } else if (SplineConfig.class.equals(seriesConfig.getClass())) {
            seriesType = SeriesType.SPLINE;
        } else if (ScatterConfig.class.equals(seriesConfig.getClass())) {
            seriesType = SeriesType.SCATTER;
        } else if (AreaConfig.class.equals(seriesConfig.getClass())) {
            seriesType = SeriesType.AREA;
        } else if (AreaSplineConfig.class.equals(seriesConfig.getClass())) {
            seriesType = SeriesType.AREASPLINE;
        } else if (BarConfig.class.equals(seriesConfig.getClass())) {
            seriesType = SeriesType.BAR;
        } else if (ColumnConfig.class.equals(seriesConfig.getClass())) {
            seriesType = SeriesType.COLUMN;
        } else if (PieConfig.class.equals(seriesConfig.getClass())) {
            seriesType = SeriesType.PIE;
        }
        return seriesType;
    }

    /**
     * This class contains configuration properties at a chart level.
     * 
     * @author Invient
     * 
     */
    public static class GeneralChartConfig implements Serializable {
        private Paint backgroundColor;
        private Paint borderColor;
        private Integer borderRadius;
        private Integer borderWidth;
        private Integer height;
        private Integer width;
        private Boolean ignoreHiddenSeries;
        private Boolean inverted;
        private Margin margin;
        private Spacing spacing;
        private Boolean showAxes;
        private SeriesType type = SeriesType.LINE;
        private ZoomType zoomType = ZoomType.NONE;
        private boolean clientZoom = true;
        private Boolean alignTicks;
        private Boolean animation;
        private String className;
        private Boolean reflow;
        private Boolean shadow;
        private Plot plot;
        private String style;

        /**
         * This class represents drawing area of the chart and contains methods
         * specific to it.
         * 
         * @author chirag
         * 
         */
        public static class Plot implements Serializable {
            private Paint backgroundColor;
            private String backgroundImage;
            private Paint borderColor;
            private Integer borderWidth;
            private Boolean shadow;

            public Paint getBackgroundColor() {
                return backgroundColor;
            }

            public void setBackgroundColor(Paint backgroundColor) {
                this.backgroundColor = backgroundColor;
            }

            public String getBackgroundImage() {
                return backgroundImage;
            }

            public void setBackgroundImage(String backgroundImage) {
                this.backgroundImage = backgroundImage;
            }

            public Paint getBorderColor() {
                return borderColor;
            }

            public void setBorderColor(Paint borderColor) {
                this.borderColor = borderColor;
            }

            public Integer getBorderWidth() {
                return borderWidth;
            }

            public void setBorderWidth(Integer borderWidth) {
                this.borderWidth = borderWidth;
            }

            public Boolean getShadow() {
                return shadow;
            }

            public void setShadow(Boolean shadow) {
                this.shadow = shadow;
            }

            @Override
            public String toString() {
                return "Plot [backgroundColor=" + backgroundColor
                        + ", backgroundImage=" + backgroundImage
                        + ", borderColor=" + borderColor + ", borderWidth="
                        + borderWidth + ", shadow=" + shadow + "]";
            }

        }

        /**
         * This class represents space around the chart. The boundary of the
         * chart includes axis, axis labels, legend, chart title and subtitle.
         * 
         * @author Invient
         * 
         */
        public static class Spacing implements Serializable {
            private Integer left;
            private Integer top;
            private Integer right;
            private Integer bottom;

            public Integer getLeft() {
                return left;
            }

            public void setLeft(Integer left) {
                this.left = left;
            }

            public Integer getTop() {
                return top;
            }

            public void setTop(Integer top) {
                this.top = top;
            }

            public Integer getRight() {
                return right;
            }

            public void setRight(Integer right) {
                this.right = right;
            }

            public Integer getBottom() {
                return bottom;
            }

            public void setBottom(Integer bottom) {
                this.bottom = bottom;
            }

            @Override
            public String toString() {
                return "Spacing [left=" + left + ", top=" + top + ", right="
                        + right + ", bottom=" + bottom + "]";
            }

        }

        /**
         * 
         * This class represents margin between the outer edge of the chart and
         * the plot area.
         * 
         * @author Invient
         * 
         */
        public static class Margin implements Serializable {
            private Integer left;
            private Integer top;
            private Integer right;
            private Integer bottom;

            public Margin() {                
            }
            
            public Margin(Integer top, Integer right, Integer bottom, Integer left) {
                this.top = top;
                this.right = right;
                this.bottom = bottom;
                this.left = left;
            }
            
            public Integer getLeft() {
                return left;
            }

            public void setLeft(Integer left) {
                this.left = left;
            }

            public Integer getTop() {
                return top;
            }

            public void setTop(Integer top) {
                this.top = top;
            }

            public Integer getRight() {
                return right;
            }

            public void setRight(Integer right) {
                this.right = right;
            }

            public Integer getBottom() {
                return bottom;
            }

            public void setBottom(Integer bottom) {
                this.bottom = bottom;
            }

            @Override
            public String toString() {
                return "Margin [left=" + left + ", top=" + top + ", right="
                        + right + ", bottom=" + bottom + "]";
            }

        }

        /**
         * @return
         */
        public Boolean getAlignTicks() {
            return alignTicks;
        }

        /**
         * When using multiple axis, the ticks of two or more opposite axes will
         * automatically be aligned by adding ticks to the axis or axes with the
         * least ticks. This can be prevented by setting alignTicks to false.
         * 
         * @param alignTicks
         */
        public void setAlignTicks(Boolean alignTicks) {
            this.alignTicks = alignTicks;
        }

        /**
         * @return
         */
        public Boolean getAnimation() {
            return animation;
        }

        /**
         * Set the overall animation for all chart updating.
         * 
         * @param animation
         */
        public void setAnimation(Boolean animation) {
            this.animation = animation;
        }

        /**
         * @return
         */
        public String getClassName() {
            return className;
        }

        /**
         * A CSS class name to apply to the charts container
         * 
         * @param className
         */
        public void setClassName(String className) {
            this.className = className;
        }

        /**
         * 
         * @return Returns plot object representing chart's drawing area
         */
        public Plot getPlot() {
            return plot;
        }

        /**
         * Sets plot object
         * 
         * @param plot
         */
        public void setPlot(Plot plot) {
            this.plot = plot;
        }

        /**
         * 
         * @return
         */
        public Boolean getReflow() {
            return reflow;
        }

        /**
         * A value of true indicates that the chart will fit the width of the
         * charts container otherwise not.
         * 
         * @param reflow
         */
        public void setReflow(Boolean reflow) {
            this.reflow = reflow;
        }

        /**
         * @return
         */
        public Boolean getShadow() {
            return shadow;
        }

        /**
         * A value of true indicates that the drop shadow will apply to the
         * outer chart area otherwise not.
         * 
         * @param shadow
         */
        public void setShadow(Boolean shadow) {
            this.shadow = shadow;
        }

        /**
         * @return
         */
        public String getStyle() {
            return style;
        }

        /**
         * A CSS string to apply to the charts container
         * 
         * @param style
         */
        public void setStyle(String style) {
            this.style = style;
        }

        /**
         * @return
         */
        public Paint getBackgroundColor() {
            return backgroundColor;
        }

        /**
         * Sets the background color for the outer chart area
         * 
         * @param backgroundColor
         */
        public void setBackgroundColor(Paint backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        /**
         * @return
         */
        public Paint getBorderColor() {
            return borderColor;
        }

        /**
         * Sets the border color for the outer chart border
         * 
         * @param borderColor
         */
        public void setBorderColor(Paint borderColor) {
            this.borderColor = borderColor;
        }

        /**
         * @return
         */
        public Integer getBorderRadius() {
            return borderRadius;
        }

        /**
         * Sets radius for the outer chart border
         * 
         * @param borderRadius
         */
        public void setBorderRadius(Integer borderRadius) {
            this.borderRadius = borderRadius;
        }

        /**
         * @return
         */
        public Integer getBorderWidth() {
            return borderWidth;
        }

        /**
         * Sets pixel width of the outer chart border
         * 
         * @param borderWidth
         */
        public void setBorderWidth(Integer borderWidth) {
            this.borderWidth = borderWidth;
        }

        /**
         * @return
         */
        public Integer getHeight() {
            return height;
        }

        /**
         * Sets height for the chart
         * 
         * @param height
         */
        public void setHeight(Integer height) {
            this.height = height;
        }

        /**
         * @return
         */
        public Integer getWidth() {
            return width;
        }

        /**
         * Sets width for the chart
         * 
         * @param width
         */
        public void setWidth(Integer width) {
            this.width = width;
        }

        /**
         * 
         * @return
         */
        public Boolean getIgnoreHiddenSeries() {
            return ignoreHiddenSeries;
        }

        /**
         * If the argument is true, the axes will scale to the remaining visible
         * series once one series is hidden. If the argument is false, hiding
         * and showing a series will not affect the axes or the other series.
         * 
         * @param ignoreHiddenSeries
         */
        public void setIgnoreHiddenSeries(Boolean ignoreHiddenSeries) {
            this.ignoreHiddenSeries = ignoreHiddenSeries;
        }

        /**
         * @return
         */
        public Boolean getInverted() {
            return inverted;
        }

        /**
         * If the argument is true then the x-axis is reversed. If a bar plot is
         * present, it will be inverted automatically.
         * 
         * @param inverted
         */
        public void setInverted(Boolean inverted) {
            this.inverted = inverted;
        }

        /**
         * 
         * @return
         */
        public Margin getMargin() {
            return margin;
        }

        /**
         * @param margin
         */
        public void setMargin(Margin margin) {
            this.margin = margin;
        }

        /**
         * @return
         */
        public Boolean getShowAxes() {
            return showAxes;
        }

        /**
         * If the argument is true then the axes will be shown initially. This
         * is useful when the chart is empty and axes must be shown.
         * 
         * @param showAxes
         */
        public void setShowAxes(Boolean showAxes) {
            this.showAxes = showAxes;
        }

        /**
         * @return
         */
        public Spacing getSpacing() {
            return spacing;
        }

        /**
         * @param spacing
         */
        public void setSpacing(Spacing spacing) {
            this.spacing = spacing;
        }

        /**
         * @return
         */
        public SeriesType getType() {
            return type;
        }

        /**
         * Sets series type to one of line, spline, scatter, area, areaspline,
         * pie, bar and column.
         * 
         * @param type
         */
        public void setType(SeriesType type) {
            this.type = type;
        }

        /**
         * @return
         */
        public ZoomType getZoomType() {
            return zoomType;
        }

        /**
         * Sets zoom type. It decides how a chart can be zoomed by dragging the
         * mouse.
         * 
         * @param zoomType
         */
        public void setZoomType(ZoomType zoomType) {
            this.zoomType = zoomType;
        }

        /**
         * @return
         */
        public boolean isClientZoom() {
            return clientZoom;
        }

        /**
         * If the argument is true then the scaling will happen on client. If
         * the argument is false then the chart will not scale. In any case, the
         * server will receive event notification if registered.
         * 
         * @param clientZoom
         */
        public void setClientZoom(boolean clientZoom) {
            this.clientZoom = clientZoom;
        }

        @Override
        public String toString() {
            return "Chart [backgroundColor=" + backgroundColor
                    + ", borderColor=" + borderColor + ", borderRadius="
                    + borderRadius + ", borderWidth=" + borderWidth
                    + ", height=" + height + ", width=" + width
                    + ", ignoreHiddenSeries=" + ignoreHiddenSeries
                    + ", inverted=" + inverted + ", margin=" + margin
                    + ", spacing=" + spacing + ", showAxes=" + showAxes
                    + ", type=" + type + ", zoomType=" + zoomType
                    + ", alignTicks=" + alignTicks + ", animation=" + animation
                    + ", className=" + className + ", reflow=" + reflow
                    + ", shadow=" + shadow + ", plot=" + plot + ", style="
                    + style + "]";
        }

        /**
         * The value {@link ZoomType.X} represents horizontal zoom. The value
         * {@link ZoomType.Y} represents vertical zoom. The value
         * {@link ZoomType.XY} represents horizontal as well as vertical zoom.
         * 
         * @author Invient
         * 
         */
        public static enum ZoomType {
            X("x"), Y("y"), XY("xy"), NONE("");
            private String type;

            private ZoomType(String type) {
                this.type = type;
            }

            public String getName() {
                return this.type;
            }
        }
    }

    /**
     * This class contains general configuration options for all series types
     * such as line, area and pie.
     * 
     * @author Invient
     * 
     */
    public static class SeriesConfig implements Serializable {
        private Boolean allowPointSelect;
        private Boolean animation;
        private Boolean enableMouseTracking;
        private Boolean showInLegend;
        private String cursor;
        // No impact in case of Pie chart
        private Stacking stacking;
        private Boolean showCheckbox;
        // private Boolean selected;
        private Boolean visible; // NA for pie
        private Boolean shadow; // NA for scatter
        private SeriesState hoverState;
        private DataLabel dataLabel;
        private Paint color;

        public SeriesConfig() {
        }

        /**
         * @return
         */
        public Boolean getAllowPointSelect() {
            return allowPointSelect;
        }

        /**
         * If the argument is true then the points of a can be selected
         * otherwise not. Defaults to false, The point on a chart will toggle.
         * Also, whenever a point is selected or deselected, the registered
         * event listeners will be triggered.
         * 
         * @param allowPointSelect
         */
        public void setAllowPointSelect(Boolean allowPointSelect) {
            this.allowPointSelect = allowPointSelect;
        }

        public Boolean getAnimation() {
            return animation;
        }

        /**
         * If the argument is true then animation will be enabled when a series
         * will be displayed otherwise not. Defaults to false.
         * 
         * @param animation
         */
        public void setAnimation(Boolean animation) {
            this.animation = animation;
        }

        /**
         * @return
         */
        public Boolean getEnableMouseTracking() {
            return enableMouseTracking;
        }

        /**
         * If the argument is true then the mouse tracking will be enabled for a
         * series otherwise not. Defaults to true.
         * 
         * @param enableMouseTracking
         */
        public void setEnableMouseTracking(Boolean enableMouseTracking) {
            this.enableMouseTracking = enableMouseTracking;
        }

        /**
         * @return
         */
        public Boolean getShowInLegend() {
            return showInLegend;
        }

        /**
         * If the argument is true then a series will be displayed in the legend
         * otherwise not. Defaults to true.
         * 
         * @param showInLegend
         */
        public void setShowInLegend(Boolean showInLegend) {
            this.showInLegend = showInLegend;
        }

        /**
         * @return
         */
        public String getCursor() {
            return cursor;
        }

        /**
         * Sets the cursor style. E.g. cursor can be set to css cursor style
         * 'pointer', 'hand' or any other. Defaults to null.
         * 
         * @param cursor
         */
        public void setCursor(String cursor) {
            this.cursor = cursor;
        }

        /**
         * @return
         */
        public Stacking getStacking() {
            return stacking;
        }

        /**
         * Specifies whether the values of each series should be stacked on top
         * of each other or not. Defaults to null. If the argument is null then
         * the values of each series are not stacked.
         * 
         * @param stacking
         */
        public void setStacking(Stacking stacking) {
            this.stacking = stacking;
        }

        /**
         * @return
         */
        public Boolean getShowCheckbox() {
            return showCheckbox;
        }

        /**
         * If the argument is true then a checkbox is displayed next to the
         * legend item in the legend area. Defaults to false
         * 
         * @param showCheckbox
         */
        public void setShowCheckbox(Boolean showCheckbox) {
            this.showCheckbox = showCheckbox;
        }

        // public Boolean getSelected() {
        // return selected;
        // }
        //
        // public void setSelected(Boolean selected) {
        // this.selected = selected;
        // }

        /**
         * @return
         */
        public Boolean getVisible() {
            return visible;
        }

        // Only in case of Pie chart exception is thrown
        /**
         * If the argument is true then the series is visible otherwise not when
         * a chart is rendered initially. Defaults to true However, this is not
         * applicable for series related to Pie chart.
         * 
         * @param visible
         * @throws UnsupportedOperationException
         *             If this method is invoked on {@link PieConfig}
         */
        public void setVisible(Boolean visible)
                throws UnsupportedOperationException {
            this.visible = visible;
        }

        /**
         * @return
         */
        public Boolean getShadow() {
            return shadow;
        }

        // Only in case of Pie and Scatter chart exception is thrown
        /**
         * If the argument is true then a shadow will be shown to the graph line
         * otherwise not. Defaults to true.
         * 
         * @param shadow
         * @throws UnsupportedOperationException
         *             If this method is invoked on {@link PieConfig}
         */
        public void setShadow(Boolean shadow)
                throws UnsupportedOperationException {
            this.shadow = shadow;
        }

        /**
         * 
         * @return
         */
        public SeriesState getHoverState() {
            return hoverState;
        }

        /**
         * Sets attributes which should be applied to a series when series is
         * hovered.
         * 
         * @param state
         */
        public void setHoverState(SeriesState state) {
            this.hoverState = state;
        }

        /**
         * @return
         */
        public DataLabel getDataLabel() {
            return dataLabel;
        }

        /**
         * Sets how point value should be formatted and displayed for each
         * point.
         * 
         * @param dataLabel
         */
        public void setDataLabel(DataLabel dataLabel) {
            this.dataLabel = dataLabel;
        }

        /**
         * @return
         */
        public Paint getColor() {
            return color;
        }

        /**
         * Sets color for the series.
         * 
         * @param color
         */
        public void setColor(Paint color) {
            this.color = color;
        }

    }

    /**
     * This class contains various attributes to format data labels. The data
     * labels are displayed along with points and axis.
     * 
     * @author Invient
     * 
     */
    public static class DataLabel implements Serializable {
        private HorzAlign align; // NA for pie
        private Boolean enabled = Boolean.TRUE;
        private String formatterJsFunc;
        private Integer rotation;
        private String style;
        private Integer x;
        private Integer y;
        private Color color;

        public DataLabel() {

        }

        /**
         * If the argument is true then the datalabels will be displayed
         * otherwise not.
         * 
         * @param enabled
         */
        public DataLabel(boolean enabled) {
            this.enabled = enabled;
        }

        /**
         * @return
         */
        public HorzAlign getAlign() {
            return align;
        }

        /**
         * @param align
         */
        public void setAlign(HorzAlign align) {
            this.align = align;
        }

        /**
         * @return
         */
        public Boolean getEnabled() {
            return enabled;
        }

        /**
         * If the argument is true then the datalabels will be displayed
         * otherwise not.
         * 
         * @param enabled
         */
        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        /**
         * @return
         */
        public String getFormatterJsFunc() {
            return formatterJsFunc;
        }

        /**
         * Sets the argument string JavaScript function. This function will be
         * called to format the data label. Refer to highchart documentation for
         * more details on this
         * http://www.highcharts.com/ref/#plotOptions-series-dataLabels
         * 
         * @param formatterJsFunc
         */
        public void setFormatterJsFunc(String formatterJsFunc) {
            this.formatterJsFunc = formatterJsFunc;
        }

        /**
         * @return
         */
        public Integer getRotation() {
            return rotation;
        }

        /**
         * Sets text rotation in degrees
         * 
         * @param rotation
         */
        public void setRotation(Integer rotation) {
            this.rotation = rotation;
        }

        /**
         * @return
         */
        public String getStyle() {
            return style;
        }

        /**
         * Sets css style for the data label
         * 
         * @param style
         */
        public void setStyle(String style) {
            this.style = style;
        }

        /**
         * @return
         */
        public Integer getX() {
            return x;
        }

        /**
         * Sets the x position offset of the label relative to the point.
         * Defaults to 0.
         * 
         * @param x
         */
        public void setX(Integer x) {
            this.x = x;
        }

        /**
         * @return
         */
        public Integer getY() {
            return y;
        }

        /**
         * Sets the y position offset of the label relative to the point.
         * Defaults to -6.
         * 
         * @param y
         */
        public void setY(Integer y) {
            this.y = y;
        }

        /**
         * @return
         */
        public Color getColor() {
            return color;
        }

        /**
         * Sets color for the data labels. e.g. if the color is blue then in
         * case of line series, for each point, the data label will be displayed
         * in blue color.
         * 
         * @param color
         */
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "DataLabel [align=" + align + ", enabled=" + enabled
                    + ", formatter=" + formatterJsFunc + ", rotation="
                    + rotation + ", style=" + style + ", x=" + x + ", y=" + y
                    + "]";
        }

    }

    /**
     * This class contains configuration attributes of data labels specific to
     * Pie series.
     * 
     * @author Invient
     * 
     */
    public final static class PieDataLabel extends DataLabel {
        private Integer connectorWidth;
        private Paint connectorColor;
        private Integer connectorPadding;
        private Integer distance;

        /**
         * 
         */
        public PieDataLabel() {

        }

        /**
         * If the argument is true then the datalabels will be displayed
         * otherwise not.
         * 
         * @param enabled
         */
        public PieDataLabel(boolean enabled) {
            super(enabled);
        }

        /**
         * @return
         */
        public Integer getConnectorWidth() {
            return connectorWidth;
        }

        /**
         * Sets width (in pixel) of the line connecting the data label to the
         * pie slice. Defaults to 1.
         * 
         * @param connectorWidth
         */
        public void setConnectorWidth(Integer connectorWidth) {
            this.connectorWidth = connectorWidth;
        }

        /**
         * @return
         */
        public Paint getConnectorColor() {
            return connectorColor;
        }

        /**
         * Sets the color of the line connecting the data label to the pie
         * slice.
         * 
         * @param connectorColor
         */
        public void setConnectorColor(Paint connectorColor) {
            this.connectorColor = connectorColor;
        }

        /**
         * 
         * @return
         */
        public Integer getConnectorPadding() {
            return connectorPadding;
        }

        /**
         * Sets the distance (in pixel) from the data label to the connector.
         * Defaults to 5.
         * 
         * @param connectorPadding
         */
        public void setConnectorPadding(Integer connectorPadding) {
            this.connectorPadding = connectorPadding;
        }

        /**
         * @return
         */
        public Integer getDistance() {
            return distance;
        }

        /**
         * Sets the distance (in pixel) of the data label from the pie's edge.
         * 
         * @param distance
         */
        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "PieDataLabel [connectorWidth=" + connectorWidth
                    + ", connectorColor=" + connectorColor
                    + ", connectorPadding=" + connectorPadding + ", distance="
                    + distance + ", getAlign()=" + getAlign()
                    + ", getEnabled()=" + getEnabled() + ", getFormatter()="
                    + getFormatterJsFunc() + ", getRotation()=" + getRotation()
                    + ", getStyle()=" + getStyle() + ", getX()=" + getX()
                    + ", getY()=" + getY() + ", toString()=" + super.toString()
                    + ", getClass()=" + getClass() + ", hashCode()="
                    + hashCode() + "]";
        }

    }

    /**
     * This class contains configuration properties for axis labels. The axis
     * labels are the one which are displayed for each tick.
     * 
     * @author Invient
     * 
     */
    public static abstract class AxisDataLabel extends DataLabel {
        private Integer step;

        public AxisDataLabel() {
            super();
        }

        /**
         * If the argument is true then the data labels will be displayed
         * otherwise not.
         * 
         * @param enabled
         */
        public AxisDataLabel(boolean enabled) {
            super(enabled);
        }

        /**
         * @return
         */
        public Integer getStep() {
            return step;
        }

        /**
         * Sets at what interval the labels on the axis should be displayed.
         * Setting the step to 2 shows every other label. Defaults to null
         * 
         * @param step
         */
        public void setStep(Integer step) {
            this.step = step;
        }
    }

    /**
     * This class contains configuration properties specifically for x-axis
     * labels.
     * 
     * @author Invient
     * 
     */
    public static final class XAxisDataLabel extends AxisDataLabel {
        private Integer staggerLines;

        /**
         * If the argument is true then the data labels will be displayed
         * otherwise not.
         * 
         * @param enabled
         */
        public XAxisDataLabel(boolean enabled) {
            super(enabled);
        }

        public XAxisDataLabel() {
            super();
        }

        /**
         * @return
         */
        public Integer getStaggerLines() {
            return staggerLines;
        }

        /**
         * Sets number of lines to spread the labels over to make room or
         * tighter labels.
         * 
         * @param staggerLines
         */
        public void setStaggerLines(Integer staggerLines) {
            this.staggerLines = staggerLines;
        }
    }

    /**
     * This class contains configuration properties specifically for x-axis
     * labels.
     * 
     * @author Invient
     * 
     */
    public static final class YAxisDataLabel extends AxisDataLabel {
        public YAxisDataLabel() {
            super();
        }

        /**
         * If the argument is true then the data labels will be displayed
         * otherwise not.
         * 
         * @param enabled
         */
        public YAxisDataLabel(boolean enabled) {
            super(enabled);
        }
    }

    /**
     * This class contains configuration options for line series such as line
     * and area but not column series.
     * 
     * @author Invient
     * 
     */
    public abstract static class BaseLineConfig extends SeriesConfig {
        private Double pointStart;
        private Double pointInterval;
        private Boolean stickyTracking;
        private Marker marker;
        private DashStyle dashStyle;
        private Integer lineWidth;

        /**
         * @return
         */
        public Double getPointStart() {
            return pointStart;
        }

        /**
         * If no x values are given for the points in a series, the argument
         * pointStart defines on what value to start. Defaults to 0. e.g. if a
         * series contains values higher than 2 m $ then sets pointStart to
         * 2,000,000
         * 
         * @param pointStart
         */
        public void setPointStart(Double pointStart) {
            this.pointStart = pointStart;
        }

        /**
         * @return
         */
        public Double getPointInterval() {
            return pointInterval;
        }

        /**
         * If no x values are given for the points in a series, the argument
         * pointInterval defines the interval of the x values. For example, if a
         * series contains one value every day then set pointInterval to 24 *
         * 3600 * 1000
         * 
         * @param pointInterval
         */
        public void setPointInterval(Double pointInterval) {
            this.pointInterval = pointInterval;
        }

        /**
         * @return
         */
        public Boolean getStickyTracking() {
            return stickyTracking;
        }

        /**
         * If the argument is true then the mouseout event on a series is not
         * triggered until mouse moves over another series or comes out of the
         * plot area. If the argument is true then the mouseout event occurs as
         * soon as mouse leaves area near to the point or marker
         * 
         * @param stickyTracking
         */
        public void setStickyTracking(Boolean stickyTracking) {
            this.stickyTracking = stickyTracking;
        }

        /**
         * @return
         */
        public Marker getMarker() {
            return marker;
        }

        /**
         * Sets marker for points of a series
         * 
         * @param marker
         */
        public void setMarker(Marker marker) {
            this.marker = marker;
        }

        /**
         * @return
         */
        public DashStyle getDashStyle() {
            return dashStyle;
        }

        /**
         * Sets dash style to use when drawing a series.
         * 
         * @param dashStyle
         */
        public void setDashStyle(DashStyle dashStyle) {
            this.dashStyle = dashStyle;
        }

        /**
         * @return
         */
        public Integer getLineWidth() {
            return lineWidth;
        }

        /**
         * Sets width of a line
         * 
         * @param lineWidth
         */
        public void setLineWidth(Integer lineWidth) {
            this.lineWidth = lineWidth;
        }
    }

    public static enum DashStyle {
        SOLID("Solid"), SHORT_DASH("ShortDash"), SHORT_DOT("ShortDot"), SHORT_DASH_DOT(
                "ShortDashDot"), SHORT_DASH_DOT_DOT("ShortDashDotDot"), DOT(
                "Dot"), DASH("Dash"), LONG_DASH("LongDash"), DASH_DOT("DashDot"), LONG_DASH_DOT(
                "LongDashDot"), LONG_DASH_DOT_DOT("LongDashDotDot");
        private String name;

        private DashStyle(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    /**
     * 
     * This class contains configuration options for area series, area and
     * areaspline.
     * 
     * @author Invient
     * 
     */
    public static class AreaConfig extends BaseLineConfig {
        private Paint fillColor;
        private Paint lineColor;
        private Double fillOpacity;
        private Integer threshold;

        /**
         * @return Returns fill color of the area. 
         */
        public Paint getFillColor() {
            return fillColor;
        }

        /**
         * Sets fill gradient for the area
         * 
         * @param fillColor
         */
        public void setFillColor(Paint fillColor) {
            this.fillColor = fillColor;
        }

        /**
         * @return Returns color of a line drawing above the area
         */
        public Paint getLineColor() {
            return lineColor;
        }

        /**
         * Sets line color for the line of an area.
         * 
         * @param lineColor
         */
        public void setLineColor(Paint lineColor) {
            this.lineColor = lineColor;
        }

        /**
         * @return Returns opacity (transparency) which will be used when the area is filled with the fill color  
         */
        public Double getFillOpacity() {
            return fillOpacity;
        }

        /**
         * Sets opacity for the area
         * 
         * @param fillOpacity
         */
        public void setFillOpacity(Double fillOpacity) {
            this.fillOpacity = fillOpacity;
        }

        /**
         * @return Returns threadshold of the area
         */
        public Integer getThreshold() {
            return threshold;
        }

        /**
         * Sets threshold value which servers as the base for the area, for
         * distinguishing between values above and below a threshold. Defaults
         * to 0.
         * 
         * @param threshold
         */
        public void setThreshold(Integer threshold) {
            this.threshold = threshold;
        }

    }

    /**
     * This class contains configuration options for areaspline series
     * 
     * @author Invient
     * 
     */
    public static class AreaSplineConfig extends AreaConfig {

    }

    /**
     * This class contains configuration options for line series
     * 
     * @author Invient
     * 
     */
    public static class LineConfig extends BaseLineConfig {
        private Boolean step;

        /**
         * @return Returns true if the line should be drawn using steps otherwise false.
         */
        public Boolean getStep() {
            return step;
        }

        /**
         * If the argument is true then line will be drawn using steps otherwise
         * not. Defaults to false.
         * 
         * @param step
         */
        public void setStep(Boolean step) {
            this.step = step;
        }
    }

    /**
     * This class contains configuration options for scatter series
     * 
     * @author Invient
     * 
     */
    public static class ScatterConfig extends BaseLineConfig {

        /**
         * 
         * @param shadow
         * @exception UnsupportedOperationException
         *                Scatter series does not support shadow so this method
         *                throws an exception if invoked.
         */
        @Override
        public void setShadow(Boolean shadow)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException(
                    "Scatter chart does not support shadow.");
        }

        /**
         * @return Returns null as scatter series does not have shadow.
         */
        @Override
        public Boolean getShadow() {
            return null;
        }
    }

    /**
     * This class contains configuration options for spline series
     * 
     * @author Invient
     * 
     */
    public static class SplineConfig extends BaseLineConfig {

    }

    /**
     * This class contains configuration options for pie series.
     * 
     * @author Invient
     * 
     */
    public static class PieConfig extends SeriesConfig {
        private Integer centerX;
        private Integer centerY;
        private Paint borderColor;
        private Double borderWidth;
        private Integer innerSize;
        private Integer size;
        private Integer slicedOffset;
		private Boolean ignoreHiddenPoint;

		public Boolean getIgnoreHiddenPoint() {
			return ignoreHiddenPoint;
		}

		public void setIgnoreHiddenPoint(Boolean ignoreHiddenPoint) {
			this.ignoreHiddenPoint = ignoreHiddenPoint;
		}

        /**
         * @return Returns x position (in pixel) of the center of the pie chart relative to
         * the plot area.
         */
        public Integer getCenterX() {
            return centerX;
        }

        /**
         * Sets x position (in pixel) of the center of the pie chart relative to
         * the plot area.
         * 
         * @param centerX
         */
        public void setCenterX(Integer centerX) {
            this.centerX = centerX;
        }

        /**
         * @return Returns y position (in pixel) of the center of the pie chart relative to
         * the plot area.
         */
        public Integer getCenterY() {
            return centerY;
        }

        /**
         * Sets y position (in pixel) of the center of the pie chart relative to
         * the plot area.
         * 
         * @param centerY
         */
        public void setCenterY(Integer centerY) {
            this.centerY = centerY;
        }

        /**
         * @return Returns color of border surrounding each slice.
         */
        public Paint getBorderColor() {
            return borderColor;
        }

        /**
         * Sets color of border surrounding each slice.
         * 
         * @param borderColor
         */
        public void setBorderColor(Paint borderColor) {
            this.borderColor = borderColor;
        }

        /**
         * @return Returns width of the border surrounding each slice.
         */
        public Double getBorderWidth() {
            return borderWidth;
        }

        /**
         * Sets width of border surrounding each slice.
         * 
         * @param borderWidth
         */
        public void setBorderWidth(Double borderWidth) {
            this.borderWidth = borderWidth;
        }

        /**
         * @return Returns size of the inner diameter of the pie.
         */
        public Integer getInnerSize() {
            return innerSize;
        }

        /**
         * Sets the size of the inner diameter for the pie. Any value greater
         * than 0 renders a donut chart.
         * 
         * @param innerSize
         */
        public void setInnerSize(Integer innerSize) {
            this.innerSize = innerSize;
        }

        /**
         * @return Returns size of diameter of the pie relative to the plot area. 
         */
        public Integer getSize() {
            return size;
        }

        /**
         * Sets size of diameter of the pie relative to the plot area.
         * 
         * @param size
         */
        public void setSize(Integer size) {
            this.size = size;
        }

        /**
         * @return Returns offset in pixel by which a slice should be moved out from the
         * center.
         */
        public Integer getSlicedOffset() {
            return slicedOffset;
        }

        /**
         * Sets offset in pixel by which a slice should be moved out from the
         * center.
         * 
         * @param slicedOffset
         */
        public void setSlicedOffset(Integer slicedOffset) {
            this.slicedOffset = slicedOffset;
        }

        /**
         * @exception UnsupportedOperationException
         *                Pie chart does not support visible property so this
         *                method throws an exception if invoked.
         */
        @Override
        public void setVisible(Boolean visible)
                throws UnsupportedOperationException {
            throw new UnsupportedOperationException(
                    "Pie chart does not support visible property.");
        }

        /**
         * @return Returns null as pie does not support toggle (show/hide pie) feature.
         */
        @Override
        public Boolean getVisible() {
            return null;
        }

        /**
         * Sets an object of {@link PieDataLabel} which contains configuration
         * for formatting data labels.
         * 
         * @param dataLabel
         */
        public void setDataLabel(PieDataLabel dataLabel) {
            super.setDataLabel(dataLabel);
        }

        /**
         * 
         */
        public PieDataLabel getDataLabel() {
            return (PieDataLabel) super.getDataLabel();
        }

        /**
         * Sets state which should be applied to a slice when a mouse is over
         * the slice
         * 
         * @param state
         */
        public void setHoverState(NonLinearSeriesState state) {
            super.setHoverState(state);
        }

        public NonLinearSeriesState getHoverState() {
            if (super.getHoverState() instanceof NonLinearSeriesState) {
                return (NonLinearSeriesState) super.getHoverState();
            }
            return null;
        }

    }

    /**
     * This class contains configuration options for bar and column series.
     * 
     * @author Invient
     * 
     */
    public abstract static class BaseBarConfig extends SeriesConfig {
        private Paint borderColor;
        private Integer borderRadius;
        private Integer borderWidth;
        private Boolean colorByPoint;
        private Double groupPadding;
        private Double minPointLength;
        private Double pointPadding;
        private Integer pointWidth;

        /**
         * @return
         */
        public Paint getBorderColor() {
            return borderColor;
        }

        /**
         * Sets the color of the border surronding each column or bar.
         * 
         * @param borderColor
         */
        public void setBorderColor(Paint borderColor) {
            this.borderColor = borderColor;
        }

        /**
         * @return
         */
        public Integer getBorderRadius() {
            return borderRadius;
        }

        /**
         * Sets corner radius of the border surronding each column or bar.
         * 
         * @param borderRadius
         */
        public void setBorderRadius(Integer borderRadius) {
            this.borderRadius = borderRadius;
        }

        /**
         * @return
         */
        public Integer getBorderWidth() {
            return borderWidth;
        }

        /**
         * Sets width of the border surronding each column or bar.
         * 
         * @param borderWidth
         */
        public void setBorderWidth(Integer borderWidth) {
            this.borderWidth = borderWidth;
        }

        /**
         * @return
         */
        public Boolean getColorByPoint() {
            return colorByPoint;
        }

        /**
         * If the argument is true then each point (bar or column in a series
         * will have a different color otherwise all points (bars/columns) of a
         * series will have same color.
         * 
         * @param colorByPoint
         */
        public void setColorByPoint(Boolean colorByPoint) {
            this.colorByPoint = colorByPoint;
        }

        /**
         * 
         * @return
         */
        public Double getGroupPadding() {
            return groupPadding;
        }

        /**
         * Sets padding between each value groups, in x axis units. Defaults to
         * 0.2.
         * 
         * @param groupPadding
         */
        public void setGroupPadding(Double groupPadding) {
            this.groupPadding = groupPadding;
        }

        /**
         * @return
         */
        public Double getMinPointLength() {
            return minPointLength;
        }

        /**
         * Sets the minimal height for a column or width for a bar. By default,
         * 0 values are not shown. To visualize a 0 (or close to zero) point,
         * set the minimal point length to a pixel value like 3. In stacked
         * column charts, minPointLength might not be respected for tightly
         * packed values. Defaults to 0. (For detail, refer to
         * http://www.highcharts.com/ref/#plotOptions-bar);
         * 
         * @param minPointLength
         */
        public void setMinPointLength(Double minPointLength) {
            this.minPointLength = minPointLength;
        }

        /**
         * 
         * @return
         */
        public Double getPointPadding() {
            return pointPadding;
        }

        /**
         * Sets padding between each column or bar, in x axis units.
         * 
         * @param pointPadding
         */
        public void setPointPadding(Double pointPadding) {
            this.pointPadding = pointPadding;
        }

        /**
         * @return
         */
        public Integer getPointWidth() {
            return pointWidth;
        }

        /**
         * Sets width of each bar or column in pixel.
         * 
         * @param pointWidth
         */
        public void setPointWidth(Integer pointWidth) {
            this.pointWidth = pointWidth;
        }

        /**
         * Sets state which should be applied to a bar or column when a mouse is
         * over the bar or column
         * 
         * @param state
         */
        public void setHoverState(NonLinearSeriesState state) {
            super.setHoverState(state);
        }

        /**
         * 
         */
        public NonLinearSeriesState getHoverState() {
            if (super.getHoverState() instanceof NonLinearSeriesState) {
                return (NonLinearSeriesState) super.getHoverState();
            }
            return null;
        }
    }

    /**
     * This class contains configuration options for column series.
     * 
     * @author Invient
     * 
     */
    public static class ColumnConfig extends BaseBarConfig {

    }

    /**
     * This class contains configuration options for bar series.
     * 
     * @author Invient
     * 
     */
    public static class BarConfig extends BaseBarConfig {

    }

    /**
     * Defines ways in which series of a chart can be stacked.
     * 
     * Stacking.Normal - represents that the values of each series are stacked.
     * 
     * Stacking.Percent - represents that the the values of each series are
     * stacked based on percentage of sum of total value, where total value is
     * sum of values of all points on a particular tick of an axis.
     * 
     * @author Invient
     * 
     */
    public static enum Stacking {
        NORMAL("normal"), PERCENT("percent");
        private String stacking;

        private Stacking(String stacking) {
            this.stacking = stacking;
        }

        public String getName() {
            return this.stacking;
        }
    }

    /**
     * Defines configuration per point in a series. It is possible to assign
     * each point a different color and marker.
     * 
     * @author Invient
     * 
     */
    public static final class PointConfig implements Serializable {
        private Boolean sliced;
        private Boolean selected;
        private Paint color;
        private Marker marker;

        /**
         * Creates an instance of this class with specified marker
         * 
         * @param marker
         */
        public PointConfig(Marker marker) {
            this(null, null, null, marker);
        }

        /**
         * Creates an instance of this class with specified color
         * 
         * @param color
         */
        public PointConfig(Paint color) {
            this(null, null, color, null);
        }

        /**
         * Creates an instance of this class with specified argument. The sliced
         * attribute has meaning only for Pie chart/series.
         * 
         * @param sliced
         */
        public PointConfig(Boolean sliced) {
            this(sliced, sliced, null, null);
        }

        /**
         * 
         * @param sliced
         *            - If true then the slice of a pie will be at an offset
         *            from the center of the pie. Applicable only for Pie
         *            chart/series.
         * @param selected
         *            - If true then the point, to which this object is
         *            associated, will be shown as selected otherwise not.
         * @param color
         *            - Specifies individual color for a point, to which this
         *            object is associated.
         * @param marker
         *            - Specifies marker for a point, to which this object is
         *            associated.
         * @see Marker
         * @see Color
         */
        public PointConfig(Boolean sliced, Boolean selected, Paint color,
                Marker marker) {
            super();
            this.sliced = sliced;
            this.selected = selected;
            this.color = color;
            this.marker = marker;
        }

        /**
         * @return
         */
        public Boolean getSliced() {
            return sliced;
        }

        /**
         * @param sliced
         */
        public void setSliced(Boolean sliced) {
            this.sliced = sliced;
        }

        /**
         * @return
         */
        public Boolean getSelected() {
            return selected;
        }

        /**
         * @param selected
         */
        public void setSelected(Boolean selected) {
            this.selected = selected;
        }

        /**
         * @return
         */
        public Paint getColor() {
            return color;
        }

        /**
         * @param color
         */
        public void setColor(Paint color) {
            this.color = color;
        }

        /**
         * @return
         */
        public Marker getMarker() {
            return marker;
        }

        /**
         * @param marker
         */
        public void setMarker(Marker marker) {
            this.marker = marker;
        }

        /**
         * @return Returns string representation of this object.
         */
        @Override
        public String toString() {
            return "PointConfig [sliced=" + sliced + ", selected=" + selected
                    + ", color=" + color + ", marker=" + marker + "]";
        }

    }

    /**
     * A chart has a title and a subtitle. This class defines attributes which
     * are common to both.
     * 
     * The text of a title can be plain text or html text containing html
     * elements. It is also possible to apply css to the title. The css must be
     * valid css string e.g. { color: 'red' }
     * 
     * @author Invient
     * 
     * @see Title
     * @see SubTitle
     * @see HorzAlign
     * @see VertAlign
     */
    public static abstract class TitleBase implements Serializable {
        private HorzAlign align;
        private VertAlign vertAlign;
        private Boolean floating;
        private String text;
        private Integer x;
        private Integer y;
        private String style;

        /**
         * @return
         */
        public HorzAlign getAlign() {
            return align;
        }

        /**
         * Sets horizontal alignment of the title. Defaults to HorzAlign.CENTER
         * 
         * @param align
         */
        public void setAlign(HorzAlign align) {
            this.align = align;
        }

        /**
         * @return
         */
        public VertAlign getVertAlign() {
            return vertAlign;
        }

        /**
         * Sets horizontal alignment of the title. Defaults to VertAlign.TOP
         * 
         * @param vertAlign
         */
        public void setVertAlign(VertAlign vertAlign) {
            this.vertAlign = vertAlign;
        }

        /**
         * @return
         */
        public Boolean getFloating() {
            return floating;
        }

        /**
         * If the argument is true then the plot area will not move to make
         * space for the chart title. Defaults to false.
         * 
         * @param floating
         */
        public void setFloating(Boolean floating) {
            this.floating = floating;
        }

        /**
         * @return
         */
        public String getText() {
            return text;
        }

        /**
         * Sets text for the chart's title. The text can be plain or html
         * string.
         * 
         * @param text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         * @return
         */
        public Integer getX() {
            return x;
        }

        /**
         * Sets x position (in pixel) of the title relative to the alignment
         * within Spacing.left and Spacing.right. Defaults to 0
         * 
         * @param x
         */
        public void setX(Integer x) {
            this.x = x;
        }

        /**
         * @return
         */
        public Integer getY() {
            return y;
        }

        /**
         * Sets y position (in pixel) of the title relative to the alignment
         * within Spacing.top and Spacing.bottom. Defaults to 0
         * 
         * @param y
         */
        public void setY(Integer y) {
            this.y = y;
        }

        /**
         * @return
         */
        public String getStyle() {
            return style;
        }

        /**
         * Sets css for the title. The css must be a valid css object. e.g. css
         * string "{ color:'red' }" is valid but "{ color: 'red'" is invalid.
         * 
         * @param style
         */
        public void setStyle(String style) {
            this.style = style;
        }
    }

    /**
     * Defines attributes of chart title.
     * 
     * @author Invient
     * 
     */
    public static final class Title extends TitleBase {
        private Integer margin;

        /**
         * @return
         */
        public Integer getMargin() {
            return margin;
        }

        /**
         * Sets margin (in pixel) between the chart title and subtitle, if any.
         * If chart subtitle doesn't exist then it indicates the margin between
         * subtitle and plotarea. Defaults to 15
         * 
         * @param margin
         */
        public void setMargin(Integer margin) {
            this.margin = margin;
        }
    }

    /**
     * Defines attributes of chart subtitle.
     * 
     * @author Invient
     * 
     */
    public static final class SubTitle extends TitleBase {

    }

    public static enum HorzAlign {
        LEFT("left"), CENTER("center"), RIGHT("right");
        private String align;

        private HorzAlign(String align) {
            this.align = align;
        }

        public String getName() {
            return this.align;
        }
    }

    public static enum VertAlign {
        TOP("top"), MIDDLE("middle"), BOTTOM("bottom");
        private String align;

        private VertAlign(String align) {
            this.align = align;
        }

        public String getName() {
            return this.align;
        }
    }

    /**
     * Defines state for a series and point. A series can be in hover state. A
     * point can be in hover and select state. In each state, a series and a
     * point can have different visual clues. This is achived by setting some
     * attributes of a seires and point.
     * 
     * @author Invient
     * 
     * @see SeriesState
     */
    public static interface State extends Serializable {
        public Boolean getEnabled();
    }

    /**
     * Defines a set of attributes which will be applied to a series upon hover.
     * The attributes linWidth is not applicable for Pie, Scatter, Bar and
     * Column series.
     * 
     * @author Invient
     * 
     */
    public static class SeriesState implements State {
        private Boolean enabled;
        private Integer lineWidth;

        public Boolean getEnabled() {
            return enabled;
        }

        /**
         * If the argument is true then the other properties of this class have
         * impact on visual rendering of the series when a series is hovered or
         * when a mouse is over the legend. Enabling this has a performance
         * penalty.
         * 
         * Defaults to false.
         * 
         * @param enabled
         */
        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        /**
         * @return
         */
        public Integer getLineWidth() {
            return lineWidth;
        }

        /**
         * Sets width of a line in pixel. Defaults to 2.
         * 
         * @param lineWidth
         */
        public void setLineWidth(Integer lineWidth) {
            this.lineWidth = lineWidth;
        }
    }

    /**
     * Defines a set of attributes which are meaningful for bar and colum
     * series.
     * 
     * @author Invient
     * 
     */
    public static class NonLinearSeriesState extends SeriesState {
        private Double brightness;

        /**
         * @return
         */
        public Double getBrightness() {
            return brightness;
        }

        /**
         * Sets intensity of brightness for a point. This applies only to bar
         * and column series/chart
         * 
         * Defaults to 0.1
         * 
         * @param brightness
         */
        public void setBrightness(Double brightness) {
            this.brightness = brightness;
        }
    }

    /**
     * Defines a collection of attributes which makes a marker. Markers are
     * generally used to annotate a graph points.
     * 
     * @author Invient
     * 
     */
    private static class MarkerAttribute implements Serializable {
        private Boolean enabled;
        private Paint fillColor;
        private Paint lineColor;
        private Integer lineWidth;
        private Integer radius;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Paint getFillColor() {
            return fillColor;
        }

        public void setFillColor(Paint fillColor) {
            this.fillColor = fillColor;
        }

        public Paint getLineColor() {
            return lineColor;
        }

        public void setLineColor(Paint lineColor) {
            this.lineColor = lineColor;
        }

        public Integer getLineWidth() {
            return lineWidth;
        }

        public void setLineWidth(Integer lineWidth) {
            this.lineWidth = lineWidth;
        }

        public Integer getRadius() {
            return radius;
        }

        public void setRadius(Integer radius) {
            this.radius = radius;
        }

        @Override
        public String toString() {
            return "MarkerStateAttribute [enabled=" + enabled + ", fillColor="
                    + fillColor + ", lineColor=" + lineColor + ", lineWidth="
                    + lineWidth + ", radius=" + radius + "]";
        }

    }

    /**
     * Defines a set of attributes which gets applied to a point when a point is
     * selected or hovered. By default, markers are enabled so when a mouse is
     * over a point marker gets applied. To turn off marker, set flag enabled to
     * false.
     * 
     * A point marker is useful only if the marker is not an image.
     * 
     * @author Invient
     * 
     * @see ImageMarker
     * @see SymbolMarker
     * 
     */
    public static final class MarkerState implements State {
        private MarkerAttribute markerAttribute = new MarkerAttribute();

        /**
         * Creates this marker with enabled = true
         */
        public MarkerState() {
            markerAttribute.setEnabled(true);
        }

        /**
         * Creates this marker with specified argument. If enabled = false then
         * the marker will not be applied to a point on hover or select state.
         */
        public MarkerState(boolean enabled) {
            markerAttribute.setEnabled(enabled);
        }

        /**
         * 
         */
        public Boolean getEnabled() {
            return markerAttribute.getEnabled();
        }

        /**
         * If enabled = false then the marker will not be applied to a point on
         * hover or select state. Defaults to true
         * 
         * @param enabled
         */
        public void setEnabled(Boolean enabled) {
            this.markerAttribute.setEnabled(enabled);
        }

        /**
         * @return
         */
        public Paint getFillColor() {
            return markerAttribute.getFillColor();
        }

        /**
         * Sets fill color for the marker. When not specified it takes color of
         * a series or point.
         * 
         * @param fillColor
         */
        public void setFillColor(Paint fillColor) {
            this.markerAttribute.setFillColor(fillColor);
        }

        /**
         * @return
         */
        public Paint getLineColor() {
            return markerAttribute.getLineColor();
        }

        /**
         * Sets color of the point marker's outline. When not specified it takes
         * color of a series or point.
         * 
         * @param lineColor
         */
        public void setLineColor(Paint lineColor) {
            this.markerAttribute.setLineColor(lineColor);
        }

        /**
         * @return
         */
        public Integer getLineWidth() {
            return markerAttribute.getLineWidth();
        }

        /**
         * Sets width of the point marker's outline. Defaults to 0.
         * 
         * @param lineWidth
         */
        public void setLineWidth(Integer lineWidth) {
            this.markerAttribute.setLineWidth(lineWidth);
        }

        /**
         * @return
         */
        public Integer getRadius() {
            return markerAttribute.getRadius();
        }

        /**
         * Sets radius of the point marker. Defaults to 0.
         * 
         * @param radius
         */
        public void setRadius(Integer radius) {
            this.markerAttribute.setRadius(radius);
        }

        @Override
        public String toString() {
            return "MarkerState [enabled=" + getEnabled() + ", fillColor="
                    + getFillColor() + ", lineColor=" + getLineColor()
                    + ", lineWidth=" + getLineWidth() + ", radius="
                    + getRadius() + "]";
        }

    }

    /**
     * Defines a marker for a point. Markers are applied to a point of chart's
     * series. The marker can be applied at the time of drawing the chart or
     * when a point is selcted or hovered.
     * 
     * There are two types of marker.
     * <ul>
     * <li>
     * {@link SymbolMarker}</li>
     * <li>
     * {@link ImageMarker}</li>
     * </ul>
     * 
     * @author Invient
     * 
     * @see SymbolMarker
     * @see ImageMarker
     */
    public static interface Marker extends Serializable {
        public Boolean getEnabled();

        public void setEnabled(Boolean enabled);
    }

    /**
     * Defines attributes for a marker.
     * 
     * @author Invient
     * 
     * @see SymbolMarker
     * @see ImageMarker
     */
    public static abstract class AbstractMarker implements Marker {
        private MarkerAttribute markerAttribute = new MarkerAttribute();

        public AbstractMarker() {
        }

        public AbstractMarker(boolean enabled) {
            this.markerAttribute.setEnabled(enabled);
        }

        protected Paint getLineColor() {
            return markerAttribute.getLineColor();
        }

        protected void setLineColor(Paint lineColor) {
            this.markerAttribute.setLineColor(lineColor);
        }

        protected Paint getFillColor() {
            return markerAttribute.getFillColor();
        }

        protected void setFillColor(Paint fillColor) {
            this.markerAttribute.setFillColor(fillColor);
        }

        protected Integer getLineWidth() {
            return markerAttribute.getLineWidth();
        }

        protected void setLineWidth(Integer lineWidth) {
            this.markerAttribute.setLineWidth(lineWidth);
        }

        protected Integer getRadius() {
            return markerAttribute.getRadius();
        }

        protected void setRadius(Integer radius) {
            this.markerAttribute.setRadius(radius);
        }

        public Boolean getEnabled() {
            return markerAttribute.getEnabled();
        }

        public void setEnabled(Boolean enabled) {
            this.markerAttribute.setEnabled(enabled);
        }

    }

    /**
     * This marker can take url of an image which will be used as a marker for a
     * point or all points of a series.
     * 
     * The url of an image must be with respect to root of the web application.
     * e.g. If an image named temperature.png is under directory
     * <app.root.war>/img/climate then the url must be
     * /img/climate/temperature.png
     * 
     * @author Invient
     * 
     */
    public static class ImageMarker extends AbstractMarker {
        private String imageURL;

        /**
         * Creates this marker with specified arguments.
         * 
         * @param imageURL
         *            - URL of an image
         * @param enabled
         *            - If false then this marker will not be applied to a
         *            point. What this means is that the data points of a line
         *            chart will not stand out.
         */
        public ImageMarker(String imageURL, boolean enabled) {
            super(enabled);
            this.imageURL = imageURL;
        }

        /**
         * Creates this marker with specified arguments.
         * 
         * @param imageURL
         *            - URL of an image
         */
        public ImageMarker(String imageURL) {
            super(true);
            this.imageURL = imageURL;
        }

        /**
         * @return
         */
        public String getImageURL() {
            return imageURL;
        }

        /**
         * @param imageURL
         */
        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        @Override
        public String toString() {
            return "ImageMarker [imageURL=" + imageURL + ", enabled"
                    + getEnabled() + "]";
        }
    }

    /**
     * This marker has predefined shape which cannot be changed. However, marker
     * attributes can be set.
     * 
     * @author Invient
     * 
     */
    public static class SymbolMarker extends AbstractMarker {
        private Symbol symbol;
        private MarkerState hoverState;
        private MarkerState selectState;

        /**
         * Creates this marker with enabled = true
         */
        public SymbolMarker() {
            super(true);
        }

        /**
         * Creates this marker with specified arguments.
         * 
         * @param enabled
         *            If false then this marker will not be applied to a point.
         *            What this means is that the data points of a line chart
         *            will not stand out.
         */
        public SymbolMarker(boolean enabled) {
            super(enabled);
        }

        /**
         * Creates this marker with specified arguments.
         * 
         * @param lineColor
         *            - Color of the point marker's outline
         */
        public SymbolMarker(Paint lineColor) {
            super(true);
            super.setLineColor(lineColor);
        }

        /**
         * Creates this marker with specified arguments.
         * 
         * @param radius
         *            Radius of the point marker.
         */
        public SymbolMarker(Integer radius) {
            super(true);
            this.setRadius(radius);
        }

        /**
         * Creates this marker with specified arguments.
         * 
         * @param symbol
         *            It must be one of the predefine symbol such as
         *            Symbol.CIRCLE or Symbol.DIAMOND
         */
        public SymbolMarker(Symbol symbol) {
            super(true);
            this.symbol = symbol;
        }

        /**
         * Creates this marker with specified arguments.
         * 
         * @param lineColor
         *            Color of the point marker's outline
         * @param radius
         *            Radius of the point marker.
         */
        public SymbolMarker(Paint lineColor, Integer radius) {
            super(true);
            super.setLineColor(lineColor);
            super.setRadius(radius);
        }

        /**
         * Creates this marker with specified arguments.
         * 
         * @param lineColor
         *            - Color of the point marker's outline
         * @param radius
         *            Radius of the point marker.
         * @param symbol
         *            It must be one of the predefine symbol such as
         *            Symbol.CIRCLE or Symbol.DIAMOND
         */
        public SymbolMarker(Paint lineColor, Integer radius, Symbol symbol) {
            super(true);
            super.setLineColor(lineColor);
            super.setRadius(radius);
            this.symbol = symbol;
        }

        /**
         * 
         */
        @Override
        public Paint getLineColor() {
            return super.getLineColor();
        }

        /**
         * Sets color of the point marker's outline
         * 
         * @param lineColor
         */
        @Override
        public void setLineColor(Paint lineColor) {
            super.setLineColor(lineColor);
        }

        /**
         * 
         */
        @Override
        public Paint getFillColor() {
            return super.getFillColor();
        }

        /**
         * Sets color of the point marker
         * 
         * @param fillColor
         */
        @Override
        public void setFillColor(Paint fillColor) {
            super.setFillColor(fillColor);
        }

        /**
         * 
         */
        @Override
        public Integer getLineWidth() {
            return super.getLineWidth();
        }

        /**
         * Sets width of the point marker outline
         * 
         * @param lineWidth
         */
        @Override
        public void setLineWidth(Integer lineWidth) {
            super.setLineWidth(lineWidth);
        }

        /**
         * 
         */
        @Override
        public Integer getRadius() {
            return super.getRadius();
        }

        /**
         * Sets radius of the point marker
         * 
         * @param radius
         */
        @Override
        public void setRadius(Integer radius) {
            super.setRadius(radius);
        }

        /**
         * 
         * @return
         */
        public Symbol getSymbol() {
            return symbol;
        }

        /**
         * Sets symbol for the point marker. It must be one of the predefine
         * symbol such as Symbol.CIRCLE or Symbol.DIAMOND
         * 
         * @param symbol
         */
        public void setSymbol(Symbol symbol) {
            this.symbol = symbol;
        }

        /**
         * 
         * @return
         */
        public MarkerState getHoverState() {
            return hoverState;
        }

        /**
         * Sets marker to be applied to a point when it is hovered.
         * 
         * @param hoverState
         */
        public void setHoverState(MarkerState hoverState) {
            this.hoverState = hoverState;
        }

        /**
         * @return
         */
        public MarkerState getSelectState() {
            return selectState;
        }

        /**
         * Sets marker to be applied to a point when it is selected.
         * 
         * @param selectState
         */
        public void setSelectState(MarkerState selectState) {
            this.selectState = selectState;
        }

        @Override
        public String toString() {
            return "SymbolMarker [symbol=" + symbol + ", hoverState="
                    + hoverState + ", selectState=" + selectState
                    + ", getLineColor()=" + getLineColor()
                    + ", getFillColor()=" + getFillColor()
                    + ", getLineWidth()=" + getLineWidth() + ", getRadius()="
                    + getRadius() + ", getSymbol()=" + getSymbol()
                    + ", getHoverState()=" + getHoverState()
                    + ", getSelectState()=" + getSelectState() + "]";
        }

        /**
         * Defines predefined marker shapes to be used along with
         * {@link SymbolMarker}
         * 
         * @author Invient
         * 
         * @see SymbolMarker
         */
        public static enum Symbol {
            CIRCLE("circle"), DIAMOND("diamond"), SQUARE("square"), TRIANGLE(
                    "triangle"), TRIANGLE_DOWN("triangle-down");
            private String symbol;

            private Symbol(String symbol) {
                this.symbol = symbol;
            }

            public String getName() {
                return this.symbol;
            }
        }

    }

    /**
     * This class defines attributes common to X axis and Y axis. A chart can
     * have one or more axis of each type.
     * 
     * @author chirag
     * 
     * @see XAxis
     * @see YAxis
     * 
     */
    public static abstract class AxisBase implements Axis {
        private String id;

        private AxisType type = AxisType.LINEAR;
        private AxisTitle title;
        private AxisDataLabel label;
        private LinkedHashSet plotBands = new LinkedHashSet();
        private LinkedHashSet plotLines = new LinkedHashSet();

        private Paint alternateGridColor;
        private Boolean endOnTick;

        private Grid grid;

        private Paint lineColor;
        private Integer lineWidth;
        private Axis linkedTo;

        private Double maxPadding;
        private Integer maxZoom;
        // private Double max;
        // private Double min;
        private Double minPadding;

        private Tick tick;
        private MinorGrid minorGrid;
        private MinorTick minorTick;

        private Integer offset;
        private Boolean opposite;
        private Boolean reversed;
        private Boolean showFirstLabel;
        private Boolean showLastLabel;
        private WeekDay startOfWeek;
        private Boolean startOnTick;

        /**
         * Defines attributes of a minor tick. The minor ticks do not have a
         * label. By default, minor ticks are not shown. To display minor ticks,
         * set interval property.
         * 
         * @author Invient
         * 
         * @see Tick
         * 
         */
        public static class MinorTick implements Serializable {
            private Paint color;
            private Double interval;
            private Integer length;
            private TickPosition position;
            private Integer width;

            public Paint getColor() {
                return color;
            }

            public void setColor(Paint color) {
                this.color = color;
            }

            public Double getInterval() {
                return interval;
            }

            /**
             * Sets interval for the minor tick. The interval must be specified
             * in the axis unit. e.g. If an axis has tick interval of 50 units
             * then setting minortick interval to 10 will show 5 minor ticks.
             * 
             * @param interval
             */
            public void setInterval(Double interval) {
                this.interval = interval;
            }

            public Integer getLength() {
                return length;
            }

            /**
             * Sets length of the minorticks in pixel
             * 
             * @param length
             */
            public void setLength(Integer length) {
                this.length = length;
            }

            /**
             * @return
             */
            public TickPosition getPosition() {
                return position;
            }

            /**
             * @param position
             */
            public void setPosition(TickPosition position) {
                this.position = position;
            }

            /**
             * @return
             */
            public Integer getWidth() {
                return width;
            }

            /**
             * Sets width of the minorticks in pixel
             * 
             * @param width
             */
            public void setWidth(Integer width) {
                this.width = width;
            }

            @Override
            public String toString() {
                return "MinorTick [color=" + color + ", length=" + length
                        + ", position=" + position + ", width=" + width + "]";
            }

        }

        /**
         * 
         * Defines attributes of a tick marks. The interval of the tick marks
         * must be specified in axis unit. For datetime axis, the interval must
         * be in millisecond.
         * 
         * The default tick interval is 1.
         * 
         * @author Invient
         * 
         * @see MinorTick
         * @see TickmarkPlacement
         */
        public static final class Tick extends MinorTick {
            private TickmarkPlacement placement;
            private Integer pixelInterval;

            /**
             * @return
             */
            public TickmarkPlacement getPlacement() {
                return placement;
            }

            /**
             * Sets placement of the tick marks.
             * 
             * @param placement
             */
            public void setPlacement(TickmarkPlacement placement) {
                this.placement = placement;
            }

            /**
             * @return
             */
            public Integer getPixelInterval() {
                return pixelInterval;
            }

            /**
             * Sets pixel interval of the tick marks
             * 
             * @param pixelInterval
             */
            public void setPixelInterval(Integer pixelInterval) {
                this.pixelInterval = pixelInterval;
            }

            @Override
            public String toString() {
                return "Tick [placement=" + placement + ", pixelInterval="
                        + pixelInterval + ", getColor()=" + getColor()
                        + ", getLength()=" + getLength() + ", getPosition()="
                        + getPosition() + ", getWidth()=" + getWidth() + "]";
            }

        }

        /**
         * Defines attributes of minor grid lines of the chart. In order to show
         * minor grid lines, you must specify set MinorTick for the axis also.
         * 
         * @author Invient
         * @see MinorTick
         * @see Grid
         */
        public static class MinorGrid implements Serializable {
            private Paint lineColor;
            private DashStyle lineDashStyle;
            private Integer lineWidth;

            public Paint getLineColor() {
                return lineColor;
            }

            /**
             * Sets color of the minor grid lines
             * 
             * @param lineColor
             */
            public void setLineColor(Paint lineColor) {
                this.lineColor = lineColor;
            }

            /**
             * @return
             */
            public DashStyle getLineDashStyle() {
                return lineDashStyle;
            }

            /**
             * Sets dash or dot style of the minor grid lines. Defaults to
             * DashStyle.SOLID
             * 
             * @param lineDashStyle
             * 
             * @see DashStyle
             * 
             */
            public void setLineDashStyle(DashStyle lineDashStyle) {
                this.lineDashStyle = lineDashStyle;
            }

            /**
             * @return
             */
            public Integer getLineWidth() {
                return lineWidth;
            }

            /**
             * Sets width (in pixel) of the minor grid lines. Defaults to 1
             * 
             * @param lineWidth
             */
            public void setLineWidth(Integer lineWidth) {
                this.lineWidth = lineWidth;
            }

            @Override
            public String toString() {
                return "MinorGrid [lineColor=" + lineColor + ", lineDashStyle="
                        + lineDashStyle + ", lineWidth=" + lineWidth + "]";
            }

        }

        /**
         * Defines attributes of grid lines of the chart. By default, the grid
         * lines are shown. To hide them set property lineWidth to 0.
         * 
         * @author Invient
         * 
         */
        public static final class Grid extends MinorGrid {

        }

        protected LinkedHashSet getAllPlotBands() {
            return plotBands;
        }

        protected void setAllPlotBands(LinkedHashSet plotBands) {
            if (plotBands != null) {
                this.plotBands = plotBands;
            }
        }

        protected void addPlotBand(PlotBand plotBand) {
            this.plotBands.add(plotBand);
        }

        protected void removePlotBand(PlotBand plotBand) {
            this.plotBands.remove(plotBand);
        }

        /**
         * Removes a plotband with given id.
         * 
         * @param id
         */
        public void removePlotBand(String id) {
            Iterator<PlotBand> plotBandItr = ((LinkedHashSet<PlotBand>) this.plotBands)
                    .iterator();
            while (plotBandItr.hasNext()) {
                if (plotBandItr.next().getId().equals(id)) {
                    plotBandItr.remove();
                    break;
                }
            }
        }

        /**
         * @return
         */
        protected LinkedHashSet getAllPlotLines() {
            return plotLines;
        }

        /**
         * @param plotLines
         */
        protected void setAllPlotLines(LinkedHashSet plotLines) {
            if (plotLines != null) {
                this.plotLines = plotLines;
            }
        }

        /**
         * @param plotLine
         */
        protected void addPlotLine(PlotLine plotLine) {
            this.plotLines.add(plotLine);
        }

        /**
         * @param plotLine
         */
        protected void removePlotLine(PlotLine plotLine) {
            this.plotLines.remove(plotLine);
        }

        /**
         * @param id
         */
        public void removePlotLine(String id) {
            Iterator<PlotLine> plotLineItr = ((LinkedHashSet<PlotLine>) this.plotLines)
                    .iterator();
            while (plotLineItr.hasNext()) {
                if (plotLineItr.next().getId().equals(id)) {
                    plotLineItr.remove();
                    break;
                }
            }
        }

        /**
         *  
         */
        public String getId() {
            return id;
        }

        /**
         * Sets an id for the axis
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 
         */
        public Tick getTick() {
            return tick;
        }

        /**
         * Sets tick for the axis
         */
        public void setTick(Tick tick) {
            this.tick = tick;
        }

        /**
         * 
         */
        public Integer getMaxZoom() {
            return maxZoom;
        }

        /**
         * Sets maximum amount of zoom for this axis. For datetime axis, the
         * maxZoom must be specified in milliseconds. For example, for a
         * datetime axis the main unit is milliseconds. If maxZoom is set to
         * 3600000, you can't zoom in more than to one hour. (Above example is
         * taken from Highcharts documentation)
         */
        public void setMaxZoom(Integer maxZoom) {
            this.maxZoom = maxZoom;
        }

        /**
         * 
         */
        public Boolean getReversed() {
            return reversed;
        }

        /**
         * If the argument it true then this axis will be reversed. Defaults to
         * false.
         */
        public void setReversed(Boolean reversed) {
            this.reversed = reversed;
        }

        /**
         * 
         */
        public Boolean getOpposite() {
            return opposite;
        }

        /**
         * If the argument is true then another axis on the opposite side of
         * this axis will be displayed. The normal axis is on left side for
         * vertical axes and bottom for horzontal axes.
         */
        public void setOpposite(Boolean opposite) {
            this.opposite = opposite;
        }

        public AxisType getType() {
            return type;
        }

        /**
         * Sets type of this axis. Used by subclasses
         * 
         * @param type
         * 
         * @see NumberXAxis
         * @see NumberYAxis
         * @see DateTimeAxis
         * 
         */
        protected void setType(AxisType type) {
            this.type = type;
        }

        public AxisTitle getTitle() {
            return title;
        }

        /**
         * Sets title for the axis
         * 
         * @see AxisTitle
         */
        public void setTitle(AxisTitle title) {
            this.title = title;
        }

        /**
         * 
         * @return
         */
        protected AxisDataLabel getLabel() {
            return label;
        }

        /**
         * 
         * @param label
         */
        protected void setLabel(AxisDataLabel label) {
            this.label = label;
        }

        /**
         * 
         */
        public Paint getAlternateGridColor() {
            return alternateGridColor;
        }

        /**
         * Sets a color to be used for alternate grids of the chart
         */
        public void setAlternateGridColor(Paint alternateGridColor) {
            this.alternateGridColor = alternateGridColor;
        }

        /**
         * 
         */
        public Boolean getEndOnTick() {
            return endOnTick;
        }

        /**
         * If the argument is true then this axis will end on a tick.
         */
        public void setEndOnTick(Boolean endOnTick) {
            this.endOnTick = endOnTick;
        }

        /**
         * 
         */
        public Grid getGrid() {
            return grid;
        }

        /**
         * Sets grid for this axis
         * 
         * @see Grid
         */
        public void setGrid(Grid grid) {
            this.grid = grid;
        }

        /**
         * 
         */
        public Paint getLineColor() {
            return lineColor;
        }

        /**
         * Sets a color for line of this axis. This line indicate this axis
         */
        public void setLineColor(Paint lineColor) {
            this.lineColor = lineColor;
        }

        /**
         * 
         */
        public Integer getLineWidth() {
            return lineWidth;
        }

        /**
         * Sets width of this axis line
         */
        public void setLineWidth(Integer lineWidth) {
            this.lineWidth = lineWidth;
        }

        /**
         * 
         */
        public Axis getLinkedTo() {
            return linkedTo;
        }

        /**
         * Sets another axis which is linked with this axis. The following
         * description is copied from Highcharts API documentation
         * http://www.highcharts.com/ref/#xAxis.
         * 
         * When an axis is linked to a master axis, it will take the same
         * extremes as the master, but as assigned by min or max or by
         * setExtremes. It can be used to show additional info, or to ease
         * reading the chart by duplicating the scales. Defaults to null.
         */
        public void setLinkedTo(Axis linkedTo) {
            if (linkedTo != this) {
                this.linkedTo = linkedTo;
            }
        }

        /**
         * 
         */
        public Double getMaxPadding() {
            return maxPadding;
        }

        public void setMaxPadding(Double maxPadding) {
            this.maxPadding = maxPadding;
        }

        public Double getMinPadding() {
            return minPadding;
        }

        public void setMinPadding(Double minPadding) {
            this.minPadding = minPadding;
        }

        public MinorGrid getMinorGrid() {
            return minorGrid;
        }

        public void setMinorGrid(MinorGrid minorGrid) {
            this.minorGrid = minorGrid;
        }

        public MinorTick getMinorTick() {
            return minorTick;
        }

        public void setMinorTick(MinorTick minorTick) {
            this.minorTick = minorTick;
        }

        public Integer getOffset() {
            return offset;
        }

        /**
         * Sets distance of this axis from the plot area
         */
        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Boolean getShowFirstLabel() {
            return showFirstLabel;
        }

        /**
         * If the argument is true then the label of this axis' first tick will
         * be displayed. Defaults to true.
         */
        public void setShowFirstLabel(Boolean showFirstLabel) {
            this.showFirstLabel = showFirstLabel;
        }

        public Boolean getShowLastLabel() {
            return showLastLabel;
        }

        /**
         * If the argument is true then the label of this axis' last tick will
         * be displayed. Defaults to false
         */
        public void setShowLastLabel(Boolean showLastLabel) {
            this.showLastLabel = showLastLabel;
        }

        public WeekDay getStartOfWeek() {
            return startOfWeek;
        }

        /**
         * Sets a day to be considered as start of the week. For datetime axis,
         * this decides where to put tick. e.g. if startOfWeek = THURSDAY then
         * tick will be placed on every thursday.
         */
        public void setStartOfWeek(WeekDay startOfWeek) {
            this.startOfWeek = startOfWeek;
        }

        public Boolean getStartOnTick() {
            return startOnTick;
        }

        /**
         * If the argument is true then this axis must start on a tick. Defaults
         * to false
         */
        public void setStartOnTick(Boolean startOnTick) {
            this.startOnTick = startOnTick;
        }

        public static enum WeekDay {
            SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
        }

        /**
         * Defines position of the tick marks with respect to the axis
         * categories. It is applicable only for categorized axes.
         * 
         * TickmarkPlacement.ON - tick mark is placed in the center of the
         * category
         * 
         * TickmarkPlacement.BETWEEN - tick mark is placed between categories
         * 
         * @author Invient
         * 
         */
        public static enum TickmarkPlacement {
            ON("on"), BETWEEN("between");
            private String name;

            private TickmarkPlacement(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }
        }

        /**
         * Defines position of the axis ticks with respect to the axis line
         * 
         * @author Invient
         * 
         */
        public static enum TickPosition {
            OUTSIDE("outside"), INSIDE("inside");
            private String name;

            private TickPosition(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }
        }

        /**
         * Defines axis types.
         * 
         * AxisType.LINEAR -
         * 
         * AxisType.DATETIME - For datetime axis, the values are given in date
         * except for {@link BaseLineConfig}.pointStart and {@link BaseLineConfig}.pointInterval
         * properties, which are specified in milliseconds.
         * 
         * @author Invient
         * 
         * @see NumberXAxis
         * @see NumberYAxis
         * @see DateTimeAxis
         */
        public static enum AxisType {
            LINEAR("linear"), DATETIME("datetime");
            private String type;

            private AxisType(String type) {
                this.type = type;
            }

            public String getName() {
                return this.type;
            }
        }

        public static enum AxisTitleAlign {
            LOW("low"), MIDDLE("middle"), HIGH("high");
            private String name;

            private AxisTitleAlign(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }
        }

        public static final class AxisTitle implements Serializable {
            private String text;
            private AxisTitleAlign align;
            private String style;
            private Integer rotation;
            private Integer margin;

            public AxisTitle(String text) {
                this.text = text;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public AxisTitleAlign getAlign() {
                return align;
            }

            public void setAlign(AxisTitleAlign align) {
                this.align = align;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public Integer getRotation() {
                return rotation;
            }

            public void setRotation(Integer rotation) {
                this.rotation = rotation;
            }

            public Integer getMargin() {
                return margin;
            }

            public void setMargin(Integer margin) {
                this.margin = margin;
            }

        }

        public static final class PlotLabel implements Serializable {
            private HorzAlign align;
            private VertAlign vertAlign;
            private Integer rotation;
            private String style;
            private HorzAlign textAlign;
            private Integer x;
            private Integer y;
            private String text;

            public PlotLabel(String text) {
                super();
                this.text = text;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public HorzAlign getAlign() {
                return align;
            }

            public void setAlign(HorzAlign align) {
                this.align = align;
            }

            public VertAlign getVertAlign() {
                return vertAlign;
            }

            public void setVertAlign(VertAlign vertAlign) {
                this.vertAlign = vertAlign;
            }

            public Integer getRotation() {
                return rotation;
            }

            public void setRotation(Integer rotation) {
                this.rotation = rotation;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public HorzAlign getTextAlign() {
                return textAlign;
            }

            public void setTextAlign(HorzAlign textAlign) {
                this.textAlign = textAlign;
            }

            public Integer getX() {
                return x;
            }

            public void setX(Integer x) {
                this.x = x;
            }

            public Integer getY() {
                return y;
            }

            public void setY(Integer y) {
                this.y = y;
            }

        }

        public static abstract class PlotBand implements Serializable {
            private Paint color;
            private Range range;
            private String id;
            private Integer zIndex;
            private PlotLabel label;

            public PlotBand(String id) {
                this.id = id;
            }

            public Paint getColor() {
                return color;
            }

            public void setColor(Paint color) {
                this.color = color;
            }

            protected Range getRange() {
                return range;
            }

            protected void setRange(Range range) {
                this.range = range;
            }

            public String getId() {
                return id;
            }

            public Integer getZIndex() {
                return zIndex;
            }

            public void setZIndex(Integer zIndex) {
                this.zIndex = zIndex;
            }

            public PlotLabel getLabel() {
                return label;
            }

            public void setLabel(PlotLabel label) {
                this.label = label;
            }

            public static abstract class Range implements Serializable {

            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((id == null) ? 0 : id.hashCode());
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
                PlotBand other = (PlotBand) obj;
                if (id == null) {
                    if (other.id != null)
                        return false;
                } else if (!id.equals(other.id))
                    return false;
                return true;
            }

        }

        public static final class NumberPlotBand extends PlotBand {

            public NumberPlotBand(String id) {
                super(id);
            }

            public NumberRange getRange() {
                return (NumberRange) super.getRange();
            }

            public void setRange(NumberRange range) {
                super.setRange((NumberRange) range);
            }

            public static final class NumberRange extends Range {
                private Double from;
                private Double to;

                public NumberRange(Double from, Double to) {
                    super();
                    this.from = from;
                    this.to = to;
                }

                public Double getFrom() {
                    return from;
                }

                public void setFrom(Double from) {
                    this.from = from;
                }

                public Double getTo() {
                    return to;
                }

                public void setTo(Double to) {
                    this.to = to;
                }
            }
        }

        public static final class DateTimePlotBand extends PlotBand {

            public DateTimePlotBand(String id) {
                super(id);
            }

            public DateTimeRange getRange() {
                return (DateTimeRange) super.getRange();
            }

            public void setRange(DateTimeRange range) {
                super.setRange((DateTimeRange) range);
            }

            public static final class DateTimeRange extends Range {
                private Date from;
                private Date to;

                public DateTimeRange(Date from, Date to) {
                    super();
                    this.from = from;
                    this.to = to;
                }

                public Date getFrom() {
                    return from;
                }

                public void setFrom(Date from) {
                    this.from = from;
                }

                public Date getTo() {
                    return to;
                }

                public void setTo(Date to) {
                    this.to = to;
                }
            }

        }

        public static abstract class PlotLine implements Serializable {
            private Paint color;
            private DashStyle dashStyle;
            private String id;
            private Value value;
            private Integer width = 1;
            private Integer zIndex;
            private PlotLabel label;

            public PlotLine(String id) {
                this.id = id;
            }

            public Paint getColor() {
                return color;
            }

            public void setColor(Paint color) {
                this.color = color;
            }

            public DashStyle getDashStyle() {
                return dashStyle;
            }

            public void setDashStyle(DashStyle dashStyle) {
                this.dashStyle = dashStyle;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            protected Value getValue() {
                return value;
            }

            protected void setValue(Value value) {
                this.value = value;
            }

            public Integer getWidth() {
                return width;
            }

            public void setWidth(Integer width) {
                this.width = width;
            }

            public Integer getZIndex() {
                return zIndex;
            }

            public void setZIndex(Integer zIndex) {
                this.zIndex = zIndex;
            }

            public PlotLabel getLabel() {
                return label;
            }

            public void setLabel(PlotLabel label) {
                this.label = label;
            }

            public static abstract class Value implements Serializable {

            }

        }

        public static final class NumberPlotLine extends PlotLine {

            public NumberPlotLine(String id) {
                super(id);
            }

            public NumberValue getValue() {
                return (NumberValue) super.getValue();
            }

            public void setValue(NumberValue value) {
                super.setValue((NumberValue) value);
            }

            public static final class NumberValue extends Value {

                public NumberValue(Double value) {
                    super();
                    this.value = value;
                }

                private Double value;

                public Double getValue() {
                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }
            }
        }

        public static final class DateTimePlotLine extends PlotLine {
            public DateTimePlotLine(String id) {
                super(id);
            }

            public DateTimeValue getValue() {
                return (DateTimeValue) super.getValue();
            }

            public void setValue(DateTimeValue value) {
                super.setValue((DateTimeValue) value);
            }

            public static final class DateTimeValue extends Value {
                private Date value;

                public DateTimeValue(Date value) {
                    super();
                    this.value = value;
                }

                public Date getValue() {
                    return value;
                }

                public void setValue(Date value) {
                    this.value = value;
                }
            }
        }
    }

    public static interface Axis extends Serializable {
        public String getId();

        public void setId(String id);

        public Tick getTick();

        public void setTick(Tick tick);

        public Integer getMaxZoom();

        public void setMaxZoom(Integer maxZoom);

        public Boolean getReversed();

        public void setReversed(Boolean reversed);

        public Boolean getOpposite();

        public void setOpposite(Boolean opposite);

        public AxisType getType();

        public AxisTitle getTitle();

        public void setTitle(AxisTitle title);

        public Paint getAlternateGridColor();

        public void setAlternateGridColor(Paint alternateGridColor);

        public Boolean getEndOnTick();

        public void setEndOnTick(Boolean endOnTick);

        public Grid getGrid();

        public void setGrid(Grid grid);

        public Paint getLineColor();

        public void setLineColor(Paint lineColor);

        public Integer getLineWidth();

        public void setLineWidth(Integer lineWidth);

        public Axis getLinkedTo();

        public void setLinkedTo(Axis linkedTo);

        public Double getMaxPadding();

        public void setMaxPadding(Double maxPadding);

        public Double getMinPadding();

        public void setMinPadding(Double minPadding);

        public MinorGrid getMinorGrid();

        public void setMinorGrid(MinorGrid minorGrid);

        public MinorTick getMinorTick();

        public void setMinorTick(MinorTick minorTick);

        public Integer getOffset();

        public void setOffset(Integer offset);

        public Boolean getShowFirstLabel();

        public void setShowFirstLabel(Boolean showFirstLabel);

        public Boolean getShowLastLabel();

        public void setShowLastLabel(Boolean showLastLabel);

        public WeekDay getStartOfWeek();

        public void setStartOfWeek(WeekDay startOfWeek);

        public Boolean getStartOnTick();

        public void setStartOnTick(Boolean startOnTick);

    }

    public static interface XAxis extends Axis {
    }

    public static interface YAxis extends Axis {
    }

    public static abstract class NumberAxis extends AxisBase {
        private Boolean allowDecimals;
        private Double max;
        private Double min;

        public NumberAxis() {
            super.setType(AxisType.LINEAR);
        }

        public Boolean getAllowDecimals() {
            return allowDecimals;
        }

        public void setAllowDecimals(Boolean allowDecimals) {
            this.allowDecimals = allowDecimals;
        }

        public void setMax(Double max) {
            this.max = max;
        }

        public void setMin(Double min) {
            this.min = min;
        }

        public Double getMin() {
            return this.min;
        }

        public Double getMax() {
            return this.max;
        }

        public LinkedHashSet<NumberPlotBand> getPlotBands() {
            return super.getAllPlotBands();
        }

        public void setPlotBands(LinkedHashSet<NumberPlotBand> plotBands) {
            super.setAllPlotBands(plotBands);
        }

        public void addPlotBand(NumberPlotBand plotBand) {
            super.addPlotBand(plotBand);
        }

        public void removePlotBand(NumberPlotBand plotBand) {
            super.removePlotBand(plotBand);
        }

        public LinkedHashSet<NumberPlotLine> getPlotLines() {
            return super.getAllPlotLines();
        }

        public void setPlotLines(LinkedHashSet<NumberPlotLine> plotLines) {
            super.setAllPlotLines(plotLines);
        }

        public void addPlotLine(NumberPlotLine plotLine) {
            super.addPlotLine(plotLine);
        }

        public void removePlotLine(NumberPlotLine plotLine) {
            super.removePlotLine(plotLine);
        }

    }

    public static final class NumberXAxis extends NumberAxis implements XAxis {

        public void setLabel(XAxisDataLabel label) {
            super.setLabel(label);
        }

        public XAxisDataLabel getLabel() {
            return (XAxisDataLabel) super.getLabel();
        }
    }

    public static final class NumberYAxis extends NumberAxis implements YAxis {
        public void setLabel(YAxisDataLabel label) {
            super.setLabel(label);
        }

        public YAxisDataLabel getLabel() {
            return (YAxisDataLabel) super.getLabel();
        }

    }

    public static final class DateTimeAxis extends AxisBase implements XAxis {
        private DateTimeLabelFormat dateTimeLabelFormats;
        private Date max;
        private Date min;

        // private Date tickInterval; // FIXME It should be more intuitive to
        // specify tick interval such as
        // Month, Week, day, year similar to
        // private Date minorTickInterval;

        public static final class DateTimeLabelFormat {
            private String second = "%H:%M:%S";
            private String minute = "%H:%M";
            private String hour = "%H:%M";
            private String day = "%e. %b";
            private String week = "%e. %b";
            private String month = "%b '%y";
            private String year = "%Y";

            public String getSecond() {
                return second;
            }

            public void setSecond(String second) {
                this.second = second;
            }

            public String getMinute() {
                return minute;
            }

            public void setMinute(String minute) {
                this.minute = minute;
            }

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            @Override
            public String toString() {
                return "DateTimeLabelFormat [second=" + second + ", minute="
                        + minute + ", hour=" + hour + ", day=" + day
                        + ", week=" + week + ", month=" + month + ", year="
                        + year + "]";
            }

        }

        public DateTimeAxis() {
            super.setType(AxisType.DATETIME);
        }

        public DateTimeLabelFormat getDateTimeLabelFormat() {
            return dateTimeLabelFormats;
        }

        public void setDateTimeLabelFormat(
                DateTimeLabelFormat dateTimeLabelFormat) {
            this.dateTimeLabelFormats = dateTimeLabelFormat;
        }

        public void setMax(Date max) {
            this.max = max;
        }

        public void setMin(Date min) {
            this.min = min;
        }

        public Date getMin() {
            return this.min;
        }

        public Date getMax() {
            return this.max;
        }

        public LinkedHashSet<DateTimePlotBand> getPlotBands() {
            return super.getAllPlotBands();
        }

        public void setPlotBands(LinkedHashSet<DateTimePlotBand> plotBands) {
            super.setAllPlotBands(plotBands);
        }

        public void addPlotBand(DateTimePlotBand plotBand) {
            super.addPlotBand(plotBand);
        }

        public void removePlotBand(DateTimePlotBand plotBand) {
            super.removePlotBand(plotBand);
        }

        public LinkedHashSet<DateTimePlotLine> getPlotLines() {
            return super.getAllPlotLines();
        }

        public void setPlotLines(LinkedHashSet<DateTimePlotLine> plotLines) {
            super.setAllPlotLines(plotLines);
        }

        public void addPlotLine(DateTimePlotLine plotLine) {
            super.addPlotLine(plotLine);
        }

        public void removePlotLine(DateTimePlotLine plotLine) {
            super.removePlotLine(plotLine);
        }

    }

    public static final class CategoryAxis extends AxisBase implements XAxis {
        private List<String> categories = new ArrayList<String>();

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            if (categories != null) {
                this.categories = categories;
            }
        }

        public void setLabel(XAxisDataLabel label) {
            super.setLabel(label);
        }

        public XAxisDataLabel getLabel() {
            return (XAxisDataLabel) super.getLabel();
        }

        public LinkedHashSet<NumberPlotBand> getPlotBands() {
            return super.getAllPlotBands();
        }

        public void setPlotBands(LinkedHashSet<NumberPlotBand> plotBands) {
            super.setAllPlotBands(plotBands);
        }

        public void addPlotBand(NumberPlotBand plotBand) {
            super.addPlotBand(plotBand);
        }

        public void removePlotBand(NumberPlotBand plotBand) {
            super.removePlotBand(plotBand);
        }

        public LinkedHashSet<NumberPlotLine> getPlotLines() {
            return super.getAllPlotLines();
        }

        public void setPlotLines(LinkedHashSet<NumberPlotLine> plotLines) {
            super.setAllPlotLines(plotLines);
        }

        public void addPlotLine(NumberPlotLine plotLine) {
            super.addPlotLine(plotLine);
        }

        public void removePlotLine(NumberPlotLine plotLine) {
            super.removePlotLine(plotLine);
        }
    }

    // Legend
    public static final class Legend implements Serializable {
        private Paint backgroundColor;
        private Paint borderColor;
        private Integer borderRadius;
        private Integer borderWidth;
        private Boolean enabled;
        private Boolean floating;
        private String itemHiddenStyle;
        private String itemHoverStyle;
        private String itemStyle;
        private Integer itemWidth;
        private Layout layout;
        private String labelFormatterJsFunc;
        private Integer margin;
        private Boolean reversed;
        private Boolean shadow;
        private Integer symbolPadding;
        private Integer symbolWidth;
        private Integer width;
        private Position position;

        public Legend() {
        }

        public Legend(boolean enabled) {
            this.enabled = enabled;
        }

        public static enum Layout {
            HORIZONTAL("horizontal"), VERTICAL("vertical");
            private String name;

            private Layout(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }
        }

        public Paint getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(Paint backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        public Paint getBorderColor() {
            return borderColor;
        }

        public void setBorderColor(Paint borderColor) {
            this.borderColor = borderColor;
        }

        public Integer getBorderRadius() {
            return borderRadius;
        }

        public void setBorderRadius(Integer borderRadius) {
            this.borderRadius = borderRadius;
        }

        public Integer getBorderWidth() {
            return borderWidth;
        }

        public void setBorderWidth(Integer borderWidth) {
            this.borderWidth = borderWidth;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getFloating() {
            return floating;
        }

        public void setFloating(Boolean floating) {
            this.floating = floating;
        }

        public String getItemHiddenStyle() {
            return itemHiddenStyle;
        }

        public void setItemHiddenStyle(String itemHiddenStyle) {
            this.itemHiddenStyle = itemHiddenStyle;
        }

        public String getItemHoverStyle() {
            return itemHoverStyle;
        }

        public void setItemHoverStyle(String itemHoverStyle) {
            this.itemHoverStyle = itemHoverStyle;
        }

        public String getItemStyle() {
            return itemStyle;
        }

        public void setItemStyle(String itemStyle) {
            this.itemStyle = itemStyle;
        }

        public Integer getItemWidth() {
            return itemWidth;
        }

        public void setItemWidth(Integer itemWidth) {
            this.itemWidth = itemWidth;
        }

        public Layout getLayout() {
            return layout;
        }

        public void setLayout(Layout layout) {
            this.layout = layout;
        }

        public String getLabelFormatterJsFunc() {
            return labelFormatterJsFunc;
        }

        public void setLabelFormatterJsFunc(String labelFormatterJsFunc) {
            this.labelFormatterJsFunc = labelFormatterJsFunc;
        }

        public Integer getMargin() {
            return margin;
        }

        public void setMargin(Integer margin) {
            this.margin = margin;
        }

        public Boolean getReversed() {
            return reversed;
        }

        public void setReversed(Boolean reversed) {
            this.reversed = reversed;
        }

        public Boolean getShadow() {
            return shadow;
        }

        public void setShadow(Boolean shadow) {
            this.shadow = shadow;
        }

        public Integer getSymbolPadding() {
            return symbolPadding;
        }

        public void setSymbolPadding(Integer symbolPadding) {
            this.symbolPadding = symbolPadding;
        }

        public Integer getSymbolWidth() {
            return symbolWidth;
        }

        public void setSymbolWidth(Integer symbolWidth) {
            this.symbolWidth = symbolWidth;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "Legend [backgroundColor=" + backgroundColor
                    + ", borderColor=" + borderColor + ", borderRadius="
                    + borderRadius + ", borderWidth=" + borderWidth
                    + ", enabled=" + enabled + ", floating=" + floating
                    + ", itemHiddenStyle=" + itemHiddenStyle
                    + ", itemHoverStyle=" + itemHoverStyle + ", itemStyle="
                    + itemStyle + ", itemWidth=" + itemWidth + ", layout="
                    + layout + ", labelFormatter=" + labelFormatterJsFunc
                    + ", margin=" + margin + ", reversed=" + reversed
                    + ", shadow=" + shadow + ", symbolPadding=" + symbolPadding
                    + ", symbolWidth=" + symbolWidth + ", width=" + width
                    + ", position=" + position + "]";
        }

    }

    // Credits
    public static final class Credit implements Serializable {
        private Boolean enabled;
        private String link;
        private String style;
        private String text;
        private Position position;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "Credit [enabled=" + enabled + ", link=" + link + ", style="
                    + style + ", text=" + text + ", position=" + position + "]";
        }
    }

    public static final class Position implements Serializable {
        private HorzAlign align;
        private VertAlign vertAlign;
        private Integer x;
        private Integer y;

        public HorzAlign getAlign() {
            return align;
        }

        public void setAlign(HorzAlign align) {
            this.align = align;
        }

        public VertAlign getVertAlign() {
            return vertAlign;
        }

        public void setVertAlign(VertAlign vertAlign) {
            this.vertAlign = vertAlign;
        }

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position [align=" + align + ", vertAlign=" + vertAlign
                    + ", x=" + x + ", y=" + y + "]";
        }

    }

    // Tooltip
    public static final class Tooltip implements Serializable {
        private Paint backgroundColor;
        private Paint borderColor;
        private Integer borderRadius;
        private Integer borderWidth;
        private Boolean crosshairs; // FIMXE
        private Boolean enabled;
        private String formatterJsFunc;
        private Boolean shadow;
        private Boolean shared;
        private Integer snap; // NA for pie/bar/column
        private String style;
		private Boolean useHTML;
		private String headerFormat;
		private String pointFormat;
		private String footerFormat;

		public String getHeaderFormat() {
			return headerFormat;
		}

		public void setHeaderFormat(String headerFormat) {
			this.headerFormat = headerFormat;
		}

		public String getPointFormat() {
			return pointFormat;
		}

		public void setPointFormat(String pointFormat) {
			this.pointFormat = pointFormat;
		}

		public String getFooterFormat() {
			return footerFormat;
		}

		public void setFooterFormat(String footerFormat) {
			this.footerFormat = footerFormat;
		}

		public Boolean getUseHTML() {
			return useHTML;
		}

		public void setUseHTML(Boolean useHTML) {
			this.useHTML = useHTML;
		}

		public Paint getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(Paint backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        public Paint getBorderColor() {
            return borderColor;
        }

        public void setBorderColor(Paint borderColor) {
            this.borderColor = borderColor;
        }

        public Integer getBorderRadius() {
            return borderRadius;
        }

        public void setBorderRadius(Integer borderRadius) {
            this.borderRadius = borderRadius;
        }

        public Integer getBorderWidth() {
            return borderWidth;
        }

        public void setBorderWidth(Integer borderWidth) {
            this.borderWidth = borderWidth;
        }

        public Boolean getCrosshairs() {
            return crosshairs;
        }

        public void setCrosshairs(Boolean crosshairs) {
            this.crosshairs = crosshairs;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getFormatterJsFunc() {
            return formatterJsFunc;
        }

        public void setFormatterJsFunc(String formatterJsFunc) {
            this.formatterJsFunc = formatterJsFunc;
        }

        public Boolean getShadow() {
            return shadow;
        }

        public void setShadow(Boolean shadow) {
            this.shadow = shadow;
        }

        public Boolean getShared() {
            return shared;
        }

        public void setShared(Boolean shared) {
            this.shared = shared;
        }

        public Integer getSnap() {
            return snap;
        }

        public void setSnap(Integer snap) {
            this.snap = snap;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        @Override
        public String toString() {
            return "Tooltip [backgroundColor=" + backgroundColor
                    + ", borderColor=" + borderColor + ", borderRadius="
                    + borderRadius + ", borderWidth=" + borderWidth
                    + ", crosshairs=" + crosshairs + ", enabled=" + enabled
                    + ", formatter=" + formatterJsFunc + ", shadow=" + shadow
                    + ", shared=" + shared + ", snap=" + snap + ", style="
                    + style + "]";
        }

    }
}
