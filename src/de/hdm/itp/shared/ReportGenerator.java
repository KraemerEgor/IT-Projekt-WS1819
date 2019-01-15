package de.hdm.itp.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.itp.shared.bo.*;
import de.hdm.itp.shared.report.*;

/*
 * Interface des Report Generators zur Rahmenabsteckung für Impl Klasse
 */
@RemoteServiceRelativePath("report")
public interface ReportGenerator extends RemoteService {
	
	/*
	 * Init Methode
	 */
	public void init() throws IllegalArgumentException;

	/*
	 * Setzen eines Users
	 */
	public void setUser(User u) throws IllegalArgumentException;

	/*
	 * Abrufen und Ausgabe aller Kommentare eines Users entweder allgemein oder in einem spezifisch angegebenem Zeitraum
	 */
	public abstract AllCommentsFromUserReport createAllCommentsFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException; 

	/*
	 * Abrufen und Ausgabe aller gesetzten likes eines Users allgemein oder in einem spezifisch angegebenen Zeitraum
	 */
	public abstract AllLikesFromUserReport createAllLikesFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException;

	/*
	 * Abrufen und Ausgabe aller erstellten Post eines Users allgemein oder in einem spezifisch angegeben Zeitraum
	 */
	public abstract AllPostsFromUserReport createAllPostsFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException;

	/*
	 * Abrufen und Ausgabe aller abonnierten User des abfragenden Users allgemein oder in einem spezifischen Zeitraum
	 */
	public abstract AllSubsFromUserReport createAllSubsFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException;

	/*
	 * Abrufen und Ausgabe aller User welche den abfragenden User abonniert haben allgemein oder in einem spezifischen Zeitraum
	 */
	public abstract AllSubsOfUserReport createAllSubsOfUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException;

	/*
	 * Abrufen und Ausgabe aller Kommentare von allen Posts eines Users allgemein oder in einem spezifischen Zeitraum
	 */
	public abstract AllCommentsOfAllPostsFromUserReport createAllCommentsOfAllPostsFromUserReportForm(User u, Date dateFrom,
			Date dateTill) throws IllegalArgumentException;

}
