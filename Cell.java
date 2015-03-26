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

	public Cell printCell(Cell cellToPrint){
		if(cellToPrint.occupyingAgent == null ){
			System.out.print(". ");
		}
		else if (cellToPrint.occupyingAgent != null){
			System.out.print(cellToPrint.occupyingAgent.colour.toString().substring(0,3)+" ");
		}
		return cellToPrint;
	}
}