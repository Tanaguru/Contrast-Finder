/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.color.finder.webservice;

import java.util.ArrayList;
import java.util.Collection;
import org.opens.colorfinder.result.ColorCombinaison;
import org.opens.colorfinder.result.ColorResult;

/**
 *
 * @author alingua
 */
public class JsonColorResult {
 
    ColorResult colorResult;
    public JsonColorResult(ColorResult colorResult) {
        this.colorResult = colorResult;
    }

    public JsonColorCombinaison getSubmittedCombinaisonColor() {
        return new JsonColorCombinaison(colorResult.getSubmittedCombinaisonColor());
    }

    public boolean isCombinaisonValid() {
        return colorResult.isCombinaisonValid();
    }

    public Collection<JsonColorCombinaison> getSuggestedColors() {
        Collection<JsonColorCombinaison> result = new ArrayList<JsonColorCombinaison>();
        for (ColorCombinaison cc : colorResult.getSuggestedColors()) {
            result.add(new JsonColorCombinaison(cc));
        }
        return result;
    }

    public int getNumberOfSuggestedColors() {
        return colorResult.getNumberOfSuggestedColors();
    }

    public Float getThreashold() {
        return colorResult.getThreashold();
    }
}