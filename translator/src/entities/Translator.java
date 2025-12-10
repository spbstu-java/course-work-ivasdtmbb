package entities;

import exceptions.FileReadException;
import exceptions.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Translator {
    private Map<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
    }

    public void loadDictionary(String filePath) throws InvalidFileFormatException, FileReadException {
        if(!filePath.toLowerCase().endsWith(".dict")) {
            throw new InvalidFileFormatException(
                    "Expected extension: .dict Received: " + getFileExtension(filePath)
            );
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileReadException("File doesn't exist: " + filePath);
        }
        if (!file.canRead()) {
            throw new FileReadException("Access denied. Not enough permissions to read the file: " + filePath);
        }

        this.dictionary = readDictionaryFromFile(file);

        System.out.println("Loaded into dictionary: " + dictionary.size() + " words.");
    }

    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < filePath.length() - 1) {
            return filePath.substring(lastDotIndex);
        }
        return "нет расширения";
    }

    private Map<String, String> readDictionaryFromFile(File file) throws InvalidFileFormatException, FileReadException {
        Map<String, String> dict = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] lineParts = line.split("\\|");

                if (lineParts.length != 2) {
                    throw new InvalidFileFormatException(
                            "Invalid format on line " + lineNumber  + ": " + line +
                                    " (expected format: word | translation)"
                    );
                }

                String word = lineParts[0].trim().toLowerCase();
                String translation = lineParts[1].trim();

                dict.put(word, translation);
            }
        } catch (IOException e) {
            throw new FileReadException("File reading error: " + e.getMessage());
        }

        return dict;
    }

    // 1 - Ignore letters' case.
    // 2 - If a word not in dictionary - return without translation, as it is.
    // 3 - Choose a translation case with a left side of a maximum length.
    // 4 - Print result into stdout (console)
    public String translateText(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        String[] words = text.split("\\s+");
        StringBuilder sbResultText = new StringBuilder();
        int i = 0;

        while (i < words.length) {
            String currentWord = words[i];
            // remove all non-letters & non-digits symbols.
            String cleanWord = currentWord.replaceAll("[^a-zA-Zа-яА-Я0-9]", "").toLowerCase();

            // Find all candidates from the dictionary that starts with cleanWord.
            // Sort candidates by words quantity descending.
            // Find the longest valid candidate - translation from the dictionary.
            // Add translation or original word and update the index i.
            List<Map.Entry<String, String>> candidates = findCandidates(cleanWord);

            sortCandidatesByLength(candidates);

            Map.Entry<String, String> bestMatch = findBestMatch(words, candidates, i);

            if (bestMatch != null) {
                sbResultText.append(bestMatch.getValue());
                i += bestMatch.getKey().split("\\s+").length;
            } else {
                sbResultText.append(words[i]);
                i++;
            }

            if (i < words.length) {
                sbResultText.append(" ");
            }
        }

        return sbResultText.toString();
    }

    private List<Map.Entry<String, String>> findCandidates(String currentWord) {
        List<Map.Entry<String, String>> candidates = new ArrayList<>();

        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            String dictKey = entry.getKey().toLowerCase();
            String[] dictWords = dictKey.split("\\s+");

            if (dictWords[0].equals(currentWord)) {
                candidates.add(entry);
            }
        }

        return candidates;
    }

    private void sortCandidatesByLength(List<Map.Entry<String, String>> candidates) {
        candidates.sort((a, b) -> {
            int lengthA = a.getKey().split("\\s+").length;
            int lengthB = b.getKey().split("\\s+").length;
            return Integer.compare(lengthB, lengthA);
        });
    }

    private Map.Entry<String, String> findBestMatch(
            String[] words,
            List<Map.Entry<String, String>> candidates,
            int startIndex) {

        for (Map.Entry<String, String> candidate: candidates) {
            String dictKey = candidate.getKey().toLowerCase();
            String[] dictCandidateWords = dictKey.split("\\s+");

            if (startIndex + dictCandidateWords.length <= words.length) {
                boolean allWordsMatch = true;

                // Check each word in dictionary for matching with text.
                for (int j = 0; j < dictCandidateWords.length; j++) {
                    String textWord = words[startIndex + j].toLowerCase();
                    // remove all non-letters & non-digits symbols.
                    textWord = textWord.replaceAll("[^a-zA-Zа-яА-Я0-9]", "");

                    String dictWord = dictCandidateWords[j];

                    if (!textWord.equals(dictWord)) {
                        allWordsMatch = false;
                        break;
                    }
                }

                if (allWordsMatch) {
                    return candidate;
                }
            }
        }
        return null;
    }

}
















