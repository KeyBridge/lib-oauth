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
package org.ietf.oauth.type;

import javax.json.bind.annotation.JsonbProperty;

/**
 * RFC 7521 OAuth Assertion Framework
 * <p>
 * Enumerated values for the AccessTokenRequest `client_assertion_type` field.
 *
 * @see
 * <a href="https://www.iana.org/assignments/oauth-parameters/oauth-parameters.xhtml">OAuth
 * URI</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-14
 */
public enum ClientAssertionType {

  /**
   * [RFC7523] JWT Bearer Token Profile for OAuth 2.0 Client Authentication
   */
  @JsonbProperty("urn:ietf:params:oauth:client-assertion-type:jwt-bearer")
  jwt_bearer,
  /**
   * [RFC7522] SAML 2.0 Bearer Assertion Profile for OAuth 2.0 Client
   * Authentication
   */
  @JsonbProperty("urn:ietf:params:oauth:client-assertion-type:saml2-bearer")
  saml2_bearer;

}
