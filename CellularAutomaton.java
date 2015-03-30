public class CellularAutomaton {
	public static Grid grid;
	public final static double costOfGiving = 0.1;
	public final static double benefitOfReceiving = 0.02;
	public final static double ptr = 0.2; //probability of reproducing per tick
	public final static double dr = 0.7;	//probability of death per tick
	public final static double probImmigrant = 0.3;
	public final static int maxImmigrants = 10;
	
	public static void main(String[] args){
		//Initial Seed from Command Line
		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		int ticks = Integer.parseInt(args[2]);

		boolean fill;
		boolean stochUpdate;
		fill = args[3].equals("true") ? true : false;
		stochUpdate = args[4].equals("true") ? true : false;

		grid = new Grid(width,height,fill);
		
		//Update Loop
		System.out.println("t = 0");
		grid.printGrid();

		if(stochUpdate) {
			for(int i = 0; i < ticks; i++){
				System.out.println("t = " + (i+1));
				grid.stochUpdate();
				grid.printGrid();
			}
		} else {
			for(int i = 0; i < ticks; i++){
				System.out.println("t = " + (i+1));
				grid.update();
				grid.printGrid();
			}
		}
		
		System.out.println("Done.");
	}
}
