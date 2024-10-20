package org.petriNet;

public class Transition {
    private int id;
    private List<Arc_SORTANT> arcs_SORTANTS;

    public Transition(int id) {
        this.id = id;
        this.arcs_SORTANTS = new ArrayList<Arc_SORTANT>();
    }

    public boolean est_tirable() {
        // verifier si la transition est tirable à travers l'ARC_SORTANT
        int n_jetons_necessaires;
        int n_jetons_existant;

        // pour chaque arc sortant de la transition
        // n_jetons_necessaires += arc_SORTANT.getPoids();
        for (Arc_SORTANT arc_SORTANT : arcs_SORTANTS) {
            n_jetons_necessaires += arc_SORTANT.getPoids();
        }

        // pour chaque arc entrant de la transition
        // n_jetons_existant += arc_ENTRANT.getPoids();
        for (Arc_ENTRANT arc_ENTRANT : arcs_ENTRANTS) {
            n_jetons_existant += arc_ENTRANT.getPoids();
        }

        // si n_jetons_necessaires <= n_jetons_existant
        // return true;

        if (n_jetons_necessaires <= n_jetons_existant) {
            return true;
        }

    }
    public void tirer(){
        // fait appel à valider() dans les classes Arc_SORTANT et
        // Arc_ENTRANT pour modifier les jetons

        for (Arc_ENTRANT arc_ENTRANT : arcs_ENTRANTS) {
            arc_ENTRANT.valider();
        }

        for (Arc_SORTANT arc_SORTANT : arcs_SORTANTS) {
            arc_SORTANT.valider();
        }
    }
}
