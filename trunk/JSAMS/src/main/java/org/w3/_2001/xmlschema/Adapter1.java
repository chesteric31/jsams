//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.07 at 09:57:15 PM CET 
//


package org.w3._2001.xmlschema;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Auto generated adapter.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class Adapter1 extends XmlAdapter<String, Date> {

    /**
     * {@inheritDoc}
     */
    public Date unmarshal(String value) {
        return (be.jsams.server.model.utils.xml.JsamsDateAdapter.parseDate(value));
    }

    /**
     * {@inheritDoc}
     */
    public String marshal(Date value) {
        return (be.jsams.server.model.utils.xml.JsamsDateAdapter.printDate(value));
    }

}
