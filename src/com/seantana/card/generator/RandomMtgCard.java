package com.seantana.card.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Lists;
import com.seantana.card.loading.SetReader;
import com.seantana.card.pojos.Card;
import com.seantana.card.pojos.CardColor;
import com.seantana.card.pojos.CardType;

public class RandomMtgCard {

  public static void main(final String[] args) throws IOException {
    List<Card> cards = new SetReader(new File("AllSetFiles")).createCardList();
    cards = SetReader.filterCardList(cards, filterByCardType(CardType.CREATURE));
    cards = SetReader.adjustRulesText(cards);
    final Card card = new TextGenerator(1, 4).createRandomCard(cards);
    System.out.println(card);
  }

  private static Predicate<Card> filterByCardType(final CardType type) {
    return new Predicate<Card>() {

      @Override
      public boolean test(final Card t) {
        if ((t.getTypes() != null) && (t.getTypes().equals(Lists.newArrayList(type.getType()))))
          return true;
        return false;
      }

    };
  }

  private static Predicate<Card> filterByCardColor(final CardColor type) {
    return new Predicate<Card>() {

      @Override
      public boolean test(final Card t) {
        if ((t.getTypes() != null) && (t.getColors().contains(type.getColor())))
          return true;
        return false;
      }

    };
  }

}
