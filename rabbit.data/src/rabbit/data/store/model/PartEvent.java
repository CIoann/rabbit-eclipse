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

import java.sql.Timestamp;

import org.eclipse.ui.IWorkbenchPart;
import org.joda.time.Interval;

/**
 * Represents a workbench part event.
 */
public class PartEvent extends ContinuousEvent {

  private final IWorkbenchPart workbenchPart;
  private final int sid;
  /**
   * Constructs a new event.
   * 
   * @param interval The time interval.
   * @param part The workbench part.
   * @throws NullPointerException If any of the arguments are null.
   */
  public PartEvent(Interval interval, Timestamp tsStart, Timestamp tsEnd, IWorkbenchPart part, int sid) {
    super(tsStart, tsEnd,interval);
    this.workbenchPart = checkNotNull(part);
    this.sid=sid;
  }

  /**
   * Gets the workbench part.
   * 
   * @return The workbench part.
   */
  public final IWorkbenchPart getWorkbenchPart() {
    return workbenchPart;
  }

public int getSid() {
	return sid;
}


}
