package com.seantana.card.generator.generators;

import static com.seantana.card.generator.generators.GeneratorUtils.END_PHRASE_CONSTANT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NameGenerator implements Generator<String> {

  private final List<List<String>>   wordOnlyNameMatrix;
  private final List<List<String>>   fullNameMatrix;
  private final Map<String, Boolean> nameEndWords;

  public NameGenerator() {
    wordOnlyNameMatrix = new ArrayList<List<String>>();
    fullNameMatrix = new ArrayList<List<String>>();
    nameEndWords = new HashMap<String, Boolean>();
  }

  @Override
  public String generate() {
    final Random random = new Random();
    final StringBuilder builder = new StringBuilder();
    String word;
    String nextWord = wordOnlyNameMatrix.get(0).get(
        random.nextInt(wordOnlyNameMatrix.get(0).size()));
    for (int x = 1; x < fullNameMatrix.size(); x++) {
      word = nextWord;
      builder.append(word + " ");
      List<String> list;
      if (nameEndWords.get(word)) {
        list = fullNameMatrix.get(x);
      } else {
        list = wordOnlyNameMatrix.get(x);
      }
      nextWord = list.get(random.nextInt(list.size()));
      if (nextWord.equals(END_PHRASE_CONSTANT))
        return builder.toString().trim();
    }
    return builder.toString().trim();
  }

  @Override
  public void addToPool(final String input) {
    final String[] inputArray = input.split(" ");
    int x = 0;
    String word = "";
    for (final String inputWord : inputArray) {
      word = inputWord;
      addWordToMatrix(x, word, wordOnlyNameMatrix);
      addWordToMatrix(x, word, fullNameMatrix);
      if (nameEndWords.get(word) == null) {
        nameEndWords.put(word, Boolean.valueOf(false));
      }
      x++;
    }
    if (x >= 0) {
      addWordToMatrix(x, END_PHRASE_CONSTANT, fullNameMatrix);
      nameEndWords.put(word, Boolean.valueOf(true));
    }

  }

  private void addWordToMatrix(final int x, final String word,
      final List<List<String>> list) {
    if (list.size() <= x) {
      list.add(new ArrayList<String>());
    }
    list.get(x).add(word);
  }

}
