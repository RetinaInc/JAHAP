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
package org.jahap.business.res;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.roomsbean;
import org.jahap.config.WriteConfig;

import org.jahap.entities.Address;
import org.jahap.entities.Occ;
import org.jahap.entities.Res;
import org.jahap.entities.Rooms;
import org.joda.time.DateTime;
import org.joda.time.Period;
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
public class occbeanTest {
    
    public occbeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        WriteConfig instance1 = new WriteConfig();
        instance1.setPersistenceUnit("JAHAPTEST");
       
        instance1.setFile("config.xml");
        instance1.saveConfig();
        
            File F = new File("w");
        System.out.print(F.getAbsolutePath());
        IDataSet dataSet = getDataSet();
        System.out.print(dataSet.getTableNames());
         cleanlyInsertDataset(dataSet);
        
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
    
     private static void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
       
        
  IDatabaseTester databaseTester = new JdbcDatabaseTester(
      "org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/JAHAPTEST;DB_CLOSE_DELAY=-1", "root", "gandhi");
  //databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
 
  DatabaseOperation.CLEAN_INSERT.execute(databaseTester.getConnection(), dataSet); 
  //databaseTester.setDataSet(dataSet);
  //databaseTester.onSetup();
  
 
  
}
    
    //@Test
    public void createNewOccRecord(){
       
         System.out.println("createNewOccRecord");
         occbean instance = new occbean();
         addressbean adds=new addressbean();
         resbean res=new resbean();
         roomsbean room=new roomsbean();
         DateTime arrivaldate = new DateTime();
         DateTime departuredate= new DateTime();
         departuredate=arrivaldate.plus(Period.days(2));
         
         instance.createNewEmptyRecord();
         instance.setArrivaldate(arrivaldate.toDate());
         instance.setDeparturedate(departuredate.toDate());
         instance.setGuest(adds.getDataRecord(1));
         instance.setRes(res.getDataRecord(1));
         instance.setRoom(room.getDataRecord((long)1));
         List<String>overlaps=new ArrayList<String>(); 
         overlaps=instance.saveRecord(true);
         assertEquals("Exits an overlaping res1",overlaps,null);
    }
    
    
    
    //@Test
    public void createSecondNewOccRecord(){
       
         System.out.println("createNewOccRecord");
         occbean instance = new occbean();
         addressbean adds=new addressbean();
         resbean res=new resbean();
         roomsbean room=new roomsbean();
         DateTime arrivaldate = new DateTime();
         DateTime departuredate= new DateTime();
         arrivaldate=arrivaldate.plus(Period.days(3));
         departuredate=arrivaldate.plus(Period.days(6));
         
         instance.createNewEmptyRecord();
         instance.setArrivaldate(arrivaldate.toDate());
         instance.setDeparturedate(departuredate.toDate());
         instance.setGuest(adds.getDataRecord(1));
         instance.setRes(res.getDataRecord(1));
         instance.setRoom(room.getDataRecord((long)1));
         List<String>overlaps=new ArrayList<String>(); 
         overlaps=instance.saveRecord(true);
         assertEquals("Exits an overlaping res2",overlaps,null);
    }
    
    
    //@Test
    public void createErrorOccRecord(){
       
         System.out.println("createNewOccRecord");
         occbean instance = new occbean();
         addressbean adds=new addressbean();
         resbean res=new resbean();
         roomsbean room=new roomsbean();
         DateTime arrivaldate = new DateTime();
         DateTime departuredate= new DateTime();
         departuredate=arrivaldate.plus(Period.days(2));
         
         instance.createNewEmptyRecord();
         instance.setArrivaldate(arrivaldate.toDate());
         instance.setDeparturedate(departuredate.toDate());
         instance.setGuest(adds.getDataRecord(1));
         instance.setRes(res.getDataRecord(1));
         instance.setRoom(room.getDataRecord((long)1));
         List<String>overlaps=new ArrayList<String>(); 
         overlaps=instance.saveRecord(true);
         assertEquals("Exits an overlaping res3",overlaps.size(),1); 
    }
    
    
    
    /**
     * Test of SearchForOcc method, of class occbean.
     */
  
    public void testSearchForOcc() {
        System.out.println("SearchForOcc");
        String searchstring = "";
        occbean instance = new occbean();
        List expResult = null;
        List result = instance.SearchForOcc(searchstring);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SearchForOccforRes method, of class occbean.
     */
   
    public void testSearchForOccforRes() {
        System.out.println("SearchForOccforRes");
        Res res = null;
        occbean instance = new occbean();
        List expResult = null;
        List result = instance.SearchForOccforRes(res);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNewEmptyRecord method, of class occbean.
     */
    
    public void testCreateNewEmptyRecord() {
        System.out.println("createNewEmptyRecord");
        occbean instance = new occbean();
        instance.createNewEmptyRecord();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CheckForOverlappingReservations method, of class occbean.
     */
    
    public void testCheckForOverlappingReservations() {
        System.out.println("CheckForOverlappingReservations");
        occbean instance = new occbean();
        List expResult = null;
        List result = instance.CheckForOverlappingReservations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextRecordBackward method, of class occbean.
     */
    
    public void testNextRecordBackward() {
        System.out.println("nextRecordBackward");
        occbean instance = new occbean();
        instance.nextRecordBackward();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextRecordForeward method, of class occbean.
     */
  
    public void testNextRecordForeward() {
        System.out.println("nextRecordForeward");
        occbean instance = new occbean();
        instance.nextRecordForeward();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveRecord method, of class occbean.
     */
    
    public void testSaveRecord_0args() {
        System.out.println("saveRecord");
        occbean instance = new occbean();
        instance.saveRecord();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveRecord method, of class occbean.
     */
    
    public void testSaveRecord_boolean() {
        System.out.println("saveRecord");
        boolean test = false;
        occbean instance = new occbean();
        List expResult = null;
        List result = instance.saveRecord(test);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class occbean.
     */
    
    public void testGetId() {
        System.out.println("getId");
        occbean instance = new occbean();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataRecordId method, of class occbean.
     */
   
    public void testSetDataRecordId() {
        System.out.println("setDataRecordId");
        Long id = null;
        occbean instance = new occbean();
        instance.setDataRecordId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataRecord method, of class occbean.
     */
 
    public void testGetDataRecord() {
        System.out.println("getDataRecord");
        long id = 0L;
        occbean instance = new occbean();
        Occ expResult = null;
        Occ result = instance.getDataRecord(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArrivaltime method, of class occbean.
     */
   
    public void testGetArrivaltime() {
        System.out.println("getArrivaltime");
        occbean instance = new occbean();
        Date expResult = null;
        Date result = instance.getArrivaltime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDeparturetime method, of class occbean.
     */
    
    public void testGetDeparturetime() {
        System.out.println("getDeparturetime");
        occbean instance = new occbean();
        Date expResult = null;
        Date result = instance.getDeparturetime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRes method, of class occbean.
     */
    
    public void testGetRes() {
        System.out.println("getRes");
        occbean instance = new occbean();
        Res expResult = null;
        Res result = instance.getRes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoom method, of class occbean.
     */
    
    public void testGetRoom() {
        System.out.println("getRoom");
        occbean instance = new occbean();
        Rooms expResult = null;
        Rooms result = instance.getRoom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArrivaldate method, of class occbean.
     */
   
    public void testGetArrivaldate() {
        System.out.println("getArrivaldate");
        occbean instance = new occbean();
        Date expResult = null;
        Date result = instance.getArrivaldate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDeparturedate method, of class occbean.
     */
   
    public void testGetDeparturedate() {
        System.out.println("getDeparturedate");
        occbean instance = new occbean();
        Date expResult = null;
        Date result = instance.getDeparturedate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRes method, of class occbean.
     */
   
    public void testSetRes() {
        System.out.println("setRes");
        Res res = null;
        occbean instance = new occbean();
        instance.setRes(res);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoom method, of class occbean.
     */
    
    public void testSetRoom() {
        System.out.println("setRoom");
        Rooms room = null;
        occbean instance = new occbean();
        instance.setRoom(room);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArrivaldate method, of class occbean.
     */
    
    public void testSetArrivaldate_Date() {
        System.out.println("setArrivaldate");
        Date arrivaldate = null;
        occbean instance = new occbean();
        instance.setArrivaldate(arrivaldate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArrivaldate method, of class occbean.
     */
    
    public void testSetArrivaldate_String() {
        System.out.println("setArrivaldate");
        String arrivaldate = "";
        occbean instance = new occbean();
        instance.setArrivaldate(arrivaldate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDeparturedate method, of class occbean.
     */
    
    public void testSetDeparturedate_Date() {
        System.out.println("setDeparturedate");
        Date departuredate = null;
        occbean instance = new occbean();
        instance.setDeparturedate(departuredate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDeparturedate method, of class occbean.
     */
    
    public void testSetDeparturedate_String() {
        System.out.println("setDeparturedate");
        String departuredate = "";
        occbean instance = new occbean();
        instance.setDeparturedate(departuredate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArrivaltime method, of class occbean.
     */
    
    public void testSetArrivaltime_Date() {
        System.out.println("setArrivaltime");
        Date arrivaltime = new Date();
        occbean instance = new occbean();
        instance.setArrivaltime(arrivaltime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArrivaltime method, of class occbean.
     */
   
    public void testSetArrivaltime_String() {
        System.out.println("setArrivaltime");
        String arrivaltime = "";
        occbean instance = new occbean();
        instance.setArrivaltime(arrivaltime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDeparturetime method, of class occbean.
     */
   
    public void testSetDeparturetime_Date() {
        System.out.println("setDeparturetime");
        Date departuretime = null;
        occbean instance = new occbean();
        instance.setDeparturetime(departuretime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDeparturetime method, of class occbean.
     */
   
    public void testSetDeparturetime_String() {
        System.out.println("setDeparturetime");
        String departuretime = "";
        occbean instance = new occbean();
        instance.setDeparturetime(departuretime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuest method, of class occbean.
     */
   
    public void testGetGuest() {
        System.out.println("getGuest");
        occbean instance = new occbean();
        Address expResult = null;
        Address result = instance.getGuest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGuest method, of class occbean.
     */
    
    public void testSetGuest() {
        System.out.println("setGuest");
        Address guest = null;
        occbean instance = new occbean();
        instance.setGuest(guest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}