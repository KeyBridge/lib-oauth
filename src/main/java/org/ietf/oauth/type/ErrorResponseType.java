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
 * <p>
 * OpenID Connect Core 3.1.2.6. Authentication Error Response
 * <p>
 * An Authentication Error Response is an OAuth 2.0 Authorization Error Response
 * message returned from the OP's Authorization Endpoint in response to the
 * Authorization Request message sent by the RP.
 * <p>
 * If the End-User denies the request or the End-User authentication fails, the
 * OP (Authorization Server) informs the RP (Client) by using the Error Response
 * parameters defined in Section 4.1.2.1 of OAuth 2.0 [RFC6749]. (HTTP errors
 * unrelated to RFC 6749 are returned to the User Agent using the appropriate
 * HTTP status code.)
 * <p>
 * Unless the Redirection URI is invalid, the Authorization Server returns the
 * Client to the Redirection URI specified in the Authorization Request with the
 * appropriate error and state parameters. Other parameters SHOULD NOT be
 * returned.
 * <p>
 * In addition to the error codes defined in Section 4.1.2.1 of OAuth 2.0, this
 * specification also defines the following error codes.
 *
 * @see
 * <a href="https://www.iana.org/assignments/oauth-parameters/oauth-parameters.xhtml">OAuth
 * Extensions Error Registry</a>
 * @author Jesse Caulfield 10/06/17
 * @since v0.0.1
 * @since v3.1.0 updated 2020-09-12 for other supported RFCs in this library
 */
public enum ErrorResponseType {

  // RFC 6749  OAuth 2.0   4.1.2.1.  Error Response
  /**
   * RFC 6749 The request is missing a required parameter, includes an invalid
   * parameter value, includes a parameter more than once, or is otherwise
   * malformed.
   * <p>
   * RFC 6750 The request is missing a required parameter, includes an
   * unsupported parameter or parameter value, repeats the same parameter, uses
   * more than one method for including an access token, or is otherwise
   * malformed. The resource server SHOULD respond with the HTTP 400 (Bad
   * Request) status code.
   */
  invalid_request,
  /**
   * RFC 6749 The client is not authorized to request an authorization code
   * using this method.
   */
  unauthorized_client,
  /**
   * RFC 6749 The resource owner or authorization server denied the request.
   * <p>
   * RFC 8628 The authorization request was denied.
   */
  access_denied,
  /**
   * RFC 6749 The authorization server does not support obtaining an
   * authorization code using this method.
   */
  unsupported_response_type,
  /**
   * RFC 6749 The requested scope is invalid, unknown, or malformed.
   */
  invalid_scope,
  /**
   * RFC 6749 The authorization server encountered an unexpected condition that
   * prevented it from fulfilling the request. (This error code is needed
   * because a 500 Internal Server Error HTTP status code cannot be returned to
   * the client via an HTTP redirect.)
   */
  server_error,
  /**
   * RFC 6749 The authorization server is currently unable to handle the request
   * due to a temporary overloading or maintenance of the server. (This error
   * code is needed because a 503 Service Unavailable HTTP status code cannot be
   * returned to the client via an HTTP redirect.)
   */
  temporarily_unavailable,

  // RFC 6750 OAuth 2.0 Bearer Token Usage
  /**
   * RFC 6750 The access token provided is expired, revoked, malformed, or
   * invalid for other reasons. The resource SHOULD respond with the HTTP 401
   * (Unauthorized) status code. The client MAY request a new access token and
   * retry the protected resource request.
   */
  invalid_token,
  /**
   * RFC 6750 The request requires higher privileges than provided by the access
   * token. The resource server SHOULD respond with the HTTP 403 (Forbidden)
   * status code and MAY include the "scope" attribute with the scope necessary
   * to access the protected resource.
   */
  insufficient_scope,

  // RFC 7009 Token Revocation
  /**
   * RFC 7009 The authorization server does not support the revocation of the
   * presented token type. That is, the client tried to revoke an access token
   * on a server not supporting this feature.
   */
  unsupported_token_type,

  // RFC 7591 OAuth 2.0 Dynamic Registration 3.2.2.  Client Registration Error Response
  /**
   * RFC 7591 The value of one or more redirection URIs is invalid.
   */
  invalid_redirect_uri,
  /**
   * RFC 7591 The value of one of the client metadata fields is invalid and the
   * server has rejected this request. Note that an authorization server MAY
   * choose to substitute a valid value for any requested parameter of a
   * client's metadata.
   */
  invalid_client_metadata,
  /**
   * RFC 7591 The software statement presented is invalid.
   */
  invalid_software_statement,
  /**
   * RFC 7591 The software statement presented is not approved for use by this
   * authorization server.
   */
  unapproved_software_statement,

  // OpenID Connect Core
  /**
   * OpenID The Authorization Server requires End-User interaction of some form
   * to proceed. This error MAY be returned when the prompt parameter value in
   * the Authentication Request is none, but the Authentication Request cannot
   * be completed without displaying a user interface for End-User interaction.
   */
  interaction_required,
  /**
   * OpenID The Authorization Server requires End-User authentication. This
   * error MAY be returned when the prompt parameter value in the Authentication
   * Request is none, but the Authentication Request cannot be completed without
   * displaying a user interface for End-User authentication.
   */
  login_required,
  /**
   * OpenID The End-User is REQUIRED to select a session at the Authorization
   * Server. The End-User MAY be authenticated at the Authorization Server with
   * different associated accounts, but the End-User did not select a session.
   * This error MAY be returned when the prompt parameter value in the
   * Authentication Request is none, but the Authentication Request cannot be
   * completed without displaying a user interface to prompt for a session to
   * use.
   */
  account_selection_required,
  /**
   * OpenID The Authorization Server requires End-User consent. This error MAY
   * be returned when the prompt parameter value in the Authentication Request
   * is none, but the Authentication Request cannot be completed without
   * displaying a user interface for End-User consent.
   */
  consent_required,
  /**
   * OpenID The request_uri in the Authorization Request returns an error or
   * contains invalid data.
   */
  invalid_request_uri,
  /**
   * OpenID The request parameter contains an invalid Request Object.
   */
  invalid_request_object,
  /**
   * OpenID The OP does not support use of the request parameter defined in
   * Section 6.
   */
  request_not_supported,
  /**
   * OpenID The OP does not support use of the request_uri parameter defined in
   * Section 6.
   */
  request_uri_not_supported,
  /**
   * OpenID The OP does not support use of the registration parameter defined in
   * Section 7.2.1.
   */
  registration_not_supported,

  // RFC 8693 OAuth 2.0 Token Exchange
  /**
   * RFC 8693 If the authorization server is unwilling or unable to issue a
   * token for any target service indicated by the "resource" or "audience"
   * parameters of a RFC8693 Token Exchange Request, the "invalid_target" error
   * code SHOULD be used in the error response.
   */
  invalid_target,

  // RFC 8628 OAuth 2.0 Device Grant
  /**
   * RFC 8628 The authorization request is still pending as the end user hasn't
   * yet completed the user-interaction steps (Section 3.3). The client SHOULD
   * repeat the access token request to the token endpoint (a process known as
   * polling). Before each new request, the client MUST wait at least the number
   * of seconds specified by the "interval" parameter of the device
   * authorization response (see Section 3.2), or 5 seconds if none was
   * provided, and respect any increase in the polling interval required by the
   * "slow_down" error.
   */
  authorization_pending,
  /**
   * RFC 8628 A variant of "authorization_pending", the authorization request is
   * still pending and polling should continue, but the interval MUST be
   * increased by 5 seconds for this and all subsequent requests.
   */
  slow_down,
  /**
   * RFC 8628 The "device_code" has expired, and the device authorization
   * session has concluded. The client MAY commence a new device authorization
   * request but SHOULD wait for user interaction before restarting to avoid
   * unnecessary polling.
   */
  expired_token;

}
