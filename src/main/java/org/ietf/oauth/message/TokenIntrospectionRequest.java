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
import org.ietf.oauth.type.TokenTypeHint;

/**
 * Request for Comments: 7662 OAuth 2.0 Token Introspection request message
 * <p>
 * In OAuth 2.0 [RFC6749], the contents of tokens are opaque to clients. This
 * means that the client does not need to know anything about the content or
 * structure of the token itself, if there is any. However, there is still a
 * large amount of metadata that may be attached to a token, such as its current
 * validity, approved scopes, and information about the context in which the
 * token was issued.
 * <p>
 * The protected resource calls the introspection endpoint using an HTTP POST
 * request with parameters sent as "application/x-www-form-urlencoded".
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc7662.html">OAuth
 * Introspection</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-05
 */
public class TokenIntrospectionRequest extends AbstractUrlEncodedMessage {

  /**
   * REQUIRED. The string value of the token. For access tokens, this is the
   * "access_token" value returned from the token endpoint defined in OAuth 2.0
   * [RFC6749], Section 5.1. For refresh tokens, this is the "refresh_token"
   * value returned from the token endpoint as defined in OAuth 2.0 [RFC6749],
   * Section 5.1. Other token types are outside the scope of this specification.
   */
  private String token;
  /**
   * OPTIONAL. A hint about the type of the token submitted for introspection.
   * The protected resource MAY pass this parameter to help the authorization
   * server optimize the token lookup. If the server is unable to locate the
   * token using the given hint, it MUST extend its search across all of its
   * supported token types. An authorization server MAY ignore this parameter,
   * particularly if it is able to detect the token type automatically. Values
   * for this field are defined in the "OAuth Token Type Hints" registry defined
   * in OAuth Token Revocation [RFC7009].
   */
  @JsonbProperty("token_type_hint")
  private TokenTypeHint tokenTypeHint;

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param multivaluedMap a MultivaluedMap instance
   * @return a new class instance
   * @throws java.lang.Exception on mv-map parse error
   */
  public static TokenIntrospectionRequest fromMultivaluedMap(MultivaluedMap<String, String> multivaluedMap) throws Exception {
    return OauthUtility.fromMultivaluedMap(multivaluedMap, TokenIntrospectionRequest.class);
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param urlEncodedString a URL encoded string
   * @return the class instance
   * @throws Exception on error
   */
  public static TokenIntrospectionRequest fromUrlEncodedString(String urlEncodedString) throws Exception {
    return OauthUtility.fromUrlEncodedString(urlEncodedString, TokenIntrospectionRequest.class);
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public TokenTypeHint getTokenTypeHint() {
    return tokenTypeHint;
  }

  public void setTokenTypeHint(TokenTypeHint tokenTypeHint) {
    this.tokenTypeHint = tokenTypeHint;
  }//</editor-fold>

  /**
   * Require a valid token.
   *
   * @return TRUE if the token is configured
   */
  public boolean isValid() {
    return token != null && !token.trim().isEmpty();
  }

}
