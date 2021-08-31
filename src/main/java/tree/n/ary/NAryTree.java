package tree.n.ary;

import java.util.LinkedList;
import java.util.List;

public class NAryTree {

    static class TreeNode{
        private Integer value;
        private List<TreeNode> children;

        public TreeNode(Integer value){
            this.value = value;
            this.children = new LinkedList<>();
        }

        public TreeNode(Integer value, List<TreeNode> children){
            this.value = value;
            this.children = children;
        }

        public Integer getValue(){
            return this.value;
        }

        public List<TreeNode> getChildren(){
            return this.children;
        }
    }

    private TreeNode root;

    public NAryTree(){
        this.root = null;
    }

    public NAryTree(Integer value){
        this.root = new TreeNode(value);
    }

    public NAryTree(Integer value, List<TreeNode> children){
        this.root = new TreeNode(value,children);
    }

    public TreeNode getRoot(){
        return this.root;
    }
}
