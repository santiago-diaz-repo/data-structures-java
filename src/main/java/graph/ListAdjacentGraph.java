package graph;

import java.util.*;

public class ListAdjacentGraph {

    private int order;
    private int size;
    private List<Integer>[] elements;

    public ListAdjacentGraph(int order){
        this.order= order;
        this.size = 0;
        this.elements = new LinkedList[order];
        for(int i=0; i< this.elements.length; i++){
            this.elements[i] = new LinkedList<>();
        }
    }

    public List<Integer>[] getElements() {
        return elements;
    }

    public void add(int node1, int node2){
        this.elements[node1].add(node2);
        this.elements[node2].add(node1);
        this.size++;
    }

    public void addDirected(int node1, int node2){
        this.elements[node1].add(node2);
        this.size++;
    }

    public void breathFirstSearch(int root){
        boolean[] visited = new boolean[this.order];
        Arrays.fill(visited,false);
        Queue<Integer> queue = new ArrayDeque<>(this.order);
        visited[root] = true;
        queue.offer(root);
        int vis;
        while (!queue.isEmpty()){
            vis = queue.poll();
            System.out.print(String.format(" %d ->",vis));
            for (Integer numb: this.elements[vis]){
                if(!visited[numb]) {
                    visited[numb] = true;
                    queue.add(numb);
                }
            }
        }
    }

    public void depthFirstSearch(int root){
        boolean[] visited = new boolean[this.order];
        Stack<Integer> stack = new Stack<>();
        stack.push(root);
        int value;
        while (!stack.isEmpty()){
            value = stack.pop();
            if(!visited[value]) {
                System.out.print(String.format("%d -> ", value));
                visited[value] = true;
                for (int num : this.elements[value]) {
                    if (!visited[num])
                        stack.push(num);
                }
            }
        }
    }

    public void depthFirstSearch(){
        boolean[] visited = new boolean[this.order];
        for(int i =0; i < this.order; i++){
            if(!visited[i]){
                depthFirstSearch(i,visited);
            }
        }
    }

    private void depthFirstSearch(int vertex, boolean[] visited){
        visited[vertex] = true;
        System.out.print(String.format("%d ->",vertex));
        for(Integer num: this.elements[vertex]){
            if(!visited[num]){
                depthFirstSearch(num,visited);
            }
        }
    }

    public boolean cycleDetectionUndirected(){
        boolean[] visited = new boolean[this.order];
        for(int i=0; i < this.order; i++){
            if(!visited[i]){
                if(cycleDetectionUndirected(i,visited,-1))
                    return true;
            }
        }
        return false;
    }

    private boolean cycleDetectionUndirected(int node, boolean[] visited, int parent){
        visited[node] = true;
        for(Integer num: this.elements[node]){
            if(!visited[num]){
                if(cycleDetectionUndirected(num,visited,node))
                    return true;
            } else if(num != parent)
                return true;
        }
        return false;
    }

    public boolean cycleDetectionDirected() {
        boolean[] visited = new boolean[this.order];
        boolean[] curStack = new boolean[this.order];
        for(int i =0; i < this.order; i++){
                if(cycleDetectionDirected(i,visited,curStack))
                    return true;
        }
        return false;
    }

    private boolean cycleDetectionDirected(int node, boolean[] visited, boolean[] curStack){

        if (curStack[node])
            return true;

        if (visited[node])
            return false;

        curStack[node] = true;
        visited[node] = true;

        for(Integer num: this.elements[node]){
                if (cycleDetectionDirected(num,visited,curStack))
                    return true;
        }
        curStack[node] = false;

        return false;
    }

    public List<Integer> topologicalSort(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[this.order];
        for (int i = 0; i < this.order; i++){
            if(!visited[i])
                this.topologicalSort(i,visited,stack);
        }

        List<Integer> list = new ArrayList<>();
        while(!stack.isEmpty()){
            list.add(stack.pop());
        }

        return list;
    }

    private void topologicalSort(int node, boolean[] visited, Stack<Integer> stack){
        visited[node] = true;
        for(Integer num: this.elements[node]) {
            if (!visited[num])
                topologicalSort(num, visited, stack);
        }

        stack.push(node);
    }

    public ListAdjacentGraph transpose(){
        ListAdjacentGraph transposed = new ListAdjacentGraph(this.order);
        for(int i=0; i < this.order;i++ ){
            for (Integer num: this.elements[i])
                transposed.addDirected(num,i);
        }

        return transposed;
    }

    // Kosaraju's algorithm
    public void stronglyConnectedComponents(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[this.order];

        for(int i =0; i < this.order; i++){
            if(!visited[i])
                topologicalSort(i,visited,stack);
        }

        ListAdjacentGraph transposed = this.transpose();
        Arrays.fill(visited,false);
        Integer vertex;
        while (!stack.isEmpty()){
            vertex = stack.pop();
            if(!visited[vertex]) {
                transposed.depthFirstSearch(vertex,visited);
                System.out.println();
            }
        }
    }

    public int motherGraph(){
        boolean[] visited = new boolean[this.order];
        int lastV=0;
        for(int i = 0; i < this.order; i++){
            if(!visited[i]){
                this.depthFirstSearch(i,visited);
                lastV = i;
            }
        }

        Arrays.fill(visited,false);
        this.depthFirstSearch(lastV,visited);
        for (int i =0 ; i < this.order; i++) {
            if (!visited[i])
                return -1;
        }
        return lastV;
    }

    public int numberEdges(boolean directed){
        int sum=0;
        for(int i=0; i< this.order;i++){
            sum+= this.elements[i].size();
        }

        if (directed)
            return sum;

        return sum/2;
    }

    public boolean pathExists(int source, int target){
        if(source == target)
            return true;

        boolean[] visited = new boolean[this.order];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        visited[source] = true;
        int data;

        while(!stack.isEmpty()){
            data = stack.pop();

            for(Integer num: this.elements[data]){
                if(!visited[num]){
                    if(num == target)
                        return true;
                    stack.push(num);
                    visited[num] = true;
                }
            }
        }
        return false;
    }

    public boolean hasOneParent(int root){
        boolean[] visited = new boolean[this.order];
        for(int i = 0; i < this.order; i++){
            for (Integer num: this.elements[i]){
                if(visited[num])
                    return false;
                visited[num] = true;
            }
        }

        //Assuming there's only one root
        for(int i =0; i < this.order; i++){
            if(i == root && visited[i])
                return false;
            else if(i != root && !visited[i])
                return false;
        }

        return true;
    }

    public int shortestPathEdges(int source, int target){
        if (source == target)
            return 0;

        boolean[] visited = new boolean[this.order];
        int[] distance = new int[this.order];
        Queue<Integer> queue = new ArrayDeque();

        queue.add(source);
        visited[source] = true;

        int vertex;

        while (!queue.isEmpty()){
            vertex = queue.poll();

            for (Integer num: this.elements[vertex]){
                if(!visited[num]){
                    queue.add(num);
                    visited[num] = true;
                    distance[num] = distance[vertex] + 1;
                }

                if(target == num)
                    return distance[num];
            }
        }

        return -1;
    }

    public boolean isBipartite(int source){

        // 1 is red, -1 is blue
        int[] colours = new int[this.order];
        Arrays.fill(colours,0);

        Queue<Integer> queue = new ArrayDeque();
        queue.add(source);
        colours[source] = 1;

        while(!queue.isEmpty()){
            int parent = queue.poll();
            for (Integer child: this.elements[parent]){
                if(colours[child] == 0){
                    colours[child] = colours[parent] * -1;
                    queue.add(child);
                }else if (colours[parent] == colours[child])
                    return false;
            }
        }
        return true;
    }
}


