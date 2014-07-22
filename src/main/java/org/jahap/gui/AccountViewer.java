/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
