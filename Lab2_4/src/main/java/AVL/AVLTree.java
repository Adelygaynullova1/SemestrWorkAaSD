package AVL;

public class AVLTree {
    Node root;
    public int count = 0;

    // метод для получения высоты узла
    private int height(Node node) {
        if (node == null) {
            count++;
            return 0;
        } else {
            count++;
            return node.height;
        }
    }

    // метод для получения разницы высоты поддеревьев узла
    private int balanceFactor(Node node) {
        if (node == null) {
            count++;
            return 0;
        } else {
            count++;
            return height(node.left) - height(node.right); //возвращаем разницу высот левого и правого поддеревьев узла.
        }
    }

    // метод для пересчета высоты узла
    private void updateHeight(Node node) {
        count++;
        node.height = Math.max(height(node.left), height(node.right)) + 1; //устанавливаем новое значение высоты узла, как максимум из высот левого и правого поддеревьев плюс 1.
    }

    // метод для поворота право
    private Node rotateRight(Node node) {
        count++;
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    // метод для поворота влево
    private Node rotateLeft(Node node) {
        count++;
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    // метод для балансировки
    private Node balance(Node node) {
        updateHeight(node);
        if (balanceFactor(node) == 2) {
            count++;
            if (balanceFactor(node.left) < 0) {
                count++;
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (balanceFactor(node) == -2) {
            count++;
            if (balanceFactor(node.right) > 0) {
                count++;
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        return node;
    }

    // метод для добавления узла
    private Node insertNode(Node node, int value) {
        if (node == null) {
            count++;
            return new Node(value);
        }
        if (value < node.value) { // - если вставляемое значение меньше, чем значение узла
            count++;
            node.left = insertNode(node.left, value); //  рекурсивно вызываем insertNode для левого поддерева узла node
        } else if (value > node.value) {  //  иначе, если вставляемое значение больше, чем значение узла
            count++;
            node.right = insertNode(node.right, value);  //рекурсивно вызываем insertNode для правого поддерева узла node
        }
        return balance(node);
    }

    public void insert(int value) {
        root = insertNode(root, value);
    }

    // метод для поиска узла
    private boolean searchNode(Node node, int value) {
        if (node == null) {
            count++;
            return false;
        }
        if (value == node.value) {
            count++;
            return true;
        } else if (value < node.value) {  // иначе, если искомое значение меньше, чем значение узла
            count++;
            return searchNode(node.left, value); //  рекурсивно вызываем searchNode для левого поддерева узла node
        } else {
            count++;
            return searchNode(node.right, value);  // рекурсивно вызываем searchNode для правого поддерева узла node
        }
    }

    public boolean search(int value) {
        return searchNode(root, value);
    }

    // метод для нахождения минимального узла
    private Node findMin(Node node) {
        while (node.left != null) {
            count++;
            node = node.left;
        }
        return node;
    }

    // метод для удаления узла
    private Node removeNode(Node node, int value) {
        if (node == null) {
            count++;
            return null;
        }
        if (value < node.value) {  // Если значение удаляемого элемента меньше значения текущего узла
            count++;
            node.left = removeNode(node.left, value);  // идем в левое поддерево рекурсивно выполняя поиск по дереву
        } else if (value > node.value) {  // Если значение больше значения текущего узла
            count++;
            node.right = removeNode(node.right, value);  // идем в правое поддерево рекурсивно выполняя поиск по дереву
        } else {
            count++;
            Node leftChild = node.left;
            Node rightChild = node.right;
            if (rightChild == null) { // случай, когда нет правого поддерева
                count++;
                return leftChild; // Если правое поддерево отсутствует, просто заменяем текущий узел на его левого потомка.
            }
            // Если правое поддерево не отсутствует
            count++;
            Node minNode = findMin(rightChild); // находим минимальный узел в правом поддереве
            minNode.right = removeNode(rightChild, minNode.value);
            minNode.left = leftChild;
            return balance(minNode);
        }
        return balance(node);
    }

    public void remove(int value) {
        root = removeNode(root, value);
    }

    public int getCount() {
        return count;
    }
}
