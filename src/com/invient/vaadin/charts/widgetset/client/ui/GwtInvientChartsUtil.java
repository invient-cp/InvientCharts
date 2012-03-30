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

/**
 * A JavaScript overlay representing Highcharts namespace
 * @author Invient
 *
 */
class GwtInvientChartsUtil extends JavaScriptObject {

    protected GwtInvientChartsUtil() {
    }

    public final native static GwtChart newChart(GwtInvientChartsConfig options) /*-{
                                                                                return new $wnd.Highcharts.Chart(options);
                                                                                }-*/;

    public final native static String dateFormat(String format, double time) /*-{
                                                                             return $wnd.Highcharts.dateFormat(format, time);
                                                                             }-*/;

    public final native static String dateFormat(String format, double time,
            boolean capitalize) /*-{
                                return $wnd.Highcharts.dateFormat(format, time, capitalize);
                                }-*/;

    public final native static String numberFormat(double number) /*-{
                                                                  return $wnd.Highcharts.numberFormat(number);
                                                                  }-*/;

    public final native static String numberFormat(double number, int decimals,
            String decimalPoint) /*-{
                                 return $wnd.Highcharts.numberFormat(number, decimals, decimalPoint);
                                 }-*/;

    public final native static String numberFormat(double number, int decimals,
            String decimalPoint, String thousandSep) /*-{
                                                     return $wnd.Highcharts.numberFormat(number, decimals, decimalPoint, thousandSep);
                                                     }-*/;

    // FIXME The argument and return type JavaScriptObject should be specific
    // subclass of JavaScriptObject
    public final native static GwtInvientChartsConfig setGlobalHighchartsOptions(
            GwtInvientChartsConfig options) /*-{
                                           return $wnd.Highcharts.setOptions(options);
                                           }-*/;

}
