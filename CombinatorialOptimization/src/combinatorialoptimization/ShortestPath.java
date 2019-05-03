/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combinatorialoptimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jackson
 */
public class ShortestPath {

    private int[][] adjacencyMatrix;
    private int numOfVertex;

    public ShortestPath(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        numOfVertex = adjacencyMatrix.length;
    }

    private int minDistance(int dist[], boolean sptSet[]) {
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < numOfVertex; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    public List<Integer> dijkstra(int src, int des) {
        int dist[] = new int[numOfVertex];
        int previousVertex[] = new int[numOfVertex];
        boolean sptSet[] = new boolean[numOfVertex];
        
        for (int i = 0; i < numOfVertex; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;
        previousVertex[src] = -1;
        
        for (int count = 0; count < numOfVertex - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < numOfVertex; v++) {
                if (!sptSet[v] && adjacencyMatrix[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + adjacencyMatrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + adjacencyMatrix[u][v];
                    
                    previousVertex[v] = u;
                }
            }
        }

//        System.out.println(Arrays.toString(dist));
//        System.out.println(Arrays.toString(previousVertex));
        
        
        List<Integer> output = new ArrayList<>();
        
        if (dist[des] != Integer.MAX_VALUE) {
            int indexPrev = des;
            while (indexPrev != -1) {
                output.add(0, indexPrev);
                indexPrev = previousVertex[indexPrev];
            }
            output.add(0, dist[des]);
        }
        return output;
    }
}
