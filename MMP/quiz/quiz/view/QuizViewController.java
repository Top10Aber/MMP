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
import javafx.scene.text.Font;

public class QuizViewController {
	private Controller mainApp;		// Reference to the Controller application
	@FXML private Label question; // The question
	@FXML private RadioButton optionA;  // The options
	@FXML private RadioButton optionB;
	@FXML private RadioButton optionC;
	@FXML private RadioButton optionD;
	@FXML private RadioButton optionE;
	@FXML private RadioButton optionF;
	@FXML private RadioButton optionG;
	@FXML private ToggleGroup optionGroup;
	private RadioButton[] radioButtons;
	@FXML private ProgressBar progressBar; 
	@FXML private Button buttonNext;
	@FXML private Button buttonSkip;
	@FXML private Button bigger;
	@FXML private Button reset;
	
	int size = 0;
	int qSize = 18;
	
	@FXML private void buttonBig() throws Exception {size = 25;			
													 question.setFont(new Font(size));
													 optionG.setFont(new Font(size));
													 optionA.setFont(new Font(size));
													 optionB.setFont(new Font(size));
													 optionC.setFont(new Font(size));
													 optionD.setFont(new Font(size));
													 optionE.setFont(new Font(size));
													 optionF.setFont(new Font(size));
													 this.bigger.setDisable(true);
													 this.reset.setDisable(false);
	}

	@FXML private void buttonReset() throws Exception {size = 14; 	
	 question.setFont(new Font(qSize));
	 optionA.setFont(new Font(size));
	 optionB.setFont(new Font(size));
	 optionC.setFont(new Font(size));
	 optionD.setFont(new Font(size));
	 optionE.setFont(new Font(size));
	 optionF.setFont(new Font(size));
	 this.bigger.setDisable(false);
	 this.reset.setDisable(true);
}
	
	//load in features
	public QuizViewController() {
		question = new Label("");
		optionGroup = new ToggleGroup();
		progressBar = new ProgressBar(0);
	}

	//sets everything up after loading fxml in
	@FXML
	private void initialize() { 
		radioButtons = new RadioButton[7];
		radioButtons[0] = optionA; 
		radioButtons[1] = optionB; 
		radioButtons[2] = optionC;	// Allocating radio buttons to the options
		radioButtons[3] = optionD; 
		radioButtons[4] = optionE; 
		radioButtons[5] = optionF;
		radioButtons[6] = optionG;
		optionA.setToggleGroup(optionGroup); 
		optionB.setToggleGroup(optionGroup);
		optionC.setToggleGroup(optionGroup); 
		optionD.setToggleGroup(optionGroup);
		optionE.setToggleGroup(optionGroup); 
		optionF.setToggleGroup(optionGroup);
		optionG.setToggleGroup(optionGroup);
	}
	//set up program in the Controller screen (easier to make using fxml)
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;	
	}
	
	@FXML private void rbClicked() { buttonNext.setDisable(false); }
	@FXML private void buttonNext() throws IOException { getNextQuestion(); }
	@FXML private void buttonSkip() throws IOException { skipQuestion(); }

	private void getNextQuestion() throws IOException {
		String picked = ((Labeled) optionGroup.getSelectedToggle()).getText();
		mainApp.loadNextQuestion(picked);
		System.out.println(Controller.questionNum);
	}
	
	private void skipQuestion() throws IOException {
		mainApp.skipQuestion();
		System.out.println(Controller.questionNum);

	}
	
	//makes features visible
	public void showQuiz(String question, String[] allAnswers){
		
		this.question.setText(question); 
		if(question.contains("?")){
			this.question.setText(question);
		} else {
			this.question.setText(question + "?");
		}
		
		this.buttonNext.setDisable(true);
		this.question.setVisible(true);
		this.buttonSkip.setDisable(false);
		for (int i = 0; i < 6; i++){
			if (i >= allAnswers.length) {
				radioButtons[i].setText("");
				radioButtons[i].setSelected(false);
				radioButtons[i].setVisible(false);
				radioButtons[6].setVisible(false);
				radioButtons[6].setSelected(false);
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