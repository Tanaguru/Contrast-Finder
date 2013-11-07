/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.color.finder.hsv;

import java.awt.Color;
import java.util.Collection;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.opens.colorfinder.result.ColorResult;

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
        Collection<ColorResult> result = instance.findColors(foregroundColor, backgroundColor, true, coefficientLevel);
        for (ColorResult colorResult : result) {
            LOGGER.debug("HashCode :" + colorResult.hashCode() + "    COLOR : " + colorResult.getContrast());
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
        Collection<ColorResult> result = instance.findColors(colorToChange, colorToKeep, coefficientLevel);
        //for (ColorResult colorResult : result) {
            //LOGGER.debug("HashCode :" + colorResult.hashCode() + "    COLOR : " + colorResult.getContrast());
        //}
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