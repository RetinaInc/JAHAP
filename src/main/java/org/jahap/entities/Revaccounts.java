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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "REVACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revaccounts.findAll", query = "SELECT r FROM Revaccounts r"),
    @NamedQuery(name = "Revaccounts.findById", query = "SELECT r FROM Revaccounts r WHERE r.id = :id"),
    @NamedQuery(name = "Revaccounts.findByRevaccnumber", query = "SELECT r FROM Revaccounts r WHERE r.revaccnumber = :revaccnumber"),
    @NamedQuery(name = "Revaccounts.findByName", query = "SELECT r FROM Revaccounts r WHERE r.name = :name")})
public class Revaccounts implements Serializable, revaccounts_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "REVACCOUNTNUMBER")
    private BigInteger revaccnumber;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "revaccount")
    private Collection<Revenue> revenueCollection;

    @Override
    public Collection<Revenue> getRevenueCollection() {
        return revenueCollection;
    }

    @Override
    public void setRevenueCollection(Collection<Revenue> revenueCollection) {
        this.revenueCollection = revenueCollection;
    }
    
    
    public Revaccounts() {
    }

    public Revaccounts(Long id) {
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
    public BigInteger getRevaccnumber() {
        return revaccnumber;
    }

    @Override
    public void setRevaccnumber(BigInteger revaccnumber) {
        this.revaccnumber = revaccnumber;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Revaccounts)) {
            return false;
        }
        Revaccounts other = (Revaccounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Revaccounts[ id=" + id + " ]";
    }
    
}
