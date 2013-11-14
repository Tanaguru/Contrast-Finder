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