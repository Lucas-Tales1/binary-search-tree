public class Node {
    int element;
    Node leftChild;
    Node rightChild;
    Node father;

    public Node(int element) {
        this.element = element;
        this.leftChild = null;
        this.rightChild = null;
        this.father = null;
    }

    public boolean isLeaf() {
        return this.leftChild == null && this.rightChild == null;
    }

    public boolean hasLeftChild() {
        return this.leftChild != null;
    }

    public boolean hasRightChild() {
        return this.rightChild != null;
    }

    public boolean isLeftChild() {
        return this.father != null && this.father.leftChild == this;
    }

    public boolean isRightChild() {
        return this.father != null && this.father.rightChild == this;
    }

    public boolean isRoot() {
        return this.father == null;
    }

    public int numberOfChilds() {
        int count = 0;
        if (this.leftChild != null) count++;
        if (this.rightChild != null) count++;
        return count;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    @Override
    public String toString() {
        return "Node{element=" + element + "}";
    }
}
