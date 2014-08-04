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
package org.jahap.business.acc;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import static org.jahap.business.acc.accountspositionbeanTest.getDataSet;
import org.jahap.business.base.ratesbean;
import org.jahap.business.res.resbean;
import org.jahap.config.WriteConfig;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Bill;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Res;
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
public class accountsbeanTest {
    static accountsbean instance;
    static resbean instres;
    static ratesbean intrates;
    public accountsbeanTest() {
    }
    
    private static void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
       
        
  IDatabaseTester databaseTester = new JdbcDatabaseTester(
      "org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/JAHAPTEST;DB_CLOSE_DELAY=-1", "root", "gandhi");
  //databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
 
  DatabaseOperation.CLEAN_INSERT.execute(databaseTester.getConnection(), dataSet); 
  //databaseTester.setDataSet(dataSet);
  //databaseTester.onSetup();
  
 
  
}
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
         // JahapDatabaseConnector jh=new JahapDatabaseConnector();
        // EntityManagerFactory managerFactory = null;
  //  Map<String, String> persistenceMap = new HashMap<String, String>();
     
        
        
    /*
    persistenceMap.put("javax.persistence.jdbc.url", "jdbc:derby://localhost:1527/JAHAPTEST");
    persistenceMap.put("javax.persistence.jdbc.user", "root");
    persistenceMap.put("javax.persistence.jdbc.password", "gandhi");
    persistenceMap.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.ClientDriver");
   */
  //  managerFactory = Persistence.createEntityManagerFactory("JAHAPTEST", persistenceMap);
  //   JahapDatabaseConnector.setFactory(managerFactory);
        
     
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
      
    }
    
    @Before
    public void setUp() throws Exception 
    {
        WriteConfig instance1 = new WriteConfig();
        instance1.setPersistenceUnit("JAHAPTEST");
       
        instance1.setFile("config.xml");
        instance1.saveConfig();
        
            File F = new File("w");
        System.out.print(F.getAbsolutePath());
        IDataSet dataSet = getDataSet();
        System.out.print(dataSet.getTableNames());
         cleanlyInsertDataset(dataSet);
           instance = new accountsbean();
         intrates=new ratesbean();
        instres= new resbean(); 
    }

 protected static IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("full.xml")); //To change body of generated methods, choose Tools | Templates.
    }
    
    @After
    public void tearDown() throws Exception {
           File F = new File("w");
        System.out.print(F.getAbsolutePath());
        IDataSet dataSet = getDataSet();
        System.out.print(dataSet.getTableNames());
        cleanlyInsertDataset(dataSet);
        
        
      WriteConfig instance1 = new WriteConfig();
        instance1.setPersistenceUnit("JAHAP");
       
        instance1.setFile("config.xml");
        instance1.saveConfig();
        
       
    }

    /**
     * Test of SearchForOcc method, of class accountsbean.
     */
    
    public void testSearchForOcc() {
        System.out.println("SearchForOcc");
        String searchstring = "";
        accountsbean instance = new accountsbean();
        List expResult = null;
        List result = instance.SearchForAcc(searchstring);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNewEmptyRecord method, of class accountsbean.
     */
    @Test
    public void testCreateNewEmptyRecord() {
        System.out.println("createNewEmptyRecord");
         
        
        
        instance.createNewEmptyRecord();
        instance.setCheckin(false);
        instance.setBalance(34);
        instance.setCheckindate("11.09.2010");
        instance.setCheckoutdate("22.09.2377");
        instance.setCheckout(true);
        instance.addPosition(intrates.getDataRecord((long)1),1,77.99,"TestrunLogis");
        instance.setReservation(instres.getDataRecord(1));
        instance.saveRecord();
        
        
      
    }

    /**
     * Test of nextRecordBackward method, of class accountsbean.
     */
    
    public void testNextRecordBackward() {
        System.out.println("nextRecordBackward");
        accountsbean instance = new accountsbean();
        instance.nextRecordBackward();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextRecordForeward method, of class accountsbean.
     */
   
    public void testNextRecordForeward() {
        System.out.println("nextRecordForeward");
        accountsbean instance = new accountsbean();
        instance.nextRecordForeward();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveRecord method, of class accountsbean.
     */
    
    public void testSaveRecord() {
        System.out.println("saveRecord");
        accountsbean instance = new accountsbean();
        instance.saveRecord();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quitDBaccess method, of class accountsbean.
     */
    
    public void testQuitDBaccess() {
        System.out.println("quitDBaccess");
        accountsbean instance = new accountsbean();
        instance.quitDBaccess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPosition method, of class accountsbean.
     */
    @Test
    public void testAddPosition() {
        System.out.println("addPosition");
        instance.addPosition(intrates.getDataRecord((long)1),1,88.99,"TestrunLogis2");
        instance.addPosition(intrates.getDataRecord((long)2),1,12.50,"Buffet");
        
        
        
    }

   
    
    

    /**
     * Test of adjustPosition method, of class accountsbean.
     */
    @Test
    public void testAdjustPosition() {
        

        
        long idofPosition=3;
        AccountPosition accpos = new AccountPosition();
        accountsbean acc = new accountsbean();
        ratesbean rc = new ratesbean();
        accountspositionbean acp = new accountspositionbean();
        
        
        accpos.setPrice(119.0);
        accpos.setAmount(1);
        accpos.setPositionname("Deluxe Rate");
        accpos.setAccount(acc.getDataRecord(2));
        accpos.setRate(rc.getDataRecord((long) 1));
          
        
        // instance = new accountsbean();
           
        
        
       
        instance.adjustPosition(idofPosition,accpos);
        System.out.print(acp.getDataRecord(3).getRevenueCollection().toString());
        System.out.print(acp.getDataRecord(3).getVatCollection().toString());
        System.out.println("adjustPosition");
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calcBalance method, of class accountsbean.
     */
    //@Test
    public void testCalcBalance() {
        System.out.println("calcBalance");
        //accountsbean instance = new accountsbean();
        instance.moveToFirstRecord();
        instance.nextRecordForeward();
        instance.nextRecordForeward();
        instance.nextRecordForeward();
        instance.nextRecordForeward();
        double lp=instance.calcBalance();
        //assertNull(lp);
        System.out.println(lp);
        System.out.println(instance.getId());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    //@Test
    public void testCopyPositionsIntoThisAccount(){
          System.out.println("CopyPositionToAccount");
          instance.moveToFirstRecord();
         long gg[]=new long[1];
          gg[0]=2;
          
          instance.copyPositionsIntoThisAccount(2,gg);
          
          
    }
    
     //@Test
    public void testMovePositionsIntoThisAccount(){
           System.out.println("CopyPositionToAccount");
          instance.moveToFirstRecord();
         long gg[]=new long[1];
          gg[0]=2;
          
          instance.movePositionsIntoThisAccount(2, gg);
        
    }
    
    

    //@Test
    public void testReplicatePosition(){
           System.out.println("RepilicatePositions");
           instance.moveToFirstRecord();
           long accPos[]=new long[1];
           accPos[0]=instance.getAccountPositionCollection().iterator().next().getId();
           instance.replicatePosition(accPos);
                  
           
    }
    /**
     * Test of createBill method, of class accountsbean.
     */
    
    public void testCreateBill() {
        System.out.println("createBill");
        accountsbean instance = new accountsbean();
        instance.createBill();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copyPositonToBill method, of class accountsbean.
     */
    
    public void testCopyPositonToBill() {
        System.out.println("copyPositonToBill");
        accountsbean instance = new accountsbean();
        instance.copyPositonToBill();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markPositionAsBilled method, of class accountsbean.
     */
    
    public void testMarkPositionAsBilled() {
        System.out.println("markPositionAsBilled");
        accountsbean instance = new accountsbean();
        instance.markPositionAsBilled();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrintedAccountReport method, of class accountsbean.
     */
    
    public void testGetPrintedAccountReport() {
        System.out.println("getPrintedAccountReport");
        accountsbean instance = new accountsbean();
        instance.getPrintedAccountReport();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReportDataAccount method, of class accountsbean.
     */
   
    public void testGetReportDataAccount() {
        System.out.println("getReportDataAccount");
        accountsbean instance = new accountsbean();
        instance.getReportDataAccount();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountPositionCollection method, of class accountsbean.
     */
    
    public void testGetAccountPositionCollection() {
        System.out.println("getAccountPositionCollection");
        accountsbean instance = new accountsbean();
        Collection expResult = null;
        Collection result = instance.getAccountPositionCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBalance method, of class accountsbean.
     */
    
    public void testGetBalance() {
        System.out.println("getBalance");
        accountsbean instance = new accountsbean();
        double expResult = 0;
        double result = instance.getBalance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCheckin method, of class accountsbean.
     */
    
    public void testGetCheckin() {
        System.out.println("getCheckin");
        accountsbean instance = new accountsbean();
        Serializable expResult = null;
        Serializable result = instance.getCheckin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCheckindate method, of class accountsbean.
     */
   
    public void testGetCheckindate() {
        System.out.println("getCheckindate");
        accountsbean instance = new accountsbean();
        String expResult = "";
        String result = instance.getCheckindate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCheckout method, of class accountsbean.
     */
    
    public void testGetCheckout() {
        System.out.println("getCheckout");
        accountsbean instance = new accountsbean();
        Serializable expResult = null;
        Serializable result = instance.getCheckout();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCheckoutdate method, of class accountsbean.
     */
    
    public void testGetCheckoutdate() {
        System.out.println("getCheckoutdate");
        accountsbean instance = new accountsbean();
        String expResult = "";
        String result = instance.getCheckoutdate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class accountsbean.
     */
    
    public void testGetId() {
        System.out.println("getId");
        accountsbean instance = new accountsbean();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReservation method, of class accountsbean.
     */
    
    public void testGetReservation() {
        System.out.println("getReservation");
        accountsbean instance = new accountsbean();
        Res expResult = null;
        Res result = instance.getReservation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccountPositionCollection method, of class accountsbean.
     */
    
    public void testSetAccountPositionCollection() {
        System.out.println("setAccountPositionCollection");
        Collection<AccountPosition> accountPositionCollection = null;
        accountsbean instance = new accountsbean();
        instance.setAccountPositionCollection(accountPositionCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBalance method, of class accountsbean.
     */
    
    public void testSetBalance() {
        System.out.println("setBalance");
        Integer balance = null;
        accountsbean instance = new accountsbean();
        instance.setBalance(balance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBill method, of class accountsbean.
     */
    
    public void testSetBill() {
        System.out.println("setBill");
        Bill bill = null;
        accountsbean instance = new accountsbean();
        instance.setBill(bill);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCheckin method, of class accountsbean.
     */
    
    public void testSetCheckin() {
        System.out.println("setCheckin");
        Serializable checkin = null;
        accountsbean instance = new accountsbean();
        instance.setCheckin(checkin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCheckindate method, of class accountsbean.
     */
    
    public void testSetCheckindate() {
        System.out.println("setCheckindate");
        String checkindate = "";
        accountsbean instance = new accountsbean();
        instance.setCheckindate(checkindate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCheckout method, of class accountsbean.
     */
    
    public void testSetCheckout() {
        System.out.println("setCheckout");
        Serializable checkout = null;
        accountsbean instance = new accountsbean();
       // instance.setCheckout(checkout);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCheckoutdate method, of class accountsbean.
     */
   
    public void testSetCheckoutdate() {
        System.out.println("setCheckoutdate");
        String checkoutdate = "";
        accountsbean instance = new accountsbean();
        instance.setCheckoutdate(checkoutdate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReservation method, of class accountsbean.
     */
    
    public void testSetReservation() {
        System.out.println("setReservation");
        Res reservation = null;
        accountsbean instance = new accountsbean();
        instance.setReservation(reservation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
