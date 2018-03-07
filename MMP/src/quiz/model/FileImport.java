package quiz.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileImport {

	// Opens file search dialog
	public static void importQuiz(QuizModel quiz) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Import quiz"); // Name of dialog
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop")); // default starting area
		//reference
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt")); // only look for text files
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			try {
				readFileToQuiz(selectedFile, quiz);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	// describe
	private static void readFileToQuiz(File f, QuizModel quiz) throws IOException {
		
		if (f.exists()) {
			FileInputStream file = new FileInputStream(f);
			Scanner read = new Scanner(file);
			String string;
			List<String> list = new LinkedList<String>();
			while (read.hasNext()) {
				string = read.nextLine();
				if ((string.isEmpty()) && (!list.isEmpty())) {
					quiz.addQuiz(list);
					list.clear();
				} else if ((!string.isEmpty()) && (!string.substring(0, 1).equals("/"))) {
					list.add(string);
				}
			}
			quiz.addQuiz(list);
			read.close();
		} else { 
			throw new IOException("File not found");
					
				}
			}
		}