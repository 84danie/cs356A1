package test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data.Choice;
import data.MultipleAnswerQuestion;
import data.Question;
import data.SingleAnswerQuestion;
import data.provider.IVoteService;
import data.provider.Student;

public class SimulationDriver {

	private static final int NUM_STUDENTS = 20;
	public static void main(String[] args) {
		Student[] students = setupStudents(NUM_STUDENTS);

		IVoteService ivote = new IVoteService();

		Question question = setUpQuestion1();
		ivote.newQuestion(question);
		System.out.println(testQuestion1(ivote,students));

		question = setUpQuestion2();
		ivote.newQuestion(question);
		System.out.println(testQuestion2(ivote,students));
	}
	public static Student[] setupStudents(final int numStudents){
		Student[] students = new Student[numStudents];

		for(int i=0;i<numStudents;i++)
			students[i] = new Student();

		return students;
	}
	public static Question setUpQuestion1(){
		String questionString = "Who is James Gosling?";
		String answerString = "The creator of Java.";
		List<Choice> choices = new ArrayList<Choice>();
		choices.add(new Choice("The actor from La La Land and The Notebook."));
		choices.add(new Choice(answerString));
		choices.add(new Choice("I don't know."));

		return new SingleAnswerQuestion(questionString,new Choice(answerString),choices);
	}

	public static String testQuestion1(IVoteService ivote, Student[] students){
		ivote.beginPoll();
		Random r = new Random();
		List<Choice>choices = ivote.getChoices();
		for(Student student : students){
			int choice = r.nextInt(choices.size());
			List<Choice>answers = new ArrayList<Choice>();
			answers.add(choices.get(choice));
			if(student.sendSubmission(ivote, answers) == false){
				System.out.println("Error- a submission was not successfully sent");
				System.exit(1);
			}
		}

		int unsureStudents = r.nextInt(NUM_STUDENTS)+1;

		System.out.println("Students that want to change their answers: " + unsureStudents);
		for(int i =0; i < unsureStudents; i++){
			int unsureStudent = r.nextInt(NUM_STUDENTS);
			int choice = r.nextInt(choices.size());
			List<Choice>answers = new ArrayList<Choice>();
			answers.add(choices.get(choice));
			if(!students[unsureStudent].sendSubmission(ivote,answers)){
				System.out.println("Error- a submission was not successfully sent");
				System.exit(1);
			}
		}
		ivote.closePoll();
		return ivote.displayResults();
	}

	public static String testQuestion2(IVoteService ivote, Student[] students){
		ivote.beginPoll();
		Random r = new Random();
		List<Choice>choices = ivote.getChoices();
		for(Student student : students){
			List<Choice>answers = new ArrayList<Choice>();
			int numChoices = r.nextInt(choices.size());
			if(numChoices == choices.size()){
				answers = choices;
			}
			else {
				for(int i = 0; i < numChoices; i++){
					int choice = 0;
					do{
						choice = r.nextInt(choices.size());
					}while(answers.contains(choices.get(choice)));
					answers.add(choices.get(choice));
				}
			}

			if(student.sendSubmission(ivote, answers) == false){
				System.out.println("Error- a submission was not successfully sent");
				System.exit(1);
			}
		}

		
		ivote.closePoll();
		return ivote.displayResults();
	}
	public static Question setUpQuestion2(){
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
