// --== CS400 File Header Information ==--
// Name: Han Yu Foong
// Email: hfoong@wisc.edu
// Group and Team: CH Blue
// Group TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: my team's code is hard to understand

import java.util.List;

public interface MapperInterface {
	//public CHSearchBackend()
	public String findNearestplace() ;
	// returns the nearest node to the current place in a String format: “Place: time”
	public double findNearestplaceDist();
	public List<String> Placelist();
	//returns all the type of places (list of all places on the map
	public List<String> shortestPath(String end);
	//returns a list with the shortest path possible for 2 nodes in the map
	public double shortestPathTime(String end);
	//returns a list with the shortest path time possible for 2 nodes in the map
	public void setCurrentPosition(String newPlace);
	// assigns one of the locations on the map as the current location
	public String getCurrentPosition();
	// returns the string representation of the place’s name such as Mcdonalds(Chicago)
}
