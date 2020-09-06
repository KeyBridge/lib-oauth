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
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.ietf.oauth.type.TokenType;
import org.junit.*;

/**
 *
 * @author Key Bridge
 */
public class TokenExchangeResponseTest {

  private LoremIpsum l = LoremIpsum.getInstance();
  private Random r = new Random();
  private JsonbUtility jsonb = new JsonbUtility();

  public TokenExchangeResponseTest() {
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
  public void testRoundTrip() {

    TokenExchangeResponse tr = new TokenExchangeResponse();
    tr.setAccessToken(UUID.randomUUID().toString());
    tr.setIssuedTokenType(TokenType.id_token);
    tr.setExpiresIn(21038192038l);
    tr.getScope().add(l.getWords(1));
    tr.getScope().add(l.getWords(1));
    tr.setRefreshToken(UUID.randomUUID().toString());
    String json = jsonb.marshal(tr);
//    System.out.println("TokenExchangeResponse " + json);

    TokenExchangeResponse recovered = jsonb.unmarshal(json, TokenExchangeResponse.class);
    Assert.assertEquals(tr, recovered);

    System.out.println("testRoundTrip OK");

    // round trip established OK.
    // test the claims builder, visually inspect until a parser is written
    Map<String, Object> claims = new TokenExchangeResponse.ClaimsBuilder()
      .withActor("actor-" + l.getWords(1))
      .withClientId("client_id-" + l.getWords(1))
      .withScope(Arrays.asList(l.getWords(1), l.getWords(1), l.getWords(1)))
      .withMayAct("may_act-" + l.getWords(1))
      .buildClaims();

//    json = jsonb.marshal(claims);
//    System.out.println("Claims " + json);
    //
    // @todo: deserialize the claims into an Actor object and String list
    //
    //
//    Map<String, Object> deserialized = jsonb.unmarshal(json, Map.class);
//    System.out.println("Deserialized " + deserialized);
//    System.out.println(jsonb.marshal(claims));
  }

}
