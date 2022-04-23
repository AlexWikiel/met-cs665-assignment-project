package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.AnalyzerStatus;
import edu.bu.met.cs665.app.SystemStatus;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExperimentLoaderTest {

  ExperimentLoader testExperimentLoader;
  LoadInstrumentState testLoadInstrumentState;
  ProcedureEvent testProcedureEvent;

  @Before
  public void setUp() throws Exception {
    AnalyzerStatus.resetAnalazerApp();
    testProcedureEvent = new ProcedureEvent();
    testLoadInstrumentState = new LoadInstrumentState(testProcedureEvent);
    testExperimentLoader = new ExperimentLoader(testLoadInstrumentState);
  }

  @After
  public void tearDown() throws Exception {
    testExperimentLoader = null;
    testLoadInstrumentState = null;
    testProcedureEvent = null;
  }

  @Test
  public void transitionTo() {
    testExperimentLoader.transitionTo(new LoadMethodState(testProcedureEvent));
    testExperimentLoader.execute();
    SystemStatus status = AnalyzerStatus.getAnalyzerStatus().getSystemStatus();
    Assert.assertEquals(SystemStatus.uploadMethod,status);
  }

  @Test
  public void execute() {
    testExperimentLoader = new ExperimentLoader(testLoadInstrumentState);
    testExperimentLoader.execute();
    SystemStatus status = AnalyzerStatus.getAnalyzerStatus().getSystemStatus();
    Assert.assertEquals(SystemStatus.uploadInstrument,status);
  }
}