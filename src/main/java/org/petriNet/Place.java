package org.petriNet;

public class Place {

    private int id;
    private int nombre_jeton;

    public Place(int nombre_jeton, int id) {
        this.id = id;
        // VERIFY THAT THE NUMBER OF TOKENS IS NOT NEGATIVE
        if (nombre_jeton >= 0) {
            this.nombre_jeton = nombre_jeton;
        } else {
            System.out.println("The number of tokens cannot be negative.");
        }
    }

    public void ajouter_jeton(int jetons) {
        // verify that the number of tokens is not negative
        if (jetons >= 0) {
            this.nombre_jeton += jetons;
        } else {
            System.out.println("The number of tokens cannot be negative.");
        }
    }

    public void enlever_jeton(int jetons) {
        // verify that the number of tokens is not negative
        if (jetons >= 0) {
            this.nombre_jeton -= jetons;
        } else {
            System.out.println("The number of tokens cannot be negative.");
        }
    }

    public int get_nombre_jetons() {
        return this.nombre_jeton;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre_jeton(int nombre_jeton) {
        this.nombre_jeton = nombre_jeton;
    }

    // Une fonction pour trouver les arcs entrants d'une place

}
