package quiz.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class QuizModel {
	public List<AQuiz> quiz;	// Quiz as loaded from file.
	private List<AQuiz> game;	//clone of the quiz but the correctly answered fellas are removed to let the user revisit the other ones 

	//makes quiz from imported file
	public QuizModel() throws Exception{
		setQuiz(new LinkedList<AQuiz>());
		FileImport.importQuiz(this);
		startGame();
	}
	
	//the clone of the quiz so can be redone
	@SuppressWarnings("unchecked") //might cause issues else where- but probably fine... 
	public void startGame(){
		game = (List<AQuiz>) ((LinkedList<AQuiz>) getQuiz()).clone();
	}
	
	public void addAQuiz(List<String> list){
		getQuiz().add(new AQuiz(list));
	}
	
	// Getters for the controller:

	//loads question
	public String getQuestion(int index){
		return game.get(index).getQuestion();
	}

	//loads correct ans
	public String getCorrect(int index){
		return game.get(index).getCorrect();
	}
	
	//loads other ans
	public String[] getAllAnswers(int index){
		return game.get(index).getAllAnswers();
	}

	
	//counts the number of questions so it can update progress bar or say "wow you got X out of a possible Y!"
	//that could be a feature actually.. put it above the progress bar maybe?
	public int getGameSize(){
		return game.size();
	}

	//load total score
	public int getTotalScore(){
		return getQuiz().size();
	}
	
	//adds up current score
	public int getScore(){
		return getQuiz().size() - game.size();
	}
	
	//removes correct ones from clone so can be redone
	public void remove(int index) throws IOException{
		if (index < game.size())
			game.remove(index);
		else
			throw new IOException("Error with removing correct questions"); 
	}

	public List<AQuiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(List<AQuiz> quiz) {
		this.quiz = quiz;
	}
}
