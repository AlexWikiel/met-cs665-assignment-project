package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.configuration.ProcedureEvent;

public abstract class Analyzer {
  protected ProcedureEvent procedureEvent;

  public Analyzer(ProcedureEvent procedureEvent) {
    this.procedureEvent = procedureEvent;
  }

  public abstract void loadExperiment();

  public abstract void startExperiment();

  public abstract void stopExperiment();

  public abstract void collectData();

  public abstract void getControllerInfo();
}
