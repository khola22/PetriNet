package org.petriNet;

import java.util.*;

public class PetriNet implements PetriNetService {

	// Create attributes as Lists of Places, Transitions, and Arcs
	private final LinkedList<Integer> idCounters;
	private List<Place> places;
	private List<Transition> transitions;
	private LinkedList<Arc> arcs;
	private String networkState = "No transition fired";

	// Create a constructor
	public PetriNet() {
		// Initialize the lists as empty
		this.places = new ArrayList<>();
		this.transitions = new ArrayList<>();
		this.arcs = new LinkedList<>();
		idCounters = new LinkedList<>();

		// Initialize the id counters for Arcs, Places, and Transitions
		idCounters.add(0);
		idCounters.add(0);
		idCounters.add(0);
	}

	// Create a method to generate unique ids
	public int generateId(int index) {
		int id = idCounters.get(index);
		idCounters.set(index, id + 1);
		return id;
	}

	// Create getters and setters
	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public LinkedList<Arc> getArcs() {
		return this.arcs;
	}

	public void setArcs(LinkedList<Arc> arcs) {
		this.arcs = arcs;
	}

	@Override
	public void addPlace(Place place) {
		// Verify that there is no similar place in the list
		for (Place place1 : this.places) {
			if (place1.getId() == place.getId()) {
				System.out.println("Place already exists");
				return;
			}
		}
		this.places.add(place);
	}

	@Override
	public void addTransition(Transition transition) {
		// Verify that there is no similar transition in the list
		for (Transition transition1 : this.transitions) {
			if (transition1.getId() == transition.getId()) {
				System.out.println("Transition already exists");
				return;
			}
		}
		this.transitions.add(transition);
	}

	@Override
	public void addArc(Arc arc) {
		// Verify that there is no similar arc in the list
		for (Arc arc1 : this.arcs) {
			if (arc1.getPlace().getId() == arc.getPlace().getId() &&
					arc1.getTransition().getId() == arc.getTransition().getId() &&
					arc1.getClass() == arc.getClass()) {
				System.out.println("Arc already exists");
				return;
			}
		}
		this.arcs.add(arc);
	}

	/**
	 * Removing a place results in the removal of all arcs linked to it.
	 * Removing a transition results in the removal of all arcs linked to it.
	 */
	@Override
	public void removePlace(Place place) {
		this.places.remove(place);
		// Remove all arcs linked to the place
		this.arcs.removeIf(arc -> arc.getPlace().getId() == place.getId());
	}

	@Override
	public void removeTransition(Transition transition) {
		this.transitions.remove(transition);
		// Remove all arcs linked to the transition
		this.arcs.removeIf(arc -> arc.getTransition().getId() == transition.getId());
	}

	@Override
	public void removeArc(Arc arc) {
		this.arcs.remove(arc);
		// Remove it from the list of arcs of the transition
		if (arc instanceof IncomingArc) {
			arc.getTransition().getIncomingArcs().remove(arc);
		} else if (arc instanceof OutgoingArc) {
			arc.getTransition().getOutgoingArcs().remove(arc);
		}
	}

	@Override
	public void fireTransition(String id) {
		// Set the state of the network to "Transition being validated"
		this.networkState = "Transition being validated";

		// Find the transition with the id
		Transition chosenTransition = null;
		for (Transition transition : this.transitions) {
			if (String.valueOf(transition.getId()).equals(id)) {
				chosenTransition = transition;
			}
		}

		// Check if the transition id is valid
		if (chosenTransition == null) {
			System.out.println("The transition id is not valid");
			return;
		} else {
			chosenTransition.fire();
		}

		// Set the state of the network to "No transition fired"
		this.networkState = "No transition fired";
	}

	@Override
	public void displayState() {
		if (this.networkState.equals("No transition fired")) {
			System.out.println("No transition fired");
			// Ask the user if they want to fire a transition
			Scanner scanner = new Scanner(System.in);
			System.out.println("Do you want to fire a transition? (Y/N)");
			String response = scanner.nextLine();
			if (response.equals("Y")) {
				// Show the Petri Net
				displayNetwork();
				// Ask the user for the id of the transition to fire
				System.out.println("Enter the id of the transition to fire: ");
				String id = scanner.nextLine();
				fireTransition(id);
			}
		} else {
			System.out.println("Transition being validated");
		}
	}

	@Override
	public void displayNetwork() {
		System.out.println("Petri Net");
		System.out.println(this.places.size() + " places");
		System.out.println(this.transitions.size() + " transitions");
		System.out.println(this.arcs.size() + " arcs");

		System.out.println("List of places:");
		// Iterate through the list of arcs to display the places
		// A list for incoming arcs and a list for outgoing arcs
		for (Place place : this.places) {
			List<Arc> incomingArcs = new ArrayList<>();
			List<Arc> outgoingArcs = new ArrayList<>();
			for (Arc arc : this.arcs) {
				// Verify if arc.getPlace() == place
				if (arc.getPlace().getId() == place.getId()) {
					// Verify if it's an incoming arc
					if (arc instanceof IncomingArc) {
						incomingArcs.add(arc);
					} else if (arc instanceof OutgoingArc) {
						outgoingArcs.add(arc);
					}
				}
			}
			// Display incoming/outgoing arcs for the place
			System.out.println(place.getId() + " : place with " + place.getTokenCount() + " tokens, "
					+ incomingArcs.size() + " outgoing arc(s), " + outgoingArcs.size() + " incoming arc(s)");
		}

		System.out.println("List of transitions:");
		for (Transition transition : this.transitions) {
			System.out.println(transition.getId() + " : transition " + transition.getName() + " " + transition.getIncomingArcs().size()
					+ " incoming arc(s), " + transition.getOutgoingArcs().size() + " outgoing arc(s)");
		}

		System.out.println("List of arcs:");
		for (Arc arc : this.arcs) {
			if (arc instanceof IncomingArc) {
				System.out.println(arc.getId() + " : simple arc with weight " + arc.getWeight() + " ("
						+ "Place with Id " + arc.getPlace().getId() + " to " + arc.getTransition().getName() + ")");
			} else {
				System.out.println(arc.getId() + " : simple arc with weight " + arc.getWeight() + " ("
						+ arc.getTransition().getName() + " to " + "Place with Id " + arc.getPlace().getId() + ")");
			}
		}
	}
}