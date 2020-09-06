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
 * RFC 7591 OAuth 2.0 Dynamic Registration 2. Client Metadata
 * token_endpoint_auth_method
 * <p>
 * String indicator of the requested authentication method for the token
 * endpoint.
 *
 * @author Key Bridge
 * @since v2.1.0 created 2020-08-27
 * @see <a href="https://tools.ietf.org/html/rfc7591">OAuth 2.0 Dynamic Client
 * Registration Protocol</a>
 */
public enum TokenAuthenticationType {
  /**
   * The client is a public client as defined in OAuth 2.0, Section 2.1, and
   * does not have a client secret.
   */
  none,
  /**
   * The client uses the HTTP POST parameters as defined in OAuth 2.0, Section
   * 2.3.1.
   */
  client_secret_post,
  /**
   * The client uses HTTP Basic as defined in OAuth 2.0, Section 2.3.1.
   */
  client_secret_basic
}
