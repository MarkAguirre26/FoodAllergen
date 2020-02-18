package com.thesis.pdm.hallergen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringManager {
    private String input;

    public StringManager(String input) {
        this.input = input;
    }

    public String getValueFromString(String value) {
        String found = "";

        String wordToFind = value.toLowerCase();
        String[] words = input.split("\n");
        for (int i = 0; i <= words.length - 1; i++) {
            if (words[i].toLowerCase().contains(wordToFind)) {
                found = words[i];
            }

        }


        return found.substring(found.lastIndexOf(" ") + 1);
    }
}
