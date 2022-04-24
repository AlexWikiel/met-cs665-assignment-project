package edu.bu.met.cs665.app.configuration;

import edu.bu.met.cs665.app.configuration.types.Procedure;
import edu.bu.met.cs665.app.configuration.types.Routine;
import edu.bu.met.cs665.app.configuration.types.SystemConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This test the ConfigBuilder class.  For the purposes of this project we can simply check if the simulated
 * configuration is made of the expected objects.
 * In a real implementation these tests would be harder to test and might require
 * some mock code not used in production for testing purposes.
 */
public class ConfigBuilderTest {

  private ConfigBuilder configBuilder;

  @Before
  public void setUp() {
    configBuilder = new ConfigBuilder();
    configBuilder.readSystemConfig();
    configBuilder.readRoutine();
  }

  @After
  public void tearDown()  {
    configBuilder = null;
  }

  /**
   * Check if returned object is an instance of the expected SystemConfig object.
   */
  @Test
  public void readSystemConfig() {
    Object systemConfig = configBuilder.getConfig();
    assertEquals(true,systemConfig instanceof SystemConfig);
  }

  /**
   * Dive deeper and get the systemConfig and then the first available in iterator and check if it is a Routine.
   */
  @Test
  public void readRoutine() {
    Object routine = configBuilder.getConfig().getNext();
    assertEquals(true,routine instanceof Routine);
  }

  /**
   * Dive even deeper and get the systemConfig and then the first available in iterator for routines and then getNext
   * which should be a procedures within the routine and check is the object is a procedure.
   */
  @Test
  public void readProcedure() {
    Object routine = configBuilder.getConfig().getNext().getNext();
    assertEquals(true,routine instanceof Procedure);
  }
}