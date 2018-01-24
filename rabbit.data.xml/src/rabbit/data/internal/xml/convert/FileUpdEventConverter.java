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

import rabbit.data.internal.xml.schema.events.FileUpdEventType;
import rabbit.data.store.model.FileUpdEvent;

/**
 * Converts from {@link FileUpdEvent} to {@link FileUpdEventType}.
 */
public class FileUpdEventConverter extends
    AbstractConverter<FileUpdEvent, FileUpdEventType> {

  public FileUpdEventConverter() {
  }
  
  @Override
  protected FileUpdEventType doConvert(FileUpdEvent element) {
    FileUpdEventType type = new FileUpdEventType();
    type.setDuration(element.getInterval().toDurationMillis());
    type.setFilePath(element.getFilePath().toString());
    type.setTsStart(element.getStart().toString());
    type.setTsEnd(element.getEnd().toString());
    type.setSid(String.valueOf(element.getSid()));
    type.setName(element.getFilePath().lastSegment());
    type.setAct(element.getFileActivity());
    type.setFtype(element.getFiletype().toString());
    type.setMethodname(element.getMethodname().toString());
    type.setMethodtype(element.getMethodtype().toString());
    type.setMethodsign(element.getMethodsign().toString());
    

    type.setCategory("");
  //  System.out.println("The path NAME" +element.getFilePath().lastSegment());
//gives out where this class is implemented
    //    System.out.println("The name of file event "+ element.getClass().getName().toString());
    return type;
  }
  
}
