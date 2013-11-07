/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.result;

import java.awt.Color;

/**
 *
 * @author alingua
 */
public interface ColorResult {
    /**
     * 
     * @return 
     */
   Color    getColor();
   
   /**
    * 
    * @param color 
    */
   void     setColor(Color color);
   
   /**
    * 
    * @return 
    */
   Double   getContrast();
   
   /**
    * 
    * @return 
    */
   Color   getComparisonColor();
   
   /**
    * 
    * @param color 
    */
   void     setComparisonColor(Color color);
}