public class Grid {
	int width, length;
	Cell[][] position;

	public Grid (int newWidth, int newLength, boolean filled)
	{
		width = newWidth;
		length = newLength;
		position = new Cell[width][length];
		for(int x = 0; x < width; x ++){
			for(int y = 0; y < length; y++){
				if (filled){
					position[x][y] = new Cell(x,y, new Agent(Agent.Colour.RED,Agent.Bias.ALTRUIST, this.position[x][y], 1));
				}
				else if (!filled){
					position[x][y] = new Cell(x,y);
				}
			}
		}
	}

	public static Grid printGrid(Grid gridToPrint){
		for(int x = 0; x < gridToPrint.width; x++)
		{
			for(int y = 0; y < gridToPrint.length; y++){
				gridToPrint.position[x][y].printCell(gridToPrint.position[x][y]);
				if(y == gridToPrint.length-1){
					System.out.println();
				}
			}
		}

		return gridToPrint;
	}
}