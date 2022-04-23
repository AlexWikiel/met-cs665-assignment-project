package edu.bu.met.cs665.app.configuration.readertypes;

/**
 * Read text files.  This is a simulation only, to demonstrate we can pass the path if we were really reading the file.
 */
public class TextFileReader {

  private Boolean fileExists;

  protected String readFile(String file) {
    //When implementing check if file exists
    return "Reading file: "+ file;
  }
}
