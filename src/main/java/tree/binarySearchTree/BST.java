package tree.binarySearchTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BST {

     class TreeNode{
        private Integer value;
        private TreeNode leftNode, rightNode;

        public TreeNode(Integer value){
            this.value = value;
            this.leftNode = null;
            this.rightNode = null;
        }

        public Integer getValue(){
            return this.value;
        }
    }

    private TreeNode root;
    private Integer size;

    public BST(){
        this.root = null;
        this.size = 0;
    }

    public boolean addIterative(Integer value){

        if(this.isEmpty()){
            this.root = new TreeNode(value);
            this.size++;
            return true;
        }

        TreeNode temp = root;

        while (temp != null){

            if(value <= temp.value){
                if(temp.leftNode == null){
                    temp.leftNode = new TreeNode(value);
                    this.size++;
                    return true;
                }
                temp = temp.leftNode;
            }else{
                if(temp.rightNode == null){
                    temp.rightNode = new TreeNode(value);
                    this.size++;
                    return true;
                }
                temp = temp.rightNode;
            }
        }

        return false;
    }

    public boolean addRecursive(Integer value){
        this.root = this.addRecursive(this.root,value);
        this.size++;
        return true;
    }

    private TreeNode addRecursive(TreeNode current,Integer value){
        if(current == null){
            current = new TreeNode(value);
        } else if(value <= current.value){
            current.leftNode = addRecursive(current.leftNode,value);
        } else {
            current.rightNode = addRecursive(current.rightNode,value);
        }
        return current;
    }

    public TreeNode searchIterative(Integer value){

        if(this.isEmpty())
            return null;

        TreeNode temp = this.root;
        while(temp != null){
            if(value == temp.value){
                return temp;
            }

            if(value <= temp.value){
                temp = temp.leftNode;
            } else {
                temp = temp.rightNode;
            }
        }

        return null;
    }

    public TreeNode searchRecursive(Integer value){
        if(this.isEmpty())
            return null;
        return this.searchRecursive(this.root,value);
    }

    private TreeNode searchRecursive(TreeNode node, Integer value){
        if(node == null || value == node.value){
            return node;
        } else if(value <= node.value){
            return searchRecursive(node.leftNode, value);
        } else {
            return searchRecursive(node.rightNode, value);
        }
    }

    public boolean deleteNode(Integer value){
        if(this.isEmpty())
            return false;

        TreeNode parent = null;
        TreeNode current = this.root;
        while (current != null && value != current.value ){
            parent = current;
            if(value <= current.value){
                current = current.leftNode;
            }else {
                current = current.rightNode;
            }
        }

        if(current == null)
            return false;

        if(current.leftNode == null && current.rightNode == null){

            if(current == this.root){
                this.root = null;
            } else if(value <= parent.value){
                parent.leftNode = null;
            } else {
                parent.rightNode = null;
            }

            this.size--;
            return true;

        } else if(current.leftNode != null && current.rightNode != null){

            TreeNode mostLeft = findMostLeftNode(current.rightNode);
            Integer temp = mostLeft.value;
            deleteNode(temp);
            current.value = temp;
            return true;

        }else {

            if(value <= parent.value){
                if(current.leftNode != null)
                    parent.leftNode = current.leftNode;
                else
                    parent.leftNode = current.rightNode;
            }else {
                if(current.leftNode != null)
                    parent.rightNode = current.leftNode;
                else
                    parent.rightNode = current.rightNode;
            }

            this.size--;
            return true;
        }
    }

    private TreeNode findMostLeftNode(TreeNode node){
        TreeNode temp = node;
        while(temp.leftNode != null)
            temp = temp.leftNode;

        return temp;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public Integer getSize(){
        return this.size;
    }

    public List<Integer> preOrderTraversal(){
        if(this.isEmpty())
            return null;

        List<Integer> data = new ArrayList<>();
        this.preOrderTraversal(this.root,data);
        return data;
    }

    private void preOrderTraversal(TreeNode node, List<Integer> data){
        data.add(node.value);
        if(node.leftNode != null)
            preOrderTraversal(node.leftNode,data);
        if(node.rightNode != null)
            preOrderTraversal(node.rightNode,data);
    }

    public List<Integer> inOrderTraversal(){
        if(this.isEmpty())
            return null;

        List<Integer> data = new ArrayList<>();
        this.inOrderTraversal(this.root,data);
        return data;
    }

    private void inOrderTraversal(TreeNode node, List<Integer> data){
        if(node.leftNode != null)
            inOrderTraversal(node.leftNode,data);
        data.add(node.value);

        if(node.rightNode != null)
            inOrderTraversal(node.rightNode,data);
    }

    public List<Integer> postOrderTraversal(){
        if(this.isEmpty())
            return null;

        List<Integer> data= new ArrayList<>();
        this.postOrderTraversal(this.root,data);
        return data;
    }

    private void postOrderTraversal(TreeNode node,List<Integer> data){
        if(node.leftNode != null)
            postOrderTraversal(node.leftNode, data);
        if(node.rightNode != null)
            postOrderTraversal(node.rightNode,data);
        data.add(node.value);
    }

}
