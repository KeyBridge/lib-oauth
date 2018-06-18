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

import javax.xml.bind.annotation.*;

/**
 * Internet-Draft OAuth 2.0 MAC Tokens
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
 * @author Key Bridge
 */
@XmlRootElement(name = "MacAccessTokenResponse")
@XmlType(name = "MacAccessTokenResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class MacAccessTokenResponse extends AccessTokenResponse {

  @XmlTransient
  private static final String TOKEN_TYPE_MAC = "mac";

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
   * The session key generated by the authorization server. This is the shared
   * **secret key** portion of the token response.
   * <p>
   * Note that the lifetime of the session key is equal to the lifetime of the
   * access token.
   */
  private String mac_key;
  /**
   * The MAC algorithm used to calculate the request MAC. The value MUST be one
   * of "hmac-sha-1", "hmac-sha-256", or a registered extension algorithm name
   * as described in Section 9.2. The authorization server is assumed to know
   * the set of algorithms supported by the client and the resource server. It
   * selects an algorithm that meets the security policies and is supported by
   * both nodes.
   */
  private String mac_algorithm;

  public MacAccessTokenResponse() {
    super(TOKEN_TYPE_MAC);
  }

  /**
   * Build a new Message Authentication Code (MAC) token.
   * <p>
   * The default duration is 7 days.
   *
   * @param access_token  The OAuth 2.0 access token;
   * @param kid           The name of the key (i.e. the key id), which is an
   *                      identifier generated by the resource server.
   * @param mac_key       The session **secret key** generated by the
   *                      authorization server.
   * @param mac_algorithm The MAC algorithm used to calculate the request MAC
   *                      (the access_token).
   * @return a new Message Authentication Code (MAC) token.
   */
  public static MacAccessTokenResponse getInstance(String access_token,
                                                   String kid,
                                                   String mac_key,
                                                   String mac_algorithm) {
    MacAccessTokenResponse mac = new MacAccessTokenResponse();
    mac.setAccess_token(access_token);
    mac.setKid(kid);
    mac.setMac_key(mac_key);
    mac.setMac_algorithm(mac_algorithm);
    return mac;
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
  public String getMac_key() {
    return mac_key;
  }

  /**
   * Set the session key generated by the authorization server.
   *
   * @param mac_key The session key
   */
  public void setMac_key(String mac_key) {
    this.mac_key = mac_key;
  }

  /**
   * Get The MAC algorithm used to calculate the request MAC. The value MUST be
   * one of "hmac-sha-1", "hmac-sha-256", or a registered extension algorithm
   * name as described in Section 9.2.
   *
   * @return The MAC algorithm used to calculate the request MAC.
   */
  public String getMac_algorithm() {
    return mac_algorithm;
  }

  /**
   * Set the MAC algorithm used to calculate the request MAC.
   *
   * @param mac_algorithm the MAC algorithm
   */
  public void setMac_algorithm(String mac_algorithm) {
    this.mac_algorithm = mac_algorithm;
  }//</editor-fold>

}
