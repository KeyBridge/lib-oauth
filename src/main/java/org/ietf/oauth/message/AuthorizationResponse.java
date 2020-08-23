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
import java.util.Objects;
import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.core.MultivaluedMap;
import org.ietf.oauth.AbstractUrlEncodedMessage;
import org.ietf.oauth.type.ErrorResponseType;

/**
 * A combined response object handling both the success and fail conditions
 * described in sections 4.1.2 and 4.1.2.1.
 * <p>
 * RFC 6749 OAuth 2.0
 * <p>
 * 4.1.2. Authorization Response
 * <p>
 * If the resource owner grants the access request, the authorization server
 * issues an authorization code and delivers it to the client. For example, the
 * authorization server redirects the user-agent by sending the following HTTP
 * response:
 * <pre>
 * HTTP/1.1 302 Found
 * Location: https://client.example.com/cb?code=SplxlOBeZQQYbYS6WxSbIA&amp;state=xyz
 * </pre>
 * <p>
 * 4.1.2.1. Error Response
 * <p>
 * If the request fails due to a missing, invalid, or mismatching redirection
 * URI, or if the client identifier is missing or invalid, the authorization
 * server SHOULD inform the resource owner of the error and MUST NOT
 * automatically redirect the user-agent to the invalid redirection URI.
 * <p>
 * For example, the authorization server redirects the user-agent by sending the
 * following HTTP response:
 * <pre>
 * HTTP/1.1 302 Found
 * Location: https://client.example.com/cb?error=access_denied&amp;state=xyz
 * </pre>
 *
 * @author Key Bridge 10/08/17
 * @since v0.2.0
 */
public class AuthorizationResponse extends AbstractUrlEncodedMessage implements Serializable {

  /**
   * REQUIRED. The authorization code generated by the authorization server. The
   * authorization code MUST expire shortly after it is issued to mitigate the
   * risk of leaks. A maximum authorization code lifetime of 10 minutes is
   * RECOMMENDED. The client MUST NOT use the authorization code more than once.
   * If an authorization code is used more than once, the authorization server
   * MUST deny the request and SHOULD revoke (when possible) all tokens
   * previously issued based on that authorization code. The authorization code
   * is bound to the client identifier and redirection URI.
   */
  private String code;
  /**
   * RECOMMENDED. An opaque value used by the client to maintain state between
   * the request and callback. The authorization server includes this value when
   * redirecting the user-agent back to the client. The parameter SHOULD be used
   * for preventing cross-site request forgery as described in Section 10.12.
   */
  private String state;

  /**
   * REQUIRED. A single enumerated ASCII [USASCII] error code.
   * <p>
   * 4.1.2.1. Error Response
   * <p>
   * If the request fails due to a missing, invalid, or mismatching redirection
   * URI, or if the client identifier is missing or invalid, the authorization
   * server SHOULD inform the resource owner of the error and MUST NOT
   * automatically redirect the user-agent to the invalid redirection URI.
   * <p>
   * If the resource owner denies the access request or if the request fails for
   * reasons other than a missing or invalid redirection URI, the authorization
   * server informs the client by adding the following parameters to the query
   * component of the redirection URI using the
   * "application/x-www-form-urlencoded" format, per Appendix B:
   */
  private String error;
  /**
   * A human readable note about the error. This is useful for debugging.
   */
  @JsonbProperty("error_message")
  private String errorMessage;

  /**
   * Construct a new AuthorizationResponse instance
   *
   * @param code  The authorization code generated by the authorization server.
   * @param state An opaque value used by the client to maintain state between
   *              the request and callback.
   * @return A new AuthorizationResponse instance
   */
  public static AuthorizationResponse getInstance(String code, String state) {
    AuthorizationResponse ar = new AuthorizationResponse();
    ar.setCode(code);
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
  public static AuthorizationResponse getInstance(MultivaluedMap<String, String> multivaluedMap) throws Exception {
    AuthorizationResponse ar = new AuthorizationResponse();
    ar.readMultivaluedMap(multivaluedMap);
    return ar;
  }

  /**
   * Construct a new error AuthorizationResponse instance
   *
   * @param error The authorization error code generated by the authorization
   *              server. See {@code ErrorResponseType}
   * @param state An opaque value used by the client to maintain state between
   *              the request and callback.
   * @return A new AuthorizationResponse instance
   */
  public static AuthorizationResponse getInstanceError(String error, String state) {
    AuthorizationResponse ar = new AuthorizationResponse();
    ar.setError(error);
    ar.setState(state);
    return ar;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getError() {
    return error;
  }

  public void setError(ErrorResponseType error) {
    this.error = error.name();
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }//</editor-fold>

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 59 * hash + Objects.hashCode(this.code);
    hash = 59 * hash + Objects.hashCode(this.state);
    hash = 59 * hash + Objects.hashCode(this.error);
    hash = 59 * hash + Objects.hashCode(this.errorMessage);
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
    final AuthorizationResponse other = (AuthorizationResponse) obj;
    if (!Objects.equals(this.code, other.code)) {
      return false;
    }
    if (!Objects.equals(this.state, other.state)) {
      return false;
    }
    if (!Objects.equals(this.errorMessage, other.errorMessage)) {
      return false;
    }
    return this.error == other.error;
  }

}
