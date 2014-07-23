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

package org.jahap.gui;

import java.util.List;

/**
 *
 * @author russ
 */
public class AccountViewer {
    
    private List<viewAccountPositionsProperty> accprop;
    private String billid;
    private boolean isBill;
    private boolean isAccount;

    public List<viewAccountPositionsProperty> getAccprop() {
        return accprop;
    }

    public void setAccprop(List<viewAccountPositionsProperty> accprop) {
        this.accprop = accprop;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public boolean isIsBill() {
        return isBill;
    }

    public void setIsBill(boolean isBill) {
        this.isBill = isBill;
    }

    public boolean isIsAccount() {
        return isAccount;
    }

    public void setIsAccount(boolean isAccount) {
        this.isAccount = isAccount;
    }
    
    
    
}
