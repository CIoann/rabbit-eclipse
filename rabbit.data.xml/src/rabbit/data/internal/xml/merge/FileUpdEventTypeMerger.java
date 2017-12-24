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

import rabbit.data.internal.xml.schema.events.FileUpdEventType;

/**
 * Merger for {@link FileEventType}.
 */
public class FileUpdEventTypeMerger extends AbstractMerger<FileUpdEventType> {
  
  public FileUpdEventTypeMerger() {
  }

  @Override
  protected FileUpdEventType doMerge(FileUpdEventType t1, FileUpdEventType t2) {
	 FileUpdEventType result = new FileUpdEventType();
    result.setFilePath(t1.getFilePath());
    result.setDuration(t1.getDuration() + t2.getDuration());
    result.setTsEnd(t2.getTsEnd());
    result.setTsStart(t1.getTsStart());
    result.setAct(t2.getAct());
   // System.out.println("Test 3: Getting Duration for file");
   // System.out.println("Test 3: Setting start and end\n");// +" :Start: " +result.getTsStart().toString() +" :End: " +result.getTsEnd().toString());
    return result;
  }

  @Override
  public boolean doIsMergeable(FileUpdEventType t1, FileUpdEventType t2) {
	  //Even if they can be merged return false because we don't want them to be merged
	//  System.out.println("Test 10: FileEventTypeMerger - Keep Multiple Entries");
	 return false;
	  //return (t1.getFilePath() != null) && (t1.getFilePath().equals(t2.getFilePath()));
  }

}
