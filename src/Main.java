
import java.util.*;

public class Main {
    public static void main(String[] args) {


        /* Jika ingin memuat database baru silahkan uncomment ini ! */
        // // Membaca kata-kata dari file dan menyimpannya dalam Map
        // Map<Integer, List<String>> wordMap = Utility.readWordsFromFile("data.txt");

        // // Membuat graf word ladder menggunakan multithreading
        // Map<String, List<String>> wordGraph = Utility.buildWordGraphMultiThread(wordMap);

        //   // Setelah graf dibuat, simpan ke file
        // Utility.saveWordGraphToFile(wordGraph, "data_graph.txt");

        Map<String, List<String>> loadedWordGraph;
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Selamat datang di Word Ladder Solver!");
        System.out.println("Silahkan Pilih Database yang akan digunakan: ");
        System.out.println("1. Database 1 (data.txt dari asisten)");
        System.out.println("2. Database 2 (word_alpha.txt)");    
        System.out.print("Masukkan pilihan: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if(choice == 1){
            loadedWordGraph = Utility.loadWordGraphFromFile("../data/data_graph.txt");
        } else if(choice == 2){
            loadedWordGraph = Utility.loadWordGraphFromFile("../data/word_graph.txt");
        } else {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        
    
        System.out.print("Masukkan kata awal: ");
        String startWord = scanner.nextLine().trim().toLowerCase();

        System.out.print("Masukkan kata akhir: ");
        String endWord = scanner.nextLine().trim().toLowerCase();
        scanner.close();


        if (!loadedWordGraph.containsKey(startWord) || !loadedWordGraph.containsKey(endWord)) {
            System.out.println("Kata awal atau kata akhir tidak ditemukan dalam graf kata.");
            return;
        }

        UCS.SearchResult ucsResult = UCS.findPath(loadedWordGraph, startWord, endWord);
        List<String> shortestPathUCS = ucsResult.getPath();
        int nodesExploredUCS = ucsResult.getNodesExplored();
        long memoryUsedUCS = ucsResult.getMemoryUsed();
        long runtimeUCS = ucsResult.getRuntime();

        AStar.SearchResult aStarResult = AStar.findPath(loadedWordGraph, startWord, endWord);
        List<String> shortestPathAStar = aStarResult.getPath();
        int nodesExploredAStar = aStarResult.getNodesExplored();
        long memoryUsedAStar = aStarResult.getMemoryUsed();
        long runtimeAStar = aStarResult.getRuntime();

        GreedyBestFirstSearch.SearchResult greedyResult = GreedyBestFirstSearch.findPath(loadedWordGraph, startWord, endWord);
        List<String> shortestPathGreedy = greedyResult.getPath();
        int nodesExploredGreedy = greedyResult.getNodesExplored();
        long memoryUsedGreedy = greedyResult.getMemoryUsed();
        long runtimeGreedy = greedyResult.getRuntime();

        System.out.print("Path from '" + startWord + "' to '" + endWord + "' " + "UCS" + ": ");
        Utility.PathPrinter.printResults(shortestPathUCS, nodesExploredUCS, memoryUsedUCS, runtimeUCS);
        System.out.print("Path from '" + startWord + "' to '" + endWord + "' " + "AStar" + ": ");
        Utility.PathPrinter.printResults(shortestPathAStar, nodesExploredAStar, memoryUsedAStar, runtimeAStar);
        System.out.print("Path from '" + startWord + "' to '" + endWord + "' " + "GBFS" + ": ");
        Utility.PathPrinter.printResults(shortestPathGreedy, nodesExploredGreedy, memoryUsedGreedy, runtimeGreedy);
        
        
    }

   
    
}
