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
public interface rates_ie {

    @XmlTransient
    Collection<AccountPosition> getAccountPositionCollection();

    String getCode();

    Long getId();

    String getName();
    
    long getRevaccount();

    double getPrice();

    void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection);

    void setCode(String code);
     Collection<Csc> getCscCollection();
    
    boolean getOvernight();
    
    void setCscCollection(Collection<Csc> cscCollection);
            
    void setOvernight(boolean overnight);


    void setRevaccount(long revaccount);
       
    void setId(Long id);

    void setName(String name);

    void setPrice(double price);
    
}
