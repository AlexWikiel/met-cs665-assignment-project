package edu.bu.met.cs665.app.configuration.types;

import edu.bu.met.cs665.app.configuration.Iteratable;

/**
 * The class holds information about the entire routine, including name, code, and path plus the procedures that
 * make it up.  We utilize the iterator pattern to make iteration of the procedures simpler.
 */
public class Routine implements Iteratable<Procedure> {

  private String name;
  private String path;
  private String code;
  private Procedure[] procedures;

  // Iterator serves as the index of which item we are currently viewing and is necessary for the iterator pattern
  int iterator = 0;

  public String getName() {
    return name;
  }

  /**
   * sets the name of the routine, this can only be set once when its null.
   * @param name or procedure event
   */
  public void setName(String name) {
    if (this.name == null) {
    this.name = name;
    }
  }

  public String getCode() {
    return code;
  }

  /**
   * sets the code of the routine, this can only be set once when its null.
   * @param code or procedure event
   */
  public void setCode(String code) {
    if (this.name == null) {
    this.code = code;
    }
  }

  public String getPath() {
    return path;
  }

  /**
   * sets the path of the routine, this can only be set once when its null.
   * @param path or procedure event
   */
  public void setPath(String path) {
    if (this.name == null) {
    this.path = path;
    }
  }

  /**
   * returns the array that holds all the procedures for this routine.
   * @return procedure array
   */
  public Procedure[] getProcedures() {
    return procedures;
  }

  /**
   * sizes the array that hold procedures and initializes each object.
   * @param count number of procedure
   */
  public void createProcedures(int count) {
    this.procedures = new Procedure[count];
      for (int i=0; i<count; i++){
        this.procedures[i] = new Procedure();
      }
  }

  /**
   * checks if another procedure is in the array.  Utilizes the iterator pattern.
   * @return
   */
  @Override
  public boolean hasNext() {
    if (procedures.length > 0 && iterator < procedures.length ) {
      return true;
    } else {
      reset(); // reset the iterator so we can loop again
      return false;
    }
  }

  /**
   * returns the next queued procedure, must check if one is available with hasNext() first.
   * Utilizes the iterator pattern
   * @return
   */
  @Override
  public Procedure getNext() {
    Procedure proc =  procedures[iterator];
    iterator++;
    return proc;
  }

  /**
   * resets the iterator pointer, so that iteration can start over.
   * @return
   */
  @Override
  public void reset() {
    iterator = 0;
  }
}
