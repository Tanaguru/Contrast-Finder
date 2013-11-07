/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.factory;

import org.opens.color.finder.hsv.ColorFinderHsv;
import org.opens.colorfinder.ColorFinder;

/**
 *
 * @author alingua
 */
public class ColorFinderHsvFactory implements ColorFinderFactory {

    public ColorFinder getColorFinder() {
        return new ColorFinderHsv();
    }
}