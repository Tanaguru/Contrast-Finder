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
import java.util.Collection;
import java.util.HashSet;
import org.apache.log4j.Logger;
import org.opens.colorfinder.AbstractColorFinder;
import org.opens.colorfinder.result.factory.ColorCombinaisonFactoryImpl;
import org.opens.colorfinder.result.factory.ColorResultFactoryImpl;
import org.opens.utils.colorconvertor.ColorConverter;
import org.opens.utils.contrastchecker.ContrastChecker;

/**
 *
 * @author alingua
 */
public class ColorFinderHsv extends AbstractColorFinder {

    private static final Logger LOGGER = Logger.getLogger(ColorFinderHsv.class);
    private static final float STEP_BRIGHTNESS = 0.1f;
    private static final float STEP_SATURATION = 0.1f;
    private static final float STEP_HUE = 0.1f;
    private static final float NO_CHANGE_COMPONENT = 0.0f;
    private static final float MAX_POSSIBLE_VALUE = 1.0f;
    private static final float MIN_POSSIBLE_VALUE = 0.0f;
    private static final int MAX_RESULT_ONE_HUE = 20;
    private Collection<Color> testedColorsFromHueRound;
    private int testedColors;

    /**
     * Constructor
     */
    public ColorFinderHsv() {
        super(new ColorResultFactoryImpl(), new ColorCombinaisonFactoryImpl());
        LOGGER.info("instanciation of ColorFinderHsv class");
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param coefficientLevel
     * @return
     */
    @Override
    protected void findColors() {
        LOGGER.info("Appel de findColors, couleurs : " + getColorToKeep() + getColorResult().getSubmittedCombinaisonColor().getColor() + getColorResult().getNumberOfSuggestedColors());
        testedColorsFromHueRound = new HashSet<Color>();

        Color colorToModify = getColorResult().getSubmittedCombinaisonColor().getColor();

        testedColors = 0;
        changeHue(colorToModify, false);
        changeHue(colorToModify, true);

        getColorResult().setNumberOfTestedColors(testedColors);
        LOGGER.debug("Size of Color list : " + getColorResult().getSuggestedColors().size());
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param increment
     */
    private void changeHue(final Color colorToChange, boolean increment) {
        float offset;
        if (increment) {
            offset = STEP_HUE;
        } else {
            offset = -STEP_HUE;
        }

        Color newColor = colorToChange;
        int initialResultSize = getColorResult().getNumberOfSuggestedColors();
        float currentHue = ColorConverter.getHue(newColor);
        boolean testNextColor = true;
        while (testNextColor) {
            testedColorsFromHueRound.add(newColor);
            LOGGER.debug("changeHue New Color  " + newColor.hashCode() + " " + newColor.toString());
            // the return is not used here, we just want to add the current color
            // in the result collection in case of its contrast is valid
            isNewColorValid(newColor);
            if (getColorResult().getNumberOfSuggestedColors() - initialResultSize < MAX_RESULT_ONE_HUE) {
                changeSaturation(newColor, false);
                changeSaturation(newColor, true);
                if (isNextColorBounded(currentHue, offset)) {
                    Color offsetColor = ColorConverter.offsetHsbColor(newColor,
                            offset,
                            NO_CHANGE_COMPONENT,
                            NO_CHANGE_COMPONENT);
                    currentHue = ColorConverter.getHue(offsetColor);
                    LOGGER.debug("offsetColor New Color  " + offsetColor.hashCode() + " " + offsetColor.toString());
                    if (!testedColorsFromHueRound.contains(offsetColor)) {
                        newColor = offsetColor;
                    } else {
                        // the offset never has impact on the color
                        testNextColor = false;
                    }
                } else {
                    // the next color is out of bound
                    testNextColor = false;
                }
            } else {
                // the maximum number of found color is reached for the current "changeHue" round
                testNextColor = false;
            }
        }
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param increment
     */
    private void changeSaturation(final Color colorToChange, boolean increment) {
        LOGGER.debug("start saturation change : " + increment + " " + colorToChange);
        float offset;
        if (increment) {
            offset = STEP_SATURATION;
        } else {
            offset = -STEP_SATURATION;
        }
        Color newColor = colorToChange;
        float currentSaturation = ColorConverter.getSaturation(newColor);
        boolean testNextColor = true;
        while (testNextColor) {
            if (isNewColorValid(newColor)) {
                testNextColor = false;
            }
            if (isNextColorBounded(currentSaturation, offset)) {
                Color offsetColor = ColorConverter.offsetHsbColor(newColor,
                        NO_CHANGE_COMPONENT,
                        offset,
                        NO_CHANGE_COMPONENT);
                changeBrightness(newColor, false);
                changeBrightness(newColor, true);
                currentSaturation = ColorConverter.getSaturation(newColor);
                LOGGER.debug("offset color :  " + offsetColor);
                if (!newColor.equals(offsetColor)) {
                    newColor = offsetColor;
                } else {
                    testNextColor = false;
                }
            } else {
                testNextColor = false;
            }
        }
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param increment
     */
    private void changeBrightness(final Color colorToChange, boolean increment) {
        float offset;
        if (increment) {
            offset = STEP_BRIGHTNESS;
        } else {
            offset = -STEP_BRIGHTNESS;
        }
        Color newColor = colorToChange;
        float currentBrightness = ColorConverter.getBrightness(newColor);

        boolean testNextColor = true;
        while (testNextColor) {
            testedColors++;
            LOGGER.debug("(Direction : " + increment + ")    Color in change Brightness Loop : " + newColor.getRed() + " " + newColor.getGreen() + " " + newColor.getBlue() + " Contrast : " + ContrastChecker.getConstrastRatio(newColor, getColorToKeep()));
            if (isNewColorValid(newColor)) {
                testNextColor = false;
            }
            if (isNextColorBounded(currentBrightness, offset)) {
                newColor = ColorConverter.offsetHsbColor(newColor,
                        NO_CHANGE_COMPONENT,
                        NO_CHANGE_COMPONENT,
                        offset);
                currentBrightness = ColorConverter.getBrightness(newColor);
            } else {
                LOGGER.debug("Why do I return in brightness? (offset condition) ... Color : " + newColor.getRed() + " " + newColor.getGreen() + " " + newColor.getBlue() + " Contrast : " + ContrastChecker.getConstrastRatio(newColor, getColorToKeep()) + "  Brigtness" + ColorConverter.getBrightness(newColor));
                testNextColor = false;
            }
        }
    }

    /**
     *
     * @param currentValue
     * @param offset
     * @return whether the next color is bounded
     */
    private boolean isNextColorBounded(float currentValue, float offset) {
        return (currentValue + offset >= MIN_POSSIBLE_VALUE
                && currentValue + offset <= MAX_POSSIBLE_VALUE);
    }

    /**
     *
     * @return
     */
    @Override
    public String getColorFinderKey() {
        return "HSV";
    }
}
