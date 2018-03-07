package quiz.view;

import quiz.main.Controller;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class QuizViewController {

	private Controller mainApp;
	@FXML private Label question;
	@FXML private RadioButton rbA;
	@FXML private RadioButton rbB;
	@FXML private RadioButton rbC;
	@FXML private RadioButton rbD;
	@FXML private RadioButton rbE;
	@FXML private RadioButton rbF;
	@FXML private ToggleGroup rbGroup;
	private RadioButton[] rB;
	@FXML private ProgressBar pB;
	@FXML private Button bNext;
	
	public QuizViewController() {
		question = new Label("");
		rbGroup = new ToggleGroup();
		pB = new ProgressBar(0);
	}
	
	@FXML
	private void initialize() {
		rB = new RadioButton[6];
		rB[0] = rbA;
		rB[1] = rbB;
		rB[2] = rbC;
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
	
	
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML private void rbClicked() { bNext.setDisable(false); }
	@FXML private void bNext() throws IOException { getNextQuiz(); }
	
	private void getNextQuiz() throws IOException {
		String picked = ((Labeled) rbGroup.getSelectedToggle()).getText();
		mainApp.stepQuiz(picked);
	}
	
	public void showQuiz(String question, String[] allAnswers) {
		this.question.setText(question + "?"); // needs a conditional thingy
		this.question.setVisible(true);
		this.bNext.setDisable(true);
		for (int i = 0; i < 6; i++) { 
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
	
	public void setProgress(double progress) { 
		pB.setProgress(progress);
	}
	
}
