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

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

public class PinboardPanel extends ScrollPanel {
	
	private EditorAdministrationAsync editorAdministration = null;
	
	ScrollPanel pinboard = new ScrollPanel();
	VerticalPanel post = new VerticalPanel();
	Label lbl = new Label("HulapaluHulapaluHulapalu");
	
	User currentUser;

	
	public void onLoad() {
		currentUser.setId(10000001);
		
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		//this.clear();
		super.onLoad();
		
		pinboard.add(lbl);
			
		
		
		this.addStyleName("Pinboard");
		this.getElement().getStyle().setBackgroundColor("red");
		this.setHeight("400px");
		this.add(pinboard);
		
	}

	
	
	
	/* Pinboard-Schema (TODO später löschen):
	 * 			
	 * 				_________________________
	 * 				|   	Text-			|
	 * 	 		/	|	Beitrag (Content)	|
	 * 			P	|_______________________|
	 * 	/			|Optionen(Like/Komnt/DEL|
	 * 	P		O	|_______________________|
	 *	 			|	Kommentar 1			|
	 * 	I		S	|_______________________|
	 * 				|	Kommentar 2			|
	 * 	N		T	|_______________________|
	 * 		 	\	|	Kommentar ...		|
	 * 	B			|_______________________|
	 * 
	 * 	O			_________________________
	 * 				|   	Text-			|
	 * 	A		/	|	Beitrag (Content)	|
	 * 			P	|_______________________|
	 * 	R			|Optionen(Like/Komnt/DEL|
	 * 			O	|_______________________|
	 *	D			|	Kommentar 1			|
	 * 	\		S	|_______________________|
	 * 				|	Kommentar 2			|
	 * 			T	|_______________________|
	 * 		 	\	|	Kommentar ...		|
	 * 				|_______________________|
	 * 
	 * */
	
	public void createPinboard(User u) {
		
		editorAdministration.getAllPostsOfUser(u, new AsyncCallback<Vector<Post>>() {
			public void onFailure(Throwable t) {
				Window.alert(t.getMessage());}		
			
			public void onSuccess(Vector<Post> result) {
					for(Post p: result) {
					createPost(p);
					}}
		});
		
	}
		
		
	private void createPost(Post p) {
		
		/*
		 * Post-Text-Teil 
		 * */
		Label ueberschrift = new Label("Post:");
		post.add(ueberschrift);
		
		Label content = new Label(p.getContent());
		post.add(content);
		
		
		/*
		 * Optionen-Teil 
		 * */
		
		
		HorizontalPanel options = new HorizontalPanel();
		
		Button like = new Button("Like");
		like.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		      // handle the click event
		    }
		});
		options.add(like);
		
		Button dislike = new Button("Dislike");
		dislike.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		      // handle the click event
		    }
		});
		options.add(dislike);
		
		Button comment = new Button("Kommentieren");
		comment.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		      // handle the click event
		    }
		});
		options.add(comment);
		
		if (permissionCheck(currentUser)) {
		Button delete = new Button("Löschen");
		delete.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		      // handle the click event
		    }
		});
		options.add(delete);
		}
		
		post.add(options);
		
		
		/*
		 * Kommentar-Teil 
		 * */
		
		
		editorAdministration.getCommentsOfPost(p, new AsyncCallback<Vector<Comment>>() {
			public void onFailure(Throwable t) {
				Window.alert(t.getMessage());}		
			
			public void onSuccess(Vector<Comment> result) {
					for(Comment c: result) {
					createComment(c);
					}
			
			}


		});
		
		pinboard.add(post);
		
};



		private void createComment(Comment c) {
			Label comnt = new Label(c.getText());
			post.add(comnt);
		}
		
		
		private Boolean permissionCheck(User u) {
			/*if (u == currentUser)
			return true;
			else return false;*/
			return true;
		}
		
};