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
            System.out.println(words[i]);
            if (words[i].toLowerCase().contains(wordToFind)) {

                if (words[i].toLowerCase().contains("calories")) {
                    found = words[i + 1];
                } else {
                    found = words[i];
                }

            }

        }


        return found.substring(found.lastIndexOf(" ") + 1).replace("(", "").replace(")", "").replace("g", "").replace("L", "").replaceAll("\\D4+", "");
    }


    public String getFirstWordFromString() {
        if(input  != null){
            String[] v = input.split(" ");
            return v[0];
        }
        return input;

    }
}
