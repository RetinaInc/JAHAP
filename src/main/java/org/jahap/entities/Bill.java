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
package org.jahap.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "BILL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
    @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id"),
  
    @NamedQuery(name = "Bill.findByBillno", query = "SELECT b FROM Bill b WHERE b.billno = :billno"),

   

    @NamedQuery(name = "Bill.findByBilldate", query = "SELECT b FROM Bill b WHERE b.billdate = :billdate")})
public class Bill implements Serializable, Bill_ie {
    @Size(max = 100)
    @Column(name = "BILLNAME")
    private String billname;
    @Column(name = "CANCELED")
    private Boolean canceled;
    @Column(name = "TEMP_BILL")
    private Boolean temp_bill;
    @Column(name = "CANCELEDBILL")
    private long canceledbill;
    @Column(name = "TOTAL")
    private double total;
    @Column(name="BILLNOSTRING")
    private String billnostring;
    @Column(name="UUID")
    private String UUID;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name="BILLCHANGE")
    private java.sql.Timestamp billchange;
    
    @Column(name = "BILLNO")
    private long billno;

 
    @Column(name = "BILLDATE")
    @Temporal(TemporalType.DATE)
    private Date billdate;
    @JoinColumn(name = "ADDRESS", referencedColumnName = "ID")
    @ManyToOne
    private Address address;
    
    @OneToMany
    @JoinColumn(name="BILL", referencedColumnName="ID")
    private Collection<AccountPosition> accountPositionCollection;
    
    public Bill() {
    }

    public Bill(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Timestamp getBillchange() {
        return billchange;
    }

    public void setBillchange(Timestamp billchange) {
        
        this.billchange = billchange;
    }

     
    public void setId(Long id) {
        this.id = id;
    }

     

    @Override
    public long getBillno() {
        return billno;
    }

    
    public void setBillno(long billno) {
        this.billno = billno;
    }
  
    @Override
    public Collection<AccountPosition>getAccountPositionCollection() {
        return accountPositionCollection;
    }

    @Override
    public void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection) {
        this.accountPositionCollection = accountPositionCollection;
    }
  


    @Override
    public Date getBilldate() {
        return billdate;
    }

    @Override
    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Bill[ id=" + id + " ]";
    }

    public Boolean isTemp_bill() {
        return temp_bill;
    }

    public void setTemp_bill(Boolean temp_bill) {
        this.temp_bill = temp_bill;
    }

    @Override
    public String getBillname() {
        return billname;
    }

    @Override
    public void setBillname(String billname) {
        this.billname = billname;
    }

    @Override
    public Boolean getCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public long getCanceledbill() {
        return canceledbill;
    }

    @Override
    public void setCanceledbill(long canceledbill) {
        this.canceledbill = canceledbill;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public void setTotal(double total) {
        this.total = total;
    }

   

    public String getBillNoString() {
       return this.billnostring;
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setBillNoString(String billno) {
       this.billnostring=billno;
    }

    public void setUUID(String UUID) {
        this.UUID=UUID;
    }
    
}
