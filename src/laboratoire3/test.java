package laboratoire3;

public class test {
    public static void main(String[] args){

        String fichier1 = "src/laboratoire3/fables_Lafontaine.txt";
        String fichier2 = "src/laboratoire3/l_avare-Moliere.txt" ;
        DocDist doc = new DocDist();
        double angle = doc.docDistance(fichier1, fichier2);
        System.out.println("La distance entre les 2 documents est " + angle + "radians.");

        String fichier3 = "src/laboratoire3/adventures_of_Sherlock_Holmes-Doyle.txt";
        String fichier4 = "src/laboratoire3/return_of_Sherlock_Holmes-Doyle.txt" ;
        DocDist doc2 = new DocDist();
        double angle2 = doc2.docDistance(fichier3, fichier4);
        System.out.println("La distance entre les 2 documents est " + angle2 + "radians.");

        String fichier5 = "src/laboratoire3/monte_cristo_1-Dumas.txt";
        String fichier6 = "src/laboratoire3/monte_cristo_2-Dumas.txt" ;
        DocDist doc3 = new DocDist();
        double angle3 = doc3.docDistance(fichier5, fichier6);
        System.out.println("La distance entre les 2 documents est " + angle3 + "radians.");
    }
}
