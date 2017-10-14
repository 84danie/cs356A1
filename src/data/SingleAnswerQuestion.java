package data;
import java.util.List;

public class SingleAnswerQuestion implements Question{
	private Choice answer;
	private String question;
	private List<Choice> choices;
	
	public SingleAnswerQuestion(String question, Choice answer, List<Choice> choices) {
		setChoices(choices);
		setAnswer(answer);
		setQuestion(question);
	}

	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public boolean isCorrect(Choice response) {
		if(response.equals(answer))
			return true;
		else
			return false;
	}

	@Override
	public void setQuestion(String question) {
		this.question = question;
		
	}

	@Override
	public List<Choice> getChoices() {
		return choices;
	}

	@Override
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	@Override
	public void setAnswer(Choice answer) {
		this.answer = answer;
		if(!choices.contains(answer)){
			choices.add(answer);
		}
		
	}
	
	public Choice getAnswer() {
		return answer;
	}
	@Override
	public String toString(){
		String s = question+"\n";
		
		for(Choice c : choices)
			s+=c.toString()+'\n';
		return s;
		
	}

}
