package edu.bu.met.cs665.app.configuration.readertypes;

import edu.bu.met.cs665.app.configuration.types.SystemConfig;

/**
 * This class reads main configuration. This is a fake implementation for this project to create dummy data.
 */
public class SystemConfigReader {
  private SystemConfig systemConfig;

  public SystemConfigReader(SystemConfig systemConfig) {
    this.systemConfig = systemConfig;
  }

  public void read() {
    systemConfig.addRoutine("fakeAnalysis_1");
    systemConfig.setRoutinePath("fakeAnalysis_1","c:\\fake\\RoutinePath1");
    systemConfig.setRoutineCode("fakeAnalysis_1","fk_1");

    systemConfig.addRoutine("fakeAnalysis_2");
    systemConfig.setRoutinePath("fakeAnalysis_2","c:\\fake\\RoutinePath2");
    systemConfig.setRoutineCode("fakeAnalysis_2","fk_2");

    systemConfig.addRoutine("fakeAnalysis_3");
    systemConfig.setRoutinePath("fakeAnalysis_3","c:\\fake\\RoutinePath3");
    systemConfig.setRoutineCode("fakeAnalysis_3","fk_3");
  }
}
