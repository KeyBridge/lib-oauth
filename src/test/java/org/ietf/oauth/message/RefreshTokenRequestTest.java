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
import org.junit.*;

/**
 *
 * @author Key Bridge
 */
public class RefreshTokenRequestTest {

  private LoremIpsum l = LoremIpsum.getInstance();
  private Random r = new Random();
  private JsonbUtility jsonb = new JsonbUtility();

  public RefreshTokenRequestTest() {
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
  public void testSomeMethod() throws Exception {

    RefreshTokenRequest a = new RefreshTokenRequest();
    a.setGrantType(l.getWords(1));
    a.setClientId(UUID.randomUUID().toString());
    a.setRefreshToken(UUID.randomUUID().toString());
    for (int i = 0; i < r.nextInt(5) + 1; i++) {
      a.addScope(l.getWords(1));
    }

    System.out.println("Marshal RefreshTokenRequest");
    System.out.println(jsonb.marshal(a));

    String encodedUrl = a.toUrlEncodedString();
    System.out.println("RefreshTokenRequest as url " + encodedUrl);

    RefreshTokenRequest recovered = a.fromUrlEncodedString(encodedUrl);

    System.out.println("recovered...");
    System.out.println(jsonb.marshal(recovered));
    Assert.assertEquals(a, recovered);

    System.out.println("RefreshTokenRequest testRoundTrip OK");

  }

}
