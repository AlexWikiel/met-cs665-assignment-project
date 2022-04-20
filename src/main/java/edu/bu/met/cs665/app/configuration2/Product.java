package edu.bu.met.cs665.app.configuration2;

import java.util.ArrayList;

public class Product {

  private ArrayList<Object> parts = new ArrayList<Object>();

  public void Add(String part) {
    this.parts.add(part);
  }

  public String ListParts() {
    String str = "";

    for (int i = 0; i < this.parts.size(); i++) {
      str += this.parts.get(i) + ", ";
    }
    //str = str.Remove(str.Length - 2); // removing last ",c"

    return "Product parts: " + str + "\n";
  }

}
