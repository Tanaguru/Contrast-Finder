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

package org.opens.colorfinder.factory;

import org.opens.color.finder.hsv.Boundary;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.opens.color.finder.hsv.ColorFinderRgb;
import org.opens.colorfinder.ColorFinder;

/**
 *
 * @author alingua
 */
public class ColorFinderRgbFactory implements ColorFinderFactory {

    private static final int DEFAUT_MAX_MOVE = 60;
    private static final float DEFAUT_HUE_BOUNDER = 5.0f;
    private static final float DEFAUT_MAX_COEFFICIENT = 0.001f;
    private int maxMove = DEFAUT_MAX_MOVE;
    private float hueBounder = DEFAUT_HUE_BOUNDER;
    private float maxCoefficient = DEFAUT_MAX_COEFFICIENT;
    private Map<Integer, Boundary> threasholdVariator;
    public void setThreasholdVariator(Map<Integer, Boundary> threasholdVariator) {
        this.threasholdVariator = threasholdVariator;
    }
    
    public void setMaxMove(int maxMove) {
        this.maxMove = maxMove;
    }

    public void setMaxCoefficient(String maxCoefficient) {
        this.maxCoefficient = Float.valueOf(maxCoefficient);
    }

    public void setHueBounder(float hueBounder) {
        this.hueBounder = hueBounder;
    }
    
    @Override
    public ColorFinder getColorFinder() {
        ColorFinderRgb colorFinder = new ColorFinderRgb();
        colorFinder.setHueBounder(hueBounder);
        colorFinder.setMaxCoefficient(maxCoefficient);
        colorFinder.setMaxMove(maxMove);
        if (threasholdVariator != null) {
            colorFinder.setThreasholdVariator(threasholdVariator);
        }
        return colorFinder;
    }

    @Override
    public ColorFinder getColorFinder(String colorFinderKey) {
        if (StringUtils.equals(colorFinderKey, "Rgb")) {
            return new ColorFinderRgb();
        }
        return null;
    }

}