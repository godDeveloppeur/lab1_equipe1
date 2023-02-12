import laboratoire2.BitOutputStream;
import laboratoire2.Frequency;
import laboratoire2.Huffman;
import laboratoire2.HuffmanNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class App {

    public static void main(String[] args) {
        Huffman huff = new Huffman();
        /*Frequency tabFrequencies[] = new Frequency[]{
                new Frequency("a", 5),
                new Frequency("r", 2),
                new Frequency("b", 2),
                new Frequency("d", 1),
                new Frequency("c", 1)};

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

        tab = tree.getAllNodeBitInOrder();

        for(HuffmanNode n : tab){
            System.out.println(n.getName() + " : " + n.getValue() + " : " + n.getBitNode() );
        }*/

        String fileNameEntry = "src/laboratoire2/exemple.txt";
        String fileNameOut = "src/laboratoire2/exempleCompresse.gzip";

        String fileNameDecompress = "src/laboratoire2/exempleDeCompress.txt";
/*

        fileNameEntry = "src/laboratoire2/test2.pdf";
        fileNameOut = "src/laboratoire2/test2Compresse.bit";

        fileNameEntry = "src/laboratoire2/1087062.jpg";
        fileNameOut = "src/laboratoire2/imageCompresse.bit";*/
        try {
            huff.Compresser(fileNameEntry, fileNameOut);

            huff.Decompresser(fileNameOut, fileNameDecompress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*File file = new File(fileName);
        try {
            //huff.readFileBinaryModeWithBuffered();
            Map<String, Integer> hm =
            huff.createFrequencyTable(file);

            Frequency tabFrequencies[] = huff.getByteByFrequencyOrder(hm);
            HuffmanNode tree = null;
            try {
                tree = huff.creationDArbreHuffman(tabFrequencies);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < tabFrequencies.length; i++){
                System.out.println(tabFrequencies[i].getName());
            }

            HuffmanNode[] tab = tree.getAllNodeBitInOrder();

            BitOutputStream bout = new BitOutputStream("outputFile.txt");



            for(HuffmanNode n : tab){
                System.out.println(n.getName() + " : " + n.getValue() + " : " + n.getBitNode() );
            }



        } catch (IOException e) {
            e.printStackTrace();
        }*/



    }
}
