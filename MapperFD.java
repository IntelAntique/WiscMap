import java.util.ArrayList;
import java.util.List;



public class MapperFD implements MapperInterface{
	String currentPos = "";
	
	@Override
	public String findNearestplace() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<String> Placelist() {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
/*	public List<String> shortestPath(String endNode) {
		ArrayList<String> list = new ArrayList<>();
		list.add("Memorial Union, Weeks Hall for Geological Sciences, Agricultural Hall");
		return list;
	}*/

	@Override
	public double shortestPathTime(String endNode) {
		return 15.0;
	}

	@Override
	public void setCurrentPosition(String currPos) {
		currentPos = currPos;		
	}

	@Override
	public String getCurrentPosition() {
		return currentPos;
	}

	@Override
	public double findNearestplaceDist() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> shortestPath(String end) {
		ArrayList<String> list = new ArrayList<>();
		list.add("Memorial Union, Weeks Hall for Geological Sciences, Agricultural Hall");
		return list;
		
	}

}

