package data;

import java.util.List;

/**
 *
 *This class represents a Question that has several correct answers.
 */
public class MultipleAnswerQuestion implements Question{

	private String question;
	private List<Choice> answers;
	private List<Choice> choices;

	/**
	 * Constructor. Set the question, answers, and choices
	 * 
	 * @param question the question to be asked
	 * @param answers the List of Choices representing the answer
	 * @param choices the list of possible Choices
	 */
	public MultipleAnswerQuestion(String question, List<Choice> answers, List<Choice> choices) {
		setQuestion(question);
		setChoices(choices);
		setAnswer(choices);
	}

	/** 
	 * Set the question.
	 * @param question the new question
	 */
	@Override
	public void setQuestion(String question) {
		this.question = question;

	}

	/** 
	 * Set an answer. Since this is a MultipleAnswerQuestion, 
	 * setting an answer adds an answer Choice to the current
	 * list of answers.
	 */
	@Override
	public void setAnswer(Choice answer) {
		answers.add(answer);
		if(!choices.contains(answer))
			choices.add(answer);
	}
	/**
	 * Set the answers. Unlike the implemented setAnswer, this overloaded
	 * method replaces the current set of answer Choices with a new List of
	 * answer Choices.
	 */
	
	public void setAnswer(List<Choice> answers){
		this.answers = answers;
		for(Choice c : answers)
			if(!choices.contains(c))
				choices.add(c);
	}

	/** 
	 * Set the choices for this Question. Note that setting the choices
	 * will NOT automatically update the answers. setAnswer must be called
	 * to update the answer choices.
	 */
	@Override
	public void setChoices(List<Choice> choices) {
		this.choices = choices;

	}

	/** 
	 * @return String representation of the question being asked in this Question
	 */
	@Override
	public String getQuestion() {
		return question;
	}

	/** 
	 * Get the choices for this Question.
	 * @return List containing all possible choices for this Question
	 */
	@Override
	public List<Choice> getChoices() {
		return choices;
	}

	/** 
	 * @return a String representation of this Question
	 */
	@Override
	public String toString(){
		String s = question+"\n";

		for(Choice c : choices)
			s+=c.toString()+'\n';
		return s;
	}

	/** 
	 * Get the answer to this Question.
	 * 
	 * @return a List of Choices representing the answer to this Question
	 */
	@Override
	public List<Choice> getAnswer() {
		return answers;
	}

}
