
import java.util.*;

import javax.naming.directory.SearchResult;


public class GreedyBestFirstSearch {
    public static class SearchResult {
        private List<String> path;
        private int nodesExplored;
        private long memoryUsed;
        private long runtime;

        public SearchResult(List<String> path, int nodesExplored, long memoryUsed, long runtime) {
            this.path = path;
            this.nodesExplored = nodesExplored;
            this.memoryUsed = memoryUsed;
            this.runtime = runtime;
        }

        public List<String> getPath() {
            return path;
        }

        public int getNodesExplored() {
            return nodesExplored;
        }

        public long getMemoryUsed() {
            return memoryUsed;
        }

        public long getRuntime() {
            return runtime;
        }
    }

    public static SearchResult findPath(Map<String, List<String>> graph, String startWord, String endWord) {
        PriorityQueue<WordNode> queue = new PriorityQueue<>(Comparator.comparingInt(WordNode::getCost));
        Map<String, String> parents = new HashMap<>();
        Set<String> visited = new HashSet<>();
        int nodesExplored = 0;
        long startTime = System.nanoTime();

        queue.offer(new WordNode(startWord, Utility.calculateHeuristic(startWord, endWord)));

        while (!queue.isEmpty()) {
            WordNode current = queue.poll();
            String currentWord = current.getWord();
            nodesExplored++;

            if (currentWord.equals(endWord)) {
                // Path found, reconstruct and return it
                List<String> path = new ArrayList<>();
                while (!currentWord.equals(startWord)) {
                    path.add(0, currentWord);
                    currentWord = parents.get(currentWord);
                }
                path.add(0, startWord);
                long endTime = System.nanoTime();
                long runtime = endTime - startTime;
                long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                return new SearchResult(path, nodesExplored, memoryUsed, runtime);
            }

            visited.add(currentWord);

            for (String neighbor : graph.getOrDefault(currentWord, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    int heuristic = Utility.calculateHeuristic(neighbor, endWord);
                    queue.offer(new WordNode(neighbor, heuristic));
                    parents.put(neighbor, currentWord);
                }
            }
        }

        return new SearchResult(Collections.emptyList(), nodesExplored, 0, 0); // No path found
    }
}



