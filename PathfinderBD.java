import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

//--== CS400 File Header Information ==--
//Name: Han Yu Foong
//Email: hfoong@wisc.edu
//Group and Team: CH Blue
//Group TA: Karan Grover
//Lecturer: Florian Heimerl
//Notes to Grader: my team's code is hard to understand

//AE class

public class PathfinderBD<Place, N extends Number>
extends DijkstraGraph<Place, N>{

//public Hashtable<Place, BaseGraph<Place, N>.Node> nodesBD = nodes;

public List<Place> loadFile(String filename) {
        List<Place> L = new ArrayList<>();
        L.add((Place)filename);
        return L;
}

public List<Place> findNodesFromPath(Place startNode, Place endNode) {
        List<Place> L = new ArrayList<>();
        L.add(startNode);
        L.add(endNode);
        return L;
}

public Double findTimeFromPath(Place startNode, Place endNode) {
        return 0.0;
}


}


