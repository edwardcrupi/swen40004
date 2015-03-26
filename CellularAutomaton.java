public class CellularAutomaton{
	static Grid grid;
	
	public static void main(String[] args){
		grid = new Grid(10,10, false);
		Grid.printGrid(grid);
		System.out.println("Done.");
	}
}