package rabbit.data.internal.xml.convert;
import rabbit.data.internal.xml.schema.events.FmEventType;
import rabbit.data.store.model.FmEvent;
public class FmEventConverter extends AbstractConverter<FmEvent,FmEventType>{

	@Override
	protected FmEventType doConvert(FmEvent element) {
		
	    FmEventType type = new FmEventType();
	    type.setDuration(element.getInterval().toDurationMillis());
	    type.setFilePath(element.getFilePath().toString());
	    type.setTsStart(element.getStart().toString());
	    type.setTsEnd(element.getEnd().toString());
	    type.setSid(String.valueOf(element.getSid()));
	   type.setFilename(element.getFilename().toString());
	   type.setMethodname(element.getMethodname().toString());
	   type.setMethodtype(element.getMethodtype().toString());
	   type.setMethodsign(element.getMethodsign().toString());

	    return type;
	}
}
