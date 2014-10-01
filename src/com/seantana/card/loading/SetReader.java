package com.seantana.card.loading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seantana.card.pojos.Card;
import com.seantana.card.pojos.CardSet;

public class SetReader {

  private final File folder;

  public SetReader(final File folder) {
    this.folder = folder;
  }

  public List<Card> createCardList() throws IOException {

    BufferedReader reader;
    String line;
    final List<Card> cards = new ArrayList<Card>();
    final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    for (final File jsonFile : folder.listFiles()) {
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile), "UTF-8"));
      try {
        while ((line = reader.readLine()) != null) {
          cards.addAll(gson.fromJson(line, CardSet.class).getCards());
        }
      } finally {
        reader.close();
      }
    }
    return cards;
  }

  @SafeVarargs
  public static List<Card> filterCardList(final List<Card> cards, final Predicate<Card>... filters) {
    final List<Card> filteredCards = new ArrayList<Card>();
    for (final Card card : cards) {
      for (final Predicate<Card> filter : filters) {
        if (filter.test(card)) {
          filteredCards.add(card);
        }
      }
    }
    return filteredCards;
  }

}
