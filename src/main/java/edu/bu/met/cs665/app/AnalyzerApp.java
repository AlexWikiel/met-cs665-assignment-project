package edu.bu.met.cs665.app;

// Main Class
public class AnalyzerApp {

  private static AnalyzerApp analyzerApp = new AnalyzerApp();

  private AnalyzerApp() {}

  private int[][] data;

  public static AnalyzerApp getAnalyzerApp(){
    return analyzerApp;
  }

  public static void main(String[] args) {
    analyzerApp.initialize();
  }

  public void initDataStructure(int points, int channels) {
    data = new int[points][channels];
  }

  private void initialize() {
    //Config.getConfig();
    //ExperimentLoader experimentLoader = new ExperimentLoader(new LoadinstrumentState());

    //ExperimentLoader experimentLoader = new ExperimentLoader(new LoadInstrumentState());
    //experimentLoader.execute();
//    Analyzer potentiostat = new Potentiostat(null);
//    potentiostat.loadExperiment();
//    potentiostat.startExperiment();
//    potentiostat.stopExperiment();
//    potentiostat.getControllerInfo();
//    potentiostat.collectData();

//    displayData();
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


}
