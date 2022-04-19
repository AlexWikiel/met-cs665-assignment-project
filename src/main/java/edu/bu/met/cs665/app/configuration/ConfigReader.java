package edu.bu.met.cs665.app.configuration;

public abstract class ConfigReader extends XmlReader {

  protected String fileName;

  protected abstract void setFileName();

  protected abstract Configuration getConfigurationType();


  public Configuration getConfiguration() {
    setFileName();
    String fileContent = readFile(this.fileName);
    System.out.println(fileContent);
    Configuration config = getConfigurationType();

    // if we were really deserializing then this would be fileContent
    return DeserializeXml(fileName, config);
  }


}
