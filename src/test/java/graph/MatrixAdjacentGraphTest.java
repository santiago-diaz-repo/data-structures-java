package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixAdjacentGraphTest {

    private MatrixAdjacentGraph subject;

    @BeforeEach
    public void setUp(){
        this.subject = new MatrixAdjacentGraph(5);
    }

    @Test
    @DisplayName("Test Breath first search")
    public void testBreadthFirstSearch(){
        this.subject.add(0,2);
        this.subject.add(0,3);
        this.subject.add(2,4);
        this.subject.add(3,4);
        this.subject.breathFirstSearch(0);
    }

    @Test
    public void testDepthFirstSearch(){
        this.subject.add(0,2);
        this.subject.add(0,3);
        this.subject.add(2,4);
        this.subject.add(3,4);
        this.subject.depthFirstSearch(0);
    }

    @Test
    public void testDepthFirstSearchRecursive(){
        this.subject.add(0,2);
        this.subject.add(0,3);
        this.subject.add(2,4);
        this.subject.add(3,1);
        this.subject.depthFirstSearch();
    }

    @Test
    public void testCycleDetectionUndirected(){
        this.subject.add(0,1);
        this.subject.add(0,2);
        this.subject.add(1,3);
        this.subject.add(3,4);
        assertFalse(this.subject.cycleDetectionUndirected());

        this.subject.add(4,0);
        assertTrue(this.subject.cycleDetectionUndirected());

    }
}
