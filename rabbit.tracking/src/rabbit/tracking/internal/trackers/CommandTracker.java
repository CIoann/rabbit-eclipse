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
package rabbit.tracking.internal.trackers;

import rabbit.data.handler.DataHandler;
import rabbit.data.store.IStorer;
import rabbit.data.store.model.CommandEvent;
import rabbit.data.store.model.FileUpdEvent;
import rabbit.tracking.internal.DeltaVis;
import rabbit.tracking.internal.TrackingPlugin;
import rabbit.tracking.internal.util.WorkbenchUtil;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.joda.time.DateTime;

/**
 * Tracks command executions.
 */
public class CommandTracker extends AbstractTracker<CommandEvent> implements
    IExecutionListener {

  /*
   * We only record commands that have been successfully executed, therefore we
   * only use postExecuteSuccess(String, Object) to record the event.
   * 
   * Note that preExecute(String, ExecutionEvent) will always be called when a
   * command is called to be execute, even if the command is non-executable at
   * that moment. For example, when there is nothing to undo in an editor, the
   * "Undo" menu is disabled, but if the user uses Ctrl+Z, the undo command will
   * still be called. Therefore we don't use preExecute(String, ExecutionEvent).
   */

  /**
   * The last recorded ExecutionEvent, to be updated every time
   * {@link #preExecute(String, ExecutionEvent)} is called. We need this because
   * {@link #postExecuteSuccess(String, Object)} does not have an
   * {@link ExecutionEvent} parameter. This variable is null from the beginning.
   */
  private ExecutionEvent lastEvent;
  private final String CMD_EDIT = "Edit";
  private final String CMD_TEXTEDIT = "Text Editing";
  private final String CMD_FILE = "File";
  private final String CMD_UNCATEGORIZED = "Uncategorized";
  
  private final String CMD_NAVIGATE = "Navigate";
  private final String CMD_SOURCE = "Source";
  
  private final String CMD_PROJECT = "Project";
  private final String CMD_HELP = "Help";
  private final String CMD_VIEW = "View";

  private final String CMD_SEARCH = "Search";
  private final String CMD_REFACTOR = "Refactor - Java";
  private final String CMD_WINDOW = "Window";
  
  private final String CMD_RUN = "Run/Debug";
  private final String CMD_PERSPECTIVE = "Perspectives";
 
  private final String CMD_INVALID = "invalid";
  private final int CMD_ON_FILE = 0;
  private final int CMD_IGNORE = -1;
  private final int CMD_ON_PROJECT = 1;
  private final int CMD_NON_FILE = 2;
  private final int CMD_ERROR = -2;
  
  public ExecutionEvent getLastEvent() {
	return lastEvent;
}

public void setLastEvent(ExecutionEvent lastEvent) {
	this.lastEvent = lastEvent;
}

/** Constructor. */
  public CommandTracker() {
    super();
    lastEvent = null;
  }

  @Override
  public void notHandled(String commandId, NotHandledException exception) {
  }

  @Override
  public void postExecuteFailure(String commandId, ExecutionException e) {
  }

  @Override
  public void postExecuteSuccess(String commandId, Object returnValue) {
	  int cmdHandle = CMD_ERROR;
	  String cmd_category = CMD_INVALID;
	  DateTime now = new DateTime();
	  String dsfile = CMD_INVALID;
    
	  if (lastEvent != null && lastEvent.getCommand().getId().equals(commandId)) {
		  ExecutionEvent lee = lastEvent;
		  Command cmd = lee.getCommand();	
				
		  try {
				cmd_category = cmd.getCategory().getName();
				cmdHandle= defineHandle(cmd_category);
			
			} catch (NotDefinedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			if( getActivePart()!=null) {
	    			if (getActivePart().contains(".java")) {
	    				try {
	    						System.out.println("COMMAND-TRACKER :" + lastEvent.getCommand().getName() + "what is the type " + lastEvent.getCommand().getCategory().getName()+ "on file "+ getActivePart());
						//	System.out.println("Test phase 1: CommandTracker" +cmd.getName());
						} catch (NotDefinedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    			if (cmdHandle == CMD_ON_FILE) {
		  		    		dsfile = getActivePart();
		    				
		    			}else if (cmdHandle == CMD_IGNORE) {
		    				dsfile= getActivePart();
		    				
		    			}else if(cmdHandle == CMD_ON_PROJECT) {
		    				//if this is true for any reason then we need to use getactiveproject
		    				dsfile = DeltaVis.getfue().getFileName();
	        				 
		    				if (!DeltaVis.getFueList().isEmpty()) {
		    					 System.out.println("COMMAND-TRACKER: CMD_ON_PROJECT");
		    				 }
		    				//dsfile= getActiveProject();
		    				
		    			}else if(cmdHandle == CMD_NON_FILE) {
		    				
		    				if (!(DeltaVis.getfue() == null)) {
			    				dsfile = DeltaVis.getfue().getFileName();	
		    				}
		    					//dsfile = getActivePart();

		    		 //  	  	DeltaVis.getFueList().clear();
		    				
		    			}else{
		    				dsfile = CMD_INVALID;
		    			}
		    			
		    		}
			}
			addData(new CommandEvent(now, lee,TrackingPlugin.test_sid,dsfile));
			
    }
  }
  

	
/* 
  
    
//    			}else if (cmdHandle == CMD_NON_FILE){
		    			//		System.out.println("CMD_NON_FILE: Must run list for refactor affected files" + lastEvent.getCommand().getName() + lastEvent.getCommand().getCategory().getName());
		    		//			System.out.println("SIZE OF FUE LIST : " + DeltaVis.getFueList().size() );
    					
    					System.out.println("what happens if its not a .java file");
		    					addData(new CommandEvent(now, lastEvent, TrackingPlugin.test_sid,DeltaVis.getfue().getFileName()));
    				
    		*/		
  

  @Override
  public void preExecute(String commandId, ExecutionEvent event) {

	// lastEvent = event;	
		if (!DeltaVis.getFueList().isEmpty()) {
			DateTime now = new DateTime(); 
			System.out.println("The files that have been refactored");
			for (FileUpdEvent e : DeltaVis.getFueListReduced()) {
//				
					System.out.println("The files: " + e.getFileName());
					if (lastEvent != null) {
						addData(new CommandEvent(now, lastEvent, TrackingPlugin.test_sid, e.getFileName()));
					}
			}
	   	  	DeltaVis.getFueList().clear();
	   	  	}
		  lastEvent = event;	
  }

  @Override
  protected IStorer<CommandEvent> createDataStorer() {
    return DataHandler.getStorer(CommandEvent.class);
  }

  @Override
  protected void doDisable() {
    getCommandService().removeExecutionListener(this);
  }

  @Override
  protected void doEnable() {
    getCommandService().addExecutionListener(this);
  }

  private String getActivePart() {
	  if(WorkbenchUtil.getActivePart().getSite().getPage().getActiveEditor()==null) {
		  return null;
	  }else
		  return WorkbenchUtil.getActivePart().getSite().getPage().getActiveEditor().getTitle();
	  
  }
  
  
  private String getActiveProject(){
	  return "notdone";
  }
  private ICommandService getCommandService() {
    return (ICommandService) PlatformUI.getWorkbench().getService(
        ICommandService.class);
  }

  private boolean isSameChangeActive(String active, String changed) {
	  if(active.equals(changed)) {
		  return true;
	  }
	  return false;
  }
  

  private int defineHandle(String evaluate) {
	if (evaluate.equals(CMD_HELP) || (evaluate.equals(CMD_WINDOW)) || evaluate.equals(CMD_VIEW)) {
		return CMD_IGNORE;
	}
	else if (evaluate.equals(CMD_TEXTEDIT) || (evaluate.equals(CMD_EDIT) ) ||  (evaluate.equals(CMD_UNCATEGORIZED) || evaluate.equals(CMD_SOURCE))) {
		return CMD_ON_FILE;
		
	}
	else if( (evaluate.equals(CMD_PERSPECTIVE)) || (evaluate.equals(CMD_RUN)) || (evaluate.equals(CMD_PROJECT)) ) {
		return CMD_ON_PROJECT;
		
	}else if ((evaluate.equals(CMD_REFACTOR)) ||(evaluate.equals(CMD_FILE)) || evaluate.equals(CMD_NAVIGATE) || (evaluate.equals(CMD_SEARCH))) {
		return CMD_NON_FILE;
	}else {
		return CMD_ERROR;
	}
  }
  
}
