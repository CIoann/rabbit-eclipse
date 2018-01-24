package rabbit.data.internal.xml.merge;

import rabbit.data.internal.xml.schema.events.FmEventType;
import rabbit.data.store.model.FmEvent;;

public class FmEventTypeMerger extends AbstractMerger<FmEventType> {

	@Override
	protected boolean doIsMergeable(FmEventType t1, FmEventType t2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected FmEventType doMerge(FmEventType t1, FmEventType t2) {
		// TODO Auto-generated method stub
		return t1;
	}

	
}
