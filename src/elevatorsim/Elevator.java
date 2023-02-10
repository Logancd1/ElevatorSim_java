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
		elevatorFloor = new Floor("elevator");
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
		if(primaryDestination == null) {
			//do nothing, there are no current destinations for the elevator, time to wait for instruction
		} else {
			if(current > primaryDestination.getFloorID()) {
				System.out.println("Elevator going down");
				currentFloorID--;
				loadPassengers();
				unloadPassengers();
			} else if(current < primaryDestination.getFloorID()) {
				System.out.println("Elevator going up");
				currentFloorID++;
				loadPassengers();
				unloadPassengers();
			}
		}
	}
	
	private ArrayList<Person> getWaitingPersons() {
		return waitingPersons;
	}
	
	private ArrayList<Floor> getWaitingFloors() {
		return floorsWaiting;
	}
	
	private ArrayList<Person> getPassengers() {
		return currentPassengers;
	}
	
	private void addPassenger(Person passenger) {
		currentPassengers.add(passenger);
	}
	
	private ArrayList<Floor> getDestinations() {
		return destinationFloors;
	}
	
	private void addDestination(Floor floor) {
		
		if(destinationFloors.contains(floor)) {
			//do nothing, it's already there
		} else {
			destinationFloors.add(floor);
		}
	}
	
	public void addWaitingPerson(Person person) {
		waitingPersons.add(person);
	}
	
	public void addWaitingFloor(Floor floor) {

		if(floorsWaiting.contains(floor)) {
			//do nothing, it's already there
		} else {
			floorsWaiting.add(floor);
		}
	}
	
	public void loadPassengers() {

		Floor current = getCurrentFloor();
		ArrayList<Floor> waitFloors = getWaitingFloors();
		ArrayList<Person> waitPeople = getWaitingPersons();
		
		if (waitFloors.contains(current)) {
			for(Person waitPerson : waitPeople) {
				if(waitPerson.getCurrentFloor().equals(current)) {
					waitPeople.remove(waitPerson);
					addPassenger(waitPerson);
					addDestination(waitPerson.getDestinationFloor());
					waitPerson.setCurrentFloor(elevatorFloor);//set passenger's current floor to be the elevator
					waitFloors.remove(current);//remove current floor as we just loaded its passengers
				}
			}
		} else {
			//no people waiting on this floor, elevator can continue moving
		}
	}
	
	private void unloadPassengers() {
		
		Floor current = getCurrentFloor();
		ArrayList<Person> passengers = getPassengers();
		ArrayList<Floor> dests = getDestinations();
		
		if(dests.contains(current)) {
			for(Person passenger : passengers) {
				if(passenger.getDestinationFloor().equals(current)) {//if a passenger wanted to go to the current floor, unload them and set their current floor
					passengers.remove(passenger);
					passenger.setCurrentFloor(current);
				}
			}
			
		} else {
			//no passengers wanted to go to this floor, elevator can continue moving
		}
	}
}
