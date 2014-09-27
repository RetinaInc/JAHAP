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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "VATTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vattype.findAll", query = "SELECT v FROM Vattype v"),
    @NamedQuery(name = "Vattype.findById", query = "SELECT v FROM Vattype v WHERE v.id = :id"),
    @NamedQuery(name = "Vattype.findByName", query = "SELECT v FROM Vattype v WHERE v.name = :name"),
    @NamedQuery(name = "Vattype.findByDatevat", query = "SELECT v FROM Vattype v WHERE v.datevat = :datevat"),
    @NamedQuery(name = "Vattype.findByPercentage", query = "SELECT v FROM Vattype v WHERE v.percentage = :percentage")})
public class Vattype implements Serializable, Vattype_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @Column(name = "DATEVAT")
    @Temporal(TemporalType.DATE)
    private Date datevat;
    @Column(name = "PERCENTAGE")
    private float percentage;
    @OneToMany(mappedBy = "vattype")
    private Collection<Vat> vatCollection;
    @OneToMany(mappedBy = "vattype")
    private Collection<Rates> rates;
    
    public Vattype() {
    }

    public Vattype(Long id) {
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Date getDatevat() {
        return datevat;
    }

    @Override
    public void setDatevat(Date datevat) {
        this.datevat = datevat;
    }

    public Collection<Rates> getRates() {
        return rates;
    }

    public void setRates(Collection<Rates> rates) {
        this.rates = rates;
    }
    
    
    
    
    @Override
    public float getPercentage() {
        return percentage;
    }

    @Override
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @XmlTransient
    @Override
    public Collection<Vat> getVatCollection() {
        return vatCollection;
    }

    @Override
    public void setVatCollection(Collection<Vat> vatCollection) {
        this.vatCollection = vatCollection;
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
        if (!(object instanceof Vattype)) {
            return false;
        }
        Vattype other = (Vattype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.Vattype[ id=" + id + " ]";
    }
    
}
