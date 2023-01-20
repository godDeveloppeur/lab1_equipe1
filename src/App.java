import laboratoire1.RechercheTableau;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        RechercheTableau rech = new RechercheTableau();
        int[] tab = new int[]{10, 24, 12, 36, 17, 48, 23, 8};
        System.out.println(rech.RechercheLineaire(tab, tab.length, 17));
        System.out.println(rech.RechercheBinaire(tab, tab.length, 17));
    }
}
