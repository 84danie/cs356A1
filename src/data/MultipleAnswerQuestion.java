package data;

import java.util.List;

/**
 * @author danie
 *
 *This class represents a Question that has several correct answers.
 */
public class MultipleAnswerQuestion implements Question{

	private String question;
	private List<Choice> answers;
	private List<Choice> choices;

	/**
	 * @param question
	 * @param answers
	 * @param choices
	 */
	public MultipleAnswerQuestion(String question, List<Choice> answers, List<Choice> choices) {
		setQuestion(question);
		setAnswer(choices);
		setChoices(choices);
	}

	/* (non-Javadoc)
	 * @see data.Question#setQuestion(java.lang.String)
	 */
	@Override
	public void setQuestion(String question) {
		this.question = question;

	}

	/* (non-Javadoc)
	 * @see data.Question#setAnswer(data.Choice)
	 */
	@Override
	public void setAnswer(Choice answer) {
		answers.add(answer);
	}
	public void setAnswer(List<Choice> answers){
		this.answers = answers;
	}

	/* (non-Javadoc)
	 * @see data.Question#setChoices(java.util.List)
	 */
	@Override
	public void setChoices(List<Choice> choices) {
		this.choices = choices;

	}

	/* (non-Javadoc)
	 * @see data.Question#getQuestion()
	 */
	@Override
	public String getQuestion() {
		return question;
	}

	/* (non-Javadoc)
	 * @see data.Question#getChoices()
	 */
	@Override
	public List<Choice> getChoices() {
		return choices;
	}

	public String toString(){
		String s = question+"\n";

		for(Choice c : choices)
			s+=c.toString()+'\n';
		return s;
	}

}
