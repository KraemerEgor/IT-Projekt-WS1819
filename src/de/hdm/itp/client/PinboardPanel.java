package de.hdm.itp.client;

import java.util.Vector;
import de.hdm.itp.server.EditorAdministrationImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

public class PinboardPanel extends ScrollPanel {
	
	Label name_lbl = new Label("Nils Kaper");
	
	public void onLoad() {
		
		
		super.onLoad();
		this.addStyleName("Pinboard");
		this.add(name_lbl);
		this.getElement().getStyle().setBackgroundColor("red");
		this.setHeight("400px");
		name_lbl.addStyleName("TestLabel");
		
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
		
		ScrollPanel pinboard = new ScrollPanel();
		
		AsyncCallback callback = new AsyncCallback() {
		    public void onSuccess(Object result) {
		      
				Vector<Post> vector = new Vector<Post>();
				vector = this.getAllPostsOfUser(u);
				
				for (Post post : vector) {
				    
					createPost(post);
					
				}
		    	
		    }

		    public void onFailure(Throwable caught) {
		      // do some UI stuff to show failure
		    }
		  };
				

		
	}
	
	private void createPost(Post p) {
		
		VerticalPanel post = new VerticalPanel();
		String text = new String (p.getContent());
		Label content = new Label(text);
		
		post.add(content);
		
		HorizontalPanel options = new HorizontalPanel();
		
		/* In options: Liken, Disliken, Löschen (falls berechtigt)*/
		
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
		options.add(dislike);
		
		/* Abfrage ob currentUser = PinboardOwner -> falls ja, Möglichkeit zum Löschen */
		
		post.add(options);
		
		
		AsyncCallback callback = new AsyncCallback() {
		    public void onSuccess(Object result) {
		      
				Vector<Comment> comment = new Vector<Comment>();
				comment = this.getCommentsOfPost(p);
				
				for (Comment com : comment) {
				    
					VerticalPanel comments = new VerticalPanel();
					post.add(comments);
					
				}
		    	
		}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
	}


}
