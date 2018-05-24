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
package org.ietf.oauth.adapter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.time.Duration instance
 * to seconds.
 *
 * @since v0.0.1
 * @author Jesse Caulfield 10/06/17
 */
public class XmlDurationSecondsAdapter extends XmlAdapter<String, Duration> {

  @Override
  public Duration unmarshal(String v) throws Exception {
    return Duration.of(Long.parseLong(v), ChronoUnit.SECONDS);
  }

  @Override
  public String marshal(Duration v) throws Exception {
    return String.valueOf(v.getSeconds());
  }

}
