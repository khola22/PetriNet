package org.petriNet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transition {
    private int id;
    private List<Arc_SORTANT> arcs_SORTANTS;
    private List<Arc_ENTRANT> arcs_ENTRANTS;
    private List<Arc_ENTRANT> arcs_ENTRANTS_TIRABLE = new ArrayList<Arc_ENTRANT>();

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Arc_SORTANT> getArcs_SORTANTS() {
        return arcs_SORTANTS;
    }

    public void setArcs_SORTANTS(List<Arc_SORTANT> arcs_SORTANTS) {
        this.arcs_SORTANTS = arcs_SORTANTS;
    }

    public void ajouterArc_SORTANT(Arc_SORTANT arc_SORTANT) {
        this.arcs_SORTANTS.add(arc_SORTANT);
    }

    public void ajouterArc_ENTRANT(Arc_ENTRANT arc_ENTRANT) {
        this.arcs_ENTRANTS.add(arc_ENTRANT);
    }


    public void est_tirable() {

        // creer liste vide d'arcs
        this.arcs_ENTRANTS_TIRABLE = new ArrayList<Arc_ENTRANT>();

        for (Arc_ENTRANT arc_ENTRANT : arcs_ENTRANTS) {
            if (arc_ENTRANT.verifier_tirable()) {
                arcs_ENTRANTS_TIRABLE.add(arc_ENTRANT);
            }
        }

        if(arcs_ENTRANTS_TIRABLE.isEmpty()){
            System.out.println("La transition n'est pas tirable");
        }
        else {
            System.out.println("La transition est tirable");
        }
    }

    public void tirer(){

        est_tirable();

        // print the list of arcs entrants tirables
        System.out.println("Arcs entrants tirable: " + this.arcs_ENTRANTS_TIRABLE
                    + "Saisir l'arc entrant à tirer: ");

        // copilot get input from user of the id of the arc entrant to tirer (java.util.Scanner)
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        // find the Arc_ENTRANT by id
        Arc_ENTRANT arc_ENTRANT_toTirer = null;
        for (Arc_ENTRANT arc_ENTRANT : this.arcs_ENTRANTS_TIRABLE) {
            if (arc_ENTRANT.getId() == id) {
                arc_ENTRANT_toTirer = arc_ENTRANT;
            }
        }

        // valider l'arc entrant
        arc_ENTRANT_toTirer.valider();

        for (Arc_SORTANT arc_SORTANT : arcs_SORTANTS) {
            arc_SORTANT.valider();
        }

    }
}
