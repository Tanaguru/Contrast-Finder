/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.factory;

import org.opens.color.finder.onebyone.ColorFinderStepOnebyOne;
import org.opens.colorfinder.ColorFinder;

/**
 *
 * @author alingua
 */
public class ColorFinderStepOneByOneFactory implements ColorFinderFactory {

    public ColorFinder getColorFinder() {
        return new ColorFinderStepOnebyOne();
    }

}