package graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class MatrixAdjacentGraph {

    private int size;
    private int order;
    private int[][] elements;

    public MatrixAdjacentGraph(int order){
        this.order = order;
        this.size = 0;
        this.elements = new int[order][order];
        for(int[] arr: this.elements)
            Arrays.fill(arr,0);
    }

    public void add(int node1, int node2){
        this.elements[node1][node2]=1;
        this.elements[node1][node2]=1;
        this.size++;
    }

    public void breathFirstSearch(int root){
        boolean[] visited = new boolean[this.order];
        Arrays.fill(visited,false);
        Queue<Integer> queue = new ArrayDeque<>(this.order);
        visited[root] = true;
        queue.offer(root);
        int vis;
        while(!queue.isEmpty()){
            vis = queue.poll();
            System.out.print(String.format("%d -> ",vis));
            for(int i=0; i< this.order; i++){
                if(this.elements[vis][i] == 1 && !visited[i]){
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }

    public void depthFirstSearch(int root){
        boolean[] visited = new boolean[this.order];
        Arrays.fill(visited,false);
        Stack<Integer> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            int val = stack.pop();
            if(!visited[val]){
                visited[val] = true;
                System.out.print(String.format("%d ->",val));
                for(int i =0; i < this.order; i++){
                    if(this.elements[val][i] == 1 && !visited[i]){
                        stack.push(i);
                    }
                }
            }
        }
    }

    public void depthFirstSearch(){
        boolean[] visited = new boolean[this.order];
        for(int i = 0;i < this.order; i++ ){
            if(!visited[i])
                depthFirstSearch(i,visited);
        }
    }

    private void depthFirstSearch(int vertex, boolean[] visited){
        visited[vertex] = true;
        System.out.print(String.format("%d ->", vertex ));
        for(int i =0; i< this.elements.length; i++){
            if(this.elements[vertex][i] == 1 && !visited[i]){
                depthFirstSearch(i,visited);
            }
        }
    }

    public boolean cycleDetectionUndirected(){
        boolean[] visited = new boolean[this.order];
        for (int i =0; i < this.order; i++){
            if(!visited[i]){
                if(cycleDetectionUndirected(i,visited,-1))
                    return true;
            }
        }
        return false;
    }

    private boolean cycleDetectionUndirected(int node, boolean[] visited, int parent){
        visited[node] = true;
        for(int i = 0; i < this.order; i++){
            if(this.elements[node][i] == 1){
                if(!visited[i]){
                    if(cycleDetectionUndirected(i,visited,node))
                        return true;
                } else if(parent != -1 && i != parent)
                    return true;
            }
        }
        return false;
    }
}
