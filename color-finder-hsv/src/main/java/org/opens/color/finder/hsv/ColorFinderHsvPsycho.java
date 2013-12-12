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
import org.apache.log4j.Logger;
import org.opens.colorfinder.AbstractColorFinder;
import org.opens.colorfinder.result.ColorCombinaison;
import org.opens.colorfinder.result.factory.ColorCombinaisonFactoryImpl;
import org.opens.colorfinder.result.factory.ColorResultFactoryImpl;
import org.opens.utils.colorconvertor.ColorConverter;

/**
 *
 * @author alingua
 */
public class ColorFinderHsvPsycho extends AbstractColorFinder {

    private static final Logger LOGGER = Logger.getLogger(ColorFinderHsvPsycho.class);
    private static final float STEP_BRIGHTNESS = 0.01f;
    private static final float STEP_SATURATION = 0.01f;
    private static final float NO_CHANGE_COMPONENT = 0.0f;
    private static final float MAX_POSSIBLE_VALUE = 1.0f;
    private static final float MIN_POSSIBLE_VALUE = 0.0f;
    public static final float DEFAULT_MAX_COEFFICIENT = 0.001f;
    public static final float DEFAULT_HUE_BOUNDER = 10.0f;
    public static final float DEFAULT_COLOR_COMPONENT_BOUNDER = 40.0f;
    
    private float hueBounder = DEFAULT_HUE_BOUNDER;
    private float maxMoveHue = UNITARY_STEP_HUE * hueBounder;
    private float maxCoefficient = DEFAULT_MAX_COEFFICIENT;
    private float maxGreen = DEFAULT_COLOR_COMPONENT_BOUNDER;
    private float maxBlue = DEFAULT_COLOR_COMPONENT_BOUNDER;
    private float maxRed = DEFAULT_COLOR_COMPONENT_BOUNDER;

    public void setMaxRed(float maxRed) {
        this.maxRed = maxRed;
    }

    public void setMaxGreen(float maxGreen) {
        this.maxGreen = maxGreen;
    }

    public void setMaxBlue(float maxBlue) {
        this.maxBlue = maxBlue;
    }

    public void setHueBounder(float hueBounder) {
        this.hueBounder = hueBounder;
        maxMoveHue = UNITARY_STEP_HUE * this.hueBounder;
        LOGGER.info("SetBounderHue " + this.hueBounder);
    }

    public void setMaxCoefficient(float maxCoefficient) {
        this.maxCoefficient = maxCoefficient;
    }

    /**
     * Constructor
     */
    public ColorFinderHsvPsycho() {
        super(new ColorResultFactoryImpl(), new ColorCombinaisonFactoryImpl());
        LOGGER.debug("instanciation of ColorFinderHsv class");
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
        LOGGER.info("findColors of ColorFinderHsvPsycho");
        Color colorToModify = getColorResult().getSubmittedCombinaisonColor().getColor();

        changeHue(colorToModify, false);
        changeHue(colorToModify, true);
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
            offset = UNITARY_STEP_HUE;
        } else {
            offset = -UNITARY_STEP_HUE;
        }

        Color newColor = colorToChange;
        float initialHue = ColorConverter.getHue(newColor);
        float currentHue = ColorConverter.getHue(newColor);
        int offsetRound = 1;
        boolean testNextColor = true;
        while (testNextColor) {
            LOGGER.info("Change Hue" + newColor);
            addNewColorValid(newColor);
            changeSaturation(newColor, false);
            changeSaturation(newColor, true);
            if (isPossibleHue(currentHue, (offset * offsetRound), initialHue)) {
                Color offsetColor = ColorConverter.offsetHsbColor(newColor,
                        offset * offsetRound,
                        NO_CHANGE_COMPONENT,
                        NO_CHANGE_COMPONENT);
                if (offsetColor.equals(newColor)) {
                    offsetRound++;
                } else {
                    offsetRound = 1;
                    newColor = offsetColor;
                }
                currentHue = ColorConverter.getHue(offsetColor);
            } else {
                // the next color is out of bound
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
        float offset;
        if (increment) {
            offset = STEP_SATURATION;
        } else {
            offset = -STEP_SATURATION;
        }
        Color newColor = colorToChange;
        float currentSaturation = ColorConverter.getSaturation(newColor);
        boolean testNextColor = true;
        int offsetRound = 1;
        while (testNextColor) {
            addNewColorValid(newColor);
            if (isNextColorBounded(currentSaturation, (offset * offsetRound))) {
                Color offsetColor = ColorConverter.offsetHsbColor(newColor,
                        NO_CHANGE_COMPONENT,
                        offset * offsetRound,
                        NO_CHANGE_COMPONENT);
                changeBrightness(newColor, false);
                changeBrightness(newColor, true);
                currentSaturation = ColorConverter.getSaturation(newColor);
                if (offsetColor.equals(newColor)) {
                    offsetRound++;
                } else {
                    offsetRound = 1;
                    newColor = offsetColor;
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
        int offsetRound = 1;
        boolean testNextColor = true;
        while (testNextColor) {
            addNewColorValid(newColor);
            if (isNextColorBounded(currentBrightness, (offset * offsetRound))) {
                Color offsetColor = ColorConverter.offsetHsbColor(newColor,
                        NO_CHANGE_COMPONENT,
                        NO_CHANGE_COMPONENT,
                        offset * offsetRound);
                currentBrightness = ColorConverter.getBrightness(newColor);
                LOGGER.debug("Brightness : " + currentBrightness);

                if (offsetColor.equals(newColor)) {
                    offsetRound++;
                    LOGGER.debug("increment offsetRound : " + offsetRound);
                } else {
                    offsetRound = 1;
                    newColor = offsetColor;
                    LOGGER.debug("re-initialize offsetRound : " + offsetRound);
                }
            } else {
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
        return (currentValue + offset > MIN_POSSIBLE_VALUE
                && currentValue + offset < MAX_POSSIBLE_VALUE);
    }

    /**
     * 
     * @param currentValue
     * @param offset
     * @param initialHue
     * @return whether the current hue value is within bounds
     */
    private boolean isPossibleHue(Float currentValue, float offset, float initialHue) {
        float current = currentValue + offset;
        float minBound = initialHue - maxMoveHue;

        float maxBound = initialHue + maxMoveHue;
        return (current > MIN_POSSIBLE_VALUE
                && current < MAX_POSSIBLE_VALUE
                && current <= maxBound
                && current >= minBound);
    }

    /**
     *
     * @param newColor
     * @return
     */
    private void addNewColorValid(Color newColor) {
        ColorCombinaison colorCombinaison =
                getColorCombinaisonFactory().getColorCombinaison(newColor, getColorToKeep(), Double.valueOf(getCoefficientLevel()));
        Color initialColor = getColorResult().getSubmittedCombinaisonColor().getColor();
        /**/
        if (colorCombinaison.isContrastValid()) {
            /* Is contrast within bounds ie. the actual coefficient plus the max allowed coefficient difference */
            if (colorCombinaison.getContrast() < (getCoefficientLevel() + maxCoefficient)) {
                /**/
                if (Math.abs((ColorConverter.getHue(newColor) - ColorConverter.getHue(initialColor))) < (hueBounder * UNITARY_STEP_HUE)) {
                    /**/
                    if (Math.abs(newColor.getRed() - initialColor.getRed()) <= maxRed) {
                        /**/
                        if (Math.abs(newColor.getGreen() - initialColor.getGreen()) <= maxGreen) {
                            /**/
                            if (Math.abs(newColor.getBlue() - initialColor.getBlue()) <= maxBlue) {
                                LOGGER.debug("Add new color " + newColor);
                                getColorResult().addSuggestedColor(colorCombinaison);

                            } else {
                                LOGGER.debug("blue out of bounf " + newColor);
                            }
                        } else {
                            LOGGER.debug("green out of bounf " + newColor);
                        }
                    } else {
                        LOGGER.debug("red out of bounf " + newColor);
                    }
                } else {
                    LOGGER.debug("hue out of bound " + newColor);
                }
            } else {
                LOGGER.debug("contrast too high " + newColor + " , " + colorCombinaison.getContrast());
            }
        } else {
            LOGGER.debug("contrast invalide " + newColor + " , " + colorCombinaison.getContrast());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getColorFinderKey() {
        return "Psycho";
    }
}
