package com.seantana.card.generator.generators;

public interface Generator<T> {

  public void addToPool(T input);

  public T generate();
}
