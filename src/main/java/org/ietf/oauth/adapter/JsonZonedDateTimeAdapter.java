/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ietf.oauth.adapter;

import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.json.bind.adapter.JsonbAdapter;

/**
 * Java JSON adapter to translate between a standard java.time.ZonedDateTime
 * instance and the ISO 8601 Date format. The ISO instant formatter that formats
 * or parses an instant in UTC, such as '2011-12-03T10:15:30Z'.
 *
 * @see
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/text/DateFormat.html">DateFormat</a>
 * @see <a href="http://www.w3.org/TR/NOTE-datetime">W3C Date and Time
 * Formats</a>
 * @author Key Bridge LLC
 * @since v2.0.0 rewrite 2020-08-20
 */
public class JsonZonedDateTimeAdapter implements JsonbAdapter<ZonedDateTime, String> {

  /**
   * The ISO instant formatter that formats or parses an instant in UTC, such as
   * '2011-12-03T10:15:30Z'. This returns an immutable formatter capable of
   * formatting and parsing the ISO-8601 extended offset date-time format.
   */
  private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT;

  /**
   * {@inheritDoc}
   */
  @Override
  public String adaptToJson(ZonedDateTime obj) throws Exception {
    return obj == null ? null : obj.format(dateTimeFormatter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ZonedDateTime adaptFromJson(String obj) throws Exception {
    return ZonedDateTime.ofInstant(Instant.parse(obj), Clock.systemUTC().getZone());
  }
}
