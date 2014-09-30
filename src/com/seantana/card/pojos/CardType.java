package com.seantana.card.pojos;

public enum CardType {

  ARTIFACT("Artifact"), //
  CREATURE("Creature"), //
  ENCHANTMENT("Enchantment"), //
  INSTANT("Instant"), //
  LAND("Land"), //
  PLANESWALKER("Planeswalker"), //
  SORCERY("Sorcery");

  private String type;

  private CardType(final String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
