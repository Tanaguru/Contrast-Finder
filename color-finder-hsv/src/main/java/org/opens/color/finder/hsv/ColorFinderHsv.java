package org.opens.color.finder.hsv;

import java.awt.Color;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.apache.log4j.Logger;
import org.opens.colorfinder.AbstractColorFinderImpl;
import org.opens.colorfinder.result.ColorResult;
import org.opens.colorfinder.result.factory.ColorResultFactory;
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
    ColorResultFactory colorResultFactory = new ColorResultFactoryImpl();
    Collection<ColorResult> result = new LinkedHashSet<ColorResult>();
    Float coefficientLevel;

    /**
     * Constructor
     */
    public ColorFinderHsv() {
        super();
        LOGGER.debug("instanciation of ColorFinderHsv class");
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param coefficientLevel
     * @return
     */
    protected Collection<ColorResult> findColors(
            Color colorToChange,
            Color colorToKeep,
            Float coefficientLevel) {
        this.coefficientLevel = coefficientLevel;
        Color newColor = colorToChange;
        changeHue(newColor, colorToKeep, false);
        changeHue(newColor, colorToKeep, true);
        LOGGER.debug("Size of Color list : " + result.size());
        return result;
    }

    /**
     *
     * @param colorToChange
     * @param colorToKeep
     * @param increment
     */
    private void changeHue(final Color colorToChange, final Color colorToKeep, boolean increment) {
        float offset;
        if (increment) {
            offset = STEP_HUE;
        } else {
            offset = -STEP_HUE;
        }
        Color newColor = new Color(colorToChange.getRed() + 1, colorToChange.getGreen(), colorToChange.getBlue());
        int listSize = result.size();
        float currentHue = ColorConverter.getHue(newColor);
        while (currentHue <= MAX_POSSIBLE_VALUE
                && currentHue >= MIN_POSSIBLE_VALUE) {
            if (isCombinaisonValid(newColor, colorToKeep)
                    && ContrastChecker.getConstrastRatio(newColor, colorToKeep) < (coefficientLevel + 2.5)) {
                result.add(colorResultFactory.getColorResult(newColor, colorToKeep));
            }
            if (result.size() - listSize < 20) {
                changeSaturation(newColor, colorToKeep, false);
                changeSaturation(newColor, colorToKeep, true);
                if (currentHue + offset >= MIN_POSSIBLE_VALUE
                        && currentHue + offset <= MAX_POSSIBLE_VALUE) {
                    Color offsetColor = ColorConverter.offsetHsbColor(newColor,
                            offset,
                            NO_CHANGE_COMPONENT,
                            NO_CHANGE_COMPONENT);
                    LOGGER.debug("Offset Color  " + offsetColor.hashCode() + " " + offsetColor.toString());
                    currentHue = ColorConverter.getHue(offsetColor);
                    newColor = offsetColor;
                    LOGGER.debug("New Color  " + newColor.hashCode() + " " + newColor.toString());
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
    private void changeSaturation(final Color colorToChange, final Color colorToKeep, boolean increment) {
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
//            LOGGER.debug("(Direction : " + increment + ")    Color in change Saturation Loop : " + newColor.getRed() + " " + newColor.getGreen() + " " + newColor.getBlue() + " Contrast : " + ContrastChecker.getConstrastRatio(newColor, colorToKeep));
            if (isCombinaisonValid(newColor, colorToKeep)
                    && ContrastChecker.getConstrastRatio(newColor, colorToKeep) < (coefficientLevel + 2.5)) {
                result.add(colorResultFactory.getColorResult(newColor, colorToKeep));
                return;
            }
            if (currentSaturation + offset >= MIN_POSSIBLE_VALUE
                    && currentSaturation + offset <= MAX_POSSIBLE_VALUE) {
                newColor = ColorConverter.offsetHsbColor(newColor,
                        NO_CHANGE_COMPONENT,
                        offset,
                        NO_CHANGE_COMPONENT);
                changeBrightness(newColor, colorToKeep, false);
                changeBrightness(newColor, colorToKeep, true);
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
    private void changeBrightness(final Color colorToChange, final Color colorToKeep, boolean increment) {
        float offset;
        if (increment) {
            offset = STEP_BRIGHTNESS;
        } else {
            offset = -STEP_BRIGHTNESS;
        }
        Color newColor = colorToChange;
        float currentBrightness = ColorConverter.getBrightness(newColor);
//        Double oldContrastRatio = ContrastChecker.getConstrastRatio(newColor, colorToKeep);
        while (currentBrightness <= MAX_POSSIBLE_VALUE
                && currentBrightness >= (MIN_POSSIBLE_VALUE)) {
//            LOGGER.debug("(Direction : " + increment + ")    Color in change Brightness Loop : " + newColor.getRed() + " " + newColor.getGreen() + " " + newColor.getBlue() + " Contrast : " + ContrastChecker.getConstrastRatio(newColor, colorToKeep));
            if (isCombinaisonValid(newColor, colorToKeep)
                    && ContrastChecker.getConstrastRatio(newColor, colorToKeep) < (coefficientLevel + 2.5)) {
//                LOGGER.debug("Adding a color to list in the Brightness Loop : " + newColor.getRed() + " " + newColor.getGreen() + " " + newColor.getBlue() + " Contrast : " + ContrastChecker.getConstrastRatio(newColor, colorToKeep));
                result.add(colorResultFactory.getColorResult(newColor, colorToKeep));
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
//                LOGGER.debug("Why do I return ? (offset condition) ... Color : " + newColor.getRed() + " " + newColor.getGreen() + " " + newColor.getBlue() + " Contrast : " + ContrastChecker.getConstrastRatio(newColor, colorToKeep) + "  Brigtness" + ColorConverter.getBrightness(newColor));
                return;
            }
        }
    }

    /**
     *
     * @param color1
     * @param color2
     * @return
     */
    private boolean isCombinaisonValid(Color color1, Color color2) {
        return ContrastChecker.isContrastValid(
                color1,
                color2,
                coefficientLevel);
    }

    public String getColorFinderKey() {
        return "HSV";
    }
}
