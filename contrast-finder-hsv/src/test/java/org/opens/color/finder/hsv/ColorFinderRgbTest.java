/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.color.finder.hsv;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.opens.colorfinder.result.ColorCombinaison;

/**
 *
 * @author alingua
 */
public class ColorFinderRgbTest extends TestCase {

    private static final Logger LOGGER = Logger.getLogger(ColorFinderRgbTest.class);

    public ColorFinderRgbTest(String testName) {
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

    public void testFindColorsNearColorOrange() {
        System.out.println("FindColorsNearColor");
        Color foregroundColor = new Color(255, 255, 255);
        Color backgroundColor = new Color(255, 192, 7);
        Float coefficientLevel = 3.0f;
        ColorFinderRgb instance = new ColorFinderRgb();
        List<ColorCombinaison> colorCombinaison = new ArrayList<ColorCombinaison>();
        instance.findColors(foregroundColor, backgroundColor, true, coefficientLevel);
        LOGGER.info(instance.getColorResult().getSuggestedColors().size());
        for (ColorCombinaison combinaisons : instance.getColorResult().getSuggestedColors()) {
            LOGGER.info("Color found " + combinaisons.getHexaColor());
        }
    }

    /**
     * Test of getColorFinderKey method, of class ColorFinderRgb.
     */
    public void testGetColorFinderKey() {
        System.out.println("Key");
        ColorFinderHsv instance = new ColorFinderHsv();
        String result = instance.getColorFinderKey();
    }
}
