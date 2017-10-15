package data;
import java.util.List;

/**
 * 
 * Interface for Question
 * 
 * Classes that implement Question have a question that is being asked, possible
 * answers to that question, and at least 1 answer to the question. 
 *
 */
public interface Question {
	/**
	 * Set the question to be answered
	 * @param question
	 */
	void setQuestion(String question);
	/**
	 * Set the answer to the question. If @param answer is not
	 * one of the choices, it is added to the choice list.
	 * @param answer
	 */
	void setAnswer(Choice answer);
	/**
	 * Set the candidate choices.
	 * @param choices
	 */
	void setChoices(List<Choice> choices);	
	/**
	 * Get the question to be answered
	 * @return the string representation of the question
	 */
	String getQuestion();
	
	/**
	 * Get the answer(s) to this Question.
	 * @return the List<Choice> containing the answer(s
	 */
	List<Choice> getAnswer();
	/**
	 * Get all the possible candidate choices
	 * @return the candidate choices
	 */
	List<Choice> getChoices();	
	/**
	 * @return a string containing the question and all candidate choices
	 */
	String toString();
	
}
