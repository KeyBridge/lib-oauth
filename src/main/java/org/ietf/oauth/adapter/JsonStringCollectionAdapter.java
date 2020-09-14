/*
 * Copyright 2020 Key Bridge.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ietf.oauth.adapter;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.json.bind.adapter.JsonbAdapter;

/**
 * Simple String collection JSON adapter. Implements functionality found in the
 * `@XmlList` annotation. Used to map a property to a list simple type. Allows
 * multiple values to be represented as whitespace-separated tokens in a single
 * element.
 *
 * @author Key Bridge
 * @since v2.1.0 created 2020-08-27
 * @see
 * <a href="https://docs.oracle.com/javaee/5/api/javax/xml/bind/annotation/XmlList.html">XmlList</a>
 */
public class JsonStringCollectionAdapter implements JsonbAdapter<Collection<String>, String> {

  /**
   * {@inheritDoc} URL encode each entry to support strings with spaces and
   * special characters.
   */
  @Override
  public String adaptToJson(Collection<String> obj) throws Exception {
    StringBuilder sb = new StringBuilder();
    obj.forEach(s -> sb.append(URLEncoder.encode(s.trim())).append(" "));
    return sb.toString().trim();
  }

  /**
   * {@inheritDoc} URL decode each entry to support strings with spaces and
   * special characters.
   */
  @Override
  public Collection<String> adaptFromJson(String obj) throws Exception {
    return Arrays.asList(obj.trim().split(" "))
      .stream()
      .map(s -> URLDecoder.decode(s))
      .collect(Collectors.toList());
  }

}
