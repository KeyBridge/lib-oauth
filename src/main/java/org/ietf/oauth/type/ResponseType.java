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
   * RFC 6749 Used for the authorization code grant type. For requesting an
   * authorization code as described by RFC 6749 OAuth 2.0 Section 4.1.1.
   */
  code("Authorization Code Grant Type"),
  /**
   * RFC 6749 Used for the implicit grant type. For requesting an access token
   * (implicit grant) as described by RFC 6749 OAuth 2.0 Section 4.2.1.
   */
  token("Implicit Grant Type"),

  /**
   * OpenId. The intended purpose is to enable use cases where a party requests
   * the Authorization Server to register a grant of access to a Protected
   * Resource on behalf of a Client but requires no access credentials to be
   * returned to the Client at that time. The means by which the Client
   * eventually obtains the access credentials is left unspecified.
   * <p>
   * When supplied as the response_type parameter in an OAuth 2.0 Authorization
   * Request, the Authorization Server SHOULD NOT return an OAuth 2.0
   * Authorization Code, Access Token, Access Token Type, or ID Token in a
   * successful response to the grant request. If a redirect_uri is supplied,
   * the User Agent SHOULD be redirected there after granting or denying access.
   * The request MAY include a state parameter, and if so, the Authorization
   * Server MUST echo its value as a response parameter when issuing either a
   * successful response or an error response. The default Response Mode for
   * this Response Type is the query encoding. Both successful and error
   * responses SHOULD be returned using the supplied Response Mode, or if none
   * is supplied, using the default Response Mode.
   *
   * @see
   * <a href="https://openid.net/specs/oauth-v2-multiple-response-types-1_0.html#none">OAuth
   * 2.0 Multiple Response Type Encoding Practices</a>
   */
  none("The client does not require access credentials"),
  /**
   * OpenId Connect Core 3.2.2.1. Authentication Request. ID Token value
   * associated with the authenticated session. When using the Implicit Flow,
   * the response_type value is `id_token token` or `id_token`. The intended
   * purpose of the id_token is that it MUST provide an assertion of the
   * identity of the Resource Owner as understood by the Authorization Server.
   * The assertion MUST specify a targeted audience, e.g. the requesting Client.
   * However, the specific semantics of the assertion and how it can be
   * validated are not specified in this document.
   *
   * @see
   * <a href="https://openid.net/specs/oauth-v2-multiple-response-types-1_0.html#id_token">OAuth
   * 2.0 Multiple Response Type Encoding Practices</a>
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
