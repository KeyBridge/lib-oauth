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

import java.io.Serializable;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.*;
import org.ietf.oauth.AbstractUrlEncodedMessage;
import org.ietf.oauth.type.ScopeType;

/**
 * <img alt="image" src="doc-files/accessTokenRequest.png">
 * <p>
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
 * 4.1.3. Access Token Request
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
 * </pre>
 *
 * @author Key Bridge 10/08/17
 * @since v0.2.0
 */
@XmlRootElement(name = "AccessTokenRequest")
@XmlType(name = "AccessTokenRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessTokenRequest extends AbstractUrlEncodedMessage implements Serializable {

  /**
   * 3.1.3.1. Token Request
   */
  @XmlTransient
  private static final String AUTHORIZATION_CODE = "authorization_code";
  /**
   * 4.1.1. Authorization Request scope is OPTIONAL and describes the scope of
   * the access request.
   */
  @XmlTransient
  private static final ScopeType SCOPE_TYPE = ScopeType.OAUTH;

  /**
   * REQUIRED. Value MUST be set to "authorization_code".
   */
  @XmlElement(required = true)
  private String grant_type;
  /**
   * REQUIRED. The authorization code received from the authorization server.
   */
  @XmlElement(required = true)
  private String code;
  /**
   * REQUIRED, if the "redirect_uri" parameter was included in the authorization
   * request as described in Section 4.1.1, and their values MUST be identical.
   */
  @XmlElement(required = true)
  private String redirect_uri;
  /**
   * REQUIRED, if the client is not authenticating with the authorization server
   * as described in Section 3.2.1.
   */
  @XmlElement(required = true)
  private String client_id;
  /**
   * RECOMMENDED. An opaque value used by the client to maintain state between
   * the request and callback. The authorization server includes this value when
   * redirecting the user-agent back to the client. The parameter SHOULD be used
   * for preventing cross-site request forgery as described in Section 10.12.
   */
  @XmlElement(required = true)
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
    this.grant_type = AUTHORIZATION_CODE;
    this.scope = SCOPE_TYPE.toText();
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
    ar.setClient_id(client_id);
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
  public String getGrant_type() {
    return grant_type;
  }

  public void setGrant_type(String grant_type) {
    this.grant_type = grant_type;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getRedirect_uri() {
    return redirect_uri;
  }

  public void setRedirect_uri(String redirect_uri) {
    this.redirect_uri = redirect_uri;
  }

  public String getClient_id() {
    return client_id;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
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
