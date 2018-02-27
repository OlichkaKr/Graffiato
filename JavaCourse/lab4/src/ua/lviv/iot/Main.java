package ua.lviv.iot;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        StringProcessor stringProcessor = new StringProcessor();
        try {
            String input = stringProcessor.readInputText();
            String result = stringProcessor.processText(input);
            stringProcessor.showResult(result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
