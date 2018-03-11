package quiz.model;

import java.util.List;

public class Quiz {
	private final static int MAX_ANSWER_OPTIONS = 6;
	
	private String questions;
	private String correctAnswer;
	private String[] allAnswers;

	
	public Quiz(List<String> list){
		questions = list.get(0); //question is top line of txt. 0 because obviously arrays/lists start at 0
		correctAnswer = list.get(1); //correct answer has to be the top answer

		if (list.size()-1 > MAX_ANSWER_OPTIONS)		     // Adjust to number of answers
			allAnswers = new String[MAX_ANSWER_OPTIONS]; // If there's more than 6, scrap the rest lol
		else
			allAnswers = new String[list.size()-1];
		//Randomises order of answers to select. Makes it easy for maker but not obvious the answer is always the top one 
		// this can be an adaption thing
		int correctIndex = (int) (Math.random() * allAnswers.length);
		int listIndex = 2;
		for (int i=0; i < allAnswers.length; i++){
			if (i == correctIndex){
				allAnswers[i] = correctAnswer;
			} else {
			allAnswers[i] = list.get(listIndex);
			listIndex = listIndex + 1;
			}
		}
	}

	//loads question in
	public String getQuestion() { return new String (questions); }

	//loads correct answer in
	public String getCorrect() { return new String (correctAnswer); }

	//loads all of the answers in
	public String[] getAllAnswers() { return allAnswers.clone(); }

}
