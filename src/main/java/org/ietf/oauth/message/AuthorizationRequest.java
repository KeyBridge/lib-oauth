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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.oauth.AbstractUrlEncodedMessage;
import org.ietf.oauth.adapter.XmlResponseTypeAdapter;
import org.ietf.oauth.type.ResponseType;
import org.ietf.oauth.type.ScopeType;

/**
 * <img alt="image" src="doc-files/authorizationRequest.png">
 * <p>
 * RFC 6749 OAuth 2.0
 * <p>
 * 4.1.1. Authorization Request
 * <p>
 * To request an access token, the client obtains authorization from the
 * resource owner. The authorization is expressed in the form of an
 * authorization grant, which the client uses to request the access token. OAuth
 * defines four grant types: authorization code, implicit, resource owner
 * password credentials, and client credentials. It also provides an extension
 * mechanism for defining additional grant types.
 * <p>
 * The client constructs the request URI by adding the following parameters to
 * the query component of the authorization endpoint URI using the
 * "application/x-www-form-urlencoded" format, per Appendix B:
 * <p>
 * The following is a non-normative example
 * <pre>
 * Host: server.example.com
 * Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
 *
 * GET /authorize?response_type=code&amp;client_id=s6BhdRkqt3&amp;state=xyz
 * &amp;redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb HTTP/1.1
 * Host: server.example.com
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
 * </pre>
 *
 * @author Key Bridge 10/08/17
 * @since v0.2.0
 */
@XmlRootElement(name = "AuthorizationRequest")
@XmlType(name = "AuthorizationRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorizationRequest extends AbstractUrlEncodedMessage implements Serializable {

  /**
   * 3.1.1. Response Type
   * <p>
   * REQUIRED. The value MUST be one of "code" for requesting an authorization
   * code as described by Section 4.1.1, "token" for requesting an access token
   * (implicit grant) as described by Section 4.2.1, or a registered extension
   * value as described by Section 8.4.
   * <p>
   * Extension response types MAY contain a space-delimited (%x20) list of
   * values, where the order of values does not matter (e.g., response type "a
   * b" is the same as "b a"). The meaning of such composite response types is
   * defined by their respective specifications.
   */
  @XmlTransient
  private static final ResponseType RESPONSE_TYPE = ResponseType.MAC;
  /**
   * 4.1.1. Authorization Request scope is OPTIONAL and describes the scope of
   * the access request.
   */
  @XmlTransient
  private static final ScopeType SCOPE_TYPE = ScopeType.OAUTH;

  /**
   * REQUIRED. Value MUST be set to "code" or "token" (or "mac" for new client
   * registration).
   */
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(XmlResponseTypeAdapter.class)
  private ResponseType response_type;
  /**
   * REQUIRED. OAuth 2.0 Client Identifier valid at the Authorization Server.
   * <p>
   * RFC 6749 OAuth 2.0 2.2. Client Identifier
   * <p>
   * The authorization server issues the registered client a client identifier
   * -- a unique string representing the registration information provided by
   * the client. The client identifier is not a secret; it is exposed to the
   * resource owner and MUST NOT be used alone for client authentication. The
   * client identifier is unique to the authorization server.
   */
  @XmlElement(required = true)
  private String client_id;
  /**
   * OPTIONAL. After completing its interaction with the resource owner, the
   * authorization server directs the resource owner's user-agent back to the
   * client. The authorization server redirects the user-agent to the client's
   * redirection endpoint previously established with the authorization server
   * during the client registration process or when making the authorization
   * request.
   */
  private String redirect_uri;

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
  @XmlElement(required = true)
  @XmlList
  private Collection<String> scope;

  /**
   * RECOMMENDED. An opaque value used by the client to maintain state between
   * the request and callback. The authorization server includes this value when
   * redirecting the user-agent back to the client. The parameter SHOULD be used
   * for preventing cross-site request forgery as described in Section 10.12.
   */
  private String state;

  public AuthorizationRequest() {
    this.response_type = RESPONSE_TYPE;
    this.scope = Arrays.asList(new String[]{SCOPE_TYPE.toText()});
  }

  /**
   * Construct a new OAUTH AuthorizationRequest instance. The default response
   * type is "MAC".
   *
   * @param client_id The Client Identifier valid at the Authorization Server
   * @param state     An opaque value used by the client to maintain state
   *                  between the request and callback
   * @return a new OAUTH AuthorizationRequest instance.
   */
  public static AuthorizationRequest getInstance(String client_id, String state) {
    AuthorizationRequest ar = new AuthorizationRequest();
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
  public static AuthorizationRequest getInstance(MultivaluedMap<String, String> multivaluedMap) throws Exception {
    AuthorizationRequest ar = new AuthorizationRequest();
    ar.readMultivaluedMap(multivaluedMap);
    return ar;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public ResponseType getResponse_type() {
    return response_type;
  }

  public void setResponse_type(ResponseType response_type) {
    this.response_type = response_type;
  }

  public String getClient_id() {
    return client_id;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }

  public String getRedirect_uri() {
    return redirect_uri;
  }

  public void setRedirect_uri(String redirect_uri) {
    this.redirect_uri = redirect_uri;
  }

  public Collection<String> getScope() {
    if (scope == null) {
      scope = new HashSet<>();
    }
    return scope;
  }

  public void setScope(Collection<String> scope) {
    this.scope = scope;
  }

  public void addScope(String scope) {
    getScope().add(scope);
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }//</editor-fold>

}
