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
package com.invient.vaadin.charts.widgetset.client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtAxisBaseOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtAxisBaseOptions.GwtAxisDataLabels;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtAxisBaseOptions.GwtAxisTitleOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtAxisBaseOptions.GwtPlotBands;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtAxisBaseOptions.GwtPlotLabel;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtAxisBaseOptions.GwtPlotLines;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtAxisBaseOptions.GwtXAxisDataLabels;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtAxisBaseOptions.GwtYAxisDataLabels;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtChartLabels;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtChartLabels.GwtChartLabelItem;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtChartOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtChartOptions.GwtChartEvents;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtCreditOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtLegendOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtAreaOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtAreaSplineOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtBarOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtBaseBarOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtBaseLineOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtColumnOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtDataLabels;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtLineOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtMarker;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtMarker.GwtMarkerStates;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtMarker.GwtMarkerStates.GwtMarkerState;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtPieDataLabels;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtPieOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtScatterOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtSeriesEvents;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtSeriesGeneralOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtSeriesGeneralOptions.GwtStates;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtSeriesGeneralOptions.GwtStates.GwtHover;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtSplineOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPointOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPointOptions.GwtPointEvents;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPosition;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtSeriesDataOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtSubtitleOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtTitleBaseOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtTitleOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtTooltipOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtXAxisOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtXAxisOptions.GwtDateTimeLabelFormats;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtYAxisOptions;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.VConsole;

/**
 * Client side widget which communicates with the server. Messages from the
 * server are shown as HTML and mouse clicks are sent to the server.
 * 
 * Reads data from UIDL and create appropriate JavaScript overlay objects such
 * as {@link GwtChart}, {@link GwtAxis}, {@link GwtInvientChartsConfig},
 * {@link GwtPoint} and {@link GwtSeries}
 * 
 * Uses a method newChart() of {@link GwtInvientChartsUtil} to create a chart
 * object of type {@link GwtChart}
 * 
 * @author Invient
 */
public class VInvientCharts extends GwtInvientCharts implements Paintable /*
                                                                           * ,
                                                                           * ClickHandler
                                                                           * ,
                                                                           * ScrollHandler
                                                                           */{

    private static final long serialVersionUID = -762763091427791681L;

    /** Set the CSS class name to allow styling. */
    public static final String CLASSNAME = "v-invientcharts";

    /** The client side widget identifier */
    protected String uidlId;

    /** Reference to the server connection object. */
    protected ApplicationConnection client;

    /**
     * The constructor should first call super() to initialize the component and
     * then handle any initialization relevant to Vaadin.
     */
    public VInvientCharts() {
        super();
        setStyleName(CLASSNAME);
        publish();
    }

    /**
     * Called whenever an update is received from the server
     */
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
        VConsole.log("Enter [updateFromUIDL]");
        // This call should be made first.
        // It handles sizes, captions, tooltips, etc. automatically.
        if (client.updateComponent(this, uidl, true)) {
            // If client.updateComponent returns true there has been no changes
            // and we
            // do not need to update anything.
            return;
        }

        // Save reference to server connection object to be able to send
        // user interaction later
        this.client = client;

        // Save the client side identifier (paintable id) for the widget
        uidlId = uidl.getId();

        // Create chart only once along with chart options
        // Chart options are set only once.
        if (chart == null) {
            // Chart options
            GwtInvientChartsConfig options = getInvientChartOptions(uidl
                    .getChildUIDL(ChartUIDLIndex.OPTIONS.ordinal()));
            // Chart events
            updateOptionsWithEvents(options,
                    uidl.getChildUIDL(ChartUIDLIndex.EVENTS.ordinal()));
            // Chart data
            JsArray<GwtSeriesDataOptions> chartData = getChartData(uidl
                    .getChildUIDL(ChartUIDLIndex.DATA.ordinal()));
            options.setSeriesInstanceOptions(chartData);
            VConsole.log("Going to create a chart.");            
            createChart(options);
        } else {
            resetRedrawChart();
            if (uidl.getBooleanAttribute("reloadChartSeries")) {
                // Get all series and add them to chart
                JsArray<GwtSeriesDataOptions> chartData = getChartData(uidl
                        .getChildUIDL(ChartUIDLIndex.DATA.ordinal()));
                int seriesCount = chart.getSeries().length();
                VConsole.log("# of series the chart has " + seriesCount);
                VConsole.log("Going to remove all series of the chart.");
                for (int ind = seriesCount - 1; ind >= 0; ind--) {
                    setRedrawChart();
                    chart.getSeries().get(ind).remove(false);
                }
                VConsole.log("Goint to add series to the chart.");
                for (int ind = 0; ind < chartData.length(); ind++) {
                    setRedrawChart();
                    chart.addSeries(chartData.get(ind), false);
                }
            } else {
                VConsole.log("Going to update chart data.");
                UIDL chartDataUIDL = uidl.getChildUIDL(ChartUIDLIndex.DATA
                        .ordinal());
                UIDL chartDataUpdatesUIDL = uidl
                        .getChildUIDL(ChartUIDLIndex.DATA_UPDATES.ordinal());
                updateChartData(chartDataUpdatesUIDL, chartDataUIDL);
            }
            
            // Options UIDL
            UIDL optionsUIDL = uidl.getChildUIDL(ChartUIDLIndex.OPTIONS
                    .ordinal());
            // Update chart title & subtitle
            setChartTitleAndSubtitle(optionsUIDL);
            // Size
            setChartSize(optionsUIDL);

            VConsole.log("Getting x-axis options...");
            JsArray<GwtXAxisOptions> uidlXAxesOptionsArr = getXAxisOptions(optionsUIDL
                    .getChildUIDL(ChartOptionsUIDLIndex.X_AXES.ordinal()));
            JsArray<GwtXAxisOptions> chartXAxesOptionsArr = JavaScriptObject
                    .createArray().cast();
            JsArray<GwtAxis> chartXAxesArr = chart.getXAxes();
            if (chart.getOptions().hasXAxesOptions()) {
                chartXAxesOptionsArr = chart.getOptions().getXAxesOptions();
                updateXAxisCategories(chartXAxesArr, chartXAxesOptionsArr,
                        uidlXAxesOptionsArr);
            }
            updateAxesPlotBandsAndPlotLines(chartXAxesArr,
                    chartXAxesOptionsArr, uidlXAxesOptionsArr);

            VConsole.log("Getting y-axis options...");
            JsArray<GwtYAxisOptions> uidlYAxesOptionsArr = getYAxisOptions(optionsUIDL
                    .getChildUIDL(ChartOptionsUIDLIndex.Y_AXES.ordinal()));
            JsArray<GwtYAxisOptions> chartYAxesOptionsArr = JavaScriptObject
                    .createArray().cast();
            if (chart.getOptions().hasYAxesOptions()) {
                chartYAxesOptionsArr = chart.getOptions().getYAxesOptions();
            }
            JsArray<GwtAxis> chartYAxesArr = chart.getYAxes();
            updateAxesPlotBandsAndPlotLines(chartYAxesArr,
                    chartYAxesOptionsArr, uidlYAxesOptionsArr);
            // Update axis extremes
            if (chart.getOptions().hasXAxesOptions()
                    || chart.getOptions().hasYAxesOptions()) {
                updateAxisExtremes(chart.getXAxes(), chartXAxesOptionsArr,
                        uidlXAxesOptionsArr);
                updateAxisExtremes(chart.getYAxes(), chartYAxesOptionsArr,
                        uidlYAxesOptionsArr);
            }
            if (isRedrawChart()) {
                VConsole.log("Going to redraw the chart.");
                chart.redraw();
            }
        }
        // Get SVG if required and send it to server
        handleChartSVG(uidl);
        handlePrint(uidl);
        VConsole.log("Exit [updateFromUIDL]");
    }

    // Set title & subtitle
    private void setChartTitleAndSubtitle(UIDL optionsUIDL) {
        VConsole.log("Enter [setChartTitleAndSubtitle]");
        // There is not need to set redrawChart flag as setting title & subtitle 
        // does not require redrawing of the chart.
        chart.setTitle(
                getTitleOptions(optionsUIDL
                        .getChildUIDL(ChartOptionsUIDLIndex.TITLE.ordinal())),
                getSubtitleOptions(optionsUIDL
                        .getChildUIDL(ChartOptionsUIDLIndex.SUBTITLE.ordinal())));
        VConsole.log("Exit [setChartTitleAndSubtitle]");
    }

    // Set chart size
    private void setChartSize(UIDL optionsUIDL) {
        // There is not need to set redrawChart flag as setting title & subtitle 
        // does not require redrawing of the chart.
        GwtChartOptions chartOptions = getChartOptions(optionsUIDL
                .getChildUIDL(ChartOptionsUIDLIndex.CHART_CONFIG.ordinal()));
        int newWidth = chartOptions.getWidth();
        int newHeight = chartOptions.getHeight();
        updateChartSize(newWidth, newHeight);
    }

    private void updateChartSize(int newWidth, int newHeight) {
        int existingWidth = chart.getOptions().getChartOptions().getWidth();
        int existingHeight = chart.getOptions().getChartOptions().getHeight();

        if ((newWidth != -1 && newWidth != existingWidth)
                || (newHeight != -1 && newHeight != existingHeight)) {
            VConsole.log("Set chart size.");            
            chart.getOptions().getChartOptions().setWidth(newWidth);
            chart.getOptions().getChartOptions().setHeight(newHeight);
            chart.setSize(newWidth, newHeight);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeight(String height) {
        super.setHeight(height);
        updateChartSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidth(String width) {
        super.setWidth(width);
        updateChartSize();
    }

    private void updateChartSize() {
        if (chart != null) {
            updateChartSize(getElement().getOffsetWidth(), getElement().getOffsetHeight());
        }
    }

    private void handlePrint(UIDL uidl) {
        boolean isPrint = uidl.getBooleanAttribute("isPrint");
        if (isPrint) {
            VConsole.log("Going to print the chart...");
            chart.printInvientChart();            
        }
    }

    private void handleChartSVG(UIDL uidl) {
        boolean isRetrieveSVG = uidl.getBooleanAttribute("isRetrieveSVG");
        if (isRetrieveSVG) {
            VConsole.log("Get an svg string...");
            String svg = chart.getSVG(null);
            // send svg to server
            client.updateVariable(uidlId, "event", "chartSVGAvailable", false);
            Map<String, Object> eventData = new HashMap<String, Object>();
            eventData.put("svg", svg);
            client.updateVariable(uidlId, "eventData", eventData, true);
        }
    }

    private void updateXAxisCategories(JsArray<GwtAxis> chartAxesArr,
            JsArray<GwtXAxisOptions> chartXAxesOptionsArr,
            JsArray<GwtXAxisOptions> uidlXAxesOptionsArr) {
        VConsole.log("Enter [updateXAxisCategories]");
        if (chartXAxesOptionsArr == null || chartXAxesOptionsArr.length() == 0) {
            VConsole.log("Chart doesn't have any X axis]");
            VConsole.log("Exit [updateXAxisCategories]");
            return;
        }
        int noOfAxis = chartXAxesOptionsArr.length();
        for (int ind = 0; ind < noOfAxis; ind++) {
            GwtAxis chartAxis = chartAxesArr.get(ind);
            GwtXAxisOptions chartAxisOptions = chartXAxesOptionsArr.get(ind);
            GwtXAxisOptions uidlAxisOptions = uidlXAxesOptionsArr.get(ind);
            if (chartAxis != null && chartAxisOptions != null
                    && uidlAxisOptions != null) {
                // If axis
                if (!areStringArraysEqual(uidlAxisOptions.getCategories(),
                        chartAxis.getCategories())) {
                    setRedrawChart();
                    chartAxisOptions.setCategories(uidlAxisOptions
                            .getCategories());
                    chartAxis.setCategories(uidlAxisOptions.getCategories(),
                            false);
                }
            }
        }
        VConsole.log("Exit [updateXAxisCategories]");
    }

    private boolean areStringArraysEqual(JsArrayString arrOne,
            JsArrayString arrTwo) {
        if (arrOne == arrTwo) {
            return true;
        }
        if ((arrOne != null && arrTwo == null)
                || (arrOne == null && arrTwo != null)) {
            return false;
        }
        if (arrOne.length() != arrTwo.length()) {
            return false;
        }
        // Compare each array element
        for (int arrInd = 0; arrInd < arrOne.length(); arrInd++) {
            String arrOneVal = arrOne.get(arrInd);
            String arrTwoVal = arrTwo.get(arrInd);
            if (arrOneVal == null) {
                if (arrTwoVal != null) {
                    return false;
                }
            }
            else if(!arrOneVal.equals(arrTwoVal)) {
                return false;
            }
        }

        return true;
    }

    private void updateAxisExtremes(JsArray<GwtAxis> chartAxesArr,
            JsArray<? extends GwtAxisBaseOptions> chartAxesOptionsArr,
            JsArray<? extends GwtAxisBaseOptions> uidlAxesOptionsArr) {
        VConsole.log("Enter [updateAxisExtremes]");
        if (chartAxesOptionsArr == null || chartAxesOptionsArr.length() == 0) {
            VConsole.log("Chart doesn't have any X/Y axis]");
            VConsole.log("Exit [updateAxisExtremes]");
            return;
        }
        int noOfAxis = chartAxesOptionsArr.length();
        for (int ind = 0; ind < noOfAxis; ind++) {
            GwtAxis chartAxis = chartAxesArr.get(ind);
            GwtAxisBaseOptions chartAxisOptions = chartAxesOptionsArr.get(ind);
            GwtAxisBaseOptions uidlAxisOptions = uidlAxesOptionsArr.get(ind);
            if (chartAxis != null && chartAxisOptions != null
                    && uidlAxisOptions != null) {
                double uidlMin = uidlAxisOptions.getMin();
                double uidlMax = uidlAxisOptions.getMax();
                double chartMin = chartAxisOptions.getMin();
                double chartMax = chartAxisOptions.getMax();
                // Update chart's axis options as
                // it is not updated when extremes are set using
                // axis.setExtremes()
                if (uidlMin != chartMin) {
                    setRedrawChart();
                    chartAxisOptions.setMin(uidlMin);
                }                
                if (uidlMax != chartMax) {
                    setRedrawChart();
                    chartAxisOptions.setMax(uidlMax);
                }
                VConsole.log("[updateAxisExtremes] min " + chartAxisOptions.getMin() + ", max " + chartAxisOptions.getMax());                
                chartAxis.setExtremes(chartAxisOptions.getMin(), chartAxisOptions.getMax(), false);
            }
        }
        VConsole.log("Exit [updateAxisExtremes]");
    }

    private enum ChartUIDLIndex {
        OPTIONS, DATA, EVENTS, DATA_UPDATES;
    }

    private enum ChartOptionsUIDLIndex {
        TITLE, SUBTITLE, CREDIT, LEGEND, TOOLTIP, CHART_CONFIG, SERIES_OPTIONS, X_AXES, Y_AXES, LABEL;
    }

    private void updateAxesPlotBandsAndPlotLines(
            JsArray<? extends GwtAxis> chartAxesArr,
            JsArray<? extends GwtAxisBaseOptions> chartAxesOptionsArr,
            JsArray<? extends GwtAxisBaseOptions> uidlAxesOptionsArr) {
        VConsole.log("Enter [updateAxesPlotBandsAndPlotLines]");
        int noOfAxis = chartAxesArr.length();
        for (int ind = 0; ind < noOfAxis; ind++) {
            GwtAxis chartAxis = chartAxesArr.get(ind);
            GwtAxisBaseOptions chartAxisOptions = chartAxesOptionsArr.get(ind);
            GwtAxisBaseOptions uidlAxisOptions = uidlAxesOptionsArr.get(ind);
            if (chartAxis != null && chartAxisOptions != null
                    && uidlAxisOptions != null) {
                updatePlotBands(chartAxis, chartAxisOptions, uidlAxisOptions);
                updatePlotLines(chartAxis, chartAxisOptions, uidlAxisOptions);
            }
        }
        VConsole.log("Exit [updateAxesPlotBandsAndPlotLines]");
    }

    //
    private void updatePlotLines(GwtAxis chartAxis,
            GwtAxisBaseOptions chartAxisOptions,
            GwtAxisBaseOptions uidlAxisOptions) {
        VConsole.log("Enter [updatePlotLines]");
        // Update chartAxisPlotBands whenever a plotline is added or removed as
        // the library
        // does not update chart options by itself.
        JsArray<GwtPlotLines> chartAxisPlotLines = chartAxisOptions
                .getPlotLines();
        JsArray<GwtPlotLines> uidlAxisPlotLines = uidlAxisOptions
                .getPlotLines();
        if (uidlAxisPlotLines == null && chartAxisPlotLines == null) {
            VConsole.log("No plotlines found.");
            VConsole.log("Exit [updatePlotLines]");
            return;
        }
        if (uidlAxisPlotLines == null) {
            uidlAxisPlotLines = JavaScriptObject.createArray().cast();
        }
        if (chartAxisPlotLines == null) {
            chartAxisPlotLines = JavaScriptObject.createArray().cast();
        }
        JsArray<GwtPlotLines> updatedChartAxisPlotLines = JavaScriptObject
                .createArray().cast();
        int numOfChartAxisPlotLines = chartAxisPlotLines.length();
        int numOfUIDLAxisPlotLines = uidlAxisPlotLines.length();
        boolean updatedAxisPlotLines = false;
        for (int indOuter = 0; indOuter < numOfChartAxisPlotLines; indOuter++) {
            GwtPlotLines chartPlotLine = chartAxisPlotLines.get(indOuter);
            String plotLineId = chartPlotLine.getId();
            boolean found = false;
            for (int indInner = 0; indInner < numOfUIDLAxisPlotLines; indInner++) {
                GwtPlotLines uidlPlotLine = uidlAxisPlotLines.get(indInner);
                if (uidlPlotLine != null
                        && uidlPlotLine.getId().equals(plotLineId)) {
                    if (uidlPlotLine.getValue() == chartPlotLine.getValue()) {
                        // PlotLine exists and value is same so no action should
                        // be taken except marking UIDL PlotLine to null.
                        // Setting UIDL PlotLine
                        // to null ensures that remaining PlotLines in UIDL can
                        // be added
                        // safely to the chart.
                        uidlAxisPlotLines.set(indInner, null);
                        updatedChartAxisPlotLines.push(chartPlotLine);
                        found = true;
                    }
                    break;
                }
            }
            if (!found) {
                // remove plot line as it is not found in UIDL received from the
                // server
                updatedAxisPlotLines = true;
                chartAxis.removePlotLine(plotLineId);
            }
        }
        // Add all remaining plot lines in UIDL to the chart
        for (int ind = 0; ind < numOfUIDLAxisPlotLines; ind++) {
            GwtPlotLines uidlPlotLine = uidlAxisPlotLines.get(ind);
            if (uidlPlotLine != null) {
                updatedAxisPlotLines = true;
                chartAxis.addPlotLine(uidlPlotLine);
                updatedChartAxisPlotLines.push(uidlPlotLine);
            }
        }

        // Update chart axis plotlines
        if (updatedAxisPlotLines) {
            setRedrawChart();
            chartAxisOptions.setPlotLines(updatedChartAxisPlotLines);
        }
        VConsole.log("Exit [updatePlotLines]");
    }

    //
    private void updatePlotBands(GwtAxis chartAxis,
            GwtAxisBaseOptions chartAxisOptions,
            GwtAxisBaseOptions uidlAxisOptions) {
        VConsole.log("Enter [updatePlotBands]");
        // Update chartAxisPlotBands whenever a plotband is added or removed as
        // the library
        // does not update chart options by itself.
        JsArray<GwtPlotBands> chartAxisPlotBands = chartAxisOptions
                .getPlotBands();
        JsArray<GwtPlotBands> uidlAxisPlotBands = uidlAxisOptions
                .getPlotBands();
        if (uidlAxisPlotBands == null && chartAxisPlotBands == null) {
            VConsole.log("No plotbands found.");
            VConsole.log("Exit [updatePlotBands]");
            return;
        }
        if (uidlAxisPlotBands == null) {
            uidlAxisPlotBands = JavaScriptObject.createArray().cast();
        }
        if (chartAxisPlotBands == null) {
            chartAxisPlotBands = JavaScriptObject.createArray().cast();
        }
        JsArray<GwtPlotBands> updatedChartAxisPlotBands = JavaScriptObject
                .createArray().cast();
        int numOfChartAxisPlotBands = chartAxisPlotBands.length();
        int numOfUIDLAxisPlotBands = uidlAxisPlotBands.length();
        boolean updatedAxisPlotBands = false;
        for (int indOuter = 0; indOuter < numOfChartAxisPlotBands; indOuter++) {
            GwtPlotBands chartPlotBand = chartAxisPlotBands.get(indOuter);
            String plotBandId = chartPlotBand.getId();
            boolean found = false;
            for (int indInner = 0; indInner < numOfUIDLAxisPlotBands; indInner++) {
                GwtPlotBands uidlPlotBand = uidlAxisPlotBands.get(indInner);
                if (uidlPlotBand != null
                        && uidlPlotBand.getId().equals(plotBandId)) {
                    if (chartPlotBand.getFrom() == uidlPlotBand.getFrom()
                            && chartPlotBand.getTo() == uidlPlotBand.getTo()) {
                        VConsole.log("Plotband id "
                                + plotBandId
                                + " exists in chart as well as in UIDL from the server.");
                        // PlotBand exists and from/to values are same so
                        // nothing to be done.
                        // The UIDL plotband is set to null so that remaining
                        // plotbands
                        // can be safely added to the chart
                        uidlAxisPlotBands.set(indInner, null);
                        updatedChartAxisPlotBands.push(chartPlotBand);
                        VConsole.log("Plotband id " + plotBandId
                                + " exists in both.");
                        found = true;
                    }
                    break;
                }
            }
            if (!found) {
                // remove plot band as it is not found in UIDL received from the
                // server
                VConsole.log("Plotband id " + plotBandId + " removed.");
                updatedAxisPlotBands = true;
                chartAxis.removePlotBand(plotBandId);
            }
        }
        // Add all remaining plot bands in UIDL to the chart
        for (int ind = 0; ind < numOfUIDLAxisPlotBands; ind++) {
            GwtPlotBands uidlPlotBand = uidlAxisPlotBands.get(ind);
            if (uidlPlotBand != null) {
                updatedAxisPlotBands = true;
                VConsole.log("Plotband id " + uidlPlotBand.getId()
                        + " added with from : " + uidlPlotBand.getFrom()
                        + " and to: " + uidlPlotBand.getTo());
                chartAxis.addPlotBand(uidlPlotBand);
                updatedChartAxisPlotBands.push(uidlPlotBand);
            }
        }

        // Update chart axis plotbands
        if (updatedAxisPlotBands) {
            setRedrawChart();
            chartAxisOptions.setPlotBands(updatedChartAxisPlotBands);
        }
        VConsole.log("Exit [updatePlotBands]");
    }

    private boolean redrawChart = false;

    private void setRedrawChart() {
        this.redrawChart = true;
    }

    private boolean isRedrawChart() {
        return this.redrawChart;
    }

    private void resetRedrawChart() {
        this.redrawChart = false;
    }
    
    private void updateChartData(UIDL uidlChartDataUpdates, UIDL uidlChartData) {
        VConsole.log("Enter [updateChartData]");
        JsArrayString seriesToAdd = JavaScriptObject.createArray().cast();
        JsArrayString seriesToUpdate = JavaScriptObject.createArray().cast();
        List<UIDL> seriesToUpdateList = new ArrayList<UIDL>();
        for (int ind = 0; ind < uidlChartDataUpdates.getChildCount(); ind++) {
            UIDL seriesUpdateUIDL = uidlChartDataUpdates.getChildUIDL(ind);
            String seriesName = seriesUpdateUIDL
                    .getStringAttribute("seriesName");
            String operation = seriesUpdateUIDL.getStringAttribute("operation");
            VConsole.log("Series name : " + seriesName + ", operation : "
                    + operation);
            if (seriesName != null && seriesName.length() > 0
                    && operation != null && operation.length() > 0) {
                if (SeriesCURType.REMOVE.getName().equals(operation)) {
                    GwtSeries series = chart.getSeries(seriesName);
                    if (series != null) {
                        VConsole.log("Removing series : " + seriesName);
                        setRedrawChart();
                        series.remove(false);
                    }
                } else if (SeriesCURType.ADD.getName().equals(operation)) {
                    seriesToAdd.push(seriesName);
                } else if (SeriesCURType.UPDATE.getName().equals(operation)) {
                    VConsole.log("Will update series : " + seriesName);
                    seriesToUpdateList.add(seriesUpdateUIDL);
                    seriesToUpdate.push(seriesName);
                }
            }
        }

        if (seriesToAdd.length() > 0) {
            setRedrawChart();
            JsArray<GwtSeriesDataOptions> uidlChartDataArr = getChartData(
                    uidlChartData, seriesToAdd);
            for (int ind = 0; ind < uidlChartDataArr.length(); ind++) {
                VConsole.log("Adding series "
                        + uidlChartDataArr.get(ind).getName());
                chart.addSeries(uidlChartDataArr.get(ind), false);
            }
        }
        if (seriesToUpdateList.size() > 0) {
            setRedrawChart();
            JsArray<GwtSeriesDataOptions> uidlChartDataArr = getChartData(
                    uidlChartData, seriesToUpdate);
            for (int ind = 0; ind < seriesToUpdateList.size(); ind++) {
                UIDL uidlSeriesToUpdate = seriesToUpdateList.get(ind);
                GwtSeriesDataOptions uidlSeriesDataOptions = uidlChartDataArr
                        .get(ind);
                GwtSeries chartSeries = chart.getSeries(uidlSeriesDataOptions
                        .getName());
                GwtSeriesGeneralOptions chartSeriesOptions = chartSeries
                        .getSeriesGeneralOptions();
                GwtSeriesGeneralOptions uidlSeriesOptions = uidlSeriesDataOptions
                        .getSeriesOptions();
                // Update visibility
                boolean isVisible = (uidlSeriesOptions != null ? uidlSeriesOptions
                        .isVisible() : true);
                chartSeriesOptions.setVisible(isVisible);
                if (chartSeriesOptions.isVisible() == true
                        && chartSeries.isVisible() == false) {
                    chartSeries.show();
                }
                if (chartSeriesOptions.isVisible() == false
                        && chartSeries.isVisible() == true) {
                    chartSeries.hide();
                }
                // Update points
                if (uidlSeriesToUpdate.getBooleanAttribute("isReloadPoints")) {
                    // update all points
                    VConsole.log("Reloading points for series : "
                            + uidlSeriesToUpdate.getStringAttribute("name"));
                    chartSeries.setDataAsPointOptions(
                            uidlSeriesDataOptions.getDataAsPointOptions(),
                            false);
                } else {
                    UIDL uidlPointsAdded = uidlSeriesToUpdate.getChildUIDL(0);
                    UIDL uidlPointsRemoved = uidlSeriesToUpdate.getChildUIDL(1);
                    updateSeriesData(chartSeries, uidlPointsAdded,
                            uidlPointsRemoved);
                }
            }
        }

        VConsole.log("Exit [updateChartData]");
    }

    private void updateSeriesData(GwtSeries chartSeries, UIDL uidlPointsAdded,
            UIDL uidlPointsRemoved) {
        VConsole.log("Enter [updateSeriesData]");
        if (uidlPointsAdded != null && uidlPointsAdded.getChildCount() > 0) {
            // Add points
            JsArray<GwtPointOptions> pointsTobeAdded = getSeriesPoints(uidlPointsAdded);
            VConsole.log("# of points to be added : "
                    + pointsTobeAdded.length());
            for (int cnt = 0; cnt < pointsTobeAdded.length(); cnt++) {
                GwtPointOptions pointOptions = pointsTobeAdded.get(cnt);
                chartSeries.addPoint(pointOptions, false,
                        pointOptions.isShift());
            }
        }

        if (uidlPointsRemoved != null && uidlPointsRemoved.getChildCount() > 0) {
            // Remove points
            JsArray<GwtPointOptions> pointsTobeRemoved = getSeriesPoints(uidlPointsRemoved);
            VConsole.log("# of points to be removed : "
                    + pointsTobeRemoved.length());
            JsArray<GwtPoint> chartSeriesData = chartSeries.getData();
            for (int cnt = 0; cnt < pointsTobeRemoved.length(); cnt++) {
                GwtPointOptions pointToRemove = pointsTobeRemoved.get(cnt);
                for (int chartPointCnt = 0; chartPointCnt < chartSeriesData
                        .length(); chartPointCnt++) {
                    GwtPoint chartSeriesPoint = chartSeriesData
                            .get(chartPointCnt);
                    // Using Double.compareTo(another Double) does not result in
                    // appr. code which can be executed in JS correctly.
                    // e.g. x.compareTo(y) results in compare(x.value, y.value)
                    // where x.value is undefined in JS,
                    // Don't know the reason yet but will figure out. So do a
                    // direct comparison
                    if (chartSeriesPoint.getX() == pointToRemove.getX()
                            && chartSeriesPoint.getY() == pointToRemove.getY()) {
                        VConsole.log("Removing point ("
                                + chartSeriesPoint.getX() + ", "
                                + chartSeriesPoint.getY() + ")");
                        chartSeriesPoint.remove();
                        break;
                    }
                }
            }
        }
        VConsole.log("Exit [updateSeriesData]");
    }

    private static enum SeriesCURType {
        ADD("Add"), UPDATE("Update"), REMOVE("Remove");
        private String name;

        private SeriesCURType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private JsArray<GwtSeriesDataOptions> getChartData(UIDL uidl) {
        return getChartData(uidl, null);
    }

    private boolean doesArrayContainSeriesName(
            JsArrayString namesOfSeriesToAdd, String seriesName) {
        for (int ind = 0; ind < namesOfSeriesToAdd.length(); ind++) {
            if (seriesName.equals(namesOfSeriesToAdd.get(ind))) {
                return true;
            }
        }
        return false;
    }

    private JsArray<GwtSeriesDataOptions> getChartData(UIDL uidl,
            JsArrayString namesOfSeriesToAdd) {
        VConsole.log("Enter [getChartData]");

        JsArray<GwtSeriesDataOptions> seriesDataArr = JavaScriptObject
                .createArray().cast();
        // Process each series data
        for (int cnt = 0; cnt < uidl.getChildCount(); cnt++) {
            GwtSeriesDataOptions seriesData = GwtSeriesDataOptions.create();
            UIDL seriesUIDL = uidl.getChildUIDL(cnt);
            String seriesName = seriesUIDL.getStringAttribute("name");
            if (seriesName != null && namesOfSeriesToAdd != null) {
                if (!doesArrayContainSeriesName(namesOfSeriesToAdd, seriesName)) {
                    continue;
                }
            }
            // From charts series data retrieve only those series data
            // whose names are specified in the second argument
            if (seriesUIDL.hasAttribute("name")) {
                // Setting name automatically sets series id which can later be
                // used to retrieve using chart.get(id);
                seriesData.setName(seriesName);
            }
            if (seriesUIDL.hasAttribute("stack")) {
                seriesData.setStack(seriesUIDL.getStringAttribute("stack"));
            }
            // FIXME - fallback on chart options type if series doesn't have a
            // type
            String seriesType = "line";
            if (seriesUIDL.hasAttribute("type")) {
                seriesType = seriesUIDL.getStringAttribute("type");
                seriesData.setType(seriesType);
            }
            if (seriesUIDL.hasAttribute("xAxis")) {
                seriesData.setXAxis(seriesUIDL.getIntAttribute("xAxis"));
            }
            if (seriesUIDL.hasAttribute("yAxis")) {
                seriesData.setYAxis(seriesUIDL.getIntAttribute("yAxis"));
            }
            // Get data/points
            seriesData.setDataAsPointOptions(getSeriesPoints(seriesUIDL
                    .getChildUIDL(1)));
            // Get series options
            GwtSeriesGeneralOptions seriesOptions = getSeriesOptions(
                    seriesType, seriesUIDL.getChildUIDL(0));
            if (seriesOptions != null) {
                seriesData.setSeriesOptions(seriesOptions);
            }

            seriesDataArr.push(seriesData);
        }

        VConsole.log("Exit [getChartData]");
        return seriesDataArr;
    }

    private JsArray<GwtPointOptions> getSeriesPoints(UIDL pointsUIDL) {
        VConsole.log("Enter [getSeriesPoints]");

        JsArray<GwtPointOptions> pointsArr = JavaScriptObject.createArray()
                .cast();
        for (int cnt = 0; cnt < pointsUIDL.getChildCount(); cnt++) {
            UIDL pointUIDL = pointsUIDL.getChildUIDL(cnt);
            GwtPointOptions pointOptions = GwtPointOptions.create();
            // If a point doesn't have any attributes then
            // consider it as a null since a user might want to represent
            // no activity graph
            if (pointUIDL.getAttributeNames().size() == 0) {
                pointOptions.setNullY();
            } else {
                if (pointUIDL.hasAttribute("id")) {
                    pointOptions.setId(pointUIDL.getStringAttribute("id"));
                }
                if (pointUIDL.hasAttribute("name")) {
                    pointOptions.setName(pointUIDL.getStringAttribute("name"));
                }
                if (pointUIDL.hasAttribute("color")) {
                    pointOptions
                            .setColor(pointUIDL.getStringAttribute("color"));
                }
                if (pointUIDL.hasAttribute("sliced")) {
                    pointOptions.setSliced(pointUIDL
                            .getBooleanAttribute("sliced"));
                }
                if (pointUIDL.hasAttribute("selected")) {
                    pointOptions.setSelected(pointUIDL
                            .getBooleanAttribute("selected"));
                }
                if (pointUIDL.hasAttribute("x")) {
                    pointOptions.setX(pointUIDL.getIntAttribute("x"));
                } else {
                    pointOptions.setNullX();
                }
                if (pointUIDL.hasAttribute("y")) {
                    pointOptions.setY(pointUIDL.getIntAttribute("y"));
                } else {
                    pointOptions.setNullY();
                }
                if (pointUIDL.hasAttribute("isShift")) {
                    pointOptions.setShift(pointUIDL
                            .getBooleanAttribute("isShift"));
                }
                GwtMarker markerOptions = getMarkerOptions(pointUIDL
                        .getChildUIDL(0));
                if (markerOptions != null) {
                    pointOptions.setMarker(markerOptions);
                }
            }
            pointsArr.push(pointOptions);
        }

        VConsole.log("Exit [getSeriesPoints]");
        return pointsArr;
    }

    private GwtInvientChartsConfig getInvientChartOptions(UIDL uidl) {
        VConsole.log("Enter [getInvientChartOptions]");
        VConsole.log("Child UIDL count : " + uidl.getChildCount());
        GwtInvientChartsConfig options = GwtInvientChartsConfig.create();
        // Get title UIDL
        VConsole.log("Getting title options...");
        // Title
        options.setTitleOptions(getTitleOptions(uidl
                .getChildUIDL(ChartOptionsUIDLIndex.TITLE.ordinal())));

        VConsole.log("Getting subtitle options...");
        // Subtitle
        options.setSubtitleOptions(getSubtitleOptions(uidl
                .getChildUIDL(ChartOptionsUIDLIndex.SUBTITLE.ordinal())));
        // Credit
        options.setCreditOptions(getCreditOptions(uidl
                .getChildUIDL(ChartOptionsUIDLIndex.CREDIT.ordinal())));
        // Legend
        options.setLegendOptions(getLegendOptions(uidl
                .getChildUIDL(ChartOptionsUIDLIndex.LEGEND.ordinal())));
        // Tooltip
        options.setTooltipOptions(getTooltipOptions(uidl
                .getChildUIDL(ChartOptionsUIDLIndex.TOOLTIP.ordinal())));

        // Then DEMO application
        VConsole.log("Getting chart options...");
        // Chart Options
        options.setChartOptions(getChartOptions(uidl
                .getChildUIDL(ChartOptionsUIDLIndex.CHART_CONFIG.ordinal())));

        VConsole.log("Getting plot options...");
        // Plot Options for various series types
        options.setPlotOptions(getPlotOptions(uidl
                .getChildUIDL(ChartOptionsUIDLIndex.SERIES_OPTIONS.ordinal())));

        VConsole.log("Getting x-axis options...");
        JsArray<GwtXAxisOptions> xAxisOptions = getXAxisOptions(uidl
                .getChildUIDL(ChartOptionsUIDLIndex.X_AXES.ordinal()));
        if (xAxisOptions.length() > 0) {
            options.setXAxesOptions(xAxisOptions);
        }

        VConsole.log("Getting y-axis options...");
        JsArray<GwtYAxisOptions> yAxisOptions = getYAxisOptions(uidl
                .getChildUIDL(ChartOptionsUIDLIndex.Y_AXES.ordinal()));
        if (yAxisOptions.length() > 0) {
            options.setYAxesOptions(yAxisOptions);
        }

        VConsole.log("Getting chart labels...");
        GwtChartLabels labels = getChartLabels(uidl
                .getChildUIDL(ChartOptionsUIDLIndex.LABEL.ordinal()));
        if (labels != null) {
            options.setLabels(labels);
        }

        VConsole.log("Exit [getInvientChartOptions]");
        return options;
    }

    private GwtChartLabels getChartLabels(UIDL uidl) {
        VConsole.log("Enter [getChartLabels]");
        VConsole.log("Tag name -> " + uidl.getTag());
        if ((uidl.getAttributeNames().size() == 0 && uidl.getChildCount() == 0)
                || (uidl.getAttributeNames().size() > 0 && uidl.getChildCount() == 0)) {
            VConsole.log("Exit [getChartLabels]");
            return null;
        }
        UIDL labelItemsUIDL = uidl.getChildUIDL(0);
        if (labelItemsUIDL.getChildCount() == 0) {
            VConsole.log("Exit [getChartLabels]");
            return null;
        }

        GwtChartLabels labels = GwtChartLabels.create();
        if (uidl.hasAttribute("style")) {
            labels.setStyle(uidl.getStringAttribute("style"));
        }

        JsArray<GwtChartLabelItem> chartLabelItemsArr = JavaScriptObject
                .createArray().cast();
        for (int cnt = 0; cnt < labelItemsUIDL.getChildCount(); cnt++) {
            UIDL labelItemUIDL = labelItemsUIDL.getChildUIDL(cnt);
            if (labelItemUIDL.hasAttribute("html")
                    || labelItemUIDL.hasAttribute("style")) {
                GwtChartLabelItem labelItem = GwtChartLabelItem.create();
                if (labelItemUIDL.hasAttribute("html")) {
                    labelItem.setHtml(labelItemUIDL.getStringAttribute("html"));
                }
                //
                if (labelItemUIDL.hasAttribute("style")) {
                    labelItem.setStyle(labelItemUIDL
                            .getStringAttribute("style"));
                }
                chartLabelItemsArr.push(labelItem);
            }
        }
        labels.setItems(chartLabelItemsArr);
        VConsole.log("Exit [getChartLabels]");
        return labels;
    }

    private GwtCreditOptions getCreditOptions(UIDL uidl) {
        VConsole.log("Enter [getCreditOptions]");
        VConsole.log("Tag name -> " + uidl.getTag());
        GwtCreditOptions creditOptions = GwtCreditOptions.create();

        if (uidl.hasAttribute("enabled")) {
            creditOptions.setEnabled(uidl.getBooleanAttribute("enabled"));
        }
        if (uidl.hasAttribute("href")) {
            creditOptions.setHref(uidl.getStringAttribute("href"));
        }
        if (uidl.hasAttribute("style")) {
            creditOptions.setStyle(uidl.getStringAttribute("style"));
        }
        if (uidl.hasAttribute("text")) {
            creditOptions.setText(uidl.getStringAttribute("text"));
        }
        UIDL positionUIDL = uidl.getChildUIDL(0);
        GwtPosition position = GwtPosition.create();
        if (positionUIDL.hasAttribute("align")) {
            position.setAlign(positionUIDL.getStringAttribute("align"));
        }
        if (positionUIDL.hasAttribute("verticalAlign")) {
            position.setVerticalAlign(positionUIDL
                    .getStringAttribute("verticalAlign"));
        }
        if (positionUIDL.hasAttribute("x")) {
            position.setX(positionUIDL.getIntAttribute("x"));
        }
        if (positionUIDL.hasAttribute("y")) {
            position.setY(positionUIDL.getIntAttribute("y"));
        }
        creditOptions.setPosition(position);

        VConsole.log("Exit [getCreditOptions]");
        return creditOptions;
    }

    private GwtLegendOptions getLegendOptions(UIDL uidl) {
        VConsole.log("Enter [getLegendOptions]");
        VConsole.log("Tag name -> " + uidl.getTag());

        GwtLegendOptions legendOptions = GwtLegendOptions.create();
        if (uidl.hasAttribute("align")) {
            legendOptions.setAlign(uidl.getStringAttribute("align"));
        }
        if (uidl.hasAttribute("backgroundColor")) {
            legendOptions.setBackgroundColor(uidl
                    .getStringAttribute("backgroundColor"));
        }
        if (uidl.hasAttribute("borderColor")) {
            legendOptions
                    .setBorderColor(uidl.getStringAttribute("borderColor"));
        }
        if (uidl.hasAttribute("borderRadius")) {
            legendOptions.setBorderRadius(uidl.getIntAttribute("borderRadius"));
        }
        if (uidl.hasAttribute("borderWidth")) {
            legendOptions.setBorderWidth(uidl.getIntAttribute("borderWidth"));
        }
        if (uidl.hasAttribute("enabled")) {
            legendOptions.setEnabled(uidl.getBooleanAttribute("enabled"));
        }
        if (uidl.hasAttribute("floating")) {
            legendOptions.setFloating(uidl.getBooleanAttribute("floating"));
        }
        if (uidl.hasAttribute("itemHiddenStyle")) {
            legendOptions.setItemHiddenStyle(uidl
                    .getStringAttribute("itemHiddenStyle"));
        }
        if (uidl.hasAttribute("itemHoverStyle")) {
            legendOptions.setItemHoverStyle(uidl
                    .getStringAttribute("itemHoverStyle"));
        }
        if (uidl.hasAttribute("itemStyle")) {
            legendOptions.setItemStyle(uidl.getStringAttribute("itemStyle"));
        }
        if (uidl.hasAttribute("itemWidth")) {
            legendOptions.setItemWidth(uidl.getIntAttribute("itemWidth"));
        }
        if (uidl.hasAttribute("layout")) {
            legendOptions.setLayout(uidl.getStringAttribute("layout"));
        }
        if (uidl.hasAttribute("labelFormatter")) {
            legendOptions.setLabelFormatter(getExecutableFunction(uidl
                    .getStringAttribute("labelFormatter")));
        }
        if (uidl.hasAttribute("margin")) {
            legendOptions.setMargin(uidl.getIntAttribute("margin"));
        }
        if (uidl.hasAttribute("reversed")) {
            legendOptions.setReversed(uidl.getBooleanAttribute("reversed"));
        }
        if (uidl.hasAttribute("shadow")) {
            legendOptions.setShadow(uidl.getBooleanAttribute("shadow"));
        }
        if (uidl.hasAttribute("symbolPadding")) {
            legendOptions.setSymbolPadding(uidl
                    .getIntAttribute("symbolPadding"));
        }
        if (uidl.hasAttribute("symbolWidth")) {
            legendOptions.setSymbolWidth(uidl.getIntAttribute("symbolWidth"));
        }
        if (uidl.hasAttribute("verticalAlign")) {
            legendOptions.setVerticalAlign(uidl
                    .getStringAttribute("verticalAlign"));
        }
        if (uidl.hasAttribute("width")) {
            legendOptions.setWidth(uidl.getIntAttribute("width"));
        }
        if (uidl.hasAttribute("x")) {
            legendOptions.setX(uidl.getIntAttribute("x"));
        }
        if (uidl.hasAttribute("y")) {
            legendOptions.setY(uidl.getIntAttribute("y"));
        }

        VConsole.log("Exit [getLegendOptions]");
        return legendOptions;
    }

    private GwtTooltipOptions getTooltipOptions(UIDL uidl) {
        VConsole.log("Enter [getTooltipOptions]");
        VConsole.log("Tag name -> " + uidl.getTag());
        GwtTooltipOptions tooltipOptions = GwtTooltipOptions.create();

        if (uidl.hasAttribute("backgroundColor")) {
            tooltipOptions.setBackgroundColor(uidl
                    .getStringAttribute("backgroundColor"));
        }
        if (uidl.hasAttribute("borderColor")) {
            tooltipOptions.setBorderColor(uidl
                    .getStringAttribute("borderColor"));
        }
        if (uidl.hasAttribute("borderRadius")) {
            tooltipOptions
                    .setBorderRadius(uidl.getIntAttribute("borderRadius"));
        }
        if (uidl.hasAttribute("borderWidth")) {
            tooltipOptions.setBorderWidth(uidl.getIntAttribute("borderWidth"));
        }
        if (uidl.hasAttribute("crosshairs")) {
            tooltipOptions
                    .setCrosshairs(uidl.getBooleanAttribute("crosshairs"));
        }
        if (uidl.hasAttribute("enabled")) {
            tooltipOptions.setEnabled(uidl.getBooleanAttribute("enabled"));
        }
        if (uidl.hasAttribute("formatter")) {
            tooltipOptions.setFormatter(getExecutableFunction(uidl
                    .getStringAttribute("formatter")));
        }
        if (uidl.hasAttribute("shadow")) {
            tooltipOptions.setShadow(uidl.getBooleanAttribute("shadow"));
        }
        if (uidl.hasAttribute("shared")) {
            tooltipOptions.setShared(uidl.getBooleanAttribute("shared"));
        }
        if (uidl.hasAttribute("snap")) {
            tooltipOptions.setSnap(uidl.getIntAttribute("snap"));
        }
        if (uidl.hasAttribute("style")) {
            tooltipOptions.setStyle(uidl.getStringAttribute("style"));
        }
		if (uidl.hasAttribute("useHTML")) {
			tooltipOptions.setUseHTML(uidl.getBooleanAttribute("useHTML"));
		}
		if (uidl.hasAttribute("headerFormat")) {
			tooltipOptions.setHeaderFormat(uidl.getStringAttribute("headerFormat"));
		}
		if (uidl.hasAttribute("pointFormat")) {
			tooltipOptions.setPointFormat(uidl.getStringAttribute("pointFormat"));
		}
		if (uidl.hasAttribute("footerFormat")) {
			tooltipOptions.setFooterFormat(uidl.getStringAttribute("footerFormat"));
		}

        VConsole.log("Exit [getTooltipOptions]");
        return tooltipOptions;
    }

    private GwtTitleOptions getTitleOptions(UIDL uidl) {
        VConsole.log("Enter [getTitleOptions]");
        VConsole.log("Tag Name : " + uidl.getTag());
        GwtTitleOptions titleOptions = GwtTitleOptions.createTitleOptions();
        updateTitleBaseOptions(uidl, titleOptions);

        if (uidl.hasAttribute("margin")) {
            titleOptions.setMargin(uidl.getIntAttribute("margin"));
        }

        VConsole.log("Exit [getTitleOptions]");
        return titleOptions;
    }

    private GwtSubtitleOptions getSubtitleOptions(UIDL uidl) {
        VConsole.log("Enter [getSubtitleOptions]");
        VConsole.log("Tag Name : " + uidl.getTag());
        GwtSubtitleOptions subtitleOptions = GwtSubtitleOptions
                .createSubtitleOptions();
        updateTitleBaseOptions(uidl, subtitleOptions);
        VConsole.log("Exit [getTitleOptions]");
        return subtitleOptions;
    }

    private void updateTitleBaseOptions(UIDL uidl,
            GwtTitleBaseOptions titleBaseOptions) {
        VConsole.log("Enter [updateTitleBaseOptions]");
        VConsole.log("Tag Name : " + uidl.getTag());
        if (uidl.hasAttribute("text")) {
            titleBaseOptions.setText(uidl.getStringAttribute("text"));
        }
        if (uidl.hasAttribute("align")) {
            titleBaseOptions.setAlign(uidl.getStringAttribute("align"));
        }
        if (uidl.hasAttribute("floating")) {
            titleBaseOptions.setFloating(uidl.getBooleanAttribute("floating"));
        }
        if (uidl.hasAttribute("style")) {
            titleBaseOptions.setStyle(uidl.getStringAttribute("style"));
        }
        if (uidl.hasAttribute("verticalAlign")) {
            titleBaseOptions.setVerticalAlign(uidl
                    .getStringAttribute("verticalAlign"));
        }
        if (uidl.hasAttribute("x")) {
            titleBaseOptions.setX(uidl.getIntAttribute("x"));
        }
        if (uidl.hasAttribute("y")) {
            titleBaseOptions.setY(uidl.getIntAttribute("y"));
        }

        VConsole.log("Exit [updateTitleBaseOptions]");
    }

    private GwtChartOptions getChartOptions(UIDL uidl) {
        VConsole.log("Enter [getChartOptions]");
        VConsole.log("Tag Name : " + uidl.getTag());

        GwtChartOptions chartOptions = GwtChartOptions.create();
        // DIV - A container for the InvientChart
        chartOptions.setRenderTo(super.divId);

        if (uidl.hasAttribute("type")) {
            chartOptions.setType(uidl.getStringAttribute("type"));
        }
        if (uidl.hasAttribute("width")) {
            chartOptions.setWidth(uidl.getIntAttribute("width"));
        }
        if (uidl.hasAttribute("height")) {
            chartOptions.setHeight(uidl.getIntAttribute("height"));
        }
        if (uidl.hasAttribute("backgroundColor")) {
            chartOptions.setBackgroundColor(uidl
                    .getStringAttribute("backgroundColor"));
        }
        if (uidl.hasAttribute("borderColor")) {
            chartOptions.setBorderColor(uidl.getStringAttribute("borderColor"));
        }
        if (uidl.hasAttribute("borderRadius")) {
            chartOptions.setBorderRadius(uidl.getIntAttribute("borderRadius"));
        }
        if (uidl.hasAttribute("borderWidth")) {
            chartOptions.setBorderWidth(uidl.getIntAttribute("borderWidth"));
        }
        if (uidl.hasAttribute("ignoreHiddenSeries")) {
            chartOptions.setIgnoreHiddenSeries(uidl
                    .getBooleanAttribute("ignoreHiddenSeries"));
        }
        if (uidl.hasAttribute("inverted")) {
            chartOptions.setInverted(uidl.getBooleanAttribute("inverted"));
        }
        if (uidl.hasAttribute("marginTop")) {
            chartOptions.setMarginTop(uidl.getIntAttribute("marginTop"));
        }
        if (uidl.hasAttribute("marginLeft")) {
            chartOptions.setMarginLeft(uidl.getIntAttribute("marginLeft"));
        }
        if (uidl.hasAttribute("marginRight")) {
            chartOptions.setMarginRight(uidl.getIntAttribute("marginRight"));
        }
        if (uidl.hasAttribute("marginBottom")) {
            chartOptions.setMarginBottom(uidl.getIntAttribute("marginBottom"));
        }
        if (uidl.hasAttribute("spacingTop")) {
            chartOptions.setSpacingTop(uidl.getIntAttribute("spacingTop"));
        }
        if (uidl.hasAttribute("spacingLeft")) {
            chartOptions.setSpacingLeft(uidl.getIntAttribute("spacingLeft"));
        }
        if (uidl.hasAttribute("spacingRight")) {
            chartOptions.setSpacingRight(uidl.getIntAttribute("spacingRight"));
        }
        if (uidl.hasAttribute("spacingBottom")) {
            chartOptions
                    .setSpacingBottom(uidl.getIntAttribute("spacingBottom"));
        }
        if (uidl.hasAttribute("showAxes")) {
            chartOptions.setShowAxes(uidl.getBooleanAttribute("showAxes"));
        }
        if (uidl.hasAttribute("zoomType")) {
            chartOptions.setZoomType(uidl.getStringAttribute("zoomType"));
        }
        if (uidl.hasAttribute("clientZoom")) {
            chartOptions.setClientZoom(uidl.getBooleanAttribute("clientZoom"));
        }

        if (uidl.hasAttribute("alignTicks")) {
            chartOptions.setAlignTicks(uidl.getBooleanAttribute("alignTicks"));
        }
        if (uidl.hasAttribute("animation")) {
            chartOptions.setAnimation(uidl.getBooleanAttribute("animation"));
        }
        if (uidl.hasAttribute("className")) {
            chartOptions.setClassName(uidl.getStringAttribute("className"));
        }
        if (uidl.hasAttribute("plotBackgroundColor")) {
            chartOptions.setPlotBackgroundColor(uidl
                    .getStringAttribute("plotBackgroundColor"));
        }
        if (uidl.hasAttribute("plotBorderColor")) {
            chartOptions.setPlotBorderColor(uidl
                    .getStringAttribute("plotBorderColor"));
        }
        if (uidl.hasAttribute("plotBackgroundImage")) {
            chartOptions.setPlotBackgroundImage(uidl
                    .getStringAttribute("plotBackgroundImage"));
        }
        if (uidl.hasAttribute("plotBorderWidth")) {
            chartOptions.setPlotBorderWidth(uidl
                    .getIntAttribute("plotBorderWidth"));
        }
        if (uidl.hasAttribute("plotShadow")) {
            chartOptions.setPlotShadow(uidl.getBooleanAttribute("plotShadow"));
        }
        if (uidl.hasAttribute("reflow")) {
            chartOptions.setReflow(uidl.getBooleanAttribute("reflow"));
        }
        if (uidl.hasAttribute("shadow")) {
            chartOptions.setShadow(uidl.getBooleanAttribute("shadow"));
        }
        if (uidl.hasAttribute("style")) {
            chartOptions.setStyle(uidl.getStringAttribute("style"));
        }

        VConsole.log("Exit [getChartOptions]");
        return chartOptions;
    }

    private void updateBaseAxisOptions(UIDL axisUIDL,
            GwtAxisBaseOptions axisBaseOptions) {
        VConsole.log("Enter [updateBaseAxisOptions]");
        if (axisUIDL.hasAttribute("id")) {
            axisBaseOptions.setId(axisUIDL.getStringAttribute("id"));
        }
        if (axisUIDL.hasAttribute("allowDecimals")) {
            axisBaseOptions.setAllowDecimals(axisUIDL
                    .getBooleanAttribute("allowDecimals"));
        }
        if (axisUIDL.hasAttribute("alternateGridColor")) {
            axisBaseOptions.setAlternateGridColor(axisUIDL
                    .getStringAttribute("alternateGridColor"));
        }
        if (axisUIDL.hasAttribute("endOnTick")) {
            axisBaseOptions.setEndOnTick(axisUIDL
                    .getBooleanAttribute("endOnTick"));
        }
        // Grid
        if (axisUIDL.hasAttribute("gridLineColor")) {
            axisBaseOptions.setGridLineColor(axisUIDL
                    .getStringAttribute("gridLineColor"));
        }
        if (axisUIDL.hasAttribute("gridLineWidth")) {
            axisBaseOptions.setGridLineWidth(axisUIDL
                    .getIntAttribute("gridLineWidth"));
        }
        if (axisUIDL.hasAttribute("gridLineDashStyle")) {
            axisBaseOptions.setGridLineDashStyle(axisUIDL
                    .getStringAttribute("gridLineDashStyle"));
        }
        // Line
        if (axisUIDL.hasAttribute("lineColor")) {
            axisBaseOptions.setLineColor(axisUIDL
                    .getStringAttribute("lineColor"));
        }
        if (axisUIDL.hasAttribute("lineWidth")) {
            axisBaseOptions.setLineWidth(axisUIDL.getIntAttribute("lineWidth"));
        }
        //
        if (axisUIDL.hasAttribute("linkedTo")) {
            axisBaseOptions.setLinkedTo(axisUIDL.getIntAttribute("linkedTo"));
        }

        if (axisUIDL.hasAttribute("max")) {
            axisBaseOptions.setMax(axisUIDL.getDoubleAttribute("max"));
        }
        if (axisUIDL.hasAttribute("maxPadding")) {
            axisBaseOptions.setMaxPadding(axisUIDL
                    .getDoubleAttribute("maxPadding"));
        }
        if (axisUIDL.hasAttribute("maxZoom")) {
            axisBaseOptions.setMaxZoom(axisUIDL.getIntAttribute("maxZoom"));
        }

        //
        if (axisUIDL.hasAttribute("min")) {
            axisBaseOptions.setMin(axisUIDL.getDoubleAttribute("min"));
        }
        if (axisUIDL.hasAttribute("minPadding")) {
            axisBaseOptions.setMinPadding(axisUIDL
                    .getDoubleAttribute("minPadding"));
        }
        // Minor Grid
        if (axisUIDL.hasAttribute("minorGridLineColor")) {
            axisBaseOptions.setMinorGridLineColor(axisUIDL
                    .getStringAttribute("minorGridLineColor"));
        }
        if (axisUIDL.hasAttribute("minorGridLineWidth")) {
            axisBaseOptions.setMinorGridLineWidth(axisUIDL
                    .getIntAttribute("minorGridLineWidth"));
        }
        if (axisUIDL.hasAttribute("minorGridLineDashStyle")) {
            axisBaseOptions.setMinorGridLineDashStyle(axisUIDL
                    .getStringAttribute("minorGridLineDashStyle"));
        }
        // Minor Ticks
        if (axisUIDL.hasAttribute("minorTickColor")) {
            axisBaseOptions.setMinorTickColor(axisUIDL
                    .getStringAttribute("minorTickColor"));
        }
        if (axisUIDL.hasAttribute("minorTickInterval")) {
            axisBaseOptions.setMinorTickInterval(axisUIDL
                    .getDoubleAttribute("minorTickInterval"));
        }
        if (axisUIDL.hasAttribute("minorTickLength")) {
            axisBaseOptions.setMinorTickLength(axisUIDL
                    .getIntAttribute("minorTickLength"));
        }
        if (axisUIDL.hasAttribute("minorTickPosition")) {
            axisBaseOptions.setMinorTickPosition(axisUIDL
                    .getStringAttribute("minorTickPosition"));
        }
        if (axisUIDL.hasAttribute("minorTickWidth")) {
            axisBaseOptions.setMinorTickWidth(axisUIDL
                    .getIntAttribute("minorTickWidth"));
        }
        //
        if (axisUIDL.hasAttribute("offset")) {
            axisBaseOptions.setOffset(axisUIDL.getIntAttribute("offset"));
        }
        if (axisUIDL.hasAttribute("opposite")) {
            axisBaseOptions.setOpposite(axisUIDL
                    .getBooleanAttribute("opposite"));
        }
        if (axisUIDL.hasAttribute("reversed")) {
            axisBaseOptions.setReversed(axisUIDL
                    .getBooleanAttribute("reversed"));
        }
        if (axisUIDL.hasAttribute("showFirstLabel")) {
            axisBaseOptions.setShowFirstLabel(axisUIDL
                    .getBooleanAttribute("showFirstLabel"));
        }
        if (axisUIDL.hasAttribute("showLastLabel")) {
            axisBaseOptions.setShowLastLabel(axisUIDL
                    .getBooleanAttribute("showLastLabel"));
        }
        if (axisUIDL.hasAttribute("startOfWeek")) {
            axisBaseOptions.setStartOfWeek(axisUIDL
                    .getIntAttribute("startOfWeek"));
        }
        if (axisUIDL.hasAttribute("startOnTick")) {
            axisBaseOptions.setStartOnTick(axisUIDL
                    .getBooleanAttribute("startOnTick"));
        }
        // Tick
        if (axisUIDL.hasAttribute("tickColor")) {
            axisBaseOptions.setTickColor(axisUIDL
                    .getStringAttribute("tickColor"));
        }
        if (axisUIDL.hasAttribute("tickInterval")) {
            axisBaseOptions.setTickInterval(axisUIDL
                    .getDoubleAttribute("tickInterval"));
        }
        if (axisUIDL.hasAttribute("tickLength")) {
            axisBaseOptions.setTickLength(axisUIDL
                    .getIntAttribute("tickLength"));
        }
        if (axisUIDL.hasAttribute("tickPosition")) {
            axisBaseOptions.setTickPosition(axisUIDL
                    .getStringAttribute("tickPosition"));
        }
        if (axisUIDL.hasAttribute("tickWidth")) {
            axisBaseOptions.setTickWidth(axisUIDL.getIntAttribute("tickWidth"));
        }
        if (axisUIDL.hasAttribute("tickPixelInterval")) {
            axisBaseOptions.setTickPixelInterval(axisUIDL
                    .getIntAttribute("tickPixelInterval"));
        }
        if (axisUIDL.hasAttribute("tickmarkPlacement")) {
            axisBaseOptions.setTickmarkPlacement(axisUIDL
                    .getStringAttribute("tickmarkPlacement"));
        }

        if (axisUIDL.hasAttribute("type")) {
            axisBaseOptions.setType(axisUIDL.getStringAttribute("type"));
        }

        // title
        UIDL titleUIDL = axisUIDL.getChildUIDL(0);
        GwtAxisTitleOptions titleOptions = getAxisTitleOptions(titleUIDL);
        if (titleOptions != null) {
            axisBaseOptions.setTitle(titleOptions);
        }

        // label
        UIDL labelUIDL = axisUIDL.getChildUIDL(1);
        String axisName = axisUIDL.getTag();
        GwtAxisDataLabels axisDataLabels = getAxisDataLabels(labelUIDL,
                axisName);
        if (axisDataLabels != null) {
            axisBaseOptions.setLabels(axisDataLabels);
        }
        // plotband
        UIDL plotBandsUIDL = axisUIDL.getChildUIDL(2);
        JsArray<GwtPlotBands> plotBands = getPlotBands(plotBandsUIDL);
        if (plotBands.length() > 0) {
            axisBaseOptions.setPlotBands(plotBands);
        }
        // plotline
        UIDL plotLinesUIDL = axisUIDL.getChildUIDL(3);
        JsArray<GwtPlotLines> plotLines = getPlotLines(plotLinesUIDL);
        if (plotLines.length() > 0) {
            axisBaseOptions.setPlotLines(plotLines);
        }
        VConsole.log("Exit [updateBaseAxisOptions]");
    }

    private GwtAxisTitleOptions getAxisTitleOptions(UIDL axisTitleUIDL) {
        if (axisTitleUIDL == null
                || axisTitleUIDL.getAttributeNames().size() == 0) {
            return null;
        }
        GwtAxisTitleOptions titleOptions = GwtAxisTitleOptions.create();
        if (axisTitleUIDL.hasAttribute("align")) {
            titleOptions.setAlign(axisTitleUIDL.getStringAttribute("align"));
        }
        if (axisTitleUIDL.hasAttribute("margin")) {
            titleOptions.setMargin(axisTitleUIDL.getIntAttribute("margin"));
        }
        if (axisTitleUIDL.hasAttribute("rotation")) {
            titleOptions.setRotation(axisTitleUIDL.getIntAttribute("rotation"));
        }
        if (axisTitleUIDL.hasAttribute("style")) {
            titleOptions.setStyle(axisTitleUIDL.getStringAttribute("style"));
        }
        if (axisTitleUIDL.hasAttribute("text")) {
            titleOptions.setText(axisTitleUIDL.getStringAttribute("text"));
        }
        return titleOptions;
    }

    private JsArray<GwtPlotBands> getPlotBands(UIDL plotBandsUIDL) {
        JsArray<GwtPlotBands> plotBandsArr = JavaScriptObject.createArray()
                .cast();
        for (int cnt = 0; cnt < plotBandsUIDL.getChildCount(); cnt++) {
            UIDL plotBandUIDL = plotBandsUIDL.getChildUIDL(cnt);
            if (plotBandUIDL.getAttributeNames().size() == 0
                    && plotBandUIDL.getChildCount() == 0) {
                continue;
            }
            GwtPlotBands plotBands = GwtPlotBands.create();
            if (plotBandUIDL.hasAttribute("color")) {
                plotBands.setColor(plotBandUIDL.getStringAttribute("color"));
            }
            if (plotBandUIDL.hasAttribute("id")) {
                plotBands.setId(plotBandUIDL.getStringAttribute("id"));
            }
            if (plotBandUIDL.hasAttribute("zIndex")) {
                plotBands.setZIndex(plotBandUIDL.getIntAttribute("zIndex"));
            }
            // label
            GwtPlotLabel label = getPlotLabel(plotBandUIDL.getChildUIDL(0));
            if (label != null) {
                plotBands.setLabel(label);
            }
            // from/to value
            UIDL valueUIDL = plotBandUIDL.getChildUIDL(1);
            if (valueUIDL.hasAttribute("valueType")) {
                String valueType = valueUIDL.getStringAttribute("valueType");
                if (valueType.equals("number")) {
                    plotBands.setFrom(valueUIDL.getDoubleAttribute("from"));
                    plotBands.setTo(valueUIDL.getDoubleAttribute("to"));
                } else { // date
                    // from
                    UIDL fromUIDL = valueUIDL.getChildUIDL(0);
                    int fromYear = fromUIDL.getIntAttribute("year");
                    int fromMonth = fromUIDL.getIntAttribute("month");
                    int fromDay = fromUIDL.getIntAttribute("day");
                    plotBands.setFrom("Date.UTC(" + fromYear + ", " + fromMonth
                            + "," + fromDay + ")");
                    // to
                    UIDL toUIDL = valueUIDL.getChildUIDL(1);
                    int toYear = toUIDL.getIntAttribute("year");
                    int toMonth = toUIDL.getIntAttribute("month");
                    int toDay = toUIDL.getIntAttribute("day");
                    plotBands.setTo("Date.UTC(" + toYear + ", " + toMonth + ","
                            + toDay + ")");
                }
            }
            //
            plotBandsArr.push(plotBands);
        }
        return plotBandsArr;
    }

    private JsArray<GwtPlotLines> getPlotLines(UIDL plotLinesUIDL) {
        JsArray<GwtPlotLines> plotLinesArr = JavaScriptObject.createArray()
                .cast();
        for (int cnt = 0; cnt < plotLinesUIDL.getChildCount(); cnt++) {
            UIDL plotLineUIDL = plotLinesUIDL.getChildUIDL(cnt);
            if (plotLineUIDL.getAttributeNames().size() == 0
                    && plotLineUIDL.getChildCount() == 0) {
                continue;
            }
            GwtPlotLines plotLines = GwtPlotLines.create();
            if (plotLineUIDL.hasAttribute("color")) {
                plotLines.setColor(plotLineUIDL.getStringAttribute("color"));
            }
            if (plotLineUIDL.hasAttribute("dashStyle")) {
                plotLines.setDashStyle(plotLineUIDL
                        .getStringAttribute("dashStyle"));
            }
            if (plotLineUIDL.hasAttribute("id")) {
                plotLines.setId(plotLineUIDL.getStringAttribute("id"));
            }
            if (plotLineUIDL.hasAttribute("width")) {
                plotLines.setWidth(plotLineUIDL.getIntAttribute("width"));
            }
            if (plotLineUIDL.hasAttribute("zIndex")) {
                plotLines.setZIndex(plotLineUIDL.getIntAttribute("zIndex"));
            }
            // label
            GwtPlotLabel label = getPlotLabel(plotLineUIDL.getChildUIDL(0));
            if (label != null) {
                plotLines.setLabel(label);
            }
            // line value
            UIDL lineValueUIDL = plotLineUIDL.getChildUIDL(1);
            if (lineValueUIDL.hasAttribute("valueType")) {
                String valueType = lineValueUIDL
                        .getStringAttribute("valueType");
                if (valueType.equals("number")) {
                    if (lineValueUIDL.hasAttribute("value")) {
                        plotLines.setValue(lineValueUIDL
                                .getDoubleAttribute("value"));
                    }
                } else { // date
                    int year = lineValueUIDL.getIntAttribute("year");
                    int month = lineValueUIDL.getIntAttribute("month");
                    int day = lineValueUIDL.getIntAttribute("day");
                    plotLines.setValue("Date.UTC(" + year + ", " + month + ","
                            + day + ")");
                }
            }
            //
            plotLinesArr.push(plotLines);
        }
        return plotLinesArr;
    }

    private GwtPlotLabel getPlotLabel(UIDL plotLabelUIDL) {
        if (plotLabelUIDL == null
                || plotLabelUIDL.getAttributeNames().size() == 0) {
            return null;
        }
        GwtPlotLabel label = GwtPlotLabel.create();
        if (plotLabelUIDL.hasAttribute("align")) {
            label.setAlign(plotLabelUIDL.getStringAttribute("align"));
        }
        if (plotLabelUIDL.hasAttribute("rotation")) {
            label.setRotation(plotLabelUIDL.getIntAttribute("rotation"));
        }
        if (plotLabelUIDL.hasAttribute("style")) {
            label.setStyle(plotLabelUIDL.getStringAttribute("style"));
        }
        if (plotLabelUIDL.hasAttribute("align")) {
            label.setAlign(plotLabelUIDL.getStringAttribute("align"));
        }
        if (plotLabelUIDL.hasAttribute("text")) {
            label.setText(plotLabelUIDL.getStringAttribute("text"));
        }
        if (plotLabelUIDL.hasAttribute("verticalAlign")) {
            label.setVerticalAlign(plotLabelUIDL
                    .getStringAttribute("verticalAlign"));
        }
        if (plotLabelUIDL.hasAttribute("x")) {
            label.setX(plotLabelUIDL.getIntAttribute("x"));
        }
        if (plotLabelUIDL.hasAttribute("y")) {
            label.setY(plotLabelUIDL.getIntAttribute("y"));
        }

        return label;
    }

    // FIXME - Code organization
    private GwtAxisDataLabels getAxisDataLabels(UIDL labelUIDL, String axisName) {
        if (labelUIDL == null || labelUIDL.getAttributeNames().size() == 0) {
            return null;
        }
        if (axisName.equals("xAxis")) {
            GwtXAxisDataLabels labels = GwtXAxisDataLabels.createXAxisLabels();
            updateDataLabel(labelUIDL, labels);
            if (labelUIDL.hasAttribute("staggerLines")) {
                labels.setStaggerLines(labelUIDL
                        .getIntAttribute("staggerLines"));
            }
            if (labelUIDL.hasAttribute("step")) {
                labels.setStep(labelUIDL.getIntAttribute("step"));
            }
            return labels;
        } else {
            GwtYAxisDataLabels labels = GwtYAxisDataLabels.createYAxisLabels();
            updateDataLabel(labelUIDL, labels);
            return labels;
        }
    }

    private JsArray<GwtXAxisOptions> getXAxisOptions(UIDL uidl) {
        VConsole.log("Enter [getXAxisOptions]");
        VConsole.log("Tag Name : " + uidl.getTag());
        JsArray<GwtXAxisOptions> xAxes = JavaScriptObject.createArray().cast();

        for (int cnt = 0; cnt < uidl.getChildCount(); cnt++) {
            GwtXAxisOptions xAxisOptions = GwtXAxisOptions.create();
            UIDL axisUIDL = uidl.getChildUIDL(cnt);
            if (axisUIDL.getAttributeNames().size() == 0
                    && axisUIDL.getChildCount() == 0) {
                continue;
            }
            updateBaseAxisOptions(axisUIDL, xAxisOptions);

            UIDL childUIDL = axisUIDL.getChildUIDL(4);
            if (childUIDL != null) {
                if (childUIDL.getTag().equals("categories")
                        && childUIDL.getChildCount() > 0) {
                    JsArrayString categories = JavaScriptObject.createArray()
                            .cast();
                    UIDL categoriesUIDL = childUIDL;
                    for (int idx = 0; idx < categoriesUIDL.getChildCount(); idx++) {
                        categories.push(categoriesUIDL.getChildUIDL(idx)
                                .getStringAttribute("name"));
                    }
                    xAxisOptions.setCategories(categories);
                } else if (childUIDL.getTag().equals("dateTimeLabelFormats")
                        && childUIDL.getAttributeNames().size() > 0) {
                    UIDL dateTimeLblFmtsUIDL = childUIDL;
                    GwtDateTimeLabelFormats formats = GwtDateTimeLabelFormats
                            .create();
                    if (dateTimeLblFmtsUIDL.hasAttribute("second")) {
                        formats.setSecond(dateTimeLblFmtsUIDL
                                .getStringAttribute("second"));
                    }
                    if (dateTimeLblFmtsUIDL.hasAttribute("minute")) {
                        formats.setMinute(dateTimeLblFmtsUIDL
                                .getStringAttribute("minute"));
                    }
                    if (dateTimeLblFmtsUIDL.hasAttribute("hour")) {
                        formats.setHour(dateTimeLblFmtsUIDL
                                .getStringAttribute("hour"));
                    }
                    if (dateTimeLblFmtsUIDL.hasAttribute("day")) {
                        formats.setDay(dateTimeLblFmtsUIDL
                                .getStringAttribute("day"));
                    }
                    if (dateTimeLblFmtsUIDL.hasAttribute("week")) {
                        formats.setWeek(dateTimeLblFmtsUIDL
                                .getStringAttribute("week"));
                    }
                    if (dateTimeLblFmtsUIDL.hasAttribute("month")) {
                        formats.setMonth(dateTimeLblFmtsUIDL
                                .getStringAttribute("month"));
                    }
                    if (dateTimeLblFmtsUIDL.hasAttribute("year")) {
                        formats.setYear(dateTimeLblFmtsUIDL
                                .getStringAttribute("year"));
                    }
                    xAxisOptions.setDateTimeLabelFormats(formats);
                }
            }
            xAxes.push(xAxisOptions);
        }

        VConsole.log("Exit [getXAxisOptions]");
        return xAxes;
    }

    private JsArray<GwtYAxisOptions> getYAxisOptions(UIDL uidl) {
        VConsole.log("Enter [getYAxisOptions]");
        VConsole.log("Tag Name : " + uidl.getTag());
        JsArray<GwtYAxisOptions> yAxes = JavaScriptObject.createArray().cast();

        for (int cnt = 0; cnt < uidl.getChildCount(); cnt++) {
            GwtYAxisOptions yAxisOptions = GwtYAxisOptions.create();
            UIDL axisUIDL = uidl.getChildUIDL(cnt);
            if (axisUIDL.getAttributeNames().size() == 0
                    && axisUIDL.getChildCount() == 0) {
                continue;
            }
            updateBaseAxisOptions(axisUIDL, yAxisOptions);
            yAxes.push(yAxisOptions);
        }

        VConsole.log("Exit [getYAxisOptions]");
        return yAxes;
    }

    private GwtPlotOptions getPlotOptions(UIDL uidl) {
        VConsole.log("Enter [getPlotOptions]");
        VConsole.log("Tag Name : " + uidl.getTag());

        GwtPlotOptions plotOptions = GwtPlotOptions.create();

        for (int cnt = 0; cnt < uidl.getChildCount(); cnt++) {
            UIDL seriesUIDL = uidl.getChildUIDL(cnt);
            String seriesType = seriesUIDL.getTag();
            VConsole.log("Series Type : " + seriesType);
            GwtSeriesGeneralOptions seriesOptions = getSeriesOptions(
                    seriesType, seriesUIDL);
            if (seriesOptions == null) {
                continue;
            }
            if (seriesType.equals("series")) {
                plotOptions.setSeries(seriesOptions);
            } else if (seriesType.equals("line")) {
                plotOptions.setLine((GwtLineOptions) seriesOptions);
            } else if (seriesType.equals("scatter")) {
                plotOptions.setScatter((GwtScatterOptions) seriesOptions);
            } else if (seriesType.equals("spline")) {
                plotOptions.setSpline((GwtSplineOptions) seriesOptions);
            } else if (seriesType.equals("area")) {
                plotOptions.setArea((GwtAreaOptions) seriesOptions);
            } else if (seriesType.equals("areaspline")) {
                plotOptions.setAreaSpline((GwtAreaSplineOptions) seriesOptions);
            } else if (seriesType.equals("bar")) {
                plotOptions.setBar((GwtBarOptions) seriesOptions);
            } else if (seriesType.equals("column")) {
                plotOptions.setColumn((GwtColumnOptions) seriesOptions);
            } else if (seriesType.equals("pie")) {
                plotOptions.setPie((GwtPieOptions) seriesOptions);
            }
        }

        VConsole.log("Exit [getPlotOptions]");
        return plotOptions;
    }

    private GwtSeriesGeneralOptions getSeriesOptions(String seriesType,
            UIDL seriesUIDL) {
        VConsole.log("Enter [getSeriesOptions]");
        VConsole.log("Tag Name : " + seriesUIDL.getTag());
        if (seriesUIDL.getAttributeNames().size() == 0
                && seriesUIDL.getChildCount() == 0) {
            VConsole.log("No attributes/children found for series type : "
                    + seriesType);
            VConsole.log("Exit [getSeriesOptions]");
            return null;
        }
        GwtSeriesGeneralOptions seriesOptions = null;
        if (seriesType.equals("series")) {
            seriesOptions = GwtSeriesGeneralOptions.createSeriesOptions();
            updateSeriesOptions(seriesUIDL, seriesOptions);
        } else if (seriesType.equals("line")) {
            seriesOptions = GwtLineOptions.createLineOptions();
            updateLineOptions(seriesUIDL, (GwtLineOptions) seriesOptions);
        } else if (seriesType.equals("scatter")) {
            seriesOptions = GwtScatterOptions.createScatterOptions();
            updateScatterOptions(seriesUIDL, (GwtScatterOptions) seriesOptions);
        } else if (seriesType.equals("spline")) {
            seriesOptions = GwtSplineOptions.createSplineOptions();
            updateSplineOptions(seriesUIDL, (GwtSplineOptions) seriesOptions);
        } else if (seriesType.equals("area")) {
            seriesOptions = GwtAreaOptions.createAreaOptions();
            updateAreaOptions(seriesUIDL, (GwtAreaOptions) seriesOptions);
        } else if (seriesType.equals("areaspline")) {
            seriesOptions = GwtAreaSplineOptions.createAreaSplineOptions();
            updateAreaSplineOptions(seriesUIDL,
                    (GwtAreaSplineOptions) seriesOptions);
        } else if (seriesType.equals("bar")) {
            seriesOptions = GwtBarOptions.createBarOptions();
            updateBarOptions(seriesUIDL, (GwtBarOptions) seriesOptions);
        } else if (seriesType.equals("column")) {
            seriesOptions = GwtColumnOptions.createColumnOptions();
            updateColumnOptions(seriesUIDL, (GwtColumnOptions) seriesOptions);
        } else if (seriesType.equals("pie")) {
            seriesOptions = GwtPieOptions.createPieOptions();
            updatePieOptions(seriesUIDL, (GwtPieOptions) seriesOptions);
        } else {
            // This should not happen
            VConsole.log("[getSeriesOptions] : Invalid series type "
                    + seriesType);
        }
        VConsole.log("Exit [getSeriesOptions]");
        return seriesOptions;
    }

    private void updateSeriesOptions(UIDL seriesUIDL,
            GwtSeriesGeneralOptions seriesOptions) {
        VConsole.log("Enter [updateSeriesOptions]");
        VConsole.log("Tag Name : " + seriesUIDL.getTag());

        if (seriesUIDL.hasAttribute("allowPointSelect")) {
            seriesOptions.setAllowPointSelect(seriesUIDL
                    .getBooleanAttribute("allowPointSelect"));
        }
        if (seriesUIDL.hasAttribute("animation")) {
            seriesOptions.setAnimation(seriesUIDL
                    .getBooleanAttribute("animation"));
        }
        if (seriesUIDL.hasAttribute("cursor")) {
            seriesOptions.setCursor(seriesUIDL.getStringAttribute("cursor"));
        }
        if (seriesUIDL.hasAttribute("enableMouseTracking")) {
            seriesOptions.setEnableMouseTracking(seriesUIDL
                    .getBooleanAttribute("enableMouseTracking"));
        }
        if (seriesUIDL.hasAttribute("selected")) {
            seriesOptions.setSelected(seriesUIDL
                    .getBooleanAttribute("selected"));
        }
        if (seriesUIDL.hasAttribute("shadow")) {
            seriesOptions.setShadow(seriesUIDL.getBooleanAttribute("shadow"));
        }
        if (seriesUIDL.hasAttribute("showCheckbox")) {
            seriesOptions.setShowCheckbox(seriesUIDL
                    .getBooleanAttribute("showCheckbox"));
        }
        if (seriesUIDL.hasAttribute("showInLegend")) {
            seriesOptions.setShowInLegend(seriesUIDL
                    .getBooleanAttribute("showInLegend"));
        }
        if (seriesUIDL.hasAttribute("stacking")) {
            seriesOptions
                    .setStacking(seriesUIDL.getStringAttribute("stacking"));
        }
        if (seriesUIDL.hasAttribute("visible")) {
            seriesOptions.setVisible(seriesUIDL.getBooleanAttribute("visible"));
        }
        if (seriesUIDL.hasAttribute("color")) {
            seriesOptions.setColor(seriesUIDL.getStringAttribute("color"));
        }
        // FIXME - How to get series type
        // datalabels
        GwtDataLabels dataLabels = getSeriesDataLabel(
                seriesUIDL.getChildUIDL(0), seriesUIDL.getTag());
        if (dataLabels != null) {
            seriesOptions.setDataLabels(dataLabels);
        }

        // state
        GwtStates seriesState = getSeriesState(seriesUIDL.getChildUIDL(1));

        if (seriesState != null) {
            seriesOptions.setStates(seriesState);
        }

        VConsole.log("Exit [updateSeriesOptions]");
    }

    private GwtDataLabels getSeriesDataLabel(UIDL dataLabelUIDL,
            String seriesType) {
        VConsole.log("Enter [getSeriesDataLabel]");
        if (dataLabelUIDL == null
                || dataLabelUIDL.getAttributeNames().size() == 0) {
            return null;
        }
        GwtDataLabels dataLabel = GwtDataLabels.createDataLabels();
        if (seriesType.equals("pie")) {
            dataLabel = GwtPieDataLabels.createPieDataLabels();
            updatePieDataLabel(dataLabelUIDL, (GwtPieDataLabels) dataLabel);
        } else {
            updateDataLabel(dataLabelUIDL, dataLabel);
        }
        VConsole.log("Exit [getSeriesDataLabel]");
        return dataLabel;
    }

    private void updatePieDataLabel(UIDL dataLabelUIDL,
            GwtPieDataLabels pieDataLabel) {
        updateDataLabel(dataLabelUIDL, pieDataLabel);
        if (dataLabelUIDL.hasAttribute("connectorColor")) {
            pieDataLabel.setConnectorColor(dataLabelUIDL
                    .getStringAttribute("connectorColor"));
        }
        if (dataLabelUIDL.hasAttribute("connectorWidth")) {
            pieDataLabel.setConnectorWidth(dataLabelUIDL
                    .getIntAttribute("connectorWidth"));
        }
        if (dataLabelUIDL.hasAttribute("connectorPadding")) {
            pieDataLabel.setConnectorPadding(dataLabelUIDL
                    .getIntAttribute("connectorPadding"));
        }
        if (dataLabelUIDL.hasAttribute("distance")) {
            pieDataLabel.setDistance(dataLabelUIDL.getIntAttribute("distance"));
        }
    }

    private void updateDataLabel(UIDL dataLabelUIDL, GwtDataLabels dataLabel) {
        if (dataLabelUIDL.hasAttribute("align")) {
            dataLabel.setAlign(dataLabelUIDL.getStringAttribute("align"));
        }
        if (dataLabelUIDL.hasAttribute("enabled")) {
            dataLabel.setEnabled(dataLabelUIDL.getBooleanAttribute("enabled"));
        }
        if (dataLabelUIDL.hasAttribute("formatter")) {
            dataLabel.setFormatter(getExecutableFunction(dataLabelUIDL
                    .getStringAttribute("formatter")));
        }
        if (dataLabelUIDL.hasAttribute("rotation")) {
            dataLabel.setRotation(dataLabelUIDL.getIntAttribute("rotation"));
        }
        if (dataLabelUIDL.hasAttribute("style")) {
            dataLabel.setStyle(dataLabelUIDL.getStringAttribute("style"));
        }
        if (dataLabelUIDL.hasAttribute("x")) {
            dataLabel.setX(dataLabelUIDL.getIntAttribute("x"));
        }
        if (dataLabelUIDL.hasAttribute("y")) {
            dataLabel.setY(dataLabelUIDL.getIntAttribute("y"));
        }
        if (dataLabelUIDL.hasAttribute("color")) {
            dataLabel.setColor(dataLabelUIDL.getStringAttribute("color"));
        }
    }

    private GwtStates getSeriesState(UIDL stateUIDL) {
        if (stateUIDL == null
                || (stateUIDL != null && stateUIDL.getChildCount() == 0)
                || (stateUIDL.getChildCount() > 0 && stateUIDL.getChildUIDL(0)
                        .getAttributeNames().size() == 0)) {
            return null;
        }
        GwtStates state = GwtStates.create();
        GwtHover hover = GwtHover.create();
        state.setHover(hover);
        UIDL hoverUIDL = stateUIDL.getChildUIDL(0);
        if (hoverUIDL.hasAttribute("enabled")) {
            hover.setEnabled(hoverUIDL.getBooleanAttribute("enabled"));
        }
        if (hoverUIDL.hasAttribute("lineWidth")) {
            hover.setLineWidth(hoverUIDL.getIntAttribute("lineWidth"));
        }
        if (hoverUIDL.hasAttribute("brightness")) {
            hover.setBrightness(hoverUIDL.getDoubleAttribute("brightness"));
        }
        return state;
    }

    private GwtMarker getMarkerOptions(UIDL uidl) {
        VConsole.log("Enter [getMarkerOptions]");
        int noOfAttrs = 0;
        noOfAttrs = (uidl != null ? uidl.getAttributeNames().size() : 0);
        if (uidl == null || (noOfAttrs == 0 && uidl.getChildCount() == 0)) {
            VConsole.log("Exit [getMarkerOptions]");
            return null;
        }
        GwtMarker marker = GwtMarker.create();
        String markerType = uidl.getStringAttribute("markerType");
        if (uidl.hasAttribute("enabled")) {
            marker.setEnabled(uidl.getBooleanAttribute("enabled"));
        }
        if (uidl.hasAttribute("lineColor")) {
            marker.setLineColor(uidl.getStringAttribute("lineColor"));
        }
        if (uidl.hasAttribute("fillColor")) {
            marker.setFillColor(uidl.getStringAttribute("fillColor"));
        }
        if (uidl.hasAttribute("lineWidth")) {
            marker.setLineWidth(uidl.getIntAttribute("lineWidth"));
        }
        if (uidl.hasAttribute("radius")) {
            marker.setRadius(uidl.getIntAttribute("radius"));
        }
        if (uidl.hasAttribute("symbol")) {
            if (markerType.equals("image")) {
                marker.setSymbol("url(." + uidl.getStringAttribute("symbol")
                        + ")");
            } else {
                marker.setSymbol(uidl.getStringAttribute("symbol"));
            }
        }

        // Marker states exist only in case of SymbolMarker and not ImageMarker
        if (uidl.getChildCount() > 0) {
            UIDL statesUIDL = uidl.getChildUIDL(0);
            UIDL hoverStateUIDL = statesUIDL.getChildUIDL(0);
            UIDL selectStateUIDL = statesUIDL.getChildUIDL(1);
            GwtMarkerState markerHoverState = getMarkerState(hoverStateUIDL);
            GwtMarkerState markerSelectState = getMarkerState(selectStateUIDL);
            if (markerHoverState != null || markerSelectState != null) {
                VConsole.log("Setting marker states...");
                GwtMarkerStates markerStates = GwtMarkerStates.create();
                if (markerHoverState != null) {
                    markerStates.setHover(markerHoverState);
                }
                if (markerSelectState != null) {
                    markerStates.setSelect(markerSelectState);
                }
                marker.setStates(markerStates);
            }
        }
        VConsole.log("Exit [getMarkerOptions]");
        return marker;
    }

    private GwtMarkerState getMarkerState(UIDL uidl) {
        VConsole.log("Enter [getMarkerState]");
        if (uidl == null || uidl.getAttributeNames().size() == 0) {
            VConsole.log("Neither hover nor select states found for a maker.");
            VConsole.log("Exit [getMarkerState]");
            return null;
        }
        GwtMarkerState markerState = GwtMarkerState.create();
        if (uidl.hasAttribute("enabled")) {
            markerState.setEnabled(uidl.getBooleanAttribute("enabled"));
        }
        if (uidl.hasAttribute("lineColor")) {
            markerState.setLineColor(uidl.getStringAttribute("lineColor"));
        }
        if (uidl.hasAttribute("fillColor")) {
            markerState.setFillColor(uidl.getStringAttribute("fillColor"));
        }
        if (uidl.hasAttribute("lineWidth")) {
            markerState.setLineWidth(uidl.getIntAttribute("lineWidth"));
        }
        if (uidl.hasAttribute("radius")) {
            markerState.setRadius(uidl.getIntAttribute("radius"));
        }
        VConsole.log("Exit [getMarkerState]");
        return markerState;
    }

    private void updateBaseLineOptions(UIDL lineUIDL,
            GwtBaseLineOptions lineOptions) {
        VConsole.log("Enter [updateBaseLineOptions]");

        updateSeriesOptions(lineUIDL, lineOptions);
        if (lineUIDL.hasAttribute("color")) {
            lineOptions.setColor(lineUIDL.getStringAttribute("color"));
        }
        if (lineUIDL.hasAttribute("dashStyle")) {
            lineOptions.setDashStyle(lineUIDL.getStringAttribute("dashStyle"));
        }
        if (lineUIDL.hasAttribute("lineWidth")) {
            lineOptions.setLineWidth(lineUIDL.getIntAttribute("lineWidth"));
        }
        if (lineUIDL.hasAttribute("pointStart")) {
            lineOptions.setPointStart(lineUIDL.getDoubleAttribute("pointStart"));
        }
        if (lineUIDL.hasAttribute("pointInterval")) {
            lineOptions.setPointInterval(lineUIDL
                    .getIntAttribute("pointInterval"));
        }
        if (lineUIDL.hasAttribute("stickyTracking")) {
            lineOptions.setStickyTracking(lineUIDL
                    .getBooleanAttribute("stickyTracking"));
        }

        GwtMarker marker = getMarkerOptions(lineUIDL.getChildUIDL(2));
        if (marker != null) {
            lineOptions.setMarker(marker);
        }
        VConsole.log("Exit [updateBaseLineOptions]");
    }

    private void updateLineOptions(UIDL lineUIDL, GwtLineOptions lineOptions) {
        VConsole.log("Enter [updateLineOptions]");
        VConsole.log("Tag Name : " + lineUIDL.getTag());

        updateBaseLineOptions(lineUIDL, lineOptions);

        if (lineUIDL.hasAttribute("step")) {
            lineOptions.setStep(lineUIDL.getBooleanAttribute("step"));
        }

        VConsole.log("Exit [updateBaseLineOptions]");
    }

    private void updateScatterOptions(UIDL scatterUIDL,
            GwtScatterOptions scatterOptions) {
        VConsole.log("Enter [updateScatterOptions]");
        VConsole.log("Tag Name : " + scatterUIDL.getTag());

        updateBaseLineOptions(scatterUIDL, scatterOptions);

        VConsole.log("Exit [updateScatterOptions]");
    }

    private void updateSplineOptions(UIDL splineUIDL,
            GwtSplineOptions splineOptions) {
        VConsole.log("Enter [updateSplineOptions]");
        VConsole.log("Tag Name : " + splineUIDL.getTag());

        updateBaseLineOptions(splineUIDL, splineOptions);

        VConsole.log("Exit [updateSplineOptions]");
    }

    private void updateAreaOptions(UIDL areaUIDL, GwtAreaOptions areaOptions) {
        VConsole.log("Enter [updateAreaOptions]");
        VConsole.log("Tag Name : " + areaUIDL.getTag());

        updateBaseLineOptions(areaUIDL, areaOptions);
        if (areaUIDL.hasAttribute("fillColor")) {
            areaOptions.setFillColor(areaUIDL.getStringAttribute("fillColor"));
        }
        if (areaUIDL.hasAttribute("lineColor")) {
            areaOptions.setLineColor(areaUIDL.getStringAttribute("lineColor"));
        }
        if (areaUIDL.hasAttribute("fillOpacity")) {
            areaOptions.setFillOpacity(areaUIDL
                    .getDoubleAttribute("fillOpacity"));
        }
        if (areaUIDL.hasAttribute("threshold")) {
            areaOptions.setThreshold(areaUIDL.getIntAttribute("threshold"));
        }

        VConsole.log("Exit [updateAreaOptions]");
    }

    private void updateAreaSplineOptions(UIDL areaSplineUIDL,
            GwtAreaSplineOptions areaSplineOptions) {
        VConsole.log("Enter [updateAreaSplineOptions]");
        VConsole.log("Tag Name : " + areaSplineUIDL.getTag());

        updateAreaOptions(areaSplineUIDL, areaSplineOptions);

        VConsole.log("Exit [updateAreaSplineOptions]");
    }

    private void updatePieOptions(UIDL pieUIDL, GwtPieOptions pieOptions) {
        VConsole.log("Enter [updatePieOptions]");
        VConsole.log("Tag Name : " + pieUIDL.getTag());

        updateSeriesOptions(pieUIDL, pieOptions);
        Integer centerX = null;
        Integer centerY = null;
        if (pieUIDL.hasAttribute("centerX")) {
            centerX = pieUIDL.getIntAttribute("centerX");
        }
        if (pieUIDL.hasAttribute("centerY")) {
            centerY = pieUIDL.getIntAttribute("centerY");
        }
        if (centerX != null || centerY != null) {
            JsArrayNumber center = JavaScriptObject.createArray().cast();
            center.push((centerX == null ? 0 : centerX));
            center.push((centerY == null ? 0 : centerY));
            pieOptions.setCenter(center);
        }
        if (pieUIDL.hasAttribute("borderColor")) {
            pieOptions
                    .setBorderColor(pieUIDL.getStringAttribute("borderColor"));
        }
        if (pieUIDL.hasAttribute("borderWidth")) {
            pieOptions.setBorderWidth(pieUIDL.getDoubleAttribute("borderWidth"));
        }
        if (pieUIDL.hasAttribute("innerSize")) {
            pieOptions.setInnerSize(pieUIDL.getIntAttribute("innerSize"));
        }
		if (pieUIDL.hasAttribute("ignoreHiddenPoint")) {
			pieOptions.setIgnoreHiddenPoint(pieUIDL.getBooleanAttribute("ignoreHiddenPoint"));
		}
        if (pieUIDL.hasAttribute("size")) {
            pieOptions.setSize(pieUIDL.getIntAttribute("size"));
        }
        if (pieUIDL.hasAttribute("slicedOffset")) {
            pieOptions.setSlicedOffset(pieUIDL.getIntAttribute("slicedOffset"));
        }

        VConsole.log("Exit [updatePieOptions]");
    }

    private void updateBaseBarOptions(UIDL barUIDL,
            GwtBaseBarOptions baseBarOptions) {
        VConsole.log("Enter [updateBaseBarOptions]");

        updateSeriesOptions(barUIDL, baseBarOptions);
        if (barUIDL.hasAttribute("borderColor")) {
            baseBarOptions.setBorderColor(barUIDL
                    .getStringAttribute("borderColor"));
        }
        if (barUIDL.hasAttribute("borderWidth")) {
            baseBarOptions.setBorderWidth(barUIDL
                    .getIntAttribute("borderWidth"));
        }
        if (barUIDL.hasAttribute("borderRadius")) {
            baseBarOptions.setBorderRadius(barUIDL
                    .getIntAttribute("borderRadius"));
        }
        if (barUIDL.hasAttribute("colorByPoint")) {
            baseBarOptions.setColorByPoint(barUIDL
                    .getBooleanAttribute("colorByPoint"));
        }
        if (barUIDL.hasAttribute("groupPadding")) {
            baseBarOptions.setGroupPadding(barUIDL
                    .getDoubleAttribute("groupPadding"));
        }
        if (barUIDL.hasAttribute("minPointLength")) {
            baseBarOptions.setMinPointLength(barUIDL
                    .getDoubleAttribute("minPointLength"));
        }
        if (barUIDL.hasAttribute("pointPadding")) {
            baseBarOptions.setPointPadding(barUIDL
                    .getDoubleAttribute("pointPadding"));
        }
        if (barUIDL.hasAttribute("pointWidth")) {
            baseBarOptions.setPointWidth(barUIDL.getIntAttribute("pointWidth"));
        }

        VConsole.log("Exit [updateBaseBarOptions]");
    }

    private void updateBarOptions(UIDL barUIDL, GwtBarOptions barOptions) {
        VConsole.log("Enter [updateBarOptions]");
        VConsole.log("Tag Name : " + barUIDL.getTag());

        updateBaseBarOptions(barUIDL, barOptions);

        VConsole.log("Exit [updateBarOptions]");
    }

    private void updateColumnOptions(UIDL columnUIDL,
            GwtColumnOptions columnOptions) {
        VConsole.log("Enter [updateColumnOptions]");
        VConsole.log("Tag Name : " + columnUIDL.getTag());

        updateBaseBarOptions(columnUIDL, columnOptions);

        VConsole.log("Exit [updateColumnOptions]");
    }

    private void updateOptionsWithChartEvents(GwtInvientChartsConfig options,
            UIDL chartEventUIDL) {
        VConsole.log("Enter [updateOptionsWithChartEvents]");

        // Chart events
        GwtChartEvents chartEvents = GwtChartEvents.create();
        if (chartEventUIDL.hasAttribute("addSeries")
                && chartEventUIDL.getBooleanAttribute("addSeries")) {
            chartEvents.setAddSeriesEvent(EventCallbacks
                    .getChartAddSeries(this));
        }
        if (chartEventUIDL.hasAttribute("click")
                && chartEventUIDL.getBooleanAttribute("click")) {
            chartEvents.setClickEvent(EventCallbacks.getChartClick(this));
        }
        if (chartEventUIDL.hasAttribute("selection")
                && chartEventUIDL.getBooleanAttribute("selection")) {
            if (options.getChartOptions().getClientZoom()) {
                chartEvents.setSelectionEvent(EventCallbacks
                        .getClientChartSelection(this));
            } else {
                chartEvents.setSelectionEvent(EventCallbacks
                        .getServerChartSelection(this));
            }
        }
        if (options.getChartOptions() == null) {
            options.setChartOptions(GwtChartOptions.create());
        }
        options.getChartOptions().setEvents(chartEvents);

        VConsole.log("Exit [updateOptionsWithChartEvents]");
    }

    private GwtSeriesEvents getSeriesEvents(UIDL seriesEventUIDL) {
        GwtSeriesEvents seriesEvents = GwtSeriesEvents.create();
        boolean foundEvt = false;
        if (seriesEventUIDL.hasAttribute("legendItemClick")
                && seriesEventUIDL.getBooleanAttribute("legendItemClick")) {
            seriesEvents.setLegendItemClickEvent(EventCallbacks
                    .getSeriesLegendItemClick(this));
            foundEvt = true;
        }
        if (seriesEventUIDL.hasAttribute("click")
                && seriesEventUIDL.getBooleanAttribute("click")) {
            seriesEvents.setClickEvent(EventCallbacks.getSeriesClick(this));
            foundEvt = true;
        }
        if (seriesEventUIDL.hasAttribute("show")
                && seriesEventUIDL.getBooleanAttribute("show")) {
            seriesEvents.setShowEvent(EventCallbacks.getSeriesShow(this));
            foundEvt = true;
        }
        if (seriesEventUIDL.hasAttribute("hide")
                && seriesEventUIDL.getBooleanAttribute("hide")) {
            seriesEvents.setHideEvent(EventCallbacks.getSeriesHide(this));
            foundEvt = true;
        }
        if (foundEvt) {
            return seriesEvents;
        }
        return null;
    }

    private void updateOptionsWithSeriesAndPoingEvents(
            GwtInvientChartsConfig options, UIDL chartSeriesEventsUIDL) {
        VConsole.log("Enter [updateOptionsWithSeriesAndPoingEvents]");
        VConsole.log("[updateOptionsWithSeriesEvents] # of series : "
                + chartSeriesEventsUIDL.getChildCount());

        // UIDL seriesEventUIDL = eventUIDL.getChildUIDL(1);
        if (chartSeriesEventsUIDL.getChildCount() > 0
                && options.getPlotOptions() == null) {
            options.setPlotOptions(GwtPlotOptions.create());
        }
        for (int cnt = 0; cnt < chartSeriesEventsUIDL.getChildCount(); cnt++) {
            UIDL seriesEventsUIDL = chartSeriesEventsUIDL.getChildUIDL(cnt);
            String seriesType = seriesEventsUIDL.getTag(); // can be
                                                           // series/pie/line
                                                           // etc
            VConsole.log("Series type " + seriesType);
            //
            GwtSeriesEvents seriesEvents = getSeriesEvents(seriesEventsUIDL);
            //
            GwtPointEvents pointEvents = null;
            if (seriesEventsUIDL.getChildCount() > 0) {
                pointEvents = getPointEvents(options,
                        seriesEventsUIDL.getChildUIDL(0));
            }
            if (seriesEvents == null && pointEvents == null) {
                VConsole.log("No series/point events found for series type : "
                        + seriesType);
                continue;
            }
            GwtSeriesGeneralOptions seriesOptions = null;
            if (seriesType.equals("line")) {
                if (options.getPlotOptions().getLine() == null) {
                    options.getPlotOptions().setLine(
                            GwtLineOptions.createLineOptions());
                }
                seriesOptions = options.getPlotOptions().getLine();
            } else if (seriesType.equals("spline")) {
                if (options.getPlotOptions().getSpline() == null) {
                    options.getPlotOptions().setSpline(
                            GwtSplineOptions.createSplineOptions());
                }
                seriesOptions = options.getPlotOptions().getSpline();
            } else if (seriesType.equals("area")) {
                if (options.getPlotOptions().getArea() == null) {
                    options.getPlotOptions().setArea(
                            GwtAreaOptions.createAreaOptions());
                }
                seriesOptions = options.getPlotOptions().getArea();
            } else if (seriesType.equals("areaspline")) {
                if (options.getPlotOptions().getAreaSpline() == null) {
                    options.getPlotOptions().setAreaSpline(
                            GwtAreaSplineOptions.createAreaSplineOptions());
                }
                seriesOptions = options.getPlotOptions().getAreaSpline();
            } else if (seriesType.equals("bar")) {
                if (options.getPlotOptions().getBar() == null) {
                    options.getPlotOptions().setBar(
                            GwtBarOptions.createBarOptions());
                }
                seriesOptions = options.getPlotOptions().getBar();
            } else if (seriesType.equals("column")) {
                if (options.getPlotOptions().getColumn() == null) {
                    options.getPlotOptions().setColumn(
                            GwtColumnOptions.createColumnOptions());
                }
                seriesOptions = options.getPlotOptions().getColumn();
            } else if (seriesType.equals("scatter")) {
                if (options.getPlotOptions().getScatter() == null) {
                    options.getPlotOptions().setScatter(
                            GwtScatterOptions.createScatterOptions());
                }
                seriesOptions = options.getPlotOptions().getScatter();
            } else if (seriesType.equals("pie")) {
                if (options.getPlotOptions().getPie() == null) {
                    options.getPlotOptions().setPie(
                            GwtPieOptions.createPieOptions());
                }
                seriesOptions = options.getPlotOptions().getPie();
            } else {
                if (options.getPlotOptions().getSeries() == null) {
                    options.getPlotOptions().setSeries(
                            GwtSeriesGeneralOptions.createSeriesOptions());
                }
                seriesOptions = options.getPlotOptions().getSeries();
            }
            // Set series/point events
            if (seriesEvents != null) {
                seriesOptions.setEvents(seriesEvents);
            }
            if (pointEvents != null) {
                seriesOptions.setPointEvents(pointEvents);
            }
        }
        VConsole.log("Exit [updateOptionsWithSeriesAndPoingEvents]");
    }

    private GwtPointEvents getPointEvents(GwtInvientChartsConfig options,
            UIDL pointEventsUIDL) {
        VConsole.log("Enter [getPointEvents]");
        // Point events
        boolean foundEvt = false;
        GwtPointEvents pointEvents = GwtPointEvents.create();
        if (pointEventsUIDL.hasAttribute("legendItemClick")
                && pointEventsUIDL.getBooleanAttribute("legendItemClick")) {
            pointEvents.setLegendItemClickEvent(EventCallbacks
                    .getPieLegendItemClick(this));
            foundEvt = true;
        }
        if (pointEventsUIDL.hasAttribute("click")
                && pointEventsUIDL.getBooleanAttribute("click")) {
            pointEvents.setClickEvent(EventCallbacks.getPointClick(this));
            foundEvt = true;
        }
        if (pointEventsUIDL.hasAttribute("remove")
                && pointEventsUIDL.getBooleanAttribute("remove")) {
            pointEvents.setRemoveEvent(EventCallbacks.getPointRemove(this));
            foundEvt = true;
        }
        if (pointEventsUIDL.hasAttribute("select")
                && pointEventsUIDL.getBooleanAttribute("select")) {
            pointEvents.setSelectEvent(EventCallbacks.getPointSelect(this));
            foundEvt = true;
        }
        if (pointEventsUIDL.hasAttribute("unselect")
                && pointEventsUIDL.getBooleanAttribute("unselect")) {
            pointEvents.setUnselectEvent(EventCallbacks.getPointUnselect(this));
            foundEvt = true;
        }
        VConsole.log("Exit [getPointEvents]");
        if (foundEvt) {
            return pointEvents;
        }
        return null;
    }

    private void updateOptionsWithEvents(GwtInvientChartsConfig options,
            UIDL eventUIDL) {
        VConsole.log("Enter [updateOptionsWithEvents]");

        // Chart events
        updateOptionsWithChartEvents(options, eventUIDL.getChildUIDL(0));

        // Series events
        updateOptionsWithSeriesAndPoingEvents(options,
                eventUIDL.getChildUIDL(1));

        VConsole.log("Exit [updateOptionsWithEvents]");
    }

    protected void chartAddSeriesListener(GwtChart chart) {
        VConsole.log("Enter [chartAddSeriesListener]");

        client.updateVariable(uidlId, "event", "addSeries", true);

        VConsole.log("Exit [chartAddSeriesListener]");
    }

    protected void chartClickListener(GwtChart chart, double xAxisPos,
            double yAxisPos, int pageX, int pageY) {
        VConsole.log("Enter [chartClickListener]");

        VConsole.log("chartClickListener : xAxisPos : " + xAxisPos
                + ", yAxisPos : " + yAxisPos);
        client.updateVariable(uidlId, "event", "chartClick", false);
        Map<String, Object> eventData = new HashMap<String, Object>();
        eventData.put("xAxisPos", String.valueOf(xAxisPos));
        eventData.put("yAxisPos", String.valueOf(yAxisPos));
        updateEventDataWithMousePosition(eventData, pageX, pageY);
        client.updateVariable(uidlId, "eventData", eventData, true);

        VConsole.log("Exit [chartClickListener]");
    }

    protected void chartSelectionListener(GwtChart chart, double xAxisMin,
            double xAxisMax, double yAxisMin, double yAxisMax) {
        VConsole.log("Enter [chartSelectionListener]");

        VConsole.log("[chartSelectionListener] xAxisMin : " + xAxisMin
                + ", xAxisMax : " + xAxisMax + ", yAxisMin : " + yAxisMin
                + ", yAxisMax : " + yAxisMax);
        client.updateVariable(uidlId, "event", "chartZoom", false);
        Map<String, Object> eventData = new HashMap<String, Object>();
        eventData.put("xAxisMin", String.valueOf(xAxisMin));
        eventData.put("xAxisMax", String.valueOf(xAxisMax));
        eventData.put("yAxisMin", String.valueOf(yAxisMin));
        eventData.put("yAxisMax", String.valueOf(yAxisMax));
        client.updateVariable(uidlId, "eventData", eventData, true);

        VConsole.log("Exit [chartSelectionListener]");
    }

    protected void chartResetZoomListener(GwtChart chart) {
        VConsole.log("Enter [chartResetZoomListener]");

        client.updateVariable(uidlId, "event", "chartResetZoom", true);

        VConsole.log("Exit [chartResetZoomListener]");
    }

    protected void seriesClickListener(GwtSeries series, GwtPoint nearestPoint,
            int pageX, int pageY) {
        VConsole.log("Enter [seriesClickListener]");

        VConsole.log("[seriesClickListener] point x: " + nearestPoint.getX()
                + ", point y: " + nearestPoint.getY());
        client.updateVariable(uidlId, "event", "seriesClick", false);
        Map<String, Object> eventData = getEventData(nearestPoint);
        updateEventDataWithMousePosition(eventData, pageX, pageY);
        client.updateVariable(uidlId, "eventData", eventData, true);

        VConsole.log("Exit [seriesClickListener]");
    }

    protected void seriesHideListener(GwtSeries series) {
        VConsole.log("Enter [seriesHideListener]");

        VConsole.log("[seriesHideListener] series name " + series.getName());
        client.updateVariable(uidlId, "event", "seriesHide", false);
        Map<String, Object> eventData = new HashMap<String, Object>();
        eventData.put("seriesName", series.getName());
        client.updateVariable(uidlId, "eventData", eventData, true);

        VConsole.log("Exit [seriesHideListener]");
    }

    protected void seriesShowListener(GwtSeries series) {
        VConsole.log("Enter [seriesShowListener]");

        VConsole.log("[seriesShowListener] series name " + series.getName());
        client.updateVariable(uidlId, "event", "seriesShow", false);
        Map<String, Object> eventData = new HashMap<String, Object>();
        eventData.put("seriesName", series.getName());
        client.updateVariable(uidlId, "eventData", eventData, true);

        VConsole.log("Exit [seriesShowListener]");
    }

    protected void seriesLegendItemClickListener(GwtSeries series) {
        VConsole.log("Enter [seriesLegendItemClickListener]");

        VConsole.log("[seriesLegendItemClickListener] name " + series.getName());
        client.updateVariable(uidlId, "event", "seriesLegendItemClick", false);
        Map<String, Object> eventData = new HashMap<String, Object>();
        eventData.put("seriesName", series.getName());
        client.updateVariable(uidlId, "eventData", eventData, true);

        VConsole.log("Exit [seriesLegendItemClickListener]");
    }

    protected void pieLegendItemClickListener(GwtPoint point) {
        VConsole.log("Enter [pieLegendItemClickListener]");

        client.updateVariable(uidlId, "event", "pieLegendItemClick", false);
        Map<String, Object> eventData = getEventData(point);
        client.updateVariable(uidlId, "eventData", eventData, true);

        VConsole.log("Exit [pieLegendItemClickListener]");
    }

    protected void pointClickListener(GwtPoint point, int pageX, int pageY) {
        VConsole.log("Enter [pointClickListener]");

        client.updateVariable(uidlId, "event", "pointClick", false);
        Map<String, Object> eventData = getEventData(point);
        updateEventDataWithMousePosition(eventData, pageX, pageY);
        client.updateVariable(uidlId, "eventData", eventData, true);

        VConsole.log("Exit [pointClickListener]");
    }

    protected void pointSelectListener(GwtPoint point) {
        VConsole.log("Enter [pointSelectListener]");

        VConsole.log("[pointSelectListener] point " + point.getX() + ", "
                + point.getY());
        client.updateVariable(uidlId, "event", "pointSelect", false);
        client.updateVariable(uidlId, "eventData", getEventData(point), true);

        VConsole.log("Exit [pointSelectListener]");
    }

    protected void pointUnselectListener(GwtPoint point) {
        VConsole.log("Enter [pointUnselectListener]");

        VConsole.log("[pointUnselectListener] point " + point.getX() + ", "
                + point.getY());
        client.updateVariable(uidlId, "event", "pointUnselect", false);
        client.updateVariable(uidlId, "eventData", getEventData(point), true);

        VConsole.log("Exit [pointUnselectListener]");
    }

    protected void pointRemoveListener(GwtPoint point) {
        VConsole.log("Enter [pointRemoveListener]");

        VConsole.log("[pointRemoveListener] point " + point.getX() + ", "
                + point.getY());
        client.updateVariable(uidlId, "event", "pointRemove", false);
        client.updateVariable(uidlId, "eventData", getEventData(point), true);

        VConsole.log("Exit [pointRemoveListener]");
    }

    private Map<String, Object> getEventData(GwtPoint point) {
        Map<String, Object> eventData = new HashMap<String, Object>();
        eventData.put("seriesName", point.getSeries().getName());
        eventData.put("category", point.getCategory());
        // The point x and y values are converted into
        // string value to avoid data conversion issues
        // for datetime series/axis
        // It is responsibility of the server to convert string value
        // into appropriate data type e.g. double or Date
        eventData.put("pointX", String.valueOf(point.getX()));
        eventData.put("pointY", String.valueOf(point.getY()));
        return eventData;
    }

    private void updateEventDataWithMousePosition(
            Map<String, Object> eventData, int pageX, int pageY) {
        eventData.put("mouseX", pageX);
        eventData.put("mouseY", pageY);
    }

    private String getExecutableFunction(String formatterFunc) {
        StringBuilder sb = new StringBuilder("");
        sb.append("function dummy() { ");
        sb.append(" return ");
        sb.append(formatterFunc).append(";");
        sb.append(" }");
        sb.append(" dummy();");
        return sb.toString();
    }

    /**
     * Define a JS function to be used in order to get mouse coordinates when
     * click event occurs on a chart/series/point
     */
    private native final void publish() /*-{
                                        // Used in based class GwtInvientCharts.java
                                        $wnd.getMouseCoords = function(ev) {
                                            if(ev.pageX || ev.pageY){
                                                return {x:ev.pageX, y:ev.pageY};   
                                            } else {   
                                                return {   
                                                    x:ev.clientX + document.documentElement.scrollLeft,   
                                                    y:ev.clientY + document.documentElement.scrollTop   
                                                };
                                            }
                                        };
                                        // Used in class GwtInvientChartsConfig.java
                                        $wnd.getInvientChartsColor = function(colorVal) {
                                            var colorKey = 'JSOBJ:';
                                            var index = colorVal.indexOf(colorKey);
                                            if(index == 0) {
                                                return eval('(' + colorVal.substring(index+colorKey.length) + ')');
                                            }
                                            return colorVal;
                                        };
                                        }-*/;

}
