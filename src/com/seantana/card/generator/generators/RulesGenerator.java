package com.seantana.card.generator.generators;

import static com.seantana.card.generator.generators.GeneratorUtils.END_PHRASE_CONSTANT;

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

public class RulesGenerator implements Generator<String> {

  private final Set<String>              prefixSet;
  private final Multimap<String, String> textMap;

  private final int                      markov;

  public RulesGenerator(final int markov) {
    prefixSet = new HashSet<String>();
    textMap = ArrayListMultimap.create();
    this.markov = markov;
  }

  @Override
  public String generate() {

    final StringBuilder stringBuilder = new StringBuilder();
    final Random random = new Random();
    String currentKey = new ArrayList<String>(prefixSet).get(random
        .nextInt(prefixSet.size()));
    Collection<String> keySet = textMap.get(currentKey);
    String currentWord = new ArrayList<String>(keySet).get(random
        .nextInt(keySet.size()));
    stringBuilder.append(currentKey);
    while ((currentWord != null) && (!currentWord.equals(END_PHRASE_CONSTANT))) {
      stringBuilder.append(" " + currentWord);
      final List<String> keyList = Lists.newArrayList(currentKey.split("\\s+"));
      keyList.remove(0);
      keyList.add(currentWord);
      currentKey = createKeyStringFromArray(keyList);
      keySet = textMap.get(currentKey);
      currentWord = new ArrayList<String>(keySet).get(random.nextInt(keySet
          .size()));
    }
    return stringBuilder.toString().trim();
  }

  @Override
  public void addToPool(final String input) {
    if ((input == null) || (input.isEmpty()))
      return;

    final Scanner scanner = new Scanner(input);
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
        textMap.put(keyString, END_PHRASE_CONSTANT);
        return;
      }

      while (scanner.hasNext()) {
        final String word = scanner.next();
        textMap.put(keyString, word);
        currentWordList.remove(0);
        currentWordList.add(word);
        keyString = createKeyStringFromArray(currentWordList);
      }
      textMap.put(keyString, END_PHRASE_CONSTANT);
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

}
