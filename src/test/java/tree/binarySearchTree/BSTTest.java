package tree.binarySearchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {

    private BST subject;

    @BeforeEach
    public void setUp(){
        this.subject = new BST();
    }

    @Test
    public void testAddIterative(){
        assertTrue(this.subject.addIterative(9));
        this.subject.addIterative(4);
        this.subject.addIterative(10);
        assertEquals(3,this.subject.getSize());
    }

    @Test
    public void testAddRecursive(){
        assertTrue(this.subject.addRecursive(9));
        this.subject.addRecursive(4);
        this.subject.addRecursive(10);
        assertEquals(3,this.subject.getSize());
    }

    @Test
    public void testSearchIterative(){
        this.subject.addIterative(9);
        this.subject.addIterative(4);
        this.subject.addIterative(15);
        this.subject.addIterative(11);
        this.subject.addIterative(5);
        this.subject.addIterative(3);
        BST.TreeNode got = this.subject.searchIterative(3);

        assertEquals(3, got.getValue());

        assertNull(this.subject.searchIterative(90));
    }

    @Test
    public void testSearchRecursive(){
        this.subject.addRecursive(9);
        this.subject.addRecursive(4);
        this.subject.addRecursive(15);
        this.subject.addRecursive(11);
        this.subject.addRecursive(5);
        this.subject.addRecursive(3);
        BST.TreeNode got = this.subject.searchRecursive(3);

        assertEquals(3, got.getValue());

        assertNull(this.subject.searchRecursive(90));
    }

    @Test
    public void testDelete(){
        assertFalse(this.subject.deleteNode(5));
        this.subject.addRecursive(9);
        assertTrue(this.subject.deleteNode(9));
        assertEquals(0,this.subject.getSize());

        this.subject.addRecursive(9);
        this.subject.addRecursive(4);
        this.subject.addRecursive(15);
        this.subject.addRecursive(11);
        this.subject.addRecursive(5);
        this.subject.addRecursive(3);
        this.subject.addRecursive(18);
        assertFalse(this.subject.deleteNode(90));

        assertTrue(this.subject.deleteNode(5));
        assertEquals(6,this.subject.getSize());

        assertTrue(this.subject.deleteNode(4));
        assertEquals(5,this.subject.getSize());

        assertTrue(this.subject.deleteNode(15));
        assertEquals(4,this.subject.getSize());
    }

    @Test
    public void testPreOrderTraversal(){
        this.subject.addRecursive(9);
        this.subject.addRecursive(4);
        this.subject.addRecursive(15);
        this.subject.addRecursive(11);
        this.subject.addRecursive(5);
        this.subject.addRecursive(3);
        this.subject.addRecursive(18);

        List<Integer> data = new ArrayList<>();
        data.add(9);
        data.add(4);
        data.add(3);
        data.add(5);
        data.add(15);
        data.add(11);
        data.add(18);

        assertEquals(data,this.subject.preOrderTraversal());
    }

    @Test
    public void testInOrderTraversal(){
        this.subject.addRecursive(9);
        this.subject.addRecursive(4);
        this.subject.addRecursive(15);
        this.subject.addRecursive(11);
        this.subject.addRecursive(5);
        this.subject.addRecursive(3);
        this.subject.addRecursive(18);

        List<Integer> data = new ArrayList<>();
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(9);
        data.add(11);
        data.add(15);
        data.add(18);

        assertEquals(data,this.subject.inOrderTraversal());
    }

    @Test
    public void testPostOrderTraversal(){
        this.subject.addRecursive(9);
        this.subject.addRecursive(4);
        this.subject.addRecursive(15);
        this.subject.addRecursive(11);
        this.subject.addRecursive(5);
        this.subject.addRecursive(3);
        this.subject.addRecursive(18);

        List<Integer> data = new ArrayList<>();
        data.add(3);
        data.add(5);
        data.add(4);
        data.add(11);
        data.add(18);
        data.add(15);
        data.add(9);

        assertEquals(data,this.subject.postOrderTraversal());
    }
}
