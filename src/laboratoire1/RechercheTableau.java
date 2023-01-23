package laboratoire1;


public class RechercheTableau {

    // Ne pas changer ces fonctions, elles seront utilisées pour tester votre programme
    // Elles peuvent cependant servir seulement d'interface et utiliser une méthode "helper"
    // avec des paramètres supplémentaires, au besoin.
    public int RechercheLineaire(int[] tab, int n, int x) {

        for (int i = 0; i < n; i++) {
            if (tab[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public int RechercheBinaire(int[] tab, int n, int val) {
        return RechercheBinaireHelper(tab, 0, n - 1, val);
    }

    private int RechercheBinaireHelper(int[] tab, int indexDepart, int indexFin, int val) {

        if (indexDepart == indexFin) {
            if (tab[indexDepart] == val) {
                return indexDepart;
            }
            return -1;
        }

        int indexMilieu = (indexDepart + indexFin) / 2;

        if (tab[indexMilieu] > val) {
            return RechercheBinaireHelper(tab, indexDepart, indexMilieu - 1, val);
        } else if (tab[indexMilieu] < val) {
            return RechercheBinaireHelper(tab, indexMilieu + 1, indexFin, val);
        }

        return indexMilieu;

    }

    public int RechercheBinaireModifie(int[] tab, int n, int val) {
        return RechercheBinaireModifieHelper(tab, 0, n - 1, val);
    }

    private int RechercheBinaireModifieHelper(int[] tab, int indexDepart, int indexFin, int val) {
        if (indexFin == indexDepart) {
            if (tab[indexDepart] == val) {
                return indexDepart;
            }
            return -1;

        } else {
            int sectionTri = (indexFin - indexDepart) / 3;
            int indexTier1 = indexDepart + sectionTri;
            int indexTier2 = indexDepart + 2 * sectionTri;

            if (tab[indexTier1] == val) {
                return indexTier1;
            } else if (tab[indexTier1] > val) {
                return RechercheBinaireModifieHelper(tab, indexDepart, indexTier1 - 1, val);
            }

            if (tab[indexTier2] == val) {
                return indexTier2;
            } else if (tab[indexTier2] > val) {
                return RechercheBinaireModifieHelper(tab, indexTier1 + 1, indexTier2 - 1, val);
            }
            return RechercheBinaireModifieHelper(tab, indexTier2 + 1, indexFin, val);
        }
    }
}