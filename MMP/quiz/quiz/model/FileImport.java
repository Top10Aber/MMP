package quiz.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public final class FileImport {
		//opens file search dialog
	public static void importQuiz(QuizModel quiz) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Import Quiz"); //name of dialog
		
		// Below code is used from Reference [1] from research
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop")); //default starting area.  
		// End of Reference [1]
		
		// Reference [1] lead to finding this built in function:
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt")); //only looks for txt files
		// -----
		
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			try {
				readFileToQuiz(selectedFile, quiz);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
/*	
 	Reference [1]
	- Authors name: "book2s.com"
	- Title: "javafx FileChooser File Chooser set Initial Directory"
	- Type: Tutorial code
	- Source URL: http://book2s.com/java/api/javafx/stage/filechooser/setinitialdirectory-1.html
*/
	
	private static void readFileToQuiz(File f, QuizModel quiz) throws IOException{
		if (f.exists()) {
			FileInputStream file = new FileInputStream(f);
			Scanner read = new Scanner(file);
			String string;
			List<String> list = new LinkedList<String>();
			while (read.hasNext()) {
				string = read.nextLine();						
				if ((string.isEmpty()) && (!list.isEmpty())) {
					quiz.addAQuiz(list);
					list.clear();
				} else if ((!string.isEmpty()) && (!string.substring(0, 1).equals("/"))) {
						list.add(string);
				}
			}
			quiz.addAQuiz(list);
			read.close();
		} else {
			throw new IOException("File not found.");
		}
	}
}