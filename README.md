# ElevatorSim_java
Simulation of an elevator

## Assumptions
1. Elevator does not have up & down buttons on each floor.
2. Elevator uses a FIFO algorithm (while also picking up and dropping off passengers along its route).
3. Elevator has no maximum capacity.

## Outline

### Classes

#### Elevator
The Elevator class tracks all information that pertains to its movement and its passengers: 
- Who is waiting for the elevator
- What floor they are waiting on
- Who is on the elevator
- What floor each passenger wants to go to

The Elevator's movement uses a basic (and slightly modified) FIFO algorithm to determine its next destination. The modification added to this algorithm allows passengers and potential passengers to enter and exit the elevator if the elevator happens to be passing by their floor. The decision to select this algorithm was made for the sake of simplicity. The elevator is still going to successfully get all passengers to their destinations within a reasonable amount of time. The downside is that if a potential passenger is only a floor away from the elevator's current location, they may have to wait for the elevator for a longer amount of time.

#### Floor
The Floor class is very basic. It assigns an ID (numerical value) and a name to each floor within the building. The elevator also counts as a special floor to help track those who are on the elevator.

#### Person
The Person class tracks all information regarding for a person in the building. This includes: 
- Their current floor (could be the elevator)
- Whether or not they are waiting for the elevator
- Their name (presently all are assigned numbers - very humane!)

#### Main
The Main class creates a random number of floors and people. The people are then each assigned a random floor on which they are currently located. 
There are two modes available within the Elevator simulator, Manual and Automatic.
Manual mode allows the user to manually step through each elevator movement. 
Automatic mode places the elevator movement on a 10 second timer. Each movement occurs after the 10 seconds are up.

Once the floors, people, and elevator are all created, the Main method starts the elevator movement. Starting the elevator movement also starts randomly selecting people to "click the elevator button" (request the elevator to their floor).

No person that is already waiting for the elevator or is on the elevator will "press the elevator button".

### Interaction within the Command Line
There is no user interaction with this elevator simulator, with the exception of manual mode, when the user can step through each elevator movement.
Within each elevator movement, the simulator will announce the following events: 
- The elevator's current primary destination (the first floor in its queue)
- The elevator's current floor
- The elevator reaching its current primary destination (this also removes that destination from the elevator's queue)
- A person clicking the elevator button, or requesting the elevator, and what floor they are on
- A person entering the elevator
- A person exiting the elevator, along with the floor they are now on
