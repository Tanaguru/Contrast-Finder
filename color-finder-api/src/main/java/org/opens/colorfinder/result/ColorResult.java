/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.result;

import java.awt.Color;
import java.util.Collection;

/**
 *
 * @author alingua
 */
public interface ColorResult {

    /**
     * 
     * @param foreground
     * @param backgroud
     * @param threashold 
     */
    void setSubmittedColors(Color foreground, Color backgroud, Float threashold);
    
    /**
     * 
     * @return 
     */
    ColorCombinaison getSubmittedCombinaisonColor();
    
    /**
     * 
     * @return 
     */
    boolean isCombinaisonValid();
    
    /**
     * 
     * @return 
     */
    Collection<ColorCombinaison> getSuggestedColors();
    
    /**
     * 
     * @return 
     */
    int getNumberOfSuggestedColors();
    
    /**
     * 
     * @param combinaison 
     */
    void addSuggestedColor(ColorCombinaison combinaison);
    
    /**
     * 
     * @return 
     */
    Float getThreashold();

}