package org.petriNet;

public class Main {

    public static void main(String[] args) {
        // Initialize the Petri network
        PetriNet petriNetwork = new PetriNet();

        // Create places
        Place place1 = new Place(3, petriNetwork.generateId(1));
        Place place2 = new Place(0, petriNetwork.generateId(1));

        // Add places to the network
        petriNetwork.addPlace(place1);
        petriNetwork.addPlace(place2);

        // Create a transition
        Transition transition1 = new Transition("T1", petriNetwork.generateId(2));

        // Add the transition to the network
        petriNetwork.addTransition(transition1);

        // Create arcs and add them to the network
        // Incoming arc from place1 to transition1 with weight 1
        IncomingArc incomingArc = new IncomingArc_Simple(transition1, place1, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(incomingArc);
        transition1.addIncomingArc(incomingArc);

        // Outgoing arc from transition1 to place2 with weight 1
        OutgoingArc outgoingArc = new OutgoingArc(transition1, place2, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(outgoingArc);
        transition1.addOutgoingArc(outgoingArc);

        // Display the initial state of the Petri network
        System.out.println("Initial State of Petri Network:");
        petriNetwork.displayNetwork();

        // Activate the transition
        System.out.println("\nActivating transition T1...");
        petriNetwork.fireTransition(String.valueOf(transition1.getId()));

        // Display the state of the Petri network after activation
        System.out.println("\nState of Petri Network after Transition T1 Activation:");
        petriNetwork.displayNetwork();

        // Example assertions to verify changes
        System.out.println("\nVerifying state:");
        System.out.println("Tokens in Place 1 (expected 2): " + place1.getTokenCount());
        System.out.println("Tokens in Place 2 (expected 1): " + place2.getTokenCount());
    }
}
