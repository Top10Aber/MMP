package quiz.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class QuizModel {
	public List<Quiz> quiz;
	private List<Quiz> game;

	// makes quiz
	public QuizModel() throws IOException {

		setQuiz(new LinkedList<Quiz>());
		FileImport.importQuiz(this);
		startGame();

	}

	// clone
	@SuppressWarnings("unchecked")
	public void startGame() {
		game = (List<Quiz>) ((LinkedList<Quiz>) getQuiz()).clone();
	}

	public void addQuiz(List<String> list) {
		getQuiz().add(new Quiz(list));
	}

	// getters

	// loads
	public String getQuestion(int index) {
		return game.get(index).getQuestion();
	}

	// loads correct ans
	public String getCorrect(int index) {
		return game.get(index).getCorrect();
	}

	// loads other ans
	public String[] getAllAnswers(int index) {
		return game.get(index).getAllAnswers();
	}

	public int getGameSize() {
		return game.size();
	}

	public int getTotalScore() {
		return getQuiz().size();
	}

	public int getScore() {
		return getQuiz().size() - game.size();
	}

	public void remove(int index) throws IOException {
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