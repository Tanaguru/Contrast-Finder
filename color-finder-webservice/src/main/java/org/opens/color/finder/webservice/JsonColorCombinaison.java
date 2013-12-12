/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.color.finder.webservice;

import org.opens.colorfinder.result.ColorCombinaison;

/**
 *
 * @author alingua
 */
public class JsonColorCombinaison {
 
    private ColorCombinaison colorCombinaison;
    public JsonColorCombinaison(ColorCombinaison colorCombinaison) {
        this.colorCombinaison = colorCombinaison;
    }

    public Double getContrast() {
        return colorCombinaison.getContrast();
    }

    public Double getThreshold() {
        return colorCombinaison.getThreshold();
    }

    public boolean isContrastValid() {
        return colorCombinaison.isContrastValid();
    }

    public String getModifiedColor() {
        return colorCombinaison.getHexaColor();
    }

    public String getOtherColor() {
        return colorCombinaison.getHexaColorComp();
    }

}