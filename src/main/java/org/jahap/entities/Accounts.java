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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "ACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a"),
    @NamedQuery(name = "Accounts.findById", query = "SELECT a FROM Accounts a WHERE a.id = :id"),
    @NamedQuery(name = "Accounts.findByBalance", query = "SELECT a FROM Accounts a WHERE a.balance = :balance"),
    @NamedQuery(name = "Accounts.findByCheckout", query = "SELECT a FROM Accounts a WHERE a.checkout = :checkout"),
    @NamedQuery(name = "Accounts.findByCheckindate", query = "SELECT a FROM Accounts a WHERE a.checkindate = :checkindate"),
    @NamedQuery(name = "Accounts.findByCheckoutdate", query = "SELECT a FROM Accounts a WHERE a.checkoutdate = :checkoutdate")})
public class Accounts implements Serializable, accounts_ie {
    @Column(name = "CHECKOUT")
    private boolean checkout;
    
    @OneToMany(mappedBy = "account")
    private Collection<Csc> cscCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "BALANCE")
    private double balance;
    @Size(max = 50)
    @Column(name = "CHECKINDATE")
    private String checkindate;
    @Size(max = 50)
    @Column(name = "CHECKOUTDATE")
    private String checkoutdate;
    @JoinColumn(name = "RESERVATION",referencedColumnName = "ID")
    @ManyToOne
    private Res reservation;
    
  
    
    @OneToMany(mappedBy = "account", targetEntity=AccountPosition.class)
    private Collection<AccountPosition> accountPositionCollection;
    @JoinColumn(name="ADDRESS", referencedColumnName = "ID")
    @ManyToOne
    private Address address; 
    
    
    @Override
    public Collection<AccountPosition> getAccountPositionCollection() {
        return accountPositionCollection;
    }
   
    @Override
    public void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection) {
        this.accountPositionCollection = accountPositionCollection;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Accounts() {
    }

    public Accounts(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String getCheckindate() {
        return checkindate;
    }

    @Override
    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    @Override
    public String getCheckoutdate() {
        return checkoutdate;
    }

    @Override
    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    @XmlTransient
    @Override
    public Res getReservation() {
        return reservation;
    }

    @Override
    public void setReservation(Res reservation) {
        this.reservation = reservation;
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
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Accounts[ id=" + id + " ]";
    }

    public boolean getCheckout() {
        return checkout;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

 
    @XmlTransient
    public Collection<Csc> getCscCollection() {
        return cscCollection;
    }

    public void setCscCollection(Collection<Csc> cscCollection) {
        this.cscCollection = cscCollection;
    }
    
}
