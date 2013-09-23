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
package com.invient.vaadin;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import com.invient.vaadin.charts.Color;
import com.invient.vaadin.charts.Color.RGB;
import com.invient.vaadin.charts.Color.RGBA;
import com.invient.vaadin.charts.Gradient;
import com.invient.vaadin.charts.Gradient.LinearGradient.LinearColorStop;
import com.invient.vaadin.charts.InvientCharts;
import com.invient.vaadin.charts.InvientCharts.ChartClickEvent;
import com.invient.vaadin.charts.InvientCharts.ChartClickListener;
import com.invient.vaadin.charts.InvientCharts.ChartResetZoomEvent;
import com.invient.vaadin.charts.InvientCharts.ChartSVGAvailableEvent;
import com.invient.vaadin.charts.InvientCharts.ChartZoomEvent;
import com.invient.vaadin.charts.InvientCharts.ChartZoomListener;
import com.invient.vaadin.charts.InvientCharts.DateTimePoint;
import com.invient.vaadin.charts.InvientCharts.DateTimeSeries;
import com.invient.vaadin.charts.InvientCharts.DecimalPoint;
import com.invient.vaadin.charts.InvientCharts.PieChartLegendItemClickEvent;
import com.invient.vaadin.charts.InvientCharts.PointClickEvent;
import com.invient.vaadin.charts.InvientCharts.PointClickListener;
import com.invient.vaadin.charts.InvientCharts.PointRemoveEvent;
import com.invient.vaadin.charts.InvientCharts.PointSelectEvent;
import com.invient.vaadin.charts.InvientCharts.PointUnselectEvent;
import com.invient.vaadin.charts.InvientCharts.Series;
import com.invient.vaadin.charts.InvientCharts.SeriesClickEvent;
import com.invient.vaadin.charts.InvientCharts.SeriesHideEvent;
import com.invient.vaadin.charts.InvientCharts.SeriesLegendItemClickEvent;
import com.invient.vaadin.charts.InvientCharts.SeriesShowEvent;
import com.invient.vaadin.charts.InvientCharts.SeriesType;
import com.invient.vaadin.charts.InvientCharts.XYSeries;
import com.invient.vaadin.charts.InvientChartsConfig;
import com.invient.vaadin.charts.InvientChartsConfig.AreaConfig;
import com.invient.vaadin.charts.InvientChartsConfig.AreaSplineConfig;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.AxisTitle;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.AxisTitleAlign;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.DateTimePlotBand;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.DateTimePlotBand.DateTimeRange;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.Grid;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.MinorGrid;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotBand;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotBand.NumberRange;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotLine;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotLine.NumberValue;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.PlotLabel;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.Tick;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.TickmarkPlacement;
import com.invient.vaadin.charts.InvientChartsConfig.BarConfig;
import com.invient.vaadin.charts.InvientChartsConfig.CategoryAxis;
import com.invient.vaadin.charts.InvientChartsConfig.ChartLabel;
import com.invient.vaadin.charts.InvientChartsConfig.ChartLabel.ChartLabelItem;
import com.invient.vaadin.charts.InvientChartsConfig.ColumnConfig;
import com.invient.vaadin.charts.InvientChartsConfig.DashStyle;
import com.invient.vaadin.charts.InvientChartsConfig.DataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.DateTimeAxis;
import com.invient.vaadin.charts.InvientChartsConfig.GeneralChartConfig.Margin;
import com.invient.vaadin.charts.InvientChartsConfig.GeneralChartConfig.Spacing;
import com.invient.vaadin.charts.InvientChartsConfig.GeneralChartConfig.ZoomType;
import com.invient.vaadin.charts.InvientChartsConfig.HorzAlign;
import com.invient.vaadin.charts.InvientChartsConfig.ImageMarker;
import com.invient.vaadin.charts.InvientChartsConfig.Legend;
import com.invient.vaadin.charts.InvientChartsConfig.Legend.Layout;
import com.invient.vaadin.charts.InvientChartsConfig.LineConfig;
import com.invient.vaadin.charts.InvientChartsConfig.MarkerState;
import com.invient.vaadin.charts.InvientChartsConfig.NumberXAxis;
import com.invient.vaadin.charts.InvientChartsConfig.NumberYAxis;
import com.invient.vaadin.charts.InvientChartsConfig.PieConfig;
import com.invient.vaadin.charts.InvientChartsConfig.PieDataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.PointConfig;
import com.invient.vaadin.charts.InvientChartsConfig.Position;
import com.invient.vaadin.charts.InvientChartsConfig.ScatterConfig;
import com.invient.vaadin.charts.InvientChartsConfig.SeriesConfig;
import com.invient.vaadin.charts.InvientChartsConfig.SeriesState;
import com.invient.vaadin.charts.InvientChartsConfig.SplineConfig;
import com.invient.vaadin.charts.InvientChartsConfig.Stacking;
import com.invient.vaadin.charts.InvientChartsConfig.SymbolMarker;
import com.invient.vaadin.charts.InvientChartsConfig.SymbolMarker.Symbol;
import com.invient.vaadin.charts.InvientChartsConfig.Tooltip;
import com.invient.vaadin.charts.InvientChartsConfig.VertAlign;
import com.invient.vaadin.charts.InvientChartsConfig.XAxis;
import com.invient.vaadin.charts.InvientChartsConfig.XAxisDataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.YAxis;
import com.invient.vaadin.charts.InvientChartsConfig.YAxisDataLabel;

@SuppressWarnings("serial")
public class InvientChartsDemoWin extends Window {

    private final HorizontalSplitPanel mainSplit;
    private final VerticalLayout leftLayout;
    private final VerticalLayout rightLayout;
    private final Tree navTree;
    private TextArea eventLog = new TextArea();
    private static final String TREE_ITEM_CAPTION_PROP_ID = "ChartType";

    public InvientChartsDemoWin() {
        VerticalLayout mainLayout = new VerticalLayout();
        setContent(mainLayout);
        setSizeFull();
        mainLayout.setSizeFull();
        setCaption("Invient Charts");

        HorizontalLayout infoBar = new HorizontalLayout();
        mainLayout.addComponent(infoBar);
        infoBar.setHeight("50px");
        infoBar.setWidth("100%");
        Label lblAppTitle = new Label("Demo Gallery for Invient Charts");
        lblAppTitle.setSizeFull();
        lblAppTitle.setStyleName("v-label-app-title");
        infoBar.addComponent(lblAppTitle);

        mainSplit = new HorizontalSplitPanel();
        mainSplit.setSizeFull();
        mainLayout.addComponent(mainSplit);
        mainLayout.setExpandRatio(mainSplit, 1);

        leftLayout = new VerticalLayout();
        leftLayout.setSpacing(true);
        mainSplit.setFirstComponent(leftLayout);

        rightLayout = new VerticalLayout();
        rightLayout.setSpacing(true);
        rightLayout.setMargin(true);
        mainSplit.setSecondComponent(rightLayout);

        mainSplit.setSplitPosition(200, Sizeable.UNITS_PIXELS);

        navTree = createChartsTree();
        leftLayout.addComponent(navTree);

        eventLog.setReadOnly(true);
        eventLog.setStyleName("v-textarea-chart-events-log");
        eventLog.setSizeFull();
        eventLog.setHeight("200px");
        setTheme("chartdemo");

    }
    
    @Override
    public void attach() {
        super.attach();
        isAppRunningOnGAE = getInvientChartsDemoApp().isAppRunningOnGAE();
        // Select line chart when the screen is loaded
        navTree.select(DemoSeriesType.LINE.getName() + SEPARATOR
                + ChartName.BASIC.getName());
    }

    private boolean isAppRunningOnGAE;

    public boolean isAppRunningOnGAE() {
        return isAppRunningOnGAE;
    }

    private InvientChartsDemoApp getInvientChartsDemoApp() {
        return (InvientChartsDemoApp) getApplication();
    }

    private void showChart(String demoSeriesTypeName, String chartNameString) {
        if (!isAppRunningOnGAE) {
            stopSplineSelfUpdateThread();
        }
        DemoSeriesType demoSeriesType = getDemoSeriesType(demoSeriesTypeName);
        ChartName chartName = getChartName(chartNameString);
        if (demoSeriesType != null && chartName != null) {
            switch (demoSeriesType) {
            case COMBINATION:
                switch (chartName) {
                case COMBINATION_COLUMN_LINE_AND_PIE:
                    showCombination();
                    break;
                case SCATTER_WITH_REGRESSION_LINE:
                    showCombinationScatterWithRegressionLine();
                    break;
                case MULTIPLE_AXES:
                    showCombinationMultipleAxes();
                    break;
                }
                break;
            case LINE:
                switch (chartName) {
                case BASIC:
                    showLine();
                    break;
                case CLICK_TO_ADD_POINT:
                    showClickToAddPoint();
                    break;
                case WITH_DATA_LABELS:
                    showLineWithDataLabels();
                    break;
                case TIMESERIES_ZOOMABLE:
                    showTimeSeriesZoomable();
                    break;
                case MASTER_DETAIL:
                    showMasterDetail();
                    break;
                }
                break;
            case BAR:
                switch (chartName) {
                case BASIC:
                    showBarBasic();
                    break;
                case STACKED:
                    showBarStacked();
                    break;
                case WITH_NEGATIVE_STACK:
                    showBarWithNegStack();
                    break;
                }
                break;
            case COLUMN:
                switch (chartName) {
                case BASIC:
                    showColumnBasic();
                    break;
                case WITH_NEGATIVE_VALUES:
                    showColumnWithNegValues();
                    break;
                case STACKED:
                    showColumnStacked();
                    break;
                case STACKED_AND_GROUPED:
                    showColumnStackedAndGrouped();
                    break;
                case STACKED_PERCENT:
                    showColumnStackedPercent();
                    break;
                case WITH_ROTATED_LABELS:
                    showColumnWithRotatedLabels();
                    break;
                }
                break;
            case AREA:
                switch (chartName) {
                case BASIC:
                    showAreaBasic();
                    break;
                case WITH_NEGATIVE_VALUES:
                    showAreaWithNegValues();
                    break;
                case STACKED:
                    showAreaStacked();
                    break;
                case PERCENTAGE:
                    showAreaPercent();
                    break;
                case INVERTED_AXES:
                    showAreaInvertedAxes();
                    break;
                case WITH_MISSING_POINTS:
                    showAreaWithMissingPoints();
                    break;
                }
                break;
            case AREASPLINE:
                switch (chartName) {
                case BASIC:
                    showAreaSpline();
                    break;
                }
                break;
            case PIE:
                switch (chartName) {
                case BASIC:
                    showPie();
                    break;
                case WITH_LEGEND:
                    showPieWithLegend();
                    break;
                case DONUT:
                    showDonut();
                    break;
                }
                break;
            case SCATTER:
                switch (chartName) {
                case BASIC:
                    showScatter();
                    break;
                }
                break;
            case SPLINE:
                switch (chartName) {
                case BASIC:
                    showSpline();
                    break;
                case WITH_PLOTBANDS:
                    showSplineWithPlotBands();
                    break;
                case WITH_SYMBOLS:
                    showSplineWithSymbol();
                    break;
                case UPDATING_EACH_SECOND:
                    showSplineUpdatingEachSecond();
                    break;
                }
                break;
            default:
                getApplication().getMainWindow().showNotification(
                        "Error occurred during chart processing! Try again!!!");
            }
        } else {
            getApplication().getMainWindow().showNotification(
                    "Error occurred during chart processing! Try again!!!");
        }
    }

    private void showMasterDetail() {
        // Create the master chart
        final InvientCharts masterChart = getMasterChart();
        // Create detail chart
        final InvientCharts detailChart = getDetailChart(masterChart);
        // Register events
        masterChart.addListener(new ChartZoomListener() {

            @Override
            public void chartZoom(ChartZoomEvent chartZoomEvent) {
                // chartZoomEvent.getChartArea().get
                DateTimeSeries masterChartSeries = (DateTimeSeries) masterChart
                        .getSeries("USD to EUR");
                double min = chartZoomEvent.getChartArea().getxAxisMin();
                double max = chartZoomEvent.getChartArea().getxAxisMax();
                LinkedHashSet<DateTimePoint> detailPoints = new LinkedHashSet<InvientCharts.DateTimePoint>();
                DateTimeSeries detailChartSeries = (DateTimeSeries) detailChart
                        .getSeries("USD to EUR");
                detailChart.removeSeries(detailChartSeries);

                for (DateTimePoint point : masterChartSeries.getPoints()) {
                    if (point.getX().getTime() > min
                            && point.getX().getTime() < max) {
                        detailPoints.add(new DateTimePoint(detailChartSeries,
                                point.getX(), point.getY()));
                    }
                }
                // Update series with new points
                detailChartSeries.setSeriesPoints(detailPoints);
                detailChart.addSeries(detailChartSeries);
                detailChart.refresh();
                // Update plotbands
                DateTimeAxis masterDateTimeAxis = (DateTimeAxis) masterChart
                        .getConfig().getXAxes().iterator().next();
                masterDateTimeAxis.removePlotBand("mask-before");
                DateTimePlotBand plotBandBefore = new DateTimePlotBand(
                        "mask-before");
                plotBandBefore.setRange(new DateTimeRange(masterChartMinDate,
                        new Date((long) min)));
                plotBandBefore.setColor(new RGBA(0, 0, 0, 0.2f));
                masterDateTimeAxis.addPlotBand(plotBandBefore);

                masterDateTimeAxis.removePlotBand("mask-after");
                DateTimePlotBand plotBandAfter = new DateTimePlotBand(
                        "mask-after");
                plotBandAfter.setRange(new DateTimeRange(new Date((long) max),
                        masterChartMaxDate));
                plotBandAfter.setColor(new RGBA(0, 0, 0, 0.2f));
                masterDateTimeAxis.addPlotBand(plotBandAfter);
                masterChart.refresh();
            }
        });
        // Add master
        addChart(masterChart, false, false, false, false);
        // Add detail
        addChart(detailChart, true, true, false);
    }

    private InvientCharts getDetailChart(InvientCharts masterChart) {
        InvientChartsConfig detailChartConfig = new InvientChartsConfig();
        detailChartConfig.getGeneralChartConfig().setMargin(new Margin());
        detailChartConfig.getGeneralChartConfig().getMargin().setBottom(120);
        detailChartConfig.getGeneralChartConfig().getMargin().setLeft(50);
        detailChartConfig.getGeneralChartConfig().getMargin().setRight(20);
        detailChartConfig.getGeneralChartConfig().setReflow(false);

        detailChartConfig.getCredit().setEnabled(false);

        detailChartConfig.getTitle().setText(
                "Historical USD to EUR Exchange Rate");

        detailChartConfig.getSubtitle().setText(
                "Select an area by dragging across the lower chart");

        DateTimeAxis detailXAxis = new DateTimeAxis();
        LinkedHashSet<XAxis> detailXAxes = new LinkedHashSet<InvientChartsConfig.XAxis>();
        detailXAxes.add(detailXAxis);
        detailChartConfig.setXAxes(detailXAxes);

        NumberYAxis detailYAxis = new NumberYAxis();
        detailYAxis.setTitle(new AxisTitle(""));
        LinkedHashSet<YAxis> detailYAxes = new LinkedHashSet<InvientChartsConfig.YAxis>();
        detailYAxes.add(detailYAxis);
        detailChartConfig.setYAxes(detailYAxes);

        detailChartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " var point = this.points[0];"
                                + " return '<b>'+ point.series.name +'</b><br/>' + "
                                + " $wnd.Highcharts.dateFormat('%A %B %e %Y', this.x) + ':<br/>' + "
                                + " '1 USD = '+ $wnd.Highcharts.numberFormat(point.y, 2) +' EUR';"
                                + "}");
        detailChartConfig.getTooltip().setShared(true);

        detailChartConfig.getLegend().setEnabled(false);

        LineConfig lineCfg = new LineConfig();
        SymbolMarker marker = new SymbolMarker(false);
        lineCfg.setMarker(marker);
        marker.setHoverState(new MarkerState());
        marker.getHoverState().setEnabled(true);
        marker.getHoverState().setRadius(3);
        detailChartConfig.addSeriesConfig(lineCfg);

        InvientCharts detailChart = new InvientCharts(detailChartConfig);

        // Line instance configuration
        LineConfig lineSeriesCfg = new LineConfig();
        lineSeriesCfg.setPointStart((double) detailChartPointStartDate
                .getTime());
        lineSeriesCfg.setPointInterval(24 * 3600 * 1000.0);
        lineSeriesCfg.setColor(new RGB(69, 114, 167));
        DateTimeSeries detailSeries = new DateTimeSeries("USD to EUR",
                SeriesType.LINE, lineSeriesCfg);

        LinkedHashSet<DateTimePoint> detailPoints = new LinkedHashSet<InvientCharts.DateTimePoint>();
        DateTimeSeries masterChartSeries = (DateTimeSeries) masterChart
                .getSeries("USD to EUR");
        for (DateTimePoint point : masterChartSeries.getPoints()) {
            if (point.getX().getTime() >= detailChartPointStartDate.getTime()) {
                detailPoints.add(new DateTimePoint(detailSeries, point.getY()));
            }
        }

        detailSeries.setSeriesPoints(detailPoints);
        detailChart.addSeries(detailSeries);

        return detailChart;
    }

    private Date masterChartMinDate = getDateZeroTime(2006, 0, 1);
    private Date masterChartMaxDate = getDateZeroTime(2008, 11, 31);
    private Date detailChartPointStartDate = getDateZeroTime(2008, 7, 1);

    private InvientCharts getMasterChart() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setReflow(false);
        chartConfig.getGeneralChartConfig().setBorderWidth(0);
        chartConfig.getGeneralChartConfig().setMargin(new Margin());
        chartConfig.getGeneralChartConfig().getMargin().setLeft(50);
        chartConfig.getGeneralChartConfig().getMargin().setRight(20);
        chartConfig.getGeneralChartConfig().setZoomType(ZoomType.X);
        chartConfig.getGeneralChartConfig().setClientZoom(false);
        chartConfig.getGeneralChartConfig().setHeight(80);
        chartConfig.getTitle().setText("");

        DateTimeAxis xAxis = new DateTimeAxis();
        xAxis.setShowLastLabel(true);
        xAxis.setMaxZoom(14 * 24 * 3600000);
        DateTimePlotBand plotBand = new DateTimePlotBand("mask-before");
        plotBand.setRange(new DateTimeRange(masterChartMinDate,
                detailChartPointStartDate));
        plotBand.setColor(new RGBA(0, 0, 0, 0.2f));
        xAxis.addPlotBand(plotBand);
        xAxis.setTitle(new AxisTitle(""));

        LinkedHashSet<XAxis> xAxes = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxes.add(xAxis);
        chartConfig.setXAxes(xAxes);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setShowFirstLabel(false);
        yAxis.setMin(0.6);
        yAxis.setGrid(new Grid());
        yAxis.getGrid().setLineWidth(0);
        yAxis.setLabel(new YAxisDataLabel(false));
        yAxis.setTitle(new AxisTitle(""));

        LinkedHashSet<YAxis> yAxes = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxes.add(yAxis);
        chartConfig.setYAxes(yAxes);

        chartConfig.getTooltip().setFormatterJsFunc(
                "function() { return false; }");

        chartConfig.getLegend().setEnabled(false);
        chartConfig.getCredit().setEnabled(false);

        // Plot options
        AreaConfig areaCfg = new AreaConfig();
        List<LinearColorStop> colorStops = new ArrayList<Gradient.LinearGradient.LinearColorStop>();
        colorStops.add(new LinearColorStop(0, new RGB(69, 114, 167)));
        colorStops.add(new LinearColorStop(1, new RGBA(0, 0, 0, 0)));
        // Fill color
        areaCfg.setFillColor(new Gradient.LinearGradient(0, 0, 0, 70,
                colorStops));
        areaCfg.setLineWidth(1);
        areaCfg.setMarker(new SymbolMarker(false));
        areaCfg.setShadow(false);
        areaCfg.setEnableMouseTracking(false);
        areaCfg.setHoverState(new SeriesState());
        areaCfg.getHoverState().setLineWidth(1);
        chartConfig.addSeriesConfig(areaCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        // Provide methods to set pointInterval and pointStart and delegate call
        // to SeriesConfig
        AreaConfig seriesDataCfg = new AreaConfig();
        seriesDataCfg.setPointInterval(24 * 3600 * 1000.0);
        seriesDataCfg.setPointStart((double) masterChartMinDate.getTime());
        DateTimeSeries masterChartSeries = new DateTimeSeries("USD to EUR",
                SeriesType.AREA, seriesDataCfg);
        masterChartSeries
                .setSeriesPoints(getMasterDetailData(masterChartSeries));
        chart.addSeries(masterChartSeries);

        return chart;
    }

    private void showLine() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.LINE);
        chartConfig.getGeneralChartConfig().setMargin(new Margin());
        chartConfig.getGeneralChartConfig().getMargin().setRight(130);
        chartConfig.getGeneralChartConfig().getMargin().setBottom(25);

        chartConfig.getTitle().setX(-20);
        chartConfig.getTitle().setText("Monthly Average Temperature");
        chartConfig.getSubtitle().setText("Source: WorldClimate.com");
        chartConfig.getTitle().setX(-20);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setCategories(Arrays.asList("Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(categoryAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis numberYAxis = new NumberYAxis();
        numberYAxis.setTitle(new AxisTitle("Temperature (°C)"));
        NumberPlotLine plotLine = new NumberPlotLine("TempAt0");
        plotLine.setValue(new NumberValue(0.0));
        plotLine.setWidth(1);
        plotLine.setZIndex(1);
        plotLine.setColor(new RGB(128, 128, 128));
        numberYAxis.addPlotLine(plotLine);
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(numberYAxis);
        chartConfig.setYAxes(yAxesSet);

        Legend legend = new Legend();
        legend.setLayout(Layout.VERTICAL);
        Position legendPos = new Position();
        legendPos.setAlign(HorzAlign.RIGHT);
        legendPos.setVertAlign(VertAlign.TOP);
        legendPos.setX(-10);
        legendPos.setY(100);
        legend.setPosition(legendPos);
        legend.setBorderWidth(0);
        chartConfig.setLegend(legend);

        // Series data label formatter
        LineConfig lineCfg = new LineConfig();
        chartConfig.addSeriesConfig(lineCfg);
        // Tooltip formatter
        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() { "
                                + " return '<b>' + this.series.name + '</b><br/>' +  this.x + ': '+ this.y +'°C'"
                                + "}");

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries seriesData = new XYSeries("Tokyo");
        seriesData.setSeriesPoints(getPoints(seriesData, 7.0, 6.9, 9.5, 14.5,
                18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("New York");
        seriesData.setSeriesPoints(getPoints(seriesData, -0.2, 0.8, 5.7, 11.3,
                17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Berlin");
        seriesData.setSeriesPoints(getPoints(seriesData, -0.9, 0.6, 3.5, 8.4,
                13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("London");
        seriesData.setSeriesPoints(getPoints(seriesData, 3.9, 4.2, 5.7, 8.5,
                11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8));
        chart.addSeries(seriesData);

        addChart(chart);
    }

    private void showClickToAddPoint() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.SCATTER);
        chartConfig.getGeneralChartConfig().setMargin(
                new Margin(70, 50, 60, 80));

        chartConfig.getTitle().setText("User supplied data");
        chartConfig
                .getSubtitle()
                .setText(
                        "Click the plot area to add a point. Click a point to remove it.");

        NumberXAxis xAxis = new NumberXAxis();
        xAxis.setMinPadding(0.2);
        xAxis.setMaxPadding(0.2);
        xAxis.setMaxZoom(60);
        LinkedHashSet<XAxis> xAxes = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxes.add(xAxis);
        chartConfig.setXAxes(xAxes);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Value"));
        yAxis.setMinPadding(0.2);
        yAxis.setMaxPadding(0.2);
        yAxis.setMaxZoom(60);
        NumberPlotLine plotLine = new NumberPlotLine("At0");
        plotLine.setValue(new NumberValue(0.0));
        plotLine.setWidth(1);
        plotLine.setColor(new RGB(128, 128, 128));
        yAxis.addPlotLine(plotLine);
        LinkedHashSet<YAxis> yAxes = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxes.add(yAxis);
        chartConfig.setYAxes(yAxes);

        chartConfig.getLegend().setEnabled(false);

        ScatterConfig scatterCfg = new ScatterConfig();
        scatterCfg.setLineWidth(1);
        chartConfig.addSeriesConfig(scatterCfg);
        // chart data
        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries seriesData = new XYSeries("User Supplied Data");
        seriesData.addPoint(new DecimalPoint(seriesData, 20, 20));
        seriesData.addPoint(new DecimalPoint(seriesData, 80, 80));
        chart.addSeries(seriesData);

        chart.addListener(new ChartClickListener() {

            @Override
            public void chartClick(ChartClickEvent chartClickEvent) {
                logEventInfo("chartClick",
                        ((DecimalPoint) chartClickEvent.getPoint()).getX(),
                        ((DecimalPoint) chartClickEvent.getPoint()).getY(),
                        chartClickEvent.getMousePosition().getMouseX(),
                        chartClickEvent.getMousePosition().getMouseY());
                XYSeries xySeries = (XYSeries) chartClickEvent.getChart()
                        .getSeries("User Supplied Data");
                xySeries.addPoint(new DecimalPoint(xySeries,
                        ((DecimalPoint) chartClickEvent.getPoint()).getX(),
                        ((DecimalPoint) chartClickEvent.getPoint()).getY()));
            }
        });

        chart.addListener(new PointClickListener() {

            @Override
            public void pointClick(PointClickEvent pointClickEvent) {
                logEventInfo("pointClick", pointClickEvent.getPoint()
                        .getSeries().getName(), pointClickEvent.getCategory(),
                        (Double) pointClickEvent.getPoint().getX(),
                        (Double) pointClickEvent.getPoint().getY(),
                        pointClickEvent.getMousePosition().getMouseX(),
                        pointClickEvent.getMousePosition().getMouseY());
                XYSeries xySeries = (XYSeries) pointClickEvent.getChart()
                        .getSeries("User Supplied Data");
                if (xySeries.getPoints().size() > 1) {
                    // remove the clicked point
                    xySeries.removePoint((DecimalPoint) pointClickEvent
                            .getPoint());
                }
            }
        });

        addChart(chart, false, false);

    }

    private void showLineWithDataLabels() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setMargin(new Margin());

        chartConfig.getTitle().setText("Monthly Average Temperature");
        chartConfig.getSubtitle().setText("Source: WorldClimate.com");

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setCategories(Arrays.asList("Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(categoryAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis numberYAxis = new NumberYAxis();
        numberYAxis.setTitle(new AxisTitle("Temperature (°C)"));
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(numberYAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig.getTooltip().setEnabled(false);

        // Series data label formatter
        LineConfig lineCfg = new LineConfig();
        lineCfg.setDataLabel(new DataLabel());
        lineCfg.getDataLabel().setEnabled(true);
        lineCfg.setEnableMouseTracking(false);
        chartConfig.addSeriesConfig(lineCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries seriesData = new XYSeries("Tokyo");
        seriesData.setSeriesPoints(getPoints(seriesData, 7.0, 6.9, 9.5, 14.5,
                18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("London");
        seriesData.setSeriesPoints(getPoints(seriesData, 3.9, 4.2, 5.7, 8.5,
                11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8));
        chart.addSeries(seriesData);

        addChart(chart);
    }

    private void showBarStacked() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.BAR);

        chartConfig.getTitle().setText("Stacked bar chart");

        CategoryAxis xAxis = new CategoryAxis();
        List<String> categories = Arrays.asList("Apples", "Oranges", "Pears",
                "Grapes", "Bananas");
        xAxis.setCategories(categories);
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis numberYAxis = new NumberYAxis();
        numberYAxis.setMin(0.0);
        numberYAxis.setTitle(new AxisTitle("Total fruit consumption"));
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(numberYAxis);
        chartConfig.setYAxes(yAxesSet);

        Legend legend = new Legend();
        legend.setBackgroundColor(new RGB(255, 255, 255));
        legend.setReversed(true);
        chartConfig.setLegend(legend);

        chartConfig.getTooltip().setFormatterJsFunc(
                "function() {"
                        + " return ''+ this.series.name +': '+ this.y +''; "
                        + "}");

        SeriesConfig seriesCfg = new SeriesConfig();
        seriesCfg.setStacking(Stacking.NORMAL);
        chartConfig.addSeriesConfig(seriesCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries seriesData = new XYSeries("John");
        seriesData.setSeriesPoints(getPoints(seriesData, 5, 3, 4, 7, 2));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Jane");
        seriesData.setSeriesPoints(getPoints(seriesData, 2, 2, 3, 2, 1));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Joe");
        seriesData.setSeriesPoints(getPoints(seriesData, 3, 4, 4, 2, 5));
        chart.addSeries(seriesData);

        addChart(chart);

    }

    private void showBarBasic() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.BAR);

        chartConfig.getTitle().setText("Historic World Population by Region");
        chartConfig.getSubtitle().setText("Source: Wikipedia.org");

        CategoryAxis xAxisMain = new CategoryAxis();
        List<String> categories = Arrays.asList("Africa", "America", "Asia",
                "Europe", "Oceania");
        xAxisMain.setCategories(categories);
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxisMain);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setMin(0.0);
        yAxis.setTitle(new AxisTitle("Population (millions)"));
        yAxis.getTitle().setAlign(AxisTitleAlign.HIGH);
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '' + this.series.name +': '+ this.y +' millions';"
                                + "}");

        BarConfig barCfg = new BarConfig();
        barCfg.setDataLabel(new DataLabel());
        chartConfig.addSeriesConfig(barCfg);

        Legend legend = new Legend();
        legend.setLayout(Layout.VERTICAL);
        legend.setPosition(new Position());
        legend.getPosition().setAlign(HorzAlign.RIGHT);
        legend.getPosition().setVertAlign(VertAlign.TOP);
        legend.getPosition().setX(-100);
        legend.getPosition().setY(100);
        legend.setFloating(true);
        legend.setBorderWidth(1);
        legend.setBackgroundColor(new RGB(255, 255, 255));
        legend.setShadow(true);
        chartConfig.setLegend(legend);

        chartConfig.getCredit().setEnabled(false);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries seriesData = new XYSeries("Year 1800");
        seriesData.setSeriesPoints(getPoints(seriesData, 107, 31, 635, 203, 2));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Year 1900");
        seriesData
                .setSeriesPoints(getPoints(seriesData, 133, 156, 947, 408, 6));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Year 2008");
        seriesData.setSeriesPoints(getPoints(seriesData, 973, 914, 4054, 732,
                34));
        chart.addSeries(seriesData);

        addChart(chart);

    }

    private void showBarWithNegStack() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.BAR);

        chartConfig.getTitle().setText(
                "Population pyramid for Germany, midyear 2010");
        chartConfig.getSubtitle().setText("Source: www.census.gov");

        CategoryAxis xAxisMain = new CategoryAxis();
        List<String> categories = Arrays.asList("0-4", "5-9", "10-14", "15-19",
                "20-24", "25-29", "30-34", "35-39", "40-44", "45-49", "50-54",
                "55-59", "60-64", "65-69", "70-74", "75-79", "80-84", "85-89",
                "90-94", "95-99", "100 +");
        xAxisMain.setCategories(categories);
        xAxisMain.setReversed(false);
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        // Opposite axis
        xAxesSet.add(xAxisMain);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(categories);
        xAxis.setOpposite(true);
        xAxis.setReversed(false);
        xAxis.setLinkedTo(xAxisMain);
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle(""));
        yAxis.setMin(-4000000.0);
        yAxis.setMax(4000000.0);
        yAxis.setLabel(new YAxisDataLabel());
        yAxis.getLabel().setFormatterJsFunc(
                "function() {"
                        + " return (Math.abs(this.value) / 1000000) + 'M';"
                        + " }");

        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatterJsFunc("function() {"
                + " return '<b>'+ this.series.name +', age '+ this.point.category +'</b><br/>' + "
                + " 'Population: '+ Highcharts.numberFormat(Math.abs(this.point.y), 0); "
                + "}");

        SeriesConfig series = new SeriesConfig();
        series.setStacking(Stacking.NORMAL);
        chartConfig.addSeriesConfig(series);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries seriesData = new XYSeries("Male");
        seriesData.setSeriesPoints(getPoints(seriesData, -1746181, -1884428,
                -2089758, -2222362, -2537431, -2507081, -2443179, -2664537,
                -3556505, -3680231, -3143062, -2721122, -2229181, -2227768,
                -2176300, -1329968, -836804, -354784, -90569, -28367, -3878));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Female");
        seriesData.setSeriesPoints(getPoints(seriesData, 1656154, 1787564,
                1981671, 2108575, 2403438, 2366003, 2301402, 2519874, 3360596,
                3493473, 3050775, 2759560, 2304444, 2426504, 2568938, 1785638,
                1447162, 1005011, 330870, 130632, 21208));
        chart.addSeries(seriesData);

        addChart(chart);
    }

    private void showColumnBasic() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.COLUMN);

        chartConfig.getTitle().setText("Monthly Average Rainfall");
        chartConfig.getSubtitle().setText("Source: WorldClimate.com");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setMin(0.0);
        yAxis.setTitle(new AxisTitle("Rainfall (mm)"));
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        Legend legend = new Legend();
        legend.setFloating(true);
        legend.setLayout(Layout.VERTICAL);
        legend.setPosition(new Position());
        legend.getPosition().setAlign(HorzAlign.LEFT);
        legend.getPosition().setVertAlign(VertAlign.TOP);
        legend.getPosition().setX(100);
        legend.getPosition().setY(70);
        legend.setShadow(true);
        legend.setBackgroundColor(new RGB(255, 255, 255));
        chartConfig.setLegend(legend);

        chartConfig.getTooltip().setFormatterJsFunc(
                "function() {" + " return '' + this.x +': '+ this.y +' mm'; "
                        + "}");

        ColumnConfig colCfg = new ColumnConfig();
        colCfg.setPointPadding(0.2);
        colCfg.setBorderWidth(0);
        chartConfig.addSeriesConfig(colCfg);

        InvientCharts chart = new InvientCharts(chartConfig);
        XYSeries seriesData = new XYSeries("Tokyo");
        seriesData.setSeriesPoints(getPoints(seriesData, 49.9, 71.5, 106.4,
                129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("New York");
        seriesData.setSeriesPoints(getPoints(seriesData, 83.6, 78.8, 98.5,
                93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("London");
        seriesData.setSeriesPoints(getPoints(seriesData, 48.9, 38.8, 39.3,
                41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Berlin");
        seriesData.setSeriesPoints(getPoints(seriesData, 42.4, 33.2, 34.5,
                39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1));
        chart.addSeries(seriesData);

        addChart(chart);

    }

    private void showColumnWithNegValues() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.COLUMN);

        chartConfig.getTitle().setText("Column chart with negative values");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Apples", "Oranges", "Pears",
                "Grapes", "Bananas"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatterJsFunc("function() {"
                + " return '' + this.series.name +': '+ this.y +''; " + "}");
        chartConfig.setTooltip(tooltip);
        chartConfig.getCredit().setEnabled(false);

        InvientCharts chart = new InvientCharts(chartConfig);
        XYSeries seriesData = new XYSeries("John");
        seriesData.setSeriesPoints(getPoints(seriesData, 5, 3, 4, 7, 2));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Jane");
        seriesData.setSeriesPoints(getPoints(seriesData, 2, -2, -3, 2, 1));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Joe");
        seriesData.setSeriesPoints(getPoints(seriesData, 3, 4, 4, -2, 5));
        chart.addSeries(seriesData);

        addChart(chart);
    }

    private void showColumnStacked() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.COLUMN);

        chartConfig.getTitle().setText("Stacked column chart");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Apples", "Oranges", "Pears",
                "Grapes", "Bananas"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setMin(0.0);
        yAxis.setTitle(new AxisTitle("Total fruit consumption"));
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        Legend legend = new Legend();
        legend.setPosition(new Position());
        legend.getPosition().setAlign(HorzAlign.RIGHT);
        legend.getPosition().setVertAlign(VertAlign.TOP);
        legend.getPosition().setX(-100);
        legend.getPosition().setY(20);
        legend.setFloating(true);
        legend.setBackgroundColor(new RGB(255, 255, 255));
        legend.setBorderWidth(1);
        legend.setShadow(true);
        chartConfig.setLegend(legend);

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '<b>'+ this.x +'</b><br/>'+ this.series.name +': '+ this.y +'<br/>'+"
                                + "        'Total: '+ this.point.stackTotal; "
                                + "}");

        ColumnConfig colCfg = new ColumnConfig();
        colCfg.setStacking(Stacking.NORMAL);
        chartConfig.addSeriesConfig(colCfg);

        InvientCharts chart = new InvientCharts(chartConfig);
        XYSeries seriesData = new XYSeries("John");
        seriesData.setSeriesPoints(getPoints(seriesData, 5, 3, 4, 7, 2));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Jane");
        seriesData.setSeriesPoints(getPoints(seriesData, 2, 2, 3, 2, 1));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Joe");
        seriesData.setSeriesPoints(getPoints(seriesData, 3, 4, 4, 2, 5));
        chart.addSeries(seriesData);

        addChart(chart);
    }

    private void showColumnStackedAndGrouped() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.COLUMN);

        chartConfig.getTitle().setText(
                "Total fruit consumtion, grouped by gender");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Apples", "Oranges", "Pears",
                "Grapes", "Bananas"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setAllowDecimals(false);
        yAxis.setMin(0.0);
        yAxis.setTitle(new AxisTitle("Number of fruits"));
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        ColumnConfig series = new ColumnConfig();
        series.setStacking(Stacking.NORMAL);
        chartConfig.addSeriesConfig(series);

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '<b>'+ this.x +'</b><br/>'+ this.series.name +': '+ this.y +'<br/>'+ 'Total: '+ this.point.stackTotal;"
                                + "}");

        InvientCharts chart = new InvientCharts(chartConfig);
        XYSeries seriesData = new XYSeries("John");
        seriesData.setSeriesPoints(getPoints(seriesData, 5, 3, 4, 7, 2));
        seriesData.setStack("male");
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Joe");
        seriesData.setSeriesPoints(getPoints(seriesData, 3, 4, 4, 2, 5));
        seriesData.setStack("male");
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Jane");
        seriesData.setSeriesPoints(getPoints(seriesData, 2, 5, 6, 2, 1));
        seriesData.setStack("female");
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Janet");
        seriesData.setSeriesPoints(getPoints(seriesData, 3, 0, 4, 4, 3));
        seriesData.setStack("female");
        chart.addSeries(seriesData);

        addChart(chart);
    }

    private void showColumnStackedPercent() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.COLUMN);

        chartConfig.getTitle().setText("Stacked column chart");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Apples", "Oranges", "Pears",
                "Grapes", "Bananas"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setMin(0.0);
        yAxis.setTitle(new AxisTitle("Total fruit consumption"));
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        ColumnConfig series = new ColumnConfig();
        series.setStacking(Stacking.PERCENT);
        chartConfig.addSeriesConfig(series);

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '' + this.series.name +': '+ this.y +' ('+ Math.round(this.percentage) +'%)'; "
                                + "}");

        InvientCharts chart = new InvientCharts(chartConfig);
        XYSeries seriesData = new XYSeries("John");
        seriesData.setSeriesPoints(getPoints(seriesData, 5, 3, 4, 7, 2));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Joe");
        seriesData.setSeriesPoints(getPoints(seriesData, 3, 4, 4, 2, 5));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Jane");
        seriesData.setSeriesPoints(getPoints(seriesData, 2, 2, 3, 2, 1));
        chart.addSeries(seriesData);

        addChart(chart);
    }

    private void showColumnWithRotatedLabels() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.COLUMN);
        chartConfig.getGeneralChartConfig().setMargin(new Margin());
        chartConfig.getGeneralChartConfig().getMargin().setTop(50);
        chartConfig.getGeneralChartConfig().getMargin().setRight(50);
        chartConfig.getGeneralChartConfig().getMargin().setBottom(100);
        chartConfig.getGeneralChartConfig().getMargin().setLeft(80);

        chartConfig.getTitle().setText("World\'s largest cities per 2008");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Tokyo", "Jakarta", "New York",
                "Seoul", "Manila", "Mumbai", "Sao Paulo", "Mexico City",
                "Dehli", "Osaka", "Cairo", "Kolkata", "Los Angeles",
                "Shanghai", "Moscow", "Beijing", "Buenos Aires", "Guangzhou",
                "Shenzhen", "Istanbul"));
        xAxis.setLabel(new XAxisDataLabel());
        xAxis.getLabel().setRotation(-45);
        xAxis.getLabel().setAlign(HorzAlign.RIGHT);
        xAxis.getLabel()
                .setStyle("{ font: 'normal 13px Verdana, sans-serif' }");
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setMin(0.0);
        yAxis.setTitle(new AxisTitle("Population (millions)"));
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig.setLegend(new Legend(false));

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '<b>'+ this.x +'</b><br/>'+ 'Population in 2008: '+ $wnd.Highcharts.numberFormat(this.y, 1) + "
                                + " ' millions' " + "}");

        InvientCharts chart = new InvientCharts(chartConfig);

        ColumnConfig colCfg = new ColumnConfig();
        colCfg.setDataLabel(new DataLabel());
        colCfg.getDataLabel().setRotation(-90);
        colCfg.getDataLabel().setAlign(HorzAlign.RIGHT);
        colCfg.getDataLabel().setX(-3);
        colCfg.getDataLabel().setY(10);
        colCfg.getDataLabel().setColor(new RGB(255, 255, 255));
        colCfg.getDataLabel().setFormatterJsFunc(
                "function() {" + " return this.y; " + "}");
        colCfg.getDataLabel().setStyle(
                " { font: 'normal 13px Verdana, sans-serif' } ");
        XYSeries seriesData = new XYSeries("Population", colCfg);
        seriesData.setSeriesPoints(getPoints(seriesData, 34.4, 21.8, 20.1, 20,
                19.6, 19.5, 19.1, 18.4, 18, 17.3, 16.8, 15, 14.7, 14.5, 13.3,
                12.8, 12.4, 11.8, 11.7, 11.2));

        chart.addSeries(seriesData);

        addChart(chart);
    }

    private void showAreaWithNegValues() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.AREA);

        chartConfig.getTitle().setText("Area chart with negative values");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Apples", "Oranges", "Pears",
                "Grapes", "Bananas"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        chartConfig.getCredit().setEnabled(false);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries series = new XYSeries("John");
        series.setSeriesPoints(getPoints(series, 5, 3, 4, 7, 2));
        chart.addSeries(series);

        series = new XYSeries("Jane");
        series.setSeriesPoints(getPoints(series, 2, -2, -3, 2, 1));
        chart.addSeries(series);

        series = new XYSeries("Joe");
        series.setSeriesPoints(getPoints(series, 3, 4, 4, -2, 5));
        chart.addSeries(series);

        addChart(chart);
    }

    private void showAreaInvertedAxes() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.AREA);
        chartConfig.getGeneralChartConfig().setInverted(true);

        chartConfig.getTitle().setText(
                "Average fruit consumption during one week");
        chartConfig.getSubtitle().setStyle(
                "{ position: 'absolute', right: '0px', bottom: '10px'}");

        Legend legend = new Legend();
        legend.setFloating(true);
        legend.setLayout(Layout.VERTICAL);
        legend.setPosition(new Position());
        legend.getPosition().setAlign(HorzAlign.RIGHT);
        legend.getPosition().setVertAlign(VertAlign.TOP);
        legend.getPosition().setX(-150);
        legend.getPosition().setY(100);
        legend.setBorderWidth(1);
        legend.setBackgroundColor(new RGB(255, 255, 255));
        chartConfig.setLegend(legend);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Number of units"));
        yAxis.setMin(0.0);
        yAxis.setLabel(new YAxisDataLabel());
        yAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value; " + "}");
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig.getTooltip().setFormatterJsFunc(
                "function() {" + " return '' + this.x + ': ' + this.y; " + "}");

        AreaConfig areaCfg = new AreaConfig();
        areaCfg.setFillOpacity(0.5);
        chartConfig.addSeriesConfig(areaCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries series = new XYSeries("John");
        series.setSeriesPoints(getPoints(series, 3, 4, 3, 5, 4, 10, 12));
        chart.addSeries(series);

        series = new XYSeries("Jane");
        series.setSeriesPoints(getPoints(series, 1, 3, 4, 3, 3, 5, 4));
        chart.addSeries(series);

        addChart(chart);
    }

    private void showAreaWithMissingPoints() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.AREA);
        chartConfig.getGeneralChartConfig().setSpacing(new Spacing());
        chartConfig.getGeneralChartConfig().getSpacing().setBottom(30);

        chartConfig.getTitle().setText("Fruit consumption *");
        chartConfig.getSubtitle().setText(
                "* Jane\'s banana consumption is unknown");
        chartConfig.getSubtitle().setFloating(true);
        chartConfig.getSubtitle().setAlign(HorzAlign.RIGHT);
        chartConfig.getSubtitle().setVertAlign(VertAlign.BOTTOM);
        chartConfig.getSubtitle().setY(15);

        Legend legend = new Legend();
        legend.setFloating(true);
        legend.setLayout(Layout.VERTICAL);
        legend.setPosition(new Position());
        legend.getPosition().setAlign(HorzAlign.LEFT);
        legend.getPosition().setVertAlign(VertAlign.TOP);
        legend.getPosition().setX(150);
        legend.getPosition().setY(100);
        legend.setBorderWidth(1);
        legend.setBackgroundColor(new RGB(255, 255, 255));
        chartConfig.setLegend(legend);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Apples", "Pears", "Oranges",
                "Bananas", "Grapes", "Plums", "Strawberries", "Raspberries"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Y-Axis"));
        yAxis.setLabel(new YAxisDataLabel());
        yAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value; " + "}");
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '<b>'+ this.series.name +'</b><br/>'+ this.x +': '+ this.y;"
                                + "}");

        chartConfig.getCredit().setEnabled(false);

        AreaConfig areaCfg = new AreaConfig();
        areaCfg.setFillOpacity(0.5);
        chartConfig.addSeriesConfig(areaCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries series = new XYSeries("John");
        series.setSeriesPoints(getPoints(series, 0, 1, 4, 4, 5, 2, 3, 7));
        chart.addSeries(series);

        series = new XYSeries("Jane");
        series.addPoint(new DecimalPoint(series, 1.0), new DecimalPoint(series,
                0.0), new DecimalPoint(series, 3.0), new DecimalPoint(series),
                new DecimalPoint(series, 3.0), new DecimalPoint(series, 1.0),
                new DecimalPoint(series, 2.0), new DecimalPoint(series, 1.0));
        chart.addSeries(series);

        addChart(chart);
    }

    private void showAreaStacked() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.AREA);

        chartConfig.getTitle().setText(
                "Historic and Estimated Worldwide Population Growth by Region");
        chartConfig.getSubtitle().setText("Source: Wikipedia.org");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("1750", "1800", "1850", "1900",
                "1950", "1999", "2050"));
        Tick tick = new Tick();
        tick.setPlacement(TickmarkPlacement.ON);
        xAxis.setTick(tick);
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Billions"));
        yAxis.setLabel(new YAxisDataLabel());
        yAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value / 1000; " + "}");

        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return ''+ this.x +': '+ $wnd.Highcharts.numberFormat(this.y, 0, ',') +' millions';"
                                + "}");

        AreaConfig areaCfg = new AreaConfig();
        areaCfg.setStacking(Stacking.NORMAL);
        areaCfg.setLineColor(new RGB(102, 102, 102));
        areaCfg.setLineWidth(1);

        SymbolMarker marker = new SymbolMarker();
        marker.setLineColor(new RGB(102, 102, 102));
        marker.setLineWidth(1);
        areaCfg.setMarker(marker);

        chartConfig.addSeriesConfig(areaCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries series = new XYSeries("Asia");
        series.setSeriesPoints(getPoints(series, 502, 635, 809, 947, 1402,
                3634, 5268));
        chart.addSeries(series);

        series = new XYSeries("Africa");
        series.setSeriesPoints(getPoints(series, 106, 107, 111, 133, 221, 767,
                1766));
        chart.addSeries(series);

        series = new XYSeries("Europe");
        series.setSeriesPoints(getPoints(series, 163, 203, 276, 408, 547, 729,
                628));
        chart.addSeries(series);

        series = new XYSeries("America");
        series.setSeriesPoints(getPoints(series, 18, 31, 54, 156, 339, 818,
                1201));
        chart.addSeries(series);

        series = new XYSeries("Oceania");
        series.setSeriesPoints(getPoints(series, 2, 2, 2, 6, 13, 30, 46));
        chart.addSeries(series);

        addChart(chart);
    }

    private void showAreaPercent() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.AREA);

        chartConfig
                .getTitle()
                .setText(
                        "Historic and Estimated Worldwide Population Distribution by Region");
        chartConfig.getSubtitle().setText("Source: Wikipedia.org");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("1750", "1800", "1850", "1900",
                "1950", "1999", "2050"));
        Tick tick = new Tick();
        tick.setPlacement(TickmarkPlacement.ON);
        xAxis.setTick(tick);
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Percent"));
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '' + this.x +': ' + $wnd.Highcharts.numberFormat(this.percentage, 1) + "
                                + "    '% ('+ $wnd.Highcharts.numberFormat(this.y, 0, ',') +' millions)'; "
                                + "}");

        AreaConfig areaCfg = new AreaConfig();
        areaCfg.setStacking(Stacking.PERCENT);
        areaCfg.setLineColor(new RGB(255, 255, 255));
        areaCfg.setLineWidth(1);

        SymbolMarker marker = new SymbolMarker();
        marker.setLineColor(new RGB(255, 255, 255));
        marker.setLineWidth(1);
        areaCfg.setMarker(marker);

        chartConfig.addSeriesConfig(areaCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries series = new XYSeries("Asia");
        series.setSeriesPoints(getPoints(series, 502, 635, 809, 947, 1402,
                3634, 5268));
        chart.addSeries(series);

        series = new XYSeries("Africa");
        series.setSeriesPoints(getPoints(series, 106, 107, 111, 133, 221, 767,
                1766));
        chart.addSeries(series);

        series = new XYSeries("Europe");
        series.setSeriesPoints(getPoints(series, 163, 203, 276, 408, 547, 729,
                628));
        chart.addSeries(series);

        series = new XYSeries("America");
        series.setSeriesPoints(getPoints(series, 18, 31, 54, 156, 339, 818,
                1201));
        chart.addSeries(series);

        series = new XYSeries("Oceania");
        series.setSeriesPoints(getPoints(series, 2, 2, 2, 6, 13, 30, 46));
        chart.addSeries(series);

        addChart(chart);
    }

    private void showAreaBasic() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.AREA);

        chartConfig.getTitle().setText("US and USSR nuclear stockpiles");
        // 
        chartConfig
                .getSubtitle()
                .setText(
                        "Source: <a href='http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf'>thebulletin.metapress.com</a>");

        NumberXAxis xAxis = new NumberXAxis();
        xAxis.setLabel(new XAxisDataLabel());
        xAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value;" + "}");

        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Nuclear weapon states"));
        yAxis.setLabel(new YAxisDataLabel());
        yAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value / 1000 +'k';" + "}");

        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return this.series.name +' produced <b>'+"
                                + "    $wnd.Highcharts.numberFormat(this.y, 0) +'</b><br/>warheads in '+ this.x;"
                                + "}");

        AreaConfig areaCfg = new AreaConfig();
        areaCfg.setPointStart(1940.0);
        SymbolMarker marker = new SymbolMarker();
        areaCfg.setMarker(marker);
        marker.setEnabled(false);
        marker.setSymbol(Symbol.CIRCLE);
        marker.setRadius(2);
        marker.setHoverState(new MarkerState(true));
        chartConfig.addSeriesConfig(areaCfg);
        InvientCharts chart = new InvientCharts(chartConfig);

        // Series -
        AreaConfig usaAreaCfg = new AreaConfig();
        usaAreaCfg.setPointStart(1940.0);
        XYSeries series = new XYSeries("USA", usaAreaCfg);

        LinkedHashSet<DecimalPoint> points = new LinkedHashSet<InvientCharts.DecimalPoint>();
        addNullPoints(points, series, 5);
        points.addAll(getPoints(series, 6, 11, 32, 110, 235, 369, 640, 1005,
                1436, 2063, 3057, 4618, 6444, 9822, 15468, 20434, 24126, 27387,
                29459, 31056, 31982, 32040, 31233, 29224, 27342, 26662, 26956,
                27912, 28999, 28965, 27826, 25579, 25722, 24826, 24605, 24304,
                23464, 23708, 24099, 24357, 24237, 24401, 24344, 23586, 22380,
                21004, 17287, 14747, 13076, 12555, 12144, 11009, 10950, 10871,
                10824, 10577, 10527, 10475, 10421, 10358, 10295, 10104));
        series.setSeriesPoints(points);
        chart.addSeries(series);

        // Series -
        AreaConfig russiaAreaCfg = new AreaConfig();
        russiaAreaCfg.setPointStart(1940.0);
        series = new XYSeries("USSR/Russia", russiaAreaCfg);
        points = new LinkedHashSet<InvientCharts.DecimalPoint>();
        addNullPoints(points, series, 10);
        points.addAll(getPoints(series, 5, 25, 50, 120, 150, 200, 426, 660,
                869, 1060, 1605, 2471, 3322, 4238, 5221, 6129, 7089, 8339,
                9399, 10538, 11643, 13092, 14478, 15915, 17385, 19055, 21205,
                23044, 25393, 27935, 30062, 32049, 33952, 35804, 37431, 39197,
                45000, 43000, 41000, 39000, 37000, 35000, 33000, 31000, 29000,
                27000, 25000, 24000, 23000, 22000, 21000, 20000, 19000, 18000,
                18000, 17000, 16000));
        series.setSeriesPoints(points);
        chart.addSeries(series);

        addChart(chart);
    }

    private void addNullPoints(
            LinkedHashSet<InvientCharts.DecimalPoint> points, Series series,
            int howManyNullPoints) {
        for (int cnt = 0; cnt < howManyNullPoints; cnt++) {
            points.add(new DecimalPoint(series));
        }
    }

    private void showAreaSpline() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.AREASPLINE);

        chartConfig.getTitle().setText(
                "Average fruit consumption during one week");

        Legend legend = new Legend();
        legend.setLayout(Layout.VERTICAL);
        Position legendPos = new Position();
        legendPos.setAlign(HorzAlign.LEFT);
        legendPos.setVertAlign(VertAlign.TOP);
        legendPos.setX(150);
        legendPos.setY(100);
        legend.setPosition(legendPos);
        legend.setFloating(true);
        legend.setBorderWidth(1);
        legend.setBackgroundColor(new RGB(255, 255, 255));
        chartConfig.setLegend(legend);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"));
        NumberPlotBand plotBand = new NumberPlotBand("sat-sun");
        plotBand.setRange(new NumberRange(4.6, 6.5));
        plotBand.setColor(new RGBA(68, 170, 213, 0.2f));
        xAxis.addPlotBand(plotBand);

        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Fruit units"));

        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig.getCredit().setEnabled(false);

        AreaSplineConfig areaSpline = new AreaSplineConfig();
        areaSpline.setFillOpacity(0.5);
        chartConfig.addSeriesConfig(areaSpline);
        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries series = new XYSeries("John");
        series.setSeriesPoints(getPoints(series, 3, 4, 3, 5, 4, 10, 12));
        chart.addSeries(series);

        series = new XYSeries("Jane");
        series.setSeriesPoints(getPoints(series, 1, 3, 4, 3, 3, 5, 4));
        chart.addSeries(series);

        addChart(chart);
    }

    private void showPieWithLegend() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.PIE);

        chartConfig.getTitle().setText(
                "Browser market shares at a specific website, 2010");

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '<b>'+ this.point.name +'</b>: '+ this.y +' %'; "
                                + "}");

        PieConfig pie = new PieConfig();
        pie.setAllowPointSelect(true);
        pie.setCursor("pointer");
        pie.setDataLabel(new PieDataLabel(false));
        pie.setShowInLegend(true);
        chartConfig.addSeriesConfig(pie);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries series = new XYSeries("Browser Share");
        LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
        points.add(new DecimalPoint(series, "Firefox", 45.0));
        points.add(new DecimalPoint(series, "IE", 26.8));
        PointConfig config = new PointConfig(true);
        points.add(new DecimalPoint(series, "Chrome", 12.8, config));
        points.add(new DecimalPoint(series, "Safari", 8.5));
        points.add(new DecimalPoint(series, "Opera", 6.2));
        points.add(new DecimalPoint(series, "Others", 0.7));

        series.setSeriesPoints(points);
        chart.addSeries(series);

        addChart(chart);
    }

    //
    private void showDonut() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.PIE);

        chartConfig.getGeneralChartConfig().setMargin(new Margin());
        chartConfig.getGeneralChartConfig().getMargin().setTop(50);
        chartConfig.getGeneralChartConfig().getMargin().setRight(0);
        chartConfig.getGeneralChartConfig().getMargin().setBottom(0);
        chartConfig.getGeneralChartConfig().getMargin().setLeft(0);

        chartConfig.getTitle().setText(
                "Browser market shares at a specific website");
        chartConfig.getSubtitle().setText(
                "Inner circle: 2008, outer circle: 2010");

        chartConfig.getTooltip().setFormatterJsFunc(
                "function() {"
                        + " return '<b>'+ this.series.name +'</b><br/>'+ "
                        + "     this.point.name +': '+ this.y +' %'; " + "}");

        InvientCharts chart = new InvientCharts(chartConfig);
        // Series 1
        //
        PieConfig pieCfg = new PieConfig();
        pieCfg.setInnerSize(65);
        pieCfg.setDataLabel(new PieDataLabel(false));

        XYSeries series = new XYSeries("2008", SeriesType.PIE, pieCfg);
        LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
        points.add(getPointWithColor(series, "Firefox", 44.2, new RGB(69, 114,
                167)));
        points.add(getPointWithColor(series, "IE", 46.6, new RGB(170, 70, 67)));
        points.add(getPointWithColor(series, "Chrome", 3.1, new RGB(137, 165,
                78)));
        points.add(getPointWithColor(series, "Safari", 2.7, new RGB(128, 105,
                155)));
        points.add(getPointWithColor(series, "Opera", 2.3, new RGB(128, 105,
                155)));
        points.add(getPointWithColor(series, "Mozilla", 0.4, new RGB(219, 132,
                61)));
        series.setSeriesPoints(points);

        chart.addSeries(series);

        // Series 1
        pieCfg = new PieConfig();
        pieCfg.setInnerSize(150);
        pieCfg.setDataLabel(new PieDataLabel());
        pieCfg.setColor(new RGB(0, 0, 0));
        pieCfg.getDataLabel().setConnectorColor(new RGB(0, 0, 0));

        series = new XYSeries("2010", SeriesType.PIE, pieCfg);
        points = new LinkedHashSet<DecimalPoint>();
        points.add(getPointWithColor(series, "Firefox", 45.0, new RGB(69, 114,
                167)));
        points.add(getPointWithColor(series, "IE", 26.8, new RGB(170, 70, 67)));
        points.add(getPointWithColor(series, "Chrome", 12.8, new RGB(137, 165,
                78)));
        points.add(getPointWithColor(series, "Safari", 8.5, new RGB(128, 105,
                155)));
        points.add(getPointWithColor(series, "Opera", 6.2, new RGB(128, 105,
                155)));
        points.add(getPointWithColor(series, "Mozilla", 0.2, new RGB(219, 132,
                61)));
        series.setSeriesPoints(points);

        chart.addSeries(series);

        addChart(chart);
    }

    private DecimalPoint getPointWithColor(Series series, String name,
            double y, Color color) {
        DecimalPoint point = new DecimalPoint(series, name, y);
        point.setConfig(new PointConfig(color));
        return point;
    }

    private void showPie() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.PIE);
        chartConfig.getTitle().setText(
                "Browser market shares at a specific website, 2010");

        PieConfig pieCfg = new PieConfig();
        pieCfg.setAllowPointSelect(true);
        pieCfg.setCursor("pointer");
        pieCfg.setDataLabel(new PieDataLabel());
        pieCfg.getDataLabel().setEnabled(true);
        pieCfg.getDataLabel()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '<b>'+ this.point.name +'</b>: '+ this.y +' %';"
                                + "}");
        pieCfg.getDataLabel().setConnectorColor(new RGB(0, 0, 0));

        chartConfig.addSeriesConfig(pieCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries series = new XYSeries("Browser Share");
        LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
        points.add(new DecimalPoint(series, "Firefox", 45.0));
        points.add(new DecimalPoint(series, "IE", 26.8));
        PointConfig config = new PointConfig(true);
        points.add(new DecimalPoint(series, "Chrome", 12.8, config));
        points.add(new DecimalPoint(series, "Safari", 8.5));
        points.add(new DecimalPoint(series, "Opera", 6.2));
        points.add(new DecimalPoint(series, "Others", 0.7));

        series.setSeriesPoints(points);
        chart.addSeries(series);

        addChart(chart);
    }

    private void showScatter() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.SCATTER);
        chartConfig.getGeneralChartConfig().setZoomType(ZoomType.XY);

        chartConfig.getTitle().setText(
                "Height Versus Weight of Individuals by Gender");
        chartConfig.getSubtitle().setText("Source: Heinz  2003");

        chartConfig.getTooltip().setFormatterJsFunc(
                "function() {"
                        + " return '' + this.x + ' cm, ' + this.y + ' kg'; "
                        + "}");

        NumberXAxis xAxis = new NumberXAxis();
        xAxis.setTitle(new AxisTitle("Height (cm)"));
        xAxis.setStartOnTick(true);
        xAxis.setEndOnTick(true);
        xAxis.setShowLastLabel(true);
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Weight (kg)"));
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        Legend legend = new Legend();
        legend.setLayout(Layout.VERTICAL);
        Position legendPos = new Position();
        legendPos.setAlign(HorzAlign.LEFT);
        legendPos.setVertAlign(VertAlign.TOP);
        legendPos.setX(100);
        legendPos.setY(70);
        legend.setPosition(legendPos);
        legend.setFloating(true);
        legend.setBorderWidth(1);
        legend.setBackgroundColor(new RGB(255, 255, 255));
        chartConfig.setLegend(legend);

        ScatterConfig scatterCfg = new ScatterConfig();

        SymbolMarker marker = new SymbolMarker(5);
        scatterCfg.setMarker(marker);
        marker.setHoverState(new MarkerState());
        marker.getHoverState().setEnabled(true);
        marker.getHoverState().setLineColor(new RGB(100, 100, 100));
        chartConfig.addSeriesConfig(scatterCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        ScatterConfig femaleScatterCfg = new ScatterConfig();
        femaleScatterCfg.setColor(new RGBA(223, 83, 83, 0.5f));
        XYSeries series = new XYSeries("Female", femaleScatterCfg);
        series.setSeriesPoints(getScatterFemalePoints(series));
        chart.addSeries(series);

        ScatterConfig maleScatterCfg = new ScatterConfig();
        maleScatterCfg.setColor(new RGBA(119, 152, 191, 0.5f));
        series = new XYSeries("Male", maleScatterCfg);
        series.setSeriesPoints(getScatterMalePoints(series));
        chart.addSeries(series);
        addChart(chart);
    }

    private void showCombinationScatterWithRegressionLine() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();

        chartConfig.getTitle().setText("Scatter plot with regression line");

        NumberXAxis xAxis = new NumberXAxis();
        xAxis.setMin(-0.5);
        xAxis.setMax(5.5);
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setMin(0.0);
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        InvientCharts chart = new InvientCharts(chartConfig);

        // Line series
        LineConfig lineCfg = new LineConfig();
        lineCfg.setMarker(new SymbolMarker(false));
        lineCfg.setHoverState(new SeriesState());
        lineCfg.getHoverState().setLineWidth(0);

        XYSeries lineSeries = new XYSeries("Regression Line", lineCfg);
        lineSeries.setType(SeriesType.LINE);
        lineSeries.setSeriesPoints(getPoints(lineSeries,
                new double[] { 0, 1.11 }, new double[] { 5, 4.51 }));
        chart.addSeries(lineSeries);

        // Scatter series
        ScatterConfig scatterCfg = new ScatterConfig();
        scatterCfg.setMarker(new SymbolMarker(4));

        XYSeries scatterSeries = new XYSeries("Observations", scatterCfg);
        scatterSeries.setType(SeriesType.SCATTER);
        scatterSeries.setSeriesPoints(getPoints(scatterSeries, 1, 1.5, 2.8,
                3.5, 3.9, 4.2));
        chart.addSeries(scatterSeries);

        addChart(chart);
    }

    private void showSpline() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.SPLINE);
        chartConfig.getGeneralChartConfig().setInverted(true);
        chartConfig.getGeneralChartConfig().setWidth(500);

        chartConfig.getTitle().setText("Atmosphere Temperature by Altitude");
        chartConfig.getSubtitle().setText(
                "According to the Standard Atmosphere Model");

        NumberXAxis xAxis = new NumberXAxis();
        xAxis.setReversed(false);
        xAxis.setTitle(new AxisTitle("Altitude"));
        xAxis.setLabel(new XAxisDataLabel());
        xAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value +'km';" + "}");
        xAxis.setMaxPadding(0.05);
        xAxis.setShowLastLabel(true);
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Temperature"));
        yAxis.setLineWidth(2);
        yAxis.setLabel(new YAxisDataLabel());
        yAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value + '°C';" + "}");
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        //
        Tooltip tooltip = new Tooltip();
        tooltip.setFormatterJsFunc("function() {"
                + " return '' + this.x +' km: '+ this.y +'°C';" + "}");
        chartConfig.setTooltip(tooltip);

        Legend legend = new Legend();
        legend.setEnabled(false);
        chartConfig.setLegend(legend);

        SplineConfig splineCfg = new SplineConfig();
        splineCfg.setMarker(new SymbolMarker(true));
        chartConfig.addSeriesConfig(splineCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries series = new XYSeries("Temperature");
        series.setSeriesPoints(getPoints(series, new double[] { 0, 15 },
                new double[] { 10, -50 }, new double[] { 20, -56.5 },
                new double[] { 30, -46.5 }, new double[] { 40, -22.1 },
                new double[] { 50, -2.5 }, new double[] { 60, -27.7 },
                new double[] { 70, -55.7 }, new double[] { 80, -76.5 }));
        chart.addSeries(series);

        addChart(chart);
    }

    private void showSplineWithSymbol() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.SPLINE);

        chartConfig.getTitle().setText("Monthly Average Temperature");
        chartConfig.getSubtitle().setText("Source: WorldClimate.com");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Temperature"));
        yAxis.setLabel(new YAxisDataLabel());
        yAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value + '°';" + "}");
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        //
        Tooltip tooltip = new Tooltip();
        tooltip.setCrosshairs(new Tooltip.Crosshairs());
        tooltip.setShared(true);
        chartConfig.setTooltip(tooltip);

        SplineConfig splineCfg = new SplineConfig();
        SymbolMarker symbolMarker = new SymbolMarker(true);
        symbolMarker.setRadius(4);
        symbolMarker.setLineColor(new RGB(102, 102, 102));
        symbolMarker.setLineWidth(1);
        splineCfg.setMarker(symbolMarker);
        chartConfig.addSeriesConfig(splineCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        // Series
        splineCfg = new SplineConfig();
        splineCfg.setMarker(new SymbolMarker(Symbol.SQUARE));
        XYSeries series = new XYSeries("Tokyo", splineCfg);
        series.setSeriesPoints(getPoints(series, 7.0, 6.9, 9.5, 14.5, 18.2,
                21.5, 25.2));
        PointConfig config = new PointConfig(new ImageMarker(
                "/graphics/sun.png"));
        DecimalPoint highest = new DecimalPoint(series, 26.5, config);
        series.addPoint(highest);
        series.addPoint(new DecimalPoint(series, 23.3));
        series.addPoint(new DecimalPoint(series, 18.3));
        series.addPoint(new DecimalPoint(series, 13.9));
        series.addPoint(new DecimalPoint(series, 9.6));
        chart.addSeries(series);

        // Series
        splineCfg = new SplineConfig();
        splineCfg.setMarker(new SymbolMarker(Symbol.DIAMOND));
        series = new XYSeries("London", splineCfg);
        config = new PointConfig(new ImageMarker("/graphics/snow.png"));
        DecimalPoint lowest = new DecimalPoint(series, 3.9, config);
        series.addPoint(lowest);
        series.addPoint(new DecimalPoint(series, 4.2));
        series.addPoint(new DecimalPoint(series, 5.7));
        series.addPoint(new DecimalPoint(series, 8.5));
        series.addPoint(new DecimalPoint(series, 11.9));
        series.addPoint(new DecimalPoint(series, 15.2));
        series.addPoint(new DecimalPoint(series, 17.0));
        series.addPoint(new DecimalPoint(series, 16.6));
        series.addPoint(new DecimalPoint(series, 14.2));
        series.addPoint(new DecimalPoint(series, 10.3));
        series.addPoint(new DecimalPoint(series, 6.6));
        series.addPoint(new DecimalPoint(series, 4.8));
        chart.addSeries(series);

        addChart(chart);

    }

    private void showSplineUpdatingEachSecond() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.SPLINE);
        chartConfig.getGeneralChartConfig().setMargin(new Margin());
        chartConfig.getGeneralChartConfig().getMargin().setRight(10);

        chartConfig.getTitle().setText("Live random data");

        DateTimeAxis xAxis = new DateTimeAxis();
        xAxis.setTick(new Tick());
        xAxis.getTick().setPixelInterval(150);
        LinkedHashSet<XAxis> xAxes = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxes.add(xAxis);
        chartConfig.setXAxes(xAxes);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Value"));
        NumberPlotLine plotLine;
        yAxis.addPlotLine(plotLine = new NumberPlotLine("LineAt0"));
        plotLine.setValue(new NumberValue(0.0));
        plotLine.setWidth(1);
        plotLine.setColor(new RGB(128, 128, 128));
        LinkedHashSet<YAxis> yAxes = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxes.add(yAxis);
        chartConfig.setYAxes(yAxes);

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '<b>'+ this.series.name +'</b><br/>'+ "
                                + " $wnd.Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+ "
                                + " $wnd.Highcharts.numberFormat(this.y, 2);"
                                + "}");

        chartConfig.getLegend().setEnabled(false);

        InvientCharts chart = new InvientCharts(chartConfig);

        DateTimeSeries seriesData = new DateTimeSeries("Random Data", true);
        LinkedHashSet<DateTimePoint> points = new LinkedHashSet<InvientCharts.DateTimePoint>();
        Date dtNow = new Date();
        // Add random data.
        for (int cnt = -19; cnt <= 0; cnt++) {
            points.add(new DateTimePoint(seriesData, getUpdatedDate(dtNow,
                    cnt * 1000), Math.random()));
        }
        seriesData.setSeriesPoints(points);
        chart.addSeries(seriesData);

        addChart(chart, false, false, false);

        indicator = new ProgressIndicator(0.2f);
        indicator.setPollingInterval(1000);
        indicator.setStyleName("i-progressindicator-invisible");
        rightLayout.addComponent(indicator);

        if (!isAppRunningOnGAE()) {
            splineThread = new SelfUpdateSplineThread(chart);
            splineThread.start();
        } else {
            getApplication()
                    .getMainWindow()
                    .showNotification(
                            "This chart does not auto-update because Google App Engine does not support threads.");
        }
    }

    private void stopSplineSelfUpdateThread() {
        if (splineThread != null) {
            synchronized (getApplication()) {
                splineThread.stopUpdating();
                indicator.setEnabled(false);
                getApplication().notifyAll();
            }
        }
    }

    private SelfUpdateSplineThread splineThread;
    private ProgressIndicator indicator;

    private class SelfUpdateSplineThread extends Thread implements Serializable {
        InvientCharts chart;

        SelfUpdateSplineThread(InvientCharts chart) {
            this.chart = chart;
        }

        private volatile boolean keepUpdating = true;

        void stopUpdating() {
            keepUpdating = false;
            System.out.println("stopUpdating " + keepUpdating);
        }

        boolean keepUpdating() {
            return keepUpdating;
        }

        @Override
        public void run() {
            while (keepUpdating()) {
                try {
                    // Sleep for 1 second
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out
                            .println("InterruptedException occured. Exception message "
                                    + e.getMessage());
                }
                synchronized (getApplication()) {
                    DateTimeSeries seriesData = (DateTimeSeries) chart
                            .getSeries("Random Data");
                    seriesData.addPoint(new DateTimePoint(seriesData,
                            new Date(), Math.random()), true);
                }
                System.out.println("Inside run() keepUpdating " + keepUpdating);
            }
        }

    }

    private static Date getUpdatedDate(Date dt, long milliseconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dt.getTime() + milliseconds);
        return cal.getTime();
    }

    private void showSplineWithPlotBands() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setType(SeriesType.SPLINE);

        chartConfig.getTitle().setText("Wind speed during two days");
        chartConfig
                .getSubtitle()
                .setText(
                        "October 6th and 7th 2009 at two locations in Vik i Sogn, Norway");

        //
        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " return '' + $wnd.Highcharts.dateFormat('%e. %b %Y, %H:00', this.x) +': '+ this.y +' m/s'; "
                                + "}");

        DateTimeAxis xAxis = new DateTimeAxis();
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Wind speed (m/s)"));
        yAxis.setMin(0.0);
        yAxis.setMinorGrid(new MinorGrid());
        yAxis.getMinorGrid().setLineWidth(0);
        yAxis.setGrid(new Grid());
        yAxis.getGrid().setLineWidth(0);
        NumberPlotBand numberBand = new NumberPlotBand("Light air");
        numberBand.setRange(new NumberRange(0.3, 1.5));
        numberBand.setColor(new RGBA(68, 170, 213, 0.1f));
        numberBand.setLabel(new PlotLabel("Light air"));
        numberBand.getLabel().setStyle("{ color: '#606060' }");
        yAxis.getPlotBands().add(numberBand);

        numberBand = new NumberPlotBand("Light breeze");
        numberBand.setRange(new NumberRange(1.5, 3.3));
        numberBand.setColor(new RGBA(0, 0, 0, 0.0f));
        numberBand.setLabel(new PlotLabel("Light breeze"));
        numberBand.getLabel().setStyle("{ color: '#606060' }");
        yAxis.getPlotBands().add(numberBand);

        numberBand = new NumberPlotBand("Gentle breeze");
        numberBand.setRange(new NumberRange(3.3, 5.5));
        numberBand.setColor(new RGBA(68, 170, 213, 0.1f));
        numberBand.setLabel(new PlotLabel("Gentle breeze"));
        numberBand.getLabel().setStyle("{ color: '#606060' }");
        yAxis.getPlotBands().add(numberBand);

        numberBand = new NumberPlotBand("Moderate breeze");
        numberBand.setRange(new NumberRange(5.5, 8.0));
        numberBand.setColor(new RGBA(0, 0, 0, 0.0f));
        numberBand.setLabel(new PlotLabel("Moderate breeze"));
        numberBand.getLabel().setStyle("{ color: '#606060' }");
        yAxis.getPlotBands().add(numberBand);

        numberBand = new NumberPlotBand("Fresh breeze");
        numberBand.setRange(new NumberRange(8.0, 11.0));
        numberBand.setColor(new RGBA(68, 170, 213, 0.1f));
        numberBand.setLabel(new PlotLabel("Fresh breeze"));
        numberBand.getLabel().setStyle("{ color: '#606060' }");
        yAxis.getPlotBands().add(numberBand);

        numberBand = new NumberPlotBand("Strong breeze");
        numberBand.setRange(new NumberRange(11.0, 14.0));
        numberBand.setColor(new RGBA(0, 0, 0, 0.0f));
        numberBand.setLabel(new PlotLabel("Strong breeze"));
        numberBand.getLabel().setStyle("{ color: '#606060' }");
        yAxis.getPlotBands().add(numberBand);

        numberBand = new NumberPlotBand("High wind");
        numberBand.setRange(new NumberRange(14.0, 15.0));
        numberBand.setColor(new RGBA(68, 170, 213, 0.1f));
        numberBand.setLabel(new PlotLabel("High wind"));
        numberBand.getLabel().setStyle("{ color: '#606060' }");
        yAxis.getPlotBands().add(numberBand);

        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        SplineConfig splineCfg = new SplineConfig();
        splineCfg.setLineWidth(4);
        splineCfg.setHoverState(new SeriesState());
        splineCfg.getHoverState().setLineWidth(5);
        //
        SymbolMarker symbolMarker = new SymbolMarker(false);
        splineCfg.setMarker(symbolMarker);
        symbolMarker.setSymbol(Symbol.CIRCLE);
        symbolMarker.setHoverState(new MarkerState());
        symbolMarker.getHoverState().setEnabled(true);
        symbolMarker.getHoverState().setRadius(5);
        symbolMarker.getHoverState().setLineWidth(1);

        splineCfg.setPointStart((double) getPointStartDate(2009, 8, 6));
        splineCfg.setPointInterval(3600000.0);
        chartConfig.addSeriesConfig(splineCfg);

        InvientCharts chart = new InvientCharts(chartConfig);
        DateTimeSeries series = new DateTimeSeries("Hestavollane", splineCfg, true);
        series.setSeriesPoints(getDateTimePoints(series, 4.3, 5.1, 4.3, 5.2,
                5.4, 4.7, 3.5, 4.1, 5.6, 7.4, 6.9, 7.1, 7.9, 7.9, 7.5, 6.7,
                7.7, 7.7, 7.4, 7.0, 7.1, 5.8, 5.9, 7.4, 8.2, 8.5, 9.4, 8.1,
                10.9, 10.4, 10.9, 12.4, 12.1, 9.5, 7.5, 7.1, 7.5, 8.1, 6.8,
                3.4, 2.1, 1.9, 2.8, 2.9, 1.3, 4.4, 4.2, 3.0, 3.0));
        chart.addSeries(series);

        series = new DateTimeSeries("Voll", splineCfg, true);
        series.setSeriesPoints(getDateTimePoints(series, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.0, 0.0, 0.4, 0.0, 0.1,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.6, 1.2, 1.7,
                0.7, 2.9, 4.1, 2.6, 3.7, 3.9, 1.7, 2.3, 3.0, 3.3, 4.8, 5.0,
                4.8, 5.0, 3.2, 2.0, 0.9, 0.4, 0.3, 0.5, 0.4));
        chart.addSeries(series);

        addChart(chart);
    }

    private void showCombination() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getTitle().setText("Combination chart");

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatterJsFunc("function() {"
                + " if (this.point.name) { // the pie chart "
                + "   return this.point.name +': '+ this.y +' fruits'; "
                + " } else {" + "   return this.x  +': '+ this.y; " + " } "
                + "}");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Apples", "Oranges", "Pears",
                "Bananas", "Plums"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setAllowDecimals(false);
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        InvientCharts chart = new InvientCharts(chartConfig);

        XYSeries seriesData = new XYSeries("Jane", SeriesType.COLUMN);
        seriesData.setSeriesPoints(getPoints(seriesData, 3, 2, 1, 3, 4));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("John", SeriesType.COLUMN);
        seriesData.setSeriesPoints(getPoints(seriesData, 2, 3, 5, 7, 6));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Joe", SeriesType.COLUMN);
        seriesData.setSeriesPoints(getPoints(seriesData, 4, 3, 3, 9, 0));
        chart.addSeries(seriesData);

        seriesData = new XYSeries("Average", SeriesType.SPLINE);
        seriesData
                .setSeriesPoints(getPoints(seriesData, 3, 2.67, 3, 6.33, 3.33));
        chart.addSeries(seriesData);

        // Series Total consumption
        PieConfig pieCfg = new PieConfig();
        pieCfg.setCenterX(100);
        pieCfg.setCenterY(80);
        pieCfg.setSize(100);
        pieCfg.setShowInLegend(false);
        pieCfg.setDataLabel(new PieDataLabel());
        pieCfg.getDataLabel().setEnabled(false);

        XYSeries totalConsumpSeriesData = new XYSeries("Total consumption",
                SeriesType.PIE, pieCfg);
        PointConfig config = new PointConfig(new RGB(69, 114, 167));
        DecimalPoint point = new DecimalPoint(totalConsumpSeriesData, "Jane",
                13, config);
        totalConsumpSeriesData.addPoint(point);
        config = new PointConfig(new RGB(170, 70, 67));
        point = new DecimalPoint(totalConsumpSeriesData, "John", 23, config);
        totalConsumpSeriesData.addPoint(point);
        config = new PointConfig(new RGB(137, 165, 78));
        point = new DecimalPoint(totalConsumpSeriesData, "Joe", 19, config);
        totalConsumpSeriesData.addPoint(point);

        ChartLabel chartLabel = new ChartLabel();
        chartLabel.addLabel(new ChartLabelItem("Total fruit consumption",
                "{ left: '40px', top: '8px', color: 'black' }"));
        chartConfig.setChartLabel(chartLabel);
        chart.addSeries(totalConsumpSeriesData);

        addChart(chart);
    }

    //
    private void showCombinationMultipleAxes() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();

        chartConfig.getTitle()
                .setText("Average Monthly Weather Data for Tokyo");
        chartConfig.getSubtitle().setText("Source: WorldClimate.com");

        chartConfig
                .getTooltip()
                .setFormatterJsFunc(
                        "function() {"
                                + " var unit = { "
                                + "         'Rainfall': 'mm',"
                                + "         'Temperature': '°C',"
                                + "         'Sea-Level Pressure': 'mb'"
                                + " }[this.series.name];"
                                + "   return '' + this.x + ': ' + this.y + ' ' + unit; "
                                + "}");

        Legend legend = new Legend();
        legend.setLayout(Layout.VERTICAL);
        legend.setPosition(new Position());
        legend.getPosition().setAlign(HorzAlign.LEFT);
        legend.getPosition().setVertAlign(VertAlign.TOP);
        legend.getPosition().setX(120);
        legend.getPosition().setY(80);
        legend.setFloating(true);
        legend.setBackgroundColor(new RGB(255, 255, 255));
        chartConfig.setLegend(legend);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        // Multiple axes
        NumberYAxis temperatureAxis = new NumberYAxis();
        temperatureAxis.setAllowDecimals(false);
        temperatureAxis.setLabel(new YAxisDataLabel());
        temperatureAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value +'°C'; " + "}");
        temperatureAxis.getLabel().setStyle("{ color: '#89A54E' }");
        temperatureAxis.setTitle(new AxisTitle("Temperature"));
        temperatureAxis.getTitle().setStyle(" { color: '#89A54E' }");
        temperatureAxis.setOpposite(true);

        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(temperatureAxis);

        // secondary y-axis
        NumberYAxis rainfallAxis = new NumberYAxis();
        rainfallAxis.setGrid(new Grid());
        rainfallAxis.getGrid().setLineWidth(0);
        rainfallAxis.setTitle(new AxisTitle("Rainfall"));
        rainfallAxis.getTitle().setStyle(" { color: '#4572A7' }");
        rainfallAxis.setLabel(new YAxisDataLabel());
        rainfallAxis.getLabel().setStyle("{ color: '#4572A7' }");
        rainfallAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value +' mm'; " + "}");
        yAxesSet.add(rainfallAxis);

        // tertiary y-axis
        NumberYAxis sealevelPressureAxis = new NumberYAxis();
        sealevelPressureAxis.setGrid(new Grid());
        sealevelPressureAxis.getGrid().setLineWidth(0);
        sealevelPressureAxis.setTitle(new AxisTitle("Sea-Level Pressure"));
        sealevelPressureAxis.getTitle().setStyle(" { color: '#AA4643' }");
        sealevelPressureAxis.setLabel(new YAxisDataLabel());
        sealevelPressureAxis.getLabel().setStyle("{ color: '#AA4643' }");
        sealevelPressureAxis.getLabel().setFormatterJsFunc(
                "function() {" + " return this.value +' mb'; " + "}");
        sealevelPressureAxis.setOpposite(true);
        yAxesSet.add(sealevelPressureAxis);
        chartConfig.setYAxes(yAxesSet);

        InvientCharts chart = new InvientCharts(chartConfig);
        // Configuration of Rainfall series
        ColumnConfig colCfg = new ColumnConfig();
        colCfg.setColor(new RGB(69, 114, 167));
        // Rainfall series
        XYSeries rainfallSeriesData = new XYSeries("Rainfall",
                SeriesType.COLUMN, colCfg);
        rainfallSeriesData.setSeriesPoints(getPoints(rainfallSeriesData, 49.9,
                71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1,
                95.6, 54.4));
        rainfallSeriesData.setYAxis(rainfallAxis);
        chart.addSeries(rainfallSeriesData);

        // Configuration of Sealevel series
        SplineConfig seaLevelSplineCfg = new SplineConfig();
        seaLevelSplineCfg.setColor(new RGB(170, 70, 67));
        seaLevelSplineCfg.setMarker(new SymbolMarker(false));
        seaLevelSplineCfg.setDashStyle(DashStyle.SHORT_DOT);
        // Sealevel series
        XYSeries seaLevelSeriesData = new XYSeries("Sea-Level Pressure",
                SeriesType.SPLINE, seaLevelSplineCfg);
        seaLevelSeriesData.setSeriesPoints(getPoints(seaLevelSeriesData, 1016,
                1016, 1015.9, 1015.5, 1012.3, 1009.5, 1009.6, 1010.2, 1013.1,
                1016.9, 1018.2, 1016.7));
        seaLevelSeriesData.setYAxis(sealevelPressureAxis);
        chart.addSeries(seaLevelSeriesData);

        // Configuration of Temperature series
        SplineConfig tempSplineCfg = new SplineConfig();
        tempSplineCfg.setColor(new RGB(137, 165, 78));
        // Temperature series
        XYSeries tempSeriesData = new XYSeries("Temperature",
                SeriesType.SPLINE, tempSplineCfg);
        tempSeriesData.setSeriesPoints(getPoints(tempSeriesData, 7.0, 6.9, 9.5,
                14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6));
        chart.addSeries(tempSeriesData);

        addChart(chart);
    }

    private void showTimeSeriesZoomable() {
        InvientChartsConfig chartConfig = new InvientChartsConfig();
        chartConfig.getGeneralChartConfig().setZoomType(ZoomType.X);
        chartConfig.getGeneralChartConfig().setSpacing(new Spacing());
        chartConfig.getGeneralChartConfig().getSpacing().setRight(20);

        chartConfig.getSubtitle().setText(
                "Click and drag in the plot area to zoom in");

        DateTimeAxis xAxis = new DateTimeAxis();
        xAxis.setMaxZoom(14 * 24 * 3600000);
        LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
        xAxesSet.add(xAxis);
        chartConfig.setXAxes(xAxesSet);

        NumberYAxis yAxis = new NumberYAxis();
        yAxis.setTitle(new AxisTitle("Exchange rate"));
        yAxis.setMin(0.6);
        yAxis.setStartOnTick(true);
        yAxis.setShowFirstLabel(false);
        LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
        yAxesSet.add(yAxis);
        chartConfig.setYAxes(yAxesSet);

        chartConfig.getTooltip().setShared(true);

        chartConfig.getLegend().setEnabled(false);

        // Set plot options
        AreaConfig areaCfg = new AreaConfig();

        List<LinearColorStop> colorStops = new ArrayList<Gradient.LinearGradient.LinearColorStop>();
        colorStops.add(new LinearColorStop(0, new RGB(69, 114, 167)));
        colorStops.add(new LinearColorStop(1, new RGBA(2, 0, 0, 0)));
        // Fill color
        areaCfg.setFillColor(new Gradient.LinearGradient(0, 0, 0, 300,
                colorStops));

        areaCfg.setLineWidth(1);
        areaCfg.setShadow(false);
        areaCfg.setHoverState(new SeriesState());
        areaCfg.getHoverState().setLineWidth(1);
        SymbolMarker marker;
        areaCfg.setMarker(marker = new SymbolMarker(false));
        marker.setHoverState(new MarkerState());
        marker.getHoverState().setEnabled(true);
        marker.getHoverState().setRadius(5);

        chartConfig.addSeriesConfig(areaCfg);

        InvientCharts chart = new InvientCharts(chartConfig);

        // Area configuration
        AreaConfig serieaAreaCfg = new AreaConfig();
        serieaAreaCfg.setPointStart((double) getPointStartDate(2006, 0, 01));
        serieaAreaCfg.setPointInterval(24 * 3600 * 1000.0);
        // Series
        DateTimeSeries dateTimeSeries = new DateTimeSeries("USD to EUR",
                SeriesType.AREA, serieaAreaCfg);

        dateTimeSeries
                .addPoint((DateTimePoint[]) getDateTimeSeriesPoints(dateTimeSeries));
        chart.addSeries(dateTimeSeries);

        addChart(chart);

    }

    private void addChart(InvientCharts chart, boolean isPrepend,
            boolean isRegisterEvents) {
        addChart(chart, isPrepend, isRegisterEvents, true);
    }

    private void addChart(InvientCharts chart, boolean isPrepend,
            boolean isRegisterEvents, boolean isRegisterSVGEvent) {
        addChart(chart, isPrepend, isRegisterEvents, isRegisterSVGEvent, true);
    }

    private void addChart(InvientCharts chart, boolean isPrepend,
            boolean isRegisterEvents, boolean isRegisterSVGEvent,
            boolean isSetHeight) {
        // add events
        if (isRegisterEvents) {
            registerEvents(chart);
        }

        chart.setSizeFull();
        chart.setStyleName("v-chart-min-width");
        if (isSetHeight) {
            chart.setHeight("410px");
        }

        if (isPrepend) {
            rightLayout.setStyleName("v-chart-master-detail");
            rightLayout.addComponentAsFirst(chart);
        } else {
            rightLayout.removeStyleName("v-chart-master-detail");
            emptyEventLog();
            rightLayout.removeAllComponents();
            // Add chart
            rightLayout.addComponent(chart);
            // Add "Get SVG" button and register SVG available event
            if (isRegisterSVGEvent) {
                registerSVGAndPrintEvent(chart);
            }
            // Server events log
            rightLayout
                    .addComponent(new Label("Events received by the server:"));
            rightLayout.addComponent(eventLog);
        }
    }

    private void addChart(InvientCharts chart) {
        addChart(chart, false, true);
    }

    private void registerSVGAndPrintEvent(final InvientCharts chart) {
        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setWidth("100%");
        gridLayout.setSpacing(true);
        Button svgBtn;
        gridLayout.addComponent(svgBtn = new Button("Get SVG"));
        gridLayout.setComponentAlignment(svgBtn, Alignment.MIDDLE_RIGHT);
        Button printBtn;
        gridLayout.addComponent(printBtn = new Button("Print"));
        gridLayout.setComponentAlignment(printBtn, Alignment.MIDDLE_LEFT);
        rightLayout.addComponent(gridLayout);
        svgBtn.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                chart.addListener(new InvientCharts.ChartSVGAvailableListener() {

                    @Override
                    public void svgAvailable(
                            ChartSVGAvailableEvent chartSVGAvailableEvent) {
                        logEventInfo("[svgAvailable]" + " svg -> "
                                + chartSVGAvailableEvent.getSVG());
                    }
                });
            }
        });
        printBtn.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                chart.print();
            }
        });
    }

    private void registerEvents(InvientCharts chart) {

        chart.addListener(new InvientCharts.ChartClickListener() {

            @Override
            public void chartClick(ChartClickEvent chartClickEvent) {
                logEventInfo("chartClick",
                        ((DecimalPoint) chartClickEvent.getPoint()).getX(),
                        ((DecimalPoint) chartClickEvent.getPoint()).getY(),
                        chartClickEvent.getMousePosition().getMouseX(),
                        chartClickEvent.getMousePosition().getMouseY());
            }
        });

        if (chart.getConfig().getGeneralChartConfig().getZoomType() != null) {
            chart.addListener(new InvientCharts.ChartZoomListener() {

                @Override
                public void chartZoom(ChartZoomEvent chartZoomEvent) {
                    logEventInfo("chartSelection", chartZoomEvent
                            .getChartArea().getxAxisMin(), chartZoomEvent
                            .getChartArea().getxAxisMax(), chartZoomEvent
                            .getChartArea().getyAxisMin(), chartZoomEvent
                            .getChartArea().getyAxisMax());
                }
            });

            chart.addListener(new InvientCharts.ChartResetZoomListener() {

                @Override
                public void chartResetZoom(
                        ChartResetZoomEvent chartResetZoomEvent) {
                    logEventInfo("[chartSelectionReset]");
                }
            });
        }

        chart.addListener(new InvientCharts.SeriesClickListerner() {

            @Override
            public void seriesClick(SeriesClickEvent seriesClickEvent) {
                final String EVENT_NAME = "seriesClick";
                if (seriesClickEvent.getNearestPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, seriesClickEvent.getSeries()
                            .getName(), null, (Double) seriesClickEvent
                            .getNearestPoint().getX(),
                            (Double) seriesClickEvent.getNearestPoint().getY(),
                            seriesClickEvent.getMousePosition().getMouseX(),
                            seriesClickEvent.getMousePosition().getMouseY());
                } else {
                    logEventInfo(EVENT_NAME, seriesClickEvent.getSeries()
                            .getName(), null, (Date) seriesClickEvent
                            .getNearestPoint().getX(),
                            (Double) seriesClickEvent.getNearestPoint().getY(),
                            seriesClickEvent.getMousePosition().getMouseX(),
                            seriesClickEvent.getMousePosition().getMouseY());
                }

            }
        });

        chart.addListener(new InvientCharts.SeriesHideListerner() {

            @Override
            public void seriesHide(SeriesHideEvent seriesHideEvent) {
                logEventInfo("seriesHide", seriesHideEvent.getSeries()
                        .getName());
            }
        });

        chart.addListener(new InvientCharts.SeriesShowListerner() {

            @Override
            public void seriesShow(SeriesShowEvent seriesShowEvent) {
                logEventInfo("seriesShow", seriesShowEvent.getSeries()
                        .getName());
            }
        });

        chart.addListener(new InvientCharts.SeriesLegendItemClickListerner() {

            @Override
            public void seriesLegendItemClick(
                    SeriesLegendItemClickEvent seriesLegendItemClickEvent) {
                logEventInfo("seriesLegendItemClick",
                        seriesLegendItemClickEvent.getSeries().getName());
            }
        });

        chart.addListener(new InvientCharts.PointClickListener() {

            @Override
            public void pointClick(PointClickEvent pointClickEvent) {
                final String EVENT_NAME = "pointClick";
                if (pointClickEvent.getPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, pointClickEvent.getPoint()
                            .getSeries().getName(),
                            pointClickEvent.getCategory(),
                            (Double) pointClickEvent.getPoint().getX(),
                            (Double) pointClickEvent.getPoint().getY(),
                            pointClickEvent.getMousePosition().getMouseX(),
                            pointClickEvent.getMousePosition().getMouseY());
                } else {
                    logEventInfo(EVENT_NAME, pointClickEvent.getPoint()
                            .getSeries().getName(),
                            pointClickEvent.getCategory(),
                            (Date) pointClickEvent.getPoint().getX(),
                            (Double) pointClickEvent.getPoint().getY(),
                            pointClickEvent.getMousePosition().getMouseX(),
                            pointClickEvent.getMousePosition().getMouseY());
                }
            }
        });

        chart.addListener(new InvientCharts.PointRemoveListener() {

            @Override
            public void pointRemove(PointRemoveEvent pointRemoveEvent) {
                final String EVENT_NAME = "pointRemove";
                if (pointRemoveEvent.getPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, pointRemoveEvent.getPoint()
                            .getSeries().getName(),
                            pointRemoveEvent.getCategory(),
                            (Double) pointRemoveEvent.getPoint().getX(),
                            (Double) pointRemoveEvent.getPoint().getY());
                } else {
                    logEventInfo(EVENT_NAME, pointRemoveEvent.getPoint()
                            .getSeries().getName(),
                            pointRemoveEvent.getCategory(),
                            (Date) pointRemoveEvent.getPoint().getX(),
                            (Double) pointRemoveEvent.getPoint().getY());
                }
            }
        });

        chart.addListener(new InvientCharts.PointSelectListener() {

            @Override
            public void pointSelected(PointSelectEvent pointSelectEvent) {
                final String EVENT_NAME = "pointSelected";
                if (pointSelectEvent.getPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, pointSelectEvent.getPoint()
                            .getSeries().getName(),
                            pointSelectEvent.getCategory(),
                            (Double) pointSelectEvent.getPoint().getX(),
                            (Double) pointSelectEvent.getPoint().getY());
                } else {
                    logEventInfo(EVENT_NAME, pointSelectEvent.getPoint()
                            .getSeries().getName(),
                            pointSelectEvent.getCategory(),
                            (Date) pointSelectEvent.getPoint().getX(),
                            (Double) pointSelectEvent.getPoint().getY());
                }
            }
        });

        chart.addListener(new InvientCharts.PointUnselectListener() {

            @Override
            public void pointUnSelect(PointUnselectEvent pointUnSelectEvent) {
                final String EVENT_NAME = "pointUnSelected";
                if (pointUnSelectEvent.getPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, pointUnSelectEvent.getPoint()
                            .getSeries().getName(),
                            pointUnSelectEvent.getCategory(),
                            (Double) pointUnSelectEvent.getPoint().getX(),
                            (Double) pointUnSelectEvent.getPoint().getY());
                } else {
                    logEventInfo(EVENT_NAME, pointUnSelectEvent.getPoint()
                            .getSeries().getName(),
                            pointUnSelectEvent.getCategory(),
                            (Date) pointUnSelectEvent.getPoint().getX(),
                            (Double) pointUnSelectEvent.getPoint().getY());
                }
            }
        });

        chart.addListener(new InvientCharts.PieChartLegendItemClickListener() {

            @Override
            public void legendItemClick(
                    PieChartLegendItemClickEvent legendItemClickEvent) {
                final String EVENT_NAME = "pieLegendItemClick";
                if (legendItemClickEvent.getPoint() instanceof DecimalPoint) {
                    logEventInfo(EVENT_NAME, legendItemClickEvent.getPoint()
                            .getSeries().getName(), null,
                            (Double) legendItemClickEvent.getPoint().getX(),
                            (Double) legendItemClickEvent.getPoint().getY());
                }
            }
        });

    }

    private static long getPointStartDate(int year, int month, int day) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    private static Date getDateZeroTime(int year, int month, int day) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        setZeroTime(cal);
        return cal.getTime();
    }

    private static void setZeroTime(Calendar cal) {
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    private LinkedHashSet<DateTimePoint> getDateTimePoints(Series series,
            double... values) {
        LinkedHashSet<DateTimePoint> points = new LinkedHashSet<DateTimePoint>();
        for (double value : values) {
            points.add(new DateTimePoint(series, value));
        }
        return points;
    }

    private static LinkedHashSet<DecimalPoint> getPoints(Series series,
            double... values) {
        LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
        for (double value : values) {
            points.add(new DecimalPoint(series, value));
        }
        return points;
    }

    private static LinkedHashSet<DecimalPoint> getPoints(Series series,
            double[]... values) {
        LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
        for (double[] value : values) {
            Double x, y = null;
            if (value.length == 0)
                continue;
            if (value.length == 2) {
                x = value[0];
                y = value[1];
            } else {
                x = value[0];
            }
            points.add(new DecimalPoint(series, x, y));
        }
        return points;
    }

    private static String getFormattedTimestamp(Date dt) {
        if (dt == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
        return format.format(dt);
    }

    private static String getCurrFormattedTimestamp() {
        return getFormattedTimestamp(new Date());
    }

    private static enum ChartName {
        BASIC("Basic"), DONUT("Donut"), CLICK_TO_ADD_POINT(
                "Click to add a point"), MASTER_DETAIL("Master-detail"), TIMESERIES_ZOOMABLE(
                "Time series, zoomable"), WITH_DATA_LABELS("With data labels"), STACKED(
                "Stacked"), WITH_NEGATIVE_STACK("With negative stack"), WITH_NEGATIVE_VALUES(
                "With negative values"), STACKED_AND_GROUPED(
                "Stacked and grouped"), STACKED_PERCENT("Stacked percentage"), WITH_ROTATED_LABELS(
                "With rotated labels"), WITH_MISSING_POINTS(
                "With missing points"), INVERTED_AXES("Inverted axes"), WITH_LEGEND(
                "With legend"), WITH_PLOTBANDS("With plot bands"), WITH_SYMBOLS(
                "With symbols"), UPDATING_EACH_SECOND("Updating each second"), COMBINATION_COLUMN_LINE_AND_PIE(
                "Column, spline and pie"), PERCENTAGE("Percentage"), SCATTER_WITH_REGRESSION_LINE(
                "Scatter with regression line"), MULTIPLE_AXES("Multiple axes");

        private String name;

        private ChartName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private ChartName getChartName(String chartNameString) {
        for (ChartName chartName : ChartName.values()) {
            if (chartNameString.equalsIgnoreCase(chartName.getName())) {
                return chartName;
            }
        }
        return null;
    }

    private static enum DemoSeriesType {
        LINE(SeriesType.LINE, "Line"), SPLINE(SeriesType.SPLINE, "Spline"), SCATTER(
                SeriesType.SCATTER, "Scatter"), AREA(SeriesType.AREA,
                "Area - Line"), AREASPLINE(SeriesType.AREASPLINE,
                "Area - Spline"), BAR(SeriesType.BAR, "Bar"), COLUMN(
                SeriesType.COLUMN, "Column"), PIE(SeriesType.PIE, "Pie"), COMBINATION(
                SeriesType.COMMONSERIES, "Combination");

        private SeriesType seriesType;
        private String name;

        DemoSeriesType(SeriesType seriesType, String name) {
            this.seriesType = seriesType;
            this.name = name;
        }

        public SeriesType getSeriesType() {
            return this.seriesType;
        }

        public String getName() {
            return this.name;
        }

    }

    private DemoSeriesType getDemoSeriesType(String demoSeriesTypeName) {
        for (DemoSeriesType demoSeriesType : DemoSeriesType.values()) {
            if (demoSeriesTypeName.equalsIgnoreCase(demoSeriesType.getName())) {
                return demoSeriesType;
            }
        }
        return null;
    }

    private Tree createChartsTree() {
        final Tree tree = new Tree("Chart Type");
        tree.setContainerDataSource(getContainer());
        tree.setImmediate(true);
        tree.setItemCaptionPropertyId(TREE_ITEM_CAPTION_PROP_ID);
        tree.setItemCaptionMode(Tree.ITEM_CAPTION_MODE_PROPERTY);
        tree.setNullSelectionAllowed(false);

        for (Object id : tree.rootItemIds()) {
            tree.expandItemsRecursively(id);
        }

        tree.addListener(new Tree.ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                try {
                    Object selectedId = event.getProperty().getValue();
                    if (tree.getParent(selectedId) != null) {
                        Object parentId = tree.getParent(selectedId);
                        String demoSeriesTypeName = (String) tree
                                .getContainerProperty(parentId,
                                        TREE_ITEM_CAPTION_PROP_ID).getValue();
                        String seriesInstanceName = (String) tree
                                .getContainerProperty(selectedId,
                                        TREE_ITEM_CAPTION_PROP_ID).getValue();

                        System.out.println("parent : " + demoSeriesTypeName
                                + ", selected : " + seriesInstanceName);
                        showChart(demoSeriesTypeName, seriesInstanceName);
                    } else {
                        String demoSeriesTypeName = (String) tree
                                .getContainerProperty(selectedId,
                                        TREE_ITEM_CAPTION_PROP_ID).getValue();
                        System.out.println("Selected " + demoSeriesTypeName);
                        showChartInstancesForSeriesType(demoSeriesTypeName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return tree;
    }

    private void showChartInstancesForSeriesType(final String demoSeriesTypeName) {
        rightLayout.removeAllComponents();
        List<ChartName> demoCharts = getDemoCharts(getDemoSeriesType(demoSeriesTypeName));
        for (ChartName chartName : demoCharts) {
            Button btn = null;
            rightLayout.addComponent(btn = new Button(chartName.getName(),
                    new Button.ClickListener() {

                        @Override
                        public void buttonClick(ClickEvent event) {
                            navTree.select(demoSeriesTypeName + SEPARATOR
                                    + event.getButton().getCaption());
                        }
                    }));
            btn.setWidth("200px");
        }
    }

    private static final String SEPARATOR = "|";

    private HierarchicalContainer getContainer() {

        HierarchicalContainer container = new HierarchicalContainer();

        container.addContainerProperty(TREE_ITEM_CAPTION_PROP_ID, String.class,
                "");

        for (DemoSeriesType demoSeriesType : DemoSeriesType.values()) {
            String itemId = demoSeriesType.getName();
            Item item = container.addItem(itemId);
            item.getItemProperty(TREE_ITEM_CAPTION_PROP_ID).setValue(
                    demoSeriesType.getName());
            container.setChildrenAllowed(itemId, true);
            // add child
            addChartNamesForSeriesType(container, itemId, demoSeriesType);
        }

        return container;
    }

    private void addChartNamesForSeriesType(HierarchicalContainer container,
            Object parentId, DemoSeriesType demoSeriesType) {
        for (ChartName chartName : getDemoCharts(demoSeriesType)) {
            Object childItemId = demoSeriesType.getName() + SEPARATOR
                    + chartName.getName();
            Item childItem = container.addItem(childItemId);
            childItem.getItemProperty(TREE_ITEM_CAPTION_PROP_ID).setValue(
                    chartName.getName());
            container.setParent(childItemId, parentId);
            container.setChildrenAllowed(childItemId, false);
        }
    }

    private List<ChartName> getDemoCharts(DemoSeriesType demoSeriesType) {
        List<ChartName> chartNames = new ArrayList<ChartName>();
        switch (demoSeriesType) {
        case LINE:
            chartNames.add(ChartName.BASIC);
            chartNames.add(ChartName.WITH_DATA_LABELS);
            chartNames.add(ChartName.TIMESERIES_ZOOMABLE);
            chartNames.add(ChartName.MASTER_DETAIL);
            chartNames.add(ChartName.CLICK_TO_ADD_POINT);
            break;
        case BAR:
            chartNames.add(ChartName.BASIC);
            chartNames.add(ChartName.STACKED);
            chartNames.add(ChartName.WITH_NEGATIVE_STACK);
            break;
        case COLUMN:
            chartNames.add(ChartName.BASIC);
            chartNames.add(ChartName.WITH_NEGATIVE_VALUES);
            chartNames.add(ChartName.STACKED);
            chartNames.add(ChartName.STACKED_AND_GROUPED);
            chartNames.add(ChartName.STACKED_PERCENT);
            chartNames.add(ChartName.WITH_ROTATED_LABELS);
            break;
        case AREA:
            chartNames.add(ChartName.BASIC);
            chartNames.add(ChartName.WITH_NEGATIVE_VALUES);
            chartNames.add(ChartName.STACKED);
            chartNames.add(ChartName.PERCENTAGE);
            chartNames.add(ChartName.WITH_MISSING_POINTS);
            chartNames.add(ChartName.INVERTED_AXES);
            break;
        case AREASPLINE:
            chartNames.add(ChartName.BASIC);
            break;
        case PIE:
            chartNames.add(ChartName.BASIC);
            chartNames.add(ChartName.WITH_LEGEND);
            chartNames.add(ChartName.DONUT);
            break;
        case SCATTER:
            chartNames.add(ChartName.BASIC);
            break;
        case SPLINE:
            chartNames.add(ChartName.BASIC);
            chartNames.add(ChartName.WITH_PLOTBANDS);
            chartNames.add(ChartName.WITH_SYMBOLS);
            chartNames.add(ChartName.UPDATING_EACH_SECOND);
            break;
        case COMBINATION:
            chartNames.add(ChartName.COMBINATION_COLUMN_LINE_AND_PIE);
            chartNames.add(ChartName.SCATTER_WITH_REGRESSION_LINE);
            chartNames.add(ChartName.MULTIPLE_AXES);
            break;
        }

        return chartNames;
    }

    private void logEventInfo(String eventName, String seriesName) {

        StringBuilder sb = new StringBuilder("");
        sb.append("[" + eventName + "]");
        sb.append(" series -> " + seriesName);
        logEventInfo(sb.toString());

    }

    private void logEventInfo(String eventName, String seriesName,
            String category, Date x, Double y, Integer mouseX, Integer mouseY) {
        logStringEventInfo(eventName, seriesName, category,
                (x != null ? x.toString() : null), (y != null ? y.toString()
                        : null), (mouseX != null ? mouseX.toString() : null),
                (mouseY != null ? mouseY.toString() : null));
    }

    private void logEventInfo(String eventName, String seriesName,
            String category, Double x, Double y, Integer mouseX, Integer mouseY) {
        logStringEventInfo(eventName, seriesName, category,
                (x != null ? x.toString() : null), (y != null ? y.toString()
                        : null), (mouseX != null ? mouseX.toString() : null),
                (mouseY != null ? mouseY.toString() : null));
    }

    private void logStringEventInfo(String eventName, String seriesName,
            String category, String x, String y, String mouseX, String mouseY) {
        StringBuilder sb = new StringBuilder("");
        sb.append("[" + eventName + "]");
        sb.append(" series -> " + seriesName);
        if (category != null && category.length() > 0) {
            sb.append(", category -> " + category);
        }
        if (x != null) {
            sb.append(", x -> " + x);
        }
        if (y != null) {
            sb.append(", y -> " + y);
        }
        if (mouseX != null) {
            sb.append(", mouseX -> " + mouseX);
        }
        if (mouseY != null) {
            sb.append(", mouseY -> " + mouseY);
        }
        logEventInfo(sb.toString());
    }

    private void logEventInfo(String eventName, String seriesName,
            String category, Double x, Double y) {
        logEventInfo(eventName, seriesName, category, x, y, null, null);
    }

    private void logEventInfo(String eventName, String seriesName,
            String category, Date x, Double y) {
        logEventInfo(eventName, seriesName, category, x, y, null, null);
    }

    private void logEventInfo(String eventName, double xAxisPos,
            double yAxisPos, int mouseX, int mouseY) {
        StringBuilder sb = new StringBuilder("");
        sb.append("[" + eventName + "]");
        sb.append(", xAxisPos -> " + xAxisPos);
        sb.append(", yAxisPos -> " + yAxisPos);
        sb.append(", mouseX -> " + mouseX);
        sb.append(", mouseY -> " + mouseY);
        logEventInfo(sb.toString());
    }

    private void logEventInfo(String eventName, double xAxisMin,
            double xAxisMax, double yAxisMin, double yAxisMax) {
        StringBuilder sb = new StringBuilder("");
        sb.append("[" + eventName + "]");
        sb.append(", xAxisMin -> " + xAxisMin);
        sb.append(", xAxisMax -> " + xAxisMax);
        sb.append(", yAxisMin -> " + yAxisMin);
        sb.append(", yAxisMax -> " + yAxisMax);
        logEventInfo(sb.toString());
    }

    private void logEventInfo(String eventInfo) {
        logEventInfo(eventInfo, true);
    }

    private void logEventInfo(String eventInfo, boolean isAppend) {
        eventLog.setReadOnly(false);
        if (isAppend) {
            eventLog.setValue("[" + getCurrFormattedTimestamp() + "] "
                    + eventInfo + "\n" + eventLog.getValue());
        } else {
            eventLog.setValue("");
        }
        eventLog.setReadOnly(true);
    }

    private void emptyEventLog() {
        logEventInfo("", false);
    }

    private LinkedHashSet<DecimalPoint> scatterMaleData = null;
    private LinkedHashSet<DecimalPoint> scatterFemaleData = null;

    private LinkedHashSet<DecimalPoint> getScatterFemalePoints(Series series) {
        if (scatterFemaleData != null) {
            return scatterFemaleData;
        }
        // Initialize data
        scatterFemaleData = getPoints(series, new double[] { 161.2, 51.6 },
                new double[] { 167.5, 59.0 }, new double[] { 159.5, 49.2 },
                new double[] { 157.0, 63.0 }, new double[] { 155.8, 53.6 },
                new double[] { 170.0, 59.0 }, new double[] { 159.1, 47.6 },
                new double[] { 166.0, 69.8 }, new double[] { 176.2, 66.8 },
                new double[] { 160.2, 75.2 }, new double[] { 172.5, 55.2 },
                new double[] { 170.9, 54.2 }, new double[] { 172.9, 62.5 },
                new double[] { 153.4, 42.0 }, new double[] { 160.0, 50.0 },
                new double[] { 147.2, 49.8 }, new double[] { 168.2, 49.2 },
                new double[] { 175.0, 73.2 }, new double[] { 157.0, 47.8 },
                new double[] { 167.6, 68.8 }, new double[] { 159.5, 50.6 },
                new double[] { 175.0, 82.5 }, new double[] { 166.8, 57.2 },
                new double[] { 176.5, 87.8 }, new double[] { 170.2, 72.8 },
                new double[] { 174.0, 54.5 }, new double[] { 173.0, 59.8 },
                new double[] { 179.9, 67.3 }, new double[] { 170.5, 67.8 },
                new double[] { 160.0, 47.0 }, new double[] { 154.4, 46.2 },
                new double[] { 162.0, 55.0 }, new double[] { 176.5, 83.0 },
                new double[] { 160.0, 54.4 }, new double[] { 152.0, 45.8 },
                new double[] { 162.1, 53.6 }, new double[] { 170.0, 73.2 },
                new double[] { 160.2, 52.1 }, new double[] { 161.3, 67.9 },
                new double[] { 166.4, 56.6 }, new double[] { 168.9, 62.3 },
                new double[] { 163.8, 58.5 }, new double[] { 167.6, 54.5 },
                new double[] { 160.0, 50.2 }, new double[] { 161.3, 60.3 },
                new double[] { 167.6, 58.3 }, new double[] { 165.1, 56.2 },
                new double[] { 160.0, 50.2 }, new double[] { 170.0, 72.9 },
                new double[] { 157.5, 59.8 }, new double[] { 167.6, 61.0 },
                new double[] { 160.7, 69.1 }, new double[] { 163.2, 55.9 },
                new double[] { 152.4, 46.5 }, new double[] { 157.5, 54.3 },
                new double[] { 168.3, 54.8 }, new double[] { 180.3, 60.7 },
                new double[] { 165.5, 60.0 }, new double[] { 165.0, 62.0 },
                new double[] { 164.5, 60.3 }, new double[] { 156.0, 52.7 },
                new double[] { 160.0, 74.3 }, new double[] { 163.0, 62.0 },
                new double[] { 165.7, 73.1 }, new double[] { 161.0, 80.0 },
                new double[] { 162.0, 54.7 }, new double[] { 166.0, 53.2 },
                new double[] { 174.0, 75.7 }, new double[] { 172.7, 61.1 },
                new double[] { 167.6, 55.7 }, new double[] { 151.1, 48.7 },
                new double[] { 164.5, 52.3 }, new double[] { 163.5, 50.0 },
                new double[] { 152.0, 59.3 }, new double[] { 169.0, 62.5 },
                new double[] { 164.0, 55.7 }, new double[] { 161.2, 54.8 },
                new double[] { 155.0, 45.9 }, new double[] { 170.0, 70.6 },
                new double[] { 176.2, 67.2 }, new double[] { 170.0, 69.4 },
                new double[] { 162.5, 58.2 }, new double[] { 170.3, 64.8 },
                new double[] { 164.1, 71.6 }, new double[] { 169.5, 52.8 },
                new double[] { 163.2, 59.8 }, new double[] { 154.5, 49.0 },
                new double[] { 159.8, 50.0 }, new double[] { 173.2, 69.2 },
                new double[] { 170.0, 55.9 }, new double[] { 161.4, 63.4 },
                new double[] { 169.0, 58.2 }, new double[] { 166.2, 58.6 },
                new double[] { 159.4, 45.7 }, new double[] { 162.5, 52.2 },
                new double[] { 159.0, 48.6 }, new double[] { 162.8, 57.8 },
                new double[] { 159.0, 55.6 }, new double[] { 179.8, 66.8 },
                new double[] { 162.9, 59.4 }, new double[] { 161.0, 53.6 },
                new double[] { 151.1, 73.2 }, new double[] { 168.2, 53.4 },
                new double[] { 168.9, 69.0 }, new double[] { 173.2, 58.4 },
                new double[] { 171.8, 56.2 }, new double[] { 178.0, 70.6 },
                new double[] { 164.3, 59.8 }, new double[] { 163.0, 72.0 },
                new double[] { 168.5, 65.2 }, new double[] { 166.8, 56.6 },
                new double[] { 172.7, 105.2 }, new double[] { 163.5, 51.8 },
                new double[] { 169.4, 63.4 }, new double[] { 167.8, 59.0 },
                new double[] { 159.5, 47.6 }, new double[] { 167.6, 63.0 },
                new double[] { 161.2, 55.2 }, new double[] { 160.0, 45.0 },
                new double[] { 163.2, 54.0 }, new double[] { 162.2, 50.2 },
                new double[] { 161.3, 60.2 }, new double[] { 149.5, 44.8 },
                new double[] { 157.5, 58.8 }, new double[] { 163.2, 56.4 },
                new double[] { 172.7, 62.0 }, new double[] { 155.0, 49.2 },
                new double[] { 156.5, 67.2 }, new double[] { 164.0, 53.8 },
                new double[] { 160.9, 54.4 }, new double[] { 162.8, 58.0 },
                new double[] { 167.0, 59.8 }, new double[] { 160.0, 54.8 },
                new double[] { 160.0, 43.2 }, new double[] { 168.9, 60.5 },
                new double[] { 158.2, 46.4 }, new double[] { 156.0, 64.4 },
                new double[] { 160.0, 48.8 }, new double[] { 167.1, 62.2 },
                new double[] { 158.0, 55.5 }, new double[] { 167.6, 57.8 },
                new double[] { 156.0, 54.6 }, new double[] { 162.1, 59.2 },
                new double[] { 173.4, 52.7 }, new double[] { 159.8, 53.2 },
                new double[] { 170.5, 64.5 }, new double[] { 159.2, 51.8 },
                new double[] { 157.5, 56.0 }, new double[] { 161.3, 63.6 },
                new double[] { 162.6, 63.2 }, new double[] { 160.0, 59.5 },
                new double[] { 168.9, 56.8 }, new double[] { 165.1, 64.1 },
                new double[] { 162.6, 50.0 }, new double[] { 165.1, 72.3 },
                new double[] { 166.4, 55.0 }, new double[] { 160.0, 55.9 },
                new double[] { 152.4, 60.4 }, new double[] { 170.2, 69.1 },
                new double[] { 162.6, 84.5 }, new double[] { 170.2, 55.9 },
                new double[] { 158.8, 55.5 }, new double[] { 172.7, 69.5 },
                new double[] { 167.6, 76.4 }, new double[] { 162.6, 61.4 },
                new double[] { 167.6, 65.9 }, new double[] { 156.2, 58.6 },
                new double[] { 175.2, 66.8 }, new double[] { 172.1, 56.6 },
                new double[] { 162.6, 58.6 }, new double[] { 160.0, 55.9 },
                new double[] { 165.1, 59.1 }, new double[] { 182.9, 81.8 },
                new double[] { 166.4, 70.7 }, new double[] { 165.1, 56.8 },
                new double[] { 177.8, 60.0 }, new double[] { 165.1, 58.2 },
                new double[] { 175.3, 72.7 }, new double[] { 154.9, 54.1 },
                new double[] { 158.8, 49.1 }, new double[] { 172.7, 75.9 },
                new double[] { 168.9, 55.0 }, new double[] { 161.3, 57.3 },
                new double[] { 167.6, 55.0 }, new double[] { 165.1, 65.5 },
                new double[] { 175.3, 65.5 }, new double[] { 157.5, 48.6 },
                new double[] { 163.8, 58.6 }, new double[] { 167.6, 63.6 },
                new double[] { 165.1, 55.2 }, new double[] { 165.1, 62.7 },
                new double[] { 168.9, 56.6 }, new double[] { 162.6, 53.9 },
                new double[] { 164.5, 63.2 }, new double[] { 176.5, 73.6 },
                new double[] { 168.9, 62.0 }, new double[] { 175.3, 63.6 },
                new double[] { 159.4, 53.2 }, new double[] { 160.0, 53.4 },
                new double[] { 170.2, 55.0 }, new double[] { 162.6, 70.5 },
                new double[] { 167.6, 54.5 }, new double[] { 162.6, 54.5 },
                new double[] { 160.7, 55.9 }, new double[] { 160.0, 59.0 },
                new double[] { 157.5, 63.6 }, new double[] { 162.6, 54.5 },
                new double[] { 152.4, 47.3 }, new double[] { 170.2, 67.7 },
                new double[] { 165.1, 80.9 }, new double[] { 172.7, 70.5 },
                new double[] { 165.1, 60.9 }, new double[] { 170.2, 63.6 },
                new double[] { 170.2, 54.5 }, new double[] { 170.2, 59.1 },
                new double[] { 161.3, 70.5 }, new double[] { 167.6, 52.7 },
                new double[] { 167.6, 62.7 }, new double[] { 165.1, 86.3 },
                new double[] { 162.6, 66.4 }, new double[] { 152.4, 67.3 },
                new double[] { 168.9, 63.0 }, new double[] { 170.2, 73.6 },
                new double[] { 175.2, 62.3 }, new double[] { 175.2, 57.7 },
                new double[] { 160.0, 55.4 }, new double[] { 165.1, 104.1 },
                new double[] { 174.0, 55.5 }, new double[] { 170.2, 77.3 },
                new double[] { 160.0, 80.5 }, new double[] { 167.6, 64.5 },
                new double[] { 167.6, 72.3 }, new double[] { 167.6, 61.4 },
                new double[] { 154.9, 58.2 }, new double[] { 162.6, 81.8 },
                new double[] { 175.3, 63.6 }, new double[] { 171.4, 53.4 },
                new double[] { 157.5, 54.5 }, new double[] { 165.1, 53.6 },
                new double[] { 160.0, 60.0 }, new double[] { 174.0, 73.6 },
                new double[] { 162.6, 61.4 }, new double[] { 174.0, 55.5 },
                new double[] { 162.6, 63.6 }, new double[] { 161.3, 60.9 },
                new double[] { 156.2, 60.0 }, new double[] { 149.9, 46.8 },
                new double[] { 169.5, 57.3 }, new double[] { 160.0, 64.1 },
                new double[] { 175.3, 63.6 }, new double[] { 169.5, 67.3 },
                new double[] { 160.0, 75.5 }, new double[] { 172.7, 68.2 },
                new double[] { 162.6, 61.4 }, new double[] { 157.5, 76.8 },
                new double[] { 176.5, 71.8 }, new double[] { 164.4, 55.5 },
                new double[] { 160.7, 48.6 }, new double[] { 174.0, 66.4 },
                new double[] { 163.8, 67.3 });

        return scatterFemaleData;
    }

    private LinkedHashSet<DecimalPoint> getScatterMalePoints(Series series) {
        if (scatterMaleData != null) {
            return scatterMaleData;
        }

        scatterMaleData = getPoints(series, new double[] { 174.0, 65.6 },
                new double[] { 175.3, 71.8 }, new double[] { 193.5, 80.7 },
                new double[] { 186.5, 72.6 }, new double[] { 187.2, 78.8 },
                new double[] { 181.5, 74.8 }, new double[] { 184.0, 86.4 },
                new double[] { 184.5, 78.4 }, new double[] { 175.0, 62.0 },
                new double[] { 184.0, 81.6 }, new double[] { 180.0, 76.6 },
                new double[] { 177.8, 83.6 }, new double[] { 192.0, 90.0 },
                new double[] { 176.0, 74.6 }, new double[] { 174.0, 71.0 },
                new double[] { 184.0, 79.6 }, new double[] { 192.7, 93.8 },
                new double[] { 171.5, 70.0 }, new double[] { 173.0, 72.4 },
                new double[] { 176.0, 85.9 }, new double[] { 176.0, 78.8 },
                new double[] { 180.5, 77.8 }, new double[] { 172.7, 66.2 },
                new double[] { 176.0, 86.4 }, new double[] { 173.5, 81.8 },
                new double[] { 178.0, 89.6 }, new double[] { 180.3, 82.8 },
                new double[] { 180.3, 76.4 }, new double[] { 164.5, 63.2 },
                new double[] { 173.0, 60.9 }, new double[] { 183.5, 74.8 },
                new double[] { 175.5, 70.0 }, new double[] { 188.0, 72.4 },
                new double[] { 189.2, 84.1 }, new double[] { 172.8, 69.1 },
                new double[] { 170.0, 59.5 }, new double[] { 182.0, 67.2 },
                new double[] { 170.0, 61.3 }, new double[] { 177.8, 68.6 },
                new double[] { 184.2, 80.1 }, new double[] { 186.7, 87.8 },
                new double[] { 171.4, 84.7 }, new double[] { 172.7, 73.4 },
                new double[] { 175.3, 72.1 }, new double[] { 180.3, 82.6 },
                new double[] { 182.9, 88.7 }, new double[] { 188.0, 84.1 },
                new double[] { 177.2, 94.1 }, new double[] { 172.1, 74.9 },
                new double[] { 167.0, 59.1 }, new double[] { 169.5, 75.6 },
                new double[] { 174.0, 86.2 }, new double[] { 172.7, 75.3 },
                new double[] { 182.2, 87.1 }, new double[] { 164.1, 55.2 },
                new double[] { 163.0, 57.0 }, new double[] { 171.5, 61.4 },
                new double[] { 184.2, 76.8 }, new double[] { 174.0, 86.8 },
                new double[] { 174.0, 72.2 }, new double[] { 177.0, 71.6 },
                new double[] { 186.0, 84.8 }, new double[] { 167.0, 68.2 },
                new double[] { 171.8, 66.1 }, new double[] { 182.0, 72.0 },
                new double[] { 167.0, 64.6 }, new double[] { 177.8, 74.8 },
                new double[] { 164.5, 70.0 }, new double[] { 192.0, 101.6 },
                new double[] { 175.5, 63.2 }, new double[] { 171.2, 79.1 },
                new double[] { 181.6, 78.9 }, new double[] { 167.4, 67.7 },
                new double[] { 181.1, 66.0 }, new double[] { 177.0, 68.2 },
                new double[] { 174.5, 63.9 }, new double[] { 177.5, 72.0 },
                new double[] { 170.5, 56.8 }, new double[] { 182.4, 74.5 },
                new double[] { 197.1, 90.9 }, new double[] { 180.1, 93.0 },
                new double[] { 175.5, 80.9 }, new double[] { 180.6, 72.7 },
                new double[] { 184.4, 68.0 }, new double[] { 175.5, 70.9 },
                new double[] { 180.6, 72.5 }, new double[] { 177.0, 72.5 },
                new double[] { 177.1, 83.4 }, new double[] { 181.6, 75.5 },
                new double[] { 176.5, 73.0 }, new double[] { 175.0, 70.2 },
                new double[] { 174.0, 73.4 }, new double[] { 165.1, 70.5 },
                new double[] { 177.0, 68.9 }, new double[] { 192.0, 102.3 },
                new double[] { 176.5, 68.4 }, new double[] { 169.4, 65.9 },
                new double[] { 182.1, 75.7 }, new double[] { 179.8, 84.5 },
                new double[] { 175.3, 87.7 }, new double[] { 184.9, 86.4 },
                new double[] { 177.3, 73.2 }, new double[] { 167.4, 53.9 },
                new double[] { 178.1, 72.0 }, new double[] { 168.9, 55.5 },
                new double[] { 157.2, 58.4 }, new double[] { 180.3, 83.2 },
                new double[] { 170.2, 72.7 }, new double[] { 177.8, 64.1 },
                new double[] { 172.7, 72.3 }, new double[] { 165.1, 65.0 },
                new double[] { 186.7, 86.4 }, new double[] { 165.1, 65.0 },
                new double[] { 174.0, 88.6 }, new double[] { 175.3, 84.1 },
                new double[] { 185.4, 66.8 }, new double[] { 177.8, 75.5 },
                new double[] { 180.3, 93.2 }, new double[] { 180.3, 82.7 },
                new double[] { 177.8, 58.0 }, new double[] { 177.8, 79.5 },
                new double[] { 177.8, 78.6 }, new double[] { 177.8, 71.8 },
                new double[] { 177.8, 116.4 }, new double[] { 163.8, 72.2 },
                new double[] { 188.0, 83.6 }, new double[] { 198.1, 85.5 },
                new double[] { 175.3, 90.9 }, new double[] { 166.4, 85.9 },
                new double[] { 190.5, 89.1 }, new double[] { 166.4, 75.0 },
                new double[] { 177.8, 77.7 }, new double[] { 179.7, 86.4 },
                new double[] { 172.7, 90.9 }, new double[] { 190.5, 73.6 },
                new double[] { 185.4, 76.4 }, new double[] { 168.9, 69.1 },
                new double[] { 167.6, 84.5 }, new double[] { 175.3, 64.5 },
                new double[] { 170.2, 69.1 }, new double[] { 190.5, 108.6 },
                new double[] { 177.8, 86.4 }, new double[] { 190.5, 80.9 },
                new double[] { 177.8, 87.7 }, new double[] { 184.2, 94.5 },
                new double[] { 176.5, 80.2 }, new double[] { 177.8, 72.0 },
                new double[] { 180.3, 71.4 }, new double[] { 171.4, 72.7 },
                new double[] { 172.7, 84.1 }, new double[] { 172.7, 76.8 },
                new double[] { 177.8, 63.6 }, new double[] { 177.8, 80.9 },
                new double[] { 182.9, 80.9 }, new double[] { 170.2, 85.5 },
                new double[] { 167.6, 68.6 }, new double[] { 175.3, 67.7 },
                new double[] { 165.1, 66.4 }, new double[] { 185.4, 102.3 },
                new double[] { 181.6, 70.5 }, new double[] { 172.7, 95.9 },
                new double[] { 190.5, 84.1 }, new double[] { 179.1, 87.3 },
                new double[] { 175.3, 71.8 }, new double[] { 170.2, 65.9 },
                new double[] { 193.0, 95.9 }, new double[] { 171.4, 91.4 },
                new double[] { 177.8, 81.8 }, new double[] { 177.8, 96.8 },
                new double[] { 167.6, 69.1 }, new double[] { 167.6, 82.7 },
                new double[] { 180.3, 75.5 }, new double[] { 182.9, 79.5 },
                new double[] { 176.5, 73.6 }, new double[] { 186.7, 91.8 },
                new double[] { 188.0, 84.1 }, new double[] { 188.0, 85.9 },
                new double[] { 177.8, 81.8 }, new double[] { 174.0, 82.5 },
                new double[] { 177.8, 80.5 }, new double[] { 171.4, 70.0 },
                new double[] { 185.4, 81.8 }, new double[] { 185.4, 84.1 },
                new double[] { 188.0, 90.5 }, new double[] { 188.0, 91.4 },
                new double[] { 182.9, 89.1 }, new double[] { 176.5, 85.0 },
                new double[] { 175.3, 69.1 }, new double[] { 175.3, 73.6 },
                new double[] { 188.0, 80.5 }, new double[] { 188.0, 82.7 },
                new double[] { 175.3, 86.4 }, new double[] { 170.5, 67.7 },
                new double[] { 179.1, 92.7 }, new double[] { 177.8, 93.6 },
                new double[] { 175.3, 70.9 }, new double[] { 182.9, 75.0 },
                new double[] { 170.8, 93.2 }, new double[] { 188.0, 93.2 },
                new double[] { 180.3, 77.7 }, new double[] { 177.8, 61.4 },
                new double[] { 185.4, 94.1 }, new double[] { 168.9, 75.0 },
                new double[] { 185.4, 83.6 }, new double[] { 180.3, 85.5 },
                new double[] { 174.0, 73.9 }, new double[] { 167.6, 66.8 },
                new double[] { 182.9, 87.3 }, new double[] { 160.0, 72.3 },
                new double[] { 180.3, 88.6 }, new double[] { 167.6, 75.5 },
                new double[] { 186.7, 101.4 }, new double[] { 175.3, 91.1 },
                new double[] { 175.3, 67.3 }, new double[] { 175.9, 77.7 },
                new double[] { 175.3, 81.8 }, new double[] { 179.1, 75.5 },
                new double[] { 181.6, 84.5 }, new double[] { 177.8, 76.6 },
                new double[] { 182.9, 85.0 }, new double[] { 177.8, 102.5 },
                new double[] { 184.2, 77.3 }, new double[] { 179.1, 71.8 },
                new double[] { 176.5, 87.9 }, new double[] { 188.0, 94.3 },
                new double[] { 174.0, 70.9 }, new double[] { 167.6, 64.5 },
                new double[] { 170.2, 77.3 }, new double[] { 167.6, 72.3 },
                new double[] { 188.0, 87.3 }, new double[] { 174.0, 80.0 },
                new double[] { 176.5, 82.3 }, new double[] { 180.3, 73.6 },
                new double[] { 167.6, 74.1 }, new double[] { 188.0, 85.9 },
                new double[] { 180.3, 73.2 }, new double[] { 167.6, 76.3 },
                new double[] { 183.0, 65.9 }, new double[] { 183.0, 90.9 },
                new double[] { 179.1, 89.1 }, new double[] { 170.2, 62.3 },
                new double[] { 177.8, 82.7 }, new double[] { 179.1, 79.1 },
                new double[] { 190.5, 98.2 }, new double[] { 177.8, 84.1 },
                new double[] { 180.3, 83.2 }, new double[] { 180.3, 83.2 });

        return scatterMaleData;
    }

    private DateTimePoint[] getDateTimeSeriesPoints(DateTimeSeries series) {
        return getDateTimePoints(series, 0.8446, 0.8445, 0.8444, 0.8451,
                0.8418, 0.8264, 0.8258, 0.8232, 0.8233, 0.8258, 0.8283, 0.8278,
                0.8256, 0.8292, 0.8239, 0.8239, 0.8245, 0.8265, 0.8261, 0.8269,
                0.8273, 0.8244, 0.8244, 0.8172, 0.8139, 0.8146, 0.8164, 0.82,
                0.8269, 0.8269, 0.8269, 0.8258, 0.8247, 0.8286, 0.8289, 0.8316,
                0.832, 0.8333, 0.8352, 0.8357, 0.8355, 0.8354, 0.8403, 0.8403,
                0.8406, 0.8403, 0.8396, 0.8418, 0.8409, 0.8384, 0.8386, 0.8372,
                0.839, 0.84, 0.8389, 0.84, 0.8423, 0.8423, 0.8435, 0.8422,
                0.838, 0.8373, 0.8316, 0.8303, 0.8303, 0.8302, 0.8369, 0.84,
                0.8385, 0.84, 0.8401, 0.8402, 0.8381, 0.8351, 0.8314, 0.8273,
                0.8213, 0.8207, 0.8207, 0.8215, 0.8242, 0.8273, 0.8301, 0.8346,
                0.8312, 0.8312, 0.8312, 0.8306, 0.8327, 0.8282, 0.824, 0.8255,
                0.8256, 0.8273, 0.8209, 0.8151, 0.8149, 0.8213, 0.8273, 0.8273,
                0.8261, 0.8252, 0.824, 0.8262, 0.8258, 0.8261, 0.826, 0.8199,
                0.8153, 0.8097, 0.8101, 0.8119, 0.8107, 0.8105, 0.8084, 0.8069,
                0.8047, 0.8023, 0.7965, 0.7919, 0.7921, 0.7922, 0.7934, 0.7918,
                0.7915, 0.787, 0.7861, 0.7861, 0.7853, 0.7867, 0.7827, 0.7834,
                0.7766, 0.7751, 0.7739, 0.7767, 0.7802, 0.7788, 0.7828, 0.7816,
                0.7829, 0.783, 0.7829, 0.7781, 0.7811, 0.7831, 0.7826, 0.7855,
                0.7855, 0.7845, 0.7798, 0.7777, 0.7822, 0.7785, 0.7744, 0.7743,
                0.7726, 0.7766, 0.7806, 0.785, 0.7907, 0.7912, 0.7913, 0.7931,
                0.7952, 0.7951, 0.7928, 0.791, 0.7913, 0.7912, 0.7941, 0.7953,
                0.7921, 0.7919, 0.7968, 0.7999, 0.7999, 0.7974, 0.7942, 0.796,
                0.7969, 0.7862, 0.7821, 0.7821, 0.7821, 0.7811, 0.7833, 0.7849,
                0.7819, 0.7809, 0.7809, 0.7827, 0.7848, 0.785, 0.7873, 0.7894,
                0.7907, 0.7909, 0.7947, 0.7987, 0.799, 0.7927, 0.79, 0.7878,
                0.7878, 0.7907, 0.7922, 0.7937, 0.786, 0.787, 0.7838, 0.7838,
                0.7837, 0.7836, 0.7806, 0.7825, 0.7798, 0.777, 0.777, 0.7772,
                0.7793, 0.7788, 0.7785, 0.7832, 0.7865, 0.7865, 0.7853, 0.7847,
                0.7809, 0.778, 0.7799, 0.78, 0.7801, 0.7765, 0.7785, 0.7811,
                0.782, 0.7835, 0.7845, 0.7844, 0.782, 0.7811, 0.7795, 0.7794,
                0.7806, 0.7794, 0.7794, 0.7778, 0.7793, 0.7808, 0.7824, 0.787,
                0.7894, 0.7893, 0.7882, 0.7871, 0.7882, 0.7871, 0.7878, 0.79,
                0.7901, 0.7898, 0.7879, 0.7886, 0.7858, 0.7814, 0.7825, 0.7826,
                0.7826, 0.786, 0.7878, 0.7868, 0.7883, 0.7893, 0.7892, 0.7876,
                0.785, 0.787, 0.7873, 0.7901, 0.7936, 0.7939, 0.7938, 0.7956,
                0.7975, 0.7978, 0.7972, 0.7995, 0.7995, 0.7994, 0.7976, 0.7977,
                0.796, 0.7922, 0.7928, 0.7929, 0.7948, 0.797, 0.7953, 0.7907,
                0.7872, 0.7852, 0.7852, 0.786, 0.7862, 0.7836, 0.7837, 0.784,
                0.7867, 0.7867, 0.7869, 0.7837, 0.7827, 0.7825, 0.7779, 0.7791,
                0.779, 0.7787, 0.78, 0.7807, 0.7803, 0.7817, 0.7799, 0.7799,
                0.7795, 0.7801, 0.7765, 0.7725, 0.7683, 0.7641, 0.7639, 0.7616,
                0.7608, 0.759, 0.7582, 0.7539, 0.75, 0.75, 0.7507, 0.7505,
                0.7516, 0.7522, 0.7531, 0.7577, 0.7577, 0.7582, 0.755, 0.7542,
                0.7576, 0.7616, 0.7648, 0.7648, 0.7641, 0.7614, 0.757, 0.7587,
                0.7588, 0.762, 0.762, 0.7617, 0.7618, 0.7615, 0.7612, 0.7596,
                0.758, 0.758, 0.758, 0.7547, 0.7549, 0.7613, 0.7655, 0.7693,
                0.7694, 0.7688, 0.7678, 0.7708, 0.7727, 0.7749, 0.7741, 0.7741,
                0.7732, 0.7727, 0.7737, 0.7724, 0.7712, 0.772, 0.7721, 0.7717,
                0.7704, 0.769, 0.7711, 0.774, 0.7745, 0.7745, 0.774, 0.7716,
                0.7713, 0.7678, 0.7688, 0.7718, 0.7718, 0.7728, 0.7729, 0.7698,
                0.7685, 0.7681, 0.769, 0.769, 0.7698, 0.7699, 0.7651, 0.7613,
                0.7616, 0.7614, 0.7614, 0.7607, 0.7602, 0.7611, 0.7622, 0.7615,
                0.7598, 0.7598, 0.7592, 0.7573, 0.7566, 0.7567, 0.7591, 0.7582,
                0.7585, 0.7613, 0.7631, 0.7615, 0.76, 0.7613, 0.7627, 0.7627,
                0.7608, 0.7583, 0.7575, 0.7562, 0.752, 0.7512, 0.7512, 0.7517,
                0.752, 0.7511, 0.748, 0.7509, 0.7531, 0.7531, 0.7527, 0.7498,
                0.7493, 0.7504, 0.75, 0.7491, 0.7491, 0.7485, 0.7484, 0.7492,
                0.7471, 0.7459, 0.7477, 0.7477, 0.7483, 0.7458, 0.7448, 0.743,
                0.7399, 0.7395, 0.7395, 0.7378, 0.7382, 0.7362, 0.7355, 0.7348,
                0.7361, 0.7361, 0.7365, 0.7362, 0.7331, 0.7339, 0.7344, 0.7327,
                0.7327, 0.7336, 0.7333, 0.7359, 0.7359, 0.7372, 0.736, 0.736,
                0.735, 0.7365, 0.7384, 0.7395, 0.7413, 0.7397, 0.7396, 0.7385,
                0.7378, 0.7366, 0.74, 0.7411, 0.7406, 0.7405, 0.7414, 0.7431,
                0.7431, 0.7438, 0.7443, 0.7443, 0.7443, 0.7434, 0.7429, 0.7442,
                0.744, 0.7439, 0.7437, 0.7437, 0.7429, 0.7403, 0.7399, 0.7418,
                0.7468, 0.748, 0.748, 0.749, 0.7494, 0.7522, 0.7515, 0.7502,
                0.7472, 0.7472, 0.7462, 0.7455, 0.7449, 0.7467, 0.7458, 0.7427,
                0.7427, 0.743, 0.7429, 0.744, 0.743, 0.7422, 0.7388, 0.7388,
                0.7369, 0.7345, 0.7345, 0.7345, 0.7352, 0.7341, 0.7341, 0.734,
                0.7324, 0.7272, 0.7264, 0.7255, 0.7258, 0.7258, 0.7256, 0.7257,
                0.7247, 0.7243, 0.7244, 0.7235, 0.7235, 0.7235, 0.7235, 0.7262,
                0.7288, 0.7301, 0.7337, 0.7337, 0.7324, 0.7297, 0.7317, 0.7315,
                0.7288, 0.7263, 0.7263, 0.7242, 0.7253, 0.7264, 0.727, 0.7312,
                0.7305, 0.7305, 0.7318, 0.7358, 0.7409, 0.7454, 0.7437, 0.7424,
                0.7424, 0.7415, 0.7419, 0.7414, 0.7377, 0.7355, 0.7315, 0.7315,
                0.732, 0.7332, 0.7346, 0.7328, 0.7323, 0.734, 0.734, 0.7336,
                0.7351, 0.7346, 0.7321, 0.7294, 0.7266, 0.7266, 0.7254, 0.7242,
                0.7213, 0.7197, 0.7209, 0.721, 0.721, 0.721, 0.7209, 0.7159,
                0.7133, 0.7105, 0.7099, 0.7099, 0.7093, 0.7093, 0.7076, 0.707,
                0.7049, 0.7012, 0.7011, 0.7019, 0.7046, 0.7063, 0.7089, 0.7077,
                0.7077, 0.7077, 0.7091, 0.7118, 0.7079, 0.7053, 0.705, 0.7055,
                0.7055, 0.7045, 0.7051, 0.7051, 0.7017, 0.7, 0.6995, 0.6994,
                0.7014, 0.7036, 0.7021, 0.7002, 0.6967, 0.695, 0.695, 0.6939,
                0.694, 0.6922, 0.6919, 0.6914, 0.6894, 0.6891, 0.6904, 0.689,
                0.6834, 0.6823, 0.6807, 0.6815, 0.6815, 0.6847, 0.6859, 0.6822,
                0.6827, 0.6837, 0.6823, 0.6822, 0.6822, 0.6792, 0.6746, 0.6735,
                0.6731, 0.6742, 0.6744, 0.6739, 0.6731, 0.6761, 0.6761, 0.6785,
                0.6818, 0.6836, 0.6823, 0.6805, 0.6793, 0.6849, 0.6833, 0.6825,
                0.6825, 0.6816, 0.6799, 0.6813, 0.6809, 0.6868, 0.6933, 0.6933,
                0.6945, 0.6944, 0.6946, 0.6964, 0.6965, 0.6956, 0.6956, 0.695,
                0.6948, 0.6928, 0.6887, 0.6824, 0.6794, 0.6794, 0.6803, 0.6855,
                0.6824, 0.6791, 0.6783, 0.6785, 0.6785, 0.6797, 0.68, 0.6803,
                0.6805, 0.676, 0.677, 0.677, 0.6736, 0.6726, 0.6764, 0.6821,
                0.6831, 0.6842, 0.6842, 0.6887, 0.6903, 0.6848, 0.6824, 0.6788,
                0.6814, 0.6814, 0.6797, 0.6769, 0.6765, 0.6733, 0.6729, 0.6758,
                0.6758, 0.675, 0.678, 0.6833, 0.6856, 0.6903, 0.6896, 0.6896,
                0.6882, 0.6879, 0.6862, 0.6852, 0.6823, 0.6813, 0.6813, 0.6822,
                0.6802, 0.6802, 0.6784, 0.6748, 0.6747, 0.6747, 0.6748, 0.6733,
                0.665, 0.6611, 0.6583, 0.659, 0.659, 0.6581, 0.6578, 0.6574,
                0.6532, 0.6502, 0.6514, 0.6514, 0.6507, 0.651, 0.6489, 0.6424,
                0.6406, 0.6382, 0.6382, 0.6341, 0.6344, 0.6378, 0.6439, 0.6478,
                0.6481, 0.6481, 0.6494, 0.6438, 0.6377, 0.6329, 0.6336, 0.6333,
                0.6333, 0.633, 0.6371, 0.6403, 0.6396, 0.6364, 0.6356, 0.6356,
                0.6368, 0.6357, 0.6354, 0.632, 0.6332, 0.6328, 0.6331, 0.6342,
                0.6321, 0.6302, 0.6278, 0.6308, 0.6324, 0.6324, 0.6307, 0.6277,
                0.6269, 0.6335, 0.6392, 0.64, 0.6401, 0.6396, 0.6407, 0.6423,
                0.6429, 0.6472, 0.6485, 0.6486, 0.6467, 0.6444, 0.6467, 0.6509,
                0.6478, 0.6461, 0.6461, 0.6468, 0.6449, 0.647, 0.6461, 0.6452,
                0.6422, 0.6422, 0.6425, 0.6414, 0.6366, 0.6346, 0.635, 0.6346,
                0.6346, 0.6343, 0.6346, 0.6379, 0.6416, 0.6442, 0.6431, 0.6431,
                0.6435, 0.644, 0.6473, 0.6469, 0.6386, 0.6356, 0.634, 0.6346,
                0.643, 0.6452, 0.6467, 0.6506, 0.6504, 0.6503, 0.6481, 0.6451,
                0.645, 0.6441, 0.6414, 0.6409, 0.6409, 0.6428, 0.6431, 0.6418,
                0.6371, 0.6349, 0.6333, 0.6334, 0.6338, 0.6342, 0.632, 0.6318,
                0.637, 0.6368, 0.6368, 0.6383, 0.6371, 0.6371, 0.6355, 0.632,
                0.6277, 0.6276, 0.6291, 0.6274, 0.6293, 0.6311, 0.631, 0.6312,
                0.6312, 0.6304, 0.6294, 0.6348, 0.6378, 0.6368, 0.6368, 0.6368,
                0.636, 0.637, 0.6418, 0.6411, 0.6435, 0.6427, 0.6427, 0.6419,
                0.6446, 0.6468, 0.6487, 0.6594, 0.6666, 0.6666, 0.6678, 0.6712,
                0.6705, 0.6718, 0.6784, 0.6811, 0.6811, 0.6794, 0.6804, 0.6781,
                0.6756, 0.6735, 0.6763, 0.6762, 0.6777, 0.6815, 0.6802, 0.678,
                0.6796, 0.6817, 0.6817, 0.6832, 0.6877, 0.6912, 0.6914, 0.7009,
                0.7012, 0.701, 0.7005, 0.7076, 0.7087, 0.717, 0.7105, 0.7031,
                0.7029, 0.7006, 0.7035, 0.7045, 0.6956, 0.6988, 0.6915, 0.6914,
                0.6859, 0.6778, 0.6815, 0.6815, 0.6843, 0.6846, 0.6846, 0.6923,
                0.6997, 0.7098, 0.7188, 0.7232, 0.7262, 0.7266, 0.7359, 0.7368,
                0.7337, 0.7317, 0.7387, 0.7467, 0.7461, 0.7366, 0.7319, 0.7361,
                0.7437, 0.7432, 0.7461, 0.7461, 0.7454, 0.7549, 0.7742, 0.7801,
                0.7903, 0.7876, 0.7928, 0.7991, 0.8007, 0.7823, 0.7661, 0.785,
                0.7863, 0.7862, 0.7821, 0.7858, 0.7731, 0.7779, 0.7844, 0.7866,
                0.7864, 0.7788, 0.7875, 0.7971, 0.8004, 0.7857, 0.7932, 0.7938,
                0.7927, 0.7918, 0.7919, 0.7989, 0.7988, 0.7949, 0.7948, 0.7882,
                0.7745, 0.771, 0.775, 0.7791, 0.7882, 0.7882, 0.7899, 0.7905,
                0.7889, 0.7879, 0.7855, 0.7866, 0.7865, 0.7795, 0.7758, 0.7717,
                0.761, 0.7497, 0.7471, 0.7473, 0.7407, 0.7288, 0.7074, 0.6927,
                0.7083, 0.7191, 0.719, 0.7153, 0.7156, 0.7158, 0.714, 0.7119,
                0.7129, 0.7129, 0.7049, 0.7095).toArray(new DateTimePoint[0]);
    }

    private LinkedHashSet<DateTimePoint> getMasterDetailData(Series series) {
        return getDateTimePoints(series, 0.8446, 0.8445, 0.8444, 0.8451,
                0.8418, 0.8264, 0.8258, 0.8232, 0.8233, 0.8258, 0.8283, 0.8278,
                0.8256, 0.8292, 0.8239, 0.8239, 0.8245, 0.8265, 0.8261, 0.8269,
                0.8273, 0.8244, 0.8244, 0.8172, 0.8139, 0.8146, 0.8164, 0.82,
                0.8269, 0.8269, 0.8269, 0.8258, 0.8247, 0.8286, 0.8289, 0.8316,
                0.832, 0.8333, 0.8352, 0.8357, 0.8355, 0.8354, 0.8403, 0.8403,
                0.8406, 0.8403, 0.8396, 0.8418, 0.8409, 0.8384, 0.8386, 0.8372,
                0.839, 0.84, 0.8389, 0.84, 0.8423, 0.8423, 0.8435, 0.8422,
                0.838, 0.8373, 0.8316, 0.8303, 0.8303, 0.8302, 0.8369, 0.84,
                0.8385, 0.84, 0.8401, 0.8402, 0.8381, 0.8351, 0.8314, 0.8273,
                0.8213, 0.8207, 0.8207, 0.8215, 0.8242, 0.8273, 0.8301, 0.8346,
                0.8312, 0.8312, 0.8312, 0.8306, 0.8327, 0.8282, 0.824, 0.8255,
                0.8256, 0.8273, 0.8209, 0.8151, 0.8149, 0.8213, 0.8273, 0.8273,
                0.8261, 0.8252, 0.824, 0.8262, 0.8258, 0.8261, 0.826, 0.8199,
                0.8153, 0.8097, 0.8101, 0.8119, 0.8107, 0.8105, 0.8084, 0.8069,
                0.8047, 0.8023, 0.7965, 0.7919, 0.7921, 0.7922, 0.7934, 0.7918,
                0.7915, 0.787, 0.7861, 0.7861, 0.7853, 0.7867, 0.7827, 0.7834,
                0.7766, 0.7751, 0.7739, 0.7767, 0.7802, 0.7788, 0.7828, 0.7816,
                0.7829, 0.783, 0.7829, 0.7781, 0.7811, 0.7831, 0.7826, 0.7855,
                0.7855, 0.7845, 0.7798, 0.7777, 0.7822, 0.7785, 0.7744, 0.7743,
                0.7726, 0.7766, 0.7806, 0.785, 0.7907, 0.7912, 0.7913, 0.7931,
                0.7952, 0.7951, 0.7928, 0.791, 0.7913, 0.7912, 0.7941, 0.7953,
                0.7921, 0.7919, 0.7968, 0.7999, 0.7999, 0.7974, 0.7942, 0.796,
                0.7969, 0.7862, 0.7821, 0.7821, 0.7821, 0.7811, 0.7833, 0.7849,
                0.7819, 0.7809, 0.7809, 0.7827, 0.7848, 0.785, 0.7873, 0.7894,
                0.7907, 0.7909, 0.7947, 0.7987, 0.799, 0.7927, 0.79, 0.7878,
                0.7878, 0.7907, 0.7922, 0.7937, 0.786, 0.787, 0.7838, 0.7838,
                0.7837, 0.7836, 0.7806, 0.7825, 0.7798, 0.777, 0.777, 0.7772,
                0.7793, 0.7788, 0.7785, 0.7832, 0.7865, 0.7865, 0.7853, 0.7847,
                0.7809, 0.778, 0.7799, 0.78, 0.7801, 0.7765, 0.7785, 0.7811,
                0.782, 0.7835, 0.7845, 0.7844, 0.782, 0.7811, 0.7795, 0.7794,
                0.7806, 0.7794, 0.7794, 0.7778, 0.7793, 0.7808, 0.7824, 0.787,
                0.7894, 0.7893, 0.7882, 0.7871, 0.7882, 0.7871, 0.7878, 0.79,
                0.7901, 0.7898, 0.7879, 0.7886, 0.7858, 0.7814, 0.7825, 0.7826,
                0.7826, 0.786, 0.7878, 0.7868, 0.7883, 0.7893, 0.7892, 0.7876,
                0.785, 0.787, 0.7873, 0.7901, 0.7936, 0.7939, 0.7938, 0.7956,
                0.7975, 0.7978, 0.7972, 0.7995, 0.7995, 0.7994, 0.7976, 0.7977,
                0.796, 0.7922, 0.7928, 0.7929, 0.7948, 0.797, 0.7953, 0.7907,
                0.7872, 0.7852, 0.7852, 0.786, 0.7862, 0.7836, 0.7837, 0.784,
                0.7867, 0.7867, 0.7869, 0.7837, 0.7827, 0.7825, 0.7779, 0.7791,
                0.779, 0.7787, 0.78, 0.7807, 0.7803, 0.7817, 0.7799, 0.7799,
                0.7795, 0.7801, 0.7765, 0.7725, 0.7683, 0.7641, 0.7639, 0.7616,
                0.7608, 0.759, 0.7582, 0.7539, 0.75, 0.75, 0.7507, 0.7505,
                0.7516, 0.7522, 0.7531, 0.7577, 0.7577, 0.7582, 0.755, 0.7542,
                0.7576, 0.7616, 0.7648, 0.7648, 0.7641, 0.7614, 0.757, 0.7587,
                0.7588, 0.762, 0.762, 0.7617, 0.7618, 0.7615, 0.7612, 0.7596,
                0.758, 0.758, 0.758, 0.7547, 0.7549, 0.7613, 0.7655, 0.7693,
                0.7694, 0.7688, 0.7678, 0.7708, 0.7727, 0.7749, 0.7741, 0.7741,
                0.7732, 0.7727, 0.7737, 0.7724, 0.7712, 0.772, 0.7721, 0.7717,
                0.7704, 0.769, 0.7711, 0.774, 0.7745, 0.7745, 0.774, 0.7716,
                0.7713, 0.7678, 0.7688, 0.7718, 0.7718, 0.7728, 0.7729, 0.7698,
                0.7685, 0.7681, 0.769, 0.769, 0.7698, 0.7699, 0.7651, 0.7613,
                0.7616, 0.7614, 0.7614, 0.7607, 0.7602, 0.7611, 0.7622, 0.7615,
                0.7598, 0.7598, 0.7592, 0.7573, 0.7566, 0.7567, 0.7591, 0.7582,
                0.7585, 0.7613, 0.7631, 0.7615, 0.76, 0.7613, 0.7627, 0.7627,
                0.7608, 0.7583, 0.7575, 0.7562, 0.752, 0.7512, 0.7512, 0.7517,
                0.752, 0.7511, 0.748, 0.7509, 0.7531, 0.7531, 0.7527, 0.7498,
                0.7493, 0.7504, 0.75, 0.7491, 0.7491, 0.7485, 0.7484, 0.7492,
                0.7471, 0.7459, 0.7477, 0.7477, 0.7483, 0.7458, 0.7448, 0.743,
                0.7399, 0.7395, 0.7395, 0.7378, 0.7382, 0.7362, 0.7355, 0.7348,
                0.7361, 0.7361, 0.7365, 0.7362, 0.7331, 0.7339, 0.7344, 0.7327,
                0.7327, 0.7336, 0.7333, 0.7359, 0.7359, 0.7372, 0.736, 0.736,
                0.735, 0.7365, 0.7384, 0.7395, 0.7413, 0.7397, 0.7396, 0.7385,
                0.7378, 0.7366, 0.74, 0.7411, 0.7406, 0.7405, 0.7414, 0.7431,
                0.7431, 0.7438, 0.7443, 0.7443, 0.7443, 0.7434, 0.7429, 0.7442,
                0.744, 0.7439, 0.7437, 0.7437, 0.7429, 0.7403, 0.7399, 0.7418,
                0.7468, 0.748, 0.748, 0.749, 0.7494, 0.7522, 0.7515, 0.7502,
                0.7472, 0.7472, 0.7462, 0.7455, 0.7449, 0.7467, 0.7458, 0.7427,
                0.7427, 0.743, 0.7429, 0.744, 0.743, 0.7422, 0.7388, 0.7388,
                0.7369, 0.7345, 0.7345, 0.7345, 0.7352, 0.7341, 0.7341, 0.734,
                0.7324, 0.7272, 0.7264, 0.7255, 0.7258, 0.7258, 0.7256, 0.7257,
                0.7247, 0.7243, 0.7244, 0.7235, 0.7235, 0.7235, 0.7235, 0.7262,
                0.7288, 0.7301, 0.7337, 0.7337, 0.7324, 0.7297, 0.7317, 0.7315,
                0.7288, 0.7263, 0.7263, 0.7242, 0.7253, 0.7264, 0.727, 0.7312,
                0.7305, 0.7305, 0.7318, 0.7358, 0.7409, 0.7454, 0.7437, 0.7424,
                0.7424, 0.7415, 0.7419, 0.7414, 0.7377, 0.7355, 0.7315, 0.7315,
                0.732, 0.7332, 0.7346, 0.7328, 0.7323, 0.734, 0.734, 0.7336,
                0.7351, 0.7346, 0.7321, 0.7294, 0.7266, 0.7266, 0.7254, 0.7242,
                0.7213, 0.7197, 0.7209, 0.721, 0.721, 0.721, 0.7209, 0.7159,
                0.7133, 0.7105, 0.7099, 0.7099, 0.7093, 0.7093, 0.7076, 0.707,
                0.7049, 0.7012, 0.7011, 0.7019, 0.7046, 0.7063, 0.7089, 0.7077,
                0.7077, 0.7077, 0.7091, 0.7118, 0.7079, 0.7053, 0.705, 0.7055,
                0.7055, 0.7045, 0.7051, 0.7051, 0.7017, 0.7, 0.6995, 0.6994,
                0.7014, 0.7036, 0.7021, 0.7002, 0.6967, 0.695, 0.695, 0.6939,
                0.694, 0.6922, 0.6919, 0.6914, 0.6894, 0.6891, 0.6904, 0.689,
                0.6834, 0.6823, 0.6807, 0.6815, 0.6815, 0.6847, 0.6859, 0.6822,
                0.6827, 0.6837, 0.6823, 0.6822, 0.6822, 0.6792, 0.6746, 0.6735,
                0.6731, 0.6742, 0.6744, 0.6739, 0.6731, 0.6761, 0.6761, 0.6785,
                0.6818, 0.6836, 0.6823, 0.6805, 0.6793, 0.6849, 0.6833, 0.6825,
                0.6825, 0.6816, 0.6799, 0.6813, 0.6809, 0.6868, 0.6933, 0.6933,
                0.6945, 0.6944, 0.6946, 0.6964, 0.6965, 0.6956, 0.6956, 0.695,
                0.6948, 0.6928, 0.6887, 0.6824, 0.6794, 0.6794, 0.6803, 0.6855,
                0.6824, 0.6791, 0.6783, 0.6785, 0.6785, 0.6797, 0.68, 0.6803,
                0.6805, 0.676, 0.677, 0.677, 0.6736, 0.6726, 0.6764, 0.6821,
                0.6831, 0.6842, 0.6842, 0.6887, 0.6903, 0.6848, 0.6824, 0.6788,
                0.6814, 0.6814, 0.6797, 0.6769, 0.6765, 0.6733, 0.6729, 0.6758,
                0.6758, 0.675, 0.678, 0.6833, 0.6856, 0.6903, 0.6896, 0.6896,
                0.6882, 0.6879, 0.6862, 0.6852, 0.6823, 0.6813, 0.6813, 0.6822,
                0.6802, 0.6802, 0.6784, 0.6748, 0.6747, 0.6747, 0.6748, 0.6733,
                0.665, 0.6611, 0.6583, 0.659, 0.659, 0.6581, 0.6578, 0.6574,
                0.6532, 0.6502, 0.6514, 0.6514, 0.6507, 0.651, 0.6489, 0.6424,
                0.6406, 0.6382, 0.6382, 0.6341, 0.6344, 0.6378, 0.6439, 0.6478,
                0.6481, 0.6481, 0.6494, 0.6438, 0.6377, 0.6329, 0.6336, 0.6333,
                0.6333, 0.633, 0.6371, 0.6403, 0.6396, 0.6364, 0.6356, 0.6356,
                0.6368, 0.6357, 0.6354, 0.632, 0.6332, 0.6328, 0.6331, 0.6342,
                0.6321, 0.6302, 0.6278, 0.6308, 0.6324, 0.6324, 0.6307, 0.6277,
                0.6269, 0.6335, 0.6392, 0.64, 0.6401, 0.6396, 0.6407, 0.6423,
                0.6429, 0.6472, 0.6485, 0.6486, 0.6467, 0.6444, 0.6467, 0.6509,
                0.6478, 0.6461, 0.6461, 0.6468, 0.6449, 0.647, 0.6461, 0.6452,
                0.6422, 0.6422, 0.6425, 0.6414, 0.6366, 0.6346, 0.635, 0.6346,
                0.6346, 0.6343, 0.6346, 0.6379, 0.6416, 0.6442, 0.6431, 0.6431,
                0.6435, 0.644, 0.6473, 0.6469, 0.6386, 0.6356, 0.634, 0.6346,
                0.643, 0.6452, 0.6467, 0.6506, 0.6504, 0.6503, 0.6481, 0.6451,
                0.645, 0.6441, 0.6414, 0.6409, 0.6409, 0.6428, 0.6431, 0.6418,
                0.6371, 0.6349, 0.6333, 0.6334, 0.6338, 0.6342, 0.632, 0.6318,
                0.637, 0.6368, 0.6368, 0.6383, 0.6371, 0.6371, 0.6355, 0.632,
                0.6277, 0.6276, 0.6291, 0.6274, 0.6293, 0.6311, 0.631, 0.6312,
                0.6312, 0.6304, 0.6294, 0.6348, 0.6378, 0.6368, 0.6368, 0.6368,
                0.636, 0.637, 0.6418, 0.6411, 0.6435, 0.6427, 0.6427, 0.6419,
                0.6446, 0.6468, 0.6487, 0.6594, 0.6666, 0.6666, 0.6678, 0.6712,
                0.6705, 0.6718, 0.6784, 0.6811, 0.6811, 0.6794, 0.6804, 0.6781,
                0.6756, 0.6735, 0.6763, 0.6762, 0.6777, 0.6815, 0.6802, 0.678,
                0.6796, 0.6817, 0.6817, 0.6832, 0.6877, 0.6912, 0.6914, 0.7009,
                0.7012, 0.701, 0.7005, 0.7076, 0.7087, 0.717, 0.7105, 0.7031,
                0.7029, 0.7006, 0.7035, 0.7045, 0.6956, 0.6988, 0.6915, 0.6914,
                0.6859, 0.6778, 0.6815, 0.6815, 0.6843, 0.6846, 0.6846, 0.6923,
                0.6997, 0.7098, 0.7188, 0.7232, 0.7262, 0.7266, 0.7359, 0.7368,
                0.7337, 0.7317, 0.7387, 0.7467, 0.7461, 0.7366, 0.7319, 0.7361,
                0.7437, 0.7432, 0.7461, 0.7461, 0.7454, 0.7549, 0.7742, 0.7801,
                0.7903, 0.7876, 0.7928, 0.7991, 0.8007, 0.7823, 0.7661, 0.785,
                0.7863, 0.7862, 0.7821, 0.7858, 0.7731, 0.7779, 0.7844, 0.7866,
                0.7864, 0.7788, 0.7875, 0.7971, 0.8004, 0.7857, 0.7932, 0.7938,
                0.7927, 0.7918, 0.7919, 0.7989, 0.7988, 0.7949, 0.7948, 0.7882,
                0.7745, 0.771, 0.775, 0.7791, 0.7882, 0.7882, 0.7899, 0.7905,
                0.7889, 0.7879, 0.7855, 0.7866, 0.7865, 0.7795, 0.7758, 0.7717,
                0.761, 0.7497, 0.7471, 0.7473, 0.7407, 0.7288, 0.7074, 0.6927,
                0.7083, 0.7191, 0.719, 0.7153, 0.7156, 0.7158, 0.714, 0.7119,
                0.7129, 0.7129, 0.7049, 0.7095);
    }
    
}
