/*
 * Contrast Finder
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
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