package edu.bu.met.cs665.app.analyzer;

/**
 * This is the controller for the states, which uses the state pattern and allows the necessary
 * files to be loaded in sequence to the external controller.
 */
public class ExperimentLoader {
  private LoadState loadState;

  /**
   * Load initial state.
   * @param loadState initial state.
   */
  public ExperimentLoader(LoadState loadState) {
    transitionTo(loadState);
  }

  /**
   * Switch state.
   * @param state to transition to.
   */
  public void transitionTo(LoadState state) {
    this.loadState = state;
    this.loadState.setExperimentLoader(this);
    //execute();
  }

  /**
   * Run the state.
   */
  public void execute() {
    loadState.load();
  }
}
