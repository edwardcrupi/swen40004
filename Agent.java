public class Agent {

	//Instance Variables

	//Defines the unique 'races' of people in the model. 
	public enum Colour {
		RED ("RED"), BLUE ("BLUE"), GREEN ("GREEN");
		
		private final String name;       

    	private Colour(String s) {
    	    name = s;
    	}

    	public String toString(){
    		return name;
    	}

		public static Colour getRandom() {
        	return values()[(int) (Math.random() * values().length)];
    	}
	}

	/*
	**

	This could also be defined as:

	public enum bias {
		CD, CC, DD, DC
	}

	**
	*/
	
	/*
	public enum Bias {
		ETHNOCENTRIC ("ETHNOCENTRIC"), ALTRUIST ("ALTRUIST"), EGOIST ("EGOIST"), COSMOPOLITAN ("COSMOPOLITAN");
		
		private final String name;

		private Bias(String s) {
    	    name = s;
    	}

    	//Define Bias.ETHNOCENTRIC.toString(); to return the string "ETHNOCENTRIC"
    	public String toString(){
    		return name;
    	}

    	//Returns a random Bias value.
		public static Bias getRandom() {
        	return values()[(int) (Math.random() * values().length)];
    	}
	}
	*/

	public Colour colour;
	public String bias;
	public Cell occupiedCell;
	//Ours lives will be easier with PTR as a float since it's how agents interact.
	public float probReproduce;
	public float deathRate;

	public boolean cooperateWithSame;
	public boolean cooperateWithDifferent;

	public float costOfGiving;
	public float benefitOfReceiving;

	public int x;
	public int y;
	//Constructor
	public Agent (Colour colour, boolean cooperateWithSame, boolean cooperateWithDifferent,
					int cellX, int cellY, float probReproduce, float deathRate) {
		
		//Set parameters
		this.colour = colour;
		this.cooperateWithSame = cooperateWithSame;
		this.cooperateWithDifferent = cooperateWithDifferent;
		//In the NetLogo model the chance of different biases is configurable.
		x=cellX;
		y=cellY;
		this.occupiedCell = occupiedCell;
		this.probReproduce = probReproduce;
		this.deathRate = deathRate;

		//I don't like this structure. Wouldn't it be better to store coop-with-same
		//and coop-with-diff and label their bias from there, rather than the other way around?
		//that way it's easier to have them mutate (fluctuating binary values vs fluctuating enums that
		//have to update to representative binary values)
		/*
		switch(this.bias) {
			case ETHNOCENTRIC:
				this.cooperateWithSame = true;
				this.cooperateWithDifferent = false;
				break;
			case ALTRUIST:
				this.cooperateWithSame = true;
				this.cooperateWithDifferent = true;
				break;
			case EGOIST:
				this.cooperateWithSame = false;
				this.cooperateWithDifferent = false;			
				break;
			case COSMOPOLITAN:
				this.cooperateWithSame = false;
				this.cooperateWithDifferent = true;			
				break;
			default: System.out.println("No bias set!");
		}
		*/

		//Sets bias based on cooperation values.
		if(this.cooperateWithSame = true) {
			if (this.cooperateWithDifferent = false) {
				this.bias = "ETHNOCENTRIC";
			} else {
				this.bias = "ALTRUIST";
			}
		}	
		else {
				if(this.cooperateWithDifferent = true) {
					this.bias = "COSMOPOLITAN";
				} else {
					this.bias = "EGOIST";
				}
		}
	}
	
	public void update(Grid grid){
		//Identify neighbours coordinates
		/*
		The (n % m) + m is because the modulus operator in java
		actually returns the remainder in the range -m < x < m, 
		rather than a modulus
		in the range 0 < x < m
		*/
		int width = grid.width-1;
		int length = grid.length-1;
		int leftx = ((x - 1)%width) + width;
		int lefty = y;
		int rightx = ((x + 1)%width) + width;
		int righty = y;
		int abovex = x;
		int abovey = ((y + 1)%length) + length;
		int belowx = x;
		int belowy = ((y - 1)%length) + length;

		System.out.printf("%d, %d, %d, %d, %d ,%d, %d, %d, %d, %d", width, length, 
			leftx, lefty, rightx, righty, abovex, abovey, belowx, belowy);
		//Interact with Von Neumann neighbourhood (in English: adjacent cells)
		interact(grid.cell[leftx][lefty].occupyingAgent);
		interact(grid.cell[rightx][righty].occupyingAgent);
		interact(grid.cell[abovex][abovey].occupyingAgent);
		interact(grid.cell[belowx][belowy].occupyingAgent);
	}

	public void interact(Agent otherAgent){

		//If they're the same color and cooperate with same
		//or if they're different colour and cooperate with different
		//Update ptr.
		if ((colour == otherAgent.colour && cooperateWithSame) || (
			colour != otherAgent.colour && cooperateWithDifferent)) {
				probReproduce -= costOfGiving;
				otherAgent.probReproduce += benefitOfReceiving;
		}


		/*
		**
		-- This may be better as its own method --
					IMMIGRANT-CHANCE-COOPERATE-WITH-SAME
					IMMIGRANT-CHANCE-COOPERATE-WITH-DIFFERENT
		**
		*/
	}
}