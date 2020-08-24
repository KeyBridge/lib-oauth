/*
 * Copyright 2019 Key Bridge.
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

import ch.keybridge.json.JsonbUtility;
import java.time.Duration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Key Bridge
 */
public class MacAccessTokenResponseTest {

  private JsonbUtility JSONB;

  public MacAccessTokenResponseTest() {
  }

  @Before
  public void setUp() {

    JSONB = new JsonbUtility();

  }

  @After
  public void tearDown() {
  }

  /**
   * The following tests require, and are intended to test, lib-json-adapter.
   */
  @Test
  public void testMarshal() {

    MacAccessTokenResponse m = MacAccessTokenResponse.getInstance("access_token", "mac_key");
    m.setExpiresIn(Duration.ofMinutes(10));
//    m.setNot_after(ZonedDateTime.now(ZoneId.of("UTC")).plusHours(1));
    System.out.println(JSONB.marshal(m));

    System.out.println(JSONB.marshal(m));
    System.out.println("testMarshal OK");
  }

  @Test
  public void testUnmarshal() {
    MacAccessTokenResponse m = MacAccessTokenResponse.getInstance("access_token", "mac_key");
    System.out.println(JSONB.marshal(m));

    String json = JSONB.marshal(m);
    MacAccessTokenResponse m2 = JSONB.unmarshal(json, MacAccessTokenResponse.class);
//    System.out.println("Unmarshalled M2 ");
    System.out.println(JSONB.marshal(m2));
    Assert.assertEquals(m, m2);
    System.out.println("testUnmarshal OK");
  }
}
