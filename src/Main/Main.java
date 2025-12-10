package Main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import game_hero.controllers.GameHeroController;
import annotation_example.controllers.AnnotationController;
import streams.controllers.StreamsController;
import translator.controllers.TranslatorController;

public class Main extends Application {

    private VBox contentArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        ComboBox<String> selector = new ComboBox<>();
        selector.getItems().addAll(
                "Lab 1: Game Hero",
                "Lab 2: Annotations",
                "Lab 3: Translator",
                "Lab 4: Streams"
        );
        selector.setPromptText("Select laboratory work");

        contentArea = new VBox(10);
        contentArea.setPadding(new Insets(10));

        selector.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                updateContent(newVal);
            }
        });

        root.getChildren().addAll(selector, contentArea);

        Scene scene = new Scene(root, 850, 750);
        stage.setTitle("OOP Laboratory Works");
        stage.setScene(scene);
        stage.show();
    }

    private void updateContent(String selectedValue) {
        contentArea.getChildren().clear();

        switch (selectedValue) {
            case "Lab 1: Game Hero":
                GameHeroController heroController = new GameHeroController();
                contentArea.getChildren().add(heroController.createInterface());
                break;

            case "Lab 2: Annotations":
                AnnotationController annotationController = new AnnotationController();
                contentArea.getChildren().add(annotationController.createInterface());
                break;

            case "Lab 3: Translator":
                TranslatorController translatorController = new TranslatorController();
                contentArea.getChildren().add(translatorController.createInterface());
                break;

            case "Lab 4: Streams":
                StreamsController streamsController = new StreamsController();
                contentArea.getChildren().add(streamsController.createInterface());
                break;
        }
    }
}