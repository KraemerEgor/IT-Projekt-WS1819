package de.hdm.itp.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itp.shared.EditorAdministration;
import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.ReportGenerator;
import de.hdm.itp.shared.ReportGeneratorAsync;

public class ClientsideSettings {
	
	
	private static ReportGeneratorAsync reportGenerator = null;
	
	private static EditorAdministrationAsync editorAdministration = null;

	
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


}
