package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.configuration.ProcedureEvent;

public class LoadMethodState extends LoadState{
  public LoadMethodState(ProcedureEvent procedureEvent) {
    super(procedureEvent);
  }

  @Override
  public void load() {
    System.out.println(readFile("Method.cfg"));
    System.out.println("Loading method...");
    experimentLoader.transitionTo(new LoadFillingState(procedureEvent));
  }
}
