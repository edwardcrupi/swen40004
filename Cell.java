public class Cell {
	public int x, y;
	private Agent occupyingAgent;

	public Agent getOccupyingAgent() {
		return occupyingAgent;
	}

	public void setOccupyingAgent(Agent occupyingAgent) {
		this.occupyingAgent = occupyingAgent;
	}

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
			System.out.print(this.occupyingAgent.colour.toString().substring(0,1)+" ");
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