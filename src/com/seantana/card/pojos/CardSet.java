package com.seantana.card.pojos;

import java.util.List;

import com.google.gson.GsonBuilder;

public class CardSet {

  private String     name;
  private String     code;
  private String     gathererCode;
  private String     releaseDate;
  private String     border;
  private String     core;
  private String     block;
  private List<Card> cards;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public String getGathererCode() {
    return gathererCode;
  }

  public void setGathererCode(final String gathererCode) {
    this.gathererCode = gathererCode;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(final String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getBorder() {
    return border;
  }

  public void setBorder(final String border) {
    this.border = border;
  }

  public String getCore() {
    return core;
  }

  public void setCore(final String core) {
    this.core = core;
  }

  public String getBlock() {
    return block;
  }

  public void setBlock(final String block) {
    this.block = block;
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setCards(final List<Card> cards) {
    this.cards = cards;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((block == null) ? 0 : block.hashCode());
    result = prime * result + ((border == null) ? 0 : border.hashCode());
    result = prime * result + ((cards == null) ? 0 : cards.hashCode());
    result = prime * result + ((code == null) ? 0 : code.hashCode());
    result = prime * result + ((core == null) ? 0 : core.hashCode());
    result = prime * result + ((gathererCode == null) ? 0 : gathererCode.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final CardSet other = (CardSet) obj;
    if (block == null) {
      if (other.block != null)
        return false;
    } else if (!block.equals(other.block))
      return false;
    if (border == null) {
      if (other.border != null)
        return false;
    } else if (!border.equals(other.border))
      return false;
    if (cards == null) {
      if (other.cards != null)
        return false;
    } else if (!cards.equals(other.cards))
      return false;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    if (core == null) {
      if (other.core != null)
        return false;
    } else if (!core.equals(other.core))
      return false;
    if (gathererCode == null) {
      if (other.gathererCode != null)
        return false;
    } else if (!gathererCode.equals(other.gathererCode))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (releaseDate == null) {
      if (other.releaseDate != null)
        return false;
    } else if (!releaseDate.equals(other.releaseDate))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return new GsonBuilder().create().toJson(this);
  }

}
