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
import junit.framework.TestCase;

/**
 *
 * @author alingua
 */
public class ColorConverterTest extends TestCase {
    
    public ColorConverterTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of offsetHsbColor method, of class ColorConverter.
     */
//    public void testOffsetHsbColor() {
//        System.out.println("offsetHsbColor");
//        Color bgColor = null;
//        float offsetHue = 0.0F;
//        float offsetSaturation = 0.0F;
//        float offsetBrightness = 0.0F;
//        Color expResult = null;
//        Color result = ColorConverter.offsetHsbColor(bgColor, offsetHue, offsetSaturation, offsetBrightness);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setBrightnessToColor method, of class ColorConverter.
//     */
//    public void testSetBrightnessToColor() {
//        System.out.println("setBrightnessToColor");
//        Float Brightness = null;
//        Color color = null;
//        Color expResult = null;
//        Color result = ColorConverter.setBrightnessToColor(Brightness, color);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getBrightness method, of class ColorConverter.
//     */
//    public void testGetBrightness() {
//        System.out.println("getBrightness");
//        Color bgColor = null;
//        Float expResult = null;
//        Float result = ColorConverter.getBrightness(bgColor);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSaturation method, of class ColorConverter.
//     */
//    public void testGetSaturation() {
//        System.out.println("getSaturation");
//        Color bgColor = null;
//        Float expResult = null;
//        Float result = ColorConverter.getSaturation(bgColor);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getHue method, of class ColorConverter.
     */
    public void testGetHue() {
        System.out.println("getHue");
        Color bgColor = new Color(128, 127, 127);
        Color result = ColorConverter.offsetHsbColor(bgColor, Float.valueOf(0.1f), Float.valueOf(0.0f), Float.valueOf(0.0f));
        System.out.println(result.toString());
        System.out.println("Hue : " + result);
    }
//
//    /**
//     * Test of offsetRgbColor method, of class ColorConverter.
//     */
//    public void testOffsetRgbColor() {
//        System.out.println("offsetRgbColor");
//        Color bgColor = null;
//        int offsetRed = 0;
//        int offsetGreen = 0;
//        int offsetBlue = 0;
//        Color expResult = null;
//        Color result = ColorConverter.offsetRgbColor(bgColor, offsetRed, offsetGreen, offsetBlue);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of hex2Rgb method, of class ColorConverter.
     */
//    public void testHex2Rgb() {
//        System.out.println("hex2Rgb");
//        String colorStr = "";
//        Color expResult = null;
//        Color result = ColorConverter.hex2Rgb(colorStr);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    public void testRgb2hexBlack() {
        System.out.println("Rgb2hexBlack");
        Color color = Color.BLACK;
        String expResult = "#000000";
        String result = ColorConverter.rgb2Hex(color);
        assertEquals(expResult, result);
    }
    
    public void testRgb2hexWhite() {
        System.out.println("Rgb2hexWhite");
        Color color = Color.WHITE;
        String expResult = "#FFFFFF";
        String result = ColorConverter.rgb2Hex(color);
        assertEquals(expResult, result);
    }
    
    public void testRgb2hexPink() {
        System.out.println("Rgb2hexPink");
        Color color = Color.PINK;
        String expResult = "#FFAFAF";
        String result = ColorConverter.rgb2Hex(color);
        assertEquals(expResult, result);
    }
    
}
