package edu.bu.met.cs665.app.configuration;

/**
 * This class reads routine file.
 * TODO This is a fake implementation for this project to create dummy data.
 */
public class RoutineReader {
  private Routine routine;

  public RoutineReader(Routine routine) {
    this.routine = routine;
  }

  public void read() {
    routine.createProcedures(5);
    int pathIndex = 1; // Only to generate fake names
    while(routine.hasNext()) {
      Procedure procedure = routine.getNext();
      procedure.setName("fakeProcedure_" + pathIndex);
      procedure.setPath("c:\\fake\\routine\\procedure" + pathIndex);
      pathIndex++;
    }
  }
}
