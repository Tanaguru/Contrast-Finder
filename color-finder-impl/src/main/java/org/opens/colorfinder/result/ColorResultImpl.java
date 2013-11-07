/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.result;

import java.awt.Color;
import org.opens.utils.contrastchecker.ContrastChecker;

/**
 * 
 * @author alingua
 */
public class ColorResultImpl implements ColorResult {
    
    private static int HASH = 7;
    private static int HASH_OFFSET = 63;
    
    private Color color;
    private Color comparisonColor;

    public ColorResultImpl() {
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
        return ContrastChecker.getConstrastRatio(this.getColor(), this.getComparisonColor());
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ColorResultImpl other = (ColorResultImpl) obj;
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
