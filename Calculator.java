package calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/******
 * <p> Title: Calculator Class. </p>
 * 
 * <p> Description: A JavaFX demonstration application and baseline for a sequence of projects </p>
 * 
 * <p> Copyright: Lynn Robert Carter Â© 2016 </p>
 * 
 * @author Lynn Robert Carter
 *         Sanchit
 * 
 * @version 9:45	2018-04-28 The mainline of a JavaFX-based GUI implementation of a double calculator with error terms
 * 
 */

public class Calculator extends Application {
	
	public final static double WINDOW_WIDTH = 850;
	public final static double WINDOW_HEIGHT = 500;
	public UserInterface theGUI;

	/**********
	 * This is the start method that is called once the application has been loaded into memory and is 
	 * ready to get to work.
	 * 
	 * In designing this application I have elected to IGNORE all opportunities for automatic layout
	 * support and instead have elected to manually position each GUI element and its properties in
	 * order to exercise complete control over the user interface look and feel.
	 * 
	 */
	
	@Override
	public void start(Stage theStage) throws Exception {
		theStage.setTitle("Sanchit's Calculator");				// Label the stage (a window)
		
		Pane theRoot = new Pane();							// Create a pane within the window
		
		theGUI = new UserInterface(theRoot);					// Create the Graphical User Interface
		
		Scene theScene = new Scene(theRoot, WINDOW_WIDTH, WINDOW_HEIGHT);	// Create the scene
		
		theStage.setScene(theScene);							// Set the scene on the stage
		
		theStage.show();
				
	}
	


	public static void main(String[] args) {						// This method may not be required
		launch(args);											// for all JavaFX applications using
	}															// other IDEs.
}
