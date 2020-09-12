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
 * RFC 6749 OAuth 2.0 3.1.1. Response Type
 * <p>
 * The authorization endpoint is used by the authorization code grant type and
 * implicit grant type flows. The client informs the authorization server of the
 * desired grant type using the `response_type` parameter.
 * <p>
 * Extension response types MAY contain a space-delimited (%x20) list of values,
 * where the order of values does not matter (e.g., response type "a b" is the
 * same as "b a").
 * <p>
 * Enumerate values of the authorization endpoint {@code response_type}
 * parameter. The client informs the authorization server of the desired grant
 * type.
 *
 * @since v0.0.1
 * @author Jesse Caulfield 10/06/17
 */
public enum ResponseType {

  /**
   * Used for the authorization code grant type. For requesting an authorization
   * code as described by RFC 6749 OAuth 2.0 Section 4.1.1.
   */
  code("Authorization Code Grant Type"),
  /**
   * Used for the implicit grant type. For requesting an access token (implicit
   * grant) as described by RFC 6749 OAuth 2.0 Section 4.2.1.
   */
  token("Implicit Grant Type"),
  /**
   * OpenId Connect Core 3.2.2.1. Authentication Request. ID Token value
   * associated with the authenticated session. When using the Implicit Flow,
   * the response_type value is `id_token token` or `id_token`.
   */
  id_token("ID Token"),

  /**
   * @deprecated 2020-09-12 use `token`
   *
   * Message access token. Used for client registration.
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
