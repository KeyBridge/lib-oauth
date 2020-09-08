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
 * RFC 7591 OAuth 2.0 Dynamic Registration 2. Client Metadata
 * token_endpoint_auth_method
 * <p>
 * String indicator of the requested authentication method for the token
 * endpoint.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7591">OAuth 2.0 Dynamic Client
 * Registration Protocol</a>
 * @author Key Bridge
 * @since v2.1.0 created 2020-08-27
 * @since v2.2.0 add OpenId Connect types 2020-09-06
 */
public enum TokenAuthenticationType {

  /**
   * The client is a public client as defined in OAuth 2.0, Section 2.1, and
   * does not have a client secret.
   * <p>
   * Public clients use a "none" value for the "token_endpoint_auth_method"
   * metadata field and are generally used with the "implicit" grant type. Often
   * these clients will be short-lived in-browser applications requesting access
   * to a user's resources and access is tied to a user's active session at the
   * authorization server.
   * <p>
   * The Client does not authenticate itself at the Token Endpoint, either
   * because it uses only the Implicit Flow (and so does not use the Token
   * Endpoint) or because it is a Public Client with no Client Secret or other
   * authentication mechanism.
   */
  none,
  /**
   * The client uses the HTTP POST parameters as defined in OAuth 2.0, Section
   * 2.3.1.
   * <p>
   * The authorization server MAY support including the client credentials in
   * the request-body using the following parameters: `client_id`,
   * `client_secret`.
   */
  client_secret_post,
  /**
   * The client uses HTTP Basic as defined in OAuth 2.0, Section 2.3.1.
   * <p>
   * Clients in possession of a client password MAY use the HTTP Basic
   * authentication scheme as defined in [RFC2617] to authenticate with the
   * authorization server.
   */
  client_secret_basic,

  //  OpenID Connect Core 1.0 Section 9 Client Authentication
  /**
   * Clients that have received a `client_secret value` from the Authorization
   * Server create a JWT using an HMAC SHA algorithm, such as HMAC SHA-256. The
   * HMAC (Hash-based Message Authentication Code) is calculated using the
   * octets of the UTF-8 representation of the client_secret as the shared key.
   * <p>
   * The JWT MUST contain the following REQUIRED Claim Values and MAY contain
   * the following OPTIONAL Claim Values:
   * <p>
   * iss - REQUIRED. Issuer. This MUST contain the client_id of the OAuth
   * Client.
   * <br>
   * sub - REQUIRED. Subject. This MUST contain the client_id of the OAuth
   * Client.
   * <br>
   * aud - REQUIRED. Audience. The aud (audience) Claim. Value that identifies
   * the Authorization Server as an intended audience. The Authorization Server
   * MUST verify that it is an intended audience for the token. The Audience
   * SHOULD be the URL of the Authorization Server's Token Endpoint.
   * <br>
   * jti - REQUIRED. JWT ID. A unique identifier for the token, which can be
   * used to prevent reuse of the token. These tokens MUST only be used once,
   * unless conditions for reuse were negotiated between the parties; any such
   * negotiation is beyond the scope of this specification.
   * <br>
   * exp - REQUIRED. Expiration time on or after which the ID Token MUST NOT be
   * accepted for processing.
   * <br>
   * iat - OPTIONAL. Time at which the JWT was issued.
   * <p>
   * The authentication token MUST be sent as the value of the
   * [OAuth.Assertions] client_assertion parameter. The value of the
   * [OAuth.Assertions] client_assertion_type parameter MUST be
   * "urn:ietf:params:oauth:client-assertion-type:jwt-bearer", per [OAuth.JWT].
   *
   * @see
   * <a href="https://openid.net/specs/openid-connect-core-1_0.html#ClientAuthentication">OpenID
   * Connect Core 1.0 Client Authentication</a>
   */
  client_secret_jwt,
  /**
   * Clients that have registered a `public key` sign a JWT using that key. The
   * JWT MUST contain the following REQUIRED Claim Values and MAY contain the
   * following OPTIONAL Claim Values:
   * <p>
   * iss - REQUIRED. Issuer. This MUST contain the client_id of the OAuth
   * Client.
   * <br>
   * sub - REQUIRED. Subject. This MUST contain the client_id of the OAuth
   * Client.
   * <br>
   * aud - REQUIRED. Audience. The aud (audience) Claim. Value that identifies
   * the Authorization Server as an intended audience. The Authorization Server
   * MUST verify that it is an intended audience for the token. The Audience
   * SHOULD be the URL of the Authorization Server's Token Endpoint.
   * <br>
   * jti - REQUIRED. JWT ID. A unique identifier for the token, which can be
   * used to prevent reuse of the token. These tokens MUST only be used once,
   * unless conditions for reuse were negotiated between the parties; any such
   * negotiation is beyond the scope of this specification.
   * <br>
   * exp - REQUIRED. Expiration time on or after which the ID Token MUST NOT be
   * accepted for processing.
   * <br>
   * iat - OPTIONAL. Time at which the JWT was issued.
   * <br>
   * The JWT MAY contain other Claims. Any Claims used that are not understood
   * MUST be ignored. The authentication token MUST be sent as the value of the
   * [OAuth.Assertions] client_assertion parameter. The value of the
   * [OAuth.Assertions] client_assertion_type parameter MUST be
   * "urn:ietf:params:oauth:client-assertion-type:jwt-bearer", per [OAuth.JWT].
   *
   * @see
   * <a href="https://openid.net/specs/openid-connect-core-1_0.html#ClientAuthentication">OpenID
   * Connect Core 1.0 Client Authentication</a>
   */
  private_key_jwt,

  /**
   * Indicates that client authentication to the authorization server will occur
   * with mutual TLS utilizing the PKI method of associating a certificate to a
   * client.
   *
   * @see <a href="https://tools.ietf.org/html/rfc8705">8705 OAuth 2.0
   * Mutual-TLS</a>
   */
  tls_client_auth,

}
