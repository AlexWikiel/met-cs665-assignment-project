package edu.bu.met.cs665.app.analyzer;


public class ExperimentLoader {
  private LoadState loadState;

  /**
   * This is the controller for the states, which use dthe state pattern and allows the necessary files to be loaded
   * in sequence to the external controller.
   * @param loadState
   */
  public ExperimentLoader(LoadState loadState) {
    transitionTo(loadState);
  }

  /**
   * Switch state.
   * @param state
   */
  public void transitionTo(LoadState state) {
    this.loadState = state;
    this.loadState.setExperimentLoader(this);
    execute();
  }

  /**
   * Run the state.
   */
  public void execute() {
    loadState.load();
  }
}
