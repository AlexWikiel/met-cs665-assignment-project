package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;

public class LoadStepState extends LoadState {
  public LoadStepState(ProcedureEvent procedureEvent) {
    super(procedureEvent);
  }

  @Override
  public void load() {
    System.out.println(readFile("Step.cfg"));
    System.out.println("Loading Step...");

  }
}
