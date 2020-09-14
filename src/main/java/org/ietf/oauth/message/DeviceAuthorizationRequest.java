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
package org.ietf.oauth.message;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.ws.rs.core.MultivaluedMap;
import org.ietf.oauth.AbstractUrlEncodedMessage;
import org.ietf.oauth.OauthUtility;
import org.ietf.oauth.adapter.JsonStringCollectionAdapter;

/**
 * RFC 8628 OAuth 2.0 Device Grant 3.1. Device Authorization Request
 * <p>
 * Authorization Request to enable OAuth clients on such devices (like smart
 * TVs, media consoles, digital picture frames, and printers) to obtain user
 * authorization to access protected resources by using a user agent on a
 * separate device.
 *
 * @see <a href="https://tools.ietf.org/html/rfc8628">OAuth 2.0 Device Grant</a>
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-13
 */
public class DeviceAuthorizationRequest extends AbstractUrlEncodedMessage {

  /**
   * REQUIRED if the client is not authenticating with the authorization server
   * as described in Section 3.2.1. of [RFC6749]. The client identifier as
   * described in Section 2.2 of [RFC6749].
   */
  @JsonbProperty("client_id")
  private String clientId;
  /**
   * OPTIONAL. The scope of the access request as defined by Section 3.3 of
   * [RFC6749].
   */
  @JsonbTypeAdapter(JsonStringCollectionAdapter.class)
  private Collection<String> scope;

  /**
   * Construct a new OAUTH DeviceAuthorizationRequest instance.
   *
   * @param clientId The Client Identifier valid at the Authorization Server
   * @return a new OAUTH AuthorizationRequest instance.
   */
  public static DeviceAuthorizationRequest getInstance(String clientId) {
    DeviceAuthorizationRequest dr = new DeviceAuthorizationRequest();
    dr.setClientId(clientId);
    return dr;
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param multivaluedMap a MultivaluedMap instance
   * @return a new class instance
   * @throws java.lang.Exception on mv-map parse error
   */
  public static DeviceAuthorizationRequest fromMultivaluedMap(MultivaluedMap<String, String> multivaluedMap) throws Exception {
    return OauthUtility.fromMultivaluedMap(multivaluedMap, DeviceAuthorizationRequest.class);
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param urlEncodedString a URL encoded string
   * @return the class instance
   * @throws Exception on error
   */
  public static DeviceAuthorizationRequest fromUrlEncodedString(String urlEncodedString) throws Exception {
    return OauthUtility.fromUrlEncodedString(urlEncodedString, DeviceAuthorizationRequest.class);
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public Collection<String> getScope() {
    if (scope == null) {
      scope = new HashSet<>();
    }
    return scope;
  }

  public void setScope(Collection<String> scope) {
    this.scope = scope;
  }

  public void addScope(String scope) {
    getScope().add(scope);
  }//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Equals and Hashcode">
  @Override
  public int hashCode() {
    int hash = 5;
    hash = 53 * hash + Objects.hashCode(this.clientId);
    hash = 53 * hash + Objects.hashCode(this.scope);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final DeviceAuthorizationRequest other = (DeviceAuthorizationRequest) obj;
    if (!Objects.equals(this.clientId, other.clientId)) {
      return false;
    }
    return Objects.equals(this.scope, other.scope);
  }//</editor-fold>

}
