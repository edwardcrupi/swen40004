class Agent {

	//Instance Variables
	public enum colour {
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

	public enum bias {
		ETHNOCENTRIC, ALTRUIST, EGOIST, COSMOPOLITAN 
	}

	public Cell occupiedCell;
	public int PTR;

	public boolean cooperateWithSame;
	public boolean cooperateWithDifferent;

	//Constructor
	public class Agent (newColour, newBias, newOccupiedCell, newPTR) {
		
		//Set parameters
		colour = newColour;
		bias = newBias;
		occupiedCell = newOccupiedCell;
		PTR = newPTR;
		switch(self.bias) {
			case ETHNOCENTRIC:
				self.cooperateWithSame = true;
				self.cooperateWithDifferent = false;
				break;
			case ALTRUIST:
				self.cooperateWithSame = true;
				self.cooperateWithDifferent = true;
				break;
			case EGOIST:
				self.cooperateWithSame = false;
				self.cooperateWithDifferent = false;			
				break;
			case COSMOPOLITAN:
				self.cooperateWithSame = false;
				self.cooperateWithDifferent = true;			
				break;
			default: System.out.println("No bias set!");
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