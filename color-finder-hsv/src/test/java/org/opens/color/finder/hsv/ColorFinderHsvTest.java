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
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.opens.colorfinder.result.ColorCombinaison;

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

    /**
     * Test of findColors method, of class ColorFinderHsv.
     */
    public void testFindColorsWithFgAndBg() {
        System.out.println("findColors");
        Color foregroundColor = new Color(127, 127, 127);
        Color backgroundColor = new Color(128, 128, 128);
        Float coefficientLevel = 4.5f;
        ColorFinderHsv instance = new ColorFinderHsv();
        instance.findColors(foregroundColor, backgroundColor, true, coefficientLevel);
        for (ColorCombinaison colorCombinaison : instance.getColorResult().getSuggestedColors()) {
            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + colorCombinaison.getContrast());
        }
//        result = instance.findColors(foregroundColor, backgroundColor, false, coefficientLevel);
//        for (ColorResult colorResult : result) {
//            LOGGER.debug("HashCode :" + colorResult.hashCode() + "    COLOR : " + colorResult.getContrast());
//        }
    }
    
    /**
     * Test of findColors method, of class ColorFinderHsv.
     */
    public void testFindColors() {
        System.out.println("findColors");
        Color colorToKeep = new Color(70, 136, 71);
        Color colorToChange = new Color(223, 240, 216);
        Float coefficientLevel = 4.5f;
        ColorFinderHsv instance = new ColorFinderHsv();
//        instance.findColors();
//        for (ColorCombinaison colorCombinaison : instance.getColorResult().getSuggestedColors()) {
//            LOGGER.debug("HashCode :" + colorCombinaison.hashCode() + "    COLOR : " + colorCombinaison.getContrast());
//        }
    }
//    public void testFindColors2() {
//        System.out.println("findColors2");
//        Color colorToChange = new Color(70, 136, 71);
//        Color colorToKeep = new Color(223, 240, 216);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        Collection<ColorResult> result = instance.findColors(colorToChange, colorToKeep, coefficientLevel);
//        for (ColorResult colorResult : result) {
//            LOGGER.debug("Couleur obtenue :" + colorResult.getColor());
//        }
//    }
//    /**
//     * Test of findColors method, of class ColorFinderHsv.
//     */
//    public void testFindColorsThatWorksStrangely1() {
//        System.out.println("FindColorsThatWorksStrangely1");
//        Color colorToKeep = new Color(0, 0, 0);
//        Color colorToChange = new Color(30, 70, 31);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        Collection<ColorResult> result = instance.findColors(colorToChange, colorToKeep, false, coefficientLevel);
//        for (ColorResult colorResult : result) {
//            LOGGER.debug("Couleur obtenue :" + colorResult.getColor());
//        }
//    }
//    
//    /**
//     * Test of findColors method, of class ColorFinderHsv.
//     */
//    public void testFindColorsThatWorksStrangely2() {
//        System.out.println("FindColorsThatWorksStrangely2");
//        Color colorToKeep = new Color(10, 10, 10);
//        Color colorToChange = new Color(30, 70, 31);
//        Float coefficientLevel = 4.5f;
//        ColorFinderHsv instance = new ColorFinderHsv();
//        Collection<ColorResult> result = instance.findColors(colorToChange, colorToKeep, false, coefficientLevel);
//        for (ColorResult colorResult : result) {
//            LOGGER.debug("Couleur obtenue :" + colorResult.getColor());
//        }
//    }
}