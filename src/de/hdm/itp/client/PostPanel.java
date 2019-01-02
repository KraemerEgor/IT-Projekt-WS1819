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

public class PostPanel extends VerticalPanel {
	
	private EditorAdministrationAsync editorAdministration = null;
	
	User currentUser;
	
	public void onLoad() {

		
		this.clear();
		super.onLoad();
		
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		
			
		this.addStyleName("Post");
		this.setStylePrimaryName("Post");
		
		Label lbl = new Label("Post:");
		this.add(lbl);	
		
		
		
		this.getElement().getStyle().setBackgroundColor("darkgoldenrod");

	}

	
	
	
	/* Post-Schema (TODO später löschen):
	 * 			
	 * 				_________________________
	 * 				|   	Text-			|
	 * 	 		/	|	Beitrag (Content)	|
	 * 			P	|_______________________|
	 * 	 			|Optionen(Like/Komnt/DEL|
	 * 			O	|_______________________|
	 *	 			|	Kommentar 1			|
	 * 	 		S	|_______________________|
	 * 				|	Kommentar 2			|
	 * 			T	|_______________________|
	 * 		 	\	|	Kommentar ...		|
	 * 				|_______________________|
	 * */
	
	public void createPost(Post p) {
		
		/*
		 * Post-Text-Teil 
		 * */
		
		VerticalPanel vpost = new VerticalPanel();
		Label ueberschrift = new Label("Post:");
		vpost.add(ueberschrift);
		
		Label content = new Label(p.getContent());
		vpost.add(content);
		

		/*
		 * Optionen-Teil 
		 * */
		
		
		HorizontalPanel options = new HorizontalPanel();
		
		/*
		 * Like setzen TODO Callback aussstehend
		 * */		
		
		Button like = new Button("Like");
		like.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		    	editorAdministration.createLike(p, null);;
		    }
		});
		options.add(like);
		
		/*
		 * Like löschen TODO Callback aussstehend:
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
		    	like.setPostId(p.getId());
		    	
		    	editorAdministration.deleteLike(like, null);
		    }
		});
		options.add(dislike);
		
		/*
		 * Neuen Kommentar anlegen TODO Callback aussstehend:
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
		    	
		    	editorAdministration.createComment(p, neuerKommentar.CommentDialog(), null); //null = callback
		    }
		});
		options.add(comment);
		
		/*
		 * Post löschen TODO Callback aussstehend:
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
		      editorAdministration.deletePost(p, null);
		    }
		});
		options.add(delete);
		}
		
		vpost.add(options);
		
		
		/*
		 * Kommentar-Teil 
		 * */
		
		
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
		
}
	
	/*
	 * Wie unter "Post löschen" ausgeführt:
	 * 
	 * Die Methode prüft, ob die Pinnwand dem
	 * derzeit angemeldeten Nutzer "gehört".
	 * In diesem Fall werden weitere Optionen
	 * ermöglicht.
	 */
		
		private Boolean permissionCheck(User u) {
			/*if (u == currentUser)
			return true;
			else return false;*/
			return true;
		}
		
}