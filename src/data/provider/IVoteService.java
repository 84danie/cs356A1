package data.provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import data.Choice;
import data.Question;
import data.Submission;

/**
 * Class that simulates the iVote service. An IVoteService is configured with a question,
 * and can accept Submissions from Students while polling. 
 * 
 * The interaction with an IVoteService is restricted to setting the Question, opening and closing a
 * poll, accessing the Question choices, and getting the statistics. Only Students may send Submissions, 
 * and all Submissions are handled internally, including verifying the integrity of the Submission.
 * 
 * Only the last VALID Submission received by a Student is counted. Submissions containing selections
 * that are not one of the choices to the current question are omitted.
 */
public class IVoteService {

	private boolean polling;
	private Question question;
	private Map<Choice,Integer> results;  //keeps track of the number of times a choice is selected
	private List<Submission> submissions; //keeps track of all submissions

	/**
	 * Default Constructor. Polling state is set to false.
	 */
	public IVoteService() {
		polling = false;
		question = null;
		results = new HashMap<Choice,Integer>();
		submissions = new ArrayList<Submission>();
	}

	/**
	 * Sets the current question. If this IVoteService is currently polling,
	 * the question will not be set.
	 * 
	 * NOTE: Calling newQuestion only updates the question, results and submissions are not affected.
	 * 
	 * @param question the question to be set
	 * @return true if the question was successfully set, false otherwise
	 */
	public boolean newQuestion(Question question){
		if(!polling){
			this.question = question;
			return true;
		}
		return false;
	}

	/**
	 * Sets up this IVoteService to begin a new poll. This function is internal, and
	 * is only meant to be called when beginPoll() is invoked.
	 * 
	 * @return true if the setup is a success, false otherwise
	 */
	private boolean setup(){
		if(question!=null){
			List<Choice> choices = question.getChoices();
			results.clear();
			for(Choice c : choices)
				results.put(c,0);
			submissions.clear();
			return true;
		}
		else
			return false;
	}


	/**
	 * @return a String representation of the current question and its candidate choices
	 */
	public String displayQuestion(){
		if(question!=null)
			return question.toString();
		else
			return "No question is currently set.";

	}
	/**
	 * Receives a submission if this IVoteService is currently polling answers, and
	 * the submission is valid. This method is protected so that it can only be 
	 * called by data providers, namely Students.
	 * 
	 * @param newSubmission the submission to be received
	 * @return true if the submission was successfully received, false otherwise
	 */
	protected boolean receiveSubmission(Submission newSubmission){
		if(isPolling() && isValid(newSubmission)){
			removeSubmission(newSubmission); //remove in case a student submitted already
			submissions.add(newSubmission);
			updateSubmissionResults(newSubmission,1);
			return true;
		}
		else
			return false;
	}
	/**
	 * Removes a submission. This internal method is called by receiveSubmission() in order
	 * to check if a new submission is from a student that previously sent a submission.
	 * 
	 * Removes the student's previous submission results and submission.
	 * 
	 * @param submission the Submission to be checked and possibly removed
	 * @return true if the submission was removed, false otherwise
	 */
	private boolean removeSubmission(Submission submission){
		if(submissions.contains(submission)){
			Submission previousSubmission = submissions.get(submissions.indexOf(submission));
			updateSubmissionResults(previousSubmission,-1);
			submissions.remove(submissions.indexOf(previousSubmission));
			return true;
		}
		else
			return false;
	}
	/**
	 * Updates the submission results. This internal method is called by receive/removeSubmission().
	 * 
	 * @param submission the submission containing the candidate Choice(s) to be recorded in results
	 * @param count the amount to modify each choice in results
	 */
	private void updateSubmissionResults(Submission submission,final int count){
		for(Choice answer: submission.getChoices())
			results.put(answer, results.getOrDefault(answer, 0) + count);	
	}

	/**
	 * Allows this IVoteServie to begin receiving submissions. Must not be already open for polling,
	 * and must have a question set up. Calls setup() to reset submissions and results. 
	 * 
	 * @return true if this service is ready to receive submissions, false otherwise
	 */
	public boolean beginPoll(){
		if(!isPolling() && question!=null){
			if(setup()){
				polling = true;
				return true;
			}
		}
		return false;
	}

	/**
	 * Stops this IVoteService from receiving submissions.
	 * @return true if this IVoteService was successfully closed, false otherwise
	 */
	public boolean closePoll(){
		if(isPolling()){
			polling = false;
			return true;
		}
		else
			return false;
	}

	/**
	 * @return the current polling status of this IVoteService
	 */
	public boolean isPolling(){
		return polling;
	}

	/**
	 * Get the most recent results of this IVoteService. Can be called while polling answers, or after 
	 * the poll has closed. If a question has not been set, no results are displayed.
	 * 
	 * The results include statistics for specific choices, as well as the total number of correct students.
	 * 
	 * @return a string representation of the submission results. 
	 */
	public String displayResults(){
		int longest = getLongestChoiceLength();

		String s = String.format("%-"+longest+"s|%1s\n","Choice","Number of times selected");
		@SuppressWarnings("rawtypes")
		Iterator it = results.entrySet().iterator();
		while(it.hasNext()){
			@SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry)it.next();
			s += String.format("%-"+longest+"s|%1d\n", pair.getKey(),pair.getValue());
		}

		s+="Number of Correct Submissions: "+getNumberOfCorrectStudents()+"\n";
		return s;

	}
	/**
	 * @return String representation of all the current submissions
	 */
	public String displaySubmissions(){
		String s = "";
		for(Submission sub : submissions)
			s+=sub+"\n";
		return s;
	}

	/**
	 * @return the total number of correct Submissions currently received
	 */
	public int getNumberOfCorrectStudents(){
		int correct = 0;
		for(Submission s : submissions)
			if(isCorrect(s))
				correct++;
		return correct;
	}
	/**
	 * Internal method for checking if a Submission is correct. 
	 * 
	 * @param s the Submission to be checked
	 * @return true if the choices in s equals the answer choice(s) for the current Question
	 */
	private boolean isCorrect(Submission s){
		if(s.getChoices().size()==question.getAnswer().size()){
			for(Choice c : s.getChoices()){
				if(!question.getAnswer().contains(c))
					return false;
			}
			return true;
		}
		return false;

	}

	/**
	 * Internal method to determine the longest Choice length in order to scale
	 * the display.
	 * 
	 * @return the length of the longest Choice
	 */
	private int getLongestChoiceLength(){
		int longest = 0;
		for(Choice c : getChoices())
			if (c.getStatement().length()>longest)
				longest = c.getStatement().length();
		return longest;
	}

	/**
	 * Get the current choices that can be chosen for the current question.
	 * If a question has not been set, returns null.
	 * 
	 * NOTE: Choices can be retrieved regardless of the polling status.
	 * 
	 * @return the current choices available, null if a question has not been set
	 */
	public List<Choice> getChoices(){
		if(question!=null)
			return question.getChoices();
		else
			return null;

	}

	/**
	 * Internal method for checking if a Submission contains Choices that match
	 * the current available Choices.
	 * 
	 * @param submission the Submission to be checked
	 * @return true if submission contains choices matching the current available choices, false otherwise
	 */
	private boolean isValid(Submission submission){
		for(Choice c : submission.getChoices()){
			if(!question.getChoices().contains(c))
				return false;
		}
		return true;

	}

}
