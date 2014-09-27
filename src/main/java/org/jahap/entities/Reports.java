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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
@Entity
@Table(name = "REPORTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reports.findAll", query = "SELECT r FROM Reports r"),
    @NamedQuery(name = "Reports.findById", query = "SELECT r FROM Reports r WHERE r.id = :id"),
    @NamedQuery(name = "Reports.findByName", query = "SELECT r FROM Reports r WHERE r.name = :name"),
    @NamedQuery(name = "Reports.findByDescription", query = "SELECT r FROM Reports r WHERE r.description = :description"),
    @NamedQuery(name = "Reports.findByReportGroup", query = "SELECT r FROM Reports r WHERE r.reportGroup = :reportGroup")})
public class Reports implements Reports_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 50)
    @Column(name = "REPORT_GROUP")
    private String reportGroup;
    @Lob
    @Column(name = "REPORT")
    private byte[] report;
    @Lob
    @Column(name = "REPORT_LAYOUT")
    private byte[] reportLayout;
    @JoinColumn(name = "LANGUAGE", referencedColumnName = "ID")
    @ManyToOne
    private Language language;

    public Reports() {
    }

    public Reports(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getReportGroup() {
        return reportGroup;
    }

    @Override
    public void setReportGroup(String reportGroup) {
        this.reportGroup = reportGroup;
    }

    @Override
    public byte[] getReport() {
        return report;
    }

    @Override
    public void setReport(byte[] report) {
        this.report = report;
    }
    
    
    @Override
     public void setReportLayoutFile(File file)throws IOException {
        InputStream is = new FileInputStream(file); 
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
          int offset = 0;
          int numRead = 0;
          while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
          {
            offset += numRead;
          }
        if (offset < bytes.length)
          {
            throw new IOException("Could not completely read file " + file.getName());
          }
          // Close the input stream and return bytes
          is.close();  
          setReportLayout(bytes);
    }

    @Override
    public byte[] getReportLayout() {
        return reportLayout;
    }

    @Override
    public void setReportLayout(byte[] reportLayout) {
        this.reportLayout = reportLayout;
    }
    
    @Override
    public void setReportFile(File file)throws IOException {
        InputStream is = new FileInputStream(file); 
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
          int offset = 0;
          int numRead = 0;
          while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
          {
            offset += numRead;
          }
        if (offset < bytes.length)
          {
            throw new IOException("Could not completely read file " + file.getName());
          }
          // Close the input stream and return bytes
          is.close();  
          setReport(bytes);
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
        if (!(object instanceof Reports)) {
            return false;
        }
        Reports other = (Reports) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.Reports[ id=" + id + " ]";
    }

    @Override
    public Language getLanguage() {
       return this.language;
    }

    @Override
    public void setLanguage(Language language) {
        this.language=language;
    }
    
}
