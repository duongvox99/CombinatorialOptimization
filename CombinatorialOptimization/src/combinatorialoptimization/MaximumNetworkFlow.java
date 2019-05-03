/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combinatorialoptimization;

/**
 *
 * @author Jackson
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumNetworkFlow {

    private int[][] adjacencyMatrix;
    private int numOfVertex;

    public MaximumNetworkFlow(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numOfVertex = adjacencyMatrix.length;
    }

    public List<Integer> fordFulkerson(int src, int des) {
        int u, v;
        
        int[][] tempGraph = new int[numOfVertex][numOfVertex]; // change weight in adjacency Matrix

        for (u = 0; u < numOfVertex; u++) {
            for (v = 0; v < numOfVertex; v++) {
                tempGraph[u][v] = adjacencyMatrix[u][v];
            }
        }

        int[] parent = new int[numOfVertex]; // Root
        int max_flow = 0;

        while (bfs(tempGraph, src, des, parent)) { // true if visit(des) == true and add parent[v]
            int pathFlow = Integer.MAX_VALUE;
            for (v = des; v != src; v = parent[v]) { // stop when 5 != 0
                //System.out.println(v);
                //System.out.println(parent[v]);
                u = parent[v];
                // System.out.println(u);
                pathFlow = Math.min(pathFlow, tempGraph[u][v]); // set pathFlow. 
            }

            for (v = des; v != src; v = parent[v]) {
                u = parent[v];
                // System.out.println(u + " " + v + "  " + tempGraph[u][v] + " " + pathFlow);
                tempGraph[u][v] -= pathFlow;
                tempGraph[v][u] += pathFlow;
            }
            max_flow += pathFlow;
        }
        
        List<Integer> output = new ArrayList<>();        
        output.add(max_flow);
        
        boolean[] isVisited = new boolean[numOfVertex];
        dfs(tempGraph, src, isVisited);
        for (int i = 0; i < numOfVertex; i++) {
            for (int j = 0; j < numOfVertex; j++) {
                if (adjacencyMatrix[i][j] > 0 && isVisited[i] && !isVisited[j]) {
                    output.add(i);
                    output.add(j);
                }
            }
        }
        
        return output;
    }

    private boolean bfs(int[][] tempGraph, int source, int destination, int[] parent) {
        boolean[] visited = new boolean[numOfVertex];
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int i = 0; i < numOfVertex; i++) {
                if (tempGraph[v][i] > 0 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                    parent[i] = v;
                }
            }
        }
        return (visited[destination] == true);
    }

    private void dfs(int[][] tempGraph, int s, boolean[] visited) {
        visited[s] = true;
        for (int i = 0; i < numOfVertex; i++) {
            if (tempGraph[s][i] > 0 && !visited[i]) {
                dfs(tempGraph, i, visited);
            }
        }
    }
}
