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

import com.invient.vaadin.charts.InvientCharts.*;
import com.invient.vaadin.charts.InvientChartsConfig.*;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.*;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.DateTimePlotBand.DateTimeRange;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.DateTimePlotLine.DateTimeValue;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotBand.NumberRange;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotLine.NumberValue;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.PlotBand.Range;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.PlotLine.Value;
import com.invient.vaadin.charts.InvientChartsConfig.ChartLabel.ChartLabelItem;
import com.invient.vaadin.charts.InvientChartsConfig.DateTimeAxis.DateTimeLabelFormat;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;

import java.util.*;
import java.util.Map.Entry;

/**
 * A utility class used by {@link InvientCharts} to write its state to the UIDL
 * stream. The state includes properties of {@link InvientCharts} such as
 * {@link InvientChartsConfig}, {@link Series}, {@link Point} and various chart
 * events.
 * <p/>
 * In general, only non-null properties/attributes of a chart are written to the
 * UIDL stream.
 *
 * @author Invient
 */
final class InvientChartsUtil {

    /**
     * Private constructor to prevent instantiation.
     */
    private InvientChartsUtil() {

    }

    /**
     * Writes configuration attributes common to chart title and subtitle.
     *
     * @param target           The paint target.
     * @param titleBaseOptions The title base options.
     * @throws PaintException
     */
    private static void writeTitleBaseOptions(PaintTarget target,
                                              TitleBase titleBaseOptions) throws PaintException {
        if (titleBaseOptions.getText() != null) {
            target.addAttribute("text", titleBaseOptions.getText());
        }
        if (titleBaseOptions.getX() != null) {
            target.addAttribute("x", titleBaseOptions.getX());
        }
        if (titleBaseOptions.getY() != null) {
            target.addAttribute("y", titleBaseOptions.getY());
        }
        if (titleBaseOptions.getFloating() != null) {
            target.addAttribute("floating", titleBaseOptions.getFloating());
        }

        if (titleBaseOptions.getAlign() != null) {
            target.addAttribute("align", titleBaseOptions.getAlign().getName());
        }
        if (titleBaseOptions.getVertAlign() != null) {
            target.addAttribute("verticalAlign", titleBaseOptions
                    .getVertAlign().getName());
        }
        if (titleBaseOptions.getStyle() != null) {
            target.addAttribute("style", titleBaseOptions.getStyle());
        }
    }

    /**
     * Writes configuration attributes of the chart title.
     *
     * @param target       The paint target.
     * @param titleOptions The title options.
     * @throws PaintException
     */
    public static void writeTitleConfig(PaintTarget target, Title titleOptions)
            throws PaintException {
        target.startTag("title");
        writeTitleBaseOptions(target, titleOptions);
        if (titleOptions.getMargin() != null) {
            target.addAttribute("margin", titleOptions.getMargin());
        }
        target.endTag("title");
    }

    /**
     * Writes configuration attributes of the chart subtitle. Only those
     * attributes are written who have got non-null values.
     *
     * @param target          The paint target.
     * @param subtitleOptions The subtitle options.
     * @throws PaintException
     */
    public static void writeSubtitleConfig(PaintTarget target,
                                           SubTitle subtitleOptions) throws PaintException {
        target.startTag("subtitle");
        writeTitleBaseOptions(target, subtitleOptions);
        target.endTag("subtitle");
    }

    /**
     * Writes configuration attributes of the chart subtitle.
     *
     * @param target        The paint target.
     * @param creditOptions The credit options.
     * @throws PaintException
     */
    public static void writeCreditConfig(PaintTarget target,
                                         Credit creditOptions) throws PaintException {
        target.startTag("credit");

        if (creditOptions.getEnabled() != null) {
            target.addAttribute("enabled", creditOptions.getEnabled());
        }
        target.startTag("position");
        if (creditOptions.getPosition() != null) {
            if (creditOptions.getPosition().getAlign() != null) {
                target.addAttribute("align", creditOptions.getPosition()
                        .getAlign().getName());
            }
            if (creditOptions.getPosition().getVertAlign() != null) {
                target.addAttribute("verticalAlign", creditOptions
                        .getPosition().getVertAlign().getName());
            }
            if (creditOptions.getPosition().getX() != null) {
                target.addAttribute("x", creditOptions.getPosition().getX());
            }
            if (creditOptions.getPosition().getY() != null) {
                target.addAttribute("y", creditOptions.getPosition().getY());
            }
        }
        target.endTag("position");

        if (creditOptions.getLink() != null) {
            target.addAttribute("href", creditOptions.getLink());
        }
        if (creditOptions.getStyle() != null) {
            target.addAttribute("style", creditOptions.getStyle());
        }
        if (creditOptions.getText() != null) {
            target.addAttribute("text", creditOptions.getText());
        }
        target.endTag("credit");
    }

    /**
     * Writes configuration attributes of the chart legend.
     *
     * @param target        The paint target.
     * @param legendOptions The ledgend options.
     * @throws PaintException
     */
    public static void writeLegendConfig(PaintTarget target,
                                         Legend legendOptions) throws PaintException {
        target.startTag("legend");

        if (legendOptions.getBackgroundColor() != null) {
            target.addAttribute("backgroundColor", legendOptions
                    .getBackgroundColor().getString());
        }

        if (legendOptions.getBorderColor() != null) {
            target.addAttribute("borderColor", legendOptions.getBorderColor()
                    .getString());
        }

        if (legendOptions.getBorderRadius() != null) {
            target.addAttribute("borderRadius", legendOptions.getBorderRadius());
        }

        if (legendOptions.getBorderWidth() != null) {
            target.addAttribute("borderWidth", legendOptions.getBorderWidth());
        }

        if (legendOptions.getEnabled() != null) {
            target.addAttribute("enabled", legendOptions.getEnabled());
        }

        if (legendOptions.getFloating() != null) {
            target.addAttribute("floating", legendOptions.getFloating());
        }

        if (legendOptions.getItemHiddenStyle() != null) {
            target.addAttribute("itemHiddenStyle",
                    legendOptions.getItemHiddenStyle());
        }

        if (legendOptions.getItemHoverStyle() != null) {
            target.addAttribute("itemHoverStyle",
                    legendOptions.getItemHoverStyle());
        }

        if (legendOptions.getItemStyle() != null) {
            target.addAttribute("itemStyle", legendOptions.getItemStyle());
        }

        if (legendOptions.getItemWidth() != null) {
            target.addAttribute("itemWidth", legendOptions.getItemWidth());
        }

        if (legendOptions.getLayout() != null) {
            target.addAttribute("layout", legendOptions.getLayout().getName());
        }

        if (legendOptions.getLabelFormatterJsFunc() != null) {
            target.addAttribute("labelFormatter",
                    legendOptions.getLabelFormatterJsFunc());
        }

        if (legendOptions.getMargin() != null) {
            target.addAttribute("margin", legendOptions.getMargin());
        }

        if (legendOptions.getReversed() != null) {
            target.addAttribute("reversed", legendOptions.getReversed());
        }

        if (legendOptions.getShadow() != null) {
            target.addAttribute("shadow", legendOptions.getShadow());
        }

        if (legendOptions.getSymbolPadding() != null) {
            target.addAttribute("symbolPadding",
                    legendOptions.getSymbolPadding());
        }

        if (legendOptions.getSymbolWidth() != null) {
            target.addAttribute("symbolWidth", legendOptions.getSymbolWidth());
        }

        if (legendOptions.getWidth() != null) {
            target.addAttribute("width", legendOptions.getWidth());
        }

        if (legendOptions.getPosition() != null) {
            if (legendOptions.getPosition().getAlign() != null) {
                target.addAttribute("align", legendOptions.getPosition()
                        .getAlign().getName());
            }
            if (legendOptions.getPosition().getVertAlign() != null) {
                target.addAttribute("verticalAlign", legendOptions
                        .getPosition().getVertAlign().getName());
            }
            if (legendOptions.getPosition().getX() != null) {
                target.addAttribute("x", legendOptions.getPosition().getX());
            }
            if (legendOptions.getPosition().getY() != null) {
                target.addAttribute("y", legendOptions.getPosition().getY());
            }
        }

        target.endTag("legend");
    }

    /**
     * Writes configuration attributes of the chart tooltip.
     *
     * @param target         The paint target.
     * @param tooltipOptions The tooltip options.
     * @throws PaintException
     */
    public static void writeTooltipConfig(PaintTarget target,
                                          Tooltip tooltipOptions) throws PaintException {
        target.startTag("tooltip");

        if (tooltipOptions.getBackgroundColor() != null) {
            target.addAttribute("backgroundColor", tooltipOptions
                    .getBackgroundColor().getString());
        }
        if (tooltipOptions.getBorderColor() != null) {
            target.addAttribute("borderColor", tooltipOptions.getBorderColor()
                    .getString());
        }
        if (tooltipOptions.getBorderRadius() != null) {
            target.addAttribute("borderRadius",
                    tooltipOptions.getBorderRadius());
        }
        if (tooltipOptions.getBorderWidth() != null) {
            target.addAttribute("borderWidth", tooltipOptions.getBorderWidth());
        }
        if (tooltipOptions.getCrosshairs() != null) {
            target.startTag("crosshairs");
            writeTooltipCrosshairsConfig(target, tooltipOptions.getCrosshairs());
            target.endTag("crosshairs");
        }
        if (tooltipOptions.getEnabled() != null) {
            target.addAttribute("enabled", tooltipOptions.getEnabled());
        }
        if (tooltipOptions.getFormatterJsFunc() != null) {
            target.addAttribute("formatter",
                    tooltipOptions.getFormatterJsFunc());
        }
        if (tooltipOptions.getShadow() != null) {
            target.addAttribute("shadow", tooltipOptions.getShadow());
        }
        if (tooltipOptions.getShared() != null) {
            target.addAttribute("shared", tooltipOptions.getShared());
        }
        if (tooltipOptions.getSnap() != null) {
            target.addAttribute("snap", tooltipOptions.getSnap());
        }
        if (tooltipOptions.getStyle() != null) {
            target.addAttribute("style", tooltipOptions.getStyle());
        }
        if (tooltipOptions.getUseHTML() != null) {
            target.addAttribute("useHTML", tooltipOptions.getUseHTML());
        }
        if (tooltipOptions.getHeaderFormat() != null) {
            target.addAttribute("headerFormat", tooltipOptions.getHeaderFormat());
        }
        if (tooltipOptions.getPointFormat() != null) {
            target.addAttribute("pointFormat", tooltipOptions.getPointFormat());
        }
        if (tooltipOptions.getFooterFormat() != null) {
            target.addAttribute("footerFormat", tooltipOptions.getFooterFormat());
        }

        target.endTag("tooltip");
    }

    /**
     * Writes configuration attributes of the charts tooltip crosshairsOptions.
     *
     * @param target            The paint target.
     * @param crosshairsOptions The crosshairs options.
     * @throws PaintException
     */
    public static void writeTooltipCrosshairsConfig(PaintTarget target,
                                                    Tooltip.Crosshairs crosshairsOptions) throws PaintException {
        if (crosshairsOptions.getWidth() != null) {
            target.addAttribute("width", crosshairsOptions.getWidth());
        }
        if (crosshairsOptions.getColor() != null) {
            target.addAttribute("color", crosshairsOptions.getColor().getString());
        }
        if (crosshairsOptions.getDashStyle() != null) {
            target.addAttribute("dashStyle", crosshairsOptions.getDashStyle().getName());
        }
        if (crosshairsOptions.getzIndex() != null) {
            target.addAttribute("zIndex", crosshairsOptions.getzIndex());
        }

    }

    /**
     * Writes configuration attributes of the chart itself.
     *
     * @param target       The paint target.
     * @param chartOptions The chart options.
     * @throws PaintException
     */
    public static void writeGeneralChartConfig(PaintTarget target,
                                               GeneralChartConfig chartOptions) throws PaintException {
        target.startTag("chart");

        if (chartOptions.getType() != null) {
            target.addAttribute("type", chartOptions.getType().getName());
        }

        if (chartOptions.getWidth() != null) {
            target.addAttribute("width", chartOptions.getWidth());
        }
        if (chartOptions.getHeight() != null) {
            target.addAttribute("height", chartOptions.getHeight());
        }

        if (chartOptions.getBackgroundColor() != null) {
            target.addAttribute("backgroundColor", chartOptions
                    .getBackgroundColor().getString());
        }
        if (chartOptions.getBorderColor() != null) {
            target.addAttribute("borderColor", chartOptions.getBorderColor()
                    .getString());
        }
        if (chartOptions.getBorderRadius() != null) {
            target.addAttribute("borderRadius", chartOptions.getBorderRadius());
        }
        if (chartOptions.getBorderWidth() != null) {
            target.addAttribute("borderWidth", chartOptions.getBorderWidth());
        }

        if (chartOptions.getIgnoreHiddenSeries() != null) {
            target.addAttribute("ignoreHiddenSeries",
                    chartOptions.getIgnoreHiddenSeries());
        }
        if (chartOptions.getInverted() != null) {
            target.addAttribute("inverted", chartOptions.getInverted());
        }

        if (chartOptions.getMargin() != null) {
            if (chartOptions.getMargin().getTop() != null) {
                target.addAttribute("marginTop", chartOptions.getMargin()
                        .getTop());
            }
            if (chartOptions.getMargin().getLeft() != null) {
                target.addAttribute("marginLeft", chartOptions.getMargin()
                        .getLeft());
            }
            if (chartOptions.getMargin().getBottom() != null) {
                target.addAttribute("marginBottom", chartOptions.getMargin()
                        .getBottom());
            }
            if (chartOptions.getMargin().getRight() != null) {
                target.addAttribute("marginRight", chartOptions.getMargin()
                        .getRight());
            }
        }

        if (chartOptions.getSpacing() != null) {

            if (chartOptions.getSpacing().getTop() != null) {
                target.addAttribute("spacingTop", chartOptions.getSpacing()
                        .getTop());
            }
            if (chartOptions.getSpacing().getLeft() != null) {
                target.addAttribute("spacingLeft", chartOptions.getSpacing()
                        .getLeft());
            }
            if (chartOptions.getSpacing().getBottom() != null) {
                target.addAttribute("spacingBottom", chartOptions.getSpacing()
                        .getBottom());
            }
            if (chartOptions.getSpacing().getRight() != null) {
                target.addAttribute("spacingRight", chartOptions.getSpacing()
                        .getRight());
            }
        }

        if (chartOptions.getShowAxes() != null) {
            target.addAttribute("showAxes", chartOptions.getShowAxes());
        }
        if (chartOptions.getZoomType() != null) {
            target.addAttribute("zoomType", chartOptions.getZoomType()
                    .getName());
        }
        target.addAttribute("clientZoom", chartOptions.isClientZoom());

        if (chartOptions.getAlignTicks() != null) {
            target.addAttribute("alignTicks", chartOptions.getAlignTicks());
        }
        if (chartOptions.getAnimation() != null) {
            target.addAttribute("animation", chartOptions.getAnimation());
        }
        if (chartOptions.getClassName() != null) {
            target.addAttribute("className", chartOptions.getClassName());
        }

        if (chartOptions.getPlot() != null) {
            if (chartOptions.getPlot().getBackgroundColor() != null) {
                target.addAttribute("plotBackgroundColor", chartOptions
                        .getPlot().getBackgroundColor().getString());
            }
            if (chartOptions.getPlot().getBorderColor() != null) {
                target.addAttribute("plotBorderColor", chartOptions.getPlot()
                        .getBorderColor().getString());
            }
            if (chartOptions.getPlot().getBackgroundImage() != null) {
                target.addAttribute("plotBackgroundImage", chartOptions
                        .getPlot().getBackgroundImage());
            }
            if (chartOptions.getPlot().getBorderWidth() != null) {
                target.addAttribute("plotBorderWidth", chartOptions.getPlot()
                        .getBorderWidth());
            }
            if (chartOptions.getPlot().getShadow() != null) {
                target.addAttribute("plotShadow", chartOptions.getPlot()
                        .getShadow());
            }
        }

        if (chartOptions.getReflow() != null) {
            target.addAttribute("reflow", chartOptions.getReflow());
        }
        if (chartOptions.getShadow() != null) {
            target.addAttribute("shadow", chartOptions.getShadow());
        }
        if (chartOptions.getStyle() != null) {
            target.addAttribute("style", chartOptions.getStyle());
        }

        target.endTag("chart");
    }

    /**
     * Writes configuration attributes of every series type. The series type can
     * be one of the line, spline, scatter, area, areaspline, bar, column and
     * pie.
     *
     * @param target        The paint target.
     * @param seriesOptions The series options.
     * @throws PaintException
     */
    public static void writeSeriesConfigPerSeriesType(PaintTarget target,
                                                      Map<SeriesType, SeriesConfig> seriesOptions) throws PaintException {
        target.startTag("seriesOptionsPerSeriesType");
        // For each SeriesType have separate tag
        for (Entry<SeriesType, SeriesConfig> seriesEntryOptions : seriesOptions
                .entrySet()) {
            String tagName = seriesEntryOptions.getKey().getName();
            //
            target.startTag(tagName);
            // Write options for appropriate series type
            writeSeriesConfig(target, seriesEntryOptions.getValue());
            //
            target.endTag(tagName);
        }
        target.endTag("seriesOptionsPerSeriesType");
    }

    /**
     * Writes configuration attributes of a single series.
     *
     * @param target The paint target.
     * @param series The series options.
     * @throws PaintException
     */
    private static void writeSeriesConfig(PaintTarget target, SeriesConfig series) throws PaintException {
        // Write options for appropriate series type

        if (series instanceof LineConfig) {
            writeLineOptions(target, (LineConfig) series);
        } else if (series instanceof ScatterConfig) {
            writeScatterOptions(target, (ScatterConfig) series);
        } else if (series instanceof AreaSplineConfig) {
            writeAreaSplineOptions(target, (AreaSplineConfig) series);
        } else if (series instanceof SplineConfig) {
            writeSplineOptions(target, (SplineConfig) series);
        } else if (series instanceof AreaConfig) {
            writeAreaOptions(target, (AreaConfig) series);
        } else if (series instanceof ColumnConfig) {
            writeColumnOptions(target, (ColumnConfig) series);
        } else if (series instanceof BarConfig) {
            writeBarOptions(target, (BarConfig) series);
        } else if (series instanceof PieConfig) {
            writePieOptions(target, (PieConfig) series);
        } else {
            // Common series attributes
            writeCommonSeriesOptions(target, series);
        }

    }

    /**
     * Writes configuration attributes common to all types of series.
     *
     * @param target        The paint target.
     * @param seriesOptions The series options.
     * @throws PaintException
     */
    private static void writeCommonSeriesOptions(PaintTarget target,
                                                 SeriesConfig seriesOptions) throws PaintException {
        if (seriesOptions.getAllowPointSelect() != null) {
            target.addAttribute("allowPointSelect",
                    seriesOptions.getAllowPointSelect());
        }
        if (seriesOptions.getAnimation() != null) {
            target.addAttribute("animation", seriesOptions.getAnimation());
        }
        if (seriesOptions.getCursor() != null) {
            target.addAttribute("cursor", seriesOptions.getCursor());
        }

        if (seriesOptions.getColor() != null) {
            target.addAttribute("color", seriesOptions.getColor().getString());
        }
        if (seriesOptions.getEnableMouseTracking() != null) {
            target.addAttribute("enableMouseTracking",
                    seriesOptions.getEnableMouseTracking());
        }

        // if (seriesOptions.getSelected() != null) {
        // target.addAttribute("selected", seriesOptions.getSelected());
        // }

        if (seriesOptions.getShowCheckbox() != null) {
            target.addAttribute("showCheckbox", seriesOptions.getShowCheckbox());
        }
        if (seriesOptions.getShowInLegend() != null) {
            target.addAttribute("showInLegend", seriesOptions.getShowInLegend());
        }
        if (seriesOptions.getStacking() != null) {
            target.addAttribute("stacking", seriesOptions.getStacking()
                    .getName());
        }
        if (seriesOptions.getShadow() != null) {
            target.addAttribute("shadow", seriesOptions.getShadow());
        }
        if (seriesOptions.getVisible() != null) {
            target.addAttribute("visible", seriesOptions.getVisible());
        }

        writeSeriesDataLabel(target, seriesOptions.getDataLabel());
        writeSeriesState(target, seriesOptions.getHoverState());
    }

    /**
     * Writes configuration attributes of a series hover state.
     *
     * @param target      The paint target.
     * @param seriesState The series state.
     * @throws PaintException
     */
    private static void writeSeriesState(PaintTarget target,
                                         SeriesState seriesState) throws PaintException {
        target.startTag("state");
        if (seriesState != null) {
            target.startTag("hover");
            if (seriesState.getEnabled() != null) {
                target.addAttribute("enabled", seriesState.getEnabled());
            }
            if (seriesState.getLineWidth() != null) {
                target.addAttribute("lineWidth", seriesState.getLineWidth());
            }
            if (seriesState instanceof NonLinearSeriesState
                    && ((NonLinearSeriesState) seriesState).getBrightness() != null) {
                target.addAttribute("brightness",
                        ((NonLinearSeriesState) seriesState).getBrightness());
            }
            target.endTag("hover");
        }
        target.endTag("state");
    }

    /**
     * Writes configuration attributes common to all types of series. It takes
     * care of specific data labels in case of pie.
     *
     * @param target    The paint target.
     * @param dataLabel The data label.
     * @throws PaintException
     */
    private static void writeSeriesDataLabel(PaintTarget target,
                                             DataLabel dataLabel) throws PaintException {
        target.startTag("dataLabel");
        if (dataLabel != null) {
            if (dataLabel instanceof PieDataLabel) {
                writePieDataLabel(target, (PieDataLabel) dataLabel);
            } else {
                writeDataLabel(target, dataLabel);
            }
        }
        target.endTag("dataLabel");
    }

    /**
     * Writes configuration attributes of a series data labels.
     *
     * @param target    The paint target.
     * @param dataLabel The data label.
     * @throws PaintException
     */
    private static void writeDataLabel(PaintTarget target, DataLabel dataLabel)
            throws PaintException {
        if (dataLabel.getAlign() != null) {
            target.addAttribute("align", dataLabel.getAlign().getName());
        }
        if (dataLabel.getEnabled() != null) {
            target.addAttribute("enabled", dataLabel.getEnabled());
        }
        if (dataLabel.getFormatterJsFunc() != null) {
            target.addAttribute("formatter", dataLabel.getFormatterJsFunc());
        }
        if (dataLabel.getRotation() != null) {
            target.addAttribute("rotation", dataLabel.getRotation());
        }
        if (dataLabel.getStyle() != null) {
            target.addAttribute("style", dataLabel.getStyle());
        }
        if (dataLabel.getX() != null) {
            target.addAttribute("x", dataLabel.getX());
        }
        if (dataLabel.getY() != null) {
            target.addAttribute("y", dataLabel.getY());
        }
        if (dataLabel.getColor() != null) {
            target.addAttribute("color", dataLabel.getColor().getString());
        }
    }

    /**
     * Writes configuration attributes of a pie chart's data label.
     *
     * @param target    The paint target.
     * @param dataLabel The data label.
     * @throws PaintException
     */
    private static void writePieDataLabel(PaintTarget target,
                                          PieDataLabel dataLabel) throws PaintException {

        writeDataLabel(target, dataLabel);

        if (dataLabel.getConnectorWidth() != null) {
            target.addAttribute("connectorWidth", dataLabel.getConnectorWidth());
        }
        if (dataLabel.getConnectorPadding() != null) {
            target.addAttribute("connectorPadding",
                    dataLabel.getConnectorPadding());
        }
        if (dataLabel.getConnectorColor() != null) {
            target.addAttribute("connectorColor", dataLabel.getConnectorColor()
                    .getString());
        }
        if (dataLabel.getDistance() != null) {
            target.addAttribute("distance", dataLabel.getDistance());
        }

    }

    /**
     * Writes configuration attributes of an axis data labels.
     *
     * @param target    The paint target.
     * @param dataLabel The data label.
     * @throws PaintException
     */
    private static void writeAxisDataLabel(PaintTarget target,
                                           AxisDataLabel dataLabel) throws PaintException {

        writeDataLabel(target, dataLabel);

        if (dataLabel.getStep() != null) {
            target.addAttribute("step", dataLabel.getStep());
        }

    }

    /**
     * Writes configuration attributes of an x-axis data labels.
     *
     * @param target    The paint target.
     * @param dataLabel The data label.
     * @throws PaintException
     */
    private static void writeXAxisDataLabel(PaintTarget target,
                                            XAxisDataLabel dataLabel) throws PaintException {
        target.startTag("label");
        if (dataLabel != null) {
            writeAxisDataLabel(target, dataLabel);

            if (dataLabel.getStaggerLines() != null) {
                target.addAttribute("staggerLines", dataLabel.getStaggerLines());
            }
        }

        target.endTag("label");
    }

    /**
     * Writes configuration attributes of y-axis data labels.
     *
     * @param target    The paint target.
     * @param dataLabel The data label.
     * @throws PaintException
     */
    private static void writeYAxisDataLabel(PaintTarget target,
                                            YAxisDataLabel dataLabel) throws PaintException {
        target.startTag("label");

        if (dataLabel != null) {
            writeAxisDataLabel(target, dataLabel);
        }
        target.endTag("label");
    }

    /**
     * Writes configuration attributes of a marker. It takes care of handling
     * image or symbol marker.
     *
     * @param target        The paint target.
     * @param markerOptions The marker options.
     * @throws PaintException
     */
    private static void writeMarkerOptions(PaintTarget target,
                                           Marker markerOptions) throws PaintException {
        target.startTag("marker");
        if (markerOptions != null) {
            if (markerOptions.getEnabled() != null) {
                target.addAttribute("enabled", markerOptions.getEnabled());
            }
            if (markerOptions instanceof ImageMarker) {
                target.addAttribute("markerType", "image");
                writeImageMarkerOptions(target, (ImageMarker) markerOptions);
            } else if (markerOptions instanceof SymbolMarker) {
                target.addAttribute("markerType", "symbol");
                writeSymbolMarkerOptions(target, (SymbolMarker) markerOptions);
                writeMarkerStates(target, (SymbolMarker) markerOptions);
            }
        }
        target.endTag("marker");
    }

    /**
     * Writes configuration attributes of a marker states hover and select
     *
     * @param target The paint target.
     * @param marker The marker.
     * @throws PaintException
     */
    private static void writeMarkerStates(PaintTarget target,
                                          SymbolMarker marker) throws PaintException {
        target.startTag("states");
        //
        target.startTag("hover");
        if (marker.getHoverState() != null) {
            writeMarkerState(target, marker.getHoverState());
        }
        target.endTag("hover");

        //
        target.startTag("select");
        if (marker.getSelectState() != null) {
            writeMarkerState(target, marker.getSelectState());
        }
        target.endTag("select");

        target.endTag("states");
    }

    /**
     * Writes configuration attributes of an image marker
     *
     * @param target    The paint target.
     * @param imgMarker The image marker.
     * @throws PaintException
     */
    private static void writeImageMarkerOptions(PaintTarget target,
                                                ImageMarker imgMarker) throws PaintException {
        if (imgMarker.getImageURL() != null) {
            target.addAttribute("symbol", imgMarker.getImageURL());
        }
    }

    /**
     * Writes configuration attributes of a symbol marker
     *
     * @param target       The paint target.
     * @param symbolMarker The symbol marker.
     * @throws PaintException
     */
    private static void writeSymbolMarkerOptions(PaintTarget target,
                                                 SymbolMarker symbolMarker) throws PaintException {
        if (symbolMarker.getFillColor() != null) {
            target.addAttribute("fillColor", symbolMarker.getFillColor()
                    .getString());
        }
        if (symbolMarker.getLineColor() != null) {
            target.addAttribute("lineColor", symbolMarker.getLineColor()
                    .getString());
        }
        if (symbolMarker.getLineWidth() != null) {
            target.addAttribute("lineWidth", symbolMarker.getLineWidth());
        }
        if (symbolMarker.getRadius() != null) {
            target.addAttribute("radius", symbolMarker.getRadius());
        }
        if (symbolMarker.getSymbol() != null) {
            target.addAttribute("symbol", symbolMarker.getSymbol().getName());
        }
    }

    /**
     * Writes configuration attributes of a marker
     *
     * @param target      The paint target.
     * @param markerState The marker state.
     * @throws PaintException
     */
    private static void writeMarkerState(PaintTarget target,
                                         MarkerState markerState) throws PaintException {
        if (markerState.getEnabled() != null) {
            target.addAttribute("enabled", markerState.getEnabled());
        }
        if (markerState.getFillColor() != null) {
            target.addAttribute("fillColor", markerState.getFillColor()
                    .getString());
        }
        if (markerState.getLineColor() != null) {
            target.addAttribute("lineColor", markerState.getLineColor()
                    .getString());
        }
        if (markerState.getLineWidth() != null) {
            target.addAttribute("lineWidth", markerState.getLineWidth());
        }
        if (markerState.getRadius() != null) {
            target.addAttribute("radius", markerState.getRadius());
        }
    }

    /**
     * Writes configuration attributes common to all lines series such as line,
     * spline and area.
     *
     * @param target          The paint target.
     * @param baseLineOptions The base line options.
     * @throws PaintException
     */
    private static void writeBaseLineOptions(PaintTarget target,
                                             BaseLineConfig baseLineOptions) throws PaintException {
        writeCommonSeriesOptions(target, baseLineOptions);
        if (baseLineOptions.getDashStyle() != null) {
            target.addAttribute("dashStyle", baseLineOptions.getDashStyle()
                    .getName());
        }
        if (baseLineOptions.getLineWidth() != null) {
            target.addAttribute("lineWidth", baseLineOptions.getLineWidth());
        }
        if (baseLineOptions.getPointInterval() != null) {
            target.addAttribute("pointInterval",
                    baseLineOptions.getPointInterval());
        }
        if (baseLineOptions.getTurboThreshold() != null) {
            target.addAttribute("turboThreshold",
                    baseLineOptions.getTurboThreshold());
        }
        if (baseLineOptions.getPointStart() != null) {
            target.addAttribute("pointStart", baseLineOptions.getPointStart());
        }
        if (baseLineOptions.getStickyTracking() != null) {
            target.addAttribute("stickyTracking",
                    baseLineOptions.getStickyTracking());
        }
        writeMarkerOptions(target, baseLineOptions.getMarker());
    }

    /**
     * Writes configuration attributes of a spline series
     *
     * @param target        The paint target.
     * @param splineOptions The spline options.
     * @throws PaintException
     */
    private static void writeSplineOptions(PaintTarget target,
                                           SplineConfig splineOptions) throws PaintException {
        writeBaseLineOptions(target, splineOptions);
    }

    /**
     * Writes configuration attributes of s scatter series
     *
     * @param target         The paint target.
     * @param scatterOptions The scatter options.
     * @throws PaintException
     */
    private static void writeScatterOptions(PaintTarget target,
                                            ScatterConfig scatterOptions) throws PaintException {
        writeBaseLineOptions(target, scatterOptions);
    }

    /**
     * Writes configuration attributes of a line series
     *
     * @param target      The paint target.
     * @param lineOptions The line options.
     * @throws PaintException
     */
    private static void writeLineOptions(PaintTarget target,
                                         LineConfig lineOptions) throws PaintException {
        writeBaseLineOptions(target, lineOptions);
        //
        if (lineOptions.getStep() != null) {
            target.addAttribute("step", lineOptions.getStep());
        }
    }

    /**
     * Writes configuration attributes of an area series
     *
     * @param target      The paint target.
     * @param areaOptions The area options.
     * @throws PaintException
     */
    private static void writeAreaOptions(PaintTarget target,
                                         AreaConfig areaOptions) throws PaintException {
        writeBaseLineOptions(target, areaOptions);
        //
        if (areaOptions.getFillColor() != null) {
            target.addAttribute("fillColor", areaOptions.getFillColor()
                    .getString());
        }
        if (areaOptions.getFillOpacity() != null) {
            target.addAttribute("fillOpacity", areaOptions.getFillOpacity());
        }
        if (areaOptions.getLineColor() != null) {
            target.addAttribute("lineColor", areaOptions.getLineColor()
                    .getString());
        }
        if (areaOptions.getThreshold() != null) {
            target.addAttribute("threshold", areaOptions.getThreshold());
        }

    }

    /**
     * Writes configuration attributes of an area-spline
     *
     * @param target            The paint target.
     * @param areaSplineOptions The area spline options.
     * @throws PaintException
     */
    private static void writeAreaSplineOptions(PaintTarget target,
                                               AreaSplineConfig areaSplineOptions) throws PaintException {
        writeAreaOptions(target, areaSplineOptions);
    }

    /**
     * Writes configuration attributes of a pie series
     *
     * @param target     The paint target.
     * @param pieOptions The pie options.
     * @throws PaintException
     */
    private static void writePieOptions(PaintTarget target, PieConfig pieOptions)
            throws PaintException {
        writeCommonSeriesOptions(target, pieOptions);
        //
        if (pieOptions.getBorderColor() != null) {
            target.addAttribute("borderColor", pieOptions.getBorderColor()
                    .getString());
        }
        if (pieOptions.getBorderWidth() != null) {
            target.addAttribute("borderWidth", pieOptions.getBorderWidth());
        }
        if (pieOptions.getCenterX() != null) {
            target.addAttribute("centerX", pieOptions.getCenterX());
        }
        if (pieOptions.getCenterY() != null) {
            target.addAttribute("centerY", pieOptions.getCenterY());
        }
        if (pieOptions.getInnerSize() != null) {
            target.addAttribute("innerSize", pieOptions.getInnerSize());
        }
        if (pieOptions.getSize() != null) {
            target.addAttribute("size", pieOptions.getSize());
        }
        if (pieOptions.getSlicedOffset() != null) {
            target.addAttribute("slicedOffset", pieOptions.getSlicedOffset());
        }
        if (pieOptions.getIgnoreHiddenPoint() != null) {
            target.addAttribute("ignoreHiddenPoint", pieOptions.getIgnoreHiddenPoint());
        }
    }

    /**
     * Writes configuration attributes common to columnar series such as bar and
     * column
     *
     * @param target         The paint target.
     * @param baseBarOptions The base bar options.
     * @throws PaintException
     */
    private static void writeBaseBarOptions(PaintTarget target,
                                            BaseBarConfig baseBarOptions) throws PaintException {
        writeCommonSeriesOptions(target, baseBarOptions);

        if (baseBarOptions.getBorderColor() != null) {
            target.addAttribute("borderColor", baseBarOptions.getBorderColor()
                    .getString());
        }
        if (baseBarOptions.getBorderRadius() != null) {
            target.addAttribute("borderRadius",
                    baseBarOptions.getBorderRadius());
        }
        if (baseBarOptions.getBorderWidth() != null) {
            target.addAttribute("borderWidth", baseBarOptions.getBorderWidth());
        }
        if (baseBarOptions.getColorByPoint() != null) {
            target.addAttribute("colorByPoint",
                    baseBarOptions.getColorByPoint());
        }
        if (baseBarOptions.getGroupPadding() != null) {
            target.addAttribute("groupPadding",
                    baseBarOptions.getGroupPadding());
        }
        if (baseBarOptions.getMinPointLength() != null) {
            target.addAttribute("minPointLength",
                    baseBarOptions.getMinPointLength());
        }
        if (baseBarOptions.getPointPadding() != null) {
            target.addAttribute("pointPadding",
                    baseBarOptions.getPointPadding());
        }
        if (baseBarOptions.getPointWidth() != null) {
            target.addAttribute("pointWidth", baseBarOptions.getPointWidth());
        }
    }

    /**
     * Writes configuration attributes of a bar series
     *
     * @param target     The paint target.
     * @param barOptions The bar options.
     * @throws PaintException
     */
    private static void writeBarOptions(PaintTarget target, BarConfig barOptions)
            throws PaintException {
        writeBaseBarOptions(target, barOptions);
    }

    /**
     * Writes configuration attributes of a column series
     *
     * @param target        The paint target.
     * @param columnOptions The column options.
     * @throws PaintException
     */
    private static void writeColumnOptions(PaintTarget target,
                                           ColumnConfig columnOptions) throws PaintException {
        writeBaseBarOptions(target, columnOptions);
    }

    /**
     * Writes data of each series of the chart. It transforms data into a form
     * which is usable by the Vaadin terminal class. It also writes
     * configuration attributes specific to each series, if any.
     *
     * @param target          The paint target.
     * @param chartSeriesType The chart series type.
     * @param data            The data.
     * @param xAxis           The x-axis.
     * @param yAxis           The y-axis.
     *
     * @throws PaintException
     */
    public static void writeSeries(PaintTarget target,
                                   SeriesType chartSeriesType, LinkedHashSet<Series> data,
                                   LinkedHashSet<XAxis> xAxis, LinkedHashSet<YAxis> yAxis)
            throws PaintException {
        if (data == null) {
            return;
        }
        for (Series series : data) {
            target.startTag("series");

            if (series.getName() != null && series.getName().length() > 0) {
                target.addAttribute("name", series.getName());
            }
            if (series.getType() != null) {
                target.addAttribute("type", series.getType().getName());
            }
            if (series.getStack() != null && series.getStack().length() > 0) {
                target.addAttribute("stack", series.getStack());
            }

            target.addAttribute("xAxis",
                    getXAxisIndex(series.getXAxis(), xAxis));
            target.addAttribute("yAxis",
                    getYAxisIndex(series.getYAxis(), yAxis));

            String seriesOptionsTagName = chartSeriesType.getName();
            if (series.getType() != null) {
                seriesOptionsTagName = series.getType().getName();
            }

            target.startTag(seriesOptionsTagName);
            if (series.getConfig() != null) {
                writeSeriesConfig(target, series.getConfig());
            }
            target.endTag(seriesOptionsTagName);

            target.startTag("points");
            if (series.getPoints() != null) {
                writePoints(target, series.getPoints());
            }
            target.endTag("points");

            target.endTag("series");
        }

    }

    /**
     * Writes point data (x, y) and its configuration attributes, if any. If a
     * point does not have x and y values then the point is skipped. However,
     * for such points empty tags is created without any attributes or children.
     *
     * @param target
     * @param points
     * @throws PaintException
     */
    private static void writePoints(PaintTarget target,
                                    LinkedHashSet<? extends Point> points) throws PaintException {
        if (points == null) {
            return;
        }
        for (Point point : points) {
            target.startTag("point");
            if (point.getX() != null || point.getY() != null) {
                if (point.getId() != null && point.getId().length() > 0) {
                    target.addAttribute("id", point.getId());
                }
                if (point.getName() != null && point.getName().length() > 0) {
                    target.addAttribute("name", point.getName());
                }
                if (point.getX() != null) {
                    if (point instanceof DecimalPoint) {
                        target.addAttribute("x", (Double) point.getX());
                    } else {
                        target.addAttribute(
                                "x",
                                getDate((Date) point.getX(),
                                        ((DateTimeSeries) point.getSeries())
                                                .isIncludeTime()));
                    }
                }
                if (point.getY() != null) {
                    target.addAttribute("y", (Double) point.getY());
                }
                target.addAttribute("isShift", point.isShift());
                // Point config
                if (point.getConfig() != null) {
                    if (point.getConfig().getSliced() != null) {
                        target.addAttribute("sliced", point.getConfig()
                                .getSliced());
                    }
                    if (point.getConfig().getSelected() != null) {
                        target.addAttribute("selected", point.getConfig()
                                .getSelected());
                    }
                    if (point.getConfig().getColor() != null) {
                        target.addAttribute("color", point.getConfig()
                                .getColor().getString());
                    }
                    if (point.getConfig().getMarker() != null) {
                        writeMarkerOptions(target, point.getConfig()
                                .getMarker());
                    }
                }
            }
            target.endTag("point");
        }
    }

    /**
     * Writes configuration attributes common to all types of axis.
     *
     * @param target
     * @param axis
     * @param axes
     * @throws PaintException
     */
    private static void writeBaseAxis(PaintTarget target, AxisBase axis,
                                      LinkedHashSet<? extends Axis> axes) throws PaintException {

        if (axis.getAlternateGridColor() != null) {
            target.addAttribute("alternateGridColor", axis
                    .getAlternateGridColor().getString());
        }
        if (axis.getEndOnTick() != null) {
            target.addAttribute("endOnTick", axis.getEndOnTick());
        }
        if (axis.getGrid() != null) {
            writeAxisGrid(target, axis.getGrid());
        }
        if (axis.getId() != null && axis.getId().length() > 0) {
            target.addAttribute("id", axis.getId());
        }

        if (axis.getLineColor() != null) {
            target.addAttribute("lineColor", axis.getLineColor().getString());
        }
        if (axis.getLineWidth() != null) {
            target.addAttribute("lineWidth", axis.getLineWidth());
        }
        if (axis.getLinkedTo() != null) {
            target.addAttribute("linkedTo",
                    getAxisIndex(axis.getLinkedTo(), axes));
        }

        if (axis.getMaxPadding() != null) {
            target.addAttribute("maxPadding", axis.getMaxPadding());
        }
        if (axis.getMaxZoom() != null) {
            target.addAttribute("maxZoom", axis.getMaxZoom());
        }
        if (axis.getMinPadding() != null) {
            target.addAttribute("minPadding", axis.getMinPadding());
        }

        //
        if (axis.getMinorGrid() != null) {
            writeAxisMinorGrid(target, axis.getMinorGrid());
        }
        //
        if (axis.getMinorTick() != null) {
            writeAxisMinorTick(target, axis.getMinorTick());
        }

        if (axis.getOffset() != null) {
            target.addAttribute("offset", axis.getOffset());
        }
        if (axis.getOpposite() != null) {
            target.addAttribute("opposite", axis.getOpposite());
        }
        if (axis.getReversed() != null) {
            target.addAttribute("reversed", axis.getReversed());
        }
        if (axis.getShowFirstLabel() != null) {
            target.addAttribute("showFirstLabel", axis.getShowFirstLabel());
        }
        if (axis.getShowLastLabel() != null) {
            target.addAttribute("showLastLabel", axis.getShowLastLabel());
        }

        if (axis.getStartOfWeek() != null) {
            target.addAttribute("startOfWeek", axis.getStartOfWeek().ordinal());
        }
        if (axis.getStartOnTick() != null) {
            target.addAttribute("startOnTick", axis.getStartOnTick());
        }
        //
        if (axis.getTick() != null) {
            writeAxisTick(target, axis.getTick());
        }
        //
        if (axis.getType() != null) {
            target.addAttribute("type", axis.getType().getName());
        }
        // Title
        writeAxisTitle(target, axis.getTitle());
        // Labels
        if (axis.getLabel() instanceof XAxisDataLabel) {
            writeXAxisDataLabel(target, (XAxisDataLabel) axis.getLabel());
        } else {
            writeYAxisDataLabel(target, (YAxisDataLabel) axis.getLabel());
        }

        if (axis instanceof NumberAxis) {
            writePlotBands(target, ((NumberAxis) axis).getPlotBands());
            //
            writePlotLines(target, ((NumberAxis) axis).getPlotLines());
        } else if (axis instanceof DateTimeAxis) {
            writePlotBands(target, ((DateTimeAxis) axis).getPlotBands());
            //
            writePlotLines(target, ((DateTimeAxis) axis).getPlotLines());
        } else if (axis instanceof CategoryAxis) {
            writePlotBands(target, ((CategoryAxis) axis).getPlotBands());
            //
            writePlotLines(target, ((CategoryAxis) axis).getPlotLines());
        }

    }

    /**
     * Returns an index of an x-axis in a list of x-axis only if the x-axis
     * exists otherwise null
     *
     * @param indexOfXAxis
     * @param xAxes
     * @return Retrieves Retrieves an index of an x-axis in a list of x-axis
     *         only if the x-axis exists otherwise null
     */
    private static Integer getXAxisIndex(XAxis indexOfXAxis,
                                         LinkedHashSet<XAxis> xAxes) {
        return getAxisIndex(indexOfXAxis, xAxes);
    }

    /**
     * Returns an index of a y-axis in a list of y-axis only if the y-axis
     * exists otherwise null
     *
     * @param indexOfYAxis
     * @param yAxes
     * @return Returns index of a y-axis in a list of y-axis only if the y-axis
     *         exists otherwise null
     */
    private static Integer getYAxisIndex(YAxis indexOfYAxis,
                                         LinkedHashSet<YAxis> yAxes) {
        return getAxisIndex(indexOfYAxis, yAxes);
    }

    /**
     * Returns an index of an axis in a list of axis only if the axis exists
     * otherwise null
     *
     * @param indexOfAxis
     * @param axes
     * @return Returns an index of an axis in a list of axis only if the axis
     *         exists otherwise null
     */
    private static Integer getAxisIndex(Axis indexOfAxis,
                                        LinkedHashSet<? extends Axis> axes) {
        if (indexOfAxis == null || axes == null || axes.size() == 0) {
            return 0;
        }
        int index = 0;
        for (Axis axis : axes) {
            if (axis == indexOfAxis) {
                return index;
            }
            index++;
        }

        return null;
    }

    /**
     * Writes configuration attributes of the plotbands associated with an axis.
     *
     * @param target
     * @param plotBands
     * @throws PaintException
     */
    private static void writePlotBands(PaintTarget target,
                                       LinkedHashSet<? extends PlotBand> plotBands) throws PaintException {
        target.startTag("plotBands");
        if (plotBands != null) {
            for (PlotBand plotBand : plotBands) {
                target.startTag("plotBand");

                if (plotBand.getColor() != null) {
                    target.addAttribute("color", plotBand.getColor()
                            .getString());
                }
                if (plotBand.getId() != null) {
                    target.addAttribute("id", plotBand.getId());
                }
                if (plotBand.getZIndex() != null) {
                    target.addAttribute("zIndex", plotBand.getZIndex());
                }
                writePlotLabel(target, plotBand.getLabel());
                writePlotBandRange(target, plotBand.getRange());

                target.endTag("plotBand");
            }
        }
        target.endTag("plotBands");
    }

    /**
     * Writes configuration attributes of a plotlabel.
     *
     * @param target
     * @param plotLabel
     * @throws PaintException
     */
    private static void writePlotLabel(PaintTarget target, PlotLabel plotLabel)
            throws PaintException {

        target.startTag("label");

        if (plotLabel != null) {
            if (plotLabel.getAlign() != null) {
                target.addAttribute("align", plotLabel.getAlign().getName());
            }
            if (plotLabel.getRotation() != null) {
                target.addAttribute("rotation", plotLabel.getRotation());
            }
            if (plotLabel.getStyle() != null) {
                target.addAttribute("style", plotLabel.getStyle());
            }
            if (plotLabel.getText() != null) {
                target.addAttribute("text", plotLabel.getText());
            }
            if (plotLabel.getTextAlign() != null) {
                target.addAttribute("textAlign", plotLabel.getTextAlign()
                        .getName());
            }
            if (plotLabel.getVertAlign() != null) {
                target.addAttribute("verticalAlign", plotLabel.getVertAlign()
                        .getName());
            }
            if (plotLabel.getX() != null) {
                target.addAttribute("x", plotLabel.getX());
            }
            if (plotLabel.getY() != null) {
                target.addAttribute("y", plotLabel.getY());
            }
        }

        target.endTag("label");
    }

    /**
     * Writes from/to value for a plotband. It considers date and number values
     * separately.
     *
     * @param target
     * @param plotBandRange
     * @throws PaintException
     */
    private static void writePlotBandRange(PaintTarget target,
                                           Range plotBandRange) throws PaintException {
        target.startTag("rangeValue");
        if (plotBandRange != null) {
            if (plotBandRange instanceof NumberRange) {
                target.addAttribute("valueType", "number");
                NumberRange numberRange = (NumberRange) plotBandRange;
                if (numberRange.getFrom() != null) {
                    target.addAttribute("from", numberRange.getFrom());
                }
                if (numberRange.getTo() != null) {
                    target.addAttribute("to", numberRange.getTo());
                }
            } else if (plotBandRange instanceof DateTimeRange) {
                target.addAttribute("valueType", "date");
                DateTimeRange dateRange = (DateTimeRange) plotBandRange;
                target.startTag("from");
                if (dateRange.getFrom() != null) {
                    target.addAttribute("year",
                            getYearFromDate(dateRange.getFrom()));
                    target.addAttribute("month",
                            getMonthFromDate(dateRange.getFrom()));
                    target.addAttribute("day",
                            getDayFromDate(dateRange.getFrom()));
                }
                target.endTag("from");
                target.startTag("to");
                if (dateRange.getTo() != null) {
                    target.addAttribute("year",
                            getYearFromDate(dateRange.getTo()));
                    target.addAttribute("month",
                            getMonthFromDate(dateRange.getTo()));
                    target.addAttribute("day",
                            getDayFromDate(dateRange.getTo()));
                }
                target.endTag("to");
            }
        }
        target.endTag("rangeValue");
    }

    /**
     * Writes configuration attributes of the plotlines
     *
     * @param target
     * @param plotLines
     * @throws PaintException
     */
    private static void writePlotLines(PaintTarget target,
                                       LinkedHashSet<? extends PlotLine> plotLines) throws PaintException {
        target.startTag("plotLines");
        if (plotLines != null) {
            for (PlotLine plotLine : plotLines) {
                target.startTag("plotLine");

                if (plotLine.getColor() != null) {
                    target.addAttribute("color", plotLine.getColor()
                            .getString());
                }
                if (plotLine.getDashStyle() != null) {
                    target.addAttribute("dashStyle", plotLine.getDashStyle()
                            .getName());
                }
                if (plotLine.getId() != null) {
                    target.addAttribute("id", plotLine.getId());
                }
                if (plotLine.getWidth() != null) {
                    target.addAttribute("width", plotLine.getWidth());
                }
                if (plotLine.getZIndex() != null) {
                    target.addAttribute("zIndex", plotLine.getZIndex());
                }
                writePlotLabel(target, plotLine.getLabel());
                writePlotLineValue(target, plotLine.getValue());
                target.endTag("plotLine");
            }
        }
        target.endTag("plotLines");
    }

    /**
     * Writes value of a plotline. It considers date and number value
     * separately.
     *
     * @param target
     * @param plotLineValue
     * @throws PaintException
     */
    private static void writePlotLineValue(PaintTarget target,
                                           Value plotLineValue) throws PaintException {

        target.startTag("lineValue");
        if (plotLineValue != null) {
            if (plotLineValue instanceof NumberValue
                    && ((NumberValue) plotLineValue).getValue() != null) {
                target.addAttribute("valueType", "number");
                target.addAttribute("value",
                        ((NumberValue) plotLineValue).getValue());
            } else if (plotLineValue instanceof DateTimeValue
                    && ((DateTimeValue) plotLineValue).getValue() != null) {
                target.addAttribute("valueType", "date");
                Date date = ((DateTimeValue) plotLineValue).getValue();
                target.addAttribute("year", getYearFromDate(date));
                target.addAttribute("month", getMonthFromDate(date));
                target.addAttribute("day", getDayFromDate(date));
            }
        }
        target.endTag("lineValue");
    }

    /**
     * @param target
     * @param tick
     * @throws PaintException
     */
    private static void writeAxisTick(PaintTarget target, Tick tick)
            throws PaintException {
        writeAxisMinorTick(target, tick);
        if (tick.getPixelInterval() != null) {
            target.addAttribute("tickPixelInterval", tick.getPixelInterval());
        }
        if (tick.getPlacement() != null) {
            target.addAttribute("tickmarkPlacement", tick.getPlacement()
                    .getName());
        }
    }

    /**
     * Writes configuration attributes of an axis. Depending on type of the
     * argument tick, it either writes attributes for {@link MinorTick} or
     * {@link Tick}
     *
     * @param target
     * @param tick
     * @throws PaintException
     */
    private static void writeAxisMinorTick(PaintTarget target, MinorTick tick)
            throws PaintException {

        String attNameColor = "minorTickColor";
        String attNameInterval = "minorTickInterval";
        String attNameLength = "minorTickLength";
        String attNamePosition = "minorTickPosition";
        String attNameWidth = "minorTickWidth";
        if (tick instanceof Tick) {
            attNameColor = "tickColor";
            attNameInterval = "tickInterval";
            attNameLength = "tickLength";
            attNamePosition = "tickPosition";
            attNameWidth = "tickWidth";
        }
        if (tick.getColor() != null) {
            target.addAttribute(attNameColor, tick.getColor().getString());
        }
        if (tick.getInterval() != null) {
            target.addAttribute(attNameInterval, tick.getInterval());
        }
        if (tick.getLength() != null) {
            target.addAttribute(attNameLength, tick.getLength());
        }
        if (tick.getPosition() != null) {
            target.addAttribute(attNamePosition, tick.getPosition().getName());
        }
        if (tick.getWidth() != null) {
            target.addAttribute(attNameWidth, tick.getWidth());
        }
    }

    /**
     * @param target
     * @param grid
     * @throws PaintException
     */
    private static void writeAxisGrid(PaintTarget target, Grid grid)
            throws PaintException {
        writeAxisMinorGrid(target, grid);
    }

    /**
     * Writes configuration attributes of an axis. Depending on type of the
     * argument tick, it either writes attributes for {@link MinorGrid} or
     * {@link Grid}
     *
     * @param target
     * @param grid
     * @throws PaintException
     */
    private static void writeAxisMinorGrid(PaintTarget target, MinorGrid grid)
            throws PaintException {

        String attNameLineColor = "minorGridLineColor";
        String attNameLineWidth = "minorGridLineWidth";
        String attNameLineDashStyle = "minorGridLineDashStyle";
        if (grid instanceof Grid) {
            attNameLineColor = "gridLineColor";
            attNameLineWidth = "gridLineWidth";
            attNameLineDashStyle = "gridLineDashStyle";
        }

        if (grid.getLineColor() != null) {
            target.addAttribute(attNameLineColor, grid.getLineColor()
                    .getString());
        }
        if (grid.getLineWidth() != null) {
            target.addAttribute(attNameLineWidth, grid.getLineWidth());
        }
        if (grid.getLineDashStyle() != null) {
            target.addAttribute(attNameLineDashStyle, grid.getLineDashStyle()
                    .getName());
        }
    }

    /**
     * @param target
     * @param title
     * @throws PaintException
     */
    private static void writeAxisTitle(PaintTarget target, AxisTitle title)
            throws PaintException {
        target.startTag("title");
        if (title != null) {
            if (title.getAlign() != null) {
                target.addAttribute("align", title.getAlign().getName());
            }
            if (title.getMargin() != null) {
                target.addAttribute("margin", title.getMargin());
            }
            if (title.getRotation() != null) {
                target.addAttribute("rotation", title.getRotation());
            }
            if (title.getStyle() != null) {
                target.addAttribute("style", title.getStyle());
            }
            if (title.getText() != null) {
                target.addAttribute("text", title.getText());
            }
        }
        target.endTag("title");
    }

    /**
     * Iteratively processes each x-axis and writes configuration attributes of
     * each axis based on type of the axis e.g. {@link NumberAxis},
     * {@link DateTimeAxis} and {@link CategoryAxis}
     *
     * @param target
     * @param axes
     * @throws PaintException
     */
    public static void writeXAxes(PaintTarget target,
                                  LinkedHashSet<XAxis> axes, InvientChartsConfig config)
            throws PaintException {
        target.startTag("xAxes");

        if (axes != null) {
            for (XAxis xAxis : axes) {
                target.startTag("xAxis");
                writeBaseAxis(target, (AxisBase) xAxis, axes);
                if (xAxis instanceof NumberXAxis) {
                    writeNumberAxis(target, (NumberXAxis) xAxis);
                } else if (xAxis instanceof CategoryAxis) {
                    writeCategoryAxis(target, (CategoryAxis) xAxis);
                } else if (xAxis instanceof DateTimeAxis) {
                    // Check if time should be included as part of a date value.
                    // If any of the datetime series
                    writeDateTimeAxis(
                            target,
                            (DateTimeAxis) xAxis,
                            isIncludeTime((DateTimeAxis) xAxis, config
                                    .getInvientCharts().getAllSeries()));
                }

                target.endTag("xAxis");
            }
        }

        target.endTag("xAxes");
    }

    private static boolean isIncludeTime(DateTimeAxis axis,
                                         LinkedHashSet<Series> chartSeries) {
        for (Series series : chartSeries) {
            if (series instanceof DateTimeSeries && series.getXAxis() == axis) {
                return ((DateTimeSeries) series).isIncludeTime();
            }
        }
        return false;
    }

    /**
     * @param target
     * @param numberAxis
     * @throws PaintException
     */
    private static void writeNumberAxis(PaintTarget target,
                                        NumberAxis numberAxis) throws PaintException {
        if (numberAxis.getAllowDecimals() != null) {
            target.addAttribute("allowDecimals", numberAxis.getAllowDecimals());
        }
        if (numberAxis.getMax() != null) {
            target.addAttribute("max", numberAxis.getMax());
        }
        if (numberAxis.getMin() != null) {
            target.addAttribute("min", numberAxis.getMin());
        }
    }

    /**
     * Returns milliseconds of the date argument dt excluding time.
     *
     * @param dt
     * @return
     */
    public static long getDate(Date dt) {
        return getDate(dt, false);
    }

    /**
     * Returns milliseconds of the date argument dt. If the argument
     * isIncludeTime is false then the returned milliseconds does not include
     * time.
     *
     * @param dt
     * @param isIncludeTime
     * @return
     */
    private static long getDate(Date dt, boolean isIncludeTime) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(dt);
        if (!isIncludeTime) {
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        }
        return cal.getTimeInMillis();
    }

    /**
     * @param target
     * @param dateTimeAxis
     * @throws PaintException
     */
    private static void writeDateTimeAxis(PaintTarget target,
                                          DateTimeAxis dateTimeAxis, boolean isIncludeTime)
            throws PaintException {
        if (dateTimeAxis.getMax() != null) {
            target.addAttribute("max",
                    getDate(dateTimeAxis.getMax(), isIncludeTime));
        }
        if (dateTimeAxis.getMin() != null) {
            target.addAttribute("min",
                    getDate(dateTimeAxis.getMin(), isIncludeTime));
        }
        if (dateTimeAxis.getDateTimeLabelFormat() != null) {
            target.startTag("dateTimeLabelFormats");
            DateTimeLabelFormat dateTimeLabelFormat = dateTimeAxis
                    .getDateTimeLabelFormat();
            if (dateTimeLabelFormat.getMillisecond() != null) {
                target.addAttribute("millisecond", dateTimeAxis
                        .getDateTimeLabelFormat().getMillisecond());
            }
            if (dateTimeLabelFormat.getSecond() != null) {
                target.addAttribute("second", dateTimeAxis
                        .getDateTimeLabelFormat().getSecond());
            }
            if (dateTimeLabelFormat.getMinute() != null) {
                target.addAttribute("minute", dateTimeAxis
                        .getDateTimeLabelFormat().getMinute());
            }
            if (dateTimeLabelFormat.getHour() != null) {
                target.addAttribute("hour", dateTimeAxis
                        .getDateTimeLabelFormat().getHour());
            }
            if (dateTimeLabelFormat.getDay() != null) {
                target.addAttribute("day", dateTimeAxis
                        .getDateTimeLabelFormat().getDay());
            }
            if (dateTimeLabelFormat.getWeek() != null) {
                target.addAttribute("week", dateTimeAxis
                        .getDateTimeLabelFormat().getWeek());
            }
            if (dateTimeLabelFormat.getMonth() != null) {
                target.addAttribute("month", dateTimeAxis
                        .getDateTimeLabelFormat().getMonth());
            }
            if (dateTimeLabelFormat.getYear() != null) {
                target.addAttribute("year", dateTimeAxis
                        .getDateTimeLabelFormat().getYear());
            }
            target.endTag("dateTimeLabelFormats");
        }
    }

    /**
     * @param target
     * @param categoryAxis
     * @throws PaintException
     */
    private static void writeCategoryAxis(PaintTarget target,
                                          CategoryAxis categoryAxis) throws PaintException {
        target.startTag("categories");
        if (categoryAxis.getCategories() != null
                && categoryAxis.getCategories().size() > 0) {
            for (String category : categoryAxis.getCategories()) {
                target.startTag("category");
                target.addAttribute("name", category);
                target.endTag("category");
            }
        }
        target.endTag("categories");
    }

    /**
     * @param target
     * @param axes
     * @throws PaintException
     */
    public static void writeYAxes(PaintTarget target,
                                  LinkedHashSet<YAxis> axes, InvientChartsConfig config)
            throws PaintException {
        target.startTag("yAxes");

        if (axes != null) {
            for (YAxis yAxis : axes) {
                target.startTag("yAxis");
                writeBaseAxis(target, (AxisBase) yAxis, axes);
                if (yAxis instanceof NumberYAxis) {
                    writeNumberAxis(target, (NumberYAxis) yAxis);
                }
                target.endTag("yAxis");
            }
        }

        target.endTag("yAxes");
    }

    /**
     * Writes configuration attributes of the chart labels.
     *
     * @param target
     * @param chartLabel
     * @throws PaintException
     */
    public static void writeChartLabelConfig(PaintTarget target,
                                             ChartLabel chartLabel) throws PaintException {
        target.startTag("labels");

        if (chartLabel != null && chartLabel.getLabels() != null
                && chartLabel.getLabels().size() > 0) {
            if (chartLabel.getStyle() != null) {
                target.addAttribute("style", chartLabel.getStyle());
            }
            target.startTag("items");
            for (ChartLabelItem label : chartLabel.getLabels()) {
                if (label.getHtml() != null || label.getStyle() != null) {
                    target.startTag("item");
                    if (label.getHtml() != null) {
                        target.addAttribute("html", label.getHtml());
                    }
                    if (label.getStyle() != null) {
                        target.addAttribute("style", label.getStyle());
                    }
                    target.endTag("item");
                }
            }
            target.endTag("items");
        }

        target.endTag("labels");
    }

    /**
     * @param date
     * @return Returns year of the argument date.
     */
    private static String getYearFromDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(Calendar.YEAR));
    }

    /**
     * @param date
     * @return Returns month of the argument date. The returned values is based
     *         on zero-index i.e. for month January, the values returned is "0"
     */
    private static String getMonthFromDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(Calendar.MONTH));
    }

    /**
     * @param date
     * @return
     */
    private static String getDayFromDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Writes information about which series were added, removed or updated.
     * This information is used by Vaadin terminal class to decide whether to
     * add a new series or remove/delete an existing series. Basically, this
     * information helps client to update only a portion of the chart instead of
     * full chart.
     *
     * @param target
     * @param seriesCURMap
     * @throws PaintException
     */

    public static void writeChartDataUpdates(PaintTarget target,
                                             LinkedHashMap<String, LinkedHashSet<SeriesCUR>> seriesCURMap)
            throws PaintException {
        for (String seriesName : seriesCURMap.keySet()) {
            LinkedHashSet<SeriesCUR> seriesCURSet = seriesCURMap
                    .get(seriesName);
            if (seriesCURSet != null && seriesCURSet.size() > 0) {
                for (SeriesCUR seriesCUR : seriesCURSet) {
                    target.startTag("seriesDataUpdate");
                    target.addAttribute("seriesName", seriesCUR.getName());
                    target.addAttribute("operation", seriesCUR.getType()
                            .getName());
                    target.addAttribute("isReloadPoints",
                            seriesCUR.isReloadPoints());
                    target.startTag("pointsAdded");
                    if (seriesCUR.getPointsAdded().size() > 0) {
                        writePoints(target, seriesCUR.getPointsAdded());
                    }
                    target.endTag("pointsAdded");
                    target.startTag("pointsRemoved");
                    if (seriesCUR.getPointsRemoved().size() > 0) {
                        writePoints(target, seriesCUR.getPointsRemoved());
                    }
                    target.endTag("pointsRemoved");
                    target.endTag("seriesDataUpdate");
                }
            }
        }
    }

}
