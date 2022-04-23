package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.AnalyzerApp;
import edu.bu.met.cs665.app.AnalyzerStatus;
import edu.bu.met.cs665.app.SystemStatus;

import java.util.Random;

/**
 * Runs on a designated thread and repeatedly pings the external analyzer to get data as it is ready.
 * This version of data collector is simple simulation that generates 20 data points with Java's random function.
 */
public class DataCollector implements Runnable{
  // used for fake data creation
  private Random random = new Random();
  private final int  bound = 200;

  // data structure
  private int[][] data;
  // flag to that can break the polling loop
  private Boolean keepPolling = false;


  /**
   * This data is faked for this project and we added an artificial sleep to mimic
   * real word operation.
   */
  @Override
  public void run() {
    System.out.println("Query potentiostat for results...");
    keepPolling = true; // set polling flag to true
    data = new int[20][3]; // int data
    AnalyzerApp.getAnalyzerApp().initDataStructure(); // init main thread data structure

    int dataPointer = 0; // keeps track of which point we are recording for indexing
    while(keepPolling) {
      //System.out.println(dataPointer);  //used to debugging

      // when we hit 20 points we are done and leaving the loop
      // in this case  setting keepPolling to false is not necessary, but this was created
      // for when this class will be properly implemented then all exits will be via while conditional
      // and not breaking from loop, the break is simply a short-circuit could be a continue as well.
      if (dataPointer >= 20) {
        keepPolling=false;
        break;
      }
      // generate fake data
      int chOne = this.random.nextInt(bound);
      int chTwo = this.random.nextInt(bound);
      // record fake data
      data[dataPointer][0] = dataPointer + 1; // fake position but not zero-indexed
      data[dataPointer][1] = chOne; // fake ch1 data
      data[dataPointer][2] = chTwo; // fake ch2 data

      // update main thread
      postData(data[dataPointer]);
      dataPointer++;

      // a sleep is here simply to slow down the simulation for a more realistic affect, in real life
      // data point are generated considerably slower than this loop executes. 100millis works nicely
      // and looks more realistic to a real analyzer.
      try {
        Thread.sleep(0);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      // Let other threads do some work
      Thread.yield();
    }
    // Analyzer is done with this experiment, inform the rest of the program
    AnalyzerStatus.getAnalyzerStatus().setSystemStatus(SystemStatus.processingRoutine);
  }

  /**
   * Update the main thread data structure, so it has access to it.
   * @param data the datapoint to update.
   */
  private void postData(int[] data){
    AnalyzerApp.getAnalyzerApp().updateData(data);
  }

  /**
   * Stop the loop from continuing. If we had a stop procedure this would kill the loop, effectively ending  this
   * thread.
   */
  public synchronized void stopPolling() {
    keepPolling = false;
  }
}
