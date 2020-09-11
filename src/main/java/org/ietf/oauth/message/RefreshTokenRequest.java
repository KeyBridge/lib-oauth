/*
 * Copyright 2018 Key Bridge.
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
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.ws.rs.core.MultivaluedMap;
import org.ietf.oauth.AbstractUrlEncodedMessage;
import org.ietf.oauth.OauthUtility;
import org.ietf.oauth.adapter.JsonCollectionAdapter;

/**
 * RFC 6749 OAuth 2.0
 * <p>
 * 1.5. Refresh Token
 * <p>
 * Refresh tokens are credentials used to obtain access tokens. Refresh tokens
 * are issued to the client by the authorization server and are used to obtain a
 * new access token when the current access token becomes invalid or expires, or
 * to obtain additional access tokens with identical or narrower scope (access
 * tokens may have a shorter lifetime and fewer permissions than authorized by
 * the resource owner). Issuing a refresh token is optional at the discretion of
 * the authorization server. If the authorization server issues a refresh token,
 * it is included when issuing an access token (i.e., step (D) in Figure 1).
 * <p>
 * A refresh token is a string representing the authorization granted to the
 * client by the resource owner. The string is usually opaque to the client. The
 * token denotes an identifier used to retrieve the authorization information.
 * Unlike access tokens, refresh tokens are intended for use only with
 * authorization servers and are never sent to resource servers.
 * <p>
 * 6. Refreshing an Access Token
 * <p>
 * If the authorization server issued a refresh token to the client, the client
 * makes a refresh request to the token endpoint by adding the following
 * parameters using the "application/x-www-form-urlencoded" format per Appendix
 * B with a character encoding of UTF-8 in the HTTP request entity-body:
 * grant_type, refresh_token, scope.
 * <p>
 * Because refresh tokens are typically long-lasting credentials used to request
 * additional access tokens, the refresh token is bound to the client to which
 * it was issued. If the client type is confidential or the client was issued
 * client credentials (or assigned other authentication requirements), the
 * client MUST authenticate with the authorization server as described in
 * Section 3.2.1.
 * <p>
 * For example, the client makes the following HTTP request using
 * transport-layer security (with extra line breaks for display purposes only):
 * <pre>
 * POST /token HTTP/1.1
 * Host: server.example.com
 * Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
 * Content-Type: application/x-www-form-urlencoded
 *
 * grant_type=refresh_token&amp;refresh_token=tGzv3JOkF0XG5Qx2TlKWIA
 * </pre>
 * <p>
 * If valid and authorized, the authorization server issues an access token as
 * described in Section 5.1. If the request failed verification or is invalid,
 * the authorization server returns an error response as described in Section
 * 5.2.
 * <p>
 * The authorization server MAY issue a new refresh token, in which case the
 * client MUST discard the old refresh token and replace it with the new refresh
 * token. The authorization server MAY revoke the old refresh token after
 * issuing a new refresh token to the client. If a new refresh token is issued,
 * the refresh token scope MUST be identical to that of the refresh token
 * included by the client in the request.
 *
 * @author Key Bridge 10/08/17
 * @since 0.2.0
 */
public class RefreshTokenRequest extends AbstractUrlEncodedMessage implements Serializable {

  /**
   * 12.1. Refresh Request
   */
  private static final String REFRESH_TOKEN = "refresh_token";

  /**
   * REQUIRED. Value MUST be set to "refresh_token".
   */
  @JsonbProperty("grant_type")
  private String grantType;
  /**
   * REQUIRED, if the client is not authenticating with the authorization server
   * as described in OpenID Section 3.2.1.
   */
  @JsonbProperty("client_id")
  private String clientId;
  /**
   * REQUIRED. The refresh token issued to the client.
   */
  @JsonbProperty("refresh_token")
  private String refreshToken;
  /**
   * OPTIONAL. The scope of the access request as described by Section 3.3. The
   * requested scope MUST NOT include any scope not originally granted by the
   * resource owner, and if omitted is treated as equal to the scope originally
   * granted by the resource owner.
   */
  @JsonbTypeAdapter(JsonCollectionAdapter.class)
  private Collection<String> scope;

  public RefreshTokenRequest() {
    this.grantType = refreshToken;
  }

  /**
   * Construct a new TokenRequest REFRESH instance.
   * <p>
   * The refresh token is included for regular OAuth 2.0 reasons, so that the
   * client can get a new access token when the old one expires without
   * involving explicit user authentication again.
   * <p>
   * The using application must set the SCOPE.
   *
   * @param client_id     the client id
   * @param refresh_token the previously issued token (if any)
   * @return a RefreshTokenRequest instance with no scope.
   */
  public static RefreshTokenRequest getInstance(String client_id, String refresh_token) {
    RefreshTokenRequest tr = new RefreshTokenRequest();
    tr.setGrantType(REFRESH_TOKEN);
    tr.setClientId(client_id);
    tr.setRefreshToken(refresh_token);
    return tr;
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param multivaluedMap a MultivaluedMap instance
   * @return a new class instance
   * @throws java.lang.Exception on mv-map parse error
   */
  public static RefreshTokenRequest fromMultivaluedMap(MultivaluedMap<String, String> multivaluedMap) throws Exception {
    return OauthUtility.fromMultivaluedMap(multivaluedMap, RefreshTokenRequest.class);
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param urlEncodedString a URL encoded string
   * @return the class instance
   * @throws Exception on error
   */
  public static RefreshTokenRequest fromUrlEncodedString(String urlEncodedString) throws Exception {
    return OauthUtility.fromUrlEncodedString(urlEncodedString, RefreshTokenRequest.class);
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getGrantType() {
    return grantType;
  }

  public void setGrantType(String grantType) {
    this.grantType = grantType;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public Collection<String> getScope() {
    if (scope == null) {
      scope = new HashSet<>();
    }
    return scope;
  }

  public void setScope(Collection<String> scope) {
    this.scope = scope;
  }

  public void addScope(String scope) {
    getScope().add(scope);
  }//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Equals and Hashcode">
  @Override
  public int hashCode() {
    int hash = 3;
    hash = 31 * hash + Objects.hashCode(this.grantType);
    hash = 31 * hash + Objects.hashCode(this.clientId);
    hash = 31 * hash + Objects.hashCode(this.refreshToken);
    hash = 31 * hash + Objects.hashCode(this.scope);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final RefreshTokenRequest other = (RefreshTokenRequest) obj;
    if (!Objects.equals(this.grantType, other.grantType)) {
      return false;
    }
    if (!Objects.equals(this.clientId, other.clientId)) {
      return false;
    }
    if (!Objects.equals(this.refreshToken, other.refreshToken)) {
      return false;
    }
    if (!this.getScope().containsAll(other.getScope())) {
      return false;
    }
    return other.getScope().containsAll(this.getScope());
  }//</editor-fold>

}
