package edu.bu.met.cs665.app.configuration3;

/**
 * The class that will build the SystemConfig object via the specified readers.  This utilizes the builder pattern
 */
public class ConfigBuilder implements Configuration {
  private SystemConfig systemConfig;

  /**
   * Init and read the system configuration
   */
  @Override
  public void readSystemConfig() {
    this.systemConfig =  new SystemConfig();
    SystemConfigReader sconReader = new SystemConfigReader(systemConfig);
    sconReader.read();
  }

  /**
   * Init and read all the routines specified in the system configuration
   */
  @Override
  public void readRoutine() {
    int counter = 1; // used to create fake path
    while (systemConfig.hasNext()){
      Routine routine = systemConfig.getNext();
      RoutineReader routineReader = new RoutineReader(routine);
      routineReader.read();
    }
  }

  /**
   * Init and read all the procedures specified in the routines
   */
  @Override
  public void readProcedure() {
    while (systemConfig.hasNext()) {
      Routine routine = systemConfig.getNext();
      while (routine.hasNext()) {
        Procedure procedure = routine.getNext();
        ProcedureReader procedureReader = new ProcedureReader(procedure);
        procedureReader.read();
      }
    }
  }
}
