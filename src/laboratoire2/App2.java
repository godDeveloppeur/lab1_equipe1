package laboratoire2;

import java.io.IOException;

public class App2 {
    public static void main(String[] args) {
        testTheHuffManTree();
        testCompress();
        testDeCompress();

    }

    public static void testCompress(){
        Huffman huff = new Huffman();
        String fileNameEntry = "src/laboratoire2/exemple.txt";
        String fileNameOut = "src/laboratoire2/exempleCompresse.txt";

        try {
            huff.Compresser(fileNameEntry, fileNameOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testDeCompress(){
        Huffman huff = new Huffman();
        String fileNameOut = "src/laboratoire2/exempleCompresse.txt";
        String fileNameDecompress = "src/laboratoire2/exempleDeCompress.txt";

        try {
            huff.Decompresser(fileNameOut, fileNameDecompress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testTheHuffManTree(){
        Huffman huff = new Huffman();
        Frequency tabFrequencies[] = new Frequency[]{
                new Frequency("a", 25),
                new Frequency("r", 22),
                new Frequency("b", 20),
                new Frequency("d", 18),
                new Frequency("c", 16),
                new Frequency("e", 16),
                new Frequency("f", 14),
                new Frequency("g", 12),
                new Frequency("h", 10)};

        HuffmanNode tree = null;
        try {
            tree = huff.creationDArbreHuffman(tabFrequencies);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(tree.printTabName());
        System.out.println(tree.printTabValue());

        System.out.println(tree.printTabBitCode());

        HuffmanNode[] tab = tree.getAllNodeBitInOrder();

        for(HuffmanNode n : tab){
            System.out.println(n.getName() + " : " + n.getValue() + " : " + n.getBitNode() );
        }
    }
}
