/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.result;

import java.awt.Color;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.apache.log4j.Logger;
import org.opens.colorfinder.result.factory.ColorCombinaisonFactory;
import org.opens.colorfinder.result.factory.ColorCombinaisonFactoryImpl;

/**
 *
 * @author alingua
 */
public class ColorResultImpl implements ColorResult {

    private static int MAX_SUGGESTED_COLOR = 20;
    
    private static final ColorCombinaisonFactory colorCombinaisonFactory = 
                new ColorCombinaisonFactoryImpl();
    
    private ColorCombinaison submittedColors;
    
    Collection<ColorCombinaison> suggestedColors = new LinkedHashSet<ColorCombinaison>();
    
    public Float getThreashold() {
        return Float.valueOf(submittedColors.getThreshold().floatValue());
    }

    public void setSubmittedColors(Color foreground, Color background, Float threashold) {
        submittedColors = 
                colorCombinaisonFactory.getColorCombinaison(
                    foreground, 
                    background, 
                    Double.valueOf(threashold));
    }

    public ColorCombinaison getSubmittedCombinaisonColor() {
        return submittedColors;
    }

    public boolean isCombinaisonValid() {
        return submittedColors.isContrastValid();
    }

    public Collection<ColorCombinaison> getSuggestedColors() {
        return suggestedColors;
    }
    
    public void addSuggestedColor(ColorCombinaison colorCombinaison) {
        suggestedColors.add(colorCombinaison);
        Logger.getLogger(this.getClass()).debug("new color added,  "+ suggestedColors.size() +" colors in collection");
    }

    public int getNumberOfSuggestedColors() {
        return suggestedColors.size();
    }
    
    public boolean isSuggestedColorsFull() {
        return getNumberOfSuggestedColors() >= MAX_SUGGESTED_COLOR;
    }

}