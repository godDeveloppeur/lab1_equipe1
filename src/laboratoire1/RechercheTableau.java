package laboratoire1;

import java.util.Arrays;

public class RechercheTableau{

    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    // Elles peuvent cependant servir seulement d'interface et utiliser une méthode "helper"
    // avec des paramètres supplémentaires, au besoin.
    public int RechercheLineaire(int[] tab, int n, int x){
        for(int i = 0; i < n; i++){
            if(tab[i] == x){
                return i;
            }
        }
        return -1;
    }

    public int RechercheBinaire(int[] tab, int n, int val){
        if(n == tab.length){
            n = 0;
        }

        if(tab.length == 1){
            if(tab[0] == val){
                return 0;
            }else return -1;
        }

        int mid = (tab.length + n)/ 2;
        if(tab[mid] == val){
            return mid;
        }

        if(tab[mid] > val){
            int[] tabLeft = Arrays.copyOfRange(tab, 0, mid);
            return RechercheBinaire(tabLeft, n, val);
        }
        return RechercheBinaire(tab, mid, val);
    }

    public int RechercheBinaireModifie(int[] tab, int n, int val){

        return -1;
    }

}