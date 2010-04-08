/*
 * Copyright 2010 The Rabbit Eclipse Plug-in Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package rabbit.data.test.xml.access;

import rabbit.data.access.model.CommandDataDescriptor;
import rabbit.data.internal.xml.schema.events.CommandEventListType;
import rabbit.data.internal.xml.schema.events.CommandEventType;
import rabbit.data.test.xml.AbstractDataNodeAccessorTest;
import rabbit.data.xml.access.CommandDataAccessor;
import rabbit.data.xml.access.CommandDataAccessor2;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Test for {@link CommandDataAccessor}
 */
@SuppressWarnings("restriction")
public class CommandDataAccessorTest2
    extends
    AbstractDataNodeAccessorTest<CommandDataDescriptor, CommandEventType, CommandEventListType> {

  @Override
  protected CommandDataAccessor2 create() {
    return new CommandDataAccessor2();
  }

  @Override
  protected CommandEventListType createCategory() {
    return objectFactory.createCommandEventListType();
  }

  @Override
  protected CommandEventType createElement() {
    CommandEventType type = objectFactory.createCommandEventType();
    type.setCommandId("abc");
    type.setCount(10);
    return type;
  }

  @Override
  protected List<CommandEventType> getElements(CommandEventListType list) {
    return list.getCommandEvent();
  }

  @Override
  protected void setId(CommandEventType type, String id) {
    type.setCommandId(id);
  }

  @Override
  protected void setUsage(CommandEventType type, long usage) {
    type.setCount((int) usage);
  }

  @Override
  public void testCreateDataNode() throws Exception {
    LocalDate date = new LocalDate();
    CommandEventType type = createElement();
    CommandDataDescriptor des = createDataNode(accessor, date, type);
    
    assertEquals(date, des.getDate());
    assertEquals(type.getCommandId(), des.getCommandId());
    assertEquals(type.getCount(), des.getValue());
  }

}