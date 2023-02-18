package lab2;

public class Node implements Comparable<Node>{
    int frequence;
    char data;
    Node left;
    Node right;

    @Override
    public int compareTo(Node node) {
        return frequence - node.frequence;
    }
}
