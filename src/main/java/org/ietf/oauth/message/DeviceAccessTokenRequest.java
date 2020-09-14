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
import javax.json.bind.annotation.JsonbTypeAdapter;
import org.ietf.oauth.AbstractUrlEncodedMessage;
import org.ietf.oauth.adapter.JsonGrantTypeAdapter;
import org.ietf.oauth.type.GrantType;

/**
 * RFC 8628 OAuth 2.0 Device Grant 3.4. Device Access Token Request
 * <p>
 * After displaying instructions to the user, the client creates an access token
 * request and sends it to the token endpoint (as defined by Section 3.2 of
 * [RFC6749]) with a "grant_type" of
 * "urn:ietf:params:oauth:grant-type:device_code". This is an extension grant
 * type (as defined by Section 4.5 of [RFC6749]). For example, the client makes
 * the following HTTPS request (line breaks are for display purposes only):
 * <pre>
 * POST /token HTTP/1.1
 * Host: server.example.com
 * Content-Type: application/x-www-form-urlencoded
 *
 * grant_type=urn%3Aietf%3Aparams%3Aoauth%3Agrant-type%3Adevice_code
 * &amp;device_code=GmRhmhcxhwAzkoEqiMEg_DnyEysNkuNhszIySk9eS
 * &amp;client_id=1406020730</pre>
 * <p>
 * If the user has approved the grant, the token endpoint responds with a
 * success response defined in Section 5.1 of [RFC6749] (i.e. a
 * {@code AccessTokenResponse}) ; otherwise, it responds with an error, as
 * defined in Section 5.2 of [RFC6749] (i.e. a {@code ErrorResposne}.
 *
 * @see <a href="https://tools.ietf.org/html/rfc8628">OAuth 2.0 Device Grant</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-13
 */
public class DeviceAccessTokenRequest extends AbstractUrlEncodedMessage {

  private static final GrantType GRANT_TYPE = GrantType.device_code;

  /**
   * REQUIRED. Value MUST be set to
   * "urn:ietf:params:oauth:grant-type:device_code".
   */
  @JsonbProperty("grant_type")
  @JsonbTypeAdapter(JsonGrantTypeAdapter.class)
  private final GrantType grantType = GRANT_TYPE;
  /**
   * REQUIRED. The device verification code, "device_code" from the device
   * authorization response, defined in Section 3.2.
   */
  @JsonbProperty("device_code")
  private String deviceCode;
  /**
   * REQUIRED if the client is not authenticating with the authorization server
   * as described in Section 3.2.1. of [RFC6749]. The client identifier as
   * described in Section 2.2 of [RFC6749].
   */
  @JsonbProperty("client_id")
  private String clientId;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public GrantType getGrantType() {
    return grantType;
  }

  public String getDeviceCode() {
    return deviceCode;
  }

  public void setDeviceCode(String deviceCode) {
    this.deviceCode = deviceCode;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }//</editor-fold>

}
