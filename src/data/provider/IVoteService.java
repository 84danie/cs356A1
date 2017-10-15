package data.provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.Choice;
import data.Question;
import data.Submission;

/**
 * @author danie
 * 
 * Class that simulates the iVote service. An IVoteService is configured with a question,
 * and can accept submissions from Students while polling.
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
		results = null;
		submissions = null;
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
			results = new HashMap<Choice,Integer>(choices.size());
			for(Choice c : choices)
				results.put(c,0);
			submissions = new ArrayList<Submission>();
			return true;
		}
		else
			return false;
	}
	

	/**
	 * @return a String representation of the current question and its candidate choices
	 */
	public String displayQuestion(){
		return question.toString();

	}
	/**
	 * Receives a submission if this IVoteService is currently polling answers. 
	 * This method can only be called by Students. 
	 * 
	 * @param newSubmission the submission to be received
	 * @return true if the submission was successfully received, false otherwise
	 */
	protected boolean receiveSubmission(Submission newSubmission){
		if(isPolling()){
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
			updateSubmissionResults(submission,-1);
			submissions.remove(submissions.indexOf(submission));
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
	 * the poll has closed.
	 * 
	 * @return a string representation of the submission results. 
	 */
	public String displayResults(){
		return results.toString();

	}

}
