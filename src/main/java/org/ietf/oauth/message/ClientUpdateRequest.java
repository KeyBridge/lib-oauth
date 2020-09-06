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

import javax.json.bind.annotation.JsonbProperty;

/**
 * RFC 7592 OAuth 2.0 Dynamic Registration Management
 * <p>
 * To update a previously registered client's registration with an authorization
 * server, the client makes an HTTP PUT request to the client configuration
 * endpoint.
 * <p>
 * This request MUST include all client metadata fields as returned to the
 * client from a previous registration, read, or update operation. The updated
 * client metadata fields request MUST NOT include the
 * "registration_access_token", "registration_client_uri",
 * "client_secret_expires_at", or "client_id_issued_at" fields.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7592">OAuth 2.0 Dynamic
 * Registration Management</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-06
 */
public class ClientUpdateRequest extends AbstractClientMetadata {

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
   * <p>
   * (This is basically a client "password" shared secret and is used for
   * "Basic" type HTTP authentication.)
   */
  @JsonbProperty("client_secret")
  private String clientSecret;

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

}
