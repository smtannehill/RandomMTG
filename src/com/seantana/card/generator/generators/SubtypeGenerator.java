package com.seantana.card.generator.generators;

import static com.seantana.card.generator.generators.GeneratorUtils.END_PHRASE_CONSTANT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Lists;

public class SubtypeGenerator implements Generator<List<String>> {
  private final List<List<String>>   wordOnlyMatrix;
  private final List<List<String>>   fullWordMatrix;
  private final Map<String, Boolean> endWords;

  public SubtypeGenerator() {
    wordOnlyMatrix = new ArrayList<List<String>>();
    fullWordMatrix = new ArrayList<List<String>>();
    endWords = new HashMap<String, Boolean>();
  }

  @Override
  public List<String> generate() {
    final Random random = new Random();
    final StringBuilder builder = new StringBuilder();
    String word;
    String nextWord = wordOnlyMatrix.get(0).get(
        random.nextInt(wordOnlyMatrix.get(0).size()));
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
        return Lists.newArrayList(builder.toString().trim().split(" "));
    }
    return Lists.newArrayList(builder.toString().trim().split(" "));
  }

  @Override
  public void addToPool(final List<String> input) {
    int x = 0;
    String word = "";
    for (final String inputWord : input) {
      word = inputWord;
      addWordToMatrix(x, word, wordOnlyMatrix);
      addWordToMatrix(x, word, fullWordMatrix);
      if (endWords.get(word) == null) {
        endWords.put(word, Boolean.valueOf(false));
      }
      x++;
    }
    if (x >= 0) {
      addWordToMatrix(x, END_PHRASE_CONSTANT, fullWordMatrix);
      endWords.put(word, Boolean.valueOf(true));
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
