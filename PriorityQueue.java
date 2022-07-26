import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>> {

    // no. of elements inside heap
    private int heapSize = 0;   

    // heap capacity
    private int heapCapacity = 0;

    // heap array representation
    private List<T> heap = null;
    
    // empty priority queue
    public PriorityQueue() {
        this(1);
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
        for(int i=0;i<heapSize;i++)
            heap.add(elems[i]);

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
    }

    private boolean less(int right, int left) {
        T node1 = heap.get(right);
        T node2 = heap.get(left);

        return node1.compareTo(node2) <= 0;
    }

    public static void main(String[] args) {
        Integer[] pq = new Integer[] {23, 12, 67, 43, 23, 89, 10, 33};
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(pq);

        heap.print();
    }

    private void print() {
        for(int i=0;i<heapSize;i++)
            System.out.print(heap.get(i)+" ");
    }
}
