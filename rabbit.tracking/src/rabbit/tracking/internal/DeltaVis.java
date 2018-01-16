package rabbit.tracking.internal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.joda.time.Interval;

import rabbit.data.store.model.FileUpdEvent;

public class DeltaVis  implements IResourceDeltaVisitor{

	public static final String FILE_CHANGED = "changed";
	public static final String FILE_ADDED = "added";
	public static final String FILE_REMOVED = "removed";
 
	private static ArrayList<FileUpdEvent> updFiles;
	private static FileUpdEvent fue; 
	private static boolean listupd ;
	
	private JMHandle jmHandle;
	IResource res ;
	public DeltaVis() {
		super();
		updFiles = new ArrayList<FileUpdEvent>();
		listupd = false;
		jmHandle = new JMHandle();
	}
	
	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		// TODO Auto-generated method stub
	     res= delta.getResource();
	     Timestamp ts = new Timestamp(System.currentTimeMillis());
         String str = res.getFullPath().lastSegment();
         IPath ipjf = res.getFullPath();
         String filename = res.getFullPath().toString();

       
	 	listupd = true;
	       switch (delta.getKind()) {
	          case IResourceDelta.ADDED:
	        	  	configureResource(str, res, FILE_ADDED, ts, ipjf, filename);
	        	  	break;
	          case IResourceDelta.REMOVED:   

		       //    	 System.out.println("remove" + filename);
	        	  	configureResource(str, res, FILE_REMOVED, ts, ipjf, filename);
	             break;
	          case IResourceDelta.CHANGED:
	             if (str!=null) {
	                 if(str.contains("java")) {
	            	 		System.out.println("Java File Changed" +res.getFullPath());
	            	 		 fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,TrackingPlugin.test_sid,FILE_CHANGED);
	            	 		 updFiles.add(fue);
		       	        	 	
	            	    	 	}
	             }
	               break;
	       
	         	}
		return true;
	}
	
	
	private void configureResource(String lastSegment, IResource rest, String act, Timestamp ts, IPath ipjf, String filename) {
			
		if(isValidCreatedFile(filename)){
				 
		//	 System.out.println("add/remove" + filename);
		     jmHandle.checkClass(filename)	;	       
			switch (rest.getType()) {
	        case IResource.FILE:
       
	       	 	if (lastSegment!=null) {
		            	 	if(lastSegment.contains("java")) {
		            	 		 fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,TrackingPlugin.test_sid,act);
		            	 		 updFiles.add(fue);
		       	        	 	}
	       	 	}
	       	 	break;
	        case IResource.FOLDER:
	        		if (lastSegment!=null) {
	        		//	if (!(resPath.contains(".settings")) && (!(resPath.contains("bin")))){
	       	 		 fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,TrackingPlugin.test_sid,act);
	       	 		 updFiles.add(fue);
       	 	//	}
	        		}
	        		break;
	        case IResource.PROJECT:
	      	  			if (lastSegment!=null) {
	      	  				fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,TrackingPlugin.test_sid,act);
		             	 	updFiles.add(fue);
	      	  			}
	      	  	break;
	        case IResource.ROOT:
		
		    	 		System.out.println("it was a root");
		       	 	break;
		        }
		}else {
			
		}
	
	}
	
	private boolean isValidCreatedFile(String resPath) {
		if (		   (resPath.contains(".class"))
				|| (resPath.contains("/bin/")) 
				|| (resPath.contains("/bin"))
				|| (resPath.contains(".settings")) 
				|| (resPath.contains("/.settings/")) 
				|| (resPath.contains(".project"))
				){
						return false;
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