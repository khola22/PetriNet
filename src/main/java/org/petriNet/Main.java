package org.petriNet;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running Standard Petri Network Simulation...");

        // CR1
        PetriNet Mutex = new PetriNet();
        Arc arc;

        // CP1
        Place P1 = new Place(0, Mutex.generateId(1));
        Mutex.addPlace(P1);

        // CT1
        Transition T1 = new Transition("T1", Mutex.generateId(2));
        Mutex.addTransition(T1);

        // CP2
        Place P2 = new Place(1, Mutex.generateId(1));
        Mutex.addPlace(P2);

        // CT2
        Transition T2 = new Transition("T2", Mutex.generateId(2));
        Mutex.addTransition(T2);

        // CP5
        Place P3 = new Place(0, Mutex.generateId(1));
        Mutex.addPlace(P3);

        // CT3
        Transition T3 = new Transition("T3", Mutex.generateId(2));
        Mutex.addTransition(T3);

        // CP4
        Place P4 = new Place(0, Mutex.generateId(1));
        Mutex.addPlace(P4);

        // CT4
        Transition T4 = new Transition("T4", Mutex.generateId(2));
        Mutex.addTransition(T4);

        // CP5
        Place P5 = new Place(1, Mutex.generateId(1));
        Mutex.addPlace(P5);

        // CPT1
        // Create the simple incoming arc to T1 from P1
        arc = new IncomingArc_Simple(T1, P1, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T1.addIncomingArc((IncomingArc) arc);


        // CTP2
        // Create the simple outgoing arc from T2 to P1
        arc = new OutgoingArc(T2, P1, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T2.addOutgoingArc((OutgoingArc) arc);


        // CTP1
        // Create the simple outgoing arc from T1 to P2
        arc = new OutgoingArc(T1, P2, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T1.addOutgoingArc((OutgoingArc) arc);


        // CPT2
        // Create the simple incoming arc to T2 from P2
        arc = new IncomingArc_Simple(T2, P2, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T2.addIncomingArc((IncomingArc) arc);


        // CPT5
        // Create the simple incoming arc to T1 from P3
        arc = new IncomingArc_Simple(T1, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T1.addIncomingArc((IncomingArc) arc);


        // CTP5
        // Create the simple outgoing arc from T2 to P3
        arc = new OutgoingArc(T2, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T2.addOutgoingArc((OutgoingArc) arc);


        // CPT6
        // Create the simple incoming arc to T3 from P3
        arc = new IncomingArc_Simple(T3, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T3.addIncomingArc((IncomingArc) arc);


        // CTP6
        // Create the simple outgoing arc from T4 to P3
        arc = new OutgoingArc(T4, P3, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T4.addOutgoingArc((OutgoingArc) arc);


        // CTP3
        // Create the simple outgoing arc from T3 to P4
        arc = new OutgoingArc(T3, P4, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T3.addOutgoingArc((OutgoingArc) arc);


        // CPT4
        // Create the simple incoming arc to T4 from P4
        arc = new IncomingArc_Simple(T4, P4, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T4.addIncomingArc((IncomingArc) arc);


        // CPT3
        // Create the simple incoming arc to T3 from P5
        arc = new IncomingArc_Simple(T3, P5, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T3.addIncomingArc((IncomingArc) arc);


        // CTP4
        // Create the simple outgoing arc from T4 to P5
        arc = new OutgoingArc(T4, P5, 1, Mutex.generateId(0));
        Mutex.addArc(arc);
        T4.addOutgoingArc((OutgoingArc) arc);


        Mutex.displayState();
    }
}
