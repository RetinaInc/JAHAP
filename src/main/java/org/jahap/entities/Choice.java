/*
 * The MIT License
 *
 * Copyright 2014 Open Jahap Community.
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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.jahap.business.base.language;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "CHOICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Choice.findAll", query = "SELECT c FROM Choice c"),
    @NamedQuery(name = "Choice.findById", query = "SELECT c FROM Choice c WHERE c.id = :id"),
    @NamedQuery(name = "Choice.findByGroupid", query = "SELECT c FROM Choice c WHERE c.groupid = :groupid"),
    @NamedQuery(name = "Choice.findByGroupcode", query = "SELECT c FROM Choice c WHERE c.groupcode = :groupcode"),
    @NamedQuery(name = "Choice.findByGroupname", query = "SELECT c FROM Choice c WHERE c.groupname = :groupname"),
    @NamedQuery(name = "Choice.findByChoicecode", query = "SELECT c FROM Choice c WHERE c.choicecode = :choicecode"),
    @NamedQuery(name = "Choice.findByChoicetext", query = "SELECT c FROM Choice c WHERE c.choicetext = :choicetext"),
    @NamedQuery(name = "Choice.findByChoiceint", query = "SELECT c FROM Choice c WHERE c.choiceint = :choiceint"),
    @NamedQuery(name = "Choice.findByChoicefloat", query = "SELECT c FROM Choice c WHERE c.choicefloat = :choicefloat"),
    @NamedQuery(name = "Choice.findByLanguage", query = "SELECT c FROM Choice c WHERE c.language = :language")})
public class Choice implements Serializable, Choice_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "GROUPID")
    private Integer groupid;
    @Size(max = 5)
    @Column(name = "GROUPCODE")
    private String groupcode;
    @Size(max = 50)
    @Column(name = "GROUPNAME")
    private String groupname;
    @Size(max = 5)
    @Column(name = "CHOICECODE")
    private String choicecode;
    @Size(max = 100)
    @Column(name = "CHOICETEXT")
    private String choicetext;
    @Column(name = "CHOICEINT")
    private Integer choiceint;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CHOICEFLOAT")
    private BigDecimal choicefloat;
    @JoinColumn(name = "LANGUAGE", referencedColumnName = "ID")
    @ManyToOne
    private Language language;

    public Choice() {
    }

    public Choice(Long id) {
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
    public Integer getGroupid() {
        return groupid;
    }

    @Override
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    @Override
    public String getGroupcode() {
        return groupcode;
    }

    @Override
    public void setGroupcode(String groupcode) {
        this.groupcode = groupcode;
    }

    @Override
    public String getGroupname() {
        return groupname;
    }

    @Override
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Override
    public String getChoicecode() {
        return choicecode;
    }

    @Override
    public void setChoicecode(String choicecode) {
        this.choicecode = choicecode;
    }

    @Override
    public String getChoicetext() {
        return choicetext;
    }

    @Override
    public void setChoicetext(String choicetext) {
        this.choicetext = choicetext;
    }

    @Override
    public Integer getChoiceint() {
        return choiceint;
    }

    @Override
    public void setChoiceint(Integer choiceint) {
        this.choiceint = choiceint;
    }

    @Override
    public BigDecimal getChoicefloat() {
        return choicefloat;
    }

    @Override
    public void setChoicefloat(BigDecimal choicefloat) {
        this.choicefloat = choicefloat;
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(Language language) {
        this.language = language;
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
        if (!(object instanceof Choice)) {
            return false;
        }
        Choice other = (Choice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.Choice[ id=" + id + " ]";
    }
    
}
