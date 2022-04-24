package edu.bu.met.cs665.app;

import edu.bu.met.cs665.app.configuration.types.Procedure;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;
import edu.bu.met.cs665.app.configuration.types.Routine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test AnalyzerStatus class.
 */
public class AnalyzerStatusTest {

  @Before
  public void setUp(){
    AnalyzerStatus.resetAnalyzerStatus();
  }

  @After
  public void tearDown(){
  }

  /**
   * get the analyzer class and make sure  it is an instance of AnalyzerStatus.
   */
  @Test
  public void getAnalyzerStatus() {
    Object analyzerStatusTest = AnalyzerStatus.getAnalyzerStatus();
    assertEquals(true, analyzerStatusTest instanceof AnalyzerStatus);
  }

  /**
   * Set The system status and then get it makes sure they match.
   */
  @Test
  public void getSystemStatus() {
    AnalyzerStatus.getAnalyzerStatus().setSystemStatus(SystemStatus.uploadFilling);
    SystemStatus status =  AnalyzerStatus.getAnalyzerStatus().getSystemStatus();
    assertEquals(SystemStatus.uploadFilling,status);
  }

  /**
   * Set the current Routine and get it and check if the match.
   */
  @Test
  public void getCurrentRoutine() {
    Routine routineSetTest = new Routine();
    routineSetTest.setName("AnalyzerStatusGetRoutineTest");
    AnalyzerStatus.getAnalyzerStatus().setCurrentRoutine(routineSetTest);
    Routine routineGetTest =  AnalyzerStatus.getAnalyzerStatus().getCurrentRoutine();
    assertEquals("AnalyzerStatusGetRoutineTest",routineGetTest.getName());
  }

  /**
   * Set the current procedure and get the current procedure and check match.
   */
  @Test
  public void getCurrentProcedure() {
    Procedure procedureSetTest = new Procedure();
    procedureSetTest.setName("AnalyzerStatusGetProcedureTest");
    AnalyzerStatus.getAnalyzerStatus().setCurrentProcedure(procedureSetTest);
    Procedure procedureGetTest =  AnalyzerStatus.getAnalyzerStatus().getCurrentProcedure();
    assertEquals("AnalyzerStatusGetProcedureTest",procedureGetTest.getName());
  }

  /**
   * Set the current procedure event and get it check if it matches.
   */
  @Test
  public void getCurrentProcedureEvent() {
    ProcedureEvent procedureEventSetTest = new ProcedureEvent();
    procedureEventSetTest.setName("AnalyzerStatusGetProcedureEventTest");
    AnalyzerStatus.getAnalyzerStatus().setCurrentProcedureEvent(procedureEventSetTest);
    ProcedureEvent procedureEventGetTest =  AnalyzerStatus.getAnalyzerStatus().getCurrentProcedureEvent();
    assertEquals("AnalyzerStatusGetProcedureEventTest",procedureEventGetTest.getName());
  }

  /**
   * Create a inner class implemnting listend and see if it updates when you fire the update.  This also tests
   * update().
   */
  @Test
  public void attach() {

    // make a listener on the fly
    class TestListener implements Listener {
      private String echo = "crickets...";

      @Override
      public void update(String update) {
        this.echo = update;
      }

      public String getEcho(){
        return echo;
      }
    }
    TestListener listener = new TestListener();

    // attach the listener
    AnalyzerStatus.getAnalyzerStatus().attach(listener);
    AnalyzerStatus.getAnalyzerStatus().update("Yodoleheehoo");
    assertEquals("Yodoleheehoo", listener.echo);
  }
}