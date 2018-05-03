package quiz.model;

import java.util.List;

public class AQuiz {

	private String questions;
	private String correctAnswer;
	private String[] remainingAnswers;

	public AQuiz(List<String> list) {
		questions = list.get(0); // question is top line of txt. 0 because obviously arrays/lists start at 0
		correctAnswer = list.get(1); // correct answer has to be the top answer
		remainingAnswers = new String[list.size() - 1];

		// Randomises order of answers to select. Makes it easy for maker but not
		// obvious the answer is always the top one #smart
		int inputtedOrder = (int) (Math.random() * remainingAnswers.length);
		int listOrder = 2;
		for (int i = 0; i < remainingAnswers.length; i++) {
			if (i == inputtedOrder) {
				remainingAnswers[i] = correctAnswer;
			} else {
				remainingAnswers[i] = list.get(listOrder);
				listOrder = listOrder + 1;
			}
		}
	}

	// loads question in
	public String getQuestion() {
		return new String(questions);
	}

	// loads correct answer in
	public String getCorrect() {
		return new String(correctAnswer);
	}

	// loads all of the answers in
	public String[] getAllAnswers() {
		return remainingAnswers.clone();
	}

}