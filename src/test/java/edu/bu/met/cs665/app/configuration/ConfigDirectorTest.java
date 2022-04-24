package edu.bu.met.cs665.app.configuration;

import edu.bu.met.cs665.app.configuration.types.SystemConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test The config director.
 */
public class ConfigDirectorTest {

  Configuration configuration;
  ConfigDirector configDirector;

  @Before
  public void setUp() {
    configuration = new ConfigBuilder();
    configDirector = new ConfigDirector();
  }

  @After
  public void tearDown() {
    configuration = null;
    configDirector = null;
  }

  /**
   * We have to test setBuilder and read configuration together.  Inject the configuration and the run
   * readConfiguration() we can then check if a SystemConfig object was created as expected.
   */
  @Test
  public void setBuilder() {
    configDirector.setBuilder(configuration);
    configDirector.readConfiguration();
    Object systemConfig = configuration.getConfig();
    assertEquals(true, systemConfig instanceof SystemConfig);
  }
}