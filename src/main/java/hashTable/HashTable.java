package hashTable;

import java.util.ArrayList;

public class HashTable {

    private ArrayList<HashEntry> bucket;
    private int slots;
    private int size;

    class HashEntry{
        String key;
        int value;
        HashEntry next;

        public HashEntry(String key, int value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public HashTable() {
        bucket = new ArrayList <HashEntry>();
        slots = 10;
        size = 0;
        //initialize buckets
        for (int i = 0; i < slots; i++)
            bucket.add(null);

    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size() == 0;
    }

    private int getIndex(String key) {
        //hashCode is a built in function of Strings
        int hashCode = key.hashCode();
        int index = hashCode % slots;
        //Check if index is negative
        if(index<0){
            index=(index + slots) % slots;
        }
        return index;
    }

    public void insert(String key, int value){
        int pos = this.getIndex(key);
        HashEntry temp = this.bucket.get(pos);

        while (temp != null){
            if(temp.key.equals(key)){
                temp.value = value;
                return;
            }
            temp = temp.next;
        }

        temp = this.bucket.get(pos);
        HashEntry newData = new HashEntry(key,value);
        newData.next = temp;
        this.bucket.set(pos,newData);
    }

}
