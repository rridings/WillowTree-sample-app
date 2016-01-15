# WillowTree-sample-app
WillowTree sample app

Sample_App.pdf includes Architecture and Design description, API definitions and Stress
Test results and analysis.

Running the application  
1. Make sure Java 1.8 is the your path or modify run.sh to point a specific java install.  
2. Make sure sample-app-1.0.0.one-jar.jar is in the same location as the run.sh.  
3. Execute run.sh - ./run.sh  

Notes
* The sample jar is package use the one-jar plugin for maven.  Its contains all its dependencies.
* The run script launches the application in the foreground.  If you want to run it in the background add & to the end of the line.
To stop the background you will have to use something like KILL.  The is no shutdown command built into the application.
