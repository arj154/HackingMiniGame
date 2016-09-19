package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by arjun on 22/07/2016.
 */
public class LoadTextFile {


    public static ArrayList<String> getList(InputStream stream) {

        String word ;
        ArrayList<String> listOfWords = new ArrayList<>();

        if (stream == null) {

            return listOfWords;
        }

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            while ((word = reader.readLine()) != null) {

                listOfWords.add(word);
            }

        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            try {

                stream.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        return listOfWords;
    }

}
