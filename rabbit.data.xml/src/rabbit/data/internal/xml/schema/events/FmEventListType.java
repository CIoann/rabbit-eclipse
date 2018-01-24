package rabbit.data.internal.xml.schema.events;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FmEventListType", propOrder = {
 "event"
})
public class FmEventListType extends EventGroupType{

	 @XmlElement(required = true) 
	protected List<FmEventType> event;

	 public List<FmEventType> getFmEvent(){
		 if(event == null) {
			 event = new ArrayList<FmEventType>();
		 }
		 return this.event;
	 }
}
