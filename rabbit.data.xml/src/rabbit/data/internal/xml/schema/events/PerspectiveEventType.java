/*
 * Copyright 2010 The Rabbit Eclipse Plug-in Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.1-b02-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2010.04.01 at 11:52:54 AM NZDT
//

package rabbit.data.internal.xml.schema.events;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for perspectiveEventType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="perspectiveEventType">
 *   &lt;complexContent>
 *     &lt;extension base="{}durationEventType">
 *       &lt;attribute name="perspectiveId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "perspectiveEventType")
public class PerspectiveEventType extends DurationEventType {

  @XmlAttribute(required = true)
  protected String perspectiveId;

  /**
   * Gets the value of the perspectiveId property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getPerspectiveId() {
    return perspectiveId;
  }

  /**
   * Sets the value of the perspectiveId property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setPerspectiveId(String value) {
    this.perspectiveId = value;
  }

}