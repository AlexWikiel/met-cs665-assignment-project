package edu.bu.met.cs665.app.configuration.readertypes;

import edu.bu.met.cs665.app.configuration.ConfigReader;
import edu.bu.met.cs665.app.configuration.Configuration;
import edu.bu.met.cs665.app.configuration.types.SystemConfiguration;

public class SystemConfigReader extends ConfigReader {

  @Override
  protected void setFileName() {
    fileName = "SystemConfig.cfg";
  }

  @Override
  protected Configuration getConfigurationType() {
    return new SystemConfiguration();
  }
}
