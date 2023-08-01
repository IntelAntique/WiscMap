run:
	make runAlgorithmEngineerTests 
	make runBackendDeveloperTests
	make runFrontendDeveloperTests
	make clean

runFrontendDeveloperTests:
	javac MapperFD.java
	javac CHWiscMapFrontend.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java
	java -jar junit5.jar -cp . --select-class=FrontendDeveloperTests

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java    
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

AlgorithmEngineerTests.java:
	javac DataReaderInterface.java
	javac DataReaderAE.java
	javac PathfinderInterface.java
	javac PathfinderAE.java
	javac NodeInterface.java
	javac NodeAE.java
	javac AlgorithmEngineerTests.java
runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

BackendDeveloperTests.class: PlaceBD.java PathfinderBD.java DijkstraGraph.java Mapper.java dataReaderBD.java
	javac -cp .:junit5.jar BackendDeveloperTests.java

PlaceBD.java:
	javac -cp .:junit5.jar PlaceBD.java

#Algorithm Engineer
PathfinderBD.java:
	javac -cp .:junit5.jar PathfinderBD.java

DijkstraGraph.java:
	javac -cp .:junit5.jar DijkstraGraph.java

#MyBackEnd
Mapper.java:
	javac -cp .:junit5.jar Mapper.java

#Data Wrangler
dataReaderBD.java:
	javac -cp .:junit5.jar dataReaderBD.java

clean:
	rm *.class
