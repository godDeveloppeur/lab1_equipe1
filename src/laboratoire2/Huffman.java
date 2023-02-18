package laboratoire2;

import java.io.*;
import java.util.*;

public class Huffman{

    // Création de la table de fréquence
    public Map<String, Integer> createFrequencyTable(File file) throws IOException {
        Map<String, Integer> hm = new HashMap<>();
        try (InputStream bufferedReader = new BufferedInputStream(new FileInputStream(file))) {
            int singleCharInt;
            while((singleCharInt = bufferedReader.read()) != -1) {
                String byteRead = String.format("0x%X", singleCharInt);

                if(hm.containsKey(byteRead)){
                    hm.put(byteRead, hm.get(byteRead) + 1);
                }else{
                    hm.put(byteRead, 1);
                }
            }
        }
        return hm;
    }

    public String generateFrequenceTableToString(Map<String, Integer> hm){
        String result = "";
        Set<Map.Entry<String, Integer>> setHm = hm.entrySet();
        Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> e = it.next();
            result += Integer.parseInt(e.getKey().substring(2), 16) +  ":" + e.getValue() + ";";
        }

        return result;
    }

    public Map<String, Integer>  generateFrequenceTableWithString(String str){
        Map<String, Integer> hm = new HashMap<>();
        String[] elts = str.split(";");
        for(String e : elts){
            //System.out.println(e);
            String[] delts = e.split(":");
            hm.put(String.format("0x%X", Integer.parseInt(delts[0])), Integer.parseInt(delts[1]));
        }

        return hm;
    }


    // Trie un map et le met dans un tableau avec l'algo max
    public PriorityQueue<HuffmanNode> getByteByFrequencyOrder(Map<String, Integer> hm){
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        Set<Map.Entry<String, Integer>> setHm = hm.entrySet();
        Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> e = it.next();
            HuffmanNode node = new HuffmanNode(e.getKey(), e.getValue(), null,null);
            priorityQueue.offer(node);
        }

        return priorityQueue;
    }



    public HuffmanNode creationDArbreHuffman(PriorityQueue<HuffmanNode> huffNodeQueue) throws CloneNotSupportedException {
        System.out.println("Start generate huffman tree");
        while (huffNodeQueue.size() > 1) {

            HuffmanNode x = huffNodeQueue.poll();
            x.setBit(0);

            HuffmanNode y = huffNodeQueue.poll();
            y.setBit(1);

            HuffmanNode sum = new HuffmanNode("#", x.getFrequence() + y.getFrequence(), x, y);

            huffNodeQueue.offer(sum);
        }
        System.out.println("Finish generate huffman tree");
        return huffNodeQueue.poll();
    }

    public String generateCompresseFile(File file, Map<String, String> allNodeBit) throws IOException {
        StringBuilder result = new StringBuilder();
        System.out.println("Start generate string bit file");
        try (InputStream bufferedReader = new BufferedInputStream(new FileInputStream(file))) {
            int singleCharInt;
            while((singleCharInt = bufferedReader.read()) != -1) {
                //System.out.println(singleCharInt);
                String byteRead = String.format("0x%X", singleCharInt);
                //System.out.println(byteRead);
                result.append(allNodeBit.get(byteRead));
            }
        }
        System.out.println("Finish generate string bit file");
        return result.toString();
    }




    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    public void Compresser(String nomFichierEntre, String nomFichierSortie) throws IOException {
        File entryFile = new File(nomFichierEntre);
        Map<String, Integer> hm = createFrequencyTable(entryFile);
        String mapFrequenciesString = generateFrequenceTableToString(hm);
        PriorityQueue<HuffmanNode> huffmanNodepriorityQueue = getByteByFrequencyOrder(hm);
        HuffmanNode tree = null;
        try {
            tree = creationDArbreHuffman(huffmanNodepriorityQueue);
            Map<String, String> allNodeBit = tree.getAllNodeBitInOrder();
            BitOutputStream bout = new BitOutputStream(nomFichierSortie, mapFrequenciesString);
            String compresseFileString = generateCompresseFile(entryFile, allNodeBit);

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


    }

    public void Decompresser(String nomFichierEntre, String nomFichierSortie) throws IOException {
        BitInputStream bint = new BitInputStream(nomFichierEntre);
        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(nomFichierSortie));
        String mapFrequenciesString = bint.getMapFrequenciesString();
        Map<String, Integer> hm = generateFrequenceTableWithString(mapFrequenciesString);
        PriorityQueue<HuffmanNode> huffmanNodepriorityQueue = getByteByFrequencyOrder(hm);
        HuffmanNode tree = null;
        try {
            tree = creationDArbreHuffman(huffmanNodepriorityQueue);
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
        } catch (IOException | CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }

}