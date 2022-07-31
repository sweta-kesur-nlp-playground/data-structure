public class BinarySearchTree<T extends Comparable<T>> {
    // no. of nodes in BST
    private int nodeCount = 0;

    private Node root = null;

    // BST
    public class Node {
        T data;
        Node left, right;

        public Node(Node left, Node right, T elem) {
            this.data = elem;
            this.left = left;
            this.right = right; 
        }
    }

    // check if BST is empty
    public boolean isEmpty() {
        return nodeCount == 0;
    }

    // check elem present in a BST
    public boolean contains(T elem) {
        return contains(root, elem);
    }

    // recursive method to check the elem
    private boolean contains(Node node, T elem) {
        if(node == null)
            return false;

        int cmp = elem.compareTo(node.data);

        if(cmp < 0) 
            return contains(node.left, elem);
        else if(cmp == 0)
            return true;
        else 
            return contains(node.right, elem);
    }

    // add a node to BST
    public boolean add(T elem) {
        if(contains(elem)) {
            return false;
        }
        else{
            root = add(root, elem);
            nodeCount++;
            return true;
        }
    }

    private Node add(Node node, T elem) {
        if(node == null)
            return new Node(null, null, elem);
        else {
            if(elem.compareTo(node.data) < 0)
                node.left = add(node.left, elem);
            else
                node.right = add(node.right, elem);
        }
        return node;
    }

    // remove a node from BST
    public boolean remove(T elem) {
        if(contains(elem)){
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T elem) {
        if(node == null)
            return null;

        int cmp = elem.compareTo(node.data);

        if(cmp < 0){
            node.left = remove(node.left, elem);
        }
        else if(cmp > 0){
            node.right = remove(node.right, elem);
        }
        else{
            // case 1 if left subtree is null
            // and right subtree not null
            if(node.left == null) {
                return node.right;
            }
            // case 1 if right subtree is null
            // and left subtree not null
            else if(node.right == null){
                return node.left;
            }
            else {
                Node temp = findMin(node.right);

                node.data = temp.data;

                node.right = remove(node.right, temp.data);

            }
        }
        
        return node;
    }

    private Node findMin(Node node) {
        while(node != null)
            node = node.left;
        return node;
    }

    public void print(Node node) {
        inorder(node);
    }

    private void inorder(Node node) {
        if(node == null)
            return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        BinarySearchTree<String> bst = new BinarySearchTree<String>();

        bst.add("23");
        bst.add("46");
        bst.add("43");
        bst.add("53");
        bst.add("10");
        bst.add("93");
        bst.add("12");

        bst.print(bst.root);
    }
}
