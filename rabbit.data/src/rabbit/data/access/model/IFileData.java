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
package rabbit.data.access.model;

import java.sql.Timestamp;

import org.eclipse.core.resources.IFile;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

/**
 * Contains information about time spent on a file.
 * <p>
 * Values represented by the keys defined in this interface are not null.
 * </p>
 * 
 * @noimplement
 */
public interface IFileData extends IData {
	/**
	   * Key for the date.
	   */
	  static final IKey<Integer> SID = Keys.SID;
  /**
   * Key for the date.
   */
  static final IKey<LocalDate> DATE = Keys.DATE;

  /**
   * Key for the workspace.
   */
  static final IKey<WorkspaceStorage> WORKSPACE = Keys.WORKSPACE;

  /**
   * Key for the duration.
   */
  static final IKey<Duration> DURATION = Keys.DURATION;
  
  /**
   * Key for the tsStart.
   */
  static final IKey<Timestamp> TIMESTART = Keys.TIMESTART;
  /**
   * Key for the tsEnd.
   */
  static final IKey<Timestamp> TIMEND = Keys.TIMEND;

  /**
   * Key for the file.
   */
  static final IKey<IFile> FILE = Keys.FILE;
}
