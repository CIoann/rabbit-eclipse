package rabbit.data.internal.xml.schema.events;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FmEventType")
public class FmEventType extends DurationEventType {

 @XmlAttribute(required = true)
 protected String id;
 @XmlAttribute(required = true)
 protected String sid;
 @XmlAttribute(required = true)
 protected String filename;
 @XmlAttribute(required = true)
 protected String methodname;

 public String getFilename() {
	return filename;
}

public void setFilename(String filename) {
	this.filename = filename;
}

public String getMethodname() {
	return methodname;
}

public void setMethodname(String methodname) {
	this.methodname = methodname;
}

public String getMethodsign() {
	return methodsign;
}

public void setMethodsign(String methodsign) {
	this.methodsign = methodsign;
}

public String getMethodtype() {
	return methodtype;
}

public void setMethodtype(String methodtype) {
	this.methodtype = methodtype;
}

@XmlAttribute(required = true)
 protected String methodsign;

 @XmlAttribute(required = true)
 protected String methodtype;
 
	@XmlAttribute(required = true)
 protected String tsStart;
 @XmlAttribute(required = true)
 protected String tsEnd;

 
 public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
  * Gets the value of the filePath property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getFilePath() {
     return id;
 }

 /**
  * Sets the value of the filePath property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setFilePath(String value) {
     this.id = value;
 }

	public String getTsStart() {
		return tsStart;
	}

	public void setTsStart(String tsStart) {
		this.tsStart = tsStart;
	}

	public String getTsEnd() {
		return tsEnd;
	}

	public void setTsEnd(String tsEnd) {
		this.tsEnd = tsEnd;
	}

}
