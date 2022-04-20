package edu.bu.met.cs665.app.configuration3;

/**
 * This class reads procedure file.
 * TODO This is a fake implementation for this project to create dummy data.
 */
public class ProcedureReader {
  private Procedure procedure;

  public ProcedureReader(Procedure procedure) {
    this.procedure = procedure;
  }

  public void read() {
    procedure.createProcedureEvents(4);
    int pathIndex = 1; // Only to generate fake names
    while(procedure.hasNext()) {
      ProcedureEvent procedureEvent = procedure.getNext();
      procedureEvent.setName("fakeProcedureEvent_" + pathIndex);
      procedureEvent.setFillingPath("c:\\fakeProcedureEvent\\fillingPath" + pathIndex);
      procedureEvent.setMethodPath("c:\\fakeProcedureEvent\\methodPath" + pathIndex);
      procedureEvent.setInstrumentPath("c:\\fakeProcedureEvent\\instrumentPath" + pathIndex);
      procedureEvent.setStepPath("c:\\fakeProcedureEvent\\stepPath" + pathIndex);
      pathIndex++;
    }
  }

}
