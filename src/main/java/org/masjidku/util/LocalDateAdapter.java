package org.masjidku.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 *  Adapter (for JAXB) to convert between the LocalDate and the ISO 8601
 *  String representation of the date such as '2021-05-05'.
 *
 *  @author Andi Irham
 */
@SuppressWarnings("unused")
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) {
        return v.toString();
    }
}
