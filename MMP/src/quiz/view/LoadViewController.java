package quiz.view;

import quiz.main.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoadViewController {

	private Controller mainApp;
	@FXML private Label headline;
	@FXML private Label result;
	@FXML private Button bResume;
	@FXML private Button bRestart;
	@FXML private Button bLoad;
	@FXML private Button bMenu;
	@FXML private Button bAbout;
	@FXML private Button bExit;
	
	
	public LoadViewController() { 
		headline = new Label();
		result = new Label();
	}
	
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML private void quit() { System.exit(0); }
	
	@FXML private void bResume() throws Exception  { mainApp.resumeQuiz(); }
	@FXML private void bRestart() throws Exception { mainApp.restartQuiz(); }
	@FXML private void bLoad() throws Exception    { mainApp.loadQuiz(); }
	@FXML private void bMenu() throws Exception    { mainApp.mainMenu(); }
	@FXML private void bAbout() throws Exception   { mainApp.aboutScreen(); }
	@FXML private void bExit() throws Exception    { mainApp.Exit(); }
	
	
	public void showResult(int score, int max, int attempt) { 
		bLoad.setVisible(false);
		bAbout.setVisible(false);
		bMenu.setVisible(true);
		bRestart.setVisible(true);
		headline.setText("Results from Quiz: ");
		
		// All correct
		if (score == max) {
			result.setText("Congratulations, you scored the full " + score + " points! \n"
					+ "It took " + attempt + " attempts");
			bResume.setVisible(false); // full marks cant revisit any wrong ones
		// None correct
		} else if (score == 0) {
			result.setText("Sorry, you didn't get any correct. Try again.");
			bResume.setVisible(true);
		// Scored some, not all
		} else {
			result.setText("You scored " + score + " out of " + max + " points. \n"
					+ "Revisit the incorrect answers or return to the main menu.");
			bResume.setVisible(true);
		}
		
	}
		
}
