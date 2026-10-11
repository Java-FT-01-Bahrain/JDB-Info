class BST {
    private BSTNode root;

    /* Constructor */
    public BST() {
        root = null;
    }

    /* Function to check if tree is empty */
    public boolean isEmpty() {
        return root == null;
    }

    /* Functions to insert data */
    public void insert(int data) {
        root = insert(root, data);
    }

    /* Function to insert data recursively */
    private BSTNode insert(BSTNode node, int data) {
        if (node == null)
            node = new BSTNode(data);
        else {
            if (data <= node.getData())
                node.left = insert(node.left, data);
            else
                node.right = insert(node.right, data);
        }
        return node;
    }

    /* Functions to delete data */
    public void delete(int k) {
        if (isEmpty())
            System.out.println("Tree Empty");
        else if (!search(k))
            System.out.println("Sorry " + k + " is not present");
        else {
            root = delete(root, k);
            System.out.println(k + " deleted from the tree");
        }
    }

    private BSTNode delete(BSTNode root, int k) {
        BSTNode p, p2, n;
        if (root.getData() == k) {
            BSTNode lt, rt;
            lt = root.getLeft();
            rt = root.getRight();
            if (lt == null && rt == null)
                return null;
            else if (lt == null) {
                p = rt;
                return p;
            } else if (rt == null) {
                p = lt;
                return p;
            } else {
                p2 = rt;
                p = rt;
                while (p.getLeft() != null)
                    p = p.getLeft();
                p.setLeft(lt);
                return p2;
            }
        }
        if (k < root.getData()) {
            n = delete(root.getLeft(), k);
            root.setLeft(n);
        } else {
            n = delete(root.getRight(), k);
            root.setRight(n);
        }
        return root;
    }

    /* Functions to count number of nodes */
    public int countNodes() {
        return countNodes(root);
    }

    /* Function to count number of nodes recursively */
    private int countNodes(BSTNode r) {
        if (r == null)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }

    /* Functions to search for an element */
    public boolean search(int val) {
        return search(root, val);
    }

    /* Function to search for an element recursively */
    private boolean search(BSTNode r, int val) {
        if (r == null) {
            return false;  // Base case: value not found
        }

        int rval = r.getData();
        if (val < rval) {
            return search(r.getLeft(), val);  // Search in the left subtree
        } else if (val > rval) {
            return search(r.getRight(), val);  // Search in the right subtree
        } else {
            return true;  // Base case: value found
        }
    }


    /* Function for inorder traversal */
    public void inorder() {
        inorder(root);
    }

    private void inorder(BSTNode r) {
        if (r != null) {
            inorder(r.getLeft());
            System.out.print(r.getData() + " ");
            inorder(r.getRight());
        }
    }

    /* Function for preorder traversal */
    public void preorder() {
        preorder(root);
    }

    private void preorder(BSTNode r) {
        if (r != null) {
            System.out.print(r.getData() + " ");
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }

    /* Function for postorder traversal */
    public void postorder() {
        postorder(root);
    }

    private void postorder(BSTNode r) {
        if (r != null) {
            postorder(r.getLeft());
            postorder(r.getRight());
            System.out.print(r.getData() + " ");
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();

        /* Test insertions */
        System.out.println("Inserting values 10, 5, 15, 3, 7, 13, 17 into the BST.");
        int[] valuesToInsert = {10, 5, 15, 3, 7, 13, 17};
        for (int value : valuesToInsert) {
            bst.insert(value);
        }

        /* Test inorder traversal */
        System.out.print("Inorder traversal: ");
        bst.inorder();
        System.out.println();

        /* Test preorder traversal */
        System.out.print("Preorder traversal: ");
        bst.preorder();
        System.out.println();

        /* Test postorder traversal */
        System.out.print("Postorder traversal: ");
        bst.postorder();
        System.out.println();

        /* Test countNodes */
        System.out.println("Number of nodes in the tree: " + bst.countNodes());

        /* Test search */
        int searchValue = 7;
        System.out.println("Searching for value " + searchValue + ": " + (bst.search(searchValue) ? "found" : "not found"));

        searchValue = 20;
        System.out.println("Searching for value " + searchValue + ": " + (bst.search(searchValue) ? "found" : "not found"));

        /* Test deletion */
        int valueToDelete = 10;
        System.out.println("Deleting value " + valueToDelete);
        bst.delete(valueToDelete);

        System.out.print("Inorder traversal after deletion: ");
        bst.inorder();
        System.out.println();
    }
}