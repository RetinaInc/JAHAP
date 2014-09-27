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

import java.math.BigDecimal;

/**
 *
 * @author russ
 */
public interface Choice_ie {

    String getChoicecode();

    BigDecimal getChoicefloat();

    Integer getChoiceint();

    String getChoicetext();

    String getGroupcode();

    Integer getGroupid();

    String getGroupname();

    Long getId();

    Language getLanguage();

    void setChoicecode(String choicecode);

    void setChoicefloat(BigDecimal choicefloat);

    void setChoiceint(Integer choiceint);

    void setChoicetext(String choicetext);

    void setGroupcode(String groupcode);

    void setGroupid(Integer groupid);

    void setGroupname(String groupname);

    void setLanguage(Language language);
    
}
