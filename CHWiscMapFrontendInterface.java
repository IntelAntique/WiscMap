public interface CHWiscMapFrontendInterface {
	//public CHWiscMapFrontendXX(Scanner userInput, MapperInterface backend);

	public void runCommandLoop();
	public String mainMenuPrompt();
	public void loadDataCommand();
	public void findPath(String location);
	public void addStop(String location); 
	public void returnMST(); 

}
