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

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.ietf.oauth.type.ScopeType;

/**
 * Java XML adapter to translate between a ScopeType instance.
 *
 * @since v0.0.1
 * @author Jesse Caulfield 10/06/17
 */
public class XmlScopeTypeAdapter extends XmlAdapter<String, ScopeType> {

  @Override
  public ScopeType unmarshal(String v) throws Exception {
    return ScopeType.fromText(v);
  }

  @Override
  public String marshal(ScopeType v) throws Exception {
    return v.toString();
  }

}
