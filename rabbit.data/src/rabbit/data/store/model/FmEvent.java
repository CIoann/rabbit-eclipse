package rabbit.data.store.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.URI;
import java.sql.Timestamp;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.joda.time.Interval;

public class FmEvent extends ContinuousEvent {

	 
	private final IPath filePath;
	  
		private final String filename;
		private final String methodname;
		private final String methodsign;
		private final String methodtype;
		private final int sid;
	  	
		public FmEvent(Timestamp tsStart, Timestamp tsEnd, Interval interval, int sid, String filename,
					String methodname, String methodtype, String methodsign, 
				IPath filePath) {
			super(tsStart, tsEnd, interval);
			this.sid =sid;
			this.filename = filename;
			this.filePath = checkNotNull(filePath);
			this.methodname = methodname;
			this.methodsign = methodsign;
			this.methodtype = methodtype;
					
			// TODO Auto-generated constructor stub
		}

		public FmEvent(Timestamp ts, Interval interval) {
			super(ts,ts,interval);
			this.sid =-1;
			this.filename = "";
			this.filePath =null;
			this.methodname = "";
			this.methodsign = "";
			this.methodtype = "";
		}
		public IPath getFilePath() {
			return filePath;
		}

		public String getFilename() {
			return filename;
		}

		public String getMethodname() {
			return methodname;
		}

		public String getMethodsign() {
			return methodsign;
		}

		public String getMethodtype() {
			return methodtype;
		}

		public int getSid() {
			return sid;
		}	
		
}