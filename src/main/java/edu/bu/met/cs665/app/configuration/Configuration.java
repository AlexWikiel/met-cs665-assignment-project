package edu.bu.met.cs665.app.configuration;

import edu.bu.met.cs665.app.configuration.types.SystemConfig;

/**
 * the set of methods of how we must build the configuration object. Utilizing the Builder Pattern
 */
public interface Configuration {

  void readSystemConfig();

  void readRoutine();

  void readProcedure();

  SystemConfig getConfig();
}
