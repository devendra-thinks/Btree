package inmem;

public class TestBtree {
    public static  void iterate(BtreeNode itr){
        if(itr == null)return;
        for (int i = 0 ; i < itr.currentElements ; i++ ){
            System.out.print(itr.keys[i] + " ");
        }

        System.out.println();
        for (int i = 0 ; i <= itr.currentElements ; i++ ){
            iterate(itr.children[i]);
        }
    }

    public static void main(String[] args) {
        Btree btree = new Btree(2);
        // 3 keys, 4 children
        btree.insert(5);
        btree.insert(10);
        btree.insert(15);
        btree.insert(20);
        btree.insert(25);
        btree.insert(30);
        btree.insert(35);
        btree.insert(40);
        btree.insert(45);

        iterate(btree.root);
    }
}
