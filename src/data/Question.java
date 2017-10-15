package data;
import java.util.List;

/**
 * @author danie
 * 
 * Interface for Question classes
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
	 * Get all the possible candidate choices
	 * @return the candidate choices
	 */
	List<Choice> getChoices();	
	/**
	 * @return a string containing the question and all candidate choices
	 */
	String toString();
	
}
