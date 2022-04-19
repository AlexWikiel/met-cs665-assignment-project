package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;

public class Potentiostat extends Analyzer{
  
  public Potentiostat(ProcedureEvent procedureEvent) {
    super(procedureEvent);
  }

  @Override
  public void loadExperiment() {
    ExperimentLoader experimentLoader = new ExperimentLoader(new LoadInstrumentState(procedureEvent));
  }

  @Override
  public void runExperiment() {

  }

  @Override
  public void stopExperiment() {

  }

  @Override
  public void collectData() {

  }

  @Override
  public void getControllerInfo() {

  }
}
