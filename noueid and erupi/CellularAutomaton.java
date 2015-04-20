/**
**
	CellularAutomaton.java
	The Cellular Automaton (CA) class for the system replicating the Ethnocentrism
	model used for Assignment 1 of SWEN40004 - Modelling Complex Software
	Systems

	Edward Crupi 538156 & Naser Soueid 359161

	@@Class Variables:
	grid 			- The instance of Grid that contain the cells for this automaton
	costOfGiving 	- The cost of giving help between two interacting agents
	ptr 			- Potential to Reproduce for an existing Agent
	dr 				- The death rate of all the existing Agents
	maxImmigrants	- The maximum number of immigrants per tick
	mutationRate	- The rate of mutation for all existing Agents
	probImmigrant	- The probability of immigration of an Agent to the grid 

	@Instance Variables
	width 			- The width of the grid (in Cells)
	height			- The height of the grid (in Cells)
	ticks			- The number of ticks the CA will run for
	fill			- A boolean value determining whether or not to fill the
						grid with Agents
	stochUpdate		- A boolean value determining whether or not to
						stochastically update the grid's Cell's.
**
*/
public class CellularAutomaton {
	public static Grid grid;

	public final static double costOfGiving = 0.01;
	public final static double benefitOfReceiving = 0.01;
	public final static double ptr = 0.01; //probability of reproducing per tick
	public final static double dr = 0.1;	//probability of death per tick
	public final static int maxImmigrants = 250;//per tick
	public final static double mutationRate = 0.005;
	
	/*
	**
		Set Probability of spawning immigrant = to death rate.
		Will work fine except where the grid STARTS empty and the updateing
		is done deterministically, in which case 
		most immigrants will appear at the top of the grid given relatively
		low maxImmigrants value or deathRate. Therefore if the grid starts
		empty, set Stochastic updating to true.
	**
	*/

	//If you plan on using deterministic updating, uncomment this.
	//public final static double probImmigrant = dr;
	//otherwise
	public final static double probImmigrant = 1;	

	/*
	**
		main
		Behaviour:
			The main method in which the entire system shall run
		Assumes:
			args.size() == 5
			No element of args is null
	**
	*/
	public static void main(String[] args){
		
		//Initial Seed from Command Line
		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		int ticks = Integer.parseInt(args[2]);
 	
		boolean fill;
		boolean stochUpdate;
		fill = args[3].equals("true") ? true : false;
		stochUpdate = args[4].equals("true") ? true : false;

		grid = new Grid(width,height,fill);
		
		//Update Loop
		//System.out.println("t = 0");
		//grid.printGrid();
		//grid.printStats();

		if(stochUpdate) {
				for(int i = 0; i < ticks; i++){
					//System.out.println("t = " + (i+1));
					grid.stochUpdate();
					//grid.printGrid();
					grid.printStats();
				}
		} else {
			for(int i = 0; i < ticks; i++){
				//System.out.println("t = " + (i+1));
				grid.update();
				//grid.printGrid();
				grid.printStats();
			}
		}
		System.out.println("Done.");
	}
}
