package rabbit.tracking.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class JavaMethodData {
	private ArrayList<JavaData> lsJD;
	private String filename;
	
	public JavaMethodData(String filename) {
		lsJD = new ArrayList<JavaData>();
		this.filename = filename;
			
	}
	
	public void init() {
		lsJD = new ArrayList<JavaData>();
	}
	
	public ArrayList<JavaData> getlsJD(){
		return this.lsJD;
	}
	public String getFilename() {
		return this.filename;
	}
	public void update(ArrayList<JavaData> lsIN) {
		ArrayList<JavaData> insertItems = new ArrayList<JavaData>();
		for (JavaData jdNItem : lsIN) {
			boolean isFound = false;
			for (JavaData jdItem : lsJD) {
				if (jdItem.getMethodName().equals(jdNItem.getMethodName())){
					isFound = true;
					break;
							
				}
			}
			if (!isFound) {
				    	insertItems.add(jdNItem);
			}
				}
		lsJD.addAll(insertItems);
		
		
	}
}
