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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.joda.time.Interval;

import java.net.URI;
import java.sql.Timestamp;

/**
 * Represents a file event.
 */
public class FileUpdEvent extends ContinuousEvent {

  private final IPath filePath;
  
	private final String filename;
	private final int sid;
  private final String fileActivity;

	private final String filetype;
	private final String methodname;
	private final String methodsign;
	private final String methodtype;
  public FileUpdEvent(Interval interval, Timestamp tsStart, Timestamp tsEnd,IPath filePath, String filename, 
		  int sid, String act,
		  String ft, String mn, String ms, String mt) {
	    super(tsStart,tsEnd,interval);
	    this.sid=sid;
	    this.filename = filename;
	    this.filePath = checkNotNull(filePath);
	    this.fileActivity = checkNotNull(act);
	    this.filetype = ft;
	    this.methodname = mn;
	    this.methodsign = ms;
	    this.methodtype = mt;
	  }

  public String getFilename() {
	return filename;
}

public String getFiletype() {
	return filetype;
}

public String getMethodname() {
	return methodname;
}

public String getMethodsign() {
	return methodsign;
}

public String getMethodtype() {
	return methodtype;
}

public String getFileActivity() {
	  return this.fileActivity;
  }
  public int getSid() {
	return sid;
}

/**
   * Gets the file path.
   * 
   * @return The file path, never null.
   */
  public final String getFileName() {
	  return filename;
  }
 public final IPath getFilePath() {
    return filePath;
  }
}
