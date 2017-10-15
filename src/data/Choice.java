package data;

/**
 * @author danie
 *
 *This class represents a possible answer choice to a Question.
 *
 */
public class Choice {
	private String statement;
	
	/**
	 * Constructor. 
	 * 
	 * @param statement the description of this Choice
	 */
	public Choice(String statement) {
		this.statement = statement;
	}

	/** 
	 * Hashcode method to allow Choices to be used in HashMaps.
	 * 
	 * @return the hash of this Choice.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((statement == null) ? 0 : statement.hashCode());
		return result;
	}

	/** 
	 * Compares an Object to this Choice to see if they are equal.
	 * 
	 * @param obj the Object to be compared to
	 * @return true if obj is equal to this Choice, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Choice other = (Choice) obj;
		if (statement == null) {
			if (other.statement != null)
				return false;
		} else if (!statement.equals(other.statement))
			return false;
		return true;
	}

	/**
	 * @return the statement of this Choice
	 */
	public String getStatement() {
		return statement;
	}

	/**
	 * Set the statement of this Choice
	 * @param statement the new statement of this Choice
	 */
	public void setStatement(String statement) {
		this.statement = statement;
	}
	/** 
	 * @return the string representation of this Choice
	 */
	@Override
	public String toString(){
		return statement;
	}

	

}
