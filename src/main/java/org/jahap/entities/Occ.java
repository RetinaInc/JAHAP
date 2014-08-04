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
import javax.persistence.OneToOne;
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
@Table(name = "OCC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Occ.findAll", query = "SELECT o FROM Occ o"),
    @NamedQuery(name = "Occ.findById", query = "SELECT o FROM Occ o WHERE o.id = :id"),
    @NamedQuery(name = "Occ.findByArrivaltime", query = "SELECT o FROM Occ o WHERE o.arrivaltime = :arrivaltime"),
    @NamedQuery(name = "Occ.findByDeparturetime", query = "SELECT o FROM Occ o WHERE o.departuretime = :departuretime"),
    @NamedQuery(name = "Occ.findByArrivaldate", query = "SELECT o FROM Occ o WHERE o.arrivaldate = :arrivaldate"),
    @NamedQuery(name = "Occ.findByDeparturedate", query = "SELECT o FROM Occ o WHERE o.departuredate = :departuredate")})
public class Occ implements Serializable, occ_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "ARRIVALTIME")
    @Temporal(TemporalType.TIME)
    private Date arrivaltime;
    @Column(name = "DEPARTURETIME")
    @Temporal(TemporalType.TIME)
    private Date departuretime;
    @Column(name = "ARRIVALDATE")
    @Temporal(TemporalType.DATE)
    private Date arrivaldate;
    @Column(name = "DEPARTUREDATE")
    @Temporal(TemporalType.DATE)
    private Date departuredate;
    @JoinColumn(name = "ROOM", referencedColumnName = "ID")
    @ManyToOne
    private Rooms room;
    @JoinColumn(name = "RES", referencedColumnName = "ID")
    @ManyToOne
    private Res res;
    @JoinColumn(name="GUEST", referencedColumnName = "ID")
    @ManyToOne
    private Address guest;
    @JoinColumn(name="ACCOUNT", referencedColumnName = "ID")
    @ManyToOne
    private Accounts account;

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }
     
    @Override
    public Address getGuest() {
        return guest;
    }
    @Override
    public void setGuest(Address guest) {
        this.guest = guest;
    }
   

    public Occ() {
    }

    public Occ(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getArrivaltime() {
        return arrivaltime;
    }

    @Override
    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    @Override
    public Date getDeparturetime() {
        return departuretime;
    }

    @Override
    public void setDeparturetime(Date departuretime) {
        this.departuretime = departuretime;
    }

    @Override
    public Date getArrivaldate() {
        return arrivaldate;
    }

@Override
    public void setArrivaldate(Date arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    @Override
    public Date getDeparturedate() {
        return departuredate;
    }

    @Override
    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }

    @Override
    public Rooms getRoom() {
        return room;
    }

    @Override
    public void setRoom(Rooms room) {
        this.room = room;
    }

    @Override
    public Res getRes() {
        return res;
    }

    @Override
    public void setRes(Res res) {
        this.res = res;
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
        if (!(object instanceof Occ)) {
            return false;
        }
        Occ other = (Occ) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Occ[ id=" + id + " ]";
    }
    
}
