package bin;

public class BSTree {
    public class Node {
        int value;
        Node left_child, right_child;
        Node(int value) {
            this.value = value;
            left_child = null;
            right_child = null;
        }

    }

    private Node root;
    public BSTree(int root_value) {
        root = new Node(root_value);
        
    }

    public void in_order() {
        in_order_recursive(root);
    }

    private void in_order_recursive(Node root) {
        if(root != null) {
            in_order_recursive(root.left_child);
            System.out.println(root.value + " ");
            in_order_recursive(root.right_child);
        }
    }

    private Node insert_recursive(Node root, int value) {
        if(root == null) {
            root = new Node(value);
            return root;
        }if(value < root.value) {
            root.left_child = insert_recursive(root.left_child, value);
        }else if(value > root.value) {
            root.right_child = insert_recursive(root.right_child, value);
        }
        return root;
    }

    public void insert(int value) {
        root = insert_recursive(root,value);
    }

    private int min_value(Node root) {
        int minv = root.value;
        while(root.left_child != null) {
            minv = root.left_child.value;
            root = root.left_child;
        }
        return minv;
    }

    private Node delete_recursive(Node root, int value) {
        if(root == null) return root;
        if(value < root.value) {
            root.left_child = delete_recursive(root.left_child, value);
        }else if(value > root.value) {
            root.right_child = delete_recursive(root.right_child, value);
        }else {
            if(root.left_child == null) {
                return root.right_child;
            }else if(root.right_child == null) {
                return root.left_child;
            }
            root.value = min_value(root.right_child);
            root.right_child = delete_recursive(root.right_child, root.value);
        }
        return root;
    }

    public void delete(int value) {
        root = delete_recursive(root, value);
    }
}
