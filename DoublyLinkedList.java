import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node <T> head = null;
    private Node <T> tail = null;

    // internal node class to represent the data
    private static class Node <T> {
        T data;
        Node <T> prev, next;
        public Node(T data, Node <T>prev, Node <T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        @Override
        public String toString() {
            return data.toString();
        }
    }

    // empty doubly linkedlist
    public void clear() {
        Node<T> trav = head;
        while(trav != null) {
            Node<T> next = trav.next;
            trav.next = trav.prev = null;
            trav.data = null; 
            trav = next;
        }
        trav = head = tail = null;
        size = 0;
    }

    // return size of linkedlist
    public int size() {
        return size;
    }

    // is this linked list empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // add element at the end of the list
    public void addLast(T elem) {
        if(isEmpty()) head = tail = new Node<T>(elem, null, null);
        else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    // add element at the start of the list
    public void addFirst(T elem) {
        if(isEmpty()) head = tail = new Node<T>(elem, null, null);
        else {
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }
        size++;
    }

    // remove first node
    public T removeFirst() {
        if(isEmpty()) throw new RuntimeException("Empty List");
        
        T data = head.data;
        head = head.next;
        --size;

        if(isEmpty()) tail = null;
        else head.prev = null;

        return data;
    }

    // remove last node
    public T removeLast() {
        if(isEmpty()) throw new RuntimeException("Empty List");
        
        T data = tail.data;
        tail = tail.prev;
        --size;

        if(isEmpty()) head = null;
        else tail.next = null;

        return data;
    }

    // remove arbitary node
    private T remove(Node<T> node) {
        if(node.prev == null) removeFirst();
        if(node.next == null) removeLast();

        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;

        node.data = null;
        node.next = node.prev = null;

        --size;

        return data;
    }
    
    // remove node at some index
    public T removeAt(int index) {
        if(index < 0 || index >=size) {
            throw new IllegalArgumentException();
        }

        int i;
        Node<T> trav;

        if(index < size/2) {
            for(i=0, trav=head; i!=index; i++) {
                trav = trav.next;
            }
        }
        else {
            for(i=size-1, trav=tail; i!=index; i--) {
                trav = trav.prev;
            }
        }
        return remove(trav);
    }

    // remove a particular value in the LL
    public boolean remove(Object obj) {
        Node<T> trav;
        for(trav=head; trav!=null; trav=trav.next) {
            if(obj.equals(trav.data)){
                remove(trav);
                return true;
            }
        }
        return false;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return (trav != null);
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while(trav!=null){
            sb.append(trav.data + ", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> sweta = new DoublyLinkedList<Integer>();
        sweta.addFirst(1);
        sweta.addLast(3);
        sweta.addLast(8);
        sweta.addLast(2);
        sweta.addLast(6);
        System.out.println(sweta);
        sweta.removeAt(3);
        System.out.println(sweta);
    }
    
}
