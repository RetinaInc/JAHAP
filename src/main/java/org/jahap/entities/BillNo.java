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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
@Entity
@Table(name = "BILL_NO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BillNo.findAll", query = "SELECT b FROM BillNo b"),
    @NamedQuery(name = "BillNo.findByBillno", query = "SELECT b FROM BillNo b WHERE b.billno = :billno")})
public class BillNo implements Serializable, BILLNO_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILLNO")
    @TableGenerator( name = "bill_gen", table = "SEQ_STORE", pkColumnName = "TABLE_NAME", pkColumnValue = "BILL", valueColumnName = "VALUE" , allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "bill_gen" )
    private Long billno;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="BILLNO")
    private Bill bill;
    
    
    public BillNo() {
    }
    
    
    public BillNo(Long billno) {
        this.billno = billno;
    }

    @Override
    public Bill getBill() {
        return bill;
    }

    @Override
    public void setBill(Bill bill) {
        this.bill = bill;
    }

    
    
    
    @Override
    public Long getBillno() {
        return billno;
    }

    @Override
    public void setBillno(Long billno) {
        this.billno = billno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billno != null ? billno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillNo)) {
            return false;
        }
        BillNo other = (BillNo) object;
        if ((this.billno == null && other.billno != null) || (this.billno != null && !this.billno.equals(other.billno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.BillNo[ billno=" + billno + " ]";
    }
    
}
