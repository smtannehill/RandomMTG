package com.seantana.card.pojos;

public enum CardColor {

  WHITE("White"), //
  BLUE("Blue"), //
  BLACK("Black"), //
  RED("Red"), //
  GREEN("Green"), //
  COLORLESS("Colorless");

  private String color;

  private CardColor(final String color) {
    this.color = color;
  }

  public String getColor() {
    return color;
  }
}
