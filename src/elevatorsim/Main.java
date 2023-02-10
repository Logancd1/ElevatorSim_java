package elevatorsim;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		//want to first randomize number of floors and number of people in the building
		Random random = new Random();
		int floors = random.nextInt(15);
		int numPeople = random.nextInt(floors*2);
		ArrayList<Person> people = new ArrayList<>();
		for(int i = 0; i < numPeople; i++) {
			people.add(new Person(floors,i));
		}
	}

}
