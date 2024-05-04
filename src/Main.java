
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<String, List<String>> loadedWordGraph = Utility.loadWordGraphFromFile("word_graph.txt");

        Scanner scanner = new Scanner(System.in);
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

        System.out.print("Shortest path from '" + startWord + "' to '" + endWord + "' " + "UCS" + ": ");
        Utility.PathPrinter.printResults(shortestPathUCS, nodesExploredUCS, memoryUsedUCS, runtimeUCS);
        System.out.print("Shortest path from '" + startWord + "' to '" + endWord + "' " + "AStar" + ": ");
        Utility.PathPrinter.printResults(shortestPathAStar, nodesExploredAStar, memoryUsedAStar, runtimeAStar);
        System.out.print("Shortest path from '" + startWord + "' to '" + endWord + "' " + "GBFS" + ": ");
        Utility.PathPrinter.printResults(shortestPathGreedy, nodesExploredGreedy, memoryUsedGreedy, runtimeGreedy);
        
        
    }

   
    
}
