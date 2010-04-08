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

import rabbit.data.internal.xml.schema.events.CommandEventListType;
import rabbit.data.internal.xml.schema.events.CommandEventType;
import rabbit.data.test.xml.AbstractIdToValueAccessorTest;
import rabbit.data.xml.access.CommandDataAccessor;

import java.util.List;

/**
 * Test for {@link CommandDataAccessor}
 */
public class CommandDataAccessorTest extends
    AbstractIdToValueAccessorTest<CommandEventType, CommandEventListType> {

  @Override
  protected CommandDataAccessor create() {
    return new CommandDataAccessor();
  }

  @Override
  protected CommandEventListType createListType() {
    return objectFactory.createCommandEventListType();
  }

  @Override
  protected CommandEventType createXmlType() {
    return objectFactory.createCommandEventType();
  }

  @Override
  protected List<CommandEventType> getXmlTypes(CommandEventListType list) {
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

}