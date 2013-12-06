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
package org.opens.color.finder.hsv;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.opens.colorfinder.result.ColorCombinaison;
import org.opens.colorfinder.result.ColorCombinaisonImpl;

/**
 *
 * @author alingua
 */
public class ColorFinderHsvTest extends TestCase {

    private static final Logger LOGGER = Logger.getLogger(ColorFinderHsvTest.class);

    public ColorFinderHsvTest(String testName) {
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
//
//    /**
//     * Test of findColors method, of class ColorFinderHsv.
//     */
//    public void testFindColorsWithFgAndBg() {
//        System.out.println("FindColorsWithFgAndBg");
//        Color foregroundColor = new Color(127, 127, 127);
//        Color backgroundColor = new Color(128, 128, 128);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        instance.findColors(foregroundColor, backgroundColor, true, coefficientLevel);
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            LOGGER.debug("HashCode :" + combinaisons.hashCode() + "    COLOR : " + combinaisons.getContrast());
//            colorCombinaison.add(combinaisons);
//        }
//        int sizeList = colorCombinaison.size();
//        Color firstColor = new Color(2, 2, 2);
//        assertEquals(firstColor, colorCombinaison.get(0).getColor());
//        Color mediumColor = new Color(28, 10, 10);
//        assertEquals(mediumColor, colorCombinaison.get(4).getColor());
//        Color endColor = new Color(28, 3, 3);
//        assertEquals(endColor, colorCombinaison.get(sizeList - 1).getColor());
//    }
//
//    public void testFindColorsWithFgAndBg2() {
//        System.out.println("FindColorsWithFgAndBg2");
//        Color foregroundColor = new Color(127, 127, 127);
//        Color backgroundColor = new Color(128, 128, 128);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.findColors(foregroundColor, backgroundColor, false, coefficientLevel);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + combinaisons.getContrast());
//            colorCombinaison.add(combinaisons);
//        }
//        int sizeList = colorCombinaison.size();
//        Color firstColor = new Color(1, 1, 1);
//        assertEquals(firstColor, colorCombinaison.get(0).getColor());
//        Color mediumColor = new Color(27, 15, 15);
//        assertEquals(mediumColor, colorCombinaison.get(4).getColor());
//        Color endColor = new Color(52, 4, 4);
//        assertEquals(endColor, colorCombinaison.get(sizeList - 1).getColor());
//    }
//
//    public void testFindColorsBornBlack() {
//        System.out.println("FindColorsBornBlack");
//        Color foregroundColor = new Color(1, 1, 1);
//        Color backgroundColor = new Color(3, 3, 3);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.findColors(foregroundColor, backgroundColor, true, coefficientLevel);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + combinaisons.getContrast());
//            colorCombinaison.add(combinaisons);
//        }
//        Color firstColor = new Color(133, 133, 133);
//        assertEquals(firstColor, colorCombinaison.get(0).getColor());
//    }
//
//    public void testFindColorsBornBlack2() {
//        System.out.println("FindColorsBornBlack2");
//        Color foregroundColor = new Color(1, 1, 1);
//        Color backgroundColor = new Color(3, 3, 3);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.findColors(foregroundColor, backgroundColor, false, coefficientLevel);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + combinaisons.getContrast());
//            colorCombinaison.add(combinaisons);
//        }
//        Color firstColor = new Color(131, 131, 131);
//        assertEquals(firstColor, colorCombinaison.get(0).getColor());
//    }
//
//    public void testFindColorsBornWhite() {
//        System.out.println("FindColorsBornWhite");
//        Color foregroundColor = new Color(254, 254, 254);
//        Color backgroundColor = new Color(255, 255, 255);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.findColors(foregroundColor, backgroundColor, true, coefficientLevel);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + combinaisons.getContrast());
//            colorCombinaison.add(combinaisons);
//        }
//        int sizeList = colorCombinaison.size();
//        Color firstColor = new Color(105, 105, 105);
//        assertEquals(firstColor, colorCombinaison.get(0).getColor());
//        Color mediumColor = new Color(155, 94, 94);
//        assertEquals(mediumColor, colorCombinaison.get(4).getColor());
//        Color endColor = new Color(230, 1, 1);
//        assertEquals(endColor, colorCombinaison.get(sizeList - 1).getColor());
//    }
//
//    public void testFindColorsBornWhite2() {
//        System.out.println("FindColorsBornWhite2");
//        Color foregroundColor = new Color(254, 254, 254);
//        Color backgroundColor = new Color(255, 255, 255);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.findColors(foregroundColor, backgroundColor, false, coefficientLevel);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + combinaisons.getContrast());
//            colorCombinaison.add(combinaisons);
//        }
//        int sizeList = colorCombinaison.size();
//        Color firstColor = new Color(104, 104, 104);
//        assertEquals(firstColor, colorCombinaison.get(0).getColor());
//        Color mediumColor = new Color(154, 93, 93);
//        assertEquals(mediumColor, colorCombinaison.get(4).getColor());
//        Color endColor = new Color(229, 4, 4);
//        assertEquals(endColor, colorCombinaison.get(sizeList - 1).getColor());
//    }
//
//    /**
//     * Test of findColors method, of class ColorFinderHsv.
//     */
//    public void testFindColorsGreenAlert() {
//        System.out.println("FindColorsGreenAlert");
//        Color foregroundColor = new Color(70, 136, 71);
//        Color backgroundColor = new Color(223, 240, 216);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.findColors(foregroundColor, backgroundColor, true, coefficientLevel);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + combinaisons.getContrast());
//            colorCombinaison.add(combinaisons);
//        }
//        int sizeList = colorCombinaison.size();
//        Color firstColor = new Color(8, 15, 6);
//        assertEquals(firstColor, colorCombinaison.get(0).getColor());
//        Color mediumColor = new Color(4, 15, 0);
//        assertEquals(mediumColor, colorCombinaison.get(4).getColor());
//        Color endColor = new Color(0, 8, 15);
//        assertEquals(endColor, colorCombinaison.get(sizeList - 1).getColor());
//    }
//
//    public void testFindColorsGreenAlert2() {
//        System.out.println("FindColorsGreenAlert2");
//        Color foregroundColor = new Color(70, 136, 71);
//        Color backgroundColor = new Color(223, 240, 216);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.findColors(foregroundColor, backgroundColor, false, coefficientLevel);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + combinaisons.getContrast());
//            colorCombinaison.add(combinaisons);
//        }
//        int sizeList = colorCombinaison.size();
//        Color firstColor = new Color(57, 111, 58);
//        assertEquals(firstColor, colorCombinaison.get(0).getColor());
//        Color mediumColor = new Color(79, 86, 79);
//        assertEquals(mediumColor, colorCombinaison.get(4).getColor());
//        Color endColor = new Color(136, 70, 133);
//        assertEquals(endColor, colorCombinaison.get(sizeList - 1).getColor());
//    }
//
//    public void testFindColorsNearColor() {
//        System.out.println("FindColorsNearColor");
//        Color foregroundColor = new Color(1, 0, 1);
//        Color backgroundColor = new Color(0, 0, 0);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.getColorFinderKey();
//        instance.findColors(foregroundColor, backgroundColor, false, coefficientLevel);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + combinaisons.getContrast());
//            colorCombinaison.add(combinaisons);
//        }
//        int sizeList = colorCombinaison.size();
//        Color firstColor = new Color(209, 0, 209);
//        assertEquals(firstColor, colorCombinaison.get(0).getColor());
//        Color endColor = new Color(235, 0, 0);
//        assertEquals(endColor, colorCombinaison.get(sizeList - 1).getColor());
//    }

    public void testFindColorsNearColor() {
        System.out.println("FindColorsNearColor");
        Color foregroundColor = new Color(255, 165, 0);
        Color backgroundColor = new Color(255, 255, 255);
        Float coefficientLevel = 4.5f;
        ColorFinderHsv instance = new ColorFinderHsv();
        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
        instance.getColorFinderKey();
        instance.findColors(foregroundColor, backgroundColor, false, coefficientLevel);
        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + combinaisons.getContrast());
            colorCombinaison.add(combinaisons);
        }
    }
//    public void testFindColorsAllCombinaisonsPossible() {
//        System.out.println("FindColorsAllCombinaisonsPossible");
//        int i = 0, j = 0, k = 0, cpt = 0;
//        int testCounter=  0;
//        while (i < 255) {
//            cpt += computeR(i, j, k, 4.5f);
//            if (testCounter % 10000 == 0) {
//                LOGGER.debug("##########################");
//                LOGGER.debug("garbage 10000");
//                System.gc();
//            }
//            testCounter++;
////            LOGGER.debug("Test Number " + testCounter + " Compute Red : " + cpt);
//            j = 0;
//            while (j < 255) {
//                cpt += computeG(i, j, k, 4.5f);
//                if (testCounter % 10000 == 0) {
//                    LOGGER.debug("##########################");
//                    LOGGER.debug("garbage 10000");
//                    System.gc();
//                }
//                testCounter++;
////                LOGGER.debug("Test Number " + testCounter + " Compute Green : " + cpt);
//                k = 0;
//                while (k < 255) {
//                    cpt += computeB(i, j, k, 4.5f);
//                    testCounter++;
//                    if (testCounter % 10000 == 0) {
//                        LOGGER.debug("##########################");
//                        LOGGER.debug("garbage 10000");
//                        System.gc();
//                    }
////                    LOGGER.debug("Test Number " + testCounter + " Compute Blue : " + cpt);
//                    k++;
//                }
//                j++;
//            }
//            i++;
//        }
//        LOGGER.debug("Total de couleurs : " + cpt);
//    }
//
//    public int computeR(int r, int g, int b, float cl) {
//        //System.out.println("computeR");                                                                                                                                                                        
//        Color foregroundColor = new Color(r, g, b);
////        LOGGER.debug("Changed color Red: " + foregroundColor);
//        Color backgroundColor = new Color(0, 0, 0);
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.getColorFinderKey();
//        instance.findColors(foregroundColor, backgroundColor, true, cl);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            colorCombinaison.add(combinaisons);
//        }
////        LOGGER.debug(colorCombinaison.size() + "colors found");
//        return colorCombinaison.size();
//    }
//
//    public int computeG(int r, int g, int b, float cl) {
//        //System.out.println("computeG");                                                                                                                                                                        
//        Color foregroundColor = new Color(r, g, b);
////        LOGGER.debug("Changed color Green: " + foregroundColor);
//        Color backgroundColor = new Color(0, 0, 0);
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.getColorFinderKey();
//        instance.findColors(foregroundColor, backgroundColor, true, cl);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            colorCombinaison.add(combinaisons);
//        }
////        LOGGER.debug(colorCombinaison.size() + "colors found");
//        return colorCombinaison.size();
//    }
//
//    public int computeB(int r, int g, int b, float cl) {
//        //System.out.println("computeB");                                                                                                                                                                        
//        Color foregroundColor = new Color(r, g, b);
////        LOGGER.debug("Changed color Blue: " + foregroundColor);
//        Color backgroundColor = new Color(0, 0, 0);
//        ColorFinderHsv instance = new ColorFinderHsv();
//        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
//        instance.getColorFinderKey();
//        instance.findColors(foregroundColor, backgroundColor, true, cl);
//        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
//            colorCombinaison.add(combinaisons);
//        }
////        LOGGER.debug(colorCombinaison.size() + "colors found");
//        return colorCombinaison.size();
//    }

    public void testGetColorFinderKey() {
        System.out.println("Key");
        ColorFinderHsv instance = new ColorFinderHsv();
        String result = instance.getColorFinderKey();
        assertEquals("HSV".toString(), result);
    }
}