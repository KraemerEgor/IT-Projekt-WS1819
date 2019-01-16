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

public class PinboardPanel extends VerticalPanel {

	private EditorAdministrationAsync editorAdministration = null;

	static MainPanel mainPanel = new MainPanel();

	VerticalPanel postPanel = new VerticalPanel();
	PinboardPanel pinboardPanel;

	Label lbl_user = new Label("Loading...");
	Label lbl_content = new Label("Loading...");
	Label lbl_date = new Label("Loading...");

	User currentUser;
	User pinboardUser = new User();
	Post pinboardPost = new Post();
	

	int commentcounter = 0;

	public static MainPanel getMainPanel() {
		return mainPanel;
	}

	public static void setMainPanel(MainPanel mainPanel) {
		PinboardPanel.mainPanel = mainPanel;
	}

	public void onLoad() {

		super.onLoad();
		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		currentUser = ClientsideSettings.getUser();
		pinboardPanel=this;

		// this.add(postpanel);
		createPinboard(currentUser);

		// this.addStyleName("Pinboard");
		// this.getElement().getStyle().setBackgroundColor("red");
		// this.setHeight("400px");

		// this.setStylePrimaryName("Pinboard");

		// this.clear();

//		this.getElement().getStyle().setBackgroundColor("red");
//		this.setHeight("400px");

	}

	/*
	 * Pinboard-Schema (TODO später löschen):
	 * 
	 * _________________________ | Text- | / | Beitrag (Content) | P
	 * |_______________________| / |Optionen(Like/Komnt/DEL| P O
	 * |_______________________| | Kommentar 1 | I S |_______________________| |
	 * Kommentar 2 | N T |_______________________| \ | Kommentar ... | B
	 * |_______________________|
	 * 
	 * O _________________________ | Text- | A / | Beitrag (Content) | P
	 * |_______________________| R |Optionen(Like/Komnt/DEL| O
	 * |_______________________| D | Kommentar 1 | \ S |_______________________| |
	 * Kommentar 2 | T |_______________________| \ | Kommentar ... |
	 * |_______________________|
	 * 
	 */

	public void createPinboard(User u) {
		editorAdministration.getUserById(u.getId(), new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());

			}

			@Override
			public void onSuccess(User result) {
				pinboardUser = result;

			}

		});
		postPanel.clear();
		postPanel.setStylePrimaryName("postbox");

		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}

		editorAdministration.getAllPostsOfUser(u, new AsyncCallback<Vector<Post>>() {

			public void onFailure(Throwable t) {
				Window.alert(t.getMessage());
			}

			public void onSuccess(Vector<Post> result) {
				// postsPanel.clear();
				Collections.reverse(result);
				for (Post p : result) {

					postPanel.add(createPostPanel(p));

					// TODO: das funktioniert noch nichtÏ
					// postsPanel.add(postpanel.createPost2(p));

				}
			}

		});
		this.add(postPanel);

	}

	public VerticalPanel createPostPanel(Post post) {

		final VerticalPanel postsPanel = new VerticalPanel();
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		final Post pinboardPost = post;
		
		buttonPanel.setStylePrimaryName("buttonPanel");

		editorAdministration.getAllLikesOfPost(post, new AsyncCallback<Vector<Like>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
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
					postsPanel.add(new StyleLabel("Post von " + pinboardUser.getFirstname() + " '"
							+ pinboardUser.getNickname() + "' " + pinboardUser.getLastname() + ": ", "postuser_lbl"));
					postsPanel.add(new StyleLabel(pinboardPost.getContent(), "posttext_lbl"));
					postsPanel.add(new StyleLabel("Zuletzt geändert am: " + pinboardPost.getModDate(), "postdate_lbl"));
					// postsPanel.add(new StyleLabel("Zuletzt geändert am
					// "+pinboardPost.getModDate(),"search_lbl"));
					postsPanel.add(new Label("Post ID:" + pinboardPost.getId() + " User ID:" + pinboardUser.getId()));
					postsPanel.add(new StyleLabel("isLiked ist auf true.", "search_lbl"));
					postsPanel.add(new StyleLabel("So vielen Leuten gefällt das: " + result.size(), "search_lbl"));
					editorAdministration.getCommentsOfPost(pinboardPost, new AsyncCallback<Vector<Comment>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());

						}

						@Override
						public void onSuccess(Vector<Comment> result) {
							commentcounter = result.size();

							postsPanel.add(new StyleLabel("So viele Leuten haben das kommentiert: " + result.size(),
									"search_lbl"));
							
							Button unlike = new Button("Unlike", new UnlikeClickHandler(pinboardPost));
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
							Button comment = new Button("Kommentieren", new CommentClickHandler(pinboardPost));
							comment.setStylePrimaryName("submit");
							buttonPanel.add(comment);
							postsPanel.add(buttonPanel);

						}

					});

				} else {
					postsPanel.add(new StyleLabel("Post von " + pinboardUser.getFirstname() + " '"
							+ pinboardUser.getNickname() + "' " + pinboardUser.getLastname() + ": ", "postuser_lbl"));
					postsPanel.add(new StyleLabel(pinboardPost.getContent(), "posttext_lbl"));
					//Timestamp modDate = pinboardPost.getModDate();
					postsPanel.add(new StyleLabel("Zuletzt geändert am " + pinboardPost.getModDate(), "postdate_lbl"));
					// postsPanel.add(new StyleLabel("Zuletzt geändert am
					// "+pinboardPost.getModDate(),"search_lbl"));
					postsPanel.add(new Label("Post ID:" + pinboardPost.getId() + " User ID:" + pinboardUser.getId()));
					postsPanel.add(new StyleLabel("isLiked ist auf true.", "search_lbl"));
					postsPanel.add(new StyleLabel("So vielen Leuten gefällt das: " + result.size(), "search_lbl"));
					editorAdministration.getCommentsOfPost(pinboardPost, new AsyncCallback<Vector<Comment>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());

						}

						@Override
						public void onSuccess(Vector<Comment> result) {
							commentcounter = result.size();

							postsPanel.add(new StyleLabel("So viele Leuten haben das kommentiert: " + result.size(),
									"search_lbl"));
							Button like = new Button("Like", new LikeClickHandler(pinboardPost));
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
							Button comment = new Button("Kommentieren", new CommentClickHandler(pinboardPost));
							comment.setStylePrimaryName("submit");
							buttonPanel.add(comment);
							postsPanel.add(buttonPanel);
						}

					});

				}

			}

		});
		// ich drehe mich im Kreis: hier wird immer der neuste post zurückgegeben
		// anstatt des jeweils passenden
		// TODO: im commits schauen als es noch funktioniert hat

		return new DefaultVerticalPanel(postsPanel).newvp;

	}

	// TODO: ClickHandler haben sich einfach so nicht mehr compilieren lassen...
	// ohne Grund
	private class LikeClickHandler implements ClickHandler {
		Post post = new Post();

		public LikeClickHandler(Post p) {
			post = p;

		}

		@Override
		public void onClick(ClickEvent event) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			currentUser = ClientsideSettings.getUser();

			editorAdministration.createLike(post, currentUser, new AsyncCallback<Like>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());

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

	private class UpdatePostClickHandler implements ClickHandler {
		Post post = new Post();

		public UpdatePostClickHandler(Post p) {
			post = p;

		}

		@Override
		public void onClick(ClickEvent event) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			currentUser = ClientsideSettings.getUser();

			ClientsideFunctions.UpdatePostDialogBox updatePostDB = new ClientsideFunctions.UpdatePostDialogBox(post,pinboardPanel);
			
		

		}

	}

	private class DeletePostClickHandler implements ClickHandler {
		Post post = new Post();

		public DeletePostClickHandler(Post p) {
			post = p;
		}

		@Override
		public void onClick(ClickEvent event) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			currentUser = ClientsideSettings.getUser();

			editorAdministration.deletePost(post, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());

				}

				@Override
				public void onSuccess(Void result) {
					Window.alert("Der folgende Beitrag wurde gelöscht:" + post.getContent());

					User user = new User();
					user.setId(post.getOwnerId());
					createPinboard(user);
				}

			});

		}

	}

	private class UnlikeClickHandler implements ClickHandler {
		Post post = new Post();

		public UnlikeClickHandler(Post p) {
			post = p;

		}

		@Override
		public void onClick(ClickEvent event) {
			if (editorAdministration == null) {
				editorAdministration = ClientsideSettings.getAdministration();
			}
			currentUser = ClientsideSettings.getUser();

			Like like = new Like();
			like.setOwnerId(currentUser.getId());
			like.setPostId(post.getId());

			editorAdministration.deleteLike(like, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());

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

	private class CommentClickHandler implements ClickHandler {
		Post post = new Post();

		public CommentClickHandler(Post p) {
			post = p;

		}

		@Override
		public void onClick(ClickEvent event) {
			ClientsideFunctions.CommentDialogBox cdb = new ClientsideFunctions.CommentDialogBox(post);

		}

	}
}