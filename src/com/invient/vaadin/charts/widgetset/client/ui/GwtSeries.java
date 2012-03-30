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
import com.google.gwt.core.client.JsArrayNumber;

import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtAreaOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtAreaSplineOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtBarOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtColumnOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtLineOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtPieOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtScatterOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtSeriesGeneralOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtSplineOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPointOptions;

/**
 * A JavaScript overlay representing Highcharts Series object
 * 
 * @author Invient
 * 
 */
class GwtSeries extends JavaScriptObject {

    protected GwtSeries() {
    }

    public native final static GwtSeries create() /*-{
                                                  return {};
                                                  }-*/;

    public native final void addPoint(double point, boolean redraw,
            boolean shift, boolean animation) /*-{
                                              this.addPoint(point, redraw, shift, animation);
                                              }-*/;

    public native final void addPoint(JsArrayNumber point, boolean redraw,
            boolean shift, boolean animation) /*-{
                                              this.addPoint(point, redraw, shift, animation);
                                              }-*/;

    public native final void addPoint(GwtPointOptions pointOptions,
            boolean redraw, boolean shift, boolean animation) /*-{
                                                              this.addPoint(pointOptions, redraw, shift, animation);
                                                              }-*/;

    public native final void addPoint(GwtPointOptions pointOptions,
            boolean redraw, boolean shift) /*-{
                                                              this.addPoint(pointOptions, redraw, shift);
                                                              }-*/;

    public native final void addPoint(GwtPointOptions pointOptions,
            boolean redraw) /*-{
                                                              this.addPoint(pointOptions, redraw);
                                                              }-*/;

    public native final void hide() /*-{
                                    this.hide();
                                    }-*/;

    public native final void show() /*-{
                                    this.show();
                                    }-*/;

    public native final void remove(boolean redraw) /*-{
                                                    this.remove(redraw);
                                                    }-*/;

    public native final void select(Boolean selected) /*-{
                                                      this.select(selected);
                                                      }-*/;

    public native final void setData(JsArrayNumber data, boolean redraw) /*-{
                                                                         this.data = data;
                                                                         }-*/;

    public native final void setData(JsArray<JsArrayNumber> data, boolean redraw) /*-{
                                                                                  this.data = data;
                                                                                  }-*/;

    public native final void setDataAsPointOptions(
            JsArray<GwtPointOptions> data, boolean redraw) /*-{
                                                           this.data = data;
                                                           }-*/;

    public native final JsArray<GwtPoint> getData() /*-{
                                                    return this.data;
                                                    }-*/;

    public native final GwtChart getChart() /*-{
                                            return this.chart;
                                            }-*/;

    public native final String getName() /*-{
                                         return this.name;
                                         }-*/;

    public native final String getType() /*-{
                                         return this.type;
                                         }-*/;

    // Call appropriate method based on type of the Series to get
    // appropriate Series options
    public native final GwtSeriesGeneralOptions getSeriesGeneralOptions() /*-{
                                                                          return this.options;
                                                                          }-*/;

    public native final GwtLineOptions getLineOptions() /*-{
                                                        return this.options;
                                                        }-*/;

    public native final GwtAreaOptions getAreaOptions() /*-{
                                                        return this.options;
                                                        }-*/;

    public native final GwtAreaSplineOptions getAreaSplineOptions() /*-{
                                                                    return this.options;
                                                                    }-*/;

    public native final GwtScatterOptions getScatterOptions() /*-{
                                                              return this.options;
                                                              }-*/;

    public native final GwtSplineOptions getSplineOptions() /*-{
                                                            return this.options;
                                                            }-*/;

    public native final GwtPieOptions getPieOptions() /*-{
                                                      return this.options;
                                                      }-*/;

    public native final GwtBarOptions getBarOptions() /*-{
                                                      return this.options;
                                                      }-*/;

    public native final GwtColumnOptions getColumnOptions() /*-{
                                                            return this.options;
                                                            }-*/;

    public native final boolean isSelected() /*-{
                                             return this.selected;
                                             }-*/;

    public native final boolean isVisible() /*-{
                                            return this.visible;
                                            }-*/;

    public native final GwtAxis getXAxis() /*-{
                                           return this.xAxis;
                                           }-*/;

    public native final GwtAxis getYAxis() /*-{
                                           return this.yAxis;
                                           }-*/;

}
