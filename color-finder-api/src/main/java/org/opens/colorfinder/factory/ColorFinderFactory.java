package org.opens.colorfinder.factory;

import org.opens.colorfinder.ColorFinder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * 
 * @author alingua
 */
public interface ColorFinderFactory {
    
    /**
     * 
     * @return a colorFinder instance regarding a given type
     */
     ColorFinder getColorFinder();

}