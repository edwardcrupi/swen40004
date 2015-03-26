class Agent {

	//Instance Variables
	public enum Colour {
		RED, BLUE, GREEN
	}

	/*
	**

	This could also be defined as:

	public enum bias {
		CD, CC, DD, DC
	}

	**
	*/

	public enum Bias {
		ETHNOCENTRIC, ALTRUIST, EGOIST, COSMOPOLITAN 
	}

	public Colour colour;
	public Bias bias;

	public Cell occupiedCell;
	public int PTR;

	public boolean cooperateWithSame;
	public boolean cooperateWithDifferent;

	//Constructor
	public Agent (Colour newColour, Bias newBias, Cell newOccupiedCell, int newPTR) {
		
		//Set parameters
		colour = newColour;
		bias = newBias;
		occupiedCell = newOccupiedCell;
		PTR = newPTR;
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
	}
	
	/*
	**
		TODO:	INTERACTION WITH OTHER AGENTS METHOD
				UPDATE PTR BASED ON COST-OF-GIVING
				UPDATE PTR BASED ON GAIN-OF-RECEIVING
				IMMIGRANT-CHANCE-COOPERATE-WITH-SAME
				IMMIGRANT-CHANCE-COOPERATE-WITH-DIFFERENT
	**
	*/
}