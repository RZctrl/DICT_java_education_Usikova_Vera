package RockPaperScissors;

import java.io.*;
import java.util.*;


public class Rating {

    private final String fileName;
    private final Map<String, Integer> ratings;

    public Rating(String fileName) {
        this.fileName = fileName;
        this.ratings = new HashMap<>();
        loadRatings();
    }

    private void loadRatings() {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    try {
                        String name = parts[0];
                        int score = Integer.parseInt(parts[1]);
                        ratings.put(name, score);
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Rating file not found. Starting with empty ratings.");
        }
    }

    public int getScore(String playerName) {
        return ratings.getOrDefault(playerName, 0);
    }

    public void updateScore(String playerName, int points) {
        int currentScore = getScore(playerName);
        ratings.put(playerName, currentScore + points);
        saveRatings();
    }

    private void saveRatings() {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Map.Entry<String, Integer> entry : ratings.entrySet()) {
                writer.println(entry.getKey() + " " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving ratings");
        }
    }
}