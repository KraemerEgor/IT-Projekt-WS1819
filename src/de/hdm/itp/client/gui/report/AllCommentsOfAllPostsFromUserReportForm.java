package de.hdm.itp.client.gui.report;

	import java.util.Date;
	import com.google.gwt.user.client.Window;
	import com.google.gwt.user.client.rpc.AsyncCallback;
	import de.hdm.itp.client.ClientsideSettings;
	import de.hdm.itp.shared.ReportGeneratorAsync;
	import de.hdm.itp.shared.bo.User;
	import de.hdm.itp.shared.report.AllCommentsOfAllPostsFromUserReport;
	import de.hdm.itp.shared.report.HTMLReportWriter;

public class AllCommentsOfAllPostsFromUserReportForm extends ReportResultPanel {
	


		private User u;
		private Date dateFrom;
		private Date dateTill;

		public AllCommentsOfAllPostsFromUserReportForm(User u, Date dateFrom, Date dateTill) {
			this.u = u;
			this.dateFrom = dateFrom;
			this.dateTill = dateTill;
			
			run();
		}

		protected void run() {

			ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();

			reportGenerator.createAllCommentsOfAllPostsFromUserReportForm(u, dateFrom, dateTill, new AsyncCallback<AllCommentsOfAllPostsFromUserReport>() {

				public void onFailure(Throwable caught) {

					ClientsideSettings.getLogger().severe("Erzeugen des Reports fehlgeschlagen!");
					Window.alert("Fehlgeschlagen");
					Window.alert(caught.getMessage());

				}

				public void onSuccess(AllCommentsOfAllPostsFromUserReport report) {

					if (report != null) {

						HTMLReportWriter writer = new HTMLReportWriter();
						writer.process(report);
						append(writer.getReportText());

					}
				}

			});
		}
	}


