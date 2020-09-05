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
import java.net.URI;
import java.util.Locale;
import java.util.Random;
import org.ietf.oauth.type.GrantType;
import org.ietf.oauth.type.ResponseType;
import org.ietf.oauth.type.TokenAuthenticationType;
import org.junit.*;

/**
 *
 * @author Key Bridge
 */
public class ServerMetadataResponseTest {

  private LoremIpsum l = LoremIpsum.getInstance();
  private Random r = new Random();

  public ServerMetadataResponseTest() {
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
  public void testSomeMethod() {

    ServerMetadataResponse response = buildResponse();

//    clear the locales
    response.getUiLocalesSupported().clear();
    response.getTokenEndpointAuthSigningAlgValuesSupported().clear();
    // should set locales to null
    response.prepareForSerialization();

    System.out.println(new JsonbUtility().marshal(response));

  }

  private ServerMetadataResponse buildResponse() {
    ServerMetadataResponse r = new ServerMetadataResponse();

    r.setIssuer(l.getUrl());
    r.setAuthorizationEndpoint(getUri());
    r.setTokenEndpoint(getUri());
    r.setJwksUri(getUri());

    r.getScopesSupported().add(l.getWords(1));
    r.getScopesSupported().add(l.getWords(1));
    r.getScopesSupported().add(l.getWords(1));

    r.setRegistrationEndpoint(getUri());
    r.getResponseTypesSupported().add(ResponseType.code);
    r.getResponseTypesSupported().add(ResponseType.id_token);
    r.getResponseTypesSupported().add(ResponseType.token);

    r.getResponseModesSupported().add(l.getWords(1));
    r.getResponseModesSupported().add(l.getWords(1));
    r.getResponseModesSupported().add(l.getWords(1));

    r.getGrantTypesSupported().add(GrantType.implicit);
    r.getGrantTypesSupported().add(GrantType.jwt_bearer);
    r.getGrantTypesSupported().add(GrantType.client_credentials);

    r.getTokenEndpointAuthMethodsSupported().add(TokenAuthenticationType.none);
    r.getTokenEndpointAuthMethodsSupported().add(TokenAuthenticationType.client_secret_basic);

    r.getTokenEndpointAuthSigningAlgValuesSupported().add(l.getWords(1));

    r.setServiceDocumentation(getUri());

    r.getUiLocalesSupported().add(Locale.UK);
    r.getUiLocalesSupported().add(Locale.FRANCE);
    r.getUiLocalesSupported().add(Locale.US);
    r.setOpTosUri(getUri());
    r.setRevocationEndpoint(getUri());
    r.getRevocationEndpointAuthMethodsSupported().add(l.getWords(1));
    r.getRevocationEndpointAuthMethodsSupported().add(l.getWords(1));
    r.getRevocationEndpointAuthSigningAlgValuesSupported().add(l.getWords(1));
    r.getRevocationEndpointAuthSigningAlgValuesSupported().add(l.getWords(1));

    r.setIntrospectionEndpoint(getUri());

    r.getIntrospectionEndpointAuthMethodsSupported().add(l.getWords(1));
    r.getIntrospectionEndpointAuthSigningAlgValuesSupported().add(l.getWords(1));

    r.getCodeChallengeMethodsSupported().add(l.getWords(1));
    r.getCodeChallengeMethodsSupported().add(l.getWords(1));
    r.getCodeChallengeMethodsSupported().add(l.getWords(1));

    return r;
  }

  private URI getUri() {
    return URI.create(l.getUrl());
  }
}
