public class Grid {
	int width, height;
	Cell[][] cell;

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
					cell[y][x] = new Cell(x,y, new Agent(Agent.Colour.getRandom(),(Math.random() < 0.5), (Math.random() < 0.5), x, y, 1, 1));
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
				this.cell[y][x].printCell();
				/*if(y == this.height-1){
					System.out.println();
				}*/
			}
			System.out.println();
		}

		return this;
	}

	public Grid update(){
		for(int y = 0; y < this.height; y++)
		{
			for(int x = 0; x < this.width; x++){
				if(cell[y][x] != null)
					cell[y][x].update(this);
				/*

				*/
			}
		}

		return this;
	}
}