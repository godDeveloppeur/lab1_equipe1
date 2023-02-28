package laboratoire3;

public class App3 {

    public static void main(String[] args) {
        DocDist dist = new DocDist();
        double angle = dist.docDistance("src/laboratoire3/l_avare-Moliere.txt", "src/laboratoire3/fables_Lafontaine.txt");
        double angle2 = dist.docDistance("src/laboratoire3/adventures_of_Sherlock_Holmes-Doyle.txt",
                "src/laboratoire3/return_of_Sherlock_Holmes-Doyle.txt");
        double angle3 = dist.docDistance("src/laboratoire3/monte_cristo_1-Dumas.txt",
                "src/laboratoire3/monte_cristo_2-Dumas.txt");
    }
}
