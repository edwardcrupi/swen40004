public class Grid {
	int width, length;
	Cell[][] cell;

	//Generates a grid
	public Grid (int newWidth, int newLength, boolean filled)
	{
		width = newWidth;
		length = newLength;
		cell = new Cell[width][length];
		for(int x = 0; x < width; x ++){
			for(int y = 0; y < length; y++){
				if (filled){
					//populates the grid with agents of random 'color(race)'
					// the (Math.random < 0.5) generates a random boolean for cooperation.
					cell[x][y] = new Cell(x,y, new Agent(Agent.Colour.getRandom(),(Math.random() < 0.5), (Math.random() < 0.5), x, y, 1, 1));
				}
				else if (!filled){
					//sets up an empty grid
					cell[x][y] = new Cell(x,y);
				}
			}
		}
	}

	public Grid printGrid(){
		for(int x = 0; x < this.width; x++)
		{
			for(int y = 0; y < this.length; y++){
				this.cell[x][y].printCell();
				if(y == this.length-1){
					System.out.println();
				}
			}
		}

		return this;
	}

	public Grid update(){
		for(int x = 0; x < this.width; x++)
		{
			for(int y = 0; y < this.length; y++){
				if(cell[x][y] != null)
					cell[x][y].update(this);
				/*

				*/
			}
		}

		return this;
	}
}