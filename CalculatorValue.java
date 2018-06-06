package calculator;

import java.math.RoundingMode;
import java.util.Scanner;
import java.math.BigDecimal;

/**
 * <p> Title: CalculatorValue Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 
 * <p> Copyright: Lynn Robert Carter Â© 2017 </p>
 * 
 * @author Sanchit
 * 
 * @version 2.1	2018-02-28 Double float implementation of the CalculatorValue class 
 * 
 * @version 2.1 2018-03-23 Double float implementation of the CalculatorValue class with error terms
 * 
 */
public class CalculatorValue {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major values that define a calculator value
	
	
	double MV = 0.00;
	public static String errorTerm1 = "0.5";
	public static String errorTerm2 = "0.5";
	public static double errorTerm = 0.00;
	
	String errorMessage = "";
	
	public static String errorTermErrorMessage = "Error Term recognition has not been implements";
	public static String errorTermInput = "";			// The input being processed
	public static int errorTermIndexofError = -1;		// The index where the error was located
	private static int errorTermState = 0;
	private static int errorTermCurrentCharNdx;
	private static char errorTermCurrentChar;
	public static String errorTermInputLine = "";
	public static boolean errorTermRunning;
	public static boolean errorTermFinalState = false;
	private static int errorTermNextState = 0;

	
	double operand1LowerBound = 0.00;
	double operand2LowerBound = 0.00;
	double operand1UpperBound = 0.00;
	double operand2UpperBound = 0.00;
	double resultLowerBound = 0.00;
	double resultUpperBoundB = 0.00;
	
	String zeroMessage ="Invalid Input";                // this defines the error message when the divisor is zero
	public static String measuredValueErrorMessage = "";	// The alternate error message text
	public static String measuredValueInput = "";		// The input being processed
	public static int measuredValueIndexofError = -1;		// The index where the error was located
	public static int state = 0;						// The current state value
	public static int nextState = 0;					// The next state value
	public static boolean finalState = false;			// Is this state a final state
	private static String inputLine = "";				// The input line
	private static char currentChar;						// The current character in the line
	private static int currentCharNdx;					// The index of the current character
	private static boolean running;						// The flag that specifies if it is running
	
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
	}

	/*****
	 * This constructor creates a calculator value based on a long integer. For future calculators, it
	 * is best to avoid using this constructor.
	 */
	public CalculatorValue(double v) {
		MV = v;
	}

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		MV = v.MV;
		errorMessage = v.errorMessage;
	}

	/***********
	 * This method sets the value of operand1 error term
	 * @param value
	 * @return
	 */
	public double setErrTerm1( String value) {
		try {
        errorTerm1 = value;
		return Double.parseDouble(errorTerm1);}
		
		catch (Exception e) {
			return 0.00;
		}
	}
	
	/***********
	 * This method sets the value of operand1 error term
	 * @param value
	 * @return
	 */
	public double setErrTerm2( String value) {
		try {
	        errorTerm2 = value;
			return Double.parseDouble(errorTerm2);}
			
			catch (Exception e) {
				return 0.00;
			}
	}
	
	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the 
	 * routine returns the value with the error message value set to empty or the string 
	 * of an error message.
	 */
	public CalculatorValue(String s) {
		MV = 0.00;
		
		if (s.length() == 0) {							     	// If there is nothing there,
			errorMessage = "***Error*** Input is empty";		// signal an error	
			return;												
		}
		
														
		// If the first character is a plus sign, ignore it.
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		
		switch(s.charAt(start)) {
		case 1: start++; negative =true;break;                  //Switch case is used to check sign only once as break will stops
		                                                   // after one check and two consecutive signs will be considered as 
		                                                    //an invalid input
				}
		
		/*****
		 * Here a scanner is created for the digits, to see if the next token is a valid or not, here numbers starting 
		 * with "+" are invalid as they don't have any influence over the number, they remain as it is 
		 *
		 */
		// See if the user-entered string can be converted into an double value
		Scanner tempScanner = new Scanner(s.substring(start));// Create scanner for the digits
		
		if (!tempScanner.hasNextDouble()) {					// See if the next token is a valid
			errorMessage = "***Error*** Invalid value"; 		
			tempScanner.close();								
			return;												
		}
		
		// Convert the user-entered string to a integer value and see if something else is following it
		MV = tempScanner.nextDouble();				// Convert the value and check to see
		if (tempScanner.hasNext()) {							// that there is nothing else is 
			errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
			tempScanner.close();								// is an error.  Therefore we must
			MV = 0;								// return a zero value.
			return;													
		}
		
		if (s.charAt(0) == '+') {
			errorMessage = "***Error*** Invalid value"; 		
			tempScanner.close();								
			return;
			
		}
		tempScanner.close();
		
		
		errorMessage = "";
		if (negative)										// Return the proper value based
			MV = -MV;					// on the state of the flag that
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 */
	public String getErrorMessage(){
		return errorMessage;
	}

	
	/*****
	 * Set the current value of a calculator value to a specific long integer
	 */
	public void setValue(double v){
		MV = v;
	}
	
	/*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}
	
	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 */
	public void setValue(CalculatorValue v){
		MV = v.MV;
		errorMessage = v.errorMessage;
	}
	
	/****
	 * Sets the value of error term
	 * @param value
	 * @return
	 */
	
	public double setErrTerm(String value) {
		errorTerm = Double.parseDouble(value);
		return errorTerm;
	}
	
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	public String toString() {
		return checkingUp(MV) + "";
	}
	
	
	public String toStringErrTerm() {
		return checkingUp(errorTerm) + "";
	}
	
	/*************
	 * Rounding the result
	 */

	 // converting the resultant error term to String
    public double checkingUp (double resultErrorTerm){
    
    //If the word is in exponential form convert it into normal form
    String text = BigDecimal.valueOf(resultErrorTerm).toPlainString();
	    
	// Finding the occurrence of first significant digit after decimal point
    	int ndx= text.indexOf(".")+1;                       // we will be using after the for loops ends, thus we declare it outside the for loop 
	   	for(; ndx < text.length(); ndx++) {
	   			   		
         if(text.charAt(ndx)>='1'&& text.charAt(ndx)<='9') 
        	 // Checking for the first significant digit 
       	 	 break;                                      // coming out of the loop if we have encountered the first 
                                                          // significant digit
    	}
	 
		return round(text,ndx);
    }
    
    public static double round(String value, int places) {
		   places = (places== 0)?places:--places;
		    if (places < 0) throw new IllegalArgumentException();
		 
		    BigDecimal bd = new BigDecimal(value);
		    bd = bd.setScale(places, RoundingMode.HALF_UP);
		    Double str = bd.doubleValue();
		  
		    return str;
		}

	
	public String debugToString() {
		return "MV = " + MV + "\nerrorMessage = " + errorMessage + "\n";
	}
	
	public String debugToStringErrTerm() {
		return "errorTerm = " + errorTerm + "\n";
	}

	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	

	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is addition and we do not yet support units, we don't recognize any errors.
	 */

	public void add(CalculatorValue v) {
		double s = Double.parseDouble(errorTerm1);
	    double a = Double.parseDouble(errorTerm2);
		operand1LowerBound = MV - s;
		operand1UpperBound = MV + s;
		operand2LowerBound = v.MV - a;
		operand2UpperBound = v.MV + a;
		
		resultLowerBound = operand1LowerBound + operand2LowerBound;
		resultUpperBoundB = operand1UpperBound + operand2UpperBound;

		MV =  (resultUpperBoundB + resultLowerBound)/2;
		errorTerm = Math.abs((resultUpperBoundB + resultLowerBound)/2);

		errorMessage = "";
		
		
	}


	/*****
	 * Algorithm for subtraction  
	 * @param v
	 */
	public void sub(CalculatorValue v) {
		double s = Double.parseDouble(errorTerm1);
	    double a = Double.parseDouble(errorTerm2);
		operand1LowerBound = MV - s;
		operand1UpperBound = MV + s;
		operand2LowerBound = v.MV - a;
		operand2UpperBound = v.MV + a;
		
		resultLowerBound = operand1LowerBound - operand2LowerBound;
		resultUpperBoundB = operand1UpperBound - operand2UpperBound;
		
		MV =  (resultUpperBoundB + resultLowerBound)/2;
		
		errorTerm = Math.abs((resultUpperBoundB - resultLowerBound)/2);

		errorMessage = "";
		
	}
	
	/*****
	 * Algorithm for multiplication  
	 * @param v
	 */

	public void mpy(CalculatorValue v) {
		double s = Double.parseDouble(errorTerm1);
	    double a = Double.parseDouble(errorTerm2);
		operand1LowerBound = MV - s;
		operand1UpperBound = MV + s;
		operand2LowerBound = v.MV - a;
		operand2UpperBound = v.MV + a;
		
		resultLowerBound = operand1LowerBound * operand2LowerBound;
		resultUpperBoundB = operand1UpperBound * operand2UpperBound;
		
		MV =  (resultUpperBoundB + resultLowerBound)/2;
		
		errorTerm = Math.abs((resultUpperBoundB - resultLowerBound)/2);

		errorMessage = "";
		
	}
	
	/*****
	 * Algorithm for division  
	 * @param v
	 */

	public void div(CalculatorValue v) {
		double s = Double.parseDouble(errorTerm1);
	    double a = Double.parseDouble(errorTerm2);
		
		operand1LowerBound = MV - s;
		operand1UpperBound = MV + s;
		operand2LowerBound = v.MV - a;
		operand2UpperBound = v.MV + a;
		
		resultLowerBound = operand1LowerBound / operand2LowerBound;
		resultUpperBoundB = operand1UpperBound / operand2UpperBound;
		
		MV =  (resultUpperBoundB + resultLowerBound)/2;
		
		errorTerm = Math.abs((resultUpperBoundB - resultLowerBound)/2)/2;

		errorMessage = "";
		
		
	}
	
	/*****
	 * For square root only one operand is required, thus operation is only performed over 
	 * MV and operand1 error term only
	 * 
	 * @param v
	 */
	public void sqrt(CalculatorValue v){    
		double s = Double.parseDouble(errorTerm1);                                                
   		errorTerm = Math.abs(0.5 * (s/MV) * Math.sqrt(MV));
   		MV = Math.sqrt(MV); 
   		errorMessage = "";                         
		
	}

	private static String displayInput(String input, int currentCharNdx) {
		// Display the entire input line
		String result = input + "\n";

		// Display a line with enough spaces so the up arrow point to the point of an error
		for (int ndx=0; ndx < currentCharNdx; ndx++) result += " ";

		// Add the up arrow to the end of the second line
		return result + "\u21EB";				// A Unicode up arrow with a base
	}


	private static void moveToNextCharacter() {
		currentCharNdx++;
		if (currentCharNdx < inputLine.length())
			currentChar = inputLine.charAt(currentCharNdx);
		else {
			currentChar = ' ';
			running = false;
		}
	}

	/**********
	 * This method is a mechanical transformation of a Finite State Machine diagram into a Java
	 * method.
	 * 
	 * @param input		The input string for the Finite State Machine
	 * @return			An output string that is empty if every things is okay or it will be
	 * 						a string with a help description of the error follow by two lines
	 * 						that shows the input line follow by a line with an up arrow at the
	 *						point where the error was found.
	 */
	public static String checkMeasureValue(String input) {
		if(input.length() <= 0) return "";
		// The following are the local va2riable used to perform the Finite State Machine simulation
		state = 0;							// This is the FSM state number
		inputLine = input;					// Save the reference to the input line as a global
		currentCharNdx = 0;					// The index of the current character
		currentChar = input.charAt(0);		// The current character from the above indexed position

		// The Finite State Machines continues until the end of the input is reached or at some 
		// state the current character does not match any valid transition to a next state

		measuredValueInput = input;			// Set up the alternate result copy of the input
		running = true;						// Start the loop
		

		// The Finite State Machines continues until the end of the input is reached or at some 
		// state the current character does not match any valid transition to a next state
		while (running) {
			// The switch statement takes the execution to the code for the current state, where
			// that code sees whether or not the current character is valid to transition to a
			// next state
			switch (state) {
			case 0: 
				// State 0 has three valid transitions.  Each is addressed by an if statement.
				
				// This is not a final state
				finalState = false;
				
				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 1;
					break;
				}
				// If the current character is a decimal point, it transitions to state 3
				else if (currentChar == '.') {
					nextState = 3;
					break;					
				}
				
				else if(currentChar =='-') {
					nextState = 0;
					break;
				}
				
				// If it is none of those characters, the FSM halts
				else 
					running = false;
				
				// The execution of this state is finished
				break;
			
			case 1: 
				// State 1 has three valid transitions.  Each is addressed by an if statement.
				
				// This is a final state
				finalState = true;
				
				// In state 1, if the character is 0 through 9, it is accepted and we stay in this
				// state
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 1;
					break;
				}
				
				// If the current character is a decimal point, it transitions to state 2
				else if (currentChar == '.') {
					nextState = 2;
					break;
				}
				// If the current character is an E or an e, it transitions to state 5
				else if (currentChar == 'E' || currentChar == 'e') {
					nextState = 5;
					break;
				}
				// If it is none of those characters, the FSM halts
				else
					running = false;
				
				// The execution of this state is finished
				break;			
				
			case 2: 
				// State 2 has two valid transitions.  Each is addressed by an if statement.
				
				// This is a final state
				finalState = true;
				
				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 2;
					break;
				}
				// If the current character is an 'E' or 'e", it transitions to state 5
				else if (currentChar == 'E' || currentChar == 'e') {
					nextState = 5;
					break;
				}

				// If it is none of those characters, the FSM halts
				else 
					running = false;

				// The execution of this state is finished
				break;
	
			case 3:
				// State 3 has only one valid transition.  It is addressed by an if statement.
				
				// This is not a final state
				finalState = false;
				
				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 4;
					break;
				}

				// If it is none of those characters, the FSM halts
				else 
					running = false;

				// The execution of this state is finished
				break;

			case 4: 
				// State 4 has two valid transitions.  Each is addressed by an if statement.
				
				// This is a final state
				finalState = true;
				
				// If the current character is in the range from 1 to 9, it transitions to state 4
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 4;
					break;
				}
				// If the current character is an 'E' or 'e", it transitions to state 5
				else if (currentChar == 'E' || currentChar == 'e') {
					nextState = 5;
					break;
				}

				// If it is none of those characters, the FSM halts
				else 
					running = false;

				// The execution of this state is finished
				break;

			case 5: 
                 //State 5 has two valid transitions.  Each is addressed by an if statement.
				
				
				finalState = false;
				
				// If the current character is in the range from 1 to 9, it transitions to state 4
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 7;
					break;
				}
				// If the current character is an 'E' or 'e", it transitions to state 5
				else if (currentChar == '+' || currentChar == '-') {
					nextState = 6;
					break;
				}

				// If it is none of those characters, the FSM halts
				else 
					running = false;

				// The execution of this state is finished
				break;

			case 6: 
                 //State 6 has one valid transitions.  It is addressed by an if statement.
				
				
				finalState = false;
				
				// If the current character is in the range from 1 to 9, it transitions to state 4
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 7;
					break;
				}
				
				// If it is none of those characters, the FSM halts
				else 
					running = false;

				// The execution of this state is finished
				break;

			case 7: 
                  //State 7 has one valid transitions.  It is addressed by an if statement.
				
				// This is a final state
				finalState = true;
				
				// If the current character is in the range from 1 to 9, it transitions to state 4
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 7;
					break;
				}
				 
				// If it is none of those characters, the FSM halts
				else 
					running = false;

				// The execution of this state is finished
				break;
				
			}
			
			if (running) {
				
				// When the processing of a state has finished, the FSM proceeds to the next character
				// in the input and if there is one, it fetches that character and updates the 
				// currentChar.  If there is no next character the currentChar is set to a blank.
				moveToNextCharacter();
				
				// Move to the next state
				state = nextState;

			}
			// Should the FSM get here, the loop starts again

		}

		measuredValueIndexofError = currentCharNdx;		// Copy the index of the current character;
		
		// When the FSM halts, we must determine if the situation is an error or not.  That depends
		// of the current state of the FSM and whether or not the whole string has been consumed.
		// This switch directs the execution to separate code for each of the FSM states.
		switch (state) {
		case 0:
			// State 0 is not a final state, so we can return a very specific error message
			measuredValueIndexofError = currentCharNdx;		// Copy the index of the current character;
			measuredValueErrorMessage = "The first character must be a digit or a decimal point.";
			return "The first character must be a \"+\" sign, digit a decimal point.";

		case 1:
			// State 1 is a final state, so we must see if the whole string has been consumed.
			if (currentCharNdx<input.length()) {
				// If not all of the string has been consumed, we point to the current character
				// in the input line and specify what that character must be in order to move
				// forward.
				measuredValueErrorMessage = "This character may only be an \"E\", an \"e\", a digit, "
						+ "a \".\", or it must be the end of the input.\n";
				return measuredValueErrorMessage + displayInput(input, currentCharNdx);
			}
			else {
				measuredValueIndexofError = -1;
				measuredValueErrorMessage = "";
				return measuredValueErrorMessage;
			}

		case 2:
		case 4:
			// States 2 and 4 are the same.  They are both final states with only one possible
			// transition forward, if the next character is an E or an e.
			if (currentCharNdx<input.length()) {
				measuredValueErrorMessage = "This character may only be an \"E\", an \"e\", a digit or it must"
						+ " be the end of the input.\n";
				return measuredValueErrorMessage + displayInput(input, currentCharNdx);
			}
			// If there is no more input, the input was recognized.
			else {
				measuredValueIndexofError = -1;
				measuredValueErrorMessage = "";
				return measuredValueErrorMessage;
			}
		case 3:
		case 6:
			// States 3, and 6 are the same. None of them are final states and in order to
			// move forward, the next character must be a digit.
			measuredValueErrorMessage = "This character may only be a digit.\n";
			return measuredValueErrorMessage + displayInput(input, currentCharNdx);

		case 7:
			// States 7 is similar to states 3 and 6, but it is a final state, so it must be
			// processed differently. If the next character is not a digit, the FSM stops with an
			// error.  We must see here if there are no more characters. If there are no more
			// characters, we accept the input, otherwise we return an error
			if (currentCharNdx<input.length()) {
				measuredValueErrorMessage = "This character may only be a digit.\n";
				return measuredValueErrorMessage + displayInput(input, currentCharNdx);
			}
			else {
				measuredValueIndexofError = -1;
				measuredValueErrorMessage = "";
				return measuredValueErrorMessage;
			}

		case 5:
			// State 5 is not a final state.  In order to move forward, the next character must be
			// a digit or a plus or a minus character.
			measuredValueErrorMessage = "This character may only be a digit, a plus, or minus "
					+ "character.\n";
			return measuredValueErrorMessage + displayInput(input, currentCharNdx);
			
					
		default:
			return "";
		}
	}

	private static String errorTermDisplayInput(String input, int errorTermCurrentCharNdx) {
		// Display the entire input line
		String result = input + "\n";

		// Display a line with enough spaces so the up arrow point to the point of an error
		for (int ndx=0; ndx < errorTermCurrentCharNdx; ndx++) result += " ";

		// Add the up arrow to the end of the second line
		return result + "\u21EB";				// A Unicode up arrow with a base
	}

	private static void errorTermmoveToNextCharacter() {
		errorTermCurrentCharNdx++;
		if (errorTermCurrentCharNdx < errorTermInputLine.length())
			errorTermCurrentChar = errorTermInputLine.charAt(errorTermCurrentCharNdx);
		else {
			errorTermCurrentChar = ' ';
			errorTermRunning = false;
		}
	}

	/**********
	 * This method is a mechanical transformation of a Finite State Machine diagram into a Java
	 * method.
	 * 
	 * @param input		The input string for the Finite State Machine
	 * @return			An output string that is empty if every things is okay or it will be
	 * 						a string with a help description of the error follow by two lines
	 * 						that shows the input line follow by a line with an up arrow at the
	 *						point where the error was found.
	 */
	public static String checkErrorTerm(String input) {
		if(input.length() <= 0) return "";
		// The following are the local variable used to perform the Finite State Machine simulation
		errorTermState = 0;							// This is the FSM state number
		errorTermInputLine = input;					// Save the reference to the input line as a global
		errorTermCurrentCharNdx = 0;					// The index of the current character
		errorTermCurrentChar = input.charAt(0);		// The current character from the above indexed position

		// The Finite State Machines continues until the end of the input is reached or at some 
		// state the current character does not match any valid transition to a next state

		errorTermInput = input;			// Set up the alternate result copy of the input
		errorTermRunning = true;						// Start the loop

		// The Finite State Machines continues until the end of the input is reached or at some 
		// state the current character does not match any valid transition to a next state
		while (errorTermRunning) {
			// The switch statement takes the execution to the code for the current state, where
			// that code sees whether or not the current character is valid to transition to a
			// next state
			switch (errorTermState) {
			case 0: 
				// State 0 has three valid transitions.  Each is addressed by an if statement.
				
				// This is not a final state
				errorTermFinalState = false;
				
				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (errorTermCurrentChar >= '1' && errorTermCurrentChar <= '9') {
					errorTermNextState = 1;
					break;
				}
				// If the current character is a decimal point, it transitions to state 3
				else if (errorTermCurrentChar == '.') {
					errorTermNextState = 3;
					break;					
				}
				
				else if (errorTermCurrentChar == '0') {
					errorTermNextState = 8;
					break;
				}
				// If it is none of those characters, the FSM halts
				else 
					errorTermRunning = false;
				
				// The execution of this state is finished
				break;
			
			case 1: 
				// State 1 has three valid transitions.  Each is addressed by an if statement.
				
				// This is a final state
				errorTermFinalState = true;
				
				// In state 1, if the character is 0 through 9, it is accepted and we stay in this
				// state
				if (errorTermCurrentChar == '0') {
					errorTermNextState = 1;
					break;
				}
				
				// If the current character is a decimal point, it transitions to state 2
				else if (errorTermCurrentChar == '.') {
					errorTermNextState = 2;
					break;
				}
				// If the current character is an E or an e, it transitions to state 5
				else if (errorTermCurrentChar == 'E' || errorTermCurrentChar == 'e') {
					errorTermNextState = 5;
					break;
				}
				// If it is none of those characters, the FSM halts
				else
					errorTermRunning = false;
				
				// The execution of this state is finished
				break;			
				
			case 2: 
				// State 2 has two valid transitions.  Each is addressed by an if statement.
				
				// This is a final state
				errorTermFinalState = true;
				
				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (errorTermCurrentChar == 'E' || errorTermCurrentChar == 'e') {
					errorTermNextState = 5;
					break;
				}
				
				// If it is none of those characters, the FSM halts
				else 
					errorTermRunning = false;

				// The execution of this state is finished
				break;
	
			case 3:
				// State 3 has only one valid transition.  It is addressed by an if statement.
				
				// This is not a final state
				errorTermFinalState = false;
				
				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (errorTermCurrentChar >= '1' && errorTermCurrentChar <= '9') {
					errorTermNextState = 4;
					break;
				}

				else if (errorTermCurrentChar == '0') {
					errorTermNextState = 3;
					break;
					
				}
				// If it is none of those characters, the FSM halts
				else 
					errorTermRunning = false;

				// The execution of this state is finished
				break;

			case 4: 
				// State 4 has one valid transitions.  Each is addressed by an if statement.
				
				// This is a final state
				errorTermFinalState = true;
				
				
				if (errorTermCurrentChar == 'E' || errorTermCurrentChar == 'e') {
					errorTermNextState = 5;
					break;
				}

				// If it is none of those characters, the FSM halts
				else 
					errorTermRunning = false;

				// The execution of this state is finished
				break;

			case 5:
                // State 5 has two valid transition.  These are addressed by an if statement.
				
				// This is not a final state
				errorTermFinalState = false;
				
				if(errorTermCurrentChar =='+' || errorTermCurrentChar =='-') {
					errorTermNextState = 6;
					break;
				}
				
				else if (errorTermCurrentChar >= '0' && errorTermCurrentChar <= '9') {
					errorTermNextState = 7;
					break;
				}
				
				// If it is none of those characters, the FSM halts
				else 
					errorTermRunning = false;

				// The execution of this state is finished
				break;

			case 6: 
                // State 6 has only one valid transition.  It is addressed by an if statement.
				
				// This is not a final state				
				errorTermFinalState = false;
				
				if (errorTermCurrentChar >= '0' && errorTermCurrentChar <= '9') {
					errorTermNextState = 7;
					break;
				}
				
				// If it is none of those characters, the FSM halts
				else 
					errorTermRunning = false;

				// The execution of this state is finished
				break;

			case 7: 
                // State 7 has only one valid transition.  It is addressed by an if statement.
				
				// This is not a final state				
				errorTermFinalState = true;
				
				if (errorTermCurrentChar >= '0' && errorTermCurrentChar <= '9') {
					errorTermNextState = 7;
					break;
				}
				
				// If it is none of those characters, the FSM halts
				else 
					errorTermRunning = false;

				// The execution of this state is finished
				break;
			
			case 8:
                // State 8 has only one valid transition.  It is addressed by an if statement.
				
				// This is not a final state				
				errorTermFinalState = false;
				if (errorTermCurrentChar == '.') {
					errorTermNextState = 9;
					break;
				}
				
				// If it is none of those characters, the FSM halts
				else 
					errorTermRunning = false;

				// The execution of this state is finished
				break;
			
			
			case 9:
                // State 9 has only one valid transition.  It is addressed by an if statement.
				
				// This is not a final state
				errorTermFinalState = false;
				if (errorTermCurrentChar == '0') {
					errorTermNextState = 9;
					break;
				}
				
				else if (errorTermCurrentChar >= '1' && errorTermCurrentChar <= '9') {
					errorTermNextState = 4;
					break;
				}
				
				// If it is none of those characters, the FSM halts
				else 
					errorTermRunning = false;

				// The execution of this state is finished
				break;
				
			}	
			
			if (errorTermRunning) {
				// When the processing of a state has finished, the FSM proceeds to the next character
				// in the input and if there is one, it fetches that character and updates the 
				// errorTermCurrentChar.  If there is no next character the errorTermCurrentChar is set to a blank.
				errorTermmoveToNextCharacter();
				
				// Move to the next state
				errorTermState = errorTermNextState;

			}
			// Should the FSM get here, the loop starts again

		}

		errorTermIndexofError = errorTermCurrentCharNdx;		// Copy the index of the current character;
		
		// When the FSM halts, we must determine if the situation is an error or not.  That depends
		// of the current state of the FSM and whether or not the whole string has been consumed.
		// This switch directs the execution to separate code for each of the FSM states.
		switch (errorTermState) {
		case 0:
			// State 0 is not a final state, so we can return a very specific error message
			errorTermIndexofError = errorTermCurrentCharNdx;		// Copy the index of the current character;
			errorTermErrorMessage = "The first character must be a digit or a decimal point.";
			return "The first character must be a digit or a decimal point.";

		case 1:
			// State 1 is a final state, so we must see if the whole string has been consumed.
			if (errorTermCurrentCharNdx<input.length()) {
				// If not all of the string has been consumed, we point to the current character
				// in the input line and specify what that character must be in order to move
				// forward.
				errorTermErrorMessage = "This character may only be an \"E\", an \"e\", a digit, "
						+ "a \".\", or it must be the end of the input.\n";
				return errorTermErrorMessage + errorTermDisplayInput(input, errorTermCurrentCharNdx);
			}
			else {
				errorTermIndexofError = -1;
				errorTermErrorMessage = "";
				return errorTermErrorMessage;
			}

		case 2:
		case 4:
			// States 2 and 4 are the same.  They are both final states with only one possible
			// transition forward, if the next character is an E or an e.
			if (errorTermCurrentCharNdx<input.length()) {
				errorTermErrorMessage = "This character may only be an \"E\", an \"e\", or it must"
						+ " be the end of the input.\n";
				return errorTermErrorMessage + errorTermDisplayInput(input, errorTermCurrentCharNdx);
			}
			// If there is no more input, the input was recognized.
			else {
				errorTermIndexofError = -1;
				errorTermErrorMessage = "";
				return errorTermErrorMessage;
			}
		case 6:
			// State 6. None of them are final states and in order to
			// move forward, the next character must be a digit.
			errorTermErrorMessage = "This character may only be a digit.\n";
			return errorTermErrorMessage + errorTermDisplayInput(input, errorTermCurrentCharNdx);

		case 3:
		case 9:
		case 7:
			// States 3, 7 and 9 has similar valid transitions, but 7 is a final state, so it must be
			// processed differently. If the next character is not a digit, the FSM stops with an
			// error.  We must see here if there are no more characters. If there are no more
			// characters, we accept the input, otherwise we return an error
			if (errorTermCurrentCharNdx<input.length()) {
				errorTermErrorMessage = "This character may only be a digit.\n";
				return errorTermErrorMessage + errorTermDisplayInput(input, errorTermCurrentCharNdx);
			}
			else {
				errorTermIndexofError = -1;
				errorTermErrorMessage = "";
				return errorTermErrorMessage;
			}
			
		case 5:
			// State 5 is not a final state.  In order to move forward, the next character must be
			// a digit or a plus or a minus character.
			errorTermErrorMessage = "This character may only be a digit, a plus, or minus "
					+ "character.\n";
			return errorTermErrorMessage + errorTermDisplayInput(input, errorTermCurrentCharNdx);
		
		case 8:
			// States 8 is not a final states with only one possible
			  errorTermErrorMessage = "This character can only be \".\" \n";
			  return errorTermErrorMessage + errorTermDisplayInput(input, errorTermCurrentCharNdx);
				
		
		default:
			return "";
		}
	}
	
}
