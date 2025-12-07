package translator.core;

import entities.Translator;
import exceptions.FileReadException;
import exceptions.InvalidFileFormatException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        String dictionaryFilePath = "translator/src/data/dictionary_01.dict";

        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();

        try {
            translator.loadDictionary(dictionaryFilePath);
            System.out.println("Dictionary " + dictionaryFilePath + " was loaded.");
            System.out.println("Type 'exit' to end the program.");

            while (true) {
                System.out.println("Enter the text to translate...\n");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                String translated = translator.translateText(input);
                System.out.println("Translation: " + translated);
            }
        } catch (InvalidFileFormatException | FileReadException e) {
            System.out.println("ERROR! " + e.getMessage());
            System.exit(1);
        } finally {
            scanner.close();
            System.out.println("Good bye!");
        }
    }
}


