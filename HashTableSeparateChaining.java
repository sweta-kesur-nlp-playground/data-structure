import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

class Entry<K, V> {
    
    int hash;
    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    // check equal hash values in a table
    public boolean equals(Entry<K, V> other) {
        if(hash != other.hash) 
            return false;
        return key.equals(other.key);
    }

    @Override
    public String toString() {
        return key + " => " + value;
    }
}

public class HashTableSeparateChaining<K, V> implements Iterable<K> {

    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K, V>>[] table;

    public HashTableSeparateChaining() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity, double maxLoadFactor) {
        if(capacity < 0)
            throw new IllegalArgumentException("Illegal capacity");
        if(maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
            throw new IllegalArgumentException("Illegal maxLoadFactor");
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        threshold = (int) (this.capacity * maxLoadFactor);
        table = new LinkedList[this.capacity];
    }

    // size of elements currently inside hash table
    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int normalizeIndex(int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    // clear all the hash table elements
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    // find index using hashfunction
    // search for key in that index
    public boolean containsKey(K key) {
        return hasKey(key);
    }

    public boolean hasKey(K key) {
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }


    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
        if(key == null)
            return null;
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if(bucket == null)
            return null;
        for(Entry<K, V> entry : bucket)
            if(entry.key.equals(key))
                return entry;
        return null;
    }

    // insert entry in to hash table
    public V add(K key, V value) {
        return insert(key, value);
    }

    private V insert(K key, V value) {
        
        if(key == null)
            throw new IllegalArgumentException("Null key");
        Entry<K, V> newEntry = new Entry<K, V>(key, value);
        int bucketIndex = normalizeIndex(newEntry.hash);
        return bucketInsertEntry(bucketIndex, newEntry);
    }

    private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {
        LinkedList<Entry <K, V>> bucket = table[bucketIndex];

        if(bucket == null)
            table[bucketIndex] = bucket = new LinkedList<>();

        Entry<K, V> exisEntry = bucketSeekEntry(bucketIndex, entry.key);

        if(exisEntry == null){
            bucket.add(entry);
            if(++size > threshold)
                resizeTable();
            return null;
        }
        else {
            V oldVal = exisEntry.value;
            exisEntry.value = entry.value;
            return oldVal;
        }
    }

    private void resizeTable() {
        
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);

        LinkedList<Entry<K,V>>[] newTable = new LinkedList[capacity];

        for(int i=0; i<table.length; i++) {
            if(table[i] != null) {
                for(Entry<K,V> entry: table[i]) {
                    int bucketIndex = normalizeIndex(entry.hash);
                    LinkedList<Entry<K,V>> bucket = newTable[bucketIndex];
                    if(bucket == null)
                        newTable[bucketIndex] = bucket = new LinkedList<>();
                    bucket.add(entry);
                }

                // clear the memory
                table[i].clear();
                table[i] = null;
            }
        }
        table = newTable;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i=0;i<table.length;i++){
            for(Entry<K,V> entry : table[i]) {
                sb.append(entry+", ");
            } 
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {

        HashTableSeparateChaining hashSweta = new HashTableSeparateChaining(6);
        hashSweta.add("S", 1);
        hashSweta.add("w", 2);
        hashSweta.add("e",3);
        hashSweta.add("t", 4);
        hashSweta.add("a", 5);
        System.out.println(hashSweta.toString());
    }
    
}
