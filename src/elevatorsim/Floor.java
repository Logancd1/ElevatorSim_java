package elevatorsim;

public class Floor {

	private int floorID;
	private String floorName;
	
	public Floor(int floor) {
		setFloorID(floor);
		setFloorName("Floor " + floor);
	}
	
	public Floor(int floor, String name) {
		setFloorName(name);
		//no floor id for the elevator
	}

	public int getFloorID() {
		return floorID;
	}

	private void setFloorID(int floor) {
		floorID = floor;
	}

	public String getFloorName() {
		return floorName;
	}

	private void setFloorName(String floor) {
		floorName = floor;
	}
	
	public String toString() {
		return getFloorName();
	}
	
}
