package de.hdm.itp.client.gui.report;

import java.util.Date;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.itp.client.ClientsideSettings;
import de.hdm.itp.shared.ReportGeneratorAsync;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.AllCommentsFromUserReport;
import de.hdm.itp.shared.report.HTMLReportWriter;

public class AllCommentsFromUserReportForm extends ReportResultPanel {

	private User u;
	private Date dateFrom;
	private Date dateTill;
	

	public AllCommentsFromUserReportForm(User u, Date dateFrom, Date dateTill) {
		this.u = u;
		this.dateFrom = dateFrom;
		this.dateTill = dateTill;
		
		run();
	}

	protected void run() {

		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();

		reportGenerator.createAllCommentsFromUserReport(u,  dateFrom, dateTill, new AsyncCallback<AllCommentsFromUserReport>() {

			public void onFailure(Throwable caught) {
			

				ClientsideSettings.getLogger().severe("Erzeugen des Reports fehlgeschlagen!");
				Window.alert("Fehlgeschlagen");
				Window.alert(caught.getMessage());

			}

			
			public void onSuccess(AllCommentsFromUserReport report) {

				if (report != null) {

					HTMLReportWriter writer = new HTMLReportWriter();
					writer.process(report);
					append(writer.getReportText());

				}
			}

		});
	}
}