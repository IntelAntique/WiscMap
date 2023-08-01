import java.util.Scanner;
/**
 * Main entry point for starting and running the WiscMap App.
 *
 * @author BackendDeveloper, courtesy of the Han Yu Foong.
 */
public class WiscMapApp {
        public static void main(String[] args) {
            Scanner s = new Scanner(System.in);
            Mapper backend = new Mapper(); // backend's object
            backend.StoredMap(); // backend initiates map for GPS campus app
            // Use algorithm engineer's code to store and search for data

            // Use front end's main interface
            CHWiscMapFrontend App = new CHWiscMapFrontend(s, backend);
            App.runCommandLoop();

             
                
        }
}

