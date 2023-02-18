package lab2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Huffman {

    private Map<Character, String> charPrefixHashMap = new HashMap<>();
    private Map<Character, Integer> freq = new HashMap<>();
    private int freqTotal = 0;

    private Node root;

    private void Encode(String inputFile) {

        try {

            FileInputStream fileInputStream = new FileInputStream(inputFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            // Créer la table de fréquence
            int input;
            char c;

            while((input = bufferedInputStream.read()) != -1){
                c = (char)input;

                if (!freq.containsKey(c)) {
                    freq.put(c, 0);
                }
                freq.put(c, freq.get(c) + 1);
                freqTotal ++;
            }

            bufferedInputStream.close();
            fileInputStream.close();

            System.out.println("Sum of frequency map = " + freqTotal);

            System.out.println("Character Frequency Map = " + freq);
            root = creerArbre(freq);

            SetPrefixCodes(root, new StringBuilder());
            System.out.println("Character Prefix Map = " + charPrefixHashMap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Node creerArbre(Map<Character, Integer> freq) {

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Set<Character> keySet = freq.keySet();
        for (Character c : keySet) {

            Node node = new Node();
            node.data = c;
            node.frequence = freq.get(c);
            node.left = null;
            node.right = null;
            priorityQueue.offer(node);
        }
        assert priorityQueue.size() > 0;

        while (priorityQueue.size() > 1) {

            Node x = priorityQueue.peek();
            priorityQueue.poll();

            Node y = priorityQueue.peek();
            priorityQueue.poll();

            Node sum = new Node();

            sum.frequence = x.frequence + y.frequence;
            sum.data = '-';

            sum.left = x;

            sum.right = y;
            root = sum;

            priorityQueue.offer(sum);
        }

        return priorityQueue.poll();
    }

    private void SetPrefixCodes(Node node, StringBuilder prefix) {

        if (node != null) {
            if (node.left == null && node.right == null) {
                charPrefixHashMap.put(node.data, prefix.toString());

            } else {
                prefix.append('0');
                SetPrefixCodes(node.left, prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                SetPrefixCodes(node.right, prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

    }

    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    public void Compresser(String nomFichierEntre, String nomFichierSortie){

        Encode(nomFichierEntre);
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        BitOutputStream bitOutputStream;


        try{
             fileInputStream = new FileInputStream(nomFichierEntre);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bitOutputStream = new BitOutputStream(nomFichierSortie);

            int input;
            char c;
            String s;

            while((input = bufferedInputStream.read()) != -1){


                c = (char)input;
                s = charPrefixHashMap.get(c);

                for (char ch : s.toCharArray()) {
                    if(ch == '0'){
                        input = 0;
                    }
                    else{
                        input = 1;
                    }
                    bitOutputStream.writeBit(input);
                }

            }

            bufferedInputStream.close();
            fileInputStream.close();
            bitOutputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Decompresser(String nomFichierEntre, String nomFichierSortie){

        BitInputStream bitInputStream;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;

        try{

            bitInputStream = new BitInputStream(nomFichierEntre);
            fileOutputStream = new FileOutputStream(nomFichierSortie);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            Node temp = root;
            int b;
            int characterCount = 0; //  Add characterCount to avoid add more octet in the file to complete 8 bits.

            while((characterCount < freqTotal) && (b = bitInputStream.readBit()) != -1){
                int j = Integer.parseInt(String.valueOf(b));

                if (j == 0) {
                    temp = temp.left;
                    if (temp.left == null && temp.right == null) {
                        bufferedOutputStream.write(temp.data);
                        temp = root;
                        characterCount++;
                    }
                }
                if (j == 1) {
                    temp = temp.right;
                    if (temp.left == null && temp.right == null) {
                        bufferedOutputStream.write(temp.data);
                        temp = root;
                        characterCount++;
                    }
                }

            }

            bitInputStream.close();
            bufferedOutputStream.close();
            fileOutputStream.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
