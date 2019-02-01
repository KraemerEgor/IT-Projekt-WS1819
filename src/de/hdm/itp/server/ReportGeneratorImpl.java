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
import de.hdm.itp.shared.report.AllMyCommentsFromPostFromUserReport;
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
	 * 
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
	 * @param u the u
	 * @param dateFrom the date from
	 * @param dateTill the date till
	 * @return the all contacts of user report
	 * @throws IllegalArgumentException the illegal argument exception
	 */

	@Override

	// AllCommentsFromUserReport report schreiben ALLPOSTSANDCOMMENTSFROMUSERREPORT
	// compostite report

	public AllCommentsFromUserReport createAllCommentsFromUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {

		if (this.getEditorAdministration() == null) {
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
				// hier werden die Millisekunden vom Create_Date rausgeschnitten
				String fullcdate = c.getCreateDate().toString();
				String[] cparts = fullcdate.split(" ");
				String cutcdate = cparts[0];
				commentRow.addColumn(new Column(String.valueOf(cutcdate)));
				// hier werden die Millisekunden vom Mod_Date rausgeschnitten
				String fullmdate = c.getModDate().toString();
				String[] mparts = fullmdate.split(" ");
				String cutmdate = mparts[0];
				commentRow.addColumn(new Column(String.valueOf(cutmdate)));

				result.addRow(commentRow);

				result.setTitel("Alle Ihre Kommentare ");
				result.setAmount("Anzahl Ihrer Kommentare: " + i);

			} else {
				if (c.getModDate().after(dateFrom) && c.getModDate().before(dateTill)) {
					i++;
					commentRow
							.addColumn(new Column(String.valueOf(this.admin.getPostById(c.getPostId()).getContent())));
					commentRow.addColumn(new Column(String.valueOf(c.getText())));
					// hier werden die Millisekunden vom Create_Date rausgeschnitten
					String fullcdate = c.getCreateDate().toString();
					String[] cparts = fullcdate.split(" ");
					String cutcdate = cparts[0];
					commentRow.addColumn(new Column(String.valueOf(cutcdate)));
					// hier werden die Millisekunden vom Mod_Date rausgeschnitten
					String fullmdate = c.getModDate().toString();
					String[] mparts = fullmdate.split(" ");
					String cutmdate = mparts[0];
					commentRow.addColumn(new Column(String.valueOf(cutmdate)));

					result.addRow(commentRow);

					result.setAmount("Anzahl Ihrer Kommentare im ausgewählten Zeitraum: " + i);

				} else if (i >= 1) {
					result.setAmount("Anzahl Ihrer Kommentare im ausgewählten Zeitraum: " + i);

				} else {
					result.setTitel("Tut uns leid! Im ausgewählten Zeitraum wurden keine Kommentare erstellt");
				}
			}

		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.shared.ReportGenerator#createAllLikesFromUserReport(de.hdm.itp.
	 * shared.bo.User, java.util.Date, java.util.Date)
	 */
	public AllLikesFromUserReport createAllLikesFromUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {

		if (this.getEditorAdministration() == null) {
			return null;
		}
		AllLikesFromUserReport result = new AllLikesFromUserReport();

		int i = 0;

		result.setCreateDate(new Date());

		Row headline = new Row();
		headline.addColumn(new Column("Beitragstext:"));
		headline.addColumn(new Column("Beitrag erstellt von:"));
		headline.addColumn(new Column("Erstellungsdatum des Beitrags:"));
		headline.addColumn(new Column("Erstellungsdatum des Likes:"));

		result.addRow(headline);

		Vector<Like> like = this.admin.getAllLikesOfUser(u);

		for (Like l : like) {
			Row likesRow = new Row();

			if (dateFrom == null) {
				i++;

				Post p = this.admin.getPostById(l.getPostId());

				likesRow.addColumn(new Column(String.valueOf(p.getContent())));
				likesRow.addColumn(new Column(String.valueOf(this.admin.getUserById(l.getOwnerId()).getNickname())));
				// hier werden die Millisekunden vom Create_Date rausgeschnitten
				String fullcdate = p.getCreateDate().toString();
				String[] cparts = fullcdate.split(" ");
				String cutcdate = cparts[0];
				likesRow.addColumn(new Column(String.valueOf(cutcdate)));
				// hier werden die Millisekunden vom Create_Date rausgeschnitten
				String fullcldate = l.getCreateDate().toString();
				String[] clparts = fullcldate.split(" ");
				String cutcldate = clparts[0];
				likesRow.addColumn(new Column(String.valueOf(cutcldate)));

				result.addRow(likesRow);

				result.setTitel("Alle Ihre Likes ");
				result.setAmount("Anzahl Ihrer Likes: " + i);

			} else {
				if (l.getCreateDate().after(dateFrom) && l.getCreateDate().before(dateTill)) {
					i++;

					Post p = this.admin.getPostById(l.getPostId());

					likesRow.addColumn(new Column(String.valueOf(p.getContent())));
					likesRow.addColumn(
							new Column(String.valueOf(this.admin.getUserById(l.getOwnerId()).getNickname())));
					// hier werden die Millisekunden vom Create_Date rausgeschnitten
					String fullcdate = p.getCreateDate().toString();
					String[] cparts = fullcdate.split(" ");
					String cutcdate = cparts[0];
					likesRow.addColumn(new Column(String.valueOf(cutcdate)));
					// hier werden die Millisekunden vom Create_Date rausgeschnitten
					String fullcldate = l.getCreateDate().toString();
					String[] clparts = fullcldate.split(" ");
					String cutcldate = clparts[0];
					likesRow.addColumn(new Column(String.valueOf(cutcldate)));

					result.addRow(likesRow);

					result.setAmount("Anzahl Ihrer Likes im ausgewählten Zeitraum: " + i);

				} else if (i >= 1) {
					result.setAmount("Anzahl Ihrer Likes im ausgewählten Zeitraum: " + i);

				} else {
					result.setTitel("Tut uns leid! Im ausgewählten Zeitraum wurden keine Likes erstellt");
				}

			}

		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.shared.ReportGenerator#createAllPostsFromUserReport(de.hdm.itp.
	 * shared.bo.User, java.util.Date, java.util.Date)
	 */
	public AllPostsFromUserReport createAllPostsFromUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {

		if (this.getEditorAdministration() == null) {
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
				// hier werden die Millisekunden vom Create_Date rausgeschnitten
				String fullcdate = p.getCreateDate().toString();
				String[] cparts = fullcdate.split(" ");
				String cutcdate = cparts[0];
				postRow.addColumn(new Column(String.valueOf(cutcdate)));
				// hier werden die Millisekunden vom Mod_Date rausgeschnitten
				String fullmdate = p.getModDate().toString();
				String[] mparts = fullmdate.split(" ");
				String cutmdate = mparts[0];
				postRow.addColumn(new Column(String.valueOf(cutmdate)));
				postRow.addColumn(new Column(String.valueOf(this.admin.getAllLikesOfPost(p).size())));

				result.addRow(postRow);

				result.setTitel("Alle Ihre Beiträge ");
				result.setAmount("Anzahl Ihrer Beiträge: " + i);

			} else {
				if (p.getModDate().after(dateFrom) && p.getModDate().before(dateTill)) {
					i++;
					postRow.addColumn(new Column(String.valueOf(p.getContent())));
					// hier werden die Millisekunden vom Create_Date rausgeschnitten
					String fullcdate = p.getCreateDate().toString();
					String[] cparts = fullcdate.split(" ");
					String cutcdate = cparts[0];
					postRow.addColumn(new Column(String.valueOf(cutcdate)));
					// hier werden die Millisekunden vom Mod_Date rausgeschnitten
					String fullmdate = p.getModDate().toString();
					String[] mparts = fullmdate.split(" ");
					String cutmdate = mparts[0];
					postRow.addColumn(new Column(String.valueOf(cutmdate)));
					postRow.addColumn(new Column(String.valueOf(this.admin.getAllLikesOfPost(p).size())));

					result.addRow(postRow);

					result.setAmount("Anzahl Ihrer Beiträge im ausgewählten Zeitraum: " + i);

				} else if (i >= 1) {
					result.setAmount("Anzahl Ihrer Beiträge im ausgewählten Zeitraum: " + i);

				} else {
					result.setTitel("Tut uns leid! Im ausgewählten Zeitraum wurden keine Beiträge erstellt");
				}

			}

		}

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.shared.ReportGenerator#createAllSubsFromUserReport(de.hdm.itp.
	 * shared.bo.User, java.util.Date, java.util.Date)
	 */
	public AllSubsFromUserReport createAllSubsFromUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {

		if (this.getEditorAdministration() == null) {
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
				// hier werden die Millisekunden vom Create_Date rausgeschnitten
				String fullcdate = u2.getCreateDate().toString();
				String[] cparts = fullcdate.split(" ");
				String cutcdate = cparts[0];
				subsRow.addColumn(new Column(String.valueOf(cutcdate)));
				result.addRow(subsRow);

				result.setTitel("Hier sehen Sie Ihre Abonnenten ");
				result.setAmount("Anzahl Ihrer Abonnenten: " + i);
			} else {
				if (s.getCreateDate().after(dateFrom) && s.getCreateDate().before(dateTill)) {
					i++;
					User u2 = this.admin.getUserById(s.getCurrentUser());
					subsRow.addColumn(new Column(String.valueOf(u2.getFirstname())));
					subsRow.addColumn(new Column(String.valueOf(u2.getLastname())));
					subsRow.addColumn(new Column(String.valueOf(u2.getNickname())));
					// hier werden die Millisekunden vom Create_Date rausgeschnitten
					String fullcdate = u2.getCreateDate().toString();
					String[] cparts = fullcdate.split(" ");
					String cutcdate = cparts[0];
					subsRow.addColumn(new Column(String.valueOf(cutcdate)));

					result.addRow(subsRow);

					result.setAmount("Anzahl Ihrer Abonnenten im ausgewählten Zeitraum: " + i);
					

				} else if (i >= 1) {
					result.setAmount("Anzahl Ihrer Abonnenten im ausgewählten Zeitraum: " + i);

				} else {
					result.setTitel(
							"Tut uns leid! Im ausgewählten Zeitraum hat Sie keiner abonniert");
				}

			}

		}

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.itp.shared.ReportGenerator#createAllSubsOfUserReport(de.hdm.itp.shared
	 * .bo.User, java.util.Date, java.util.Date)
	 */
	public AllSubsOfUserReport createAllSubsOfUserReport(User u, Date dateFrom, Date dateTill)
			throws IllegalArgumentException {
		if (this.getEditorAdministration() == null) {
			return null;
		}

		AllSubsOfUserReport result = new AllSubsOfUserReport();

		int i = 0;

		result.setCreateDate(new Date());

		Row headline = new Row();

		headline.addColumn(new Column("Vorname des abonnierten Benutzers"));
		headline.addColumn(new Column("Nachname des abonnierten Benutzers"));
		headline.addColumn(new Column("Nickname des abonnierten Benutzers"));
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
				// hier werden die Millisekunden vom Create_Date rausgeschnitten
				String fullcdate = u2.getCreateDate().toString();
				String[] cparts = fullcdate.split(" ");
				String cutcdate = cparts[0];
				subsRow.addColumn(new Column(String.valueOf(cutcdate)));
				result.addRow(subsRow);
				
				result.setTitel("Hier sehen Sie Ihre Abonnements");
				result.setAmount("Anzahl Ihrer Abonnements: " + i);

			} else {
				if (s.getCreateDate().after(dateFrom) && s.getCreateDate().before(dateTill)) {
					i++;
					User u2 = this.admin.getUserById(s.getTargetUser());
					subsRow.addColumn(new Column(String.valueOf(u2.getFirstname())));
					subsRow.addColumn(new Column(String.valueOf(u2.getLastname())));
					subsRow.addColumn(new Column(String.valueOf(u2.getNickname())));
					// hier werden die Millisekunden vom Create_Date rausgeschnitten
					String fullcdate = u2.getCreateDate().toString();
					String[] cparts = fullcdate.split(" ");
					String cutcdate = cparts[0];
					subsRow.addColumn(new Column(String.valueOf(cutcdate)));

					result.addRow(subsRow);

					result.setAmount("Anzahl Ihrer Abonnements im augewählten Zeitraum: " + i);

				} else if (i >= 1) {
					result.setAmount("Anzahl Ihrer Abonnements im ausgewählten Zeitraum: " + i);

				} else {
					result.setTitel("Tut uns leid! Im ausgewählten Zeitraum wurde kein Benutzer von Ihnen abonniert");
				}

			}

		}

		return result;

	}


/**
	 * Creates the all my comments from post from user report.
	 *
	 * @param myComment the my comment
	 * @param p the p
	 * @return the all my comments from post from user report
	 */
	//
	public AllMyCommentsFromPostFromUserReport createAllMyCommentsFromPostFromUserReport(Vector<Comment> myComment,
			Post p) {
		if (this.getEditorAdministration() == null) {
			return null;
		}
		AllMyCommentsFromPostFromUserReport result = new AllMyCommentsFromPostFromUserReport();

		result.setCreateDate(new Date());

		result.setTitel("Beitrag: " + p.getContent() + " von " + this.admin.getUserById(p.getOwnerId()).getNickname());

		Row headline2 = new Row();

		headline2.addColumn(new Column("Kommentar"));
		headline2.addColumn(new Column("Erstellungsdatum"));
		headline2.addColumn(new Column("Änderungsdatum"));
		result.addRow(headline2);

		for (Comment c : myComment) {

			Row commentRow = new Row();

			String fullcdate2 = c.getCreateDate().toString();
			String[] cparts2 = fullcdate2.split(" ");
			String cutcdate2 = cparts2[0];

			String fullmdate2 = c.getModDate().toString();
			String[] mparts2 = fullmdate2.split(" ");
			String cutmdate2 = mparts2[0];

			commentRow.addColumn(new Column(String.valueOf(c.getText())));
			commentRow.addColumn(new Column(String.valueOf(cutcdate2)));
			commentRow.addColumn(new Column(String.valueOf(cutmdate2)));

			result.addRow(commentRow);

		}

		return result;
	}

	/* (non-Javadoc)
	 * @see de.hdm.itp.shared.ReportGenerator#createAllCommentsOfAllPostsFromUserReportForm(de.hdm.itp.shared.bo.User, java.util.Date, java.util.Date)
	 */
	public AllCommentsOfAllPostsFromUserReport createAllCommentsOfAllPostsFromUserReportForm(User u, Date dateFrom,
			Date dateTill) throws IllegalArgumentException {

		if (this.getEditorAdministration() == null) {
			return null;
		}
		AllCommentsOfAllPostsFromUserReport result = new AllCommentsOfAllPostsFromUserReport();

		result.setTitel("Alle Ihre Kommentare mit den dazugehörigen Beiträgen");

		result.setCreateDate(new Date());

		Vector<Post> posts = this.admin.getAllPosts();
		
		if (dateFrom == null) {

			for (Post p : posts) {

				Vector<Comment> comments = this.admin.getCommentsOfPost(p);

				Vector<Comment> myComments = new Vector<Comment>();

				if (comments.size() != 0) {

					for (Comment c : comments) {

						if (c.getOwnerId() == u.getId()) {

							myComments.add(c);

						}
						
					}
					if (myComments.isEmpty() == false) {

						result.addSubReport(this.createAllMyCommentsFromPostFromUserReport(myComments, p));
					}

				} else {

					SimpleParagraph errornote = new SimpleParagraph("Es wurden leider keine Kommentare gefunden");

					result.setHeader(errornote);
				}
			}

		} else {

			for (Post p : posts) {

				Vector<Comment> comments = this.admin.getCommentsOfPost(p);

				Vector<Comment> myComments = new Vector<Comment>();

				if (comments.size() != 0) {

					for (Comment c : comments) {
						if (c.getModDate().after(dateFrom) && c.getModDate().before(dateTill)) {

							if (c.getOwnerId() == u.getId()) {

								myComments.add(c);

							}

						}
					}
					if (myComments.isEmpty() == false) {

						result.addSubReport(this.createAllMyCommentsFromPostFromUserReport(myComments, p));

					}

					else {

						SimpleParagraph errornote = new SimpleParagraph("Es wurden leider keine Kommentare gefunden");

						result.setHeader(errornote);
					}

				}

			}

		}
		return result;

	}
}