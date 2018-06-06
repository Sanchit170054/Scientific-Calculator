
package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import calculator.BusinessLogic;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Sanchit
 * 
 * @version 10:50	2018-02-25 The JavaFX-based GUI for the implementation of a calculator
 * 
 * @version 10:50    2018-03-23 The JavaFX-based GUI for the implementation of a calculator along with error term values 
 * 
 */

public class UserInterface {
	

	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 6;
	private final double BUTTON_OFFSET = BUTTON_WIDTH/2;

	// These are the application values required by the user interface
	private Label label_title = new Label("Double Calculator Enhanced");
	private Label label_Operand1 = new Label("First Operand");
	private Label label_Operand2 = new Label("Second Operand");
	private Label label_Result = new Label("Result");
	private Label label_Error1 = new Label("Error Term");
	private Label label_Error2 = new Label("Error Term");
	private Label label_ErrorTermResult = new Label("Error Term");
	
	private TextField text_Operand2 = new TextField();
	private TextField text_Operand1 = new TextField();
	private TextField text_Result = new TextField();
	private TextField text_Error1 = new TextField();	
	private TextField text_Error2 = new TextField();	
	private TextField text_ErrorTermResult = new TextField();
	
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Button button_Sqrt = new Button("\u221A");              // The root symbol: \u221A
	private Label label_PM1 = new Label("\u00B1");	       //The plus minus symbol for opernd1: \u00B1  
	private Label label_PM2 = new Label("\u00B1");	           //The plus minus symbol for operand2: \u00B1	
	private Label label_PM3 = new Label("\u00B1");	           //The plus minus symbol for operand2: \u00B1
	
	private Label label_errOperand1 = new Label("");                // Label to display specific 
	private Label label_errOperand2 = new Label("");                // error messages
	private Label label_errOperand1S = new Label("");               // Label to display a error message 
	private Label label_errOperand2P = new Label("");	            // when user tries to perform any function 
	private Label label_errResult = new Label("");
	
	private TextFlow err1;
    private Text operand1ErrPart1 = new Text();                     
    private Text operand1ErrPart2 = new Text();
    
    private TextFlow err2;
    private Text operand2ErrPart1 = new Text();
    private Text operand2ErrPart2 = new Text();
   
	private TextFlow err3;
    private Text errOperand1_ETPart1 = new Text();
    private Text errOperand1_ETPart2 = new Text();
    
	private TextFlow err4;
    private Text errOperand2_ETPart1 = new Text();
    private Text errOperand2_ETPart2 = new Text();
 
    
   	private Label label_errOperand1My = new Label(null);                // Label to display specific 
   	private Label label_errOperand2Code = new Label(null); 

    
    
	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used.
	
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();

	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	
	public UserInterface(Pane theRoot) {
	
		
			
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_title, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 2);
		
		//............First Operand........//
		
		// Label the first operand just above it, left aligned
				//@sid//
				setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 50);
				
				// Establish the first text input operand field and when anything changes in operand 1,
				// process both fields to ensure that we are ready to perform as soon as possible.
				//.............//
				setupTextUI(text_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-500, Pos.BASELINE_LEFT, 10, 70, true);
				text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
				
				// Move focus to the first error term when the user presses the enter (return) key
				//@sid//
				text_Operand1.setOnAction((event) -> { text_Error1.requestFocus(); });
				
				// Establish an error message for the first operand just above it with, left aligned
				//.............//
				setupLabelUI(label_errOperand1S, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 460, 30);
				label_errOperand1S.setTextFill(Color.RED);
				
				//Bottom proper error message
				//@sid//
				label_errOperand1.setTextFill(Color.RED);
				label_errOperand1.setAlignment(Pos.BASELINE_LEFT);
				setupLabelUI(label_errOperand1, "Arial", 14, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 128);
				
				//label the plus minus variable between the operand 1 and error 1.........
				//.............//
				//@sid//
				setupLabelUI(label_PM1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 383, 70);
				
				
		//..........Error Term 1.........//
		
		// Label the first error term just above it, left aligned.........
		//@sid//
		setupLabelUI(label_Error1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 410, 40);		
		
		// Establish the first text input error term field and when anything changes in error1,
		// process both fields to ensure that we are ready to perform as soon as possible............
		//.............//@sanchit
		setupTextUI(text_Error1, "Arial", 18, 280, Pos.BASELINE_LEFT, 410, 70, true);
		text_Error1.textProperty().addListener((observable, oldValue, newValue) -> {setError1(); });
		
		// Move focus to the second operand when the user presses the enter (return) key
		text_Error1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
		// Establish an error message for the first error operand just above it with, left aligned............. 
		//.............//
		setupLabelUI(label_errOperand1S, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 470, 130);
		label_errOperand1S.setTextFill(Color.RED);
		
		//Bottom proper error message
		//@sid//		
		label_errOperand1My.setTextFill(Color.RED);
		label_errOperand1My.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1My, "Arial", 14,  
						Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 400, 120);
		
		
		//..........Second Operand.........//
		
		// Label the second operand just above it, left aligned
		//.............//
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 155);
		
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		//@sid//
		setupTextUI(text_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-500, Pos.BASELINE_LEFT, 10, 180, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		
		// Move the focus to the result when the user presses the enter (return) key
		//.............//
		text_Operand2.setOnAction((event) -> { text_Error2.requestFocus(); });
		
		// Establish an error message for the second operand just above it with, left aligned		
		//@sid//
		setupLabelUI(label_errOperand2P, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 460, 150);
		label_errOperand2P.setTextFill(Color.RED);
		
		//Bottom proper error message				
		//.............//
		label_errOperand2.setTextFill(Color.RED);
		label_errOperand2.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand2, "Arial", 14, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 233);
		label_errOperand2.setTextFill(Color.RED);
		
		//label the variabel plus minus between operand 2 and error term 2............
		//@sid//
		setupLabelUI(label_PM2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 383, 180);
		
				
		
	    //.........Second Error Term...........//
		
		// Label the second error term just above it, left aligned
		//@sid//
		setupLabelUI(label_Error2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 410, 155);	

		// Establish the second text input for error 2 field and when anything changes in error2....... ,
		// process both fields to ensure that we are ready to perform as soon as possible.
		//.............//
		setupTextUI(text_Error2, "Arial", 18, 280, Pos.BASELINE_LEFT, 410, 180, true);
		text_Error2.textProperty().addListener((observable, oldValue, newValue) -> {setError2(); });
		
		// Move the focus to the error 2 when the user presses the enter (return) key
		text_Error2.setOnAction((event) -> { text_Result.requestFocus(); });		
		
		// Establish an error message for the second error term just above it with, left aligned		
				//@sid//
		setupLabelUI(label_errOperand2P, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 460, 230);
		label_errOperand2P.setTextFill(Color.RED);
		
		//Bottom proper error message				
		//.............//
		label_errOperand2Code .setTextFill(Color.RED);
		label_errOperand2Code .setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand2Code , "Arial", 14,  
						Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 400, 235);		
		
	    
		//.............Result Operand..........//
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 260);
				
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		//.............//
		setupTextUI(text_Result, "Arial", 18, Calculator.WINDOW_WIDTH-500, Pos.BASELINE_LEFT, 10, 290, false);
				
		// Establish an error message for the second operand just above it with, right aligned
		//@sid//
		setupLabelUI(label_errResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400,195);
		label_errResult.setTextFill(Color.RED);
	
		//label the plus minus between result and the error term result............
		//@sid//
		setupLabelUI(label_PM3, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 383, 290);
				
		
		//..........Result Error term.........//
		
		// Label the error term result just above the result output field, left aligned
		//.............//
		setupLabelUI(label_ErrorTermResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 410, 260);
				
		// Establish the Error term result output field.  It is not editable, so the text can be selected and copied, 
				// but it cannot be altered by the user.  The text is left aligned.
				//@sid//
		setupTextUI(text_ErrorTermResult, "Arial", 18, 280, Pos.BASELINE_LEFT, 410, 290, false);		
		
		// Establish an error message for the error term result operand just above it with, left aligned
				//.............//
		setupLabelUI(label_errResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 195);
		label_errResult.setTextFill(Color.RED);
		
		
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 350);
		button_Add.setOnAction((event) -> { AddOperands(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 350);
		button_Sub.setOnAction((event) -> { SubOperands(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 350);
		button_Mpy.setOnAction((event) -> { MpyOperands(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 350);
		button_Div.setOnAction((event) -> { DivOperands(); });
		
		// Establish the SQRT "root" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 350);
		button_Sqrt.setOnAction((event) -> { SqrtOperands(); });
		
		
		// Error Message for the Measured Value for operand 1
				operand1ErrPart1.setFill(Color.BLACK);
			    operand1ErrPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    operand1ErrPart2.setFill(Color.RED);
			    operand1ErrPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    err1 = new TextFlow(operand1ErrPart1, operand1ErrPart2);
				err1.setMinWidth(Calculator.WINDOW_WIDTH-10); 
				err1.setLayoutX(22);  
				err1.setLayoutY(100);
		
		// Error Message for the Measured Value for operand 2
				operand2ErrPart1.setFill(Color.BLACK);
			    operand2ErrPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    operand2ErrPart2.setFill(Color.RED);
			    operand2ErrPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    err2 = new TextFlow(operand2ErrPart1, operand2ErrPart2);
				err2.setMinWidth(Calculator.WINDOW_WIDTH-10); 
				err2.setLayoutX(22);  
				err2.setLayoutY(210);	
				
				// Error Message for the Error Term for operand 1
			    errOperand1_ETPart1.setFill(Color.BLACK);
			    errOperand1_ETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    errOperand1_ETPart2.setFill(Color.RED);
			    errOperand1_ETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    err3 = new TextFlow(errOperand1_ETPart1, errOperand1_ETPart2);
				err3.setMinWidth(Calculator.WINDOW_WIDTH-10); 
			    err3.setLayoutX(422);  
			    err3.setLayoutY(100);
				
				// Error Message for the Error Term for operand 2
			    errOperand2_ETPart1.setFill(Color.BLACK);
			    errOperand2_ETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    errOperand2_ETPart2.setFill(Color.RED);
			    errOperand2_ETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    err4 = new TextFlow(errOperand2_ETPart1, errOperand2_ETPart2);
				// Establish an error message for the second operand just above it with, left aligned
			    err4.setMinWidth(Calculator.WINDOW_WIDTH-10); 
			    err4.setLayoutX(422);  
			    err4.setLayoutY(210);
				
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_title, label_Operand1, text_Operand1, label_errOperand1,label_Operand2, text_Operand2, label_errOperand2, label_Result, text_Result, label_errResult, 
				 label_errOperand1S,label_errOperand2P,text_Error1, text_Error2, label_Error1,label_Error2, text_ErrorTermResult,label_PM3, label_ErrorTermResult,button_Add, button_Sub, button_Mpy, button_Div, button_Sqrt, err1, err2, err3, label_errOperand1My,err4, label_errOperand2Code,	label_PM1, label_PM2);
	}
	
	/*******
	 * This public methods invokes the methods of Calculator class and generate a specific error
	 * message when the user enters the value of operand1 and error term of operand1
	 * 
	 */
	public void message1() {
		String errMessage = CalculatorValue.checkMeasureValue(text_Operand1.getText());
		if (errMessage != "") {
			label_errOperand1.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			operand1ErrPart1.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			operand1ErrPart2.setText("\u21EB");
			errOperand1_ETPart1.setText("");
			errOperand1_ETPart2.setText("");
		}
		
		else {
			errMessage = CalculatorValue.checkErrorTerm(text_Error1.getText());
			if (errMessage != "") {
				label_errOperand1My.setText(CalculatorValue.errorTermErrorMessage);
				String input = CalculatorValue.errorTermInput;
				if (CalculatorValue.errorTermIndexofError <= -1) return;
				errOperand1_ETPart1.setText(input.substring(0, CalculatorValue.errorTermIndexofError));
				errOperand1_ETPart2.setText("\u21EB");
				operand1ErrPart1.setText("");
				operand1ErrPart2.setText("");
			}
			
		}
	}
	
	/*******
	 * This public methods invokes the methods of Calculator class and generate a specific error
	 * message when the user enters the value of operand2 and error term of operand1
	 * 
	 */
	public void message2() {
		String errMessage = CalculatorValue.checkMeasureValue(text_Operand2.getText());
		if (errMessage != "") {
			label_errOperand2.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			operand2ErrPart1.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			operand2ErrPart2.setText("\u21EB");
			errOperand2_ETPart1.setText("");
			errOperand2_ETPart2.setText("");
			
		}
		
		else {
			errMessage = CalculatorValue.checkErrorTerm(text_Error2.getText());
			if (errMessage != "") {
				label_errOperand2Code.setText(CalculatorValue.errorTermErrorMessage);
				String input = CalculatorValue.errorTermInput;
				if (CalculatorValue.errorTermIndexofError <= -1) return;
				errOperand2_ETPart1.setText(input.substring(0, CalculatorValue.errorTermIndexofError));
				errOperand2_ETPart2.setText("\u21EB");
				operand2ErrPart1.setText("");
				operand2ErrPart2.setText("");
			}
			
		}
	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void setOperand1() {
		text_Result.setText("");							// Any change of an operand probably invalidates
		text_ErrorTermResult.setText("");
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.setOperand1(text_Operand1.getText())) {	// Set the operand and see if there was an error
			label_errOperand1.setText("");					// If no error, clear this operands error
			label_errOperand1S.setText("");
			operand1ErrPart1.setText("");                   // Clear the first term of error part
			operand1ErrPart2.setText("");                   // Clear the second term of error part
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			message1();
	}
	
	/**********
	 * Private local method to set the value of the first operand error term given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	
	private void setError1() {
		label_errOperand1My.setText("");
		perform.setOperand1ET(text_Error1.getText());
		errOperand1_ETPart1.setText("");
		errOperand1_ETPart2.setText("");
		message1();
		}
	
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");								   // Any change of an operand probably invalidates
		text_ErrorTermResult.setText("");
		label_Result.setText("Result");				               // the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.setOperand2(text_Operand2.getText())) {        // Set the operand and see if there was an error
			label_errOperand2.setText("");                         // If no error, clear this operands error
			label_errOperand2P.setText("");
			operand2ErrPart1.setText("");                          // Clear the first term of error part
			operand2ErrPart2.setText("");                          // Clear the second term of error part
			if (text_Operand1.getText().length() == 0)             // If the other operand is empty, clear its error
				label_errOperand1.setText("");                     // as well.
		}
		else                                            // If there's a problem with the operand, display
			message2();
	}
	
	/**********
	 * Private local method to set the value of the second operand error term given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setError2() {
		label_errOperand2Code.setText("");
		perform.setOperand2ET(text_Error2.getText());
		errOperand2_ETPart1.setText("");
		errOperand2_ETPart2.setText("");
		message2();
		}
	
	
	/**********
	 * This method is called when an binary operation (expect square root) button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	
	
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {						    // Check the first.  If the string is not empty
			label_errOperand1S.setText(errorMessage1);			    // there's an error message, so display it.
			if (errorMessage2.length() > 0) {					    // Check the second and display it if there is
				label_errOperand2P.setText(errorMessage2);		    // and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;									    // Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					        // No error with the first, so check the second
			label_errOperand2P.setText(errorMessage2);			    // operand. If non-empty string, display the error
			return true;										    // message and return true... the second has an error
		}														    // Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1S.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				label_errOperand2P.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} 
		else if (!perform.getOperand2Defined()) {				    // If the first is defined, check the second. Both
			label_errOperand2P.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											    // Signal there are no issues with the operands
	}
	
	/**********
	 * This method is called when an binary operation (expect square root) button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssuesE() {
		
		if (label_errOperand1My == null) {
			return false;			
		}
		
		if (err4 != null) {
			return false;			
		}
		return true;											    // Signal there are no issues with the operands
	}
	
	
	
	/**********
	 * This method is called when square root button has been pressed. It assesses if there are issues 
	 * with either of the binary operand1 or it is not defined. If not return false (there are no issues)
	 * As to perform square root, we only need operand1 thus any value added in the second field is 
	 * automatically cleared when square root button is pressed
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	
	private boolean binaryOperandIssuesForSqrt() {
		
		String errorMessage1 = perform.getOperand1ErrorMessage();    // Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {						     // Check the first.  If the string is not empty
			label_errOperand1S.setText(errorMessage1);			     // there's an error message, so display it.
		    return true;
		}
			
		if (errorMessage2.length() > 0) {					         // Check is there is something in second operand, 
				text_Operand2.setText("");               		     // if it is then clear it.
														
			}
					                                         			
			text_Operand2.setText("");                  		     // if anything is in second operand field
			text_Error2.setText("");
			label_errOperand2P.setText("");                           // then clear it
		    
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						 // Check to see if the first operand is defined
			label_errOperand1S.setText("No value found");			 // If not, this is an issue for a binary operator
			text_Result.setText("");
			text_ErrorTermResult.setText("");
			return true;
		} 
		
		return false;											     // Signal there are no issues with the operand1
	}
	
	/*****************
	 * This private method is to check, if the error term are left empty then this should not effect the wrking 
	 * of calculator. A default value of 0.5 is set in both the error terms
	 */
	
	private void errorTermCheck() {
		if (text_Error1.getLength() == 0) {
			text_Error1.setText("0.5");
		}
		
		if (text_Error2.getLength() == 0) {
			text_Error2.setText("0.5");
		}
	}
	
	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, divide and square root) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	
	private void AddOperands(){
		
		errorTermCheck();
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues() || binaryOperandIssuesE()) {          // If there are issues with the operands, return	
			text_Result.setText("");
			text_ErrorTermResult.setText("");
			return;			}
												                    // without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.addition();						             // Call the business logic addition method
		String theAnswerET = perform.Errors(); 
		label_errResult.setText("");									         // Reset any result error messages from before
		if (theAnswer.length() > 0) {								             // Check the returned String to see if it is okay
			label_errOperand1S.setText("");
			label_errOperand2P.setText("");
			text_Result.setText(theAnswer);							             // If okay, display it in the result field and
			text_ErrorTermResult.setText(theAnswerET);
			label_Result.setText("Sum");								         // change the title of the field to "Sum"
		}
		else
		{														             // Some error occurred while doing the addition.
			text_Result.setText("");									         // Do not display a result if there is an error.				
			text_ErrorTermResult.setText("");
			label_Result.setText("Result");							             // Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	         // Display the error message.
		}
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void SubOperands(){
		errorTermCheck();
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues() || binaryOperandIssuesE()) {          // If there are issues with the operands, return	
			text_Result.setText("");
			text_ErrorTermResult.setText("");
			return;			}													     // without doing the computation
				
				
				String theAnswer = perform.subtraction();						 // Call the business logic subtraction method
				String theAnswerET = perform.Errors(); 
				label_errResult.setText("");									         // Reset any result error messages from before
				if (theAnswer.length() > 0) {								             // Check the returned String to see if it is okay
					label_errOperand1S.setText("");
					label_errOperand2P.setText("");
					text_Result.setText(theAnswer);							             // If okay, display it in the result field and
					text_ErrorTermResult.setText(theAnswerET);						     // If okay, display it in the result field and
					label_Result.setText("Difference");							 // change the title of the field to "Difference"
				}
				else 
				{													     	 // Some error occurred while doing the subtraction.
					text_Result.setText("");									 // Do not display a result if there is an error.				
					text_ErrorTermResult.setText("");
					label_Result.setText("Result");						         // Reset the result label if there is an error.
					label_errResult.setText(perform.getResultErrorMessage());	 // Display the error message.
				}
		
	}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void MpyOperands(){
		errorTermCheck();
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues() || binaryOperandIssuesE()) {          // If there are issues with the operands, return	
			text_Result.setText("");
			text_ErrorTermResult.setText("");
			return;			}													             // without doing the computation
		
		String theAnswer = perform.multiplication();				             // Call the business logic multiplication method
		String theAnswerET = perform.Errors(); 
		label_errResult.setText("");									         // Reset any result error messages from before
		
		if (theAnswer.length() > 0) {								             // Check the returned String to see if it is okay
			label_errOperand1S.setText("");
			label_errOperand2P.setText("");
			text_Result.setText(theAnswer);							             // If okay, display it in the result field and
			text_ErrorTermResult.setText(theAnswerET);						             // If okay, display it in the result field and
			label_Result.setText("Product");						             // change the title of the field to "Product"
		}
		else
		{														             // Some error occurred while doing the multiplication.
			text_Result.setText("");								             // Do not display a result if there is an error.				
			label_Result.setText("Result");						                 // Reset the result label if there is an error.
			text_ErrorTermResult.setText("");
			label_errResult.setText(perform.getResultErrorMessage());	         // Display the error message.
		}
        								
	}

	
	
	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void DivOperands(){
		errorTermCheck();
		if (binaryOperandIssues() || binaryOperandIssuesE()) {          // If there are issues with the operands, return	
			text_Result.setText("");
			text_ErrorTermResult.setText("");
			return;			}												             // without doing the computation
		
		double operand2 = Double.parseDouble(text_Operand2.getText());           // Fetching the value of operand2 and saving it in 
		                                                                         // double data type
		        
					
				if (operand2 != 0f ) {                                           // Check if the operand2 is zero or not  
				    	                                                         // 0f defines it for all float values like, 0.0, 0.00 .... etc
				    // If the operands are defined and valid, request the business logic method to do the divide and return the
				    // result as a String. If there is a problem with the actual computation, an empty string is returned
				    String theAnswer = perform.division();		       			 // Call the business logic division method
				    String theAnswerET = perform.Errors(); 
					label_errResult.setText("");									         // Reset any result error messages from before
			                                               				             // Check the returned String to see if it is okay
						label_errOperand1S.setText("");
						label_errOperand2P.setText("");
						text_Result.setText(theAnswer);							             // If okay, display it in the result field and
						text_ErrorTermResult.setText(theAnswerET);	     					 // If okay, display it in the result field and
					label_Result.setText("Division");	    				 // change the title of the field to "Division"
				
				}
				else
				{	                                                         // Some error occurred while doing the division.
					text_Result.setText("");			   			             // Do not display a result if there is an error.				
					label_Result.setText("Result");						         // Reset the result label if there is an error.
					text_ErrorTermResult.setText("");
					label_errResult.setText(perform.getResultErrorMessage());    // Display the error message.
					label_errOperand2P.setText("Invalid");             // Display error message if second operand is zero
				}								
	}
	
	/**********
	 * This is the square root routine.
	 * 
	 */
	private void SqrtOperands(){
		if (text_Error1.getLength() == 0) {    	// Check to see if both operands are defined and valid
			text_Error1.setText("0.5");
		}
		// Check to see if both operands are defined and valid
		if (binaryOperandIssuesForSqrt() || label_errOperand1My == null) {          // If there are issues with the operands,without doing the computation	
			text_Result.setText("");
			text_ErrorTermResult.setText("");
			return;			}												      	     
		   	   			     
				
				
				String theAnswer = perform.squareroot();		       			 // Call the business logic Root method			    			 // Reset any result error messages from before
				String theAnswerET = perform.Errors(); 
				                                   								         
				if (theAnswer.length() > 0) {								             // Check the returned String to see if it is okay
					label_errOperand1S.setText("");             // Reset any result error messages from before
					label_errOperand2P.setText("");
					text_Result.setText(theAnswer);							             
					text_ErrorTermResult.setText(theAnswerET);     						 // If okay, display it in the result field and
					label_Result.setText("Square root");	    				 // change the title of the field to "Square root"
				}
				else {	                                                         // Some error occurred while doing the square root.
					text_Result.setText(" ");			   			             // Do not display a result if there is an error.				
					text_ErrorTermResult.setText("");
					label_Result.setText("Result");						         // Reset the result label if there is an error.
					label_errResult.setText(perform.getResultErrorMessage());    // Display the error message.
	 	}								
	}
	
}
