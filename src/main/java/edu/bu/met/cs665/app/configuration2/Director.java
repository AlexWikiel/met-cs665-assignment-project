package edu.bu.met.cs665.app.configuration2;

public class Director {

  private IBuilder builder;

  public void setBuilder(IBuilder builder) {
    this.builder = builder;
  }

  public void BuildMinimalViableProduct() {
    this.builder.BuildPartA();
  }

  public void BuildFullFeaturedProduct() {
    this.builder.BuildPartA();
    this.builder.BuildPartB();
    this.builder.BuildPartC();
  }

}
