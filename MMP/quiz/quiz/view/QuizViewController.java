package quiz.view;

import quiz.Controller;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class QuizViewController {
	private Controller mainApp;		// Reference to the main application
	@FXML private Label question; // The question
	@FXML private RadioButton optionA;  // The options
	@FXML private RadioButton optionB;
	@FXML private RadioButton optionC;
	@FXML private RadioButton optionD;
	@FXML private RadioButton optionE;
	@FXML private RadioButton optionF;
	@FXML private ToggleGroup optionGroup;
	private RadioButton[] radioButtons;
	@FXML private ProgressBar progressBar; 
	@FXML private Button buttonNext;
	@FXML private Button buttonSkip;
	@FXML private Button buttonBack;
	
	//load in features
	public QuizViewController() {
		question = new Label("");
		optionGroup = new ToggleGroup();
		progressBar = new ProgressBar(0);
	}

	//sets everything up after loading fxml in
	@FXML
	private void initialize() { 
		radioButtons = new RadioButton[6];
		radioButtons[0] = optionA; 
		radioButtons[1] = optionB; 
		radioButtons[2] = optionC;	// Allocating radio buttons to the options
		radioButtons[3] = optionD; 
		radioButtons[4] = optionE; 
		radioButtons[5] = optionF;
		optionA.setToggleGroup(optionGroup); 
		optionB.setToggleGroup(optionGroup);
		optionC.setToggleGroup(optionGroup); 
		optionD.setToggleGroup(optionGroup);
		optionE.setToggleGroup(optionGroup); 
		optionF.setToggleGroup(optionGroup);
	}
	//set up program in the main screen (easier to make using fxml)
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;	
	}
	
	@FXML private void rbClicked() { buttonNext.setDisable(false); }
	@FXML private void buttonNext() throws IOException { getNextQuestion(); }
	@FXML private void buttonSkip() throws IOException { skipQuestion(); }
	@FXML private void buttonBack() throws IOException { getPrevQuestion(); }

	private void getNextQuestion() throws IOException {
		String picked = ((Labeled) optionGroup.getSelectedToggle()).getText();
		mainApp.loadNextQuestion(picked);
		System.out.println(Controller.questionNum);
	}
	
	private void skipQuestion() throws IOException {
		mainApp.skipQuestion();
		System.out.println(Controller.questionNum);

	}
	
	private void getPrevQuestion() throws IOException {
		mainApp.prevQuestion();
		System.out.println(Controller.questionNum);

	}

	//makes features visible
	public void showQuiz(String question, String[] allAnswers){
		this.question.setText(question + "?"); //Make question mark conditional if possible
		this.question.setVisible(true);
		this.buttonNext.setDisable(true);
		
		//Once I find a fix, remove the below line
		this.buttonBack.setVisible(false);
		
		//defaultNOA for not going back on correct ones
		// QUESTION NUM WORKS BUT IT REMOVES THE QUESTION!
		
		//Ok so it currently works but doesn't remove the question which is annoying...
		
		if(Controller.questionNum < 1){
			buttonBack.setDisable(true);
		} else { buttonBack.setDisable(false);}
		
		
		this.buttonSkip.setDisable(false);
		for (int i = 0; i < 6; i++){
			if (i >= allAnswers.length) {
				radioButtons[i].setText("");
				radioButtons[i].setSelected(false);
				radioButtons[i].setVisible(false);
			} else {
				radioButtons[i].setText(allAnswers[i]);
				radioButtons[i].setSelected(false);
				radioButtons[i].setVisible(true);
			}
		}
	}
	
	public void setProgress(double progress){
		progressBar.setProgress(progress);
	}
}
