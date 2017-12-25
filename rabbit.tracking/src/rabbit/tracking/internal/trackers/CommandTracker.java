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

import java.util.ArrayList;

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
    if (lastEvent != null && lastEvent.getCommand().getId().equals(commandId)) {

    	try {
			 cmd_category = lastEvent.getCommand().getCategory().getName();
			cmdHandle= defineHandle(cmd_category);
		} catch (NotDefinedException e1) {
			e1.printStackTrace();
		}
//    	try {
//			System.out.println("Test " + lastEvent.getCommand().getName() + "\n AppContext: " +
//								//	lastEvent.getApplicationContext().getClass().getName() +"\n Category: "+
//								lastEvent.getCommand().getCategory().getName());
//		} catch (NotDefinedException e) {
//			e.printStackTrace();
//		}

    	DateTime now = new DateTime();
     // lastEvent.getCommand();
      
      
  
      if( getActivePart()!=null) {
    		try {
    			if (getActivePart().contains(".java")) {
    				System.out.println("Test phase 1: CommandTracker, new command entered" + lastEvent.getCommand().getName() + "what is the type " + lastEvent.getCommand().getCategory().getName()+ "on file"+ getActivePart());

    				if (cmdHandle == CMD_ON_FILE) {
    					System.out.println("CMD_ON_FILE: File was Edited by commands: "+ CMD_EDIT + " " + CMD_TEXTEDIT + " " + CMD_SOURCE +" " +  CMD_UNCATEGORIZED);
    					addData(new CommandEvent(now, lastEvent,TrackingPlugin.test_sid,getActivePart()));
    				}else if (cmdHandle == CMD_NON_FILE){
    					
		    					System.out.println("CMD_NON_FILE: Must run list for refactor affected files" + lastEvent.getCommand().getName() + lastEvent.getCommand().getCategory().getName());

		    					System.out.println("SIZE OF FUE LIST : " + DeltaVis.getFueList().size() );
//		    					if (!DeltaVis.getFueList().isEmpty()) {
//		    						System.out.println(""+DeltaVis.getfue().getFilePath());
//		    					
//		    			       	  	for (int i=0;i<DeltaVis.getFueList().size(); i++) {
//		    			       	  		System.out.println("List : "+ DeltaVis.getFueList().get(i).getFileName());
//		    			       	  	} 
		    						System.out.println("Run the list and save for each file");
		    				//	}
    				
    				}else if (cmdHandle == CMD_IGNORE) {
    					System.out.println("CMD_IGNORE: This commands should not be used for process mining on files");
    					addData(new CommandEvent(now, lastEvent,TrackingPlugin.test_sid,CMD_INVALID));
    				//	DeltaVis.getFueList().clear();
    				}else if (cmdHandle == CMD_ON_PROJECT) {
    					 if (!DeltaVis.getFueList().isEmpty()) {
    						 System.out.println("Not Empty");
    						 System.out.println("Same Time" + DeltaVis.getfue().getFilePath() + getActivePart());
    						 addData(new CommandEvent(now, lastEvent,TrackingPlugin.test_sid,DeltaVis.getfue().getFilePath().toString()));
    					 }else {
    						 addData(new CommandEvent(now, lastEvent,TrackingPlugin.test_sid,CMD_INVALID));
    					 }
    					System.out.println("CMD_ON_PROJECT: run/debug commands checked");
    				}else {
    					System.out.println("CMD_ERROR: unregistered command");
    					addData(new CommandEvent(now, lastEvent,TrackingPlugin.test_sid,CMD_INVALID));
    				}
    			}else {
      		      addData(new CommandEvent(now, lastEvent,TrackingPlugin.test_sid));
    			}
			//	System.out.println("Test phase 1: CommandTracker, new command entered" + lastEvent.getCommand().getName() + "what is the type " + lastEvent.getCommand().getCategory().getName()+ "on file"+ getActivePart());
			} catch (NotDefinedException e) {
				e.printStackTrace();
			}

    	  
    	  
      }
		
      }
    
  }
  

  @Override
  public void preExecute(String commandId, ExecutionEvent event) {

		ArrayList<FileUpdEvent> fue = new ArrayList<FileUpdEvent>();
		if (!DeltaVis.getFueList().isEmpty()) {
			fue = DeltaVis.getFueListReduced();
			
			DateTime now = new DateTime(); 
			for (FileUpdEvent e : DeltaVis.getFueListReduced()) {
				
//				if (e.getFileActivity().equals(DeltaVis.FILE_REMOVED)) {

					addData(new CommandEvent(now, lastEvent, TrackingPlugin.test_sid, e.getFileName()));
		   	  		
//				}	System.out.println("List : "+ e.getFileName());   	 
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
