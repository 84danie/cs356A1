package test;
import java.util.ArrayList;
import java.util.List;

import data.Choice;
import data.Question;
import data.SingleAnswerQuestion;
import data.provider.IVoteService;
import data.provider.Student;

public class SimulationDriver {

	public static void main(String[] args) {
		IVoteService ivote = new IVoteService();
		
		Student student1 = new Student();
		System.out.println(student1);
		Student student2 = new Student();
		System.out.println(student2);
		List<Choice> choices = new ArrayList<Choice>();
		choices.add(new Choice("A 23"));
		choices.add(new Choice("B 3"));
		choices.add(new Choice("C 4"));
		Question question = new SingleAnswerQuestion("What is 2+2?",new Choice("C 4"),choices);
		ivote.newQuestion(question);
		
		if(ivote.beginPoll()){
			System.out.println(ivote.displayQuestion());
		}
		List<Choice> answers = new ArrayList<Choice>();
		answers.add(new Choice("C 4"));
		student1.sendSubmission(ivote,answers);
		answers = new ArrayList<Choice>();
		answers.add(new Choice("A 23"));
	
		student1.sendSubmission(ivote, answers);
		answers.remove(new Choice("A 23"));
		answers.add(new Choice("C 4"));
		student2.sendSubmission(ivote, answers);
		student1.sendSubmission(ivote, answers);
		
		ivote.closePoll();
		
		System.out.println(ivote.displayResults());
		choices.add(new Choice("D 2x"));
		ivote.newQuestion(new SingleAnswerQuestion("What is the derivative of x^2?",new Choice("D 2x"),choices));
		if(ivote.beginPoll()){
			System.out.println(ivote.displayQuestion());
		}
		answers = new ArrayList<Choice>();
		answers.add(new Choice("C 4"));
		answers.add(new Choice("D 2x"));
		student1.sendSubmission(ivote,answers);

		
		ivote.closePoll();
		System.out.println(ivote.displayResults());
		
		
	}

}
