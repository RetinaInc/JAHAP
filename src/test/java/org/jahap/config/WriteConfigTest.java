/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jahap.config;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russ
 */
public class WriteConfigTest {
    
    public WriteConfigTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setFile method, of class WriteConfig.
     */
    
    public void testSetFile() {
        System.out.println("setFile");
        String configFile = "";
        WriteConfig instance = new WriteConfig();
        instance.setFile(configFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersistenceUnit method, of class WriteConfig.
     */
    
    public void testGetPersistenceUnit() {
        System.out.println("getPersistenceUnit");
        WriteConfig instance = new WriteConfig();
        String expResult = "";
        String result = instance.getPersistenceUnit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPersistenceUnit method, of class WriteConfig.
     */
    
    public void testSetPersistenceUnit() {
        System.out.println("setPersistenceUnit");
        String PersistenceUnit = "";
        WriteConfig instance = new WriteConfig();
        instance.setPersistenceUnit(PersistenceUnit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveConfig method, of class WriteConfig.
     */
    @Test
    public void testSaveConfig() throws Exception {
        System.out.println("saveConfig");
        WriteConfig instance = new WriteConfig();
        instance.setPersistenceUnit("JAHAPTEST");
       
        instance.setFile("config.xml");
        instance.saveConfig();
        
        instance.saveConfig();
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
