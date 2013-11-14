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
package org.opens.colorfinder;

import java.awt.Color;
import org.opens.colorfinder.result.ColorResult;

/**
 *
 * @author alingua
 */
public interface ColorFinder {

    /**
     * 
     * @param foregroundColor
     * @param backgroundColor
     * @param isBackgroundTested
     * @param coefficientLevel
     * @return 
     */
    void findColors (
            Color foregroundColor, 
            Color backgroundColor, 
            boolean isBackgroundTested, 
            Float coefficientLevel);

    /**
     * 
     * @return 
     */
    ColorResult getColorResult();
    
    /**
     *
     * @return a key that represents the colorFinder
     */
    String getColorFinderKey();
}
