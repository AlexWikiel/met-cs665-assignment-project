package edu.bu.met.cs665.app;

import edu.bu.met.cs665.app.configuration.types.Procedure;
import edu.bu.met.cs665.app.configuration.types.ProcedureEvent;
import edu.bu.met.cs665.app.configuration.types.Routine;
import java.util.ArrayList;

/**
 * Statuses of current states of the app. This can be used for potential GUI.
 */
public class AnalyzerStatus implements StatusInformer {

  private AnalyzerStatus() {
  }

  private static AnalyzerStatus analyzerStatus;
  private SystemStatus systemStatus = SystemStatus.idle;
  private Routine currentRoutine;
  private Procedure currentProcedure;
  private ProcedureEvent currentProcedureEvent;

  /**
   * returns an instance of the singleton AnalyzerStatus.
   * @return
   */
  public static AnalyzerStatus getAnalyzerStatus() {
    if (analyzerStatus == null) {
      analyzerStatus = new AnalyzerStatus();
    }
    return analyzerStatus;
  }

  public SystemStatus getSystemStatus() {
    return systemStatus;
  }

  public void setSystemStatus(SystemStatus systemStatus) {
    this.systemStatus = systemStatus;
    update("System status has changed to: " + systemStatus.toString());
  }

  /**
   * Returns the current routine that was selected.
   * @return
   */
  public Routine getCurrentRoutine() {
    return currentRoutine;
  }

  /**
   * Set the current selected routine.
   * @param currentRoutine selected.
   */
  public void setCurrentRoutine(Routine currentRoutine) {
    this.currentRoutine = currentRoutine;
    if (currentRoutine != null) {
      update("Current Selected Routine is : " + currentRoutine.getName());
    } else {
      update("*** Routine Complete...");
    }
  }

  /**
   * Returns the currently selected routine.
   * @return
   */
  public Procedure getCurrentProcedure() {
    return currentProcedure;
  }

  /**
   * Set the current routine that is running.
   * @param currentProcedure that is running.
   */
  public void setCurrentProcedure(Procedure currentProcedure) {
    this.currentProcedure = currentProcedure;
    if (currentProcedure != null) {
      update("Current Procedure is : " + currentProcedure.getName());
    } else {
      update("*** Procedure Complete...");
    }
  }

  /**
   * get the current procedure event that is running.
   * @return
   */
  public ProcedureEvent getCurrentProcedureEvent() {
    return currentProcedureEvent;
  }

  /**
   * Set the current procedure event that is running.
   * @param currentProcedureEvent the procedure event that is running.
   */
  public void setCurrentProcedureEvent(ProcedureEvent currentProcedureEvent) {
    this.currentProcedureEvent = currentProcedureEvent;
    if (currentProcedureEvent != null) {
      update("Current Procedure Event is : " + currentProcedureEvent.getName());
    } else {
      update("*** Procedure Event Complete...");
    }
  }

  private ArrayList<Listener> listeners;

  @Override
  public void attach(Listener listener) {
    if (listeners == null) {
      listeners = new ArrayList<Listener>();
    }
    listeners.add(listener);
  }

  @Override
  public void detach(Listener listener) {
    listeners.remove(listener);
  }

  @Override
  public void update(String update) {
    if (listeners != null) {
      for (Listener listener : listeners) {
        listener.update(update);
      }
    }
  }

  /**
   * This  overwrites the instance of AnalyzerApp, and is used for unit testing.
   */
  public static void resetAnalyzerStatus() {
    analyzerStatus = new AnalyzerStatus();
  }

}
