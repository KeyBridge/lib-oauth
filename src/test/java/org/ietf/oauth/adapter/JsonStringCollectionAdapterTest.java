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

import com.thedeanda.lorem.LoremIpsum;
import java.util.Collection;
import java.util.TreeSet;
import org.junit.*;

/**
 *
 * @author Key Bridge
 */
public class JsonStringCollectionAdapterTest {

  private static LoremIpsum l = LoremIpsum.getInstance();

  private static JsonStringCollectionAdapter adapter;

  public JsonStringCollectionAdapterTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    adapter = new JsonStringCollectionAdapter();
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
  public void testAdaptToandFromJson() throws Exception {
    Collection<String> theCollection = new TreeSet<>();
    for (int i = 0; i < 10; i++) {
      theCollection.add(l.getWords(1, 5));
    }
    String flattened = adapter.adaptToJson(theCollection);
//    System.out.println("LIST " + theCollection);
//    System.out.println("FLAT " + flattened);
    Collection<String> recovered = adapter.adaptFromJson(flattened);
//    System.out.println("RECO " + recovered);
    /**
     * Note: The `recovered` collection is an ArrayList so equality does not
     * hold.
     */
    Assert.assertTrue(theCollection.containsAll(recovered));
    Assert.assertTrue(recovered.containsAll(theCollection));
    System.out.println("testAdaptToandFromJson OK");
  }

}
