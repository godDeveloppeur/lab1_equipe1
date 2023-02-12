package laboratoire2;

public class HuffmanNode implements Cloneable{

    private String name;
    private int value;
    private HuffmanNode left;
    private HuffmanNode right;

    private int bit;
    private String bitNode;
    private boolean active;

    private int numberNodeWithOutMixNode;
    private int numberNode;
    private HuffmanNode actualReadNode;

    public HuffmanNode(String name, int value, HuffmanNode left, HuffmanNode right) {
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


    public void addNode(HuffmanNode node) throws CloneNotSupportedException {
        this.numberNode += 2;
        this.numberNodeWithOutMixNode++;
        if(this.left == null && this.right == null){
            HuffmanNode newNode = new HuffmanNode(this.name, this.value, null, null);
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
            HuffmanNode newNodeLeft = initializeNewNodeLeft();
            HuffmanNode newNodeRight = initializeNewNodeRight();
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

    public void checkTheSummaryValueAndAddNode(HuffmanNode node, HuffmanNode newNodeLeft, HuffmanNode newNodeRight) throws CloneNotSupportedException {
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

    public void addNodeToRightOrToLeft(HuffmanNode node, HuffmanNode newNodeLeft, HuffmanNode newNodeRight){
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

    public HuffmanNode initializeNewNodeLeft() throws CloneNotSupportedException {
        HuffmanNode newNodeLeft;
        if(this.left.left != null){
            newNodeLeft = new HuffmanNode(this.left.name, this.left.value, (HuffmanNode) this.left.left.clone(), (HuffmanNode) this.left.right.clone());
        }else{
            newNodeLeft = new HuffmanNode(this.left.name, this.left.value, null, null);
        }
        return newNodeLeft;
    }

    public HuffmanNode initializeNewNodeRight() throws CloneNotSupportedException {
        HuffmanNode newNodeRight;
        if(this.right.left != null){
            newNodeRight = new HuffmanNode(this.right.name, this.right.value, (HuffmanNode) this.right.left.clone(), (HuffmanNode) this.right.right.clone());
        }else{
            newNodeRight = new HuffmanNode(this.right.name, this.right.value, null, null);
        }

        return newNodeRight;
    }

    public HuffmanNode readTheBit(int bit){
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


    public HuffmanNode[] getAllNodeBitInOrder(){
        System.out.println("Start generate Node Bit in order");
        this.left.setBitNode(this.left.getBit() + "");
        this.right.setBitNode(this.right.getBit() + "");
        int a = this.numberNodeWithOutMixNode;
        HuffmanNode[] tree = new HuffmanNode[this.numberNodeWithOutMixNode];
        for(int i = this.numberNode - 1; i >= 0; i--){
            HuffmanNode n = this.getTheLastChild();
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

                lastChildNode = this;
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

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}
