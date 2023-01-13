[![Build Status](https://api.travis-ci.com/LetteraUnica/SDM-final-project.svg?branch=main)](https://api.travis-ci.com/LetteraUnica/SDM-final-project)

# Take it Easy

## What is this?
This is an implementation of the the board game "Take it Easy" by Peter Burley in the Java programming language, created as part of the Software Development Methods course at the University of Trieste, Italy.
The project is intended for educational purposes only and no copyright infringement is intended.
The authors of the project are Lorenzo Cavuoti, Marco Sicklinger and Marco Sciorilli.

## Game rules:

1. On each turn, select one of the 19 empty hexagonal slots to place a tile by clicking on the slot and confirming with the "Place Tile" button.
2. Once a tile is placed, its location cannot be changed and the next player will place the same tile.
3. This process continues until all players have placed the tile, then a new tile is drawn from a set of 27 possible tiles.
4. This cycle repeats until all slots on the hexagonal board are filled. Once all slots are filled, the game ends and the player with the highest number of points is declared the winner.

### Calculating points:

1. Points are earned by aligning tiles with the same value on the line that corresponds to their orientation.
2. When all tiles in a line have the same number, the line is worth that number multiplied by the number of tiles on the line.


## In-game controls

To make the game as user-friendly as possible, we've kept the user interface simple and intuitive. Here's a quick overview of the controls.

### Starting a Game

When you launch the application, you can start a new game by clicking the "Start Game" button in the game menu. This will take you to the lobby, where you can add or remove players.


### Adding Players

You can add new players by typing their name into the form in the top right corner. As soon as you start typing, the "Add Player" button will light up, indicating that you can add that player. Note that you cannot add two players with the same name; if you try, the "Add Player" button will be disabled. Once you've added at least one player, the "Start Match" button will light up, indicating that you can begin the game.

### Removing Players

You can remove players by clicking the "Remove" button next to their name. The "Remove" button will only appear once you've added a player to the player list.

Once you've added all the players you want, you can start the match with the "Start Match" button.

### Placing Tiles

During your turn, you can place a tile in any available slot (indicated by a light blue color) by clicking on it with your mouse. Confirm the placement by clicking the "Place Tile" button. You can also view the boards of other players by clicking the "View" button on the left side of the screen. To return to your own board, click the "Return" button on the right side.
Win Screen

Once all players have filled their boards with tiles, a victory screen will be displayed. Here, you can see the winner, start a new game, or return to the main menu.


## Development details
After an initial exploration phase carried out by all group members we decided to follow the MVC design pattern and started laying out the main component of the application, we then splitted the work evenly between the three of us:
* Lorenzo Cavuoti was responsible for the UI development, which was carried out using JavaFX and SceneBuilder.
* Marco Sicklinger worked on the Controller
* Marco Sciorilli worked on the model

Throuought the project weekly meetings were performed in order to check on each-other development and see if any of us needed help in case the assigned tasks were longer than expected. 

Both the model and controller were developed using a test-driven approach, while the UI was tested manually.

The whole project was written in Java 17 and uses gradle version 7.4 for setting up the build. We also used travis CI for build automation, however we only used it in the last phase of the project as the free trial only lasts one month.

The development was mainly done in IntelliJ IDEA on both Linux and Windows machines, and using github for version control. 


## Development Overview

After conducting an initial exploration phase as a team, we chose to implement the MVC design pattern for our application. We then divided the work evenly among the group members:

* Lorenzo Cavuoti was responsible for UI development using JavaFX and SceneBuilder.
* Marco Sicklinger focused on the controller.
* Marco Sciorilli worked on the model.

To ensure effective collaboration and progress tracking, we held weekly meetings throughout the project. We also implemented a test-driven approach for the model and controller, while the UI was tested manually.

The project was written in Java 17 and built using gradle version 7.4. We also utilized Travis CI for build automation in the final phase of the project, as the free version only lasts one month. Development was primarily done using IntelliJ IDEA on both Linux and Windows machines, and version control was managed through Github.

### Dependencies
* Java 17.0.5
* Gradle 7.4
* gson 2.10
* junit 5.9.0
* javafx 17.0.2


## Architecture
To write


## References
Take it easy rules: https://en.wikipedia.org/wiki/Take_It_Easy_(game)
