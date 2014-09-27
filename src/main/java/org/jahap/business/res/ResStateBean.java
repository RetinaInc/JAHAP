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

import java.util.ArrayList;
import java.util.List;
import org.jahap.business.base.DatabaseOperations;
import org.jahap.entities.resstate;

/**
 *
 * @author russ
 */
public class ResStateBean extends DatabaseOperations  implements resstate_i{

 

    private static List<resstate> ressttatelist;
    
    public ResStateBean() {
        ressttatelist=new ArrayList<resstate>();   
        resstate zw=new resstate();
        zw.setState("option");
        ressttatelist.add(zw);
        
        zw=new resstate();
        zw.setState("confirmed");
        ressttatelist.add(zw);
        
        zw=new resstate();
        zw.setState("cancel");
        ressttatelist.add(zw);
        
        
    }
    
    public List<resstate>SearchForState(String searchstring){
        return this.ressttatelist;
    }
    
    
    public void createNewEmptyRecord() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nextRecordBackward() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nextRecordForeward() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void saveRecord() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void quitDBaccess() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getState() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setState(String state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
