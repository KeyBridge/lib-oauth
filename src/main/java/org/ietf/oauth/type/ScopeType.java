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
package org.ietf.oauth.type;

/**
 * Enumeration of supported / expected scope values.
 * <p>
 * 3.3. Access Token Scope
 * <p>
 * The authorization and token endpoints allow the client to specify the scope
 * of the access request using the "scope" request parameter. In turn, the
 * authorization server uses the "scope" response parameter to inform the client
 * of the scope of the access token issued.
 * <p>
 * The value of the scope parameter is expressed as a list of space- delimited,
 * case-sensitive strings. The strings are defined by the authorization server.
 * If the value contains multiple space-delimited strings, their order does not
 * matter, and each string adds an additional access range to the requested
 * scope.
 * <pre>
 * scope       = scope-token *( SP scope-token )
 * scope-token = 1*( %x21 / %x23-5B / %x5D-7E )
 * </pre> The authorization server MAY fully or partially ignore the scope
 * requested by the client, based on the authorization server policy or the
 * resource owner's instructions. If the issued access token scope is different
 * from the one requested by the client, the authorization server MUST include
 * the "scope" response parameter to inform the client of the actual scope
 * granted.
 * <p>
 * If the client omits the scope parameter when requesting authorization, the
 * authorization server MUST either process the request using a pre-defined
 * default value or fail the request indicating an invalid scope. The
 * authorization server SHOULD document its scope requirements and default value
 * (if defined).
 *
 * @since v0.0.1
 * @author Jesse Caulfield 10/06/17
 */
public enum ScopeType {

  /**
   * The mandatory scope of all OAUTH authorization messages.
   * <p>
   * NOT USED FOR OPENID TRANSACTIONS.
   */
  oauth;

}
