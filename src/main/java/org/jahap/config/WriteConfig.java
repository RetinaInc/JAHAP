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
package org.jahap.config;

import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author russ
 */
public class WriteConfig {
    private String configFile;
    private String PersistenceUnit;

  public void setFile(String configFile) {
    this.configFile = configFile;
  }

    public String getPersistenceUnit() {
        return PersistenceUnit;
    }

    public void setPersistenceUnit(String PersistenceUnit) {
        this.PersistenceUnit = PersistenceUnit;
    }
   
  
   
  public void saveConfig() throws Exception {
    // create an XMLOutputFactory
    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
    // create XMLEventWriter
    XMLEventWriter eventWriter = outputFactory
        .createXMLEventWriter(new FileOutputStream(configFile));
    // create an EventFactory
    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    XMLEvent end = eventFactory.createDTD("\n");
    // create and write Start Tag
    StartDocument startDocument = eventFactory.createStartDocument();
    eventWriter.add(startDocument);

    // create config open tag
    StartElement configStartElement = eventFactory.createStartElement("",
        "", "config");
    eventWriter.add(configStartElement);
    eventWriter.add(end);
     StartElement databaseStartElement = eventFactory.createStartElement("",
        "", "database");
    eventWriter.add(databaseStartElement);
    eventWriter.add(end);
    
    // Write the different nodes
    createNode(eventWriter, "persitence_unit", PersistenceUnit);
    
    eventWriter.add(eventFactory.createEndElement("", "", "database"));
    eventWriter.add(end); 
    eventWriter.add(eventFactory.createEndElement("", "", "config"));
    eventWriter.add(end);
    eventWriter.add(eventFactory.createEndDocument());
    eventWriter.close();
  }

  private void createNode(XMLEventWriter eventWriter, String name,
      String value) throws XMLStreamException {

    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    XMLEvent end = eventFactory.createDTD("\n");
    XMLEvent tab = eventFactory.createDTD("\t");
    // create Start node
    StartElement sElement = eventFactory.createStartElement("", "", name);
    eventWriter.add(tab);
    eventWriter.add(sElement);
    // create Content
    Characters characters = eventFactory.createCharacters(value);
    eventWriter.add(characters);
    // create End node
    EndElement eElement = eventFactory.createEndElement("", "", name);
    eventWriter.add(eElement);
    eventWriter.add(end);

  }

}
