package streams.controllers;

import entities.Streams;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.*;

public class StreamsController {

    private interface StringSetter {
        String set(String string);
    }

    public VBox createInterface() {
        VBox box = new VBox(10);
        box.getChildren().addAll(
            getFromStreams("Average", "2,2,2,2", this::getAverage),
            getFromStreams("String with prefix", "x,y,helloMYfriend", this::getWithPrefix),
            getFromStreams("Squares of unique", "5,2,5,3,4,4,6", this::getSquaresOfUnique),
            getFromStreams("Last element", "aaa,bbb,c,ww,ok", this::getLastOfCollection),
            getFromStreams("Sum of even", "1,8,3,10", this::getSumEvens),
            getFromStreams("Collection to Map", "atp73,Hello my dear friend,lkj", this::getColToMap)
        );
        return box;
    }

    private List<Integer> getIntList(String s) {
        List<Integer> list = new ArrayList<>();
        for (String part : s.split(",")) {
            list.add(Integer.parseInt(part.trim()));
        }
        return list;
    }

    private List<String> getStringList(String s) {
        List<String> list = new ArrayList<>();
        for (String part : s.split(",")) {
            list.add(part.trim());
        }
        return list;
    }

    private String getAverage(String s) {
        try {
            return String.valueOf(Streams.averageOfList(getIntList(s)));
        } catch (Exception e) {
            return "Error";
        }
    }

    private String getWithPrefix(String s) {
        try {
            return Streams.toUpperWithPrefix(getStringList(s)).toString();
        } catch (Exception e) {
            return "Error";
        }
    }

    private String getSquaresOfUnique(String s) {
        try {
            return Streams.squaresOfUnique(getIntList(s)).toString();
        } catch (Exception e) {
            return "Error";
        }
    }

    private String getLastOfCollection(String s) {
        try {
            return Streams.lastElement(getStringList(s));
        } catch (Exception e) {
            return "Error";
        }
    }

    private String getSumEvens(String s) {
        try {
            List<Integer> list = getIntList(s);
            return String.valueOf(Streams.sumOfEven(list.stream().mapToInt(i -> i).toArray()));
        } catch (Exception e) {
            return "Error";
        }
    }

    private String getColToMap(String s) {
        try {
            return Streams.listOfStringsToMap(getStringList(s)).toString();
        } catch (Exception e) {
            return "Error";
        }
    }

    private VBox getFromStreams(String label, String example, StringSetter setter) {
        TextField in = new TextField();
        in.setPromptText("Example: " + example);
        TextField out = new TextField();
        out.setDisable(true);
        out.setMinWidth(320);
        out.setStyle("-fx-background-color: #AF9C8C;");
        in.textProperty().addListener((_, _, v) -> out.setText(v != null && !v.trim().isEmpty() ? setter.set(v) : ""));
        return new VBox(6, new Label(label), new HBox(12, in, out));
    }
}