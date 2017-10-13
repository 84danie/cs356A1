import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IVoteService {

	private boolean polling;
	private Question question;
	private Map<Choice,Integer> results;
	private List<Submission> submissions; 

	public IVoteService() {
		polling = false;
		question = null;
		results = null;
		submissions = null;
	}

	public boolean newQuestion(Question question){
		if(!polling){
			this.question = question;
			return true;
		}
		return false;
	}

	private boolean setup(){
		if(question!=null){
			List<Choice> choices = question.getChoices();
			results = new HashMap<Choice,Integer>(choices.size());
			for(Choice c : choices)
				results.put(c,0);
			submissions = new ArrayList<Submission>();
			return true;
		}
		else
			return false;
	}

	public String displayQuestion(){
		return question.toString();

	}
	public boolean receiveSubmission(Submission newSubmission){
		if(isPolling()){
			Submission student = newSubmission;
			if(submissions.contains(student)){
				List<Choice> previousAnswer = submissions.get(submissions.indexOf(student)).getChoices();
				for(int i =0; i < previousAnswer.size(); i++){
					results.put(previousAnswer.get(i), results.get(previousAnswer.get(i)) - 1);
				}
			}
			submissions.add(newSubmission);
			for(Choice answer: newSubmission.getChoices())
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
		if(!isPolling() && question!=null){
			if(setup()){
				polling = true;
				return true;
			}
		}
		return false;
	}

	public boolean closePoll(){
		if(isPolling()){
			polling = false;
			return true;
		}
		else
			return false;
	}
	
	public boolean isPolling(){
		return polling;
	}

	public String displayResults(){
		return submissions.toString()+"\n"+results.toString();

	}

}
