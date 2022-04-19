package edu.bu.met.cs665.app.configuration.types;

import edu.bu.met.cs665.app.configuration.Configuration;

public class SystemConfiguration implements Configuration {
  private  String[] baths;

  public class bath{
    String bath_name;
    String bath_code;
    int bath_time;
    String bath_id;
    Routine routine;
  }
}
