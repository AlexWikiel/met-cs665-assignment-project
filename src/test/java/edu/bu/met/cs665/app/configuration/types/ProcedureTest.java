package edu.bu.met.cs665.app.configuration.types;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the procedure class.
 */
public class ProcedureTest {

  Procedure procedure;

  @Before
  public void setUp() {
    procedure = new Procedure();
  }

  @After
  public void tearDown() {
    procedure = null;
  }

  /**
   * Run setName and then getName check if they are the same.
   */
  @Test
  public void getName() {
    procedure.setName("test");
    assertEquals("test", procedure.getName());
  }

  /**
   * Run setPath and then getPath check if they are the same.
   */
  @Test
  public void getPath() {
    procedure.setPath("testPath");
    assertEquals("testPath", procedure.getPath());
  }


  /**
   * Create 5 procedure events and then count them to compare if 5 were created.
   */
  @Test
  public void createProcedureEvents() {
    procedure.createProcedureEvents(5);
    int counter = 0;
    while (procedure.hasNext()) {
      procedure.getNext();
      counter++;
    }
    assertEquals(5,counter);
  }

  /**
   * check if hasNext will give a false when there isn't any and then add some and check again which should be true.
   */
  @Test
  public void hasNext() {
    procedure.createProcedureEvents(0);
    assertEquals(false,procedure.hasNext());
    procedure.createProcedureEvents(1);
    assertEquals(true,procedure.hasNext());
  }

  /**
   * Add a procedureEvent and then getNext it and make sure you get ProcedureEvent type.
   */
  @Test
  public void getNext() {
    procedure.createProcedureEvents(1);
    ProcedureEvent procedureEvent = procedure.getNext();
    assertEquals(true, procedureEvent instanceof ProcedureEvent);
  }

  /**
   * Add 5 procedure events and give the names.  Then iterate twice by using GetNext(), reset and see if the next
   * getNext return the first ProcedureEvent.
   */
  @Test
  public void reset() {
    procedure.createProcedureEvents(5);

    // Name the 5 created procedure events
    int counter = 0;
    while (procedure.hasNext()) {
      ProcedureEvent proceduruEvent = procedure.getNext();
      proceduruEvent.setName("procedure" + counter++);
    }

    // iterate twice
    procedure.getNext();
    procedure.getNext(); // after this you should be on the third procedure event
    procedure.reset(); // Reset brings you back to beginning of the list

    // Check if you are the beginning of the list
    ProcedureEvent testProcedureEvent =  procedure.getNext();
    assertEquals("procedure0",testProcedureEvent.getName());
  }
}