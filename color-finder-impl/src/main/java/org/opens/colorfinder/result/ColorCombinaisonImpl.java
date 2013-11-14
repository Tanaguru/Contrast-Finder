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
import org.opens.utils.colorconvertor.ColorConverter;
import org.opens.utils.contrastchecker.ContrastChecker;

/**
 *
 * @author alingua
 */
public class ColorCombinaisonImpl implements ColorCombinaison {

    private static int HASH = 7;
    private static int HASH_OFFSET = 63;
    private Color color;
    private Color comparisonColor;
    private Double contrastRatio;
    private Double threashold;

    /**
     * Default constructor
     */
    public ColorCombinaisonImpl() {
    }

    /**
     * Constructor
     * 
     * @param color
     * @param comparisonColor
     * @param threashold 
     */
    public ColorCombinaisonImpl(Color color, Color comparisonColor, Double threashold) {
        this.color = color;
        this.comparisonColor = comparisonColor;
        this.threashold = threashold;
    }

    /**
     *
     * @return
     */
    public Color getColor() {
        return this.color;
    }

    /**
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *
     * @return
     */
    public Double getContrast() {
        if (contrastRatio == null) {
            contrastRatio = 
                    Double.valueOf(
                        ContrastChecker.getConstrastRatio(
                            this.getColor(), 
                            this.getComparisonColor()));
        }
        return contrastRatio;
    }

    /**
     * 
     * @param threshold 
     */
    public void setThreshold(Double threshold) {
        this.threashold = threshold;
    }
    
    /**
     * 
     * @param threshold 
     */
    public Double getThreshold() {
        return this.threashold;
    }

    /**
     * 
     * @return 
     */
    public boolean isContrastValid() {
        return getContrast() > threashold;
    }
    
    /**
     *
     * @return
     */
    public Color getComparisonColor() {
        return comparisonColor;
    }

    /**
     *
     * @param color
     */
    public void setComparisonColor(Color color) {
        this.comparisonColor = color;
    }

    /**
     * 
     * @return 
     */
    public String getHexaColor() {
        return ColorConverter.Rgb2hex(color);
    }

    /**
     * 
     * @return 
     */
    public String getHexaColorComp() {
        return ColorConverter.Rgb2hex(comparisonColor);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ColorCombinaisonImpl other = (ColorCombinaisonImpl) obj;
        if (this.color != other.color && (this.color == null || !this.color.equals(other.color))) {
            return false;
        }
        if (this.comparisonColor != other.comparisonColor && (this.comparisonColor == null || !this.comparisonColor.equals(other.comparisonColor))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = HASH;
        hash = HASH_OFFSET * hash + (this.color != null ? this.color.hashCode() : 0);
        hash = HASH_OFFSET * hash + (this.comparisonColor != null ? this.comparisonColor.hashCode() : 0);
        return hash;
    }

}