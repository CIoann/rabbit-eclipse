package rabbit.tracking.internal.trackers;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.team.IResourceTree;
import org.eclipse.core.runtime.CoreException;

import rabbit.data.handler.DataHandler;
import rabbit.data.store.IStorer;
import rabbit.data.store.model.FileUpdEvent;
import rabbit.tracking.internal.DeltaVis;

public class FileUpdTracker extends AbstractTracker<FileUpdEvent> {
	IWorkspace ws = ResourcesPlugin.getWorkspace();

 	DeltaVis dv;
	IResourceChangeListener fcl = new IResourceChangeListener() {
		
		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			   IResource res = event.getResource();
		         switch (event.getType()) {
		   
		            case IResourceChangeEvent.PRE_CLOSE:
		               break;
		            case IResourceChangeEvent.PRE_DELETE:
		               break;
		            case IResourceChangeEvent.POST_CHANGE:
		            //   System.out.println("Resources have changed.");
				         try {
				      			event.getDelta().accept(dv);
				      			addData(DeltaVis.getfue());
				      			//System.out.println("Fue" +DeltaVis.getfue().getFilePath());
				        	  //	System.out.println("Size FUEL " + DeltaVis.getFueList().size());
				      	//		for (int i=0;i<DeltaVis.getFueList().size(); i++) {
				      				//System.out.println("File: " + DeltaVis.getFueList().get(i).getFilePath());
				      	//		}
							} catch (CoreException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		               break;
		      
		         }
		       
		      }
			
	};
	public FileUpdTracker() {
		super();
		dv = new DeltaVis();

	}
	@Override
	protected IStorer<FileUpdEvent> createDataStorer() {
		return DataHandler.getStorer(FileUpdEvent.class);
	}

	@Override
	protected void doDisable() {
		// TODO Auto-generated method stub
		ws.removeResourceChangeListener(fcl);
		System.out.println("DISABLED FCL");
		
	
	}

	@Override
	protected void doEnable() {
		// TODO Auto-generated method stub
		  ws.addResourceChangeListener(fcl,IResourceChangeEvent.POST_CHANGE);	
		  System.out.println("ENABLED FCL");
	}

}
