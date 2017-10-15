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

	private static final int NUM_STUDENTS = 40;
	public static void main(String[] args) {
		 Student[] students = setupStudents(NUM_STUDENTS);
		 
		 IVoteService ivote = new IVoteService();
		 
		 Question question = setUpQuestion1();
		 ivote.newQuestion(question);
		 
		 ivote.beginPoll();
		 System.out.println(ivote.displayQuestion());
		 
		 Random r = new Random();
		 List<Choice>choices = question.getChoices();
		 for(Student student : students){
			 int choice = r.nextInt(choices.size());
			 List<Choice>answers = new ArrayList<Choice>();
			 answers.add(choices.get(choice));
			 System.out.println(student.sendSubmission(ivote, answers));
		 }
		 
		 System.out.println(ivote.displayResults());
		 
		 int unsureStudents = r.nextInt(NUM_STUDENTS)+1;
		 
		 System.out.println("Students that want to change their answers: " + unsureStudents);
		 for(int i =0; i < unsureStudents; i++){
			 int unsureStudent = r.nextInt(NUM_STUDENTS);
			 int choice = r.nextInt(choices.size());
			 List<Choice>answers = new ArrayList<Choice>();
			 answers.add(choices.get(choice));
			 System.out.println(students[unsureStudent].sendSubmission(ivote,answers));
		 }
		 
		 System.out.println(ivote.displayResults());
		 
		 ivote.closePoll();
		 
		 
		 
		 
		 
		
		
		
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

}
