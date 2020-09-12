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
 * 3. Client Information Response
 * <p>
 * This specification extends the client information response defined in "OAuth
 * 2.0 Client Dynamic Registration" [RFC7591]. The client information response
 * also contains the fully qualified URL of the client configuration endpoint
 * (Section 2) for this specific client that the client or developer may use to
 * manage the client's registration configuration, as well as a registration
 * access token that is to be used by the client or developer to perform
 * subsequent operations at the client configuration endpoint.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7592">OAuth 2.0 Dynamic
 * Registration Management</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-06
 */
public class ClientInformationResponse extends ClientRegistrationResponse {

  /**
   * REQUIRED. String containing the access token to be used at the client
   * configuration endpoint to perform subsequent operations upon the client
   * registration.
   */
  @JsonbProperty("registration_access_token")
  private String registrationAccessToken;
  /**
   * REQUIRED. String containing the fully qualified URL of the client
   * configuration endpoint for this client.
   */
  @JsonbProperty("registration_client_uri")
  private String registrationClientUri;

  public String getRegistrationClientUri() {
    return registrationClientUri;
  }

  public void setRegistrationClientUri(String registrationClientUri) {
    this.registrationClientUri = registrationClientUri;
  }

  public String getRegistrationAccessToken() {
    return registrationAccessToken;
  }

  public void setRegistrationAccessToken(String registrationAccessToken) {
    this.registrationAccessToken = registrationAccessToken;
  }

  /**
   * Build a client information response with _exact_ copies of the request
   * metadata. The authorization server MUST return all registered metadata
   * about this client, including any fields provisioned by the authorization
   * server itself.
   * <p>
   * Note: The response fields must be populated.
   *
   * @param q the client registration request
   * @return a client information response instance
   */
  public static ClientRegistrationResponse getInstance(AbstractClientMetadata q) {
    ClientRegistrationResponse c = new ClientRegistrationResponse();
    /**
     * Copy the request data.
     */
    c.copyMetadata(q);
    return c;
  }

}
