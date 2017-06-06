import java.awt.*;
/*import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestionSet {

	String[] difficulties = { "easy", "medium", "hard" };
	String[] subjects = { "english", "math", "geo" };
	/** Arraylist of question class which stores question and answers. */
	ArrayList<Question> questions = new ArrayList<Question>();

	public QuestionSet(int diffIndex) {
		int questionIndex = 0;
		for (int subjectIndex = 0; subjectIndex < 3; subjectIndex++) {
			String path = "Questions\\" + difficulties[diffIndex] + subjects[subjectIndex] + ".txt";
			int lineIndex = 0;
			try {
				BufferedReader input = new BufferedReader(new FileReader(path));
				while (true) {
					String s = input.readLine();
					if (s == null)
						break;
					if (lineIndex == 0) {
						questions.add(new Question(s));
			    	} else {
						//if correct answer
						if (s.charAt(s.length() - 1) == '*') {
							questions.get(questionIndex).correct = lineIndex - 1;
							s = s.substring(0, s.length() - 1);
						}
						//add answer
						questions.get(questionIndex).answers.add(s);
					}
					lineIndex++;
					if (lineIndex == 5) {
						questionIndex++;
						lineIndex = 0;
					}
				}
			} catch (IOException e) {
			}
		}
	}

}