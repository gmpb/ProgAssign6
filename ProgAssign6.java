package progassign6;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * <class> ProgAssign6 </class> contains a method to create an adjacency matrix
 * from a text file of data and a method to construct a minimum spanning tree
 * from the adjacency matrix.
 *
 * @author Greg Becker
 */
public class ProgAssign6 {

    int CITI;
    int[][] adjacency;
    int bestcost = Integer.MAX_VALUE;

    Stack pathStack = new Stack();
    int[] visitedCities;
    int currentCity;
    int visited = 1;
    int closestCity;
    boolean minFlag;
    int min;

    /**
     * Lab4: constructor where number of cities are assigned by the user.
     *
     * @param N: the number of cities
     */
    public ProgAssign6(int N) {
        CITI = N;
        adjacency = new int[N][N];
        visitedCities = new int[N];

    } // lab4 constructor

    /*
     * populateMatix: the values of cities are assigned through a text file
     */
    public void populateMatrix() {
        File f = new File("tsp" + CITI + ".txt");
        try {
            Scanner input = new Scanner(f);
            int value, i, j;
            for (i = 0; i < CITI && input.hasNext(); i++) {
                for (j = i; j < CITI && input.hasNext(); j++) {
                    if (i == j) {
                        adjacency[i][j] = 0;
                    } else {
                        value = input.nextInt();
                        adjacency[i][j] = value;
                        adjacency[j][i] = value;
                    } // else
                } // for
            } // for
        } catch (IOException e) {
            e.printStackTrace();
        } // catch
    } // populateMatrix

    /**
     * mst: the data in the adjacency matrix is evaluated to create a
     * mininum spanning tree.
     */
    public void mst() {
        visitedCities[0] = visited;
        pathStack.push(0);
        closestCity = 0;
        minFlag = false;
        System.out.println("Start City: " + pathStack.peek());
        while (!pathStack.empty()) {
            currentCity = (int) pathStack.peek();
            min = Integer.MAX_VALUE;
            for (int i = 1; i < CITI; i++) {
                if ((adjacency[currentCity][i] != 0) && (visitedCities[i] != visited)) {
                    if ((adjacency[currentCity][i]) < min) {
                        min = adjacency[currentCity][i];
                        closestCity = i;
                        minFlag = true;
                    } // if
                } // if
            } // for
            if (minFlag) {
                visitedCities[closestCity] = visited;
                pathStack.push(closestCity);
                System.out.println("closest city " + closestCity);
                minFlag = false;
            } // if
            if ((int) (pathStack.peek()) == 0) {
                System.out.println("End city: " + pathStack.peek());
            } // if
            pathStack.pop();

        } // while
    } //mst

    /**
     * Main: where methods are called and arguments passed through by user.
     *
     * @param args
     */
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        
        ProgAssign6 l4 = new ProgAssign6(N);
        
        l4.populateMatrix();
        l4.mst();
    } // main

} // class
