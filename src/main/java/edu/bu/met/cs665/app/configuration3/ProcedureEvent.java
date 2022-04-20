package edu.bu.met.cs665.app.configuration3;

/**
 * This class holds the file paths of the files that would need to be transmitted to the analyzer.  These files hold all
 * the parameters to run an experiment.
 */
public class ProcedureEvent {

  private String name;
  private String fillingPath;
  private String methodPath;
  private String instrumentPath;
  private String stepPath;

  public String getName() {
    return name;
  }

  /**
   * sets the name of the procedure event, this can only be set once when its null.
   * @param name or procedure event
   */
  public void setName(String name) {
    if (this.name == null) {
      this.name = name;
    }
  }

  public String getFillingPath() {
    return fillingPath;
  }

  /**
   * sets the filling path of the procedure event, this can only be set once when its null.
   * @param fillingPath or procedure event
   */
  public void setFillingPath(String fillingPath) {
    if (this.fillingPath == null) {
      this.fillingPath = fillingPath;
    }
  }

  public String getMethodPath() {
    return methodPath;
  }

  /**
   * sets the method path of the procedure event, this can only be set once when its null.
   * @param methodPath or procedure event
   */
  public void setMethodPath(String methodPath) {
    if (this.methodPath == null) {
      this.methodPath = methodPath;
    }
  }

  public String getInstrumentPath() {
    return instrumentPath;
  }

  /**
   * sets the instrument path of the procedure event, this can only be set once when its null.
   * @param instrumentPath or procedure event
   */
  public void setInstrumentPath(String instrumentPath) {
    if (this.instrumentPath == null) {
      this.instrumentPath = instrumentPath;
    }
  }

  public String getStepPath() {
    return stepPath;
  }

  /**
   * sets the step path of the procedure event, this can only be set once when its null.
   * @param stepPath or procedure event
   */
  public void setStepPath(String stepPath) {
    if (this.fillingPath == null) {
      this.fillingPath = stepPath;
    }
  }
}