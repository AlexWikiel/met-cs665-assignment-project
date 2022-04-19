package edu.bu.met.cs665.app.analyzer;


public class ExperimentLoader {
  private LoadState loadState;

  public ExperimentLoader(LoadState loadState) {
    transitionTo(loadState);
  }

  public void transitionTo(LoadState state) {
    this.loadState = state;
    this.loadState.setExperimentLoader(this);
    execute();
  }

  public void execute() {
    loadState.load();
  }
}
