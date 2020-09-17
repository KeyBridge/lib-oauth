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

/**
 * OAuth Token Type Hints
 *
 * @see
 * <a href="https://www.iana.org/assignments/oauth-parameters/oauth-parameters.xhtml#token-type-hint">OAuth
 * Parameters</a>
 * @author Key Bridge
 */
public enum TokenTypeHint {

  /**
   * RFC7009 access_token: An access token as defined in [RFC6749], Section 1.4
   */
  access_token,
  /**
   * RFC7009 refresh_token: A refresh token as defined in [RFC6749], Section 1.5
   */
  refresh_token,
  /**
   * [Kantara_UMA_WG] [UMA 2.0 Grant for OAuth 2.0, Section 3.7]
   * <p>
   * "persisted claims token" (PCT) - A correlation handle issued by an
   * authorization server that represents a set of claims collected during one
   * authorization process, available for a client to use in attempting to
   * optimize a future authorization process.
   * <p>
   * If the authorization server presents a token revocation endpoint as defined
   * in [RFC7009], the client MAY use the endpoint to request revocation of an
   * RPT (access token), refresh token, or PCT previously issued to it on behalf
   * of a requesting party. This specification defines the following token type
   * hint value:
   *
   * @see
   * <a href="https://docs.kantarainitiative.org/uma/wg/rec-oauth-uma-grant-2.0.html#token-revocation">Token
   * Revocation</a>
   */
  pct;

}
