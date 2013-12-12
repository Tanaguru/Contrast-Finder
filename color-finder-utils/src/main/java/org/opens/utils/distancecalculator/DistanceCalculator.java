/*
 * Contrast Finder
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.utils.distancecalculator;

import java.awt.Color;

public final class DistanceCalculator {

    private static final int CUBIC = 3;
    private static final int ROUND_VALUE = 100;
    
    private DistanceCalculator() {
    }

    /**
     * 
     * @param colorToChange
     * @param colorToKeep
     * @return the calculated distance between 2 colors regarding the 
     * distance definition that can be found here 
     * http://en.wikipedia.org/wiki/Euclidean_distance#Three_dimensions
     */
    public static double calculate(Color colorToChange, Color colorToKeep) {
        return (double) Math.round(Math.abs((Math.cbrt(Math.pow(Double.valueOf(colorToChange.getRed()) - Double.valueOf(colorToKeep.getRed()), CUBIC)
                + Math.pow(Double.valueOf(colorToChange.getGreen()) - Double.valueOf(colorToKeep.getGreen()), CUBIC)
                + Math.pow(Double.valueOf(colorToChange.getBlue()) - Double.valueOf(colorToKeep.getBlue()), CUBIC)))) * ROUND_VALUE) / ROUND_VALUE;
    }
}
