# Documentation

Before running, please ensure you have JRE set up on your computer. If not, Follow this link to [setup JRE](https://docs.oracle.com/goldengate/1212/gg-winux/GDRAD/java.htm#BGBFJHAB).

### Run unit test

##### On Mac

1. Open Terminal
2. Change directory to Solution build folder. Easiest way would be
* In Solution folder, navigate to **build | classes | production | Solution**
* In terminal, type "cd" followed by a space
* Drag the Solution folder unto the terminal screen. You should now have
```C
cd <PATH TO SOLUTION FOLDER>
```
* Click enter
3. Run
```C
java -cp .:../../../../.idea/modules/lib/junit-4.12.jar:../../../../.idea/modules/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.TestSuite
```

##### On Windows

1. In Solution folder, navigate to **build | classes | production | Solution**
2. Right-click on Solution folder
3. Select "open command window here"
4. On the command prompt, run
```C
java -cp .:../../../../.idea/modules/lib/junit-4.12.jar:../../../../.idea/modules/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.TestSuite
```

### Build executable .jar file

1. From the Solution directory, run
```C
jar -cvfm Solution.jar ../../../../src/META-INF/manifest.MF com*
```
2. This would create a TaxiLicenceCal.jar file in the directory.

### Run .jar file

From the Solution directory, run
```C
java -jar Solution.jar
```