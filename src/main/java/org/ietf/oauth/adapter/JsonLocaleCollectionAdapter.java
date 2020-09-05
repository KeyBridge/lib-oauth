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

import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.json.bind.adapter.JsonbAdapter;

/**
 * Simple Locale collection JSON adapter. Maps Local to a language tag.
 *
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-05
 */
public class JsonLocaleCollectionAdapter implements JsonbAdapter<Collection<Locale>, Collection<String>> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<String> adaptToJson(Collection<Locale> obj) throws Exception {
    return obj.stream().map(l -> l.toLanguageTag()).collect(Collectors.toSet());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Locale> adaptFromJson(Collection<String> obj) throws Exception {
    return obj.stream().map(s -> Locale.forLanguageTag(s)).collect(Collectors.toSet());
  }

}
