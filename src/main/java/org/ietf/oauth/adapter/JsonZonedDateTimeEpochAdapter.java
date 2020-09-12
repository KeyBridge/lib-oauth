/*
 * Copyright 2018 Key Bridge. All rights reserved. Use is subject to license
 * terms.
 *
 * This software code is protected by Copyrights and remains the property of
 * Key Bridge and its suppliers, if any. Key Bridge reserves all rights in and to
 * Copyrights and no license is granted under Copyrights in this Software
 * License Agreement.
 *
 * Key Bridge generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request by sending an email to info@keybridgewireless.com.
 *
 * All information contained herein is the property of Key Bridge and its
 * suppliers, if any. The intellectual and technical concepts contained herein
 * are proprietary.
 */
package org.ietf.oauth.adapter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.json.bind.adapter.JsonbAdapter;

/**
 * Json-b adapter to transform a (UTC) ZonedDateTime and epoch seconds.
 * <p>
 * From RFC 7519 § 2. Terminology:
 * <p>
 * NumericDate
 * <p>
 * A JSON numeric value representing the number of seconds from
 * 1970-01-01T00:00:00Z UTC until the specified UTC date/time, ignoring leap
 * seconds. This is equivalent to the IEEE Std 1003.1, 2013 Edition [POSIX.1]
 * definition "Seconds Since the Epoch", in which each day is accounted for by
 * exactly 86400 seconds, other than that non-integer values can be represented.
 * See RFC 3339 [RFC3339] for details regarding date/times in general and UTC in
 * particular.
 *
 * @author Key Bridge
 * @since v0.8.0 added 06/02/18 replacing XmlAdapterInstantLong
 */
public class JsonZonedDateTimeEpochAdapter implements JsonbAdapter<ZonedDateTime, Long> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Long adaptToJson(ZonedDateTime obj) throws Exception {
    return obj == null ? null : obj.toEpochSecond();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ZonedDateTime adaptFromJson(Long obj) throws Exception {
    return ZonedDateTime.ofInstant(Instant.ofEpochSecond(obj), ZoneId.of("UTC"));
  }

  /**
   * Extra method to handle deserialization of String types.
   * <p>
   * This method is used on deserialization only. It contains a conversion logic
   * from type Adapted to type Original.
   *
   * @param obj Object to convert.
   * @return Converted object representing pojo to be set into object graph.
   * @throws Exception if there is an error during the conversion.
   */
  public ZonedDateTime adaptFromJson(String obj) throws Exception {
    return ZonedDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(obj)), ZoneId.of("UTC"));
  }

}
