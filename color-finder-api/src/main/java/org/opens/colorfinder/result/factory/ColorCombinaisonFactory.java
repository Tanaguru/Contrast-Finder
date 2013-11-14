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
package org.opens.colorfinder.result.factory;

import java.awt.Color;
import org.opens.colorfinder.result.ColorCombinaison;

/**
 *
 * @author alingua
 */
public interface ColorCombinaisonFactory {
    
    /**
     * 
     * @param color1
     * @param color2
     * @param threashold
     * @return a ColorCombinaison instance
     */
    ColorCombinaison getColorCombinaison(Color color1, Color color2, Double threashold);
    
}