/*
 * The MIT License
 *
 * Copyright 2014 Open Jahap Community.
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

package org.jahap.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.shape.Rectangle;
import org.apache.log4j.Logger;
import org.jahap.business.base.roomsbean;
import org.jahap.business.res.occbean;
import org.jahap.business.res.resbean;
import org.jahap.entities.Occ;
import org.jahap.entities.Res;
import org.jahap.entities.Rooms;

/**
 * FXML Controller class
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
public class occplan implements Initializable {
    static Logger log = Logger.getLogger(occplan.class.getName());
    @FXML
    private DatePicker from_datepicker;
    @FXML
    private DatePicker to_datepicker;
    @FXML
    private AnchorPane occpanel_anchorpane;
    @FXML
    private Label box_label;

    //********************  OCC GRID **************** 
    
    class occgrid extends Label{
            private String idxHeader;
            private String idyHeader;
            private int idxNumeric;
            private int idyNumeric;
        
         public double getXPosAtPercentOf(int PercentOfBox){
        double XPos=0;
        float hg;
        Dimension j = new Dimension();
             log.debug("Function entry " + String.valueOf(box_label.getPrefWidth())+ "  " + String.valueOf(box_label.getPrefHeight()) );
        j.setSize(box_label.getPrefWidth(),box_label.getPrefHeight());
      
        hg=j.width;
        hg=hg/100;        
        hg=hg*PercentOfBox;
        XPos=Math.round(hg);
        XPos=XPos+this.getLayoutX();
        
        return XPos;
    }
    
     public double getYPosAtPercentOf(int PercentOfBox){
        double YPos;
        // ######## not  yet implemented
        
        //YPos=this.getSize().width/100*PercentOfBox;
        //YPos=YPos+this.getLocation().x;
        YPos=this.getLayoutY();
        return YPos;
    }
        
     
      public String getIdxHeader() {
        return idxHeader;
    }

    public void setIdxHeader(String idxHeader) {
        this.idxHeader = idxHeader;
    }

    public String getIdyHeader() {
        return idyHeader;
    }

    public void setIdyHeader(String idyHeader) {
        this.idyHeader = idyHeader;
    }

    public int getIdxNumeric() {
        return idxNumeric;
    }

    public void setIdxNumeric(int idxNumeric) {
        this.idxNumeric = idxNumeric;
    }

    public int getIdyNumeric() {
        return idyNumeric;
    }

    public void setIdyNumeric(int idyNumeric) {
        this.idyNumeric = idyNumeric;
    }
     
     
  }
    
    
    
    //******************* OCC BOX **************************
    
    /**
     *
     */
        
   public class occbox extends Label {
    
    private String id;

   
      private String xPosStartByXHeader;
    private String yPosStartByYHeader;
    private int xPosStartByXHeaderPercentOfbox=50;
    private String xPosEndByXHeader;
    private String yPosEndByYHeader;
    private int yPosEndByYHeaderPercentOfbox=50;

        /**
         *
         * @param xPosStartByXHeader
         * @param xPosEndByXHeader
         * @param yPosStartByYHeader
         * @param text
         */
        public occbox(String xPosStartByXHeader,String xPosEndByXHeader, String yPosStartByYHeader,String text ){
        this.xPosStartByXHeader=xPosStartByXHeader;
        this.xPosEndByXHeader=xPosEndByXHeader;
        this.yPosStartByYHeader=yPosStartByYHeader;
        this.xPosStartByXHeaderPercentOfbox=50;
        this.setText(text);
    }

        /**
         *
         * @return
         */
        public String getxPosStartByXHeader() {
        return xPosStartByXHeader;
    }

        /**
         *
         * @param xPosStartByXHeader
         */
        public void setxPosStartByXHeader(String xPosStartByXHeader) {
        this.xPosStartByXHeader = xPosStartByXHeader;
    }

        /**
         *
         * @return
         */
        public String getyPosStartByYHeader() {
        return yPosStartByYHeader;
    }

        /**
         *
         * @param yPosStartByYHeader
         */
        public void setyPosStartByYHeader(String yPosStartByYHeader) {
        this.yPosStartByYHeader = yPosStartByYHeader;
    }

        /**
         *
         * @return
         */
        public int getxPosStartByXHeaderPercentOfbox() {
        return xPosStartByXHeaderPercentOfbox;
    }

        /**
         *
         * @param xPosStartByXHeaderPercentOfbox
         */
        public void setxPosStartByXHeaderPercentOfbox(int xPosStartByXHeaderPercentOfbox) {
        this.xPosStartByXHeaderPercentOfbox = xPosStartByXHeaderPercentOfbox;
    }

        /**
         *
         * @return
         */
        public String getxPosEndByXHeader() {
        return xPosEndByXHeader;
    }

        /**
         *
         * @param xPosEndByXHeader
         */
        public void setxPosEndByXHeader(String xPosEndByXHeader) {
        this.xPosEndByXHeader = xPosEndByXHeader;
    }

        /**
         *
         * @return
         */
        public String getyPosEndByYHeader() {
        return yPosEndByYHeader;
    }

        /**
         *
         * @param yPosEndByYHeader
         */
        public void setyPosEndByYHeader(String yPosEndByYHeader) {
        this.yPosEndByYHeader = yPosEndByYHeader;
    }

        /**
         *
         * @return
         */
        public int getyPosEndByYHeaderPercentOfbox() {
        return yPosEndByYHeaderPercentOfbox;
    }

        /**
         *
         * @param yPosEndByYHeaderPercentOfbox
         */
        public void setyPosEndByYHeaderPercentOfbox(int yPosEndByYHeaderPercentOfbox) {
        this.yPosEndByYHeaderPercentOfbox = yPosEndByYHeaderPercentOfbox;
    }

        /**
         *
         * @return
         */
        public String getObjId() {
        return id;
    }

        /**
         *
         * @param id
         */
        public void setObjId(String id) {
        this.id = id;
    }

   
    
} 
    
  // -------------- defs ------------- 
    
     private int xPos=1;
    private int yPos=50;
    private int xDim=40;
    private int yDim=20;
    private int spacingX=1;
    private int spacingY=1;
    private List<String>xheader;
    private List<String>yheader;
    private List<occbox>OccDataList;
    
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
   

    /**
     *
     * @param xheader
     * @param yheader
     * @param OccDataList
     */
    public void occplandraw(List<String>xheader,List<String>yheader,List<occbox>OccDataList) {
       // List<occbox>OccDataList
       // initComponents();
       
       // ***********Date converting: View Date *************
        this.xheader=new ArrayList<String>(); 
       this.yheader=new ArrayList<String>(); 
        this.xheader=xheader;
        this.yheader=yheader;
        this.OccDataList=OccDataList;
       // this.OccDataList=OccDataList;
        drawMainSheet();
    }
    
    /**
     *
     */
    public void  drawMainSheet(){
        List<Label> labels= new ArrayList<Label>(); 
        List<occgrid> grid= new ArrayList<occgrid>();
        Dimension dim=new Dimension();
        Point root,next,lastbox;
         root=new Point();
         root.x=xPos;
        root.y=yPos;
         lastbox=new Point(1,1);
        lastbox.x=root.x;
        lastbox.y=root.y;
        next= new Point(1,1);
        
        
        
        next.x=root.x;  // Set First Box Startposition
        next.y=root.y;
        
        
        
        dim.setSize(xDim, yDim);  // Set Standard box size
        
        
        
        
        
        
        // Draw Header
        int xcounter=0;
        Label jLabel2, jLabel3;
        
        // First Empty Box
        
         jLabel2 = new Label();
         
         jLabel2.setLayoutX(next.x);
         jLabel2.setLayoutY(next.y);
         
         jLabel2.setPrefWidth(dim.getWidth());
         jLabel2.setPrefHeight(dim.getHeight());
         //jLabel2.setBorder();
         jLabel2.setVisible(true);
         occpanel_anchorpane.getChildren().add(jLabel2);
         
         
         labels.add(jLabel2);
        
        
        // Draw X-Header
        for (xcounter=0;xcounter<=xheader.size()-1;xcounter++){
           
               next.x=lastbox.x+dim.width+1;
                jLabel2 = new Label();
                jLabel2.setRotate(90);
                jLabel2.setLayoutX(next.x);
                 jLabel2.setLayoutY(next.y);

                 jLabel2.setMinWidth(dim.getWidth());
                 jLabel2.setMinHeight(dim.getHeight());
                
                jLabel2.setStyle("-fx-border-color: black");
                jLabel2.setVisible(true);
                jLabel2.setText(xheader.get(xcounter));
                lastbox=next;
                occpanel_anchorpane.getChildren().add(jLabel2);
                labels.add(jLabel2);
        
        }
        
        
        // Draw Y-Header
       
               next.x=root.x;  // Reset to firstbox
               next.y=root.y;
        for (xcounter=0;xcounter<=yheader.size()-1;xcounter++){
                 
                next.y=lastbox.y+dim.height+1;
                 jLabel2 = new Label();
                jLabel2.setLayoutX(next.x);
                 jLabel2.setLayoutY(next.y);

                 jLabel2.setMinWidth(dim.getWidth());
                 jLabel2.setMinHeight(dim.getHeight());
                
                jLabel2.setStyle("-fx-border-color: black");
                jLabel2.setVisible(true);
                jLabel2.setText(yheader.get(xcounter));
                lastbox=next;
                occpanel_anchorpane.getChildren().add(jLabel2);
                labels.add(jLabel2);
        
        }
         
                 
                 
         
        
         // Draw Grid
        
         // Reset to firstbox
        next.y=root.y + dim.height+1;
       
        log.debug("Function entry create Gridbox");    
        occgrid gridbox;
        int i,j;
        for (j=0;j<=yheader.size()-1;j++){
            for(i=0;i<=xheader.size()-1;i++){
                next.x=lastbox.x+dim.width+1;
                gridbox = new occgrid();
                gridbox.setLayoutX(next.x);
                gridbox.setLayoutY(next.y);
                gridbox.setMinWidth(dim.getWidth());   
                gridbox.setMinHeight(dim.getHeight());
                
                gridbox.setStyle("-fx-border-color: black");
                gridbox.setVisible(true);
                gridbox.setIdxHeader(xheader.get(i));
                gridbox.setIdyHeader(yheader.get(j));
                lastbox=next;
                
                occpanel_anchorpane.getChildren().add(gridbox);
                //labels.add(jLabel2);
                grid.add(gridbox);
                }
            next.y=lastbox.y+dim.height+1;
            next.x=root.x;
            
        } 
        
        int occcounter,occsearch,searchStartposX=0,searchStartposY=0,searchEndposX=0,searchEndposY=0;
        
        
        for(occcounter=0;occcounter<=OccDataList.size()-1;occcounter++){
              // Get Start pos from xheader   
              for(occsearch=0;occsearch<=xheader.size()-1;occsearch++){
                  if (xheader.get(occsearch)==OccDataList.get(occcounter).getxPosStartByXHeader()){
                      searchStartposX=occsearch;
                  }
              }
               for(occsearch=0;occsearch<=yheader.size()-1;occsearch++){
                  if (yheader.get(occsearch)==OccDataList.get(occcounter).getyPosStartByYHeader()){
                      searchStartposY=occsearch;
                        }   
               }
               // Get End pos from Header  
                  
                  for(occsearch=0;occsearch<=xheader.size()-1;occsearch++){
                  if (xheader.get(occsearch)==OccDataList.get(occcounter).getxPosEndByXHeader()){
                      searchEndposX=occsearch;
                  }
                  }
               for(occsearch=0;occsearch<=yheader.size()-1;occsearch++){
                  if (yheader.get(occsearch)==OccDataList.get(occcounter).getyPosEndByYHeader()){
                      searchEndposY=occsearch;
                  }  
               }
                  
                  double wideness=0,startx=0,starty=0;
                  int recordSetStart=0,recordSetEnd=0;
                 double endx=0, endy=0;
                  
                  recordSetStart=searchStartposX;
                  if(searchStartposX!=0 || searchStartposY!=0){
                        recordSetStart=searchStartposX+xheader.size()*searchStartposY;
                  }
                  
                  recordSetEnd=searchEndposX;
                  if(searchEndposX!=0){
                        recordSetEnd=searchEndposX+xheader.size()*searchEndposY;
                  }
                  
                  
                  startx=grid.get(recordSetStart).getXPosAtPercentOf(OccDataList.get(occcounter).getxPosStartByXHeaderPercentOfbox());
                  starty=grid.get(recordSetStart).getLayoutY();
                  endx=grid.get(recordSetEnd).getXPosAtPercentOf(OccDataList.get(occcounter).getxPosStartByXHeaderPercentOfbox());
                  
                  
                 
                  OccDataList.get(occcounter).setLayoutX(startx);
                  OccDataList.get(occcounter).setLayoutY(starty);
                  wideness=endx-startx;
                 
                  OccDataList.get(occcounter).setMinWidth(wideness); // Standard Y Size
                  OccDataList.get(occcounter).setMinHeight(yDim);
                  
                  OccDataList.get(occcounter).setStyle("-fx-border-color: black");
                  OccDataList.get(occcounter).setVisible(true);
                  OccDataList.get(occcounter).setStyle("-fx-background-color:red");
                 //OccDataList.get(occcounter).setOpaque(true);
                 // OccDataList.get(occcounter).addMouseListener(OccDataList.get(occcounter).getClass());
                  
                   occpanel_anchorpane.getChildren().add(OccDataList.get(occcounter));
                  
                          
                  
                    
                  
                  
              }
                  
        
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       
       roomsbean roomsbean=new roomsbean(); 
       occbean occl= new occbean();
       List<Occ>occs=new ArrayList<>();
       List<Rooms>rooms=new ArrayList<>();
       LocalDate today=LocalDate.now();
       List<String>xheader=new ArrayList<>();
       List<String>yheader=new ArrayList<>();
       List<occbox>occboxes=new ArrayList<>();
       xheader.add(today.toString());
       for(int k=1;k<31;k++){
           xheader.add(today.plusDays(k).toString());
       }
       rooms=roomsbean.SearchForRooms(null);
       for(Rooms k:rooms){
           yheader.add(k.getCode());
       }
      
       occs=occl.SearchForOcc(null);
       for(Occ rs:occs){
          occbox kk= new occbox(rs.getArrivaldate().toString(), rs.getDeparturedate().toString(),rs.getRoom().getCode(), rs.getGuest().getName());
           occboxes.add(kk);
       }
       
         occplandraw(xheader, yheader, occboxes);
       
    }
    
    public void init(){
        System.out.print("wwwww");
    }
}
