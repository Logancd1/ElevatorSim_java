package elevatorsim;

import java.util.Random;

public class Person {

	private static String currentFloor;
	private static int name;
	
	public Person(int numFloors, int personNum) {
		Random random = new Random();
		setName(personNum);
		setCurrentFloor("" + random.nextInt(numFloors));
	}
	
	public void setCurrentFloor(String floorID) {
		System.out.println("Person " + getName() + " is on floor " + floorID);
		currentFloor = floorID;
	}
	
	public String getCurrentFloor() {
		return currentFloor;
	}
	
	public int getName() {
		return name;
	}
	
	private void setName(int newName) {
		name = newName;
	}
}
