package rabbit.tracking.internal.startup;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

public class DeltaVis implements IResourceDeltaVisitor {

	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		// TODO Auto-generated method stub
	     IResource res = delta.getResource();
	       switch (delta.getKind()) {
	          case IResourceDelta.ADDED:
	            // System.out.print("Resource ");
	             //System.out.print(res.getFullPath());
	             //System.out.println(" was added.");
	             break;
	          case IResourceDelta.REMOVED:
	            // System.out.print("Resource ");
	           //  System.out.print(res.getFullPath());
	          //   System.out.println(" was removed.");
	             break;
	          case IResourceDelta.CHANGED:
	      //       System.out.print("Resource ");
	       //      System.out.print(res.getFullPath());
	        //     System.out.println(" has changed.");
	             break;
	       }
		return true;
	}
 }