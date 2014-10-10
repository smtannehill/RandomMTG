package com.seantana.card.generator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.seantana.card.generator.generators.Generator;
import com.seantana.card.generator.generators.NameGenerator;
import com.seantana.card.generator.generators.RulesGenerator;
import com.seantana.card.generator.generators.SubtypeGenerator;
import com.seantana.card.pojos.Card;

public class TextGenerator {

  private final Set<String>             cardNameSet;

  private final Generator<String>       nameGenerator;
  private final Generator<List<String>> subtypeGenerator;
  private final Generator<String>       rulesGenerator;

  public TextGenerator(final int textMarkov) {

    cardNameSet = new HashSet<String>();

    nameGenerator = new NameGenerator();
    subtypeGenerator = new SubtypeGenerator();
    rulesGenerator = new RulesGenerator(textMarkov);
  }

  public Card createRandomCard(final List<Card> cards) {
    for (final Card card : cards) {
      if (!cardNameSet.contains(card.getName())) {

        nameGenerator.addToPool(card.getName());
        if ((card.getSubtypes() != null) && (card.getSubtypes().size() > 0)) {
          subtypeGenerator.addToPool(card.getSubtypes());
        }
        rulesGenerator.addToPool(card.getText());

        cardNameSet.add(card.getName());
      }
    }
    return generateRandomCard();
  }

  private Card generateRandomCard() {
    final Card card = new Card();
    card.setName(nameGenerator.generate());
    card.setSubtypes(subtypeGenerator.generate());
    card.setText(rulesGenerator.generate());
    return card;
  }
}
