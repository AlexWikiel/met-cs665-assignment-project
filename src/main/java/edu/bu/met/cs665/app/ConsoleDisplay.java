package edu.bu.met.cs665.app;

import edu.bu.met.cs665.app.configuration.types.Routine;
import edu.bu.met.cs665.app.configuration.types.SystemConfig;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleDisplay {
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
  }

  private  void runProgram() {
    while(true) {



      DisplayAvailableAnalyses();
      int userIn = getInput();
      if (userIn == 0) {
        continue;
      }
      else if (userIn == validChoices.get(validChoices.size()-1)) {
        break;
      }
      else if (validChoices.contains(userIn)) {
        String routineName = systemConfig.getRoutineOrder().get(userIn-1); // subtract one to go to zero-indexing
        System.out.println( "yes we can analyze this: " + routineName);
        analyzerApp.routineProcessor(systemConfig.getRoutine(routineName));
      }





    }

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
}
