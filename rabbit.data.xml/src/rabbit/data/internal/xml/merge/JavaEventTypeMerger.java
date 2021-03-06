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

import rabbit.data.internal.xml.schema.events.JavaEventType;

/**
 * Merges {@link JavaEventType}s.
 */
public class JavaEventTypeMerger extends AbstractMerger<JavaEventType> {
  
  public JavaEventTypeMerger() {
  }

  @Override
  protected boolean doIsMergeable(JavaEventType t1, JavaEventType t2) {
//	  System.out.println("Test 10: JavaEventTypeMerger - Keep Multiple Entries");
		 return false;//return t1.getHandleIdentifier().equals(t2.getHandleIdentifier());
  }

  @Override
  protected JavaEventType doMerge(JavaEventType t1, JavaEventType t2) {
    JavaEventType result = new JavaEventType();
    result.setHandleIdentifier(t1.getHandleIdentifier());
    result.setDuration(t1.getDuration() + t2.getDuration());
    result.setTsEnd(t2.getTsEnd());
    result.setTsStart(t1.getTsStart());
    result.setFilename(t2.getFilename());
    result.setName(t2.getName());
    result.setParent(t2.getParent());
    result.setType(t2.getType());
    result.setSid(t1.getSid());
   // System.out.println("Test 7: JavaEvent Merger");
  //  System.out.println("Test 7: Setting start and end\n");// +" :Start: " +result.getTsStart().toString() +" :End: " +result.getTsEnd().toString());

    
    return result;
  }

}
