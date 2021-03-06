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
package rabbit.data.store.model;

import static com.google.common.base.Preconditions.checkNotNull;

import org.eclipse.core.commands.ExecutionEvent;
import org.joda.time.DateTime;

/**
 * A command execution event.
 */
public class CommandEvent extends DiscreteEvent {

  private final ExecutionEvent event;
  private final int sid;
  private final String filename; 
  /**
   * Constructs a new event.
   * 
   * @param time The time of the event.
   * @param event The execution event.
   * @throws NullPointerException If any of the arguments is null.
   */
  public CommandEvent(DateTime time,int sid) {
	    super(time);
	    this.sid =sid;
	    this.event = null;
	    this.filename = "-1";
	  }
  public CommandEvent(DateTime time, ExecutionEvent event) {
    super(time);
    this.sid = -1;
    this.event = checkNotNull(event);
    this.filename = "-1";
  }

  public int getSid() {
	return sid;
}
public CommandEvent(DateTime time, ExecutionEvent event, int sid) {
	    super(time);
	    this.sid = sid;
	    this.event = checkNotNull(event);
	    this.filename = "-1";
	  }
public CommandEvent(DateTime time, ExecutionEvent event, int sid, String filename) {
    super(time);
    this.sid = sid;
    this.event = checkNotNull(event);
    this.filename = filename;
  }

public final String getFilename() {
	return this.filename;
}

  /**
   * Gets the execution event.
   * 
   * @return The execution event.
   */
  public final ExecutionEvent getExecutionEvent() {
    return event;
  }
  
}
