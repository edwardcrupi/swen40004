/**
**
	Cell.java
	The Cell class for the system replicating the Ethnocentrism
	model used for Assignment 1 of SWEN40004 - Modelling Complex Software
	Systems

	Edward Crupi 538156 & Naser Soueid 359161

	@Instance Variables
	x				- The horizontal position of the cell within the grid
	y 				- The vertical position of the cell within the grid
	occupyingAgent 	- The instance of Agent that may be occupying the cell (can be null)
**
*/
public class Cell {
	public int x, y;
	private Agent occupyingAgent;

	/*
	**
		(get,set)OccupyingAgent
		Behaviour:
			The getters and setter for the private occupyingAgent instance
		Assumes:
			-
	**
	*/
	public Agent getOccupyingAgent() {
		return occupyingAgent;
	}

	public void setOccupyingAgent(Agent occupyingAgent) {
		this.occupyingAgent = occupyingAgent;
	}

	
	/*
	**
		Cell
		Behaviour:
			The Cell constructor method for an occupied cell
		Assumes:
			newX, newY, newOccupyingAgent are non-null
	**
	*/
	public Cell (int newX, int newY, Agent newOccupyingAgent) {
		x = newX;
		y = newY;
		occupyingAgent = newOccupyingAgent;
	}

	/*
	**
		Cell
		Behaviour:
			The Cell constructor method for an unoccupied cell
		Assumes:
			newX, newY
	**
	*/
	public Cell (int newX, int newY){
		x = newX;
		y = newY;
		occupyingAgent = null;
	}

	/*
	**
		printCell
		Behaviour:
			Prints the 'color' of the current cell's occupier
		Assumes:
			this is not null
	**
	*/
	public Cell printCell(){
		if(this.occupyingAgent == null ){
			System.out.print(". ");
		}
		else if (this.occupyingAgent != null){
			System.out.print(this.occupyingAgent.colour.toString().substring(0,1)+" ");
		}
		return this;
	}


	/*
	**
		update
		Behaviour:
			Passes control to the occupyingAgent for updating
		Assumes:
			grid is not null
	**
	*/
	public Cell update(Grid grid){
		if(this.occupyingAgent != null)
			this.occupyingAgent.update(grid);
		return this;
	}
}