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

import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
 * Object URL-encoding and decoding utility. This utility provides object
 * serialization and deserialization methods commonly used in OAuth and related
 * specifications.
 *
 * @author Key Bridge
 * @since v2.2.0 created 2020-09-08
 */
public class OauthUtility {

  private static final Logger LOG = Logger.getLogger(OauthUtility.class.getName());

  /**
   * Parse a URL encoded string into a MultivaluedMap.
   *
   * @param urlEncodedString a URL-encoded query string.
   * @return a MultivaluedMap instance
   */
  public static MultivaluedMap<String, Object> parseUrlEncodedString(String urlEncodedString) {
    /**
     * Parse the query string into an MVMap.
     * <p>
     * Defensive programming note: Possibly force the key to lower case to
     * ensure a match with the class field name. In this library all field names
     * are lower case. Currently the parsing logic is extremely case sensitive.
     */
    MultivaluedMap<String, Object> multivaluedMap = new MultivaluedHashMap<>();
    for (String string : urlEncodedString.split("&")) {
      try {
        multivaluedMap.add(string.split("=")[0], URLDecoder.decode(string.split("=")[1], StandardCharsets.UTF_8.name()));
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        multivaluedMap.add(string.split("=")[0], string.split("=")[1]);
        LOG.log(Level.WARNING, "{0}  {1}", new Object[]{unsupportedEncodingException.getMessage(), string});
      }
    }
    return multivaluedMap;
  }

  /**
   * Transform the provided object instance into a URL-encoded string.
   * <p>
   * This method uses Java bean reflection to inspect the current bean instance
   * configuration and assign each non-null field into a query parameter array.
   * <p>
   * Collection fields are transformed into space-delimited strings. All other
   * fields are copied in as Strings with an implicit call to their toString
   * method.
   *
   * @param instance an object instance
   * @return the bean configuration as a URL-encoded query string
   */
  public static String toUrlEncodedString(Object instance) {
    /**
     * Use the Jersey UriBuilder to handle encoding.
     */
    UriBuilder uribuilder = UriBuilder.fromUri("");
    MultivaluedMap<String, Object> mvMap = toMultivaluedMap(instance);
    for (Map.Entry<String, List<Object>> entry : mvMap.entrySet()) {
      for (Object value : entry.getValue()) {
        uribuilder.queryParam(entry.getKey(), value);
      }
    }
    /**
     * Return the query parameter portion of the URI builder, less the initial
     * "?" character. Note: return the RAW query string, which is URL encoded.
     */
    return uribuilder.build("").getRawQuery();
  }

  public static <T> T fromUrlEncodedString(String urlEncodedString, Class cls) throws Exception {
    return fromMultivaluedMap(parseUrlEncodedString(urlEncodedString), cls);
  }

  /**
   * Transform this entity to a multivalued map. The map can then be used to
   * generate a URL-encoded version of the object instance.
   *
   * @param instance an object instance
   * @return a MultivaluedMap instance configuration
   */
  public static MultivaluedMap<String, Object> toMultivaluedMap(Object instance) {
    return toMultivaluedMap(instance, instance.getClass());
  }

  /**
   * Recursively inspect the current instance class and its superclasses,
   * transforming each non-null field into a MultivaluedMap entry. The map can
   * then be used to generate a URL-encoded version of the object instance.
   *
   * @param instance the class instance
   * @param cls      the class type
   * @return a non-null MultivaluedMap instance
   */
  private static MultivaluedMap<String, Object> toMultivaluedMap(Object instance, Class cls) {
    MultivaluedMap<String, Object> multivaluedMap = new MultivaluedHashMap<>();
    if (cls.getSuperclass() != null) {
      /**
       * If the class extends another then first recursive to inspect the super
       * class.
       */
      for (Map.Entry<String, List<Object>> entry : toMultivaluedMap(instance, cls.getSuperclass()).entrySet()) {
        multivaluedMap.put(entry.getKey(), entry.getValue());
      }
    }
    /**
     * Inspect the current class.
     */
    for (Field field : cls.getDeclaredFields()) {
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
      /**
       * Wrap each field access attempt in a try/catch.
       */
      try {
        field.setAccessible(true);
        Object fieldValue = field.get(instance);
        if (fieldValue != null) {
          /**
           * Get the adapted field name.
           */
          String fieldName = field.getDeclaredAnnotation(JsonbProperty.class) == null
                             ? field.getName()
                             : field.getDeclaredAnnotation(JsonbProperty.class).value();
          /**
           * If the field has an adapter then use it directly. JSONB adapters
           * are applied to collections.
           */
          if (field.getDeclaredAnnotation(JsonbTypeAdapter.class) != null) {
            JsonbTypeAdapter typeAdapter = field.getDeclaredAnnotation(JsonbTypeAdapter.class);
            JsonbAdapter adapter = typeAdapter.value().getConstructor().newInstance();
            multivaluedMap.putSingle(fieldName, adapter.adaptToJson(fieldValue));
          } else if (field.getType().isAssignableFrom(Map.class)) {
            /**
             * NO-OP
             * <p>
             * Ignore and do no not copy object map contents into the top level.
             * This is specifically for AbstractClientMetadata
             * extendedParameters field, which is Key Bridge proprietary.
             */
//            Map<Object, Object> theMap = (Map) field.get(instance);
//            theMap.entrySet().stream()
//              .filter(e -> e.getValue() != null)
//              .forEach(e -> {
//                multivaluedMap.putSingle(String.valueOf(e.getKey()), String.valueOf(e.getValue()));
//              });
          } else if (field.getType().isAssignableFrom(Collection.class)) {
            /**
             * Intercept collections. Extract the collection then add the
             * collection to the map.
             */
            Collection theCollection = (Collection) field.get(instance);
            if (!theCollection.isEmpty()) {
              multivaluedMap.put(fieldName, new ArrayList(theCollection));
            }
          } else {
            /**
             * The field is a normal field with no transformer. Write its String
             * value to the map.
             */
            multivaluedMap.putSingle(fieldName, field.get(instance));
          }
        }
      } catch (Exception exception) {
        LOG.log(Level.INFO, "Error accessing field {0}.{1}", new Object[]{cls.getSimpleName(), field.getName()});
        LOG.log(Level.SEVERE, null, exception);
      }
    }
    /**
     * Done.
     */
    return multivaluedMap;
  }

  /**
   * Internal helper method to read class fields. This recursively inspects the
   * class and its parents. The returned map provides the field itself with a
   * mapping to its declared JSON serialized name.
   *
   * @param instance the object instance
   * @param cls      the class type
   * @return a map of field name to field
   */
  private static Map<String, Field> readClassFields(Object instance, Class cls) {
    Map<String, Field> fields = new HashMap<>();
    /**
     * Capture the inherited fields.
     */
    if (cls.getSuperclass() != null) {
      fields.putAll(readClassFields(instance, cls.getSuperclass()));
    }
    /**
     * Capture the fields.
     */
    for (Field field : cls.getDeclaredFields()) {
      /**
       * Get the adapted field name if declared.
       */
      String fieldName = field.getDeclaredAnnotation(JsonbProperty.class) == null
                         ? field.getName()
                         : field.getDeclaredAnnotation(JsonbProperty.class).value();
      fields.put(fieldName, field);
    }
    return fields;
  }

  /**
   * Read MultivaluedMap entries and assign them to class fields according to
   * their matching field name.
   * <p>
   * Note that collection fields must have an {@code add} method available as
   * discovered collection entries are added to the object instance one at a
   * time.
   *
   * @param <T>            the class type to produce
   * @param multivaluedMap the MultivaluedMap instance from which to parse
   *                       values
   * @param cls            the class type to produce
   * @return an object instance of the class
   * @throws Exception on parse or method assignment error
   */
  public static <T> T fromMultivaluedMap(MultivaluedMap<String, Object> multivaluedMap, Class cls) throws Exception {
    /**
     * Instantiate a new instance of the class.
     */
    T instance = (T) cls.getConstructor().newInstance();
    /**
     * Build a mapping between the json property names and the object field.
     */
    Map<String, Field> jsonNamedFields = readClassFields(instance, cls);
    /**
     * Scan and apply the multi-map.
     */
    for (Map.Entry<String, List<Object>> entry : multivaluedMap.entrySet()) {
      try {
        Field field = jsonNamedFields.get(entry.getKey());
        field.setAccessible(true);
        /**
         * If the field has an adapter then use it directly. JSONB adapters are
         * applied to collections.
         */
        if (field.getDeclaredAnnotation(JsonbTypeAdapter.class) != null) {
          JsonbTypeAdapter typeAdapter = field.getDeclaredAnnotation(JsonbTypeAdapter.class);
          JsonbAdapter adapter = typeAdapter.value().getConstructor().newInstance();
          field.set(instance, adapter.adaptFromJson(entry.getValue().get(0)));
        } else if (field.getType().isAssignableFrom(Collection.class)) {
          /**
           * If the field is a collection then expect to iterate over one or
           * more entries. The expected entries may be String or Enumerated.
           * Need to convert the types.
           */
          ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
          Type actualType = parameterizedType.getActualTypeArguments()[0];
          /**
           * Look for an add method to add the enumerated type.
           */
          Method addMethod = cls.getDeclaredMethod("add" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), (Class) actualType);
          /**
           * Call the add[type] method, using the Json Adapter if available to
           * correctly unmarshal String to object (Enum, etc.)
           */
          for (Object entryValue : entry.getValue()) {
            /**
             * param is a space-delimited list of Strings. Split and parse.
             */
            if (field.getDeclaredAnnotation(JsonbTypeAdapter.class) != null) {
              JsonbTypeAdapter typeAdapter = field.getDeclaredAnnotation(JsonbTypeAdapter.class);
              JsonbAdapter adapter = typeAdapter.value().getConstructor().newInstance();
              addMethod.invoke(instance, adapter.adaptFromJson(entryValue));
            } else {
              /**
               * If no Adapter then the add method must accept a single
               * parameter which must be either a String _or_ Enum. (This is not
               * an assumption, but an implicit requirement.) Complex object
               * lists must be intercepted with an adapter.
               */
              if (addMethod.getParameters()[0].getType().isEnum()) {
                Enum enumInstance = buildEnumInstance((Class<Enum>) addMethod.getParameters()[0].getType(), (String) entryValue);
                addMethod.invoke(instance, enumInstance);
              } else {
                addMethod.invoke(instance, entryValue);
              }
            }
          }
        } else {
          /**
           * Set the field value directly. Use the Json Adapter if available to
           * correctly unmarshal String to object (Enum, etc.)
           */
          if (field.getDeclaredAnnotation(JsonbTypeAdapter.class) != null) {
            JsonbTypeAdapter typeAdapter = field.getDeclaredAnnotation(JsonbTypeAdapter.class);
            JsonbAdapter adapter = typeAdapter.value().getConstructor().newInstance();
            field.set(instance, adapter.adaptFromJson(entry.getValue().get(0)));
          } else {
            /**
             * If no Adapter then the field must be either a String _or_ Enum.
             * (This is not an assumption, but an implicit requirement.) Complex
             * object lists must be intercepted with an adapter.
             */
            if (field.getType().isEnum() && !entry.getValue().get(0).getClass().isEnum()) {
              Enum enumInstance = buildEnumInstance((Class<Enum>) field.getType(), (String) entry.getValue().get(0));
              field.set(instance, enumInstance);
            } else {
              field.set(instance, entry.getValue().get(0));
            }
          }
        }
      } catch (NoSuchFieldException | SecurityException noSuchFieldException) {
        LOG.log(Level.SEVERE, "{0} is not a valid field name of class {1}", new Object[]{entry.getKey(), cls.getSimpleName()});
      }
    }
    return instance;
  }

  /**
   * Build an enumerated instance of the indicated class type.
   *
   * @param type the class type
   * @param name the enumerated instance name i.e. value)
   * @return an enumerated instance
   * @throws Exception on error
   */
  private static Enum buildEnumInstance(Class<Enum> type, String name) throws Exception {
    Enum enumInstance = null;
    try {
      enumInstance = Enum.valueOf(type, name);
    } catch (Exception e) {
      /**
       * If the names do not match then try looking for a 'fromText' method.
       */
      try {
        Method fromTextMethod = type.getDeclaredMethod("fromText", String.class);
        enumInstance = (Enum) fromTextMethod.invoke(type, name);
      } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException noSuchMethodException) {
        LOG.log(Level.SEVERE, "Failed instantiate enumerated class {0} from name {1}", new Object[]{type.getSimpleName(), name});
      }
    }
    return enumInstance;
  }
}
