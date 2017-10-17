package test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import data.Choice;
import data.MultipleAnswerQuestion;
import data.Question;
import data.SingleAnswerQuestion;
import data.provider.IVoteService;
import data.provider.Student;

/**
 * Driver class for testing the IVoteService. Generates an array of Students (size given by user), and tests an
 * IVoteService instance with two questions of different question types. 
 * 
 * Candidate answers are randomly generated.The first test also randomly chooses a number of submissions
 * that will be "resent", and randomly chooses students that will send such new submissions in order to
 * test that the IVoteService updates submissions accordingly (and only records the last submission a student sent).
 * 
 * The second test also attempts to send submissions containing erroneous choices. 
 *
 * A user can run as many simulations as they desire, with the option of also displaying all submissions at the end
 * of each test (not recommended for a large number of students).
 */
public class SimulationDriver {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean continueSimulating = true;
		do{
			final int NUM_STUDENTS = getNumberOfStudents(input);
			boolean VERBOSE = askYesNo("Would you like to display ALL submissions after each question?",input);
			Student[] students = setupStudents(NUM_STUDENTS);

			IVoteService ivote = new IVoteService();

			Question question = setUpQuestion1();
			ivote.newQuestion(question);
			System.out.println("***Question 1***\n\n"+ivote.displayQuestion());
			System.out.println("The correct answer to this question is 'The Creator of Java.'");
			System.out.println("Displaying Results for Question 1: ");
			System.out.println(testQuestion1(ivote,students,NUM_STUDENTS));
			if(VERBOSE)
				System.out.println(ivote.displaySubmissions());

			question = setUpQuestion2();
			ivote.newQuestion(question);
			System.out.println("***Question 2***\n\n"+ivote.displayQuestion());
			System.out.println("All three answer choices are correct in this question.");
			System.out.println("Displaying Results for Question 2: ");
			System.out.println(testQuestion2(ivote,students,NUM_STUDENTS));
			if(VERBOSE)
				System.out.println(ivote.displaySubmissions());
			
			continueSimulating = askYesNo("Would you like to complete another simulation?",input);
		}while(continueSimulating);
		System.out.println("Thank you for choosing iVote!");
	}
	//Get the number of students to run the simulation with
	private static int getNumberOfStudents(Scanner input){
		System.out.print("How many students will be sending submissions? ");
		int numStudents = input.nextInt();
		input.nextLine(); //catch enter key
		return numStudents;
	}
	//Utility function for obtaining yes/no answers to specified questions
	private static boolean askYesNo(String question, Scanner input){
		char display;
		do{
			System.out.print(question+" (Y/N) ");
			String temp =  input.nextLine().toUpperCase();
			if(!temp.isEmpty())
				display = temp.charAt(0);
			else
				display = ' ';
		}while(display!='Y' && display!= 'N');
		if(display=='Y')
			return true;
		else
			return false;
	}
	//Generate an array of students of a specified size
	private static Student[] setupStudents(final int numStudents){
		Student[] students = new Student[numStudents];

		for(int i=0;i<numStudents;i++)
			students[i] = new Student();

		return students;
	}
	//Set up the first question for the simulation
	private static Question setUpQuestion1(){
		String questionString = "Who is James Gosling?";
		String answerString = "The creator of Java.";
		List<Choice> choices = new ArrayList<Choice>();
		choices.add(new Choice("The actor from La La Land and The Notebook."));
		choices.add(new Choice(answerString));
		choices.add(new Choice("I don't know."));

		return new SingleAnswerQuestion(questionString,new Choice(answerString),choices);
	}
	//Test the first question type (SingleAnswer). Also randomly chooses a number of submissions
	//that will be "resent"
	private static String testQuestion1(IVoteService ivote, Student[] students, final int NUM_STUDENTS){
		ivote.beginPoll();
		Random r = new Random();
		List<Choice>choices = ivote.getChoices();
		for(Student student : students){
			//randomly select a choice
			int choice = r.nextInt(choices.size());

			List<Choice>answers = new ArrayList<Choice>();
			//add the student's answer to their answer buffer
			answers.add(choices.get(choice));
			//send the submission
			if(student.sendSubmission(ivote, answers) == false){
				System.out.println("Error- a submission was not successfully sent");
				System.exit(1);
			}
			
		}
		String initialResults = ivote.displayResults();


		//test to make sure only the last submissions sent by a student are counted
		//pick a random number of new submissions to send
		int newSubmissions = r.nextInt(NUM_STUDENTS)+1;
		String status = newSubmissions + " submissions were resent. Here are the final results:\n";

		for(int i =0; i < newSubmissions; i++){
			int unsureStudent = r.nextInt(NUM_STUDENTS); 
			//randomly pick a choice (might be the same one as before, silly students!)
			int choice = r.nextInt(choices.size()); 

			List<Choice>answers = new ArrayList<Choice>();
			//add the student's new choice to their answer buffer
			answers.add(choices.get(choice));
			//send the submission
			if(!students[unsureStudent].sendSubmission(ivote,answers)){
				System.out.println("Error- a submission was not successfully sent");
				System.exit(1);
			}
			
			
			
		}
		ivote.closePoll();
		return initialResults+"\n"+status+"\n"+ivote.displayResults();
	}
	//Test the second question type (MultipleAnswer). 
	private static String testQuestion2(IVoteService ivote, Student[] students, final int NUM_STUDENTS){
		ivote.beginPoll();
		Random r = new Random();
		List<Choice>choices = ivote.getChoices(); 
		for(Student student : students){
			List<Choice>answers = new ArrayList<Choice>(); 
			//pick random number of choices
			int numChoices = r.nextInt(choices.size())+1; 

			//for the students that select all the choices
			if(numChoices == choices.size())
				answers = new ArrayList<Choice>(choices);
			else {
				//randomly select multiple choices
				for(int i = 1; i <= numChoices; i++){
					//not guaranteed to select numChoices since the same choice may be selected
					//good test of how submissions with redundant answer choices are handled
					int choice = r.nextInt(choices.size());
					answers.add(choices.get(choice));
				}
			}

			if(student.sendSubmission(ivote, answers) == false){
				System.out.println("Error- a submission was not successfully sent");
				System.exit(1);
			}
			
			answers.add(new Choice("This is not one of the choices."));
			if(student.sendSubmission(ivote, answers) == true){
				System.out.println("Error- a submission with an erroneous answer was successfully sent.");
				System.out.println(answers);
				System.exit(1);
			}
		}
		

		ivote.closePoll();
		return ivote.displayResults();
	}
	
	private static Question setUpQuestion2(){
		String questionString = "Who is Linus Torvald?";
		String answerString1 = "The creator of Linux.";
		String answerString2 = "The creator of Git.";
		String answerString3 = "A narcissist.";
		List<Choice> choices = new ArrayList<Choice>();
		choices.add(new Choice(answerString1));
		choices.add(new Choice(answerString2));
		choices.add(new Choice(answerString3));
		return new MultipleAnswerQuestion(questionString,choices,choices);
	}

}
