# PetriNet Project

## Overview
This project is a Java-based implementation of a **Petri Net** model, a mathematical representation of distributed systems. The project focuses on modeling and simulating various Petri Net components such as **places**, **transitions**, and **arcs**, while also providing functionality to simulate state changes based on these elements.

## Features
- **Core Components**:
    - `Place`: Represents a condition or state in the Petri Net.
    - `Transition`: Represents events that can change the state.
    - `Arc`: Connects places to transitions or transitions to places.
    - `Arc_ENTRANT`: Specific type of arc leading to a transition.
    - `Arc_SORTANT`: Specific type of arc leading from a transition.
    - `Arc_zero`: A special arc type with specific rules.
    - `Arc_videur`: A special arc type with specific rules.

- **Simulation Features**:
    - Add tokens to places.
    - Trigger transitions based on firing conditions.
    - Simulate the state evolution of the Petri Net.

- **Validation**:

    - Ensure transitions only fire when conditions are met.


## Requirements
- **Java** version 17 or higher.
- Any IDE that supports Java (e.g., IntelliJ IDEA, Eclipse).

## Installation
1. Clone the repository:
   ```bash
   git clone https://gitlab-df.imt-atlantique.fr/k23aroui/mapd_file_rouge
   cd MAPD_file_rouge
2. Open the project in your favorite IDE.

## Usage
Running the Project
To execute the simulation:

- Compile and run the Main class.
- Configure your Petri Net setup in Main.java by specifying:
  Places and their initial token counts.
  Transitions and the arcs connecting them.

## Future Enhancements:

Graphical User Interface: A graphical interface to visualize and interact with Petri nets.