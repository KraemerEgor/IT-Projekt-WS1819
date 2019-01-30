package de.hdm.itp.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.itp.shared.bo.*;
import de.hdm.itp.shared.report.*;

/**
 * Interface des Report Generators zur Rahmenabsteckung für Impl Klasse
 */
@RemoteServiceRelativePath("report")
public interface ReportGenerator extends RemoteService {
	
	/**
	 * Inits method.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void init() throws IllegalArgumentException;

	/**
	 * Sets the user.
	 *
	 * @param u the new user
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void setUser(User u) throws IllegalArgumentException;

	/**
	 * Abrufen und Ausgabe aller Kommentare eines Users entweder allgemein oder in einem spezifisch angegebenem Zeitraum
	 *
	 * @param u the u
	 * @param dateFrom the date from
	 * @param dateTill the date till
	 * @return the all comments from user report
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public abstract AllCommentsFromUserReport createAllCommentsFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException; 

	/**
	 * brufen und Ausgabe aller gesetzten likes eines Users allgemein oder in einem spezifisch angegebenen Zeitraum
	 *
	 * @param u the u
	 * @param dateFrom the date from
	 * @param dateTill the date till
	 * @return the all likes from user report
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public abstract AllLikesFromUserReport createAllLikesFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException;

	/**
	 * Abrufen und Ausgabe aller erstellten Post eines Users allgemein oder in einem spezifisch angegeben Zeitraum
	 *
	 * @param u the u
	 * @param dateFrom the date from
	 * @param dateTill the date till
	 * @return the all posts from user report
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public abstract AllPostsFromUserReport createAllPostsFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException;

	/**
	 * Abrufen und Ausgabe aller abonnierten User des abfragenden Users allgemein oder in einem spezifischen Zeitraum
	 *
	 * @param u the u
	 * @param dateFrom the date from
	 * @param dateTill the date till
	 * @return the all subs from user report
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public abstract AllSubsFromUserReport createAllSubsFromUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException;

	/**
	 * Abrufen und Ausgabe aller User welche den abfragenden User abonniert haben allgemein oder in einem spezifischen Zeitraum
	 *
	 * @param u the u
	 * @param dateFrom the date from
	 * @param dateTill the date till
	 * @return the all subs of user report
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public abstract AllSubsOfUserReport createAllSubsOfUserReport(User u, Date dateFrom, Date dateTill) throws IllegalArgumentException;

	/**
	 * Abrufen und Ausgabe aller Kommentare von allen Posts eines Users allgemein oder in einem spezifischen Zeitraum
	 *
	 * @param u the u
	 * @param dateFrom the date from
	 * @param dateTill the date till
	 * @return the all comments of all posts from user report
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public abstract AllCommentsOfAllPostsFromUserReport createAllCommentsOfAllPostsFromUserReportForm(User u, Date dateFrom,
			Date dateTill) throws IllegalArgumentException;

}
