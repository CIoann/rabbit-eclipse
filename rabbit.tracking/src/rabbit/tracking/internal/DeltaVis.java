package rabbit.tracking.internal;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.joda.time.Interval;

import rabbit.data.store.model.FileUpdEvent;

public class DeltaVis  implements IResourceDeltaVisitor{

	private static ArrayList<FileUpdEvent> updFiles;
	private static FileUpdEvent fue; 
	public DeltaVis() {
		super();
		updFiles = new ArrayList<FileUpdEvent>();
	}
	
	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		// TODO Auto-generated method stub
	     IResource res = delta.getResource();
	     Timestamp ts = new Timestamp(System.currentTimeMillis());
         String str = res.getFullPath().lastSegment();
         IPath ipjf = res.getFullPath();
	 		
	       switch (delta.getKind()) {
	          case IResourceDelta.ADDED:
	        	  if (str!=null) {
	            	 	if(str.contains("java")) {
	            	 	//	System.out.println("Java File Added" +res.getFullPath());
	            	 		 fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,TrackingPlugin.test_sid,"added");
	            	 		 updFiles.add(	fue);
	       	        	 	}
	             }
	             break;
	          case IResourceDelta.REMOVED:
	             if (str!=null) {
	            	 	if(str.contains("java")) {
	            	 	//	System.out.println("Java File Removed" +res.getFullPath());
	            	 		 fue = new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,TrackingPlugin.test_sid,"removed");
	            	 		 updFiles.add(fue);
		       	        	 	
	            	 	}
	             }
	             break;
	          case IResourceDelta.CHANGED:
	             if (str!=null) {
	            	 	if(str.contains("java")) {
	            	 	//	System.out.println("Java File Changed" +res.getFullPath());
	            	 		 fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,TrackingPlugin.test_sid,"changed");
	            	 		 updFiles.add(fue);
		       	        	 	
	            	    	 	}
	             }
	               break;
	       
	         	}
		return true;
	}

	public static FileUpdEvent getfue(){
		return fue;
	}
	public static ArrayList<FileUpdEvent> getFueList() {
		return updFiles;
	}


}