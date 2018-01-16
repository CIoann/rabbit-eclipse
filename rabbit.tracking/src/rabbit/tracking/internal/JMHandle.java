package rabbit.tracking.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
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
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.Document;


public class JMHandle{
	IWorkspace ws= ResourcesPlugin.getWorkspace();
	
	IWorkspaceRoot root ;

	IProject[] projects;
	
	private boolean isClass;
	 String fileCheck="";
	
	private final String nature = "org.eclipse.jdt.core.javanature";  
 
	public JMHandle() {

		fileCheck = "";
		updateProjects();
	}

	
	private void updateProjects() {
	
		System.out.println("A change occurred! ");
		root = ws.getRoot();
		projects = root.getProjects();
		accessProjects(); 

		isClass = false;
	}
	
	public boolean checkClass(String filename) {
		System.out.println("filename ");
		this.fileCheck=filename;
		updateProjects();
		return isClass;
	}
	public boolean isInterface(String filename) {
		return true;
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
		
	//	System.out.println("Working in project" + project.getName());
		if(project.isNatureEnabled(nature)) {
			IJavaProject jp = JavaCore.create(project);
			printPackageInfo(jp);
			
			
		}
	}
	
	
	private void printPackageInfo(IJavaProject jp) throws JavaModelException {
		IPackageFragment[] packages = jp.getPackageFragments();
		for (IPackageFragment pkg : packages) {
			if(pkg.getKind() == IPackageFragmentRoot.K_SOURCE) {
		//		System.out.println("Package: " + pkg.getElementName());
				printICompilationUnitInfo(pkg);
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void printICompilationUnitInfo(IPackageFragment pkg)     throws JavaModelException {
        for (ICompilationUnit unit : pkg.getCompilationUnits()) {
//             printCompilationUnitDetails(unit);
        	if (fileCheck.equals(unit.getPath().toString())) {
    	   		System.out.println("FILE NAME "+ fileCheck + " FILE UNIT " +unit.getPath().toString());
    
    	   		ASTParser parser = ASTParser.newParser(AST.JLS9);
             parser.setKind(ASTParser.K_COMPILATION_UNIT);
             parser.setSource(unit);
             parser.setResolveBindings(true);
             CompilationUnit astRoot = (CompilationUnit) parser.createAST(null);
            
             JavaSourceVisitor jsv = new JavaSourceVisitor();
             astRoot.accept(jsv);
             System.out.println("Visitor collected NODE INFO " +jsv.getNodeInfo());
             if(  jsv.getNodeInfo().contains("class")) {
 	   
 	   			System.out.println("CLASS" + unit.getElementName());  		     		       
 	   		}
             if(  jsv.getNodeInfo().contains("interface")) {
  	   			System.out.println("INTERFACE" + unit.getElementName());  		     		       
  	   		}
             

        	}
        	}
       
	}


	private void printCompilationUnitDetails(ICompilationUnit unit) throws JavaModelException {
		//System.out.println("Source file " + unit.getElementName());
        Document doc = new Document(unit.getSource());
      //  System.out.println("" + unit.getElementType());
        
     
      analyseSource(unit);
      printIMethods(unit);
		
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

}
