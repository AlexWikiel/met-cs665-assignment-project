package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;

public class LoadInstrumentState extends LoadState {
  public LoadInstrumentState(ProcedureEvent procedureEvent) {
    super(procedureEvent);
  }

  @Override
  public void load() {
    System.out.println(readFile("Instrument.cfg"));
    System.out.println("Loading Instrument...");
    experimentLoader.transitionTo(new LoadMethodState(procedureEvent));
  }
}
