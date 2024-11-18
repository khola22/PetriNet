package org.petriNet;

public abstract class Arc {

    private final int id;
    private Place place;
    private final Transition transition;
    private int weight;

    public Arc(Transition transition, Place place, int weight, int id) {
        this.place = place;
        this.transition = transition;
        // verify that the weight is not negative
        if (weight >= 0) {
            this.weight = weight;
        } else {
            System.out.println("The weight cannot be negative.");
        }
        this.id = id; // Assign a unique ID with the generateId method in the PetriNet class
    }

    public abstract void validate();

    public int getWeight() {
        return this.weight;
    }

    public int getId() {
        return this.id;
    }

    public Place getPlace() {
        return this.place;
    }

    public Transition getTransition() {
        return this.transition;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}


