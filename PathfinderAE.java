import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class PathfinderAE extends DijkstraGraph<String, Double> implements PathfinderInterface {
    public DataReaderAE storedData = new DataReaderAE();
    public void loadFile (String filename){
      storedData.readData(filename);
    }
    
    //nearly identical node to the search node protected class from last week
    protected class SearchNode implements Comparable<SearchNode> {
        public NodeAE node;
        public double cost;
        public SearchNode predecessor;
        public SearchNode(NodeAE node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }
        public int compareTo(SearchNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
    }
    
    //dijkstra algorithm which returns a SearchNode like in the previous week, but has no edge class and uses different nodes as shown in our proposoal doc
    //it's pretty ugly but i was tired when making it so
    protected SearchNode dijkstraComputeHelper(NodeAE startNode, NodeAE endNode){
      if(!storedData.storedMap.contains(startNode)||!storedData.storedMap.contains(endNode)) {
        return(null);
      }
      PriorityQueue<SearchNode> openNodes = new PriorityQueue<>();
      Hashtable<NodeInterface,SearchNode> seenNodes = new Hashtable();
      SearchNode startSN = new SearchNode(startNode, 0.0, null);
      openNodes.add(startSN);
      while (!openNodes.isEmpty()) {
        SearchNode currentSN = openNodes.poll();
        if (currentSN.node.locationName.equals(endNode.locationName)) {
          return(currentSN);
        }
        for (int i = 0; i<currentSN.node.neighborNodeNames.size();i++) {
          String neighborName = currentSN.node.neighborNodeNames.get(i);
          NodeAE neighborN = null;
          for (int j = 0; j<storedData.storedMap.size(); j++) {
            if (storedData.storedMap.get(j).locationName.equals(neighborName)) {
              neighborN = storedData.storedMap.get(j);
            }
          }
          double gValue = currentSN.node.neighborNodeDistances.get(i);
          SearchNode neighborSN = null;
          if (!seenNodes.containsKey(currentSN.node.neighborNodeNames.get(i))) {
            neighborSN = new SearchNode(neighborN,Double.POSITIVE_INFINITY,currentSN);
            seenNodes.put(neighborN, neighborSN);
          } else {
            neighborSN = seenNodes.get(neighborN);
          }
          double newGValue = currentSN.cost+gValue;
          if (newGValue < neighborSN.cost) {
            openNodes.remove(neighborSN);
            neighborSN.cost = newGValue;
            neighborSN.predecessor = currentSN;
            openNodes.add(neighborSN);
          }
        }
      }
      return(null);
    }
    
    //finds nodes from a path between the two provided
    public List<NodeAE> findNodesFromPath(NodeAE startNode, NodeAE endNode) {
        SearchNode currentSN = dijkstraComputeHelper(startNode,endNode);
        LinkedList<NodeAE> rList = new LinkedList();
        while(currentSN!=null) {
          rList.addFirst(currentSN.node);
          currentSN = currentSN.predecessor;
        }
        return rList;
    }
    //finds the time in minutes between two provided nodes
    public Double findTimeFromPath(NodeAE startNode, NodeAE endNode){
      SearchNode currentSN = dijkstraComputeHelper(startNode,endNode);
      return currentSN.cost;
    }
}