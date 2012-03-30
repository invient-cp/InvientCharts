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
import com.google.gwt.core.client.JsArrayString;

import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtAxisBaseOptions.GwtPlotBands;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtAxisBaseOptions.GwtPlotLines;

/**
 * A JavaScript overlay representing Highcharts Axis object
 * @author Invient
 *
 */
final class GwtAxis extends JavaScriptObject {

    protected GwtAxis() {
    }

    public native final static GwtAxis create() /*-{
                                                return {};
                                                }-*/;

    public native final void addPlotBand(GwtPlotBands plotBandOptions) /*-{
                                                                           this.addPlotBand(plotBandOptions);
                                                                           }-*/;

    public native final void addPlotLine(GwtPlotLines plotLineOptions) /*-{
                                                                           this.addPlotLine(plotLineOptions);
                                                                           }-*/;

    public native final void removePlotBand(String id) /*-{
                                                       this.removePlotBand(id);
                                                       }-*/;

    public native final void removePlotLine(String id) /*-{
                                                       this.removePlotLine(id);
                                                       }-*/;

    public native final void setCategories(JsArrayString categories,
            boolean redraw) /*-{
                            this.setCategories(categories, redraw);
                            }-*/;

    public native final JsArrayString getCategories() /*-{
                            return this.categories;
                            }-*/;

    public native final GwtAxisExtreme getExtremes() /*-{
                                                  return this.getExtremes();
                                                  }-*/;

    public native final void setExtremes(double min, double max,
            boolean redraw, boolean animation) /*-{
                                               this.setExtremes(min, max, redraw, animation);
                                               }-*/;

    public native final void setExtremes(double min, double max,
            boolean redraw) /*-{
                                               this.setExtremes(min, max, redraw);
                                               }-*/;

    static class GwtAxisExtreme extends JavaScriptObject {
        protected GwtAxisExtreme() {
        }

        public native final static GwtAxisExtreme create() /*-{
                                                        return { };
                                                        }-*/;

        public native final double getDataMax() /*-{
                                                return this.dataMax;
                                                }-*/;

        public native final double getDataMin() /*-{
                                                return this.dataMin;
                                                }-*/;

        public native final double getMax() /*-{
                                            return this.max;
                                            }-*/;

        public native final double getMin() /*-{
                                            return this.min;
                                            }-*/;
    }
}
