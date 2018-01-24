package rabbit.data.access.model;
import java.sql.Timestamp;

import org.eclipse.core.resources.IFile;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

public interface IFileUpdData extends IData {
  
  /**
   * Key for the date.
   */
  static final IKey<Integer> SID = Keys.SID;
  /**
   * Key for the date.
   */
  static final IKey<LocalDate> DATE = Keys.DATE;

  /**
   * Key for the workspace.
   */
  static final IKey<WorkspaceStorage> WORKSPACE = Keys.WORKSPACE;

  /**
   * Key for the duration.
   */
  static final IKey<Duration> DURATION = Keys.DURATION;
  
  /**
   * Key for the tsStart.
   */
  static final IKey<Timestamp> TIMESTART = Keys.TIMESTART;
  /**
   * Key for the tsEnd.
   */
  static final IKey<Timestamp> TIMEND = Keys.TIMEND;

  /**
   * Key for the file.
   */
  static final IKey<IFile> FILE = Keys.FILE;
  
  static final IKey<String> FILE_ACTIVITY = Keys.FILE_ACTIVITY;
  static final IKey<String> FTYPE = Keys.FTYPE;
  
  static final IKey<String> FM_MNAME = Keys.FM_MNAME;
  static final IKey<String> FM_MTYPE = Keys.FM_MTYPE;
  static final IKey<String> FM_MSIGN = Keys.FM_MSIGN;

}
