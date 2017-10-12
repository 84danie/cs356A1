import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IVoteService {

	private boolean polling;
	private Question question;
	private Map<Choice,Integer> results;
	private Map<Student,List<Choice>> submissions; //remove multi-map

	public IVoteService() {
		polling = false;
		question = null;
		results = null;
		submissions = null;
	}

	public boolean newQuestion(Question question){
		this.question = question;
		return true;
	}

	private boolean setup(){
		if(question!=null){
			List<Choice> choices = question.getChoices();
			results = new HashMap<Choice,Integer>(choices.size());
			for(Choice c : choices)
				results.put(c,0);
			submissions = new HashMap<Student,List<Choice>>();
			return true;
		}
		else
			return false;
	}

	public String displayQuestion(){
		return question.toString();

	}
	public boolean receiveAnswer(Student student, List<Choice> answers){
		if(polling){
			if(submissions.containsKey(student)){
				List<Choice> previousAnswer = submissions.get(student);
				for(Choice c : previousAnswer){
					results.put(c, results.get(c) - 1);
				}
			}
			submissions.put(student, answers);
			for(Choice answer: answers)
				receiveAnswer(answer);
			return true;
		}
		else
			return false;
	}
	private boolean receiveAnswer(Choice answer){
		results.put(answer, results.getOrDefault(answer, 0) + 1);
		return true;	
	}

	public boolean beginPoll(){
		if(!polling && question!=null){
			if(setup()){
				polling = true;
				return true;
			}
		}
		return false;
	}

	public boolean closePoll(){
		if(polling){
			polling = false;
			return true;
		}
		else
			return false;
	}

	public String displayResults(){
		return submissions.toString()+"\n"+results.toString();

	}

}
