public class Grid {
	int width, height;
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
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[y][x].getOccupyingAgent() != null) {
					cell[y][x].update(this);
				} else if (Math.random() < CellularAutomaton.probImmigrant){
					//ALL HAIL DIVERSITY!
					cell[y][x].setOccupyingAgent(new Agent(Agent.Colour.getRandom(),(Math.random() < 0.5), (Math.random() < 0.5), 
							x, y));
					counter++;
					} 
			if (counter>=CellularAutomaton.maxImmigrants) break;
			}
		if (counter>=CellularAutomaton.maxImmigrants) break;
		}

		return this;
	}
}