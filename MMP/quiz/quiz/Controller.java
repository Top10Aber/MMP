package quiz;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import quiz.model.QuizModel;
import quiz.view.LoadViewController;
import quiz.view.QuizViewController;

public class Controller extends Application {
	
	private Stage primaryStage;
	private BorderPane mainLayout;
	private QuizModel quizDesign;
	private QuizViewController view;
	private LoadViewController load;
	private int defaultNumOfAttempts;
	private int numOfAttempts;

	// Finish the launch-sequence
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Multiple Choice System"); //Title of the program
		primaryStage.getIcons().add(new Image("icon_1.png")); // Change to something relevant
		primaryStage.setResizable(false);  //Locks size 
		//REFERENCE: https://stackoverflow.com/questions/5625436/how-can-i-remove-just-the-maximize-button-from-a-jframe
		
		loadMainLayout();
		showLoadScreen();
	}

	//things load into this
	private void loadMainLayout() throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/BaseLayout.fxml"));
			mainLayout = (BorderPane) loader.load();
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			throw new IOException("Error with loading the main layout.");
		}
	}

	// Loads the load thing menu
	private void showLoadScreen() throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/MainMenuView.fxml"));
			AnchorPane loadView = (AnchorPane) loader.load();
			mainLayout.setCenter(loadView);
			LoadViewController viewController = loader.getController();
			viewController.setMainApp(this);
			load = viewController;				

		} catch (IOException e) {
			throw new IOException("Error with loading the Main Menu");
		}
	}
	
	// Loads the load thing menu
	public void showAboutScreen() throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/About.fxml"));
			AnchorPane loadView = (AnchorPane) loader.load();
			mainLayout.setCenter(loadView);
			LoadViewController viewController = loader.getController();
			viewController.setMainApp(this);
			load = viewController;				

		} catch (IOException e) {
			throw new IOException("Error with loading the About Menu");
		}
	}
	
	//Loads the revisit wrong question function
	public void resumeQuiz() throws IOException{
		showQuizView();
	}

	//Restart quiz button
	public void restartQuiz() throws IOException {
		numOfAttempts = 0;
		quizDesign.startGame();
		showQuizView();
	}
	
	public void loadQuiz() throws Exception{
		numOfAttempts = 0;
		this.quizDesign = new QuizModel();
		showQuizView();
	}

	//Loads the quiz
	private void showQuizView() throws IOException {
		defaultNumOfAttempts = 0;
		numOfAttempts = numOfAttempts + 1;
		if (quizDesign.getGameSize() > 0) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Controller.class.getResource("view/QuizView.fxml"));
				AnchorPane quizView = (AnchorPane) loader.load();
				mainLayout.setCenter(quizView);
				QuizViewController viewController = loader.getController();
				viewController.setMainApp(this);	
				view = viewController;				
				showQuiz(defaultNumOfAttempts);

			} catch (IOException e) {
				throw new IOException("Error - fix me pls");
			}
		}
	}

	//comment this
	private void showQuiz(int index) throws IOException{
		view.setProgress((double) (1 + defaultNumOfAttempts + quizDesign.getScore()) / (double) quizDesign.getTotalScore());
		if (index < quizDesign.getGameSize())
			view.showQuiz(quizDesign.getQuestion(index), quizDesign.getAllAnswers(index));
		else
			throw new IOException("Error - fix me pls");
	}

	public void stepQuiz(String pick) throws IOException{
		if (pick.equals(quizDesign.getCorrect(defaultNumOfAttempts)))
			quizDesign.remove(defaultNumOfAttempts);
		else
			defaultNumOfAttempts = defaultNumOfAttempts + 1;

		if (defaultNumOfAttempts < quizDesign.getGameSize()){
			showQuiz((defaultNumOfAttempts));
		} else {
			showResult();
		}
	}

	//Show results and attempts
	private void showResult() throws IOException{
		showLoadScreen(); 
		load.showResult(quizDesign.getScore(), quizDesign.getTotalScore(), numOfAttempts);
	}
	
	public void mainMenu() throws IOException {
		showLoadScreen();
	 }
	
	public void aboutScreen() throws IOException {
		showAboutScreen();
	 }
	
	public void Exit() throws IOException {
		System.exit(0);
	 }
	
	//DO NOT DELETE THIS AGAIN I ALMOST CRIED AFTER LOSING EVERYTHING
	public static void main(String[] args) {
		launch(args);
	}

}