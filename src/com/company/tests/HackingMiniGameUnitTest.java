package com.company.tests;

import com.company.GridGenerator;
import com.company.LoadTextFile;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class HackingMiniGameUnitTest {

    private InputStream stream;

    String content;

    @Before
    public void setUp() throws Exception {

        String file = "enable1.txt";
         stream =  HackingMiniGameUnitTest.class.getResourceAsStream(file);
    }

    @Test
    public void textFileLoadedAsList() throws Exception {

        List<String> list = LoadTextFile.getList(stream);
        assertEquals(172820, list.size());
    }

    @Test
    public void selectRandomWordInList() throws Exception {

        ArrayList<String> list = LoadTextFile.getList(stream);
        GridGenerator gridGenerator = new GridGenerator();
        String randomWord = gridGenerator.selectRandomWord(5, list);
        assertEquals(5, randomWord.length());

        String secondRandomWord = gridGenerator.selectRandomWord(5, list);
        assertNotEquals(randomWord, secondRandomWord);
    }

    @Test
    public void retrieveSimilarWords() throws Exception {

        ArrayList<String> list = LoadTextFile.getList(stream);
        GridGenerator gridGenerator = new GridGenerator();

        int characterLimit = 4;
        int numberOfWords = 5;

        runGameDifficultyTest(list, gridGenerator, characterLimit, numberOfWords);
    }

    @Test
    public void retrieveDifferentModes() throws Exception {


        ArrayList<String> list = LoadTextFile.getList(stream);
        GridGenerator gridGenerator = new GridGenerator();

        int characterLimit = 4;
        int numberOfWords = 5;

        runGameDifficultyTest(list, gridGenerator, characterLimit, numberOfWords);

        characterLimit = 7;
        numberOfWords = 8;

        runGameDifficultyTest(list, gridGenerator, characterLimit, numberOfWords);

        characterLimit = 10;
        numberOfWords = 10;

        runGameDifficultyTest(list, gridGenerator, characterLimit, numberOfWords);

        characterLimit = 13;
        numberOfWords = 12;

        runGameDifficultyTest(list, gridGenerator, characterLimit, numberOfWords);

        characterLimit = 15;
        numberOfWords = 15;

        runGameDifficultyTest(list, gridGenerator, characterLimit, numberOfWords);
    }

    private void runGameDifficultyTest(ArrayList<String> list, GridGenerator gridGenerator, int characterLimit, int numberOfWords) {

        String randomWord = gridGenerator.selectRandomWord(characterLimit, list);
        ArrayList<String> wordsInLimit = gridGenerator.getWordsWithWordLimit(characterLimit, list);

        ArrayList<String> words = gridGenerator.getSimilarWords(randomWord, numberOfWords, wordsInLimit);

        assertEquals(numberOfWords, words.size());
    }
}