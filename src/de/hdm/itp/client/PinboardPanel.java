package de.hdm.itp.client;

import java.util.Vector;
import de.hdm.itp.server.EditorAdministrationImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

/**
 * The Class PinboardPanel.
 */
public class PinboardPanel extends VerticalPanel {

	/** The editor administration. */
	private EditorAdministrationAsync editorAdministration = null;

	/** The main panel. */
	static MainPanel mainPanel = new MainPanel();

	/** The post panel. */
	VerticalPanel postPanel = new VerticalPanel();
	
	/** The pinboard panel. */
	PinboardPanel pinboardPanel;

	/** The label user. */
	Label lbl_user = new Label("Loading...");
	
	/** The label content. */
	Label lbl_content = new Label("Loading...");
	
	/** The label date. */
	Label lbl_date = new Label("Loading...");

	/** The current user. */
	User currentUser;
	
	/** The pinboard user. */
	User pinboardUser = new User();
	
	/** The pinboard post. */
	Post pinboardPost = new Post();
	

	/** The commentcounter. */
	int commentcounter = 0;

	/**
	 * Gets the main panel.
	 *
	 * @return the main panel
	 */
	public static MainPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * Sets the main panel.
	 *
	 * @param mainPanel the new main panel
	 */
	public static void setMainPanel(MainPanel mainPanel) {
		PinboardPanel.mainPanel = mainPanel;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {

		super.onLoad();
		/** Hier wird die Instanz des AsyncronenInterfaces initialisiert
		 * dies dient im weiteren Verlauf zum Aufruf asyncroner Methoden */
		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		
		/** Hier wird der aktuelle User aus den CLientsideSettings geholt */
		currentUser = ClientsideSettings.getUser();
		pinboardPanel=this;

		// this.add(postpanel);
		/** Dieser Methodenaufruf sorgt dafür, dass die Pinnwand beim ersten betreten der Seite
		 * per Default die Pinnwand des aktuellen Users anzeigt*/
		createPinboard(currentUser);

		// this.addStyleName("Pinboard");
		// this.getElement().getStyle().setBackgroundColor("red");
		// this.setHeight("400px");

		// this.setStylePrimaryName("Pinboard");

		// this.clear();

//		this.getElement().getStyle().setBackgroundColor("red");
//		this.setHeight("400px");

	}

	/**
	 * Creates the pinboard.
	 *
	 * Diese Methode wird im Mainpanel verwendet und baut nur ein Teil der Pinnwand auf. 
	 * @param u der User dessen Pinnwand angezeigt werden soll. 
	 */
	public void createPinboard(User u) {
		editorAdministration.getUserById(u.getId(), new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());

			}

			@Override
			public void onSuccess(User result) {
				pinboardUser = result;

			}

		});
		postPanel.clear();
		postPanel.setStylePrimaryName("postboxOuter");

		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}

		editorAdministration.getAllPostsOfUser(u, new AsyncCallback<Vector<Post>>() {

			public void onFailure(Throwable t) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(t.getMessage());
			}

			public void onSuccess(Vector<Post> result) {
				// postsPanel.clear();
				Collections.reverse(result);
				for (Post p : result) {
					postPanel.add(createPostPanel(p));

					// postsPanel.add(postpanel.createPost2(p));

				}
			}

		});
		this.add(postPanel);

	}

	/**
	 * Erstellt das PostPanel.
	 * 
	 * Diese Methode wird iner der createPinboard-Methode dieser Klasse aufgerufen.
	 * Sie Baut die einzelen Posts für das PinboardPanel auf 
	 * in einem VerticalPanel und gibt dieses Panel samt Inhalt zurück.
	 *
	 * @param post Der Post der dargestellt werden soll
	 * @return Das fetige Verticalpanel mit der enthaltenen Darstellung des Posts
	 */
	public VerticalPanel createPostPanel(final Post post) {

		final VerticalPanel postsPanel = new VerticalPanel();
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		final Post pinboardPost = post;
		
		buttonPanel.setStylePrimaryName("buttonPanel");

		editorAdministration.getAllLikesOfPost(post, new AsyncCallback<Vector<Like>>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());
			}

			@Override
			public void onSuccess(Vector<Like> result) {
				boolean isLiked = false;
				for (Like l : result) {
					if (l.getOwnerId() == currentUser.getId()) {
						isLiked = true;
					}
				}
				if (isLiked) {
					postsPanel.add(new StyleLabel(pinboardUser.getFirstname() + " '"
							+ pinboardUser.getNickname() + "' " + pinboardUser.getLastname() + ": ", "postuser_lbl"));
					postsPanel.add(new StyleLabel(post.getContent(), "content"));
					//hier werden die Millisekunden vom Mod_Date rausgeschnitten
					String fulldate = post.getModDate().toString();
					String[] parts = fulldate.split("[.]");
					String cutdate = parts[0];
					postsPanel.add(new StyleLabel("Zuletzt geändert am " + cutdate, "postdate_lbl"));
					//postsPanel.add(new StyleLabel("So vielen Leuten gefällt das: " + result.size(), "postdate_lbl"));
					final int likes = result.size();

					editorAdministration.getCommentsOfPost(post, new AsyncCallback<Vector<Comment>>() {

						@Override
						public void onFailure(Throwable caught) {
							ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());

						}

						@Override
						public void onSuccess(Vector<Comment> result) {
							commentcounter = result.size();
							Collections.reverse(result);
							
							//postsPanel.add(new StyleLabel("So viele Leuten haben das kommentiert: " + result.size(),
							//		"postdate_lbl"));
									
							
							Button unlike = new Button("Unlike ("+ likes+")", new UnlikeClickHandler(pinboardPost));
							unlike.setStylePrimaryName("submit");
							buttonPanel.add(unlike);

							if (pinboardUser.getId() == currentUser.getId()) {
								
								Button edit = new Button("Bearbeiten", new UpdatePostClickHandler(pinboardPost));
								edit.setStylePrimaryName("submit");
								buttonPanel.add(edit);
								Button delete = new Button("Löschen", new DeletePostClickHandler(pinboardPost));
								delete.setStylePrimaryName("submit");
								buttonPanel.add(delete);
							}
							Button comment = new Button("Kommentieren("+ result.size()+")", new CommentClickHandler(pinboardPost));
							comment.setStylePrimaryName("submit");
							buttonPanel.add(comment);
							postsPanel.add(buttonPanel);

						}

					});

				} else {
					postsPanel.add(new StyleLabel(pinboardUser.getFirstname() + " '"
							+ pinboardUser.getNickname() + "' " + pinboardUser.getLastname() + ": ", "postuser_lbl"));
					postsPanel.add(new StyleLabel(post.getContent(), "content"));
					
					//hier werden die Millisekunden vom Mod_Date rausgeschnitten
					String fulldate = post.getModDate().toString();
					String[] parts = fulldate.split("[.]");
					String cutdate = parts[0];
					postsPanel.add(new StyleLabel("Zuletzt geändert am " + cutdate, "postdate_lbl"));
					//postsPanel.add(new StyleLabel("So vielen Leuten gefällt das: " + result.size(), "postdate_lbl"));
					final int likes = result.size();
					editorAdministration.getCommentsOfPost(post, new AsyncCallback<Vector<Comment>>() {

						@Override
						public void onFailure(Throwable caught) {
							ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());

						}

						@Override
						public void onSuccess(Vector<Comment> result) {
							commentcounter = result.size();

//							postsPanel.add(new StyleLabel("So viele Leuten haben das kommentiert: " + result.size(),
//									"postdate_lbl"));
							Button like = new Button("Like ("+ likes+")", new LikeClickHandler(pinboardPost));
							like.setStylePrimaryName("submit");
							buttonPanel.add(like);
							if (pinboardUser.getId() == currentUser.getId()) {

								Button edit = new Button("Bearbeiten", new UpdatePostClickHandler(pinboardPost));
								edit.setStylePrimaryName("submit");
								buttonPanel.add(edit);
								Button delete = new Button("Löschen", new DeletePostClickHandler(pinboardPost));
								delete.setStylePrimaryName("submit");
								buttonPanel.add(delete);
							}
							Button comment = new Button("Kommentieren("+ result.size()+")", new CommentClickHandler(pinboardPost));
							comment.setStylePrimaryName("submit");
							buttonPanel.add(comment);
							postsPanel.add(buttonPanel);
						}

					});

				}

			}

		});
		postsPanel.setStylePrimaryName("postbox");
//		postsPanel.setSpacing(20);
//		postPanel.setBorderWidth(2);

		return new DefaultVerticalPanel(postsPanel).newvp;

	}

	/** Hier startet die Erstellung der ClickHandler, welche den Buttons im PinboardPanel hinzugefügt werden*/
	
	/**
	 * LikeClickHandler.
	 * Der Clickhandler für den Like Button.
	 * Führ das Setzten eines Likes aus.
	 */
	private class LikeClickHandler implements ClickHandler {
		
		/** The post. */
		Post post = new Post();

		/**
		 * Instantiates a new like click handler.
		 *
		 * @param p der Post der geliked werden soll
		 */
		public LikeClickHandler(Post p) {
			post = p;

		}

		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		@Override
		public void onClick(ClickEvent event) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			currentUser = ClientsideSettings.getUser();
			//TODO: Entfernen vor der Präsentation?
			if(currentUser.getId() == post.getOwnerId()) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox("Sie haben nicht ernsthaft Ihren eigenen Post geliked? Das ist ziemlich peinlich! Besser Sie entfernen das...");
			}

			editorAdministration.createLike(post, currentUser, new AsyncCallback<Like>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());

				}

				@Override
				public void onSuccess(Like result) {
					// TODO: das funktioniert noch nicht
					User us = new User();
					us.setId(post.getOwnerId());
					// mainPanel.createPinnboard(us);
					createPinboard(us);
				}

			});

		}

	}

	/**
	 * UpdatePostClickHandler.
	 * Der ClickHandler für den Bearbeiten Button von einem Post.
	 * Dieser ruft eine DialogBox auf, welche das bearbeiten eines Posts erlaubt.
	 */
	private class UpdatePostClickHandler implements ClickHandler {
		
		/** The post. */
		Post post = new Post();

		/**
		 * Instantiates a new update post click handler.
		 *
		 * @param p der Post der geändert werden soll
		 */
		public UpdatePostClickHandler(Post p) {
			post = p;

		}

		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		@Override
		public void onClick(ClickEvent event) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			currentUser = ClientsideSettings.getUser();

			ClientsideFunctions.UpdatePostDialogBox updatePostDB = new ClientsideFunctions.UpdatePostDialogBox(post,pinboardPanel);
			
		

		}

	}

	/**
	 * DeletePostClickHandler.
	 * Der ClickHandler wird dem Löschen Button vom Post hinzugefügt.
	 * Dieser löscht den angegebenen Post.
	 */
	private class DeletePostClickHandler implements ClickHandler {
		
		/** The post. */
		Post post = new Post();

		/**
		 * Instantiates a new delete post click handler.
		 *
		 * @param p der Post der gelöscht werden soll.
		 */
		public DeletePostClickHandler(Post p) {
			post = p;
		}

		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		@Override
		public void onClick(ClickEvent event) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			currentUser = ClientsideSettings.getUser();

			editorAdministration.deletePost(post, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());

				}

				@Override
				public void onSuccess(Void result) {
					User user = new User();
					user.setId(post.getOwnerId());
					createPinboard(user);
				}

			});

		}

	}

	/**
	 * UnlikeClickHandler.
	 * Der ClcikHandler wird dem Unlike Button hinzugefügt.
	 * Dieser löscht ein gesetztes Like von dem aktuellen User auf einem spezifischen Post
	 */
	private class UnlikeClickHandler implements ClickHandler {
		
		/** The post. */
		Post post = new Post();

		/**
		 * Instantiates a new unlike click handler.
		 *
		 * @param p der Post dessen Like entfernt werden soll
		 */
		public UnlikeClickHandler(Post p) {
			post = p;

		}

		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		@Override
		public void onClick(ClickEvent event) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			currentUser = ClientsideSettings.getUser();
			//TODO: Entfernen vor der Präsentation?
			if(currentUser.getId() == post.getOwnerId()) {
				ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox("Immerhin sind Sie einsichtig.");
			}

			Like like = new Like();
			like.setOwnerId(currentUser.getId());
			like.setPostId(post.getId());

			editorAdministration.deleteLike(like, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideFunctions.AlertDialogBox adb = new ClientsideFunctions.AlertDialogBox(caught.getMessage());

				}

				@Override
				public void onSuccess(Void result) {
					// TODO: das funktioniert noch nicht
					User us = new User();
					us.setId(post.getOwnerId());
					// mainPanel.createPinnboard(us);
					createPinboard(us);
				}

			});

		}

	}

	/**
	 * CommentClickHandler.
	 * Der Clickhandler der dem Kommentieren Button eines Posts hinzugefügt wird.
	 * Dieser erstellt eine DilogBox, welche es ermöglicht ein Kommentar zu erstellen,
	 * zu löschen, zu bearbeiten oder zu lesen.
	 */
	private class CommentClickHandler implements ClickHandler {
		
		/** The post. */
		Post post = new Post();

		/**
		 * Instantiates a new comment click handler.
		 *
		 * @param p der Post dessen CommentBox angezeigt werden soll
		 */
		public CommentClickHandler(Post p) {
			post = p;

		}

		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		@Override
		public void onClick(ClickEvent event) {
			ClientsideFunctions.CommentDialogBox cdb = new ClientsideFunctions.CommentDialogBox(post, pinboardPanel);

		}

	}
}