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
package rabbit.data.internal.xml.convert;

import org.eclipse.core.commands.common.NotDefinedException;

import rabbit.data.internal.xml.schema.events.CommandEventType;
import rabbit.data.store.model.CommandEvent;

/**
 * Converts {@link CommandEvent} to {@link CommandEventType}.
 */
public class CommandEventConverter extends
    AbstractConverter<CommandEvent, CommandEventType> {
  
  public CommandEventConverter() {
  }
  private String classify(String type) {
	  String nType = null;
	  switch (type) {
      case "Edit":  nType = "Editing";
      break;
      case "File":  nType = "Viewing";
      break;
      case "Help":  nType = "Viewing";
      break;
      case "Navigate":  nType = "Navigating";
      break;
      case "Perspective":  nType = "Viewing";
      break;
      case "Project":  nType = "Debugging";
      break;
      case "Refactor - Java":  nType = "Editing";
      break;
      case "Run/Debug":  nType = "Debugging";
      break; 
      case "Search":  nType = "Navigating";
      break;
      case "Source":  nType = "Editing";
      break;
      case "Text Editing":  nType = "Editing";
      break;
      case "View":  nType = "Viewing";
      break;
      case "Window":  nType = "Viewing";
      break;
      default: nType = "Invalid";
               break;
  }
	  System.out.println("The type : "+type);
	  return nType;
  }
  @Override
  protected CommandEventType doConvert(CommandEvent element) {
    CommandEventType type = new CommandEventType();
    type.setCommandId(element.getExecutionEvent().getCommand().getId());
    try {
		type.setCategory(classify(element.getExecutionEvent().getCommand().getCategory().getName()));
		type.setName(element.getExecutionEvent().getCommand().getName());
	} catch (NotDefinedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    type.setTimeStamp(element.getTsStart().toString());
    type.setSid(String.valueOf(element.getSid()));
      type.setCount(1);
    return type;
  }
  

}
