package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.AnalyzerStatus;
import edu.bu.met.cs665.app.SystemStatus;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;

/**
 * A potentiostat controls an electrochemical experiment. To run an experiment must be loaded nad started. Once the
 * experiment is running data can be collected from the controller.
 */
public class Potentiostat extends Analyzer{

  private Thread dataCollectorThread;

  /**
   * Inject ProcedureEvent, which contains the paths to the experimental condition files.
   * @param procedureEvent
   */
  public Potentiostat(ProcedureEvent procedureEvent) {
    super(procedureEvent);
  }

  /**
   * Load experimental conditions to the controller.
   */
  @Override
  public void loadExperiment() {
    ExperimentLoader experimentLoader = new ExperimentLoader(new LoadInstrumentState(procedureEvent));
  }

  /**
   * Start the experiment.  Experimental conditions must be loaded first.
   */
  @Override
  public void startExperiment() {
    AnalyzerStatus.getAnalyzerStatus().setSystemStatus(SystemStatus.runningExperiment);
    System.out.println("Starting Analyzer...");
  }

  /**
   * E-stop in the event we must stop the controller and DataCollector thread
   */
  @Override
  public void stopExperiment() {
    System.out.println("Stopping Analyzer...");
  }

  /**
   * Starts the dataCollector thread to ping the the controller for available data.
   */
  @Override
  public void collectData() {
    System.out.println("Starting Data Collector");
    dataCollectorThread = new Thread(new DataCollector());
    dataCollectorThread.setDaemon(true);
    dataCollectorThread.setName("DataCollectorThread");
    dataCollectorThread.start();
  }

  /**
   * Gets serial number and firmware.  Not implemneted in this project.
   */
  @Override
  public void getControllerInfo() {
    System.out.println("Querying analyzer for info...");
  }

  /**
   * returns the dataCollector thread in th event we must interact with it.
   * @return DataCollectorThread.
   */
  public Thread getDataCollectorThread() {
    return dataCollectorThread;
  }
}
