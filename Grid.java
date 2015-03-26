public class Grid {
	int width, length;
	Cell[][] cell;

	public Grid (int newWidth, int newLength, boolean filled)
	{
		width = newWidth;
		length = newLength;
		cell = new Cell[width][length];
		for(int x = 0; x < width; x ++){
			for(int y = 0; y < length; y++){
				if (filled){
					cell[x][y] = new Cell(x,y, new Agent(Agent.Colour.getRandom(),Agent.Bias.getRandom(), this.cell[x][y], 1));
				}
				else if (!filled){
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
					cell[x][y].update();
				/*
				**
					TODO: UPDATE GRID METHOD
				**
				*/
			}
		}

		return this;
	}
}