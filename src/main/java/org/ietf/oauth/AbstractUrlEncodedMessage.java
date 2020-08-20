/*
 * Copyright 2018 Key Bridge.
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

import java.lang.reflect.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.bind.adapter.JsonbAdapter;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

/**
 * Abstract class implementing a URL encoding and decoding capabilities.
 * <p>
 * This facilitates sending and receiving URL-encoded objects; specifically
 * request messages that must be encoded as
 * {@code application/x-www-form-urlencoded}.
 *
 * @author Key Bridge 10/08/17
 * @since v0.2.0
 */
public abstract class AbstractUrlEncodedMessage {

  protected static final Logger LOGGER = Logger.getLogger(AbstractUrlEncodedMessage.class.getName());

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   * <p>
   * This parses the query string into a MultivaluedMap then calls
   * {@link #readMultivaluedMap(javax.ws.rs.core.MultivaluedMap)}
   *
   * @param <T>              The class type.
   * @param urlEncodedString a URL encoded string
   * @return the URI template appended with query parameters
   * @throws Exception on error
   */
  public <T extends AbstractUrlEncodedMessage> T readUrlEncodedString(String urlEncodedString) throws Exception {
    return readMultivaluedMap(parseUrlEncodedString(urlEncodedString));
  }

  /**
   * Create a new instance of this type and set field values from the provided
   * input configuration.
   *
   * @param urlEncodedString a URL-encoded query string.
   * @return a MultivaluedMap instance
   */
  private MultivaluedMap<String, String> parseUrlEncodedString(String urlEncodedString) {
    /**
     * Parse the query string into an MVMap.
     */
    MultivaluedMap<String, String> multivaluedMap = new MultivaluedHashMap<>();
    for (String string : urlEncodedString.split("&")) {
      /**
       * Defensive programming note: Possibly force the key to lower case to
       * ensure a match with the class field name. In this library all field
       * names are lower case. Currently the parsing logic is extremely case
       * sensitive.
       */
      multivaluedMap.put(string.split("=")[0],
                         Arrays.asList(string.split("=")[1].split("[+ ]")));
    }
    return multivaluedMap;
  }

  /**
   * Create and set field values as they may be identified in a MultivaluedMap
   * instance.
   * <p>
   * This method uses Java bean reflection to inspect the current bean instance
   * configuration and assign to each field a corresponding value in the a
   * MultivaluedMap instance.
   * <p>
   * Non-String types are converted where an XmlAdapter is identified in the
   * class annotation. Collection fields are transformed from space-delimited
   * strings.
   *
   * @param <T>            the class type
   * @param multivaluedMap a MultivaluedMap instance configuration
   * @return this class instance with populated fields
   * @throws Exception on error
   */
  public <T extends AbstractUrlEncodedMessage> T readMultivaluedMap(MultivaluedMap<String, String> multivaluedMap) throws Exception {
    Class c = this.getClass();
    /**
     * Build a mapping between the json property names and the object field.
     */
    Map<String, Field> jsonNamedFields = new HashMap<>();
    for (Field field : c.getDeclaredFields()) {
      /**
       * Get the adapted field name if declared.
       */
      String fieldName = field.getDeclaredAnnotation(JsonbProperty.class) == null
                         ? field.getName()
                         : field.getDeclaredAnnotation(JsonbProperty.class).value();
      jsonNamedFields.put(fieldName, field);
    }
    /**
     * Scan and apply the multi-map.
     */
    for (Map.Entry<String, List<String>> entry : multivaluedMap.entrySet()) {
      try {
        Field field = jsonNamedFields.get(entry.getKey());
        field.setAccessible(true);
        /**
         * If the field is a collection then expect to iterate over one or more
         * entries. The expected entries may be String or Enumerated.
         */
        if (field.getType().isAssignableFrom(Collection.class)) {
          /**
           * Need to convert the types.
           */
          ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
          Type actualType = parameterizedType.getActualTypeArguments()[0];
          /**
           * Look for an add method to add the enumerated type.
           */
          Method addMethod = c.getDeclaredMethod("add" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), (Class) actualType);
          /**
           * Call the add[type] method, using the XmlAdapter if available to
           * correctly unmarshal String to object (Enum, etc.)
           */
          for (String param : entry.getValue()) {
            /**
             * param is a space-delimited list of Strings. Split and parse.
             */
            for (String paramFragment : param.split("\\s+")) {

              if (field.getDeclaredAnnotation(JsonbTypeAdapter.class) != null) {
                JsonbTypeAdapter typeAdapter = field.getDeclaredAnnotation(JsonbTypeAdapter.class);
                JsonbAdapter adapter = typeAdapter.value().getConstructor().newInstance();
                addMethod.invoke(this, adapter.adaptFromJson(paramFragment));
              } else {
                /**
                 * If no Adapter then the add method must accept String. (This
                 * is not an assumption, but an implicit requirement.)
                 */
                addMethod.invoke(this, paramFragment);
              }
            }
          }
        } else {
          /**
           * Set the field value directly. Use the XmlAdapter if available to
           * correctly unmarshal String to object (Enum, etc.)
           */
          if (field.getDeclaredAnnotation(JsonbTypeAdapter.class) != null) {
            JsonbTypeAdapter typeAdapter = field.getDeclaredAnnotation(JsonbTypeAdapter.class);
            JsonbAdapter adapter = typeAdapter.value().getConstructor().newInstance();
            field.set(this, adapter.adaptFromJson(entry.getValue().get(0)));
          } else {
            /**
             * If no XmlAdapter then the add method must accept String. (This is
             * not an assumption, but an implicit requirement.)
             */
            field.set(this, entry.getValue().get(0));
          }
        }
      } catch (NoSuchFieldException | SecurityException noSuchFieldException) {
        LOGGER.log(Level.SEVERE, "{0} is not a valid field name of class {1}", new Object[]{entry.getKey(), c.getSimpleName()});
      }
    }
    return (T) this;
  }

  /**
   * Transform this class instance into a URL-encoded string.
   * <p>
   * This method uses Java bean reflection to inspect the current bean instance
   * configuration and assign each non-null field into a query parameter array.
   * <p>
   * Collection fields are transformed into space-delimited strings. All other
   * fields are copied in as Strings with an implicit call to their toString
   * method.
   *
   * @return the bean configuration as a URL-encoded query string
   * @throws Exception on error
   */
  public String writeUrlEncoded() throws Exception {
    /**
     * <p>
     * <p>
     * We use the Jersey UriBuilder to handle encoding.
     */
    UriBuilder uribuilder = UriBuilder.fromUri("");
    Class c = this.getClass();
    for (Field field : c.getDeclaredFields()) {
      /**
       * Ignore transient fields. Do not reveal `static final` attributes.
       */
      if (field.getDeclaredAnnotation(JsonbTransient.class) != null) {
        continue;
      } else if (Modifier.isStatic(field.getModifiers())) {
        continue;
      } else if (Modifier.isFinal(field.getModifiers())) {
        continue;
      }

      field.setAccessible(true);
      Object fieldValue = field.get(this);
      if (fieldValue != null) {
        /**
         * Get the adapted field name.
         */
        String fieldName = field.getDeclaredAnnotation(JsonbProperty.class) == null
                           ? field.getName()
                           : field.getDeclaredAnnotation(JsonbProperty.class).value();
        /**
         * Intercept collections.
         */
        if (field.getType().isAssignableFrom(Collection.class)) {
          /**
           * Extract the collection into a String list, then write the String
           * list to the URI builder.
           */
          Collection theCollection = (Collection) field.get(this);
          if (!theCollection.isEmpty()) {
            List<String> theList = new ArrayList<>();
            /**
             * Using the XmlAdapter if available to correctly marshal Object to
             * String.
             */
            if (field.getDeclaredAnnotation(JsonbTypeAdapter.class) != null) {
              JsonbTypeAdapter typeAdapter = field.getDeclaredAnnotation(JsonbTypeAdapter.class);
              JsonbAdapter adapter = typeAdapter.value().getConstructor().newInstance();
              for (Object object : theCollection) {
                theList.add((String) adapter.adaptToJson(object));
              }
            } else {
              theCollection.forEach((object) -> {
                theList.add(String.valueOf(object));
              });
            }
            /**
             * Convert the list from a comma-delimited to a space-delimited
             * list.
             */
            String theListString = theList.toString();
            uribuilder.queryParam(fieldName,
                                  theListString.substring(1, theListString.length() - 1).replaceAll("(, ?)", " "));
          }
        } else {
          /**
           * The field is a normal field. Write it to the URI.
           */
          uribuilder.queryParam(fieldName, field.get(this));
        }
      }
    }
    /**
     * Return the query parameter portion of the URI builder, less the initial
     * "?" character.
     */
    return uribuilder.build("").getQuery();
  }

  /**
   * @deprecated replaced with XmlAdapters
   *
   * Build an enumerated instance of the indicated class type.
   *
   * @param type the class type
   * @param name the enumerated instance name i.e. value)
   * @return an enumerated instance
   * @throws Exception on errro
   */
  private Enum buildEnumInstance(Class<Enum> type, String name) throws Exception {
    Enum enumInstance = null;
    try {
      enumInstance = Enum.valueOf(type, name.toUpperCase());
    } catch (Exception e) {
      /**
       * If the names do not match then try looking for a 'fromText' method.
       */
      try {
        Method fromTextMethod = type.getDeclaredMethod("fromText", String.class);
        enumInstance = (Enum) fromTextMethod.invoke(type, name);
      } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException noSuchMethodException) {
        LOGGER.log(Level.SEVERE, "Failed instantiate enumerated class {0} from name {1}", new Object[]{type.getSimpleName(), name});
      }
    }
    return enumInstance;
  }

}
