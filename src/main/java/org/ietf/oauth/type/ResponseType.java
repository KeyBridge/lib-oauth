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
package org.ietf.oauth.type;

/**
 * Enumerate values of the authorization endpoint {@code response_type}
 * parameter.
 * <p>
 * The client informs the authorization server of the desired grant type.
 * <p>
 * The authorization endpoint is used by the authorization code grant type and
 * implicit grant type flows.
 *
 * @since v0.0.1
 * @author Jesse Caulfield 10/06/17
 */
public enum ResponseType {

  /**
   * Used for the authorization code grant type.
   */
  CODE("code", "Authorization Code Grant Type"),
  /**
   * Used for the implicit grant type.
   */
  TOKEN("token", "Implicit Grant Type"),
  /**
   * Include an ID Token in the Authorization Request.
   */
  ID_TOKEN("id_token", "ID Token"),
  /**
   * Used for client registration.
   */
  MAC("mac", "Message access token");

  private final String paramName;
  private final String displayName;

  private ResponseType(String value, String displayName) {
    this.paramName = value;
    this.displayName = displayName;
  }

  /**
   * Gets param name.
   *
   * @return param name
   */
  public String toText() {
    return paramName;
  }

  public static ResponseType fromText(String param) {
    return ResponseType.valueOf(param.toUpperCase());
  }

  /**
   * Gets display name
   *
   * @return display name name
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Returns a string representation of the object. In this case the parameter
   * name for the response_type parameter.
   */
  @Override
  public String toString() {
    return paramName;
  }

}
