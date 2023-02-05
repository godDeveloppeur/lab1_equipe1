package laboratoire2;

public class HuffmanNode implements Cloneable{

    public String name;
    public int value;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode parent;
    public int bit = -1;

    public HuffmanNode(String name, int value, HuffmanNode left, HuffmanNode right) {
        this.name = name;
        this.value = value;
        this.left = left;
        this.right = right;
    }


    public void addNode(HuffmanNode node) throws CloneNotSupportedException {
        if(this.left == null && this.right == null){
            HuffmanNode newNode = new HuffmanNode(this.name, this.value, null, null);
            this.name = "#";
            this.value = newNode.getValue() + node.getValue();
            if(newNode.getValue() <= node.getValue()){
                this.left = newNode;
                this.right = node;
            }else{
                this.left = node;
                this.right = newNode;
            }
        }else{
            if(node.getValue() >= this.value){
                HuffmanNode newNodeLeft = initializeNewNodeLeft();
                HuffmanNode newNodeRight = initializeNewNodeRight();

                this.left.left = newNodeLeft;
                this.left.right = newNodeRight;
                this.left.name = this.name;
                this.left.value = this.value;

                this.value = this.left.value + node.getValue();
                this.right = node;
            }else{
                HuffmanNode newNodeLeft = initializeNewNodeLeft();
                HuffmanNode newNodeRight = initializeNewNodeRight();
                this.right.left = newNodeLeft;
                this.right.right = newNodeRight;
                this.right.name = this.name;
                this.right.value = this.value;

                this.value = this.right.value + node.getValue();
                this.left = node;
            }

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

    public String printTabName(){
        String resultat = "";
        resultat += this.name + ",";
        if(this.left != null){
            resultat += this.left.printTabName();
        }

        if(this.right != null){
            resultat += this.right.printTabName();
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
