/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShortestPath;

import java.util.Scanner;

/**
 *
 * @author Salahuddin
 */
public class ShortestPath {

    static int INF = Integer.MAX_VALUE / 2 - 1;
    static int adjacency_matrix[][], parent[][];
    static int n;

    public static void floydWarshallPath() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || adjacency_matrix[i][j] == INF) {
                    parent[i][j] = -1;
                } else {
                    parent[i][j] = i;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int newD = adjacency_matrix[i][k] + adjacency_matrix[k][j];
                    if (newD < adjacency_matrix[i][j]) {
                        adjacency_matrix[i][j] = newD;
                        parent[i][j] = parent[k][j];
                    }
                }
            }
        }
    }

    static String path(int u, int v) {
        String path = "No path found!!";

        if (parent[u][v] != -1) {
            path = v + "";
            while (u != v) {
                v = parent[u][v];
                path = v + " -> " + path;
            }
        }
        return path;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int number_no_nodes;
        Scanner scanner;

        try {
            System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            n = number_no_nodes = scanner.nextInt();

            adjacency_matrix = new int[number_no_nodes][number_no_nodes];
            parent = new int[number_no_nodes][number_no_nodes];

            System.out.println("Enter the adjacency matrix");
            for (int i = 0; i < number_no_nodes; i++) {
                for (int j = 0; j < number_no_nodes; j++) {
                    int k = scanner.nextInt();
                    switch (k) {
                        case -1:
                            adjacency_matrix[i][j] = 0;
                            break;
                        case 0:
                            adjacency_matrix[i][j] = INF;
                            break;
                        default:
                            adjacency_matrix[i][j] = k;
                            break;
                    }
                }
            }

            floydWarshallPath();
            System.out.println("All pair shortest path ");
            for (int i = 0; i < number_no_nodes; i++) {
                for (int j = 0; j < number_no_nodes; j++) {
                    if (adjacency_matrix[i][j] == INF) {
                        System.out.print(" X ");
                    } else {
                        System.out.printf("%2d ", adjacency_matrix[i][j]);
                    }
                }
                System.out.print("\n");
            }

            System.out.println("Path Recovery");
            for (int i = 0; i < number_no_nodes; i++) {
                for (int j = 0; j < number_no_nodes; j++) {
                    if (parent[i][j] == -1) {
                        System.out.print(" X ");
                    } else {
                        System.out.printf("%2d ", parent[i][j]);
                    }
                }
                System.out.print("\n");
            }

            System.out.println("Path = " + path(7, 4));
        } catch (Exception e) {
            System.out.println("Wrong Input format");
        }
    }
}
