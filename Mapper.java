import java.util.ArrayList;
import java.util.List;

/**
 * class file developed by the Backend
 *
 * @author Han Yu, Foong
 *
 */
public class Mapper implements MapperInterface{

        public String current = "unchartered";// current location in the graph
        private dataReaderBD<String> dR;// Data Wrangler
        public Pathfinder Pf = new Pathfinder();// Algorithm Engineer
        //public List<String> Placedata = Pf.loadFile("Mcdonalds"); // the data integrated into the GPS that should not be modified

        /**
         * hard-coded map
         * 
         * courtesy of the one and only, yours truly, Backend Developer, Han Yu Foong. Hard carrier
         */
        
        public void StoredMap() {
                Pf.loadFile("");
        }

        /**
         * method that finds all possible route to leave from current place
         *
         * @return the string representation of the place, which is the name
         */
        @Override
        public String findNearestplace() {// finding minimum distance from current
                double min = Double.MAX_VALUE ;
                String placeName = " no where to be found ";
                //for(BaseGraph<String, Double>.Edge e: Pf.nodes.get(current).edgesLeaving) {
                //        if(e.data <= min) {
                //                min = e.data; // if next value is smaller then take that value
                //                placeName = e.successor.data;
                //        }
                //}

                Node test = null;
                for (int j = 0; j<Pf.storedData.storedMap.size(); j++) {
                  if (Pf.storedData.storedMap.get(j).locationName.equals(current)) {
                    test = Pf.storedData.storedMap.get(j);
                  }
                }
                for(int i = 0; i < test.neighborNodeDistances.size(); i++) {
                  if(test.neighborNodeDistances.get(i)<min) {
                    min=test.neighborNodeDistances.get(i);
                    placeName = test.neighborNodeNames.get(i);
                  }
                }
                return placeName;

                //PathfinderAE.DG.nodes.get(current).edgesEntering
        }

        /**
         * method that finds the distance of the closest place
         *
         * @return the double representation of distance in minutes(time unit agreed upon by blue team)
         */
        @Override
        public double findNearestplaceDist() {// finding minimum distance from current
            double min = Double.MAX_VALUE ;
            //for(BaseGraph<String, Double>.Edge e: Pf.nodes.get(current).edgesLeaving) {
            //        if(e.data < min) {
            //                min = e.data; // if next value is smaller then take that value
            //        }
            //}
            Node test = null;
            for (int j = 0; j<Pf.storedData.storedMap.size(); j++) {
              if (Pf.storedData.storedMap.get(j).locationName.equals(current)) {
                test = Pf.storedData.storedMap.get(j);
              }
            }
            for(int i = 0; i < test.neighborNodeDistances.size(); i++) {
              if(test.neighborNodeDistances.get(i)<min) {
                min=test.neighborNodeDistances.get(i);
              }
            }
            return min;
    }


    /**
     * method that returns a list of places that exists on the map
     *
     * @return the list of type <String> of places names
     */
    @Override
    public List<String> Placelist() {
            List<String> L = new ArrayList<String>();
            //Pf.nodes.forEach((k, v) -> L.add(k));
            for(Node i : Pf.storedData.storedMap) {
              L.add(i.locationName);
            };
            return L;
    }

    /**
     * method that calls algorithm from AE to find shortest path to a certain place from current position
     *
     * @return the list of places visited in order to shortest path
     */
    @Override
    public List<String> shortestPath(String endNode) {
            return Pf.findNodesFromPath(current, endNode);

    }

    /**
     * method that calls algorithm from AE to find cost of shortest path to a certain place from current position
     *
     * @return the <double> representation of the shortest cost/ time to reach a place
     */
    @Override
    public double shortestPathTime(String endNode) {
            return Pf.findTimeFromPath(current, endNode);
    }

    /**
     * method to update current position when travelling to another place
     *
     */
    @Override
    public void setCurrentPosition(String newPlace) {
            current = newPlace;
    }

    /**
     * method to retrieve the position of user in case they ever forgot where they travelled to
     *
     * @return <String> representation of current position
     */
    @Override
    public String getCurrentPosition() {
            return current;
    }
}
