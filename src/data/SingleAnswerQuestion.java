package data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author danie
 *
 *This question represents a Question that only has 1 correct answer.
 */
public class SingleAnswerQuestion implements Question{
	private Choice answer;
	private String question;
	private List<Choice> choices;
	
	/**
	 * Constructor. Sets the question, answer and possible choices.
	 * 
	 * @param question
	 * @param answer
	 * @param choices
	 */
	public SingleAnswerQuestion(String question, Choice answer, List<Choice> choices) {
		setQuestion(question);
		setChoices(choices);
		setAnswer(answer);
	}

	/** 
	 * @return String representation of the question being asked in this Question
	 */
	@Override
	public String getQuestion() {
		return question;
	}

	/** 
	 * Set the question.
	 */
	@Override
	public void setQuestion(String question) {
		this.question = question;
		
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
	 * Set the choices for this question. Note that this will
	 * not automatically update the answers. SetAnswer must be
	 * called in order to update the answers.
	 */
	@Override
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	/** 
	 * Set the answer for this question. If the answer Choice is not
	 * one of the possible choices, answer is added to the choices.
	 */
	@Override
	public void setAnswer(Choice answer) {
		this.answer = answer;
		if(!choices.contains(answer)){
			choices.add(answer);
		}
		
	}
	
	/**
	 * Get the answer for this question.
	 * @return the List<Choice> containing the answer.
	 */
	@Override
	public List<Choice> getAnswer() {
		List<Choice> answers = new ArrayList<Choice>();
		answers.add(answer);
		return answers;
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

}
