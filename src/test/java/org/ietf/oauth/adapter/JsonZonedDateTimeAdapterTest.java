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

import java.time.Clock;
import java.time.ZonedDateTime;
import org.junit.*;

/**
 *
 * @author Key Bridge
 */
public class JsonZonedDateTimeAdapterTest {

  private static JsonZonedDateTimeAdapter adapter;

  public JsonZonedDateTimeAdapterTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    adapter = new JsonZonedDateTimeAdapter();
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

    ZonedDateTime now = ZonedDateTime.now(Clock.systemUTC().getZone());
    String string = adapter.adaptToJson(now);
    ZonedDateTime recovered = adapter.adaptFromJson(string);
    Assert.assertEquals(now, recovered);

  }

}
