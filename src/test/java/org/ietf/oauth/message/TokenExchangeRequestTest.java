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
import org.ietf.oauth.type.TokenType;
import org.junit.*;

/**
 *
 * @author Key Bridge
 */
public class TokenExchangeRequestTest {

  private LoremIpsum l = LoremIpsum.getInstance();
  private Random r = new Random();
  private JsonbUtility jsonb = new JsonbUtility();

  public TokenExchangeRequestTest() {
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
    TokenExchangeRequest t = new TokenExchangeRequest();
    t.setResource(l.getUrl());
    t.setAudience(l.getUrl());
    t.getScope().add(l.getWords(1));
    t.getScope().add(l.getWords(1));
    t.getScope().add(l.getWords(1));
    t.setRequestedTokenType(TokenType.refresh_token);
    t.setSubjectToken(UUID.randomUUID().toString());
    t.setSubjectTokenType(TokenType.access_token);
    t.setActorToken(UUID.randomUUID().toString());
    t.setActorTokenType(TokenType.id_token);

    String json = jsonb.marshal(t);
//    System.out.println("TokenExchangeRequest" + json);

//    System.out.println("Query : " + t.writeUrlEncoded()); // throws Exception
    String query = t.toUrlEncodedString();

    TokenExchangeRequest recovered = new TokenExchangeRequest().fromUrlEncodedString(query);

//    System.out.println("Recovered " + jsonb.marshal(recovered));
    Assert.assertEquals(t, recovered);
    System.out.println("testRoundTrip OK");

  }

}
