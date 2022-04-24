package edu.bu.met.cs665.app.configuration.types;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Test for SystemConfig class
 */
public class SystemConfigTest {

  SystemConfig systemConfig;

  @Before
  public void setUp() {
    systemConfig = new SystemConfig();
    systemConfig.addRoutine("test1");
    systemConfig.addRoutine("test2");
    systemConfig.addRoutine("test3");
    systemConfig.addRoutine("test4");
  }

  @After
  public void tearDown() {
    systemConfig = null;
  }

  /**
   * Get one the routines add in the setUp and check if the returned object is a Routine.
   */
  @Test
  public void getRoutine() {
    Object testRoutine =  systemConfig.getRoutine("test1");
    Assert.assertEquals(true, testRoutine instanceof Routine);
  }

  /**
   * Get a routine and check if the name matches the expected name that it was created with.
   */
  @Test
  public void setRoutineName() {
    Routine testRoutine =  systemConfig.getRoutine("test2");
    Assert.assertEquals("test2", testRoutine.getName());
  }

  /**
   * Set the path and then get the routine and check if the path was updated.
   */
  @Test
  public void setRoutinePath() {
    systemConfig.setRoutinePath("test3","c:\\setPathTest");
    Routine testRoutine =  systemConfig.getRoutine("test3");
    Assert.assertEquals("c:\\setPathTest", testRoutine.getPath());
  }

  /**
   * Set the code in a routine and then grab the routine and check if it was updated.
   */
  @Test
  public void setRoutineCode() {
    systemConfig.setRoutineCode("test4","TT");
    Routine testRoutine =  systemConfig.getRoutine("test4");
    Assert.assertEquals("TT", testRoutine.getCode());
  }

  /**
   * Get the order and iterate that the routine names are in the correct order as expected.
   */
  @Test
  public void getRoutineOrder() {
    ArrayList<String> testObject = systemConfig.getRoutineOrder();
    for (int i=1;i<=testObject.size();i++) {
      Assert.assertEquals("test"+i, testObject.get(i-1));
    }
  }

  /**
   * check if hasNext will give a false when there isn't any and then add some and check again which should be true.
   */
  @Test
  public void hasNext() {
    assertEquals(true,systemConfig.hasNext());
    systemConfig.getNext();
    systemConfig.getNext();
    systemConfig.getNext();
    systemConfig.getNext();
    assertEquals(false,systemConfig.hasNext());
  }

  /**
   * Add a procedureEvent and then getNext  and make sure you get a Routine type.
   */
  @Test
  public void getNext() {
    Object routine = systemConfig.getNext();
    assertEquals(true, routine instanceof Routine);
  }

  /**
   * Iterate twice by using GetNext(), reset and see if the next
   * getNext return the first Routine.
   */
  @Test
  public void reset() {
    // iterate twice
    systemConfig.getNext();
    systemConfig.getNext(); // after this you should be on the third routine event
    systemConfig.reset(); // Reset brings you back to beginning of the list

    // Check if you are the beginning of the list
    Routine testRoutine =  systemConfig.getNext();
    assertEquals("test1",testRoutine.getName());
  }
}