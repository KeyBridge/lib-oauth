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

import ch.keybridge.json.JsonbUtility;
import com.thedeanda.lorem.LoremIpsum;
import java.util.Random;
import java.util.UUID;
import org.ietf.oauth.type.GrantType;
import org.junit.*;

/**
 *
 * @author Key Bridge
 */
public class AccessTokenRequestTest {

  private LoremIpsum l = LoremIpsum.getInstance();
  private Random r = new Random();

  public AccessTokenRequestTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testRoundTrip() throws Exception {
    AccessTokenRequest a = new AccessTokenRequest();
    a.setGrantType(GrantType.client_credentials);
    a.setCode(UUID.randomUUID().toString());
    a.setRedirectUri(l.getUrl());
    a.setClientId(UUID.randomUUID().toString());
    a.setUsername(l.getEmail());
    a.setPassword(l.getWords(1, 2));
    a.setState(l.getWords(1));
//    a.getScope().add(l.getWords(1));

    System.out.println("Marshal AccessTokenRequest");
    System.out.println(new JsonbUtility().marshal(a));

    String encodedUrl = a.toUrlEncodedString();
    System.out.println("AccessTokenRequest as url " + encodedUrl);

    AccessTokenRequest recovered = AccessTokenRequest.fromUrlEncodedString(encodedUrl);

//    System.out.println("recovered...");
//    System.out.println(new JsonbUtility().marshal(recovered));
    Assert.assertEquals(a, recovered);

    System.out.println("AccessTokenRequest testRoundTrip OK");

  }

}
