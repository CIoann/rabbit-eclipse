package rabbit.tracking.internal;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class JavaSourceVisitor extends ASTVisitor{
	String nodeInfo = null;
	
	public boolean visit(TypeDeclaration node) {
		nodeInfo = node.toString();
		return super.visit(node);
	}
	
	public String getNodeInfo() {
		return nodeInfo;
	}

}
