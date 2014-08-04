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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "VAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vat.findAll", query = "SELECT v FROM Vat v"),
    @NamedQuery(name = "Vat.findById", query = "SELECT v FROM Vat v WHERE v.id = :id"),
    @NamedQuery(name = "Vat.findByAmount", query = "SELECT v FROM Vat v WHERE v.amount = :amount"),
    @NamedQuery(name = "Vat.findByDebit", query = "SELECT v FROM Vat v WHERE v.debit = :debit"),
    @NamedQuery(name = "Vat.findByDate", query = "SELECT v FROM Vat v WHERE v.date = :date")})
public class Vat implements Serializable, Vat_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "AMOUNT")
    private double amount;
    @Column(name = "DEBIT")
    private Boolean debit;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "VATTYPE", referencedColumnName = "ID")
    @ManyToOne
    private Vattype vattype;
    @JoinColumn(name = "ACCOUNTPOSITION", referencedColumnName = "ID")
    @ManyToOne
    private AccountPosition accountposition;
    

    public Vat() {
    }

    public Vat(Long id) {
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
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public Boolean getDebit() {
        return debit;
    }

    @Override
    public void setDebit(Boolean debit) {
        this.debit = debit;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public AccountPosition getAccountposition() {
        return accountposition;
    }

    @Override
    public void setAccountposition(AccountPosition accountposition) {
        this.accountposition = accountposition;
    }

    
    
    @Override
    public Vattype getVattype() {
        return vattype;
    }

    @Override
    public void setVattype(Vattype vattype) {
        this.vattype = vattype;
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
        if (!(object instanceof Vat)) {
            return false;
        }
        Vat other = (Vat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.Vat[ id=" + id + " ]";
    }
    
}
