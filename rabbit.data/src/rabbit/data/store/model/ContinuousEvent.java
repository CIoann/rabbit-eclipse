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

import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;

/**
 * An event that has a duration.
 */
public class ContinuousEvent extends DiscreteEvent {

  private final Interval interval;
  private final Timestamp tsStart,tsEnd;

  /**
   * Constructor.
   * 
   * @param interval The interval.
   * @throws NullPointerException If parameter is null.
   */
  public ContinuousEvent(Interval interval) {
    super(checkNotNull(interval).getStart());
    this.interval = interval;
    this.tsStart= new Timestamp(0);
    this.tsEnd= new Timestamp(0);
  }
  
  //! Test 3: Adding Timestamp
  /**
   * attempt to add start,end times
   */
  public ContinuousEvent(Timestamp tsStart, Timestamp tsEnd, Interval interval) {
	    super(checkNotNull(interval).getStart());
	    this.interval = interval;
	    this.tsStart= tsStart;
	    this.tsEnd=tsEnd;
	  }


/**
   * Gets the interval of this event.
   * 
   * @return The interval.
   */
  public final Interval getInterval() {
    return interval;
  }
  public final Timestamp getStart() {
	return tsStart;
}

public final Timestamp getEnd() {
	return tsEnd;
}

}
