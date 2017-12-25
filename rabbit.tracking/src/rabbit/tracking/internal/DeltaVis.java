package rabbit.tracking.internal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.joda.time.Interval;

import rabbit.data.store.model.FileUpdEvent;

public class DeltaVis  implements IResourceDeltaVisitor{

	public static final String FILE_CHANGED = "changed";
	public static final String FILE_ADDED = "added";
	public static final String FILE_REMOVED = "removed";
	
	private static ArrayList<FileUpdEvent> updFiles;
	private static FileUpdEvent fue; 
	private static boolean listupd ;
	public DeltaVis() {
		super();
		updFiles = new ArrayList<FileUpdEvent>();
		listupd = false;
	}
	
	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		// TODO Auto-generated method stub
	     IResource res = delta.getResource();
	     Timestamp ts = new Timestamp(System.currentTimeMillis());
         String str = res.getFullPath().lastSegment();
         IPath ipjf = res.getFullPath();
         String filename = res.getFullPath().lastSegment();
	 	listupd = true;
	       switch (delta.getKind()) {
	          case IResourceDelta.ADDED:
	        	  if (str!=null) {
	            	 	if(str.contains("java")) {
	            	 	//	System.out.println("Java File Added" +res.getFullPath());
	            	 		 fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,TrackingPlugin.test_sid,FILE_ADDED);
	            	 		 updFiles.add(fue);
	       	        	 	}
	             }
	             break;
	          case IResourceDelta.REMOVED:
	             if (str!=null) {
	            	 	if(str.contains("java")) {
	            	 	//	System.out.println("Java File Removed" +res.getFullPath());
	            	 		 fue = new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,TrackingPlugin.test_sid,FILE_REMOVED);
	            	 		 updFiles.add(fue);
		       	        	 	
	            	 	}
	             }
	             break;
	          case IResourceDelta.CHANGED:
	             if (str!=null) {
	            	 	if(str.contains("java")) {
	            	 	//	System.out.println("Java File Changed" +res.getFullPath());
	            	 		 fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,TrackingPlugin.test_sid,FILE_CHANGED);
	            	 		 updFiles.add(fue);
		       	        	 	
	            	    	 	}
	             }
	               break;
	       
	         	}
		return true;
	}
	
	public static ArrayList<FileUpdEvent> getFueListReduced(){
		int size = updFiles.size();
		
	    for (int i = 0; i < size - 1; i++) {
	        for (int j = i + 1; j < size; j++) {
	            if (!((updFiles.get(j).getFileName()).equals(updFiles.get(i).getFileName())))
	                continue;
	            updFiles.remove(j);
	            j--;
	            size--;
	        } 
	    } 
		return updFiles;
	}

	public static FileUpdEvent getfue(){
		return fue;
	}
	public static ArrayList<FileUpdEvent> getFueList() {
		return updFiles;
	}
	
	public static boolean  isListUpdate() {
		return listupd;
	}


}