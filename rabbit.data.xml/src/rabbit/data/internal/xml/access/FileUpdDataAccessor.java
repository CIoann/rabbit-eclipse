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
package rabbit.data.internal.xml.access;

import rabbit.data.access.model.IFileUpdData;
import rabbit.data.access.model.WorkspaceStorage;
import rabbit.data.internal.access.model.FileUpdData;
import rabbit.data.internal.xml.IDataStore;
import rabbit.data.internal.xml.StoreNames;
import rabbit.data.internal.xml.schema.events.EventListType;
import rabbit.data.internal.xml.schema.events.FileUpdEventListType;
import rabbit.data.internal.xml.schema.events.FileUpdEventType;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Accesses file event data.
 */
public class FileUpdDataAccessor extends
    AbstractAccessor<IFileUpdData, FileUpdEventType, FileUpdEventListType> {

  /**
   * Constructor.
   * 
   * @param store The data store to get the data from.
   * @throws NullPointerException If argument is null.
   */
  @Inject
  FileUpdDataAccessor(@Named(StoreNames.FILE_UPD_STORE) IDataStore store) {
    super(store);
  }

  @Override
  protected IFileUpdData createDataNode(LocalDate date, WorkspaceStorage ws,
      FileUpdEventType type) throws Exception {
	  //CALLED WHEN THE FIRST TIME A FILE is initiated? WAS ADDRESSED
	//  System.out.println("Test 3: FileDataAccessor: Called on every file opened in editor");
	  int sid = -1;
	  Timestamp ts = new Timestamp(System.currentTimeMillis() % 1000);
    return new FileUpdData(sid,date, ts,ts,ws, new Duration(type.getDuration()),
        workspaceRoot().getFile(new Path(type.getFilePath())),"inactive","unknown","unknown","unknown","unknown");
  }

  @Override
  protected Collection<FileUpdEventType> getElements(FileUpdEventListType list) {
    return list.getFileUpdEvent();
  }

  @Override
  protected Collection<FileUpdEventListType> getCategories(EventListType doc) {
    return doc.getFileUpdEvents();
  }

  /**
   * @return The root of the current workspace.
   */
  private IWorkspaceRoot workspaceRoot() {
    return ResourcesPlugin.getWorkspace().getRoot();
  }
}
