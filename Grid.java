import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Grid {
	int width, height, CC, CD, DD, DC;
	String timestamp;
	public static Cell[][] cell;
	
	//Generates a grid
	public Grid (int newWidth, int newLength, boolean filled)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_HH_mm_ss");
		timestamp = sdf.format(new Date()).toString()+".csv";
		width = newWidth; //this is actually the length/height
		height = newLength; //this is actually the width
		cell = new Cell[height][width];
		CC = CD = DD = DC = 0;
		//Create new header for the results file for the printStats function
		try {
			File file = new File(timestamp);
			file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("CC, CD, DD, DC\n");
			bw.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		for(int y = 0; y < height; y ++){
			for(int x = 0; x < width; x++){
				if (filled){
					//populates the grid with agents of random 'color(race)'
					// the (Math.random < 0.5) generates a random boolean for cooperation.
					cell[y][x] = new Cell(x,y, new Agent(Agent.Colour.getRandom(),(Math.random() < 0.5), (Math.random() < 0.5), 
								x, y));
					countCell(cell[y][x]);

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

		//reset ptr and update the strategy counts
		CC = CD = DD = DC = 0;
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[y][x].getOccupyingAgent() != null) {
					cell[y][x].getOccupyingAgent().resetPTR();
					countCell(cell[y][x]);
				} 
			}
		}
		return this;
	}

	public Grid printStats(){
		System.out.println("Strategy counts");
                String counts = new String(CC+","+CD+","+DD+","+DC);
                System.out.println(counts);
		try {
			File file = new File(timestamp);
			FileWriter writer = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(writer);
			PrintWriter out = new PrintWriter(bw);
			out.println(counts);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		
		//reset ptr and get a reading of the strategy counts
		CC = DD = DC = CD = 0;
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[y][x].getOccupyingAgent() != null) {
					cell[y][x].getOccupyingAgent().resetPTR();
					countCell(cell[y][x]);
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

	public void countCell(Cell x){
		CC = (x.getOccupyingAgent().bias == "ALTRUIST") ? CC+1 : CC;
		CD = (x.getOccupyingAgent().bias == "ETHNOCENTRIC") ? CD+1 : CD;
		DD = (x.getOccupyingAgent().bias == "EGOIST") ? DD+1 : DD;
		DC = (x.getOccupyingAgent().bias == "COSMOPOLITAN") ? DC+1 : DC;

		
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
