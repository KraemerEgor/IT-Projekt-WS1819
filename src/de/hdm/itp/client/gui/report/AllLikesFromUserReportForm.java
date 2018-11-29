package de.hdm.itp.client.gui.report;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itp.client.ClientsideSettings;
import de.hdm.itp.shared.report.AllLikesFromUserReport;
import de.hdm.itp.shared.report.HTMLReportWriter;

public class AllLikesFromUserReportForm implements AsyncCallback<AllLikesFromUserReport> {

	@Override
	public void onFailure(Throwable caught) {

		ClientsideSettings.getLogger().severe("Erzeugen des Reports fehlgeschlagen!");
		Window.alert(caught.getMessage());

	}

	@Override
	public void onSuccess(AllLikesFromUserReport report) {

		if (report != null) {

			HTMLReportWriter writer = new HTMLReportWriter();
			writer.process(report);
			RootPanel.get().clear();
			RootPanel.get().add(new HTML(writer.getReportText()));

		}
	}
}