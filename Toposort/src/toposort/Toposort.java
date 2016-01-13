/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toposort;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Salahuddin
 */
public class Toposort {

    public static Stack<Integer> stack = new Stack<>();

    public static int[] topological(int adjacency_matrix[][]) throws NullPointerException {
        int number_of_nodes = adjacency_matrix[0].length ;
        int[] topological_sort = new int[number_of_nodes];
        int pos = 0, i = 0, j;
        int visited[] = new int[number_of_nodes];

        while (!stack.isEmpty()) {
            int element = stack.peek();
            visited[element] = 1;
            while (i < number_of_nodes) {
                if (adjacency_matrix[element][i] == 1 && visited[i] == 1) {
                    if (stack.contains(i)) {
                        System.out.println("TOPOLOGICAL SORT NOT POSSIBLE");
                        return null;
                    }
                }
                if (adjacency_matrix[element][i] == 1 && visited[i] == 0) {
                    stack.push(i);
                    visited[i] = 1;
                    element = i;
                    i = 0;
                    continue;
                }
                i++;
            }
            j = stack.pop();
            topological_sort[pos++] = j;
            i = 0;
        }
        return topological_sort;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int number_no_nodes;
        Scanner scanner = null;
        int topological_sort[] = null;
        try {
            System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            number_no_nodes = scanner.nextInt();

            int adjacency_matrix[][] = new int[number_no_nodes][number_no_nodes];
            System.out.println("Enter the adjacency matrix");
            for (int i = 0; i < number_no_nodes; i++) {
                for (int j = 0; j < number_no_nodes; j++) {
                    adjacency_matrix[i][j] = scanner.nextInt();
                }
            }

            for (int i = 0; i < number_no_nodes; i++) {
                boolean hasIncomingEdge = false;
                for (int j = 0; j < number_no_nodes; j++) {
                    if (adjacency_matrix[j][i] == 1) {
                        hasIncomingEdge = true;
                        break;
                    }
                }
                if (!hasIncomingEdge) {
                    stack.push(i);
                    System.out.println("" + i);
                }
            }
           
            System.out.println("The Topological sort for the graph is given by ");
            topological_sort = topological(adjacency_matrix);
            System.out.println();
            for (int i = topological_sort.length - 1; i >= 0; i--) {
                    System.out.print(topological_sort[i] + "\t");
            }
        } catch (InputMismatchException inputMismatch) {
            System.out.println("Wrong Input format");
        } catch (NullPointerException nullPointer) {
        }
        scanner.close();
    }

}
