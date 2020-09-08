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

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import org.ietf.oauth.adapter.JsonCollectionAdapter;
import org.ietf.oauth.type.TokenType;

/**
 * Request for Comments: 8693 OAuth 2.0 Token Exchange Request Response
 * <p>
 * The authorization server responds to a token exchange request with a normal
 * OAuth 2.0 response from the token endpoint, as specified in Section 5 of
 * [RFC6749]. Additional details and explanation are provided in the following
 * subsections.
 *
 * @see <a href="https://tools.ietf.org/html/rfc8693">OAuth 2.0 Token
 * Exchange</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-05
 */
public class TokenExchangeResponse {

  private static final String TOKEN_TYPE = "Bearer";
  /**
   * REQUIRED. The security token issued by the authorization server in response
   * to the token exchange request. The "access_token" parameter from Section
   * 5.1 of [RFC6749] is used here to carry the requested token, which allows
   * this token exchange protocol to use the existing OAuth 2.0 request and
   * response constructs defined for the token endpoint. The identifier
   * "access_token" is used for historical reasons and the issued token need not
   * be an OAuth access token.
   * <p>
   * Note: The access_token may be a JWT. If a JWT then use the Builder class to
   * build a set of claims.
   */
  @JsonbProperty("access_token")
  private String accessToken;
  /**
   * REQUIRED. An identifier, as described in Section 3, for the representation
   * of the issued security token.
   */
  @JsonbProperty("issued_token_type")
  private TokenType issuedTokenType;
  /**
   * REQUIRED. A case-insensitive value specifying the method of using the
   * access token issued, as specified in Section 7.1 of [RFC6749]. It provides
   * the client with information about how to utilize the access token to access
   * protected resources. For example, a value of "Bearer", as specified in
   * [RFC6750], indicates that the issued security token is a bearer token and
   * the client can simply present it as is without any additional proof of
   * eligibility beyond the contents of the token itself. Note that the meaning
   * of this parameter is different from the meaning of the "issued_token_type"
   * parameter, which declares the representation of the issued security token;
   * the term "token type" is more typically used to mean the structural or
   * syntactical representation of the security token, as it is in all
   * "*_token_type" parameters in this specification. If the issued token is not
   * an access token or usable as an access token, then the "token_type" value
   * "N_A" is used to indicate that an OAuth 2.0 "token_type" identifier is not
   * applicable in that context.
   */
  @JsonbProperty("token_type")
  private String tokenType;
  /**
   * RECOMMENDED. The validity lifetime, in seconds, of the token issued by the
   * authorization server. Oftentimes, the client will not have the inclination
   * or capability to inspect the content of the token, and this parameter
   * provides a consistent and token- type-agnostic indication of how long the
   * token can be expected to be valid. For example, the value 1800 denotes that
   * the token will expire in thirty minutes from the time the response was
   * generated.
   */
  @JsonbProperty("expires_in")
  private Long expiresIn;

  /**
   * OPTIONAL if the scope of the issued security token is identical to the
   * scope requested by the client; otherwise, it is REQUIRED.
   */
  @JsonbTypeAdapter(JsonCollectionAdapter.class)
  private Collection<String> scope;
  /**
   * OPTIONAL. A refresh token will typically not be issued when the exchange is
   * of one temporary credential (the subject_token) for a different temporary
   * credential (the issued token) for use in some other context. A refresh
   * token can be issued in cases where the client of the token exchange needs
   * the ability to access a resource even when the original credential is no
   * longer valid (e.g., user-not-present or offline scenarios where there is no
   * longer any user entertaining an active session with the client). Profiles
   * or deployments of this specification should clearly document the conditions
   * under which a client should expect a refresh token in response to
   * "urn:ietf:params:oauth:grant- type:token-exchange" grant type requests.
   */
  @JsonbProperty("refresh_token")
  private String refreshToken;

  /**
   * Default no-arg constructor. Sets the `token_type` field to "Bearer".
   */
  public TokenExchangeResponse() {
    this.tokenType = TOKEN_TYPE;
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public TokenType getIssuedTokenType() {
    return issuedTokenType;
  }

  public void setIssuedTokenType(TokenType issuedTokenType) {
    this.issuedTokenType = issuedTokenType;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public Long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Long expiresIn) {
    this.expiresIn = expiresIn;
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

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Equals and Hashcode">
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 83 * hash + Objects.hashCode(this.accessToken);
    hash = 83 * hash + Objects.hashCode(this.issuedTokenType);
    hash = 83 * hash + Objects.hashCode(this.tokenType);
    hash = 83 * hash + Objects.hashCode(this.expiresIn);
    hash = 83 * hash + Objects.hashCode(this.scope);
    hash = 83 * hash + Objects.hashCode(this.refreshToken);
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
    final TokenExchangeResponse other = (TokenExchangeResponse) obj;
    if (!Objects.equals(this.accessToken, other.accessToken)) {
      return false;
    }
    if (!Objects.equals(this.tokenType, other.tokenType)) {
      return false;
    }
    if (!Objects.equals(this.refreshToken, other.refreshToken)) {
      return false;
    }
    if (this.issuedTokenType != other.issuedTokenType) {
      return false;
    }
    if (!Objects.equals(this.expiresIn, other.expiresIn)) {
      return false;
    }
    return (this.getScope().containsAll(other.getScope()) && other.getScope().containsAll(getScope()));
  }//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Internal JWT claims builder helper class">
  /**
   * Request for Comments: 8693 OAuth 2.0 Token Exchange
   * <p>
   * 4. JSON Web Token Claims and Introspection Response Parameters helper class
   * to build a TokenExchangeResponse JWT claim set.
   */
  public static class ClaimsBuilder {

    private static final Logger LOG = Logger.getLogger(ClaimsBuilder.class.getName());

    /**
     * 4.1. "act" (Actor) Claim
     * <p>
     * The "act" (actor) claim provides a means within a JWT to express that
     * delegation has occurred and identify the acting party to whom authority
     * has been delegated. The "act" claim value is a JSON object, and members
     * in the JSON object are claims that identify the actor. The claims that
     * make up the "act" claim identify and possibly provide additional
     * information about the actor. For example, the combination of the two
     * claims "iss" and "sub" might be necessary to uniquely identify an actor.
     */
    private Actor actor;
    /**
     * 4.2. "scope" (Scopes) Claim
     * <p>
     * The value of the "scope" claim is a JSON string containing a space-
     * separated list of scopes associated with the token, in the format
     * described in Section 3.3 of [RFC6749].
     */
    private Collection<String> scope;
    /**
     * 4.3. "client_id" (Client Identifier) Claim
     * <p>
     * The "client_id" claim carries the client identifier of the OAuth 2.0
     * [RFC6749] client that requested the token.
     */
    private String clientId;
    /**
     * 4.4. "may_act" (Authorized Actor) Claim
     * <p>
     * The "may_act" claim makes a statement that one party is authorized to
     * become the actor and act on behalf of another party. Claims within the
     * "may_act" claim pertain only to the identity of that party.
     */
    private Actor mayAct;

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public ClaimsBuilder withActor(String actor) {
      this.actor = new Actor(actor);
      return this;
    }

    public ClaimsBuilder withScope(Collection<String> scope) {
      this.scope = scope;
      return this;
    }

    public ClaimsBuilder withClientId(String client_id) {
      this.clientId = client_id;
      return this;
    }

    public ClaimsBuilder withMayAct(String may_act) {
      this.mayAct = new Actor(may_act);
      return this;
    }//</editor-fold>

    /**
     * Build a collection of Public and/or Private claims. See RFC 7519 JSON Web
     * Token (JWT) sections 4.2 and 4.3.
     *
     * @return a non-null HashMap
     */
    public Map<String, Object> buildClaims() {
      Map<String, Object> claims = new HashMap<>();
      if (actor != null) {
        claims.put("act", actor);
      }
      if (clientId != null) {
        claims.put("client_id", clientId);
      }
      if (mayAct != null) {
        claims.put("may_act", mayAct);
      }
      if (scope != null && !scope.isEmpty()) {
        try {
          claims.put("scope", new JsonCollectionAdapter().adaptToJson(scope));
        } catch (Exception exception) {
          LOG.log(Level.INFO, "Error serializing scope {0} .{1}", new Object[]{scope, exception.getMessage()});
        }
      }
      return claims;
    }
  }//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Internal Actor class">
  /**
   * 4.1. "act" (Actor) Claim
   * <p>
   * The "act" (actor) claim provides a means within a JWT to express that
   * delegation has occurred and identify the acting party to whom authority has
   * been delegated. The "act" claim value is a JSON object, and members in the
   * JSON object are claims that identify the actor. The claims that make up the
   * "act" claim identify and possibly provide additional information about the
   * actor.
   * <p>
   * Claims within the "act" claim pertain only to the identity of the current
   * actor.
   */
  public static class Actor {

    /**
     * The identity of the current actor.
     */
    @JsonbProperty("sub")
    private final String subject;
    /**
     * A chain of delegation can be expressed by nesting one "act" claim within
     * another. The outermost "act" claim represents the current actor while
     * nested "act" claims represent prior actors.
     * <p>
     * The least recent actor is the most deeply nested. The nested "act" claims
     * serve as a history trail that connects the initial request and subject
     * through the various delegation steps undertaken before reaching the
     * current actor.
     */
    @JsonbProperty("act")
    private Actor actor;

    public Actor(String subject) {
      this.subject = subject;
    }

    public String getSubject() {
      return subject;
    }

    public Actor getActor() {
      return actor == null ? null : actor.actor;
    }

    public void setActor(String actor) {
      this.actor = new Actor(actor);
    }
  }//</editor-fold>
}
