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
	//Ours lives will be easier with PTR as a float since it's how agents interact.
	public double probReproduce;
	public double deathRate;
	public double ptr; //default ptr for reproduction

	public boolean cooperateWithSame;
	public boolean cooperateWithDifferent;

	public double costOfGiving;
	public double benefitOfReceiving;

	public int x;
	public int y;
	//Constructor
	public Agent (Colour colour, boolean cooperateWithSame, boolean cooperateWithDifferent,
					int cellX, int cellY, double costOfGiving, double benefitOfReceiving, double ptr, double dr) {
		
		//Set parameters
		this.colour = colour;
		this.cooperateWithSame = cooperateWithSame;
		this.cooperateWithDifferent = cooperateWithDifferent;
		//In the NetLogo model the chance of different biases is configurable.
		x=cellX;
		y=cellY;
		this.probReproduce = ptr;
		this.deathRate = dr;
		this.ptr = ptr;
		this.costOfGiving = costOfGiving;
		this.benefitOfReceiving = benefitOfReceiving;
		
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
		if(this.cooperateWithSame == true) {
			if (this.cooperateWithDifferent == false) {
				this.bias = "ETHNOCENTRIC";
			} else {
				this.bias = "ALTRUIST";
			}
		}	
		else {
				if(this.cooperateWithDifferent == true) {
					this.bias = "COSMOPOLITAN";
				} else {
					this.bias = "EGOIST";
				}
		}
	}
	
	public Cell[] neighbourhood(Grid grid) {
		//Identify neighbours coordinates
				/*
				The (n % m) + m is because the modulus operator in java
				actually returns the remainder in the range -m < x < m, 
				rather than a modulus
				in the range 0 < x < m
				*/
				int width = grid.width;
				int height = grid.height;
				int leftx = ((x - 1)%width);
						if (leftx<0) {leftx += width;}
				int lefty = y;
				int rightx = ((x + 1)%width);
				int righty = y;
				int abovex = x;
				int abovey = ((y - 1)%height);
						if (abovey<0) {abovey += height;};
				int belowx = x;
				int belowy = ((y + 1)%height);

				/*System.out.printf("(%d, %d), (%d, %d), (%d ,%d), (%d, %d), (%d, %d)", x, y,
					leftx, lefty, rightx, righty, abovex, abovey, belowx, belowy);
				System.out.println();
				*/
				Cell[] result = new Cell[4];
				result[0] = Grid.cell[lefty][leftx];
				result[1] = Grid.cell[righty][rightx];
				result[2] = Grid.cell[abovey][abovex];
				result[3] = Grid.cell[belowy][belowx];
				
				return result;
	}
	public void update(Grid grid){

		Cell[] neighbour = this.neighbourhood(grid);
		
		//Interact with Von Neumann neighbourhood (in English: adjacent cells)
		interact(neighbour[0]);
		interact(neighbour[1]);
		interact(neighbour[2]);
		interact(neighbour[3]);
		
		//Reproduce + mutate
		reproduce(neighbour);
		
		//Die
		death(grid);
	}

	public void interact(Cell otherAgent){

		//If they're the same color and cooperate with same
		//or if they're different colour and cooperate with different
		//Update ptr.
		if(otherAgent.getOccupyingAgent() != null){
			if ((colour == otherAgent.getOccupyingAgent().colour && cooperateWithSame) || (
				colour != otherAgent.getOccupyingAgent().colour && cooperateWithDifferent)) {
					probReproduce -= costOfGiving;
					otherAgent.getOccupyingAgent().probReproduce += benefitOfReceiving;
			}
		}


		/*
		**
		-- This may be better as its own method --
					IMMIGRANT-CHANCE-COOPERATE-WITH-SAME
					IMMIGRANT-CHANCE-COOPERATE-WITH-DIFFERENT
		**
		*/
	}
	
	//births agents into available cells
	public void reproduce(Cell[] neighbours) {
		if (Math.random() < probReproduce) {
			for(int i=0; i<4; i++) {
				//look for empty neigbour
				if(neighbours[i].getOccupyingAgent() == null) {
					//inject identical agent (inherits parents properties)
					neighbours[i].setOccupyingAgent(
							new Agent(colour,cooperateWithSame, cooperateWithDifferent, x, y, costOfGiving, benefitOfReceiving, ptr, deathRate));
							neighbours[i].getOccupyingAgent().mutate();
					break;
				}
			}
		}
		
	}
	
	//This implements the 0.05 chance of mutation for newly born agents.
	public void mutate() {
		
		if(Math.random()<0.05) {
			this.colour=Agent.Colour.getRandom();
		}
		if(Math.random()<0.05) {
			this.cooperateWithSame = (Math.random() < 0.05);
		}
		if(Math.random()<0.05) {
			this.cooperateWithDifferent = (Math.random() < 0.05);
			
		}
	}
	

	//Implements death of agent (cell.occupyingAgent set to null)
	public void death(Grid grid) {
		
		if (Math.random() < deathRate) {
			Grid.cell[y][x] = new Cell(x,y);
		}
	}
}