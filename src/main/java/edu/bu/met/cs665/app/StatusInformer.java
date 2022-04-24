package edu.bu.met.cs665.app;

public interface StatusInformer {

  void attach(Listener listener);

  void detach(Listener listener);

  void update(String update);

}
