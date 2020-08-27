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

/**
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
   * refresh tokens and is optimized for confidential clients.
   * <p>
   * This is a redirection-based flow.
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
   *  |         -+----(A)-- & Redirection URI ----=|               |
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
   *  |  Client |          & Redirection URI                  |
   *  |         |                                             |
   *  |         |=---(E)----- Access Token -------------------'
   *  +---------+       (w/ Optional Refresh Token) </pre>
   */
  authorization_code,
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
}
