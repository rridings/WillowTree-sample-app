# WillowTree-sample-app

Sample_App.pdf includes Architecture and Design description, API definitions and Stress
Test results and analysis.

**Compiling**  
The project is a maven project.  Clone the respository and run mvn package.  The jar is included in the repository if there is trouble compiling it.

**Running the application**   
1. Make sure Java 1.8 is the your path or modify run.sh to point a specific java install.  
2. Make sure sample-app-1.0.0.one-jar.jar is in the same location as the run.sh.  
3. Execute run.sh - ./run.sh  

*Notes*
* The sample jar is package use the one-jar plugin for maven.  Its contains all its dependencies.
* The run script launches the application in the foreground.  If you want to run it in the background add & to the end of the line.
To stop the background you will have to use something like KILL.  The is no shutdown command built into the application.

**Integration and Unit Testing**
In the src-test directory are JUnit tests the exercise each component, AssetController, AssetService and AssetDAOMemoryImpl.  Each test fires up the server, runs the test, and shuts down the server.  When running the tests, make sure the Sample App server is not running. Maven also runs these tests during packaging.

**Stress Testing**  

There are two scripts, stress-read.sh and stress-write.sh.  They are using Apache Bench to call the sample application.
