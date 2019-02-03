package de.hdm.itp.client.gui.report;

	import java.util.Date;
	import com.google.gwt.user.client.Window;
	import com.google.gwt.user.client.rpc.AsyncCallback;
	import de.hdm.itp.client.ClientsideSettings;
	import de.hdm.itp.shared.ReportGeneratorAsync;
	import de.hdm.itp.shared.bo.User;
	import de.hdm.itp.shared.report.AllCommentsOfAllPostsFromUserReport;
import de.hdm.itp.shared.report.AllMyCommentsFromPostFromUserReport;
import de.hdm.itp.shared.report.HTMLReportWriter;

/**
 * The Class AllCommentsOfAllPostsFromUserReportForm.
 */
/**
 * In dieser Klasse wird unter Anderem das Proxy-Objekt des Report-Generators initialisisert oder geholt.
 * Außerdem wird der Report AllCommentsOfAllPostsFromUserReport über einen asynchronen Aufruf erstellt.
 * @author nilskaper
 *
 */
public class AllCommentsOfAllPostsFromUserReportForm extends ReportResultPanel {
	


		/** The User attribute */
		private User u;
		
		/** The date from attribute. */
		private Date dateFrom;
		
		/** The date till attribute. */
		private Date dateTill;

		/**
		 * Instantiates a new all comments of all posts from user report form.
		 *
		 * @param u the u
		 * @param dateFrom the date from
		 * @param dateTill the date till
		 */
		public AllCommentsOfAllPostsFromUserReportForm(User u, Date dateFrom, Date dateTill) {
			this.u = u;
			this.dateFrom = dateFrom;
			this.dateTill = dateTill;
			
			run();
		}

		/**
		 * Run.
		 */
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


