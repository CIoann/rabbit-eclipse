package rabbit.tracking.internal.trackers;

import rabbit.data.store.IStorer;
import rabbit.data.store.model.FileEvent;



public class FileCommandTracker extends AbstractTracker<FileEvent>{

	
	
	public FileCommandTracker() {
		    super();
		  }
	@Override
	protected IStorer<FileEvent> createDataStorer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doEnable() {
		// TODO Auto-generated method stub
		
	}

}
