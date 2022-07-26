import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PriorityQueue<T extends Comparable<T>> {

    // no. of elements inside heap
    private int heapSize = 0;   

    // heap capacity
    private int heapCapacity = 0;

    // heap array representation
    private List<T> heap = null;

    // create map to remove node at O(log n) time and O(1) for searching
    private Map<T, TreeSet<Integer>> map = new HashMap<>();
    
    // empty priority queue
    public PriorityQueue() {
        this(1);
    }

    // check for empty queue
    public boolean isEmpty() {
        return heapSize == 0;
    }

    // clear inside heap
    public void clear() {

    }

    // priority queue constructed using initial size
    public PriorityQueue(int sz) {
        heap = new ArrayList<>(sz);
    }

    // construct heap using binary tree: Heapify in O(n)
    public PriorityQueue(T[] elems) {
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList<>(heapCapacity);

        // add elements into heap
        for(int i=0;i<heapSize;i++) {
            heap.add(elems[i]);
            mapAdd(elems[i], i);
        }

        // heapify process
        for(int i=Math.max(0, heapSize/2 -1); i>=0; i--)
            sink(i);
    }

    private void sink(int i) {
        while(true) {
            int left = 2*i + 1;
            int right = 2*i + 2;
            int smallest = left;

            if(right<heapSize && less(right, left))
                smallest = right;

            if(left>=heapSize || less(i, smallest))
                break;

            swap(i, smallest);
            i = smallest;
        }

    }

    private void swap(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);

        mapSwap(elem_i, elem_j, i, j);
    }

    private void mapSwap(T val1, T val2, int val1Index, int val2Index) {
        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        set1.remove(val1Index);
        set2.remove(val2Index);

        set1.add(val2Index);
        set2.add(val1Index);
    }

    private boolean less(int right, int left) {
        T node1 = heap.get(right);
        T node2 = heap.get(left);

        return node1.compareTo(node2) <= 0;
    }

    // insert an element into heap/priority queue
    public void add(T elem) {
        if(elem == null) throw new IllegalArgumentException();

        if(heapSize < heapCapacity) {
            heap.set(heapSize, elem);
        }
        else {
            heap.add(elem);
            heapCapacity++;
        }

        mapAdd(elem, heapSize);

        swim(heapSize);
        heapSize++;

    }

    private void mapAdd(T elem, int index) {
        TreeSet<Integer> set = map.get(elem);

        if(set == null){
            set = new TreeSet<>();
            set.add(index);
            map.put(elem, set);
        }
        else{   //set already exists
            set.add(index);
        }
    }

    private void swim(int cur) {
        int parent = (cur-1)/2;

        while (cur > 0 && less(cur, parent)) {
            swap(cur, parent);
            cur = parent;

            parent = (cur-1)/2;
        }
    }

    // remove root node
    public T poll() {
        return removeAt(0);
    }

    private T removeAt(int cur) {
        if(isEmpty()) return null;

        heapSize--;
        T remove_data = heap.get(cur);
        swap(cur, heapSize);

        // memory cleanup
        heap.set(heapSize, null);
        mapRemove(remove_data, heapSize);

        if(cur == heapSize) return remove_data;
        T elem = heap.get(cur);

        swim(cur);

        if(heap.get(cur).equals(elem))
            sink(cur);
        return remove_data;
    }

    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        set.remove(index);
        if(set.size()==0)
            map.remove(value);
    }

    public static void main(String[] args) {
        Integer[] pq = new Integer[] {23, 12, 67, 43, 23, 89, 10, 33};
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(pq);

        heap.print();

        heap.add(16);

        heap.print();

        heap.poll();

        heap.print();
    }

    private void print() {
        for(int i=0;i<heapSize;i++)
            System.out.print(heap.get(i)+" ");
        System.out.println();
    }
}
