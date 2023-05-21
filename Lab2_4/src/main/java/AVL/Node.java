package AVL;

public class Node {
    int value;
    int height;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.height = 1; // высота нового узла равна 1
    }
}