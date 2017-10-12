import java.util.List;

public class SingleAnswer implements Question{
	private Choice answer;
	private String question;
	private List<Choice> choices;
	
	public SingleAnswer(String question, Choice answer, List<Choice> choices) {
		setAnswer(answer);
		setChoices(choices);
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
	public boolean setChoices(List<Choice> choices) {
		if(choices.contains(answer)){
			this.choices = choices;
			return true;
		}
		else
			return false;
	}

	@Override
	public void setAnswer(Choice answer) {
		this.answer = answer;
		
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
