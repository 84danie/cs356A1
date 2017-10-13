import java.util.List;
import java.util.UUID;

public class Student implements Comparable<Student>{

	private String id;
	
	public Student() {
		this.id = UUID.randomUUID().toString();
	}
	public String toString(){
		return id;
	}
	public boolean sendSubmission(IVoteService ivote, List<Choice> answers){
		ivote.receiveSubmission(new Submission(this,  answers));
		return true;
	}
	
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
	@Override
	public int compareTo(Student o) {
		if(id.compareTo(o.id)==0)
			return 0;
		else if (id.compareTo(o.id)<0)
			return -1;
		else
			return 1;
	}
}
