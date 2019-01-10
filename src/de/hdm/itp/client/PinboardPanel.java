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
import java.util.Collections;

import de.hdm.itp.shared.EditorAdministrationAsync;
import de.hdm.itp.shared.bo.Comment;
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

public class PinboardPanel extends VerticalPanel {
	
	private EditorAdministrationAsync editorAdministration = null;
	
	VerticalPanel postPanel = new VerticalPanel();
	
	
	Label lbl_user = new Label("Loading...");
	Label lbl_content = new Label("Loading...");
	Label lbl_date = new Label("Loading...");
	
	User currentUser;
	User pinboardUser = new User();
	Post pinboardPost = new Post();

	
	public void onLoad() {

		
		super.onLoad();
		if (editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		currentUser = ClientsideSettings.getUser();
		
		//this.add(postpanel);	
		createPinboard(currentUser);
				
		
		//this.addStyleName("Pinboard");
		//this.getElement().getStyle().setBackgroundColor("red");
		//this.setHeight("400px");
				

		
		//this.setStylePrimaryName("Pinboard");
		
		//this.clear();
		
		
		
	
		
//		this.getElement().getStyle().setBackgroundColor("red");
//		this.setHeight("400px");

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
		pinboardUser= u;
		postPanel.clear();
		postPanel.setStylePrimaryName("postbox");
		
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}		
		
		editorAdministration.getAllPostsOfUser(u, new AsyncCallback<Vector<Post>>() {
			
			
			public void onFailure(Throwable t) {
				Window.alert(t.getMessage());}		
			public void onSuccess(Vector<Post> result) {
				//postsPanel.clear();
				Collections.reverse(result);
					for(Post p: result) {
						
						postPanel.add(createPostPanel(p));
						
						
						
						//TODO: das funktioniert noch nichtÏ
						//postsPanel.add(postpanel.createPost2(p));
					
					}}
			
		});
		this.add(postPanel);
		
		
	}
	public VerticalPanel createPostPanel(Post p) {
		Window.alert("Creating Post: "+p.getContent());
		final VerticalPanel postsPanel = new VerticalPanel();
		
		//postsPanel.clear();
		pinboardPost=p;
		
		editorAdministration.getAllLikesOfPost(p, new AsyncCallback<Vector<Like>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Vector<Like> result) {
				boolean isLiked = false;
				for(Like l:result) {
					if(l.getOwnerId()==currentUser.getId()) {
						isLiked = true;
					}
				}
				if(isLiked) {
					postsPanel.add(new StyleLabel("Post von "+pinboardUser.getFirstname()+" '"+pinboardUser.getNickname()+"' "+pinboardUser.getLastname()+": ","postuser_lbl"));
					postsPanel.add(new StyleLabel(p.getContent(),"posttext_lbl"));
					postsPanel.add(new StyleLabel("Zuletzt geändert am "+p.getModDate(),"postdate_lbl"));
					//postsPanel.add(new StyleLabel("Zuletzt geändert am "+pinboardPost.getModDate(),"search_lbl"));
					postsPanel.add(new Label("Post ID:"+p.getId()+" User ID:"+pinboardUser.getId()));
					postsPanel.add(new StyleLabel("isLiked ist auf true","search_lbl"));
					postsPanel.add(new Button("Unlike",new UnlikeClickHandler(p)));	
					//postPanel.add(postsPanel);
				}else {
					postsPanel.add(new StyleLabel("Post von "+pinboardUser.getFirstname()+" '"+pinboardUser.getNickname()+"' "+pinboardUser.getLastname()+": ","postuser_lbl"));
					postsPanel.add(new StyleLabel(p.getContent(),"posttext_lbl"));
					postsPanel.add(new StyleLabel("Zuletzt geändert am "+p.getModDate(),"postdate_lbl"));
					//postsPanel.add(new StyleLabel("Zuletzt geändert am "+pinboardPost.getModDate(),"search_lbl"));
					postsPanel.add(new Label("Post ID:"+p.getId()+" User ID:"+pinboardUser.getId()));
					postsPanel.add(new StyleLabel("isLiked ist auf false","search_lbl"));
					postsPanel.add(new Button("Like",new LikeClickHandler(p)));
					//postPanel.add(postsPanel);
					
				}
				
				
			}
			
		});
		//ich drehe mich im Kreis: hier wird immer der neuste post zurückgegeben anstatt des jeweils passenden
		//TODO: im commits schauen als es noch funktioniert hat
		
		return new DefaultVerticalPanel(postsPanel).newvp;
		
		
	}



	//TODO: ClickHandler haben sich einfach so nicht mehr compilieren lassen... ohne Grund
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
			Window.alert("try to set like to: "+post.getContent()+" from: "+currentUser.getFirstname()+" "+currentUser.getId());
			editorAdministration.createLike(post, currentUser, new AsyncCallback<Like>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
					
				}

				@Override
				public void onSuccess(Like result) {
					Window.alert("like gesetzt auf '"+post.getContent()+"'");					
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
			Window.alert("try to delete like from: "+post.getContent()+" from: "+currentUser.getFirstname()+" "+currentUser.getId());
			Like like = new Like();
			like.setOwnerId(currentUser.getId());
			like.setPostId(post.getId());
			
			editorAdministration.deleteLike(like, new AsyncCallback<Void>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
					
				}

				@Override
				public void onSuccess(Void result) {
					Window.alert("like entfernt auf '"+post.getContent()+"'");					
				}
				
			});
			
			
		}
		
	}	
}