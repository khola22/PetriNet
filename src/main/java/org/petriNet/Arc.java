package org.petriNet;

public abstract class Arc {

    private int id;
    private Place place;
    private Transition transition;
    private int poids;

    public Arc(Transition transition, Place place,  int poids, int id) {
        this.place = place;
        this.transition = transition;
        // verify that the weight is not negative
        if (poids >= 0) {
            this.poids = poids;
        } else {
            System.out.println("The weight cannot be negative.");
        }
        this.id = id; // Assign a unique ID with the generateId method in the ReseauPetri class
    }

    public abstract void valider();

    public int getPoids(){
        return this.poids;
    } ;

    public int getId(){
        return this.id;
    };

    public void setPoids(int poids){
        this.poids = poids;
    };

    public Place getPlace(){
        return this.place;
    };

    public void setPlace(Place place){
        this.place = place;
    };

    public Transition getTransition(){
        return this.transition;
    };
}


