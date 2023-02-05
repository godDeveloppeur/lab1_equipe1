package laboratoire2;
import java.io.*;
import java.util.*;

public class Huffman{

    //Lire le fichier
    public void readFileBinaryMode() throws IOException {
        String fileName = "src/laboratoire2/exemple.txt";
        int numOfReadChar_binMode = 0;
        File file = new File(fileName);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int singleCharInt;
            char singleChar;
            while((singleCharInt = fileInputStream.read()) != -1) {
                singleChar = (char) singleCharInt;
                System.out.println(String.format("0x%X %c", singleCharInt, singleChar));
                numOfReadChar_binMode++;
            }
        }
        System.out.println("Nombre d'octets lus en mode binaire : "+numOfReadChar_binMode);
    }

    //Lire le fichier
    public void readFileBinaryModeWithBuffered() throws IOException {
        String fileName = "src/laboratoire2/exemple.txt";
        int numOfReadChar_binMode = 0;
        File file = new File(fileName);
        try (InputStream bufferedReader = new BufferedInputStream(new FileInputStream(file))) {
            int singleCharInt;
            char singleChar;
            while((singleCharInt = bufferedReader.read()) != -1) {
                singleChar = (char) singleCharInt;
                System.out.println(String.format("0x%X %c", singleCharInt, singleChar));
                numOfReadChar_binMode++;
            }
        }
        System.out.println("Nombre d'octets lus en mode binaire : "+numOfReadChar_binMode);
    }

    // Création de la table de fréquence
    public Map<String, Integer> createFrequencyTable(File file) throws IOException {
        Map<String, Integer> hm = new HashMap<>();
        int numOfVariousByte = 0;
        try (InputStream bufferedReader = new BufferedInputStream(new FileInputStream(file))) {
            int singleCharInt;
            while((singleCharInt = bufferedReader.read()) != -1) {
                String byteRead = String.format("0x%X", singleCharInt);

                if(hm.containsKey(byteRead)){
                    hm.put(byteRead, hm.get(byteRead) + 1);
                }else{
                    numOfVariousByte++;
                    hm.put(byteRead, 1);
                }
            }
        }

        hm.put("numOfVariousByte", numOfVariousByte);
        return hm;
    }

    // Trie un map et le met dans un tableau avec l'algo max
    public Frequency[] getByteByFrequencyOrder(Map<String, Integer> hm) throws IOException {
        Frequency[] tab = new Frequency[hm.get("numOfVariousByte")];
        hm.put("numOfVariousByte", 0);
        for(int i = 0; i < tab.length; i++){
            int max = 0;
            String maxElt = null;
            Set<Map.Entry<String, Integer>> setHm = hm.entrySet();
            Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
            while(it.hasNext()){
                Map.Entry<String, Integer> e = it.next();
                if(max < e.getValue()){
                    max = e.getValue();
                    maxElt = e.getKey();
                }
            }
            hm.put(maxElt, 0);
            tab[i] = new Frequency(maxElt, max);
        }
        hm.put("numOfVariousByte", tab.length);
        return tab;
    }

    public HuffmanNode creationDArbreHuffman(Frequency[] tabF) throws CloneNotSupportedException {
        HuffmanNode tree = new HuffmanNode(tabF[tabF.length - 1].getName(), tabF[tabF.length - 1].getValue(), null, null);
        for(int i = tabF.length - 2; i >= 0; i--){
            HuffmanNode newNode = new HuffmanNode(tabF[i].getName(), tabF[i].getValue(), null, null);
            tree.addNode(newNode);
        }

        return tree;
    }


    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    public void Compresser(String nomFichierEntre, String nomFichierSortie){

    }

    public void Decompresser(String nomFichierEntre, String nomFichierSortie){

    }

}