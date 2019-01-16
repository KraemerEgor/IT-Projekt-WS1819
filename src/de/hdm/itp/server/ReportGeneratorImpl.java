package de.hdm.itp.server;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.itp.shared.EditorAdministration;
import de.hdm.itp.shared.ReportGenerator;
import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.Subs;
import de.hdm.itp.shared.bo.User;
import de.hdm.itp.shared.report.AllCommentsFromUserReport;
import de.hdm.itp.shared.report.AllCommentsOfAllPostsFromUserReport;
import de.hdm.itp.shared.report.AllLikesFromUserReport;
import de.hdm.itp.shared.report.AllPostsFromUserReport;
import de.hdm.itp.shared.report.AllSubsFromUserReport;
import de.hdm.itp.shared.report.AllSubsOfUserReport;
import de.hdm.itp.shared.report.Column;
import de.hdm.itp.shared.report.Row;
import de.hdm.itp.shared.report.SimpleParagraph;

/**
 * Die ReportGeneratorImpl Klasse.
 */
@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	/** Die Instanz der Klasse der Editor Administration . */
	private EditorAdministration admin = null;

	/**
	 * Der Konstruktor f�r der ReportGeneratorImpl.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public ReportGeneratorImpl() throws IllegalArgumentException {
	}

	/**
	 * Initiiert die EditorAdministrationImpl, damit man auf die Funktionen der
	 * EditorAdministration Impl zugreifen kann.
	 *
	 * @throws IllegalArgumentException the illegal argument exception
	 * @see javax.servlet.GenericServlet#init()
	 */

	public void init() throws IllegalArgumentException {

		this.admin = EditorAdministrationImpl.editorAdministrationImpl();
		this.admin.init();
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

	public User getUserInformation(String email) throws IllegalArgumentException {

		return this.admin.getUserByEmail(email);
	}

	/*
	 * (non-Javadoc)
	 * @see de.hdm.itp.shared.ReportGenerator#setUser(de.hdm.itp.shared.bo.User)
	 */
	public void setUser(User u) throws IllegalArgumentException {

	}

	/*
	 * ***************************************************************************
	 * Abschnitt: Erstellen der Reports
	 * ***************************************************************************
	 */

	/**
	 * Erstellen des Reports aller Kontakte, die der angemeldete Nutzer besitzt und
	 * mit diesem geteilt wurden.
	 *
	 * @return the all contacts of user report
	 */

	@Override

	// AllCommentsFromUserReport report schreiben ALLPOSTSANDCOMMENTSFROMUSERREPORT
	// compostite report

	public AllCommentsFromUserReport createAllCommentsFromUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {
		
		 if( this.getEditorAdministration()== null){
			 return null;
			 }

		AllCommentsFromUserReport result = new AllCommentsFromUserReport();

		int i = 0;
		
		result.setCreateDate(new Date());

		Row headline = new Row();

		headline.addColumn(new Column("Kommentierter Beitrag"));
		headline.addColumn(new Column("Kommentar"));
		headline.addColumn(new Column("Erstellungsdatum"));
		headline.addColumn(new Column("Änderungsdatum"));

		result.addRow(headline);

		Vector<Comment> comments = this.admin.getCommentsOfUser(u);

		for (Comment c : comments) {
			Row commentRow = new Row();

			if (dateFrom == null) {
				i++;
				commentRow.addColumn(new Column(String.valueOf(this.admin.getPostById(c.getPostId()).getContent())));
				commentRow.addColumn(new Column(String.valueOf(c.getText())));
				commentRow.addColumn(new Column(String.valueOf(c.getCreateDate())));
				commentRow.addColumn(new Column(String.valueOf(c.getModDate())));

				result.addRow(commentRow);

				result.setTitel("All Ihre Kommentare ");
				result.setAmount("Anzahl Ihrer Kommentare: " + i);


			} else {
				if (c.getModDate().after(dateFrom) && c.getModDate().before(dateTill)) {
					i++;
					commentRow.addColumn(new Column(String.valueOf(this.admin.getPostById(c.getPostId()).getContent())));
					commentRow.addColumn(new Column(String.valueOf(c.getText())));
					commentRow.addColumn(new Column(String.valueOf(c.getCreateDate())));
					commentRow.addColumn(new Column(String.valueOf(c.getModDate())));

					result.addRow(commentRow);
					
					
					result.setTitel("Anzahl Ihrer Kommentaren in dem ausgewählten Zeitraum " + i);

				} else if (i >= 1) {
					result.setTitel("Anzahl Ihrer Kommentaren in dem ausgewählten Zeitraum " + i);

				} else {
					result.setTitel("Tut uns leid in dem Ausgewählten Zeitraum wurden keine Kommentare erstellt");
				}
			}

		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see de.hdm.itp.shared.ReportGenerator#createAllLikesFromUserReport(de.hdm.itp.shared.bo.User, java.util.Date, java.util.Date)
	 */
	public AllLikesFromUserReport createAllLikesFromUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {

		 if( this.getEditorAdministration()== null){
			 return null;
			 }
		AllLikesFromUserReport result = new AllLikesFromUserReport();

		int i = 0;

		result.setCreateDate(new Date());

		Row headline = new Row();
		headline.addColumn(new Column("Beitragstext"));
		headline.addColumn(new Column("Beitrag erstellt von"));
		headline.addColumn(new Column("Erstellungsdatum des Beitrags"));
		headline.addColumn(new Column("Erstellungsdatum des Likes"));

		result.addRow(headline);

		Vector<Like> like = this.admin.getAllLikesOfUser(u);

		for (Like l : like) {
			Row likesRow = new Row();

			if (dateFrom == null) {
				i++;

				Post p = this.admin.getPostById(l.getPostId());

				likesRow.addColumn(new Column(String.valueOf(p.getContent())));
				likesRow.addColumn(new Column(String.valueOf(this.admin.getUserById(l.getOwnerId()).getNickname())));
				likesRow.addColumn(new Column(String.valueOf(p.getCreateDate())));
				likesRow.addColumn(new Column(String.valueOf(l.getCreateDate())));

				result.addRow(likesRow);

				result.setTitel("All Ihre Likes ");
				result.setAmount("Anzahl Ihrer Likes : " + i);


			} else {
				if (l.getCreateDate().after(dateFrom) && l.getCreateDate().before(dateTill)) {
					i++;

					Post p = this.admin.getPostById(l.getPostId());

					likesRow.addColumn(new Column(String.valueOf(p.getContent())));
					likesRow.addColumn(new Column(String.valueOf(this.admin.getUserById(l.getOwnerId()).getNickname())));
					likesRow.addColumn(new Column(String.valueOf(p.getCreateDate())));
					likesRow.addColumn(new Column(String.valueOf(l.getCreateDate())));

					result.addRow(likesRow);

					result.setTitel(" Anzahl Ihrer Likes in dem ausgewählten Zeitraum " + i);
					

				} else if (i >= 1) {
					result.setTitel(" Anzahl Ihrer Likes in dem ausgewählten Zeitraum " + i);

				} else {
					result.setTitel("Tut uns leid in dem Ausgewählten Zeitraum wurden keine Likes erstellt");
				}

			}

		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * @see de.hdm.itp.shared.ReportGenerator#createAllPostsFromUserReport(de.hdm.itp.shared.bo.User, java.util.Date, java.util.Date)
	 */
	public AllPostsFromUserReport createAllPostsFromUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {

		 if( this.getEditorAdministration()== null){
			 return null;
			 }
		AllPostsFromUserReport result = new AllPostsFromUserReport();

		int i = 0;

		result.setCreateDate(new Date());

		Row headline = new Row();

		headline.addColumn(new Column("Beitragstext"));
		headline.addColumn(new Column("Erstellungsdatum"));
		headline.addColumn(new Column("Änderungsdatum"));
		headline.addColumn(new Column("Anzahl der Likes"));


		result.addRow(headline);

		Vector<Post> post = this.admin.getAllPostsOfUser(u);

		for (Post p : post) {
			Row postRow = new Row();

			if (dateFrom == null) {
				i++;
				postRow.addColumn(new Column(String.valueOf(p.getContent())));
				postRow.addColumn(new Column(String.valueOf(p.getCreateDate())));
				postRow.addColumn(new Column(String.valueOf(p.getModDate())));
				postRow.addColumn(new Column(String.valueOf(this.admin.getAllLikesOfPost(p).size())));


				result.addRow(postRow);

				result.setTitel("All Ihre Posts ");
				result.setAmount("Anzahl Ihrer Posts: " + i);

			} else {
				if (p.getModDate().after(dateFrom) && p.getModDate().before(dateTill)) {
					i++;
					postRow.addColumn(new Column(String.valueOf(p.getContent())));
					postRow.addColumn(new Column(String.valueOf(p.getCreateDate())));
					postRow.addColumn(new Column(String.valueOf(p.getModDate())));
					postRow.addColumn(new Column(String.valueOf(this.admin.getAllLikesOfPost(p).size())));


					result.addRow(postRow);

					result.setTitel(" Anzahl Ihrer Posts in dem ausgewählten Zeitraum " + i);



				} else if (i >= 1) {
					result.setTitel(" Anzahl Ihrer Posts in dem ausgewählten Zeitraum " + i);

				} else {
					result.setTitel("Tut uns leid in dem Ausgewählten Zeitraum wurden keine Posts erstellt");
				}

			}

		}

		return result;

	}
	/*
	 * (non-Javadoc)
	 * @see de.hdm.itp.shared.ReportGenerator#createAllSubsFromUserReport(de.hdm.itp.shared.bo.User, java.util.Date, java.util.Date)
	 */
	public AllSubsFromUserReport createAllSubsFromUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {

		 if( this.getEditorAdministration()== null){
			 return null;
			 }
		AllSubsFromUserReport result = new AllSubsFromUserReport();

		int i = 0;

		result.setCreateDate(new Date());

		Row headline = new Row();
		headline.addColumn(new Column("Vorname des Abonnenten"));
		headline.addColumn(new Column("Nachname des Abonnenten"));
		headline.addColumn(new Column("Nickname des Abonnenten"));
		headline.addColumn(new Column("Erstellungsdatum des Abonnements"));

		result.addRow(headline);

		Vector<Subs> subs = this.admin.getSubsOfTargetUser(u);

		for (Subs s : subs) {
			Row subsRow = new Row();

			if (dateFrom == null) {
				
				i++;
				User u2 = this.admin.getUserById(s.getCurrentUser());
				subsRow.addColumn(new Column(String.valueOf(u2.getFirstname())));
				subsRow.addColumn(new Column(String.valueOf(u2.getLastname())));
				subsRow.addColumn(new Column(String.valueOf(u2.getNickname())));
				subsRow.addColumn(new Column(String.valueOf(s.getCreateDate())));
				result.addRow(subsRow);

				result.setTitel("Hier sehen Sie Ihre Abonnements "); 
				result.setAmount("Anzahlt Ihrer Abonnements: " + i);
			} else {
				if (s.getCreateDate().after(dateFrom) && s.getCreateDate().before(dateTill)) {
					i++;
					User u2 = this.admin.getUserById(s.getCurrentUser());
					subsRow.addColumn(new Column(String.valueOf(u2.getFirstname())));
					subsRow.addColumn(new Column(String.valueOf(u2.getLastname())));
					subsRow.addColumn(new Column(String.valueOf(u2.getNickname())));
					subsRow.addColumn(new Column(String.valueOf(s.getCreateDate())));

					result.addRow(subsRow);
					
					result.setTitel("Anzahl Ihrer Abonnements im ausgewählten Zeitraum: " + i);


				} else if (i >= 1) {
					result.setAmount("Anzahl Ihrer Abonnements im ausgewählten Zeitraum: " + i);
					

				} else {
					result.setTitel(
							"Tut uns leid in dem ausgewählten Zeitraum wurden kein Abonnement von Ihnen vergeben");
				}

			}

		}

		return result;

	}
	/*
	 * (non-Javadoc)
	 * @see de.hdm.itp.shared.ReportGenerator#createAllSubsOfUserReport(de.hdm.itp.shared.bo.User, java.util.Date, java.util.Date)
	 */
	public AllSubsOfUserReport createAllSubsOfUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {
		 if( this.getEditorAdministration()== null){
			 return null;
			 }

		AllSubsOfUserReport result = new AllSubsOfUserReport();

		int i = 0;

		result.setCreateDate(new Date());

		Row headline = new Row();

		headline.addColumn(new Column("Vorname des Abonnierten Benutzers"));
		headline.addColumn(new Column("Nachname des Abonnierten Benutzers"));
		headline.addColumn(new Column("Nickname des Abonnierten Benutzers"));
		headline.addColumn(new Column("Abonnent seit"));

		result.addRow(headline);

		Vector<Subs> subs = this.admin.getSubsOfCurrentUser(u);

		for (Subs s : subs) {
			Row subsRow = new Row();

			if (dateFrom == null) {
				i++;

				User u2 = this.admin.getUserById(s.getTargetUser());
				subsRow.addColumn(new Column(String.valueOf(u2.getFirstname())));
				subsRow.addColumn(new Column(String.valueOf(u2.getLastname())));
				subsRow.addColumn(new Column(String.valueOf(u2.getNickname())));
				subsRow.addColumn(new Column(String.valueOf(s.getCreateDate())));
				result.addRow(subsRow);

				result.setTitel("All Ihre Abonnenten");
				result.setAmount("Anzahlt Ihrer Abonnenten: " + i);

			} else {
				if (s.getCreateDate().after(dateFrom) && s.getCreateDate().before(dateTill)) {
					i++;
					User u2 = this.admin.getUserById(s.getTargetUser());
					subsRow.addColumn(new Column(String.valueOf(u2.getFirstname())));
					subsRow.addColumn(new Column(String.valueOf(u2.getLastname())));
					subsRow.addColumn(new Column(String.valueOf(u2.getNickname())));
					subsRow.addColumn(new Column(String.valueOf(s.getCreateDate())));

					result.addRow(subsRow);

					result.setTitel("All Ihre Abonnenten");
					result.setAmount("Anzahlt Ihrer Abonnenten im augewählten Zeitraum: " + i);


				} else if (i >= 1) {
					result.setAmount(" Anzahl Ihrer Abonnenten in dem ausgewählten Zeitraum: " + i);

				} else {
					result.setTitel("Tut uns leid in dem Ausgewählten Zeitraum hat Sie keiner Abonniert");
				}

			}

		}

		return result;

	}
	/*
	 * (non-Javadoc)
	 * @see de.hdm.itp.shared.ReportGenerator#createAllCommentsOfAllPostsFromUserReportForm(de.hdm.itp.shared.bo.User, java.util.Date, java.util.Date)
	 */
	@Override
	public AllCommentsOfAllPostsFromUserReport createAllCommentsOfAllPostsFromUserReportForm(User u, Date dateFrom,
			Date dateTill) throws IllegalArgumentException {
		
		 if( this.getEditorAdministration()== null){
			 return null;
			 }

		AllCommentsOfAllPostsFromUserReport result = new AllCommentsOfAllPostsFromUserReport();

		result.setTitel("All Ihr kommentare mit den den dazugehörigen Beiträgen");

		result.setCreateDate(new Date());

		Vector<Post> posts = this.admin.getAllPostsOfUser(u);

		if (posts.size() != 0) {

			if (dateFrom == null) {
				
				for (Post p : posts) {
					
					//createAllPostsWithCommentFromUserReport
					
					result.addSubReport(this.createAllPostsFromUserReport(u, dateFrom, dateTill));
					
					Vector<Comment> comments = this.admin.getCommentsOfPost(p);
					
					
					if (comments.size() != 0) {
						
						result.addSubReport(this.createAllCommentsFromUserReport(u, dateFrom, dateTill));
						
					} else {
						
						SimpleParagraph errornote = new SimpleParagraph("Es wurden leider keine Kommentar gefunden");
						 

						result.setHeader(errornote);
					}
				}

			} else {

				Vector<Comment> CommentDate = this.admin.getCommentsOfUser(u);
				for (Comment cD : CommentDate) {
					if (cD.getModDate().after(dateFrom) && cD.getModDate().before(dateTill)) {
						for (Post p : posts) {
							result.addSubReport(this.createAllPostsFromUserReport(u, dateFrom, dateTill));
							Vector<Comment> comments = this.admin.getCommentsOfPost(p);
							if (comments.size() != 0) {
								result.addSubReport(this.createAllCommentsFromUserReport(u, dateFrom, dateTill));
							} else {
								SimpleParagraph errornote = new SimpleParagraph(
										"Es wurden leider keine Kommentar gefunden");

								result.setHeader(errornote);
							}

						}

					}

				}
			}

		}
		return result;

	}
}