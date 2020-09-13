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
package org.ietf.oauth.type;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Request for Comments: 8693 OAuth 2.0 Token Exchange Request
 * <p>
 * 3. Token Type Identifiers
 * <p>
 * Several parameters in this specification utilize an identifier as the value
 * to describe the token in question. Specifically, they are the
 * "requested_token_type", "subject_token_type", and "actor_token_type"
 * parameters of the request and the "issued_token_type" member of the response.
 * Token type identifiers are URIs. Token exchange can work with both tokens
 * issued by other parties and tokens from the given authorization server. For
 * the former, the token type identifier indicates the syntax (e.g., JWT or SAML
 * 2.0) so the authorization server can parse it; for the latter, it indicates
 * what the given authorization server issued it for (e.g., "access_token" or
 * "refresh_token").
 * <p>
 * Note that the distinction between an access token and a JWT is subtle. An
 * access token represents a delegated authorization decision, whereas JWT is a
 * token format.
 *
 * @see <a href="https://tools.ietf.org/html/rfc8693">OAuth 2.0 Token
 * Exchange</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-05
 */
public enum TokenType {

  /**
   * Indicates that the token is an OAuth 2.0 access token issued by the given
   * authorization server.
   */
  @JsonbProperty("urn:ietf:params:oauth:token-type:access_token ")
  access_token,
  /**
   * Indicates that the token is an OAuth 2.0 refresh token issued by the given
   * authorization server.
   */
  @JsonbProperty("urn:ietf:params:oauth:token-type:refresh_token")
  refresh_token,
  /**
   * Indicates that the token is an ID Token as defined in Section 2 of
   * [OpenID.Core].
   */
  @JsonbProperty("urn:ietf:params:oauth:token-type:id_token")
  id_token,
  /**
   * Indicates that the token is a base64url-encoded SAML 1.1
   * [OASIS.saml-core-1.1] assertion.
   */
  @JsonbProperty("urn:ietf:params:oauth:token-type:saml1")
  saml1,
  /**
   * Indicates that the token is a base64url-encoded SAML 2.0
   * [OASIS.saml-core-2.0-os] assertion.
   */
  @JsonbProperty("urn:ietf:params:oauth:token-type:saml2")
  saml2,

  /**
   * RFC 7519 JSON Web Token (JWT) registers the URN
   * "urn:ietf:params:oauth:token-type:jwt" for use by applications that declare
   * content types using URIs (rather than, for instance, media types) to
   * indicate that the content referred to is a JWT.
   */
  @JsonbProperty("urn:ietf:params:oauth:token-type:jwt")
  jwt
}
