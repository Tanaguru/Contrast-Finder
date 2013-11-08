/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
        Color result = ColorConverter.offsetHsbColor(bgColor, 0.1f, 0, 0);
        System.out.println(result.toString());
        System.out.println("Hue : " + result);
        // TODO review the generated test code and remove the default call to fail.
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
//    /**
//     * Test of hex2Rgb method, of class ColorConverter.
//     */
//    public void testHex2Rgb() {
//        System.out.println("hex2Rgb");
//        String colorStr = "";
//        Color expResult = null;
//        Color result = ColorConverter.hex2Rgb(colorStr);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
