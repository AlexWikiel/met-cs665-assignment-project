package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.AnalyzerApp;
import edu.bu.met.cs665.app.AnalyzerStatus;
import edu.bu.met.cs665.app.SystemStatus;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Tests for Potentiostat class which also test the Analyzer Abstract class.
 */
public class PotentiostatTest {

  Potentiostat potentiostat;

  @Before
  public void setUp() {
    AnalyzerApp.resetAnalyzerApp();
    AnalyzerStatus.resetAnalyzerStatus();
    ProcedureEvent procedureEvent = new ProcedureEvent();
    procedureEvent.setName("potentiostatTest");
    potentiostat = new Potentiostat(procedureEvent);
  }

  @After
  public void tearDown() {
    potentiostat = null;
  }

  /**
   * All four files should be loaded the last status update should be uploadStep, we know that all 4 files went
   * through their upload procedures.
   */
  @Test
  public void loadExperiment() {
    potentiostat.loadExperiment();
    SystemStatus status = AnalyzerStatus.getAnalyzerStatus().getSystemStatus();
    Assert.assertEquals(SystemStatus.uploadStep, status);
  }

  /**
   * The status should be updated after startExperiment is invoked.
   */
  @Test
  public void startExperiment() {
    potentiostat.startExperiment();
    SystemStatus status = AnalyzerStatus.getAnalyzerStatus().getSystemStatus();
    Assert.assertEquals(SystemStatus.runningExperiment, status);
  }

  /**
   * Collecting data can be easily tested in our simulation app, but might need to be thoroughly thought through for a
   * real implementation.  Here we spin up a thread and wait for it to create its simulated data.  Then we check if data
   * was instantiated in the analyzerApp singleton.
   */
  @Test
  public void collectData() {
    potentiostat.collectData();
    Thread dataCollectorThread = potentiostat.getDataCollectorThread();
    try {
      dataCollectorThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ArrayList<int[]> data = AnalyzerApp.getAnalyzerApp().getData();
    Assert.assertNotEquals(null,data);
  }

  /**
   * Spin up a thread, get it and check the name.
   */
  @Test
  public void getDataCollectorThread() {
    potentiostat.collectData();
    Thread dataCollectorThread = potentiostat.getDataCollectorThread();
    Assert.assertEquals("DataCollectorThread",dataCollectorThread.getName());
 }
}