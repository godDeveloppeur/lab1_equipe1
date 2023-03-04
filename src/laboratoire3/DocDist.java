package laboratoire3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DocDist {

        // Ne pas changer cette fonction, elle sera utilisée pour tester votre programme

    final String regex = "(?![A-Za-zÀ-ÿ0-9 ]).";

    public double docDistance(String nomFichier1, String nomFichier2) {
        double angle;
        final Map<String, Integer> tabFreq1 = TableFrequence(nomFichier1);
        final Map<String, Integer> tabFreq2 = TableFrequence(nomFichier2);
        double norme1 = NormeVecteur(tabFreq1);
        double norme2 = NormeVecteur(tabFreq2);
        int produitScalaire = ProduitScalaire(tabFreq1, tabFreq2);
        double number = produitScalaire / (norme1 * norme2);
        angle = Math.acos(number);
        return angle;
    }

    public int ProduitScalaire(Map<String, Integer> freq1, Map<String, Integer> freq2){
        int produitScalaire = 0;
        for (Map.Entry<String, Integer> entry : freq1.entrySet()) {
            if (freq2.containsKey(entry.getKey())) {
                produitScalaire += entry.getValue() * freq2.get(entry.getKey());
            }
        }
        return produitScalaire;
    }

    public double NormeVecteur(Map<String, Integer> freq){
        double norme;
        double total = 0;
        for (int freqence : freq.values()){
            total += Math.pow(freqence, 2);
        }
        norme = Math.sqrt(total);
        return norme;
    }

    public Map<String, Integer> TableFrequence(String nomFichier) {
        Map<String, Integer> freq = new HashMap<>();
        try (FileReader reader = new FileReader(nomFichier, Charset.forName("UTF-8"));
             BufferedReader br = new BufferedReader(reader)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] text = line.toLowerCase().replaceAll(regex, " ").split(" ");
                for (final String blocText : text){
                    String[] mots = blocText.split("'");
                    for (String mot: mots) {
                        if (mot.length() == 0) {
                            continue;
                        }
                        if (!freq.containsKey(mot)) {
                            freq.put(mot, 1);
                        }else {
                            freq.put(mot, freq.get(mot) + 1);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return freq;
    }
}
