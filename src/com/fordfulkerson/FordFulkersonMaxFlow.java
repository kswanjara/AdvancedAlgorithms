package com.fordfulkerson;

import java.util.ArrayDeque;
import java.util.Queue;

public class FordFulkersonMaxFlow {

    private int findMaxFlow(int[][] network, int source, int sink) {
        int[] path = new int[network.length];
        int[][] residualGraph = new int[network.length][network.length];
        int maxFlow = 0;
        // initializing residual graph
        for(int i = 0; i < network.length; i++)
            for(int j = 0; j < network.length; j++)
                residualGraph[i][j] = network[i][j];

        while(augmentedPathExists(residualGraph, source, sink, path)){

            int pathFlow = Integer.MAX_VALUE;
            for(int t = sink; t != source; t = path[t]){
                if(residualGraph[path[t]][t] < pathFlow){
                    pathFlow = residualGraph[path[t]][t];
                }
            }

            // update the residual graph
            for(int t = sink; t != source; t = path[t]){
                residualGraph[path[t]][t] -= pathFlow;
                residualGraph[t][path[t]] += pathFlow;
            }

            maxFlow += pathFlow;
            path = new int[network.length];
        }


        return maxFlow;
    }

    private boolean augmentedPathExists(int[][] residualGraph, int source, int sink, int[] path) {
        boolean[] visited = new boolean[residualGraph.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);

        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int i = 0; i < residualGraph.length; i++){
                if(residualGraph[current][i] > 0 && !visited[i]){
                    queue.add(i);
                    visited[i] = true;
                    path[i] = current;
                }
            }
        }

        return visited[sink];
    }

    public static void main(String[] args) {
//        int[][] network = {
//                {0, 3, 2, 0}, {0, 0, 5, 2}, {0, 0, 0, 3}, {0, 0, 0, 0}
//        };

        int network[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        int source = 0;
//        int sink = 3;
        int sink = 5;

        FordFulkersonMaxFlow f = new FordFulkersonMaxFlow();

        int maxFlow = f.findMaxFlow(network, source, sink);

        System.out.println("Max flow for the given network : " + maxFlow);
    }
}
