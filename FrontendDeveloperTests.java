// --== CS400 File Header Information ==--
// Name: Aidan Ford
// Email: ford23@wisc.edu
// Group and Team: Group CH, Blue team
// Group TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: 
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

class FrontendDeveloperTests {

	@Test
	void aTest1() {//This test ensures that main menu prompt grabs the specified key selected, In this scenario the letter types is "a"
		Scanner s = new Scanner(System.in);
		MapperFD bd = new MapperFD();
		TextUITester ui = new TextUITester("m\nf\nn");
		CHWiscMapFrontend test = new CHWiscMapFrontend(s,bd);
		test.runCommandLoop();
		String output = ui.checkOutput();
		System.out.println(output);
		assertEquals(true,output.contains("Memorial Union"));
		//assertEquals(true, output.contains("Memorial Union"));
	}
	
	@Test
	void aTest2() {//This test ensures that the shortestPath method is called and executed during command loop of a basic run
		Scanner s = new Scanner(System.in);
		MapperFD bd = new MapperFD();
		@SuppressWarnings("unused")
		TextUITester ui = new TextUITester("m\nf\nn");
		CHWiscMapFrontend test = new CHWiscMapFrontend(s,bd);
		test.runCommandLoop();
		System.out.println(test.getTime());
		double num = test.getTime();
		double key = 15.0;
		assertEquals(num, key);//If expected time comes out, it means that the totalTime variable was updated as it's supposed to
	}
	
	@Test
	void aTest3() {//This test ensures shortestPath and totalTime are updated and working when a stop is added 
		Scanner s = new Scanner(System.in);
		MapperFD bd = new MapperFD();
		@SuppressWarnings("unused")
		TextUITester ui = new TextUITester("m\nf\ny\ng");
		CHWiscMapFrontend test = new CHWiscMapFrontend(s,bd);
		test.runCommandLoop();
		System.out.println(test.getTime());
		double num = test.getTime();
		double key = 30.0;//30 because the time 
		assertEquals(num, key);//If expected time comes out, it means that the totalTime variable was updated twice like it's supposed to
		
	}
	
	@Test
	void aTest4() {//Ensures that the mainMenuPrompt sets the start location for the backend 
		Scanner s = new Scanner(System.in);
		MapperFD bd = new MapperFD();
		@SuppressWarnings("unused")
		TextUITester ui = new TextUITester("m\nf\nn");
		CHWiscMapFrontend test = new CHWiscMapFrontend(s,bd);
		test.runCommandLoop();
		String result = test.getBD().getCurrentPosition();
		assertEquals("m", result);//This would indicate that the start was set to Memorial Union like in the user input
	}
	
	@Test
	void aTest5() {//This test ensures no incorrect input is allowed to be input when choosing a destination
		Scanner s = new Scanner(System.in);
		MapperFD bd = new MapperFD();
		TextUITester ui = new TextUITester("m\nq\nf\nn");
		CHWiscMapFrontend test = new CHWiscMapFrontend(s,bd);
		test.runCommandLoop();//Input will try q, and then a
		String output = ui.checkOutput();
		System.out.println(output);
		assertEquals(true,output.contains("Incorrect letter typed"));//If the string is equal to q, the method didn't properly catch improper input
	}
	
	@Test
	void integrationTest1() {//This test ensures my frontend code communicates with Backend's code through use of getters and setters
		Scanner s = new Scanner(System.in);
		Mapper bdTest = new Mapper();
		@SuppressWarnings("unused")
		TextUITester ui = new TextUITester("a");
		CHWiscMapFrontend fd = new CHWiscMapFrontend(s, bdTest);
		String result = fd.mainMenuPrompt();
		bdTest.setCurrentPosition(result);
		assertEquals(result,bdTest.getCurrentPosition());//If these are equal then that means that the frontend is successfully interfacing with the backend
	}
	
	@Test
	void integrationTest2() {//This integration method tests the use of shortestPath between Frontend and Backend and ensures that the correct numbers are being given
		Scanner s = new Scanner(System.in);
		Mapper bdTest = new Mapper();
		bdTest.StoredMap();
		@SuppressWarnings("unused")
		TextUITester ui = new TextUITester("M\nW\nn");
		CHWiscMapFrontend fd = new CHWiscMapFrontend(s, bdTest);
		fd.runCommandLoop();//Testing location between Memorial Union and Weeks Hall
		double result = fd.totalTime;
		double actual = 7.0;
		assertEquals(result, actual);
	}
	
	@Test
	void codeReviewOfBackendTest1() {
		Mapper bdTest = new Mapper();
		bdTest.StoredMap();
		bdTest.setCurrentPosition("M");
		double result = bdTest.shortestPathTime("W");
		assertEquals(result, 7.0);
	}
	
	@Test
	void codeReviewOfAlgorithmEngineerTest2() {
		Pathfinder pFinder = new Pathfinder();
		pFinder.loadFile("");
		double result = pFinder.findTimeFromPath("M", "W");
		assertEquals(7.0, result);
	}
	public static void main(String[] args) {
	}

}

