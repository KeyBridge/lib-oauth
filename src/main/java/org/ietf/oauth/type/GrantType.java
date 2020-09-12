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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import javax.json.bind.annotation.JsonbProperty;

/**
 * RFC 6749 OAuth 2.0 4. Obtaining Authorization
 * <p>
 * OAuth defines four grant types: authorization code, implicit, resource owner
 * password credentials, and client credentials. It also provides an extension
 * mechanism for defining additional grant types.
 * <p>
 * The "grant_type" element is defined in RFC 6749 OAuth 2.0 Sections 4.1.3,
 * 4.3.2, 4.4.2, 4.5, and 6 plus OpenId Connect Core section 3.1.3.1. and 12.
 * <p>
 * Note: OpenId _only_ uses the `authorization_code` and `refresh_token` token
 * types.
 *
 * @author Key Bridge
 * @since v2.0.0 created 2020-08-27
 */
public enum GrantType {

  /**
   * 4.1. Authorization Code Grant Access Token Request
   * <p>
   * The authorization code grant type is used to obtain both access tokens and
   * refresh tokens and is optimized for confidential clients. This is a
   * redirection-based flow.
   * <pre>
   *  +----------+
   *  | Resource |
   *  |   Owner  |
   *  |          |
   *  +----------+
   *       ^
   *       |
   *      (B)
   *  +----|-----+          Client Identifier      +---------------+
   *  |         -+----(A)-- + Redirection URI ----=|               |
   *  |  User-   |                                 | Authorization |
   *  |  Agent  -+----(B)-- User authenticates ---=|     Server    |
   *  |          |                                 |               |
   *  |         -+=---(C)-- Authorization Code ----|               |
   *  +-|----|---+                                 +---------------+
   *    |    |                                         ^      v
   *   (A)  (C)                                        |      |
   *    |    |                                         |      |
   *    ^    v                                         |      |
   *  +---------+                                      |      |
   *  |         |----(D)-- Authorization Code ---------'      |
   *  |  Client |          + Redirection URI                  |
   *  |         |                                             |
   *  |         |=---(E)----- Access Token -------------------'
   *  +---------+       (w/ Optional Refresh Token) </pre>
   * <p>
   * The authorization code grant is used when an application exchanges an
   * authorization code for an access token. After the user returns to the
   * application via the redirect URL, the application will get the
   * authorization code from the URL and use it to request an access token. This
   * request will be made to the token endpoint.
   */
  authorization_code,
  /**
   * 4.2. Implicit Grant
   * <p>
   * The implicit grant type is used to obtain access tokens (it does not
   * support the issuance of refresh tokens) and is optimized for public clients
   * known to operate a particular redirection URI. These clients are typically
   * implemented in a browser using a scripting language such as JavaScript.
   */
  implicit,
  /**
   * 4.3. Resource Owner Password Credentials Grant Access Token Request
   * <p>
   * The resource owner password credentials grant type is suitable in cases
   * where the resource owner has a trust relationship with the client, such as
   * the device operating system or a highly privileged application. The
   * authorization server should take special care when enabling this grant type
   * and only allow it when other flows are not viable.
   * <pre>
   *  +----------+
   *  | Resource |
   *  |  Owner   |
   *  |          |
   *  +----------+
   *       v
   *       |    Resource Owner
   *      (A) Password Credentials
   *       |
   *       v
   *  +---------+                                  +---------------+
   *  |         |---(B)---- Resource Owner -------=|               |
   *  |         |         Password Credentials     | Authorization |
   *  | Client  |                                  |     Server    |
   *  |         |=--(C)---- Access Token ----------|               |
   *  |         |    (w/ Optional Refresh Token)   |               |
   *  +---------+                                  +---------------+ </pre>
   */
  password,
  /**
   * 4.4. Client Credentials Grant Access Token Request
   * <p>
   * The client can request an access token using only its client credentials
   * (or other supported means of authentication) when the client is requesting
   * access to the protected resources under its control, or those of another
   * resource owner that have been previously arranged with the authorization
   * server
   * <pre>
   * +---------+                                  +---------------+
   * |         |                                  |               |
   * |         |---(A)- Client Authentication ---=| Authorization |
   * | Client  |                                  |     Server    |
   * |         |=--(B)---- Access Token ---------|               |
   * |         |                                  |               |
   * +---------+                                  +---------------+ </pre>
   * <p>
   * The Client Credentials grant is used when applications request an access
   * token to access their own resources, not on behalf of a user.
   */
  client_credentials,
  /**
   * 6. Refreshing an Access Token
   * <p>
   * Available if the authorization server issued a refresh token to the client.
   * Because refresh tokens are typically long-lasting credentials used to
   * request additional access tokens, the refresh token is bound to the client
   * to which it was issued.
   */
  refresh_token,

  /**
   * The JWT Bearer Token Grant Type defined in OAuth JWT Bearer Token Profiles
   * [RFC7523].
   *
   * @see <a href="https://tools.ietf.org/html/rfc7523">JSON Web Token (JWT)
   * Profile for OAuth 2.0 Client Authentication and Authorization Grants</a>
   */
  @JsonbProperty("urn:ietf:params:oauth:grant-type:jwt-bearer")
  jwt_bearer,
  /**
   * The SAML 2.0 Bearer Assertion Grant defined in OAuth SAML 2 Bearer Token
   * Profiles [RFC7522].
   *
   * @see <a href="https://tools.ietf.org/html/rfc7522">Security Assertion
   * Markup Language (SAML) 2.0 Profile for OAuth 2.0 Client Authentication and
   * Authorization Grants</a>
   */
  @JsonbProperty("urn:ietf:params:oauth:grant-type:saml2-bearer")
  saml2_bearer,
  /**
   * Indicates that a token exchange is being performed.
   *
   * @see <a href="https://tools.ietf.org/html/rfc8693">OAuth 2.0 Token
   * Exchange</a>
   */
  @JsonbProperty("urn:ietf:params:oauth:grant-type:token-exchange")
  token_exchange,

  /**
   * Device Access Token Request
   *
   * @see <a href="https://tools.ietf.org/html/rfc8693">OAuth 2.0 Device
   * Grant</a>
   */
  @JsonbProperty("urn:ietf:params:oauth:grant-type:device_code")
  device_code;

  /**
   * RFC 7591 OAuth 2.0 Dynamic Registration
   * <p>
   * 2.1. Relationship between Grant Types and Response Types
   * <p>
   * The "grant_types" and "response_types" values described above are partially
   * orthogonal, as they refer to arguments passed to different endpoints in the
   * OAuth protocol. However, they are related in that the "grant_types"
   * available to a client influence the "response_types" that the client is
   * allowed to use, and vice versa. For instance, a "grant_types" value that
   * includes "authorization_code" implies a "response_types" value that
   * includes "code", as both values are defined as part of the OAuth 2.0
   * authorization code grant. As such, a server supporting these fields SHOULD
   * take steps to ensure that a client cannot register itself into an
   * inconsistent state, for example, by returning an "invalid_client_metadata"
   * error response to an inconsistent registration request.
   * <p>
   * The correlation between the two fields is listed in the table below.
   * <pre>
   * +-----------------------------------------------+-------------------+
   * | grant_types value includes:                   | response_types    |
   * |                                               | value includes:   |
   * +-----------------------------------------------+-------------------+
   * | authorization_code                            | code              |
   * | implicit                                      | token  id_token   |
   * | password                                      | (none)            |
   * | client_credentials                            | (none)            |
   * | refresh_token                                 | (none)            |
   * | urn:ietf:params:oauth:grant-type:jwt-bearer   | (none)            |
   * | urn:ietf:params:oauth:grant-type:saml2-bearer | (none)            |
   * +-----------------------------------------------+-------------------+</pre>
   *
   * @return correlated ResponseType for the current grant type
   */
  public Collection<ResponseType> getResponseTypes() {
    switch (this) {

      case authorization_code:
        return Collections.singleton(ResponseType.code);

      /**
       * `id_token` is added in the OpenId specification. OpenId Connect Core
       * 3.2.2.1. Authentication Request. When using the Implicit Flow, the
       * response_type value is `id_token token` or `id_token`.
       */
      case implicit:
        return Arrays.asList(ResponseType.token, ResponseType.id_token);

      case jwt_bearer:
      case saml2_bearer:
      case token_exchange:
      case device_code:
        return Collections.singleton(ResponseType.token);

      case password:
      case client_credentials:
      case refresh_token:
      default:
        return Collections.EMPTY_SET;
    }
  }
}
