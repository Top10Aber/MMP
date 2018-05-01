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
	
	public Stage layout;
	private BorderPane baseLayout;
	private QuizModel quizModel;
	private QuizViewController view;
	private LoadViewController load;
	public static int defaultNumOfAttempts;
	static int numOfAttempts;
	public static int questionNum;

		
	public void start(Stage layout) throws Exception {
		this.layout = layout;
		this.layout.setTitle("Educational Quiz System"); //Title of the program
		layout.getIcons().add(new Image("icon_1.png")); // still not happy with it but whatever.
		layout.setResizable(false);  //Locks size, Referenced below
		
		/*	
	 	Reference
		- Authors name: https://stackoverflow.com/users/373653/sjr
		- Title: "How can I remove just the Maximize button from a JFrame?"
		- Type: Tutorial code
		- Source URL: https://stackoverflow.com/questions/5625436/how-can-i-remove-just-the-maximize-button-from-a-jframe
	    */
		
		// Check if I need to reference in code and HOW!!!!
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
			layout.setScene(scene);
			layout.show();

		} catch (IOException e) {
			throw new IOException("Error with loading the Controller layout.");
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
			System.out.println("assessment: " + LoadViewController.assessMode);

		} catch (IOException e) {
			throw new IOException("Error with loading the Controller Menu");
		}
		
		
		layout.setOnCloseRequest(evt -> {
    		layout.close();
    		});
	}
	
	// Loads the about menu
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
		quizModel.startGame();
		showQuizView();
	}
	
	public void loadQuiz() throws Exception{
		numOfAttempts = 0;
		this.quizModel = new QuizModel();
		showQuizView();
	}

	//Loads the quiz
	public void showQuizView() throws IOException {
		defaultNumOfAttempts = 0;
		questionNum = 0;
		numOfAttempts = numOfAttempts + 1;
		if (quizModel.getGameSize() > 0) {
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
	
	private void showQuiz(int index) throws IOException{
		view.setProgress((double) (1 + defaultNumOfAttempts + quizModel.getScore()) / (double) quizModel.getTotalScore());
		if (index < quizModel.getGameSize())
			view.showQuiz(quizModel.getQuestion(index), quizModel.getAllAnswers(index));
		else
			throw new IOException("Error showing quiz");
	}

	public void loadNextQuestion(String pick) throws IOException{
		
		if (pick.equals(quizModel.getCorrect(defaultNumOfAttempts))){
			quizModel.remove(defaultNumOfAttempts); 
			questionNum ++;
		} else { 
		
			defaultNumOfAttempts = defaultNumOfAttempts + 1;
			questionNum ++;
			
		}

		if (defaultNumOfAttempts < quizModel.getGameSize()){
			showQuiz((defaultNumOfAttempts));
		} else {
			showResult();
		}
	}
	
	public void skipQuestion() throws IOException {

		defaultNumOfAttempts = defaultNumOfAttempts + 1;
		questionNum ++;
		if (defaultNumOfAttempts < quizModel.getGameSize()){
			showQuiz((defaultNumOfAttempts));
		} else {
			showResult();
		}
	}
	
	
	//Show results and attempts
	private void showResult() throws IOException{
		showLoadScreen(); 
		load.showResult(quizModel.getScore(), quizModel.getTotalScore(), numOfAttempts);
	}
	
	public void Exit() throws IOException {
		System.exit(0);
	 }

	public static void main(String[] args) {
		launch(args);
	}

}