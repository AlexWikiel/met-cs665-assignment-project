package edu.bu.met.cs665.app;

import edu.bu.met.cs665.app.configuration.ConfigBuilder;
import edu.bu.met.cs665.app.configuration.ConfigDirector;
import edu.bu.met.cs665.app.configuration.Configuration;
import edu.bu.met.cs665.app.configuration.types.Routine;
import edu.bu.met.cs665.app.configuration.types.SystemConfig;

import java.util.ArrayList;

/**
 * The brain that connects all the other parts together, this is the main hub of the entire analyzer.
 */
public class AnalyzerApp {

  private static AnalyzerApp analyzerApp;
  private SystemConfig config;

  /**
   * Private constructor so we can use the singleton pattern
   */
  private AnalyzerApp() {}


  private ArrayList<int[]> data;
  private Object dataLock = new Object();

  public Object getDataLock() {
    return dataLock;
  }


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

  public void initDataStructure() {
   data = new ArrayList<int[]>();
  }

  private static void initialize() {
    analyzerApp = new AnalyzerApp();
    ConfigDirector configDirector = new ConfigDirector();
    Configuration configBuilder = new ConfigBuilder();
    configDirector.setBuilder(configBuilder);
    configDirector.readConfiguration();
    analyzerApp.config = configBuilder.getConfig();
  }

  public synchronized void updateData(int[] dataPoint) {
    synchronized (dataLock) {
      data.add(dataPoint);
    }
  }

  public ArrayList<int[]> getData() {
    return data;
  }


  public void routineProcessor(Routine routine) {
    Thread routineThread = new Thread(new RoutineProcessor(routine));
    routineThread.setDaemon(true);
    routineThread.setName("routineProcessor");
    routineThread.start();
  }

  /**
   * This  overwrites the instastance of AnalyzerApp, and is used for unit testing.
   */
  public static void resetAnalazerApp() {
    analyzerApp = new AnalyzerApp();
  }
}
