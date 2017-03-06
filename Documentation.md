# Documentation

Before running, please ensure you have JRE set up on your computer. If not, follow this link to [setup JRE](https://docs.oracle.com/goldengate/1212/gg-winux/GDRAD/java.htm#BGBFJHAB).

### Run unit test

##### On Mac

1. From Solution folder, navigate to **build | classes | production**, where you'll find another Solution folder.
2. Open Terminal and type "cd" followed by a space.
3. Drag second Solution folder unto the terminal screen. You should now have
```C
cd <PATH TO SECOND SOLUTION FOLDER>
```
4. Click enter and run
```C
java -cp .:../../../../.idea/modules/lib/junit-4.12.jar:../../../../.idea/modules/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.TestSuite
```

##### On Windows

1. From Solution folder, navigate to **build | classes | production**, where you'll find another Solution folder.
2. Hold Shift and right-click on second Solution folder
3. Select "open command window here"
4. On the command prompt, run
```C
java -cp .;../../../../.idea/modules/lib/junit-4.12.jar;../../../../.idea/modules/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.TestSuite
```

### Build executable .jar file

1. While still in the Solution directory path, run
```C
jar -cvfm Solution.jar ../../../../src/META-INF/manifest.MF com*
```
2. This would create a Solution.jar file in the directory.

### Run .jar file

1. While still in the Solution directory path, run
```C
java -jar Solution.jar
```