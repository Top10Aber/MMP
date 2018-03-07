package quiz.model;

import java.util.List;

public class Quiz {

	private final static int MAX_ANSWER_OPTIONS = 6;
	private String questions;
	private String correctAnswer;
	private String[] allAnswers;
	
	public Quiz(List<String> list) { 
		questions = list.get(0); //Question is at the top of the file. 0 because arrays start at 0 etc
		correctAnswer = list.get(1); //correct answer is line underneath
		
		if (list.size()-1 > MAX_ANSWER_OPTIONS) // adjust to number of answers
			allAnswers = new String[MAX_ANSWER_OPTIONS];
		else
			allAnswers = new String[list.size()-1];
		
		// Randomises the order of answers to select from. Makes it so the answer isn't always on top, but easier on
		// the quiz maker as they don't need to randomise
		
		int correctIndex = (int) (Math.random() * allAnswers.length);
		int listIndex = 2;
		for (int i=0; i < allAnswers.length; i++) {
			if (i == correctIndex) {
				allAnswers[i] = correctAnswer;
			} else {
				allAnswers[i] = list.get(listIndex);
				listIndex = listIndex + 1;
			}
		}
		
	}
		
		
	// loads question in
	public String getQuestion() { return new String (questions); }
	
	// loads correct answer in
	public String getCorrect() { return new String (correctAnswer); } 
	
	// loads all answers in
	public String[] getAllAnswers() { return allAnswers.clone(); }
			
	}