import java.util.ArrayList;
import java.util.List;

public class Submission {

	private Student student;
	private List<Choice> choices;
	public Submission(Student student, List<Choice> choices) {
		this.student = student;
		this.choices= new ArrayList<Choice>(choices);
	}
	public List<Choice> getChoices(){
		return choices;
	}
	public Student getStudent(){
		return student;
	}
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
	

}
