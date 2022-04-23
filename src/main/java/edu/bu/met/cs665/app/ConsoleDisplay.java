package edu.bu.met.cs665.app;

import edu.bu.met.cs665.app.configuration.types.Routine;
import edu.bu.met.cs665.app.configuration.types.SystemConfig;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleDisplay implements Listener {
  private AnalyzerApp analyzerApp;
  private Scanner scanner = new Scanner(System.in);
  private ArrayList<Integer> validChoices;
  private SystemConfig systemConfig;

  public static void main(String[] args) {
    ConsoleDisplay consoleDisplay = new ConsoleDisplay();
    consoleDisplay.runProgram();


  }

  // inject the config and instantiate the analyzerApp
  public ConsoleDisplay() {
    analyzerApp = AnalyzerApp.getAnalyzerApp();
    systemConfig = analyzerApp.getConfig();
    AnalyzerStatus.getAnalyzerStatus().attach(this);
  }

  private  void runProgram() {
    while(true) {

      if (AnalyzerStatus.getAnalyzerStatus().getSystemStatus() == SystemStatus.runningExperiment){
        displayData();

      }

      if (AnalyzerStatus.getAnalyzerStatus().getSystemStatus() == SystemStatus.idle) {
        DisplayAvailableAnalyses();
        int userIn = getInput();
        //int userIn = 1;
        if (userIn == 0) {
          continue;
        } else if (userIn == validChoices.get(validChoices.size() - 1)) {
          break;
        } else if (validChoices.contains(userIn)) {
          AnalyzerStatus.getAnalyzerStatus().setSystemStatus(SystemStatus.processingRoutine);
          String routineName = systemConfig.getRoutineOrder().get(userIn - 1); // subtract one to go to zero-indexing
          System.out.println("yes we can analyze this: " + routineName);
          analyzerApp.routineProcessor(systemConfig.getRoutine(routineName));
        }
      }



    }

  }

  private synchronized void displayData() {
    synchronized (analyzerApp.getDataLock()) {

      ArrayList<int[]> data = AnalyzerApp.getAnalyzerApp().getData();
      if (data != null && data.size() > 0) {
        while(data.size() > 0){

          int[] datum = data.remove(0);
          String dataToDisplay = "";
          for (int channel : datum) {
            dataToDisplay += String.valueOf(channel) + "\t";
          }
          System.out.println(dataToDisplay);

        }






      }

    }


//    if (data.length > 0) {
//
//
//
//    }


//    // Allow enough time for the DataCollectorThread to init the data structure
//    int[][] data = AnalyzerApp.getAnalyzerApp().getData();
//    while ( data == null) {}
//    int position = 0;
//    while (position < 20)
//    {
//      if (data[position][0] != 0) {
//        System.out.println(data[position][0] + "\t" +
//                data[position][1] + "\t" +
//                data[position][2] + "\t");
//        position++;
//      }
//      Thread.yield();
//    }
  }


  private int getInput()
  {
    int selection;
    String userIn = scanner.nextLine();

    try {
      selection = Integer.parseInt(userIn);
    } catch (NumberFormatException ex) {
      return 0;
    }
    return selection;
  }

  private void DisplayAvailableAnalyses() {
    validChoices = new ArrayList<>();
    System.out.println("\n\nAvailable Analysis:\n");
    int choiceNumber = 1;
    while (systemConfig.hasNext()) {
      Routine routine = systemConfig.getNext();
      String routineName = routine.getName();
      System.out.println("\t" + choiceNumber + " - "+routineName);
      validChoices.add(choiceNumber);
      choiceNumber++;
    }
    validChoices.add(choiceNumber);
    System.out.println("\t" + choiceNumber + " - Exit");
    System.out.println("\nPlease make your selection using the designated numbers - ");
  }

  @Override
  public void update(String update) {
    System.out.println(update);
  }
}
