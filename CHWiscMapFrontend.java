// --== CS400 File Header Information ==--
// Name: Aidan Ford
// Email: ford23@wisc.edu
// Group and Team: Group CH, Blue team
// Group TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: 
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CHWiscMapFrontend implements CHWiscMapFrontendInterface {

    private MapperInterface bd;
    ArrayList<String> stops = new ArrayList<>();
    double totalTime = 0;
    Scanner s;

    public CHWiscMapFrontend(Scanner userInput, MapperInterface backend) {
        s = new Scanner(System.in);
        bd = backend;
    }

    @Override
    public void runCommandLoop() {
        System.out.println("Welcome to the WiscMap! \nYou need to select a starting point");
        String start = mainMenuPrompt(); // Displays menu and grabs starting location
        bd.setCurrentPosition(start);
        String end = mainMenuPrompt();// Gets user to select an end destination
        findPath(end);// Prints out time it takes to get to end location
        List<String> paths = bd.shortestPath(end);
        for (String stop : paths) {
            stops.add(stop);
        } // Adds all stops from pt1 to pt2 to a list

        boolean check = true;
        while (check) {
            System.out.println("Would you like to add a stop? (y/n)");
            String key = s.nextLine();
            if (key.equalsIgnoreCase("y")) {// If user wants to add a stop, prompt for which location, set bd currentpos
                                            // to previous end, and finish after adding the stop
                String toAdd = mainMenuPrompt();
                bd.setCurrentPosition(end);
                addStop(toAdd);
                break;
            }
            if (key.equalsIgnoreCase("n")) {
                String result = "[";
                for (String stop : stops) {
                    result += stop;
                    if (stops.get(0).equals(stop)) {
                        result += "-->";
                    }
                }
                result += "]";
                System.out.println(result);// Displays list of locations needed to reach traverse in order to get to
                                            // final destination
                check = false;// ends loops
            } else {
                System.out.println("Sorry, that key wasn't an option, try again");// In case an incorrect key is pushed
            }
        }
        System.out.println("Thanks for using our WiscMap!");
    }

    @Override
    public String mainMenuPrompt() {
        String end = "";
        while ((!(end.equalsIgnoreCase("m"))) || !(end.equalsIgnoreCase("m1")) || (!(end.equalsIgnoreCase("g")))
                || (!(end.equalsIgnoreCase("w"))) || (!(end.equalsIgnoreCase("f"))) || (!(end.equalsIgnoreCase("c")))
                || (!(end.equalsIgnoreCase("s"))) || (!(end.equalsIgnoreCase("b"))) || (!(end.equalsIgnoreCase("a")))) {
            System.out.println("Please select a destination:");
            System.out.println(
                    "[M]emorial Union\n[M1]oss Humanities Building\n[G]ordon's Market\n[W]eeks Hall for Geological Sciences\n"
                            + "[F]our Lakes Dining Hall");
            end = s.nextLine();// Displaying menu and retrieving and end location
            if (end.equalsIgnoreCase("m") || end.equalsIgnoreCase("m1") || end.equalsIgnoreCase("g")
                    || end.equalsIgnoreCase("w") || end.equalsIgnoreCase("f") ) {
                return end;
            } else {
                System.out.println("Incorrect letter typed");
            }
        }
        return end;
    }

    @Override
    public void loadDataCommand() {// Questioning if we are even using this.

    }

    @Override
    public void findPath(String location) {
        double time = bd.shortestPathTime(location);
        totalTime += time;
        System.out.println("The shortest route between these two locations will take " + time + " minutes.");

    }

    @Override
    public void addStop(String location) {
        List<String> extra = bd.shortestPath(location);
        for (String locations : extra) {
            stops.add(locations);
        }
        totalTime += bd.shortestPathTime(location);
        System.out.println("Your total trip will take " + totalTime + " minutes.");
        String result = "[";
        for (int i = 0; i < stops.size(); i++) {
            result += stops.get(i);
            if (i == 1) {
                result += "]  [";
            }
        }
        result += "]";
        System.out.println(result);
    }

    @Override
    public void returnMST() {
        System.out.println(bd.Placelist());

    }

    public void closeScan() {
        s.close();
    }

    public double getTime() {
        return totalTime;
    }

    public MapperInterface getBD() {
        return bd;
    }

}

