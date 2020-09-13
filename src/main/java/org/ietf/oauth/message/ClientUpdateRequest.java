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
 * RFC 7592 OAuth 2.0 Dynamic Registration Management 2.2. Client Update Request
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
   * REQUIRED. OAuth 2.0 client identifier string. The client MUST include its
   * "client_id" field in the request, and it MUST be the same as its currently
   * issued client identifier.
   */
  @JsonbProperty("client_id")
  private String clientId;
  /**
   * OPTIONAL. OAuth 2.0 client secret string. If the client includes the
   * "client_secret" field in the request, the value of this field MUST match
   * the currently issued client secret for that client.
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

  /**
   * {@inheritDoc} ClientUpdateRequest requires the `clientId` field.
   */
  @Override
  public boolean isValid() {
    if (!isSet(clientId)) {
      return false;
    }
    return super.isValid();
  }

}
