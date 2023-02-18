package lab2Essaie;

import laboratoire2.BitInputStream;
import laboratoire2.HuffmanNode;

import java.io.*;
import java.util.*;
/*
public class Huffman3{


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
            //System.out.println(maxElt + " : " + max);
            tab[i] = new Frequency(maxElt, max);
        }
        hm.put("numOfVariousByte", tab.length);
        return tab;
    }

    public HuffmanNode creationDArbreHuffman(Frequency[] tabF) throws CloneNotSupportedException {
        System.out.println("Start generate huffman tree");
        HuffmanNode tree = new HuffmanNode(tabF[tabF.length - 1].getName(), tabF[tabF.length - 1].getValue(), null, null);
        for(int i = tabF.length - 2; i >= 0; i--){
            HuffmanNode newNode = new HuffmanNode(tabF[i].getName(), tabF[i].getValue(), null, null);
            tree.addNode(newNode);
        }
        System.out.println("Finish generate huffman tree");
        return tree;
    }

    public String generateCompresseFile(File file, HuffmanNode[] huffNodes) throws IOException {
        StringBuilder result = new StringBuilder();
        System.out.println("Start generate string bit file");
        try (InputStream bufferedReader = new BufferedInputStream(new FileInputStream(file))) {
            int singleCharInt;
            while((singleCharInt = bufferedReader.read()) != -1) {
                //System.out.println(singleCharInt);
                String byteRead = String.format("0x%X", singleCharInt);
                //System.out.println(byteRead);
                for(HuffmanNode h : huffNodes){
                    if(h.getName().equals(byteRead)){
                        result.append(h.getBitNode());
                        break;
                    }
                }
            }
        }
        System.out.println("Finish generate string bit file");
        return result.toString();
    }




    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    /*public void Compresser(String nomFichierEntre, String nomFichierSortie) throws IOException {
        File entryFile = new File(nomFichierEntre);
        Map<String, Integer> hm = createFrequencyTable(entryFile);
        Frequency[] tabFrequencies = getByteByFrequencyOrder(hm);
        HuffmanNode tree = null;
        try {
            tree = creationDArbreHuffman(tabFrequencies);
            HuffmanNode[] huffNodes = tree.getAllNodeBitInOrder();
            for(HuffmanNode n : huffNodes){
                System.out.println(n.getName() + " : " + n.getValue() + " : " + n.getBitNode() );
            }
            BitOutputStream bout = new BitOutputStream(nomFichierSortie, tabFrequencies);
            String compresseFileString = generateCompresseFile(entryFile, huffNodes);

            System.out.println("Start write the bit");
            for(int i = 0; i < compresseFileString.length(); i++){
                bout.writeBit(Integer.parseInt(compresseFileString.substring(i, i+1)));
            }
            System.out.println("Finish write the bit");
            System.out.println("Finish");
            bout.finalize();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }*/

   /* public void Decompresser(String nomFichierEntre, String nomFichierSortie) throws FileNotFoundException {
        BitInputStream bint = new BitInputStream(nomFichierEntre);
        Frequency[] tabFrequencies = bint.getFrequencies();
        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(nomFichierSortie));
        HuffmanNode tree = null;
        try {
            tree = creationDArbreHuffman(tabFrequencies);
            int rbit;
            while((rbit = bint.readBit()) != -1){
                HuffmanNode n = tree.readTheBit(rbit);
                if(!n.getName().equals("#")){
                    //write in the decompress file
                    bout.write(Integer.parseInt(n.getName().substring(2), 16));
                    tree.resetActualReadNode();
                }
            }
            bout.flush();
            bout.close();
        } catch (CloneNotSupportedException | IOException e) {
            e.printStackTrace();
        }
        /*for(Frequency f : tabFrequencies){
            System.out.println(f.getName() + " : " + f.getValue());
        }*/

/*
    }

}*/