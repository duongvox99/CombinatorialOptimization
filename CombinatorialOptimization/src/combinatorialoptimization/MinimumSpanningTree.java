package combinatorialoptimization;

import java.util.List;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jackson
 */
public class MinimumSpanningTree {

    private int[][] adjacencyMatrix;
    private int numOfVertex;

    public MinimumSpanningTree(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        numOfVertex = adjacencyMatrix.length;
    }

    private int minDistance(int dist[], Boolean checkVertex[]) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;
        for (int i = 0; i < numOfVertex; i++) {
            if (!checkVertex[i] && dist[i] < min) {
                min = dist[i];
                min_index = i;
            }
        }
        return min_index;
    }

    public List<Integer> prim() {
        int[] dist = new int[numOfVertex]; // Shorest distance from 0 to i
        int[] pathTree = new int[numOfVertex];
        Boolean[] checkVertex = new Boolean[numOfVertex];

        for (int i = 0; i < numOfVertex; i++) {
            dist[i] = Integer.MAX_VALUE;
            checkVertex[i] = false;
        }

        dist[0] = 0;
        pathTree[0] = -1;

        int sum = 0;
        for (int i = 0; i < numOfVertex; i++) {
            int temp = minDistance(dist, checkVertex); // min edge and return nearest numOfVertex
            //System.out.println(temp);

            checkVertex[temp] = true; // numOfVertex did complete
            for (int j = 1; j < numOfVertex; j++) {
                if (adjacencyMatrix[temp][j] != 0 && !checkVertex[j] && adjacencyMatrix[temp][j] < dist[j]) {
                    pathTree[j] = temp;
                    dist[j] = adjacencyMatrix[temp][j];
                    sum += adjacencyMatrix[temp][j];
                }
            }
        }

        List<Integer> output = new ArrayList<>();

        output.add(sum);

        for (int i = 1; i < numOfVertex; i++) {
            output.add(pathTree[i]);
            output.add(i);
        }

        return output;
    }
}
