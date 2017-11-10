package rabbit.data.internal.xml.schema.events;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
* <p>Java class for durationEventType complex type.
* 
* <p>The following schema fragment specifies the expected content contained within this class.
* 
* <pre>
* &lt;complexType name="durationEventType">
*   &lt;complexContent>
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*       &lt;attribute name="duration" use="required" type="{}durationType" />
*     &lt;/restriction>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
* 
* 
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StartEventType")
@XmlSeeAlso({
 PartEventType.class,
 TaskFileEventType.class,
 FileEventType.class,
 SessionEventType.class,
 JavaEventType.class,
 PerspectiveEventType.class
})
public class StartEventType {

 @XmlAttribute(required = true)
 protected long tss;

 /**
  * Gets the value of the tss property.
  * 
  */
 public long getTss() {
     return tss;
 }

 /**
  * Sets the value of the tss property.
  * 
  */
 public void setTss(long value) {
     this.tss = value;
 }

}
