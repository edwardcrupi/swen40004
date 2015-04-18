public class CellularAutomaton {
	public static Grid grid;

	public final static double costOfGiving = 0.01;
	public final static double benefitOfReceiving = 0.03;
	public final static double ptr = 0.12; //probability of reproducing per tick
	public final static double dr = 0.1;	//probability of death per tick
	//public final static double probImmigrant = 0.4; //this is a shit way to do it.
	public final static int maxImmigrants = 1;//per day
	public final static double mutationRate = 0.005;
	public final static double probImmigrant = dr;

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
		grid.printStats();

		if(stochUpdate) {
				for(int i = 0; i < ticks; i++){
					System.out.println("t = " + (i+1));
					grid.stochUpdate();
					//grid.printGrid();
					grid.printStats();
				}
		} else {
			for(int i = 0; i < ticks; i++){
				System.out.println("t = " + (i+1));
				grid.update();
				//grid.printGrid();
				grid.printStats();
			}
		}
		System.out.println("Done.");
	}
}
