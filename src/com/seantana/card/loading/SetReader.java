package com.seantana.card.loading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.seantana.card.pojos.Card;
import com.seantana.card.pojos.CardSet;

public class SetReader {

	public List<Card> createCardList() throws IOException {

		final File folder = new File("AllSetFiles");
		BufferedReader reader;
		String line;
		final List<Card> cards = new ArrayList<Card>();
		for (final File jsonFile : folder.listFiles()) {
			System.out.println(jsonFile.getName());
			reader = new BufferedReader(new FileReader(jsonFile));
			try {
				while ((line = reader.readLine()) != null) {
					cards.addAll(new GsonBuilder().create()
							.fromJson(line, CardSet.class).getCards());

				}
			} finally {
				reader.close();
			}
		}
		return cards;
	}

	public static void main(final String[] args) throws IOException {
		final List<Card> cards = new SetReader().createCardList();
		System.out.println(cards.size());
	}
}
