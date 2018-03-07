package quiz.main;

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
	
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Multiple Choice System"); // Probably should change the name idk
		primaryStage.getIcons().add(new Image("icon_1.png")); // import once finished making
		primaryStage.setResizable(false); // Locks size so doesn't interfere with CSS
		
		loadMainLayout();
		showLoadScreen();
		
	}
	
	// Load in the base layout FXML file for the UI
	private void loadMainLayout() throws IOException {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/BaseLayout.fxml"));
			mainLayout = (BorderPane) loader.load();
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch (IOException e) {
			throw new IOException("Error loading the base layout");
		}
		
	}
	
	
	// Loads in the main menu to select quizzes
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
			throw new IOException("Error with loading Main Menu");
		}
	}
	
	
	// Loads in the About Menu 
	private void showAboutScreen() throws IOException {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/About.fxml"));
			AnchorPane loadView = (AnchorPane) loader.load();
			mainLayout.setCenter(loadView);
			LoadViewController viewController = loader.getController();
			viewController.setMainApp(this);
			load = viewController;
			
		} catch (IOException e) {
			throw new IOException("Error with loading the about menu");
		}
		
	}
	
	
	// Allows user to revisit incorrect answers if allowed
	public void resumeQuiz() throws IOException{
		showQuizView();
	}
	
	// Restart quiz function
	public void restartQuiz() throws IOException{
		numOfAttempts = 0;
		quizDesign.startGame();
		showQuizView();		
	}
	
	// Load the quiz in
	public void loadQuiz() throws IOException{
		
		numOfAttempts = 0;
		this.quizDesign = new QuizModel();
		showQuizView();
		
	}
	
	private void showQuizView() throws IOException {
		
		defaultNumOfAttempts = 0;
		numOfAttempts ++;
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
				
			} catch (throws IOException e) {
				throw new IOException("Error with Quiz View");
			}
		}
		
	}
	
	
	//
	private void showQuiz(int index) throws IOException {
		
		view.setProgress((double) (1 + defaultNumOfAttempts + quizDesign.getScore()) / (double) quizDesign.getTotalScore());
		if (index < quizDesign.getGameSize())
			view.showQuiz(quizDesign.getQuestion(index), quizDesign.getAllAnswers(index));
		else
			throws new IOException("Error with something?");
	}
	
	public void stepQuiz(String pick) throws IOException{
		if (pick.equals(quizDesign.getCorrect(defaultNumOfAttempts)))
			quizDesign.remove(defaultNumOfAttempts);
		else
			defaultNumOfAttempts ++;
		
		if (defaultNumOfAttempts < quizDesign.getGameSize()) {
			showQuiz((defaultNumOfAttempts));
		} else {
			showResult();
			
		}
	}

	
	// Show results and attempts taken
	private void showResult() throws IOException{
		showLoadScreen();
		load.showResult(quizDesign.getScore(), quizDesign.getTotalScore(), numOfAttempts);
	}
	
	
	// Load main menu. Could probably make it redundent...
	public void mainMenu() throws IOException {
		showLoadScreen();
	}
	
	public void aboutScreen() throws IOException { 
		showAboutScreen();
	}
	
	public void Exit() throws IOException {
		System.exit(0);
	}
	
	//DO NOT DELETE THIS AGAIN I ALMOST CRIED FROM LOSING EVERYTHING
	public static void main(String[] args) {
		launch(args);
	}
	
}