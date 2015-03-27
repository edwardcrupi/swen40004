class Agent {

	//Instance Variables
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

	public enum Bias {
		ETHNOCENTRIC ("ETHNOCENTRIC"), ALTRUIST ("ALTRUIST"), EGOIST ("EGOIST"), COSMOPOLITAN ("COSMOPOLITAN");
		
		private final String name;

		private Bias(String s) {
    	    name = s;
    	}

    	//Define Bias.ETHNOCENTRIC.toString(); to return the string "ETHNOCENTRIC"
    	//This seems unnecessary?
    	public String toString(){
    		return name;
    	}

    	//Returns a random Bias value.
		public static Bias getRandom() {
        	return values()[(int) (Math.random() * values().length)];
    	}
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
		bias = newBias; //why not use the random bias function here? 
		//In the NetLogo model the chance of different biases is configurable.
		occupiedCell = newOccupiedCell;
		PTR = newPTR;

		//I don't like this structure. Wouldn't it be better to store the binaries coop-with-same
		//and coop-with-diff and label their bias from there, rather than the other way around?
		//that way it's easier to have them mutate (fluctuating binary values vs fluctuating enums that
		//have to update to representative binary values)
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
	
	public Agent update(){
		/*
		**
			TODO: UPDATE AGENT
		**
		*/
		return this;
	}

	public Agent interact(Agent otherAgent){
		/*
		**
			TODO:	INTERACTION WITH OTHER AGENTS METHOD
					UPDATE PTR BASED ON COST-OF-GIVING
					UPDATE PTR BASED ON GAIN-OF-RECEIVING
					IMMIGRANT-CHANCE-COOPERATE-WITH-SAME
					IMMIGRANT-CHANCE-COOPERATE-WITH-DIFFERENT
		**
		*/
		return this;
	}
}