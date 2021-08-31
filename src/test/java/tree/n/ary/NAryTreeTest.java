package tree.n.ary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NAryTreeTest {

    private NAryTree subject;

    @Test
    public void testValueConstructor(){
        this.subject = new NAryTree(5);

        assertEquals(5, this.subject.getRoot().getValue());
    }

    @Test
    public void testValueChildrenConstructor(){
        List<NAryTree.TreeNode> children = new LinkedList<>();
        children.add(new NAryTree.TreeNode(3));
        children.add(new NAryTree.TreeNode(6));
        children.add(new NAryTree.TreeNode(9));
        children.add(new NAryTree.TreeNode(1));
        this.subject = new NAryTree(5, children);

        assertEquals(5,this.subject.getRoot().getValue());
        assertEquals(4, this.subject.getRoot().getChildren().size());

        this.subject.getRoot().getChildren().get(0).getChildren().add(new NAryTree.TreeNode(20));
        assertEquals(20, this.subject.getRoot().getChildren().get(0).getChildren().get(0).getValue());
    }
}
