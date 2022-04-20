package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.configuration.ProcedureEvent;

public class Potentiostat extends Analyzer{
  
  public Potentiostat(ProcedureEvent procedureEvent) {
    super(procedureEvent);
  }

  @Override
  public void loadExperiment() {
    ExperimentLoader experimentLoader = new ExperimentLoader(new LoadInstrumentState(procedureEvent));
  }

  @Override
  public void startExperiment() {
    System.out.println("Starting Analyzer...");
  }

  @Override
  public void stopExperiment() {
    System.out.println("Stopping Analyzer...");
  }

  @Override
  public void collectData() {
    System.out.println("Starting Data Collector");
    Thread thread = new Thread(new DataCollector());
    thread.setDaemon(true);
    thread.setName("DataCollectorThread");
    thread.start();
  }

  @Override
  public void getControllerInfo() {
    System.out.println("Querying analyzer for info...");
  }

}
