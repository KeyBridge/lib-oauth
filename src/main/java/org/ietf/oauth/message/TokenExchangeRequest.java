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

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.ws.rs.core.MultivaluedMap;
import org.ietf.oauth.AbstractUrlEncodedMessage;
import org.ietf.oauth.OauthUtility;
import org.ietf.oauth.adapter.JsonCollectionAdapter;
import org.ietf.oauth.adapter.JsonGrantTypeAdapter;
import org.ietf.oauth.adapter.JsonTokenTypeAdapter;
import org.ietf.oauth.type.GrantType;
import org.ietf.oauth.type.TokenType;

/**
 * Request for Comments: 8693 OAuth 2.0 Token Exchange Request
 * <p>
 * The client makes a token exchange request to the token endpoint with an
 * extension grant type using the HTTP "POST" method. The following parameters
 * are included in the HTTP request entity-body using the
 * "application/x-www-form-urlencoded" format with a character encoding of UTF-8
 * as described in Appendix B of [RFC6749].
 *
 * @see <a href="https://tools.ietf.org/html/rfc8693">OAuth 2.0 Token
 * Exchange</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-05
 */
public class TokenExchangeRequest extends AbstractUrlEncodedMessage {

  /**
   * REQUIRED. The value "urn:ietf:params:oauth:grant-type:token- exchange"
   * indicates that a token exchange is being performed.
   */
  @JsonbProperty("grant_type")
  @JsonbTypeAdapter(JsonGrantTypeAdapter.class)
  private GrantType grantType;
  /**
   * OPTIONAL. A URI that indicates the target service or resource where the
   * client intends to use the requested security token. This enables the
   * authorization server to apply policy as appropriate for the target, such as
   * determining the type and content of the token to be issued or if and how
   * the token is to be encrypted. In many cases, a client will not have
   * knowledge of the logical organization of the systems with which it
   * interacts and will only know a URI of the service where it intends to use
   * the token. The "resource" parameter allows the client to indicate to the
   * authorization server where it intends to use the issued token by providing
   * the location, typically as an https URL, in the token exchange request in
   * the same form that will be used to access that resource. The authorization
   * server will typically have the capability to map from a resource URI value
   * to an appropriate policy. The value of the "resource" parameter MUST be an
   * absolute URI, as specified by Section 4.3 of [RFC3986], that MAY include a
   * query component and MUST NOT include a fragment component. Multiple
   * "resource" parameters may be used to indicate that the issued token is
   * intended to be used at the multiple resources listed. See [OAUTH-RESOURCE]
   * for additional background and uses of the "resource" parameter.
   */
  private String resource;
  /**
   * OPTIONAL. The logical name of the target service where the client intends
   * to use the requested security token. This serves a purpose similar to the
   * "resource" parameter but with the client providing a logical name for the
   * target service. Interpretation of the name requires that the value be
   * something that both the client and the authorization server understand. An
   * OAuth client identifier, a SAML entity identifier [OASIS.saml-core-2.0-os],
   * and an OpenID Connect Issuer Identifier [OpenID.Core] are examples of
   * things that might be used as "audience" parameter values. However,
   * "audience" values used with a given authorization server must be unique
   * within that server to ensure that they are properly interpreted as the
   * intended type of value. Multiple "audience" parameters may be used to
   * indicate that the issued token is intended to be used at the multiple
   * audiences listed. The "audience" and "resource" parameters may be used
   * together to indicate multiple target services with a mix of logical names
   * and resource URIs.
   */
  private String audience;
  /**
   * OPTIONAL. A list of space-delimited, case-sensitive strings, as defined in
   * Section 3.3 of [RFC6749], that allow the client to specify the desired
   * scope of the requested security token in the context of the service or
   * resource where the token will be used. The values and associated semantics
   * of scope are service specific and expected to be described in the relevant
   * service documentation.
   */
  @JsonbTypeAdapter(JsonCollectionAdapter.class)
  private Collection<String> scope;
  /**
   * OPTIONAL. An identifier, as described in Section 3, for the type of the
   * requested security token. If the requested type is unspecified, the issued
   * token type is at the discretion of the authorization server and may be
   * dictated by knowledge of the requirements of the service or resource
   * indicated by the "resource" or "audience" parameter.
   */
  @JsonbProperty("requested_token_type")
  @JsonbTypeAdapter(JsonTokenTypeAdapter.class)
  private TokenType requestedTokenType;
  /**
   * REQUIRED. A security token that represents the identity of the party on
   * behalf of whom the request is being made. Typically, the subject of this
   * token will be the subject of the security token issued in response to the
   * request.
   */
  @JsonbProperty("subject_token")
  private String subjectToken;
  /**
   * REQUIRED. An identifier, as described in Section 3, that indicates the type
   * of the security token in the "subject_token" parameter.
   */
  @JsonbProperty("subject_token_type")
  @JsonbTypeAdapter(JsonTokenTypeAdapter.class)
  private TokenType subjectTokenType;
  /**
   * OPTIONAL. A security token that represents the identity of the acting
   * party. Typically, this will be the party that is authorized to use the
   * requested security token and act on behalf of the subject.
   */
  @JsonbProperty("actor_token")
  private String actorToken;
  /**
   * An identifier, as described in Section 3, that indicates the type of the
   * security token in the "actor_token" parameter. This is REQUIRED when the
   * "actor_token" parameter is present in the request but MUST NOT be included
   * otherwise.
   */
  @JsonbProperty("actor_token_type")
  @JsonbTypeAdapter(JsonTokenTypeAdapter.class)
  private TokenType actorTokenType;

  /**
   * Default no-arg constructor. Sets the `grant_type` field to
   * `token_exchange`.
   */
  public TokenExchangeRequest() {
    this.grantType = GrantType.token_exchange;
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param multivaluedMap a MultivaluedMap instance
   * @return a new class instance
   * @throws java.lang.Exception on mv-map parse error
   */
  public static TokenExchangeRequest fromMultivaluedMap(MultivaluedMap<String, String> multivaluedMap) throws Exception {
    return OauthUtility.fromMultivaluedMap(multivaluedMap, TokenExchangeRequest.class);
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param urlEncodedString a URL encoded string
   * @return the class instance
   * @throws Exception on error
   */
  public static TokenExchangeRequest fromUrlEncodedString(String urlEncodedString) throws Exception {
    return OauthUtility.fromUrlEncodedString(urlEncodedString, TokenExchangeRequest.class);
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public GrantType getGrantType() {
    return grantType;
  }

  /**
   * @deprecated the grant type is set to `token_exchange` in the constructor
   * and is very unlikely to need modification. This method is provided for
   * completeness but should not be needed.
   *
   * Set the grant type value.
   *
   * @param grantType the new GrantType
   */
  public void setGrantType(GrantType grantType) {
    this.grantType = grantType;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getAudience() {
    return audience;
  }

  public void setAudience(String audience) {
    this.audience = audience;
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
  }

  public TokenType getRequestedTokenType() {
    return requestedTokenType;
  }

  public void setRequestedTokenType(TokenType requestedTokenType) {
    this.requestedTokenType = requestedTokenType;
  }

  public String getSubjectToken() {
    return subjectToken;
  }

  public void setSubjectToken(String subjectToken) {
    this.subjectToken = subjectToken;
  }

  public TokenType getSubjectTokenType() {
    return subjectTokenType;
  }

  public void setSubjectTokenType(TokenType subjectTokenType) {
    this.subjectTokenType = subjectTokenType;
  }

  public String getActorToken() {
    return actorToken;
  }

  public void setActorToken(String actorToken) {
    this.actorToken = actorToken;
  }

  public TokenType getActorTokenType() {
    return actorTokenType;
  }

  public void setActorTokenType(TokenType actorTokenType) {
    this.actorTokenType = actorTokenType;
  }//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Equals and Hashcode">
  @Override
  public int hashCode() {
    int hash = 3;
    hash = 37 * hash + Objects.hashCode(this.grantType);
    hash = 37 * hash + Objects.hashCode(this.resource);
    hash = 37 * hash + Objects.hashCode(this.audience);
    hash = 37 * hash + Objects.hashCode(this.scope);
    hash = 37 * hash + Objects.hashCode(this.requestedTokenType);
    hash = 37 * hash + Objects.hashCode(this.subjectToken);
    hash = 37 * hash + Objects.hashCode(this.subjectTokenType);
    hash = 37 * hash + Objects.hashCode(this.actorToken);
    hash = 37 * hash + Objects.hashCode(this.actorTokenType);
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
    final TokenExchangeRequest other = (TokenExchangeRequest) obj;
    if (!Objects.equals(this.resource, other.resource)) {
      return false;
    }
    if (!Objects.equals(this.audience, other.audience)) {
      return false;
    }
    if (!Objects.equals(this.subjectToken, other.subjectToken)) {
      return false;
    }
    if (!Objects.equals(this.actorToken, other.actorToken)) {
      return false;
    }
    if (this.grantType != other.grantType) {
      return false;
    }

    if (this.requestedTokenType != other.requestedTokenType) {
      return false;
    }
    if (this.subjectTokenType != other.subjectTokenType) {
      return false;
    }
    if (this.actorTokenType != other.actorTokenType) {
      return false;
    }
    return (this.getScope().containsAll(other.getScope()) && other.getScope().containsAll(getScope()));
  }//</editor-fold>

}
