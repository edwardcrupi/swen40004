public class Cell {
	public int x, y;
	public Agent occupyingAgent;

	//Constrcutor for an occupied cell
	public Cell (int newX, int newY, Agent newOccupyingAgent) {
		x = newX;
		y = newY;
		occupyingAgent = newOccupyingAgent;
	}

	//Contructs an unoccupied cell
	public Cell (int newX, int newY){
		x = newX;
		y = newY;
		occupyingAgent = null;
	}

	//Prints the 'color' of the current cell's occupier
	public Cell printCell(){
		if(this.occupyingAgent == null ){
			System.out.print(". ");
		}
		else if (this.occupyingAgent != null){
			System.out.print(this.occupyingAgent.colour.toString().substring(0,3)+" ");
		}
		return this;
	}

	public Cell update(Grid grid){
		if(this.occupyingAgent != null)
			this.occupyingAgent.update(grid);
		/* 
		** 
			TODO: UPDATE CELL METHOD
		**
		*/
		return this;
	}
}