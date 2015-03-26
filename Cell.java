public class Cell {
	int x, y;
	Agent occupyingAgent;

	public Cell (int newX, int newY, Agent newOccupyingAgent) {
		x = newX;
		y = newY;
		occupyingAgent = newOccupyingAgent;
	}

	public Cell (int newX, int newY){
		x = newX;
		y = newY;
		occupyingAgent = null;
	}

	public Cell printCell(){
		if(this.occupyingAgent == null ){
			System.out.print(". ");
		}
		else if (this.occupyingAgent != null){
			System.out.print(this.occupyingAgent.colour.toString().substring(0,3)+" ");
		}
		return this;
	}

	public Cell update(){
		if(occupyingAgent!= null)
			occupyingAgent.update();
		/*
		**
			TODO: UPDATE CELL METHOD
		**
		*/
		return this;
	}
}