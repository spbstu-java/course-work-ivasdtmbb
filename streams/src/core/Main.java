package streams.core;

import entities.Streams;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // 1. ----------------------------------------------------------------
        System.out.println("1. Average of list:");
        List<Integer> numbersForAverage = Arrays.asList(2, 2, 2, 2);
        double averageResult = Streams.averageOfList(numbersForAverage);

        System.out.println("Input: " + numbersForAverage);
        System.out.println("Result: " + averageResult);
        System.out.println();

        // 2. ----------------------------------------------------------------
        System.out.println("2. Strings to upper case with prefix:");
        List<String> inputStrings01 = Arrays.asList("x", "y", "helloMYfriend");

        List<String> prefixedStrings = Streams.toUpperWithPrefix(inputStrings01);

        System.out.println("Input: " + inputStrings01);
        System.out.println("Result: " + Arrays.toString(prefixedStrings.toArray()));
        System.out.println();

        List<String> inputStrings02 = Collections.emptyList();
        prefixedStrings = Streams.toUpperWithPrefix(inputStrings02);
        System.out.println("Input: " + inputStrings02);
        System.out.println("Result: " + Arrays.toString(prefixedStrings.toArray()));
        System.out.println();


        // 3. ----------------------------------------------------------------
        System.out.println("3. Squares of unique:");
        List<Integer> numbersForUnique = Arrays.asList(5, 2, 5, 3, 4, 4, 6);
        List<Integer> uniqueSquares = Streams.squaresOfUnique(numbersForUnique);

        System.out.println("Input: " + numbersForUnique);
        System.out.println("Result: " + Arrays.toString(uniqueSquares.toArray()));
        System.out.println();

        // 4. ----------------------------------------------------------------
        System.out.println("4. Last element of collection:");
        try {
            List<String> testCollection01 = Arrays.asList("aaa", "bbb", "c", "ww", "ok");
            String lastElement = Streams.lastElement(testCollection01);

            System.out.println("Input: " + testCollection01);
            System.out.println("Result: " + lastElement);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            List<String> testCollection02 = Collections.emptyList();
            String lastElement = Streams.lastElement(testCollection02);

            System.out.println("Input: " + testCollection02);
            System.out.println("Result: " + lastElement);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        // 5. ----------------------------------------------------------------
        System.out.println("5. Sum of even numbers:");
        System.out.println("[1, 8, 3, 10] -> " + Streams.sumOfEven(new int[]{1, 8, 3, 10}));
        System.out.println("[1, 3, 5, 7, 9, 11] -> " + Streams.sumOfEven(new int[]{1, 3, 5, 7, 9, 11}));
        System.out.println();

        // 6. ----------------------------------------------------------------
        System.out.println("6. Strings to Map:");
        List<String> stringsForMap = Arrays.asList("atp73@", "Hello my dear friend", "lkj...");

        System.out.println("Input: " + stringsForMap);
        System.out.println("Result: " + Streams.listOfStringsToMap(stringsForMap));
        System.out.println();
    }
}