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
package org.opens.color.finder.webapp.model;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author alingua
 */
public class ColorModel {

    private static final String DEFAULT_FOREGROUND = "#468847";
    private static final String DEFAULT_BACKGROUND = "#DFF0D8";
    private static final String DEFAULT_RATIO = "4.5";
    private String foreground = DEFAULT_FOREGROUND;
    private boolean highratio = false;
    private String algo = "Rgb";

    /**
     *
     * @return
     */
    public String getAlgo() {
        return algo;
    }

    /**
     *
     * @param algo
     */
    public void setAlgo(String algo) {
        this.algo = algo;
    }

    /**
     *
     * @return
     */
    public boolean isHighratio() {
        return highratio;
    }

    /**
     *
     * @param highratio
     */
    public void setHighratio(boolean highratio) {
        this.highratio = highratio;
    }

    /**
     *
     * @return
     */
    public String getForeground() {
        if (StringUtils.isBlank(foreground)) {
            return null;
        }
        if (foreground.charAt(0) != '#') {
            setForeground("#" + foreground);
        }
        return foreground.toUpperCase();
    }

    /**
     *
     * @param foreground
     */
    public void setForeground(String foreground) {
        this.foreground = foreground;
    }
    private String background = DEFAULT_BACKGROUND;

    /**
     *
     * @return
     */
    public String getBackground() {
        if (StringUtils.isBlank(background)) {
            return null;
        }
        if (background.charAt(0) != '#') {
            setBackground("#" + background);
        }
        return background.toUpperCase();
    }

    public void setBackground(String background) {
        this.background = background;
    }
    private boolean isBackgroundTested = false;

    public boolean getIsBackgroundTested() {
        return isBackgroundTested;
    }

    public void setIsBackgroundTested(boolean isBackgroundTested) {
        this.isBackgroundTested = isBackgroundTested;
    }
    private String ratio = DEFAULT_RATIO;

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}
