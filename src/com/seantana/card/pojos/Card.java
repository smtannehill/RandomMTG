package com.seantana.card.pojos;

import java.util.List;

import com.google.gson.GsonBuilder;

public class Card {

  private String        layout;
  private String        name;
  private List<String>  names;
  private String        manaCost;
  private Double        cmc;
  private List<String>  colors;
  private String        type;
  private List<String>  supertypes;
  private List<String>  types;
  private List<String>  subtypes;
  private String        rarity;
  private String        text;
  private String        flavor;
  private String        artist;
  private String        number;
  private String        power;
  private String        toughness;
  private Integer       loyalty;
  private Integer       multiverseid;
  private List<Integer> variations;
  private String        imageName;
  private String        watermark;
  private String        border;
  private Boolean       timeshifted;
  private Integer       hand;
  private Integer       life;
  private Boolean       reserved;
  private String        releaseDate;

  public String getLayout() {
    return layout;
  }

  public void setLayout(final String layout) {
    this.layout = layout;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public List<String> getNames() {
    return names;
  }

  public void setNames(final List<String> names) {
    this.names = names;
  }

  public String getManaCost() {
    return manaCost;
  }

  public void setManaCost(final String manaCost) {
    this.manaCost = manaCost;
  }

  public Double getCmc() {
    return cmc;
  }

  public void setCmc(final Double cmc) {
    this.cmc = cmc;
  }

  public List<String> getColors() {
    return colors;
  }

  public void setColors(final List<String> colors) {
    this.colors = colors;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public List<String> getSupertypes() {
    return supertypes;
  }

  public void setSupertypes(final List<String> supertypes) {
    this.supertypes = supertypes;
  }

  public List<String> getTypes() {
    return types;
  }

  public void setTypes(final List<String> types) {
    this.types = types;
  }

  public List<String> getSubtypes() {
    return subtypes;
  }

  public void setSubtypes(final List<String> subtypes) {
    this.subtypes = subtypes;
  }

  public String getRarity() {
    return rarity;
  }

  public void setRarity(final String rarity) {
    this.rarity = rarity;
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public String getFlavor() {
    return flavor;
  }

  public void setFlavor(final String flavor) {
    this.flavor = flavor;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(final String artist) {
    this.artist = artist;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(final String number) {
    this.number = number;
  }

  public String getPower() {
    return power;
  }

  public void setPower(final String power) {
    this.power = power;
  }

  public String getToughness() {
    return toughness;
  }

  public void setToughness(final String toughness) {
    this.toughness = toughness;
  }

  public Integer getLoyalty() {
    return loyalty;
  }

  public void setLoyalty(final Integer loyalty) {
    this.loyalty = loyalty;
  }

  public Integer getMultiverseid() {
    return multiverseid;
  }

  public void setMultiverseid(final Integer multiverseid) {
    this.multiverseid = multiverseid;
  }

  public List<Integer> getVariations() {
    return variations;
  }

  public void setVariations(final List<Integer> variations) {
    this.variations = variations;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(final String imageName) {
    this.imageName = imageName;
  }

  public String getWatermark() {
    return watermark;
  }

  public void setWatermark(final String watermark) {
    this.watermark = watermark;
  }

  public String getBorder() {
    return border;
  }

  public void setBorder(final String border) {
    this.border = border;
  }

  public Boolean getTimeshifted() {
    return timeshifted;
  }

  public void setTimeshifted(final Boolean timeshifted) {
    this.timeshifted = timeshifted;
  }

  public Integer getHand() {
    return hand;
  }

  public void setHand(final Integer hand) {
    this.hand = hand;
  }

  public Integer getLife() {
    return life;
  }

  public void setLife(final Integer life) {
    this.life = life;
  }

  public Boolean getReserved() {
    return reserved;
  }

  public void setReserved(final Boolean reserved) {
    this.reserved = reserved;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(final String releaseDate) {
    this.releaseDate = releaseDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((artist == null) ? 0 : artist.hashCode());
    result = prime * result + ((border == null) ? 0 : border.hashCode());
    result = prime * result + ((cmc == null) ? 0 : cmc.hashCode());
    result = prime * result + ((colors == null) ? 0 : colors.hashCode());
    result = prime * result + ((flavor == null) ? 0 : flavor.hashCode());
    result = prime * result + ((hand == null) ? 0 : hand.hashCode());
    result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
    result = prime * result + ((layout == null) ? 0 : layout.hashCode());
    result = prime * result + ((life == null) ? 0 : life.hashCode());
    result = prime * result + ((loyalty == null) ? 0 : loyalty.hashCode());
    result = prime * result + ((manaCost == null) ? 0 : manaCost.hashCode());
    result = prime * result + ((multiverseid == null) ? 0 : multiverseid.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((names == null) ? 0 : names.hashCode());
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    result = prime * result + ((power == null) ? 0 : power.hashCode());
    result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
    result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
    result = prime * result + ((reserved == null) ? 0 : reserved.hashCode());
    result = prime * result + ((subtypes == null) ? 0 : subtypes.hashCode());
    result = prime * result + ((supertypes == null) ? 0 : supertypes.hashCode());
    result = prime * result + ((text == null) ? 0 : text.hashCode());
    result = prime * result + ((timeshifted == null) ? 0 : timeshifted.hashCode());
    result = prime * result + ((toughness == null) ? 0 : toughness.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + ((types == null) ? 0 : types.hashCode());
    result = prime * result + ((variations == null) ? 0 : variations.hashCode());
    result = prime * result + ((watermark == null) ? 0 : watermark.hashCode());
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
    final Card other = (Card) obj;
    if (artist == null) {
      if (other.artist != null)
        return false;
    } else if (!artist.equals(other.artist))
      return false;
    if (border == null) {
      if (other.border != null)
        return false;
    } else if (!border.equals(other.border))
      return false;
    if (cmc == null) {
      if (other.cmc != null)
        return false;
    } else if (!cmc.equals(other.cmc))
      return false;
    if (colors == null) {
      if (other.colors != null)
        return false;
    } else if (!colors.equals(other.colors))
      return false;
    if (flavor == null) {
      if (other.flavor != null)
        return false;
    } else if (!flavor.equals(other.flavor))
      return false;
    if (hand == null) {
      if (other.hand != null)
        return false;
    } else if (!hand.equals(other.hand))
      return false;
    if (imageName == null) {
      if (other.imageName != null)
        return false;
    } else if (!imageName.equals(other.imageName))
      return false;
    if (layout == null) {
      if (other.layout != null)
        return false;
    } else if (!layout.equals(other.layout))
      return false;
    if (life == null) {
      if (other.life != null)
        return false;
    } else if (!life.equals(other.life))
      return false;
    if (loyalty == null) {
      if (other.loyalty != null)
        return false;
    } else if (!loyalty.equals(other.loyalty))
      return false;
    if (manaCost == null) {
      if (other.manaCost != null)
        return false;
    } else if (!manaCost.equals(other.manaCost))
      return false;
    if (multiverseid == null) {
      if (other.multiverseid != null)
        return false;
    } else if (!multiverseid.equals(other.multiverseid))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (names == null) {
      if (other.names != null)
        return false;
    } else if (!names.equals(other.names))
      return false;
    if (number == null) {
      if (other.number != null)
        return false;
    } else if (!number.equals(other.number))
      return false;
    if (power == null) {
      if (other.power != null)
        return false;
    } else if (!power.equals(other.power))
      return false;
    if (rarity == null) {
      if (other.rarity != null)
        return false;
    } else if (!rarity.equals(other.rarity))
      return false;
    if (releaseDate == null) {
      if (other.releaseDate != null)
        return false;
    } else if (!releaseDate.equals(other.releaseDate))
      return false;
    if (reserved == null) {
      if (other.reserved != null)
        return false;
    } else if (!reserved.equals(other.reserved))
      return false;
    if (subtypes == null) {
      if (other.subtypes != null)
        return false;
    } else if (!subtypes.equals(other.subtypes))
      return false;
    if (supertypes == null) {
      if (other.supertypes != null)
        return false;
    } else if (!supertypes.equals(other.supertypes))
      return false;
    if (text == null) {
      if (other.text != null)
        return false;
    } else if (!text.equals(other.text))
      return false;
    if (timeshifted == null) {
      if (other.timeshifted != null)
        return false;
    } else if (!timeshifted.equals(other.timeshifted))
      return false;
    if (toughness == null) {
      if (other.toughness != null)
        return false;
    } else if (!toughness.equals(other.toughness))
      return false;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    if (types == null) {
      if (other.types != null)
        return false;
    } else if (!types.equals(other.types))
      return false;
    if (variations == null) {
      if (other.variations != null)
        return false;
    } else if (!variations.equals(other.variations))
      return false;
    if (watermark == null) {
      if (other.watermark != null)
        return false;
    } else if (!watermark.equals(other.watermark))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(this);
  }

}
