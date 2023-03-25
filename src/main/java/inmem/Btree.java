package inmem;

public class Btree {
    BtreeNode root;
    int order;
    public Btree(int order){
        this.order = order;
        root = new BtreeNode(order);
    }

    // Insert a key in B tree
    public void insert(int key){
        BtreeNode  n = root;
        if(n.currentElements == 2*order - 1){
            BtreeNode newRoot = new BtreeNode(order);
            newRoot.isLeaf = false;
            root = newRoot;
            newRoot.children[0] = n;
            split(n, newRoot, 0);
            insert(root, key);
        }else{
            insert(n , key);
        }
    }

    private void insert(BtreeNode node, int k){
        if(node.isLeaf){
            int i = node.currentElements - 1;
            for( ;i >= 0 && k < node.keys[i]; i-- ){
                node.keys[i + 1] = node.keys[i];
            }
            i++;
            node.keys[i] = k;
            node.currentElements += 1;
        }else{
            int i = node.currentElements - 1;
            for( ; i >= 0 && k < node.keys[i] ; i-- ){
            }
            i++;
            BtreeNode tmp = node.children[i];

            if(tmp.currentElements == 2*order - 1 ){
                split(tmp, node, i);
                if(node.keys[i] < k)++i;
            }
            insert(node.children[i], k);
        }
    }

    // split node into sibling add node into root at correct position
    private void split(BtreeNode node, BtreeNode root, int pos){
        BtreeNode sibling  = new BtreeNode(order);
        sibling.isLeaf = node.isLeaf;
        // move  order - 1 keys to sibling from [order, 2*order - 1}
        for(int i  =  0 ; i < order - 1 ; i++ ){
             sibling.keys[i] = node.keys[i + order];
        }
        // move order children to sibling [order, 2*order - 1 ]
        if(!node.isLeaf) {
            for (int i = 0; i < order; i++) {
                sibling.children[i] = node.children[i + order];
            }
        }
        // increase size  of sibling
        sibling.currentElements = order - 1;

        // reduce size of node
        node.currentElements = order - 1;

        // shift root keys to the right
        for(int i = root.currentElements - 1 ; i >= pos ; i-- ) {
            root.keys[i + 1] = root.keys[i];
        }
        // shift root children to the right
        for(int i = root.currentElements; i >= pos + 1; i-- ){
            root.children[i + 1] = root.children[i];
        }
        // set current sibling as left node to pos + 1 element
        root.keys[pos] = node.keys[order - 1];
        root.children[pos + 1] = sibling;
        root.currentElements++;
    }
}
