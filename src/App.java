import laboratoire1.RechercheTableau;

public class App {

    public static void main(String[] args) {
        RechercheTableau rech = new RechercheTableau();
        int[] tab = new int[]{1, 2};
        System.out.println(rech.RechercheBinaire(tab, 2, 1));
    }
}
