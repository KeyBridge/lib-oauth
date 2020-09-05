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
package org.ietf.oauth.message;

import java.time.Duration;
import java.util.UUID;
import javax.json.bind.annotation.JsonbProperty;

/**
 * OAuth 2.0 Message Authentication Code (MAC) Tokens
 * <p>
 * This specification describes how to use MAC Tokens in HTTP requests to access
 * OAuth 2.0 protected resources. An OAuth client willing to access a protected
 * resource needs to demonstrate possession of a cryptographic key by using it
 * with a keyed message digest function to the request.
 * <p>
 * Message authentication codes (MACs) are hash functions that take two distinct
 * inputs, a **message** and a **secret key**, and produce a fixed-size output.
 * The design goal is that it is practically infeasible to produce the same
 * output without knowledge of the key. The terms {@code keyed message digest}
 * and {@code MAC} are used interchangeably.
 * <p>
 * 4.1. Session Key Transport to Client
 * <p>
 * Authorization servers issue MAC Tokens based on requests from clients. The
 * request MUST include the audience parameter defined in
 * [I-D.tschofenig-oauth-audience], which indicates the resource server the
 * client wants to interact with. This specification assumes use of the
 * 'Authorization Code' grant.
 * <p>
 * For example:
 * <pre>
 * HTTP/1.1 200 OK
 * Content-Type: application/json
 * Cache-Control: no-store
 *
 * {
 *   "access_token":
 * "eyJhbGciOiJSU0ExXzUiLCJlbmMiOiJBMTI4Q0JDK0hTMjU2In0.
 * pwaFh7yJPivLjjPkzC-GeAyHuy7AinGcS51AZ7TXnwkC80Ow1aW47kcT_UV54ubo
 * nONbeArwOVuR7shveXnwPmucwrk_3OCcHrCbE1HR-Jfme2mF_WR3zUMcwqmU0RlH
 * kwx9txo_sKRasjlXc8RYP-evLCmT1XRXKjtY5l44Gnh0A84hGvVfMxMfCWXh38hi
 * 2h8JMjQHGQ3mivVui5lbf-zzb3qXXxNO1ZYoWgs5tP1-T54QYc9Bi9wodFPWNPKB
 * kY-BgewG-Vmc59JqFeprk1O08qhKQeOGCWc0WPC_n_LIpGWH6spRm7KGuYdgDMkQ
 * bd4uuB0uPPLx_euVCdrVrA.
 * AxY8DCtDaGlsbGljb3RoZQ.
 * 7MI2lRCaoyYx1HclVXkr8DhmDoikTmOp3IdEmm4qgBThFkqFqOs3ivXLJTku4M0f
 * laMAbGG_X6K8_B-0E-7ak-Olm_-_V03oBUUGTAc-F0A.
 * OwWNxnC-BMEie-GkFHzVWiNiaV3zUHf6fCOGTwbRckU",
 *   "token_type":"mac",
 *   "expires_in":3600,
 *   "refresh_token":"8xLOxBtZp8",
 *   "kid":"22BIjxU93h/IgwEb4zCRu5WF37s=",
 *   "mac_key":"adijq39jdlaska9asud",
 *   "mac_algorithm":"hmac-sha-256"
 * }
 * </pre>
 *
 * @see
 * <a href="https://trac.tools.ietf.org/id/draft-ietf-oauth-v2-http-mac-05.txt">draft-ietf-oauth-v2-http-mac-05.txt</a>
 * @author Key Bridge
 */
public class MacAccessTokenResponse extends AccessTokenResponse {

  /**
   * Request for Comments: 6750 The OAuth 2.0 Authorization Framework: Mac
   */
  private static final String TOKEN_TYPE_MAC = "mac";
  /**
   * The default expected MAC algorithm used to calculate the request MAC (the
   * access_token).
   */
  public static final String ALGORITHM_DEFAULT = "HMAC-SHA256";
  /**
   * 7 (days). The default access token duration.
   */
  private static final int DURATION_DEFAULT = 7;

  /**
   * The name of the key (i.e. the key id), which is an identifier generated by
   * the resource server.
   * <p>
   * It is RECOMMENDED that the authorization server generates this key id by
   * computing a hash over the access_token, for example using SHA-1, and to
   * encode it in a base64 format.
   */
  private String kid;
  /**
   * The session key generated by the authorization server. This is the
   * {@code shared secret} portion of the token response, while the
   * `access_token` field represents the {@code consumer_key} portion of the
   * token response.
   * <p>
   * Note that the lifetime of the session key is equal to the lifetime of the
   * access token.
   */
  @JsonbProperty("mac_key")
  private String macKey;
  /**
   * The MAC algorithm used to calculate the request MAC. The value MUST be one
   * of "hmac-sha-1", "hmac-sha-256", or a registered extension algorithm name
   * as described in Section 9.2. The authorization server is assumed to know
   * the set of algorithms supported by the client and the resource server. It
   * selects an algorithm that meets the security policies and is supported by
   * both nodes.
   */
  @JsonbProperty("mac_algorithm")
  private String macAlgorithm;

  /**
   * Default no-arg constructor. Sets the token type.
   */
  public MacAccessTokenResponse() {
    super(TOKEN_TYPE_MAC);
  }

  /**
   * Build a new Message Authentication Code (MAC) token.
   * <p>
   * The default duration is 7 days.
   *
   * @param kid           The name of the key (i.e. the key id), which is an
   *                      identifier generated by the resource server.
   * @param mac_algorithm The MAC algorithm used to calculate the request MAC
   *                      (the access_token). Expect "HMAC-SHA256".
   * @param access_token  The OAuth 2.0 access token (public key);
   * @param mac_key       The session **secret key** generated by the
   *                      authorization server.
   * @return a new Message Authentication Code (MAC) token.
   */
  public static MacAccessTokenResponse getInstance(String kid,
                                                   String mac_algorithm,
                                                   String access_token,
                                                   String mac_key) {
    MacAccessTokenResponse tr = new MacAccessTokenResponse();
    // set the token
    tr.setKid(kid);
    tr.setMacAlgorithm(mac_algorithm);
    tr.setAccessToken(access_token);
    tr.setMacKey(mac_key);
    // default duration is 7 days
    tr.setExpiresIn(Duration.ofDays(DURATION_DEFAULT));
    return tr;
  }

  /**
   * Build a new Message Authentication Code (MAC) token, using or inserting
   * default values for most parameters.
   * <p>
   * The duration is 7 days. A Key ID is generated. The Algorithm is
   * "HMAC-SHA256".
   *
   * @param access_token The OAuth 2.0 access token (public key);
   * @param mac_key      The session **secret key** generated by the
   *                     authorization server.
   * @return a new Message Authentication Code (MAC) token.
   */
  public static MacAccessTokenResponse getInstance(String access_token, String mac_key) {
    return getInstance(UUID.randomUUID().toString(),
                       ALGORITHM_DEFAULT,
                       access_token,
                       mac_key);
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  /**
   * Get the name of the key (i.e. the key id), which is an identifier generated
   * by the resource server.
   *
   * @return The name of the key
   */
  public String getKid() {
    return kid;
  }

  /**
   * Set the name of the key (i.e. the key id), which is an identifier generated
   * by the resource server.
   *
   * @param kid The name of the key
   */
  public void setKid(String kid) {
    this.kid = kid;
  }

  /**
   * Get the session key generated by the authorization server.
   *
   * @return The session key
   */
  public String getMacKey() {
    return macKey;
  }

  /**
   * Set the session key generated by the authorization server.
   *
   * @param macKey The session key
   */
  public void setMacKey(String macKey) {
    this.macKey = macKey;
  }

  /**
   * Get The MAC algorithm used to calculate the request MAC. The value MUST be
   * one of "hmac-sha-1", "hmac-sha-256", or a registered extension algorithm
   * name as described in Section 9.2.
   *
   * @return The MAC algorithm used to calculate the request MAC.
   */
  public String getMacAlgorithm() {
    return macAlgorithm;
  }

  /**
   * Set the MAC algorithm used to calculate the request MAC.
   *
   * @param macAlgorithm the MAC algorithm
   */
  public void setMacAlgorithm(String macAlgorithm) {
    this.macAlgorithm = macAlgorithm;
  }//</editor-fold>

}
