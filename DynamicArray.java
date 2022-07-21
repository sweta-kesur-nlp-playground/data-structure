import java.util.Arrays;
import java.util.Iterator;

public class DynamicArray implements Iterable<Integer> {

    public static final int DEFAULT_CAP = 1 << 3;

    public int[] arr;
    public int len = 0;
    private int capacity = 0;

    // intialize array with default capacity
    public DynamicArray(){
        this(DEFAULT_CAP);
    }

    // intialize array with a certain capacity
    public DynamicArray(int capacity){
        if (capacity < 0) throw new IllegalArgumentException("Illegall Capacity: " + capacity);
        this.capacity = capacity;
        arr = new int[capacity];
    }

    // given an array make it a dynamic array
    public DynamicArray(int[] array) {
        if(array == null) throw new IllegalArgumentException("Array cannot be null");
        arr = Arrays.copyOf(array, array.length);
        capacity = len = array.length;
    }

    // return size of an array
    public int size() {
        return len;
    }

    // return true/false to know the array is empty or not
    public boolean isEmpty() {
        return len == 0;
    }

    public int get(int index) {
        return arr[index];
    }

    public void set(int index, int elem) {
        arr[index] = elem;
    }

    // add an element to this dynamic array
    public void add(int elem) {
        if(len + 1 >= capacity) {
            if(capacity == 0) capacity = 1;
            else capacity *= 2;
            arr = Arrays.copyOf(arr, capacity);
        }
        arr[len++] = elem;
    }

    // remove last element in an array
    public void removeAt(int rm_index) {
        System.arraycopy(arr, rm_index + 1, arr, rm_index, len - rm_index -1);
        len--;
        capacity--;
    }

    // remove an element of a particular index in an array
    public boolean remove(int elem) {
        for(int i=0;i<len;i++){
            if(arr[i] == elem) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    // reverse array contents
    public void reverse() {
        for(int i=0;i<len/2;i++){
            int temp = arr[i];
            arr[i] = arr[len - i - 1];
            arr[len - i - 1] = temp;
        }
    }

    // binary search of an element
    public int binarySearch(int elem) {
        int index = Arrays.binarySearch(arr, 0, len, elem);
        return index;
    } 

    // sort array
    public void sort() {
        Arrays.sort(arr, 0, len);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int index = 0;

            public boolean hasNext() {
                return index < len;
            }

            @Override
            public Integer next() {
                return arr[index++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if(len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for(int i=0;i<len-1;i++) sb.append(arr[i] + ", ");
            return sb.append(arr[len - 1] + "]").toString();
        }
    }

    // example usage
    public static void main(String args[]){
        
        DynamicArray ar = new DynamicArray(50);
        ar.add(3);
        ar.add(7);
        ar.add(6);
        ar.add(-2);

        ar.sort();

        System.out.println(ar);
    }
}