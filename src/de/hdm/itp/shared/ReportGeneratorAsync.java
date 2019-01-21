package de.hdm.itp.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.AllCommentsFromUserReport;
import de.hdm.itp.shared.report.AllCommentsOfAllPostsFromUserReport;
import de.hdm.itp.shared.report.AllLikesFromUserReport;
import de.hdm.itp.shared.report.AllPostsFromUserReport;
import de.hdm.itp.shared.report.AllSubsFromUserReport;
import de.hdm.itp.shared.report.AllSubsOfUserReport;


@RemoteServiceRelativePath("reportgenerator")

public interface ReportGeneratorAsync {
	
	/*
	 * Asynchrone init Methode
	 */
	void init(AsyncCallback<Void> callback);

	/*
	 * Setzen eines User
	 */
	void setUser(User u, AsyncCallback<Void> callback);
	
	/*
	 * Erstellen eines Reports aller vergebenen Likes eines Users
	 */
	void createAllLikesFromUserReport(User u,Date dateFrom, Date dateTill, AsyncCallback<AllLikesFromUserReport> callback);
	
	/*
	 * Erstellen eines Report aller vergebenen Abos eines Users
	 */
	void createAllSubsFromUserReport(User u,Date dateFrom, Date dateTill, AsyncCallback<AllSubsFromUserReport> callback);
	
	/*
	 * Erstellen eines Reports aller Abonnenenten eines Users
	 */
	void createAllSubsOfUserReport(User u, Date dateFrom, Date dateTill, AsyncCallback<AllSubsOfUserReport> callback);
	
	/*
	 * Erstellen eines Reports aller Kommentare eines Users
	 */
	void createAllCommentsFromUserReport(User u, Date dateFrom, Date dateTill, AsyncCallback<AllCommentsFromUserReport> callback);
	
	/*
	 * Erstellen eines Reports aller Posts eines Users
	 */
	void createAllPostsFromUserReport(User u, Date dateFrom, Date dateTill, AsyncCallback<AllPostsFromUserReport> callback);
	
	/*
	 * Erstellen eines Reports aller Kommentare von allen geschriebenen Posts eines Users
	 */
	void createAllCommentsOfAllPostsFromUserReportForm (User u, Date dateFrom, Date dateTill, AsyncCallback<AllCommentsOfAllPostsFromUserReport> callback);
}