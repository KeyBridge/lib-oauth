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
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashSet;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.ietf.oauth.adapter.XmlDurationSecondsAdapter;
import org.ietf.oauth.adapter.XmlErrorResponseTypeAdapter;
import org.ietf.oauth.type.ErrorResponseType;

/**
 * <img alt="image" src="doc-files/accessTokenResponse.png">
 * <p>
 * 4.1.4. Access Token Response
 * <p>
 * All Token Responses that contain tokens, secrets, or other sensitive
 * information MUST include the following HTTP response header fields and
 * values:
 * <p>
 * Cache-Control: no-store <br>
 * Pragma: no-cache
 * <p>
 * If the access token request is valid and authorized, the authorization server
 * issues an access token and optional refresh token as described in Section
 * 5.1.
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
 * <p>
 * 3.1.3.4. Token Error Response
 * <p>
 * If the Token Request is invalid or unauthorized, the Authorization Server
 * constructs the error response. The parameters of the Token Error Response are
 * defined as in Section 5.2 of OAuth 2.0 [RFC6749]. The HTTP response body uses
 * the application/json media type with HTTP response code of 400.
 * <p>
 * The following is a non-normative example Token Error Response:
 * <pre>
 * HTTP/1.1 400 Bad Request
 * Content-Type: application/json
 * Cache-Control: no-store
 * Pragma: no-cache
 *
 * {
 *   "error": "invalid_request"
 * }
 * </pre>
 *
 * @author Key Bridge 10/08/17
 * @since 0.2.0
 */
@XmlRootElement(name = "AccessTokenResponse")
@XmlType(name = "AccessTokenResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessTokenResponse implements Serializable {

  /**
   * Request for Comments: 6750 The OAuth 2.0 Authorization Framework: Bearer
   * Token Usage describes how to use bearer tokens in HTTP requests to access
   * OAuth 2.0 protected resources.
   * <p>
   * When sending the access token in the "Authorization" request header field
   * defined by HTTP/1.1 [RFC2617], the client uses the "Bearer" authentication
   * scheme to transmit the access token. For example:
   * <pre>
   * GET /resource HTTP/1.1
   * Host: server.example.com
   * Authorization: Bearer mF_9.B5f-4.1JqM
   * </pre>
   */
  @XmlTransient
  private static final String TOKEN_TYPE_BEARER = "Bearer";

  /**
   * REQUIRED. The access token issued by the authorization server.
   * <p>
   * 3.1.3.8. Access Token Validation
   * <p>
   * When using the Authorization Code Flow, if the ID Token contains an at_hash
   * Claim, the Client MAY use it to validate the Access Token in the same
   * manner as for the Implicit Flow, as defined in Section 3.2.2.9, but using
   * the ID Token and Access Token returned from the Token Endpoint.
   */
  @XmlElement(required = true)
  private String access_token;
  /**
   * Default is "Bearer".
   * <p>
   * REQUIRED. The type of the token issued as described in Section 7.1. Value
   * is case insensitive.
   * <p>
   * RFC 6749 OAuth 2.0 7.1. Access Token Types:
   * <p>
   * The access token type provides the client with the information required to
   * successfully utilize the access token to make a protected resource request
   * (along with type-specific attributes). The client MUST NOT use an access
   * token if it does not understand the token type.
   * <p>
   * For example, the "bearer" token type defined in [RFC6750] is utilized by
   * simply including the access token string in the request:
   * <pre>
   * GET /resource/1 HTTP/1.1
   * Host: example.com
   * Authorization: Bearer mF_9.B5f-4.1JqM
   * </pre>
   */
  @XmlElement(required = true)
  private String token_type;
  /**
   * RECOMMENDED. The lifetime in seconds of the access token. For example, the
   * value "3600" denotes that the access token will expire in one hour from the
   * time the response was generated. If omitted, the authorization server
   * SHOULD provide the expiration time via other means or document the default
   * value.
   * <p>
   * Default is 7 days.
   */
  @XmlJavaTypeAdapter(XmlDurationSecondsAdapter.class)
  private Duration expires_in;
  /**
   * OPTIONAL. The refresh token, which can be used to obtain new access tokens
   * using the same authorization grant as described in Section 6.
   * <p>
   * If the access token request is valid and authorized, the authorization
   * server issues an access token and optional refresh token as described in
   * Section 5.1.
   * <p>
   * Refresh tokens are credentials used to obtain access tokens. Refresh tokens
   * are issued to the client by the authorization server and are used to obtain
   * a new access token when the current access token becomes invalid or
   * expires, or to obtain additional access tokens with identical or narrower
   * scope (access tokens may have a shorter lifetime and fewer permissions than
   * authorized by the resource owner). Issuing a refresh token is optional at
   * the discretion of the authorization server. If the authorization server
   * issues a refresh token, it is included when issuing an access token (i.e.,
   * step (D) in Figure 1).
   */
  private String refresh_token;

  /**
   * OPTIONAL, if identical to the scope requested by the client; otherwise,
   * REQUIRED. The scope of the access token as described by Section 3.3.
   * <p>
   * 3.3. Access Token Scope
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
  @XmlList
  private Collection<String> scope;

  /**
   * REQUIRED if the "state" parameter was present in the client authorization
   * request. The exact value received from the client.
   */
  private String state;

  /**
   * If the Token Request is invalid or unauthorized, the Authorization Server
   * constructs the error response. The parameters of the Token Error Response
   * are defined as in Section 5.2 of OAuth 2.0 [RFC6749]. The HTTP response
   * body uses the application/json media type with HTTP response code of 400.
   */
  @XmlJavaTypeAdapter(XmlErrorResponseTypeAdapter.class)
  private ErrorResponseType error;
  /**
   * A human readable note about the error. This is useful for debugging.
   */
  private String error_message;

  /**
   * A system time stamp when this token response was created. Marked
   * XmlTransient as this field is not intended to be conveyed to the user.
   * <p>
   * Note: the time zone is always "UTC".
   */
  @XmlTransient
  private LocalDateTime created_at;

  public AccessTokenResponse() {
    this.token_type = TOKEN_TYPE_BEARER;
    this.created_at = LocalDateTime.now(ZoneId.of("UTC"));
    this.expires_in = Duration.of(7, ChronoUnit.DAYS);
  }

  public AccessTokenResponse(String token_type) {
    this.token_type = token_type;
  }

  /**
   * Build a new TokenResponse instance. The default token duration is valid for
   * 48 hours.
   *
   * @param access_token The OAuth 2.0 access token; typically a UUID
   * @return A new TokenResponse instance.
   */
  public static AccessTokenResponse getInstance(String access_token) {
    AccessTokenResponse tr = new AccessTokenResponse();
    tr.setAccess_token(access_token);
    return tr;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  /**
   * Get the access token issued by the authorization server.
   *
   * @return The access token
   */
  public String getAccess_token() {
    return access_token;
  }

  /**
   * Set the access token issued by the authorization server.
   *
   * @param access_token The access token
   */
  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  /**
   * Get the type of the token issued as described in Section 7.1. Value is case
   * insensitive.
   *
   * @return Default is "Bearer".
   */
  public String getToken_type() {
    return token_type;
  }

  /**
   * Set the type of the token issued as described in Section 7.1. Value is case
   * insensitive.
   *
   * @param token_type Default is "Bearer".
   */
  public void setToken_type(String token_type) {
    this.token_type = token_type;
  }

  /**
   * The lifetime in seconds of the access token. For example, the value "3600"
   * denotes that the access token will expire in one hour from the time the
   * response was generated. If omitted, the authorization server SHOULD provide
   * the expiration time via other means or document the default value.
   *
   * @return The lifetime in seconds of the access token.
   */
  public Duration getExpires_in() {
    return expires_in;
  }

  /**
   * Set The lifetime in seconds of the access token. For example, the value
   * "3600" denotes that the access token will expire in one hour from the time
   * the response was generated. If omitted, the authorization server SHOULD
   * provide the expiration time via other means or document the default value.
   *
   * @param expires_in The lifetime in seconds of the access token.
   */
  public void setExpires_in(Duration expires_in) {
    this.expires_in = expires_in;
  }

  public String getRefresh_token() {
    return refresh_token;
  }

  public void setRefresh_token(String refresh_token) {
    this.refresh_token = refresh_token;
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
  }

  public ErrorResponseType getError() {
    return error;
  }

  public void setError(ErrorResponseType error) {
    this.error = error;
  }

  public String getError_message() {
    return error_message;
  }

  public void setError_message(String error_message) {
    this.error_message = error_message;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }//</editor-fold>

  /**
   * Convenience method to inspect this access token and determine if it has
   * expired.
   *
   * @return TRUE if expired, otherwise FALSE (= not expired)
   */
  public boolean isExpired() {
    return LocalDateTime.now(ZoneId.of("UTC")).isAfter(created_at.plus(expires_in));
  }

}
