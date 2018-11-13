package de.hdm.itp.shared.report;

/**
 * 
 *
 */
public abstract class ReportWriter {

	/**
	 * 
	 */
	public abstract void process(AllSubsFromUserReport r);
	
	/**
	 * 
	 */
	public abstract void process(AllSubsOfUserReport r);
	
	/**
	 * 
	 */
	public abstract void process(AllCommentsFromUserReport r);
	
	/**
	 * 
	 */
	public abstract void process(AllLikesFromUserReport r);
	
	/**
	 * 
	 */
	public abstract void process(AllPostsFromUserReport r);
	
	
}
