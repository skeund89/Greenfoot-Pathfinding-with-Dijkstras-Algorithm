# Rover Pathfinding with Dijkstra's Algorithm

## Introduction
This project showcases the implementation of Dijkstra's Algorithm for pathfinding in the Greenfoot environment. Created during my computer science lessons at school, the project utilizes node and edge classes to simulate a rover navigating through a grid. The goal is to find the shortest path from a specified starting point to a target endpoint while avoiding obstacles.

## Table of Contents
1. [Installation](#installation)
2. [Folder Setup](#folder-setup)
3. [Executing the Project](#executing-the-project)
4. [Project Method Overview](#project-method-overview)

## Installation
To run this project locally, follow these steps:
1. Clone this repository to your local machine using Git:
   ```bash
   git clone https://github.com/skeund89/Greenfoot-Pathfinding-with-Dijkstras-Algorithm.git
   ```

## Folder Setup
After cloning the repository, set up the project in Greenfoot:
1. Launch Greenfoot and create a new scenario.
2. Import the `Project 2.0` folder into your Greenfoot project workspace.

## Executing the Project
1. Open the Greenfoot IDE and load the project (`Project 2.0`) into the workspace.
2. Locate the `Rover` class and ensure it is set as the main class for execution.
3. Configure the initial parameters (`endpos_x`, `endpos_y`) within the `Rover` class's `Projekt` method.
4. Run the project to observe the rover's pathfinding behavior.

## Project Method Overview
The main method in this project is `Projekt(int endpos_x, int endpos_y)` within the `Rover` class:
- **Initialization**: Initializes the grid world and places the start and end nodes.
- **Mapping**: Maps the terrain using the rover's movement capabilities.
- **Pathfinding**: Uses Dijkstra's algorithm to find the shortest path from the start to the end node.
- **Navigation**: Guides the rover along the shortest path found, updating the map with the optimal route.

This project showcases how Dijkstra's algorithm can be applied to solve pathfinding problems in a simulated environment.