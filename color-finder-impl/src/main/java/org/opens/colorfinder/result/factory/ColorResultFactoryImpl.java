/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.result.factory;

import org.opens.colorfinder.result.ColorResult;
import org.opens.colorfinder.result.ColorResultImpl;

/**
 * 
 * @author alingua
 */
public class ColorResultFactoryImpl implements ColorResultFactory {

    @Override
    public ColorResult getColorResult() {
        ColorResult colorResult = new ColorResultImpl();
//        colorResult.setColor(foundColor);
//        colorResult.setComparisonColor(comparisonColor);
//        colorResult.setHexaColor(foundColor);
//        colorResult.setHexaColorComp(comparisonColor);
        return colorResult;
    }
//    @Override
//    public ColorResult getColorResult(Color foundColor, Color comparisonColor) {
//        ColorResult colorResult = new ColorResultImpl();
////        colorResult.setColor(foundColor);
////        colorResult.setComparisonColor(comparisonColor);
////        colorResult.setHexaColor(foundColor);
////        colorResult.setHexaColorComp(comparisonColor);
//        return colorResult;
//    }
    
}