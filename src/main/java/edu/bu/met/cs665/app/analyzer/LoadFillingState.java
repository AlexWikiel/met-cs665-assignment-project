package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;

public class LoadFillingState extends LoadState {
  public LoadFillingState(ProcedureEvent procedureEvent) {
    super(procedureEvent);
  }

  @Override
  public void load() {
    System.out.println(readFile("Filling.cfg"));
    System.out.println("Loading Filling...");
    experimentLoader.transitionTo(new LoadStepState(procedureEvent));
  }
}
