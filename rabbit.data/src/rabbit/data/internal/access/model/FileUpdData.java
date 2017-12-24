package rabbit.data.internal.access.model;

import rabbit.data.access.model.IFileUpdData;
import rabbit.data.access.model.IKey;
import rabbit.data.access.model.WorkspaceStorage;

import static com.google.common.base.Preconditions.checkNotNull;

import org.eclipse.core.resources.IFile;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Nullable;

public class FileUpdData implements IFileUpdData{

  private final Map<IKey<? extends Object>, Object> data;
  

  public FileUpdData(int sid,
      LocalDate date,Timestamp tsStart,Timestamp tsEnd, WorkspaceStorage workspace, Duration duration, IFile file, String file_activity) {
    
    data = new KeyMapBuilder()
    	.put(SID,      checkNotNull(sid,      "sid"))
    	.put(DATE,      checkNotNull(date, "date"))
        .put(WORKSPACE, checkNotNull(workspace, "workspace"))
        .put(DURATION,  checkNotNull(duration, "duration"))
        .put(TIMESTART, checkNotNull(tsStart, "timestart"))
        .put(TIMEND, checkNotNull(tsEnd, "timend"))
        .put(FILE,      checkNotNull(file, "file"))
        .put(FILE_ACTIVITY, checkNotNull(file_activity,"file_activity"))
        .build();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T get(@Nullable IKey<T> key) {
    return (T) data.get(key);
  }
}
