package laboratoire2;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HuffmanNode implements Comparable<HuffmanNode>, Serializable {

    private String name;
    private int frequence;
    private HuffmanNode left;
    private HuffmanNode right;

    private int bit;
    private String bitNode;
    private boolean active;

    private HuffmanNode actualReadNode;

    public HuffmanNode(String name, int value, HuffmanNode left, HuffmanNode right) {
        this.name = name;
        this.frequence = value;
        this.left = left;
        this.right = right;
        this.bit = -1;
        this.bitNode = "";
        this.active = true;
        this.actualReadNode = this;
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return frequence - node.getFrequence();
    }

    public HuffmanNode readTheBit(int bit){
        if(bit == 0){
            this.actualReadNode = this.actualReadNode.left;
        }else{
            this.actualReadNode = this.actualReadNode.right;
        }

        return this.actualReadNode;
    }

    public void resetActualReadNode(){
        this.actualReadNode = this;
    }


    public Map<String, String> getAllNodeBitInOrder(){
        System.out.println("Start generate Node Bit");
        this.left.setBitNode(this.left.getBit() + "");
        this.right.setBitNode(this.right.getBit() + "");
        Map<String, String> allNodeBit = new HashMap<>();
        HuffmanNode n = this.getTheLastChild();
        while(n != null){
            n.setActive(false);
            if(!n.getName().equals("#")){
                allNodeBit.put(n.getName(), n.getBitNode());
            }
            n = this.getTheLastChild();
        }

        this.reputAllTheNodeActive();
        System.out.println("Finish generate Node Bit");
        return allNodeBit;
    }

    public void reputAllTheNodeActive(){
        this.setActive(true);
        if(this.left != null){
            this.left.reputAllTheNodeActive();
        }

        if(this.right != null){
            this.right.reputAllTheNodeActive();
        }
    }

    public HuffmanNode getTheLastChild(){
        HuffmanNode lastChildNode = null;
        if(this.left != null && this.left.isActive()){
            if(this.right != null && this.right.isActive()){
                lastChildNode = compareLastChildNode(this.left.getTheLastChild(), this.right.getTheLastChild());
            }else{
                lastChildNode = this.left.getTheLastChild();
            }
        }else{
            if(this.right != null && this.right.isActive()){
                lastChildNode = this.right.getTheLastChild();
            }else{
                if(this.isActive()){
                    lastChildNode = this;
                }else{
                    lastChildNode = null;
                }
            }
        }
        return lastChildNode;
    }

    public HuffmanNode compareLastChildNode(HuffmanNode node1, HuffmanNode node2){
        if(node1.getBitNode().length() > node2.getBitNode().length()){
            return node1;
        }else{
            if(node1.getBitNode().length() < node2.getBitNode().length()){
                return node2;
            }else{
                return node1;
            }
        }
    }



    public String printTabName(){
        String resultat = "";
        resultat += this.name + ":"+ this.getBit() +  "," ;
        if(this.left != null){
            resultat += this.left.printTabName();
        }

        if(this.right != null){
            resultat += this.right.printTabName();
        }

        return resultat;
    }

    public String printTabBitCode(){
        String resultat = "";
        resultat += this.name + ":"+ this.getBitNode() +  "," ;
        if(this.left != null){
            resultat += this.left.printTabBitCode();
        }

        if(this.right != null){
            resultat += this.right.printTabBitCode();
        }

        return resultat;
    }


    public String printTabValue(){
        String resultat = "";
        resultat += this.frequence + ",";
        if(this.left != null){
            resultat += this.left.printTabValue();
        }

        if(this.right != null){
            resultat += this.right.printTabValue();
        }

        return resultat;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }

    public String getBitNode() {
        return bitNode;
    }

    public void setBitNode(String bitNode) {
        this.bitNode = bitNode;
        if(this.left != null){
            this.left.setBitNode(this.getBitNode() + "" + this.left.getBit());
        }

        if(this.right != null){
            this.right.setBitNode(this.getBitNode() + "" + this.right.getBit());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

}
