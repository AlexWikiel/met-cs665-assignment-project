package edu.bu.met.cs665.app.configuration.readertypes;

import edu.bu.met.cs665.app.configuration.ConfigReader;
import edu.bu.met.cs665.app.configuration.Configuration;
import edu.bu.met.cs665.app.configuration.types.Analysis;

public class AnalysisReader extends ConfigReader {
  @Override
  protected void setFileName() {
    fileName = "Analysis.cfg";
  }

  @Override
  protected Configuration getConfigurationType() {
    return new Analysis();
  }
}

