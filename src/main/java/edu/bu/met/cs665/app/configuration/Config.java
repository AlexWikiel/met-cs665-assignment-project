package edu.bu.met.cs665.app.configuration;

import edu.bu.met.cs665.app.configuration.readertypes.SystemConfigReader;

public class Config {
  static Config config;

  private Configuration systemConfiguration;

  //private SystemConfigReader systemConfigReader;

  private Config(){}

  public static Config getConfig(){
    if (config == null) {
      config = new Config();
      config.loadConfig();
    }
    return config;
  }

  private void loadConfig() {
    ConfigReader systemConfigReader = new SystemConfigReader();
    this.systemConfiguration = systemConfigReader.getConfiguration();




  }
}
