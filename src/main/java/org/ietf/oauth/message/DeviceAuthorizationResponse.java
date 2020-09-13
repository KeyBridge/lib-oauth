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

import java.time.Duration;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import org.ietf.oauth.adapter.JsonDurationSecondsAdapter;

/**
 * RFC 8628 OAuth 2.0 Device Grant 3.2. Device Authorization Response
 * <p>
 * In response, the authorization server generates a unique device verification
 * code and an end-user code that are valid for a limited time and includes them
 * in the HTTP response body using the "application/json" format [RFC8259] with
 * a 200 (OK) status code. The response contains the following parameters:
 *
 * @see <a href="https://tools.ietf.org/html/rfc8628">OAuth 2.0 Device Grant</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-13
 */
public class DeviceAuthorizationResponse {

  /**
   * REQUIRED. The device verification code.
   */
  @JsonbProperty("device_code")
  private String deviceCode;
  /**
   * REQUIRED. The end-user verification code.
   */
  @JsonbProperty("user_code")
  private String userCode;
  /**
   * REQUIRED. The end-user verification URI on the authorization server. The
   * URI should be short and easy to remember as end users will be asked to
   * manually type it into their user agent.
   */
  @JsonbProperty("verification_uri")
  private String verificationUri;
  /**
   * OPTIONAL. A verification URI that includes the "user_code" (or other
   * information with the same function as the "user_code"), which is designed
   * for non-textual transmission.
   */
  @JsonbProperty("verification_uri_complete")
  private String verificationUriComplete;
  /**
   * REQUIRED. The lifetime in seconds of the "device_code" and "user_code".
   */
  @JsonbProperty("expires_in")
  @JsonbTypeAdapter(JsonDurationSecondsAdapter.class)
  private Duration expiresIn;
  /**
   * OPTIONAL. The minimum amount of time in seconds that the client SHOULD wait
   * between polling requests to the token endpoint. If no value is provided,
   * clients MUST use 5 as the default.
   */
  private String interval;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getDeviceCode() {
    return deviceCode;
  }

  public void setDeviceCode(String deviceCode) {
    this.deviceCode = deviceCode;
  }

  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }

  public String getVerificationUri() {
    return verificationUri;
  }

  public void setVerificationUri(String verificationUri) {
    this.verificationUri = verificationUri;
  }

  public String getVerificationUriComplete() {
    return verificationUriComplete;
  }

  public void setVerificationUriComplete(String verificationUriComplete) {
    this.verificationUriComplete = verificationUriComplete;
  }

  public Duration getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Duration expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getInterval() {
    return interval;
  }

  public void setInterval(String interval) {
    this.interval = interval;
  }//</editor-fold>

}
