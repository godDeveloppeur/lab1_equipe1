package wordLinkLab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TxtToJsonConverter {

    public static void main(String[] args) {
        String inputFile = "./src/wordLinkLab/wordEnglish.txt";
        String outputFile = "./src/wordLinkLab/wordEnglish.json";

        try {
            List<String> wordsList = readWordsFromFile(inputFile);
            saveWordsAsJson(wordsList, outputFile);
            System.out.println("Conversion successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static List<String> readWordsFromFile(String inputFile) throws IOException {
        List<String> wordsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordsList.add(line.trim());
            }
        }

        return wordsList;
    }

    private static void saveWordsAsJson(List<String> wordsList, String outputFile) throws IOException {
        JSONArray jsonArray = new JSONArray();

        for (String word : wordsList) {
            jsonArray.add(word);
        }

        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(jsonArray.toJSONString());
        }
    }
}