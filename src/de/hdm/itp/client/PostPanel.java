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
import de.hdm.itp.shared.bo.Like;
import de.hdm.itp.shared.bo.Post;
import de.hdm.itp.shared.bo.User;

/**
 * The Class PostPanel.
 */
public class PostPanel extends VerticalPanel {
	
	/** The editor administration. */
	private EditorAdministrationAsync editorAdministration = null;
	
	/** The vpost. */
	VerticalPanel vpost = new VerticalPanel();
	
	/** The options. */
	HorizontalPanel options = new HorizontalPanel();
	
	/** The lbl. */
	Label lbl = new Label("Post:");
	
	/** The current user. */
	User currentUser;

	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {

		
		//this.clear();
		super.onLoad();
		this.add(lbl);
		
		
			
		this.addStyleName("Post");
		this.setStylePrimaryName("Post");
		
		
			
		
		
		
		this.getElement().getStyle().setBackgroundColor("darkgoldenrod");

	}
	
	/**
	 * Creates the post.
	 *
	 * @param p the p
	 * @return the vertical panel
	 */
	public VerticalPanel createPost(Post p) {
		Window.alert("kommt hier rein");
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		
		Label ueberschrift = new Label("Post:");
		vpost.add(ueberschrift);
		
		Label content = new Label(p.getContent());
		vpost.add(content);
		
		Button like = new Button("Like");
		like.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		    	//editorAdministration.createLike(p, null);
		    	Window.alert("Hier soll ein Like gesetzt werden");
		    }
		});
		options.add(like);
		
		/*
		 * Like löschen
		 * 
		 * Zum Löschen muss ein Like-Objekt an
		 * deleteLike() übergeben werden. Da dieses
		 * derzeit "nicht existiert" wird es neu
		 * erstellt. Dazu werden Benutzer und Post
		 * benötigt und der zu löschende Like 
		 * anschließend an die Methode weitergegeben.
		 * 
		 * -> Es wird "on the fly" ein Objekt erzeugt,
		 * dass dem ursprünglich vom User gesetzten
		 * Like entspricht.
		 */
		Button dislike = new Button("Dislike");
		dislike.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		    	Like like = new Like();
		    	like.setOwnerId(currentUser.getId());
		    	//like.setPostId(p.getId());
		    	
		    	//editorAdministration.deleteLike(like, null);
		    	Window.alert("Hier soll der Like von: "+like.getOwnerId()+" gelöscht werden");
		    }
		});
		options.add(dislike);
		
		/*
		 * Neuen Kommentar anlegen
		 * 
		 * Hierzu soll der Kommentar-Text aus einem erscheinenden
		 * DialogPanel übernommen werden und dann ein Kommentar
		 * mit diesem Inhalt hinzugefügt werden
		 */
		
		Button comment = new Button("Kommentieren");
		comment.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		    	DialogPanel neuerKommentar = new DialogPanel();
		    	options.add(neuerKommentar);
		    	
		    	//editorAdministration.createComment(p, neuerKommentar.CommentDialog(), null); //null = callback
		    }
		});
		options.add(comment);
		
		/*
		 * Post löschen
		 * 
		 * Nur wenn der derzeitige Nutzer gleich
		 * dem Pinnwand-Eigentümer ist, wird die
		 * Möglichkeit zum Löschen eines Posts
		 * eingeräumt. In diesem Fall kann der
		 * Post entfernt werden.
		 */
		
		if (permissionCheck(currentUser)) {
		Button delete = new Button("Löschen");
		delete.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		     // editorAdministration.deletePost(p, null);
		    }
		});
		options.add(delete);
		}
		
		vpost.add(options);
		

		editorAdministration.getCommentsOfPost(p, new AsyncCallback<Vector<Comment>>() {
			public void onFailure(Throwable t) {
				Window.alert(t.getMessage());}		
			
			public void onSuccess(Vector<Comment> result) {
					for(Comment c: result) {
						Label comnt = new Label(c.getText());
						vpost.add(comnt);
					}
			
			}


		});
		
		this.add(vpost);
		return vpost;
		
}
	
	/*
	 * Wie unter "Post löschen" ausgeführt:
	 * 
	 * Die Methode prüft, ob die Pinnwand dem
	 * derzeit angemeldeten Nutzer "gehört".
	 * In diesem Fall werden weitere Optionen
	 * ermöglicht.
	 */
		
		/**
	 * Permission check.
	 *
	 * @param u the u
	 * @return the boolean
	 */
	private Boolean permissionCheck(User u) {
			/*if (u == currentUser)
			return true;
			else return false;*/
			return true;
		}
		
		/**
		 * Creates the post 2.
		 *
		 * @param post the post
		 * @return the vertical panel
		 */
		public VerticalPanel createPost2(Post post){
			VerticalPanel result = new VerticalPanel();
			Label lbl = new Label(post.getContent());
			result.add(lbl);
			return result;
		}
		
}