package de.hdm.itp.server;

import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itp.shared.EditorAdministration;
import de.hdm.itp.shared.ReportGenerator;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.AllCommentsFromUserReport;
import de.hdm.itp.shared.report.AllLikesFromUserReport;
import de.hdm.itp.shared.report.AllPostsFromUserReport;
import de.hdm.itp.shared.report.AllSubsFromUserReport;
import de.hdm.itp.shared.report.AllSubsOfUserReport;
import de.hdm.itp.shared.report.Column;
import de.hdm.itp.shared.report.Row;

/**
 * Die ReportGeneratorImpl Klasse.
 */
@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator{
	
	/** Die Instanz der Klasse der Editor Administration . */
	private EditorAdministration admin = null;
	
	/**
	 * Der Konstruktor für der ReportGeneratorImpl.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public ReportGeneratorImpl () throws IllegalArgumentException {
	}

	
/**
 * Initiiert die EditorAdministrationImpl, damit man auf die Funktionen der EditorAdministration Impl zugreifen kann.
 *
 * @throws IllegalArgumentException the illegal argument exception
 * @see javax.servlet.GenericServlet#init()
 */

	public void init() throws IllegalArgumentException{
		this.admin = EditorAdministrationImpl.editorAdministrationImpl();
	}

	/**
	 * Auslesen des EditorAdministrationsobjekts.
	 *
	 * @return das editor administrations objekt
	 */
	protected EditorAdministration getEditorAdministration() {
		return this.admin;
	}	
	
	/**
	 * Identifizierung des angemeldeten Users.
	 *
	 * @param email the email
	 * @return the user information
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	
	public User getUserInformation (String email) throws IllegalArgumentException{
		
		return this.admin.getUserByEmail(email);
	}


	@Override
	public void setUser(User u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public AllCommentsFromUserReport createAllCommentsFromUserReport(User u) throws IllegalArgumentException {
		
		//if this.getAdministration(== null){return null;}

		AllCommentsFromUserReport result = new AllCommentsFromUserReport();

		result.setTitel("All Ihr Kommentare");

		result.setCreateDate(new Date());

		Row headline = new Row();

		headline.addColumn(new Column("Post des Kommentars"));
		headline.addColumn(new Column("Kommentar"));
		headline.addColumn(new Column("Erstellungsdatum"));
		headline.addColumn(new Column("Änderungsdatum"));

	/* 	headline.addColumn(new Column(""));
		headline.addColumn(new Column(""));
		headline.addColumn(new Column(""));
		headline.addColumn(new Column(""));
		headline.addColumn(new Column(""));
		headline.addColumn(new Column(""));

		*/

		Row commentRow = new Row();



		return result;
		
	}


































	@Override
	public AllLikesFromUserReport createAllLikesFromUserReport(User u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AllPostsFromUserReport createAllPostsFromUserReport(User u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AllSubsFromUserReport createAllSubsFromUserReport(User u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AllSubsOfUserReport createAllSubsOfUserReport(User u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	/*
	   * ***************************************************************************
	   * Abschnitt: Erstellen der Reports
	   * ***************************************************************************
	   */
	
	/**
	*  Erstellen des Reports aller Kontakte, die der angemeldete Nutzer besitzt 
	*  und mit diesem geteilt wurden.
	*
	* @return the all contacts of user report
	*/

}
