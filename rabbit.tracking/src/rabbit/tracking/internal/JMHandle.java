package rabbit.tracking.internal;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;


public class JMHandle extends AbstractHandler{

	private final String nature = "org.eclipse.jdt.core.javanature";  
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = ws.getRoot();
		
		IProject[] projects = root.getProjects();
		
		for (IProject project: projects) {
			try {
				printProjectInfo(project);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private void printProjectInfo(IProject project) throws CoreException {
		
		System.out.println("Working in project" + project.getName());
		if(project.isNatureEnabled(nature)) {
			IJavaProject jp = JavaCore.create(project);
			
			
		}
		// TODO Auto-generated method stub
		
	}
	

}
