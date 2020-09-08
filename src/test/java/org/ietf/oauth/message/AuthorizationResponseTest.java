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
import org.ietf.oauth.type.ErrorResponseType;
import org.junit.*;

/**
 *
 * @author Key Bridge
 */
public class AuthorizationResponseTest {

  private LoremIpsum l = LoremIpsum.getInstance();
  private Random r = new Random();

  public AuthorizationResponseTest() {
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

    AuthorizationResponse a = new AuthorizationResponse();
    a.setCode(UUID.randomUUID().toString());
    a.setState(l.getWords(1));
    a.setError(ErrorResponseType.server_error);
    a.setErrorMessage(l.getWords(3, 5));

    System.out.println("Marshal AuthorizationResponse");
    System.out.println(new JsonbUtility().marshal(a));

    String encodedUrl = a.toUrlEncodedString();
    System.out.println("AuthorizationResponse as url " + encodedUrl);

    AuthorizationResponse recovered = AuthorizationResponse.fromUrlEncodedString(encodedUrl);

//    System.out.println("recovered...");
//    System.out.println(new JsonbUtility().marshal(recovered));
    Assert.assertEquals(a, recovered);

    System.out.println("AuthorizationResponse testRoundTrip OK");

  }

}
