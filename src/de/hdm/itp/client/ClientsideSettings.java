package de.hdm.itp.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itp.shared.EditorAdministration;
import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.ReportGenerator;
import de.hdm.itp.shared.ReportGeneratorAsync;
import de.hdm.itp.shared.bo.User;

public class ClientsideSettings {
	
	/** Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen 
	 * Dienst namens editorAdministration. */
	private static EditorAdministrationAsync editorAdministration = null;
	
	private static User user = null;
	
	private static ReportGeneratorAsync reportGenerator = null;
	
	private static final String LOGGER_NAME = "";

	
	private static final Logger log = Logger.getLogger(LOGGER_NAME);

	public static Logger getLogger() {
		return log;
		
	}
	
	public static EditorAdministrationAsync getAdministration() {
		
		if (editorAdministration == null) {

			editorAdministration = GWT.create(EditorAdministration.class);

			final AsyncCallback<Void> initAdministrationCallback = new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe("Die Administration konnte nicht initialisiert werden!");
				}

				@Override
				public void onSuccess(Void result) {
					ClientsideSettings.getLogger().info("Die Administration wurde initialisiert.");
				}
			};

			editorAdministration.init(initAdministrationCallback);
		}

		return editorAdministration;
	}

	
	public static ReportGeneratorAsync getReportGenerator() {

		if (reportGenerator == null) {

			reportGenerator = GWT.create(ReportGenerator.class);

			final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe("Der ReportGenerator konnte nicht initialisiert werden!");
				}

				@Override
				public void onSuccess(Void result) {
					ClientsideSettings.getLogger().info("Der ReportGenerator wurde initialisiert.");
				}
			};

			reportGenerator.init(initReportGeneratorCallback);
		}

		return reportGenerator;
	}
	/**
	 * Setter für das Nutzer-Objekt.
	 *
	 * @param u neuer Nutzer
	 */
	public static void setUser(User u) {
		user = u;
	}
	
	/**
	 * Getter für das Nutzer-Objekt.
	 *
	 * @return Nutzer-Object
	 */
	public static User getUser() {
		return user;	
		
	}
	/**
	 * Anlegen und Auslesen der applikationsweit eindeutigen EditorAdministration. Diese
     * Methode erstellt die EditorAdministration, sofern sie noch nicht existiert. Bei
     * wiederholtem Aufruf dieser Methode wird das bereits zuvor angelegte Objekt zurückgegeben.
	 *
	 * @return eindeutige Instanz des Typs EditorAdministrationAsync
	 */
	public static EditorAdministrationAsync getEditorAdministration() {
	   
	    if (editorAdministration == null) {
	     editorAdministration = GWT.create(EditorAdministration.class);
	    }
	    return editorAdministration;
	  }

}
