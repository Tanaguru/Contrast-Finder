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
package org.opens.utils.contrastchecker;

import java.awt.Color;

/**
 *
 * @author alingua
 */
public final class ContrastChecker {
    /* */

    private static final double RED_FACTOR = 0.2126;
    /* */
    private static final double GREEN_FACTOR = 0.7152;
    /* */
    private static final double BLUE_FACTOR = 0.0722;
    /* */
    private static final double CONTRAST_FACTOR = 0.05;
    /* */
    private static final double RGB_MAX_VALUE = 255;
    /* */
    private static final double RSGB_FACTOR = 0.03928;
    /* */
    private static final double LUMINANCE_INF = 12.92;
    /* */
    private static final double LUMINANCE_SUP_CONST = 0.055;
    /* */
    private static final double LUMINANCE_SUP_CONST2 = 1.055;
    /* */
    private static final double LUMINANCE_EXP = 2.4;
    /* */
    private static final int ROUND_VALUE = 100000;

    /**
     *
     */
    private ContrastChecker() {
    }

    public static double distanceColor(final Color fgColor, final Color bgColor) {
        int redFg = fgColor.getRed();
        int redBg = bgColor.getRed();
        int greenBg = bgColor.getGreen();
        int greenFg = fgColor.getGreen();
        int blueFg = fgColor.getBlue();
        int blueBg = bgColor.getBlue();
        return (Math.sqrt(Math.pow(redFg - redBg, 2) + Math.pow(greenFg - greenBg, 2) + Math.pow(blueFg - blueBg, 2)));
    }

    /**
     *
     * @param fgColor
     * @param bgColor
     * @param coefficientLevel
     * @return
     */
    public static boolean isContrastValid(final Color fgColor, final Color bgColor, Float coefficientLevel) {
        return getConstrastRatio(fgColor, bgColor) > coefficientLevel;
    }

    /**
     * This method computes the contrast ratio between 2 colors. It needs to
     * determine which one is lighter first.
     *
     * @param fgColor
     * @param bgColor
     * @return the contrast ratio between the 2 colors
     */
    public static double getConstrastRatio(final Color fgColor, final Color bgColor) {
        double fgLuminosity = getLuminosity(fgColor);
        double bgLuminosity = getLuminosity(bgColor);
        if (fgLuminosity > bgLuminosity) {
            return computeContrast(fgLuminosity, bgLuminosity);
        } else {
            return computeContrast(bgLuminosity, fgLuminosity);
        }
    }

    public static double getConstrastRatio5DigitRound(final Color fgColor, final Color bgColor) {
        double fgLuminosity = getLuminosity(fgColor);
        double bgLuminosity = getLuminosity(bgColor);
        if (fgLuminosity > bgLuminosity) {
            return (double) Math.round(computeContrast(fgLuminosity, bgLuminosity) * ROUND_VALUE) / ROUND_VALUE;
        } else {
            return (double) Math.round(computeContrast(bgLuminosity, fgLuminosity) * ROUND_VALUE) / ROUND_VALUE;
        }
    }

    /**
     *
     * @param lighter
     * @param darker
     * @return
     */
    private static double computeContrast(double lighter, double darker) {
        return (Double.valueOf(((lighter + CONTRAST_FACTOR) / (darker + CONTRAST_FACTOR))));
    }

    /**
     *
     * @param color
     * @return
     */
    public static double getLuminosity(Color color) {
        double luminosity =
                getComposantValue(color.getRed()) * RED_FACTOR
                + getComposantValue(color.getGreen()) * GREEN_FACTOR
                + getComposantValue(color.getBlue()) * BLUE_FACTOR;

        return luminosity;
    }

    /**
     *
     * @param composant
     * @return
     */
    private static double getComposantValue(double composant) {
        double rsgb = composant / RGB_MAX_VALUE;
        if (rsgb <= RSGB_FACTOR) {
            return rsgb / LUMINANCE_INF;
        } else {
            return Math.pow(((rsgb + LUMINANCE_SUP_CONST) / LUMINANCE_SUP_CONST2), LUMINANCE_EXP);
        }
    }
}