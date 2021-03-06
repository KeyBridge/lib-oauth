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

import java.time.Clock;
import java.time.Period;
import java.time.ZonedDateTime;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import org.ietf.oauth.adapter.JsonZonedDateTimeEpochAdapter;

/**
 * RFC 7591 OAuth 2.0 Dynamic Registration 3.2.1. Client Information Response
 * <p>
 * The response contains the client identifier as well as the client secret, if
 * the client is a confidential client. The response MAY contain additional
 * fields as specified by extensions to this specification.
 * <p>
 * Additionally, the authorization server MUST return all registered metadata
 * about this client, including any fields provisioned by the authorization
 * server itself.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7591">OAuth 2.0 Dynamic Client
 * Registration Protocol</a>
 * @author Key Bridge
 * @since v2.1.0 created 2020-08-27
 * @since 2.2.0 use RFC 7592 ClientInformationResponse where client management
 * is enabled
 */
public class ClientRegistrationResponse extends AbstractClientMetadata {

  /**
   * REQUIRED. OAuth 2.0 client identifier string. It SHOULD NOT be currently
   * valid for any other registered client, though an authorization server MAY
   * issue the same client identifier to multiple instances of a registered
   * client at its discretion.
   */
  @JsonbProperty("client_id")
  private String clientId;
  /**
   * OPTIONAL. OAuth 2.0 client secret string. If issued, this MUST be unique
   * for each "client_id" and SHOULD be unique for multiple instances of a
   * client using the same "client_id". This value is used by confidential
   * clients to authenticate to the token endpoint, as described in OAuth 2.0
   * [RFC6749], Section 2.3.1.
   */
  @JsonbProperty("client_secret")
  private String clientSecret;
  /**
   * OPTIONAL. Time at which the client identifier was issued. The time is
   * represented as the number of seconds from 1970-01-01T00:00:00Z as measured
   * in UTC until the date/time of issuance.
   */
  @JsonbProperty("client_id_issued_at")
  @JsonbTypeAdapter(JsonZonedDateTimeEpochAdapter.class)
  private ZonedDateTime issuedAt;
  /**
   * REQUIRED if "client_secret" is issued. Time at which the client secret will
   * expire or 0 if it will not expire. The time is represented as the number of
   * seconds from 1970-01-01T00:00:00Z as measured in UTC until the date/time of
   * expiration.
   * <p>
   * After expiration the client must re-register.
   */
  @JsonbProperty("client_secret_expires_at")
  @JsonbTypeAdapter(JsonZonedDateTimeEpochAdapter.class)
  private ZonedDateTime expiresAt;

  /**
   * Default no-arg constructor. Sets the `issuedAt` field to now.
   */
  public ClientRegistrationResponse() {
    this.issuedAt = ZonedDateTime.now(Clock.systemUTC());
  }

  /**
   * Build a client registration response with _exact_ copies of the request
   * metadata. The authorization server MUST return all registered metadata
   * about this client, including any fields provisioned by the authorization
   * server itself.
   * <p>
   * Note: This copies the metadata only. The response fields must still be
   * populated.
   *
   * @param q the client registration request
   * @return a client registration response instance
   */
  public static ClientRegistrationResponse getInstance(ClientRegistrationRequest q) {
    ClientRegistrationResponse c = new ClientRegistrationResponse();
    /**
     * Copy the request data.
     */
    c.copyMetadata(q);
    return c;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public ZonedDateTime getIssuedAt() {
    return issuedAt;
  }

  public void setIssuedAt(ZonedDateTime issuedAt) {
    this.issuedAt = issuedAt;
  }

  public ZonedDateTime getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(ZonedDateTime expiresAt) {
    this.expiresAt = expiresAt;
  }

  /**
   * Set the `expiresAt` field to a specific period after the `issuedAt` time
   * stamp. After expiration the client must re-register. Period specifies
   * date-based values (years, months, days).
   * <p>
   * If the `issuedAt` field is null sets the `expiresAt` value to _now_ plus
   * the period. Otherwise sets `expiresAt` to the issuedAt value plus the
   * period.
   *
   * @param period the period of the validity
   */
  public void setPeriod(Period period) {
    this.expiresAt = issuedAt == null
                     ? ZonedDateTime.now(Clock.systemUTC()).plus(period)
                     : issuedAt.plus(period);
  }//</editor-fold>

}
