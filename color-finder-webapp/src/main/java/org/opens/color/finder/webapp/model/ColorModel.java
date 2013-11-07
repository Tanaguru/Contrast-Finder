/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.color.finder.webapp.model;

/**
 *
 * @author alingua
 */
public class ColorModel {

    private static final String DEFAULT_FOREGROUND = "#468847";
    private static final String DEFAULT_BACKGROUND = "#DFF0D8";
    private static final String DEFAULT_RATIO = "4.5";
    private String foreground = DEFAULT_FOREGROUND;

    public String getForeground() {
        return foreground;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }
    private String background = DEFAULT_BACKGROUND;
    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
    
    private String isBackgroundTested = "false";

    public String getIsBackgroundTested() {
        return isBackgroundTested;
    }

    public void setIsBackgroundTested(String isBackgroundTested) {
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
