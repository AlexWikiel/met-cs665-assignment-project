package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.configuration.TextFileReader;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;

public abstract class LoadState extends TextFileReader {
  protected ExperimentLoader experimentLoader;

  protected ProcedureEvent procedureEvent;

  public LoadState(ProcedureEvent procedureEvent) {
    this.procedureEvent = procedureEvent;
  }

  public void setExperimentLoader(ExperimentLoader experimentLoader) {
    this.experimentLoader = experimentLoader;
  }

  public abstract void load();
}