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

import java.util.UUID;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtExportingOptions;

/**
 * 
 * This is the base class for InvientCharts. 
 * It does followings:
 * <ul>
 * <li>
 *  creates container <div /> element for the chart. 
 * </li>
 * <li>
 *  destroys chart when the container <div /> element is removed
 * </li>
 * <li>
 *  defines a set of JavaScript functions to register various event listeners for the Highcharts. These JavaScript function will call
 *  predefined GWT methods where actual code is written to communicate to the server-side Vaadin components.
 * </li>
 * </ul>
 * 
 * @author Invient
 *
 */
class GwtInvientCharts extends Widget {

	private static long chartId = System.currentTimeMillis();

    protected GwtChart chart;
    protected String divId = "hchartdiv_" + chartId++;
 
    // Cached chart options so that we can re-load them if we are detached and re-attached.
    private GwtInvientChartsConfig options;

    public GwtInvientCharts() {
        DivElement divElement = Document.get().createDivElement();
        divElement.setId(divId);
        setElement(divElement);
    }

    /** {@inheritDoc} */
    @Override
    public void onLoad() {
        if(chart == null && options != null) {
            // And we are back again... (component was re-attached).
            createChart(options);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void onUnload() {
        // Remove chart and purge memory (prevents memory leaks)
        if (chart != null) {
            chart.destroy();
            // chart variable must be set to null so that the chart can be created again 
            // when using chart with Portal Layout.
            // In portal layout, when one window is dragged, the chart is removed from
            // html dom and when the window is dropped to a new position the chart
            // needs to be recreated.
            chart = null;
        }
    }

    protected void createChart(GwtInvientChartsConfig options) {
        // For now, disable exporting icons on client. If we need then we would 
        // add support for setting exporting options through server-side code
        GwtExportingOptions exportingOptions = GwtExportingOptions.create();
        exportingOptions.setEnabled(false);
        options.setExportingOptions(exportingOptions);
        // 
        chart = GwtInvientChartsUtil.newChart(options);
        this.options = options;
    }

    protected static final class EventCallbacks {

        // Chart events
        protected static native final JavaScriptObject getChartAddSeries(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::chartAddSeriesListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtChart;)(this);
                                        };
                                        }-*/;

        protected static native final JavaScriptObject getChartClick(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;
                                        var mouseCoords = $wnd.getMouseCoords(event);
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::chartClickListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtChart;DDII)(this, event.xAxis[0].value, event.yAxis[0].value, mouseCoords.x, mouseCoords.y);
                                        };
                                        }-*/;

        protected static native final JavaScriptObject getClientChartSelection(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;
                                        if(event.xAxis && event.yAxis) {                                        
                                            thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::chartSelectionListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtChart;DDDD)(this, event.xAxis[0].min, event.xAxis[0].max, event.yAxis[0].min, event.yAxis[0].max);
                                        }
                                        else {
                                            thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::chartResetZoomListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtChart;)(this);
                                        }
                                        };
                                        }-*/;

        protected static native final JavaScriptObject getServerChartSelection(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;
                                        if(event.xAxis && event.yAxis) {                                        
                                            thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::chartSelectionListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtChart;DDDD)(this, event.xAxis[0].min, event.xAxis[0].max, event.yAxis[0].min, event.yAxis[0].max);
                                        }
                                        else {
                                            thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::chartResetZoomListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtChart;)(this);
                                        }
                                        return false;
                                        };
                                        }-*/;

        // Series events
        protected static native final JavaScriptObject getSeriesClick(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;
                                        var mouseCoords = $wnd.getMouseCoords(event);
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::seriesClickListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtSeries;Lcom/invient/vaadin/charts/widgetset/client/ui/GwtPoint;II)(this, event.point, mouseCoords.x, mouseCoords.y);
                                        };
                                        }-*/;

        protected static native final JavaScriptObject getSeriesHide(
                VInvientCharts graphWidget) /*-{
                                        return function() {
                                        var thisWidget = graphWidget;
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::seriesHideListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtSeries;)(this);                                                                   
                                        };
                                        }-*/;

        protected static native final JavaScriptObject getSeriesShow(
                VInvientCharts graphWidget) /*-{
                                        return function() {
                                        var thisWidget = graphWidget;
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::seriesShowListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtSeries;)(this);                                                                   
                                        };
                                        }-*/;

        protected static native final JavaScriptObject getSeriesLegendItemClick(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;                                                                             
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::seriesLegendItemClickListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtSeries;)(this);                                                                             
                                        };
                                        }-*/;

        // Point events
        protected static native final JavaScriptObject getPieLegendItemClick(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::pieLegendItemClickListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtPoint;)(this);
                                        };
                                        }-*/;

        protected static native final JavaScriptObject getPointClick(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;
                                        var mouseCoords = $wnd.getMouseCoords(event);
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::pointClickListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtPoint;II)(this, mouseCoords.x, mouseCoords.y);                                                                             
                                        };
                                        }-*/;

        protected static native final JavaScriptObject getPointSelect(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::pointSelectListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtPoint;)(this);
                                        };
                                        }-*/;

        protected static native final JavaScriptObject getPointUnselect(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::pointUnselectListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtPoint;)(this);
                                        };
                                        }-*/;

        protected static native final JavaScriptObject getPointRemove(
                VInvientCharts graphWidget) /*-{
                                        return function(event) {
                                        var thisWidget = graphWidget;
                                        thisWidget.@com.invient.vaadin.charts.widgetset.client.ui.VInvientCharts::pointRemoveListener(Lcom/invient/vaadin/charts/widgetset/client/ui/GwtPoint;)(this);
                                        };
                                        }-*/;

    }

//    /** {@inheritDoc} */
//    @Override
//    public void setHeight(String height) {
//        getElement().setAttribute("height", height);
//    }
//
//    /** {@inheritDoc} */
//    @Override
//    public void setWidth(String width) {
//        getElement().setAttribute("width", width);
//    }

}