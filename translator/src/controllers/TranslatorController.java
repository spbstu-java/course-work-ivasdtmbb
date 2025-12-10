package translator.controllers;

import entities.Translator;
import exceptions.FileReadException;
import exceptions.InvalidFileFormatException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class TranslatorController {

    public VBox createInterface() {
        VBox box = new VBox(10);
        TextField dictPathField = new TextField("translator/src/data/dictionary_01.dict");
        TextField textFilePathField = new TextField("translator/src/data/text_to_translate.txt");
        TextArea inputTextArea = new TextArea();
        inputTextArea.setPrefRowCount(5);
        TextArea resultArea = new TextArea();
        resultArea.setPrefRowCount(6);
        resultArea.setEditable(false);
        resultArea.setStyle("-fx-background-color: #AF9C8C;");

        Button translateButton = getButton(dictPathField, textFilePathField, inputTextArea, resultArea);
        translateButton.setStyle("-fx-background-color: #AF5C9C; -fx-text-fill: white;");

        box.getChildren().addAll(
            translateButton,
            new Label("Dictionary path:"),
            dictPathField,
            new Label("Text file path:"),
            textFilePathField,
            new Label("Text to translate (priority over file):"),
            inputTextArea,
            new Label("Result:"),
            resultArea
        );
        return box;
    }

    private static Button getButton(TextField dictPath, TextField textFilePath, TextArea inputText, TextArea result) {
        Button button = new Button("Translate");
        button.setOnAction(e -> {
            if (!Objects.equals(dictPath.getText(), "")) {
                try {
                    Translator translator = new Translator();
                    translator.loadDictionary(dictPath.getText());
                    if (!Objects.equals(inputText.getText(), "")) {
                        result.setText(translator.translateText(inputText.getText()));
                    } else if (!Objects.equals(textFilePath.getText(), "")) {
                        try {
                            result.setText(translator.translateText(readTextFromFile(new File(textFilePath.getText()))));
                        } catch (IOException ex) {
                            result.setText("Error: " + ex.getMessage());
                        }
                    } else {
                        result.setText("Nothing to translate!");
                    }
                } catch (FileReadException | InvalidFileFormatException ex) {
                    result.setText("Error: " + ex.getMessage());
                }
            } else {
                result.setText("Dictionary path not specified");
            }
        });
        return button;
    }

    private static String readTextFromFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim();
    }
}