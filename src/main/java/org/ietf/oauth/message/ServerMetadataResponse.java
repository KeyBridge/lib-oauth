/*
 * Copyright 2020 Key Bridge.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); /**
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

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.TreeSet;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import org.ietf.oauth.adapter.JsonLocaleCollectionAdapter;
import org.ietf.oauth.type.GrantType;
import org.ietf.oauth.type.ResponseType;
import org.ietf.oauth.type.TokenAuthenticationType;

/**
 * Request for Comments: 8414 OAuth 2.0 Authorization Server Metadata
 * <p>
 * 3.2. Authorization Server Metadata Response. The response is a set of claims
 * about the authorization server's configuration, including all necessary
 * endpoints and public key location information. A successful response MUST use
 * the 200 OK HTTP status code and return a JSON object using the
 * "application/json" content type that contains a set of claims as its members
 * that are a subset of the metadata values defined in Section 2. Other claims
 * MAY also be returned.
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc8414.html">Authorization
 * Server Metadata</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-05
 */
public class ServerMetadataResponse {

  /**
   * REQUIRED. The authorization server's issuer identifier, which is a URL that
   * uses the "https" scheme and has no query or fragment components.
   * Authorization server metadata is published at a location that is
   * ".well-known" according to RFC 5785 [RFC5785] derived from this issuer
   * identifier, as described in Section 3. The issuer identifier is used to
   * prevent authorization server mix- up attacks, as described in "OAuth 2.0
   * Mix-Up Mitigation" [MIX-UP].
   */
  private String issuer;

  /**
   * URL of the authorization server's authorization endpoint [RFC6749]. This is
   * REQUIRED unless no grant types are supported that use the authorization
   * endpoint.
   */
  @JsonbProperty("authorization_endpoint")
  private URI authorizationEndpoint;
  /**
   * URL of the authorization server's token endpoint [RFC6749]. This is
   * REQUIRED unless only the implicit grant type is supported.
   */
  @JsonbProperty("token_endpoint")
  private URI tokenEndpoint;
  /**
   * OPTIONAL. URL of the authorization server's JWK Set [JWK] document. The
   * referenced document contains the signing key(s) the client uses to validate
   * signatures from the authorization server. This URL MUST use the "https"
   * scheme. The JWK Set MAY also contain the server's encryption key or keys,
   * which are used by clients to encrypt requests to the server. When both
   * signing and encryption keys are made available, a "use" (public key use)
   * parameter value is REQUIRED for all keys in the referenced JWK Set to
   * indicate each key's intended usage.
   */
  @JsonbProperty("jwks_uri")
  private URI jwksUri;
  /**
   * RECOMMENDED. JSON array containing a list of the OAuth 2.0 [RFC6749]
   * "scope" values that this authorization server supports. Servers MAY choose
   * not to advertise some supported scope values even when this parameter is
   * used.
   */
  @JsonbProperty("scopes_supported")
  private Collection<String> scopesSupported;
  /**
   * OPTIONAL. URL of the authorization server's OAuth 2.0 Dynamic Client
   * Registration endpoint [RFC7591].
   */
  @JsonbProperty("registration_endpoint")
  private URI registrationEndpoint;
  /**
   * REQUIRED. JSON array containing a list of the OAuth 2.0 "response_type"
   * values that this authorization server supports. The array values used are
   * the same as those used with the "response_types" parameter defined by
   * "OAuth 2.0 Dynamic Client Registration Protocol" [RFC7591].
   */
  @JsonbProperty("response_types_supported")
  private Collection<ResponseType> responseTypesSupported;
  /**
   * OPTIONAL. JSON array containing a list of the OAuth 2.0 "response_mode"
   * values that this authorization server supports, as specified in "OAuth 2.0
   * Multiple Response Type Encoding Practices" [OAuth.Responses]. If omitted,
   * the default is "["query", "fragment"]". The response mode value "form_post"
   * is also defined in "OAuth 2.0 Form Post Response Mode" [OAuth.Post].
   */
  @JsonbProperty("response_modes_supported")
  private Collection<String> responseModesSupported;
  /**
   * OPTIONAL. JSON array containing a list of the OAuth 2.0 grant type values
   * that this authorization server supports. The array values used are the same
   * as those used with the "grant_types" parameter defined by "OAuth 2.0
   * Dynamic Client Registration Protocol" [RFC7591]. If omitted, the default
   * value is "["authorization_code", "implicit"]".
   */
  @JsonbProperty("grant_types_supported")
  private Collection<GrantType> grantTypesSupported;
  /**
   * OPTIONAL. JSON array containing a list of client authentication methods
   * supported by this token endpoint. Client authentication method values are
   * used in the "token_endpoint_auth_method" parameter defined in Section 2 of
   * [RFC7591]. If omitted, the default is "client_secret_basic" -- the HTTP
   * Basic Authentication Scheme specified in Section 2.3.1 of OAuth 2.0
   * [RFC6749].
   */
  @JsonbProperty("token_endpoint_auth_methods_supported")
  private Collection<TokenAuthenticationType> tokenEndpointAuthMethodsSupported;
  /**
   * OPTIONAL. JSON array containing a list of the JWS signing algorithms ("alg"
   * values) supported by the token endpoint for the signature on the JWT [JWT]
   * used to authenticate the client at the token endpoint for the
   * "private_key_jwt" and "client_secret_jwt" authentication methods. This
   * metadata entry MUST be present if either of these authentication methods
   * are specified in the "token_endpoint_auth_methods_supported" entry. No
   * default algorithms are implied if this entry is omitted. Servers SHOULD
   * support "RS256". The value "none" MUST NOT be used.
   */
  @JsonbProperty("token_endpoint_auth_signing_alg_values_supported")
  private Collection<String> tokenEndpointAuthSigningAlgValuesSupported;
  /**
   * OPTIONAL. URL of a page containing human-readable information that
   * developers might want or need to know when using the authorization server.
   * In particular, if the authorization server does not support Dynamic Client
   * Registration, then information on how to register clients needs to be
   * provided in this documentation.
   */
  @JsonbProperty("service_documentation")
  private URI serviceDocumentation;
  /**
   * OPTIONAL. Languages and scripts supported for the user interface,
   * represented as a JSON array of language tag values from BCP 47 [RFC5646].
   * If omitted, the set of supported languages and scripts is unspecified.
   */
  @JsonbProperty("ui_locales_supported")
  @JsonbTypeAdapter(JsonLocaleCollectionAdapter.class)
  private Collection<Locale> uiLocalesSupported;
  /**
   * OPTIONAL. URL that the authorization server provides to the person
   * registering the client to read about the authorization server's
   * requirements on how the client can use the data provided by the
   * authorization server. The registration process SHOULD display this URL to
   * the person registering the client if it is given. As described in Section
   * 5, despite the identifier "op_policy_uri" appearing to be OpenID-specific,
   * its usage in this specification is actually referring to a general OAuth
   * 2.0 feature that is not specific to OpenID Connect.
   */
  @JsonbProperty("op_policy_uri")
  private URI opPolicyUri;
  /**
   * OPTIONAL. URL that the authorization server provides to the person
   * registering the client to read about the authorization server's terms of
   * service. The registration process SHOULD display this URL to the person
   * registering the client if it is given. As described in Section 5, despite
   * the identifier "op_tos_uri", appearing to be OpenID-specific, its usage in
   * this specification is actually referring to a general OAuth 2.0 feature
   * that is not specific to OpenID Connect.
   */
  @JsonbProperty("op_tos_uri")
  private URI opTosUri;
  /**
   * OPTIONAL. URL of the authorization server's OAuth 2.0 revocation endpoint
   * [RFC7009].
   */
  @JsonbProperty("revocation_endpoint")
  private URI revocationEndpoint;
  /**
   * OPTIONAL. JSON array containing a list of client authentication methods
   * supported by this revocation endpoint. The valid client authentication
   * method values are those registered in the IANA "OAuth Token Endpoint
   * Authentication Methods" registry [IANA.OAuth.Parameters]. If omitted, the
   * default is "client_secret_basic" -- the HTTP Basic Authentication Scheme
   * specified in Section 2.3.1 of OAuth 2.0 [RFC6749].
   */
  @JsonbProperty("revocation_endpoint_auth_methods_supported")
  private Collection<TokenAuthenticationType> revocationEndpointAuthMethodsSupported;
  /**
   * OPTIONAL. JSON array containing a list of the JWS signing algorithms ("alg"
   * values) supported by the revocation endpoint for the signature on the JWT
   * [JWT] used to authenticate the client at the revocation endpoint for the
   * "private_key_jwt" and "client_secret_jwt" authentication methods. This
   * metadata entry MUST be present if either of these authentication methods
   * are specified in the "revocation_endpoint_auth_methods_supported" entry. No
   * default algorithms are implied if this entry is omitted. The value "none"
   * MUST NOT be used.
   */
  @JsonbProperty("revocation_endpoint_auth_signing_alg_values_supported")
  private Collection<String> revocationEndpointAuthSigningAlgValuesSupported;
  /**
   * OPTIONAL. URL of the authorization server's OAuth 2.0 introspection
   * endpoint [RFC7662].
   */
  @JsonbProperty("introspection_endpoint")
  private URI introspectionEndpoint;
  /**
   * OPTIONAL. JSON array containing a list of client authentication methods
   * supported by this introspection endpoint. The valid client authentication
   * method values are those registered in the IANA "OAuth Token Endpoint
   * Authentication Methods" registry [IANA.OAuth.Parameters] or those
   * registered in the IANA "OAuth Access Token Types" registry
   * [IANA.OAuth.Parameters]. (These values are and will remain distinct, due to
   * Section 7.2.) If omitted, the set of supported authentication methods MUST
   * be determined by other means.
   */
  @JsonbProperty("introspection_endpoint_auth_methods_supported")
  private Collection<TokenAuthenticationType> introspectionEndpointAuthMethodsSupported;
  /**
   * OPTIONAL. JSON array containing a list of the JWS signing algorithms ("alg"
   * values) supported by the introspection endpoint for the signature on the
   * JWT [JWT] used to authenticate the client at the introspection endpoint for
   * the "private_key_jwt" and "client_secret_jwt" authentication methods. This
   * metadata entry MUST be present if either of these authentication methods
   * are specified in the "introspection_endpoint_auth_methods_supported" entry.
   * No default algorithms are implied if this entry is omitted. The value
   * "none" MUST NOT be used.
   */
  @JsonbProperty("introspection_endpoint_auth_signing_alg_values_supported")
  private Collection<String> introspectionEndpointAuthSigningAlgValuesSupported;
  /**
   * OPTIONAL. JSON array containing a list of Proof Key for Code Exchange
   * (PKCE) [RFC7636] code challenge methods supported by this authorization
   * server. Code challenge method values are used in the
   * "code_challenge_method" parameter defined in Section 4.3 of [RFC7636]. The
   * valid code challenge method values are those registered in the IANA "PKCE
   * Code Challenge Methods" registry [IANA.OAuth.Parameters]. If omitted, the
   * authorization server does not support PKCE.
   */
  @JsonbProperty("code_challenge_methods_supported")
  private Collection<String> codeChallengeMethodsSupported;

  /**
   * 2.1. Signed Authorization Server Metadata
   * <p>
   * In addition to JSON elements, metadata values MAY also be provided as a
   * "signed_metadata" value, which is a JSON Web Token (JWT) [JWT] that asserts
   * metadata values about the authorization server as a bundle. The signed
   * metadata MUST be digitally signed or MACed using JSON Web Signature (JWS)
   * [JWS] and MUST contain an "iss" (issuer) claim denoting the party attesting
   * to the claims in the signed metadata.
   * <p>
   * A JWT containing metadata values about the authorization server as claims.
   * This is a (compact form) string value consisting of the entire signed JWT.
   * A "signed_metadata" metadata value SHOULD NOT appear as a claim in the JWT.
   */
  @JsonbProperty("signed_metadata")
  private String signedMetadata;

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public URI getAuthorizationEndpoint() {
    return authorizationEndpoint;
  }

  public void setAuthorizationEndpoint(URI authorizationEndpoint) {
    this.authorizationEndpoint = authorizationEndpoint;
  }

  public URI getTokenEndpoint() {
    return tokenEndpoint;
  }

  public void setTokenEndpoint(URI tokenEndpoint) {
    this.tokenEndpoint = tokenEndpoint;
  }

  public URI getJwksUri() {
    return jwksUri;
  }

  public void setJwksUri(URI jwksUri) {
    this.jwksUri = jwksUri;
  }

  public Collection<String> getScopesSupported() {
    if (scopesSupported == null) {
      scopesSupported = new TreeSet<>();
    }
    return scopesSupported;
  }

  public void setScopesSupported(Collection<String> scopesSupported) {
    this.scopesSupported = scopesSupported;
  }

  public URI getRegistrationEndpoint() {
    return registrationEndpoint;
  }

  public void setRegistrationEndpoint(URI registrationEndpoint) {
    this.registrationEndpoint = registrationEndpoint;
  }

  public Collection<ResponseType> getResponseTypesSupported() {
    if (responseTypesSupported == null) {
      responseTypesSupported = new TreeSet<>();
    }
    return responseTypesSupported;
  }

  public void setResponseTypesSupported(Collection<ResponseType> responseTypesSupported) {
    this.responseTypesSupported = responseTypesSupported;
  }

  public Collection<String> getResponseModesSupported() {
    if (responseModesSupported == null) {
      responseModesSupported = new TreeSet<>();
    }
    return responseModesSupported;
  }

  public void setResponseModesSupported(Collection<String> responseModesSupported) {
    this.responseModesSupported = responseModesSupported;
  }

  public Collection<GrantType> getGrantTypesSupported() {
    if (grantTypesSupported == null) {
      grantTypesSupported = new TreeSet<>();
    }
    return grantTypesSupported;
  }

  public void setGrantTypesSupported(Collection<GrantType> grantTypesSupported) {
    this.grantTypesSupported = grantTypesSupported;
  }

  public Collection<TokenAuthenticationType> getTokenEndpointAuthMethodsSupported() {
    if (tokenEndpointAuthMethodsSupported == null) {
      tokenEndpointAuthMethodsSupported = new TreeSet<>();
    }
    return tokenEndpointAuthMethodsSupported;
  }

  public void setTokenEndpointAuthMethodsSupported(Collection<TokenAuthenticationType> tokenEndpointAuthMethodsSupported) {
    this.tokenEndpointAuthMethodsSupported = tokenEndpointAuthMethodsSupported;
  }

  public Collection<String> getTokenEndpointAuthSigningAlgValuesSupported() {
    if (tokenEndpointAuthSigningAlgValuesSupported == null) {
      tokenEndpointAuthSigningAlgValuesSupported = new TreeSet<>();
    }
    return tokenEndpointAuthSigningAlgValuesSupported;
  }

  public void setTokenEndpointAuthSigningAlgValuesSupported(Collection<String> tokenEndpointAuthSigningAlgValuesSupported) {
    this.tokenEndpointAuthSigningAlgValuesSupported = tokenEndpointAuthSigningAlgValuesSupported;
  }

  public URI getServiceDocumentation() {
    return serviceDocumentation;
  }

  public void setServiceDocumentation(URI serviceDocumentation) {
    this.serviceDocumentation = serviceDocumentation;
  }

  public Collection<Locale> getUiLocalesSupported() {
    if (uiLocalesSupported == null) {
      uiLocalesSupported = new HashSet<>();
    }
    return uiLocalesSupported;
  }

  public void setUiLocalesSupported(Collection<Locale> uiLocalesSupported) {
    this.uiLocalesSupported = uiLocalesSupported;
  }

  public URI getOpPolicyUri() {
    return opPolicyUri;
  }

  public void setOpPolicyUri(URI opPolicyUri) {
    this.opPolicyUri = opPolicyUri;
  }

  public URI getOpTosUri() {
    return opTosUri;
  }

  public void setOpTosUri(URI opTosUri) {
    this.opTosUri = opTosUri;
  }

  public URI getRevocationEndpoint() {
    return revocationEndpoint;
  }

  public void setRevocationEndpoint(URI revocationEndpoint) {
    this.revocationEndpoint = revocationEndpoint;
  }

  public Collection<TokenAuthenticationType> getRevocationEndpointAuthMethodsSupported() {
    if (revocationEndpointAuthMethodsSupported == null) {
      revocationEndpointAuthMethodsSupported = new TreeSet<>();
    }
    return revocationEndpointAuthMethodsSupported;
  }

  public void setRevocationEndpointAuthMethodsSupported(Collection<TokenAuthenticationType> revocationEndpointAuthMethodsSupported) {
    this.revocationEndpointAuthMethodsSupported = revocationEndpointAuthMethodsSupported;
  }

  public Collection<String> getRevocationEndpointAuthSigningAlgValuesSupported() {
    if (revocationEndpointAuthSigningAlgValuesSupported == null) {
      revocationEndpointAuthSigningAlgValuesSupported = new TreeSet<>();
    }
    return revocationEndpointAuthSigningAlgValuesSupported;
  }

  public void setRevocationEndpointAuthSigningAlgValuesSupported(Collection<String> revocationEndpointAuthSigningAlgValuesSupported) {
    this.revocationEndpointAuthSigningAlgValuesSupported = revocationEndpointAuthSigningAlgValuesSupported;
  }

  public URI getIntrospectionEndpoint() {
    return introspectionEndpoint;
  }

  public void setIntrospectionEndpoint(URI introspectionEndpoint) {
    this.introspectionEndpoint = introspectionEndpoint;
  }

  public Collection<TokenAuthenticationType> getIntrospectionEndpointAuthMethodsSupported() {
    if (introspectionEndpointAuthMethodsSupported == null) {
      introspectionEndpointAuthMethodsSupported = new TreeSet<>();
    }
    return introspectionEndpointAuthMethodsSupported;
  }

  public void setIntrospectionEndpointAuthMethodsSupported(Collection<TokenAuthenticationType> introspectionEndpointAuthMethodsSupported) {
    this.introspectionEndpointAuthMethodsSupported = introspectionEndpointAuthMethodsSupported;
  }

  public Collection<String> getIntrospectionEndpointAuthSigningAlgValuesSupported() {
    if (introspectionEndpointAuthSigningAlgValuesSupported == null) {
      introspectionEndpointAuthSigningAlgValuesSupported = new TreeSet<>();
    }
    return introspectionEndpointAuthSigningAlgValuesSupported;
  }

  public void setIntrospectionEndpointAuthSigningAlgValuesSupported(Collection<String> introspectionEndpointAuthSigningAlgValuesSupported) {
    this.introspectionEndpointAuthSigningAlgValuesSupported = introspectionEndpointAuthSigningAlgValuesSupported;
  }

  public Collection<String> getCodeChallengeMethodsSupported() {
    if (codeChallengeMethodsSupported == null) {
      codeChallengeMethodsSupported = new TreeSet<>();
    }
    return codeChallengeMethodsSupported;
  }

  public void setCodeChallengeMethodsSupported(Collection<String> codeChallengeMethodsSupported) {
    this.codeChallengeMethodsSupported = codeChallengeMethodsSupported;
  }

  public String getSignedMetadata() {
    return signedMetadata;
  }

  public void setSignedMetadata(String signedMetadata) {
    this.signedMetadata = signedMetadata;
  }//</editor-fold>

  /**
   * Prepare this object for serialization. Per RFC 8414 Section 3.2. Claims
   * that return multiple values are represented as JSON arrays. Claims with
   * zero elements MUST be omitted from the response.
   */
  public void prepareForSerialization() {
    /**
     * Scan the object declared fields for Collections. If any collection
     * instance is not null AND empty then set the field value to null.
     */
    for (Field field : this.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      try {
        if (field.getType().isAssignableFrom(Collection.class)) {
          if (field.get(this) != null) {
            if (((Collection) field.get(this)).isEmpty()) {
              field.set(this, null);
            }
          }
        }
      } catch (IllegalArgumentException | IllegalAccessException exception) {
        System.err.println("Error clearing empty collection for field " + field.getName());
      }
    }
  }

}
