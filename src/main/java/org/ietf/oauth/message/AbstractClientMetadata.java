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
import java.lang.reflect.Field;
import java.util.*;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import org.ietf.oauth.adapter.JsonCollectionAdapter;
import org.ietf.oauth.type.GrantType;
import org.ietf.oauth.type.ResponseType;
import org.ietf.oauth.type.TokenAuthenticationType;

/**
 * RFC 7591 OAuth 2.0 Dynamic Registration 2. Client Metadata
 * <p>
 * Registered clients have a set of metadata values associated with their client
 * identifier at an authorization server. These client metadata values are used
 * in two ways: <br>
 * - as input values to registration requests, and  <br>
 * - as output values in registration responses.
 * <p>
 * The authorization server MUST ignore any client metadata sent by the client
 * that it does not understand (for instance, by silently removing unknown
 * metadata from the client's registration record during processing). The
 * authorization server MAY reject any requested client metadata values by
 * replacing requested values with suitable defaults or by returning an error
 * response.
 * <p>
 * See also ClientRegistrationRequest, ClientRegistrationResponse
 *
 * @author Key Bridge
 * @since v2.1.0 created 2020-08-27
 * @see <a href="https://tools.ietf.org/html/rfc7591">OAuth 2.0 Dynamic Client
 * Registration Protocol</a>
 */
public abstract class AbstractClientMetadata implements Serializable {

  /**
   * Array of redirection URI strings for use in redirect-based flows such as
   * the authorization code and implicit flows. As required by Section 2 of
   * OAuth 2.0 [RFC6749], clients using flows with redirection MUST register
   * their redirection URI values. Authorization servers that support dynamic
   * registration for redirect-based flows MUST implement support for this
   * metadata value.
   */
  @JsonbProperty("redirect_uris")
  protected Collection<String> redirectUris;
  /**
   * String indicator of the requested authentication method for the token
   * endpoint. Values defined by this specification are:
   * <p>
   * - "none": The client is a public client as defined in OAuth 2.0, Section
   * 2.1, and does not have a client secret.  <br>
   * - "client_secret_post": The client uses the HTTP POST parameters as defined
   * in OAuth 2.0, Section 2.3.1.  <br>
   * - "client_secret_basic": The client uses HTTP Basic as defined in OAuth
   * 2.0, Section 2.3.1.
   * <p>
   * Additional values can be defined via the IANA "OAuth Token Endpoint
   * Authentication Methods" registry established in Section 4.2. Absolute URIs
   * can also be used as values for this parameter without being registered. If
   * unspecified or omitted, the default is "client_secret_basic", denoting the
   * HTTP Basic authentication scheme as specified in Section 2.3.1 of OAuth
   * 2.0.
   */
  @JsonbProperty("token_endpoint_auth_method")
  protected TokenAuthenticationType tokenEndpointAuthMethod;
  /**
   * Array of OAuth 2.0 grant type strings that the client can use at the token
   * endpoint. These grant types are defined as follows:
   * <p>
   * "authorization_code": The authorization code grant type defined in OAuth
   * 2.0, Section 4.1.<br>
   * "implicit": The implicit grant type defined in OAuth 2.0, Section 4.2.<br>
   * "password": The resource owner password credentials grant type defined in
   * OAuth 2.0, Section 4.3.<br>
   * "client_credentials": The client credentials grant type defined in OAuth
   * 2.0, Section 4.4.<br>
   * "refresh_token": The refresh token grant type defined in OAuth 2.0, Section
   * 6.<br>
   * "urn:ietf:params:oauth:grant-type:jwt-bearer": The JWT Bearer Token Grant
   * Type defined in OAuth JWT Bearer Token Profiles [RFC7523].<br>
   * "urn:ietf:params:oauth:grant-type:saml2-bearer": The SAML 2.0 Bearer
   * Assertion Grant defined in OAuth SAML 2 Bearer Token Profiles [RFC7522].
   * <p>
   * If the token endpoint is used in the grant type, the value of this
   * parameter MUST be the same as the value of the "grant_type" parameter
   * passed to the token endpoint defined in the grant type definition.
   * Authorization servers MAY allow for other values as defined in the grant
   * type extension process described in OAuth 2.0, Section 4.5. If omitted, the
   * default behavior is that the client will use only the "authorization_code"
   * Grant Type.
   */
  @JsonbProperty("grant_types")
  protected Collection<GrantType> grantTypes;
  /**
   * Array of the OAuth 2.0 response type strings that the client can use at the
   * authorization endpoint. These response types are defined as follows:
   * <p>
   * "code": The authorization code response type defined in OAuth 2.0, Section
   * 4.1.<br>
   * "token": The implicit response type defined in OAuth 2.0, Section 4.2.
   * <p>
   * If the authorization endpoint is used by the grant type, the value of this
   * parameter MUST be the same as the value of the "response_type" parameter
   * passed to the authorization endpoint defined in the grant type definition.
   * Authorization servers MAY allow for other values as defined in the grant
   * type extension process is described in OAuth 2.0, Section 4.5. If omitted,
   * the default is that the client will use only the "code" response type.
   */
  @JsonbProperty("response_types")
  protected Collection<ResponseType> responseTypes;
  /**
   * Human-readable string name of the client to be presented to the end-user
   * during authorization. If omitted, the authorization server MAY display the
   * raw "client_id" value to the end-user instead. It is RECOMMENDED that
   * clients always send this field. The value of this field MAY be
   * internationalized, as described in Section 2.2.
   */
  @JsonbProperty("client_name")
  protected String clientName;
  /**
   * URL string of a web page providing information about the client. If
   * present, the server SHOULD display this URL to the end-user in a clickable
   * fashion. It is RECOMMENDED that clients always send this field. The value
   * of this field MUST point to a valid web page. The value of this field MAY
   * be internationalized, as described in Section 2.2.
   */
  @JsonbProperty("client_uri")
  protected String clientUri;
  /**
   * URL string that references a logo for the client. If present, the server
   * SHOULD display this image to the end-user during approval. The value of
   * this field MUST point to a valid image file. The value of this field MAY be
   * internationalized, as described in Section 2.2.
   */
  @JsonbProperty("logo_uri")
  protected String logoUri;
  /**
   * String containing a space-separated list of scope values (as described in
   * Section 3.3 of OAuth 2.0 [RFC6749]) that the client can use when requesting
   * access tokens. The semantics of values in this list are service specific.
   * If omitted, an authorization server MAY register a client with a default
   * set of scopes.
   */
  @JsonbTypeAdapter(JsonCollectionAdapter.class)
  protected Collection<String> scope;
  /**
   * Array of strings representing ways to contact people responsible for this
   * client, typically email addresses. The authorization server MAY make these
   * contact addresses available to end-users for support requests for the
   * client.
   */
  protected Collection<String> contacts;
  /**
   * URL string that points to a human-readable terms of service document for
   * the client that describes a contractual relationship between the end-user
   * and the client that the end-user accepts when authorizing the client. The
   * authorization server SHOULD display this URL to the end-user if it is
   * provided. The value of this field MUST point to a valid web page. The value
   * of this field MAY be internationalized, as described in Section 2.2.
   */
  @JsonbProperty("tos_uri")
  protected String tosUri;
  /**
   * URL string that points to a human-readable privacy policy document that
   * describes how the deployment organization collects, uses, retains, and
   * discloses personal data. The authorization server SHOULD display this URL
   * to the end-user if it is provided. The value of this field MUST point to a
   * valid web page. The value of this field MAY be internationalized, as
   * described in Section 2.2.
   */
  @JsonbProperty("policy_uri")
  protected String policyUri;
  /**
   * URL string referencing the client's JSON Web Key (JWK) Set [RFC7517]
   * document, which contains the client's public keys. The value of this field
   * MUST point to a valid JWK Set document. These keys can be used by
   * higher-level protocols that use signing or encryption. For instance, these
   * keys might be used by some applications for validating signed requests made
   * to the token endpoint when using JWTs for client authentication [RFC7523].
   * Use of this parameter is preferred over the "jwks" parameter, as it allows
   * for easier key rotation. The "jwks_uri" and "jwks" parameters MUST NOT both
   * be present in the same request or response.
   */
  @JsonbProperty("jwks_uri")
  protected String jwksUri;
  /**
   * Client's JSON Web Key Set [RFC7517] document value, which contains the
   * client's public keys. The value of this field MUST be a JSON object
   * containing a valid JWK Set. These keys can be used by higher-level
   * protocols that use signing or encryption. This parameter is intended to be
   * used by clients that cannot use the "jwks_uri" parameter, such as native
   * clients that cannot host public URLs. The "jwks_uri" and "jwks" parameters
   * MUST NOT both be present in the same request or response.
   */
  protected String jwks;
  /**
   * A unique identifier string (e.g., a Universally Unique Identifier (UUID))
   * assigned by the client developer or software publisher used by registration
   * endpoints to identify the client software to be dynamically registered.
   * Unlike "client_id", which is issued by the authorization server and SHOULD
   * vary between instances, the "software_id" SHOULD remain the same for all
   * instances of the client software. The "software_id" SHOULD remain the same
   * across multiple updates or versions of the same piece of software. The
   * value of this field is not intended to be human readable and is usually
   * opaque to the client and authorization server.
   */
  @JsonbProperty("software_id")
  protected String softwareId;
  /**
   * A version identifier string for the client software identified by
   * "software_id". The value of the "software_version" SHOULD change on any
   * update to the client software identified by the same "software_id". The
   * value of this field is intended to be compared using string equality
   * matching and no other comparison semantics are defined by this
   * specification. The value of this field is outside the scope of this
   * specification, but it is not intended to be human readable and is usually
   * opaque to the client and authorization server. The definition of what
   * constitutes an update to client software that would trigger a change to
   * this value is specific to the software itself and is outside the scope of
   * this specification.
   */
  @JsonbProperty("software_version")
  protected String softwareVersion;

  /**
   * A software statement containing client metadata values about the client
   * software as claims. This is a compact form encoded string value containing
   * the entire signed JWT.
   * <p>
   * 3.1.1. Client Registration Request Using a Software Statement. In addition
   * to JSON elements, client metadata values MAY also be provided in a software
   * statement, as described in Section 2.3. If the same client metadata name is
   * present in both locations and the software statement is trusted by the
   * authorization server, the value of a claim in the software statement MUST
   * take precedence.
   */
  @JsonbProperty("software_statement")
  protected String softwareStatement;

  /**
   * Key Bridge proprietary field to allow for vendor specific extensions and
   * profiles. The specification allows for arbitrary JSON field names, which
   * require a mutable object type and complex processing logic. A simple map
   * provides a cleaner solution with equivalent functionality.
   * <p>
   * Note that the `extendedParameters` field is not included in the equality
   * evaluation.
   */
  @JsonbProperty("extended_parameters")
  protected Map<String, Object> extendedParameters;

  /**
   * Default no-arg constructor.
   */
  public AbstractClientMetadata() {
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public Collection<String> getRedirectUris() {
    if (redirectUris == null) {
      redirectUris = new TreeSet<>();
    }
    return redirectUris;
  }

  public void setRedirectUris(Collection<String> redirectUris) {
    this.redirectUris = redirectUris;
  }

  public TokenAuthenticationType getTokenEndpointAuthMethod() {
    return tokenEndpointAuthMethod;
  }

  public void setTokenEndpointAuthMethod(TokenAuthenticationType tokenEndpointAuthMethod) {
    this.tokenEndpointAuthMethod = tokenEndpointAuthMethod;
  }

  public Collection<GrantType> getGrantTypes() {
    if (grantTypes == null) {
      grantTypes = new TreeSet<>();
    }
    return grantTypes;
  }

  public void setGrantTypes(Collection<GrantType> grantTypes) {
    this.grantTypes = grantTypes;
  }

  public Collection<ResponseType> getResponseTypes() {
    if (responseTypes == null) {
      responseTypes = new TreeSet<>();
    }
    return responseTypes;
  }

  public void setResponseTypes(Collection<ResponseType> responseTypes) {
    this.responseTypes = responseTypes;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getClientUri() {
    return clientUri;
  }

  public void setClientUri(String clientUri) {
    this.clientUri = clientUri;
  }

  public String getLogoUri() {
    return logoUri;
  }

  public void setLogoUri(String logoUri) {
    this.logoUri = logoUri;
  }

  public Collection<String> getScope() {
    if (scope == null) {
      scope = new TreeSet<>();
    }
    return scope;
  }

  public void setScope(Collection<String> scope) {
    this.scope = scope;
  }

  public Collection<String> getContacts() {
    if (contacts == null) {
      contacts = new TreeSet<>();
    }
    return contacts;
  }

  public void setContacts(Collection<String> contacts) {
    this.contacts = contacts;
  }

  public String getTosUri() {
    return tosUri;
  }

  public void setTosUri(String tosUri) {
    this.tosUri = tosUri;
  }

  public String getPolicyUri() {
    return policyUri;
  }

  public void setPolicyUri(String policyUri) {
    this.policyUri = policyUri;
  }

  public String getJwksUri() {
    return jwksUri;
  }

  public void setJwksUri(String jwksUri) {
    this.jwksUri = jwksUri;
  }

  public String getJwks() {
    return jwks;
  }

  public void setJwks(String jwks) {
    this.jwks = jwks;
  }

  public String getSoftwareId() {
    return softwareId;
  }

  public void setSoftwareId(String softwareId) {
    this.softwareId = softwareId;
  }

  public String getSoftwareVersion() {
    return softwareVersion;
  }

  public void setSoftwareVersion(String softwareVersion) {
    this.softwareVersion = softwareVersion;
  }

  public String getSoftwareStatement() {
    return softwareStatement;
  }

  public void setSoftwareStatement(String softwareStatement) {
    this.softwareStatement = softwareStatement;
  }

  public Map<String, Object> getExtendedParameters() {
    if (extendedParameters == null) {
      extendedParameters = new TreeMap<>();
    }
    return extendedParameters;
  }

  public void setExtendedParameters(Map<String, Object> extendedParameters) {
    this.extendedParameters = extendedParameters;
  }

  /**
   * Shortcut to 'put' an entry to the `extendedParameters` map. Associates the
   * specified value with the specified key in this map (optional operation). If
   * the map previously contained a mapping for the key, the old value is
   * replaced by the specified value.
   *
   * @param key       the key with which the specified value is to be associated
   * @param parameter the value to be associated with the specified key
   */
  public void putExtendedParameter(String key, Object parameter) {
    getExtendedParameters().put(key, parameter);
  }//</editor-fold>

  /**
   * Helper method to prepare this object for serialization. Generally entries
   * with zero elements SHOULD be omitted from the response.
   */
  public void prepareForSerialization() {
    /**
     * Scan the current class and the superclass (the abstract class) declared
     * fields for Collections. If any collection instance is not null AND empty
     * then set the field value to null.
     */
    for (Class cls = this.getClass(); cls != null; cls = cls.getSuperclass()) {
      for (Field field : cls.getDeclaredFields()) {
        try {
          field.setAccessible(true);
          if (field.getType().isAssignableFrom(Map.class)) {
            if (field.get(this) != null && ((Map) field.get(this)).isEmpty()) {
              field.set(this, null);
            }
          } else if (field.getType().isAssignableFrom(Collection.class)) {
            if (field.get(this) != null && ((Collection) field.get(this)).isEmpty()) {
              field.set(this, null);
            }
          }
        } catch (IllegalArgumentException | IllegalAccessException exception) {
          System.err.println("Error clearing empty collection for field " + field.getName());
        }
      }
    }
  }

  /**
   * Helper method to copy metadata from another instance to this. This method
   * copies all common client metada fields EXCEPT the ExtendedParameters map,
   * which if desired must be copied separately.
   *
   * @param source the source instance
   */
  protected void copyMetadata(AbstractClientMetadata source) {
    this.setRedirectUris(source.getRedirectUris());
    this.setTokenEndpointAuthMethod(source.getTokenEndpointAuthMethod());
    this.setGrantTypes(source.getGrantTypes());
    this.setResponseTypes(source.getResponseTypes());
    this.setClientName(source.getClientName());
    this.setClientUri(source.getClientUri());
    this.setLogoUri(source.getLogoUri());
    this.setScope(source.getScope());
    this.setContacts(source.getContacts());
    this.setTosUri(source.getTosUri());
    this.setPolicyUri(source.getPolicyUri());
    this.setJwks(source.getJwks());
    this.setJwksUri(source.getJwksUri());
    this.setSoftwareId(source.getSoftwareId());
    this.setSoftwareVersion(source.getSoftwareVersion());
    this.setSoftwareStatement(source.getSoftwareStatement());
//    this.setExtendedParameters(source.getExtendedParameters());
  }

  //<editor-fold defaultstate="collapsed" desc="Equals and Hashcode">
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.redirectUris);
    hash = 97 * hash + Objects.hashCode(this.tokenEndpointAuthMethod);
    hash = 97 * hash + Objects.hashCode(this.grantTypes);
    hash = 97 * hash + Objects.hashCode(this.responseTypes);
    hash = 97 * hash + Objects.hashCode(this.clientName);
    hash = 97 * hash + Objects.hashCode(this.clientUri);
    hash = 97 * hash + Objects.hashCode(this.logoUri);
    hash = 97 * hash + Objects.hashCode(this.scope);
    hash = 97 * hash + Objects.hashCode(this.contacts);
    hash = 97 * hash + Objects.hashCode(this.tosUri);
    hash = 97 * hash + Objects.hashCode(this.policyUri);
    hash = 97 * hash + Objects.hashCode(this.jwksUri);
    hash = 97 * hash + Objects.hashCode(this.jwks);
    hash = 97 * hash + Objects.hashCode(this.softwareId);
    hash = 97 * hash + Objects.hashCode(this.softwareVersion);
    hash = 97 * hash + Objects.hashCode(this.softwareStatement);
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
    final AbstractClientMetadata other = (AbstractClientMetadata) obj;
    if (!Objects.equals(this.clientName, other.clientName)) {
      return false;
    }
    if (!Objects.equals(this.clientUri, other.clientUri)) {
      return false;
    }
    if (!Objects.equals(this.logoUri, other.logoUri)) {
      return false;
    }
    if (!Objects.equals(this.tosUri, other.tosUri)) {
      return false;
    }
    if (!Objects.equals(this.policyUri, other.policyUri)) {
      return false;
    }
    if (!Objects.equals(this.jwksUri, other.jwksUri)) {
      return false;
    }
    if (!Objects.equals(this.jwks, other.jwks)) {
      return false;
    }
    if (!Objects.equals(this.softwareId, other.softwareId)) {
      return false;
    }
    if (!Objects.equals(this.softwareVersion, other.softwareVersion)) {
      return false;
    }
    if (!Objects.equals(this.softwareStatement, other.softwareStatement)) {
      return false;
    }
    if (this.tokenEndpointAuthMethod != other.tokenEndpointAuthMethod) {
      return false;
    }
    // compare collections and maps
    if (!equalCollections(this.scope, other.scope)) {
      return false;
    }
    if (!equalCollections(this.redirectUris, other.redirectUris)) {
      return false;
    }
    if (!equalCollections(this.grantTypes, other.grantTypes)) {
      return false;
    }
    if (!equalCollections(this.responseTypes, other.responseTypes)) {
      return false;
    }
    if (!equalCollections(this.contacts, other.contacts)) {
      return false;
    }
    /**
     * Note that the extendedParameters is not included in the equality
     * evaluation.
     */
    return true;
  }

  /**
   * Internal method to compare two collections. Evaluates whether both
   * collections fully contain each other. This is needed since the collections
   * may be different types (e.g. HashSet vs. Array).
   *
   * @param thisCollection  this object's collection
   * @param otherCollection the other object's collection
   * @return true if both collections fully contain the other (i.e. are equal)
   */
  protected boolean equalCollections(Collection thisCollection, Collection otherCollection) {
    if (thisCollection == null && otherCollection == null) {
      return true;
    }
    if (thisCollection == null || otherCollection == null) {
      return false;
    }
    if (thisCollection.isEmpty() && otherCollection.isEmpty()) {
      return true;
    }
    ArrayList one = new ArrayList(thisCollection);
    ArrayList two = new ArrayList(otherCollection);
    /**
     * Clean up the 'scope' array. JSONB deserialization has a tendency to add
     * an empty entry.
     */
    one.remove("");
    two.remove("");
    return one.containsAll(two) && two.containsAll(one);
  }//</editor-fold>

}
