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

import org.apache.commons.lang3.StringUtils;
import org.opens.color.finder.hsv.ColorFinderHsvPsycho;
import org.opens.colorfinder.ColorFinder;

/**
 *
 * @author alingua
 */
public class ColorFinderHsvPsychoFactory implements ColorFinderFactory {

    private float hueBounder = ColorFinderHsvPsycho.DEFAULT_HUE_BOUNDER;
    private float maxCoefficient = ColorFinderHsvPsycho.DEFAULT_MAX_COEFFICIENT;
    private float maxGreen = ColorFinderHsvPsycho.DEFAULT_COLOR_COMPONENT_BOUNDER;
    private float maxBlue = ColorFinderHsvPsycho.DEFAULT_COLOR_COMPONENT_BOUNDER;
    private float maxRed = ColorFinderHsvPsycho.DEFAULT_COLOR_COMPONENT_BOUNDER;
    
    public void setMaxRed(String maxRed) {
        this.maxRed = Float.valueOf(maxRed);
    }

    public void setMaxGreen(String maxGreen) {
        this.maxGreen = Float.valueOf(maxGreen);
    }

    public void setMaxBlue(String maxBlue) {
        this.maxBlue = Float.valueOf(maxBlue);
    }

    public void setHueBounder(String hueBounder) {
        this.hueBounder = Float.valueOf(hueBounder);
    }

    public void setMaxCoefficient(String maxCoefficient) {
        this.maxCoefficient = Float.valueOf(maxCoefficient);
    }
    
    @Override
    public ColorFinder getColorFinder() {
        ColorFinderHsvPsycho colorFinder = new ColorFinderHsvPsycho();
        colorFinder.setHueBounder(hueBounder);
        colorFinder.setMaxBlue(maxBlue);
        colorFinder.setMaxGreen(maxGreen);
        colorFinder.setMaxRed(maxRed);
        colorFinder.setMaxCoefficient(maxCoefficient);
        return colorFinder;
    }

    @Override
    public ColorFinder getColorFinder(String colorFinderKey) {
        if (StringUtils.equals(colorFinderKey, "Psycho")) {
            return new ColorFinderHsvPsycho();
        }
        return null;
    }

}