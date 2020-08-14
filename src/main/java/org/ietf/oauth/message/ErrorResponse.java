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

import java.io.Serializable;
import javax.xml.bind.annotation.*;
import org.ietf.oauth.type.ErrorResponseType;

/**
 * 4.2.2.1. Error Response
 * <p>
 * If the request fails due to a missing, invalid, or mismatching redirection
 * URI, or if the client identifier is missing or invalid, the authorization
 * server SHOULD inform the resource owner of the error and MUST NOT
 * automatically redirect the user-agent to the invalid redirection URI.
 * <p>
 * If the resource owner denies the access request or if the request fails for
 * reasons other than a missing or invalid redirection URI, the authorization
 * server informs the client by adding the following parameters to the fragment
 * component of the redirection URI using the
 * "application/x-www-form-urlencoded" format, per Appendix B:
 * <p>
 * For example, the authorization server redirects the user-agent by sending the
 * following HTTP response:
 * <p>
 * HTTP/1.1 302 Found Location:
 * https://client.example.com/cb#error=access_denied&state=xyz
 *
 *
 * @author Key Bridge
 * @since v1.0.1 created 2020-08-14
 */
@XmlRootElement(name = "ErrorResponse")
@XmlType(name = "ErrorResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorResponse implements Serializable {

  /**
   * REQUIRED. A single ASCII [USASCII] error code.
   */
  @XmlElement(required = true)
  private ErrorResponseType error;
  /**
   * OPTIONAL. Human-readable ASCII [USASCII] text providing additional
   * information, used to assist the client developer in understanding the error
   * that occurred. Values for the "error_description" parameter MUST NOT
   * include characters outside the set %x20-21 / %x23-5B / %x5D-7E.
   */
  @XmlElement(required = true)
  private String error_description;
  /**
   * OPTIONAL. A URI identifying a human-readable web page with information
   * about the error, used to provide the client developer with additional
   * information about the error. Values for the "error_uri" parameter MUST
   * conform to the URI-reference syntax and thus MUST NOT include characters
   * outside the set %x21 / %x23-5B / %x5D-7E.
   */
  @XmlElement(required = true)
  private String error_uri;
  /**
   * REQUIRED if a "state" parameter was present in the client authorization
   * request. The exact value received from the client.
   */
  @XmlElement(required = true)
  private String state;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public ErrorResponseType getError() {
    return error;
  }

  public void setError(ErrorResponseType error) {
    this.error = error;
  }

  public String getError_description() {
    return error_description;
  }

  public void setError_description(String error_description) {
    this.error_description = error_description;
  }

  public String getError_uri() {
    return error_uri;
  }

  public void setError_uri(String error_uri) {
    this.error_uri = error_uri;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }//</editor-fold>

}
