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
 * RFC 6749 OAuth 2.0 4.1.2.1. Error Response
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

  // OAUTH codes
  /**
   * The request is missing a required parameter, includes an invalid parameter
   * value, includes a parameter more than once, or is otherwise malformed.
   */
  INVALID_REQUEST("invalid_request"),
  /**
   * The client is not authorized to request an authorization code using this
   * method.
   */
  UNAUTHORIZED_CLIENT("unauthorized_client"),
  /**
   * The resource owner or authorization server denied the request.
   */
  ACCESS_DENIED("access_denied"),
  /**
   * The authorization server does not support obtaining an authorization code
   * using this method.
   */
  UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type"),
  /**
   * The requested scope is invalid, unknown, or malformed.
   */
  INVALID_SCOPE("invalid_scope"),
  /**
   * The authorization server encountered an unexpected condition that prevented
   * it from fulfilling the request. (This error code is needed because a 500
   * Internal Server Error HTTP status code cannot be returned to the client via
   * an HTTP redirect.)
   */
  SERVER_ERROR("server_error"),
  /**
   * The authorization server is currently unable to handle the request due to a
   * temporary overloading or maintenance of the server. (This error code is
   * needed because a 503 Service Unavailable HTTP status code cannot be
   * returned to the client via an HTTP redirect.)
   */
  TEMPORARILY_UNAVAILABLE("temporarily_unavailable");

  private final String paramName;

  private ErrorResponseType(String paramName) {
    this.paramName = paramName;
  }

  /**
   * Gets error parameter.
   *
   * @return error parameter
   */
  public String toText() {
    return paramName;
  }

  /**
   * Return the corresponding enumeration from a string parameter.
   *
   * @param param The parameter to be match.
   * @return The <code>enumeration</code> if found, otherwise <code>null</code>.
   */
  public static ErrorResponseType fromText(String param) {
    return ErrorResponseType.valueOf(param.toUpperCase());
  }

  /**
   * Returns a string representation of the object. In this case, the lower case
   * code of the error.
   *
   * @return the OAuth specification name (e.g. "server_error")
   */
  @Override
  public String toString() {
    return paramName;
  }

}
