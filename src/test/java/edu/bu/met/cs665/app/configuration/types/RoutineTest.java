package edu.bu.met.cs665.app.configuration.types;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the Routine class.
 */
public class RoutineTest {

  private Routine routine;

  @Before
  public void setUp() {
    routine = new Routine();
  }

  @After
  public void tearDown() {
    routine = null;
  }

  @Test
  public void getName() {
    routine.setName("test");
    assertEquals("test", routine.getName());
  }


  @Test
  public void getCode() {
    routine.setCode("testCode");
    assertEquals("testCode", routine.getCode());
  }



  @Test
  public void getPath() {
    routine.setPath("testPath");
    assertEquals("testPath", routine.getPath());
  }



  @Test
  public void getProcedures() {
    routine.createProcedures(5);
    Procedure[] procedures = routine.getProcedures();
    assertEquals(5,procedures.length);
  }

  @Test
  public void createProcedures() {
    routine.createProcedures(5);
    int counter = 0;
    while (routine.hasNext()) {
      routine.getNext();
      counter++;
    }
    assertEquals(5,counter);

  }

  /**
   * check if hasNext will give a false when there isn't any and then add some and check again which should be true.
   */
  @Test
  public void hasNext() {
    routine.createProcedures(0);
    assertEquals(false,routine.hasNext());
    routine.createProcedures(1);
    assertEquals(true,routine.hasNext());
  }

  /**
   * Add a procedureEvent and then getNext it and make sure you get ProcedureEvent type.
   */
  @Test
  public void getNext() {
    routine.createProcedures(1);
    Procedure procedure = routine.getNext();
    assertEquals(true, procedure instanceof Procedure);
  }

  /**
   * Add 5 procedure events and give the names.  Then iterate twice by using GetNext(), reset and see if the next
   * getNext return the first ProcedureEvent.
   */
  @Test
  public void reset() {
    routine.createProcedures(5);

    // Name the 5 created procedure events
    int counter = 0;
    while (routine.hasNext()) {
      Procedure procedure = routine.getNext();
      procedure.setName("procedure" + counter++);
    }

    // iterate twice
    routine.getNext();
    routine.getNext(); // after this you should be on the third procedure event
    routine.reset(); // Reset brings you back to beginning of the list

    // Check if you are the beginning of the list
    Procedure testProcedure =  routine.getNext();
    assertEquals("procedure0",testProcedure.getName());
  }
}