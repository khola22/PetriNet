# Petri Net

## Petri Net Simulator

### Overview
This project provides an implementation of a Petri Net,
a mathematical model used to represent and simulate systems with discrete events.
The primary components of a Petri Net are places, transitions, and arcs. 
Places hold tokens, transitions are fired to modify the state of the system, and arcs define the 
relationships between places and transitions. The system allows users to define a network of places, 
transitions, and arcs, and then fire transitions to simulate the system behavior.

### Features
- **Core Components**:
    - `PetriNet`: Represents the Petri Net.
    - `Place`: Represents a container of tokens.
    - `Transition`: Represents events triggered based on the token conditions in connected places.
    - `Arc`: Connects places to transitions or transitions to places.
      - `IncomingArc_Simple`: Transfers tokens based on a weight.
      - `IncomingArc_Videur`: Removes all tokens from a place when fired.
      - `IncomingArc_Zero` :  Fires only when the connected place has no tokens.
      - `OutgoingArc`: Adds tokens to a place after a transition is fired.

- **Simulation Features**:
    - Add tokens to places.
    - Trigger transitions based on firing conditions.
    - Simulate the state evolution of the Petri Net.

- **Validation**:

    - Ensure transitions only fire when conditions are met.


### Requirements
- **Java** version 17 or higher.
- Any IDE that supports Java (e.g., IntelliJ IDEA, Eclipse).

### Installation
1. Clone the repository:
   ```bash
   git clone https://gitlab-df.imt-atlantique.fr/k23aroui/mapd_file_rouge
   cd MAPD_file_rouge
2. Open the project in your favorite IDE.

### Usage
Running the Project
To execute the simulation:

- Compile and run the Main class.
- Configure your Petri Net setup in Main.java by specifying:
  Places and their initial token counts.
  Transitions and the arcs connecting them.

### Future Enhancements:

Graphical User Interface: A graphical interface to visualize and interact with Petri nets.


## Tests for PetriNet Implementation

The PetriNetTest class contains a comprehensive suite of unit tests that verify the correctness 
and functionality of the PetriNet system. These tests are organized to validate the key components and 
operations of a Petri Net, including places, transitions, arcs, and their interactions. 
Below is a detailed description of the test cases:

###  Basic Place Operations :

- **testAddTokens**: Verifies that tokens can be added to a place.
- **testRemoveTokens**: Verifies that tokens can be removed from a place.

### Network Structure Management

- **testAddPlace**: Verifies that places can be added only once to the Petri Net.
- **testAddTransition**: Verifies that transitions can be added uniquely to the Petri Net.
- **testAddArc**: Validates the addition of arcs, including handling of negative weights and duplicate arcs.
- **testSetWeight**: Ensures that the weight of an arc can be updated.
- **testSetTokenCount**: Validates that the token count of a place can be updated.

### Petri Net Assembly

- **testAssemblePetri**: Constructs a full Petri Net representing a Mutex system with places, transitions, and arcs. This test ensures:

  - Places and transitions are uniquely added.
  - The correct number of arcs, places, and transitions are present.
  - Complex connections and token flows are correctly assembled.

### Activation and Transition Firing

Tests under this category simulate the firing of transitions and observe token dynamics across the Petri Net:
- **testActivatePetri_1 (RI)**: Tests firing a transition in a simple network with one transition. The result expects the Petri Net to be in the same state after firing.
- **testActivatePetri_2 (RD0)**: Verifies that firing a transition does not remove tokens if the input place has insufficient tokens.
- **testActivatePetri_3 (RD1)**: Tests token reduction when a transition fires with sufficient tokens in the input place.
- **testActivatePetri_4 (RD2)**: Checks for correct token reduction when the input place has more tokens than the arc weight.
- **testActivatePetri_5 (RG0)**: Ensures tokens are added to the output place when a transition fires.
- **testActivatePetri_6 (RM0)**: Simulates token transfer between two places via a transition. 
- **testActivatePetri_7 (RM1)**: Validates token transfer where input and output arcs have different weights. 
- **testActivatePetri_8 (RGM)**: Tests transitions with multiple input places. 
- **testActivatePetri_9 (RDM)**: Simulates transitions with multiple output places. 
- **testActivatePetri_10 (RDGM)**: Combines multiple inputs and outputs to validate a complex firing scenario.

### Removal of different components of the Petri Net

- **testRemoveTransition_0 (ST0)** : Validates that a transition can be removed from the Petri Net
 After the removal, the transition list size should be zero, and the network structure is updated accordingly.

- **testRemovePlace (SP1)** : Tests the removal of a place from the Petri Net, ensuring that related arcs are also
 removed while leaving unrelated transitions intact.

- **testRemoveTransition_1 (ST1)** : Validates the removal of a transition along with its associated arcs while leaving unrelated places intact.

- **testRemoveArc (SA1)** : Ensures that a specific arc can be removed from the Petri Net while keeping its associated places and transitions intact.

### Additional Tests for the specific incoming arc types

- **testIncomingArcVideur (SAV)** : Validates the behavior of the IncomingArc_Videur class by removing all tokens from a place when the connected transition fires.
- **testIncomingArcZero (SAZ)** : Tests the IncomingArc_Zero class to ensure that the transition fires only when the connected place has zero tokens.

### Test des méthode d'améloriation

- **testActivatePetri_11 (RDGM1)** : Tests the methods setPlaces, setTransitions, setArcs to add a list of these components to the PetriNet all at once, and setOutgoingArcs, setIncomingArcs to
    add Arcs to a transition all at once.
- **testActivatePetri_12 (RDGM2)** : Tests the methods createPlaces, createTransitions, create(Incoming or Outgoing)Arcs to create a list of these components automatically and all at once.
