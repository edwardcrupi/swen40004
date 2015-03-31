import java.util.*;

public class Grid {
	int width, height, CC, CD, DD, DC;
	public static Cell[][] cell;
	
	//Generates a grid
	public Grid (int newWidth, int newLength, boolean filled)
	{
		width = newWidth; //this is actually the length/height
		height = newLength; //this is actually the width
		cell = new Cell[height][width];
		for(int y = 0; y < height; y ++){
			for(int x = 0; x < width; x++){
				if (filled){
					//populates the grid with agents of random 'color(race)'
					// the (Math.random < 0.5) generates a random boolean for cooperation.
					cell[y][x] = new Cell(x,y, new Agent(Agent.Colour.getRandom(),(Math.random() < 0.5), (Math.random() < 0.5), 
								x, y));
				}
				else if (!filled){
					//sets up an empty grid
					cell[y][x] = new Cell(x,y);
				}
			}
		}
	}

	public Grid printGrid(){
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				cell[y][x].printCell();
				/*if(y == this.height-1){
					System.out.println();
				}*/
			}
			System.out.println();
		}

		return this;
	}

	public Grid update(){
		int counter = 0;

		//Immigration happens here. 
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[y][x].getOccupyingAgent() == null){
					if (Math.random() < CellularAutomaton.probImmigrant){
						cell[y][x].setOccupyingAgent(new Agent(Agent.Colour.getRandom(),(Math.random() < 0.5), (Math.random() < 0.5), 
							x, y));
						counter++;
					}}
				if (counter>=CellularAutomaton.maxImmigrants) break;
			}	
			if (counter>=CellularAutomaton.maxImmigrants) break;		
		}	

		//Agents interact
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[y][x].getOccupyingAgent() != null) {
					cell[y][x].update(this);
				} 
			}
		}

		//Agents reproduce
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[y][x].getOccupyingAgent() != null) {
					cell[y][x].getOccupyingAgent().reproduce(CellularAutomaton.grid);
				} 
			}
		}
		
		//Death
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[y][x].getOccupyingAgent() != null) {
					cell[y][x].getOccupyingAgent().death(Grid.cell[y][x]);
				} 
			}
		}
		
		//reset ptr
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[y][x].getOccupyingAgent() != null) {
					cell[y][x].getOccupyingAgent().resetPTR();
				} 
			}
		}

		return this;
	}

	public Grid printStats(){
		System.out.println("Strategy counts");
		return this;
	}
	
	public Grid stochUpdate() {
		
		//Generate randomly ordered indexes for the grid
		int[] w = randomise(genArray(width));
		int[] h = randomise(genArray(height));
		int counter = 0;
		
		//immigrate
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[h[y]][w[x]].getOccupyingAgent() == null){
					if (Math.random() < CellularAutomaton.probImmigrant){
						cell[h[y]][w[x]].setOccupyingAgent(new Agent(Agent.Colour.getRandom(),(Math.random() < 0.5), (Math.random() < 0.5), 
							w[x], h[y]));
						counter++;
					}}
				if (counter>=CellularAutomaton.maxImmigrants) break;
			}	
			if (counter>=CellularAutomaton.maxImmigrants) break;		
		}	
		
		//interact
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[h[y]][w[x]].getOccupyingAgent() != null) {
					cell[h[y]][w[x]].update(this);
				} 
			}
		}
		
		//Agents reproduce
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[h[y]][w[x]].getOccupyingAgent() != null) {
					cell[h[y]][w[x]].getOccupyingAgent().reproduce(CellularAutomaton.grid);
				} 
			}
		}
		
		//Death
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[h[y]][w[x]].getOccupyingAgent() != null) {
					cell[h[y]][w[x]].getOccupyingAgent().death(Grid.cell[h[y]][w[x]]);
				} 
			}
		}
		
		//reset ptr
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[y][x].getOccupyingAgent() != null) {
					cell[y][x].getOccupyingAgent().resetPTR();
				} 
			}
		}

		return this;
	}

	//Generate array containing indexes of an array of length x
	private int[] genArray(int x) {
		int[] result = new int[x];
		for(int i=0; i<x; i++) {
			result[i] = i;
		}
		return result;
	}
	
	//Shuffle integers in an array.
	private int[] randomise(int[] array) {
		    int index, temp;
		    Random random = new Random();
		    for (int i = array.length - 1; i > 0; i--)
		    {
		        index = random.nextInt(i + 1);
		        temp = array[index];
		        array[index] = array[i];
		        array[i] = temp;
		    }
		    return array;
	}
}