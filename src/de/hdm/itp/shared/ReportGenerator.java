package de.hdm.itp.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.itp.shared.bo.*;
import de.hdm.itp.shared.report.*;

@RemoteServiceRelativePath("report")
public interface ReportGenerator extends RemoteService {
	
	public void init() throws IllegalArgumentException;

	public void setUser(User u) throws IllegalArgumentException;

	public abstract AllCommentsFromUserReport createAllCommentsFromUserReport(User u) throws IllegalArgumentException; 

	public abstract AllLikesFromUserReport createAllLikesFromUserReport(User u) throws IllegalArgumentException;

	public abstract AllPostsFromUserReport createAllPostsFromUserReport(User u) throws IllegalArgumentException;

	public abstract AllSubsFromUserReport createAllSubsFromUserReport(User u) throws IllegalArgumentException;

	public abstract AllSubsOfUserReport createAllSubsOfUserReport(User u) throws IllegalArgumentException;	

}
