package elevatorsim;

public class Floor {

	private static int floorID;
	private static String floorName;
	
	public Floor(int floor) {
		setFloorID(floorID);
	}
	
	public Floor(String name) {
		setFloorName(name);
	}

	public static int getFloorID() {
		return floorID;
	}

	public static void setFloorID(int floorID) {
		Floor.floorID = floorID;
	}

	public static String getFloorName() {
		return floorName;
	}

	public static void setFloorName(String floorName) {
		Floor.floorName = floorName;
	}
	
}
