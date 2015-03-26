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

	public static Grid printGrid(Grid gridToPrint){
		for(int x = 0; x < gridToPrint.width; x++)
		{
			for(int y = 0; y < gridToPrint.length; y++){
				gridToPrint.cell[x][y].printCell(gridToPrint.cell[x][y]);
				if(y == gridToPrint.length-1){
					System.out.println();
				}
			}
		}

		return gridToPrint;
	}
}