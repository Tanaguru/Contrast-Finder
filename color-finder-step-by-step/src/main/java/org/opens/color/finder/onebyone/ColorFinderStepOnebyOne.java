package org.opens.color.finder.onebyone;

import org.opens.utils.colorconvertor.ColorConverter;
import org.opens.utils.contrastchecker.ContrastChecker;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import org.opens.colorfinder.AbstractColorFinderImpl;
import org.opens.colorfinder.result.ColorResult;
import org.opens.colorfinder.result.factory.ColorResultFactory;
import org.opens.colorfinder.result.factory.ColorResultFactoryImpl;

/**
 * This class is one of some finder implementation
 *
 * @author alingua
 */
public class ColorFinderStepOnebyOne extends AbstractColorFinderImpl {

    private int stepValue = 1;
    private static final int MAX_COLOR_VALUE = 254;

    /**
     * Constructor
     */
    public ColorFinderStepOnebyOne() {
        super();
    }

    @Override
    protected Collection<ColorResult> findColors(Color colorToChange, Color colorToKeep, Float coefficientLevel) {
        ColorResultFactory colorResultFactory = new ColorResultFactoryImpl();
        Collection<ColorResult> result = new ArrayList<ColorResult>();
        Color newColor = colorToChange;
        while (newColor.getRed() <= MAX_COLOR_VALUE) {
            if (ContrastChecker.isContrastValid(newColor, colorToKeep, coefficientLevel)) {
                result.add(colorResultFactory.getColorResult(newColor, colorToKeep));
            }
            newColor = ColorConverter.offsetRgbColor(newColor, stepValue, 0, 0);
            while (newColor.getGreen() <= MAX_COLOR_VALUE) {
                if (ContrastChecker.isContrastValid(newColor, colorToKeep, coefficientLevel)) {
                    result.add(colorResultFactory.getColorResult(newColor, colorToKeep));
                }
                newColor = ColorConverter.offsetRgbColor(newColor, 0, stepValue, 0);
                while (newColor.getBlue() <= MAX_COLOR_VALUE) {
                    if (ContrastChecker.isContrastValid(newColor, colorToKeep, coefficientLevel)) {
                        result.add(colorResultFactory.getColorResult(newColor, colorToKeep));
                    }
                    newColor = ColorConverter.offsetRgbColor(newColor, 0, 0, stepValue);
                }
            }
        }
        return null;
    }

    /**
     *
     * @return the key of this method
     */
    public String getColorFinderKey() {
        return "ONE_BY_ONE";
    }
}