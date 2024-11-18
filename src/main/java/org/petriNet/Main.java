package org.petriNet;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose which simulation to run:");
        System.out.println("1. Standard Petri Network");
        System.out.println("2. Special Videur Arc Network");
        System.out.println("3. Special Zero Arc Petri Network");
        System.out.print("Enter your choice (1/2/3): ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                runStandardPetriNetwork();
                break;
            case 2:
                runSpecialVideur();
                break;
            case 3:
                runSpecialZero();
                break;
            default:
                System.out.println("Invalid choice! Please run the program again.");
        }

        scanner.close();
    }

    private static void runStandardPetriNetwork() {
        System.out.println("Running Standard Petri Network Simulation...");
        // Initialize the Petri network
        PetriNet petriNetwork = new PetriNet();
        // Create places

        Place place1 = new Place(3, petriNetwork.generateId(1));
        Place place2 = new Place(0, petriNetwork.generateId(1));
        // Add places to the network

        petriNetwork.addPlace(place1);
        petriNetwork.addPlace(place2);
        // Create a transition and add it

        Transition transition1 = new Transition("T1", petriNetwork.generateId(2));
        petriNetwork.addTransition(transition1);
        // Create arcs and add them to the network
        IncomingArc incomingArc = new IncomingArc_Simple(transition1, place1, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(incomingArc);
        transition1.addIncomingArc(incomingArc);

        OutgoingArc outgoingArc = new OutgoingArc(transition1, place2, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(outgoingArc);
        transition1.addOutgoingArc(outgoingArc);

        petriNetwork.displayNetwork();

        System.out.println("\nActivating transition T1...");
        petriNetwork.fireTransition(String.valueOf(transition1.getId()));

        System.out.println("\nState of Petri Network after Transition T1 Activation:");
        petriNetwork.displayNetwork();

        System.out.println("\nVerifying state:");
        System.out.println("Tokens in Place 1 (expected 2): " + place1.getTokenCount());
        System.out.println("Tokens in Place 2 (expected 1): " + place2.getTokenCount());
    }

    private static void runSpecialVideur() {
        System.out.println("Running Special Videur Transition Simulation...");

        PetriNet petriNetwork = new PetriNet();

        Place place1 = new Place(2, petriNetwork.generateId(1));
        Place place2 = new Place(4, petriNetwork.generateId(1));

        petriNetwork.addPlace(place1);
        petriNetwork.addPlace(place2);

        Transition transition1 = new Transition("T1", petriNetwork.generateId(2));
        petriNetwork.addTransition(transition1);

        IncomingArc_Videur incomingArc_videur = new IncomingArc_Videur(transition1, place2, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(incomingArc_videur);
        transition1.addIncomingArc(incomingArc_videur);

        OutgoingArc outgoingArc = new OutgoingArc(transition1, place1, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(outgoingArc);
        transition1.addOutgoingArc(outgoingArc);

        petriNetwork.displayNetwork();

        System.out.println("\nActivating transition T1...");
        petriNetwork.fireTransition(String.valueOf(transition1.getId()));

        System.out.println("\nState of Petri Network after Transition T1 Activation:");
        petriNetwork.displayNetwork();

        System.out.println("\nVerifying state:");
        System.out.println("Tokens in Place 1 (expected 3): " + place1.getTokenCount());
        System.out.println("Tokens in Place 2 (expected 0): " + place2.getTokenCount());
    }

    private static void runSpecialZero() {
        System.out.println("Running Special Zero Transition Simulation...");

        PetriNet petriNetwork = new PetriNet();

        Place place1 = new Place(3, petriNetwork.generateId(1));
        Place place2 = new Place(0, petriNetwork.generateId(1));

        petriNetwork.addPlace(place1);
        petriNetwork.addPlace(place2);

        Transition transition1 = new Transition("T1", petriNetwork.generateId(2));
        petriNetwork.addTransition(transition1);

        IncomingArc_Zero incomingArc_zero = new IncomingArc_Zero(transition1, place2, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(incomingArc_zero);
        transition1.addIncomingArc(incomingArc_zero);

        OutgoingArc outgoingArc = new OutgoingArc(transition1, place1, 1, petriNetwork.generateId(0));
        petriNetwork.addArc(outgoingArc);
        transition1.addOutgoingArc(outgoingArc);

        petriNetwork.displayNetwork();

        System.out.println("\nActivating transition T1...");
        petriNetwork.fireTransition(String.valueOf(transition1.getId()));

        System.out.println("\nState of Petri Network after Transition T1 Activation:");
        petriNetwork.displayNetwork();

        System.out.println("\nVerifying state:");
        System.out.println("Tokens in Place 1 (expected 4): " + place1.getTokenCount());
        System.out.println("Tokens in Place 2 (expected 0): " + place2.getTokenCount());
    }
}
