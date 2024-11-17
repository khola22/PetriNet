package org.petriNet;

import java.util.ArrayList;
import java.util.List;

public class Transition {

    private int id;
    String nom;
    private List<Arc_SORTANT> arcs_SORTANTS;
    private List<Arc_ENTRANT> arcs_ENTRANTS;

    public Transition(String nom, int id) {
        this.id = id;
        this.nom = nom;
        this.arcs_SORTANTS = new ArrayList<Arc_SORTANT>();
        this.arcs_ENTRANTS = new ArrayList<Arc_ENTRANT>();
    }

    /**
     * On ajoute deux méthodes pour pouvoir ajouter les arcs entrants et
     * sortants à la transition
     * c'est une différence par rapport au diagramme de classe soumis
     */

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Arc_SORTANT> getArcs_SORTANTS() {
        return arcs_SORTANTS;
    }

    public List<Arc_ENTRANT> getArcs_ENTRANTS() {
        return arcs_ENTRANTS;
    }

    public void setArcs_SORTANTS(List<Arc_SORTANT> arcs_SORTANTS) {
        this.arcs_SORTANTS = arcs_SORTANTS;
    }

    public void ajouterArc_SORTANT(Arc_SORTANT arc_SORTANT) {
        // verify that the arc doesn't already exist
        for (Arc_SORTANT arc_SORTANT1 : arcs_SORTANTS) {
            if (arc_SORTANT1.getPlace().getId() == arc_SORTANT.getPlace().getId() &&
                    arc_SORTANT1.getTransition().getId() == arc_SORTANT.getTransition().getId() &&
                    arc_SORTANT1.getClass() == arc_SORTANT.getClass()) {
                System.out.println("The arc already exists.");
                return;
            }
        }
        this.arcs_SORTANTS.add(arc_SORTANT);
    }

    public void ajouterArc_ENTRANT(Arc_ENTRANT arc_ENTRANT) {
        // verify that the arc doesn't already exist
        for (Arc_ENTRANT arc_ENTRANT1 : arcs_ENTRANTS) {
            if (arc_ENTRANT1.getPlace().getId() == arc_ENTRANT.getPlace().getId() &&
                    arc_ENTRANT1.getTransition().getId() == arc_ENTRANT.getTransition().getId() &&
                    arc_ENTRANT1.getClass() == arc_ENTRANT.getClass()) {
                System.out.println("The arc already exists.");
                return;
            }
        }
        this.arcs_ENTRANTS.add(arc_ENTRANT);
    }


    public boolean est_tirable() {

        for (Arc_ENTRANT arc_ENTRANT : this.arcs_ENTRANTS) {
            if (!arc_ENTRANT.verifier_tirable()) {
                System.out.println("La transition n'est pas tirable");
                return false;
            }
        }
        System.out.println("La transition est tirable");
        return true;

    }

    public void tirer(){

        boolean tirable = est_tirable();

        if (tirable) {
            for (Arc_ENTRANT arc_ENTRANT : this.getArcs_ENTRANTS()) {
                arc_ENTRANT.validate();
            }

            for (Arc_SORTANT arc_SORTANT : this.getArcs_SORTANTS()) {
                arc_SORTANT.validate();
            }
            System.out.println("Transition tirée avec succès");
        }

    }
}
