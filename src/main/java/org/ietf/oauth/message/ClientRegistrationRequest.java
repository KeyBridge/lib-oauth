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

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.annotation.JsonbProperty;

/**
 * RFC 7591 OAuth 2.0 Dynamic Registration 3.1. Client Registration Request
 * <p>
 * To register, the client or developer sends an HTTP POST to the client
 * registration endpoint with a content type of "application/json". The HTTP
 * Entity Payload is a JSON [RFC7159] document consisting of a JSON object and
 * all requested client metadata values as top-level members of that JSON
 * object.
 * <p>
 * Unless used as a claim in a software statement, the authorization server MUST
 * treat all client metadata as self-asserted. Clients MAY use both the direct
 * JSON object and the JWT-encoded software statement to present client metadata
 * to the authorization server as part of the registration request. If the
 * software statement is valid and signed by an acceptable authority (such as
 * the software API publisher), the values of client metadata within the
 * software statement MUST take precedence over those metadata values presented
 * in the plain JSON object, which could have been intercepted and modified.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7591">OAuth 2.0 Dynamic Client
 * Registration Protocol</a>
 * @author Key Bridge
 * @since v2.1.0 created 2020-08-27
 */
public class ClientRegistrationRequest extends AbstractClientMetadata {

  /**
   * The hardware address (usually MAC) of the client server.
   * <p>
   * This is a Key Bridge proprietary field reported by the client server and
   * used by Key Bridge to uniquely identify the client server instance.
   */
  @JsonbProperty("hardware_address")
  private String hardwareAddress;

  /**
   * Build a new ClientRegistrationRequest instance.
   * <p>
   * Tries to read the local host configuration as set the `client_name` plus
   * `hardware_address` valies.
   *
   * @return a new ClientRegistrationRequest instance
   */
  public static ClientRegistrationRequest getInstance() {
    ClientRegistrationRequest cr = new ClientRegistrationRequest();
    /**
     * Try to add the local host configuration by scanning the
     * NetworkInterfaces. Try to read the first configured Ethernet interfaces,
     * the names of which are assumed to start with "e".
     */
    try {
      cr.setClientName(InetAddress.getLocalHost().getHostName());
      for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
        if (networkInterface.getHardwareAddress() != null && !networkInterface.getInterfaceAddresses().isEmpty()) {
          cr.setHardwareAddress(formatHardwareAddress(networkInterface.getHardwareAddress()));
          break;
        }
      }
    } catch (SocketException ex) {
      Logger.getLogger(ClientRegistrationRequest.class.getName()).log(Level.SEVERE, "Error collecting host configuration .  {0}", ex.getMessage());
    } catch (UnknownHostException ex) {
      Logger.getLogger(ClientRegistrationRequest.class.getName()).log(Level.SEVERE, "Error reading host name.  {0}", ex.getMessage());
    }
    return cr;
  }

  /**
   * Helper method to format the hardwareAddress into a standard-reading MAC
   * address. e.g. formats a byte arrays as "50:9a:4c:52:60:f9".
   *
   * @param hardwareAddress the hardware address bytes
   * @return a normal looking MAC address string
   */
  private static String formatHardwareAddress(byte[] hardwareAddress) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < hardwareAddress.length; i++) {
      sb.append(String.format("%02x%s", hardwareAddress[i], (i < hardwareAddress.length - 1) ? ":" : ""));
    }
    return sb.toString();
  }

  //<editor-fold defaultstate="collapsed" desc="Getter and Setter">
  public String getHardwareAddress() {
    return hardwareAddress;
  }

  public void setHardwareAddress(String hardwareAddress) {
    this.hardwareAddress = hardwareAddress;
  }//</editor-fold>

}
