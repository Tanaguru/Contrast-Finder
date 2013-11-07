/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.utils.contrastchecker;

import org.opens.utils.contrastchecker.ContrastChecker;
import java.awt.Color;
import java.text.DecimalFormat;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author alingua
 */
public class ContrastCheckerTest extends TestCase {
    
    public ContrastCheckerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getConstrastRatio method, of class ContrastChecker.
     */
    public void testGetConstrastRatio() {
        System.out.println("getConstrastRatio");
        
        DecimalFormat df = new DecimalFormat("#.##");
        
        Color fgColor = new Color(70, 136, 71);
        Color bgColor = new Color(223, 240, 216);
//        ContrastChecker instance = new ContrastChecker();

        
        
        double result = ContrastChecker.getConstrastRatio(bgColor, fgColor);
        System.out.println("result :" + result);
//        assertEquals("21", df.format(result));
//        
//        bgColor = new Color(255, 255, 255);
//        fgColor = new Color(0, 0, 0);
////        instance = new ContrastChecker();
//
//        result = ContrastChecker.getConstrastRatio(fgColor, bgColor);
//        System.out.println("result :" + result);
//        assertEquals("21", String.format("%.2g", result));
//        
//        fgColor = new Color(255, 53, 255);
//        bgColor = new Color(18, 52, 95);
//
//        result = ContrastChecker.getConstrastRatio(fgColor, bgColor);
//        System.out.println("result :" + result);
//        assertEquals("4,28", df.format(result));
//        
//        bgColor = new Color(255, 53, 255);
//        fgColor = new Color(18, 52, 95);
//
//        result = ContrastChecker.getConstrastRatio(fgColor, bgColor);
//        System.out.println("result :" + result);
//        assertEquals("4.28", String.format("%.3g", result));
//        
//        bgColor = new Color(255, 255, 255);
//        fgColor = new Color(255, 255, 255);
//
//        result = ContrastChecker.getConstrastRatio(fgColor, bgColor);
//        System.out.println("result :" + result);
//        assertEquals("1.00", String.format("%.3g", result));
//
//        bgColor = new Color(53, 53, 53);
//        fgColor = new Color(53, 53, 53);
//
//        result = ContrastChecker.getConstrastRatio(fgColor, bgColor);
//        System.out.println("result :" + result);
//        assertEquals("1.00", String.format("%.3g", result));
        
    }
}
