package edu.bu.met.cs665.app;

import edu.bu.met.cs665.app.analyzer.Potentiostat;
import edu.bu.met.cs665.app.configuration.ConfigBuilder;
import edu.bu.met.cs665.app.configuration.ConfigDirector;
import edu.bu.met.cs665.app.configuration.Configuration;
import edu.bu.met.cs665.app.configuration.types.Procedure;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;
import edu.bu.met.cs665.app.configuration.types.Routine;
import edu.bu.met.cs665.app.configuration.types.SystemConfig;

/**
 * The brain that connects all the other parts together, this is the main hub of the entire analyzer.
 */
public class AnalyzerApp {

  private static AnalyzerApp analyzerApp;
  private SystemConfig config;
  private Routine currentRoutine;
  private Procedure currentProcedure;
  private ProcedureEvent currentProcedureEvent;

  /**
   * Private constructor so we can use the singleton pattern
   */
  private AnalyzerApp() {}


  private int[][] data;

  public SystemConfig getConfig(){
    return config;
  }

  public static AnalyzerApp getAnalyzerApp() {
    if (analyzerApp == null) {
      initialize();
    }
    return analyzerApp;
  }

  public static void main(String[] args) {
    analyzerApp.initialize();
  }

  public void initDataStructure(int points, int channels) {
    data = new int[points][channels];
  }

  private static void initialize() {
    analyzerApp = new AnalyzerApp();
    ConfigDirector configDirector = new ConfigDirector();
    Configuration configBuilder = new ConfigBuilder();
    configDirector.setBuilder(configBuilder);
    configDirector.readConfiguration();
    analyzerApp.config = configBuilder.getConfig();
  }

  public synchronized void updateData(int address, int[] dataPoint) {
    data[address]= dataPoint;
  }

  private void displayData() {
    // Allow enough time for the DataCollectorThread to init the data structure
    while (data == null) {}
    int position = 0;
    while (position < 20)
    {
      if (data[position][0] != 0) {
        System.out.println(data[position][0] + "\t" +
                data[position][1] + "\t" +
                data[position][2] + "\t");
        position++;
      }
      Thread.yield();
    }
  }


  public void routineProcessor(Routine routine) {
    currentRoutine = routine; // set current tracker
    while(routine.hasNext()) {
      Procedure procedure = routine.getNext();
      procedureProcessor(procedure);
    }
    currentRoutine = null; // Reset current tracker
  }

  public void procedureProcessor(Procedure procedure) {
    currentProcedure = currentProcedure; // set current tracker
    while(procedure.hasNext()) {
      ProcedureEvent procedureEvent = procedure.getNext();

      currentProcedureEvent = procedureEvent; // set current tracker
      Potentiostat potentiostat = new Potentiostat(procedureEvent);
      potentiostat.loadExperiment();
      potentiostat.startExperiment();
      potentiostat.collectData();
      displayData();
      currentProcedureEvent = null; // Reset current tracker
    }
    currentProcedure = null; // Reset current tracker
  }
}
