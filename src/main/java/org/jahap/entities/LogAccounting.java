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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "LOG_ACCOUNTING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogAccounting.findAll", query = "SELECT l FROM LogAccounting l"),
    @NamedQuery(name = "LogAccounting.findById", query = "SELECT l FROM LogAccounting l WHERE l.id = :id"),
    @NamedQuery(name = "LogAccounting.findByAmount", query = "SELECT l FROM LogAccounting l WHERE l.amount = :amount"),
    @NamedQuery(name = "LogAccounting.findByDate", query = "SELECT l FROM LogAccounting l WHERE l.date = :date"),
    @NamedQuery(name = "LogAccounting.findByTime", query = "SELECT l FROM LogAccounting l WHERE l.time = :time")
})
 public class LogAccounting implements Serializable {
    @Column(name = "ACCOUNT_POSITION")
    private BigInteger accountPosition;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "AMOUNT")
    private Integer amount;
    @Size(max = 255)
    @Column(name = "DATE")
    private String date;
    @Size(max = 255)
    @Column(name = "TIME")
    private String time;
    @Size(max = 255)
    @Column(name = "ACCOUNT")
    private String account;
    @Size(max = 255)
    @Column(name = "POSITIONNAME")
    private String positionname;
    

    public LogAccounting() {
    }

    public LogAccounting(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }



    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname;
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
        if (!(object instanceof LogAccounting)) {
            return false;
        }
        LogAccounting other = (LogAccounting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.LogAccounting[ id=" + id + " ]";
    }

   
    public void setAccountPosition(BigInteger accountPosition) {
        this.accountPosition = accountPosition;
    }
    
}
