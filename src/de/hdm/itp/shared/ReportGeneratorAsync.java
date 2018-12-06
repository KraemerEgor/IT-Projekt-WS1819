package de.hdm.itp.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.AllCommentsFromUserReport;
import de.hdm.itp.shared.report.AllLikesFromUserReport;
import de.hdm.itp.shared.report.AllPostsFromUserReport;
import de.hdm.itp.shared.report.AllSubsFromUserReport;
import de.hdm.itp.shared.report.AllSubsOfUserReport;


@RemoteServiceRelativePath("reportgenerator")

public interface ReportGeneratorAsync {
	
	void init(AsyncCallback<Void> callback);

	void setUser(User u, AsyncCallback<Void> callback);
	
	void createAllLikesFromUserReport(User u,
			AsyncCallback<AllLikesFromUserReport> callback);
	
	void createAllSubsFromUserReport(User u,
			AsyncCallback<AllSubsFromUserReport> callback);
	
	void createAllSubsOfUserReport(User u,
			AsyncCallback<AllSubsOfUserReport> callback);
	
	void createAllCommentsFromUserReport(User u, Date dateFrom, Date dateTill, AsyncCallback<AllCommentsFromUserReport> callback);
	
	void createAllPostsFromUserReport(User u,
			AsyncCallback<AllPostsFromUserReport> callback);
	
}