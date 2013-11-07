/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder;

import java.awt.Color;
import java.util.Collection;
import org.opens.colorfinder.result.ColorResult;
import org.opens.utils.contrastchecker.ContrastChecker;

/**
 *
 * @author alingua
 */
public abstract class AbstractColorFinderImpl implements ColorFinder {

    /**
     * Constructor
     */
    public AbstractColorFinderImpl() {
        super();
    }

    @Override
    public Collection<ColorResult> findColors(Color foregroundColor, Color backgroundColor, boolean isBackgroundTested, Float coefficientLevel) {
        if (!ContrastChecker.isContrastValid(backgroundColor, foregroundColor, coefficientLevel)) {
            if (isBackgroundTested) {
                return (findColors(backgroundColor, foregroundColor, coefficientLevel));
            } else {
                return (findColors(foregroundColor, backgroundColor, coefficientLevel));
            }
        }
        return null;
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
    protected abstract Collection<ColorResult> findColors(
            Color colorToChange,
            Color colorToKeep,
            Float coefficientLevel);
}