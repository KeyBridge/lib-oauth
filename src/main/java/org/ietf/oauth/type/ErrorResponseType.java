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
 * RFC 6749 OAuth 2.0 5.2. Error Response and OpenId 4.1.2.1.
 * <p>
 * If the request fails due to a missing, invalid, or mismatching redirection
 * URI, or if the client identifier is missing or invalid, the authorization
 * server SHOULD inform the resource owner of the error and MUST NOT
 * automatically redirect the user-agent to the invalid redirection URI.
 *
 * @since v0.0.1
 * @author Jesse Caulfield 10/06/17
 */
public enum ErrorResponseType {

  /**
   * The request is missing a required parameter, includes an unsupported
   * parameter value (other than grant type), repeats a parameter, includes
   * multiple credentials, utilizes more than one mechanism for authenticating
   * the client, or is otherwise malformed.
   */
  invalid_request,
  /**
   * Client authentication failed (e.g., unknown client, no client
   * authentication included, or unsupported authentication method). The
   * authorization server MAY return an HTTP 401 (Unauthorized) status code to
   * indicate which HTTP authentication schemes are supported. If the client
   * attempted to authenticate via the "Authorization" request header field, the
   * authorization server MUST respond with an HTTP 401 (Unauthorized) status
   * code and include the "WWW-Authenticate" response header field matching the
   * authentication scheme used by the client.
   */
  invalid_client,
  /**
   * The authenticated client is not authorized to use this authorization grant
   * type.
   */
  unauthorized_client,
  /**
   * The resource owner or authorization server denied the request.
   */
  access_denied,
  /**
   * The authorization grant type is not supported by the authorization server.
   */
  unsupported_grant_type,
  /**
   * The authorization server does not support obtaining an authorization code
   * using this method.
   */
  unsupported_response_type,
  /**
   * The provided authorization grant (e.g., authorization code, resource owner
   * credentials) or refresh token is invalid, expired, revoked, does not match
   * the redirection URI used in the authorization request, or was issued to
   * another client.
   */
  invalid_grant,
  /**
   * The requested scope is invalid, unknown, or malformed.
   */
  invalid_scope,
  /**
   * The authorization server encountered an unexpected condition that prevented
   * it from fulfilling the request. (This error code is needed because a 500
   * Internal Server Error HTTP status code cannot be returned to the client via
   * an HTTP redirect.)
   */
  server_error,
  /**
   * The authorization server is currently unable to handle the request due to a
   * temporary overloading or maintenance of the server. (This error code is
   * needed because a 503 Service Unavailable HTTP status code cannot be
   * returned to the client via an HTTP redirect.)
   */
  temporarily_unavailable;

}
