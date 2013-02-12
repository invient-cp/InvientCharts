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
import com.google.gwt.core.client.JsArrayString;

import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtDataLabels;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtMarker;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPlotOptions.GwtSeriesGeneralOptions;
import com.invient.vaadin.charts.widgetset.client.ui.GwtInvientChartsConfig.GwtPointOptions.GwtPointEvents;

/**
 * A JavaScript overlay representing Highcharts options object
 * 
 * @author Invient
 * 
 */
class GwtInvientChartsConfig extends JavaScriptObject {

    protected GwtInvientChartsConfig() {
    }

    public final native static GwtInvientChartsConfig create() /*-{
                                                                 return { };
                                                                 }-*/;

    public native final void setChartOptions(GwtChartOptions chartOptions) /*-{
                                                                           this.chart = chartOptions;
                                                                           }-*/;

    public native final GwtChartOptions getChartOptions() /*-{
                                                          return this.chart;
                                                          }-*/;

    public native final void setTitleOptions(GwtTitleOptions titleOptions) /*-{
                                                                           this.title = titleOptions;
                                                                           }-*/;

    public native final GwtTitleOptions getTitleOptions() /*-{
                                                          return this.title;
                                                          }-*/;

    public native final void setSubtitleOptions(
            GwtSubtitleOptions subtitleOptions) /*-{
                                                this.subtitle = subtitleOptions;
                                                }-*/;

    public native final void setCreditOptions(GwtCreditOptions creditOptions) /*-{
                                                                              this.credits = creditOptions;
                                                                              }-*/;

    public native final void setLabels(GwtChartLabels chartLabels) /*-{
                                                                    this.labels = chartLabels;
                                                                    }-*/;

    public native final void setLegendOptions(GwtLegendOptions legendOptions) /*-{
                                                                              this.legend = legendOptions;
                                                                              }-*/;

    public native final void setTooltipOptions(GwtTooltipOptions tooltipOptions) /*-{
                                                                                 this.tooltip = tooltipOptions;
                                                                                 }-*/;

    public native final void setXAxesOptions(
            JsArray<GwtXAxisOptions> xAxesOptions) /*-{
                                                   this.xAxis = xAxesOptions;
                                                   }-*/;

    public native final JsArray<GwtXAxisOptions> getXAxesOptions() /*-{
                                                                   return this.xAxis;
                                                                   }-*/;

    public native final boolean hasXAxesOptions() /*-{
                                                  if(this.xAxis != null) {
                                                  return true;
                                                  }        
                                                  return false;
                                                  }-*/;

    public native final void setYAxesOptions(
            JsArray<GwtYAxisOptions> yAxesOptions) /*-{
                                                   this.yAxis = yAxesOptions;
                                                   }-*/;

    public native final JsArray<GwtYAxisOptions> getYAxesOptions() /*-{
                                                                   return this.yAxis;
                                                                   }-*/;

    public native final boolean hasYAxesOptions() /*-{
                                                  if(this.yAxis != null) {
                                                  return true;
                                                  }        
                                                  return false;
                                                  }-*/;

    public native final void setPlotOptions(GwtPlotOptions plotOptions) /*-{
                                                                        this.plotOptions = plotOptions;
                                                                        }-*/;

    public native final GwtPlotOptions getPlotOptions() /*-{
                                                        return this.plotOptions;
                                                        }-*/;

    public native final void setSeriesInstanceOptions(
            JsArray<GwtSeriesDataOptions> seriesInstanceOptions) /*-{
                                                                 this.series = seriesInstanceOptions;
                                                                 }-*/;

    public native final void setExportingOptions(
            GwtExportingOptions exportingOptions) /*-{
                                                  this.exporting = exportingOptions;
                                                  }-*/;

    static final class GwtChartLabels extends JavaScriptObject {
        protected GwtChartLabels() {
        }

        public native final static GwtChartLabels create() /*-{
                                                           return { };
                                                           }-*/;

        public native final void setItems(JsArray<GwtChartLabelItem> items) /*-{
                                                                            this.items = items;
                                                                            }-*/;

        public native final void setStyle(String style) /*-{
                                                        this.style = eval("(" + style + ")");
                                                        }-*/;

        static final class GwtChartLabelItem extends JavaScriptObject {
            protected GwtChartLabelItem() {
            }

            public native final static GwtChartLabelItem create() /*-{
                                                                  return { };
                                                                  }-*/;

            public native final void setHtml(String html) /*-{
                                                            this.html = html;
                                                            }-*/;

            public native final void setStyle(String style) /*-{
                                                            this.style = eval("(" + style + ")");
                                                            }-*/;
        }
    }

    static final class GwtChartOptions extends JavaScriptObject {
        protected GwtChartOptions() {
        }

        public native final static GwtChartOptions create() /*-{
                                                            return {};
                                                            }-*/;

        public native final void setRenderTo(String renderTo) /*-{
                                                              this.renderTo = renderTo;
                                                              }-*/;

        public native final void setType(String type) /*-{
                                                      						this.type = type;
                                                      						}-*/;

        // This is not used by Highcharts.
        public native final void setClientZoom(boolean clientZoom) /*-{
                                                                   this.clientZoom = clientZoom;
                                                                   }-*/;

        public native final boolean getClientZoom() /*-{
                                                    return this.clientZoom;
                                                    }-*/;

        public native final void setZoomType(String zoomType) /*-{
                                                              this.zoomType = zoomType;
                                                              }-*/;

        public native final void setWidth(int width) /*-{
                                                     this.width = width;
                                                     }-*/;

        public native final int getWidth() /*-{
                                            if(this.width == undefined || this.width == null) {
                                            return -1;
                                            }
                                           return this.width;
                                           }-*/;

        public native final void setHeight(int height) /*-{
                                                       this.height = height;
                                                       }-*/;

        public native final int getHeight() /*-{
                                            if(this.height == undefined || this.height == null) {
                                            return -1;
                                            }        
                                            return this.height;
                                            }-*/;

        public native final void setBackgroundColor(String backgroundColor) /*-{
                                                                            this.backgroundColor = $wnd.getInvientChartsColor(backgroundColor);
                                                                            }-*/;

        public native final void setBorderColor(String borderColor) /*-{
                                                                    this.borderColor = $wnd.getInvientChartsColor(borderColor);
                                                                    }-*/;

        public native final void setBorderRadius(int borderRadius) /*-{
                                                                   this.borderRadius = borderRadius;
                                                                   }-*/;

        public native final void setBorderWidth(int borderWidth) /*-{
                                                                 this.borderWidth = borderWidth;
                                                                 }-*/;

        public native final void setIgnoreHiddenSeries(
                boolean ignoreHiddenSeries) /*-{
                                            this.ignoreHiddenSeries = ignoreHiddenSeries;
                                            }-*/;

        public native final void setInverted(boolean inverted) /*-{
                                                               this.inverted = inverted;
                                                               }-*/;

        public native final void setMarginTop(int marginTop) /*-{
                                                             this.marginTop = marginTop;
                                                             }-*/;

        public native final void setMarginLeft(int marginLeft) /*-{
                                                               this.marginLeft = marginLeft;
                                                               }-*/;

        public native final void setMarginBottom(int marginBottom) /*-{
                                                                   this.marginBottom = marginBottom;
                                                                   }-*/;

        public native final void setMarginRight(int marginRight) /*-{
                                                                 this.marginRight = marginRight;
                                                                 }-*/;

        public native final void setSpacingTop(int spacingTop) /*-{
                                                               this.spacingTop = spacingTop;
                                                               }-*/;

        public native final void setSpacingLeft(int spacingLeft) /*-{
                                                                 this.spacingLeft = spacingLeft;
                                                                 }-*/;

        public native final void setSpacingBottom(int spacingBottom) /*-{
                                                                     this.spacingBottom = spacingBottom;
                                                                     }-*/;

        public native final void setSpacingRight(int spacingRight) /*-{
                                                                   this.spacingRight = spacingRight;
                                                                   }-*/;

        public native final void setShowAxes(boolean showAxes) /*-{
                                                               this.showAxes = showAxes;
                                                               }-*/;

        public native final void setAlignTicks(boolean alignTicks) /*-{
                                                                   this.alignTicks = alignTicks;
                                                                   }-*/;

        public native final void setAnimation(boolean animation) /*-{
                                                                 this.animation = animation;
                                                                 }-*/;

        public native final void setClassName(String className) /*-{
                                                                this.className = className;
                                                                }-*/;

        public native final void setPlotBackgroundColor(
                String plotBackgroundColor) /*-{
                                            this.plotBackgroundColor = $wnd.getInvientChartsColor(plotBackgroundColor);
                                            }-*/;

        public native final void setPlotBorderColor(String plotBorderColor) /*-{
                                                                            this.plotBorderColor = $wnd.getInvientChartsColor(plotBorderColor);
                                                                            }-*/;

        public native final void setPlotBackgroundImage(
                String plotBackgroundImage) /*-{
                                            this.plotBackgroundImage = plotBackgroundImage;
                                            }-*/;

        public native final void setPlotBorderWidth(int plotBorderWidth) /*-{
                                                                         this.plotBorderWidth = plotBorderWidth;
                                                                         }-*/;

        public native final void setPlotShadow(boolean plotShadow) /*-{
                                                                   this.plotShadow = plotShadow;
                                                                   }-*/;

        public native final void setReflow(boolean reflow) /*-{
                                                           this.reflow = reflow;
                                                           }-*/;

        public native final void setShadow(boolean shadow) /*-{
                                                           this.shadow = shadow;
                                                           }-*/;

        // FIXME - It should be a JavaScript object
        public native final void setStyle(String style) /*-{
                                                        this.style = eval("(" + style + ")");
                                                        }-*/;

        public native final void setEvents(GwtChartEvents chartEvents) /*-{
                                                                         this.events = chartEvents;
                                                                         }-*/;

        // Events
        static final class GwtChartEvents extends JavaScriptObject {
            protected GwtChartEvents() {
            }

            public native final static GwtChartEvents create() /*-{
                                                               return {};
                                                               }-*/;

            public native final void setAddSeriesEvent(
                    JavaScriptObject addSeriesEvent) /*-{
                                                     this.addSeries = addSeriesEvent;
                                                     }-*/;

            public native final void setClickEvent(JavaScriptObject clickEvent) /*-{
                                                                                this.click = clickEvent;
                                                                                }-*/;

            public native final void setLoadEvent(JavaScriptObject loadEvent) /*-{
                                                                              this.load = loadEvent;
                                                                              }-*/;

            public native final void setRedrawEvent(JavaScriptObject redrawEvent) /*-{
                                                                                  this.redraw = redrawEvent;
                                                                                  }-*/;

            public native final void setSelectionEvent(
                    JavaScriptObject selectionEvent) /*-{
                                                     this.selection = selectionEvent;
                                                     }-*/;
        }

    }

    static class GwtPlotOptions extends JavaScriptObject {
        protected GwtPlotOptions() {
        }

        public native final static GwtPlotOptions create() /*-{
                                                           return {};
                                                           }-*/;

        public native final void setSeries(GwtSeriesGeneralOptions series) /*-{
                                                                           		this.series = series;
                                                                           		}-*/;

        public native final GwtSeriesGeneralOptions getSeries() /*-{
                                                                return this.series;
                                                                }-*/;

        public native final void setLine(GwtLineOptions line) /*-{
                                                              this.line = line;
                                                              }-*/;

        public native final GwtLineOptions getLine() /*-{
                                                     return this.line;
                                                     }-*/;

        public native final void setArea(GwtAreaOptions area) /*-{
                                                              this.area = area;
                                                              }-*/;

        public native final GwtAreaOptions getArea() /*-{
                                                     return this.area;
                                                     }-*/;

        public native final void setAreaSpline(GwtAreaSplineOptions areaSpline) /*-{
                                                                                this.areaspline = areaSpline;
                                                                                }-*/;

        public native final GwtAreaSplineOptions getAreaSpline() /*-{
                                                                 return this.areaspline;
                                                                 }-*/;

        public native final void setBar(GwtBarOptions bar) /*-{
                                                           this.bar = bar;
                                                           }-*/;

        public native final GwtBarOptions getBar() /*-{
                                                   return this.bar;
                                                   }-*/;

        public native final void setColumn(GwtColumnOptions column) /*-{
                                                                    this.column = column;
                                                                    }-*/;

        public native final GwtColumnOptions getColumn() /*-{
                                                         return this.column;
                                                         }-*/;

        public native final void setPie(GwtPieOptions pie) /*-{
                                                           this.pie = pie;
                                                           }-*/;

        public native final GwtPieOptions getPie() /*-{
                                                   return this.pie;
                                                   }-*/;

        public native final void setSpline(GwtSplineOptions spline) /*-{
                                                                    this.spline = spline;
                                                                    }-*/;

        public native final GwtSplineOptions getSpline() /*-{
                                                         return this.spline;
                                                         }-*/;

        public native final void setScatter(GwtScatterOptions scatter) /*-{
                                                                       this.scatter = scatter;
                                                                       }-*/;

        public native final GwtScatterOptions getScatter() /*-{
                                                           return this.scatter;
                                                           }-*/;

        static class GwtSeriesEvents extends JavaScriptObject {
            protected GwtSeriesEvents() {
            }

            public native final static GwtSeriesEvents create() /*-{
                                                                return {};
                                                                }-*/;

            public native final void setClickEvent(JavaScriptObject clickEvent) /*-{
                                                                                this.click = clickEvent;
                                                                                }-*/;

            public native final void setHideEvent(JavaScriptObject hideEvent) /*-{
                                                                              this.hide = hideEvent;
                                                                              }-*/;

            public native final void setLegendItemClickEvent(
                    JavaScriptObject legendItemClickEvent) /*-{
                                                           this.legendItemClick = legendItemClickEvent;
                                                           }-*/;

            public native final void setMouseOverEvent(
                    JavaScriptObject mouseOverEvent) /*-{
                                                     this.mouseOver = mouseOverEvent;
                                                     }-*/;

            public native final void setMouseOutEvent(
                    JavaScriptObject mouseOutEvent) /*-{
                                                    this.mouseOut = mouseOutEvent;
                                                    }-*/;

            public native final void setShowEvent(JavaScriptObject showEvent) /*-{
                                                                              this.show = showEvent;
                                                                              }-*/;
        }

        static final class GwtMarker extends JavaScriptObject {
            protected GwtMarker() {
            }

            public native final static GwtMarker create() /*-{
                                                          return {};
                                                          }-*/;

            public native final void setEnabled(boolean enabled) /*-{
                                                                 this.enabled = enabled;
                                                                 }-*/;

            public native final void setFillColor(String fillColor) /*-{
                                                                    this.fillColor = $wnd.getInvientChartsColor(fillColor);
                                                                    }-*/;

            public native final void setLineColor(String lineColor) /*-{
                                                                    this.lineColor = $wnd.getInvientChartsColor(lineColor);
                                                                    }-*/;

            public native final void setLineWidth(int width) /*-{
                                                             this.lineWidth = width;
                                                             }-*/;

            public native final void setRadius(int radius) /*-{
                                                           this.radius = radius;
                                                           }-*/;

            public native final void setSymbol(String symbol) /*-{
                                                              this.symbol = symbol;
                                                              }-*/;

            public native final void setStates(GwtMarkerStates states) /*-{
                                                                       this.states = states;
                                                                       }-*/;

            static final class GwtMarkerStates extends JavaScriptObject {
                protected GwtMarkerStates() {
                }

                public native final static GwtMarkerStates create() /*-{
                                                                    return {};
                                                                    }-*/;

                public native final void setHover(GwtMarkerState hoverState) /*-{
                                                                             this.hover = hoverState;
                                                                             }-*/;

                public native final void setSelect(GwtMarkerState selectState) /*-{
                                                                               this.select = selectState;
                                                                               }-*/;

                static final class GwtMarkerState extends JavaScriptObject {
                    protected GwtMarkerState() {
                    }

                    public native final static GwtMarkerState create() /*-{
                                                                       return {};
                                                                       }-*/;

                    public native final void setEnabled(boolean enabled) /*-{
                                                                         this.enabled = enabled;
                                                                         }-*/;

                    public native final void setFillColor(String fillColor) /*-{
                                                                            this.fillColor = $wnd.getInvientChartsColor(fillColor);
                                                                            }-*/;

                    public native final void setLineColor(String lineColor) /*-{
                                                                            this.lineColor = $wnd.getInvientChartsColor(lineColor);
                                                                            }-*/;

                    public native final void setLineWidth(int width) /*-{
                                                                     this.lineWidth = width;
                                                                     }-*/;

                    public native final void setRadius(int radius) /*-{
                                                                   this.radius = radius;
                                                                   }-*/;
                }
            }
        }

        static class GwtSeriesGeneralOptions extends JavaScriptObject {
            protected GwtSeriesGeneralOptions() {
            }

            public native final static GwtSeriesGeneralOptions createSeriesOptions() /*-{
                                                                                     return {};
                                                                                     }-*/;

            public native final void setDataLabels(GwtDataLabels dataLabels) /*-{
                                                                                 this.dataLabels = dataLabels;
                                                                                 }-*/;

            public native final void setEvents(GwtSeriesEvents events) /*-{
                                                                       this.events = events;
                                                                       }-*/;

            public native final void setColor(String color) /*-{
                                                            this.color = $wnd.getInvientChartsColor(color);
                                                            }-*/;

            public native final void setVisible(boolean visible) /*-{
                                                                 this.visible = visible;
                                                                 }-*/;

            public native final boolean isVisible() /*-{
                                                        if(!this.visible) {
                                                            this.visible = true;
                                                        }
                                                        return this.visible;
                                                    }-*/;

            public native final void setAllowPointSelect(
                    boolean allowPointSelect) /*-{
                                              this.allowPointSelect = allowPointSelect;
                                              }-*/;

            public native final void setAnimation(boolean animation) /*-{
                                                                     this.animation = animation;
                                                                     }-*/;

            public native final void setCursor(String cursor) /*-{
                                                              this.cursor = cursor;
                                                              }-*/;

            public native final void setSelected(boolean selected) /*-{
                                                                   this.selected = selected;
                                                                   }-*/;

            public native final void setShadow(boolean shadow) /*-{
                                                               this.shadow = shadow;
                                                               }-*/;

            public native final void setShowCheckbox(boolean showCheckbox) /*-{
                                                                           this.showCheckbox = showCheckbox;
                                                                           }-*/;

            public native final void setEnableMouseTracking(
                    boolean enableMouseTracking) /*-{
                                                 this.enableMouseTracking = enableMouseTracking;
                                                 }-*/;

            public native final void setStacking(String stacking) /*-{
                                                                  this.stacking = stacking;
                                                                  }-*/;

            public native final void setShowInLegend(boolean showInLegend) /*-{
                                                                           this.showInLegend = showInLegend;
                                                                           }-*/;

            public native final void setPointEvents(GwtPointEvents pointEvents) /*-{
                                                                                this.point = {};
                                                                                this.point.events = pointEvents;
                                                                                }-*/;

            public native final void setStates(GwtStates states) /*-{
                                                                 this.states = states;
                                                                 }-*/;

            static final class GwtStates extends JavaScriptObject {
                protected GwtStates() {
                }

                public native final static GwtStates create() /*-{
                                                              return {};
                                                              }-*/;

                public native final void setHover(GwtHover hover) /*-{
                                                                  this.hover = hover;
                                                                  }-*/;

                static final class GwtHover extends JavaScriptObject {
                    protected GwtHover() {
                    }

                    public native final static GwtHover create() /*-{
                                                                 return {};
                                                                 }-*/;

                    public native final void setEnabled(boolean enabled) /*-{
                                                                         this.enabled = enabled;
                                                                         }-*/;

                    public native final void setLineWidth(int lineWidth) /*-{
                                                                         this.lineWidth = lineWidth;
                                                                         }-*/;

                    public native final void setBrightness(double brightness) /*-{
                                                                              this.brightness = brightness;
                                                                              }-*/;
                }
            }
        }

        static class GwtDataLabels extends JavaScriptObject {
            protected GwtDataLabels() {
            }

            public native final static GwtDataLabels createDataLabels() /*-{
                                                                        return {};
                                                                        }-*/;

            public native final void setAlign(String align) /*-{
                                                            this.align = align;
                                                            }-*/;

            public native final void setEnabled(boolean enabled) /*-{
                                                                 this.enabled = enabled;
                                                                 }-*/;

            public native final void setFormatter(String formatter) /*-{
                                                                                  this.formatter = eval(formatter);
                                                                                  }-*/;

            public native final void setRotation(int rotation) /*-{
                                                               this.rotation = rotation;
                                                               }-*/;

            // FIXME
            public native final void setStyle(String style) /*-{
                                                            this.style = eval("(" + style + ")");
                                                            }-*/;

            public native final void setX(int x) /*-{
                                                 this.x = x;
                                                 }-*/;

            public native final void setY(int y) /*-{
                                                 this.y = y;
                                                 }-*/;

            public native final void setColor(String color) /*-{
                                                            this.color = $wnd.getInvientChartsColor(color);
                                                            }-*/;
        }

        static class GwtPieDataLabels extends GwtDataLabels {
            protected GwtPieDataLabels() {
            }

            public native final static GwtPieDataLabels createPieDataLabels() /*-{
                                                                              return {};
                                                                              }-*/;

            public native final void setConnectorWidth(int width) /*-{
                                                                  this.connectorWidth = width;
                                                                  }-*/;

            public native final void setConnectorColor(String color) /*-{
                                                                     this.color = $wnd.getInvientChartsColor(color);
                                                                     }-*/;

            public native final void setConnectorPadding(int padding) /*-{
                                                                      this.connectorPadding = padding;
                                                                      }-*/;

            public native final void setDistance(int distance) /*-{
                                                               this.distance = distance;
                                                               }-*/;
        }

        static class GwtBaseLineOptions extends GwtSeriesGeneralOptions {
            protected GwtBaseLineOptions() {
            }

            public native final void setDashStyle(String dashStyle) /*-{
                                                                    this.dashStyle = dashStyle;
                                                                    }-*/;

            public native final void setLineWidth(int lineWidth) /*-{
                                                                 this.lineWidth = lineWidth;
                                                                 }-*/;

            public native final void setPointStart(double pointStart) /*-{
                                                                   this.pointStart = pointStart;
                                                                   }-*/;

            public native final void setPointInterval(int pointInterval) /*-{
                                                                         this.pointInterval = pointInterval;
                                                                         }-*/;

            public native final void setStickyTracking(boolean stickyTracking) /*-{
                                                                               this.stickyTracking = stickyTracking;
                                                                               }-*/;

            public native final void setMarker(GwtMarker marker) /*-{
                                                                 this.marker = marker;
                                                                 }-*/;
        }

        static class GwtLineOptions extends GwtBaseLineOptions {
            protected GwtLineOptions() {
            }

            public native final static GwtLineOptions createLineOptions() /*-{
                                                                          return {};
                                                                          }-*/;

            public native final void setStep(boolean step) /*-{
                                                           this.step = step;
                                                           }-*/;
        }

        static class GwtAreaOptions extends GwtBaseLineOptions {
            protected GwtAreaOptions() {
            }

            public native final static GwtAreaOptions createAreaOptions() /*-{
                                                                          return {};
                                                                          }-*/;

            public native final void setFillColor(String fillColor) /*-{
                                                                    this.fillColor = $wnd.getInvientChartsColor(fillColor);
                                                                    }-*/;

            public native final void setLineColor(String lineColor) /*-{
                                                                    this.lineColor = $wnd.getInvientChartsColor(lineColor);
                                                                    }-*/;

            public native final void setFillOpacity(double fillOpacity) /*-{
                                                                        this.fillOpacity = fillOpacity;
                                                                        }-*/;

            public native final void setThreshold(int threshold) /*-{
                                                                 this.threshold = threshold;
                                                                 }-*/;

        }

        static class GwtAreaSplineOptions extends GwtAreaOptions {
            protected GwtAreaSplineOptions() {
            }

            public native final static GwtAreaSplineOptions createAreaSplineOptions() /*-{
                                                                                      return {};
                                                                                      }-*/;
        }

        static class GwtScatterOptions extends GwtBaseLineOptions {
            protected GwtScatterOptions() {
            }

            public native final static GwtScatterOptions createScatterOptions() /*-{
                                                                                return {};
                                                                                }-*/;
        }

        static class GwtSplineOptions extends GwtBaseLineOptions {
            protected GwtSplineOptions() {
            }

            public native final static GwtSplineOptions createSplineOptions() /*-{
                                                                              return {};
                                                                              }-*/;
        }

        static class GwtPieOptions extends GwtSeriesGeneralOptions {
            protected GwtPieOptions() {
            }

            public native final static GwtPieOptions createPieOptions() /*-{
                                                                        return {};
                                                                        }-*/;

            public native final void setCenter(JsArrayNumber center) /*-{
                                                                     this.center = center;
                                                                     }-*/;

            public native final void setBorderColor(String borderColor) /*-{
                                                                        this.borderColor = $wnd.getInvientChartsColor(borderColor);
                                                                        }-*/;

            public native final void setBorderWidth(double borderWidth) /*-{
                                                                     this.borderWidth = borderWidth;
                                                                     }-*/;

            public native final void setInnerSize(int innerSize) /*-{
                                                                 this.innerSize = innerSize;
                                                                 }-*/;

            public native final void setSize(int size) /*-{
                                                       this.size = size;
                                                       }-*/;

            public native final void setSlicedOffset(int slicedOffset) /*-{
                                                                       this.slicedOffset = slicedOffset;
                                                                       }-*/;

			public native final void setIgnoreHiddenPoint(boolean ignoreHiddenPoint) /*-{
																					this.ignoreHiddenPoint = ignoreHiddenPoint;
																					}-*/;
		}

        static class GwtBaseBarOptions extends GwtSeriesGeneralOptions {
            protected GwtBaseBarOptions() {
            }

            public native final void setBorderColor(String borderColor) /*-{
                                                                        this.borderColor = $wnd.getInvientChartsColor(borderColor);
                                                                        }-*/;

            public native final void setBorderWidth(int borderWidth) /*-{
                                                                     this.borderWidth = borderWidth;
                                                                     }-*/;

            public native final void setBorderRadius(int borderRadius) /*-{
                                                                       this.borderRadius = borderRadius;
                                                                       }-*/;

            public native final void setColorByPoint(boolean colorByPoint) /*-{
                                                                           this.colorByPoint = colorByPoint;
                                                                           }-*/;

            public native final void setGroupPadding(double groupPadding) /*-{
                                                                          this.groupPadding = groupPadding;
                                                                          }-*/;

            public native final void setMinPointLength(double minPointLength) /*-{
                                                                              this.minPointLength = minPointLength;
                                                                              }-*/;

            public native final void setPointPadding(double pointPadding) /*-{
                                                                          this.pointPadding = pointPadding;
                                                                          }-*/;

            public native final void setPointWidth(int pointWidth) /*-{
                                                                       this.pointWidth = pointWidth;
                                                                       }-*/;

        }

        static class GwtBarOptions extends GwtBaseBarOptions {
            protected GwtBarOptions() {
            }

            public native final static GwtBarOptions createBarOptions() /*-{
                                                                        return {};
                                                                        }-*/;
        }

        static class GwtColumnOptions extends GwtBaseBarOptions {
            protected GwtColumnOptions() {
            }

            public native final static GwtColumnOptions createColumnOptions() /*-{
                                                                              return {};
                                                                              }-*/;
        }

    }

    static class GwtTitleBaseOptions extends JavaScriptObject {
        protected GwtTitleBaseOptions() {
        }

        public native final void setAlign(String align) /*-{
                                                        this.align = align;
                                                        }-*/;

        public native final void setFloating(boolean floating) /*-{
                                                               this.floating = floating;
                                                               }-*/;

        public native final void setText(String text) /*-{
                                                      this.text = text;
                                                      }-*/;

        public native final void setStyle(String style) /*-{
                                                        this.style = eval("(" + style + ")");
                                                        }-*/;

        public native final void setVerticalAlign(String verticalAlign) /*-{
                                                                        this.verticalAlign = verticalAlign;
                                                                        }-*/;

        public native final void setX(int x) /*-{
                                             this.x = x;
                                             }-*/;

        public native final void setY(int y) /*-{
                                             this.y = y;
                                             }-*/;
    }

    static class GwtTitleOptions extends GwtTitleBaseOptions {
        protected GwtTitleOptions() {
        }

        public native final static GwtTitleOptions createTitleOptions() /*-{
                                                                        return {};
                                                                        }-*/;

        public native final void setMargin(int margin) /*-{
                                                       this.margin = margin;
                                                       }-*/;
    }

    static final class GwtSubtitleOptions extends GwtTitleBaseOptions {

        protected GwtSubtitleOptions() {
        }

        public native final static GwtSubtitleOptions createSubtitleOptions() /*-{
                                                                              return {};
                                                                              }-*/;
    }

    static final class GwtSeriesDataOptions extends JavaScriptObject {
        protected GwtSeriesDataOptions() {
        }

        public native final static GwtSeriesDataOptions create() /*-{
                                                                   return {};
                                                                   }-*/;

        public native final void setData(JsArrayNumber data) /*-{
                                                             this.data = data;
                                                             }-*/;

        public native final void setData(JsArray<JsArrayNumber> data) /*-{
                                                                      this.data = data;
                                                                      }-*/;

        public native final void setDataAsPointOptions(
                JsArray<GwtPointOptions> data) /*-{
                                               this.data = data;
                                               }-*/;

        public native final JsArray<GwtPointOptions> getDataAsPointOptions() /*-{
                                                                             return this.data;
                                                                             }-*/;

        public native final void setName(String name) /*-{
                                                      this.id = name;
                                                      this.name = name;
                                                      }-*/;

        public native final String getName() /*-{
                                             return this.name;
                                             }-*/;

        public native final void setStack(String stack) /*-{
                                                        this.stack = stack;
                                                        }-*/;

        public native final void setType(String type) /*-{
                                                      this.type = type;
                                                      }-*/;

        public native final String getType() /*-{
                                             return this.type;
                                             }-*/;

        public native final void setXAxis(int xAxis) /*-{
                                                     this.xAxis = xAxis;
                                                     }-*/;

        public native final void setYAxis(int yAxis) /*-{
                                                     this.yAxis = yAxis;
                                                     }-*/;

        public native final void setSeriesOptions(
                GwtSeriesGeneralOptions options) /*-{
                                                 for (prop in options) {
                                                 this[prop] = options[prop];
                                                 }
                                                 // Stored for further reference in VInvientCharts when a series needs 
                                                 // to be updated
                                                 this.seriesOptions = options;                                                 
                                                 }-*/;

        // Note that updating any attribute on returned object will not
        // update properties of this GwtSeriesDataOptions.
        // Instead use setSeriesOptions() method to set properties.
        public native final GwtSeriesGeneralOptions getSeriesOptions() /*-{
                                                                       return this.seriesOptions;
                                                                       }-*/;

    }

    // To be used with series.data there is no
    // point in setting this to chart.setPoint()
    // It is of no use
    static final class GwtPointOptions extends JavaScriptObject {
        protected GwtPointOptions() {
        }

        public native final static GwtPointOptions create() /*-{
                                                            return {};
                                                            }-*/;

        public native final void setColor(String color) /*-{
                                                        this.color = $wnd.getInvientChartsColor(color);
                                                        }-*/;

        public native final void setId(String id) /*-{
                                                  this.id = id;
                                                  }-*/;

        public native final void setName(String name) /*-{
                                                      this.name = name;
                                                      }-*/;

        public native final void setX(double x) /*-{
                                                this.x = x;
                                                }-*/;

        public native final void setNullX() /*-{
                                            this.x = null;
                                            }-*/;

        public native final void setY(double y) /*-{
                                                this.y = y;
                                                }-*/;

        public native final void setNullY() /*-{
                                            this.y = null;
                                            }-*/;

        public native final Double getX() /*-{
                                          return this.x;
                                          }-*/;

        public native final Double getY() /*-{
                                          return this.y;
                                          }-*/;

        // This property is not used by Highcharts. It is used by
        // InvientChart to determine how a point should be added when it is
        // added
        // individually.
        public native final void setShift(boolean shift) /*-{
                                                         this.shift = shift;
                                                         }-*/;

        public native final boolean isShift() /*-{
                                              if(this.shift == undefined || this.shift == null) {
                                              return false;
                                              } 
                                              return this.shift;
                                              }-*/;

        // Only for pie chart
        public native final void setSliced(boolean sliced) /*-{
                                                           this.sliced = sliced;
                                                           }-*/;

        public native final void setSelected(boolean selected) /*-{
                                                               this.selected = selected;
                                                               }-*/;

        public native final void setPointEvents(GwtPointEvents events) /*-{
                                                                       	this.events = events;
                                                                       	}-*/;

        public native final void setMarker(GwtMarker marker) /*-{
                                                             		this.marker = marker;
                                                             		}-*/;

        static final class GwtPointEvents extends JavaScriptObject {
            protected GwtPointEvents() {
            }

            public native final static GwtPointEvents create() /*-{
                                                               return {};
                                                               }-*/;

            public native final void setClickEvent(JavaScriptObject clickEvent) /*-{
                                                                                this.click = clickEvent;
                                                                                }-*/;

            public native final void setRemoveEvent(JavaScriptObject removeEvent) /*-{
                                                                                  this.remove = removeEvent;
                                                                                  }-*/;

            public native final void setSelectEvent(JavaScriptObject selectEvent) /*-{
                                                                                  this.select = selectEvent;
                                                                                  }-*/;

            public native final void setUnselectEvent(
                    JavaScriptObject unselectEvent) /*-{
                                                    this.unselect = unselectEvent;
                                                    }-*/;

            public native final void setLegendItemClickEvent(
                    JavaScriptObject legendItemClickEvent) /*-{
                                                           this.legendItemClick = legendItemClickEvent;
                                                           }-*/;

            public native final void setUpdateEvent(JavaScriptObject updateEvent) /*-{
                                                                                  this.update = updateEvent;
                                                                                  }-*/;

            public native final void setMouseOverEvent(
                    JavaScriptObject mouseOverEvent) /*-{
                                                     this.mouseOver = mouseOverEvent;
                                                     }-*/;

            public native final void setMouseOutEvent(
                    JavaScriptObject mouseOutEvent) /*-{
                                                    this.mouseOut = mouseOutEvent;
                                                    }-*/;
        }
    }

    static class GwtAxisBaseOptions extends JavaScriptObject {
        protected GwtAxisBaseOptions() {
        }

        public native final void setId(String id) /*-{
                                                  this.id = id;
                                                  }-*/;

        public native final void setAllowDecimals(boolean allowDecimals) /*-{
                                                                         this.allowDecimals = allowDecimals;
                                                                         }-*/;

        public native final void setAlternateGridColor(String alternateGridColor) /*-{
                                                                                  this.alternateGridColor = $wnd.getInvientChartsColor(alternateGridColor);
                                                                                  }-*/;

        public native final void setEndOnTick(boolean endOnTick) /*-{
                                                                 this.endOnTick = endOnTick;
                                                                 }-*/;

        public native final void setGridLineColor(String gridLineColor) /*-{
                                                                        this.gridLineColor = $wnd.getInvientChartsColor(gridLineColor);
                                                                        }-*/;

        public native final void setGridLineDashStyle(String gridLineDashStyle) /*-{
                                                                                this.gridLineDashStyle = gridLineDashStyle;
                                                                                }-*/;

        public native final void setGridLineWidth(int gridLineWidth) /*-{
                                                                        this.gridLineWidth = gridLineWidth;
                                                                        }-*/;

        public native final void setLabels(GwtAxisDataLabels label) /*-{
                                                                    this.labels = label;
                                                                    }-*/;

        public native final void setLineColor(String lineColor) /*-{
                                                                this.lineColor = $wnd.getInvientChartsColor(lineColor);
                                                                }-*/;

        public native final void setLineWidth(int lineWidth) /*-{
                                                             this.lineWidth = lineWidth;
                                                             }-*/;

        public native final void setLinkedTo(int linkedTo) /*-{
                                                           this.linkedTo = linkedTo;
                                                           }-*/;

        public native final void setMax(double max) /*-{
                                                    this.max = max;
                                                    }-*/;

        public native final double getMax() /*-{
                                            return this.max;
                                            }-*/;

        public native final void setMaxPadding(double maxPadding) /*-{
                                                                  this.maxPadding = maxPadding;
                                                                  }-*/;

        public native final void setMaxZoom(int maxZoom) /*-{
                                                         this.maxZoom = maxZoom;
                                                         }-*/;

        public native final void setMin(double min) /*-{
                                                    this.min = min;
                                                    }-*/;

        public native final double getMin() /*-{
                                            return this.min;
                                            }-*/;

        public native final void setMinPadding(double minPadding) /*-{
                                                                  this.minPadding = minPadding;
                                                                  }-*/;

        public native final void setMinorGridLineColor(String minorGridLineColor) /*-{
                                                                                  this.minorGridLineColor = $wnd.getInvientChartsColor(minorGridLineColor);
                                                                                  }-*/;

        public native final void setMinorGridLineDashStyle(
                String minorGridLineDashStyle) /*-{
                                               this.minorGridLineDashStyle = minorGridLineDashStyle;
                                               }-*/;

        public native final void setMinorGridLineWidth(int minorGridLineWidth) /*-{
                                                                                  this.minorGridLineWidth = minorGridLineWidth;
                                                                                  }-*/;

        public native final void setMinorTickColor(String minorTickColor) /*-{
                                                                          this.minorTickColor = $wnd.getInvientChartsColor(minorTickColor);
                                                                          }-*/;

        public native final void setMinorTickInterval(double minorTickInterval) /*-{
                                                                                this.minorTickInterval = minorTickInterval;
                                                                                }-*/;

        public native final void setMinorTickLength(int minorTickLength) /*-{
                                                                         this.minorTickLength = minorTickLength;
                                                                         }-*/;

        public native final void setMinorTickPosition(String minorTickPosition) /*-{
                                                                                this.minorTickPosition = minorTickPosition;
                                                                                }-*/;

        public native final void setMinorTickWidth(int minorTickWidth) /*-{
                                                                       this.minorTickWidth = minorTickWidth;
                                                                       }-*/;

        public native final void setOffset(int offset) /*-{
                                                       this.offset = offset;
                                                       }-*/;

        public native final void setOpposite(boolean opposite) /*-{
                                                               this.opposite = opposite;
                                                               }-*/;

        // PlotBands
        public native final void setPlotBands(JsArray<GwtPlotBands> plotBands) /*-{
                                                                               this.plotBands = plotBands;
                                                                               }-*/;

        public native final JsArray<GwtPlotBands> getPlotBands() /*-{
                                                                 return this.plotBands;
                                                                 }-*/;

        // PlotLines
        public native final void setPlotLines(JsArray<GwtPlotLines> plotLines) /*-{
                                                                               this.plotLines = plotLines;
                                                                               }-*/;

        public native final JsArray<GwtPlotLines> getPlotLines() /*-{
                                                                 return this.plotLines;
                                                                 }-*/;

        public native final void setReversed(boolean reversed) /*-{
                                                               this.reversed = reversed;
                                                               }-*/;

        public native final void setShowFirstLabel(boolean showFirstLabel) /*-{
                                                                           this.showFirstLabel = showFirstLabel;
                                                                           }-*/;

        public native final void setShowLastLabel(boolean showLastLabel) /*-{
                                                                         this.showLastLabel = showLastLabel;
                                                                         }-*/;

        public native final void setStartOfWeek(int startOfWeek) /*-{
                                                                 this.startOfWeek = startOfWeek;
                                                                 }-*/;

        public native final void setStartOnTick(boolean startOnTick) /*-{
                                                                     this.startOnTick = startOnTick;
                                                                     }-*/;

        public native final void setTickColor(String tickColor) /*-{
                                                                this.tickColor = $wnd.getInvientChartsColor(tickColor);
                                                                }-*/;

        public native final void setTickInterval(double tickInterval) /*-{
                                                                      this.tickInterval = tickInterval;
                                                                      }-*/;

        public native final void setTickLength(int tickLength) /*-{
                                                               this.tickLength = tickLength;
                                                               }-*/;

        public native final void setTickPosition(String tickPosition) /*-{
                                                                      this.tickPosition = tickPosition;
                                                                      }-*/;

        public native final void setTickWidth(int tickWidth) /*-{
                                                             this.tickWidth = tickWidth;
                                                             }-*/;

        public native final void setTickmarkPlacement(String tickmarkPlacement) /*-{
                                                                                this.tickmarkPlacement = tickmarkPlacement;
                                                                                }-*/;

        public native final void setTickPixelInterval(int tickPixelInterval) /*-{
                                                                             this.tickPixelInterval = tickPixelInterval;
                                                                             }-*/;

        public native final void setTitle(GwtAxisTitleOptions title) /*-{
                                                                     this.title = title;
                                                                     }-*/;

        public native final void setType(String type) /*-{
                                                      this.type = type;
                                                      }-*/;

        static final class GwtAxisTitleOptions extends JavaScriptObject {
            protected GwtAxisTitleOptions() {
            }

            public native final static GwtAxisTitleOptions create() /*-{
                                                                    return {};
                                                                    }-*/;

            public native final void setText(String text) /*-{
                                                          this.text = text;
                                                          }-*/;

            public native final void setAlign(String align) /*-{
                                                            this.align = align;
                                                            }-*/;

            public native final void setStyle(String style) /*-{
                                                            this.style = eval("(" + style + ")");
                                                            }-*/;

            public native final void setRotation(int rotation) /*-{
                                                                   this.rotation = rotation;
                                                                   }-*/;

            public native final void setMargin(int margin) /*-{
                                                               this.margin = margin;
                                                               }-*/;
        }

        static class GwtAxisDataLabels extends GwtDataLabels {
            protected GwtAxisDataLabels() {
            }

            public native final void setStep(int step) /*-{
                                                       this.step = step;
                                                       }-*/;
        }

        static class GwtXAxisDataLabels extends GwtAxisDataLabels {
            protected GwtXAxisDataLabels() {
            }

            public native final static GwtXAxisDataLabels createXAxisLabels() /*-{
                                                                              return {};
                                                                              }-*/;

            public native final void setStaggerLines(int staggerLines) /*-{
                                                                       this.staggerLines = staggerLines;
                                                                       }-*/;;
        }

        static class GwtYAxisDataLabels extends GwtAxisDataLabels {
            protected GwtYAxisDataLabels() {
            }

            public native final static GwtYAxisDataLabels createYAxisLabels() /*-{
                                                                              return {};
                                                                              }-*/;
        }

        static class GwtPlotLabel extends JavaScriptObject {
            protected GwtPlotLabel() {
            }

            public native final static GwtPlotLabel create() /*-{
                                                             return {};
                                                             }-*/;

            public native final void setText(String text) /*-{
                                                          this.text = text;
                                                          }-*/;

            public native final void setAlign(String align) /*-{
                                                            this.align = align;
                                                            }-*/;

            public native final void setRotation(int rotation) /*-{
                                                               this.rotation = rotation;
                                                               }-*/;

            public native final void setVerticalAlign(String verticalAlign) /*-{
                                                                            this.verticalAlign = verticalAlign;
                                                                            }-*/;

            public native final void setStyle(String style) /*-{
                                                            this.style = eval("(" + style + ")");
                                                            }-*/;

            public native final void setTextAlign(String textAlign) /*-{
                                                                    this.textAlign = textAlign;
                                                                    }-*/;

            public native final void setX(int x) /*-{
                                                 this.x = x;
                                                 }-*/;

            public native final void setY(int y) /*-{
                                                 this.y = y;
                                                 }-*/;

        }

        static class GwtPlotBands extends JavaScriptObject {
            protected GwtPlotBands() {
            }

            public native final static GwtPlotBands create() /*-{
                                                             return {};
                                                             }-*/;

            public native final void setId(String id) /*-{
                                                      this.id = id;
                                                      }-*/;

            public native final String getId() /*-{
                                               return this.id;
                                               }-*/;

            public native final void setLabel(GwtPlotLabel label) /*-{
                                                                  this.label = label;
                                                                  }-*/;

            public native final void setColor(String color) /*-{
                                                            this.color = $wnd.getInvientChartsColor(color);
                                                            }-*/;

            public native final void setZIndex(int zIndex) /*-{
                                                           this.zIndex = zIndex;
                                                           }-*/;

            public native final void setFrom(double from) /*-{
                                                          this.from = from;
                                                          }-*/;

            public native final double getFrom() /*-{
                                                 return this.from;
                                                 }-*/;

            public native final void setTo(double to) /*-{
                                                      this.to = to;
                                                      }-*/;

            public native final double getTo() /*-{
                                               return this.to;
                                               }-*/;

            // FIXME how to set date???
            public native final void setFrom(String from) /*-{
                                                          this.from = eval(from);
                                                          }-*/;

            public native final void setTo(String to) /*-{
                                                      this.to = eval(to);
                                                      }-*/;
        }

        static class GwtPlotLines extends JavaScriptObject {
            protected GwtPlotLines() {
            }

            public native final static GwtPlotLines create() /*-{
                                                             return {};
                                                             }-*/;

            public native final void setId(String id) /*-{
                                                      this.id = id;
                                                      }-*/;

            public native final String getId() /*-{
                                               return this.id;
                                               }-*/;

            public native final void setLabel(GwtPlotLabel label) /*-{
                                                                  this.label = label;
                                                                  }-*/;

            public native final void setColor(String color) /*-{
                                                            this.color = $wnd.getInvientChartsColor(color);
                                                            }-*/;

            public native final void setDashStyle(String dashStyle) /*-{
                                                                    this.dashStyle = dashStyle;
                                                                    }-*/;

            public native final void setZIndex(int zIndex) /*-{
                                                           this.zIndex = zIndex;
                                                           }-*/;

            public native final void setValue(double value) /*-{
                                                            this.value = value;
                                                            }-*/;

            public native final double getValue() /*-{
                                                  return this.value;
                                                  }-*/;

            // FIXME how to set Date.UTC()
            public native final void setValue(String value) /*-{
                                                            this.value = eval(value);
                                                            }-*/;

            public native final void setWidth(int width) /*-{
                                                         this.width = width;
                                                         }-*/;
        }

    }

    static final class GwtXAxisOptions extends GwtAxisBaseOptions {

        protected GwtXAxisOptions() {
        }

        public native final static GwtXAxisOptions create() /*-{
                                                            return {};
                                                            }-*/;

        public native final void setCategories(JsArrayString categories) /*-{
                                                                         this.categories = categories;
                                                                         }-*/;

        public native final JsArrayString getCategories() /*-{
                                                          return this.categories;
                                                          }-*/;

        public native final void setDateTimeLabelFormats(
                GwtDateTimeLabelFormats dateTimeLabelFormats) /*-{
                                                              this.dateTimeLabelFormats = dateTimeLabelFormats;
                                                              }-*/;

        static final class GwtDateTimeLabelFormats extends JavaScriptObject {
            protected GwtDateTimeLabelFormats() {
            }

            public static native final GwtDateTimeLabelFormats create() /*-{
                                                                        return { };
                                                                        }-*/;

            public native final void setSecond(String second) /*-{
                                                              this.second = second;
                                                              }-*/;

            public native final void setMinute(String minute) /*-{
                                                              this.minute = minute;
                                                              }-*/;

            public native final void setHour(String hour) /*-{
                                                          this.hour = hour;
                                                          }-*/;

            public native final void setDay(String day) /*-{
                                                        this.day = day;
                                                        }-*/;

            public native final void setWeek(String week) /*-{
                                                          this.week = week;
                                                          }-*/;

            public native final void setMonth(String month) /*-{
                                                            this.month = month;
                                                            }-*/;

            public native final void setYear(String year) /*-{
                                                          this.year = year;
                                                          }-*/;
        }
    }

    static final class GwtYAxisOptions extends GwtAxisBaseOptions {

        protected GwtYAxisOptions() {
        }

        public native final static GwtYAxisOptions create() /*-{
                                                            return {};
                                                            }-*/;
    }

    static final class GwtPosition extends JavaScriptObject {
        protected GwtPosition() {
        }

        public native final static GwtPosition create() /*-{
                                                         return {};
                                                         }-*/;

        public native final void setAlign(String align) /*-{
                                                        this.align = align;
                                                        }-*/;

        public native final void setVerticalAlign(String verticalAlign) /*-{
                                                                        this.verticalAlign = verticalAlign;
                                                                        }-*/;

        public native final void setX(int x) /*-{
                                             this.x = x;
                                             }-*/;

        public native final void setY(int y) /*-{
                                             this.y = y;
                                             }-*/;
    }

    // Credits
    static final class GwtCreditOptions extends JavaScriptObject {

        protected GwtCreditOptions() {

        }

        public native final static GwtCreditOptions create() /*-{
                                                             return {};
                                                             }-*/;

        public native final void setEnabled(boolean enabled) /*-{
                                                             this.enabled = enabled;
                                                             }-*/;

        public native final void setHref(String href) /*-{
                                                      this.href = href;
                                                      }-*/;

        public native final void setStyle(String style) /*-{
                                                        this.style = eval("(" + style + ")");
                                                        }-*/;

        public native final void setText(String text) /*-{
                                                      this.text = text;
                                                      }-*/;

        public native final void setPosition(GwtPosition position) /*-{
                                                                   this.position = position;
                                                                   }-*/;
    }

    // Tooltip
    static final class GwtTooltipOptions extends JavaScriptObject {
        protected GwtTooltipOptions() {
        }

        public native final static GwtTooltipOptions create() /*-{
                                                              return {};
                                                              }-*/;

        public native final void setBackgroundColor(String backgroundColor) /*-{
                                                                            this.backgroundColor = $wnd.getInvientChartsColor(backgroundColor);
                                                                            }-*/;

        public native final void setBorderColor(String borderColor) /*-{
                                                                    this.borderColor = $wnd.getInvientChartsColor(borderColor);
                                                                    }-*/;

        public native final void setBorderRadius(int borderRadius) /*-{
                                                                       this.borderRadius = borderRadius;
                                                                       }-*/;

        public native final void setBorderWidth(int borderWidth) /*-{
                                                                     this.borderWidth = borderWidth;
                                                                     }-*/;

        // FIXME all diff. values
        public native final void setCrosshairs(GwtTooltipCrosshairs crosshairs) /*-{
																			   	this.crosshairs = crosshairs;
																			   }-*/;

        public native final void setEnabled(boolean enabled) /*-{
                                                             this.enabled = enabled;
                                                             }-*/;

        public native final void setFormatter(String formatter) /*-{
                                                                this.formatter = eval(formatter);
                                                                }-*/;

        public native final void setShadow(boolean shadow) /*-{
                                                           this.shadow = shadow;
                                                           }-*/;

        public native final void setShared(boolean shared) /*-{
                                                           this.shared = shared;
                                                           }-*/;

        public native final void setSnap(int snap) /*-{
                                                       this.snap = snap;
                                                       }-*/;

        public native final void setStyle(String style) /*-{
                                                        this.style = eval("(" + style + ")");
                                                        }-*/;

		public native final void setUseHTML(boolean useHTML) /*-{
															this.useHTML = useHTML;
															}-*/;

		public native final void setHeaderFormat(String headerFormat) /*-{
																		this.headerFormat = headerFormat;
																		}-*/;

		public native final void setPointFormat(String pointFormat) /*-{
																	this.pointFormat = pointFormat;
																	}-*/;

		public native final void setFooterFormat(String footerFormat) /*-{
																	this.footerFormat = footerFormat;
																	}-*/;
    }

	static final class GwtTooltipCrosshairs extends JavaScriptObject {
		protected GwtTooltipCrosshairs() {
		}

		public native final static GwtTooltipCrosshairs create() /*-{
																return {};
															}-*/;

		public native final void setWidth(double width) /*-{
															this.width = width;
														}-*/;

		public native final void setColor(String color) /*-{
															this.color = $wnd.getInvientChartsColor(color);
														}-*/;

		public native final void setDashstyle(String dashStyle) /*-{
																	this.dashStyle = dashStyle;
																}-*/;
		public native final void setZIndex(int zIndex) /*-{
														this.zIndex = zIndex;
														}-*/;
	}

    static final class GwtLegendOptions extends JavaScriptObject {
        protected GwtLegendOptions() {
        }

        public native final static GwtLegendOptions create() /*-{
                                                             return {};
                                                             }-*/;

        public native final void setAlign(String align) /*-{
                                                        this.align = align;
                                                        }-*/;

        public native final void setVerticalAlign(String verticalAlign) /*-{
                                                                        this.verticalAlign = verticalAlign;
                                                                        }-*/;

        public native final void setX(int x) /*-{
                                             this.x = x;
                                             }-*/;

        public native final void setY(int y) /*-{
                                             this.y = y;
                                             }-*/;

        public native final void setBackgroundColor(String backgroundColor) /*-{
                                                                            this.backgroundColor = $wnd.getInvientChartsColor(backgroundColor);
                                                                            }-*/;

        public native final void setBorderColor(String borderColor) /*-{
                                                                    this.borderColor = $wnd.getInvientChartsColor(borderColor);
                                                                    }-*/;

        public native final void setBorderRadius(int borderRadius) /*-{
                                                                   this.borderRadius = borderRadius;
                                                                   }-*/;

        public native final void setBorderWidth(int borderWidth) /*-{
                                                                 this.borderWidth = borderWidth;
                                                                 }-*/;

        public native final void setEnabled(boolean enabled) /*-{
                                                             this.enabled = enabled;
                                                             }-*/;

        public native final void setFloating(boolean floating) /*-{
                                                               this.floating = floating;
                                                               }-*/;

        public native final void setItemHiddenStyle(String itemHiddenStyle) /*-{
                                                                            this.itemHiddenStyle = itemHiddenStyle;
                                                                            }-*/;

        public native final void setItemHoverStyle(String itemHoverStyle) /*-{
                                                                          this.itemHoverStyle = itemHoverStyle;
                                                                          }-*/;

        public native final void setItemStyle(String itemStyle) /*-{
                                                                this.itemStyle = itemStyle;
                                                                }-*/;

        public native final void setItemWidth(int itemWidth) /*-{
                                                             this.itemWidth = itemWidth;
                                                             }-*/;

        public native final void setLayout(String layout) /*-{
                                                          this.layout = layout;
                                                          }-*/;

        public native final void setLabelFormatter(String labelFormatter) /*-{
                                                                          this.labelFormatter = eval(labelFormatter);
                                                                          }-*/;

        public native final void setMargin(int margin) /*-{
                                                       this.margin = margin;
                                                       }-*/;

        public native final void setReversed(boolean reversed) /*-{
                                                               this.reversed = reversed;
                                                               }-*/;

        public native final void setShadow(boolean shadow) /*-{
                                                           this.shadow = shadow;
                                                           }-*/;

        public native final void setSymbolPadding(int symbolPadding) /*-{
                                                                     this.symbolPadding = symbolPadding;
                                                                     }-*/;

        public native final void setSymbolWidth(int symbolWidth) /*-{
                                                                 this.symbolWidth = symbolWidth;
                                                                 }-*/;

        public native final void setWidth(int width) /*-{
                                                     this.width = width;
                                                     }-*/;

    }

    static final class GwtExportingOptions extends JavaScriptObject {
        protected GwtExportingOptions() {
        }

        public native final static GwtExportingOptions create() /*-{
                                                                return {};
                                                                }-*/;

        public native final void setEnabled(boolean enabled) /*-{
                                                             this.enabled = enabled;
                                                             }-*/;

    }

}
