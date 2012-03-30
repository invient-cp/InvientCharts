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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Gradient defines a way to fill a shape with a linear color gradient
 * pattern.
 * 
 * @author Invient
 */
public interface Gradient extends Paint {

    public static enum Unit {
        NUMBER("number", ""), PERCENT("percent", "%");
        private String name;
        private String symbol;

        private Unit(String name, String symbol) {
            this.name = name;
            this.symbol = symbol;
        }

        public String getName() {
            return this.name;
        }

        public String getSymbol() {
            return this.symbol;
        }
    }

    /**
     * Returns the x-coordinate of a point at which linear gradient
     *         starts.
     * @return the x-coordinate of a point at which linear gradient
     *         starts.
     */
    public int getxStart();

    /**
     * Returns the unit of x-coordinate of a point at which linear gradient starts.
     * @return the unit of x-coordinate of a point at which linear gradient starts. 
     */
    public Unit getxStartUnit();

    /**
     * Returns the y-coordinate of a point at which linear gradient starts.
     * @return the y-coordinate of a point at which linear gradient starts.
     */
    public int getyStart();

    /**
     * Returns the unit of y-coordinate of a point at which linear gradient starts.
     * @return the unit of y-coordinate of a point at which linear gradient starts. 
     */
    public Unit getyStartUnit();

    /**
     * Returns the x-coordinate of a point at which linear gradient
     *         ends.
     * @return the x-coordinate of a point at which linear gradient
     *         ends.
     */
    public int getxEnd();

    /**
     * Returns the unit of x-coordinate of a point at which linear gradient ends.
     * @return the unit of x-coordinate of a point at which linear gradient ends. 
     */
    public Unit getxEndUnit();

    /**
     * Returns the x-coordinate of a point at which linear gradient ends.
     * @return the x-coordinate of a point at which linear gradient ends.
     */
    public int getyEnd();

    /**
     * Returns the unit of y-coordinate of a point at which linear gradient ends.
     * @return the unit of y-coordinate of a point at which linear gradient ends. 
     */
    public Unit getyEndUnit();

    /**
     * Returns a list of colorstops associated with this gradient.
     * @return a list of colorstops associated with this gradient.
     * @see ColorStop
     * 
     */
    public List<? extends ColorStop> getColorStops();

    /**
     * Represents a stop-value and a color. The stop-value should be in range
     * (0.0-1.0). The color of the gradient at each stop is the color specified
     * for that stop. Between each such stop, the colors and the alpha component
     * will be linearly interpolated over the RGBA.
     * 
     * @author Invient
     * 
     */
    public interface ColorStop extends Serializable {

        public double getStopAt();

        public Unit getStopAtUnit();

        public Color getColor();
    }

    /**
     * Represents linear gradient where points of a linear gradient specify a
     * line. For more details on gradient, refer to CSS 3 gradient
     * documentation.
     * 
     * @author Invient
     * 
     */
    public class LinearGradient implements Gradient {
        private int xStart = 0;
        private Unit xStartUnit = Unit.NUMBER;
        private int yStart = 0;
        private Unit yStartUnit = Unit.NUMBER;

        private int xEnd = 0;
        private Unit xEndUnit = Unit.NUMBER;
        private int yEnd = 0;
        private Unit yEndUnit = Unit.NUMBER;

        private List<LinearColorStop> colorStops = new ArrayList<Gradient.LinearGradient.LinearColorStop>();

        /**
         * Creates a LinearGradient with the specified xStart, xEnd, yStart and
         * yEnd values with default {@link Unit} value number.
         * 
         * @param xStart
         *            the x-coordinate of a point at which linear gradient
         *            starts.
         * @param xEnd
         *            the x-coordinate of a point at which linear gradient ends.
         * @param yStart
         *            the y-coordinate of a point at which linear gradient
         *            starts.
         * @param yEnd
         *            the y-coordinate of a point at which linear gradient ends.
         * @param colorStops
         *            the list of colorstops for the linear gradient.
         */
        public LinearGradient(int xStart, int yStart, int xEnd, int yEnd,
                List<LinearColorStop> colorStops) {
            this(xStart, null, yStart, null, xEnd, null, yEnd, null, colorStops);
        }

        /**
         * 
         * @param xStart
         *            the x-coordinate of a point at which linear gradient
         *            starts.
         * @param xStartUnit
         *            the unit for the xStart value. It can have one of the two
         *            values Unit.NUMBER or Unit.PERCENT. If it is null then the
         *            default value is Unit.NUMBER.
         * @param yStart
         *            the y-coordinate of a point at which linear gradient
         *            starts.
         * @param yStartUnit
         *            the unit for the yStart value. It can have one of the two
         *            values Unit.NUMBER or Unit.PERCENT. If it is null then the
         *            default value is Unit.NUMBER.
         * @param xEnd
         *            the x-coordinate of a point at which linear gradient ends.
         * @param xEndUnit
         *            the unit for the xEnd value. It can have one of the two
         *            values Unit.NUMBER or Unit.PERCENT. If it is null then the
         *            default value is Unit.NUMBER.
         * @param yEnd
         *            the y-coordinate of a point at which linear gradient ends.
         * @param yEndUnit
         *            the unit for the yEnd value. It can have one of the two
         *            values Unit.NUMBER or Unit.PERCENT. If it is null then the
         *            default value is Unit.NUMBER.
         * @param colorStops
         *            the list of colorstops for the linear gradient.
         */
        public LinearGradient(int xStart, Unit xStartUnit, int yStart,
                Unit yStartUnit, int xEnd, Unit xEndUnit, int yEnd,
                Unit yEndUnit, List<LinearColorStop> colorStops) {
            super();
            this.xStart = xStart;
            if (xStartUnit != null) {
                this.xStartUnit = xStartUnit;
            }
            this.yStart = yStart;
            if (yStartUnit != null) {
                this.yStartUnit = yStartUnit;
            }
            this.xEnd = xEnd;
            if (xEndUnit != null) {
                this.xEndUnit = xEndUnit;
            }
            this.yEnd = yEnd;
            if (yEndUnit != null) {
                this.yEndUnit = yEndUnit;
            }
            if (colorStops != null) {
                this.colorStops = colorStops;
            }
        }

        /*
         * (non-Javadoc)
         * @see com.invient.vaadin.charts.Gradient#getxStart()
         */
        @Override
        public int getxStart() {
            return xStart;
        }

        /*
         * (non-Javadoc)
         * @see com.invient.vaadin.charts.Gradient#getxEnd()
         */
        @Override
        public int getxEnd() {
            return xEnd;
        }

        /*
         * (non-Javadoc)
         * @see com.invient.vaadin.charts.Gradient#getyStart()
         */
        @Override
        public int getyStart() {
            return yStart;
        }

        /*
         * (non-Javadoc)
         * @see com.invient.vaadin.charts.Gradient#getyEnd()
         */
        @Override
        public int getyEnd() {
            return yEnd;
        }

        /*
         * (non-Javadoc)
         * @see com.invient.vaadin.charts.Gradient#getxStartUnit()
         */
        @Override
        public Unit getxStartUnit() {
            return this.xStartUnit;
        }

        /*
         * (non-Javadoc)
         * @see com.invient.vaadin.charts.Gradient#getyStartUnit()
         */
        @Override
        public Unit getyStartUnit() {
            return this.yStartUnit;
        }

        /*
         * (non-Javadoc)
         * @see com.invient.vaadin.charts.Gradient#getxEndUnit()
         */
        @Override
        public Unit getxEndUnit() {
            return this.xEndUnit;
        }

        /*
         * (non-Javadoc)
         * @see com.invient.vaadin.charts.Gradient#getyEndUnit()
         */
        @Override
        public Unit getyEndUnit() {
            return this.yEndUnit;
        }

        /*
         * (non-Javadoc)
         * @see com.invient.vaadin.charts.Gradient#getColorStops()
         */
        @Override
        public List<LinearColorStop> getColorStops() {
            return colorStops;
        }

        /**
         * Represents stop-value and color for the {@link LinearGradient}
         * 
         * @author Invient
         * 
         */
        public static class LinearColorStop implements ColorStop {
            private double stopAt;
            private Unit stopAtUnit = Unit.NUMBER;
            private Color color;

            /**
             * 
             * @param stopAt
             * @param color
             */
            public LinearColorStop(double stopAt, Color color) {
                super();
                this.stopAt = stopAt;
                this.color = color;
            }

            /**
             * 
             * @param stopAt
             * @param stopAtUnit
             * @param color
             */
            public LinearColorStop(double stopAt, Unit stopAtUnit, Color color) {
                super();
                this.stopAt = stopAt;
                if (stopAtUnit != null) {
                    this.stopAtUnit = stopAtUnit;
                }
                this.color = color;
            }

            @Override
            public double getStopAt() {
                return stopAt;
            }

            @Override
            public Unit getStopAtUnit() {
                return stopAtUnit;
            }

            @Override
            public Color getColor() {
                return color;
            }

        }

        /**
         * @return Returns string representation of this LinearGradient
         */
        @Override
        public String getString() {
            StringBuilder sb = new StringBuilder();
            // The prefix "JSOBJ:" indicates that the string is a JavaScript
            // object
            String x1 = "'" + this.xStart + this.xStartUnit.getSymbol() + "'";
            String y1 = "'" + this.yStart + this.yStartUnit.getSymbol() + "'";
            String x2 = "'" + this.xEnd + this.xEndUnit.getSymbol() + "'";
            String y2 = "'" + this.yEnd + this.yEndUnit.getSymbol() + "'";

            sb.append("JSOBJ:{");
            sb.append(" linearGradient: [" + x1 + "," + y1 + "," + x2 + ","
                    + y2 + "],");
            sb.append(" stops: [");
            int count = 0;
            for (LinearColorStop colorStop : this.colorStops) {
                if (colorStop.getColor() != null) {
                    String stopAt = "'" + colorStop.getStopAt() + colorStop.getStopAtUnit().getSymbol() + "'";
                    String stopColor =  "'" + colorStop.getColor().getString() + "'";
                    if (count > 0) {
                        sb.append(",");
                    }
                    sb.append("[" + stopAt + ", "
                            + stopColor + "]");
                    count++;
                }
            }
            sb.append(" ]");
            sb.append("}");
            
            return sb.toString();
        }
    }

}
