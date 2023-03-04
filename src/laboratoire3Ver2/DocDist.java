package laboratoire3Ver2;


import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DocDist{


    private Map<String, Integer> frequenceTableFileCombine;
    private final String REGEX = "[^\\wáàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]";


    public DocDist(){
        frequenceTableFileCombine = new HashMap<>();
    }

    // Création de la table de fréquence
    public Map<String, Integer> makeFrequenceFile(String file) throws IOException {
        Map<String, Integer> frequenceTableFile = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream(file);
        try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8")) {
            int singleCharInt;
            char singleChar;
            String singleString;
            String actualWord = "";
            int nbWordsFile = 0;
            while((singleCharInt = inputStreamReader.read()) != -1) {
                singleChar = (char) singleCharInt;
                singleString = singleChar + "";
                singleString = singleString.replaceAll(REGEX, " ");

                if(singleString.equals(" ")){
                    if(actualWord.length() > 0){
                        nbWordsFile++;
                        actualWord = actualWord.toLowerCase();
                        //System.out.println("ajout de :  " + actualWord);
                        if(frequenceTableFile.containsKey(actualWord)){
                            frequenceTableFile.put(actualWord, frequenceTableFile.get(actualWord) + 1);
                        }else{
                            frequenceTableFile.put(actualWord, 1);
                            if(!frequenceTableFileCombine.containsKey(actualWord)){
                                frequenceTableFileCombine.put(actualWord, 0);
                            }
                        }

                        actualWord = "";
                    }
                }else{
                    actualWord += singleChar;
                }
            }
            System.out.println("Fichier " + file + " : " + nbWordsFile + " mots, " + frequenceTableFile.size() + " mots dictincts");
        }

        return frequenceTableFile;
    }

    public double normVector(Map<String, Integer> frequenceTableFile){
        double normResult = 0.0;
        Set<Map.Entry<String, Integer>> setHm = frequenceTableFile.entrySet();
        Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> e = it.next();
            normResult += e.getValue() * e.getValue();
        }
        normResult = Math.sqrt(normResult);
        return normResult;
    }


    // Ne pas changer cette fonction, elle sera utilisée pour tester votre programme
    public double docDistance(String nomFichier1, String nomFichier2)
    {
        double resultatAngle = 0.0;
        try {
            Map<String, Integer> frequenceTableFile1 = makeFrequenceFile(nomFichier1);
            Map<String, Integer> frequenceTableFile2 = makeFrequenceFile(nomFichier2);

            int produitScalaire = 0;
            Set<Map.Entry<String, Integer>> setHm = frequenceTableFileCombine.entrySet();
            Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
            while(it.hasNext()){
                Map.Entry<String, Integer> e = it.next();
                int x = frequenceTableFile1.getOrDefault(e.getKey(), 0);
                int y = frequenceTableFile2.getOrDefault(e.getKey(), 0);
                produitScalaire += x*y;
            }

            double cosinusAngle = produitScalaire / (normVector(frequenceTableFile1) * normVector(frequenceTableFile2));
            resultatAngle = Math.acos(cosinusAngle);
            frequenceTableFileCombine = new HashMap<>();
            System.out.println("La distance cosinus entre les deux documents est de " + resultatAngle + " radians\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultatAngle;
    }
}