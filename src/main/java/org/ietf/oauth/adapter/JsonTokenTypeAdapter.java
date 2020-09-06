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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.adapter.JsonbAdapter;
import javax.json.bind.annotation.JsonbProperty;
import org.ietf.oauth.type.TokenType;

/**
 * JSON adapter for the TokenType enumerated class. Types have JsonbProperty
 * annotations and require special handling.
 *
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-05
 */
public class JsonTokenTypeAdapter implements JsonbAdapter<TokenType, String> {

  private static final Logger LOG = Logger.getLogger(JsonTokenTypeAdapter.class.getName());

  @Override
  public String adaptToJson(TokenType obj) throws Exception {
    JsonbProperty annotation = obj.getClass().getField(obj.name()).getAnnotation(JsonbProperty.class);
    return annotation == null ? obj.name() : annotation.value();
  }

  @Override
  public TokenType adaptFromJson(String obj) throws Exception {
    for (TokenType type : TokenType.values()) {
      String value = adaptToJson(type);
      if (value.equals(obj)) {
        return type;
      }
    }
    LOG.log(Level.INFO, "Unrecognized TokenType: {0}", obj);
    return null;
  }

}
