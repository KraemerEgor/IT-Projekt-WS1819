package de.hdm.itp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itp.shared.EditorAdministration;
import de.hdm.itp.shared.ReportGenerator;
import de.hdm.itp.shared.bo.User;

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
