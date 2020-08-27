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

import java.net.URI;
import javax.json.bind.adapter.JsonbAdapter;

/**
 * Simple URI to string adapter.
 *
 * @author Key Bridge
 * @since v2.1.0 created 2020-08-27
 */
public class JsonUriAdapter implements JsonbAdapter<URI, String> {

  /**
   * {@inheritDoc}
   */
  @Override
  public String adaptToJson(URI obj) throws Exception {
    return obj.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public URI adaptFromJson(String obj) throws Exception {
    return URI.create(obj);
  }

}
