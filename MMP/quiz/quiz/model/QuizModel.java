package quiz.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class QuizModel {
	public List<Quiz> quiz;	// Quiz as loaded from file.
	private List<Quiz> game;	//clone of the quiz but the correctly answers fellas are removed to let the user revisit the other ones 
								//could be an adaption

	//makes quiz from imported file
	public QuizModel() throws Exception{
		setQuiz(new LinkedList<Quiz>());
		FileImport.importQuiz(this);
		startGame();
	}
	
	//the clone of the quiz so can be redone
	@SuppressWarnings("unchecked") //what dis? Research!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void startGame(){
		game = (List<Quiz>) ((LinkedList<Quiz>) getQuiz()).clone();
	}
	
	//loads quiz in... again?
	public void addAQuiz(List<String> list){
		getQuiz().add(new Quiz(list));
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

	
	//basically counts the number of questions so it can say "wow you got X out of a possible Y!"
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

	public List<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(List<Quiz> quiz) {
		this.quiz = quiz;
	}
}
