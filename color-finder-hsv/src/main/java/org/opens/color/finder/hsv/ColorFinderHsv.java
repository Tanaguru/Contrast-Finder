package org.opens.color.finder.hsv;

import java.awt.Color;
import org.apache.log4j.Logger;
import org.opens.colorfinder.AbstractColorFinderImpl;
import org.opens.colorfinder.result.factory.ColorCombinaisonFactoryImpl;
import org.opens.colorfinder.result.factory.ColorResultFactoryImpl;
import org.opens.utils.colorconvertor.ColorConverter;
import org.opens.utils.contrastchecker.ContrastChecker;

/**
 *
 * @author alingua
 */
public class ColorFinderHsv extends AbstractColorFinderImpl {

    private static final Logger LOGGER = Logger.getLogger(ColorFinderHsv.class);
    private static final float STEP_BRIGHTNESS = 0.1f;
    private static final float STEP_SATURATION = 0.1f;
    private static final float STEP_HUE = 0.1f;
    private static final float NO_CHANGE_COMPONENT = 0.0f;
    private static final float MAX_POSSIBLE_VALUE = 1.0f;
    private static final float MIN_POSSIBLE_VALUE = 0.0f;

    /**
     * Constructor
     */
    public ColorFinderHsv() {
        super(new ColorResultFactoryImpl(), new ColorCombinaisonFactoryImpl());
        LOGGER.debug("instanciation of ColorFinderHsv class");
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param coefficientLevel
     * @return
     */
    protected void findColors() {

        changeHue(colorResult.getSubmittedCombinaisonColor().getColor(), false);
        changeHue(colorResult.getSubmittedCombinaisonColor().getColor(), true);
        
        LOGGER.debug("Size of Color list : " + colorResult.getSuggestedColors().size());
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param increment
     */
    private void changeHue(final Color colorToChange, boolean increment) {
        float offset;
        if (increment) {
            offset = STEP_HUE;
        } else {
            offset = -STEP_HUE;
        }
        
        Color newColor = colorToChange;
        
        int initialResultSize = colorResult.getNumberOfSuggestedColors();
        
        float currentHue = ColorConverter.getHue(newColor);
        while (currentHue <= MAX_POSSIBLE_VALUE
                && currentHue >= MIN_POSSIBLE_VALUE) {
            
            // the return is not used here, we just want to add the current color
            // in the result collection in case of its contrast is valid
            isNewColorValid(newColor);
            if (colorResult.getNumberOfSuggestedColors() - initialResultSize < 20) {
                changeSaturation(newColor, false);
                changeSaturation(newColor, true);
                if (currentHue + offset >= MIN_POSSIBLE_VALUE
                        && currentHue + offset <= MAX_POSSIBLE_VALUE) {
                    Color offsetColor = ColorConverter.offsetHsbColor(newColor,
                            offset,
                            NO_CHANGE_COMPONENT,
                            NO_CHANGE_COMPONENT);
                    currentHue = ColorConverter.getHue(offsetColor);
                    if (!newColor.equals(offsetColor)) {
                        newColor = offsetColor;
                    } else {
                        return;
                    }
                    LOGGER.debug("changeHue New Color  " + newColor.hashCode() + " " + newColor.toString());
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param increment
     */
    private void changeSaturation(final Color colorToChange, boolean increment) {
        float offset;
        if (increment) {
            offset = STEP_SATURATION;
        } else {
            offset = -STEP_SATURATION;
        }
        Color newColor = colorToChange;
        float currentSaturation = ColorConverter.getSaturation(newColor);
        while (currentSaturation <= MAX_POSSIBLE_VALUE
                && currentSaturation >= (MIN_POSSIBLE_VALUE)) {
            if (isNewColorValid(newColor)) {
                return;
            }
            if (currentSaturation + offset >= MIN_POSSIBLE_VALUE
                    && currentSaturation + offset <= MAX_POSSIBLE_VALUE) {
                newColor = ColorConverter.offsetHsbColor(newColor,
                        NO_CHANGE_COMPONENT,
                        offset,
                        NO_CHANGE_COMPONENT);
                changeBrightness(newColor, false);
                changeBrightness(newColor, true);
                currentSaturation = ColorConverter.getSaturation(newColor);
            } else {
                return;
            }
        }
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param increment
     */
    private void changeBrightness(final Color colorToChange, boolean increment) {
        float offset;
        if (increment) {
            offset = STEP_BRIGHTNESS;
        } else {
            offset = -STEP_BRIGHTNESS;
        }
        Color newColor = colorToChange;
        float currentBrightness = ColorConverter.getBrightness(newColor);
        
        while (currentBrightness <= MAX_POSSIBLE_VALUE
                && currentBrightness >= (MIN_POSSIBLE_VALUE)) {
            LOGGER.debug("(Direction : " + increment + ")    Color in change Brightness Loop : " + newColor.getRed() + " " + newColor.getGreen() + " " + newColor.getBlue() + " Contrast : " + ContrastChecker.getConstrastRatio(newColor, colorToKeep));
            if (isNewColorValid(newColor)) {
                return;
            }
            if (currentBrightness + offset >= MIN_POSSIBLE_VALUE
                    && currentBrightness + offset <= MAX_POSSIBLE_VALUE) {
                newColor = ColorConverter.offsetHsbColor(newColor,
                        NO_CHANGE_COMPONENT,
                        NO_CHANGE_COMPONENT,
                        offset);
                currentBrightness = ColorConverter.getBrightness(newColor);
            } else {
                LOGGER.debug("Why do I return ? (offset condition) ... Color : " + newColor.getRed() + " " + newColor.getGreen() + " " + newColor.getBlue() + " Contrast : " + ContrastChecker.getConstrastRatio(newColor, colorToKeep) + "  Brigtness" + ColorConverter.getBrightness(newColor));
                return;
            }
        }
    }

    /**
     * 
     * @return 
     */
    public String getColorFinderKey() {
        return "HSV";
    }
}
