public class IntQueue {

    private int[] ar;
    private int front, end, sz;

    // create queue with maxsize
    public IntQueue(int maxsize) {
        front = end = 0;
        sz = maxsize + 1;
        ar = new int[sz];
    }

    // check empty queue
    public boolean isEmpty() {
        return front == end;
    }

    // return number of elems in queue
    public int size() {
        return end - front + 1;
    }

    public int peek(){
        return ar[front];
    }

    // enqueuing
    public void enqueue(int value) {
        if(end == size()) throw new RuntimeException("Queue full");
        ar[end++] = value;
    }

    // dequeuing
    public int dequeue() {
        if(isEmpty()) throw new RuntimeException("Queue empty");
        return ar[front++];
    }

    // print
    public void print() {
        for(int i=front; i<end; i++)
            System.out.print(ar[i]+" ");
    }

    public static void main(String[] args) {
        IntQueue qu = new IntQueue(6);

        qu.enqueue(1);
        qu.enqueue(2);
        qu.enqueue(3);
        qu.enqueue(4);
        qu.enqueue(5);

        // qu.print();

        qu.dequeue();
        qu.dequeue();

        qu.enqueue(6);
        qu.print();
    }
}