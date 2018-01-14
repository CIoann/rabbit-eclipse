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
			 //  updateProjects();
			  
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
	private void updateProjects() {
		System.out.println("A change occurred! ");
		root = ws.getRoot();
		projects = root.getProjects();
		accessProjects(); 
	}
	
	
	private void accessProjects() {
		for (IProject project: projects) {
			try {
				printProjectInfo(project);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void printProjectInfo(IProject project) throws CoreException {
		
		System.out.println("Working in project" + project.getName());
		if(project.isNatureEnabled(nature)) {
			IJavaProject jp = JavaCore.create(project);
			printPackageInfo(jp);
			
			
		}
	}
	
	
	private void printPackageInfo(IJavaProject jp) throws JavaModelException {
		IPackageFragment[] packages = jp.getPackageFragments();
		for (IPackageFragment pkg : packages) {
			if(pkg.getKind() == IPackageFragmentRoot.K_SOURCE) {
				System.out.println("Package: " + pkg.getElementName());
				printICompilationUnitInfo(pkg);
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void printICompilationUnitInfo(IPackageFragment pkg)     throws JavaModelException {
        for (ICompilationUnit unit : pkg.getCompilationUnits()) {
             printCompilationUnitDetails(unit);
             // identify if its a class, or interface 
             // new packages/ projects keep an array to compare are tracked before keep 
           if(  unit.toString().contains("class")) {
        	   System.out.println("CLASS" + unit.getElementName());
           }
           if(  unit.toString().contains("interface")) {
        	   System.out.println("interface" + unit.getElementName());
           }
             
//             ASTParser parser = ASTParser.newParser(AST.JLS9);
//             parser.setKind(ASTParser.K_COMPILATION_UNIT);
//             parser.setSource(unit);
//             parser.setResolveBindings(true);
//             CompilationUnit astRoot = (CompilationUnit) parser.createAST(null);
//             
//             astRoot.accept(new ASTVisitor() {
//     	        	public boolean visit(TypeDeclaration node) {
//     	        		
//     	        		System.out.println(("" +node.getName().toString()));
//     	        	//	System.out.println("" + node.get);
//     	        		System.out.print("NODE INFO"  + node.toString());
//     	        		return true;
//         	    	   	
//     	        }
//     		});
        	/*    	   	
        	   	public boolean visit(TypeDeclaration node) {
        	   		System.out.println("THE NAME I NEED " + node.getName());
        	   		return true;
        	   	}
        	   	
			});
	        }*/
	    }
	}


	private void printCompilationUnitDetails(ICompilationUnit unit) throws JavaModelException {
		System.out.println("Source file " + unit.getElementName());
        Document doc = new Document(unit.getSource());
        System.out.println("" + unit.getElementType());
        //IJavaElement.
       
    //  analyseSource(unit);
     // printIMethods(unit);
		
	}
	private void analyseSource(ICompilationUnit unit) throws JavaModelException {
		IScanner sc = ToolFactory.createScanner(false, false, false, false);
		sc.setSource(unit.getSource().toCharArray());
		int token;
		try {
			token = sc.getNextToken();
			while (token != ITerminalSymbols.TokenNameEOF) {
			
				sc.getCurrentTokenSource();
					token = sc.getNextToken();
			}
		} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	private void printIMethods(ICompilationUnit unit) throws JavaModelException {
		IType[] allTypes = unit.getAllTypes();
        for (IType type : allTypes) {
            printIMethodDetails(type);
            
           
        }
		
	}

	private void printIMethodDetails(IType type) throws JavaModelException {
		 IMethod[] methods = type.getMethods();
	        for (IMethod method : methods) {

	            System.out.println("Method name " + method.getElementName());
	            System.out.println("Signature " + method.getSignature());
	            System.out.println("Return Type " + method.getReturnType());

	        }	 
		
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
