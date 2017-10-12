import java.util.List;

public interface Question {
	void setQuestion(String question);
	void setAnswer(Choice answer);
	boolean setChoices(List<Choice> choices);	
	String getQuestion();
	List<Choice> getChoices();	
	String toString();
	boolean isCorrect(Choice response);
}
