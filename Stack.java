import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class Stack<T> implements Iterable<T> {
    private LinkedList<T> list = new LinkedList<T>();

    // create empty stack
    public Stack() {}

    // create a stack with initial element
    public Stack(T firstElem) {
        push(firstElem);
    }

    // return teh number of elements in a stack
    public int size() {
        return list.size();
    }

    // check if the stack is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // push an element on stack
    public void push(T elem) {
        list.addLast(elem);
    }

    // pop an element off the stack
    public T pop() {
        if(isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    // peek top of the stack
    public T peek() {
        if(isEmpty()) throw new EmptyStackException();
        return list.peekLast();
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
        Stack<String> st = new Stack<String>();
        st.push("S");
        st.push("w");
        st.push("e");
        st.push("t");
        st.push("a");
        System.out.println(st);
        st.pop();
        System.out.println(st);
    }
}
