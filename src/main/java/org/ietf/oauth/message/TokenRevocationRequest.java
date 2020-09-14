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
import javax.ws.rs.core.MultivaluedMap;
import org.ietf.oauth.AbstractUrlEncodedMessage;
import org.ietf.oauth.OauthUtility;
import org.ietf.oauth.type.TokenType;

/**
 * RFC 7009 Token Revocation 2.1. Revocation Request
 * <p>
 * Token Revocation Request allows clients to notify the authorization server
 * that a previously obtained refresh or access token is no longer needed. This
 * allows the authorization server to clean up security credentials. A
 * revocation request will invalidate the actual token and, if applicable, other
 * tokens based on the same authorization grant. For example, a client may
 * request the revocation of a refresh token with the following request:
 * <pre>
 *   POST /revoke HTTP/1.1
 *   Host: server.example.com
 *   Content-Type: application/x-www-form-urlencoded
 *   Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
 *
 *   token=45ghiukldjahdnhzdauz&amp;token_type_hint=refresh_token</pre>
 *
 * @see <a href="https://tools.ietf.org/html/rfc7009">OAuth 2.0 Token
 * Revocation</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-14
 */
public class TokenRevocationRequest extends AbstractUrlEncodedMessage {

  /**
   * REQUIRED. The token that the client wants to get revoked.
   */
  private String token;

  /**
   * OPTIONAL. A hint about the type of the token submitted for revocation.
   * Clients MAY pass this parameter in order to help the authorization server
   * to optimize the token lookup. If the server is unable to locate the token
   * using the given hint, it MUST extend its search across all of its supported
   * token types. An authorization server MAY ignore this parameter,
   * particularly if it is able to detect the token type automatically. This
   * specification defines two such values:
   * <p>
   * - access_token: An access token as defined in [RFC6749], Section 1.4 <br>
   * - refresh_token: A refresh token as defined in [RFC6749], Section 1.5
   * <p>
   * Specific implementations, profiles, and extensions of this specification
   * MAY define other values for this parameter using the registry defined in
   * Section 4.1.2.
   */
  @JsonbProperty("token_type")
  private TokenType tokenType;

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param multivaluedMap a MultivaluedMap instance
   * @return a new class instance
   * @throws java.lang.Exception on mv-map parse error
   */
  public static TokenRevocationRequest fromMultivaluedMap(MultivaluedMap<String, String> multivaluedMap) throws Exception {
    return OauthUtility.fromMultivaluedMap(multivaluedMap, TokenRevocationRequest.class);
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param urlEncodedString a URL encoded string
   * @return the class instance
   * @throws Exception on error
   */
  public static TokenRevocationRequest fromUrlEncodedString(String urlEncodedString) throws Exception {
    return OauthUtility.fromUrlEncodedString(urlEncodedString, TokenRevocationRequest.class);
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public TokenType getTokenType() {
    return tokenType;
  }

  public void setTokenType(TokenType tokenType) {
    this.tokenType = tokenType;
  }//</editor-fold>

}
