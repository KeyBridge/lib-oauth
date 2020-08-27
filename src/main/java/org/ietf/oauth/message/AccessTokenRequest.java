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
package org.ietf.oauth.message;

import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.core.MultivaluedMap;
import org.ietf.oauth.AbstractUrlEncodedMessage;
import org.ietf.oauth.type.GrantType;
import org.ietf.oauth.type.ScopeType;

/**
 * RFC 6749 OAuth 2.0
 * <p>
 * 1.4. Access Token
 * <p>
 * Access tokens are credentials used to access protected resources. An access
 * token is a string representing an authorization issued to the client. The
 * string is usually opaque to the client. Tokens represent specific scopes and
 * durations of access, granted by the resource owner, and enforced by the
 * resource server and authorization server.
 * <p>
 * This class supports all four different types of Oauth 2.0 access token
 * request flows:
 * <p>
 * 4.1. Authorization Code Grant <br>
 * 4.2. Implicit Grant  <br>
 * 4.3. Resource Owner Password Credentials Grant  <br>
 * 4.4. Client Credentials Grant  <br>
 * <p>
 * The class should be populated according to the token request type as follows:
 * <pre>
 * +----------------+-+-+-+-+
 * |                |A|I|R|C|
 * +----------------+-+-+-+-+
 * | grant_type     |x| |x|x|
 * | code           |x| | | |
 * | redirect_uri   |x|x| | |
 * | client_id      |x| | | |
 * | username       | | |x| |
 * | password       | | |x| |
 * | scope          | |x|x|x|
 * | response_type  | |x| | |
 * | state          | |x| | |
 * +----------------+-+-+-+-+</pre> 4.1.3. Access Token Request
 * <p>
 * The client makes a request to the token endpoint by sending the parameters
 * using the "application/x-www-form-urlencoded" format. For example, the client
 * makes the following HTTP request using TLS (with extra line breaks for
 * display purposes only):
 * <pre>
 * POST /token HTTP/1.1
 * Host: server.example.com
 * Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
 * Content-Type: application/x-www-form-urlencoded
 *
 *   grant_type=authorization_code&amp;code=SplxlOBeZQQYbYS6WxSbIA
 *   &amp;redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb
 * </pre>
 * <p>
 * An example successful response:
 * <pre>
 * HTTP/1.1 200 OK
 * Content-Type: application/json;charset=UTF-8
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * {
 *   "access_token":"2YotnFZFEjr1zCsicMWpAA",
 *   "token_type":"example",
 *   "expires_in":3600,
 *   "refresh_token":"tGzv3JOkF0XG5Qx2TlKWIA",
 *   "example_parameter":"example_value"
 * }
 * </pre> 4.4. Client Credentials Grant
 * <p>
 * The client can request an access token using only its client credentials (or
 * other supported means of authentication) when the client is requesting access
 * to the protected resources under its control, or those of another resource
 * owner that have been previously arranged with the authorization server (the
 * method of which is beyond the scope of this specification).
 * <p>
 * The authorization server MUST authenticate the client.
 * <p>
 * For example, the client makes the following HTTP request using
 * transport-layer security (with extra line breaks for display purposes only):
 * <pre>
 * POST /token HTTP/1.1
 * Host: server.example.com
 * Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
 * Content-Type: application/x-www-form-urlencoded
 *
 * grant_type=client_credentials
 * </pre> A refresh token SHOULD NOT be included in the response. An example
 * successful response:
 * <pre>
 * HTTP/1.1 200 OK
 * Content-Type: application/json;charset=UTF-8
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * {
 *   "access_token":"2YotnFZFEjr1zCsicMWpAA",
 *   "token_type":"example",
 *   "expires_in":3600,
 *   "example_parameter":"example_value"
 * }
 * </pre> Developer note: Oauth 2.0 Section 4.5. Extension Grants are not
 * supported in this implementation. Extension grants use a server-defined URI
 * as the grant_type. This implementation limits grant types to an enumerated
 * list defined in RFC 6749 OAuth 2.0 Sections 4.1.3, 4.3.2, 4.4.2, and 6. See
 * the {@code GrantType} class for details.
 *
 * @author Key Bridge 10/08/17
 * @since v0.2.0
 */
public class AccessTokenRequest extends AbstractUrlEncodedMessage {

  /**
   * 3.1.3.1. Token Request
   * <p>
   * The default value is "authorization_code".
   */
  private static final GrantType AUTHORIZATION_CODE = GrantType.authorization_code;
  /**
   * 4.1.1. Authorization Request scope is OPTIONAL and describes the scope of
   * the access request.
   */
  private static final ScopeType SCOPE_TYPE = ScopeType.oauth;

  /**
   * The "grant_type" element is defined in RFC 6749 OAuth 2.0 Sections 4.1.3,
   * 4.3.2, 4.4.2, 4.5, and 6.
   * <p>
   * Note that Section 4.5 is NOT supported in this implementation.
   */
  @JsonbProperty("grant_type")
  private GrantType grantType;
  /**
   * REQUIRED. The authorization code received from the authorization server.
   */
  private String code;
  /**
   * REQUIRED, if the "redirect_uri" parameter was included in the authorization
   * request as described in Section 4.1.1, and their values MUST be identical.
   */
  @JsonbProperty("redirect_uri")
  private String redirectUri;
  /**
   * REQUIRED, if the client is not authenticating with the authorization server
   * as described in Section 3.2.1.
   */
  @JsonbProperty("client_id")
  private String clientId;
  /**
   * The resource owner username. The resource owner password credentials grant
   * type is suitable in cases where the resource owner has a trust relationship
   * with the client.
   */
  private String username;
  /**
   * The resource owner password. The resource owner password credentials grant
   * type is suitable in cases where the resource owner has a trust relationship
   * with the client.
   */
  private String password;
  /**
   * RECOMMENDED. An opaque value used by the client to maintain state between
   * the request and callback. The authorization server includes this value when
   * redirecting the user-agent back to the client. The parameter SHOULD be used
   * for preventing cross-site request forgery as described in Section 10.12.
   */
  private String state;
  /**
   * OPTIONAL. The scope of the access request as described by Section 3.3.
   * <p>
   * The authorization and token endpoints allow the client to specify the scope
   * of the access request using the "scope" request parameter. In turn, the
   * authorization server uses the "scope" response parameter to inform the
   * client of the scope of the access token issued.
   * <p>
   * The value of the scope parameter is expressed as a list of space-
   * delimited, case-sensitive strings. The strings are defined by the
   * authorization server. If the value contains multiple space-delimited
   * strings, their order does not matter, and each string adds an additional
   * access range to the requested scope.
   */
  private String scope;

  public AccessTokenRequest() {
    this.grantType = AUTHORIZATION_CODE;
    this.scope = SCOPE_TYPE.name();
  }

  /**
   * Construct a new OAUTH AuthorizationRequest instance.
   *
   * @param client_id The Client Identifier valid at the Authorization Server
   * @param state     An opaque value used by the client to maintain state
   *                  between the request and callback
   * @return a new OAUTH AuthorizationRequest instance.
   */
  public static AccessTokenRequest getInstance(String client_id, String state) {
    AccessTokenRequest ar = new AccessTokenRequest();
    ar.setClientId(client_id);
    ar.setState(state);
    return ar;
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param multivaluedMap a MVMap instance
   * @return a new AuthenticationRequest instance
   * @throws java.lang.Exception on mv-map parse error
   */
  public static AccessTokenRequest getInstance(MultivaluedMap<String, String> multivaluedMap) throws Exception {
    AccessTokenRequest ar = new AccessTokenRequest();
    ar.readMultivaluedMap(multivaluedMap);
    return ar;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public GrantType getGrantType() {
    return grantType;
  }

  /**
   * Set the token request grant type.
   *
   * @param grantType the grant type
   */
  public void setGrantType(GrantType grantType) {
    this.grantType = grantType;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }//</editor-fold>

}
