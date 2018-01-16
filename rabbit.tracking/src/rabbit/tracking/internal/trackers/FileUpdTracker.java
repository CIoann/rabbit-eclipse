package rabbit.tracking.internal.trackers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.team.IResourceTree;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.internal.compiler.parser.JavadocParser;
import org.eclipse.jdt.internal.core.search.matching.DeclarationOfAccessedFieldsPattern;
import org.eclipse.jface.text.Document;

import rabbit.data.handler.DataHandler;
import rabbit.data.store.IStorer;
import rabbit.data.store.model.FileUpdEvent;
import rabbit.tracking.internal.DeltaVis;

public class FileUpdTracker extends AbstractTracker<FileUpdEvent> {
	IWorkspace ws= ResourcesPlugin.getWorkspace();
	
	IWorkspaceRoot root ;

	IProject[] projects;

	private final String nature = "org.eclipse.jdt.core.javanature";  
 	DeltaVis dv;
 	
	IResourceChangeListener fcl = new IResourceChangeListener() {
		
		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			   IResource res = event.getResource();
		//	   updateProjects();
			  
		         switch (event.getType()) {
		   
		            case IResourceChangeEvent.PRE_CLOSE:
		               break;
		            case IResourceChangeEvent.PRE_DELETE:
		               break;
		            case IResourceChangeEvent.PRE_BUILD:
		            	
		            case IResourceChangeEvent.POST_CHANGE:
		            //   System.out.println("Resources have changed.");
				         try {
				      			event.getDelta().accept(dv);
				      			addData(DeltaVis.getfue());
				      			//System.out.println("Fue" +DeltaVis.getfue().getFilePath());
				        	  //	System.out.println("Size FUEL " + DeltaVis.getFueList().size());
				      	//		for (int i=0;i<DeltaVis.getFueList().size(); i++) {
				      				//System.out.println("File: " + DeltaVis.getFueList().get(i).getFilePath());
				      	//		}
							} catch (CoreException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		               break;
		      
		         }
		       
		      }
			
	};
	public FileUpdTracker() {
		super();
		dv = new DeltaVis();
		
	//	updateProjects();

	}
	

	@Override
	protected IStorer<FileUpdEvent> createDataStorer() {
		return DataHandler.getStorer(FileUpdEvent.class);
	}

	@Override
	protected void doDisable() {
		// TODO Auto-generated method stub
		ws.removeResourceChangeListener(fcl);
		System.out.println("DISABLED FCL");
		
	
	}

	@Override
	protected void doEnable() {
		// TODO Auto-generated method stub
		  ws.addResourceChangeListener(fcl,IResourceChangeEvent.POST_CHANGE);	
		  System.out.println("ENABLED FCL");
	}

}
