package data;
import java.util.ArrayList;
import java.util.List;

import data.provider.Student;


/**
 * @author danie
 *
 */
public class Submission {

	private Student student;
	private List<Choice> choices;
	/**
	 * @param student
	 * @param choices
	 */
	public Submission(Student student, List<Choice> choices) {
		this.student = student;
		this.choices= new ArrayList<Choice>(choices);
	}
	/**
	 * @return
	 */
	public List<Choice> getChoices(){
		return choices;
	}
	/**
	 * @return
	 */
	public Student getStudent(){
		return student;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Submission [student=" + student + ", choices=" + choices + "]";
	}
	
	

}
