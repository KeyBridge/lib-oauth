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
package org.ietf.oauth;

import java.io.Serializable;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Abstract class implementing a URL encoding and decoding capabilities.
 * <p>
 * This facilitates sending and receiving URL-encoded objects; specifically
 * request messages that must be encoded as
 * {@code application/x-www-form-urlencoded}.
 *
 * @author Key Bridge 10/08/17
 * @since v0.2.0
 * @since v2.2.0 rewritten 2020-09-08 to expose OauthUtility writers
 */
public abstract class AbstractUrlEncodedMessage implements Serializable {

  /**
   * Transform this class instance into a URL-encoded string.
   *
   * @return the bean configuration as a URL-encoded query string
   */
  public String toUrlEncodedString() {
    return OauthUtility.toUrlEncodedString(this);
  }

  /**
   * Transform this entity to a multivalued map. The map can then be used to
   * generate a URL-encoded version of the object instance.
   *
   * @return a MultivaluedMap instance configuration
   */
  public MultivaluedMap<String, Object> toMultivaluedMap() {
    return OauthUtility.toMultivaluedMap(this);
  }

}
