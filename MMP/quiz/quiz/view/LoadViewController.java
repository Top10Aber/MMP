package quiz.view;

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
import quiz.Controller;

public class LoadViewController {
	private Controller mainApp; // call back to the Controller method to load it things in
	@FXML private Label headline;
	@FXML private Label titleText; // load fxml things
	@FXML private Label result;
	@FXML private Button buttonResume;
	@FXML private Button buttonRestart;
	@FXML private Button buttonLoad;
	@FXML private Button buttonMenu;
	@FXML private Button buttonAbout;
	@FXML private Button buttonExit;
	@FXML private CheckBox assessment;

	// features of load menu
	public LoadViewController() {
		titleText = new Label();
		result = new Label();
	}

	// call back to the Controller application to load it all in
	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;
	}

	@FXML private void quit() {
		System.exit(0);
	}

	// when user clicks button
	@FXML private void buttonResume() throws Exception {
		mainApp.quizMenu();
	}

	@FXML private void buttonRestart() throws Exception {
		mainApp.restart();
	}

	@FXML private void buttonLoad() throws Exception {
		mainApp.generate();
	}

	@FXML private void buttonMenu() throws Exception {
		mainApp.mainMenu();
	}

	@FXML private void buttonAbout() throws Exception {
		mainApp.aboutScreen();
	}

	@FXML private void buttonExit() throws Exception {
		quit();
	}

	
	public static boolean assessMode;
	// Credit to "Ben Cooper, MEng (Hons) MBCS" for showing me how to simplify this method
	public void checkEvent(ActionEvent event) {
		assessMode = assessment.isSelected();
		System.out.println("assessment: " + assessMode);
	}
	// End of credit to Ben

	private void assessMode() throws IOException {
		mainApp.layout.setOnCloseRequest(evt -> {
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

				assessMode = false;
				System.out.println("assessment: " + assessMode);

			}
		});

		panel.add(passwordField);
		passwordField.setBounds(5, 30, 70, 20);
		panel.add(button);
		button.setBounds(80, 30, 75, 20);
		panel.add(label);
		label.setBounds(30, 55, 120, 20);
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

	// loads results and attempts
	public void showResult(int score, int max, int attempt) throws IOException {
		buttonLoad.setVisible(false);
		buttonAbout.setVisible(false);
		buttonMenu.setVisible(true);
		buttonRestart.setVisible(true);
		titleText.setText("Result from Quiz:"); // text
		assessment.setVisible(false);

		if (assessMode == false) {
			// All correct:
			if (score == max) {
				result.setText("Score: " + score + "/" + max + "\n" + "(It took " + attempt
						+ " attempt" + (attempt > 1 ? "s)" : ")")); //Reference [2]
				
				/*
				 * Reference [2] 
				 * Authors name: "Cafe Au lait" ??? Interesting name  
				 * Title: "The ? : operator in Java" 
				 * Type: Tutorial code
				 * Source URL: http://www.cafeaulait.org/course/week2/43.html
				 * Description: Wanted to use the conditional operator to show an "S" if plural. 
				 */
				
				buttonResume.setVisible(false);// full marks dont get to revisit obviously
			
			// None correct:
			} else if (score == 0) {
				result.setText("Score: 0/" + max);
				buttonResume.setVisible(true);
				

			// Scored some, but not all/none	
			} else {
				result.setText("Score: " + score + "/" + max + "\n"
						+ "Revisit the incorrect answers or return to the menu.");
				buttonResume.setVisible(true);
			}

		} else {
			assessment.setVisible(false);
			result.setText("Score: "+ score + "/" + max + "\n"
					+ "Please wait for the assessor to collect your score.");
			buttonMenu.setVisible(false);
			buttonRestart.setVisible(false);
			buttonExit.setVisible(false);
			assessMode();
		}
	}
}
