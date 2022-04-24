package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.AnalyzerStatus;
import edu.bu.met.cs665.app.SystemStatus;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;

/**
 * This state loads the filling file.  We have ProcedureEvent here, so we have all the information
 * needed to find the correct file and load it.  In this project this is not implemented and is
 * simple simulation that writes to console.
 */
public class LoadFillingState extends LoadState {
  public LoadFillingState(ProcedureEvent procedureEvent) {
    super(procedureEvent);
  }

  @Override
  public void load() {
    AnalyzerStatus.getAnalyzerStatus().setSystemStatus(SystemStatus.uploadFilling);
    System.out.println(readFile("Filling.cfg"));
    System.out.println("Loading Filling...");
    experimentLoader.transitionTo(new LoadStepState(procedureEvent));
  }
}
