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
   * code: Used for the authorization code grant type.
   */
  code("Authorization Code Grant Type"),
  /**
   * token: Used for the implicit grant type.
   */
  token("Implicit Grant Type"),
  /**
   * id_token: Include an ID Token in the Authorization Request.
   */
  id_token("ID Token"),
  /**
   * mac: Message access token. Used for client registration.
   */
  mac("Message access token");

  private final String displayName;

  private ResponseType(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Gets display name
   *
   * @return display name name
   */
  public String getDisplayName() {
    return displayName;
  }

}
