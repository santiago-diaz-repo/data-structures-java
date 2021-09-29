package trie;

public class Trie {

    private TrieNode root;
    private int size;

    class TrieNode{
        TrieNode[] children;
        boolean isWord;
        public static final int SIZE = 26;

        public TrieNode(){
            this.children = new TrieNode[SIZE];
            this.isWord = false;
        }

        public void markAsWord(){
            this.isWord = true;
        }

        public void unmarkAsWord(){
            this.isWord = false;
        }
    }

    public Trie(){
        this.root = new TrieNode();
    }

    public boolean insert(String word){
        if(null == word)
            return false;

        word = word.toLowerCase();
        TrieNode temp = this.root;
        int pos = -1;
        for(int i = 0; i < word.length(); i++){
             pos = this.getPosition(word.charAt(i));
             if(temp.children[pos] == null){
                 temp.children[pos] = new TrieNode();
             }
             temp = temp.children[pos];
        }

        temp.markAsWord();
        this.size++;
        return true;
    }

    private int getPosition(char letter){
        return letter - 'a';
    }

    public boolean wordExist(String word){
        if(null == word)
            return false;

        word = word.toLowerCase();
        TrieNode temp = this.root;
        int pos = -1;
        for (int i = 0; i < word.length(); i++){
            pos = this.getPosition(word.charAt(i));
            if(temp.children[pos] == null)
                return false;
            temp = temp.children[pos];
        }

        return temp.isWord;
    }

    public int getSize(){
        return this.size;
    }

    public boolean delete(String word){
        if(word == null)
            return false;

        return this.delete(this.root,word,0);
    }

    private boolean delete(TrieNode node, String word, int position){

        int pos = this.getPosition(word.charAt(position));
        if(position == word.length() - 1){
            if(!node.children[pos].isWord )
                return false;
        } else{

            if(node.children[pos] == null)
                return false;
            this.delete(node.children[pos],word,++position);
        }
        this.deleteHelper(node,pos);
        return true;
    }

    private void deleteHelper(TrieNode node, int pos){
        if(node.children[pos] != null){
            node.unmarkAsWord();
        }

        if(this.hasMoreChildren(node)) {
            node.unmarkAsWord();
            return;
        }else {
            node = null;
        }
    }

    private boolean hasMoreChildren(TrieNode node){
        for (int i = 0; i < TrieNode.SIZE; i++){
            if (node.children[i] != null)
                return true;
        }
        return false;
    }
}
