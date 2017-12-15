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
package rabbit.tracking.internal.startup;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IStartup;

/**
 * Starts up the plug-in to begin tracking.
 */
public class Startup implements IStartup {
	IWorkspace ws=  ResourcesPlugin.getWorkspace();
	IResourceChangeListener fcl = new IResourceChangeListener() {
		
		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			   IResource res = event.getResource();
		         switch (event.getType()) {
		            case IResourceChangeEvent.PRE_CLOSE:
//		               System.out.print("Project ");
//		               System.out.print(res.getFullPath());
//		               System.out.println(" is about to close.");
		               break;
		            case IResourceChangeEvent.PRE_DELETE:
//		               System.out.print("Project ");
//		               System.out.print(res.getFullPath());
//		               System.out.println(" is about to be deleted.");
		               break;
		            case IResourceChangeEvent.POST_CHANGE:
		        //       System.out.println("Resources have changed.");
				         try {
								event.getDelta().accept(new DeltaVis());
							} catch (CoreException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		               break;
		      
		         }
		       
		      }
			
	};

  @Override
  public void earlyStartup() {
	  ws.addResourceChangeListener(fcl,IResourceChangeEvent.POST_CHANGE);	
  }

}
