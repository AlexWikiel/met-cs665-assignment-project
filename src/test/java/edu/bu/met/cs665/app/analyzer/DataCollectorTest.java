package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.AnalyzerApp;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Tests for DataCollector Class.
 */
public class DataCollectorTest {

  private DataCollector dataCollector;
  private ArrayList<int[]> data;

  @Before
  public void setUp() throws Exception {
    AnalyzerApp.resetAnalazerApp();
    dataCollector = new DataCollector();
  }

  @After
  public void tearDown() throws Exception {
    dataCollector = null;
  }


  /**
   * Collect data run on main thread when it's done, check if AnalyzerApp has been updated.
   */
  @Test
  public void run() {
    dataCollector.run();
    data = AnalyzerApp.getAnalyzerApp().getData();
    Assert.assertNotEquals(null,data);
  }

  /**
   * Start the thread and stop it, the data structure in AnalyzerApp should not be null.
   * @throws InterruptedException
   */
  @Test
  public void stopPolling() throws InterruptedException {
    Thread dataCollectorTestThread = new Thread(dataCollector);
    dataCollectorTestThread.start();
    dataCollector.stopPolling();
    dataCollectorTestThread.join();
    Thread.State d = dataCollectorTestThread.getState();
    data = AnalyzerApp.getAnalyzerApp().getData();
    Assert.assertNotEquals(null,data);
  }
}