import java.util.Iterator;
import java.util.LinkedList;

public class Queue<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<T>();

    // empty queue
    public Queue() {}

    // queue with some element
    public Queue(T firstElem) {
        offer(firstElem);
    }

    // return size of a queue
    public int size() {
        return list.size();
    }

    // check queue is empty or not
    public boolean isEmpty() {
        return size() == 0;
    }

    // peek the element from the front of the queue
    public T peek() {
        if(isEmpty()) throw new RuntimeException("Queue Empty");
        return list.peekFirst();
    }

    // enqueuing 
    private void offer(T elem) {
        list.addLast(elem);
    }

    // dequeuing
    private T poll() {
        if(isEmpty()) throw new RuntimeException("Queue Empty");
        return list.removeFirst();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        if(isEmpty()) return("[]");
        else{
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(int i=0;i<(size()-1);i++){
                sb.append(list.get(i) + ", ");
            }
            sb.append(list.peekLast()+"]");
            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        Queue<String> qu = new Queue<String>();
        qu.offer("S");
        qu.offer("w");
        qu.offer("e");
        qu.offer("t");
        qu.offer("a");
        System.out.println(qu);
        qu.poll();
        qu.poll();
        System.out.println(qu);
    }
}
