package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ListAdjacentGraphTest {

    private ListAdjacentGraph subject;

    @BeforeEach
    public void setUp(){
        this.subject = new ListAdjacentGraph(5);
    }

    @Test
    @DisplayName("Test Breath first search")
    public void testBreathFirstSearch(){
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

    @Test
    public void testCycleDetectionDirected(){
        this.subject.addDirected(0,3);
        this.subject.addDirected(3,4);
        this.subject.addDirected(2,0);
        this.subject.addDirected(2,1);
        assertFalse(this.subject.cycleDetectionDirected());

        this.subject.addDirected(1,2);
        assertTrue(this.subject.cycleDetectionDirected());
    }

    @Test
    public void testTopologicalSort(){
        this.subject.addDirected(2,3);
        this.subject.addDirected(2,1);
        this.subject.addDirected(1,0);
        this.subject.addDirected(0,3);
        this.subject.addDirected(3,4);

        Integer[] data = {2,1,0,3,4};

        assertArrayEquals(data,this.subject.topologicalSort().toArray());
    }

    @Test
    public void testTransposed(){
        this.subject.addDirected(2,3);
        this.subject.addDirected(2,1);
        this.subject.addDirected(1,0);
        this.subject.addDirected(3,4);

        List<Integer>[] copy= new LinkedList[5];
        for (int i =0; i < copy.length; i++)
            copy[i] = new LinkedList<>();
        copy[0].add(1);
        copy[1].add(2);
        copy[3].add(2);
        copy[4].add(3);

        List<Integer>[] got = this.subject.transpose().getElements();
        for(int i =0; i < 5; i++){
            assertArrayEquals(copy[i].toArray(),got[i].toArray());
        }
    }

    @Test
    public void testStronglyConnectedComponents(){
        this.subject.addDirected(2,3);
        this.subject.addDirected(2,1);
        this.subject.addDirected(1,0);
        this.subject.addDirected(3,4);

        this.subject.stronglyConnectedComponents();
    }

    @Test
    public void testMotherGraph(){
        this.subject.addDirected(2,3);
        this.subject.addDirected(2,1);
        this.subject.addDirected(1,0);
        this.subject.addDirected(3,4);

        assertEquals(2,this.subject.motherGraph());
    }

    @Test
    public void testNumberEdges(){
        this.subject.add(0,2);
        this.subject.add(0,3);
        this.subject.add(2,4);
        this.subject.add(3,4);
        assertEquals(4,this.subject.numberEdges(false));

      /*  this.subject.addDirected(2,3);
        this.subject.addDirected(2,1);
        this.subject.addDirected(1,0);
        this.subject.addDirected(3,4);

        assertEquals(4,this.subject.numberEdges(true));*/
    }

    @Test
    public void testPathExist(){
        this.subject.add(0,2);
        this.subject.add(0,3);
        this.subject.add(2,4);
        this.subject.add(3,4);

        assertTrue(this.subject.pathExists(2,4));

        assertFalse(this.subject.pathExists(1,4));
    }

    @Test
    public void testOneParent() {
        this.subject.addDirected(2,3);
        this.subject.addDirected(2,1);
        this.subject.addDirected(1,0);
        this.subject.addDirected(3,4);

        assertTrue(this.subject.hasOneParent(2));
    }

    @Test
    public void testShortestPathEdges(){
        this.subject.addDirected(2,3);
        this.subject.addDirected(2,1);
        this.subject.addDirected(1,0);
        this.subject.addDirected(3,4);
        this.subject.addDirected(4,0);

        assertEquals(2,this.subject.shortestPathEdges(2,0));
    }

    @Test
    public void testIsBipartite(){
        this.subject.addDirected(2,0);
        this.subject.addDirected(2,1);
        this.subject.addDirected(1,3);
        this.subject.addDirected(0,4);

        assertTrue(this.subject.isBipartite(2));

        this.subject.addDirected(2,3);
        assertFalse(this.subject.isBipartite(2));


    }
}
