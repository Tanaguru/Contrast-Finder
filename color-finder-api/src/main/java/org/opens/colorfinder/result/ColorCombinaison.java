/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.result;

import java.awt.Color;

/**
 * Interface that defines a couple of colour and its resulting contrast
 * 
 * @author alingua
 */
public interface ColorCombinaison {
    
    /**
     *
     * @return
     */
    Color getColor();

    /**
     *
     * @param color
     */
    void setColor(Color color);

    /**
     *
     * @return
     */
    Double getContrast();
    
    /**
     * 
     * @param threshold 
     */
    void setThreshold(Double threshold);
    
    /**
     * 
     * @return 
     */
    Double getThreshold();
    
    /**
     * 
     * @return 
     */
    boolean isContrastValid();

    /**
     *
     * @return
     */
    Color getComparisonColor();

    /**
     *
     * @param color
     */
    void setComparisonColor(Color color);
    
    /**
     * 
     * @param color 
     */
    String getHexaColor();
    
    /**
     * 
     * @param color 
     */
    String getHexaColorComp();
}