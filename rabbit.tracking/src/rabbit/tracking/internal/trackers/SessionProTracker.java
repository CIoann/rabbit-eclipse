package rabbit.tracking.internal.trackers;

import java.util.Observer;

import rabbit.data.handler.DataHandler;
import rabbit.data.store.IStorer;
import rabbit.data.store.model.SessionEvent;
import rabbit.tracking.internal.TrackingPlugin;
import rabbit.tracking.internal.util.Recorder;

public class SessionProTracker extends AbstractTracker<SessionEvent> {
private final Recorder<Object> recorder = new Recorder<Object>();
//private final Observer observer = new Observer() {
//	
//}
	@Override
	protected IStorer<SessionEvent> createDataStorer() {
		// TODO Auto-generated method stub
		return DataHandler.getStorer(SessionEvent.class);
	}

	@Override
	protected void doDisable() {
		// TODO Auto-generated method stub
		//TrackingPlugin.getDefault().getIdleDetector().deleteObserver(observer);
		
		
	}

	@Override
	protected void doEnable() {
		// TODO Auto-generated method stub
		
	}

}
