package rabbit.tracking.internal.trackers;

import org.eclipse.core.commands.CommandEvent;
import org.eclipse.ui.IWorkbenchPart;

import rabbit.data.store.IStorer;

public class WorkbenchTracker extends AbstractPartTracker<CommandEvent>{

	@Override
	protected CommandEvent tryCreateEvent(long startMillis, long endMillis, IWorkbenchPart part) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IStorer<CommandEvent> createDataStorer() {
		// TODO Auto-generated method stub
		return null;
	}

}
