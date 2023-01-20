import laboratoire1.RechercheTableau;

import java.util.Arrays;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        RechercheTableau rech = new RechercheTableau();
        int[] tab = new int[]{10, 24, 36, 44, 54, 68, 75, 83, 94, 105, 114, 123, 135, 147, 158, 169, 178};
        //System.out.println(rech.RechercheLineaire(tab, tab.length, 75));

        Random rand = new Random();

        int[] tab2 = new int[2000];
        for(int i = 0; i < tab2.length; i++){
            int n = rand.nextInt(1000);
            tab2[i] = n * i;
        }
        Arrays.sort(tab2);


        System.out.println("When we sort with a array that can have duplicates elements");
        for(int i = 0; i < tab2.length; i++){

            int rep1 = rech.RechercheLineaire(tab2, tab2.length, tab2[i]);
            int rep2 = rech.RechercheBinaireModifie(tab2, tab2.length, tab2[i]);
            if(rep1 != rep2){
                System.out.println(tab2[i]);
                System.out.println(rep1);
                System.out.println(rep2 + "\n");
            }
        }

        System.out.println("When we sort with binaire method with a array that can have duplicates elements");
        for(int i = 0; i < tab2.length; i++){

            int rep1 = rech.RechercheLineaire(tab2, tab2.length, tab2[i]);
            int rep2 = rech.RechercheBinaire(tab2, tab2.length, tab2[i]);
            if(rep1 != rep2){
                System.out.println(tab2[i]);
                System.out.println(rep1);
                System.out.println(rep2 + "\n");
            }
        }

        int[] tab3 = new int[2000];
        for(int i = 0; i < tab3.length; i++){
            boolean check = false;
            int val = 0;
            int j = 0;
            while(!check){
                j++;
                int n = rand.nextInt(1000);
                val = n * j;
                if(rech.RechercheLineaire(tab3, tab3.length, val) == -1){
                    check = true;
                }
            }

            tab3[i] = val;
        }
        Arrays.sort(tab3);

        System.out.println("When we sort with a array that can't have duplicates elements");
        for(int i = 0; i < tab3.length; i++){

            int rep1 = rech.RechercheLineaire(tab3, tab3.length, tab3[i]);
            int rep2 = rech.RechercheBinaireModifie(tab3, tab3.length, tab3[i]);
            if(rep1 != rep2){
                System.out.println(tab3[i]);
                System.out.println(rep1);
                System.out.println(rep2 + "\n");
            }
        }
        //System.out.println(rech.RechercheBinaire(tab, tab.length, 178));
        //System.out.println(rech.RechercheBinaireModifie(tab, tab.length, 36));


        System.out.println("When we sort with binaire Method with a array that can't have duplicates elements");
        for(int i = 0; i < tab3.length; i++){

            int rep1 = rech.RechercheLineaire(tab3, tab3.length, tab3[i]);
            int rep2 = rech.RechercheBinaire(tab3, tab3.length, tab3[i]);
            if(rep1 != rep2){
                System.out.println(tab3[i]);
                System.out.println(rep1);
                System.out.println(rep2 + "\n");
            }
        }
    }
}
