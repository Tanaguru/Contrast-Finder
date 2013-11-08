/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder;

import java.awt.Color;
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
    void findColors (
            Color foregroundColor, 
            Color backgroundColor, 
            boolean isBackgroundTested, 
            Float coefficientLevel);

    /**
     * 
     * @return 
     */
    ColorResult getColorResult();
    
    /**
     *
     * @return a key that represents the colorFinder
     */
    String getColorFinderKey();
}
