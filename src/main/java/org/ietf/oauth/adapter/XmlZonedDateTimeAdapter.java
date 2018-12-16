/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ietf.oauth.adapter;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.time.ZonedDateTime
 * instance and the ISO 8601 Date format. The ISO instant formatter that formats
 * or parses an instant in UTC, such as '2011-12-03T10:15:30Z'.
 *
 * @see
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/text/DateFormat.html">DateFormat</a>
 * @see <a href="http://www.w3.org/TR/NOTE-datetime">W3C Date and Time
 * Formats</a>
 * @author Key Bridge LLC
 * @since v1.1.1 added 10/22/15
 * @since v2.0.3 change format from ISO_ZONED_DATE_TIME to ISO_INSTANT
 */
public class XmlZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {

  /**
   * The ISO date-time formatter that formats or parses a date-time without an
   * offset, such as '2011-12-03T10:15:30'. This returns an immutable formatter
   * capable of formatting and parsing the ISO-8601 extended offset date-time
   * format.
   */
  private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT;

  /**
   * {@inheritDoc}
   */
  @Override
  public ZonedDateTime unmarshal(String v) throws ParseException {
    return v != null ? ZonedDateTime.parse(v, dateTimeFormatter) : null;
  }

  /**
   * {@inheritDoc}
   * <p>
   * An example output value would be, for example: "2001-10-26"
   */
  @Override
  public String marshal(ZonedDateTime v) {
    return v != null ? v.format(dateTimeFormatter) : null;
  }
}
