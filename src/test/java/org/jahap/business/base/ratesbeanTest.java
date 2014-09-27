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
package org.jahap.business.base;

import java.util.Collection;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Rates;
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
public class ratesbeanTest {
    
   public ratesbean instance;
    
    public ratesbeanTest() {
        instance = new ratesbean();
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
     * Test of createNewEmptyRecord method, of class ratesbean.
     */
    @Test
    public void testCreateNewEmptyRecord() {
        System.out.println("createNewEmptyRecord");
         
        instance.createNewEmptyRecord();
        // TODO review the generated test code and remove the default call to fail.
        instance.setCode("DBL");
        instance.setName("Double room logis");
        instance.setPrice(76.55);
        instance.setRevaccount((long) 4);
        instance.saveRecord();
        instance.quitDBaccess();
    }

    /**
     * Test of nextRecordBackward method, of class ratesbean.
     */
    
    public void testNextRecordBackward() {
        System.out.println("nextRecordBackward");
        ratesbean instance = new ratesbean();
        instance.nextRecordBackward();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextRecordForeward method, of class ratesbean.
     */
  
    public void testNextRecordForeward() {
        System.out.println("nextRecordForeward");
        ratesbean instance = new ratesbean();
        instance.nextRecordForeward();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveRecord method, of class ratesbean.
     */
   
    public void testSaveRecord() {
        System.out.println("saveRecord");
        ratesbean instance = new ratesbean();
        instance.saveRecord();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataRecordId method, of class ratesbean.
     */
   
    public void testSetDataRecordId() {
        System.out.println("setDataRecordId");
        Long id = null;
        ratesbean instance = new ratesbean();
        instance.setDataRecordId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataRecord method, of class ratesbean.
     */
    
    public void testGetDataRecord() {
        System.out.println("getDataRecord");
        Long id = null;
        ratesbean instance = new ratesbean();
        Rates expResult = null;
        Rates result = instance.getDataRecord(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quitDBaccess method, of class ratesbean.
     */
    
    public void testQuitDBaccess() {
        System.out.println("quitDBaccess");
        ratesbean instance = new ratesbean();
        instance.quitDBaccess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountPositionCollection method, of class ratesbean.
     */
   
    public void testGetAccountPositionCollection() {
        System.out.println("getAccountPositionCollection");
        ratesbean instance = new ratesbean();
        Collection expResult = null;
        Collection result = instance.getAccountPositionCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCode method, of class ratesbean.
     */
   
    public void testGetCode() {
        System.out.println("getCode");
        ratesbean instance = new ratesbean();
        String expResult = "";
        String result = instance.getCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class ratesbean.
     */
    
    public void testGetId() {
        System.out.println("getId");
        ratesbean instance = new ratesbean();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class ratesbean.
     */
    
    public void testGetName() {
        System.out.println("getName");
        ratesbean instance = new ratesbean();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class ratesbean.
     */
   
    public void testGetPrice() {
        System.out.println("getPrice");
        ratesbean instance = new ratesbean();
        double expResult = 0.0F;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccountPositionCollection method, of class ratesbean.
     */
   
    public void testSetAccountPositionCollection() {
        System.out.println("setAccountPositionCollection");
        Collection<AccountPosition> accountPositionCollection = null;
        ratesbean instance = new ratesbean();
        instance.setAccountPositionCollection(accountPositionCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCode method, of class ratesbean.
     */
   
    public void testSetCode() {
        System.out.println("setCode");
        String code = "";
        ratesbean instance = new ratesbean();
        instance.setCode(code);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class ratesbean.
     */
    
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        ratesbean instance = new ratesbean();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class ratesbean.
     */
    
    public void testSetPrice() {
        System.out.println("setPrice");
        float price = 0.0F;
        ratesbean instance = new ratesbean();
        instance.setPrice(price);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class ratesbean.
     */
   
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        ratesbean instance = new ratesbean();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
