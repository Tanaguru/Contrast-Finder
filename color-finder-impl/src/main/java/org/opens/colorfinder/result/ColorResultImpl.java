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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.opens.colorfinder.result.factory.ColorCombinaisonFactory;
import org.opens.utils.distancecalculator.DistanceCalculator;

/**
 *
 * @author alingua
 */
public class ColorResultImpl implements ColorResult {

    private static final int MAX_SUGGESTED_COLOR = 20;
    /* the colorCombinaisonFactory instance*/
    private ColorCombinaisonFactory colorCombinaisonFactory;
    /* the submitted color combinaison*/
    private ColorCombinaison submittedColors;
    /* the suggested colors */
    private Set<ColorCombinaison> suggestedColors =
            new LinkedHashSet<ColorCombinaison>();
    
    /*
     * Constructor
     */
    public ColorResultImpl(ColorCombinaisonFactory colorCombinaisonFactory) {
        this.colorCombinaisonFactory = colorCombinaisonFactory;
    }

    @Override
    public Float getThreashold() {
        return Float.valueOf(submittedColors.getThreshold().floatValue());
    }

    @Override
    public void setSubmittedColors(Color colorToChange, Color colorToKeep, Float threashold) {
        submittedColors =
                colorCombinaisonFactory.getColorCombinaison(
                colorToChange,
                colorToKeep,
                Double.valueOf(threashold));
    }
    
    @Override
    public ColorCombinaison getSubmittedCombinaisonColor() {
        return submittedColors;
    }
    
    @Override
    public boolean isCombinaisonValid() {
        return submittedColors.isContrastValid();
    }

    @Override
    public Collection<ColorCombinaison> getSuggestedColors() {
//        List<ColorCombinaison> suggestedColorsList = new ArrayList<ColorCombinaison>();
//        suggestedColorsList.addAll(suggestedColors);
//        Collections.sort(suggestedColorsList, new Comparator<ColorCombinaison>() {
//            @Override
//            public int compare(ColorCombinaison o1, ColorCombinaison o2) {
//                if (o1.getContrast() < o2.getContrast()) {
//                    return -1;
//                } else {
//                    return 1;
//                }
//            }
//        });
        return suggestedColors;
    }

    @Override
    public void addSuggestedColor(ColorCombinaison colorCombinaison) {
        colorCombinaison.setDistanceFromInitialColor(DistanceCalculator.calculate(submittedColors.getColor(), colorCombinaison.getColor()));
        suggestedColors.add(colorCombinaison);
    }

    @Override
    public int getNumberOfSuggestedColors() {
        return suggestedColors.size();
    }

    /**
     *
     * @return whether the max number suggested colors is reached
     */
    public boolean isSuggestedColorsFull() {
        return getNumberOfSuggestedColors() >= MAX_SUGGESTED_COLOR;
    }
}