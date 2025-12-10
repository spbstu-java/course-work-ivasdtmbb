package game_hero.controllers;

import Hero.Hero;
import MoveStrategy.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameHeroController {

    public Hero hero;
    public TextArea textArea;

    public GameHeroController() {
        this.hero = new Hero();
        textArea = new TextArea("");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(12);
        textArea.setMaxHeight(Double.MAX_VALUE);
    }

    public VBox createInterface() {
        VBox box = new VBox(12);
        box.setPadding(new Insets(12));

        ComboBox<String> movementCombo = new ComboBox<>();
        movementCombo.getItems().addAll("Walk", "Horse", "Flight");
        movementCombo.setPromptText("Movement type");

        ComboBox<String> directionCombo = new ComboBox<>();
        directionCombo.getItems().addAll("North", "South", "West", "East");
        directionCombo.setPromptText("Direction");

        Button moveButton = new Button("Move");
        moveButton.setStyle("-fx-background-color: #AF9C8C; -fx-text-fill: white;");
        moveButton.setOnAction(e -> {
            String movement = movementCombo.getValue();
            String direction = directionCombo.getValue();

            if (movement != null && direction != null) {
                performMovement(movement, direction);
            } else {
                textArea.setText("Select movement type and direction!");
            }
        });

        Button finishButton = new Button("Finish journey");
        finishButton.setStyle("-fx-background-color: #AF5C9C; -fx-text-fill: white;");
        finishButton.setOnAction(e -> {
            textArea.setText("Hero finished journey!\nFinal position: x=" + hero.getX() + " y=" + hero.getY() + "\n" + textArea.getText());
        });

        box.getChildren().addAll(
                new Label("Hero control:"),
                new Label("Movement type:"),
                movementCombo,
                new Label("Direction:"),
                directionCombo,
                moveButton,
                finishButton,
                new Label("Result:"),
                textArea
        );

        return box;
    }

    private void performMovement(String movement, String direction) {
        switch (movement) {
            case "Walk":
                hero.setMoveStrategy(new MoveStrategyByWalk());
                break;
            case "Horse":
                hero.setMoveStrategy(new MoveStrategyByHorse());
                break;
            case "Flight":
                hero.setMoveStrategy(new MoveStrategyByFlight());
                break;
        }

        Direction dir = switch (direction) {
            case "North" -> Direction.NORTH;
            case "South" -> Direction.SOUTH;
            case "West" -> Direction.WEST;
            case "East" -> Direction.EAST;
            default -> null;
        };

        if (dir != null) {
            java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
            java.io.PrintStream printStream = new java.io.PrintStream(outputStream);
            java.io.PrintStream originalOut = System.out;
            try {
                System.setOut(printStream);
                hero.move(dir);
                System.setOut(originalOut);
                String output = outputStream.toString();
                textArea.setText(output + textArea.getText());
            } catch (Exception ex) {
                System.setOut(originalOut);
                textArea.setText("Error: " + ex.getMessage());
            }
        }
    }
}