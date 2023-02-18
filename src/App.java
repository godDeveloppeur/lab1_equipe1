import laboratoire2.Huffman;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        Huffman huff = new Huffman();
        /*Frequency tabFrequencies[] = new Frequency[]{
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
        }*/

        String fileNameEntry = "src/laboratoire2/exemple.txt";
        String fileNameOut = "src/laboratoire2/exempleCompresse.gzip";

        String fileNameDecompress = "src/laboratoire2/exempleDeCompress.txt";


/*        fileNameEntry = "src/laboratoire2/testImage.jpg";
        fileNameOut = "src/laboratoire2/imageCompresse.bit";

        fileNameDecompress = "src/laboratoire2/imageDeCompress.jpg";*/

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
