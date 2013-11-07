/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.factory;

import org.opens.colorfinder.ColorFinder;
import org.opens.color.finder.onebyone.ColorFinderStepOnebyOne;

/**
 *
 * @author alingua
 */
public class ColorFinderFactoryImpl implements ColorFinderFactory {

    /**
     *
     * @param colorFinderType
     * @return a colorFinder instance regarding a given type
     */
    public ColorFinder getColorFinder(String colorFinderType) {
        if ("ONE_BY_ONE".equals(colorFinderType)) {
            return new ColorFinderStepOnebyOne();
        }
//        if ("DICHOTOMY".equals(colorFinderType)) {
//            return new ColorFinderDichotomy();
//        }
        return null;
    }
    
}