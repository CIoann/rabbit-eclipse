package rabbit.data.internal.xml.schema.events;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileUpdEventListType", propOrder = {
 "event"
})
public class FileUpdEventListType
	 extends EventGroupType
	 {

 @XmlElement(required = true)
 protected List<FileUpdEventType> event;

 /**
  * Gets the value of the fileEvent property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the fileEvent property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getFileEvent().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link FileEventType }
  * 
  * 
  */
 public List<FileUpdEventType> getFileUpdEvent() {
     if (event == null) {
     	event = new ArrayList<FileUpdEventType>();
     }
     return this.event;
 }


}
