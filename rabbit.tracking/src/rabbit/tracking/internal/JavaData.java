package rabbit.tracking.internal;

public class JavaData {
	private String filetype;
	private String methodName;
	private String methodSign;
	private String methodType;
	
	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodSign() {
		return methodSign;
	}

	public void setMethodSign(String methodSign) {
		this.methodSign = methodSign;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public JavaData(String filetype, String methodName, String methodSign, String methodType) {
		super();
		this.filetype = filetype;
		this.methodName = methodName;
		this.methodSign = methodSign;
		this.methodType = methodType;
	}
	
	
	
}
