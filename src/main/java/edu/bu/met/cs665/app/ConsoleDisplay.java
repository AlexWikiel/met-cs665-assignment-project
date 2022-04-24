package edu.bu.met.cs665.app;

import edu.bu.met.cs665.app.configuration.types.Routine;
import edu.bu.met.cs665.app.configuration.types.SystemConfig;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The user interface for this application
 */
public class ConsoleDisplay implements Listener {
  private AnalyzerApp analyzerApp;
  private Scanner scanner = new Scanner(System.in, "UTF-8");
  private ArrayList<Integer> validChoices;
  private SystemConfig systemConfig;

  /**
   * The entrance to th eprogram
   * @param args
   */
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

  // This is the loop that runs the entire program, this would be eliminated if we went to some sort of GUI
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
          analyzerApp.startRoutineProcessor(systemConfig.getRoutine(routineName));
        }
      }
    }
  }

  // Display the collected data to screen
  private synchronized void displayData() {
    synchronized (analyzerApp.getDataLock()) {

      ArrayList<int[]> data = AnalyzerApp.getAnalyzerApp().getData();
      if (data != null && data.size() > 0) {
        while(data.size() > 0){

          int[] datum = data.remove(0);
          StringBuffer dataToDisplay = new StringBuffer();
          for (int channel : datum) {
            dataToDisplay.append(channel + "\t");
          }
          System.out.println(dataToDisplay);
        }
      }
    }
  }

  // Get user input and try to parse it
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

  // Display the available choices
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

  /**
   * This is the update method that will be fired if Status inform fires this event.
   * @param update
   */
  @Override
  public void update(String update) {
    System.out.println(update);
  }
}
