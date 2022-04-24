package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;

/**
 * Represents equipment this app can use to complete the analysis.  The equipment must accept some
 * parameters for an experiment and then must be able to start and experiment, and we must be able
 * to retrieve data from the experiment.
 */
public abstract class Analyzer {
  protected ProcedureEvent procedureEvent;

  /**
   * Inject the procedure event which contains paths to the text files that contain the experimental
   * conditions.
   * @param procedureEvent structure that contains file paths to the experimental conditions.
   */
  public Analyzer(ProcedureEvent procedureEvent) {
    this.procedureEvent = procedureEvent;
  }

  /**
   * Upload the experiment conditions to the external equipment.
   */
  public abstract void loadExperiment();

  /**
   * Start the experiment.  In this project this is just simulated data, but in a real project we
   * would this starts an external program.
   */
  public abstract void startExperiment();

  /**
   * Send stop command to external equipment running an expeirment.
   */
  public abstract void stopExperiment();  // This is not implemented

  /**
   * Spin up a thread to ping the external equipment to get data back.  This is simulated in this
   * project and just spins up a thread that will create fake data using the random function.
   * This data is communicated back to the AnalyzerApp singleton using a lock to maintain thread
   * safety.
   */
  public abstract void collectData();

  /**
   * Ping the external controller for serial number and firmware version to display to screen.
   * This is not implemented in this project as we do not have GUI that would display this info.
   */
  public abstract void getControllerInfo(); // This is not implemented
}
