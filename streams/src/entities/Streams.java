package entities;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams {

    public static double averageOfList(List<Integer> numbers) {
        Objects.requireNonNull(numbers, "numbers cannot be null");

        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    public static List<String> toUpperWithPrefix(List<String> strings) {
        Objects.requireNonNull(strings, "strings cannot be null");

        return strings.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .collect(Collectors.toList());
    }

    // Список квадратов всех встречающихся только один раз элементов списка.
    public static List<Integer> squaresOfUnique(List<Integer> numbers) {
        Objects.requireNonNull(numbers, "numbers cannot be null");

        Map<Integer, Long> frequency = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return frequency.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey() * entry.getKey())
                .toList();
    }

    // Метод принимает на вход коллекцию и возвращает её последний элемент,
    // или, если коллекция пуста, кидает исключение.
    public static <T> T lastElement(Collection<T> collection) {
        Objects.requireNonNull(collection, "collection cannot be null");

        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new IllegalArgumentException("Collection is empty"));
    }


    // Метод принимает на вход массив целых чисел,
    // возвращает сумму чётных чисел
    // или, если чётных чисел нет, возвращает 0.
    public static int sumOfEven(int[] values) {
        Objects.requireNonNull(values, "values cannot be null");

        return IntStream.of(values)
                .filter(v -> v % 2 == 0)
                .sum();
    }


    // Метод преобразует все строки в списке в Map, где первый символ – ключ, оставшиеся – значение.
    // Пустые строки игнорируем.
    // Если для одного и того же первого символа встречаются несколько строк, то берётся последняя.
    public static Map<Character, String> listOfStringsToMap(List<String> strings) {
        Objects.requireNonNull(strings, "strings cannot be null");

        return strings.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (existing, replacement) -> replacement
                ));
    }
}
