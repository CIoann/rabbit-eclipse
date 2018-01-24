package rabbit.data.internal.xml.access;

import java.security.Timestamp;
import java.util.Collection;

import org.eclipse.core.runtime.Path;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.joda.time.Duration;
import java.util.Collection;
import rabbit.data.access.model.IFileUpdData;
import rabbit.data.access.model.WorkspaceStorage;
import rabbit.data.internal.access.model.FileUpdData;
import rabbit.data.internal.xml.IDataStore;
import rabbit.data.internal.xml.StoreNames;
import rabbit.data.internal.xml.schema.events.EventListType;
import rabbit.data.access.model.IFmData;
import rabbit.data.internal.access.model.FmData;
import rabbit.data.internal.xml.schema.events.FmEventListType;
import rabbit.data.internal.xml.schema.events.FmEventType;
public class FMDataAccessor extends AbstractAccessor<IFmData, FmEventType, FmEventListType>{
	protected FMDataAccessor(@Named(StoreNames.FM_STORE) IDataStore store) {
		super(store);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected IFmData createDataNode(LocalDate date, WorkspaceStorage ws, FmEventType type) throws Exception {
		int sid = -1;
//		 Timestamp ts = new Timestamp( System.currentTimeMillis() );
//				 System.currentTimeMillis() % 1000);
		  
		return null;// new FmData(sid, date, ts, ts, ws, new Duration(type.getDuration()
				 //workspaceRoot().getFile(new Path(type.getFilePath())),"empty", "empty", "empty");
	}

	@Override
	protected Collection<FmEventListType> getCategories(EventListType doc) {
		// TODO Auto-generated method stub
		return doc.getFmEvents();
	}

	@Override
	protected Collection<FmEventType> getElements(FmEventListType list) {
		// TODO Auto-generated method stub
		return list.getFmEvent();
	}
	private IWorkspaceRoot workspaceRoot() {
	    return ResourcesPlugin.getWorkspace().getRoot();
	  }
	

	
}
