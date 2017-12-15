
package rabbit.tracking.internal.trackers;

import rabbit.data.handler.DataHandler;
import rabbit.data.store.IStorer;
import rabbit.data.store.model.FileEvent;
import rabbit.tracking.internal.TrackingPlugin;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.joda.time.Interval;

import java.net.URI;
import java.sql.Timestamp;

/**
 * Tracks time spent on files.
 */
public class FCTracker extends AbstractPartTracker<FileEvent> implements  IExecutionListener{
/*	IWorkspace ws=  ResourcesPlugin.getWorkspace();
	IResourceChangeListener fcl;
	*/
	private IFile file;
	private String filename=null;
	private ExecutionEvent lastEvent;
	
	public ExecutionEvent getLastEvent() {
		return lastEvent;
	}
	
	
  /**
   * Constructor.
   */
  public FCTracker() {
    super();
    this.lastEvent = null;
  }

  @Override
  protected IStorer<FileEvent> createDataStorer() {
    return DataHandler.getStorer(FileEvent.class);
  }

  @Override
  protected FileEvent tryCreateEvent(long start, long end, IWorkbenchPart part) {
	  filename = part.getSite().getPage().getActiveEditor().getTitle(); //get active editor
	  System.out.println("Active Editor " + filename);
	  
	  return null;
  }

@Override
public void notHandled(String commandId, NotHandledException exception) {
	// TODO Auto-generated method stub
	
}

@Override
public void postExecuteFailure(String commandId, ExecutionException exception) {
	// TODO Auto-generated method stub
	
}

@Override
public void postExecuteSuccess(String commandId, Object returnValue) {
	// TODO Auto-generated method stub
	 if (lastEvent != null && lastEvent.getCommand().getId().equals(commandId)) {
		 
	 }
}

@Override
public void preExecute(String commandId, ExecutionEvent event) {
	// TODO Auto-generated method stub
	
}


private void createFileChangeListener() {
	/*System.out.println("Creating File Listener for "+ getFile().getName());
	this.fcl = new IResourceChangeListener()
    {
        public void resourceChanged( final IResourceChangeEvent event )
        {
            handleFileChangedEvent( event );
        }
    };
   
    ResourcesPlugin.getWorkspace().addResourceChangeListener( this.fcl, IResourceChangeEvent.POST_CHANGE );
*/
}

protected final void handleFileChangedEvent(final IResourceChangeEvent event) {
	/* final IResourceDelta delta = event.getDelta();
	    System.out.print("Handle changes");
     if( delta != null && getFile() != null )
     {
    	    System.out.print("file not null");
IResource res = delta.getResource();



            System.out.println("CHANGED" + getFile().getName() + res.getFullPath().lastSegment());
         //   System.out.println(" last segment" + delta.getFullPath());
            //if (getFile().getName().equals(getfile))
         final IResourceDelta localDelta = delta.findMember( getFile().getFullPath() );

         if( localDelta != null )
         {
           System.out.println("CHANGED" + file.getName());
           
         }
     }*/
}


}
