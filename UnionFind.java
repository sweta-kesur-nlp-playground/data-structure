public class UnionFind {

    // number of elements in union find
    private int size;

    // size of elements in each component
    private int[] sz;

    // array that stores all the parent node values
    private int[] id;

    // tracks the number of componenets in the union find
    private int numComponents;

    public UnionFind(int size) {
        if(size <= 0) throw new IllegalArgumentException("Size <=0 is not allowed");

        this.size = numComponents = size;

        for(int i=0; i<size; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // find which component 'p' belongs to
    public int find(int p) {
        // find the root of the component/set
        int root = p;

        while(root != id[root])
            root = id[root];

        // path compression
        while(p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    // recursive method for find operation
    // with path compression
    public int findRecursive(int p) {
        if(p == id[p]) return p;
        
        return id[p] = find(id[p]);
    }

    // return whether 'p' & 'q' is in same component
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    // return size of a component
    public int componentSize(int p) {
        return sz[find(p)];
    }

    // unify operation
    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        if(root1 == root2) return;

        if(sz[root1] > sz[root2]) {
            id[root2] = root1;
            sz[root1] += sz[root2]; 
        }
        else{
            id[root1] = root2;
            sz[root2] += sz[root1];
        }

        numComponents--;
    }
}