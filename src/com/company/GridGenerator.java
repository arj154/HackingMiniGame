package com.company;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by arjun on 22/07/2016.
 */
public class GridGenerator {

    ArrayList<String> gridWords;

    public String selectRandomWord(int lengthOfWord, ArrayList<String> wordList) {

        ArrayList<String> wordsWithWordLimit = getWordsWithWordLimit(lengthOfWord, wordList);

        Random random = new Random();
        int wordToPick = random.nextInt(wordsWithWordLimit.size()) - 1;
        return wordsWithWordLimit.get(wordToPick);
    }

    public ArrayList<String> getSimilarWords(String originalWord, int numberOfWords, ArrayList<String> wordList) {

        ArrayList<String> randomwords = new ArrayList<>();
        Random random = new Random();

        ArrayList<String> wordsWithPartialMatch = Lists.newArrayList(Iterables.filter(wordList, wordFound ->
                wordFound.contains(getRandomMatchingWords(originalWord, random))));

        for (int i = 0; i < numberOfWords - 1; i++) {

            int nextRandomWord = random.nextInt(wordsWithPartialMatch.size() - 1);
            randomwords.add(wordsWithPartialMatch.get(nextRandomWord));
        }

        randomwords.add(originalWord);

        return randomwords;
    }

    private String getRandomMatchingWords(String originalWord, Random random) {

        int randomStartingLetter = generateRandomPosition(originalWord, random);
        int randomEndingLetter = generateEndingLetter(originalWord, random, randomStartingLetter);

        return originalWord.substring(randomStartingLetter,
                randomEndingLetter);
    }

    private int generateEndingLetter(String originalWord, Random random, int randomStartingLetter) {

        return getRandomInRange(randomStartingLetter, originalWord.length(), random);
    }

    public int getRandomInRange(int from, int to, Random random) {

        if (from < to) {

            return from + random.nextInt(Math.abs(to - from));
        }
        return from - random.nextInt(Math.abs(to - from));
    }

    private int generateRandomPosition(String randomWord, Random random) {

        return random.nextInt(randomWord.length());
    }

    public ArrayList<String> getWordsWithWordLimit(int lengthOfWord, ArrayList<String> unsortedWords) {

        return Lists.newArrayList(Iterables.filter(unsortedWords, wordFound ->
                wordFound.length() == lengthOfWord));
    }
}
