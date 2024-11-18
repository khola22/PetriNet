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
		 * We add a method to create many transitions at once.
		 * This is an enhancement to avoid many lines of code.
		 * @param numberOfTransitions the number of transitions to create
		 * @return List<Transition>
		 */

		public LinkedList<Transition> createTransitions(int numberOfTransitions) {
			LinkedList<Transition> transitions = new LinkedList<>();
			// verify that the number of transitions is positive
			if (numberOfTransitions <= 0) {
				System.out.println("The number of transitions must be positive");
				return transitions;
			}
			for (int i = 0; i < numberOfTransitions; i++) {
				int id = this.generateId(2);
				Transition transition = new Transition("Transition " + id, id);
				transitions.add(transition);
			}
			return transitions;
		}

		/**
		 * We add a method to create many places at once.
		 * This is an enhancement to avoid many lines of code.
		 * @param numberOfPlaces the number of places to create
		 * @param numberOfTokens List<int> of tokens for each place
		 * @return List<Place>
		 */

		public LinkedList<Place> createPlaces(int numberOfPlaces, List<Integer> numberOfTokens) {
			LinkedList<Place> places = new LinkedList<>();

			/* Verify that the number of tokens is equal to the number of places
			* Verify that the number of tokens is positive
			* verify that the number of places is positive
			*/

			if (numberOfPlaces <= 0 || numberOfTokens.size() != numberOfPlaces || numberOfTokens.stream().anyMatch(token -> token < 0)) {
				System.out.println("Error: The number of places must be positive, the number of tokens must be positive and equal to the number of places");
				return places;
			}

			for (int i = 0; i < numberOfPlaces; i++) {
				places.add(new Place( numberOfTokens.get(i), this.generateId(1)));
			}
			return places;
		}

		/**
		 * We add a method to create many Incoming arcs at once.
		 * This is an enhancement to avoid many lines of code.
		 * If one of the types of arcs is not valid, the method will return null.
		 *
		 * @param numberOfArcs  the number of Incoming Arcs to create
		 * @param weights       List<int> of weights for each Incoming Arc
		 * @param places        List<Place> of places for each Incoming Arc
		 * @param transitions   List<Transition> of transitions for each Incoming Arc
		 * @param types_of_arcs List<String> of types for each Incoming Arc
		 * @return List<IncomingArc>
		 */

		public LinkedList<Arc> createIncomingArcs(int numberOfArcs, List<String> types_of_arcs, List<Integer> weights, List<Place> places, List<Transition> transitions) {
			LinkedList<Arc> IncomingArc = new LinkedList<>();

			/* Verify that the number of IncomingArc is positive
			 * Verify that the number of weights is equal to the number of IncomingArc
			 * Verify that the number of weights is positive
			 * Verify that the number of places is equal to the number of IncomingArc
			 * Verify that the number of transitions is equal to the number of IncomingArc
			 */

			if (numberOfArcs <= 0 || weights.size() != numberOfArcs || weights.stream().anyMatch(weight -> weight < 0) ||
					places.size() != numberOfArcs || transitions.size() != numberOfArcs) {
				System.out.println("Error: The number of weights, places, and transitions must be positive and equal to the number of IncomingArc");
				return IncomingArc;
			}

			for (int i = 0; i < numberOfArcs; i++) {
                switch (types_of_arcs.get(i)) {
					case "zero":
						IncomingArc.add(new IncomingArc_Zero(transitions.get(i), places.get(i), weights.get(i), this.generateId(0)));
					case "videur":
						IncomingArc.add(new IncomingArc_Videur(transitions.get(i), places.get(i), weights.get(i), this.generateId(0)));
					case "simple":
						IncomingArc.add(new IncomingArc_Simple(transitions.get(i), places.get(i), weights.get(i), this.generateId(0)));
						break;
                    case "Simple":
                        IncomingArc.add(new IncomingArc_Simple(transitions.get(i), places.get(i), weights.get(i), this.generateId(0)));
                        break;
                    case "Videur":
                        IncomingArc.add(new IncomingArc_Videur(transitions.get(i), places.get(i), weights.get(i), this.generateId(0)));
                        break;
                    case "Zero":
                        IncomingArc.add(new IncomingArc_Zero(transitions.get(i), places.get(i), weights.get(i), this.generateId(0)));
                        break;
                    default:
                        System.out.println("Error: one of he type of arc is not valid");
                        return null;
                }
			}
			return IncomingArc;
		}

		/**
		 * We add a method to create many Outgoing arcs at once.
		 * This is an enhancement to avoid many lines of code.
		 * @param numberOfArcs the number of Outgoing Arcs to create
		 * @param weights List<int> of weights for each Outgoing Arc
		 * @param places List<Place> of places for each Outgoing Arc
		 * @param transitions List<Transition> of transitions for each Outgoing Arc
		 * @return List<Arc>
		 */

		public LinkedList<Arc> createOutgoingArcs(int numberOfArcs, List<Integer> weights, List<Place> places, List<Transition> transitions) {
			LinkedList<Arc> OutgoingArc = new LinkedList<>();

			/* Verify that the number of OutgoingArc is positive
			 * Verify that the number of weights is equal to the number of OutgoingArc
			 * Verify that the number of weights is positive
			 * Verify that the number of places is equal to the number of OutgoingArc
			 * Verify that the number of transitions is equal to the number of OutgoingArc
			 */

			if (numberOfArcs <= 0 || weights.size() != numberOfArcs || weights.stream().anyMatch(weight -> weight < 0) ||
					places.size() != numberOfArcs || transitions.size() != numberOfArcs) {
				System.out.println("Error: The number of weights, places, and transitions must be positive and equal to the number of OutgoingArc");
				return OutgoingArc;
			}

			for (int i = 0; i < numberOfArcs; i++) {
				OutgoingArc.add(new OutgoingArc(transitions.get(i), places.get(i), weights.get(i), this.generateId(0)));
			}
			return OutgoingArc;
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