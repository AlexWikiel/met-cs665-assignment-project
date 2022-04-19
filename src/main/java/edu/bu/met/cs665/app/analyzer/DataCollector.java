package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.AnalyzerApp;

import java.util.Random;

public class DataCollector implements Runnable{
  // used for fake data creation
  private Random random = new Random(20);
  private final int  bound = 200;
  private int[][] data;
  private Boolean keepPolling = false;


  /**
   * This data is faked for this project and we added an artificial sleep to mimic
   * real word operation.
   */
  @Override
  public void run() {
    System.out.println("Query potentiostat for results...");
    keepPolling = true;
    data = new int[20][3];
    AnalyzerApp.getAnalyzerApp().initDataStructure(20,3);

    int dataPointer = 0;
    while(keepPolling) {
      if (dataPointer >= 20) {
        keepPolling=false;
        //break;
      }
      int chOne = this.random.nextInt(bound);
      int chTwo = this.random.nextInt(bound);
      data[dataPointer][0] = dataPointer+1; // fake position but not zero-indexed
      data[dataPointer][1] = chOne; // fake ch1 data
      data[dataPointer][2] = chTwo; // fake ch2 data
      postData(dataPointer,data[dataPointer]);
      dataPointer++;

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void postData(int address, int[] data){
    AnalyzerApp.getAnalyzerApp().updateData(address, data);
  }

  private synchronized void stopPolling() {
    keepPolling = false;
  }
}
