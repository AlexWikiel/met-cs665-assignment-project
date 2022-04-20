package edu.bu.met.cs665.app.configuration2;

import java.io.Console;

public class Client {
  public static void main(String[] args) {
    Director director = new Director();
    ConcreteBuilder builder = new ConcreteBuilder();
    director.setBuilder(builder);

    System.out.println("Standard basic product:");
    director.BuildMinimalViableProduct();
    System.out.println(builder.GetProduct().ListParts());

    System.out.println("Standard full featured product:");
    director.BuildFullFeaturedProduct();
    System.out.println(builder.GetProduct().ListParts());

    // Remember, the Builder pattern can be used without a Director
    // class.
    System.out.println("Custom product:");
    builder.BuildPartA();
    builder.BuildPartC();
    System.out.println(builder.GetProduct().ListParts());
  }
}
