package edu.bu.met.cs665.app.configuration.types;

import edu.bu.met.cs665.app.configuration.Iteratable;

/**
 * The class holds information about the entire procedure, including name and path plus the procedure events that
 * make it up.  We utilize the iterator pattern to make iteration of the procedure events simpler.
 */
public class Procedure implements Iteratable {

  private String name;
  private String path;
  private ProcedureEvent[] procedureEvents;

  // Iterator serves as the index of which item we are currently viewing and is necessary for the iterator pattern
  int iterator = 0;

  public String getName() {
    return name;
  }

  /**
   * sets the name of the procedure, this can only be set once when its null.
   * @param name or procedure event
   */
  public void setName(String name) {
    if (this.name == null) {
      this.name = name;
    }
  }

  public String getPath() {
    return path;
  }

  /**
   * sets the path of the procedure, this can only be set once when its null.
   * @param path or procedure event
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * returns the array that holds all the procedure events for this procedure.
   * @return procedure events array
   */
  public ProcedureEvent[] getProcedureEvents() {
    return procedureEvents;
  }

  /**
   * sizes the array that hold procedure events and initializes each object.
   * @param count number of procedure events
   */
  public void createProcedureEvents(int count) {
    this.procedureEvents = new ProcedureEvent[count];
    for (int i=0; i<count; i++){
      this.procedureEvents[i] = new ProcedureEvent();
    }
  }


  /**
   * checks if another procedure event is in the array.  Utilizes the iterator pattern.
   * @return
   */
  @Override
  public boolean hasNext() {
    if (procedureEvents.length > 0 && iterator < procedureEvents.length ) {
      return true;
    } else {
      reset(); // reset the iterator so we can loop again
      return false;
    }
  }

  /**
   * returns the next queued procedure event, must check if one is available with hasNext() first.
   * Utilizes the iterator pattern
   * @return
   */
  @Override
  public ProcedureEvent getNext() {
    ProcedureEvent proc =  procedureEvents[iterator];
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
