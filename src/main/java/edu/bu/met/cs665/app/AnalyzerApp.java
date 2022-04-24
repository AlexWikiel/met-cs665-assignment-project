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
   * Private constructor so we can use the singleton pattern.
   */
  private AnalyzerApp() {}

  // holds data that is updated by the dataCollectorThread which can be used to display to user
  private ArrayList<int[]> data;
  private Object dataLock = new Object();  // lock for data

  /**
   * Allows other threads to obtain the lock.
   * @return
   */
  public Object getDataLock() {
    return dataLock;
  }

  /**
   * Returns the system configuration object that hold the analysis specs.
   * @return SystemConfig object
   */
  public SystemConfig getConfig(){
    return config;
  }

  /**
   * returns the singleton and inits it if it is null.
   * @return
   */
  public static AnalyzerApp getAnalyzerApp() {
    if (analyzerApp == null) {
      initialize();
    }
    return analyzerApp;
  }

  /**
   * builds the data structure to get it ready to receive data.
   */
  public void initDataStructure() {
   data = new ArrayList<int[]>();
  }

  /**
   * We need to get the analyzer ready.  This method will tell the analyzer to read-in and build the system
   * configuration object
   */
  private static void initialize() {
    analyzerApp = new AnalyzerApp();
    ConfigDirector configDirector = new ConfigDirector();
    Configuration configBuilder = new ConfigBuilder();
    configDirector.setBuilder(configBuilder);
    configDirector.readConfiguration();
    analyzerApp.config = configBuilder.getConfig();
  }

  /**
   * Entry point for dataCollectorThread to communicate the data back to main thread.
   * @param dataPoint data collected from analyzer
   */
  public synchronized void updateData(int[] dataPoint) {
    synchronized (dataLock) {
      data.add(dataPoint);
    }
  }

  /**
   * Allows other clients to access the collected data.
   * @return
   */
  public ArrayList<int[]> getData() {
    return data;
  }

  /**
   * Takes the routines and runs each procedure individually.
   * @param routine
   */
  public void startRoutineProcessor(Routine routine) {
    Thread routineThread = new Thread(new RoutineProcessor(routine));
    routineThread.setDaemon(true);
    routineThread.setName("routineProcessor");
    routineThread.start();
  }

  /**
   * This  overwrites the instance of AnalyzerApp, and is used for unit testing.
   */
  public static void resetAnalyzerApp() {
    analyzerApp = null;
  }
}
