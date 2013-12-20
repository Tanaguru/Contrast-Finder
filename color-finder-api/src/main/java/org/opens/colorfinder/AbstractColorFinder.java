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
package org.opens.colorfinder;

import java.awt.Color;
import org.apache.log4j.Logger;
import org.opens.colorfinder.result.ColorCombinaison;
import org.opens.colorfinder.result.ColorResult;
import org.opens.colorfinder.result.factory.ColorCombinaisonFactory;
import org.opens.colorfinder.result.factory.ColorResultFactory;
import org.opens.utils.contrastchecker.ContrastChecker;

/**
 *
 * @author alingua
 */
public abstract class AbstractColorFinder implements ColorFinder {

    private static final Logger LOGGER = Logger.getLogger(AbstractColorFinder.class);
    private static final float MAX_HUE = 360.0f;
    private static final float MIN_HUE = 1.0f;
    private static final float MAX_COEFFICIENT_LEVEL = 2.5f;
    protected static final float UNITARY_STEP_HUE = (MIN_HUE / MAX_HUE);
    private ColorResultFactory colorResultFactory;

    public ColorResultFactory getColorResultFactory() {
            return colorResultFactory;
        }
    
    
    private ColorCombinaisonFactory colorCombinaisonFactory;

    public ColorCombinaisonFactory getColorCombinaisonFactory() {
        return colorCombinaisonFactory;
    }
    /* the color to keep, will not be modified during test */
    private Color colorToKeep;

    public Color getColorToKeep() {
        return colorToKeep;
    }
    /* the coefficient level to apply*/
    private Float coefficientLevel;

    public Float getCoefficientLevel() {
        return coefficientLevel;
    }
    /* the colorResult that handles the results of the test*/
    private ColorResult colorResult;

    @Override
    public ColorResult getColorResult() {
        return colorResult;
    }

    /**
     * Constructor
     */
    public AbstractColorFinder(
            ColorResultFactory colorResultFactory,
            ColorCombinaisonFactory colorCombinaisonFactory) {
        super();
        this.colorResultFactory = colorResultFactory;
        this.colorCombinaisonFactory = colorCombinaisonFactory;
    }

    @Override
    public void findColors(Color foregroundColor,
            Color backgroundColor,
            boolean isBackgroundTested,
            Float coefficientLevel) {
        this.coefficientLevel = coefficientLevel;

        if (isBackgroundTested) {
            colorToKeep = foregroundColor;
            initColorResult(backgroundColor, foregroundColor, coefficientLevel);
        } else {
            colorToKeep = backgroundColor;
            initColorResult(foregroundColor, backgroundColor, coefficientLevel);
        }
        if (!colorResult.isCombinaisonValid()) {
            findColors();
        }

    }

    /**
     *
     * @param newColor
     * @return
     */
    protected boolean isNewColorValid(Color newColor) {
        ColorCombinaison colorCombinaison =
                colorCombinaisonFactory.getColorCombinaison(
                newColor,
                colorToKeep,
                Double.valueOf(coefficientLevel));

        if (colorCombinaison.isContrastValid()
                && colorCombinaison.getContrast() < (coefficientLevel + MAX_COEFFICIENT_LEVEL)) {

            LOGGER.debug("Adding a color to list : " + newColor.getRed() + " "
                    + newColor.getGreen() + " "
                    + newColor.getBlue() + " Contrast : "
                    + ContrastChecker.getConstrastRatio(newColor, colorToKeep));

            colorResult.addSuggestedColor(colorCombinaison);
            return true;
        }
        return false;
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param coefficientLevel
     */
    protected void initColorResult(
            Color colorToChange,
            Color colorToKeep,
            Float coefficientLevel) {
        colorResult = colorResultFactory.getColorResult();
        colorResult.setSubmittedColors(colorToChange, colorToKeep, coefficientLevel);
    }

    /**
     * Concrete method that find the colors from one color to change, one to
     * keep, and a threshold.
     *
     * @param colorToChange
     * @param colorToKeep
     * @param coefficientLevel
     * @return
     */
    protected abstract void findColors();
}