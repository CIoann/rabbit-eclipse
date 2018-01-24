package rabbit.tracking.internal;

import java.util.ArrayList;

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
	private final int CLASS = 0;
	private final int INTERFACE = 1;
	private final int INVALID = -1;
	
	private JavaData jde;
	private ArrayList<JavaData> jd;
	private boolean isClass;
	 String fileCheck="";
	 String fileFM ="";
	 private boolean isSearch;
	private boolean isCheck;
	private final String nature = "org.eclipse.jdt.core.javanature";  
 
	public JMHandle() {
		jd = new ArrayList<JavaData>();
		fileCheck = "";
		fileFM = "";
		isCheck = false;
		isClass = false;
		isSearch =false;
		updateProjects();
	}

	
	private void updateProjects() {
		jd.clear();
		// System.out.println("A change occurred! ");
		root = ws.getRoot();
		projects = root.getProjects();
		accessProjects(); 
//		System.out.println("THE JAVA DATA JMHANDLE");
//		for (JavaData event : jd) {
//			System.out.println("COLLECTED" + event.getFiletype() + event.getMethodName() +
//					event.getMethodSign() +event.getMethodType());
//			
//		}
	
		
	}
	
	public boolean checkClass(String filename) {
		// System.out.println("Filename: Find Class or Interface : "+filename);
		this.fileCheck=filename;
		this.fileFM =filename;
		isCheck = true;
		isSearch = false;
		updateProjects();
		return isClass;
	}
	
	public void findMethods(String filename) {
		// System.out.println("Filename: Find Methods");
		this.fileFM = filename;
		this.fileCheck = filename;
		isCheck = true;
		isSearch =true;
		updateProjects();
		return;
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
        	int result=INVALID;
	        	if (isCheck) { 
		        	if (fileCheck.equals(unit.getPath().toString())) {
		        		
		    	   	//	// System.out.println("FILE NAME "+ fileCheck + " FILE UNIT " +unit.getPath().toString());
		    	   		result = interOrClass(unit);
		    	   		
		    	  
		        	}
	        	} 
	        	
	        	if (isSearch){
	        	 	if (fileCheck.equals(unit.getPath().toString())) {
		        		// System.out.println("FIND METHODS" +fileFM + " FILE UNIT" +unit.getPath().toString() );
		        		printCompilationUnitDetails(unit, result);
	        	 	}
	        	}
        	}
       
	}


	private int interOrClass( ICompilationUnit unit) {
		if (unit != null) {
		 ASTParser parser = ASTParser.newParser(AST.JLS9);
         parser.setKind(ASTParser.K_COMPILATION_UNIT);
         parser.setSource(unit);
         parser.setResolveBindings(true);
         CompilationUnit astRoot = (CompilationUnit) parser.createAST(null);
        
         JavaSourceVisitor jsv = new JavaSourceVisitor();
         astRoot.accept(jsv);
         // System.out.println("Visitor collected NODE INFO " +jsv.getNodeInfo());
         if(jsv.getNodeInfo()!=null) {
             if(  jsv.getNodeInfo().contains("class")) {
            	 	return CLASS;
 	   			//// System.out.println("CLASS" + unit.getElementName());  		     		       
 	   		}
             if(  jsv.getNodeInfo().contains("interface")) {
  	   			//// System.out.println("INTERFACE" + unit.getElementName());
            	 	return INTERFACE;
  	   		}
         }else {
        	 return INVALID;
         }
		}
		return INVALID;
	}
	
	private void printCompilationUnitDetails(ICompilationUnit unit, int result) throws JavaModelException {
	//	// System.out.println("Source file " + unit.getElementName());
        Document doc = new Document(unit.getSource());
      //  // System.out.println("" + unit.getElementType());
        
     
    //  analyseSource(unit);
      printIMethods(unit, result);
		
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

	private void printIMethods(ICompilationUnit unit, int result) throws JavaModelException {
		IType[] allTypes = unit.getAllTypes();
		// System.out.println("Methods");
        for (IType type : allTypes) {
            printIMethodDetails(type, result);
            
           
        }
		
	}

	private void printIMethodDetails(IType type, int result) throws JavaModelException {
		 IMethod[] methods = type.getMethods();
	        for (IMethod method : methods) {

		  		 jde = new JavaData(result+"", method.getElementName(), 
		  				 method.getSignature(), method.getReturnType());
		  		 jd.add(jde);
	        }	 
		
	}


	public ArrayList<JavaData> getJd() {
		return jd;
	}


	public void setJd(ArrayList<JavaData> jd) {
		this.jd = jd;
	}

}
