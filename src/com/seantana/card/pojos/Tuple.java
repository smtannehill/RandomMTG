package com.seantana.card.pojos;

public class Tuple<A, B> {

  private final A a;
  private final B b;

  public Tuple(final A a, final B b) {
    this.a = a;
    this.b = b;
  }

  public A left() {
    return a;
  }

  public B right() {
    return b;
  }

}
