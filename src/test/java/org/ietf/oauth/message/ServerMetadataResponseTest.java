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
  public void prepareForSerialization() {

    ServerMetadataResponse response = buildResponse();

//    clear the locales
    response.getUiLocalesSupported().clear();
    // should set locales to null
    response.prepareForSerialization();
    String json = new JsonbUtility().marshal(response);
//    System.out.println(new JsonbUtility().marshal(response));

    Assert.assertTrue(!json.contains("ui_locales_supported"));

    System.out.println("ServerMetadataResponse prepareForSerialization OK");

  }

  private ServerMetadataResponse buildResponse() {
    ServerMetadataResponse smr = new ServerMetadataResponse();

    smr.setIssuer(l.getUrl());
    smr.setAuthorizationEndpoint(getUri());
    smr.setTokenEndpoint(getUri());
    smr.setJwksUri(getUri());

    smr.getScopesSupported().add(l.getWords(1));
    smr.getScopesSupported().add(l.getWords(1));
    smr.getScopesSupported().add(l.getWords(1));

    smr.setRegistrationEndpoint(getUri());
    smr.getResponseTypesSupported().add(ResponseType.code);
    smr.getResponseTypesSupported().add(ResponseType.id_token);
    smr.getResponseTypesSupported().add(ResponseType.token);

    smr.getResponseModesSupported().add(l.getWords(1));
    smr.getResponseModesSupported().add(l.getWords(1));
    smr.getResponseModesSupported().add(l.getWords(1));

    smr.getGrantTypesSupported().add(GrantType.implicit);
    smr.getGrantTypesSupported().add(GrantType.jwt_bearer);
    smr.getGrantTypesSupported().add(GrantType.client_credentials);

    smr.getTokenEndpointAuthMethodsSupported().add(TokenAuthenticationType.none);
    smr.getTokenEndpointAuthMethodsSupported().add(TokenAuthenticationType.client_secret_basic);

    smr.getTokenEndpointAuthSigningAlgValuesSupported().add(l.getWords(1));

    smr.setServiceDocumentation(getUri());

    smr.getUiLocalesSupported().add(Locale.UK);
    smr.getUiLocalesSupported().add(Locale.FRANCE);
    smr.getUiLocalesSupported().add(Locale.US);
    smr.setOpTosUri(getUri());
    smr.setRevocationEndpoint(getUri());
    smr.getRevocationEndpointAuthMethodsSupported().add(l.getWords(1));
    smr.getRevocationEndpointAuthMethodsSupported().add(l.getWords(1));
    smr.getRevocationEndpointAuthSigningAlgValuesSupported().add(l.getWords(1));
    smr.getRevocationEndpointAuthSigningAlgValuesSupported().add(l.getWords(1));

    smr.setIntrospectionEndpoint(getUri());

    smr.getIntrospectionEndpointAuthMethodsSupported().add(l.getWords(1));
    smr.getIntrospectionEndpointAuthSigningAlgValuesSupported().add(l.getWords(1));

    smr.getCodeChallengeMethodsSupported().add(l.getWords(1));
    smr.getCodeChallengeMethodsSupported().add(l.getWords(1));
    smr.getCodeChallengeMethodsSupported().add(l.getWords(1));

    return smr;
  }

  private URI getUri() {
    return URI.create(l.getUrl());
  }
}
