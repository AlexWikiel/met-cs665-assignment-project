package edu.bu.met.cs665.app.configuration3;

/**
 * Defines the necessary components to use an iterator pattern
 * @param <T> objects to iterate through
 */
public interface Iterator<T> {
  boolean hasNext();

  T getNext();

  void reset();
}
