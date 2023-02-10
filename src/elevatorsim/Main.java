package elevatorsim;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		
		//want to first randomize number of floors and number of people in the building
		//then want to create the people, floors, and elevator
		
		Scanner scanner = new Scanner(System.in);
		boolean manualMode = false;
		boolean validInput = false;
		do {
			System.out.println("Would you like to run the elevator simulator in manual mode or automatic mode?\n"
					+ "Manual Mode allows the user to manually step through each elevator movement\n"
					+ "Automatic mode places the elevator on a 10 second timer in between movements\n"
					+ "Type 1 to enter Manual Mode. Type 2 to enter Automatic Mode. Type 3 to quit.");
			switch (scanner.nextLine()) {
				case "1": 	manualMode = true;
							validInput = true;
							break;
				case "2": 	manualMode = false;
							validInput = true;
							break;
				case "3": 	System.exit(0);
							break;//useless but whatever, looks better
				default: 	System.out.println("Invalid option selected. Please try again.");
			}
		}while(!validInput);
		
		
		int numFloors = (int) (Math.random() * 15 + 1);
		numFloors = numFloors==1 ? 2 : numFloors;//lets make sure there are at least 2 floors in the building
		ArrayList<Floor> floors = new ArrayList<>();//actual list of floors in the building
		for(int j = 0; j < numFloors; j++) {
			Floor floor = new Floor(j+1);
			floors.add(floor);
		}
		int numPeople = (int) (Math.random() * (numFloors*2) + 1);
		ArrayList<Person> people = new ArrayList<>();//actual list of people in the building
		for(int i = 0; i < numPeople; i++) {
			people.add(new Person(floors,i));
		}
		
		
		System.out.println("Building contains " + numFloors + " floors and " + numPeople + " people");
		Elevator elevator = new Elevator(floors);
		
		beginElevatorSim(elevator, numPeople, manualMode, people);
	}
	
	public static void beginElevatorSim(Elevator elevator, int numPeople, boolean manualMode, ArrayList<Person> people) {

		int elevatorMovements = 0;
		Scanner scanner = new Scanner(System.in);
		
		while(elevatorMovements < 25) {
			
			//randomly identify people that are going to push the elevator button
			int maxPeeps = numPeople < 2 ? numPeople : 2;
			int numMovers = (int) (Math.random() * maxPeeps + 1);//don't want too many people pushing the elevator button for now
			for(int x = 0; x < numMovers; x++) {
				Person mover = people.get((int) (Math.random() * numPeople));//select random person that is going to press the elevator button
				if(elevator.getWaitingPersons().contains(mover) || elevator.getPassengers().contains(mover) || mover.isWaiting()) {
					//skip, this person has already pushed the button
				} else {
					mover.setWaitingState(true);
					elevator.addWaitingPerson(mover);
					elevator.addWaitingFloor(mover.getCurrentFloor());				}
			}
			
			
			
			if(manualMode) {//manual mode, allows user to read through text before continuing
				System.out.println("Type yes when ready to continue, or type anything else to quit");
				if(scanner.nextLine().contains("yes")) {
					//continue
				} else {
					break;
				}
			} else {//automatic mode with no user input
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			elevator.move();
			elevatorMovements++;
			System.out.println("\n");
		}
	}

}
