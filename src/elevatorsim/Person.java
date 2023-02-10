package elevatorsim;

import java.util.ArrayList;
import java.util.Random;

public class Person {

	private Floor currentFloor;
	private int name;
	private boolean waiting;
	private Floor destinationFloor;
	
	public Person(ArrayList<Floor> floors, int personNum) {
		Random random = new Random();
		setName(personNum);
		setCurrentFloor(floors.get(random.nextInt(floors.size())), false);
		setWaitingState(false);
	}
	
	public void setCurrentFloor(Floor floor, boolean wasOnElev) {
		
		if(wasOnElev) {
			System.out.println("Person " + getName() + " has departed the elevator and is now on " + floor);
		} else {
			System.out.println("Person " + getName() + " is now on " + floor);
		}
		currentFloor = floor;
	}
	
	public Floor getCurrentFloor() {
		return currentFloor;
	}
	
	public int getName() {
		return name;
	}
	
	private void setName(int newName) {
		name = newName;
	}
	
	public void setWaitingState(boolean state) {
		waiting = state;
		if(waiting) {
			System.out.println("Person " + getName() + " has requested the elevator and is now waiting for the elevator on " + getCurrentFloor());
		}
	}
	
	public boolean isWaiting() {
		return waiting;
	}
	
	public void setDestinationFloor(Floor floor) {
		destinationFloor = floor;
		System.out.println("Person " + getName() + " wants to go to " + destinationFloor);
	}
	
	public Floor getDestinationFloor() {
		return destinationFloor;
	}

}
