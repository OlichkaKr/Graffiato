package ua.lviv.iot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcessor {
    public String readInputText() throws IOException {
        String input;
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        StringBuilder inputText = new StringBuilder();
        System.out.println("Input text (press ENTER twice when you want to finish input):");
        while (!(input = scanner.nextLine()).equals("")) {
            inputText = inputText.append(input);
        }
        String[] tempArr = inputText.toString().split("(\\. +)", 2);

        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(tempArr[0]);

        //test for exception
        if (tempArr.length == 1 || tempArr[1].equals("")) {
            throw new IOException("Please, enter at least two sentences");
        }
        while (matcher.find()) {
            count++;
        }
        if (count == 0) {
            throw new IOException("First sentence must have the words");
        }

        return inputText.toString();
    }

    public String processText(String inputText) {
        String result = "";
//        Pattern pattern = Pattern.compile("[e]?[sd]?");
        String regex = "[.\\[\\])(,-]|(['’`]\\w+)";
        String[] arr = inputText.split("(\\. )", 2);
        arr[0] = arr[0].replaceAll(regex, "");
        arr[1] = arr[1].replaceAll(regex, "");

        String[] text = arr[1].split(" ");

        ArrayList<String> tempList = new ArrayList<>(Arrays.asList(arr[0].split(" ")));

        System.out.println();
        for (int i = 0; i < tempList.size(); i++) {
            for (String aText : text) {
                if (tempList.get(i).equalsIgnoreCase(aText)
                        || tempList.get(i).equalsIgnoreCase(aText + "s")
                        || tempList.get(i).equalsIgnoreCase(aText + "es")
                        || tempList.get(i).equalsIgnoreCase(aText + "d")
                        || tempList.get(i).equalsIgnoreCase(aText + "ed")) {
                    tempList.remove(i);
                    i--;
                    break;
                }
            }
        }
        for (int i = 0; i < tempList.size() - 1; i++) {
            result = result.concat(tempList.get(i) + ", ");
        }
        result = result.concat(tempList.get(tempList.size() - 1));

        return result;
    }

    public void showResult(String resultText /* or pass your class instance as parameter */) {
        System.out.println("The first sentence has this unique words: " + resultText);

    }

}
