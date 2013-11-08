/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
public abstract class AbstractColorFinderImpl implements ColorFinder {

    private static final Logger LOGGER = Logger.getLogger(AbstractColorFinderImpl.class);
    ColorResultFactory colorResultFactory;
    ColorCombinaisonFactory colorCombinaisonFactory;
    protected Color colorToKeep;
    protected Float coefficientLevel;
    
    protected ColorResult colorResult;
    @Override
    public ColorResult getColorResult() {
        return colorResult;
    }

    /**
     * Constructor
     */
    public AbstractColorFinderImpl(
            ColorResultFactory colorResultFactory,
            ColorCombinaisonFactory colorCombinaisonFactory) {
        super();
        this.colorResultFactory = colorResultFactory;
        this.colorCombinaisonFactory = colorCombinaisonFactory;
    }

    @Override
    public void findColors(Color foregroundColor, Color backgroundColor, boolean isBackgroundTested, Float coefficientLevel) {
        
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
        ColorCombinaison colorCombinaison  = 
                colorCombinaisonFactory.getColorCombinaison(newColor, colorToKeep, Double.valueOf(coefficientLevel));
        if (colorCombinaison.isContrastValid()
                && colorCombinaison.getContrast() < (coefficientLevel + 2.5)) {
            LOGGER.debug("Adding a color to list in the Brightness Loop : " + newColor.getRed() + " " + newColor.getGreen() + " " + newColor.getBlue() + " Contrast : " + ContrastChecker.getConstrastRatio(newColor, colorToKeep));
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