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
import java.util.*;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.ws.rs.core.MultivaluedMap;
import org.ietf.oauth.AbstractUrlEncodedMessage;
import org.ietf.oauth.OauthUtility;
import org.ietf.oauth.adapter.JsonCollectionAdapter;
import org.ietf.oauth.type.ResponseType;
import org.ietf.oauth.type.ScopeType;

/**
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
   * <p>
   * Openid 4.2.1. Authorization Request {@code response_type} is REQUIRED and
   * MUST be set to "token".
   */
  private static final ResponseType RESPONSE_TYPE = ResponseType.code;
  /**
   * 4.1.1. Authorization Request scope is OPTIONAL and describes the scope of
   * the access request.
   */
  private static final ScopeType SCOPE_TYPE = ScopeType.oauth;

  /**
   * REQUIRED. Value MUST be set to "code" or "token" (or "mac" for new client
   * registration).
   */
  @JsonbProperty("response_type")
  private ResponseType responseType;
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
  @JsonbProperty("client_id")
  private String clientId;
  /**
   * OPTIONAL. After completing its interaction with the resource owner, the
   * authorization server directs the resource owner's user-agent back to the
   * client. The authorization server redirects the user-agent to the client's
   * redirection endpoint previously established with the authorization server
   * during the client registration process or when making the authorization
   * request.
   */
  @JsonbProperty("redirect_uri")
  private String redirectUri;
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
  @JsonbTypeAdapter(JsonCollectionAdapter.class)
  private Collection<String> scope;
  /**
   * RECOMMENDED. An opaque value used by the client to maintain state between
   * the request and callback. The authorization server includes this value when
   * redirecting the user-agent back to the client. The parameter SHOULD be used
   * for preventing cross-site request forgery as described in Section 10.12.
   */
  private String state;

  public AuthorizationRequest() {
    this.responseType = RESPONSE_TYPE;
    this.scope = new TreeSet<>(Arrays.asList(new String[]{SCOPE_TYPE.name()}));
  }

  /**
   * Construct a new OAUTH AuthorizationRequest instance.
   * <p>
   * The default response type is "CODE". Set to "MAC" for OpenId.
   *
   * @param client_id The Client Identifier valid at the Authorization Server
   * @param state     An opaque value used by the client to maintain state
   *                  between the request and callback
   * @return a new OAUTH AuthorizationRequest instance.
   */
  public static AuthorizationRequest getInstance(String client_id, String state) {
    AuthorizationRequest ar = new AuthorizationRequest();
    ar.setClientId(client_id);
    ar.setState(state);
    return ar;
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param multivaluedMap a MultivaluedMap instance
   * @return a new class instance
   * @throws java.lang.Exception on mv-map parse error
   */
  public static AuthorizationRequest fromMultivaluedMap(MultivaluedMap<String, Object> multivaluedMap) throws Exception {
    return OauthUtility.fromMultivaluedMap(multivaluedMap, AuthorizationRequest.class);
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param urlEncodedString a URL encoded string
   * @return the class instance
   * @throws Exception on error
   */
  public static AuthorizationRequest fromUrlEncodedString(String urlEncodedString) throws Exception {
    return OauthUtility.fromUrlEncodedString(urlEncodedString, AuthorizationRequest.class);
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public ResponseType getResponseType() {
    return responseType;
  }

  public void setResponseType(ResponseType responseType) {
    this.responseType = responseType;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
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

  //<editor-fold defaultstate="collapsed" desc="Equals and Hashcode">
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.responseType);
    hash = 97 * hash + Objects.hashCode(this.clientId);
    hash = 97 * hash + Objects.hashCode(this.redirectUri);
    hash = 97 * hash + Objects.hashCode(this.scope);
    hash = 97 * hash + Objects.hashCode(this.state);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final AuthorizationRequest other = (AuthorizationRequest) obj;
    if (!Objects.equals(this.clientId, other.clientId)) {
      return false;
    }
    if (!Objects.equals(this.redirectUri, other.redirectUri)) {
      return false;
    }
    if (!Objects.equals(this.state, other.state)) {
      return false;
    }
    if (this.responseType != other.responseType) {
      return false;
    }
    if (!this.getScope().containsAll(other.getScope())) {
      return false;
    }
    return other.getScope().containsAll(this.getScope());
  }//</editor-fold>

}
