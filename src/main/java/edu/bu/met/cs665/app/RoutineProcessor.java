package edu.bu.met.cs665.app;

import edu.bu.met.cs665.app.analyzer.Potentiostat;
import edu.bu.met.cs665.app.configuration.types.Procedure;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;
import edu.bu.met.cs665.app.configuration.types.Routine;

/**
 * The engine that will run a series of experiments described by a routine file.
 * This processor will also run a dedicated thread to avoid locking a GUI.
 */
public class RoutineProcessor implements Runnable {
  private Routine routine;

  /**
   * Inject the routine info.
   * @param routine contains all the procedures within the in routine.
   */
  public RoutineProcessor(Routine routine) {
    AnalyzerStatus.getAnalyzerStatus().setCurrentRoutine(routine);
    this.routine = routine;
  }

  @Override
  public void run() {
    processRoutine();
  }

  /**
   * Iterate over entire routine file and run each procedure individually.
   */
  private void processRoutine() {
    while (routine.hasNext()) {
      Procedure procedure = routine.getNext();
      System.out.println("Running Procedure: " + procedure.getName()); //can delete

      // set current tracker in Analyzer Status
      AnalyzerStatus.getAnalyzerStatus().setCurrentProcedure(procedure);
      processProcedure(procedure);

      // reset current tracker in Analyzer Status
      AnalyzerStatus.getAnalyzerStatus().setCurrentProcedure(null);
    }
    AnalyzerStatus.getAnalyzerStatus().setSystemStatus(SystemStatus.idle);
  }

  /**
   * Run each ProcedureEvent within the Procedure.  Each ProcedureEvent is an experiment.
   * @param procedure contains procedure events.
   */
  private void processProcedure(Procedure procedure) {
    while (procedure.hasNext()) {
      ProcedureEvent procedureEvent = procedure.getNext();

      System.out.println("Running Procedure Event: " + procedureEvent.getName()); ///can delete

      // reset current tracker in Analyzer Status
      AnalyzerStatus.getAnalyzerStatus().setCurrentProcedureEvent(procedureEvent);
      Potentiostat potentiostat = new Potentiostat(procedureEvent);
      potentiostat.loadExperiment();
      potentiostat.startExperiment();
      potentiostat.collectData();
      try {
        potentiostat.getDataCollectorThread().join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // reset current tracker in Analyzer Status
      AnalyzerStatus.getAnalyzerStatus().setCurrentProcedureEvent(null);
    }
  }
}
