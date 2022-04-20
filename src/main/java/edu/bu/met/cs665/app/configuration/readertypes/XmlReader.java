package edu.bu.met.cs665.app.configuration.readertypes;

public class XmlReader extends TextFileReader{
    protected Configuration DeserializeXml(String xmlDocument, Configuration config){
      System.out.println("Deserializing Document: " + xmlDocument + " to " + config.toString());
      return config;
    }
}
