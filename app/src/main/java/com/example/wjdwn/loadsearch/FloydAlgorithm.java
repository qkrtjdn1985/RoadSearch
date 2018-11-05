package com.example.wjdwn.loadsearch;

import android.util.Log;

import java.util.Stack;

public class FloydAlgorithm {

    private Stack<Integer> st = new Stack<>();
    private Stack<Integer> result = new Stack<>();

    public Stack<Integer> getResult() {
        return this.result;
    }

    public void setResult(Stack<Integer> result) {
        this.result = result;
    }

    public void makeGraph(int N, int graph[][]) {
        int i, j;
        for(i=1;i<=N;i++) {
            for(j=1;j<=N;j++) {
                graph[i][j] = -1;
                // save INF to -1
            }
        }

        //add example road information
        graph[1][2] = 1;
        graph[2][1] = 1;
        graph[1][4] = 1;
        graph[4][1] = 1;
        graph[2][3] = 1;
        graph[3][2] = 1;
        graph[3][4] = 1;
        graph[4][3] = 1;
        graph[3][5] = 1;
        graph[5][3] = 1;
        graph[4][8] = 1;
        graph[8][4] = 1;
        graph[5][6] = 1;
        graph[6][5] = 1;
        graph[6][7] = 1;
        graph[7][6] = 1;
        graph[7][8] = 1;
        graph[8][7] = 1;
        graph[8][9] = 1;
        graph[9][8] = 1;

    }
    public int floydAlgorithm(int N, int S, int V, int graph[][]) {
        result = new Stack<>();
        int i, j, k;
        int[][] D = new int[10][10];
        //block change graph
        int[][] P = new int[10][10];
        //save pass information
        for(i=1;i<=N;i++) {
            for(j=1;j<=N;j++) {
                P[i][j] = S;
                //reset S, start node
            }
        }
        for(i=1;i<=N;i++) {
            for(j=1;j<=N;j++) {
                D[i][j] = graph[i][j];
            }
        }//copy graph to D
        for(k=1;k<=N;k++) {
            for(i=1;i<=N;i++) {
                for(j=1;j<=N;j++) {
                    if(D[i][k] != -1 && D[k][j] != -1) {
                        //exist i -> k, k -> j.
                        if(D[i][j] == -1 || D[i][k] + D[k][j] < D[i][j]) {
                            //not exist i -> j or price(i -> k) + price(k -> j) < price(i -> j)
                            D[i][j] = D[i][k] + D[k][j];
                            //change better result
                            P[i][j] = k;
                            //save pass information
                        }
                    }
                }
            }
        }//floydAlgorithm
        //for printing pass information
        st.push(V);
        //input end node
        while(st.peek() != S) {
            //report while access start node
            st.push(P[S][st.peek()]);
            //push previous node
        }


        System.out.println(D[S][V]);
        //print Shortest distance S -> V
        System.out.print("Route is : ");
        for(i=st.size();i>0;i--) {
            System.out.print(st.peek());
            result.push(st.pop());
            System.out.print(i==1?'\n':" -> ");
        }
        return D[S][V];
        //print pass node in turn
    }
}
