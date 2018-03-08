package quiz.view;

import quiz.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoadViewController
{
	private Controller mainApp;		// call back to the main application to load it all in
	@FXML private Label titleText;	// load fxml things
	@FXML private Label result;
	@FXML private Button bResume;
	@FXML private Button bRestart;
	@FXML private Button bLoad;
	@FXML private Button bMenu;
	@FXML private Button bAbout;
	@FXML private Button bExit;
	
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
	@FXML private void bResume() throws Exception	{ mainApp.resumeQuiz(); }
	@FXML private void bRestart() throws Exception	{ mainApp.restartQuiz(); }
	@FXML private void bLoad() throws Exception		{ mainApp.loadQuiz(); }
	@FXML private void bMenu() throws Exception     { mainApp.mainMenu(); }
	@FXML private void bAbout() throws Exception	{ mainApp.aboutScreen(); } 
	@FXML private void bExit() throws Exception	    { mainApp.Exit(); } 
	
	
	/*
	private boolean assessMode = false;
	
	private void assessMode() {
		System.out.println("test");
	}
	
	*/
	
	//loads results and attempts
	public void showResult(int score, int max, int attempt){
		bLoad.setVisible(false);
		bAbout.setVisible(false);
		bMenu.setVisible(true);
		bRestart.setVisible(true);
		titleText.setText("Result from Quiz:"); //text
		
		
		//if(assessMode == false) {
			//All correct:
			if (score == max) { 
				result.setText("Congratulations, you scored the full " + score + " points!\n"
						+ "(It took " + attempt + " attempt" + (attempt > 1 ? "s)" :")")); //REFERENCE THIS !!!!!!!!!!!!!!!!!
				bResume.setVisible(false);//full marks dont get to revisit obviously
			//None correct:
			} else if (score == 0) {
				result.setText("Sorry, you didn't have any correct answers. Try again");
				bResume.setVisible(true);
			//Scored some, but not all/none
			} else {
				result.setText("You scored " + score + " out of " + max + " points. \n"  
						+ "Revisit the incorrect answers or return to the menu.");
				bResume.setVisible(true);
			}
	/*	} else {
			
			//disable closing 
			
			
			
			result.setText("You scored " + score + " out of " + max + " points. \n"
					+ "Please wait for the assessor to collect your score.");
			bMenu.setVisible(false);
			bRestart.setVisible(false);
			bExit.setVisible(false);
			
			assessMode();
		
		}*/
	
		}
}