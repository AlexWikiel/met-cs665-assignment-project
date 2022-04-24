package edu.bu.met.cs665.app.configuration;

/**
 * The director class serves as a place to control the builder pattern that will create the
 * configuration based on the specified Configuration.
 */
public class ConfigDirector {
  private Configuration configuration;

  /**
   * Inject the configuration builder into the director.
   * @param configuration configBuilder.
   */
  public void setBuilder(Configuration configuration) {
    this.configuration = configuration;
  }

  /**
   * Execute the builder.
   */
  public void readConfiguration() {
    this.configuration.readSystemConfig();
    this.configuration.readRoutine();
    this.configuration.readProcedure();
  }
}
