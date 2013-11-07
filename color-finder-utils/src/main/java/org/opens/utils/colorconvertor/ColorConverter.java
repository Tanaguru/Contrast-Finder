/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.utils.colorconvertor;

import java.awt.Color;

/**
 *
 * @author alingua
 */
public final class ColorConverter  {
    
    private static final int MAX_COMPONENT = 3;
    private static final int BRIGHTNESS = 2;
    private static final int SATURATION = 1;
    private static final int HUE = 0;
    
    private static final int R_BEGIN_COLOR = 0;
    private static final int G_BEGIN_COLOR = 2;
    private static final int B_BEGIN_COLOR = 4;
    private static final int RGB_HEXA_LENGTH = 6;
    private static final int CONVERT_TO_BASE_16 = 16;
    private static final String HEXADECIMAL_DICTIONNARY = "[0-9A-Fa-f]+";
    
    /**
     * Private constructor, utility class
     */
    private ColorConverter(){}
    
    /**
     * 
     * @param bgColor
     * @param offsetHue
     * @param offsetSaturation
     * @param offsetBrightness
     * @return 
     */
    public static Color offsetHsbColor(Color bgColor, float offsetHue, float offsetSaturation, float offsetBrightness) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float hue, saturation, brightness;
        
        Color.RGBtoHSB(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), hsbValues);
        
        // OFFSETING RSB VALUES
        hue = hsbValues[HUE] + offsetHue;
        saturation = hsbValues[SATURATION] + offsetSaturation;
        brightness = hsbValues[BRIGHTNESS] + offsetBrightness;
        
        return Color.getHSBColor(hue, saturation, brightness);
    }

    public static Color setBrightnessToColor(Float Brightness, Color color) {
        int col = Color.HSBtoRGB(getHue(color), getSaturation(color), Brightness);
        return new Color(col);
    }
    
    public static Float getBrightness(Color bgColor) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float brightness;
        Color.RGBtoHSB(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), hsbValues);
        brightness = hsbValues[BRIGHTNESS];
        return brightness;
    }
    
    public static Float getSaturation(Color bgColor) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float saturation;
        Color.RGBtoHSB(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), hsbValues);
        saturation = hsbValues[SATURATION];
        return saturation;
    }

    public static Float getHue(Color bgColor) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float hue;
        Color.RGBtoHSB(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), hsbValues);
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
        return new Color (bgColor.getRed() + offsetRed, bgColor.getGreen() + offsetGreen, bgColor.getBlue() + offsetBlue);
    }
    
    /**
     *
     * @param colorStr
     * @return the RGB Color from hex Color
     */
    public static Color hex2Rgb(String colorStr) {
        String str = colorStr.substring(1);
        if (str.matches(HEXADECIMAL_DICTIONNARY)
                && str.length() == RGB_HEXA_LENGTH
                && colorStr.charAt(0) == '#') {
            return new Color(
                    Integer.valueOf(str.substring(R_BEGIN_COLOR, G_BEGIN_COLOR), CONVERT_TO_BASE_16),
                    Integer.valueOf(str.substring(G_BEGIN_COLOR, B_BEGIN_COLOR), CONVERT_TO_BASE_16),
                    Integer.valueOf(str.substring(B_BEGIN_COLOR, RGB_HEXA_LENGTH), CONVERT_TO_BASE_16));
        }
        return null;
    }
}