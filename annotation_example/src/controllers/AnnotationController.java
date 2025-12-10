package annotation_example.controllers;

import annotation_example.core.ProgrammerLife;
import entities.CrazyProgrammer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class AnnotationController {

    public TextArea textArea;

    public AnnotationController() {
        textArea = new TextArea("");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(15);
        textArea.setMaxHeight(Double.MAX_VALUE);
    }

    public VBox createInterface() {
        VBox box = new VBox(12);
        box.setPadding(new javafx.geometry.Insets(8));

        Button button = new Button("Fun week!");
        button.setStyle("-fx-background-color: #AF5C9C; -fx-text-fill: white;");
        button.setOnAction(e -> {
            java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
            java.io.PrintStream printStream = new java.io.PrintStream(outputStream);
            java.io.PrintStream originalOut = System.out;
            try {
                System.setOut(printStream);
                ProgrammerLife.surviveOneWeek(new CrazyProgrammer());
                System.setOut(originalOut);
                textArea.setText(outputStream.toString());
            } catch (Exception ex) {
                System.setOut(originalOut);
                textArea.setText("Error: " + ex.getMessage());
            }
        });

        box.getChildren().addAll(
            new Label("Programmer's week..."),
            button,
            textArea
        );

        return box;
    }
}