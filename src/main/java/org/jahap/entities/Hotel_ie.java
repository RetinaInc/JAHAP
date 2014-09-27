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

/**
 *
 * @author russ
 */
public interface Hotel_ie {

    Address getHotelAdress();

    String getHotelBankaccountdata1();

    String getHotelBankaccountdata2();

    String getHotelCode();

    Country getHotelCountry();

    Currency getHotelCurrency();

    String getHotelFootertext();

    Language getHotelLanguage();

    String getHotelName();

    Integer getId();

    void setHotelAdress(Address hotelAdress);

    void setHotelBankaccountdata1(String hotelBankaccountdata1);

    void setHotelBankaccountdata2(String hotelBankaccountdata2);

    void setHotelCode(String hotelCode);

    void setHotelCountry(Country hotelCountry);

    void setHotelCurrency(Currency hotelCurrency);

    void setHotelFootertext(String hotelFootertext);

    void setHotelLanguage(Language hotelLanguage);

    void setHotelName(String hotelName);
    
}
