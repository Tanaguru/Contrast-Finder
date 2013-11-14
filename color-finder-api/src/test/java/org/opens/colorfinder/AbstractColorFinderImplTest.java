/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder;

import java.awt.Color;
import junit.framework.TestCase;

/**
 *
 * @author alingua
 */
public class AbstractColorFinderImplTest extends TestCase {
    
    public AbstractColorFinderImplTest(String testName) {
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
     * Test of findColors method, of class AbstractColorFinderImpl.
     */
    public void testFindColorsWithValidContrastWithBackgroundTested() {
        System.out.println("findColorsWithValidContrastWithBackgroundTested");
        Color foregroundColor = null;
        Color backgroundColor = null;
        boolean isBackgroundTested = false;
        Float coefficientLevel = null;
        AbstractColorFinder instance = new AbstractColorFinderImplImpl();
//        instance.findColors(foregroundColor, backgroundColor, isBackgroundTested, coefficientLevel);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findColors method, of class AbstractColorFinderImpl.
     */
    public void testFindColorsWithValidContrastWithForegroundTested() {
        System.out.println("findColorsWithValidContrastWithForegroundTested");
        Color foregroundColor = null;
        Color backgroundColor = null;
        boolean isBackgroundTested = false;
        Float coefficientLevel = null;
        AbstractColorFinder instance = new AbstractColorFinderImplImpl();
//        instance.findColors(foregroundColor, backgroundColor, isBackgroundTested, coefficientLevel);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findColors method, of class AbstractColorFinderImpl.
     */
    public void testFindColorsWithInvalidContrastWithBackgroundTested() {
        System.out.println("findColorsWithInvalidContrastWithBackgroundTested");
        Color foregroundColor = null;
        Color backgroundColor = null;
        boolean isBackgroundTested = false;
        Float coefficientLevel = null;
        AbstractColorFinder instance = new AbstractColorFinderImplImpl();
//        instance.findColors(foregroundColor, backgroundColor, isBackgroundTested, coefficientLevel);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findColors method, of class AbstractColorFinderImpl.
     */
    public void testFindColorsWithInvalidContrastWithForegroundTested() {
        System.out.println("findColorsWithInvalidContrastWithForegroundTested");
        Color foregroundColor = null;
        Color backgroundColor = null;
        boolean isBackgroundTested = false;
        Float coefficientLevel = null;
        AbstractColorFinder instance = new AbstractColorFinderImplImpl();
//        instance.findColors(foregroundColor, backgroundColor, isBackgroundTested, coefficientLevel);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    

    /**
     * 
     * 
     */
    public class AbstractColorFinderImplImpl extends AbstractColorFinder {

        boolean isFindColorsCalled = false;
        
        public AbstractColorFinderImplImpl() {
            super(null, null);
        }

        public void findColors() {
            isFindColorsCalled = true;
        }

        @Override
        public String getColorFinderKey() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }
}
