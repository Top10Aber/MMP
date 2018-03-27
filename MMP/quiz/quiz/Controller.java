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
	private BorderPane baseLayout;
	private QuizModel quizDesign;
	private QuizViewController view;
	private LoadViewController load;
	public static int defaultNumOfAttempts;
	static int numOfAttempts;
	public static int questionNum;

	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Educational Quiz System"); //Title of the program
		primaryStage.getIcons().add(new Image("icon_1.png")); // Change to something relevant
		primaryStage.setResizable(false);  //Locks size 
		//REFERENCE: https://stackoverflow.com/questions/5625436/how-can-i-remove-just-the-maximize-button-from-a-jframe
		// Check if I need to reference in code
		
		//System.out.println("javafx.version: " + System.getProperty("javafx.version"));
		
		loadBaseLayout();
		showLoadScreen();
	}

	//things load into this
	private void loadBaseLayout() throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/BaseLayout.fxml"));
			baseLayout = (BorderPane) loader.load();
			Scene scene = new Scene(baseLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			throw new IOException("Error with loading the main layout.");
		}
	}

	// Loads the load thing menu
	public void showLoadScreen() throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("view/MainMenuView.fxml"));
			AnchorPane loadView = (AnchorPane) loader.load();
			baseLayout.setCenter(loadView);
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
			baseLayout.setCenter(loadView);
			LoadViewController viewController = loader.getController();
			viewController.setMainApp(this);
			load = viewController;				

		} catch (IOException e) {
			throw new IOException("Error with loading About Menu");
		}
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
	public void showQuizView() throws IOException {
		defaultNumOfAttempts = 0;
		questionNum = 0;
		numOfAttempts = numOfAttempts + 1;
		if (quizDesign.getGameSize() > 0) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Controller.class.getResource("view/QuizView.fxml"));
				AnchorPane quizView = (AnchorPane) loader.load();
				baseLayout.setCenter(quizView);
				QuizViewController viewController = loader.getController();
				viewController.setMainApp(this);	
				view = viewController;				
				showQuiz(defaultNumOfAttempts);

			} catch (IOException e) {
				throw new IOException("Error with loading Quiz View");
			}
		}
	}

	//comment this
	private void showQuiz(int index) throws IOException{
		view.setProgress((double) (1 + defaultNumOfAttempts + quizDesign.getScore()) / (double) quizDesign.getTotalScore());
		if (index < quizDesign.getGameSize())
			view.showQuiz(quizDesign.getQuestion(index), quizDesign.getAllAnswers(index));
		else
			throw new IOException("Error showing quiz");
	}

	public void loadNextQuestion(String pick) throws IOException{
		
		//Find a way to go back but also remove correct ones
		
		if (pick.equals(quizDesign.getCorrect(defaultNumOfAttempts))){
			quizDesign.remove(defaultNumOfAttempts); //This is the problem line
			questionNum ++;
		} else { 
		
			defaultNumOfAttempts = defaultNumOfAttempts + 1;
			questionNum ++;
			
		}

		if (defaultNumOfAttempts < quizDesign.getGameSize()){
			showQuiz((defaultNumOfAttempts));
		} else {
			showResult();
		}
	}
	
	public void skipQuestion() throws IOException {

		defaultNumOfAttempts = defaultNumOfAttempts + 1;
		questionNum ++;
		if (defaultNumOfAttempts < quizDesign.getGameSize()){
			showQuiz((defaultNumOfAttempts));
		} else {
			showResult();
		}
	}
	
	
	
	
	// Currently does nothing until I either fix removal or remove this feature :( 
/*	public void prevQuestion() throws IOException {
		questionNum --;
		defaultNumOfAttempts = defaultNumOfAttempts - 1;
			
		}*/

	
	
	
	//Show results and attempts
	private void showResult() throws IOException{
		showLoadScreen(); 
		load.showResult(quizDesign.getScore(), quizDesign.getTotalScore(), numOfAttempts);
	}
	
	public void Exit() throws IOException {
		System.exit(0);
	 }
	
	//DO NOT DELETE THIS AGAIN I ALMOST CRIED AFTER LOSING EVERYTHING
	public static void main(String[] args) {
		launch(args);
	}

}