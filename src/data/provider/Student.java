package data.provider;
import java.util.List;
import java.util.UUID;

import data.Choice;
import data.Submission;


/**
 * @author danie
 *
 *This class represents a student that can send submissions to an IVoteService.
 *NOTE: Students are immutable.
 */
public class Student {

	private String id;
	
	/**
	 * Default Constructor. Students are identified by a random unique ID.
	 */
	public Student() {
		this.id = UUID.randomUUID().toString();
	}
	/** 
	 * @return a string representation of this Student's id
	 */
	@Override
	public String toString(){
		return id;
	}
	/**
	 * Sends a list of choices as a submission to an IVoteService.
	 * 
	 * @param ivote the IVoteService to send the submission to
	 * @param answers the choices the student has selected
	 * @return true if the submission was successfully received, false otherwise
	 */
	public boolean sendSubmission(IVoteService ivote, List<Choice> answers){
		return ivote.receiveSubmission(new Submission(this,  answers));
	}
	
	/** 
	 * Compares this Student to another Object to see if they are equal.
	 * 
	 * @param obj the Object to be compared
	 * @return true if obj is equal to this Student, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
