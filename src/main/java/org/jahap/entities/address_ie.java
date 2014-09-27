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

import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
public interface address_ie {

    @XmlTransient
    Collection<Bill> getBillCollection();

    String getChristianname();

    String getCity();

    String getEmail();

    Long getId();

    String getName();

    String getPhone();
    
    Language getLanguage();
    
    Country getCountry();
    
    Currency getCurrency();
    
    String getTitel();
    
    String getHomepage();
    
    String getAddresstype();
    
    String getRemarks();
    
    String getGreeting();
    
    String getSalutation();
    
    /*
    public Bill getBill() {
    return bill;
    }
    public void setBill(Bill bill) {
    this.bill = bill;
    }
     */
    @XmlTransient
    Collection<Res> getResCollection();

    String getStreet();

    String getZipcode();

    void setBillCollection(Collection<Bill> billCollection);

    void setChristianname(String christianname);
     
    void setCountry(Country country);
    
    void setCurrency(Currency currency);
    
    void setLanguage(Language language);
    
    void setCity(String city);

    void setEmail(String email);

    void setName(String name);

    void setPhone(String phone);

    void setResCollection(Collection<Res> resCollection);

    void setStreet(String street);

    void setZipcode(String zipcode);
    
    void setTitel(String title);
    
    void setHomepage(String homepage);
    
    void setAddresstype(String addresstype);
    
    void setRemarks(String remarks);
    
    void setGreeting(String greeting);
    
    void setSalutation(String salutation);
    
}
