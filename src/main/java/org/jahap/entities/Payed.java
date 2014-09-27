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
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "PAYED")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payed.findAll", query = "SELECT p FROM Payed p"),
    @NamedQuery(name = "Payed.findById", query = "SELECT p FROM Payed p WHERE p.id = :id"),
    @NamedQuery(name = "Payed.findByDebit", query = "SELECT p FROM Payed p WHERE p.debit = :debit")})
    public class Payed implements Serializable, Payed_ie {
    @Column(name = "DEBIT")
    private boolean debit;
    @Column(name = "TOTAL")
    private double total;
    @Column(name = "OPENPOS")
    private boolean openpos;
    @Column(name = "CANCELED")
    private boolean canceled;
    @Column(name = "CANCELEDPAYMENT")
    private long canceledpayment;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
   
    @JoinColumn(name = "PAYMENTTYPE", referencedColumnName = "ID")
    @ManyToOne
    private Paymenttypes paymenttype;

    public Payed() {
    }

    public Payed(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


  

    @Override
    public Paymenttypes getPaymenttype() {
        return paymenttype;
    }

    @Override
    public void setPaymenttype(Paymenttypes paymenttype) {
        this.paymenttype = paymenttype;
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
        if (!(object instanceof Payed)) {
            return false;
        }
        Payed other = (Payed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public boolean isCanceled() {
        return canceled;
    }
    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
    @Override
    public long getCanceledpayment() {
        return canceledpayment;
    }
    @Override
    public void setCanceledpayment(long conceledpayment) {
        this.canceledpayment = conceledpayment;
    }
    
    
    
    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Payed[ id=" + id + " ]";
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
    public double getTotal() {
        return total;
    }

    @Override
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean getOpenpos() {
        return openpos;
    }

    @Override
    public void setOpenpos(boolean openpos) {
        this.openpos = openpos;
    }
    
}
