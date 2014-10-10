package com.seantana.card.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

  private final List<List<String>>       wordOnlyNameMatrix;
  private final List<List<String>>       fullNameMatrix;
  private final Map<String, Boolean>     nameEndWords;

  private final List<List<String>>       wordOnlyTypeMatrix;
  private final List<List<String>>       fullTypeMatrix;
  private final Map<String, Boolean>     typeEndWords;

  private final Set<String>              prefixText;
  private final Multimap<String, String> textMap;

  private final int                      textMarkov;

  public TextGenerator(final int textMarkov) {
    this.textMarkov = textMarkov;

    cardNameSet = new HashSet<String>();

    wordOnlyNameMatrix = new ArrayList<List<String>>();
    fullNameMatrix = new ArrayList<List<String>>();
    nameEndWords = new HashMap<String, Boolean>();

    wordOnlyTypeMatrix = new ArrayList<List<String>>();
    fullTypeMatrix = new ArrayList<List<String>>();
    typeEndWords = new HashMap<String, Boolean>();

    prefixText = new HashSet<String>();
    textMap = ArrayListMultimap.create();
  }

  public Card createRandomCard(final List<Card> cards) {
    for (final Card card : cards) {
      if (!cardNameSet.contains(card.getName())) {
        appendToMatrix(Lists.newArrayList(card.getName().split(" ")), wordOnlyNameMatrix, fullNameMatrix, nameEndWords);
        if ((card.getSubtypes() != null) && (card.getSubtypes().size() > 0)) {
          appendToMatrix(card.getSubtypes(), wordOnlyTypeMatrix, fullTypeMatrix, typeEndWords);
        }
        appendToMap(card.getText(), textMap, prefixText, textMarkov);
        cardNameSet.add(card.getName());
      }
    }
    return generateRandomCard();
  }

  private void appendToMatrix(final List<String> input, final List<List<String>> wordOnlyMatrix, final List<List<String>> fullMatrix,
      final Map<String, Boolean> endWords) {
    int x = 0;
    String word = "";
    for (final String inputWord : input) {
      word = inputWord;
      addWordToMatrix(x, word, wordOnlyMatrix);
      addWordToMatrix(x, word, fullMatrix);
      if (endWords.get(word) == null) {
        endWords.put(word, Boolean.valueOf(false));
      }
      x++;
    }
    if (x >= 0) {
      addWordToMatrix(x, END_PHRASE_CONSTANT, fullMatrix);
      endWords.put(word, Boolean.valueOf(true));
    }

  }

  private void addWordToMatrix(final int x, final String word, final List<List<String>> list) {
    if (list.size() <= x) {
      list.add(new ArrayList<String>());
    }
    list.get(x).add(word);
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
    card.setName(generateCardName());
    card.setSubtypes(generateCardSubtype());
    card.setText(generateRandomString(textMap, prefixText));
    return card;
  }

  private String generateCardName() {
    return generateUsingMatrices(wordOnlyNameMatrix, fullNameMatrix, nameEndWords);
  }

  private List<String> generateCardSubtype() {
    return Lists.newArrayList(generateUsingMatrices(wordOnlyTypeMatrix, fullTypeMatrix, typeEndWords).split(" "));
  }

  private String generateUsingMatrices(final List<List<String>> wordOnlyMatrix, final List<List<String>> fullWordMatrix,
      final Map<String, Boolean> endWords) {
    final Random random = new Random();
    final StringBuilder builder = new StringBuilder();
    String word;
    String nextWord = wordOnlyMatrix.get(0).get(random.nextInt(wordOnlyMatrix.get(0).size()));
    for (int x = 1; x < fullWordMatrix.size(); x++) {
      word = nextWord;
      builder.append(word + " ");
      List<String> list;
      if (endWords.get(word)) {
        list = fullWordMatrix.get(x);
      } else {
        list = wordOnlyMatrix.get(x);
      }
      nextWord = list.get(random.nextInt(list.size()));
      if (nextWord.equals(END_PHRASE_CONSTANT))
        return builder.toString().trim();
    }
    return builder.toString().trim();
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
