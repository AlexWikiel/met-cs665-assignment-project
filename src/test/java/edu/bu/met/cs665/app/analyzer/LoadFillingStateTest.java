package edu.bu.met.cs665.app.analyzer;

import edu.bu.met.cs665.app.AnalyzerStatus;
import edu.bu.met.cs665.app.SystemStatus;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoadFillingStateTest {

  private LoadMethodState loadMethodState;

  @Before
  public void setUp() {
    AnalyzerStatus.resetAnalazerApp();
    loadMethodState = new LoadMethodState(new ProcedureEvent());
  }

  @After
  public void tearDown() {
    loadMethodState = null;
  }

  @Test
  public void setExperimentLoader() {
    loadMethodState.setExperimentLoader(new ExperimentLoader(loadMethodState));
    loadMethodState.load();
    SystemStatus status =  AnalyzerStatus.getAnalyzerStatus().getSystemStatus();
    assertEquals(SystemStatus.uploadMethod, status);
  }

  @Test
  public void load() {
    ExperimentLoader experimentLoader = new ExperimentLoader(loadMethodState);
    loadMethodState.load();
    SystemStatus status =  AnalyzerStatus.getAnalyzerStatus().getSystemStatus();
    assertEquals(SystemStatus.uploadMethod, status);
  }
}