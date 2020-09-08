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
package org.ietf.oauth;

import ch.keybridge.json.JsonbUtility;
import com.thedeanda.lorem.LoremIpsum;
import java.util.Random;
import java.util.UUID;
import javax.ws.rs.core.MultivaluedMap;
import org.ietf.oauth.message.AccessTokenRequest;
import org.ietf.oauth.type.GrantType;
import org.junit.*;

/**
 *
 * @author Key Bridge
 */
public class OauthUtilityTest {

  private LoremIpsum l = LoremIpsum.getInstance();
  private Random r = new Random();

  private JsonbUtility jsonb = new JsonbUtility();

  public OauthUtilityTest() {
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
    System.out.println(jsonb.marshal(a));

    MultivaluedMap<String, Object> mvmap = OauthUtility.toMultivaluedMap(a);

    System.out.println("as map \n" + mvmap);

    AccessTokenRequest recoveredMap = OauthUtility.fromMultivaluedMap(mvmap, AccessTokenRequest.class);
    System.out.println("from map" + jsonb.marshal(recoveredMap));

    String url = OauthUtility.toUrlEncodedString(a);
    System.out.println("as url \n" + url);

    AccessTokenRequest recoveredUrl = OauthUtility.fromUrlEncodedString(url, AccessTokenRequest.class);
    System.out.println("from url " + jsonb.marshal(recoveredUrl));

  }

}
