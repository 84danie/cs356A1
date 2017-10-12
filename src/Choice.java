
public class Choice {
	private char letter;
	private String statement;
	
	public Choice(char letter, String statement) {
		this.letter = letter;
		this.statement = statement;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Choice other = (Choice) obj;
		if (letter != other.letter)
			return false;
		return true;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
	@Override
	public String toString(){
		return letter+".) "+statement;
	}

	

}
