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

import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPointOptions;

/**
 * A JavaScript overlay representing Highcharts Point object
 * 
 * @author Invient
 * 
 */
class GwtPoint extends JavaScriptObject {

    protected GwtPoint() {
    }

    public native final static GwtPoint create() /*-{
                                                 return {};
                                                 }-*/;

    public native final void remove() /*-{
                                      this.remove();
                                      }-*/;

    public native final void remove(boolean redraw) /*-{
                                                    this.remove(redraw);
                                                    }-*/;

    public native final void remove(boolean redraw, boolean animation) /*-{
                                                                       this.remove(redraw, animation);
                                                                       }-*/;

    public native final void select() /*-{
                                      this.select();
                                      }-*/;

    public native final void select(boolean select) /*-{
                                                    this.select(select);
                                                    }-*/;

    public native final void select(boolean select, boolean accumulate) /*-{
                                                                        this.select(select, accumulate);
                                                                        }-*/;

    public native final void slice(boolean sliced, boolean redraw,
            boolean animation) /*-{
                               this.slice(sliced, redraw, animation);
                               }-*/;

    public native final void slice(boolean sliced, boolean redraw) /*-{
                                                                   this.slice(sliced, redraw);
                                                                   }-*/;

    public native final void slice(boolean sliced) /*-{
                                                   this.slice(sliced);
                                                   }-*/;

    public native final void update(double newValue) /*-{
                                                     this.update(newValue);
                                                     }-*/;

    public native final void update(GwtPointOptions pointOptions) /*-{
                                                                  this.update(pointOptions);
                                                                  }-*/;

    public native final String getCategory() /*-{
                                             return this.category;
                                             }-*/;

    public native final double getPercentage() /*-{
                                               return this.percentage;
                                               }-*/;

    public native final boolean isSelected() /*-{
                                             return this.selected;
                                             }-*/;

    public native final GwtSeries getSeries() /*-{
                                                     return this.series;
                                                     }-*/;

    public native final Double getX() /*-{
                                      return this.x;
                                      }-*/;

    public native final Double getY() /*-{
                                      return this.y;
                                      }-*/;

}
