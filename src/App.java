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
        Frequency tabFrequencies[] = new Frequency[]{
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
        /*String fileName = "src/laboratoire2/exemple.txt";
        File file = new File(fileName);
        try {
            //huff.readFileBinaryModeWithBuffered();
            Map<String, Integer> hm =
            huff.createFrequencyTable(file);

            //test
            Set<Map.Entry<String, Integer>> setHm = hm.entrySet();
            Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
            while(it.hasNext()){
                Map.Entry<String, Integer> e = it.next();
                System.out.println(e.getKey() + " : " + e.getValue());
            }

            Frequency tab[] = huff.getByteByFrequencyOrder(hm);
            for(int i = 0; i < tab.length; i++){
                System.out.println(tab[i].getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/



    }
}
