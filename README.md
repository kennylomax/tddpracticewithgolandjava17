# Test Driven Development Workshop with Ken Lomax 

Test Driven Development claims to : 
* **Reduce Hacking** & going down **Rabbit Holes**
* Give Clear guidelines for **"What should I do next"**
* Give Clear **status report for the Team**
* Reducte **debug time**
* Give very high **test coverage** ~90%
* Give Complete, reliable, feature/API-level **documentation**
* Give Improved decoupled testable **design**
* Give Code that is always **working a minute ago**
* Allow **fearless refactoring** of code to **avoid code rot**

In short it is important stuff, that should be in your skill set :)

# Example in action
- git clone https://github.com/kennylomax/tddpracticewithgolandjava17.git
- cd tddpracticewithgolandjava17
- To run: mvn spring-boot:run
- To run tests:
  - mvn test, or
  - java -jar junit-platform-console-standalone-1.8.0-M1.jar -cp target/test-classes -cp target/classes --scan-classpath

You will see 100% Test Coverage
<img width="927" alt="codecov" src="https://user-images.githubusercontent.com/6401254/154978731-3702e708-6e06-4a70-85be-745148cc6c22.png">

and a really nice summary of the behaviour of the component under test:
<img width="668" alt="Screenshot 2022-02-21 at 15 44 34" src="https://user-images.githubusercontent.com/6401254/154977325-f758c06c-50a3-4b20-8a73-be612b6d91cc.png">

## To see how this was developed with BDD and TDD..

- Review checkout history: git log --oneline
<img width="507" alt="Screenshot 2022-02-21 at 15 47 37" src="https://user-images.githubusercontent.com/6401254/154977884-f2af9157-eac1-4a3b-8ad0-01a2e1b42dc4.png">

- Select a checkout: git checkout <tagId>
- View the code at that checkout
- Run tests with :
  - mvn test (to compile, and then)
  - java -jar junit-platform-console-standalone-1.8.0-M1.jar -cp target/test-classes -cp target/classes --scan-classpath
- See test coverage at:  target/site/jacoco/index.html
- To get back to the Head and try out a later commit: git checkout - 

## Need Help setting up a Development Environment and Getting Started?
Participants can use which ever environment and language they prefer. If you want a Java environment and are unclear how to get going you can :

- Install JDK 17
- Download Visual Studio Code (https://code.visualstudio.com/) and beef it up with a couple of extension packs:
  - Install the Java+Spring Visual Studio Code Extension Pack into your Visual Studio Code (https://marketplace.visualstudio.com/items?itemName=loiane.java-spring-extension-pack)
  - Install the Coverage Gutters Visual Studio Code Extension Pack into your Visual Studio Code (https://marketplace.visualstudio.com/items?itemName=ryanluker.vscode-coverage-gutters)
- Create a new Spring App with Java 17
  - go to  start.spring.io and download a plain Spring App: (https://start.spring.io/)
  - unzip the Spring App and open it in Visual Studio Code
- Enable Test Coverage stats:
  - add the JaCoCo plugin to the existing list of plugins in your pom.xml's build tag.
```
  <plugin>
      <groupId>org.jacoco</groupId>
      <artifactId>jacoco-maven-plugin</artifactId>
      <version>0.8.7</version>
      <executions>
          <execution>
              <goals>
                  <goal>prepare-agent</goal>
              </goals>
          </execution>
          <!-- attached to Maven test phase -->
          <execution>
              <id>report</id>
              <phase>test</phase>
              <goals>
                  <goal>report</goal>
              </goals>
          </execution>
      </executions>
  </plugin>
  ```
  - Run mvn jacoco:prepare-agent test install jacoco:report in your Visual Studio Code's Terminal Window to activate this plugin
  - Click Watch in Visual Studio Code, to activate the Show gutters plugin
- The Junit CLI provides nicely formatted test output on the command line. To install it run:
  - curl https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.8.0-M1/junit-platform-console-standalone-1.8.0-M1.jar > junit-platform-console-standalone-1.8.0-M1.jar
  - chmod 700 junit-platform-console-standalone-1.8.0-M1.jar
- Try a TDD cycle: Run your Tests, See Test Coverage, Fix Tests and Repeat
  - Run your tests: 
    - either with mvn test
    - or with java -jar junit-platform-console-standalone-1.8.0-M1.jar -cp target/test-classes -cp target/classes --scan-classpath
  - See your test coverage: open  target/site/jacoco/index.html  in a web browser.
- Start TDDing! 
  
# Explanation of Game Of Life Rules
We will Demo benefits of TDD, using a controlled and known problem space: Game of Life.  Example code is @ Github.

Game of Life features a matrix of live and dead cells..
  <img width="139" alt="Screenshot 2022-02-21 at 15 45 16" src="https://user-images.githubusercontent.com/6401254/154978947-232b06b0-ffca-4320-9f1b-407fd67d20f5.png">

  
.. that evolve by following a few simple rules: 
- Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
- Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
- Any live cell with two or three live neighbours lives on to the next generation.
- Any live cell with more than three live neighbours dies, as if by overpopulation.
- So the matrix above evolves to the matrix below.
<img width="132" alt="Screenshot 2022-02-21 at 15 45 24" src="https://user-images.githubusercontent.com/6401254/154978967-7b8bd6c6-ad6c-46ac-bec4-dfe1dc03b366.png">

  
Our task is to develop this game with minimal hacking, good test coverage and good intermediate status reports. Here are the initial requirements following a discussion with the PO:

- To talk about cells, we will use coordinate system,

  <img width="189" alt="Screenshot 2022-02-21 at 15 45 34" src="https://user-images.githubusercontent.com/6401254/154979147-351d7550-9734-4a2e-8378-f75cc413e5eb.png">
  
- How to display in console?
0-0-0-0-0
0-0-0-0-0
0-1-1-1-0
0-0-0-0-0
0-0-0-0-0
- How to populate?
  - Let us have some flexibility
    - 00000 00000 01110 00000 00000
    - - - 01110 - -
    - 0000000000011100000000000
- What happens at the edges?
  - we will wrap around
  
