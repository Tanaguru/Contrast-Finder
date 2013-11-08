/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.result.factory;

import java.awt.Color;
import org.opens.colorfinder.result.ColorCombinaison;

/**
 *
 * @author alingua
 */
public interface ColorCombinaisonFactory {
    
    /**
     * 
     * @param color1
     * @param color2
     * @param threashold
     * @return a ColorCombinaison instance
     */
    ColorCombinaison getColorCombinaison(Color color1, Color color2, Double threashold);
    
}