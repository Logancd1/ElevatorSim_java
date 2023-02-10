package elevatorsim;

import java.util.ArrayList;
import java.util.Random;

public class Person {

	private static Floor currentFloor;
	private static int name;
	private static boolean waiting;
	private static Floor destinationFloor;
	
	public Person(ArrayList<Floor> floors, int personNum) {
		Random random = new Random();
		setName(personNum);
		setCurrentFloor(floors.get(random.nextInt(floors.size())));
		setWaitingState(false);
	}
	
	public void setCurrentFloor(Floor floor) {
		System.out.println("Person " + getName() + " is now on floor " + floor);
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
			System.out.println("Person " + getName() + " is now waiting for the elevator on floor " + getCurrentFloor());
		}
	}
	
	public boolean getWaitingState() {
		return waiting;
	}
	
	public void setDestinationFloor(Floor floor) {
		destinationFloor = floor;
	}
	
	public Floor getDestinationFloor() {
		return destinationFloor;
	}
}
