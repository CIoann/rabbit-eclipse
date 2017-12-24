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

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.team.IResourceTree;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IStartup;

import rabbit.tracking.internal.util.WorkbenchUtil;

/**
 * Starts up the plug-in to begin tracking.
 */
public class Startup implements IStartup {
	IWorkspaceRoot myroot;
	ArrayList<IProject> alProject;
	IProject[] prj;

  @Override
  public void earlyStartup() {
//	  myroot = ResourcesPlugin.getWorkspace().getRoot();
//	  
//	  alProject = new ArrayList<IProject>();
//	 try {
//		for (IResource s : myroot.members()) {
//
//			 System.out.println("ProjectName: "+s.getProject().members());
//			for( IResource f: s.getProject().members()) {
//				if (f.getName().equals("src")) {
//						System.out.println("NAME FILES: ");
//			
//			}
//		}
//	 }
//	  myroot.getProjects();
//	  //for (IProject )
//		IProject[] prjs = myroot.getProjects();
//		
//		IProject f = myroot.getProject("");
//		IProjectDescription des = f.getDescription();
//		//des.
//		
//				List<String> someList = new ArrayList<String>();
//				// add "monkey", "donkey", "skeleton key" to someList
//				for (String item : someList) {
//				    System.out.println(item);
//				}
//}
	

}
}
