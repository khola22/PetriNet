package org.petriNet;

public class Transition {
    private int id;
    private List<Arc_SORTANT> arcs_SORTANTS;
    private List<Arc_ENTRANT> arcs_ENTRANTS;

    public Transition(int id, List<Arc_SORTANT> arcs_SORTANTS, List<Arc_ENTRANT> arcs_ENTRANTS) {
        this.id = id;
        this.arcs_SORTANTS = new ArrayList<Arc_SORTANT>();
        this.arcs_ENTRANTS = new ArrayList<Arc_ENTRANT>();
    }

    /**
     * On ajoute deux méthodes pour pouvoir ajouter les arcs entrants et
     * sortants à la transition
     * c'est une différence par rapport au diagramme de classe soumis
     */

    public void ajouterArc_SORTANT(Arc_SORTANT arc_SORTANT) {
        this.arcs_SORTANTS.add(arc_SORTANT);
    }

    public void ajouterArc_ENTRANT(Arc_ENTRANT arc_ENTRANT) {
        this.arcs_ENTRANTS.add(arc_ENTRANT);
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
