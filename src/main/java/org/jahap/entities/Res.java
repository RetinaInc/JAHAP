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
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "RES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Res.findAll", query = "SELECT r FROM Res r"),
    @NamedQuery(name = "Res.findById", query = "SELECT r FROM Res r WHERE r.id = :id"),
    @NamedQuery(name = "Res.findByArrivaltime", query = "SELECT r FROM Res r WHERE r.arrivaltime = :arrivaltime"),
    @NamedQuery(name = "Res.findByArrivaldate", query = "SELECT r FROM Res r WHERE r.arrivaldate = :arrivaldate"),
    @NamedQuery(name = "Res.findByDeparturetime", query = "SELECT r FROM Res r WHERE r.departuretime = :departuretime"),
    @NamedQuery(name = "Res.findByResno", query = "SELECT r FROM Res r WHERE r.resno = :resno"),
    @NamedQuery(name = "Res.findByDeparturedate", query = "SELECT r FROM Res r WHERE r.departuredate = :departuredate")})
public class Res implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
     @GeneratedValue
    private Long id;
    @Size(max = 50)
    @Column(name = "ARRIVALTIME")
    private String arrivaltime;
    @Size(max = 50)
    @Column(name = "ARRIVALDATE")
    private String arrivaldate;
    @Size(max = 50)
    @Column(name = "DEPARTURETIME")
    private String departuretime;
    @Size(max = 50)
    @Column(name = "RESNO")
    private String resno;
    @Size(max = 50)
    @Column(name = "DEPARTUREDATE")
    private String departuredate;
    @JoinColumn(name = "ADDRESSID", referencedColumnName = "ID")
    @ManyToOne
    private Address addressid;
    

    public Res() {
    }

    public Res(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(String arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getResno() {
        return resno;
    }

    public void setResno(String resno) {
        this.resno = resno;
    }

    public String getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(String departuredate) {
        this.departuredate = departuredate;
    }

    public Address getAddressid() {
        return addressid;
    }

    public void setAddressid(Address addressid) {
        this.addressid = addressid;
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
        if (!(object instanceof Res)) {
            return false;
        }
        Res other = (Res) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Res[ id=" + id + " ]";
    }
    
}
