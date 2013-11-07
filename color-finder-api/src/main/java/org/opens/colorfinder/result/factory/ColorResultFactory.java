/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.result.factory;

import java.awt.Color;
import org.opens.colorfinder.result.ColorResult;

/**
 *
 * @author alingua
 */
public interface ColorResultFactory {
    
    /**
     * 
     * @param foundColor
     * @param comparisonColor
     * @return 
     */
    ColorResult getColorResult(Color foundColor, Color comparisonColor);
    
}