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

public class PinboardPanel extends VerticalPanel {
	
	private EditorAdministrationAsync editorAdministration = null;
	private PostPanel postpanel;
	
	VerticalPanel post = new VerticalPanel();
	Label lbl = new Label("HulapaluHulapaluHulapalu");
	
	User currentUser;

	
	public void onLoad() {

		
		this.clear();
		super.onLoad();
		this.add(lbl);
		currentUser.setId(10000001);
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		
			
		
		
		this.addStyleName("Pinboard");
		//this.getElement().getStyle().setBackgroundColor("red");
		//this.setHeight("400px");
				

		super.onLoad();
		this.setStylePrimaryName("Pinboard");
		
		if(editorAdministration == null) {
			editorAdministration = ClientsideSettings.getAdministration();
		}
		//this.clear();
		
		this.add(lbl);
		this.add(new Label("hello"));	
		
	
		
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
		
		editorAdministration.getAllPostsOfUser(u, new AsyncCallback<Vector<Post>>() {
			public void onFailure(Throwable t) {
				Window.alert(t.getMessage());}		
			
			public void onSuccess(Vector<Post> result) {
					for(Post p: result) {
					postpanel.createPost(p);
					}}
		});
		
	}
		
}