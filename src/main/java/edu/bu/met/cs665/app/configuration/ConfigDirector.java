package edu.bu.met.cs665.app.configuration;

/**
 * The director class serves as a place to control the builder pattern that will create the configuration based on the
 * specified Configuration.
 *
 */
public class ConfigDirector {
  private Configuration configuration;

  public void setBuilder(Configuration configuration) {
    this.configuration = configuration;
  }

  public void readConfiguration() {
    this.configuration.readSystemConfig();
    this.configuration.readRoutine();
    this.configuration.readProcedure();
  }
}
