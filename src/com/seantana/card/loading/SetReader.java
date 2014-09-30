package com.seantana.card.loading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
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

  public static List<Card> adjustRulesText(final List<Card> cards) {
    final List<Card> filteredCards = new ArrayList<Card>();
    String rulesText;
    for (final Card card : cards) {
      if (card.getText() != null) {
        rulesText = card.getText();
        rulesText = removeReminderText(rulesText);
        rulesText = rulesText.replace(card.getName(), "~");

        // Scanner squashes newlines, so this is a workaround until I can figure
        // out how to set Scanner to ignore newlines
        rulesText = rulesText.replace("\n", " *** ");
        card.setText(rulesText);
        filteredCards.add(card);
      }
    }
    return filteredCards;
  }

  private static String removeReminderText(final String text) {
    final StringBuilder builder = new StringBuilder();
    final StringReader reader = new StringReader(text);
    try {
      int character = reader.read();
      while (character != -1) {
        if (((char) character) == '(') {
          while (((char) reader.read()) != ')') {
          }
        } else {
          builder.append((char) character);
        }
        character = reader.read();
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return builder.toString();
  }

}
