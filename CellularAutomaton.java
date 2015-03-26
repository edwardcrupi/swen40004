public class CellularAutomaton{
	static Grid grid;
	
	public static void main(String[] args){
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		boolean fill;
		fill = args[2].equals("true") ? true : false;
		int ticks = Integer.parseInt(args[3]);
		grid = new Grid(x,y,fill);
		for(int i = 0; i < ticks; i++){
			System.out.println("t = " + (i+1));
			grid.update();
			grid.printGrid();
		}
		System.out.println("Done.");
	}
}