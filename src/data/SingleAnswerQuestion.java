package data;
import java.util.List;

/**
 * @author danie
 *
 */
public class SingleAnswerQuestion implements Question{
	private Choice answer;
	private String question;
	private List<Choice> choices;
	
	/**
	 * @param question
	 * @param answer
	 * @param choices
	 */
	public SingleAnswerQuestion(String question, Choice answer, List<Choice> choices) {
		setChoices(choices);
		setAnswer(answer);
		setQuestion(question);
	}

	/* (non-Javadoc)
	 * @see data.Question#getQuestion()
	 */
	@Override
	public String getQuestion() {
		return question;
	}

	/* (non-Javadoc)
	 * @see data.Question#isCorrect(data.Choice)
	 */
	@Override
	public boolean isCorrect(Choice response) {
		if(response.equals(answer))
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see data.Question#setQuestion(java.lang.String)
	 */
	@Override
	public void setQuestion(String question) {
		this.question = question;
		
	}

	/* (non-Javadoc)
	 * @see data.Question#getChoices()
	 */
	@Override
	public List<Choice> getChoices() {
		return choices;
	}

	/* (non-Javadoc)
	 * @see data.Question#setChoices(java.util.List)
	 */
	@Override
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	/* (non-Javadoc)
	 * @see data.Question#setAnswer(data.Choice)
	 */
	@Override
	public void setAnswer(Choice answer) {
		this.answer = answer;
		if(!choices.contains(answer)){
			choices.add(answer);
		}
		
	}
	
	/**
	 * @return
	 */
	public Choice getAnswer() {
		return answer;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String s = question+"\n";
		
		for(Choice c : choices)
			s+=c.toString()+'\n';
		return s;
		
	}

}
