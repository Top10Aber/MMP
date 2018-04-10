package quiz.view;

import quiz.Controller;

import java.awt.Robot;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.Timer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class LoadViewController
{
	private Controller   mainApp;		// call back to the main application to load it all in
	@FXML private Label  headline;
	@FXML private Label  titleText;	// load fxml things
	@FXML private Label  result;
	@FXML private Button buttonResume;
	@FXML private Button buttonRestart;
	@FXML private Button buttonLoad;
	@FXML private Button buttonMenu;
	@FXML private Button buttonAbout;
	@FXML private Button buttonExit;
	@FXML private CheckBox assessment;
	
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
	@FXML private void buttonResume() throws Exception  	{ mainApp.showQuizView();      }
	@FXML private void buttonRestart() throws Exception	    { mainApp.restartQuiz();       }
	@FXML private void buttonLoad() throws Exception		{ mainApp.loadQuiz();          }
	@FXML private void buttonMenu() throws Exception        { mainApp.showLoadScreen();    }
	@FXML private void buttonAbout() throws Exception	    { mainApp.showAboutScreen();   } 
	@FXML private void buttonExit() throws Exception	    { quit();                      } 
	
	static boolean assessMode;

	public void checkEvent(ActionEvent event) {
		assessMode = assessment.isSelected();
		System.out.println("assessment: " + assessMode);
		}
	
	private void assessMode() throws IOException {
		
		mainApp.primaryStage.setOnCloseRequest(evt -> {
    		evt.consume();
    		});
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JPasswordField passwordField = new JPasswordField();
		JButton button = new JButton("Submit");
		JLabel label = new JLabel("");
		JLabel label2 = new JLabel("Enter assessor password");
		JLabel label3 = new JLabel("");
		frame.setAlwaysOnTop(true);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String get = "password";
				@SuppressWarnings("deprecation")
				String test = passwordField.getText();

				if (get.equals(test)) {
					label.setText("Correct Password");
					label3.setText("");	
					Timer timer = new Timer(1000, new ActionListener() {
						@Override
						public void actionPerformed(java.awt.event.ActionEvent arg0) {
							frame.dispose();
	                        buttonMenu.setVisible(true);
							
						}
	                });
	                timer.start();
				} else {
					label3.setText("Invalid password");
					label.setText("");
					passwordField.setText("");
				}
				
			}
		});

		panel.add(passwordField);
		passwordField.setBounds(5, 30, 70, 20);
		panel.add(button);
		button.setBounds(80, 30, 75, 20);
		panel.add(label);
		label.setBounds(50, 55, 100, 20);
		panel.add(label2);
		label2.setBounds(7, 5, 500, 20);
		panel.add(label3);
		label3.setBounds(30, 55, 100, 20);
		frame.setSize(162, 75);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	
	
	//loads results and attempts
	public void showResult(int score, int max, int attempt) throws IOException{
		buttonLoad.setVisible(false);
		buttonAbout.setVisible(false);
		buttonMenu.setVisible(true);
		buttonRestart.setVisible(true);
		titleText.setText("Result from Quiz:"); //text
		assessment.setVisible(false);
		
		if(assessMode == false) {
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
			
		} else {
			
			assessment.setVisible(false);
			
			result.setText("You scored " + score + " out of " + max + " points. \n"
 					+ "Please wait for the assessor to collect your score.");
			buttonMenu.setVisible(false);
			buttonRestart.setVisible(false);
			buttonExit.setVisible(false);
			
			assessMode();
	
		}
	}	
}
