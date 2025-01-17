
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Utility {
    public static void saveWordGraphToFile(Map<String, List<String>> wordGraph, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Map.Entry<String, List<String>> entry : wordGraph.entrySet()) {
                String word = entry.getKey();
                List<String> connectedWords = entry.getValue();
                StringBuilder sb = new StringBuilder();
                sb.append(word).append(": ");
                for (String connectedWord : connectedWords) {
                    sb.append(connectedWord).append(", ");
                }
                sb.delete(sb.length() - 2, sb.length()); // Menghapus koma dan spasi terakhir
                sb.append("\n");
                writer.write(sb.toString());
            }
            System.out.println("Graf telah disimpan ke file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

      public static Map<String, List<String>> loadWordGraphFromFile(String filename) {
        Map<String, List<String>> loadedWordGraph = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String word = parts[0].trim();
                List<String> connectedWords = new ArrayList<>();
                if (parts.length > 1) {
                    String[] connectedWordArray = parts[1].split(",");
                    for (String connectedWord : connectedWordArray) {
                        connectedWords.add(connectedWord.trim());
                    }
                }
                loadedWordGraph.put(word, connectedWords);
            }
            System.out.println("Graf telah dimuat dari file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedWordGraph;
    }

       // Fungsi untuk membaca kata-kata dari file dan menyimpannya dalam Map
    public static Map<Integer, List<String>> readWordsFromFile(String filename) {
        Map<Integer, List<String>> wordMap = new HashMap<>();

        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int length = line.length();
                wordMap.computeIfAbsent(length, k -> new ArrayList<>()).add(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordMap;
    }

    // Fungsi untuk membuat graf word ladder menggunakan multithreading
    public static Map<String, List<String>> buildWordGraphMultiThread(Map<Integer, List<String>> wordMap) {
        Map<String, List<String>> wordGraph = new HashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try {
            for (List<String> words : wordMap.values()) {
                for (String word : words) {
                    executor.submit(() -> {
                        // Buat koneksi dengan kata-kata yang memiliki perbedaan satu huruf
                        List<String> connectedWords = new ArrayList<>();
                        for (String otherWord : words) {
                            if (!word.equals(otherWord) && isOneLetterApart(word, otherWord)) {
                                connectedWords.add(otherWord);
                            }
                        }
                        synchronized (wordGraph) {
                            wordGraph.put(word, connectedWords);
                        }

                        // Debug: Tampilkan kata dan kata-kata terhubungnya
                        System.out.println("Kata: " + word + ", Terhubung: " + connectedWords);
                    });
                }
            }
        } finally {
            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return wordGraph;
    }

        private static boolean isOneLetterApart(String word1, String word2) {
            if (word1.length() != word2.length()) {
                return false;
            }
    
            int diffCount = 0;
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    diffCount++;
                    if (diffCount > 1) {
                        return false;
                    }
                }
            }
    
            return diffCount == 1;
        }


    public static int calculateHeuristic(String currentWord, String endWord) {
        int diffCount = 0;

        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) != endWord.charAt(i)) {
                diffCount++;
            }
        }
    
        return diffCount;
    }
    
  
    public class PathPrinter {
        public static void printResults( List<String> shortestPath, int nodesExplored, long memoryUsed, long runtime) {
            
            printPath(shortestPath);
            System.out.println("Jumlah Path : " + shortestPath.size());
            System.out.println("Number of nodes explored: " + nodesExplored);
            System.out.println("Memory used: " + memoryUsed + " bytes");
            System.out.println("Runtime: " + runtime / 1e6 + " milliseconds\n");
        }
    
        private static void printPath(List<String> path) {
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i != path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }  
}


