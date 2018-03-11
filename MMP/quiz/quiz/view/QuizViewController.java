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
	@FXML private RadioButton rbA;  //Rename all this shit
	@FXML private RadioButton rbB;
	@FXML private RadioButton rbC;
	@FXML private RadioButton rbD;
	@FXML private RadioButton rbE;
	@FXML private RadioButton rbF;
	@FXML private ToggleGroup rbGroup;
	private RadioButton[] rB;		// For iteration-purpose only (indexing)
	//@FXML private ProgressBar pB; //remove for "adaptions"
	@FXML private Button bNext;

	
	//load in features
	public QuizViewController() {
		question = new Label("");
		rbGroup = new ToggleGroup();
		//pB = new ProgressBar(0);
	}

	//sets everything up after loading fxml in
	@FXML
	private void initialize() { 
		rB = new RadioButton[6];
		rB[0] = rbA; 
		rB[1] = rbB; 
		rB[2] = rbC;	// House-keeping
		rB[3] = rbD; 
		rB[4] = rbE; 
		rB[5] = rbF;
		rbA.setToggleGroup(rbGroup); 
		rbB.setToggleGroup(rbGroup);
		rbC.setToggleGroup(rbGroup); 
		rbD.setToggleGroup(rbGroup);
		rbE.setToggleGroup(rbGroup); 
		rbF.setToggleGroup(rbGroup);
	}
	//set up program in the main screen (easier to make using fxml)
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;	
	}
	
	@FXML private void rbClicked() { bNext.setDisable(false);	}
	@FXML private void bNext() throws IOException { getNextQuiz(); }

	private void getNextQuiz() throws IOException {
		String picked = ((Labeled) rbGroup.getSelectedToggle()).getText();
		mainApp.stepQuiz(picked);
	}

	//makes features visible
	public void showQuiz(String question, String[] allAnswers){
		this.question.setText(question + "?"); //Make question mark conditional if possible
		this.question.setVisible(true);
		this.bNext.setDisable(true);
		for (int i = 0; i < 6; i++){
			if (i >= allAnswers.length) {
				rB[i].setText("");
				rB[i].setSelected(false);
				rB[i].setVisible(false);
			} else {
				rB[i].setText(allAnswers[i]);
				rB[i].setSelected(false);
				rB[i].setVisible(true);
			}
		}
	}
	
	//updates progress on pB
	//play with it see if changable
	/*public void setProgress(double progress){
		pB.setProgress(progress);
	}*/
}
