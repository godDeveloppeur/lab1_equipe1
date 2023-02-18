package lab2;

import java.io.*;

public class test {

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        String original = "./src/lab2/exemplePdf.pdf";
        String compresser = "./src/lab2/exempleCompresser.pdf";
        String decompresser = "./src/lab2/exempleDecompresser.pdf";

        Huffman huffmanTree = new Huffman();

        try {
            huffmanTree.Compresser(original, compresser);
            huffmanTree.Decompresser(compresser, decompresser);
            File fichierOriginal = new File(original);
            File fichierCompresser = new File(compresser);

            System.out.println("taille du fichier original: " + fichierOriginal.length() + " bytes");
            System.out.println("taille du fichier compresser: " + fichierCompresser.length() + " bytes");
            System.out.println("le ratio de compression: " + String.format("%.2f" ,(double)fichierOriginal.length() /
                    (double)fichierCompresser.length()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
