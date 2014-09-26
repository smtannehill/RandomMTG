package com.seantana.card.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextGenerator {

	public String generateText(final String source, final int markov) {
		final Map<String, String> textMap = generateTextMap(source, markov);
		return "";
	}

	private Map<String, String> generateTextMap(final String source,
			final int markov) {
		final Map<String, String> textMap = new HashMap<String, String>();
		final Scanner scanner = new Scanner(source);
		int x = 1;
		String keyString = "";
		try {
			while ((scanner.hasNext()) || (x <= markov)) {
				keyString += scanner.next() + " ";
				x++;
			}
			keyString.trim();

		} finally {
			scanner.close();
		}
		return textMap;
	}
}
