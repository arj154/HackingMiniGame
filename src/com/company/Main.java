package com.company;

import com.company.tests.HackingMiniGameUnitTest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("-----------------------------------");
        System.out.println("-------- Hacking Mini Game --------");
        System.out.println("-----------------------------------");
        System.out.println("");

        Scanner user_input = new Scanner(System.in);

        chooseMode(user_input);
    }

    private static void chooseMode(Scanner user_input) {

        System.out.println("-------- Select Difficulty --------");
        System.out.println("1. Very Easy");
        System.out.println("2. Easy");
        System.out.println("3. Medium");
        System.out.println("4. Hard");
        System.out.println("5. Very Hard");

        String difficulty = user_input.next();
        int difficultyMode = 1;

        try {

            difficultyMode = Integer.parseInt(difficulty);
        } catch (NumberFormatException e) {

            e.printStackTrace();
            chooseMode(user_input);
        }

        System.out.flush();

        int characterLimit = 4;
        int numberOfWords = 5;

        switch (difficultyMode) {

            case 1:

                runGame(user_input, characterLimit, numberOfWords);
                break;
            case 2:

                characterLimit = 7;
                numberOfWords = 8;

                runGame(user_input, characterLimit, numberOfWords);
                break;
            case 3:

                characterLimit = 10;
                numberOfWords = 10;

                runGame(user_input, characterLimit, numberOfWords);
                break;
            case 4:

                characterLimit = 13;
                numberOfWords = 12;

                runGame(user_input, characterLimit, numberOfWords);
                break;
            case 5:

                characterLimit = 15;
                numberOfWords = 15;

                runGame(user_input, characterLimit, numberOfWords);
                break;
            default:

                chooseMode(user_input);
                break;

        }
    }

    private static void runGame(Scanner user_input, int characterLimit, int numberOfWords) {

        String file = "enable1.txt";
        InputStream stream = HackingMiniGameUnitTest.class.getResourceAsStream(file);

        ArrayList<String> list = LoadTextFile.getList(stream);

        GridGenerator gridGenerator = new GridGenerator();
        String randomWord = gridGenerator.selectRandomWord(characterLimit, list);
        ArrayList<String> wordsInLimit = gridGenerator.getWordsWithWordLimit(characterLimit, list);

        ArrayList<String> words = gridGenerator.getSimilarWords(randomWord, numberOfWords, wordsInLimit);

        for (String word : words) {

            System.out.println(word);
        }

        int noOfGuesses = 0;

        checkAnswer(user_input, randomWord, noOfGuesses);
    }


    private static void checkAnswer(Scanner user_input, String randomWord, int noOfGuesses) {

        String guess = user_input.next();
        if (guess.contentEquals(randomWord)) {

            System.out.println("you win!");
        } else {

            noOfGuesses = noOfGuesses + 1;

            int correctChars = 0;
            for (int i = 0; i < guess.length(); i++) {

                if (guess.charAt(i) == randomWord.charAt(i)) {

                    correctChars = correctChars + 1;
                }
            }

            System.out.println("guess left [" + noOfGuesses + "/4]");
            System.out.println("[" + correctChars + "/" + guess.length() + "] correct");


            if (noOfGuesses == 4) {

                System.out.println("You lose, the correct answer was: " + randomWord);
                System.out.println(" Try again? [Y/N]");

                String choice = user_input.next();

                if (choice.equalsIgnoreCase("Y")) {

                    System.out.flush();
                    chooseMode(user_input);
                } else {

                    System.out.println("Thanks for playing!");
                }


            } else {

                System.out.println("try again");
                checkAnswer(user_input, randomWord, noOfGuesses);
            }
        }
    }
}
