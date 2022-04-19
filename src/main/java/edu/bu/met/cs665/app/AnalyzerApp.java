package edu.bu.met.cs665.app;

import edu.bu.met.cs665.app.analyzer.Potentiostat;

// Main Class
public class AnalyzerApp {

  private static AnalyzerApp analyzerApp = new AnalyzerApp();

  private AnalyzerApp() {}

  static AnalyzerApp getAnalyzerApp(){
    return analyzerApp;
  }

  public static void main(String[] args) {
    analyzerApp.initialize();


  }

  private void initialize() {
    //Config.getConfig();
    //ExperimentLoader experimentLoader = new ExperimentLoader(new LoadinstrumentState());

    //ExperimentLoader experimentLoader = new ExperimentLoader(new LoadInstrumentState());
    //experimentLoader.execute();
    Potentiostat potentiostat = new Potentiostat(null);
    potentiostat.loadExperiment();




  }

}
