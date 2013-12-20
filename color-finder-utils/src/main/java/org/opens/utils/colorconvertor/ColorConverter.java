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
package org.opens.utils.colorconvertor;

import java.awt.Color;

/**
 *
 * @author alingua
 */
public final class ColorConverter {

    private static final int MAX_COMPONENT = 3;
    private static final int BRIGHTNESS = 2;
    private static final int SATURATION = 1;
    private static final int HUE = 0;
    private static final int R_BEGIN_COLOR = 0;
    private static final int G_BEGIN_COLOR = 2;
    private static final int B_BEGIN_COLOR = 4;
    private static final int RGB_HEXA_LENGTH = 6;
    private static final int RGB_SHORT_HEXA_LENGTH = 3;
    private static final int CONVERT_TO_BASE_16 = 16;
    private static final int MAX_ANGLE = 360;
    private static final String HEXADECIMAL_DICTIONNARY = "[0-9A-Fa-f]+";

    /**
     * Private constructor, utility class
     */
    private ColorConverter() {
    }

    /**
     *
     * @param color
     * @param offsetHue
     * @param offsetSaturation
     * @param offsetBrightness
     * @return
     */
    public static Color offsetHsbColor(Color color, float offsetHue, float offsetSaturation, float offsetBrightness) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float hue, saturation, brightness;

        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbValues);

        // OFFSETING RSB VALUES
        hue = hsbValues[HUE] + offsetHue;
        saturation = hsbValues[SATURATION] + offsetSaturation;
        brightness = hsbValues[BRIGHTNESS] + offsetBrightness;
        return Color.getHSBColor(hue, saturation, brightness);
    }

    /**
     * 
     * @param color
     * @return the brightness of the given color
     */
    public static Float getBrightness(Color color) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float brightness;
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbValues);
        brightness = hsbValues[BRIGHTNESS];
        return brightness;
    }

    /**
     * 
     * @param color
     * @return the saturation of the given color
     */
    public static Float getSaturation(Color color) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float saturation;
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbValues);
        saturation = hsbValues[SATURATION];
        return saturation;
    }

    /**
     * 
     * @param color
     * @return the hue of the given color
     */
    public static Float getHue(Color color) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float hue;
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbValues);
        hue = hsbValues[HUE];
        return hue;
    }

    /**
     *
     * @param bgColor
     * @param offsetRed
     * @param offsetGreen
     * @param offsetBlue
     * @return
     */
    public static Color offsetRgbColor(Color bgColor, int offsetRed, int offsetGreen, int offsetBlue) {
        return new Color(bgColor.getRed() + offsetRed, bgColor.getGreen() + offsetGreen, bgColor.getBlue() + offsetBlue);
    }

    /**
     *
     * @param colorStr
     * @return the RGB Color from hex Color
     */
    public static Color hex2Rgb(String colorStr) {
        if (colorStr.charAt(0) == '#') {
            String str = colorStr.substring(1);
            if (str.matches(HEXADECIMAL_DICTIONNARY)
                    && str.length() == RGB_HEXA_LENGTH) {
                return getNewColor(str);
            } else if (str.matches(HEXADECIMAL_DICTIONNARY)
                    && str.length() == RGB_SHORT_HEXA_LENGTH) {
                return getNewColorShortHexa(str);
            }
        } else if (colorStr.matches(HEXADECIMAL_DICTIONNARY)) {
            if (colorStr.length() == RGB_HEXA_LENGTH) {
                return getNewColor(colorStr);
            } else if (colorStr.length() == RGB_SHORT_HEXA_LENGTH) {
                return getNewColorShortHexa(colorStr);
            }
        }
        return null;
    }

    /**
     * 
     * @param colorStr
     * @return 
     */
    private static Color getNewColorShortHexa(String colorStr) {
        StringBuilder newColor = new StringBuilder();
        newColor.append(colorStr.charAt(0)).append(colorStr.charAt(0))
                .append(colorStr.charAt(1)).append(colorStr.charAt(1))
                .append(colorStr.charAt(2)).append(colorStr.charAt(2));
        return getNewColor(newColor.toString());
    }

    /**
     * 
     * @param colorStr
     * @return 
     */
    private static Color getNewColor(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(R_BEGIN_COLOR, G_BEGIN_COLOR), CONVERT_TO_BASE_16),
                Integer.valueOf(colorStr.substring(G_BEGIN_COLOR, B_BEGIN_COLOR), CONVERT_TO_BASE_16),
                Integer.valueOf(colorStr.substring(B_BEGIN_COLOR, RGB_HEXA_LENGTH), CONVERT_TO_BASE_16));
    }

    /**
     * 
     * @param color
     * @return 
     */
    public static String hex2Rgb(Color color) {
        Integer red = color.getRed();
        Integer green = color.getGreen();
        Integer blue = color.getBlue();
        return ("rgb(" + red.toString() + ", " + green.toString() + ", " + blue.toString() + ")");
    }

    /**
     * 
     * @param color
     * @return 
     */
    public static String rgb2Hex(Color color) {
        return (String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue())).toUpperCase();
    }

    
    private static final int CONSTANT_SL_COMPONENTS_HUNDRED = 100;
    private static final int CONSTANT_S_COMPONENTS_TWO_HUNDRED = 200;
    private static final int CONSTANT_SL_COMPONENTS_TWO = 2;
    private static final int CONSTANT_S_COMPONENTS_FIFTY = 50;
    /**
     * 
     * @param color
     * @return 
     */
    public static String rgb2Hsl(Color color) {
        float[] hsvTab = new float[MAX_COMPONENT];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsvTab);
        float h = hsvTab[HUE] * MAX_ANGLE;
        float l = (CONSTANT_SL_COMPONENTS_TWO - (hsvTab[SATURATION] * CONSTANT_SL_COMPONENTS_HUNDRED) / CONSTANT_SL_COMPONENTS_HUNDRED)
                * (hsvTab[BRIGHTNESS] * CONSTANT_SL_COMPONENTS_HUNDRED) / CONSTANT_SL_COMPONENTS_TWO;
        float s = (hsvTab[SATURATION] * CONSTANT_SL_COMPONENTS_HUNDRED) * (hsvTab[BRIGHTNESS] * CONSTANT_SL_COMPONENTS_HUNDRED)
                / (l < CONSTANT_S_COMPONENTS_FIFTY ? l * CONSTANT_SL_COMPONENTS_TWO : CONSTANT_S_COMPONENTS_TWO_HUNDRED - l * CONSTANT_SL_COMPONENTS_TWO);
        return ("hsl(" + Float.valueOf(h).intValue()
                + ", " + Float.valueOf(s).intValue() + "%"
                + ", " + Float.valueOf(l).intValue() + "%" + ")");
    }
}