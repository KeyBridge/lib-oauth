/*
 * Copyright 2018 Key Bridge.
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

import javax.json.bind.adapter.JsonbAdapter;
import org.ietf.oauth.type.ScopeType;

/**
 * Java JSON adapter to translate between a ScopeType instance.
 *
 * @author Jesse Caulfield 10/06/17
 * @since v0.0.1
 * @author Key Bridge LLC
 * @since v2.0.0 created 2020-08-20
 */
public class JsonScopeTypeAdapter implements JsonbAdapter< ScopeType, String> {

  /**
   * {@inheritDoc}
   */
  @Override
  public String adaptToJson(ScopeType obj) throws Exception {
    return obj == null ? null : obj.name();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ScopeType adaptFromJson(String obj) throws Exception {
    return obj == null ? null : ScopeType.valueOf(obj);
  }

}
