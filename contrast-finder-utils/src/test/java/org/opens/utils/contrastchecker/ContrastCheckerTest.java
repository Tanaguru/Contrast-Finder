/*
 * Contrast Finder
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */ 

package org.opens.utils.contrastchecker;

import java.awt.Color;
import java.text.DecimalFormat;
import junit.framework.TestCase;

/**
 *
 * @author alingua
 */
public class ContrastCheckerTest extends TestCase {
    
    public ContrastCheckerTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
    }
    
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
