package data;
import java.util.ArrayList;
import java.util.List;

import data.provider.Student;


/**
 * @author danie
 *
 *This class represents a submission that can be sent to an IVoteService. 
 *
 *NOTE: Submissions are immutable.
 */
public class Submission {

	private Student student;
	private List<Choice> choices;
	/**
	 * Constructor. 
	 * @param student the Student submitting the submission
	 * @param choices the choices the student selected
	 */
	public Submission(Student student, List<Choice> choices) {
		this.student = student;
		this.choices= new ArrayList<Choice>(choices);
	}
	/**
	 * @return the List of Choices selected in this Submission
	 */
	public List<Choice> getChoices(){
		return choices;
	}
	/**
	 * @return the Student that sent this Submission
	 */
	public Student getStudent(){
		return student;
	}
	/** 
	 * Compares this Submission to another Object
	 * 
	 * @param obj the Object to be compared to
	 * @return true if obj is equal to this Submission, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Submission other = (Submission) obj;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
	/** 
	 * @return a String representation of this Submission
	 */
	@Override
	public String toString() {
		return "Submission [student=" + student + ", choices=" + choices + "]";
	}
	
	

}
