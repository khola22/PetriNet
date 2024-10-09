package org.petriNet;

public class Place {
    private int id;
    private int nombre_jeton;
    public Place(int id, int nombre_jeton) {
        this.id = id;
        this.nombre_jeton = nombre_jeton;
    }

    public void ajouter_jeton() {
        this.nombre_jeton++;
    }

    public void enlever_jeton() {
        if (this.nombre_jeton > 0) {
            this.nombre_jeton--;
        } else {
            System.out.println("No more tokens to remove.");
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
}
