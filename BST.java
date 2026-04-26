import java.util.NoSuchElementException;

public class BST {
    public Node root;

    public void insert(int element) {
        Node newNode = new Node(element);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        while (true) {
            if (element < current.getElement()) {
                if (current.hasLeftChild()) {
                    current = current.getLeftChild();
                } else {
                    current.setLeftChild(newNode);
                    newNode.setFather(current);
                    break;
                }
            } else if (element > current.getElement()) {
                if (current.hasRightChild()) {
                    current = current.getRightChild();
                } else {
                    current.setRightChild(newNode);
                    newNode.setFather(current);
                    break;
                }
            }
        }
    }

    public Node search(int element) {
        Node current = root;

        while (current != null) {
            if (element == current.getElement()) {
                return current;
            } else if (element < current.getElement()) {
                current = current.getLeftChild();
            } else if (element > current.getElement()) {
                current = current.getRightChild();
            }
        }

        throw new NoSuchElementException("Node " + element + " is not present in the tree");
    }

    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.getLeftChild());
            System.out.print(node.getElement() + "");
            inOrder(node.getRightChild());
        }
    }

    public Node findSuccessor(Node node) {
        Node current = node.getRightChild();

        while (current.hasLeftChild()) {
            current = current.getLeftChild();
        }

        return current;
    }

    public Node remove(int element) {
        Node node = search(element);

        if (node == null) {
            throw new NoSuchElementException("Node " + element + " is not present in the tree");
        }

        Node temp = node;
        
        if (node.numberOfChilds() == 2) {
            Node successor = findSuccessor(node);
            Node successorFather = successor.getFather();

            if (successor.isLeftChild()) {
                successorFather.setLeftChild(null);
            } else if (successor.isRightChild()) {
                successorFather.setRightChild(null);
            }

            if (node.isRoot()) {
                root = successor;
            }

            successor.setLeftChild(node.getLeftChild()); 
            node.getLeftChild().setFather(successor);

            successor.setRightChild(node.getRightChild());

            if (successorFather != node) {
                node.getRightChild().setFather(successor);
            }

            successor.setFather(node.getFather());
            if (node.isLeftChild()) {
                node.getFather().setLeftChild(successor);
            } else if (node.isRightChild()) {
                node.getFather().setRightChild(successor);
            }

            return temp;
        }

        if (node.numberOfChilds() == 1) {
            if (node.isRoot()) {
                if (node.hasLeftChild()) {
                    root = node.getLeftChild();
                } else {
                    root = node.getRightChild();
                }
                root.setFather(null);
            }
            
            if (node.isLeftChild()) {
                if (node.hasLeftChild()) {
                    node.getLeftChild().setFather(node.getFather());
                    node.getFather().setLeftChild(node.getLeftChild());
                } else {
                    node.getRightChild().setFather(node.getFather());
                    node.getFather().setRightChild(node.getRightChild());
                }
            } else if (node.isRightChild()){
                if(node.hasLeftChild()) {
                    node.getLeftChild().setFather(node.getFather());
                    node.getFather().setRightChild(node.getLeftChild());
                } else {
                    node.getRightChild().setFather(node.getFather());
                    node.getFather().setRightChild(node.getRightChild());
                }
            }
            return temp;
        }
        
        if (node.numberOfChilds() == 0) {
            if (node.isRoot()) {
                root = null;
            } else if (node.isLeftChild()) {
                node.getFather().setLeftChild(null);
            } else if (node.isRightChild()){
                node.getFather().setRightChild(null);
            }
            return temp;
        }

        return temp;
    }

    public int height(Node node) {
        if (node == null) {
            return -1;
        }

        int heightLeft = height(node.getLeftChild());
        int heightRight = height(node.getRightChild());

        return Math.max(heightLeft, heightRight) + 1;
    }

    public void fillMatrix(Node node, Object[][] matrix, int line, int left, int right) {
        if (node == null)
            return;

        int mid = (left + right) / 2;
        matrix[line][mid] = node.getElement();

        fillMatrix(node.getLeftChild(), matrix, line + 1, left, mid - 1);

        fillMatrix(node.getRightChild(), matrix, line + 1, mid + 1, right);
    }

    public void show() {
        int h = height(root);
        if (h == -1) {
            throw new NoSuchElementException("The tree is empty");
        }

        int lines = h + 1;
        int columns = (int) Math.pow(2, h + 1) - 1;

        Object[][] matrix = new Object[lines][columns];

        fillMatrix(root, matrix, 0, 0, columns - 1);

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == null) {
                    System.out.print("  ");
                } else {
                    System.out.printf("%2d", matrix[i][j]);
                }
            }
            System.out.println();
        }
    }
}
