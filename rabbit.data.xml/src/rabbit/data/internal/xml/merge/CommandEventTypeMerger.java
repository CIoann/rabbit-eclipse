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
package rabbit.data.internal.xml.merge;

import java.sql.Timestamp;

import rabbit.data.internal.xml.schema.events.CommandEventType;

/**
 * Merger for {@link CommandEventType}.
 */
public class CommandEventTypeMerger extends AbstractMerger<CommandEventType> {
  
  public CommandEventTypeMerger() {
  }

  @Override
  protected CommandEventType doMerge(CommandEventType t1, CommandEventType t2) {
    CommandEventType result = new CommandEventType();
    result.setCommandId(t1.getCommandId());
    //Test 2
    result.setTimeStamp(t1.getTimeStamp());
    result.setCount(t1.getCount() + t2.getCount());
    return result;
  }

  @Override
  public boolean doIsMergeable(CommandEventType t1, CommandEventType t2) {
	  //Even if they can be merged return false because we don't want them to be merged
//	  System.out.println("Test 10: CommandEventMerger - Keep Multiple Entries");
//	  if ((t1.getCommandId()!=null) && (t1.getCommandId().equals(t2.getCommandId()))){
//		  System.out.println("result t1: "+ t1.getTimeStamp() + "  result t2: " + t2.getTimeStamp());
//		  Timestamp ts1 = java.sql.Timestamp.valueOf(t1.getTimeStamp());
//		  Timestamp ts2 = java.sql.Timestamp.valueOf(t2.getTimeStamp());
//		  long tsTime1 = ts1.getTime();
//		  long tsTime2 = ts2.getTime();
//		  System.out.println("The time " +tsTime2 + "the other time" + tsTime1);
		  
		  
	//  }
    return false;// (t1.getCommandId() != null)
        //&& (t1.getCommandId().equals(t2.getCommandId()));
  }

}
