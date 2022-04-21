package edu.bu.met.cs665.app.configuration.types;

import edu.bu.met.cs665.app.configuration.Iteratable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the entire configuration of the system. Utilizes iterator pattern.
 */
public class SystemConfig implements Iteratable<Routine> {

  // Holds the different routines described the systemc configuration
  // The list serves to hold an order so we can iterate in order through the hashmap
  private HashMap<String,Routine> routines = new HashMap<>();
  private ArrayList<String> routineOrder = new ArrayList<>();

  // Iterator serves as the index of which item we are currently viewing
  private int iterator = 0;

  /**
   * Adds a new routine to the system configuration.
   * @param name
   */
  public void addRoutine(String name){
    routines.put(name,new Routine());
    routineOrder.add(name);
    setRoutineName(name,name);
  }

  /**
   * Returns a routine found by the StringKey in the hashmap.
   * @param name
   */
  public Routine getRoutine(String name){
    return routines.get(name);
  }

  /**
   * Set the name in routine object that holds teh routine file.
   * @param routine the routine to update
   * @param name the name to update
   */
  public void setRoutineName(String routine,String name){
    routines.get(routine).setName(name);
  }

  /**
   * Set the path in routine object that holds teh routine file.
   * TODO This file/path does not currently exist and data is faked in this project.
   * @param routine the routine to update
   * @param path the path to update
   */
  public void setRoutinePath(String routine,String path){
    routines.get(routine).setPath(path);
  }

  /**
   * Set the code associated with this routine object.
   * TODO This code does not currently exist is faked in this project.
   * @param routine the routine to update
   * @param code the path to update
   */
  public void setRoutineCode(String routine, String code){
    routines.get(routine).setCode(code);
  }

  /**
   * Returns an arraylist that holds the name/keys of all available analysis base on config.
   * @return ArrayList that hold the routine keys
   */
  public ArrayList<String> getRoutineOrder() {
    return routineOrder;
  }

  /**
   * The iterator pattern allows client to loop through using while loop conditional.
   * @return
   */
  @Override
  public boolean hasNext() {
    if (routineOrder.size() > 0 && iterator < routineOrder.size() )
      return true;
    else {
      reset(); // reset the iterator so we can loop again
      return false;
    }
  }

  /**
   * Returns the next available routine object for iterator pattern
   * @return
   */
  @Override
  public Routine getNext() {
    String routineName =  routineOrder.get(iterator);
    iterator++;
    return routines.get(routineName);
  }

  /**
   * Resets the iterator pointer so the iteration can start over
   */
  @Override
  public void reset() {
    iterator = 0;
  }
}
