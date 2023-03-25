package inmem;

public class BtreeNode {
    BtreeNode [] children;
    int [] keys;
    boolean isLeaf;
    int currentElements;

    public BtreeNode(int order){
        children = new BtreeNode[2*order];
        keys = new int[2*order - 1];
        this.isLeaf = true;
        currentElements = 0;
    }
}
