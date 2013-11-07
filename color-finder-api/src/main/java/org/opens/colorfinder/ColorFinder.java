/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder;

import java.awt.Color;
import java.util.Collection;
import org.opens.colorfinder.result.ColorResult;

/**
 *
 * @author alingua
 */
public interface ColorFinder {

    /**
     * 
     * @param foregroundColor
     * @param backgroundColor
     * @param isBackgroundTested
     * @param coefficientLevel
     * @return 
     */
    Collection<ColorResult> findColors (
            Color foregroundColor, 
            Color backgroundColor, 
            boolean isBackgroundTested, 
            Float coefficientLevel);

    /**
     *
     * @return a key that represents the colorFinder
     */
    String getColorFinderKey();
}
