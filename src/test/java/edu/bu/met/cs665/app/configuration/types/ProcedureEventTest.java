package edu.bu.met.cs665.app.configuration.types;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the ProcedureEvent class.
 */
public class ProcedureEventTest {

  private ProcedureEvent procedureEvent;

  @Before
  public void setUp() {
    procedureEvent = new ProcedureEvent();
  }

  @After
  public void tearDown() {
    procedureEvent = null;
  }

  /**
   * Run setName and then getName check if they are the same.
   */
  @Test
  public void getName() {
    procedureEvent.setName("test");
    assertEquals("test", procedureEvent.getName());
  }

  /**
   * Run setFillingPath and then getFillingPath check if they are the same.
   */
  @Test
  public void getFillingPath() {
    procedureEvent.setFillingPath("testFillingPath");
    assertEquals("testFillingPath", procedureEvent.getFillingPath());
  }

  /**
   * Run setMethodPath and then getMethodPath check if they are the same.
   */
  @Test
  public void getMethodPath() {
    procedureEvent.setMethodPath("testMethodPath");
    assertEquals("testMethodPath", procedureEvent.getMethodPath());
  }

  /**
   * Run setInstrumentPath and then getInstrumentPath check if they are the same.
   */
  @Test
  public void getInstrumentPath() {
    procedureEvent.setInstrumentPath("testInstrumentPath");
    assertEquals("testInstrumentPath", procedureEvent.getInstrumentPath());
  }

  /**
   * Run setStepPath and then getStepPath check if they are the same.
   */
  @Test
  public void getStepPath() {
    procedureEvent.setStepPath("testStepPath");
    assertEquals("testStepPath", procedureEvent.getStepPath());
  }
}