/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.result.factory;

import java.awt.Color;
import org.opens.colorfinder.result.ColorCombinaison;
import org.opens.colorfinder.result.ColorCombinaisonImpl;

/**
 * 
 * @author alingua
 */
public class ColorCombinaisonFactoryImpl implements ColorCombinaisonFactory {

    @Override
    public ColorCombinaison getColorCombinaison(Color color1, Color color2, Double threashold) {
        ColorCombinaison colorCombinaison = new ColorCombinaisonImpl(color1, color2, threashold);
        return colorCombinaison;
    }
    
}