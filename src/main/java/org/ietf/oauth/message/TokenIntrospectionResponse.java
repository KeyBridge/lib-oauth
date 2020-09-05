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
package org.ietf.oauth.message;

import java.time.ZonedDateTime;
import java.util.Collection;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import org.ietf.oauth.adapter.JsonCollectionAdapter;
import org.ietf.oauth.adapter.JsonZonedDateTimeEpochAdapter;

/**
 * Request for Comments: 7662 OAuth 2.0 Token Introspection response message
 * <p>
 * In OAuth 2.0 [RFC6749], the contents of tokens are opaque to clients. This
 * means that the client does not need to know anything about the content or
 * structure of the token itself, if there is any. However, there is still a
 * large amount of metadata that may be attached to a token, such as its current
 * validity, approved scopes, and information about the context in which the
 * token was issued.
 * <p>
 * The server responds with a JSON object [RFC7159] in "application/ json"
 * format with the following top-level members.
 *
 *
 *
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-05
 */
public class TokenIntrospectionResponse {

  /**
   * REQUIRED. Boolean indicator of whether or not the presented token is
   * currently active. The specifics of a token's "active" state will vary
   * depending on the implementation of the authorization server and the
   * information it keeps about its tokens, but a "true" value return for the
   * "active" property will generally indicate that a given token has been
   * issued by this authorization server, has not been revoked by the resource
   * owner, and is within its given time window of validity (e.g., after its
   * issuance time and before its expiration time). See Section 4 for
   * information on implementation of such checks.
   */
  public boolean active;

  /**
   * String containing a space-separated list of scope values (as described in
   * Section 3.3 of OAuth 2.0 [RFC6749]) that the client can use when requesting
   * access tokens.
   */
  @JsonbTypeAdapter(JsonCollectionAdapter.class)
  protected Collection<String> scope;
  /**
   * OPTIONAL. Client identifier for the OAuth 2.0 client that requested this
   * token.
   */
  @JsonbProperty("client_id")
  private String clientId;

  /**
   * OPTIONAL. Human-readable identifier for the resource owner who authorized
   * this token.
   */
  private String username;
  /**
   * OPTIONAL. Type of the token as defined in Section 5.1 of OAuth 2.0
   * [RFC6749].
   */
  @JsonbProperty("token_type")
  private String tokenType;

  /**
   * OPTIONAL. String representing the issuer of this token, as defined in JWT
   * [RFC7519].
   */
  @JsonbProperty("iss")
  private String issuer;
  /**
   * OPTIONAL. Subject of the token, as defined in JWT [RFC7519]. Usually a
   * machine-readable identifier of the resource owner who authorized this
   * token.
   */
  @JsonbProperty("sub")
  private String subject;
  /**
   * OPTIONAL. Service-specific string identifier or list of string identifiers
   * representing the intended audience for this token, as defined in JWT
   * [RFC7519].
   */
  @JsonbProperty("aud")
  private String audience;
  /**
   * OPTIONAL. Integer timestamp, measured in the number of seconds since
   * January 1 1970 UTC, indicating when this token will expire, as defined in
   * JWT [RFC7519].
   */
  @JsonbProperty("exp")
  @JsonbTypeAdapter(JsonZonedDateTimeEpochAdapter.class)
  private ZonedDateTime expirationTime;
  /**
   * OPTIONAL. Integer timestamp, measured in the number of seconds since
   * January 1 1970 UTC, indicating when this token is not to be used before, as
   * defined in JWT [RFC7519].
   */
  @JsonbProperty("nbf")
  @JsonbTypeAdapter(JsonZonedDateTimeEpochAdapter.class)
  private ZonedDateTime notBefore;
  /**
   * OPTIONAL. Integer timestamp, measured in the number of seconds since
   * January 1 1970 UTC, indicating when this token was originally issued, as
   * defined in JWT [RFC7519].
   */
  @JsonbProperty("iat")
  @JsonbTypeAdapter(JsonZonedDateTimeEpochAdapter.class)
  private ZonedDateTime issuedAt;
  /**
   * OPTIONAL. String identifier for the token, as defined in JWT [RFC7519].
   */
  @JsonbProperty("jti")
  private String jwtId;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Collection<String> getScope() {
    return scope;
  }

  public void setScope(Collection<String> scope) {
    this.scope = scope;
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

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getAudience() {
    return audience;
  }

  public void setAudience(String audience) {
    this.audience = audience;
  }

  public ZonedDateTime getExpirationTime() {
    return expirationTime;
  }

  public void setExpirationTime(ZonedDateTime expirationTime) {
    this.expirationTime = expirationTime;
  }

  public ZonedDateTime getNotBefore() {
    return notBefore;
  }

  public void setNotBefore(ZonedDateTime notBefore) {
    this.notBefore = notBefore;
  }

  public ZonedDateTime getIssuedAt() {
    return issuedAt;
  }

  public void setIssuedAt(ZonedDateTime issuedAt) {
    this.issuedAt = issuedAt;
  }

  public String getJwtId() {
    return jwtId;
  }

  public void setJwtId(String jwtId) {
    this.jwtId = jwtId;
  }//</editor-fold>

}
