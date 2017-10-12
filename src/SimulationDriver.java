import java.util.ArrayList;
import java.util.List;

public class SimulationDriver {

	public static void main(String[] args) {
		IVoteService ivote = new IVoteService();
		
		Student student1 = new Student();
		System.out.println(student1);
		Student student2 = new Student();
		System.out.println(student2);
		List<Choice> choices = new ArrayList<Choice>();
		choices.add(new Choice('A',"23"));
		choices.add(new Choice('B',"3"));
		choices.add(new Choice('C',"4"));
		Question question = new SingleAnswer("What is 2+2?",new Choice('C',"4"),choices);
		ivote.newQuestion(question);
		
		if(ivote.beginPoll()){
			System.out.println(ivote.displayQuestion());
		}
		List<Choice> answers = new ArrayList<Choice>();
		answers.add(new Choice('C',"4"));
		student1.submitAnswers(ivote,answers);
		answers = new ArrayList<Choice>();
		answers.add(new Choice('A',"23"));
	
		student1.submitAnswers(ivote, answers);
		//answers.remove(new Choice('A',"23"));
		//student2.submitAnswers(ivote, answers);
		
		ivote.closePoll();
		
		System.out.println(ivote.displayResults());
	}

}
