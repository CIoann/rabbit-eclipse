package rabbit.data.internal.access.model;
import rabbit.data.access.model.IFmData;
import rabbit.data.access.model.IKey;
import rabbit.data.access.model.WorkspaceStorage;

import static com.google.common.base.Preconditions.checkNotNull;

import org.eclipse.core.resources.IFile;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.util.Map;

public class FmData implements IFmData{

	private final Map<IKey<? extends Object>, Object> data ; 
	
	   public FmData(int sid,
		      LocalDate date,Timestamp tsStart,
		      Timestamp tsEnd, WorkspaceStorage workspace,
		      Duration duration, IFile file, String mname,String mtype, String msign) {
		    
		    data = new KeyMapBuilder()
		    	.put(SID,      checkNotNull(sid,      "sid"))
		    	.put(DATE,      checkNotNull(date, "date"))
		        .put(WORKSPACE, checkNotNull(workspace, "workspace"))
		        .put(DURATION,  checkNotNull(duration, "duration"))
		        .put(TIMESTART, checkNotNull(tsStart, "timestart"))
		        .put(TIMEND, checkNotNull(tsEnd, "timend"))
		        .put(FILE,      checkNotNull(file, "file"))
		        .put(FM_MNAME, checkNotNull(mname, "mname"))
		    		.put(FM_MTYPE, checkNotNull(mtype,"mtype"))
		    		.put(FM_MSIGN, checkNotNull(msign,"msign"))
		    		
		    		.build();
		  }
	

	  @SuppressWarnings("unchecked")
	@Override
	public <T> T get(IKey<T> key) {
		return (T) data.get(key);
	}
	

}
