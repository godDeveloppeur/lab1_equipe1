package lab2Essaie;
/*
public class HuffmanNode implements Cloneable{

    private String name;
    private int value;
    private laboratoire2.HuffmanNode left;
    private laboratoire2.HuffmanNode right;

    private int bit;
    private String bitNode;
    private boolean active;

    private int numberNodeWithOutMixNode;
    private int numberNode;
    private laboratoire2.HuffmanNode actualReadNode;

    public HuffmanNode(String name, int value, laboratoire2.HuffmanNode left, laboratoire2.HuffmanNode right) {
        this.name = name;
        this.value = value;
        this.left = left;
        this.right = right;
        this.bit = -1;
        this.bitNode = "";
        this.active = true;
        this.numberNode = 0;
        this.numberNodeWithOutMixNode = 1;
        this.actualReadNode = this;
    }


    public void addNode(laboratoire2.HuffmanNode node) throws CloneNotSupportedException {
        this.numberNode += 2;
        this.numberNodeWithOutMixNode++;
        if(this.left == null && this.right == null){
            laboratoire2.HuffmanNode newNode = new laboratoire2.HuffmanNode(this.name, this.value, null, null);
            this.name = "#";
            this.value = newNode.getValue() + node.getValue();
            if(newNode.getValue() <= node.getValue()){
                newNode.setBit(1);
                node.setBit(0);
                this.left = newNode;
                this.right = node;
            }else{
                newNode.setBit(0);
                node.setBit(1);
                this.left = node;
                this.right = newNode;
            }
        }else{
            laboratoire2.HuffmanNode newNodeLeft = initializeNewNodeLeft();
            laboratoire2.HuffmanNode newNodeRight = initializeNewNodeRight();
            newNodeLeft.setBit(1);
            newNodeRight.setBit(0);
            if(this.left.left != null || this.right.left != null){
                if(this.left.left == null){
                    checkTheSummaryValueAndAddNode(node, newNodeLeft, newNodeRight);
                }else if(this.right.left == null){
                    this.right.addNode(node);
                }else{
                    if(node.getValue() >= this.right.getValue()){
                        addNodeToRightOrToLeft(node, newNodeLeft, newNodeRight);
                    }else{
                        checkTheSummaryValueAndAddNode(node, newNodeLeft, newNodeRight);
                    }

                }
                this.value = this.left.getValue() + this.right.getValue();
            }else{
                addNodeToRightOrToLeft(node, newNodeLeft, newNodeRight);
            }
        }
    }

    public void checkTheSummaryValueAndAddNode(laboratoire2.HuffmanNode node, laboratoire2.HuffmanNode newNodeLeft, laboratoire2.HuffmanNode newNodeRight) throws CloneNotSupportedException {
        if((this.left.getValue() + node.getValue()) < this.right.getValue()){
            this.left.addNode(node);
        }else{
            newNodeLeft.setBit(0);
            newNodeRight.setBit(1);
            this.left = newNodeRight;
            this.right = newNodeLeft;
            this.right.addNode(node);
        }
    }

    public void addNodeToRightOrToLeft(laboratoire2.HuffmanNode node, laboratoire2.HuffmanNode newNodeLeft, laboratoire2.HuffmanNode newNodeRight){
        if(node.getValue() >= this.value){
            this.left.left = newNodeLeft;
            this.left.right = newNodeRight;
            this.left.name = this.name;
            this.left.value = this.value;

            this.value = this.left.value + node.getValue();
            node.setBit(0);
            this.right = node;
        }else{
            this.right.left = newNodeLeft;
            this.right.right = newNodeRight;
            this.right.name = this.name;
            this.right.value = this.value;

            this.value = this.right.value + node.getValue();
            node.setBit(1);
            this.left = node;
        }
    }

    public laboratoire2.HuffmanNode initializeNewNodeLeft() throws CloneNotSupportedException {
        laboratoire2.HuffmanNode newNodeLeft;
        if(this.left.left != null){
            newNodeLeft = new laboratoire2.HuffmanNode(this.left.name, this.left.value, (laboratoire2.HuffmanNode) this.left.left.clone(), (laboratoire2.HuffmanNode) this.left.right.clone());
        }else{
            newNodeLeft = new laboratoire2.HuffmanNode(this.left.name, this.left.value, null, null);
        }
        return newNodeLeft;
    }

    public laboratoire2.HuffmanNode initializeNewNodeRight() throws CloneNotSupportedException {
        laboratoire2.HuffmanNode newNodeRight;
        if(this.right.left != null){
            newNodeRight = new laboratoire2.HuffmanNode(this.right.name, this.right.value, (laboratoire2.HuffmanNode) this.right.left.clone(), (laboratoire2.HuffmanNode) this.right.right.clone());
        }else{
            newNodeRight = new laboratoire2.HuffmanNode(this.right.name, this.right.value, null, null);
        }

        return newNodeRight;
    }

    public laboratoire2.HuffmanNode readTheBit(int bit){
        if(bit == 1){
            this.actualReadNode = this.actualReadNode.left;
        }else{
            this.actualReadNode = this.actualReadNode.right;
        }

        return this.actualReadNode;
    }

    public void resetActualReadNode(){
        this.actualReadNode = this;
    }


    public laboratoire2.HuffmanNode[] getAllNodeBitInOrder(){
        System.out.println("Start generate Node Bit in order");
        this.left.setBitNode(this.left.getBit() + "");
        this.right.setBitNode(this.right.getBit() + "");
        int a = this.numberNodeWithOutMixNode;
        laboratoire2.HuffmanNode[] tree = new laboratoire2.HuffmanNode[this.numberNodeWithOutMixNode];
        for(int i = this.numberNode - 1; i >= 0; i--){
            laboratoire2.HuffmanNode n = this.getTheLastChild();
            n.setActive(false);
            if(!n.getName().equals("#")){
                a--;
                tree[a] = n;
            }

        }
        this.reputAllTheNodeActive();
        System.out.println("Finish generate Node Bit in order");
        return tree;
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

    public laboratoire2.HuffmanNode getTheLastChild(){
        laboratoire2.HuffmanNode lastChildNode = null;
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

                lastChildNode = this;
            }
        }
        return lastChildNode;
    }

    public laboratoire2.HuffmanNode compareLastChildNode(laboratoire2.HuffmanNode node1, laboratoire2.HuffmanNode node2){
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
        resultat += this.value + ",";
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public laboratoire2.HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(laboratoire2.HuffmanNode left) {
        this.left = left;
    }

    public laboratoire2.HuffmanNode getRight() {
        return right;
    }

    public void setRight(laboratoire2.HuffmanNode right) {
        this.right = right;
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}
*/