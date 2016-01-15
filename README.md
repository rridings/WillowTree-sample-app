# WillowTree-sample-app

Sample_App.pdf includes Architecture and Design description, API definitions and Stress
Test results and analysis.

**Compiling**  
The project is a maven project.  Clone the respository and run mvn package.  The jar is included in the repository, if there is trouble compiling it.

**Running the application**   
1. Make sure Java 1.8 is in the your path or modify run.sh to point a specific java install.  
2. Make sure sample-app-1.0.0.one-jar.jar is in the same location as the run.sh.  
3. Execute run.sh - ./run.sh  

*Notes*
* The sample jar is packaged using the one-jar plugin for maven.  It contains all its dependencies.
* The running script launches the application in the foreground.  If you want to run it in the background, add & to the end of the line.
To stop the background proces, you will have to use something like KILL.  There is no shutdown command built into the application.

**Integration and Unit Testing**  
In the src/test directory are JUnit tests that exercise each component; AssetController, AssetService and AssetDAOMemoryImpl.  Each test fires up the server, runs the test, and shuts down the server.  When running the tests, make sure the server is not running. Maven also runs these tests during packaging.

**Stress Testing**  
There are two stress test scripts, stress-read.sh and stress-write.sh.  They are using Apache Bench to call the sample application.  In the scripts change the path to Apache Bench (ab) to your local installation.
