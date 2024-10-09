# Tennis Game
This is small project to simulate a simple tennis game.

Technologies used :
* Java 17
* Spring boot
* Jacoco for code coverage

## Build
Requirements :
* JDK 17
* Maven

Build with tests validation : 
```shell
$ mvn install
```

## Run the project from local build
Once the project is build then the tennis game is runnable :
````shell
$  java -jar ./target/tennis-0.0.1.jar <GAME_POINTS>
````

Examples :
````shell
$  java -jar ./target/tennis-0.0.1.jar AABAA
$  java -jar ./target/tennis-0.0.1.jar BABABAAA
````
