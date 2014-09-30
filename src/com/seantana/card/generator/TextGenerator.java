package com.seantana.card.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.seantana.card.pojos.Card;

public class TextGenerator {

  private static final String            END_PHRASE_CONSTANT = "###";

  private final Set<String>              cardNameSet;

  private final Set<String>              prefixNames;
  private final Multimap<String, String> nameMap;

  private final Set<String>              prefixText;
  private final Multimap<String, String> textMap;

  private final int                      nameMarkov;
  private final int                      textMarkov;

  public TextGenerator(final int nameMarkov, final int textMarkov) {
    this.nameMarkov = nameMarkov;
    this.textMarkov = textMarkov;

    cardNameSet = new HashSet<String>();
    prefixNames = new HashSet<String>();
    nameMap = ArrayListMultimap.create();
    prefixText = new HashSet<String>();
    textMap = ArrayListMultimap.create();
  }

  public Card createRandomCard(final List<Card> cards) {
    for (final Card card : cards) {
      if (!cardNameSet.contains(card.getName())) {
        if (card.getName().equals("Boldwyr Intimidator")) {
          System.out.println();
        }
        appendToMap(card.getName(), nameMap, prefixNames, nameMarkov);
        appendToMap(card.getText(), textMap, prefixText, textMarkov);
        cardNameSet.add(card.getName());
      }
    }
    return generateRandomCard();
  }

  private void appendToMap(final String source, final Multimap<String, String> map, final Set<String> prefixSet, final int markov) {
    if ((source == null) || (source.isEmpty()))
      return;

    final Scanner scanner = new Scanner(source);
    int x = 1;
    final List<String> currentWordList = new ArrayList<String>();
    try {
      while ((scanner.hasNext()) && (x <= markov)) {
        currentWordList.add(scanner.next());
        x++;
      }
      String keyString = createKeyStringFromArray(currentWordList);
      prefixSet.add(keyString);
      if (!scanner.hasNext()) {
        map.put(keyString, END_PHRASE_CONSTANT);
        return;
      }

      while (scanner.hasNext()) {
        final String word = scanner.next();
        map.put(keyString, word);
        currentWordList.remove(0);
        currentWordList.add(word);
        keyString = createKeyStringFromArray(currentWordList);
      }
      map.put(keyString, END_PHRASE_CONSTANT);
    } finally {
      scanner.close();
    }
  }

  private String createKeyStringFromArray(final List<String> wordList) {
    String keyString = "";
    for (final String word : wordList) {
      keyString = keyString + word + " ";
    }
    return keyString.trim();
  }

  private Card generateRandomCard() {
    final Card card = new Card();
    card.setName(generateRandomString(nameMap, prefixNames));
    card.setText(generateRandomString(textMap, prefixText));
    return card;
  }

  private String generateRandomString(final Multimap<String, String> map, final Set<String> prefixSet) {

    final StringBuilder stringBuilder = new StringBuilder();
    final Random random = new Random();
    String currentKey = new ArrayList<String>(prefixSet).get(random.nextInt(prefixSet.size()));
    Collection<String> keySet = map.get(currentKey);
    String currentWord = new ArrayList<String>(keySet).get(random.nextInt(keySet.size()));
    stringBuilder.append(currentKey);
    while ((currentWord != null) && (!currentWord.equals(END_PHRASE_CONSTANT))) {
      stringBuilder.append(" " + currentWord);
      final List<String> keyList = Lists.newArrayList(currentKey.split("\\s+"));
      keyList.remove(0);
      keyList.add(currentWord);
      currentKey = createKeyStringFromArray(keyList);
      keySet = map.get(currentKey);
      currentWord = new ArrayList<String>(keySet).get(random.nextInt(keySet.size()));
    }
    return stringBuilder.toString().trim();
  }

}
