package trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrieTest {

    private Trie subject;

    @BeforeEach
    public void setUp(){
        this.subject = new Trie();
    }

    @Test
    public void testInsert(){
        assertFalse(this.subject.insert(null));
        assertTrue(this.subject.insert("hello"));
        assertTrue(this.subject.insert("hint"));
        assertTrue(this.subject.insert("hi"));
        assertEquals(3, this.subject.getSize());
    }

    @Test
    public void testWordExist(){
        this.subject.insert("hello");
        this.subject.insert("hi");
        this.subject.insert("car");
        this.subject.insert("Phone");

        assertTrue(this.subject.wordExist("phone"));
        assertFalse(this.subject.wordExist("java"));
    }

    @Test
    public void testDelete(){
        this.subject.insert("hi");
        this.subject.delete("hi");
    }
}
