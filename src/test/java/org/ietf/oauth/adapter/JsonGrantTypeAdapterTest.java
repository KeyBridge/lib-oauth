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
package org.ietf.oauth.adapter;

import org.ietf.oauth.type.GrantType;
import org.junit.*;

/**
 *
 * @author Key Bridge
 */
public class JsonGrantTypeAdapterTest {

  public JsonGrantTypeAdapterTest() {
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
  public void roundTrip() throws Exception {
    JsonGrantTypeAdapter adapter = new JsonGrantTypeAdapter();
    for (GrantType type : GrantType.values()) {
      String name = adapter.adaptToJson(type);
      System.out.println(" type -> " + name);
      GrantType recovered = adapter.adaptFromJson(name);
      Assert.assertEquals(type, recovered);
    }
    System.out.println("test roundTrip OK");
  }

}
