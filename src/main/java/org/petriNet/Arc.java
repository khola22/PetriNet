package org.petriNet;

public abstract class Arc {
    private int id;
    private Place place;
    private Transition transition;
    private int poids;

    public Arc(Place place, Transition transition, int poids, int id) {
        this.place = place;
        this.transition = transition;
        this.poids = poids;
        this.id = id;
    }


    public abstract void modifierPoids(int poids);

    public abstract void valider();

    public abstract int getPoids() ;

    public abstract int getId();

    public abstract void setId(int id);

    public abstract void setPoid(int poids);

    public abstract Place getPlace();

    public abstract void setPlace(Place place);

    public abstract Transition getTransition();
}


