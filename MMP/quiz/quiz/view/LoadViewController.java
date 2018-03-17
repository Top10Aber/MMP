package quiz.view;

import quiz.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoadViewController
{
	private Controller   mainApp;		// call back to the main application to load it all in
	@FXML private Label  titleText;	// load fxml things
	@FXML private Label  result;
	@FXML private Button buttonResume;
	@FXML private Button buttonRestart;
	@FXML private Button buttonLoad;
	@FXML private Button buttonMenu;
	@FXML private Button buttonAbout;
	@FXML private Button buttonExit;
	
	//features of load menu
	public LoadViewController() {
		titleText = new Label();
		result = new Label();
	}

	// call back to the main application to load it all in
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;	
	}
	
	@FXML private void quit() { System.exit(0); }
	
	// when user clicks button
	@FXML private void buttonResume() throws Exception  	{ mainApp.resumeQuiz();  }
	@FXML private void buttonRestart() throws Exception	    { mainApp.restartQuiz(); }
	@FXML private void buttonLoad() throws Exception		{ mainApp.loadQuiz();    }
	@FXML private void buttonMenu() throws Exception        { mainApp.mainMenu();    }
	@FXML private void buttonAbout() throws Exception	    { mainApp.aboutScreen(); } 
	@FXML private void buttonExit() throws Exception	    { mainApp.Exit();        } 
	
	
	/*
	private boolean assessMode = false;
	
	private void assessMode() {
		System.out.println("test");
	}
	
	*/
	
	//loads results and attempts
	public void showResult(int score, int max, int attempt){
		buttonLoad.setVisible(false);
		buttonAbout.setVisible(false);
		buttonMenu.setVisible(true);
		buttonRestart.setVisible(true);
		titleText.setText("Result from Quiz:"); //text
		
		
		//if(assessMode == false) {
			//All correct:
			if (score == max) { 
				result.setText("Congratulations, you scored the full " + score + " points!\n"
						+ "(It took " + attempt + " attempt" + (attempt > 1 ? "s)" :")")); //REFERENCE THIS !!!!!!!!!!!!!!!!!
				buttonResume.setVisible(false);//full marks dont get to revisit obviously
			//None correct:
			} else if (score == 0) {
				result.setText("Sorry, you didn't have any correct answers. Try again");
				buttonResume.setVisible(true);
			//Scored some, but not all/none
			} else {
				result.setText("You scored " + score + " out of " + max + " points. \n"  
						+ "Revisit the incorrect answers or return to the menu.");
				buttonResume.setVisible(true);
			}
			
	/*	} else {
			
			//disable closing 
			
			
			
			result.setText("You scored " + score + " out of " + max + " points. \n"
					+ "Please wait for the assessor to collect your score.");
			buttonMenu.setVisible(false);
			buttonRestart.setVisible(false);
			buttonExit.setVisible(false);
			
			assessMode();
		
		}*/
	
		}
}