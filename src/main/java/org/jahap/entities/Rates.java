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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "RATES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rates.findAll", query = "SELECT r FROM Rates r"),
    @NamedQuery(name = "Rates.findById", query = "SELECT r FROM Rates r WHERE r.id = :id"),
    @NamedQuery(name = "Rates.findByName", query = "SELECT r FROM Rates r WHERE r.name = :name"),
    @NamedQuery(name = "Rates.findByPrice", query = "SELECT r FROM Rates r WHERE r.price = :price"),
    @NamedQuery(name = "Rates.findByCode", query = "SELECT r FROM Rates r WHERE r.code = :code")})
public class Rates implements Serializable, rates_ie {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private double price;
    @Column(name = "REVACCOUNT")
    private long revaccount;
    @Column(name = "OVERNIGHT")
    private boolean overnight;
    @OneToMany(mappedBy = "rate")
    private Collection<Csc> cscCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 50)
    @Column(name = "CODE")
    private String code;
    @OneToMany( mappedBy = "rate")
    private Collection<AccountPosition> accountPositionCollection;
    @JoinColumn(name = "VATTYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Vattype vattype; 
    
    
    public Rates() {
    }

    public Rates(Long id) {
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    @Override
    public Collection<AccountPosition> getAccountPositionCollection() {
        return accountPositionCollection;
    }

    @Override
    public void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection) {
        this.accountPositionCollection = accountPositionCollection;
    }

    public Vattype getVattype() {
        return vattype;
    }

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
        if (!(object instanceof Rates)) {
            return false;
        }
        Rates other = (Rates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Rates[ id=" + id + " ]";
    }

    public double getPrice() {
      return price;
    }
    
   
    public void setPrice(double price) {
        this.price = price;
    }

    public long getRevaccount() {
        return revaccount;
    }

    public void setRevaccount(long revaccount) {
        this.revaccount = revaccount;
    }

    public boolean getOvernight() {
        return overnight;
    }

    public void setOvernight(boolean overnight) {
        this.overnight = overnight;
    }

    @XmlTransient
    public Collection<Csc> getCscCollection() {
        return cscCollection;
    }

    public void setCscCollection(Collection<Csc> cscCollection) {
        this.cscCollection = cscCollection;
    }
    
}
