package elevatorsim;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Elevator {
	
	private static int currentFloorID;
	private static Floor currentFloor;
	private static Floor elevatorFloor;
	private static ArrayList<Floor> floorsWaiting;
	private static Queue<Floor> elevatorDestinations;
	private static ArrayList<Person> waitingPersons;
	private static ArrayList<Floor> destinationFloors;
	private static ArrayList<Person> currentPassengers;
	private static ArrayList<Floor> allFloors;
	
	public Elevator(ArrayList<Floor> floorList) {
		currentFloorID = 1;//starting floor
		allFloors = floorList;
		elevatorDestinations = new LinkedList<>();//this tells the elevator where to go
		floorsWaiting = new ArrayList<Floor>();//this tells the elevator what floors have people waiting to load
		waitingPersons = new ArrayList<>();//this tells the elevator what people are waiting for the elevator
		destinationFloors = new ArrayList<>();//this tells the elevator what floors the current passengers want to go to
		currentPassengers = new ArrayList<>();//this tells the elevator everyone currently on the elevator
		elevatorFloor = new Floor(200, "elevator");
		System.out.println("Elevator is starting on " + getCurrentFloor());
	}
	
	public int getCurrentFloorID() {
		return currentFloorID;
	}
	
	public Floor getCurrentFloor() {
		
		for(Floor floor : allFloors) {//find out which floor has the current floor's matching ID
			if(floor.getFloorID() == currentFloorID) {
				currentFloor = floor;
			}
		}
		return currentFloor;
	}
	
	public void move() {
		
		int current = getCurrentFloorID();
		Floor primaryDestination = elevatorDestinations.peek();
		System.out.println("Elevator primary destination is " + primaryDestination);
		if(primaryDestination == null) {
			//do nothing, there are no current destinations for the elevator, time to wait for instruction
		} else {
			if(current > primaryDestination.getFloorID()) {
				currentFloorID--;
				System.out.println("Elevator going down to " + getCurrentFloor());
				if(currentFloorID == primaryDestination.getFloorID()) {
					reachedDestination();
				} else {
					loadPassengers();
					unloadPassengers();
				}
			} else if(current < primaryDestination.getFloorID()) {
				currentFloorID++;
				System.out.println("Elevator going up to " + getCurrentFloor());
				if(currentFloorID == primaryDestination.getFloorID()) {
					reachedDestination();
				} else {
					loadPassengers();
					unloadPassengers();
				}
			} else {//current floor IS the destination floor
				reachedDestination();
			}
		}
	}
	
	private void reachedDestination() {
		
		System.out.println("Elevator has reached its destination of " + elevatorDestinations.peek());
		removeFromDestinationQueue();
		loadPassengers();
		unloadPassengers();
	}
	
	public ArrayList<Person> getWaitingPersons() {
		return waitingPersons;
	}
	
	private ArrayList<Floor> getWaitingFloors() {
		return floorsWaiting;
	}
	
	public ArrayList<Person> getPassengers() {
		return currentPassengers;
	}
	
	private void addPassenger(Person passenger) {
		currentPassengers.add(passenger);
	}
	
	private void removePassenger(Person passenger) {
		currentPassengers.remove(passenger);
	}
	
	private ArrayList<Floor> getDestinations() {
		return destinationFloors;
	}
	
	private void addDestination(Floor floor) {
		
		if(destinationFloors.contains(floor)) {
			//do nothing, it's already there
		} else {
			destinationFloors.add(floor);
			addToDestinationQueue(floor);
		}
	}
	
	public void addWaitingPerson(Person person) {
		waitingPersons.add(person);
	}
	
	public void removeWaitingPerson(Person person) {
		waitingPersons.remove(person);
	}
	
	public void addWaitingFloor(Floor floor) {

		if(floorsWaiting.contains(floor)) {
			//do nothing, it's already there
		} else {
			floorsWaiting.add(floor);
			addToDestinationQueue(floor);
		}
	}
	
	private void addToDestinationQueue(Floor floor) {
		
		if(elevatorDestinations.contains(floor)) {
			//do nothing, it's already there
		} else {
			elevatorDestinations.add(floor);
		}
	}
	
	private void removeFromDestinationQueue() {
		elevatorDestinations.remove();
	}
	
	public void loadPassengers() {

		Floor current = getCurrentFloor();
		ArrayList<Floor> waitFloors = getWaitingFloors();
		ArrayList<Person> waitPeople = (ArrayList<Person>) getWaitingPersons().clone();
		
		if (waitFloors.contains(current) && !waitPeople.isEmpty()) {
			for(Person waitPerson : waitPeople) {
				if(waitPerson.getCurrentFloor().equals(current)) {
					removeWaitingPerson(waitPerson);
					waitPerson.setWaitingState(false);
					addPassenger(waitPerson);
					Floor waitPersonCurrentFloor = waitPerson.getCurrentFloor();
					waitPerson.setCurrentFloor(elevatorFloor, false);//set passenger's current floor to be the elevator
					Floor waitPersonDestinationFloor = waitPerson.getCurrentFloor();
					while(waitPersonCurrentFloor.equals(waitPersonDestinationFloor) || waitPersonDestinationFloor.equals(elevatorFloor)) {
						//ensure that the person getting on the elevator doesn't want to stay on the same floor
						waitPersonDestinationFloor = allFloors.get((int) (Math.random() * allFloors.size()));//setting the person's destination floor
					}
					waitPerson.setDestinationFloor(waitPersonDestinationFloor);//setting the person's destination floor
					addDestination(waitPerson.getDestinationFloor());
					waitFloors.remove(current);//remove current floor as we just loaded its passengers
				}
			}
		} else {
			//no people waiting on this floor, elevator can continue moving
		}
	}
	
	private void unloadPassengers() {
		
		Floor current = getCurrentFloor();
		ArrayList<Person> passengers = (ArrayList<Person>) getPassengers().clone();
		ArrayList<Floor> dests = getDestinations();
		
		if(dests.contains(current) && !passengers.isEmpty()) {
			for(Person passenger : passengers) {
				if(passenger.getDestinationFloor().equals(current)) {//if a passenger wanted to go to the current floor, unload them and set their current floor
					removePassenger(passenger);
					passenger.setCurrentFloor(current, true);
				}
			}
			
		} else {
			//no passengers wanted to go to this floor, elevator can continue moving
		}
	}
}
