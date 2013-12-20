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
import java.util.Map;
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
public class ColorFinderRgb extends AbstractColorFinder {

    private static final Logger LOGGER = Logger.getLogger(ColorFinderRgb.class);
    private static final int STEP = 1;
    private static final int NO_CHANGE_COMPONENT = 0;
    private static final int MAX_POSSIBLE_VALUE = 255;
    private static final int MIN_POSSIBLE_VALUE = 0;
    private static final int DEFAUT_MAX_MOVE = 60;
    private static final float DEFAUT_HUE_BOUNDER = 5.0f;
    private static final float DEFAUT_MAX_COEFFICIENT = 0.001f;
    private int maxMove = DEFAUT_MAX_MOVE;
    private float hueBounder = DEFAUT_HUE_BOUNDER;
    private float maxMoveHue = UNITARY_STEP_HUE * hueBounder;
    private float maxCoefficient = DEFAUT_MAX_COEFFICIENT;
    private Map<Integer, Boundary> threasholdVariator;
    private int testedColors;

    public void setThreasholdVariator(Map<Integer, Boundary> threasholdVariator) {
        this.threasholdVariator = threasholdVariator;
    }

    public void setMaxMove(int maxMove) {
        this.maxMove = maxMove;
    }

    public void setMaxCoefficient(float maxCoefficient) {
        this.maxCoefficient = maxCoefficient;
    }

    public void setHueBounder(float hueBounder) {
        this.hueBounder = hueBounder;
    }

    /**
     * Constructor
     */
    public ColorFinderRgb() {
        super(new ColorResultFactoryImpl(), new ColorCombinaisonFactoryImpl());
        LOGGER.debug("instanciation of ColorFinderRgb class");
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
        LOGGER.debug("findColors of ColorFinderRgb");
        Color colorToModify = getColorResult().getSubmittedCombinaisonColor().getColor();
        if (threasholdVariator != null) {
            float gap = getColorResult().getSubmittedCombinaisonColor().getGap();
            updateBoundersFromGap(gap);
        }
        testedColors = 0;
        changeRed(colorToModify, false);
        changeRed(colorToModify, true);
        getColorResult().setNumberOfTestedColors(testedColors);
        LOGGER.debug("Size of Color list : " + getColorResult().getSuggestedColors().size());
    }

    private void updateBoundersFromGap(Float gap) {
        for (Map.Entry<Integer, Boundary> entry : threasholdVariator.entrySet()) {
            if (entry.getValue().isBounded(gap)) {
                maxMove = entry.getKey();
            }
        }
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param increment
     */
    private void changeRed(final Color colorToChange, boolean increment) {
        int offset;
        if (increment) {
            offset = STEP;
        } else {
            offset = -STEP;
        }

        Color newColor = colorToChange;
        int currentRed = newColor.getRed();
        boolean testNextColor = true;
        while (testNextColor) {
            addNewColorValid(newColor);
            changeGreen(newColor, false, colorToChange);
            changeGreen(newColor, true, colorToChange);
            if (isNextColorBounded(currentRed, offset, colorToChange.getRed())) {
                newColor = ColorConverter.offsetRgbColor(newColor,
                        offset,
                        NO_CHANGE_COMPONENT,
                        NO_CHANGE_COMPONENT);
                currentRed = newColor.getRed();
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
    private void changeGreen(final Color colorToChange, boolean increment, final Color initialColor) {
        int offset;
        if (increment) {
            offset = STEP;
        } else {
            offset = -STEP;
        }
        Color newColor = colorToChange;
        int currentGreen = newColor.getGreen();
        boolean testNextColor = true;
        while (testNextColor) {
            addNewColorValid(newColor);
            if (isNextColorBounded(currentGreen, offset, colorToChange.getGreen())) {
                newColor = ColorConverter.offsetRgbColor(newColor,
                        NO_CHANGE_COMPONENT,
                        offset,
                        NO_CHANGE_COMPONENT);
                changeBlue(newColor, false, initialColor);
                changeBlue(newColor, true, initialColor);
                currentGreen = newColor.getGreen();
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
    private void changeBlue(final Color colorToChange, boolean increment, final Color initialColor) {
        int offset;
        if (increment) {
            offset = STEP;
        } else {
            offset = -STEP;
        }
        Color newColor = colorToChange;
        int currentBlue = newColor.getBlue();
        boolean testNextColor = true;
        while (testNextColor) {
            testedColors++;
            addNewColorValid(newColor);
            if (isNextColorBounded(currentBlue, offset, initialColor.getBlue())) {
                newColor = ColorConverter.offsetRgbColor(newColor,
                        NO_CHANGE_COMPONENT,
                        NO_CHANGE_COMPONENT,
                        offset);
                currentBlue = newColor.getBlue();
            } else {
                testNextColor = false;
            }
        }
    }

    private boolean isNextColorBounded(int currentValue, int offset, int initialComponent) {
        float current = currentValue + offset;
        float minBound = initialComponent - maxMove;
        float maxBound = initialComponent + maxMove;
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
        if (colorCombinaison.isContrastValid()) {
            if (colorCombinaison.getContrast() < (getCoefficientLevel() + maxCoefficient)) {
                LOGGER.debug("New Contrast" + colorCombinaison.getContrast());
                LOGGER.debug("MAX contrast possible" + (getCoefficientLevel() + maxCoefficient));
                if (Math.abs((ColorConverter.getHue(newColor) - ColorConverter.getHue(initialColor))) < maxMoveHue) {
                    getColorResult().addSuggestedColor(colorCombinaison);
                    LOGGER.debug("Add color with contrast : " + colorCombinaison.getContrast());
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
        return "Rgb";
    }
}