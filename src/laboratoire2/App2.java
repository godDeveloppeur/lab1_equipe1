package laboratoire2;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class App2 {
    public static void main(String[] args) {
        testTheHuffManTree();
        /*testCompressTypePdf();
        testDeCompressTypePdf();*/
        /*testCompressTypeDocx();
        testDeCompressTypeDocx();*/
        testCompress();
        testDeCompress();

    }

    public static void testCompressTypePdf(){
        Huffman huff = new Huffman();
        String fileNameEntry = "src/laboratoire2/exemplePdf.pdf";
        String fileNameOut = "src/laboratoire2/exemplePdfCompresse.pdf";

        try {
            huff.Compresser(fileNameEntry, fileNameOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testDeCompressTypePdf(){
        Huffman huff = new Huffman();
        String fileNameOut = "src/laboratoire2/exemplePdfCompresse.pdf";
        String fileNameDecompress = "src/laboratoire2/exemplePdfDeCompresse.pdf";

        try {
            huff.Decompresser(fileNameOut, fileNameDecompress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testCompressTypeDocx(){
        Huffman huff = new Huffman();
        String fileNameEntry = "src/laboratoire2/exempleDocx.docx";
        String fileNameOut = "src/laboratoire2/exempleDocxCompresse.docx";

        try {
            huff.Compresser(fileNameEntry, fileNameOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testDeCompressTypeDocx(){
        Huffman huff = new Huffman();
        String fileNameOut = "src/laboratoire2/exempleDocxCompresse.docx";
        String fileNameDecompress = "src/laboratoire2/exempleDocxDeCompress.docx";

        try {
            huff.Decompresser(fileNameOut, fileNameDecompress);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        PriorityQueue<HuffmanNode> huffmanNodePriorityQueue = new PriorityQueue<>();
        huffmanNodePriorityQueue.offer(new HuffmanNode("c", 16, null, null));
        huffmanNodePriorityQueue.offer(new HuffmanNode("a", 25, null, null));
        huffmanNodePriorityQueue.offer(new HuffmanNode("b", 20, null, null));
        huffmanNodePriorityQueue.offer(new HuffmanNode("r", 22, null, null));
        huffmanNodePriorityQueue.offer(new HuffmanNode("e", 16, null, null));
        huffmanNodePriorityQueue.offer(new HuffmanNode("h", 10, null, null));
        huffmanNodePriorityQueue.offer(new HuffmanNode("g", 12, null, null));
        huffmanNodePriorityQueue.offer(new HuffmanNode("f", 14, null, null));

        HuffmanNode tree = null;
        try {
            tree = huff.creationDArbreHuffman(huffmanNodePriorityQueue);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(tree.printTabName());
        System.out.println(tree.printTabValue());

        System.out.println(tree.printTabBitCode());

        Map<String, String> allNodeBit = tree.getAllNodeBitInOrder();
        Set<Map.Entry<String, String>> setHm = allNodeBit.entrySet();
        Iterator<Map.Entry<String, String>> it = setHm.iterator();
        while(it.hasNext()){
            Map.Entry<String, String> e = it.next();
            System.out.println(e.getKey() + " : "  + e.getValue() );
        }
    }
}
