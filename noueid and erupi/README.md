# SWEN40004 - Assignment 1
# Naser Soueid and Edward Crupi

# Overview
This is our replication of a Cellular Automaton system that mimics 'Ethnocentricism' in a population.

# How to Use
Just compile the `CellularAutomaton.java` file using javac or your favourite java compiler then run `CellularAutomaton` specifying arguments for width, height, how many ticks to run for, whether or not to initially seed the grid and whether updating should be stochastic or not.

E.G:

```java
javac CellularAutomaton.java
java CellularAutomaton 5 5 10 true false
```
Will run the model on a 5x5 matrix for 10 ticks, initialized to capacity with deterministic updating. 
