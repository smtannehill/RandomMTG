package com.seantana.card.loading;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.seantana.card.pojos.Card;

public class CardFormatUtils {

  private static final String NAME_STRING    = "~";
  private static final String NEWLINE_STRING = " *** ";

  public static List<Card> adjustRulesText(final List<Card> cards) {
    final List<Card> filteredCards = new ArrayList<Card>();
    String rulesText;
    for (final Card card : cards) {
      if (card.getText() != null) {
        rulesText = card.getText();
        rulesText = removeReminderText(rulesText);
        rulesText = rulesText.replace(card.getName(), NAME_STRING);

        // Scanner squashes newlines, so this is a workaround until I can figure
        // out how to set Scanner to ignore newlines
        rulesText = rulesText.replace("\n", NEWLINE_STRING);
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

  public static void injectCardName(final Card card) {
    card.setText(card.getText().replace(NAME_STRING, card.getName()));
  }

  public static void injectNewLineCharacters(final Card card) {
    card.setText(card.getText().replace(NEWLINE_STRING, "\n"));
  }
}
