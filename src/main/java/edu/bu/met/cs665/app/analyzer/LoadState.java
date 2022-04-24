package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.configuration.readertypes.TextFileReader;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;

/**
 * Each state inherits this abstract class which inherits TextFileReader.
 */
public abstract class LoadState extends TextFileReader {

  protected ExperimentLoader experimentLoader;
  protected ProcedureEvent procedureEvent;

  /**
   * Inject the procedureEvent needed for running this experiment.
   * @param procedureEvent contains the experimental conditions.
   */
  public LoadState(ProcedureEvent procedureEvent) {
    this.procedureEvent = procedureEvent;
  }

  /**
   * Inject the ExperimentLoader so the state can change after it is completed.
   * @param experimentLoader The controller for the state pattern.
   */
  public void setExperimentLoader(ExperimentLoader experimentLoader) {
    this.experimentLoader = experimentLoader;
  }

  public abstract void load();
}
