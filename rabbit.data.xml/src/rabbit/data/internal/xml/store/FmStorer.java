package rabbit.data.internal.xml.store;

import rabbit.data.internal.xml.IDataStore;
import rabbit.data.internal.xml.StoreNames;
import rabbit.data.internal.xml.convert.IConverter;
import rabbit.data.internal.xml.merge.IMerger;
import rabbit.data.internal.xml.schema.events.EventListType;
import rabbit.data.internal.xml.schema.events.FmEventListType;
import rabbit.data.internal.xml.schema.events.FmEventType;
import rabbit.data.store.model.FileEvent;
import rabbit.data.store.model.FmEvent;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

public class FmStorer extends 
AbstractStorer<FmEvent, FmEventType, FmEventListType>{

  
  @Inject
  FmStorer(IConverter<FmEvent, FmEventType> converter,
      IMerger<FmEventType> merger,
      @Named(StoreNames.FM_STORE) IDataStore store) {
    super(converter, merger, store);
  }

@Override
protected List<FmEventListType> getCategories(EventListType events) {
	// TODO Auto-generated method stub
	return events.getFmEvents();
}

@Override
protected List<FmEventType> getElements(FmEventListType list) {
	
	return list.getFmEvent();
}

@Override
protected FmEventListType newCategory(XMLGregorianCalendar date) {
	FmEventListType type = objectFactory.createFmEventListType();
	type.setDate(date);
	return type;
}

 
}
