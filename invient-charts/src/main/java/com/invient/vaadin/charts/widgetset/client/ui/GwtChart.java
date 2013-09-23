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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtSeriesDataOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtSubtitleOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtTitleOptions;

/**
 * A JavaScript overlay representing Highcharts Chart object
 * 
 * @author Invient
 * 
 */
class GwtChart extends JavaScriptObject {

    protected GwtChart() {
    }

    public static final GwtChart create(GwtInvientChartsConfig options) {
        return GwtInvientChartsUtil.newChart(options).cast();
    }

    public native final GwtSeries addSeries(
            GwtSeriesDataOptions seriesDataOptions, boolean redraw,
            boolean animation) /*-{
                               return this.addSeries(seriesDataOptions, redraw, animation);
                               }-*/;

    public native final GwtSeries addSeries(
            GwtSeriesDataOptions seriesDataOptions, boolean redraw) /*-{
                                                                    return this.addSeries(seriesDataOptions, redraw);
                                                                    }-*/;

    public native final GwtSeries addSeries(
            GwtSeriesDataOptions seriesDataOptions) /*-{
                                                    return this.addSeries(seriesDataOptions);
                                                    }-*/;

    public native final void destroy() /*-{
                                       this.destroy();
                                       }-*/;

    public final GwtAxis getAxis(String id) {
        return get(id).cast();
    }

    public final GwtSeries getSeries(String id) {
        return get(id).cast();
    }

    public final GwtPoint getPoint(String id) {
        return get(id).cast();
    }

    // Point|Series|Axis
    private native final JavaScriptObject get(String id) /*-{
                                                         return this.get(id);
                                                         }-*/;

    public native final JsArray<GwtPoint> getSelectedPoints() /*-{
                                                              return this.getSelectedPoints();
                                                              }-*/;

    public native final JsArray<GwtSeries> getSelectedSeries() /*-{
                                                               	return this.getSelectedSeries();
                                                               	}-*/;

    //
    public native final String getSVG(GwtInvientChartsConfig chartOptions) /*-{
                                                                           return this.getSVG(chartOptions);
                                                                           }-*/;

    public native final void printChartOnClient() /*-{
        // this.print();
        $wnd.printInvientChart(this);
    }-*/;
    
    public native final void printInvientChart() /*-{            
            var chart = this;
            if (chart.isPrinting) { // printing is in-progress
                return;
            }
            var container = chart.container,
            origLeft = [], origPos = [],
            origParent = container.parentNode,
            body = $wnd.document.body,
            childNodes = body.childNodes;
            
            chart.isPrinting = true;
            // hide all body content
            for(var cnt=0; cnt<childNodes.length; cnt++) {
                var node = childNodes[cnt];                
                if (node.nodeType == 1) {                
                    origPos[cnt] = node.style.position;
                    origLeft[cnt] = node.style.left;
                    node.style.position = 'absolute';
                    node.style.left = '-99999px';                    
                }
            }
            
            var containerOrgPos = container.style.position;
            container.style.position = 'absolute';
            container.style.top = '0px';
            container.style.left = '0px';            

            // pull out the chart
            body.appendChild(container);
            // print
            $wnd.print();
            
            // allow the browser to prepare before reverting
            setTimeout(function() {
            
                container.style.position = containerOrgPos;
                // put the chart back in
                origParent.appendChild(container);

                // restore all body content
                for(var cnt=0; cnt<childNodes.length; cnt++) {
                    var node = childNodes[cnt];
                    if (node.nodeType == 1) {               
                        node.style.position = origPos[cnt];
                        node.style.left = origLeft[cnt];
                    }
                }
                
                chart.isPrinting = false;

            }, 1000);
    }-*/;

    public native final void redraw() /*-{
                                      this.redraw();
                                      }-*/;

    public native final void setSize(int width, int height, boolean animation) /*-{
                                                                               this.setSize(width, height, animation);
                                                                               }-*/;

    public native final void setSize(int width, int height) /*-{
                                                            this.setSize(width, height);
                                                            }-*/;

    public native final void setTitle(GwtTitleOptions title) /*-{
                                                              this.setTitle(title);
                                                              }-*/;

    public native final void setSubtitle(GwtSubtitleOptions subtitle) /*-{
                                                                      this.setTitle(null, subtitle);
                                                                      }-*/;

    public native final void setTitle(GwtTitleOptions title,
            GwtSubtitleOptions subtitle) /*-{
                                         this.setTitle(title, subtitle);
                                         }-*/;

    public native final void hideLoading() /*-{
                                           this.hideLoading();
                                           }-*/;

    public native final void showLoading(String loadingText) /*-{
                                                             this.showLoading(loadingText);
                                                             }-*/;

    public native final GwtInvientChartsConfig getOptions() /*-{
                                                            return this.options;
                                                            }-*/;

    public native final JsArray<GwtSeries> getSeries() /*-{
                                                       return this.series;
                                                       }-*/;

    public native final JsArray<GwtAxis> getXAxes() /*-{
                                                    return this.xAxis;
                                                    }-*/;

    public native final JsArray<GwtAxis> getYAxes() /*-{
                                                    return this.yAxis;
                                                    }-*/;

}
