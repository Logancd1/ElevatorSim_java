package elevatorsim;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		//want to first randomize number of floors and number of people in the building
		//then want to create the people, floors, and elevator
		Random random = new Random();
		int numFloors = random.nextInt(15);
		ArrayList<Floor> floors = new ArrayList<>();//actual list of floors in the building
		for(int j = 0; j < numFloors; j++) {
			floors.add(new Floor(j + ""));
		}
		int numPeople = random.nextInt(numFloors*2);
		ArrayList<Person> people = new ArrayList<>();//actual list of people in the building
		for(int i = 0; i < numPeople; i++) {
			people.add(new Person(floors,i));
		}
		
		Elevator elev = new Elevator(floors);
		
		int elevatorMovements = 0;
		while(elevatorMovements < 150) {
			
		}
	}

}
