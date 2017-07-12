import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/********************************************************************
Author - Shashwat Kapoor

Method Description:
	countStudents(fileName) - counts the number of names in the file
		and returns it.
	enterFileName() - asks for the file name to be read and returns
	 	it.
	fillPhrases(phrases) - fills the phrase array with 3 school 
		appropriate, positive messages;
  fillStudents(students, fileName) - fills the student array with 
    	the	names from the file.
	output(student, phrase) - prints the phrase and the students
		name and asks the user if they want to pick another
		student or quit. Return the users choice.
	pickAPhrase(phrases) - picks a random phrase and returns it.
	pickAStudent(students) - picks a random student and returns
		his/her name.
    
NOTE: This program only works with text (.txt) files.

********************************************************************/

public class PickAStudent
{
	public static void changeJOP()
	{
		UIManager.put("Label.font", new FontUIResource 
				(new Font("Segoe UI Light", Font.BOLD, 40)));
		UIManager.put("OptionPane.messageForeground",
				new Color(100,149,237));
		UIManager.put("TextField.background",new Color(238,233,233));
		UIManager.put("TextField.font", new FontUIResource
				(new Font("Myriad Web Pro", Font.ITALIC, 24)));
		UIManager.put("TextField.foreground",new Color(0,0,205));
		UIManager.put("Panel.background",new Color(221,160,221));
		UIManager.put("OptionPane.background",new Color(230,230,250));
		UIManager.put("Button.background",new Color(132,112,255));
		UIManager.put("Button.foreground", new Color(72,61,139));
		UIManager.put("Button.font", new FontUIResource	
				(new Font("Tempus Sans ITC", Font.BOLD, 14)));
	}
	
	public static String enterFileName()
	{
		return (JOptionPane.showInputDialog("Enter the file name"));
	}
	
	public static int countStudents(String fileName){
		int numStu = 0;
		try {
			Scanner inFile = new Scanner (new File (fileName));
			String it;
			while (inFile.hasNext()){
				it = inFile.nextLine();
				numStu++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if (numStu == 0)
			numStu = 0;
		return numStu;
	}
	
	public static void fillPhrases(String[] phrases){
		phrases[0] = "Your turn";
		phrases[1] = "Let's continue with you";
		phrases[2] = "You can do it";
	}
	
	public static void fillStudents(String[] students, String fileName){
		try {
			Scanner inFile = new Scanner (new File (fileName));
			int i = 0;
			while (inFile.hasNext())
			{
				students[i] = inFile.nextLine();
				i++;
			}
			inFile.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String pickAPhrase(String[] phrases){
		return phrases[(int)(Math.random()* 3)];
	}
	
	public static String pickAStudent(String[] students){
		if (students.length == 0)
			return "";
		else 
			return students[(int)(Math.random()* students.length)];
	}
	
	public static int output(String[] students, String[] phrases){
		String[] option = {"Pick Someone", "Done"};
		int choice;
		while (pickAStudent(students).equals("")){
			String[] options = {"Yes","No"};
			choice = JOptionPane.showOptionDialog(null, "Either there is no file like this or" +
					"\nthere are no students in this file.\n" +
					"Do you want to open another file?", "Error", 0, 0, null, options , null);
			while (choice == 0){
				String fileName = enterFileName();
				students = new String[countStudents(fileName)];
				fillPhrases(phrases);
				fillStudents(students, fileName);
				while(choice == 0){
					choice = JOptionPane.showOptionDialog(null,pickAPhrase(phrases)+", "
						+pickAStudent(students)+" ","Pick A Student", 
						0, 3, null, option, null);
				}
			}
			choice = 1;
		}
	
			choice = JOptionPane.showOptionDialog(null,pickAPhrase(phrases)+", "
					+pickAStudent(students)+" ","Pick A Student", 
					0, 3, null, option, null);
		
		return choice;
	}
	
	public static void main(String[]args){
		changeJOP();
		String fileName = enterFileName();
		String[] phrases = new String[3];
		String[] students = new String[countStudents(fileName)];
		fillPhrases(phrases);
		fillStudents(students, fileName);
		int choice = 0;
		while(choice == 0)
		{
			choice = output(students, phrases);
		}
	}
}
