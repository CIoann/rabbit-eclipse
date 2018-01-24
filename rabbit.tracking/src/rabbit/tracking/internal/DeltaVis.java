package rabbit.tracking.internal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.joda.time.Interval;

import rabbit.data.store.model.FileUpdEvent;
import rabbit.data.store.model.FmEvent;

public class DeltaVis  implements IResourceDeltaVisitor{

	public static final String FILE_CHANGED = "chaFile";
	public static final String FILE_ADDED = "addFile";
	public static final String FILE_REMOVED = "rmvFile";
	public static final String METHOD_ADDED = "addMeth";
	public static final String METHOD_REMOVED = "rmvMeth";
 
	private static ArrayList<FileUpdEvent> updFiles;
	private static FileUpdEvent fue; 
	private static boolean listupd ;
	private JMHandle jmHandle;
	private  JavaMethodData jmd;
	private ArrayList<JavaMethodData> lsJM;
	IResource res ;
	public DeltaVis() {
		super();
		updFiles = new ArrayList<FileUpdEvent>();
		listupd = false;
		lsJM = new ArrayList<JavaMethodData>();
		jmHandle = new JMHandle();
	//	jmd = new JavaMethodData();
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
	        		     jmHandle.findMethods(filename);

	            		//	System.out.println("adding new methods before ");
	        		   //  jmd.print();
	            	 		// System.out.println("Java File Changed" +res.getFullPath());
	            	 		// System.out.println("Check if file class or interaface, and go through to define methods add for each method");
	            	 		
	            	 		ArrayList<JavaData> data = new ArrayList<JavaData>();
	            	 		data = jmHandle.getJd();
	            	 	//	System.out.println("THE JAVA DATA DELTAVIS - CHANGED");
	            	 		
	            	 		
	            	 		//if (!data.isEmpty()) {
	            	 			JavaMethodData jmd = new JavaMethodData(filename);
	            	 			jmd.getlsJD().addAll(data);
		            			
		            			int filenameLoc  = lsJMcontains(filename);
		            			if (filenameLoc>=0) {
		            				//System.out.println("this filename was already in " +filename + lsJMcontains(filename));
		            				lsJMremoved(data, filenameLoc,filename,ts,ipjf);
		            				lsJMadded(data,filenameLoc,filename, ts, ipjf);
		            				
	            	 			}else {
	            	 				lsJM.add(jmd);
	            	 				fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,
	            							 TrackingPlugin.test_sid,FILE_CHANGED, "nun","nun","nun","nun");
	            	 				updFiles.add(fue);
	            	 				if (!data.isEmpty()) {
			            	    	 		for (JavaData event : jmd.getlsJD()) {
			            	 				updateLog(filenameLoc,filename,ts,ipjf,METHOD_ADDED,event);
			            	    	 		}
	            	 				}
	            	 			}
		            			
	            	    	 	}
	                 
	             	}
	           //  	print();
	           
	               break;
	          
	         	}
		return true;
	}
	private void updateLog(int filenameLoc, String filename, Timestamp ts, IPath ipjf, String action, JavaData event) {
	
			//	System.out.println("COLLECTED" + event.getFiletype() + event.getMethodName() +
				//		event.getMethodSign() +event.getMethodType());
				 fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,
						 TrackingPlugin.test_sid,action, event.getFiletype(),event.getMethodName(),event.getMethodSign(),event.getMethodType());
    	 		 updFiles.add(fue);
		
	}
	private void print() {
		  if(!lsJM.isEmpty()) {
	             System.out.println("Printing data: " + lsJM.size());
	             for (JavaMethodData d : lsJM) {
	            	 	System.out.print("Filename :" + d.getFilename());
	            	 	if(!(d.getlsJD().isEmpty())){
	            	 		for (JavaData jd : d.getlsJD()) {

		            	 		System.out.println("Methods :" +jd.getMethodName());
		            	 	
	            	 		}	
	            	 		}
	             }
          }
	}
	private ArrayList<JavaData> lsJMadded(ArrayList<JavaData> data, int filename, String name,Timestamp ts, IPath ipjf){
		ArrayList<JavaData> addedItems = new ArrayList<JavaData>();
		System.out.print("INDEX " + filename);
		ArrayList<JavaData> examine = new ArrayList<JavaData>();
		examine = lsJM.get(filename).getlsJD();
		for (JavaData itemNew :data) {
			boolean isFound = false;
			for (JavaData itemOld : examine) {
				if (equalJDItem(itemNew, itemOld)) {
					isFound = true;
					break;
							
				}
			}
			if (!isFound) {
				addedItems.add(itemNew);
				System.out.println(itemNew.getMethodName());
			}
		}
		System.out.print("SIZE " + addedItems.size());
		for (JavaData aItem : addedItems) {
			System.out.println("Added method :" + name +" : " + aItem.getMethodName());

				updateLog(filename,name,ts,ipjf,METHOD_ADDED,aItem);
			
		}
		

		lsJM.get(filename).getlsJD().addAll(addedItems);
		return addedItems;
		
	}
	private ArrayList<JavaData> lsJMremoved(ArrayList<JavaData> data, int filename,String name, Timestamp ts, IPath ipjf){
		ArrayList<JavaData> removedItems = new ArrayList<JavaData>();
		System.out.print("INDEX " + filename);
		ArrayList<JavaData> examine = new ArrayList<JavaData>();
		examine = lsJM.get(filename).getlsJD();
		for (JavaData itemOld :examine) {
			boolean isFound = false;
			for (JavaData itemNew : data) {
				if (equalJDItem(itemOld, itemNew)) {
					isFound = true;
					break;
							
				}
			}
			if (!isFound) {
				removedItems.add(itemOld);
			}
		}
		System.out.print("SIZE " + removedItems.size());
		for (JavaData rItem : removedItems) {
			System.out.println("Removed method :" + name +" : " + rItem.getMethodName());
			updateLog(filename,name,ts,ipjf,METHOD_REMOVED,rItem);
			
		}
		lsJM.get(filename).getlsJD().removeAll(removedItems);
		return removedItems;
		
	}
	private boolean equalJDItem(JavaData item1, JavaData item2) {
		if((item1.getMethodName().equals(item2.getMethodName())) &&
			(item1.getMethodSign().equals(item2.getMethodSign())) && 
			(item1.getMethodType().equals(item2.getMethodType()))) {
			return true;
		}else
			return false;
		
	}
	private void configureResource(String lastSegment, IResource rest, String act, Timestamp ts, IPath ipjf, String filename) {
			
		if(isValidCreatedFile(filename)){
				 
		//	 System.out.println("add/remove" + filename);
		     jmHandle.checkClass(filename);	
			switch (rest.getType()) {
	        case IResource.FILE:
       
	       	 	if (lastSegment!=null) {
		            	 	if(lastSegment.contains("java")) {
		            	 		// System.out.println("A file but need to know if class or interface");
		            	 		ArrayList<JavaData> data = new ArrayList<JavaData>();
		            	 		data = jmHandle.getJd();
		            	 		
		            	 		 fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,TrackingPlugin.test_sid,act, "file","","", "");
		            	 		 updFiles.add(fue);
		       	        	 	}
	       	 	}
	       	 	break;
	        case IResource.FOLDER:
	        		if (lastSegment!=null) {
	        		//	if (!(resPath.contains(".settings")) && (!(resPath.contains("bin")))){
	       	 		 fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,TrackingPlugin.test_sid,act,"folder", "","","");
	       	 		 updFiles.add(fue);
       	 	//	}
	        		}
	        		break;
	        case IResource.PROJECT:
	      	  			if (lastSegment!=null) {
	      	  				fue =new FileUpdEvent(new Interval(System.currentTimeMillis(),System.currentTimeMillis()),ts,ts,ipjf,filename,TrackingPlugin.test_sid,act,"project","","","");
		             	 	updFiles.add(fue);
	      	  			}
	      	  	break;
	        case IResource.ROOT:
		
		    	 		// System.out.println("it was a root");
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
	

	private int lsJMcontains(String filename) {
		int count =0;
		for (JavaMethodData d : lsJM) {
				if(filename.equals(d.getFilename())) {
					return count;
				}	
				count++;
			}
		return -1;
		
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